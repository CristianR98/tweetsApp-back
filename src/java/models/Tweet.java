package models;

public class Tweet {
    
    private long id;
    private String texto;
    private String fecha;
    private int cantidadFavoritos;
    private int cantidadRetweet;
    private String url;
    private UsuarioTwitter user;
    
    public Tweet () {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public UsuarioTwitter getUser() {
        return user;
    }

    public void setUser(UsuarioTwitter user) {
        this.user = user;
    }

    public int getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(int cantidadFavoritos) {
        this.cantidadFavoritos = cantidadFavoritos;
    }

    public int getCantidadRetweet() {
        return cantidadRetweet;
    }

    public void setCantidadRetweet(int cantidadRetweet) {
        this.cantidadRetweet = cantidadRetweet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
   
    
    
}
