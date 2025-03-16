import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Aerolinea {
    private String nombre;
    private List<Vuelo> vuelos;
    private List<Reserva> reservas;
    private List<Pasajero> pasajeros;
    
    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.pasajeros = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Vuelo registrarVuelo(String numeroVuelo, String origen, String destino, 
                               Date fecha, int capacidadEconomica, int capacidadEjecutiva) {
        Vuelo vuelo = new Vuelo(numeroVuelo, origen, destino, fecha, capacidadEconomica, capacidadEjecutiva);
        vuelos.add(vuelo);
        return vuelo;
    }
    
    public Pasajero registrarPasajeroEconomico(String id, String nombre) {
        Pasajero pasajero = new Economica(id, nombre);
        pasajeros.add(pasajero);
        return pasajero;
    }
    
    public Pasajero registrarPasajeroEjecutivo(String id, String nombre) {
        Pasajero pasajero = new Ejecutiva(id, nombre);
        pasajeros.add(pasajero);
        return pasajero;
    }
    
    public Reserva crearReserva(Vuelo vuelo, Pasajero pasajero, String numeroAsiento) {
        if (vuelo.asignarAsiento(pasajero, numeroAsiento)) {
            String reservaId = UUID.randomUUID().toString().substring(0, 8);
            Reserva reserva = new Reserva(reservaId, vuelo, pasajero, numeroAsiento);
            reservas.add(reserva);
            return reserva;
        }
        return null;
    }
    
    public boolean cancelarReserva(String reservaId) {
        Reserva reserva = buscarReserva(reservaId);
        if (reserva != null) {
            reserva.getVuelo().cancelarAsiento(reserva.getNumeroAsiento());
            reservas.remove(reserva);
            return true;
        }
        return false;
    }
    
    public Reserva buscarReserva(String reservaId) {
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(reservaId)) {
                return reserva;
            }
        }
        return null;
    }
    
    public List<Vuelo> buscarVuelos(String origen, String destino) {
        return vuelos.stream()
                .filter(v -> v.getOrigen().equalsIgnoreCase(origen) && 
                             v.getDestino().equalsIgnoreCase(destino))
                .collect(Collectors.toList());
    }
    
    public List<Pasajero> listarPasajeros(Vuelo vuelo) {
        List<Pasajero> pasajerosVuelo = new ArrayList<>();
        pasajerosVuelo.addAll(vuelo.getAsientosEconomicos().values());
        pasajerosVuelo.addAll(vuelo.getAsientosEjecutivos().values());
        return pasajerosVuelo;
    }
    
    public List<Vuelo> getVuelos() {
        return vuelos;
    }
    
    public List<Reserva> getReservas() {
        return reservas;
    }
    
    public List<Pasajero> getPasajeros() {
        return pasajeros;
    }
}
