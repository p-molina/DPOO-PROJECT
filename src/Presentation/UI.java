package Presentation;

import java.util.Scanner;

import static Presentation.MenuOptions.MENU_PRINCIPAL;

public class UI {
    public UI() {}

    public static void showMenu(MenuOptions seleccionMenu) {
        switch(seleccionMenu) {
            case MENU_PRINCIPAL:
                System.out.println( "\t__| |_________________________________| |__\n" +
                                    "\t__   _________________________________   __\n" +
                                    "\t  | |                                 | |  \n" +
                                    "\t  | |      _  _____       __          | |  \n" +
                                    "\t  | |     | |/ ____|     / _|         | |  \n" +
                                    "\t  | |  ___| | |     ___ | |_ _ __ ___ | |  \n" +
                                    "\t  | | / _ | | |    / _ \\|  _| '__/ _ \\| |  \n" +
                                    "\t  | ||  __| | |___| (_) | | | | |  __/| |  \n" +
                                    "\t  | | \\___|_|\\_____\\___/|_| |_|  \\___|| |  \n" +
                                    "\t__| |_________________________________| |__\n" +
                                    "\t__   _________________________________   __\n" +
                                    "\t  | |                                 | |  " +
                                    "\n  Welcome to elCofre Digital Shopping Experiences. \n"
                                    );
                break;
            case CHECKING_FILES:
                System.out.println("Verifying local files...");
                break;
            case EXIT_PROGRAM:
                System.out.println("Shutting down...");
                break;
            case START_PROGRAM:
                System.out.println("Starting program...");
                break;
            case MAIN_MENU:
                System.out.println( "\n\t1) Manage Products\n" +
                                    "\t2) Manage Shops\n" +
                                    "\t3) Search Products\n" +
                                    "\t4) List Shops\n" +
                                    "\t5) Your Cart\n" +
                                    "\n\t6) Exit\n"
                                    );
                break;
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static int askForInt(String message, int min, int max) {
        int selection;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print(message);
            try {
                selection = scanner.nextInt();
                if (selection >= min && selection <= max) {break;} //If the selection is in the range it breaks the infinite loop.
                else {
                    //We show the user that he is not in the range
                    System.out.println("\nERROR: The input is out of the range.\n Enter an option between" + min + " and " + max + ".\n Try again...\n");
                }
            } catch (Exception e) { //To avoid any other exception from the .nextInt() [InputMismatchException, NoSuchElementException or IllegalStateException]
                System.out.println("\nERROR: Problem with the input. \nTry again...\n");
            }
        }

        return selection;
    }
}
