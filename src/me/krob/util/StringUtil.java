package me.krob.util;

public class StringUtil {
    // Postcode Regex - https://howtodoinjava.com/java/regex/uk-postcode-validation/
    private static final String REGEX = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";

    // Ensuring there is a digit between 0 and 9
    private static final String DIGIT_REGEX = "(?=.*[0-9]).*";
    // Ensuring there is an uppercase letter between A and Z
    private static final String UPPERCASE_REGEX = "(?=.*[A-Z]).*";

    /**
     * Ensuring the string is not null nor empty.
     * @param field - the string
     * @return - is the string null or empty
     */
    public static boolean isEmpty(String field) {
        return field == null || field.isEmpty();
    }

    /**
     * Checks whether the postcode is valid or not
     * @param postcode - the postcode
     * @return - is the postcode valid
     */
    public static boolean isValidPostCode(String postcode) {
        return postcode.matches(REGEX);
    }

    /**
     * Checks whether the string contains at least one digit
     * @param value - the string
     * @return - does the string contain a digit
     */
    public static boolean hasDigit(String value) {
        return value.matches(DIGIT_REGEX);
    }

    /**
     * Check whether the string contains an uppercase character
     * @param value - the string
     * @return - does the string contain an uppercase character
     */
    public static boolean hasUpperCase(String value) {
        return value.matches(UPPERCASE_REGEX);
    }
}
