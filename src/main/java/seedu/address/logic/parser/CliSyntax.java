package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for task */
    public static final Prefix PREFIX_TASK_NAME = new Prefix("tn/");
    public static final Prefix PREFIX_TASK_INDEX = new Prefix("ti/");
    public static final Prefix PREFIX_TASK_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_TASK_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("at/");

    /* Prefix definitions for Member */
    public static final Prefix PREFIX_MEMBER_NAME = new Prefix("mn/");
    public static final Prefix PREFIX_MEMBER_ID = new Prefix("mi/");
    public static final Prefix PREFIX_MEMBER_TAG = new Prefix("mt/");

    /*Prefix definitions for inventory*/
    public static final Prefix PREFIX_INVENTORY_NAME = new Prefix("i/");
    public static final Prefix PREFIX_INVENTORY_PRICE = new Prefix("p/");
    public static final Prefix PREFIX_INVENTORY_INDEX = new Prefix("ii/");

    /*Prefix definitions for inventory*/
    public static final Prefix PREFIX_FILE_PATH = new Prefix("fp/");
}
