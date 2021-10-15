package Entities;

import java.util.*;

public class StudyMethod {
    /**
     * All of the supported StudyMethods used by BlockScheduler.
     * Tells BlockScheduler how to divide study and break time.
     *
     * preferred_method.size() == 2
     * preferred_method[0] is active time in minutes
     * preferred_method[1] is break time in minutes
     *
     * TODO implement additional methods
     * TODO move Pomodoro to constants
     */
    public static final ArrayList<Integer> POMODORO = new ArrayList<>(Arrays.asList(25, 5));
    public static final ArrayList<Integer> DESKTIME = new ArrayList<>(Arrays.asList(52, 17));
    public static final ArrayList<Integer> ULTRADIUM = new ArrayList<>(Arrays.asList(90, 0));
    private ArrayList<Integer> preferred_method;

    /**
     * Constructor for the Entities.StudyMethod.
     * Currently, preferred_method.size() == 2 only
     * @param method The preferred study scheduling method
     *               must be in accepted methods
     */
    public StudyMethod(ArrayList<Integer> method) {
        this.preferred_method = method;
    }

    /**
     * Returns this instance's preferred study method.
     * @return preferred_method This instance's chosen study method
     */
    public ArrayList<Integer> getMethod() {
        return this.preferred_method;
    }


    /**
     * Sets a new preferred study method.
     * @param method The preferred study scheduling method
     * @return true if the method was set successfully.
     */
    public boolean setMethod(ArrayList<Integer> method) {
        if (method.equals(POMODORO)) {
            this.preferred_method = POMODORO;
            return true;
        }
        if (method.equals(DESKTIME)) {
            this.preferred_method = DESKTIME;
            return true;
        }
        if (method.equals(ULTRADIUM)) {
            this.preferred_method = ULTRADIUM;
            return true;
        }
        // TODO add more methods
        return false;
    }
}
