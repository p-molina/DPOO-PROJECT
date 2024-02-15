package Presentation;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Products.Product;
import Bussines.Entities.Shop.LoyaltyShop;
import Bussines.Entities.Shop.Shop;
import Bussines.Entities.Shop.SponsoredShop;
import Bussines.Entities.Products.Product;

import java.util.*;

/**
* Clase para mostrarle la información al usuario
 */
public class UI {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Muestra diferentes menús basados en la opción de menú seleccionada.
     *
     * @param seleccionMenu La opción de menú a mostrar.
     */
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
                                    "\n\n\t2) Back\n");
                break;
            case LIST_SHOPS:
                System.out.println("\nThe elCofre family is formed by the following shops: ");
                break;
            case MANAGE_CATALOGUE_PRODUCT:
                System.out.println( "\n\t1) Read Reviews\n" +
                        "\t2) Review Product\n" +
                        "\t3) Add to Card");
                break;

            case MENU_REVIEWS:
                System.out.println( "\n\t1) Read reviews \n" +
                        "\t2) Review Product\n");
                break;
            case MENU_CARD:
                System.out.println( "\n\t1) Checkout \n" +
                        "\t2) Clear Card\n" +
                        "\n\t3) Back");
                break;
        }
    }
    /**
     * Muestra un mensaje dado.
     *
     * @param message El mensaje a mostrar.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Muestra una lista de productos junto con sus calificaciones.
     *
     * @param productRatingMap Un HashMap que vincula los Productos con sus calificaciones promedio.
     */
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

    public static void showListOfShops(List<Shop> shopList) {
        int maxNameLength = "Name".length();
        int maxDescriptionLength = "Description".length();
        int maxSinceLength = "Since".length();
        int maxEarningsLength = "Earnings".length();
        int maxBusinessModelLength = "Business Model".length();
        int maxSpecialFeatureLength = "Special Feature".length();

        // Ajustar las longitudes de las columnas basadas en los valores de las tiendas
        for (Shop shop : shopList) {
            maxNameLength = Math.max(maxNameLength, shop.getName().length());
            maxDescriptionLength = Math.max(maxDescriptionLength, shop.getDescription().length());
            maxBusinessModelLength = Math.max(maxBusinessModelLength, shop.getBusinessModel().length());

            if (shop instanceof LoyaltyShop) {
                maxSpecialFeatureLength = Math.max(maxSpecialFeatureLength, ("Loyalty: " + ((LoyaltyShop) shop).getLoyaltyThreshold()).length());
            } else if (shop instanceof SponsoredShop) {
                maxSpecialFeatureLength = Math.max(maxSpecialFeatureLength, ("Sponsor: " + ((SponsoredShop) shop).getSponsorBrand()).length());
            }
        }

        String headerFormat = "| %-" + maxNameLength + "s | %-" + maxDescriptionLength + "s | %-" + maxSinceLength + "s | %-" + maxEarningsLength + "s | %-" + maxBusinessModelLength + "s | %-" + maxSpecialFeatureLength + "s |\n";
        String separator = String.format("+-%1$-" + maxNameLength + "s-+-%2$-" + maxDescriptionLength + "s-+-%3$-" + maxSinceLength + "s-+-%4$-" + maxEarningsLength + "s-+-%5$-" + maxBusinessModelLength + "s-+-%6$-" + maxSpecialFeatureLength + "s-+\n",
                "", "", "", "", "", "").replace(' ', '-');

        System.out.println();
        System.out.print(separator);
        System.out.printf(headerFormat, "Name", "Description", "Since", "Earnings", "Business Model", "Special Feature");
        System.out.print(separator);

        for (Shop shop : shopList) {
            String specialFeature = "";
            if (shop instanceof LoyaltyShop) {
                specialFeature = "Loyalty: " + ((LoyaltyShop) shop).getLoyaltyThreshold();
            } else if (shop instanceof SponsoredShop) {
                specialFeature = "Sponsor: " + ((SponsoredShop) shop).getSponsorBrand();
            }

            System.out.printf(headerFormat,
                    shop.getName(),
                    shop.getDescription(),
                    String.valueOf(shop.getSince()),
                    String.format("%.2f", shop.getEarnings()),
                    shop.getBusinessModel(),
                    specialFeature);
        }

        System.out.println(separator);
    }

    public static void showListOfCatalogProducts(List<CatalogProduct> productList) {
        int maxProductNameLength = "Product Name".length();
        int maxBrandNameLength = "Brand Name".length();
        int maxShopNameLength = "Shop Name".length();
        int maxPriceLength = "Price".length();

        // Ajustar las longitudes de las columnas basadas en los valores de los productos
        for (CatalogProduct product : productList) {
            maxProductNameLength = Math.max(maxProductNameLength, product.getNameProduct().length());
            maxBrandNameLength = Math.max(maxBrandNameLength, product.getNameBrand().length());
            maxShopNameLength = Math.max(maxShopNameLength, product.getNameShop().length());
            // Asumiendo que queremos una longitud mínima para el precio para manejar el formato de precio
            maxPriceLength = Math.max(maxPriceLength, String.format("%.2f", product.getPrice()).length());
        }

        String headerFormat = "| %-" + maxProductNameLength + "s | %-" + maxBrandNameLength + "s | %-" + maxShopNameLength + "s | %" + maxPriceLength + "s |\n";
        String separator = String.format("+-%1$-" + maxProductNameLength + "s-+-%2$-" + maxBrandNameLength + "s-+-%3$-" + maxShopNameLength + "s-+-%4$" + maxPriceLength + "s-+\n",
                "", "", "", "").replace(' ', '-');

        System.out.println();
        System.out.print(separator);
        System.out.printf(headerFormat, "Product Name", "Brand Name", "Shop Name", "Price");
        System.out.print(separator);

        int i = 1;

        for (CatalogProduct product : productList) {
            System.out.printf(headerFormat,
                    i + ". " + product.getNameProduct(),
                    product.getNameBrand(),
                    product.getNameShop(),
                    String.format("%.2f", product.getPrice()));
            i++;
        }

        System.out.println(separator);
    }

    /**
     * Solicita al usuario una opción dentro de un rango especificado.
     *
     * @param message El mensaje de solicitud para el usuario.
     * @param min La opción mínima válida.
     * @param max La opción máxima válida.
     * @return La opción seleccionada por el usuario.
     */
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
    /**
     * Solicita al usuario una cadena de texto.
     *
     * @param message El mensaje de solicitud para el usuario.
     * @return La cadena de texto introducida por el usuario.
     */
    public static String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    /**
     * Solicita al usuario un número entero.
     *
     * @param message El mensaje de solicitud para el usuario.
     * @return El número entero introducido por el usuario.
     */
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
    /**
     * Solicita al usuario un valor double.
     *
     * @param message El mensaje de solicitud para el usuario.
     * @return El valor double introducido por el usuario.
     */
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
    /**
     * Cierra el recurso del escáner.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
