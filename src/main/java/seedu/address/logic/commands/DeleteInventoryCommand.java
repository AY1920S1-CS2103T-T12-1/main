package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INVENTORY_INDEX;

import javafx.collections.ObservableList;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
//import seedu.address.commons.core.index.InvIndex;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.inventory.Inventory;

/**
 * Deletes a inventory identified using it's displayed index from the address book.
 */
public class DeleteInventoryCommand extends Command {
    public static final String COMMAND_WORD = "delete-inv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the inventory identified by the index number used in the displayed task list.\n"
            + "Parameters:" + PREFIX_INVENTORY_INDEX + "INVENTORY INDEX \n"
            + "Example: " + COMMAND_WORD + "ii/1";

    public static final String MESSAGE_DELETE_INVENTORY_SUCCESS = "Deleted Inventory: %1$s";

    private final Index targetIndex;

    public DeleteInventoryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Inventory> lastShownList = model.getFilteredInventoriesList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INVENTORY_DISPLAYED_INDEX);
        }

        Inventory inventoryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInventory(inventoryToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INVENTORY_SUCCESS, inventoryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInventoryCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInventoryCommand) other).targetIndex)); // state check
    }
}
