package Presentation;

import Bussines.Entities.Product;
import Bussines.Entities.Review;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
            case DELETE_MENU:
                System.out.println( "\t1) Delete product" +
                                    "\n\n\t2) Back");
                break;
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void showListOfProducts(HashMap<Product, Double> productRatingMap) {
        int maxNameLength = "Name".length();
        int maxBrandLength = "Brand".length();
        int maxCategoryLength = "Category".length();

        for (Product product : productRatingMap.keySet()) { //Obtenemos la informacion de el nombre mas largo para rescalar la columna de el nombre, la marca y la categoria
            maxNameLength = Math.max(maxNameLength, product.getName().length());
            maxBrandLength = Math.max(maxBrandLength, product.getBrand().length());
            maxCategoryLength = Math.max(maxCategoryLength, product.getCategory().length());
        }

        String headerFormat = "| %-" + maxNameLength + "s | %-" + maxBrandLength + "s | %-9s | %-" + maxCategoryLength + "s | %-6s |\n";
        String separator = String.format("+-%1$-" + maxNameLength + "s-+-%2$-" + maxBrandLength + "s-+-%3$-9s-+-%4$-" + maxCategoryLength + "s-+-%5$-6s-+\n",
                "","" ,"", "", "").replace(' ', '-');

        System.out.println();
        System.out.print(separator);
        System.out.printf(headerFormat, "Name", "Brand", "MRP", "Category", "Rating");
        System.out.print(separator);

        for (Map.Entry<Product, Double> entry : productRatingMap.entrySet()) {
            Product product = entry.getKey();
            Double avgRating = entry.getValue();
            String ratingStr = avgRating == -1 ? "N/A" : String.format("%.2f", avgRating);

            System.out.printf(headerFormat,
                    product.getName(), product.getBrand(), String.format("%.2f", product.getMrp()), product.getCategory(), ratingStr);
        }

        System.out.println(separator);
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


    public static int askForOption(String message, int min, int max) {//TODO el tractament de exepcións s'ha de fer als controlers, no a la UI (aquesta ha de quedar amb les funcións basiques de askFor...)
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

    public static double askForMaxPvp(String message, float min) {//TODO el tractament de exepcións s'ha de fer als controlers, no a la UI (aquesta ha de quedar amb les funcións basiques de askFor...)
        while (true) {
            try {
                System.out.print(message);
                double selection = scanner.nextDouble();
                if (selection >= min) {
                    scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                    return selection;
                } else {
                    System.out.println("\nERROR: The input is out of the range.\nEnter a number bigger than " + min + ".\nTry again...\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Input is not a valid number. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
    public static double askForDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double selection = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer en caso de entrada incorrecta
                    return selection;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Input is not a valid number. \nTry again...\n");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }


    public static void closeScanner() {
        scanner.close();
    }
}
