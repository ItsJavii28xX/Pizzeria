package POJOs;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String correo;

    public Usuario()
    {
        this.nombreUsuario = "";
        this.contrasena = "";
        this.correo = "";
    }

    public Usuario(String nombreUsuario, String contrasena, String correo)
    {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
