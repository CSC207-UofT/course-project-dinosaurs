package Drivers;

public class Presenter<T> {
    /**
     * Generic Drivers.Presenter, to be extended in future
     * @param item Object to output
     */
    public void printer(T item) {
        System.out.println(item);
    }
}
