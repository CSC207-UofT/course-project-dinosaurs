package UseCases;

import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import biweekly.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class StudyBlock implements Schedulable {
    /**
     * TODO implement UseCases.StudyBlock
     * Creates a new UseCases.StudyBlock based on the selected blockLength,
     * studyMethod, chosenTasks, and priorityType.
     *
     * TODO implement private double length;
     */

    private StudyMethod studyMethod;
    private Checklist checklist;
    private ArrayList<String> listTODO;

    /**
     * Constructor for the BlockScheduler.
     *
     * @param studyMethod Preferred study scheduling method.
     * @param checklist List of Tasks to be completed.
     */
    public StudyBlock(StudyMethod studyMethod,
                      Checklist checklist) {
        this.studyMethod = studyMethod;
        this.checklist = checklist;
        this.listTODO = new ArrayList<>();
    }

    // TODO getters and setters

    /**
     * Builds an ArrayList, <listTodo>, that incorporates the studyMethod and checklist.
     * TODO implement way of checking multiple tasks, using length input as well.
     */
    public void buildListTODO() {
        ArrayList<Task> task = this.checklist.incomplete;
        Task t1 = task.get(0);
        double x = t1.length;
        ArrayList<String> msg = new ArrayList<>();

        while ((x - this.studyMethod.getMethod().get(0)) >= 0){
            msg.add(t1.name + " | " + this.studyMethod.getMethod().get(0) + " min");
            msg.add("Break" + " | " + this.studyMethod.getMethod().get(1) + " min");
            x -= this.studyMethod.getMethod().get(0);
        }
        this.listTODO = msg;
    }

    /**
     * Breaks up the study block into a two dimensional Array which stores the
     * different increments of active and break time.
     * @param length The length of the study block in minutes.
     */
    public int[][] breakUpStudyBlock(int length){
        // We first need the number of full blocks we'll have and the amount of extra time leftover.
        // Note that extra + blocks == length
        int blocks = length / (this.studyMethod.getMethod().get(0) + this.studyMethod.getMethod().get(1));
        int extra = length % (this.studyMethod.getMethod().get(0) + this.studyMethod.getMethod().get(1));
        // We return a 2D array where the length is the number of block-breaks and the indexes of each
        // array are the amount of time for the block and break.
        int[][] array = new int[blocks + 1][2];
        for (int i = 0; i < blocks; i++) {
            array[i] = new int[]{this.studyMethod.getMethod().get(0), this.studyMethod.getMethod().get(1)};
        }
        // If the extra time is less than the active study block, tag it on as an extra active study block
        // If the extra time is more than the active study block, tag it on as a full
        // active study block and an extra break.
        // Note that if there is no extra time, this last index will be [0][0].
        if (extra < this.studyMethod.getMethod().get(0)){
            array[blocks] = new int[]{extra, 0};
        } else {
            array[blocks] = new int[]{this.studyMethod.getMethod().get(0), extra - this.studyMethod.getMethod().get(0)};
        }
        return array;
    }

    public ArrayList<String> assignTasks(int[][] array){
//      A subblock refers to a StudyMethod's [study, break] (ie. [25, 5] or [52, 17])
        ArrayList<String> msg = new ArrayList<>();
        ArrayList<Integer> t_length = new ArrayList<>();
        for (int x = 0; x < (this.checklist.incomplete.size()); x++)
            t_length.add(this.checklist.incomplete.get(x).length);
        int x = 0;

        for (int i = 0; i < (array.length - 1); i ++)
            while (t_length.size() > 0) {
                int remainder = t_length.get(0) - array[i][0];
                String task = this.checklist.incomplete.get(x).name;
                int time_remaining = array[i][0];       // Checks how much study time was used in the subblock
                if (remainder < 0 && t_length.size() > 1) {
                    msg.add(task + " | " + t_length.get(0) + " min");
                    msg.add(this.checklist.incomplete.get(x + 1).name + " | " + -remainder + " min");
                    msg.add("Break" + " | " + array[i][1] + " min");
                    t_length.remove(0);     // Updates t_length for the next array index
                    int left = t_length.get(0) + remainder;
                    t_length.set(0, left);      // Updates t_length for the next array index
                    remainder = t_length.get(0) - array[i][0];
                    time_remaining = 0;
                    x++;        // Updates Task id

                }
                // Checks for extended breaks
                if (remainder < 0 && time_remaining > 0) {
                    msg.add(task + " | " + t_length.get(0) + " min");
                    msg.add("Break (extended)" + " | " + array[i][1] + " min" + " + " + -remainder);
                    t_length.remove(0);     // Updates t_length for the next array index
                }
                if (remainder == 0 && time_remaining > 0) {
                    msg.add(task + " | " + t_length.get(0) + " min");
                    msg.add("Break" + " | " + array[i][1] + " min");
                    time_remaining = 0;
                    t_length.remove(0);     // Updates t_length for the next array index
                                        x ++;     // Updates Task id
                }
                if (remainder > 0 && time_remaining > 0) {
                    msg.add(task + " | " + array[i][0] + " min");
                    t_length.set(0, remainder);     // Updates t_length for the next array index
                    msg.add("Break" + " | " + array[i][1] + " min");
                    time_remaining = 0;
                }
                if (remainder >= 0 && time_remaining == 0) {
                    i++;
                }
                if (remainder == -25) {
                    t_length.remove(0);     // Updates t_length for the next array index
                }

            }

        return msg;

    }

    public String toString() {
        buildListTODO();
        StringBuilder todo = new StringBuilder(" --- TODO List --- \n");

        for (String task: this.listTODO){
            todo.append(task).append("\n");
        }
        return todo.toString();
    }

    // Schedulable interface implementation

    /**
     * Creates and returns a new ICalendar instance to be used for the ical even creation using biweekly.
     * @return a new ICalendar.
     */

    @Override
    public ICalendar makeCalendar() {
        return new ICalendar();
    }

    /**
     * Creates and returns a new event using the current date, the description from the StudyBlock toString() method,
     * and the desired studyBlock duration of the user.
     * @return an event containing required information.
     */
    @Override
    public VEvent makeEvent() {
        VEvent event = new VEvent();
        Date start = new Date();
        event.setDateStart(start);
        Summary summary = event.setSummary("StudyBlock");
        summary.setLanguage("en-us");
        event.setDescription(toString());
        //todo: fix duration based on what the person wants
        Duration duration = new Duration.Builder().minutes(75).build();
        event.setDuration(duration);
        return event;
    }

    /**
     * Creates an ics file with the event and ICalendar created through makeEvent() and make Calendar().
     * The file can be opened and directly links to the users icalendar.
     */
    @Override
    public void writeICS() throws IOException {
        ICalendar cal = makeCalendar();
        cal.addEvent(makeEvent());
        String studyBlock = Biweekly.write(cal).go();
        FileWriter writer = new FileWriter("StudyBlock.ics");
        writer.write(studyBlock);
        writer.close();
    }
}
