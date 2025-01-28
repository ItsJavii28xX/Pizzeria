package POJOs;

public enum TipoTamanoPizzas {
    FAMILIAR("Familiar", 20.0),
    GRANDE("Grande", 15.0),
    MEDIANA("Mediana", 10.0),
    PEQUENA("Pequeña", 5.0);

    private final String nombre;
    private final double precio;

    TipoTamanoPizzas(String nombre, double precio)
    {
        this.nombre = nombre;
        this.precio = precio;
    }
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}
}
