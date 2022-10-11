package pt.ua.ies.TVQuotesAPI.POJO;

public class QuoteObject {
    private String quote;
    private String role;
    private String show;
    private boolean contain_adult_lang;

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
    public QuoteObject(String quote, String role, String show, boolean contain_adult_lang) {
        this.quote = quote;
        this.role = role;
        this.show = show;
        this.contain_adult_lang = contain_adult_lang;
    }
}
