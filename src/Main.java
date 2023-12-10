import Bussines.ProductManager;
import Bussines.ShopManager;
import Persistance.ProductDAO;
import Persistance.ShopDAO;
import Presentation.CartController;
import Presentation.Menu;
import Presentation.ProductController;
import Presentation.ShopController;

public class Main {
    public static void main(String[] args) {
        //Aqui inicializamos las clases de la capa Persistencia
        ProductDAO productDAO = new ProductDAO();
        ShopDAO shopDAO = new ShopDAO();

        //Aqui inicializamos las clases de la capa Bussines
        //MIRAR SI ESTO ESTA BIEN
        ProductManager productManager = new ProductManager(productDAO);
        ShopManager shopManager = new ShopManager(shopDAO);

        //Aqui inicializamos las clases de la capa Presentacion

        ShopController shopController = new ShopController(shopManager, productManager);
        ProductController productController = new ProductController(productManager, shopManager);
        CartController cartController = new CartController();

        //Inicializamos el menu y ejecutamos la pantalla principal
        Menu menu = new Menu(productController, shopController, cartController);
        menu.run();
    }
}