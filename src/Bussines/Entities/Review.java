package Bussines.Entities;

/**
 * Representa una reseña de un producto, incluyendo la calificación y el texto de la reseña.
 */
public class Review {
    private int classificationStars;
    private String text;

    /**
     * Constructor para crear una reseña.
     *
     * @param classificationStars Calificación en estrellas de la reseña.
     * @param text                Texto de la reseña.
     */
    public Review (int classificationStars, String text) {
        this.classificationStars = classificationStars;
        this.text = text;
    }
    /**
     * Obtiene la calificación en estrellas de la reseña.
     *
     * @return Calificación en estrellas.
     */
    public int getClassificationStars() {
        return this.classificationStars;
    }
    /**
     * Obtiene el texto de la reseña.
     *
     * @return Texto de la reseña.
     */
    public String getText() {return this.text;}
}
