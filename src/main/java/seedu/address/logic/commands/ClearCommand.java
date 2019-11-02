package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.ProjectDashboard;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String PREFIX_USAGE = "";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.setProjectDashboard(new ProjectDashboard());
        return new CommandResult("Type-2");
        //return new CommandResult(MESSAGE_SUCCESS);
    }
}
