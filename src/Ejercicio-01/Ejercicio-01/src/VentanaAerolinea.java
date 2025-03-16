import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VentanaAerolinea extends JFrame {
    private Aerolinea aerolinea;
    private JTabbedPane pestanas;
    
    // Componentes para registrar vuelos
    private JTextField txtNumeroVuelo, txtOrigen, txtDestino, txtFecha;
    private JTextField txtCapacidadEconomica, txtCapacidadEjecutiva;
    private JButton btnRegistrarVuelo;
    private DefaultTableModel modeloVuelos;
    private JTable tablaVuelos;
    
    // Componentes para registrar pasajeros
    private JTextField txtIdPasajero, txtNombrePasajero;
    private JComboBox<String> cmbCategoriaPasajero;
    private JButton btnRegistrarPasajero;
    private DefaultTableModel modeloPasajeros;
    private JTable tablaPasajeros;
    
    // Componentes para reservas
    private JComboBox<Vuelo> cmbVuelos;
    private JComboBox<Pasajero> cmbPasajeros;
    private JTextField txtAsiento;
    private JButton btnCrearReserva, btnCancelarReserva;
    private DefaultTableModel modeloReservas;
    private JTable tablaReservas;
    
    // Componentes para búsqueda de vuelos
    private JTextField txtBuscarOrigen, txtBuscarDestino;
    private JButton btnBuscarVuelos;
    private DefaultTableModel modeloBusquedaVuelos;
    private JTable tablaBusquedaVuelos;
    
    // Componentes para listar pasajeros de un vuelo
    private JComboBox<Vuelo> cmbVuelosPasajeros;
    private JButton btnListarPasajeros;
    private DefaultTableModel modeloPasajerosVuelo;
    private JTable tablaPasajerosVuelo;
    
    public VentanaAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
        
        // Configuración básica de la ventana
        setTitle("Sistema de Gestión de Aerolínea - " + aerolinea.getNombre());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicializar componentes
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        pestanas = new JTabbedPane();
        
        // Crear las pestañas
        pestanas.addTab("Registrar Vuelos", crearPanelRegistroVuelos());
        pestanas.addTab("Registrar Pasajeros", crearPanelRegistroPasajeros());
        pestanas.addTab("Gestionar Reservas", crearPanelReservas());
        pestanas.addTab("Buscar Vuelos", crearPanelBusquedaVuelos());
        pestanas.addTab("Listar Pasajeros", crearPanelListadoPasajeros());
        
        // Agregar el panel de pestañas al frame
        getContentPane().add(pestanas);
    }
    
    private JPanel crearPanelRegistroVuelos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("Número de Vuelo:"));
        txtNumeroVuelo = new JTextField();
        panelFormulario.add(txtNumeroVuelo);
        
        panelFormulario.add(new JLabel("Origen:"));
        txtOrigen = new JTextField();
        panelFormulario.add(txtOrigen);
        
        panelFormulario.add(new JLabel("Destino:"));
        txtDestino = new JTextField();
        panelFormulario.add(txtDestino);
        
        panelFormulario.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);
        
        panelFormulario.add(new JLabel("Capacidad Económica:"));
        txtCapacidadEconomica = new JTextField();
        panelFormulario.add(txtCapacidadEconomica);
        
        panelFormulario.add(new JLabel("Capacidad Ejecutiva:"));
        txtCapacidadEjecutiva = new JTextField();
        panelFormulario.add(txtCapacidadEjecutiva);
        
        btnRegistrarVuelo = new JButton("Registrar Vuelo");
        btnRegistrarVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVuelo();
            }
        });
        panelFormulario.add(btnRegistrarVuelo);
        
        // Panel de tabla
        modeloVuelos = new DefaultTableModel();
        modeloVuelos.addColumn("Número");
        modeloVuelos.addColumn("Origen");
        modeloVuelos.addColumn("Destino");
        modeloVuelos.addColumn("Fecha");
        modeloVuelos.addColumn("Asientos Eco.");
        modeloVuelos.addColumn("Asientos Ejec.");
        
        tablaVuelos = new JTable(modeloVuelos);
        JScrollPane scrollTabla = new JScrollPane(tablaVuelos);
        
        // Agregar componentes al panel principal
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelRegistroPasajeros() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("ID Pasajero:"));
        txtIdPasajero = new JTextField();
        panelFormulario.add(txtIdPasajero);
        
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombrePasajero = new JTextField();
        panelFormulario.add(txtNombrePasajero);
        
        panelFormulario.add(new JLabel("Categoría:"));
        cmbCategoriaPasajero = new JComboBox<>(new String[]{"Económica", "Ejecutiva"});
        panelFormulario.add(cmbCategoriaPasajero);
        
        btnRegistrarPasajero = new JButton("Registrar Pasajero");
        btnRegistrarPasajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPasajero();
            }
        });
        panelFormulario.add(btnRegistrarPasajero);
        
        // Panel de tabla
        modeloPasajeros = new DefaultTableModel();
        modeloPasajeros.addColumn("ID");
        modeloPasajeros.addColumn("Nombre");
        modeloPasajeros.addColumn("Categoría");
        
        tablaPasajeros = new JTable(modeloPasajeros);
        JScrollPane scrollTabla = new JScrollPane(tablaPasajeros);
        
        // Agregar componentes al panel principal
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelReservas() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("Vuelo:"));
        cmbVuelos = new JComboBox<>();
        panelFormulario.add(cmbVuelos);
        
        panelFormulario.add(new JLabel("Pasajero:"));
        cmbPasajeros = new JComboBox<>();
        panelFormulario.add(cmbPasajeros);
        
        panelFormulario.add(new JLabel("Número de Asiento:"));
        txtAsiento = new JTextField();
        panelFormulario.add(txtAsiento);
        
        btnCrearReserva = new JButton("Crear Reserva");
        btnCrearReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReserva();
            }
        });
        panelFormulario.add(btnCrearReserva);
        
        btnCancelarReserva = new JButton("Cancelar Reserva Seleccionada");
        btnCancelarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarReserva();
            }
        });
        panelFormulario.add(btnCancelarReserva);
        
        // Panel de tabla
        modeloReservas = new DefaultTableModel();
        modeloReservas.addColumn("ID Reserva");
        modeloReservas.addColumn("Vuelo");
        modeloReservas.addColumn("Pasajero");
        modeloReservas.addColumn("Asiento");
        
        tablaReservas = new JTable(modeloReservas);
        JScrollPane scrollTabla = new JScrollPane(tablaReservas);
        
        // Agregar componentes al panel principal
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBusquedaVuelos() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("Origen:"));
        txtBuscarOrigen = new JTextField();
        panelFormulario.add(txtBuscarOrigen);
        
        panelFormulario.add(new JLabel("Destino:"));
        txtBuscarDestino = new JTextField();
        panelFormulario.add(txtBuscarDestino);
        
        btnBuscarVuelos = new JButton("Buscar Vuelos");
        btnBuscarVuelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVuelos();
            }
        });
        panelFormulario.add(btnBuscarVuelos);
        
        // Panel de tabla
        modeloBusquedaVuelos = new DefaultTableModel();
        modeloBusquedaVuelos.addColumn("Número");
        modeloBusquedaVuelos.addColumn("Origen");
        modeloBusquedaVuelos.addColumn("Destino");
        modeloBusquedaVuelos.addColumn("Fecha");
        modeloBusquedaVuelos.addColumn("Asientos Eco. Disp.");
        modeloBusquedaVuelos.addColumn("Asientos Ejec. Disp.");
        
        tablaBusquedaVuelos = new JTable(modeloBusquedaVuelos);
        JScrollPane scrollTabla = new JScrollPane(tablaBusquedaVuelos);
        
        // Agregar componentes al panel principal
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelListadoPasajeros() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelFormulario.add(new JLabel("Seleccionar Vuelo:"));
        cmbVuelosPasajeros = new JComboBox<>();
        panelFormulario.add(cmbVuelosPasajeros);
        
        btnListarPasajeros = new JButton("Listar Pasajeros");
        btnListarPasajeros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPasajeros();
            }
        });
        panelFormulario.add(btnListarPasajeros);
        
        // Panel de tabla
        modeloPasajerosVuelo = new DefaultTableModel();
        modeloPasajerosVuelo.addColumn("ID");
        modeloPasajerosVuelo.addColumn("Nombre");
        modeloPasajerosVuelo.addColumn("Categoría");
        modeloPasajerosVuelo.addColumn("Asiento");
        
        tablaPasajerosVuelo = new JTable(modeloPasajerosVuelo);
        JScrollPane scrollTabla = new JScrollPane(tablaPasajerosVuelo);
        
        // Agregar componentes al panel principal
        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Métodos para manejar eventos
    
    private void registrarVuelo() {
        try {
            String numeroVuelo = txtNumeroVuelo.getText();
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            String fechaStr = txtFecha.getText();
            int capacidadEconomica = Integer.parseInt(txtCapacidadEconomica.getText());
            int capacidadEjecutiva = Integer.parseInt(txtCapacidadEjecutiva.getText());
            
            // Validar datos
            if (numeroVuelo.isEmpty() || origen.isEmpty() || destino.isEmpty() || fechaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Convertir fecha
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse(fechaStr);
            
            // Registrar vuelo
            Vuelo vuelo = aerolinea.registrarVuelo(numeroVuelo, origen, destino, fecha, capacidadEconomica, capacidadEjecutiva);
            
            // Actualizar tabla
            Object[] fila = {
                vuelo.getNumeroVuelo(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                sdf.format(vuelo.getFecha()),
                vuelo.getCapacidadEconomica(),
                vuelo.getCapacidadEjecutiva()
            };
            modeloVuelos.addRow(fila);
            
            // Actualizar combos
            actualizarCombosVuelos();
            
            // Limpiar campos
            txtNumeroVuelo.setText("");
            txtOrigen.setText("");
            txtDestino.setText("");
            txtFecha.setText("");
            txtCapacidadEconomica.setText("");
            txtCapacidadEjecutiva.setText("");
            
            JOptionPane.showMessageDialog(this, "Vuelo registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Las capacidades deben ser números enteros", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registrarPasajero() {
        String id = txtIdPasajero.getText();
        String nombre = txtNombrePasajero.getText();
        String categoria = (String) cmbCategoriaPasajero.getSelectedItem();
        
        // Validar datos
        if (id.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID y nombre son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Registrar pasajero
        Pasajero pasajero;
        if ("Ejecutiva".equals(categoria)) {
            pasajero = aerolinea.registrarPasajeroEjecutivo(id, nombre);
        } else {
            pasajero = aerolinea.registrarPasajeroEconomico(id, nombre);
        }
        
        // Actualizar tabla
        Object[] fila = {
            pasajero.getId(),
            pasajero.getNombre(),
            pasajero.getCategoria()
        };
        modeloPasajeros.addRow(fila);
        
        // Actualizar combos
        actualizarCombosPasajeros();
        
        // Limpiar campos
        txtIdPasajero.setText("");
        txtNombrePasajero.setText("");
        
        JOptionPane.showMessageDialog(this, "Pasajero registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void crearReserva() {
        try {
            Vuelo vuelo = (Vuelo) cmbVuelos.getSelectedItem();
            Pasajero pasajero = (Pasajero) cmbPasajeros.getSelectedItem();
            String numeroAsiento = txtAsiento.getText();
            
            // Validar datos
            if (vuelo == null || pasajero == null || numeroAsiento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear reserva
            Reserva reserva = aerolinea.crearReserva(vuelo, pasajero, numeroAsiento);
            
            if (reserva != null) {
                // Actualizar tabla
                Object[] fila = {
                    reserva.getId(),
                    reserva.getVuelo().getNumeroVuelo(),
                    reserva.getPasajero().getNombre(),
                    reserva.getNumeroAsiento()
                };
                modeloReservas.addRow(fila);
                
                // Limpiar campo
                txtAsiento.setText("");
                
                JOptionPane.showMessageDialog(this, "Reserva creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo crear la reserva. Verifique la disponibilidad del asiento.", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear la reserva: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cancelarReserva() {
        int filaSeleccionada = tablaReservas.getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            String reservaId = (String) modeloReservas.getValueAt(filaSeleccionada, 0);
            
            if (aerolinea.cancelarReserva(reservaId)) {
                modeloReservas.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(this, "Reserva cancelada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo cancelar la reserva", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva para cancelar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void buscarVuelos() {
        String origen = txtBuscarOrigen.getText();
        String destino = txtBuscarDestino.getText();
        
        // Validar datos
        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Origen y destino son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Buscar vuelos
        List<Vuelo> vuelosEncontrados = aerolinea.buscarVuelos(origen, destino);
        
        // Limpiar tabla
        modeloBusquedaVuelos.setRowCount(0);
        
        // Actualizar tabla
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Vuelo vuelo : vuelosEncontrados) {
            Object[] fila = {
                vuelo.getNumeroVuelo(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                sdf.format(vuelo.getFecha()),
                vuelo.getAsientosEconomicosDisponibles(),
                vuelo.getAsientosEjecutivosDisponibles()
            };
            modeloBusquedaVuelos.addRow(fila);
        }
        
        if (vuelosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron vuelos para la ruta especificada", 
                                         "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void listarPasajeros() {
        Vuelo vuelo = (Vuelo) cmbVuelosPasajeros.getSelectedItem();
        
        if (vuelo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vuelo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Obtener pasajeros
        List<Pasajero> pasajerosVuelo = aerolinea.listarPasajeros(vuelo);
        
        // Limpiar tabla
        modeloPasajerosVuelo.setRowCount(0);
        
        // Actualizar tabla
        for (Pasajero pasajero : pasajerosVuelo) {
            String asiento = "";
            
            // Buscar el asiento del pasajero
            for (String numAsiento : vuelo.getAsientosEconomicos().keySet()) {
                if (vuelo.getAsientosEconomicos().get(numAsiento) == pasajero) {
                    asiento = numAsiento;
                    break;
                }
            }
            
            if (asiento.isEmpty()) {
                for (String numAsiento : vuelo.getAsientosEjecutivos().keySet()) {
                    if (vuelo.getAsientosEjecutivos().get(numAsiento) == pasajero) {
                        asiento = numAsiento;
                        break;
                    }
                }
            }
            
            Object[] fila = {
                pasajero.getId(),
                pasajero.getNombre(),
                pasajero.getCategoria(),
                asiento
            };
            modeloPasajerosVuelo.addRow(fila);
        }
        
        if (pasajerosVuelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay pasajeros registrados en este vuelo", 
                                         "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Métodos auxiliares
    
    private void actualizarCombosVuelos() {
        cmbVuelos.removeAllItems();
        cmbVuelosPasajeros.removeAllItems();
        
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            cmbVuelos.addItem(vuelo);
            cmbVuelosPasajeros.addItem(vuelo);
        }
    }
    
    private void actualizarCombosPasajeros() {
        cmbPasajeros.removeAllItems();
        
        for (Pasajero pasajero : aerolinea.getPasajeros()) {
            cmbPasajeros.addItem(pasajero);
        }
    }
}