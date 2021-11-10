package UseCases;


import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import biweekly.util.Duration;

import java.io.*;
import java.util.Date;


public interface Schedulable {
    ICalendar makeCalendar();
//    public void eventDate();
    VEvent makeEvent();
    void writeICS() throws IOException;

}
