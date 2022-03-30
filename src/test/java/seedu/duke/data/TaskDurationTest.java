package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ModHappyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskDurationTest {
    TaskDuration taskDuration;

    @Test
    public void initializeWithHour_CheckAllDurationUnit() {
        try {
            // check all notation of hours

            // <duration>h
            taskDuration = new TaskDuration("1.0h");
            assertEquals("1 hours", taskDuration.toString());

            // <duration> h
            taskDuration = new TaskDuration("1.0 h");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>H
            taskDuration = new TaskDuration("1.0H");
            assertEquals("1 hours", taskDuration.toString());

            // <duration> H
            taskDuration = new TaskDuration("1.0 H");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>hours
            taskDuration = new TaskDuration("1.0hours");
            assertEquals("1 hours", taskDuration.toString());

            // <duration> hours
            taskDuration = new TaskDuration("1.0 hours");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>Hours
            taskDuration = new TaskDuration("1.0Hours");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>Hours
            taskDuration = new TaskDuration("1.0 Hours");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>hour
            taskDuration = new TaskDuration("1.0 hour");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>hour
            taskDuration = new TaskDuration("1.0hour");
            assertEquals("1 hours", taskDuration.toString());

            // <duration> hour
            taskDuration = new TaskDuration("1.0 hour");
            assertEquals("1 hours", taskDuration.toString());

            // <duration>Hour
            taskDuration = new TaskDuration("1.0Hour");
            assertEquals("1 hours", taskDuration.toString());

            // <duration> Hour
            taskDuration = new TaskDuration("1.0 Hour");
            assertEquals("1 hours", taskDuration.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initializeWithHour_CheckAllPossibleDurations() {
        try {
            // check all notation of hours
            taskDuration = new TaskDuration("1h");
            assertEquals("1 hours", taskDuration.toString());

            taskDuration = new TaskDuration("0.5h");
            assertEquals("30 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("1.1h");
            assertEquals("1 hours 6 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("1.7h");
            assertEquals("1 hours 42 minutes", taskDuration.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void initializeWithMinute_CheckAllDurationUnit() {
        try {
            // check all notation of hours

            // <duration>m
            taskDuration = new TaskDuration("1.0m");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> m
            taskDuration = new TaskDuration("1.0 m");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>M
            taskDuration = new TaskDuration("1.0M");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> M
            taskDuration = new TaskDuration("1.0 M");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>min
            taskDuration = new TaskDuration("1.0min");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> min
            taskDuration = new TaskDuration("1.0 min");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>Min
            taskDuration = new TaskDuration("1.0Min");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> Min
            taskDuration = new TaskDuration("1.0 Min");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>minutes
            taskDuration = new TaskDuration("1.0minutes");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> minutes
            taskDuration = new TaskDuration("1.0 minutes");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>Minutes
            taskDuration = new TaskDuration("1.0Minutes");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> Minutes
            taskDuration = new TaskDuration("1.0 Minutes");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>minute
            taskDuration = new TaskDuration("1.0minute");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>minute
            taskDuration = new TaskDuration("1.0 minutes");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration>Minute
            taskDuration = new TaskDuration("1.0Minute");
            assertEquals("1 minutes", taskDuration.toString());

            // <duration> Minute
            taskDuration = new TaskDuration("1.0 Minutes");
            assertEquals("1 minutes", taskDuration.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initializeWithMinute_CheckAllPossibleValues() {
        try {

            taskDuration = new TaskDuration("1 m");
            assertEquals("1 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("0.5 m");
            assertEquals("1 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("0.3 m");
            assertEquals("0 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("1.3 m");
            assertEquals("1 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("70 m");
            assertEquals("1 hours 10 minutes", taskDuration.toString());

            taskDuration = new TaskDuration("70.5 m");
            assertEquals("1 hours 11 minutes", taskDuration.toString());
            //System.out.println(taskDuration);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initializeWithMinute_withoutUnit() {
        try {
            taskDuration = new TaskDuration("1");
        } catch (ModHappyException e) {
            return;
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void initializeWithMinute_withWrongUnitMinute() {
        try {
            taskDuration = new TaskDuration("1minu");
        } catch (ModHappyException e) {
            return;
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void initializeWithMinute_withWrongUnitHour() {
        try {
            taskDuration = new TaskDuration("1hr");
        } catch (ModHappyException e) {
            return;
        } catch (Exception e) {
            fail();
        }

    }


}