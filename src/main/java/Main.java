public class Main {
    /**
     * Enables a Student to create and manage a Checklist of Tasks, and to schedule
     * them in a StudyBlock according to various priorities and study methods.
     * @param args No command line arguments supported.
     */
    public static void main(String[] args) {
        CmdLineUI ui = new CmdLineUI();
        // get user input to login Student
        String studentChoice = ui.studentLogin();

        // TODO check studentChoice is a valid Student in Map
        Student studentUser = ExampleData.studentMap.get(studentChoice);

        // get existing TodoList for this Student from storage and display it
        TodoList taskList = ExampleData.DEFAULT_TODOLIST;
        Presenter<TodoList> taskPrinter = new Presenter<>();
        taskPrinter.printer(taskList);

        // get user input for sorting priority
        String priorityChoice = ui.priorityChooser();
        // use priorityChoice to access correct priority from Map
        // possible choices "IMPORTANCE" "DUE_DATE" "WEIGHT"
        // TODO call TaskManager to return a sorted TodoList with priorityChoice

        // get user input for study method
        String methodChoice = ui.studyMethodChooser();
        // possible choices "POMODORO"

        // get user input for length of time
        int lengthChoice = ui.lengthChooser();

        // create a new StudyBlock with given params
        StudyBlock newBlock = new StudyBlock(lengthChoice, methodChoice,
                taskList, priorityChoice);

        // pass StudyBlock to Presenter to print
        Presenter<StudyBlock> blockPresenter = new Presenter<>();
        blockPresenter.printer(newBlock);
    }
}
