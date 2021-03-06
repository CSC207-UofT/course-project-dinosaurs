package Controllers;

import Constants.Constants;
import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;
import UseCases.DataAccessInterface;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class DataAccess implements DataAccessInterface {

    /**
     * List of Checklists (containing all Tasks) and List of StudyBlocks.
     * Read from memory when program starts, this is the central location
     * for all controllers to access and update. It is saved to disk when
     * the user chooses "Save and Exit".
     */
    @FXML static private List<StudyBlock> studyBlockList;
    @FXML static private List<Checklist> checklistList;
    @FXML static private StudyMethod chosenMethod;
    @FXML static private int checklistListIndex;
    @FXML static private int studyBlockListIndex;


    public DataAccess(){
        studyBlockList = new ArrayList<>();
        checklistList = new ArrayList<>();
        chosenMethod = new StudyMethod(Constants.POMODORO);
        checklistListIndex = 0;
        studyBlockListIndex = 0;
    }

    /**
     * Gets checklistList.
     * @return checklistList.
     */
    @Override
    public ArrayList<Checklist> getChecklistList(){
        return (ArrayList<Checklist>) checklistList;
    }

    /**
     * Gets Checklist at index from checklistList.
     * @return checklist
     */
    @Override
    public Checklist getChecklistFromList(int index) {return checklistList.get(index); }

    /**
     * Adds Checklist to checklistList.
     * @param checklist Checklist to be added.
     */
    @Override
    public void addToChecklistList(Checklist checklist){
        checklistList.add(checklist);
    }

    /**
     * Gets size of checklistList.
     * @return size of checklistList.
     */
    @Override
    public int getChecklistListSize(){
        return checklistList.size();
    }

    /**
     * Gets current index for checklistList.
     * @return checklistListIndex
     */
    @Override
    public int getChecklistListIndex(){
        return checklistListIndex;
    }

    /**
     * Get Checklist with name from checklistList
     * @return checklist.
     */
    @Override
    public Checklist getChecklistWithName(String name){
        for (Checklist checklist : checklistList){
            if (checklist.name.equals(name)){
                return checklist;
            }
        } return null;
    }

    /**
     * Sets checklistListIndex.
     * @param index index to be set.
     */
    @Override
    public void setChecklistListIndex(int index){
        checklistListIndex = index;
    }

    /**
     * Gets studyBlockList.
     * @return studyBlockList.
     */
    @Override
    public ArrayList<StudyBlock> getStudyBlockList(){
        return (ArrayList<StudyBlock>) studyBlockList;
    }

    /**
     * Adds studyblock to studyBlockList.
     * @param studyblock studyblock to be added.
     */
    @Override
    public void addToStudyBlockList(StudyBlock studyblock){
        studyBlockList.add(studyblock);
    }

    /**
     * Gets size of studyBlockList.
     * @return size of studyBlockList.
     */
    @Override
    public int getStudyBlockListSize(){
        return studyBlockList.size();
    }

    /**
     * Gets current index for studyBlockList.
     * @return studyBlockListIndex
     */
    @Override
    public int getStudyBlockListIndex(){
        return studyBlockListIndex;
    }

    /**
     * Sets studyBlockListIndex.
     * @param index index to be set.
     */
    @Override
    public void setStudyBlockListIndex(int index) {
        studyBlockListIndex = index;
    }



    /**
     * Gets current StudyMethod.
     * @return StudyMethod saved as preference.
     */
    @Override
    public StudyMethod getStudyMethod(){
        return chosenMethod;
    }

    /**
     * Sets current StudyMethod.
     * @param method method to be saved as preference.
     */
    @Override
    public void setStudyMethod(StudyMethod method){
        chosenMethod = method;
    }

}
