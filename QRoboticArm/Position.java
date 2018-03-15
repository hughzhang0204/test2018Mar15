package QRoboticArm;

public enum Position {

    START('S',0),
    A('A', 1),
    B('B', 2),
    C('C', 3),
    D('D', 4),
    E('E', 5);

    private char code;
    private int pos;

    Position(char code, int pos){
        this.code = code;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public char getCode() {
        return code;
    }
}
