package UseCases;

import Infrastructure.ChecklistReadWriter;
import Entities.Checklist;

import java.io.IOException;

public class ChecklistSaver {

    /**
     * A class which allows an inputted checklist to be saved using readWriter.
     */

    ChecklistReadWriter readWriter = new ChecklistReadWriter();


    /**
     * Saves the inputted checklist as the study block's name.
     * @param checklist The checklist to be saved to the file.
     */
    public ChecklistSaver(Checklist checklist) {
        try {
            readWriter.saveToFile(checklist.name, checklist);
        } catch (IOException e) {
            System.out.println(checklist.name + " did not save.");
        }
    }
}
