package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
<<<<<<< HEAD
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
=======
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TAG;
>>>>>>> 19e676164d33a2eb96ba8396c7bae020bf49f7b7
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ProjectDashboard;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

<<<<<<< HEAD
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
=======
    /* Task related names will be split into four categories: Finance and Publicity */
    public static final String VALID_TASK_NAME_FINANCE = "Review Project Budget";
    public static final String VALID_TASK_NAME_PUBLICITY = "Build Project Website";
    public static final String VALID_TASK_STATUS_FINANCE = "unbegun";
    public static final String VALID_TASK_STATUS_PUBLICITY = "doing";
    public static final String VALID_TAG_FINANCE = "finance";
    public static final String VALID_TAG_PUBLICITY = "publicity";
    public static final String VALID_TAG_URGENCY = "urgent";

    public static final String TASK_NAME_DESC_FINANCE = " " + PREFIX_TASK_NAME + VALID_TASK_NAME_FINANCE;
    public static final String TASK_NAME_DESC_PUBLICITY = " " + PREFIX_TASK_NAME + VALID_TASK_NAME_PUBLICITY;
    public static final String TASK_STATUS_DESC_FINANCE = " " + PREFIX_TASK_STATUS + VALID_TASK_STATUS_FINANCE;
    public static final String TASK_STATUS_DESC_PUBLICITY = " " + PREFIX_TASK_STATUS + VALID_TASK_STATUS_PUBLICITY;
    public static final String TAG_DESC_PUBLICITY = " " + PREFIX_TASK_TAG + VALID_TAG_PUBLICITY;
    public static final String TAG_DESC_URGENCY = " " + PREFIX_TASK_TAG + VALID_TAG_URGENCY;
    public static final String TAG_DESC_FINANCE = " " + PREFIX_TASK_TAG + VALID_TAG_FINANCE;

    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_TASK_NAME
            + "Project Review&"; // '&' not allowed in names
    public static final String INVALID_TASK_STATUS_DESC = " " + PREFIX_TASK_STATUS
            + "reviewing"; // invalid status - statuses are determined in {@code TaskStatus}
    public static final String INVALID_TAG_DESC = " " + PREFIX_TASK_TAG
            + "paused*"; // '*' not allowed in tags
>>>>>>> 19e676164d33a2eb96ba8396c7bae020bf49f7b7

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor TASK_DESC_FINANCE;
    public static final EditCommand.EditTaskDescriptor TASK_DESC_PUBLICITY;

    static {
<<<<<<< HEAD
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withRemark(VALID_REMARK_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withRemark(VALID_REMARK_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
=======
        TASK_DESC_FINANCE = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_FINANCE)
                .withStatus(TaskStatus.UNBEGUN)
                .withTags(VALID_TAG_FINANCE).build();
        TASK_DESC_PUBLICITY = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_PUBLICITY)
                .withStatus(TaskStatus.DOING)
                .withTags(VALID_TAG_PUBLICITY).build();
>>>>>>> 19e676164d33a2eb96ba8396c7bae020bf49f7b7
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ProjectDashboard expectedProjectDashboard = new ProjectDashboard(actualModel.getProjectDashboard());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTasksList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedProjectDashboard, actualModel.getProjectDashboard());
        assertEquals(expectedFilteredList, actualModel.getFilteredTasksList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTasksList().size());

        Task task = model.getFilteredTasksList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTasksList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTasksList().size());
    }

}
