package Controllers;

import Entities.Checklist;
import Entities.StudyMethod;
import UseCases.StudyBlock;
import UseCases.TempCreator;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

import static Constants.Constants.DUE_DATE;
//import static Controllers.Data.checklistList;
//import static Controllers.Data.studyBlockList;

//public class addStudyBlockController {
//    public void addStudyBlock(ActionEvent actionEvent, String name, int length) {
//        /* static protected List<StudyBlock> studyBlockList = new ArrayList<>();
//
//
//
//            this.studyMethod = studyMethod;
//            this.checklist = checklist;
//            this.listTODO = new ArrayList<>();
//            this.name = name;
//            this.length = length;
//
//         */
//        //todo : fix later(add preference,checklist, length )
//        Checklist list = TempCreator.createTemp("examplename", checklistList, DUE_DATE);
//        StudyMethod method = new StudyMethod(StudyMethod.POMODORO);
//
//        StudyBlock block = new StudyBlock(name, method, list, length);
//        studyBlockList.add(block);
//
//    }
//
//}
