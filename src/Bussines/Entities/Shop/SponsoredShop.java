package Bussines.Entities.Shop;

public class SponsoredShop extends Shop{
    private String sponsorBrand;
    /**
     * Constructor para crear una tienda.
     *
     * @param name          Nombre de la tienda.
     * @param description   Descripción de la tienda.
     * @param since         Año de apertura de la tienda.
     * @param earnings      Ingresos de la tienda.
     * @param businessModel Modelo de negocio de la tienda.
     */
    public SponsoredShop(String name, String description, int since, double earnings, String businessModel, String sponsorBrand) {
        super(name, description, since, earnings, businessModel);
        this.sponsorBrand = sponsorBrand;
    }

    public String getSponsorBrand() {
        return sponsorBrand;
    }
}
