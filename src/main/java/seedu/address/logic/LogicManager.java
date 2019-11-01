package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.MultiLine.MultiLineManager;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ProjectDashboardParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyProjectDashboard;
import seedu.address.model.inventory.Inventory;
import seedu.address.model.member.Member;
import seedu.address.model.settings.ClockFormat;
import seedu.address.model.settings.Theme;
import seedu.address.model.statistics.Statistics;
import seedu.address.model.task.Task;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ProjectDashboardParser projectDashboardParser;
    private final MultiLineManager multiLine;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        projectDashboardParser = new ProjectDashboardParser();
        multiLine = new MultiLineManager(model);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = projectDashboardParser.parseCommand(commandText);
        commandResult = command.execute(model);

        CommandResult commandResultMl = multiLine.manage(commandResult, command);
        if(!commandResultMl.equals(new CommandResult("No MultiLine"))) {
            return commandResultMl;
        }

        try {
            storage.saveProjectDashboard(model.getProjectDashboard());
            storage.saveUserSettings(model.getUserSettings());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyProjectDashboard getProjectDashboard() {
        return model.getProjectDashboard();
    }

    // Task

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTasksList();
    }

    @Override
    public ObservableList<Member> getFilteredMemberList() {
        return model.getFilteredMembersList();
    }

    @Override
   public ObservableList<Task> getFilteredTaskListNotStarted() {
        return model.getFilteredTaskListNotStarted();
    }

    @Override
    public ObservableList<Task> getFilteredTaskListDoing() {
        return model.getFilteredTaskListDoing();
    }

    @Override
    public ObservableList<Task> getFilteredTaskListDone() {
        return model.getFilteredTaskListDone();
    }

    @Override
    public ObservableList<Task> getFilteredTaskListByDeadline() {
        return model.getFilteredTaskListByDeadline();
    }

    @Override
    public ObservableList<Inventory> getFilteredInventoryList() {
        return model.getFilteredInventoriesList();
    }

    @Override
    public Path getProjectDashboardFilePath() {
        return model.getProjectDashboardFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Statistics getStatistics() {
        return model.getStatistics();
    }

    @Override
    public Theme getCurrentTheme() {
        return model.getCurrentTheme();
    }

    @Override
    public ClockFormat getClockFormat() {
        return model.getCurrentClockFormat();
    }
}
