package constants;

import Entities.Student;

import java.util.*;

public class ExampleData {
    /**
     * This class could be merged with Constants, but it holds entities
     * that will belong somewhere else in future to conform to Clean Architecture.
     *
     * TODO move program files into named packages so this can be moved to Constants
     */

    //default Entities.Student
    public static final Student DEFAULT_STUDENT = new Student();
    public static Map<String, Student> studentMap;
    static {
        studentMap = new HashMap<>();
        studentMap.put("DEFAULT_STUDENT", DEFAULT_STUDENT);
    }

    //default TodoList
    // TODO populate this list with tasks
    public static final TodoList DEFAULT_TODOLIST = new TodoList();
}
