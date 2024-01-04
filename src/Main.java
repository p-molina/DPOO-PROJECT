import Bussines.ProductManager;
import Bussines.ShopManager;
import Bussines.ShoppingCartManager;
import Persistance.API.APIProductDAO;
import Persistance.API.APIShopDAO;
import Persistance.DAO.ProductDAO;
import Persistance.DAO.ShopDAO;
import Presentation.CartController;
import Presentation.Menu;
import Presentation.ProductController;
import Presentation.ShopController;

/**
 * Clase principal que ejecuta la aplicación.
 */
public class Main {
    /**
     * Punto de entrada principal para la aplicación.
     *
     * Aquí se inicializan las diferentes capas de la aplicación, incluyendo la persistencia, la lógica de negocio
     * y la presentación. Se crean instancias de los DAOs, los gestores y los controladores, y finalmente se
     * inicia la ejecución del menú principal de la aplicación.
     *
     * @param args Argumentos pasados al programa (no utilizados).
     */
    public static void main(String[] args) {
        //Aqui inicializamos las clases de la capa Persistencia
        ProductDAO productDAO = new APIProductDAO();
        ShopDAO shopDAO = new APIShopDAO();

        //Aqui inicializamos las clases de la capa Bussines
        //MIRAR SI ESTO ESTA BIEN
        ProductManager productManager = new ProductManager(productDAO);
        ShopManager shopManager = new ShopManager(shopDAO);
        ShoppingCartManager shoppingCartManager = new ShoppingCartManager();

        //Aqui inicializamos las clases de la capa Presentacion

        ShopController shopController = new ShopController(shopManager, productManager, shoppingCartManager);
        ProductController productController = new ProductController(productManager, shopManager);
        CartController cartController = new CartController(shoppingCartManager, shopManager, productManager);

        //Inicializamos el menu y ejecutamos la pantalla principal
        Menu menu = new Menu(productController, shopController, cartController);
        menu.run();
    }
}