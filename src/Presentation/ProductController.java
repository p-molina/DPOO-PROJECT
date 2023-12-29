package Presentation;

import Bussines.ProductManager;
import Bussines.ShopManager;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controlador para la gestión de productos.
 */
public class ProductController {
    private ProductManager productManager;
    private ShopManager shopManager;

    /**
     * Constructor para inicializar el controlador de productos.
     *
     * @param productManager Gestor de productos.
     * @param shopManager    Gestor de tiendas.
     */
    public ProductController(ProductManager productManager, ShopManager shopManager) {
        this.shopManager = shopManager;
        this.productManager = productManager;
    }
    /**
     * Verifica la existencia del archivo de productos.
     *
     * @throws FileNotFoundException Si el archivo no se encuentra.
     */
    public void checkProductFile() throws FileNotFoundException{
        productManager.checkProductFile();
    }
    /**
     * Ejecuta el menú de gestión de productos.
     */
    public void runProductMenu() {
        UI.showMenu(MenuOptions.MENU_PRODUCTO);
        int selection = UI.askForOption("Choose an option: ",1, 3);
        switch (selection) {
            case 1:
                createProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                break;
        }
    }
    private void createProduct() {
        String name = UI.askForString("\nPlease enter the product’s name: ");
        try {
            if (productManager.checkName(name)) {
                UI.showMessage("\nERROR: This product '" + name + "' already exists. Going back...");
                return;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file!. Going back...");
            return;
        }
        String brand = productManager.toTitleCase(UI.askForString("Please enter the product’s brand: "));
        double mrp;
        while(true) {
            mrp = UI.askForDouble("Please enter the product’s maximum retail price [Please! use ',']: ");
            if(mrp >= 0) {break;}
            UI.showMessage("\nSorry! This price cant be lower than 0. Try again...\n");
        }

        UI.showMenu(MenuOptions.SELECT_CATEGORY);
        int categorySelection = UI.askForOption("\nPlease pick the product’s category: ", 1, 3);
        try {
            if (categorySelection == 1) {
                productManager.createProduct(name, brand, mrp, "GENERAL");
            } else if (categorySelection == 2) {
                productManager.createProduct(name, brand, mrp, "REDUCED");
            } else if (categorySelection == 3) {
                productManager.createProduct(name, brand, mrp, "SUPER_REDUCED");
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file!. Going back...\n");
        }

        UI.showMessage("\nThe product \"" + name + "\" by \"" + brand + "\" was added to the system.");
    }
    private void deleteProduct() {
        try {
            UI.showListOfProducts(productManager.getProductsRatingMap());
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file!. Going back...");
            return;
        }

        UI.showMenu(MenuOptions.DELETE_MENU);
        if (UI.askForOption("\nChoose an option: ", 1, 2) == 1) {
            String name;
            while(true) {
                name = UI.askForString("\nPlease enter the product’s name to delete: ");
                try {
                    if (productManager.checkName(name)) {break;}
                    UI.showMessage("\nERROR: This product '" + name + "' does not exists. Try again...");
                } catch (IOException e) {
                    UI.showMessage("ERROR: Problem with the file!. Going back...");
                    break;
                }
            }
            String option = UI.askForString("Are you sure you want to remove \"" + name + "\"? ");
            if(option.equalsIgnoreCase("yes")) {
                try {
                    productManager.deleteProduct(name);
                    //Eliminamos el prducto de todas las tiendas en las que se encuentre
                    shopManager.deleteProductFromAllShops(name);

                    UI.showMessage("\"" + name + "\" has been withdrawn from sale.");
                } catch (IOException e) {
                    UI.showMessage("ERROR: Problem with the file!. Going back...");
                }
            }
        }
    }
    /**
     * Ejecuta el menú de búsqueda de productos.
     */
    public void runSearchMenu() {
        searchProduct(UI.askForString("\nEnter your query: "));
    }
    private void searchProduct(String query) {
        try {
            UI.showMessage("\nThe following products were found:");

            String[][] productsInfo = productManager.searchProduct(query);
            for (int i = 0; i < productsInfo.length; i++) {
                String[] productRow = productsInfo[i];
                // Obtener información de tiendas para el producto actual
                String[][] shopInfo = shopManager.getCatalogueSearch(productRow);

                // Mostrar información del producto
                String productInfo = String.join(" ", productRow);
                UI.showMessage("\n" + productInfo);

                if(shopInfo.length == 0) {
                    UI.showMessage("\tThis product is not currently being sold in any shops.");
                }
                else {
                    UI.showMessage("\tSold at: ");
                    // Mostrar información de las tiendas para este producto
                    for (String[] shopRow : shopInfo) {
                        String shopDetails = String.join(" ", shopRow);
                        UI.showMessage(shopDetails);
                    }
                }
            }

            UI.showMessage("\n\n" + (productsInfo.length + 1) + ") Back");

            int option = UI.askForOption("\nWhich one would you like to review? ", 1, (productsInfo.length + 1));
            if (productsInfo.length + 1 == option) {return;}
            runReviewMenu(productsInfo[option - 1][1].replaceAll("\"", ""), productsInfo[option - 1][3].replaceAll("\"", ""));

        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back...");
        }
    }
    private void runReviewMenu(String productName, String brand) {
        UI.showMenu(MenuOptions.MENU_REVIEWS);
        int option = UI.askForOption("Choose an option: ", 1, 2);

        switch (option) {
            case 1 -> readReviews(productName, brand);
            case 2 -> reviewProduct(productName, brand);
        }
    }
    private void readReviews(String productName, String brand) {
        try {
            UI.showMessage("\nThese are the reviews for \"" + productName + "\" by \"" + brand + "\":\n");
            String[] reviews = productManager.getReviews(productName);
            if (reviews.length == 0) {//Si el size es 0 significa que no tiene ninguna review
                UI.showMessage("\tBe the first to put a review!");
                return;
            }
            double averageRating = 0;

            for (int i = 0; i < reviews.length; i++) {
                averageRating +=  Character.getNumericValue(reviews[i].charAt(0));
                UI.showMessage("\t" + reviews[i]);
                if (i == reviews.length - 1) {
                    UI.showMessage("\n\tAverage rating: " + (averageRating / reviews.length) + "*");
                }
            }
        } catch(IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back...");
        }
    }
    private void reviewProduct(String productName, String brand) {

        try {
            int rating = UI.askForOption("Please rate the product (1-5 stars): ", 1, 5);
            String comment = UI.askForString("Please add a comment to your review: ");

            productManager.addReview(productName, rating, comment);

            UI.showMessage("Thank you for your review of \"" + productName + "\" by \"" + brand + "\".");
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back...");
        }
    }
}
