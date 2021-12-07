package Infrastructure;

import Entities.Checklist;

import java.io.*;

public class ChecklistReadWriter implements ReadWriter {

    /**
     * Writes the checklist to file at filepath.
     *
     * @param filepath location of the file being saved
     * @param checklist object to be serialized
     * @throws IOException if file cannot be saved
     */
    @Override
    public void saveToFile(String filepath, Object checklist) throws IOException {

        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(checklist);
        output.close();

    }

    /**
     * Reads the checklist at filepath
     * @param filepath location where the checklist is stored
     * @return checklist
     * @throws IOException if file cannot be read
     * @throws ClassNotFoundException if file is missing from directory
     */
    @Override
    public Checklist readFromFile(String filepath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        Checklist checklist = (Checklist) input.readObject();
        input.close();
        return checklist;
    }
}
