public abstract class Action {
    private String message;
    private int value;
    private char type;

    public void setMessage(String _message) { this.message = _message;
        System.out.println("Action Message: " + _message);
    }

    public void setIntValue(int _value) { this.value = _value;
        System.out.println("Action Value: " + _value);
    }

    public void setCharValue(char _type) { this.type = _type;
        System.out.println("Action Type: " + _type);
    }


}
