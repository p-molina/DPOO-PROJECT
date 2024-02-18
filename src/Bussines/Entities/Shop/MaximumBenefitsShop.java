package Bussines.Entities.Shop;

/**
 * Representa una tienda con el modelo de negocio de "Máximos Beneficios".
 * Este tipo de tienda se enfoca en maximizar los ingresos y beneficios a través de su operación.
 * Hereda de la clase {@link Shop} y se especializa en el modelo de negocio especificado en su construcción.
 */
public class MaximumBenefitsShop extends Shop{

    /**
     * Constructor para crear una tienda con el modelo de negocio de máximos beneficios.
     *
     * @param name          Nombre de la tienda.
     * @param description   Descripción de la tienda, ofreciendo detalles sobre lo que la hace única.
     * @param since         Año de apertura de la tienda, indicando cuánto tiempo ha estado en operación.
     * @param earnings      Ingresos de la tienda, representando el total de beneficios generados.
     * @param businessModel Modelo de negocio de la tienda, que para esta clase siempre se enfoca en maximizar beneficios.
     */
    public MaximumBenefitsShop(String name, String description, int since, double earnings, String businessModel) {
        super(name, description, since, earnings, businessModel);
    }
}
