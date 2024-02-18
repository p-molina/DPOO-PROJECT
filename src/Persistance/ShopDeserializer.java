package Persistance;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Shop.MaximumBenefitsShop;
import Bussines.Entities.Shop.Shop;
import Bussines.Entities.Shop.LoyaltyShop;
import Bussines.Entities.Shop.SponsoredShop;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Deserializador personalizado para convertir representaciones JSON en instancias de la clase {@link Shop}
 * y sus subclases ({@link LoyaltyShop}, {@link SponsoredShop}, {@link MaximumBenefitsShop}). Este deserializador
 * toma en cuenta el modelo de negocio especificado en el JSON para determinar el tipo específico de tienda a crear.
 * También maneja la deserialización del catálogo de productos asociado a cada tienda.
 */
public class ShopDeserializer implements JsonDeserializer<Shop> {
    /**
     * Deserializa un elemento JSON en una instancia concreta de {@link Shop}.
     * Utiliza la propiedad 'businessModel' del JSON para determinar el tipo específico de tienda a deserializar.
     * También deserializa y añade productos al catálogo de la tienda si están presentes.
     *
     * @param json El elemento JSON a deserializar.
     * @param typeOfT El tipo específico en el que se debe deserializar el JSON.
     * @param context Contexto de deserialización que puede ser utilizado para deserializar elementos anidados.
     * @return Una instancia de {@link Shop}, específicamente de {@link LoyaltyShop}, {@link SponsoredShop}, o
     *         {@link MaximumBenefitsShop} dependiendo del modelo de negocio especificado en el JSON.
     * @throws JsonParseException Si el proceso de deserialización falla debido a un formato inesperado del objeto JSON.
     */
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
        if (jsonObject.has("catalogProductList") && jsonObject.get("catalogProductList").isJsonArray()) {
            JsonArray catalogueArray = jsonObject.getAsJsonArray("catalogProductList");
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
