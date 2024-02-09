package Bussines.Entities.Shop;

public class LoyaltyShop extends Shop{
    private double loyaltyTreshold;
    /**
     * Constructor hijo para crear una tienda con un modelo de fidelización.
     *
     * @param name          Nombre de la tienda.
     * @param description   Descripción de la tienda.
     * @param since         Año de apertura de la tienda.
     * @param earnings      Ingresos de la tienda.
     * @param businessModel Modelo de negocio de la tienda.
     */
    public LoyaltyShop(String name, String description, int since, double earnings, String businessModel, double loyaltyTreshold) {
        super(name, description, since, earnings, businessModel);
        this.loyaltyTreshold = loyaltyTreshold;
    }

    public double getLoyaltyThreshold() {
        return loyaltyTreshold;
    }
}
