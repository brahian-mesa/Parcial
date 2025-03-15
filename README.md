## Sistema de Gestión de Vuelos de una Areolínia

<p>
Una aerolínea necesita un software para gestionar vuelos, pasajeros y asignaciones de
asientos. El sistema debe:
    1️. Registrar vuelos con información sobre número de vuelo, origen, destino, fecha y
    capacidad.
    2️. Registrar pasajeros con un identificador único, nombre y categoría (económica o
    ejecutiva).
    3️. Permitir la asignación de asientos según disponibilidad y categoría del pasajero.
    4️. Permitir la consulta de vuelos disponibles según origen y destino.
    5️. Mostrar la lista de pasajeros asignados a un vuelo.
    6️. Gestionar cancelaciones de vuelos o cambios de asiento.
    7️. Tener una interfaz gráfica con JFrame para que el usuario pueda interactuar fácilmente
    con el sistema.
Diseño de Clases (mínimo 7)
    1️. Vuelo: Representa un vuelo con su número, origen, destino, fecha y asientos disponibles.
    2️. Pasajero: Representa a un viajero con nombre, ID y categoría (económica o ejecutiva).
    3️. Reserva: Gestiona la asignación de pasajeros a vuelos y asientos.
    4️. Aerolinea: Administra la lista de vuelos y reservas activas.
    5️. Economica (extiende Pasajero): Pasajeros con menor prioridad de asignación.
    6️.Ejecutiva (extiende Pasajero): Pasajeros con prioridad para mejores asientos.
    7️.VentanaAerolinea (extiende JFrame): Representa la interfaz gráfica.
<p>
