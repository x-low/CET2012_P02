package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerTools {
    private static final Pattern emailPolicy =
            Pattern.compile("^[^.\\-](?![.\\-]{2})[\\w.\\-]*(?<![.\\-])@[a-zA-Z.\\-]+(?<![.\\-])(\\.[a-z]{2,3})$");
    private static final Pattern latinEmail =
            Pattern.compile("^\\w+$");

    public static String toTitleCase(String str) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = true;
        for (char c : str.toCharArray()) {
            if (Character.isSpaceChar(c))
                nextUpperCase = true;
            else if (nextUpperCase) {
                c = Character.toUpperCase(c);
                nextUpperCase = false;
            }
            sb.append(c);
        }
        return (sb.toString());
    }

    public static boolean invalidEmail(String email) {
        Matcher matcher = emailPolicy.matcher(email);
        return (!matcher.matches());
    }

    public static boolean isLatinEmail(String email) {
        Matcher matcher = latinEmail.matcher(email);
        return (matcher.matches());
    }
}
