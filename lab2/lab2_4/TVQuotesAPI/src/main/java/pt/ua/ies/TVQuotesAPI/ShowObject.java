package pt.ua.ies.TVQuotesAPI;

public class ShowObject {
    private String name;
    private String slug;
    private int id;
    private static int counter = 0;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public int getId() {
        return id;
    }

    public ShowObject(String name, String slug) {
        this.name = name;
        this.slug = slug;
        this.id = counter;
        counter++;
    }
}
