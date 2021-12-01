package Entities;

import Constants.DueDateSingleton;
import Entities.Task;
import HelperFunctions.DueDateComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class Checklist implements Serializable, Iterable<Task>{
    /**
     * A Checklist which stores all the completed and incomplete
     * tasks and keeps the completed tasks sorted based on priority.
     * Default priority is by due date.
     * TODO data structure for complete, incomplete
     */

    public ArrayList<Task> incomplete;
    public ArrayList<Task> complete;
    public String priority;
    public String name;

    public Checklist(String name) {
        this.incomplete = new ArrayList<>();
        this.complete = new ArrayList<>();
        this.priority = DueDateSingleton.getInstance().getDueDate();
        this.name = name;
    }

    /**
     * Overrides the Tostring method.
     */
    @Override
    public String toString(){
        StringBuilder message = new StringBuilder();
        message.append("[").append(this.name).append("]\n\n");
        for (Task task : this.incomplete){
            message.append(task).append("\n").append("\n");
        }
        return message.toString();
    }

    /**
     * Returns an iterator for Tasks in Checklist.
     * @return Iterator for this checklist
     */
    @Override
    public Iterator<Task> iterator() {
        return new ChecklistIterator();
    }


    /**
     * An iterator for Checklist Tasks.
     */
    private class ChecklistIterator implements Iterator<Task> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < incomplete.size();
        }

        @Override
        public Task next() {
            Task curr;
            try {
                curr = incomplete.get(current);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current += 1;
            return curr;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException("Not supported.");
        }
    }




}
