package seedu.address.model;

import javafx.collections.ObservableList;

import seedu.address.model.calendar.CalendarWrapper;
import seedu.address.model.calendar.Meeting;
import seedu.address.model.inventory.Inventory;
import seedu.address.model.task.Task;
import seedu.address.model.member.Member;
import seedu.address.model.mapping.InvMemMapping;
import seedu.address.model.mapping.InvTasMapping;
import seedu.address.model.mapping.TasMemMapping;
import seedu.address.model.mapping.Mapping;

import java.util.List;
import java.util.HashMap;


/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyProjectDashboard {

    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Member> getMemberList();

    /**
     * Returns an unmodifiable view of the expenses list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Inventory> getInventoryList();

    ObservableList<InvMemMapping> getInvMemMappingList();

    ObservableList<InvTasMapping> getInvTasMappingList();

    ObservableList<TasMemMapping> getTasMemMappingList();

    ObservableList<Mapping> getMappingList();

    ObservableList<CalendarWrapper> getCalendarList();

    ObservableList<Meeting> getMeetingList();

    ObservableList<Task> getTasksNotStarted();

    ObservableList<Task> getTasksDoing();

    ObservableList<Task> getTasksDone();

}
