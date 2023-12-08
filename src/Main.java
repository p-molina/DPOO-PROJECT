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
        ShopManager shopManager1 = new ShopManager();//TODO comprovar si necesario
        ProductManager productManager = new ProductManager(productDAO, shopManager1);
        ShopManager shopManager = new ShopManager(shopDAO);

        //Aqui inicializamos las clases de la capa Presentacion
        ProductController productController = new ProductController(productManager);
        ShopController shopController = new ShopController(shopManager, productManager);
        CartController cartController = new CartController();

        //Inicializamos el menu y ejecutamos la pantalla principal
        Menu menu = new Menu(productController, shopController, cartController);
        menu.run();
    }
}