package pt.ua.ies.TVQuotesPersistentAPI.POJOs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String quote;
    private String role;
    @Column(name = "show_name")
    private String show;
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
    public String getShow() {
        return show;
    }
    public void setShow(String show) {
        this.show = show;
    }
    public boolean getContain_adult_lang() {
        return contain_adult_lang;
    }
    public void setContain_adult_lang(boolean contain_adult_lang) {
        this.contain_adult_lang = contain_adult_lang;
    }
}
