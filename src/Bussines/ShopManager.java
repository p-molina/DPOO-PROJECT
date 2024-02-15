package Bussines;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Shop.LoyaltyShop;
import Bussines.Entities.Shop.MaximumBenefitsShop;
import Bussines.Entities.Shop.Shop;
import Bussines.Entities.Shop.SponsoredShop;
import Persistance.DAO.ShopDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Gestor de productos que maneja las operaciones relacionadas con tiendas.
 */
public class ShopManager {
    private ShopDAO shopDAO;

    /**
     * Constructor de la clase ShopManager que recibe un objeto ShopDAO como parámetro.
     *
     * @param shopDAO El objeto ShopDAO que se utiliza para gestionar las tiendas.
     */
    public ShopManager(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    /**
     * Verifica la existencia del archivo asociado a la gestión de tiendas.
     * Lanza una excepción en caso de que el archivo no exista.
     *
     * @throws IOException Si no se encuentra el archivo o ocurre un error de E/S.
     */
    public void checkFile() throws IOException {
        shopDAO.checkFile();
    }
    /**
     * Crea una nueva tienda y la agrega al repositorio de tiendas.
     *
     * @param name         Nombre de la tienda a crear.
     * @param description  Descripción de la tienda.
     * @param since        Año de fundación de la tienda.
     * @param nameModel    Nombre del modelo de negocio de la tienda.
     * @throws IOException Si ocurre algún error de entrada o salida al operar con el repositorio.
     */
    public void createShop(String name, String description, int since, String nameModel, double threshold, String sponsor) throws IOException {
        switch(nameModel) {
            case "MAXIMUM_BENEFITS":
                shopDAO.createShop(new MaximumBenefitsShop(name, description, since, 0, nameModel));
                break;
            case "LOYALTY":
                shopDAO.createShop(new LoyaltyShop(name, description, since, 0, nameModel, threshold));
                break;
            case "SPONSORED":
                shopDAO.createShop(new SponsoredShop(name, description, since, 0, nameModel, sponsor));
                break;
        }
    }

    /**
     * Verifica si el nombre de una tienda es único en el repositorio.
     *
     * @param name Nombre de la tienda a verificar.
     * @return true si el nombre de la tienda es único, false si ya existe una tienda con ese nombre.
     * @throws IOException Si ocurre algún error de entrada o salida al operar con el repositorio.
     */
    public boolean isShopUnique(String name) throws IOException {
        Shop isUnique = findByName(name);
        return isUnique == null;
    }
    /**
     * Busca una tienda por su nombre en el repositorio de tiendas.
     *
     * @param name Nombre de la tienda a buscar.
     * @return La tienda encontrada por su nombre, o null si no se encuentra ninguna tienda con ese nombre.
     * @throws IOException Si ocurre algún error de entrada o salida al operar con el repositorio.
     */
    public Shop findByName(String name) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();

        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
    }
    /**
     * Expande el catálogo de productos de una tienda específica agregando un nuevo producto.
     *
     * @param nameShop   El nombre de la tienda a la que se agregará el producto.
     * @param nameProduct   El nombre del nuevo producto.
     * @param nameBrand     La marca del nuevo producto.
     * @param price     El precio del nuevo producto.
     * @throws IOException Si ocurre un error de entrada/salida al operar con el repositorio de tiendas.
     */
    public void expandCatalogue(String nameShop, String nameProduct, String nameBrand, double price) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        int position = 0;

        for (Shop shop : shops) {
            if(shop.getName().equals(nameShop)) {
                if (shop.getCatalogProductList() != null) {
                    List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                    CatalogProduct newProduct = new CatalogProduct(nameProduct, nameBrand, nameShop,price);
                    catalogProductList.add(newProduct);
                    break;
                }
            }
            position++;
        }

        shopDAO.deleteShop(position);
        shopDAO.createShop(shops.get(position));

    }
    /**
     * Elimina un producto específico de un catálogo de una tienda.
     *
     * @param nameProduct El nombre del producto que se va a eliminar.
     * @param nameShop    El nombre de la tienda de la que se eliminará el producto.
     * @throws IOException Si ocurre un error de entrada/salida al operar con el repositorio de tiendas.
     */
    public void deleteProductFromShop(String nameProduct, String nameShop) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        int position = 0;

        for (Shop shop : shops) {
            if (shop.getName().equals(nameShop)) {
                if (shop.getCatalogProductList() != null) {
                    List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                    Iterator<CatalogProduct> iterator = catalogProductList.iterator();
                    while (iterator.hasNext()) {
                        CatalogProduct product = iterator.next();
                        if (product.getNameProduct().equals(nameProduct)) {
                            iterator.remove(); // Eliminar el producto del catálogo
                            break; // Terminar el bucle al encontrar el producto
                        }
                    }
                    break; // Terminar la búsqueda al encontrar la tienda
                }
            }
            position++;
        }

        shopDAO.deleteShop(position);
        shopDAO.createShop(shops.get(position));
    }
    /**
     * Verifica si un producto está presente en el catálogo de una tienda específica.
     *
     * @param nameProduct El nombre del producto que se busca en el catálogo de la tienda.
     * @param nameShop    El nombre de la tienda donde se buscará el producto.
     * @return true si el producto está presente en el catálogo de la tienda, false en caso contrario.
     * @throws IOException Si ocurre un error de entrada/salida al operar con el repositorio de tiendas.
     */
    public boolean isProductInCatalogue(String nameProduct, String nameShop) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        for (Shop shop : shops) {
            if (shop.getName().equals(nameShop)) {
                List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                if (catalogProductList != null) {
                    for (CatalogProduct product : catalogProductList) {
                        if (product.getNameProduct().equals(nameProduct)) {
                            return true; // El producto está en el catálogo
                        }
                    }
                }
                break; // Terminar la búsqueda al encontrar la tienda
            }
        }
        return false; // El producto no está en el catálogo o la tienda no fue encontrada
    }
    /**
     * Obtiene el catálogo de productos de una tienda específica en forma de cadena de texto.
     *
     * @param shopName El nombre de la tienda cuyo catálogo se desea obtener.
     * @return Una cadena de texto que representa el catálogo de la tienda, o null si la tienda no se encontró.
     * @throws IOException Si hay un error de entrada/salida al operar con el repositorio de tiendas.
     */
    public String getCatalogueFromShop(String shopName) throws IOException {
        Shop shop = findByName(shopName);
        if(shop != null)
        {
            StringBuilder message = new StringBuilder();
            List<CatalogProduct> catalogue = shop.getCatalogProductList();
            if (catalogue.isEmpty()) {
                return "\nThe catalogue for shop " + shopName + " is empty.";
            }
            for (int i = 0; i < catalogue.size(); i++) {
                CatalogProduct product = catalogue.get(i);
                message.append(i + 1).append(". ").append(product.getNameProduct()).append(" by ").append(product.getNameBrand()).append(" priced at: ").append(product.getPrice()).append("\n");
            }
            return message.toString();
        }
        return null;
    }
    /**
     * Elimina un producto específico del catálogo de una tienda basándose en su índice en el catálogo.
     *
     * @param shopName    El nombre de la tienda de la que se eliminará el producto del catálogo.
     * @param productIndex El índice del producto en el catálogo que se desea eliminar (empezando en 1).
     * @return true si se elimina el producto, false si el índice no es válido o el producto no se encuentra.
     * @throws IOException Si hay un error de entrada/salida al operar con el repositorio de tiendas.
     */
    public boolean reduceCatalogue(String shopName, int productIndex) throws IOException {
        Shop shop = findByName(shopName);
        List<CatalogProduct> catalogue = shop.getCatalogProductList();
        if(productIndex != 0)
        {
            CatalogProduct selectedProduct = catalogue.get(productIndex - 1);
            String catalogueProductName = selectedProduct.getNameProduct();
            deleteProductFromShop(catalogueProductName,shopName);
            return true;
        }
        return false;
    }
    /**
     * Devuelve una cadena que enumera las tiendas disponibles junto con sus números correspondientes.
     *
     * @return Una cadena con la lista numerada de tiendas disponibles.
     * @throws IOException Si hay un error de entrada/salida al obtener la lista de tiendas.
     */
    public String listShops() throws IOException {
        String shopsString = "";
        List<Shop> shops = shopDAO.getAllShops();
        int i = 0;
        for (Shop shop: shops) {
            shopsString = shopsString + (i + 1) + ") " + shop.getName() + "\n";
            i++;
        }
        return shopsString;
    }
    /**
     * Obtiene la información detallada de una tienda específica según su índice.
     *
     * @param shopIndex El índice de la tienda que se desea obtener.
     * @return Una cadena que contiene la información detallada de la tienda, incluidos los productos en su catálogo.
     * @throws IOException Si hay un error de entrada/salida al obtener la información de la tienda.
     */
    public String getShopInfo(int shopIndex) throws IOException {
        String infoShop;
        List<Shop> shops = shopDAO.getAllShops();
        Shop selectedShop = shops.get(shopIndex - 1);
        infoShop = "\nelCofre" + selectedShop.getName() + " Shop - Since " + selectedShop.getSince() + "\n" + selectedShop.getDescription() + ".";
        infoShop = infoShop + "\n\nProducts:\n" + getCatalogueFromShop(selectedShop.getName());
        return infoShop;
    }
    /**
     * Busca un producto específico en el catálogo de todas las tiendas y devuelve la información de las tiendas que lo venden.
     *
     * @param productInfo Un arreglo de cadenas que contiene la información del producto a buscar, donde la segunda posición es el nombre del producto.
     * @return Una matriz bidimensional de cadenas que contiene la información de las tiendas que venden el producto buscado, incluido el nombre de la tienda y el precio del producto.
     * @throws IOException Si hay un error de entrada/salida al buscar el producto en los catálogos de las tiendas.
     */
    public String[][] getCatalogueSearch(String[] productInfo) throws IOException {
        List<Shop> shopsList = shopDAO.getAllShops();
        List<String[]> shopInfoForProduct = new ArrayList<>();

        // Obtener el nombre del producto de la segunda posición del arreglo
        String productName = productInfo[1].replaceAll("\"", ""); // Eliminar comillas

        for (Shop shop : shopsList) {
            for (CatalogProduct catalogProduct : shop.getCatalogProductList()) {
                if (catalogProduct.getNameProduct().equals(productName)) {
                    // Formatear la información de la tienda y el precio
                    String[] shopAndPrice = new String[3];
                    shopAndPrice[0] = "\t\t-";
                    shopAndPrice[1] = shop.getName() + ":";
                    shopAndPrice[2] = String.valueOf(catalogProduct.getPrice());
                    shopInfoForProduct.add(shopAndPrice);
                }
            }
        }

        // Convertir la lista a una matriz String[][]
        String[][] result = new String[shopInfoForProduct.size()][];
        for (int i = 0; i < shopInfoForProduct.size(); i++) {
            result[i] = shopInfoForProduct.get(i);
        }

        return result;
    }
    public CatalogProduct getCatalogueProductFromIndex(int shopIndex, int productIndex) throws IOException {
        CatalogProduct catalogProduct;
        List<Shop> shops = shopDAO.getAllShops();
        Shop selectedShop = shops.get(shopIndex - 1);
        List<CatalogProduct> catalogProductList = selectedShop.getCatalogProductList();
        catalogProduct = catalogProductList.get(productIndex - 1);
        return catalogProduct;
    }
    public String[] getCatalogueProductInfo(int shopIndex, int productIndex) throws IOException {
        CatalogProduct catalogProduct = getCatalogueProductFromIndex(shopIndex, productIndex);
        String[] infoProduct = new String[2];
        infoProduct[0] = catalogProduct.getNameProduct();
        infoProduct[1] = catalogProduct.getNameBrand();
        return infoProduct;
    }
    public String setNewIncomes(List<CatalogProduct> infoCheckout) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        String infoTicket = "";
        boolean ok = false;

        for(Shop shop: shops)
        {
            for(CatalogProduct catalogProduct: infoCheckout)
            {
                //Nom de la botiga match
                if(catalogProduct.getNameShop().equals(shop.getName()))
                {
                    shop.setEarnings((shop.getEarnings() + catalogProduct.getPrice()));
                    infoTicket = infoTicket + "\n" + catalogProduct.getNameShop() + " has earned " + catalogProduct.getPrice() + ", for an historic total of " + shop.getEarnings() + ".";
                    ok = true;
                }
            }
            shopDAO.deleteShop(0);
        }

        // Guardar la lista actualizada de tiendas en el archivo
        for (Shop shop : shops) {
            shopDAO.createShop(shop);
        }

        if(!ok)
        {
            infoTicket = "\nERROR, NO PRODUCTS TO CHECKOUT!";
        }
        return infoTicket;
    }
}
