package Bussines.Entities.Shop;

/**
 * Clase que representa una tienda patrocinada, que es un tipo específico de {@link Shop}.
 * Las tiendas patrocinadas tienen una marca patrocinadora asociada a ellas, además de las
 * propiedades heredadas de la clase {@link Shop}.
 */
public class SponsoredShop extends Shop{
    private String sponsorBrand;

    /**
     * Constructor para crear una tienda patrocinada.
     *
     * @param name          Nombre de la tienda.
     * @param description   Descripción de la tienda.
     * @param since         Año de apertura de la tienda.
     * @param earnings      Ingresos de la tienda.
     * @param businessModel Modelo de negocio de la tienda.
     * @param sponsorBrand  Marca patrocinadora asociada a la tienda.
     */
    public SponsoredShop(String name, String description, int since, double earnings, String businessModel, String sponsorBrand) {
        super(name, description, since, earnings, businessModel);
        this.sponsorBrand = sponsorBrand;
    }

    /**
     * Obtiene la marca patrocinadora de la tienda.
     *
     * @return La marca patrocinadora asociada a esta tienda patrocinada.
     */
    public String getSponsorBrand() {
        return sponsorBrand;
    }
}
