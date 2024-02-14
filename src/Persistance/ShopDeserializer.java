package Persistance;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Shop.Shop;
import Bussines.Entities.Shop.LoyaltyShop;
import Bussines.Entities.Shop.SponsoredShop;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ShopDeserializer implements JsonDeserializer<Shop> {
    @Override
    public Shop deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Identificar el modelo de negocio
        String businessModel = jsonObject.has("businessModel") ? jsonObject.get("businessModel").getAsString() : "";

        // Crear la instancia de Shop correspondiente
        Shop shop;
        switch (businessModel) {
            case "LOYALTY":
                shop = new LoyaltyShop(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("description").getAsString(),
                        jsonObject.get("since").getAsInt(),
                        jsonObject.get("earnings").getAsDouble(),
                        businessModel,
                        jsonObject.has("loyaltyThreshold") ? jsonObject.get("loyaltyThreshold").getAsDouble() : 0.0
                );
                break;
            case "SPONSORED":
                shop = new SponsoredShop(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("description").getAsString(),
                        jsonObject.get("since").getAsInt(),
                        jsonObject.get("earnings").getAsDouble(),
                        businessModel,
                        jsonObject.has("sponsorBrand") ? jsonObject.get("sponsorBrand").getAsString() : ""
                );
                break;
            default:
                shop = new MaximumBenefitsShop(
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("description").getAsString(),
                        jsonObject.get("since").getAsInt(),
                        jsonObject.get("earnings").getAsDouble(),
                        businessModel
                );
                break;
        }

        // Deserializar el catálogo de productos, si tiene algún producto dentro
        if (jsonObject.has("catalogue") && jsonObject.get("catalogue").isJsonArray()) {
            JsonArray catalogueArray = jsonObject.getAsJsonArray("catalogue");
            for (JsonElement productElement : catalogueArray) {
                JsonObject productObject = productElement.getAsJsonObject();

                String nameProduct = productObject.has("nameProduct") ? productObject.get("nameProduct").getAsString() : "";
                String nameBrand = productObject.has("nameBrand") ? productObject.get("nameBrand").getAsString() : "";
                String nameShop = productObject.has("nameShop") ? productObject.get("nameShop").getAsString() : "";
                double price = productObject.has("price") ? productObject.get("price").getAsDouble() : 0.0;

                // Crear instancia de CatalogProduct
                CatalogProduct catalogProduct = new CatalogProduct(nameProduct, nameBrand, nameShop, price);

                // Añadir el producto al catálogo de la tienda
                shop.getCatalogProductList().add(catalogProduct);
            }
        }

        return shop;
    }
}
