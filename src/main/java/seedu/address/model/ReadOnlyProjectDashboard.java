package seedu.address.model;

import javafx.collections.ObservableList;

import seedu.address.model.task.Task;
import seedu.address.model.member.Member;
import seedu.address.model.mapping.Mapping;

import java.util.List;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyProjectDashboard {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getTaskList();

    ObservableList<Member> getMemberList();

    List<Mapping> getMappingList();


}
