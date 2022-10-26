package pt.ua.ies.TVQuotesPersistentAPI.POJOs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_show")
public class Show {
    @Id
    private String slug;
    private String name;
    private int year;
    private double rating;

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
}
