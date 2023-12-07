package Presentation;

import Bussines.ShopManager;

import java.io.IOException;

public class ShopController {

    private ShopManager shopManager;

    public ShopController(ShopManager shopManager) {
        this.shopManager = shopManager;
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
                    break;
                case 3:
                    break;
                case 4:
                    isRunning = false;
                    break;
            }
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
}
