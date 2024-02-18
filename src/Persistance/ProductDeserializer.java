package Persistance;


import Bussines.Entities.Products.Product;
import Bussines.Entities.Products.GeneralProduct;
import Bussines.Entities.Products.SuperReducedProduct;
import Bussines.Entities.Products.ReducedProduct;
import Bussines.Entities.Review;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializador personalizado para convertir objetos JSON en instancias de la clase {@link Product}.
 * Este deserializador maneja la creación de diferentes tipos de productos ({@link GeneralProduct},
 * {@link ReducedProduct}, {@link SuperReducedProduct}) basándose en la categoría del producto especificada
 * en el objeto JSON. También maneja la deserialización de las reseñas asociadas a cada producto.
 */
public class ProductDeserializer implements JsonDeserializer<Product> {
    /**
     * Deserializa un elemento JSON en una instancia concreta de {@link Product}.
     *
     * @param json El elemento JSON a deserializar.
     * @param typeOfT El tipo específico en el que se debe deserializar el JSON.
     * @param context Contexto de deserialización que puede ser utilizado para deserializar elementos anidados.
     * @return Una instancia de {@link Product}, específicamente de {@link GeneralProduct},
     *         {@link ReducedProduct}, o {@link SuperReducedProduct} dependiendo de la categoría del producto.
     * @throws JsonParseException Si el proceso de deserialización falla debido a una categoría desconocida o
     *                            a un formato inesperado del objeto JSON.
     */
    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String name = jsonObject.has("name") ? jsonObject.get("name").getAsString() : null;
        String brand = jsonObject.has("brand") ? jsonObject.get("brand").getAsString() : null;
        double mrp = jsonObject.has("mrp") ? jsonObject.get("mrp").getAsDouble() : 0.0;
        String category = jsonObject.has("category") ? jsonObject.get("category").getAsString() : null;
        // Assuming 'reviews' is an array in JSON, otherwise adjust accordingly
        JsonArray reviewsArray = jsonObject.has("reviews") ? jsonObject.getAsJsonArray("reviews") : null;
        List<Review> reviews = new ArrayList<>();
        if (reviewsArray != null) {
            for (JsonElement element : reviewsArray) {
                Review review = context.deserialize(element, Review.class);
                reviews.add(review);
            }
        }

        switch (category) {
            case "GENERAL":
                return new GeneralProduct(name, brand, mrp, category, reviews);
            case "REDUCED":
                return new ReducedProduct(name,  brand, mrp, category, reviews);
            case "SUPER_REDUCED":
                return new SuperReducedProduct(name, brand, mrp, category, reviews);
            default:
                throw new JsonParseException("ERROR: unknown product category: " + category);
        }
    }
}
