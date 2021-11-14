package UseCases;


import HelperFunctions.StudyBlockReadWriter;

import java.io.IOException;

public class StudyBlockSaver {

    StudyBlockReadWriter readWriter = new StudyBlockReadWriter();

    public StudyBlockSaver(StudyBlock studyblock) {
        try {
            readWriter.saveToFile(studyblock.name, studyblock);
        } catch (IOException e) {
            System.out.println(studyblock.name + " did not save.");
        }
    }
}