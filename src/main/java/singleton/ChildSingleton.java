package singleton;

public class ChildSingleton extends Singleton{

    static {
        new ChildSingleton();
    }

    protected ChildSingleton(){
        Singleton.register(this);
    }

    @Override
    public String toString() {
        return "ChildSingleton{}";
    }

}
