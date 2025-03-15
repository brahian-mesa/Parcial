import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Vuelo {
    private String numeroVuelo;
    private String origen;
    private String destino;
    private Date fecha;
    private int capacidadEconomica;
    private int capacidadEjecutiva;
    private Map<String, Pasajero> asientosEconomicos;
    private Map<String, Pasajero> asientosEjecutivos;
    
    public Vuelo(String numeroVuelo, String origen, String destino, Date fecha, 
                 int capacidadEconomica, int capacidadEjecutiva) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidadEconomica = capacidadEconomica;
        this.capacidadEjecutiva = capacidadEjecutiva;
        this.asientosEconomicos = new HashMap<>();
        this.asientosEjecutivos = new HashMap<>();
    }
    
    public String getNumeroVuelo() {
        return numeroVuelo;
    }
    
    public String getOrigen() {
        return origen;
    }
    
    public String getDestino() {
        return destino;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public int getCapacidadEconomica() {
        return capacidadEconomica;
    }
    
    public int getCapacidadEjecutiva() {
        return capacidadEjecutiva;
    }
    
    public int getAsientosEconomicosDisponibles() {
        return capacidadEconomica - asientosEconomicos.size();
    }
    
    public int getAsientosEjecutivosDisponibles() {
        return capacidadEjecutiva - asientosEjecutivos.size();
    }
    
    public boolean asignarAsiento(Pasajero pasajero, String numeroAsiento) {
        if (pasajero instanceof Ejecutiva) {
            if (asientosEjecutivos.size() < capacidadEjecutiva && !asientosEjecutivos.containsKey(numeroAsiento)) {
                asientosEjecutivos.put(numeroAsiento, pasajero);
                return true;
            }
        } else {
            if (asientosEconomicos.size() < capacidadEconomica && !asientosEconomicos.containsKey(numeroAsiento)) {
                asientosEconomicos.put(numeroAsiento, pasajero);
                return true;
            }
        }
        return false;
    }
    
    public boolean cancelarAsiento(String numeroAsiento) {
        if (asientosEconomicos.containsKey(numeroAsiento)) {
            asientosEconomicos.remove(numeroAsiento);
            return true;
        } else if (asientosEjecutivos.containsKey(numeroAsiento)) {
            asientosEjecutivos.remove(numeroAsiento);
            return true;
        }
        return false;
    }
    
    public Map<String, Pasajero> getAsientosEconomicos() {
        return asientosEconomicos;
    }
    
    public Map<String, Pasajero> getAsientosEjecutivos() {
        return asientosEjecutivos;
    }
    
    @Override
    public String toString() {
        return numeroVuelo + " - " + origen + " a " + destino + " - " + fecha;
    }
}
