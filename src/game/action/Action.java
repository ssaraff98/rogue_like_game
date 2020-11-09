package game.action;

public abstract class Action {
    private String name;
    private String type;
    private String message;
    private int intValue;
    private char charValue;

    public String getName() { return this.name; }

    public void setName(String _name) {
        this.name = _name;
        // System.out.println("Name: " + this.name);
    }

    public String getType() { return this.type; }

    public void setType(String _type) {
        this.type = _type;
        // System.out.println("Type: " + this.type);
    }

    public String getMessage() { return this.message; }

    public void setMessage(String _message) {
        this.message = _message;
        // System.out.println("Message: " + this.message);
    }

    public int getIntValue() { return this.intValue; }

    public void setIntValue(int _value) {
        this.intValue = _value;
        // System.out.println("Int value: " + this.intValue);
    }

    public char getCharValue() { return this.charValue; }

    public void setCharValue(char _value) {
        this.charValue = _value;
        // System.out.println("Char value: " + this.charValue);
    }
}
