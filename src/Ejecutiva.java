public class Ejecutiva extends Pasajero {

    public Ejecutiva(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String getCategoria() {
        return "Ejecutiva";
    }
}
