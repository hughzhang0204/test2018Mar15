package QRoboticArm;

public class testRoboticArm {

    public static void main(String[] args){

        String commandInput = "dd{sdfdd}FD{foo}FFD{bar}RFFD{Foo}RFFD{World}{{{";
        RoboticArm.simulateArm(commandInput);
    }
}
