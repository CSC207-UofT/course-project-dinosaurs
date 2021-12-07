package Entities;

import java.io.Serializable;
import java.util.*;

public class StudyMethod implements Serializable {
    private ArrayList<Integer> preferred_method;

    /**
     * Constructor for the Entities.StudyMethod.
     * Currently, preferred_method.size() == 2 only
     *
     * @param method The preferred study scheduling method
     *               must be in accepted methods
     */
    public StudyMethod(ArrayList<Integer> method) {
        this.preferred_method = method;
    }

    public StudyMethod(int activeTime, int breakTime) {
        this.preferred_method = new ArrayList<>(Arrays.asList(activeTime, breakTime));
    }

    /**
     * Returns this instance's preferred study method.
     *
     * @return preferred_method This instance's chosen study method
     */
    public ArrayList<Integer> getMethod() {
        return this.preferred_method;
    }


    /**
     * Sets a new preferred study method.
     *
     * @param method The preferred study scheduling method
     */

    public void setMethod(ArrayList<Integer> method) {
        this.preferred_method = method;
    }

    public void setMethod(int activeTime, int breakTime) {
        this.preferred_method = new ArrayList<>(Arrays.asList(activeTime, breakTime));
    }

    /**
     * Prints current Study Method active time and break time.
     * @return Study Method string
     */
    @Override
    public String toString() {
        return "Active Time: " + this.preferred_method.get(0) + " Break Time: " + this.preferred_method.get(1);
    }
}

