
import java.util.Scanner;

public class SafeInput {

    // PART A
    public static String getNonZeroLenString(Scanner pipe, String prompt) {

        String retString = "";

        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();

        } while (retString.length() == 0);

        return retString;
    }

    // PART B
    public static int getInt(Scanner pipe, String prompt) {

        int retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("You must enter a valid integer.");
                pipe.nextLine();
            }

        } while (!done);

        return retVal;
    }

    // PART C: getDouble
    public static double getDouble(Scanner pipe, String prompt) {

        double retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");

            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("You must enter a valid decimal number.");
                pipe.nextLine();
            }

        } while (!done);

        return retVal;
    }


    // PART D: getRangedInt
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Number must be between " + low + " and " + high + ".");
                }

            } else {
                System.out.println("You must enter a valid integer.");
                pipe.nextLine();
            }

        } while (!done);

        return retVal;
    }

    // PART E: getRangedDouble
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {

        double retVal = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();

                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Number must be between " + low + " and " + high + ".");
                }

            } else {
                System.out.println("You must enter a valid decimal number.");
                pipe.nextLine();
            }

        } while (!done);

        return retVal;
    }

    // PART F: getYNConfirm
    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        String response = "";

        do {
            System.out.print("\n" + prompt + " (Y/N): ");
            response = pipe.nextLine();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Please enter Y or N.");
            }

        } while (true);
    }

    // PART G: getRegExString
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        String response = "";

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();

            if (response.matches(regEx)) {
                return response;
            } else {
                System.out.println("Your entry is not in the correct format. Please try again.");
            }

        } while (true);
    }

    public static void prettyHeader(String msg) {

        int totalWidth = 60;

        System.out.println();

        // Top row
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Middle row
        int spaces = totalWidth - msg.length() - 6;
        int leftSpaces = spaces / 2;
        int rightSpaces = spaces - leftSpaces;

        System.out.print("***");

        for (int i = 0; i < leftSpaces; i++) {
            System.out.print(" ");
        }

        System.out.print(msg);

        for (int i = 0; i < rightSpaces; i++) {
            System.out.print(" ");
        }

        System.out.println("***");

        // Bottom row
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

}