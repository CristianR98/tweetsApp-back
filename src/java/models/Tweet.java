package models;

public class Tweet {
    
    private long id;
    private String texto;
    private String fecha;
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
    
   
    
    
}
