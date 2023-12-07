package Bussines.Entities;

public class Review {
    private int classificationStars;
    private String text;

    public Review (int classificationStars, String text) {
        this.classificationStars = classificationStars;
        this.text = text;
    }

    public int getClassificationStars() {
        return this.classificationStars;
    }
}
