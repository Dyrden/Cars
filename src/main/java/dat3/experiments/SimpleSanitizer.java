package dat3.experiments;

//Never use anything in here for real
public class SimpleSanitizer {
    public static String simpleSanitize(String s) {
        /*
        boolean foundTag = false;
        boolean foundTagEnd = false;
        StringBuilder newString = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c.compareTo('<') == 0) {
                foundTag = true;
            }
            if (c.compareTo('/') == 0) {
                foundTagEnd = true;
            }
            if (!foundTag) {
                newString.append(c);
            }
            if (foundTag && foundTagEnd && c.compareTo('>') == 0) {
                foundTag = false;
                foundTagEnd = false;
                newString.append("World");
            }
        }

        * */
        String javascriptPattern = "<script>(.*?)</script>";
        return s.replaceAll(javascriptPattern,"World");



        //return newString.toString();
    }
}

