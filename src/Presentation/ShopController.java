package Presentation;

import Bussines.ProductManager;
import Bussines.ShopManager;
import Bussines.ShoppingCartManager;

import java.io.IOException;

/**
 * Controlador para la gestión de tiendas.
 */
public class ShopController {
    private ShopManager shopManager;
    private ProductManager productManager;
    private ShoppingCartManager shoppingCartManager;

    /**
     * Constructor de la clase ShopController que recibe instancias de ShopManager y ProductManager.
     *
     * @param shopManager    Instancia de ShopManager para la gestión de tiendas.
     * @param productManager Instancia de ProductManager para la gestión de productos.
     * @param shoppingCartManager Instancia de ShoppingCartManager para la gestión del carrito
     */
    public ShopController(ShopManager shopManager, ProductManager productManager, ShoppingCartManager shoppingCartManager) {
        this.shopManager = shopManager;
        this.productManager = productManager;
        this.shoppingCartManager = shoppingCartManager;
    }
    /**
     * Comprueba la existencia del archivo de tiendas ('shops.json').
     * Muestra un mensaje de error si hay un problema con el archivo.
     */
    public void checkFile() {
        try {
            shopManager.checkFile();
        } catch(IOException e) {
            UI.showMessage("ERROR: Problem with 'shops.json' file.");
        }
    }
    /**
     * Ejecuta el menú de gestión de tiendas.
     * Muestra las opciones del menú y permite al usuario realizar acciones como crear tiendas,
     * ampliar el catálogo o reducir productos del catálogo de una tienda.
     * Retorna false cuando se elige salir del menú para volver al menú principal.
     */
    public void runShopMenu() {
        boolean isRunning = true;//fer isRunning = false; y torna al menú principal.

        while (isRunning) {
            UI.showMenu(MenuOptions.MANAGE_SHOPS);
            int selection = UI.askForOption("Choose an option: ", 1, 4);
            switch (selection) {
                case 1:
                    isRunning = createShop();
                    break;
                case 2:
                    isRunning = expandCatalog();
                    break;
                case 3:
                    isRunning = reduceCatalogue();
                    break;
                case 4:
                    isRunning = false;
                    break;
            }
        }
    }
    /**
     * Muestra la lista de tiendas disponibles y permite al usuario seleccionar una tienda
     * para ver su información detallada y acceder a su catálogo de productos.
     * El usuario puede elegir una tienda y luego un producto del catálogo para realizar acciones
     * como ver las reseñas, agregar una reseña al producto o agregarlo al carrito de compras.
     * Devuelve al menú principal cuando se selecciona "0) BACK" en cualquier punto del proceso.
     */
    public void listShops() {
        UI.showMenu(MenuOptions.LIST_SHOPS);
        String shopsList;
        String shopInfo;
        try {
            shopsList = shopManager.listShops();
            UI.showMessage(shopsList);
            UI.showMessage("0) BACK");
            int shopIndex = UI.askForInt("Which catalogue do you want to see? ");
            if (shopIndex != 0)
            {
                try {
                    shopInfo = shopManager.getShopInfo(shopIndex);
                    UI.showMessage(shopInfo);
                    UI.showMessage("0) BACK");
                    int catalogueIndex = UI.askForInt("Which one are you interested in? ");
                    if(catalogueIndex != 0) {
                        UI.showMenu(MenuOptions.MANAGE_CATALOGUE_PRODUCT);
                        int option = UI.askForOption("\nChoose an option: ", 1, 3);
                        String[] infoProduct = shopManager.getCatalogueProductInfo(shopIndex,catalogueIndex);
                        switch (option) {
                            case 1:
                                readReviews(infoProduct[0],infoProduct[1]);
                                break;
                            case 2:
                                reviewProduct(infoProduct[0],infoProduct[1]);
                                break;
                            case 3:
                                shoppingCartManager.addProductToCard(shopManager.getCatalogueProductFromIndex(shopIndex,catalogueIndex));
                                UI.showMessage("PRODUCT WAS ADDED TO CARD");
                                break;
                        }
                    }
                } catch (IOException e) {
                    UI.showMessage("\nERROR, CAN NOT ACCESS SHOP");
                }
            }
        } catch (IOException e) {
            UI.showMessage("ERROR, CAN NOT LIST SHOPS");
        }

    }
    private boolean createShop() {
        String name = UI.askForString("\nPlease enter the shop’s name: ");
        //COMPROVAR NOM UNIC
        try {
            if (!shopManager.isShopUnique(name)) {
                UI.showMessage("ERROR SHOP ALREADY EXISTS");
                return false;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }

        String description = UI.askForString("Please enter the shops’s description: ");
        int year = UI.askForInt("Please enter the shop founding year: ");
        //COMPROVAR ANY POSITIU
        if(year <= 0)
        {
            UI.showMessage("ERROR YEAR IS NEGATIVE");
            return false;
        }
        UI.showMenu(MenuOptions.SELECT_MODEL);
        int modelSelection = UI.askForOption("\nPlease pick the shop’s business model: ", 1, 3);
        try {
            if (modelSelection == 1) {
                shopManager.createShop(name, description, year, "MAXIMUM_BENEFITS", 0, null);
            } else if (modelSelection == 2) {
                double threshold = UI.askForDouble("Please enter the shop’s loyalty threshold: ");
                shopManager.createShop(name, description, year, "LOYALTY", threshold, null);
            } else if (modelSelection == 3) {
                String sponsor = UI.askForString("Please enter the shop’s sponsoring brand: ");
                shopManager.createShop(name, description, year, "SPONSORED", 0, sponsor);
            }
        } catch(IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
        }

        UI.showMessage(name + " is now part of the elCofre family. \n");
        return true;
    }
    private boolean expandCatalog() {
        String nameShop = UI.askForString("\nPlease enter the shop’s name: ");
        //COMPROVAR TENDA EXISTEIX
        try {
            if (shopManager.isShopUnique(nameShop)) {
                UI.showMessage("ERROR SHOP DOES NOT EXIST");
                return false;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        String nameProduct = UI.askForString("\nPlease enter the product’s name: ");
        //COMPROVAR PRODUCTE EXISTEIX
        try {
            if(!productManager.checkName(nameProduct))
            {
                UI.showMessage("ERROR PRODUCT DOES NOT EXIST");
                return false;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        //COMPROVAR PRODUCTE NO REPETIT
        try {
            if(shopManager.isProductInCatalogue(nameProduct,nameShop))
            {
                UI.showMessage("ERROR PRODUCT IS ALREADY IN CATALOGUE");
                return false;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        String productBrand;
        //OBTENIR LA BRAND DEL PRODUCTE
        try {
            productBrand = productManager.getBrandFromProduct(nameProduct);
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        double price = UI.askForDouble("\nPlease enter the product’s price at this shop: ");
        //COMPROVAR PVP OKEY
        try {
            if(productManager.getMRPFromProduct(nameProduct) < price)
            {
                UI.showMessage("ERROR PRICE IS NOT ACCEPTED");
                return false;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        UI.showMessage("\""+ nameProduct +"\" by\"" + productBrand +  "\" is now being sold at \"" + nameShop + "\".\n");
        try {
            shopManager.expandCatalogue(nameShop, nameProduct, productBrand, price);
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
        }
        return true;
    }
    private boolean reduceCatalogue() {
        String nameShop = UI.askForString("\nPlease enter the shop’s name: ");
        //COMPROVAR TENDA EXISTEIX
        try {
            if (shopManager.isShopUnique(nameShop)) {
                UI.showMessage("ERROR SHOP DOES NOT EXIST");
                return false;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        try {
            String catalogue = shopManager.getCatalogueFromShop(nameShop);
            UI.showMessage(catalogue);
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        int option = UI.askForInt("Which one would you like to remove? ");
        try {
            boolean okReduce = shopManager.reduceCatalogue(nameShop,option);
            if(!okReduce)
            {
                UI.showMessage("ERROR, CAN NOT REDUCE CATALOGUE!");
            }
            else {
                UI.showMessage("PRODUCT HAS BEEN REMOVED FOR CATALOGUE!");
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
            return false;
        }
        return true;
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
            UI.showMessage("\nERROR: Problem with the file! Going back...");
        }
    }
    private void reviewProduct(String productName, String brand) {
        try {
            UI.showMessage("WARNING: Enter the rate using '*', with values from 1-5. Rates beyond this range, or no input, will be counted as 0.\n");
            String rating = UI.askForString("Please rate the product (1-5 stars): ");
            String comment = UI.askForString("Please add a comment to your review: ");

            productManager.addReview(productName, rating, comment);

            UI.showMessage("\nThank you for your review of \"" + productName + "\" by \"" + brand + "\".");
        } catch (IOException e) {
            UI.showMessage("\nERROR: Problem with the file! Going back...");
        }
    }
}
