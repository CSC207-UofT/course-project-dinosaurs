


import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.component.VJournal;
import biweekly.property.Comment;
import biweekly.property.Description;
import biweekly.property.Summary;
import biweekly.util.Duration;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public interface Schedulable {
    public static void main(String[] args) throws IOException {
        // Creating a new cal works
        ICalendar icalendar = new ICalendar();
        VEvent event = new VEvent();
        Summary summary = event.setSummary("Event Test");
        summary.setLanguage("en-us");


        //TODO: the thing down does the specified date
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.OCTOBER);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        Date dateRepresentation = cal.getTime();
        event.setDateStart(dateRepresentation);

        // todo: the thing down does the current date
//        Date start = new Date();
//        event.setDateStart(start);

        // todo: find a way for the comments to be in the study block but bellow is not working
        Comment comment = new Comment("math break physics break science");
        event.addComment(comment);

//        Comment comment = event.addComment("thing, thing");
//        event.addComment("thing, thing");
//
//        event.addComment("thing1, thing2, thing3");

//        event.addComment("please work");

        // adding a description of the event
//        icalendar.setDescription("chem, econs, maths");

//        Description description = new Description("Want to work on chemistry today");
//        icalendar.setDescription(description);

        event.setDescription("chem, econs, maths");


        Duration duration = new Duration.Builder().hours(4).build();
        event.setDuration(duration);

        // todo: the thing down is if we want it to be recurring

//        Recurrence recur = new Recurrence.Builder(Frequency.HOURLY).interval(2).build();
//        event.setRecurrenceRule(recur);
        icalendar.addEvent(event);

        String str = Biweekly.write(icalendar).go();

        FileWriter writer = new FileWriter("test123.ics");
        writer.write(str);
        writer.close();
    }


}
