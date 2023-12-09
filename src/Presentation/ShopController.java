package Presentation;

import Bussines.ProductManager;
import Bussines.ShopManager;

import java.io.IOException;

public class ShopController {

    private ShopManager shopManager;
    private ProductManager productManager;

    public ShopController(ShopManager shopManager, ProductManager productManager) {
        this.shopManager = shopManager;
        this.productManager = productManager;
    }

    public void checkFile() {
        try {
            shopManager.checkFile();
        } catch(IOException e) {
            UI.showMessage("ERROR: Problem with 'shops.json' file.");
        }
    }

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
    public void listShops()
    {
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
                        int option = UI.askForOption("\nChoose an option", 1, 3);
                        switch (option) {
                            case 1:
                                //String reviewsString = productManager.readReviews(nameProduct);
                                UI.showMessage("REVIEWS:");
                                break;
                            case 2:
                                //productManager.addReviewProdut();
                                UI.showMessage("PRODUCT WAS REVIEWED SUCCESFULLY");
                                break;
                            case 3:
                                //cardManger.addProduct();
                                UI.showMessage("PRODUCT WAS ADDED TO CARD");
                                break;
                        }
                    }
                } catch (IOException e) {
                    UI.showMessage("ERROR, CAN NOT ACCESS SHOP");
                }
            }
        } catch (IOException e) {
            UI.showMessage("ERROR, CAN NOT LIST SHOPS");
        }

    }

    private boolean createShop()
    {
        String name = UI.askForString("\nPlease enter the shop’s name: ");
        //COMPROVAR NOM UNIC
        try {
            if (!shopManager.isShopUnique(name)) {
                UI.showMessage("ERROR SHOP ALREADY EXISTS");
                return false;
            }
        } catch (IOException e) {
            e.getMessage();
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
                shopManager.createShop(name, description, year, "MAXIMUM_BENEFITS");
            } else if (modelSelection == 2) {
                shopManager.createShop(name, description, year, "LOYALTY");
            } else if (modelSelection == 3) {
                shopManager.createShop(name, description, year, "SPONSORED");
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
            e.getMessage();
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
            e.getMessage();
        }
        //COMPROVAR PRODUCTE NO REPETIT
        try {
            if(shopManager.isProductInCatalogue(nameProduct,nameShop))
            {
                UI.showMessage("ERROR PRODUCT IS ALREADY IN CATALOGUE");
                return false;
            }
        } catch (IOException e) {
            e.getMessage();
        }
        String productBrand = "";
        //OBTENIR LA BRAND DEL PRODUCTE
        try {
            productBrand = productManager.getBrandFromProduct(nameProduct);
        } catch (IOException e) {
            e.getMessage();
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
            e.getMessage();
        }
        UI.showMessage("\""+ nameProduct +"\" by\"" + productBrand +  "\" is now being sold at \"" + nameShop + "\".\n");
        try {
            shopManager.expandCatalogue(nameShop, nameProduct, productBrand, price);
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file! Going back.");
        }
        return true;
    }
    //Opción de reducir catalogo
    private boolean reduceCatalogue()
    {
        String nameShop = UI.askForString("\nPlease enter the shop’s name: ");
        //COMPROVAR TENDA EXISTEIX
        try {
            if (shopManager.isShopUnique(nameShop)) {
                UI.showMessage("ERROR SHOP DOES NOT EXIST");
                return false;
            }
        } catch (IOException e) {
            e.getMessage();
        }
        try {
            String catalogue = shopManager.getCatalogueFromShop(nameShop);
            UI.showMessage(catalogue);
        } catch (IOException e) {
            e.getMessage();
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
            e.getMessage();
        }
        return true;
    }
}
