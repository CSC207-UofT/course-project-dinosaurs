package UseCases;

import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;

import java.util.ArrayList;

/**
 * An interface for the DataAccess file. Controllers call
 * these methods to manipulate the DataAccess file.
 */
public interface DataAccessInterface {

    /**
     * Gets checklistList.
     * @return checklistList.
     */
    ArrayList<Checklist> getChecklistList();

    /**
     * Get Checklist at index from checklistList
     * @return checklist.
     */
    Checklist getChecklistFromList(int index);

    /**
     * Get Checklist with name from checklistList
     * @return checklist.
     */
    Checklist getChecklistWithName(String name);

    /**
     * Adds Checklist to checklistList.
     * @param checklist Checklist to be added.
     */
    void addToChecklistList(Checklist checklist);

    /**
     * Gets size of checklistList.
     *
     * @return size of checklistList.
     */
    int getChecklistListSize();
    /**
     * Gets current index for checklistList.
     * @return checklistListIndex
     */
    int getChecklistListIndex();

    /**
     * Sets checklistListIndex.
     * @param index index to be set.
     */
    void setChecklistListIndex(int index);

    /**
     * Gets studyBlockList.
     * @return studyBlockList.
     */
    ArrayList<StudyBlock> getStudyBlockList();


    /**
     * Adds studyblock to studyBlockList.
     * @param studyblock studyblock to be added.
     */
    void addToStudyBlockList(StudyBlock studyblock);

    /**
     * Gets size of studyBlockList.
     * @return size of studyBlockList.
     */
    int getStudyBlockListSize();

    /**
     * Gets current index for studyBlockList.
     * @return studyBlockListIndex
     */
    int getStudyBlockListIndex();

    /**
     * Sets studyBlockListIndex.
     * @param index index to be set.
     */
    void setStudyBlockListIndex(int index);

    /**
     * Gets current StudyMethod.
     * @return StudyMethod saved as preference.
     */
    StudyMethod getStudyMethod();

    /**
     * Sets current StudyMethod.
     * @param method method to be saved as preference.
     */
    void setStudyMethod(StudyMethod method);

}
