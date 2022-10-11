package pt.ua.ies.TVQuotesAPI.POJO;

public class ShowObject {
    private String name;
    private String slug;

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
    public ShowObject(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
}
