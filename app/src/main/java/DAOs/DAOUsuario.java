package DAOs;

import java.util.ArrayList;
import java.util.List;

import POJOs.Usuario;

public class DAOUsuario {

    private List<Usuario> listaUsuarios;
    private static DAOUsuario instancia = null;

    private DAOUsuario()
    {
        listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("javi", "1234", "jaime@jaime.com"));
        listaUsuarios.add(new Usuario("usuario1", "contrasena123", "usuario1@example.com"));
        listaUsuarios.add(new Usuario("usuario2", "clave456", "usuario2@example.com"));
        listaUsuarios.add(new Usuario("usuario3", "pass789", "usuario3@example.com"));
        listaUsuarios.add(new Usuario("usuario4", "securepass", "usuario4@example.com"));
        listaUsuarios.add(new Usuario("usuario5", "mypassword", "usuario5@example.com"));
    }

    public Boolean comprobarUsuarioContrasena(String nombreUsuario, String contrasena)
    {
        for (Usuario usuario : listaUsuarios)
        {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena))
            {
                return true;
            }
        }

        return false;
    }

    public Boolean registrarUsuario(String nombreUsuario, String contrasena, String correo)
    {
        for (Usuario usuario : listaUsuarios)
        {
            if (usuario.getNombreUsuario().equals(nombreUsuario))
            {
                return false;
            }
        }
        listaUsuarios.add(new Usuario(nombreUsuario, contrasena, correo));
        return true;

    }

    // Singleton
    public static DAOUsuario getInstancia()
    {
        if (instancia == null)
        {
            instancia = new DAOUsuario();
        }
        return instancia;
    }

}
