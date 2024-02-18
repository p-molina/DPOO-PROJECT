package Bussines.Entities.Shop;

/**
 * Representa una tienda con un modelo de negocio basado en la fidelización de clientes.
 * Este tipo de tienda incentiva la lealtad del cliente ofreciendo beneficios una vez que se supera un umbral de fidelidad.
 * Hereda de la clase {@link Shop} y añade un umbral de lealtad como característica distintiva.
 */
public class LoyaltyShop extends Shop{
    private double loyaltyThreshold;

    /**
     * Constructor para crear una tienda con un modelo de fidelización.
     *
     * @param name          Nombre de la tienda.
     * @param description   Descripción de la tienda, que puede incluir detalles sobre programas de lealtad u ofertas especiales.
     * @param since         Año de apertura de la tienda, proporcionando un contexto histórico.
     * @param earnings      Ingresos de la tienda, reflejando su desempeño financiero.
     * @param businessModel Modelo de negocio de la tienda, específicamente enfocado en la fidelización de clientes en este contexto.
     * @param loyaltyThreshold Umbral de lealtad necesario para que los clientes accedan a beneficios especiales.
     */
    public LoyaltyShop(String name, String description, int since, double earnings, String businessModel, double loyaltyThreshold) {
        super(name, description, since, earnings, businessModel);
        this.loyaltyThreshold = loyaltyThreshold;
    }

    /**
     * Obtiene el umbral de lealtad de la tienda.
     *
     * @return El umbral de lealtad que los clientes deben superar para calificar para beneficios de lealtad.
     */
    public double getLoyaltyThreshold() {
        return loyaltyThreshold;
    }
}
