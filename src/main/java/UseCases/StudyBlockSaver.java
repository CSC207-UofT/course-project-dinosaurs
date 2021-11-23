package UseCases;


import HelperFunctions.StudyBlockReadWriter;

import java.io.IOException;

public class StudyBlockSaver {

    /**
     * A class which allows an inputted studyblock to be saved using readWriter.
     */

    StudyBlockReadWriter readWriter = new StudyBlockReadWriter();


    /**
     * Saves the inputted study block as the study block's name.
     * @param studyblock The study block to be saved to the file.
     */
    public StudyBlockSaver(StudyBlock studyblock) {
        try {
            readWriter.saveToFile(studyblock.name, studyblock);
        } catch (IOException e) {
            System.out.println(studyblock.name + " did not save.");
        }
    }
}