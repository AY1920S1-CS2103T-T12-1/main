package seedu.address.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTasksMembers.getTypicalProjectDashboard;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.task.TaskStatus;

public class StatisticsTest {
    private Model model = new ModelManager(getTypicalProjectDashboard(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProjectDashboard(), new UserPrefs());
    @Test
    public void execute_correct_tasks_by_status_stats() {
        HashMap<TaskStatus, Integer> expectedResult = new HashMap<>();
        expectedResult.put(TaskStatus.UNBEGUN, 3);
        expectedResult.put(TaskStatus.DOING, 2);
        expectedResult.put(TaskStatus.DONE, 2);

        assertEquals(expectedResult, expectedModel.getStatistics().getPortionTasksByStatus());
    }

    @Test
    public void execute_correct_members_by_tasks_stats() {
        HashMap<Member, Integer> expectedResult = new HashMap<>();
        List<Member> members = model.getFilteredMembersList();

        expectedResult.put(members.get(1), 2);
        expectedResult.put(members.get(2), 1);
        expectedResult.put(members.get(3), 1);
        expectedResult.put(members.get(4), 3);
        expectedResult.put(members.get(5), 1);
        expectedResult.put(members.get(0), 0);
        expectedResult.put(members.get(6), 0);

        assertEquals(expectedResult, expectedModel.getStatistics().getPortionMembersByTasks());
    }

    @Test
    public void execute_correct_members_by_inv_stats() {
        HashMap<Member, Integer> expectedResult = new HashMap<>();
        List<Member> members = model.getFilteredMembersList();

        expectedResult.put(members.get(1), 2);
        expectedResult.put(members.get(2), 1);
        expectedResult.put(members.get(3), 1);
        expectedResult.put(members.get(4), 3);
        expectedResult.put(members.get(5), 1);
        expectedResult.put(members.get(0), 0);
        expectedResult.put(members.get(6), 0);

        assertEquals(expectedResult, expectedModel.getStatistics().getPortionMembersByTasks());
    }
}
