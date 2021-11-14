package UseCases;

import Controllers.ChecklistReadWriter;
import Entities.Checklist;

import java.io.IOException;

public class ChecklistSaver {

    ChecklistReadWriter readWriter = new ChecklistReadWriter();

    public ChecklistSaver(Checklist checklist) {
        try {
            readWriter.saveToFile(checklist.name, checklist);
        } catch (IOException e) {
            System.out.println(checklist.name + " did not save.");
        }
    }
}
