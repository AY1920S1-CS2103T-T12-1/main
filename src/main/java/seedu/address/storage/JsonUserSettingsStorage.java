package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyUserSettings;
import seedu.address.model.UserSettings;

/**
 * A class to access user settings stored in the hard disk as a json file.
 */
public class JsonUserSettingsStorage implements UserSettingsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUserSettingsStorage.class);

    private Path filePath;

    public JsonUserSettingsStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the UserSettings data file.
     */
    @Override
    public Path getUserSettingsFilePath() {
        return filePath;
    }

    /**
     * Returns UserSettings data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    @Override
    public Optional<UserSettings> readUserSettings() throws DataConversionException, IOException {
        return readUserSettings(filePath);
    }

    /**
     * @param filePath location of data. Cannot be null.
     * @see #getUserSettingsFilePath().
     */
    @Override
    public Optional<UserSettings> readUserSettings(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        return JsonUtil.readJsonFile(filePath, UserSettings.class);
    }

    /**
     * Saves the given {@link ReadOnlyUserSettings} to the storage.
     *
     * @param userSettings cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveUserSettings(ReadOnlyUserSettings userSettings) throws IOException {
        saveUserSettings(userSettings, filePath);
    }

    /**
     * Saves the given {@link ReadOnlyUserSettings} to the storage.
     *
     * @param userSettings cannot be null.
     * @param filePath cannot be null.
     * @throws IOException if there was any problem writing to file.
     */
    @Override
    public void saveUserSettings(ReadOnlyUserSettings userSettings, Path filePath) throws IOException {
        requireAllNonNull(userSettings, filePath);
        JsonUtil.saveJsonFile(userSettings, filePath);

    }
}
