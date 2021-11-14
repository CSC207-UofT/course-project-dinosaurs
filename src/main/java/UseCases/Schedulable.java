package UseCases;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import java.io.*;


public interface Schedulable {
    /**
     * Create an ICalendar and an event which then are used to write an ics file which exports the
     * user's StudyBlock to their icalendar.
     * The interface uses information about the user's desired duration and methods of study that were
     * selected earlier in the program.
     * TODO: duration of the studyblock based on user preference
     */

    ICalendar makeCalendar();
    VEvent makeEvent();
    void writeICS() throws IOException;
}
