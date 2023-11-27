package Presentation;

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
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
