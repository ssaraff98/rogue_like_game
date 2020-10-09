public abstract class Action {
    private String message;
    private int value;
    private char type;

    public void setMessage(String _message) { this.message = _message; }

    public void setIntValue(int _value) { this.value = _value; }

    public void setCharValue(char _type) { this.type = _type; }
}
