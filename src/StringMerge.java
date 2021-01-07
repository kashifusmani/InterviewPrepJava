import java.util.ArrayList;
import java.util.List;

public class StringMerge {
    public static void main(String[] args) {
        /*String largeString = "dogisaloyalanimal";
        String[] smalls = {"a", "alloy", "is", "god", "lamina"};
        System.out.println(isPossible(largeString, smalls));
*/
        String largeString = "thisisgood";
        String []smalls = new String[]{"so", "hit", "is", "god"};
        System.out.println(isPossible(largeString, smalls));

    }

    public static String isPossible(String large, String[] small) {

        if ((large.length()==0 && small.length>0) || (large.length() > 0 && small.length == 0)) {
            return "NO";
        }
        if (large.length()==0 && small.length==0) {
            return "YES";
        }

        for(int i = 0; i<small.length; i++) {
            List<String> perms = new ArrayList<>();
            String elem = small[i];
            getPerms(elem, "", perms);

            for (String item: perms) {
                if(large.startsWith(item.trim())) {
                    String [] reducedArray = new String[small.length - 1];
                    for (int j = 0; j<i; j++) {
                        reducedArray[j] = small[j];
                    }
                    for (int j= i+1; j<small.length; j++) {
                        reducedArray[j-1] = small[j];
                    }


                    return isPossible(large.substring(item.length()-1), reducedArray);
                }
            }

        }

        return "NO";

    }

    static void getPerms(String str, String ans, List<String> result)  {

        // If string is empty
        if (str.length() == 0) {
            result.add(ans + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            // Recurvise call
            getPerms(ros, ans + ch, result);
        }
    }

}
