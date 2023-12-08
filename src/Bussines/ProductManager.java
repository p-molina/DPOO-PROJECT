package Bussines;

import Bussines.Entities.Product;
import Bussines.Entities.Review;
import Persistance.ProductDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class  ProductManager {
    private ProductDAO productDAO;
    private ShopManager shopManager;

    public ProductManager(ProductDAO productDAO, ShopManager shopManager) {
        this.productDAO = productDAO;
        this.shopManager = shopManager;
    }

    public void checkProductFile() throws FileNotFoundException {
        productDAO.checkFile();
    }

    public void createProduct(String name, String brand, double mrp, String category) throws IOException {
        Product product = new Product(name, brand, mrp, category);
        List<Product> products = productDAO.getAllProducts();
        products.add(product);

        productDAO.saveAllProduct(products);
    }

    public void deleteProduct(String name) throws IOException {
        List<Product> products = productDAO.getAllProducts();
        List<Product> toRemove = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().equals(name)) {
                toRemove.add(product);
            }
        }

        products.removeAll(toRemove);
        productDAO.saveAllProduct(products);

        //Eliminamos el prducto de todas las tiendas en las que se encuentre
        shopManager.deleteProductFromShops(name);
    }

    public boolean checkName(String name) throws IOException {
        boolean found = false;

        for (Product product: productDAO.getAllProducts()) {
            if (product.getName().equals(name)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public String toTitleCase(String input) {
        String[] words = input.toLowerCase().split("\\s+"); //Indicamos que divida el nombre por espacios, \t o \n con "\\s" y que se puede repetir con el "+"
        StringBuilder titleCase = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) { //Si el array esta vacio no hay mas palabras que cambiar
                titleCase.append(Character.toUpperCase(word.charAt(0))); //En la primera posicion de la palabra se pone en mayuscula
                titleCase.append(word.substring(1)).append(" "); //Añadimos espacio entre palabras
            }
        }

        return titleCase.toString().trim(); //Lo juntamos enuna string de nuevo
    }

    public HashMap<Product, Double> getProductsRatingMap() throws IOException{
        HashMap<Product, Double> productRatingMap = new HashMap<>();

        for (Product product : productDAO.getAllProducts()) {
            double avgRating = getAverageRating(product.getReviews());
            productRatingMap.put(product, avgRating);
        }

        return productRatingMap;
    }

    private double getAverageRating(Review[] reviews) {
        if (reviews == null || reviews.length == 0) {
            return -1;
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getClassificationStars();
        }

        return sum / reviews.length;
    }
    public double getMRPFromProduct(String name) throws IOException {
        double mrp = -1;

        for (Product product : productDAO.getAllProducts()) {
            if (product.getName().equals(name)) {
                mrp = product.getMrp();
            }
        }

        return mrp;
    }
    public  String getBrandFromProduct(String nameProduct) throws IOException {
        String productBrand = null;
        for (Product product : productDAO.getAllProducts()) {
            if (product.getName().equals(nameProduct)) {
                productBrand = product.getBrand();            }
        }
        return productBrand;
    }
}
