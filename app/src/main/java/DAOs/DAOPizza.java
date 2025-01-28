package DAOs;

import java.util.ArrayList;
import java.util.List;

import POJOs.TipoIngredientes;

public class DAOPizza {

    private static DAOPizza instancia = null;
    private List<TipoIngredientes> listaIngredientes;

    private DAOPizza()
    {
        listaIngredientes = new ArrayList<>();
    }

    public DAOPizza getInstancia()
    {
        if (instancia == null)
        {
            instancia = new DAOPizza();
        }
        return instancia;
    }

    public List<TipoIngredientes> getListaIngredientes()
    {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<TipoIngredientes> listaIngredientes)
    {
        this.listaIngredientes = listaIngredientes;
    }

    public void anadirIngrediente(TipoIngredientes ingrediente)
    {
        listaIngredientes.add(ingrediente);
    }
}
