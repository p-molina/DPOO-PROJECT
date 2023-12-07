package Bussines;

import Bussines.Entities.Category;
import Bussines.Entities.Product;
import Persistance.ProductDAO;

import java.io.FileNotFoundException;
import java.io.IOException;

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

        productDAO.addProductToFile(product);
    }

    public boolean checkName(String name) throws IOException {
        boolean found = false;

        for (Product product: productDAO.getAll()) {
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
}
