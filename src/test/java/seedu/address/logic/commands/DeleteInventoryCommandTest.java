package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INVENTORY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INVENTORY;
import static seedu.address.testutil.TypicalInventories.getTypicalProjectDashboard;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.UserSettings;
import seedu.address.model.inventory.Inventory;

public class DeleteInventoryCommandTest {

    private Model model = new ModelManager(getTypicalProjectDashboard(), new UserPrefs(), new UserSettings());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Inventory inventoryToDelete = model.getFilteredInventoriesList().get(INDEX_FIRST_INVENTORY.getZeroBased());
        DeleteInventoryCommand deleteInventoryCommand = new DeleteInventoryCommand(INDEX_FIRST_INVENTORY);

        String expectedMessage = String.format(DeleteInventoryCommand.MESSAGE_DELETE_INVENTORY_SUCCESS,
                inventoryToDelete);

        ModelManager expectedModel = new ModelManager(model.getProjectDashboard(), new UserPrefs(), new UserSettings());
        expectedModel.deleteInventory(inventoryToDelete);

        assertCommandSuccess(deleteInventoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInventoriesList().size() + 1);
        DeleteInventoryCommand deleteInventoryCommand = new DeleteInventoryCommand(outOfBoundIndex);

        assertCommandFailure(deleteInventoryCommand, model, Messages.MESSAGE_INVALID_INVENTORY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInventoryCommand deleteFirstCommand = new DeleteInventoryCommand(INDEX_FIRST_INVENTORY);
        DeleteInventoryCommand deleteSecondCommand = new DeleteInventoryCommand(INDEX_SECOND_INVENTORY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInventoryCommand deleteFirstCommandCopy = new DeleteInventoryCommand(INDEX_FIRST_INVENTORY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
