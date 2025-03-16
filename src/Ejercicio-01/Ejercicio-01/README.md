# Sistema de Gestión de Vuelos de una Aerolínea

Una aerolínea necesita un software para gestionar vuelos, pasajeros y asignaciones de asientos. El sistema debe cumplir con las siguientes funcionalidades:

## Funcionalidades


1. **Registrar vuelos:** Incluir información sobre número de vuelo, origen, destino, fecha y capacidad.
2. **Registrar pasajeros:** Contar con un identificador único, nombre y categoría (económica o ejecutiva).
3. **Asignación de asientos:** Permitir la asignación de asientos según disponibilidad y categoría del pasajero.
4. **Consulta de vuelos:** Facilitar la búsqueda de vuelos disponibles según origen y destino.
5. **Listado de pasajeros:** Mostrar la lista de pasajeros asignados a un vuelo.
6. **Gestión de cancelaciones y cambios:** Administrar cancelaciones de vuelos o cambios de asiento.
7. **Interfaz gráfica:** Ofrecer una interfaz gráfica con `JFrame` para facilitar la interacción del usuario.

## Diseño de Clases (mínimo 7)

1. **Vuelo:** Representa un vuelo con su número, origen, destino, fecha y asientos disponibles.
2. **Pasajero:** Representa a un viajero con nombre, ID y categoría (económica o ejecutiva).
3. **Reserva:** Gestiona la asignación de pasajeros a vuelos y asientos.
4. **Aerolínea:** Administra la lista de vuelos y reservas activas.
5. **Económica (extiende Pasajero):** Define a los pasajeros con menor prioridad de asignación.
6. **Ejecutiva (extiende Pasajero):** Define a los pasajeros con prioridad para mejores asientos.
7. **VentanaAerolínea (extiende JFrame):** Representa la interfaz gráfica.
