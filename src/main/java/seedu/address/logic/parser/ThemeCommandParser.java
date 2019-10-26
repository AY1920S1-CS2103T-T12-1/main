package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.settings.Theme;

/**
 * Parses input arguments and creates a new ThemeCommand parser object
 */
public class ThemeCommandParser implements Parser<ThemeCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ThemeCommand parse(String userInput) throws ParseException {
        Theme newTheme;

        try {
            newTheme = ParserUtil.parseTheme(userInput);
            return new ThemeCommand(newTheme);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE), pe);
        }
    }
}
