package Controllers;

import java.io.IOException;

public interface ReadWriter {

    /**
     *
     * @param filepath location of the file being saved
     * @param o object to be serialized
     * @throws IOException If file cannot be saved
     */
    void saveToFile(String filepath, Object o) throws IOException;


    /**
     *
     * @param filepath location where the file is stored
     * @return checklist that was saved
     * @throws IOException If file cannot be found
     * @throws ClassNotFoundException If file is not in directory
     */
    Object readFromFile(String filepath) throws IOException, ClassNotFoundException;
}
