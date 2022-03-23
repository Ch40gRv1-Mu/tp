package seedu.duke.parsers;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.GradeCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.TagCommand;
import seedu.duke.exceptions.ParseException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.data.Module;
import seedu.duke.data.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModHappyParserTest {
    private ModHappyParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ModHappyParser();
    }

    @Test
    public void parse_unrecognisedCommand_throwsException() {
        final String testString = "invalid command";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (UnknownCommandException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_noDescription_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getWorkingTime());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-d \"-d-d-d /t /m -d -d  \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNull(t.getWorkingTime());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withWorkingTime_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-t \"-d-d-d /t /m -d -d  \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getWorkingTime());
            assertNull(t.getTaskDescription());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withTargetModule_invalidModuleCode() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-m cs 2113 t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -d \"-d-d-d /t /m -d -d  \" "
                + "-t \"-t-t-t t-t-t /t/t -d -d -d \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertEquals("-t-t-t t-t-t /t/t -d -d -d", t.getWorkingTime());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_wrongOrder() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d\"   "
                + "-t \"-t-t-t t-t-t /t/t -d -d -d \" "
                + "-d \"-d-d-d /t /m -d -d  \" ";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -d \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNull(t.getWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withTargetModule_wrongOrder() {
        final String testString = "add /t \"/t/t/t/t-d\" -m cs2113t "
                + "-d \"-d-d-d /t /m -d -d  \"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withWorkingTime_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -t \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t    ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertEquals("-d-d-d /t /m -d -d", t.getWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withWorkingTime_withTargetModule_wrongOrder() {
        final String testString = "add /t \"/t/t/t/t-d\" -m cs2113t "
                + "-t \"-d-d-d /t /m -d -d  \"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -d \"-d-d-t-m /m -m -d -t  \" -t \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-t-m /m -m -d -t", t.getTaskDescription());
            assertEquals("-d-d-d /t /m -d -d", t.getWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_wrongOrder1() {
        final String testString = "add /t \"/t/t/t/t-d\" -t \"-d-d-t-m /m -m -d -t  \" -d \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_wrongOrder2() {
        final String testString = "add /t \"/t/t/t/t-d\" -t \"-d-d-t-m /m -m -d -t  \" -m cs2113t"
                + "-d \"-d-d-d /t /m -d -d  \" ";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_wrongOrder3() {
        final String testString = "add /t \"/t/t/t/t-d\" -m cs2113t   -d \"-d -d-t-t -t -m -m -m /m/m\""
                + "  -t \" -d-d -t /m -m -m-d -t -m\"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_duplicateTaskDescription() {
        final String testString = "add /t 000 -d \"123\" -t \"456\" -d \"789\"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_duplicateWorkingTime() {
        final String testString = "add /t 000 -t \"123\" -d \"456\" -t \"789\"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_invalidInput() {
        final String testString = "add /t 000 -d \"123\" -t \"456\" invalid";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_noDescription_parsedCorrectly() {
        final String testString = "add  \t /m modulecode 4 \t\t    ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Module m = ((AddCommand) c).getNewModule();
            assertNotEquals(null, m);
            assertNull(((AddCommand) c).getNewTask());
            assertEquals("modulecode", m.getModuleCode());
            assertEquals(4, m.getModularCredit());
            assertNull(m.getModuleDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_invalidModularCredit() {
        final String testString = "add  \t /m modulecode four \t\t    ";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_noDescription_invalidModuleCode() {
        final String testString = "add  \t /m module code /c 4 \t\t    ";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_withDescription_parsedCorrectly() {
        final String testString = "add  \t /m modu__lec_ode \t\t  23  -d  \t \"i am a descrip\t -d-d tion\t \"\t  ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Module m = ((AddCommand) c).getNewModule();
            assertNotEquals(null, m);
            assertNull(((AddCommand) c).getNewTask());
            assertEquals("modu__lec_ode", m.getModuleCode());
            assertEquals("i am a descrip\t -d-d tion", m.getModuleDescription());
            assertEquals(23, m.getModularCredit());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_withDescription_invalidModuleCode() {
        final String testString = "add  \t /m module code \t\t  4  -d \t\t  \t \"i am a descrip\t -d-d tion\t \"\t  ";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_withDescription_invalidInput() {
        final String testString = "add /m cs2113t /c 4 -d \"11111\"123";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_invalidFlag() {
        final String testString = "add /a \"blahblah\" -d \"blahblahblah\"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_noFlagProvided() {
        final String testString = "add cs2113t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_withModuleOnly_noModuleProvided() {
        final String testString = "add /m";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_withTaskOnly_noTaskProvided() {
        final String testString = "add /t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTaskOnly_parsedCorrectly() {
        final String testString = "del /t 1";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals(0, ((DeleteCommand) c).getTaskIndex()); // zero-indexed
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTaskOnly_integerOverflow() {
        final String testString = "del /t 2147483648";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withModuleOnly_parsedCorrectly() {
        final String testString = "del /m CS2113T";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals("CS2113T", ((DeleteCommand) c).getModuleCode());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTask_withTargetModule_parsedCorrectly() {
        final String testString = "del /t 1 -m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals(0, ((DeleteCommand) c).getTaskIndex()); // zero-indexed
            assertEquals("cs2113t", ((DeleteCommand) c).getTaskModule());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTask_withTargetModule_invalidModuleCode() {
        final String testString = "del /t 1 -m cs 2113 t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_invalidFlag() {
        final String testString = "del /a 1";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_noFlagProvided() {
        final String testString = "del 1";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withModuleOnly_noModuleProvided() {
        final String testString = "del /m";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTaskOnly_noIndexProvided() {
        final String testString = "del /t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTaskOnly_notANumber() {
        final String testString = "del /t iamnotanumber";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_unnecessaryArgs() {
        final String testString = "del /t 1 blahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_editCommand_task_unnecessaryArgs() {
        final String testString = "edit /t 1 blahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_editCommand_task_parsedCorrectly() {
        final String testString = "edit /t 1 -n \"changed\" -m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof EditCommand);
            assertEquals(0, ((EditCommand) c).getTaskIndex()); // zero-indexed
            assertNull(((EditCommand) c).getModuleCode());
            assertEquals("cs2113t", ((EditCommand) c).getTaskModule());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_editCommand_task_noOptionalFlags() {
        final String testString = "edit /t 1";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_editCommand_module_wrongFlag() {
        final String testString = "edit /m cs2113t -t \"111\"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_editCommand_task_notANumber() {
        final String testString = "edit /t two -t \"111\"";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_editCommand_task_tooManyFlags() {
        final String testString = "edit /t 2 -d \"123\" -t \"111\" -m cs2113t";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_gradeCommand_parsedCorrectly() {
        final String testString = "grade /m CS2113T a+";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof GradeCommand);
            assertEquals("CS2113T", ((GradeCommand) c).getModuleCode()); // Remember, zero-indexed!
            assertEquals("A+", ((GradeCommand) c).getModuleGrade());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_gradeCommand_invalidGrade() {
        final String testString = "grade /m CS2113T F-";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_gradeCommand_wrongOrder() {
        final String testString = "grade A- /m CS2113T";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_noModule_parsedCorrectly() {
        final String testString = "mark /c 3";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof MarkCommand);
            assertEquals(2, ((MarkCommand) c).getTaskIndex()); // Remember, zero-indexed!
            assertNull(((MarkCommand) c).getTaskModuleString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_withModule_parsedCorrectly() {
        final String testString = "mark /c 3 -m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof MarkCommand);
            assertEquals(2, ((MarkCommand) c).getTaskIndex()); // Remember, zero-indexed!
            assertEquals("cs2113t", ((MarkCommand) c).getTaskModuleString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_invalidFlag() {
        final String testString = "mark /a 1";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_noFlagProvided() {
        final String testString = "mark 1";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_noIndexProvided() {
        final String testString = "mark /c";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_notANumber() {
        final String testString = "mark /c iamnotanumber";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_unnecessaryArgs() {
        final String testString = "mark /c 1 blahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_listCommand_parsedCorrectly() {
        final String testString = "list";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ListCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_listCommandwithArgument_noExeceptionThrown() {
        final String testString = "list \"test\"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ListCommand);
            assertEquals("test", ((ListCommand) c).getArgument());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() {
        final String testString = "exit";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ExitCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_exitCommand_unnecessaryArgs() {
        final String testString = "exit blahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommand_addTag_withTargetModule_parsedCorrectly() {
        final String testString = "tag add 1 -m cs2113t \"tag\"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof TagCommand);
            assertEquals("add", ((TagCommand) c).getTagOperation());
            assertEquals("cs2113t", ((TagCommand) c).getTaskModule());
            assertEquals("tag", ((TagCommand) c).getTagDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommand_invalidTagOperation_throwsParseException() {
        final String testString = "tag invalidOp 1 \"tag\"";
        AtomicReference<Command> c = null;
        assertThrows(ParseException.class, () -> {
            c.set(parser.parseCommand(testString));
        });
    }
}
