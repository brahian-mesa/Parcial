public class Economica extends Pasajero {

    public Economica(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String getCategoria() {
        return "Económica";
    }
}
