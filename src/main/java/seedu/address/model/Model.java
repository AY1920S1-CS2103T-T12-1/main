package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.Task;
import seedu.address.model.member.Member;
import seedu.address.model.mapping.Mapping;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' project dashboard file path.
     */
    Path getProjectDashboardFilePath();

    /**
     * Sets the user prefs' project dashboard file path.
     */
    void setProjectDashboardFilePath(Path projectDashboardFilePath);

    /**
     * Replaces address book data with the data in {@code projectDashboard}.
     */
    void setProjectDashboard(ReadOnlyProjectDashboard projectDashboard);

    /** Returns the ProjectDashboard */
    ReadOnlyProjectDashboard getProjectDashboard();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTasksList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTasksList(Predicate<Task> predicate);

    void addMember(Member member);

    void deleteMember(Member member);

    boolean hasMember(Member member);

    ObservableList<Member> getFilteredMembersList();

    void updateFilteredMembersList(Predicate<Member> predicate);

    void addMapping(Mapping mapping);

    void deleteMapping(Mapping mapping);

    boolean hasMapping(Mapping mapping);

    ObservableList<Mapping> getFilteredMappingsList();

    void updateFilteredMappingsList(Predicate<Mapping> predicate);
}
