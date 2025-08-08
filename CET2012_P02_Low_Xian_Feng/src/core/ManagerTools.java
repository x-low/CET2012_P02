package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains helper methods utilised by {@code EmployeeManager}
 */
public class ManagerTools {
    /**
     * Pattern enforcing email policy only matching valid emails
     */
    private static final Pattern emailPolicy =
            Pattern.compile("^\\w(?!.*[.\\-]{2})[\\w.\\-]*" +
                    "(?<![.\\-])@[a-zA-Z0-9][a-zA-Z0-9.\\-]*" +
                    "(?<![.\\-])(\\.[a-z]{2,3})$");
    /**
     * Pattern enforcing input only matching latin letters and underscores
     * for if typical email format is not followed
     */
    private static final Pattern latinEmail =
            Pattern.compile("^\\w+$");

    /**
     * {@code ManagerTools} is non-instantiable
     * as it only contains static methods
     */
    private ManagerTools() {}

    /**
     * Converts {@code String} input to title case
     * @param str {@code String} to be made title case
     * @return {@code String} input in title case
     */
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
            else
                c = Character.toLowerCase(c);
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
