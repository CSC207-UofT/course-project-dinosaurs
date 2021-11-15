package UseCases;

import Entities.Checklist;
import Entities.Task;

import java.util.ArrayList;

public class TempCreator {

    /**
     * Combines checklist(s) into one checklist which will be used to create study blocks.
     * @param checklists the list of checklists to be combined
     * @param priority the priority which the temp checklist will be
     * @return the combined checklists
     */
    public static Checklist createTemp(String name, ArrayList<Checklist> checklists, String priority) {
        Checklist temp = new Checklist(name);
        TaskManager tm = new TaskManager();
        tm.changePriority(temp, priority);

        for (Checklist checklist : checklists){
            for (Task task : checklist) {
                tm.addTask(temp, task);
            }
        }
        return temp;
    }

}
