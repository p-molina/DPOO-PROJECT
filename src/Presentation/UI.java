package Presentation;

import Bussines.Entities.Product;
import Bussines.Entities.Review;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);

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
            case MANAGE_SHOPS:
                System.out.println( "\n\t1) Create a Shop\n" +
                        "\t2) Expand a Shop's Catalogue\n" +
                        "\t3) Reduce a Shop's Catalogue\n" +
                        "\n\t4) Back\n");
                break;
            case SELECT_MODEL:
                System.out.println("\nThe system supports the following business models:\n" +
                        "\n" +
                        "\t1) Maximum Benefits\n" +
                        "\t2) Loyalty\n" +
                        "\t3) Sponsored");
                break;
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void showListOfProducts(List<Product> productList) {
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("| Name        | Brand      | MRP     | Category     | Rating  |");
        System.out.println("+-------------------------------------------------------------+");

        for (Product product : productList) {
            double avgRating = getAverageRating(product.getReviews());
            String ratingStr = avgRating == -1 ? "N/A" : String.format("%.2f", avgRating);

            System.out.printf("| %-11s | %-10s | %-7.2f | %-12s | %-6s |\n",
                    product.getName(), product.getBrand(), product.getMrp(), product.getCategory(), ratingStr);
        }

        System.out.println("+-------------------------------------------------------------+");
    }

    private static double getAverageRating(Review[] reviews) {
        if (reviews.length == 0) {
            return -1;
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getClassificationStars();
        }

        return sum / reviews.length;
    }


    public static int askForOption(String message, int min, int max) {
        int selection = 0;
        while (true) {
            System.out.print(message);
            try {
                selection = scanner.nextInt();
                if (selection >= min && selection <= max) {
                    scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                    return selection;
                } else {
                    System.out.println("\nERROR: The input is out of the range.\nEnter an option between " + min + " and " + max + ".\nTry again...\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Input is not an integer. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
            }
        }
    }

    public static String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int askForInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int input = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                return input;
            } catch (InputMismatchException e) {
                System.out.println("This isn't an integer!");
                scanner.nextLine();
            }
        }
    }

    public static float askForFloat(String message, float min) {
        while (true) {
            try {
                System.out.print(message);
                float selection = scanner.nextFloat();
                if (selection >= min) {
                    scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                    return selection;
                } else {
                    System.out.println("\nERROR: The input is out of the range.\nEnter a number bigger than " + min + ".\nTry again...\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Input is not a valid number. \nTry again...\n");
                scanner.nextLine();
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}
