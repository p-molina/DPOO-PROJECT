package Presentation;

import Bussines.ShopManager;

public class ShopController {

    private ShopManager shopManager;

    public ShopController(ShopManager shopManager) {
        this.shopManager = shopManager;
    }
    public void runShopMenu() {
        boolean isRunning = true;//fer isRunning = false; y torna al menú principal.

        while (isRunning) {
            UI.showMenu(MenuOptions.MANAGE_SHOPS);
            int selection = UI.askForOption("Choose an option: ", 1, 3);
            switch (selection) {
                case 1:
                    isRunning = createShop();
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }

    private boolean createShop()
    {
        String name = UI.askForString("\nPlease enter the shop’s name: ");
        //COMPROVAR NOM UNIC
        if(shopManager.isShopUnique(name))
        {
            UI.showMessage("ERROR SHOP ALREADY EXISTS");
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

        if (modelSelection == 1) {
            shopManager.createShop(name, description, year, "MAXIMUM_BENEFITS");
        } else if (modelSelection == 2) {
            shopManager.createShop(name, description, year, "LOYALTY");
        } else if (modelSelection == 3)  {
            shopManager.createShop(name, description, year, "SPONSORED");
        }

        UI.showMessage(name + " is now part of the elCofre family. \n");
        return true;
    }
}
