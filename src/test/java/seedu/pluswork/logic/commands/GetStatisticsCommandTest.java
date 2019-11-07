package seedu.pluswork.logic.commands;

import static seedu.pluswork.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pluswork.testutil.TypicalTasksMembers.getTypicalProjectDashboard;

import org.junit.jupiter.api.Test;

import seedu.pluswork.model.Model;
import seedu.pluswork.model.ModelManager;
import seedu.pluswork.model.UserPrefs;
import seedu.pluswork.model.UserSettings;

public class GetStatisticsCommandTest {
    private Model model = new ModelManager(getTypicalProjectDashboard(), new UserPrefs(), new UserSettings());
    private Model expectedModel = new ModelManager(getTypicalProjectDashboard(), new UserPrefs(), new UserSettings());

    @Test
    public void execute_get_stats_success() {
        CommandResult expectedCommandResult = new CommandResult(GetStatisticsCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new GetStatisticsCommand(), model, expectedCommandResult, expectedModel);
    }
}
