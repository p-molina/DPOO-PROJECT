package Persistance;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Shop.Shop;
import Bussines.Entities.Shop.LoyaltyShop;
import Bussines.Entities.Shop.SponsoredShop;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 * Serializador personalizado para convertir instancias de la clase {@link Shop} y sus subclases
 * en representaciones JSON. Este serializador maneja la conversión de diferentes tipos de tiendas,
 * incluyendo {@link LoyaltyShop} y {@link SponsoredShop}, agregando propiedades específicas según el tipo
 * de tienda. También se encarga de serializar la lista de productos de cada tienda en el catálogo.
 */
public class ShopSerializer implements JsonSerializer<Shop> {
    /**
     * Serializa una instancia de {@link Shop} en su representación JSON correspondiente.
     * Incluye información general de la tienda como nombre, descripción, ingresos y desde cuándo existe.
     * Además, identifica el tipo de modelo de negocio de la tienda y serializa su catálogo de productos.
     *
     * @param src La tienda que se va a serializar.
     * @param typeOfSrc El tipo específico de la fuente de la serialización.
     * @param context Contexto de serialización que puede ser utilizado para serializar elementos anidados.
     * @return Un {@link JsonElement} que representa la forma serializada de la {@link Shop}.
     */
    @Override
    public JsonElement serialize(Shop src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Propiedades generales de todas las tiendas
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("since", src.getSince());
        jsonObject.addProperty("earnings", src.getEarnings());

        if (src instanceof LoyaltyShop) {
            LoyaltyShop loyaltyShop = (LoyaltyShop) src;
            jsonObject.addProperty("businessModel", "LOYALTY");
            jsonObject.addProperty("loyaltyThreshold", loyaltyShop.getLoyaltyThreshold());
        } else if (src instanceof SponsoredShop) {
            SponsoredShop sponsoredShop = (SponsoredShop) src;
            jsonObject.addProperty("businessModel", "SPONSORED");
            jsonObject.addProperty("sponsorBrand", sponsoredShop.getSponsorBrand());
        } else {
            jsonObject.addProperty("businessModel", "MAXIMUM_BENEFITS");
        }

        // Serialización del catálogo de productos de la tienda
        JsonArray catalogArray = new JsonArray();
        for (CatalogProduct product : src.getCatalogProductList()) {
            JsonObject productJson = new JsonObject();
            productJson.addProperty("nameProduct", product.getNameProduct());
            productJson.addProperty("nameBrand", product.getNameBrand());
            productJson.addProperty("nameShop", product.getNameShop());
            productJson.addProperty("price", product.getPrice());
            catalogArray.add(productJson);
        }
        jsonObject.add("catalogue", catalogArray);

        return jsonObject;
    }

}
