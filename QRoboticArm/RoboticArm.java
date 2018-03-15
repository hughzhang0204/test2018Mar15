package QRoboticArm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoboticArm {

    private Map<Integer,ArrayList<String>> boxs;

    private int currentPosition;

    private String currentLoad;

    private String currentCommands;

    private List<String> commandLoadlist;

    public static final char COMMAND_FORWARD = 'F';
    public static final char COMMAND_DROP = 'D';
    public static final char COMMAND_RETURN = 'R';
    public static final char LOAD_OPEN = '{';
    public static final char LOAD_CLOSE = '}';

    public RoboticArm(){

        this.boxs =  new HashMap<Integer,ArrayList<String>>();
        boxs.put(Position.A.getPos(),new ArrayList<String>());
        boxs.put(Position.B.getPos(),new ArrayList<String>());
        boxs.put(Position.C.getPos(),new ArrayList<String>());
        boxs.put(Position.D.getPos(),new ArrayList<String>());
        boxs.put(Position.E.getPos(),new ArrayList<String>());

        this.currentPosition = Position.START.getPos();
        this.currentLoad = "";
        this.currentCommands = "";
        commandLoadlist = new ArrayList<String>();

    }


    public static void simulateArm(String commandInput){
        RoboticArm ra = new RoboticArm();
        ra.splitCommand(commandInput);

//        for(String c : ra.commandLoadlist){
//            System.out.println(c);
//        }

        ra.executeCommand(ra.commandLoadlist);

        ra.printResults();
    }

    private void printResults(){
        String results="";

        ArrayList<String> list = boxs.get(Position.A.getPos());
        results += Position.A.getCode() +" --> "+list.toString() + ", ";

        list = boxs.get(Position.B.getPos());
        results += Position.B.getCode() +" --> "+list.toString() + ", ";

        list = boxs.get(Position.C.getPos());
        results += Position.C.getCode() +" --> "+list.toString() + ", ";

        list = boxs.get(Position.D.getPos());
        results += Position.D.getCode() +" --> "+list.toString() + ", ";

        list = boxs.get(Position.E.getPos());
        results += Position.E.getCode() +" --> "+list.toString();

        System.out.println("{"+results+"}");
    }

    private void executeCommand(List<String> commandList){

        if(commandList!=null && commandList.size()>0){
            for(String command:commandList){
                boolean startLoad = false;
                currentLoad = "";
                currentCommands = "";
                for(int i=0; i<command.length();i++){
                    char c = command.charAt(i);

                    if(!startLoad &&(c == COMMAND_FORWARD || c == COMMAND_DROP || c == COMMAND_RETURN)){
                        currentCommands += c;
                    }

                    if (c == LOAD_OPEN){
                        startLoad = true;

                    }else if (c ==  LOAD_CLOSE){
                        startLoad = false;
                    }else{
                        if(startLoad){
                            currentLoad += c;
                        }
                    }
                }
//                System.out.println("currentCommands: "+currentCommands+"  currentLoad: "+currentLoad);

                for(char c :currentCommands.toCharArray()){
                    if(c == COMMAND_FORWARD){
                        if(currentPosition < Position.E.getPos()) {
                            currentPosition++;
                        }
                    }else if (c == COMMAND_DROP){

                        ArrayList<String> currentBox = boxs.get(currentPosition);
                        if(currentBox.size()<10){
                            currentBox.add(currentLoad);
                        }
                        if(currentPosition < Position.E.getPos()) {
                            currentPosition++;
                        }

                    }else if (c == COMMAND_RETURN){
                        currentPosition = Position.START.getPos();
                    }
                }

            }
        }


    }

    private void splitCommand(String commandInput){

        int startIndex = 0;
        if(commandInput.length()>0) {
            for (int i=0; i<commandInput.length();i++) {

                char c = commandInput.charAt(i);

                if (c == LOAD_CLOSE) {
                    String newCommandLoad = commandInput.substring(startIndex,i+1);
                    startIndex = i+1;
                    commandLoadlist.add(newCommandLoad);
                }

            }
        }

    }
}
