package entities;

public class DataSingleton {
    public static final DataSingleton instance = new DataSingleton();
    private int value ;

    private  DataSingleton(){}
    public static  DataSingleton getInstance() {
        return  instance
;    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
