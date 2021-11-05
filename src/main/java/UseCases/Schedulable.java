package UseCases;


import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import biweekly.util.Duration;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public interface Schedulable {
    public void makeCalendar();

    public void eventDate();

    public void makeEvent();

    public void writeICS();

}
