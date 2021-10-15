import java.util.*;

public class CmdLineUI {
    public String studentLogin() {
        System.out.println("Login as Default Student: Y/N?");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        while (!response.equals("Y")) {
            System.out.println("Please choose Y.");
            response = scanner.nextLine();
        }
        // TODO add map of Students, a way to createStudent, check Student is valid in Main
        return "DEFAULT_STUDENT";
    }

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

    public int lengthChooser() {
        System.out.println("Please enter the length of your study time in minutes (ints only");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
        }
    }

}

//    private void taskCreator() {
//
//    }



