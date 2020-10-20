package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DATE_AND_TIME = new Prefix("at/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_SCOPE = new Prefix("in/");
    public static final Prefix PREFIX_CONTACT = new Prefix("with/");
    public static final Prefix PREFIX_INDEX = new Prefix("index/");

}
