package Controllers;

import Constants.ExampleData;
import Drivers.CmdLineUI;
import Drivers.Presenter;
import Entities.Checklist;
import Entities.Student;
import Entities.StudyMethod;
import UseCases.Schedulable;
import UseCases.StudyBlock;

import java.io.IOException;

public class Main {
    /**
     * Enables a Entities.Student to create and manage a Entities.Checklist of Tasks, and to schedule
     * them in a UseCases.StudyBlock according to various priorities and study methods.
     * @param args No command line arguments supported.
     */
    public static void main(String[] args) throws IOException {
        CmdLineUI ui = new CmdLineUI();
        // get user input to login Entities.Student
        String studentChoice = ui.studentLogin();

        // TODO check studentChoice is a valid Entities.Student in Map
        Student studentUser = ExampleData.studentMap.get(studentChoice);

        // get existing TodoList for this Entities.Student from storage and display it
        Checklist taskList = ExampleData.DEFAULT_CHECKLIST;
        Presenter<Checklist> taskPrinter = new Presenter<>();
        taskPrinter.printer(taskList);

        // get user input for sorting priority NOT IMPLEMENTED
//        String priorityChoice = ui.priorityChooser();
        // use priorityChoice to access correct priority from Map
        // possible choices "DUE_DATE"
        // TODO call UseCases.TaskManager to return a sorted TodoList with priorityChoice

        // get user input for study method
        String methodChoice = ui.studyMethodChooser();
        // possible choices "POMODORO"
        // TODO check methodChoice is a valid Entities.StudyMethod
        StudyMethod methodChosen = new StudyMethod(ExampleData.methodMap.get(methodChoice));

        // get user input for length of time
        int lengthChoice = ui.lengthChooser();

        // create a new UseCases.StudyBlock with given params
        // TODO refactor based on Ken's UseCases.StudyBlock params
        StudyBlock newBlock = new StudyBlock(methodChosen,
                taskList);

        // pass UseCases.StudyBlock to Drivers.Presenter to print
        Presenter<StudyBlock> blockPresenter = new Presenter<>();
        blockPresenter.printer(newBlock);

       //write ics
       Schedulable obj = new StudyBlock(methodChosen, taskList);
        obj.writeICS();
    }
}
