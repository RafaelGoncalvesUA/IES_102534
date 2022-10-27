package pt.ua.ies.TVQuotesPersistentAPI.POJOs;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String quote;
    private String role;
    @ManyToOne(cascade = CascadeType.ALL)
    private Show show;
    private boolean contain_adult_lang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public String getrole() {
        return role;
    }
    public void setrole(String role) {
        this.role = role;
    }
    public Show getShow() {
        return show;
    }
    public void setShow(Show show) {
        this.show = show;
    }
    public boolean getContain_adult_lang() {
        return contain_adult_lang;
    }
    public void setContain_adult_lang(boolean contain_adult_lang) {
        this.contain_adult_lang = contain_adult_lang;
    }
}
