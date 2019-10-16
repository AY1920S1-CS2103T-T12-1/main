package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtId;
import static seedu.address.testutil.TypicalIds.ID_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIds.ID_SECOND_MEMBER;
import static seedu.address.testutil.TypicalTasksMembers.getTypicalProjectDashboard;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyProjectDashboard;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.member.MemberId;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteMemberCommand}.
 */
public class DeleteMemberCommandTest {
    private Model model = new ModelManager(getTypicalProjectDashboard(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Member memberToDelete = null;
        List<Member> lastShownList = model.getFilteredMembersList();
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(ID_FIRST_MEMBER);

        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getId().equals(ID_FIRST_MEMBER)) {
                memberToDelete = lastShownList.get(i);
                break;
            }
        }

        String expectedMessage = String.format(deleteMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS, memberToDelete);

        ModelManager expectedModel = new ModelManager(model.getProjectDashboard(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);

        assertCommandSuccess(deleteMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        MemberId invalidId = new MemberId("not inside");
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(invalidId);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_ID);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        showMemberAtId(model, ID_FIRST_MEMBER);

        Member memberToDelete = null;
        List<Member> lastShownList = model.getFilteredMembersList();
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(ID_FIRST_MEMBER);

        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getId() == ID_FIRST_MEMBER) {
                memberToDelete = lastShownList.get(i);
                break;
            }
        }

        String expectedMessage = String.format(deleteMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS, memberToDelete);

        Model expectedModel = new ModelManager(model.getProjectDashboard(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);
        showNoMember(expectedModel);

        assertCommandSuccess(deleteMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdFilteredList_throwsCommandException() {
        showMemberAtId(model, ID_FIRST_MEMBER);

        MemberId invalidId = ID_SECOND_MEMBER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        //assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectDashboard().getTaskList().size());

        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(invalidId);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_ID);
    }

    @Test
    public void equals() {
        DeleteMemberCommand deleteFirstCommand = new DeleteMemberCommand(ID_FIRST_MEMBER);
        DeleteMemberCommand deleteSecondCommand = new DeleteMemberCommand(ID_SECOND_MEMBER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMemberCommand deleteFirstCommandCopy = new DeleteMemberCommand(ID_FIRST_MEMBER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMember(Model model) {
        model.updateFilteredMembersList(p -> false);

        assertTrue(model.getFilteredMembersList().isEmpty());
    }
}
