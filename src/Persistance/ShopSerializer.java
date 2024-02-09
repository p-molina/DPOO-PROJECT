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

public class ShopSerializer implements JsonSerializer<Shop> {
    @Override
    public JsonElement serialize(Shop src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

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

        // Serializa los productos del catálogo
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
