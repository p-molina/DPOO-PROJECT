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

    public ProductManager(ProductDAO productDAO) {
        this.productDAO = productDAO;
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
    }
    public String[][] searchProduct(String query) throws IOException {
        List<Product> productsAux = productDAO.getAllProducts();
        List<String[]> productsFound = new ArrayList<>();

        int count = 1;
        for (Product product : productsAux) {
            // Comprobar si el nombre del producto contiene el texto introducido, ignorando mayúsculas y minúsculas
            if (product.getName().toLowerCase().contains(query.toLowerCase())
                    || product.getBrand().equals(query)) {
                String[] productInfo = new String[4];
                productInfo[0] = count + ")";
                productInfo[1] = "\"" + product.getName() + "\"";
                productInfo[2] = "by";
                productInfo[3] = "\"" + product.getBrand() + "\"";
                productsFound.add(productInfo);
                count++;
            }
        }

        // Convertir la lista de arreglos de cadenas a una matriz de cadenas
        String[][] result = new String[productsFound.size()][];
        for (int i = 0; i < productsFound.size(); i++) {
            result[i] = productsFound.get(i);
        }

        return result;
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
    private double getAverageRating(List<Review> reviews) {
        if (reviews == null || reviews.size() == 0) {
            return -1;
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getClassificationStars();
        }

        return sum / reviews.size();
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

    public String[] getReviews(String productName) throws IOException {
        List<Product> products = productDAO.getAllProducts();
        String[] reviewsArray = new String[0];

        for (Product product : products) {
            if (productName.equals(product.getName())) {
                List<Review> reviews = product.getReviews(); // Suponiendo que getReviews ahora devuelve una List<Review>

                // Inicializar el array de revisiones con la longitud de la lista de revisiones
                reviewsArray = new String[reviews.size()];

                for (int i = 0; i < reviews.size(); i++) {
                    Review review = reviews.get(i); // Usar get(i) para acceder a los elementos de la lista
                    reviewsArray[i] = review.getClassificationStars() + "* " + review.getText();
                }
                break;
            }
        }
        return reviewsArray;
    }

    public void addReview(String productName, int rating, String comment) throws IOException {
        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {
            if (productName.equals(product.getName())) {
                List<Review> reviews = product.getReviews();
                reviews.add(new Review(rating, comment));
                break;
            }
        }

        productDAO.saveAllProduct(products);
    }


}
