package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.inventory.Inventory;
import seedu.address.model.task.Task;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_TASKS = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_INVENTORIES = "Inventory list contains duplicate inventory(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedInventory> inventories = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given task.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                       @JsonProperty("inventories") List<JsonAdaptedInventory> inventories) {
        this.tasks.addAll(tasks);
        this.inventories.addAll(inventories);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).
                collect(Collectors.toList()));
        inventories.addAll(source.getInventoryList().stream().map(JsonAdaptedInventory::new).
                collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (addressBook.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASKS);
            }
            addressBook.addTask(task);
        }

        for (JsonAdaptedInventory jsonAdaptedInv : inventories) {
            Inventory inventory = jsonAdaptedInv.toModelType();
            if (addressBook.hasInventory(inventory)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INVENTORIES);
            }
            addressBook.addInventory(inventory);
        }
        return addressBook;
    }

}
