package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.GradeCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

public class GradeParser extends Parser {
    public static final String MODULE_CODE = StringConstants.MODULE_CODE;
    public static final String MODULE_GRADE = StringConstants.MODULE_GRADE;

    // Unescaped regex for testing:
    // (/m\s+(?<moduleCode>\w+)(\s+/g\s+(?<moduleGrade>(?i)([A-B][+-]?|[C-D][+]?|F))))
    private static final String GRADE_FORMAT = "(/m\\s+(?<moduleCode>\\w+)(\\s+/g\\s+"
            + "(?<moduleGrade>(?i)([A-B][+-]?|[C-D][+]?|F))))";

    public GradeParser() {
        super();
        this.commandFormat = GRADE_FORMAT;
        groupNames.add(MODULE_CODE);
        groupNames.add(MODULE_GRADE);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        String moduleGrade = parsedArguments.get(MODULE_GRADE).toUpperCase();
        if (!Objects.isNull(moduleCode)) {
            return new GradeCommand(moduleCode, moduleGrade);
        }
        throw new ModHappyException();
    }
}
