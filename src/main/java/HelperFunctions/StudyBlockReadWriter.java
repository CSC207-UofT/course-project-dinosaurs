package HelperFunctions;

import HelperFunctions.ReadWriter;
import UseCases.StudyBlock;

import java.io.*;

public class StudyBlockReadWriter implements ReadWriter {


    /**
     * Writes the checklist to file at filepath.
     *
     * @param filepath location of the file being saved
     * @param studyblock object to be serialized
     * @throws IOException if file cannot be saved
     */
    @Override
    public void saveToFile(String filepath, Object studyblock) throws IOException {

        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(studyblock);
        output.close();

    }

    /**
     * Reads the checklist at filepath
     * @param filepath location where the checklist is stored
     * @return checklist
     * @throws IOException if file cannot be read
     * @throws ClassNotFoundException if file is not in directory
     */
    @Override
    public StudyBlock readFromFile(String filepath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        StudyBlock studyblock = (StudyBlock) input.readObject();
        input.close();
        return studyblock;
    }

}
