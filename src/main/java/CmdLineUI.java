import java.util.*;

public class CmdLineUI {
    /**
     * Basic command line interface to log in an existing Student.
     * @return string that matches the key of a valid Student
     */
    public String studentLogin() {
        System.out.println("Login as Default Student: Y/N?");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        while (!response.equals("Y")) {
            System.out.println("Please choose Y.");
            response = scanner.nextLine();
        }
        // TODO a way to createStudent, check Student is valid in Main
        return "DEFAULT_STUDENT";
    }

    /**
     * Basic command line interface to choose a sorting priority.
     * @return string of a valid sorting priority
     */
    public String priorityChooser() {
        System.out.println("Please enter number for priority type: ");
        System.out.println("1. Importance");
        System.out.println("2. Due Date");
        System.out.println("3. Weight");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        while (!(response.equals("1") || response.equals("2") || response.equals("3"))) {
            System.out.println("Please choose 1, 2, or 3.");
            response = scanner.nextLine();
        }
        if (response.equals("1")) {
            return "IMPORTANCE";
        }
        if (response.equals("2")) {
            return "DUE_DATE";
        }
        else {
            return "WEIGHT";
        }
    }

    /**
     * Basic command line interface to choose a study method.
     * @return string of a valid study method
     */
    public String studyMethodChooser() {
        System.out.println("Please enter number for preferred Study Method: ");
        System.out.println("1. Pomodoro (25 min on, 5 min off");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        while (!response.equals("1")) {
            System.out.println("Please choose 1.");
            response = scanner.nextLine();
        }
//        if (response.equals("1")) { // for when more methods are added
//            return "POMODORO";
//        }
        return "POMODORO";
    }

    /**
     * Basic command line interface to choose the length of available study time.
     * @return int in minutes of study time
     */
    public int lengthChooser() {
        System.out.println("Please enter the length of your study time in minutes (ints only");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
        }
}



//    private void taskCreator() {
//
//    }



