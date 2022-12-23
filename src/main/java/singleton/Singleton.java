package singleton;

import java.util.Objects;

public class Singleton {

    private static Singleton instance;

    protected Singleton(){}

    public static Singleton getInstance() {
        if(Objects.isNull(instance)){
            instance = new Singleton();
        }
        return instance;
    }

    protected static void register(Singleton s){
        if(Objects.isNull(instance)){
            instance = s;
        }
    }
    @Override
    public String toString() {
        return "Singleton{}";
    }
}
