package UseCases;


import Entities.StudyMethod;

/**
 * A PreferencesManager class that changes stored preferences based on user input.
 * Includes StudyMethod helper for now, but could be expanded to include username or filepath preferences.
 */
public class PreferencesManager {



    /**
     * Takes user input Strings and creates a new StudyMethod matching their specifications.
     * @param activeTime How many minutes the user would like to work in a row
     * @param breakTime How many minutes of break between activeTimes
     * @return StudyMethod matching the user's preferences
     */
    public static StudyMethod changeStudyMethodHelper(String activeTime, String breakTime) {
        return new StudyMethod(Integer.parseInt(activeTime), Integer.parseInt(breakTime));
    }
}
