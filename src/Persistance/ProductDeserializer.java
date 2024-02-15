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

public class ProductDeserializer implements JsonDeserializer<Product> {
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
