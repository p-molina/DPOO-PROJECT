package Bussines;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Product;
import Bussines.Entities.Products.GeneralProduct;
import Bussines.Entities.Products.ReducedProduct;
import Bussines.Entities.Products.SuperReducedProduct;
import Bussines.Entities.Review;
import Persistance.DAO.ProductDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Gestor de productos que maneja las operaciones relacionadas con productos.
 */
public class  ProductManager {
    private ProductDAO productDAO;

    /**
     * Constructor para inicializar el gestor de productos.
     *
     * @param productDAO DAO para el acceso a datos de productos.
     */
    public ProductManager(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    /**
     * Verifica la existencia del archivo de productos.
     *
     * @throws FileNotFoundException Si el archivo no se encuentra.
     */
    public void checkProductFile() throws FileNotFoundException {
        productDAO.checkFile();
    }
    /**
     * Crea un nuevo producto y lo guarda en el archivo.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público.
     * @param category Categoría del producto.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void createProduct(String name, String brand, double mrp, String category) throws IOException {
        switch (category)
        {
            case "GENERAL":
                productDAO.createProduct(new GeneralProduct(name, brand, mrp, category));
                break;
            case "REDUCED":
                productDAO.createProduct(new ReducedProduct(name, brand, mrp, category));
                break;
            case "SUPER_REDUCED":
                productDAO.createProduct(new SuperReducedProduct(name, brand, mrp, category));
        }
    }
    /**
     * Elimina un producto por su nombre.
     *
     * @param name Nombre del producto a eliminar.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void deleteProduct(String name) throws IOException {
        List<Product> products = productDAO.getAllProducts();
        int position;
        for (position = 0; position < products.size(); position++) {
            //Paramos la ejecucion ya que neccesitamos saber la posición del producto en la lista
            if(products.get(position).getName().equals(name)) {break;}
        }
        productDAO.deleteProduct(position);
    }
    /**
     * Busca productos que coincidan con la consulta dada.
     *
     * @param query Consulta de búsqueda.
     * @return Matriz de String con información de los productos encontrados.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public String[][] searchProduct(String query) throws IOException {
        List<Product> productsAux = productDAO.getAllProducts();
        List<String[]> productsFound = new ArrayList<>();

        int count = 1;
        for (Product product : productsAux) {
            // Comprobar si el nombre del producto contiene el texto introducido, ignorando mayúsculas y minúsculas
            if (product.getName().toLowerCase().contains(query.toLowerCase()) || product.getBrand().equals(query)) {
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
    /**
     * Verifica si un nombre de producto ya existe.
     *
     * @param name Nombre del producto a verificar.
     * @return Verdadero si el nombre ya existe, falso en caso contrario.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
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
    /**
     * Convierte una cadena de texto a formato título.
     *
     * @param input Cadena de texto a convertir.
     * @return Cadena de texto en formato título.
     */
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
    /**
     * Obtiene un mapa de productos con sus calificaciones promedio.
     *
     * @return HashMap de Productos y sus calificaciones promedio.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public HashMap<Product, Double> getProductsRatingMap() throws IOException{
        HashMap<Product, Double> productRatingMap = new HashMap<>();

        for (Product product : productDAO.getAllProducts()) {
            double avgRating = product.getAverageRating();
            productRatingMap.put(product, avgRating);
        }

        return productRatingMap;
    }
    /**
     * Obtiene el precio máximo de venta al público (MRP) de un producto por su nombre.
     *
     * @param name Nombre del producto.
     * @return El MRP del producto o -1 si no se encuentra.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public double getMRPFromProduct(String name) throws IOException {
        double mrp = -1;

        for (Product product : productDAO.getAllProducts()) {
            if (product.getName().equals(name)) {
                mrp = product.getMrp();
            }
        }

        return mrp;
    }
    /**
     * Obtiene la marca de un producto dado su nombre.
     *
     * @param nameProduct Nombre del producto.
     * @return Marca del producto o null si no se encuentra.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public  String getBrandFromProduct(String nameProduct) throws IOException {
        String productBrand = null;
        for (Product product : productDAO.getAllProducts()) {
            if (product.getName().equals(nameProduct)) {
                productBrand = product.getBrand();            }
        }
        return productBrand;
    }
    /**
     * Obtiene las reseñas de un producto por su nombre.
     *
     * @param productName Nombre del producto.
     * @return Array de cadenas con las reseñas del producto.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
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
    /**
     * Agrega una reseña a un producto.
     *
     * @param productName Nombre del producto a reseñar.
     * @param ratingChar      Calificación de la reseña en estrellas.
     * @param comment     Comentario de la reseña.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void addReview(String productName, String ratingChar, String comment) throws IOException {
        List<Product> products = productDAO.getAllProducts();

        int rating = 0;

        for (int i = 0; i < ratingChar.length(); i++) {
            if(ratingChar.charAt(i) == '*') {
                rating++;
            }
        }

        int j = 0;
        for (Product product : products) {
            if (productName.equals(product.getName())) {
                List<Review> reviews = product.getReviews();
                reviews.add(new Review(rating, comment));
                break;
            }
            j++;
        }

        productDAO.deleteProduct(j);
        productDAO.createProduct(products.get(j));
    }
    public List<CatalogProduct> getTaxBasePrice(List<CatalogProduct> infoCheckout) throws IOException {
        List<Product> products = productDAO.getAllProducts();

        for (CatalogProduct catalogProduct: infoCheckout)
        {
            for (Product product: products)
            {
                if(catalogProduct.getNameProduct().equals(product.getName()))
                {
                    catalogProduct.setPrice(product.calculateTaxBasePrice(catalogProduct.getPrice()));
                }
            }
        }
        return infoCheckout;
    }
}
