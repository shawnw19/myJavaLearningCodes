
public class LoadMe{
    static {
        System.out.println(LoadMe.class + " loaded.");
    }

    public String output(String input){
        return "Hello "+input+"!";
    }
}
