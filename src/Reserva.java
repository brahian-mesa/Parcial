public class Reserva {
    private String id;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private String numeroAsiento;
    
    public Reserva(String id, Vuelo vuelo, Pasajero pasajero, String numeroAsiento) {
        this.id = id;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.numeroAsiento = numeroAsiento;
    }
    
    public String getId() {
        return id;
    }
    
    public Vuelo getVuelo() {
        return vuelo;
    }
    
    public Pasajero getPasajero() {
        return pasajero;
    }
    
    public String getNumeroAsiento() {
        return numeroAsiento;
    }
    
    public boolean cambiarAsiento(String nuevoAsiento) {
        // Verificar si el asiento está disponible
        if (vuelo.asignarAsiento(pasajero, nuevoAsiento)) {
            // Cancelar el asiento anterior
            vuelo.cancelarAsiento(numeroAsiento);
            // Actualizar el número de asiento
            this.numeroAsiento = nuevoAsiento;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return id + " - " + pasajero.getNombre() + " - Vuelo: " + vuelo.getNumeroVuelo() + 
               " - Asiento: " + numeroAsiento;
    }
}
