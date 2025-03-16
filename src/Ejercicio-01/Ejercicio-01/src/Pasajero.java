public abstract class Pasajero {
    private String id;
    private String nombre;

    public Pasajero(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String getCategoria();

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + getCategoria() + ")";
    }
}
