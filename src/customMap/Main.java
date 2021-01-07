package customMap;

public class Main {
    public static void main(String [] args) {
        MyMap<String, String> mymap = new MyMap<String, String>();

        mymap.put("Kashif", "Usmani");
        mymap.put("Kashif", "Usmani1");
        mymap.put("K", "Usmani");

        System.out.println(mymap.get("K"));
    }
}
