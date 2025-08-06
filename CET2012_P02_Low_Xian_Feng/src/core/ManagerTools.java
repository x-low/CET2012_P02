package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerTools {
    private static final Pattern emailPolicy =
            Pattern.compile("^[^.\\-](?!.*[.]{2})(?!.*[-]{2})[\\w.\\-]*(?<![.\\-])@[a-zA-Z0-9][a-zA-Z0-9.\\-]*(?<![.\\-])(\\.[a-z]{2,3})$");
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

    /**
     * Checks if input follows email format or latin format
     * @param email Email to be checked
     * @return True for latin format, false for email format
     * @throws CustomException Thrown if email provided is neither valid
     * latin nor email format
     */
    public static boolean isLatinEmail(String email) throws CustomException {
        Matcher matcher = latinEmail.matcher(email);
        if (matcher.matches())
            return true;
        matcher = emailPolicy.matcher(email);
        if (matcher.matches())
            return false;
        throw new CustomException("Error: Invalid email format");
    }
}
