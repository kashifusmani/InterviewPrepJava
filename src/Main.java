import java.util.*;

public class Main {

    public static void main(String[] args) {
        SmartLookup sm = new SmartLookup();
        sm.insert("a", "aValue", 1l);
        sm.insert("a", "bValue", 10l);
        sm.insert("a", "cValue", 20l);
        sm.insert("b", "aValue", 1l);
        sm.insert("b", "bValue", 10l);
        sm.insert("b", "cValue", 20l);

        System.out.println(sm.get("a"));
        System.out.println(sm.get("a", 0l));
        System.out.println(sm.get("b", 15l));
    }
}


class SmartLookup {
    private Map<String, SortedMap<Long, String>> mainLookup;
    private Map<String, Stack<Long>> timestampLookup;

    public SmartLookup() {
        mainLookup = new HashMap<>();
        timestampLookup = new HashMap<>();
    }

    public void insert(String key, String value, Long timestamp) {
        if (! mainLookup.containsKey(key)) {
            SortedMap<Long, String> sm = new TreeMap<>();
            sm.put(timestamp, value);
            mainLookup.put(key, sm);
            Stack<Long> timestampStack = new Stack<>();
            timestampStack.push(timestamp);
            timestampLookup.put(key, timestampStack);
        } else {
            mainLookup.get(key).put(timestamp, value);
            timestampLookup.get(key).push(timestamp);
        }

    }

    public String get(String key) {
        if (mainLookup.containsKey(key)) {
            Long innerKey = timestampLookup.get(key).peek();
            return mainLookup.get(key).get(innerKey);
        }
        return null;
    }

    public String get(String key, Long timestamp) {
        if (mainLookup.containsKey(key)) {
            TreeMap<Long, String> innerMap = (TreeMap<Long, String>) mainLookup.get(key);
            Map.Entry<Long, String> entry = innerMap.floorEntry(timestamp);
            if (entry!=null) {
                return entry.getValue();
            }
        }
        return null;
    }
}