package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasksMembers.getTypicalProjectDashboard;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class GetStatisticsCommandTest {
    private Model model = new ModelManager(getTypicalProjectDashboard(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProjectDashboard(), new UserPrefs());

    @Test
    public void execute_get_stats_success() {
        CommandResult expectedCommandResult = new CommandResult(GetStatisticsCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new GetStatisticsCommand(), model, expectedCommandResult, expectedModel);
    }
}
