import java.util.ArrayList;

public class StudyBlock {
    public ArrayList<Task> todolist;
    public String studymethod;

    /**
     * TODO implement StudyBlock
     * TODO needs .length() in minutes
     */


     /**
      * Constructs an ArrayList that holds the assignment as the
      * key and the time as the value.
      *
      * @param todolist Incomplete tasks in Student's tasks
      * @param studymethod The prefered study method that is used to organize
      *                    the study block
     */

    public StudyBlock(ArrayList<Task> todolist, String studymethod) {
        this.todolist = todolist;
        this.studymethod = studymethod;
    }

    public StudyBlock(String studymethod){
        this.todolist = new ArrayList<Task>();
        this.studymethod = studymethod;
    }



    public String toString() {
        StringBuilder todo = new StringBuilder("-- TODO List --\n");

        for (Task item : this.todolist){
            todo.append((item.toString()) + "  |  " + item.length).append("\n")
        }
    }
}
