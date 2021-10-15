public class ExampleData {
    /**
     * This class could be merged with Constants, but it holds entities
     * that will belong somewhere else in future to conform to Clean Architecture.
     *
     * TODO move program files into named packages so this can be moved to Constants
     *
     * TODO Students will be stored in a Map
     */

    //default Student
    public static final Student DEFAULT_STUDENT = new Student();
    //default TodoList
    // TODO populate this list with tasks
    public static final TodoList DEFAULT_TODOLIST = new TodoList();
}
