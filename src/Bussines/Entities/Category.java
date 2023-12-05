package Bussines.Entities;

public class Category {
    private String category;
    private static final String GENERAL = "GENERAL";
    private static final String REDUCED = "REDUCED";
    private static final String SUPER_REDUCED = "SUPER_REDUCED";

    public Category (String category) {}

    public static Category valueOf(String category) {
        switch(category) {
            case GENERAL:
                return new Category("General");
            case REDUCED:
                return new Category("Reduced Taxes");
            case SUPER_REDUCED:
                return new Category("Superreduced Taxes");
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
    }

    public String getCategory() {
        return this.category;
    }
}
