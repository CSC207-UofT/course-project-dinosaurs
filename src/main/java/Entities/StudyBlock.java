package Entities;

import UseCases.Schedulable;
import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import biweekly.util.Duration;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class StudyBlock implements Schedulable, Serializable {
    /**
     * Creates a new Entities.StudyBlock based on the selected length,
     * studyMethod, checklist.
     *
     * Holds an ArrayList<Task> assignedTasks that contains all the Tasks that were used in creating a StudyBlock
     */

    private StudyMethod studyMethod;
    private Checklist checklist;
    private ArrayList<String> listTODO;
    public String name;
    public int length;
    public ArrayList<Task> assignedTasks;

    /**
     * Constructor for the BlockScheduler.
     * @param name The name of the StudyBlock
     * @param studyMethod Preferred study scheduling method.
     * @param checklist List of Tasks to be completed.
     * @param length Length of the StudyBlock
     */
    public StudyBlock(String name, StudyMethod studyMethod,
                      Checklist checklist, int length) {
        this.studyMethod = studyMethod;
        this.checklist = checklist;
        this.listTODO = new ArrayList<>();
        this.name = name;
        this.length = length;
        this.assignedTasks = new ArrayList<>();
    }

    public StudyMethod getStudyMethod() {
        return studyMethod;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setLength(int length){ this.length = length; }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public void setStudyMethod(StudyMethod studyMethod) {
        this.studyMethod = studyMethod;
    }

    /**
     * Builds an ArrayList, <listTodo>, that incorporates the studyMethod and checklist.
     * Builds an ArrayList, <assignedTasks>, that hold all the used task names.
     */
    public void buildListTODO() {
        this.listTODO = assignTasks(breakUpStudyBlock());
    }


    public void buildassignedTasks() {
        this.assignedTasks = new ArrayList<>();
    }

    /**
     * Breaks up the study block into a two dimensional Array which stores the
     * different increments of active and break time.
     */

    public int[][] breakUpStudyBlock(){
        // We first need the number of full blocks we'll have and the amount of extra time leftover.
        // Note that extra + blocks == length
        int blocks = this.length / (this.studyMethod.getMethod().get(0) + this.studyMethod.getMethod().get(1));
        int extra = this.length % (this.studyMethod.getMethod().get(0) + this.studyMethod.getMethod().get(1));
        // We return a   2D array where the length is the number of block-breaks and the indexes of each
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

    /**
     * Builds a subblock for the full Studyblock. Is a helper function for assignTasks
     *
     * @param t_length An ArrayList<Integer> that holds the length of each task in the checklist
     * @param array Contains the studytime and breaktime for the subblock
     * @param checklist The checklist that contains the tasks
     * @param task The task index that is being used
     * @return A hashmap that stores the string of the subblock
     */

    public HashMap<ArrayList<String>, ArrayList<Integer>> fillblock(ArrayList<Integer> t_length, int[] array, Checklist checklist, int task) {
        ArrayList<String> msg = new ArrayList<>();
        ArrayList<Integer> tL = new ArrayList<>(t_length);
        HashMap<ArrayList<String>, ArrayList<Integer>> map =
                new HashMap<>();
        int t_left = array[0];
        int task_length = tL.get(0);

        if (task_length == t_left && t_length.size() == 1){
            msg.add(checklist.incomplete.get(task).name + " | " + t_length.get(0) + " min");
            assignedTasks.add(checklist.incomplete.get(task));
            tL.remove(0);
            map.put(msg, tL);
            return map;
        }
        if (task_length > t_left){
            msg.add(checklist.incomplete.get(task).name + " | " + t_left + " min");
            int left = task_length - t_left;
            tL.set(0, left);
            map.put(msg, tL);
            return map;
        }
        int x = task;
        do {
            task_length = tL.get(0);
            String task_name = checklist.incomplete.get(x).name;
            if (task_length == 0)
                return map;
            if (task_length > t_left) {
                msg.add(task_name + " | " + t_left + " min");
                int left = task_length - t_left;
                tL.set(0, left);
                map.put(msg, tL);
                return map;
            } else {
                msg.add(task_name + " | " + task_length + " min");
                assignedTasks.add(checklist.incomplete.get(task));
                t_left = t_left - task_length;
                tL.remove(0);
                x++;
            }
        } while (t_left > 0 && (x < checklist.incomplete.size()));
        map.put(msg, tL);
        return map;
    }

    /**
     * Builds a studyblock based on the given array by breakUpStudyBlock()
     *
     * @param array Contains the studytime and breaktime for the subblock given by breakUpStudyBlock
     * @return an ArrayList<String> that holds (task.name + " | " + task.length + " min");
     */

    public ArrayList<String> assignTasks(int[][] array) {
//      A subblock refers to a StudyMethod's [study, break] (ie. [25, 5] or [52, 17])
        buildassignedTasks();
        ArrayList<String> msg = new ArrayList<>();
        ArrayList<Integer> t_length = new ArrayList<>();
        for (int x = 0; x < (this.checklist.incomplete.size()); x++) {
            int num = this.checklist.incomplete.get(x).length;
            t_length.add(num);
        }
        int task_index = 0;

        for (int[] ints : array) {
            if (ints[0] > 0) {
                HashMap<ArrayList<String>, ArrayList<Integer>>
                        dic = new HashMap<>(fillblock(t_length, ints, this.checklist, task_index));
                Set<ArrayList<String>> key1 = dic.keySet();
                for (ArrayList<String> key : key1) {
                    msg.addAll(key);
                    int new1 = dic.get(key).size();
                    task_index = (t_length.size() - new1);
                    t_length = dic.get(key);
                }

                if (ints[1] > 0)
                    msg.add("Break" + " | " + ints[1] + " min");

                if (t_length.size() == 0)
                    return msg;

            } else {
                return msg;
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
        Summary summary = event.setSummary(this.name);
        summary.setLanguage("en-us");
        event.setDescription(toString());
        // the duration here is set to 75 minutes always due to time limitation and the preference of
        // not breaking clean architecture through taking the duration information from the controller class
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
        FileWriter writer = new FileWriter(this.name+".ics");
        writer.write(studyBlock);
        writer.close();
    }
}
