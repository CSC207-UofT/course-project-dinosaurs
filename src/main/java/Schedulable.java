


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
        // Create a new calendar
        ICalendar icalendar = new ICalendar();
        VEvent event = new VEvent();
        Summary summary = event.setSummary("Testing123");
        summary.setLanguage("en-us");


        // add the date bellow
        // (specified date)

//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2021);
//        cal.set(Calendar.MONTH, Calendar.OCTOBER);
//        cal.set(Calendar.DAY_OF_MONTH, 20);
//        Date dateRepresentation = cal.getTime();
//        event.setDateStart(dateRepresentation);

        // (current date)
        Date start = new Date();
        event.setDateStart(start);


        // todo: find a way for the comments to be the study block, down is an example with str

        event.setDescription("math break csc break ...");

        // set the event duration
        Duration duration = new Duration.Builder().hours(4).build();
        event.setDuration(duration);

        // if we want it recurring
//        Recurrence recur = new Recurrence.Builder(Frequency.HOURLY).interval(2).build();
//        event.setRecurrenceRule(recur);

        // add the event and write the ics file
        icalendar.addEvent(event);
        String str = Biweekly.write(icalendar).go();
        FileWriter writer = new FileWriter("test123.ics");
        writer.write(str);
        writer.close();

    }


}
