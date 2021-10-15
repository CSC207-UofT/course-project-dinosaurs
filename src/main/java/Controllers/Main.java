package Controllers;

import Drivers.CmdLineUI;

public class Main {
    /**
     * Enables a Entities.Student to create and manage a UseCases.Checklist of Tasks, and to schedule
     * them in a UseCases.StudyBlock according to various priorities and study methods.
     * @param args No command line arguments supported.
     */
    public static void main(String[] args) {
        CmdLineUI ui = new CmdLineUI();
        // get user input to login Entities.Student
        String studentChoice = ui.studentLogin();

        // TODO check studentChoice is a valid Entities.Student in Map
        Student studentUser = ExampleData.studentMap.get(studentChoice);

        // get existing TodoList for this Entities.Student from storage and display it
        TodoList taskList = ExampleData.DEFAULT_TODOLIST;
        Presenter<TodoList> taskPrinter = new Presenter<>();
        taskPrinter.printer(taskList);

        // get user input for sorting priority
        String priorityChoice = ui.priorityChooser();
        // use priorityChoice to access correct priority from Map
        // possible choices "IMPORTANCE" "DUE_DATE" "WEIGHT"
        // TODO call UseCases.TaskManager to return a sorted TodoList with priorityChoice

        // get user input for study method
        String methodChoice = ui.studyMethodChooser();
        // possible choices "POMODORO"

        // get user input for length of time
        int lengthChoice = ui.lengthChooser();

        // create a new UseCases.StudyBlock with given params
        StudyBlock newBlock = new StudyBlock(lengthChoice, methodChoice,
                taskList, priorityChoice);

        // pass UseCases.StudyBlock to Controllers.Presenter to print
        Presenter<StudyBlock> blockPresenter = new Presenter<>();
        blockPresenter.printer(newBlock);
    }
}
