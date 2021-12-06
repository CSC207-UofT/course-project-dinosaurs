package Infrastructure;


import Entities.StudyMethod;

import java.io.*;

public class PreferencesReadWriter implements ReadWriter {
    /**
     * Writes the studyMethod to file at filepath.
     *
     * @param filepath location of the file being saved
     * @param studyMethod object to be serialized
     * @throws IOException if file cannot be saved
     */
    @Override
    public void saveToFile(String filepath, Object studyMethod) throws IOException {

        OutputStream file = new FileOutputStream(filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(studyMethod);
        output.close();

    }

    /**
     * Reads the studyMethod at filepath
     * @param filepath location where the studyMethod is stored
     * @return studyMethod
     * @throws IOException if file cannot be read
     * @throws ClassNotFoundException if file is missing from directory
     */
    @Override
    public StudyMethod readFromFile(String filepath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        StudyMethod studyMethod = (StudyMethod) input.readObject();
        input.close();
        return studyMethod;
    }
}
