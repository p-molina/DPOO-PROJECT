package Presentation;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UI {
    private static Scanner scanner;
    public UI() {
        scanner = new Scanner(System.in);
    }

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
            case MENU_PRODUCTO:
                System.out.println( "\n\t1) Create a Product\n" +
                                    "\t2) Remove a Product\n" +
                                    "\n\t3) Back\n");
                break;
            case SELECT_CATEGORY:
                System.out.println("\nThe system supports the following product categories:\n" +
                        "\n" +
                        "\t1) General\n" +
                        "\t2) Reduced Taxes\n" +
                        "\t3) Superreduced Taxes");
                break;
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static int askForInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int selection = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer después de leer el entero

                if (selection >= min && selection <= max) {
                    return selection;
                } else {
                    // Mostrar el mensaje de error para la selección fuera de rango
                    System.out.println("\nERROR: The input is out of the range.\nEnter an option between " + min + " and " + max + ".\nTry again...\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Input is not an integer. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta

            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("\nERROR: Problem with the input. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
            }
        }
    }
    public static String askForString(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();

                if (!input.trim().isEmpty()) {
                    return input;
                } else {
                    System.out.println("\nERROR: The input cannot be empty. \nTry again...\n");
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("\nERROR: Problem with the input. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
            }
        }
    }
    public static float askForFloat(String message, float min, float max) {
        while (true) {
            try {
                System.out.print(message);
                float selection = scanner.nextFloat();
                scanner.nextLine(); // Limpiar el buffer después de leer el float

                if (selection >= min && selection <= max) {
                    return selection;
                } else {
                    System.out.println("\nERROR: The input is out of the range.\nEnter a number between " + min + " and " + max + ".\nTry again...\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Input is not a valid number. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("\nERROR: Problem with the input. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
            }
        }
    }
}
