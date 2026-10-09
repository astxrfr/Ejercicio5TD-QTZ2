public class RecursosMision {
    private double energiaDisponible;
    private double datosEnOrbita;
    private double datosDescargados;

    public RecursosMision(double energiaDisponible, double datosEnOrbita, double datosDescargados) {
        this.energiaDisponible = energiaDisponible;
        this.datosEnOrbita = datosEnOrbita;
        this.datosDescargados = datosDescargados;
    }

    public void agregarEnergia(double e) {
        if (e <= 0) throw new IllegalArgumentException("La energía a agregar debe ser mayor que cero.");

        this.energiaDisponible += e;
    }

    public void consumirEnergia(double e) {
        if (e <= 0) throw new IllegalArgumentException("La energía a consumir debe ser mayor que cero.");

        this.energiaDisponible -= e;
    }

    public void registrarRecoleccion(double mb) {
        if (mb <= 0) throw new IllegalArgumentException("Los datos a registrar deben ser mayores que cero.");

        datosEnOrbita += mb;
    }

    public void registrarDescarga(double mb) {
        if (mb <= 0) throw new IllegalArgumentException("Los datos a descargar deben ser mayores que cero.");
        if (mb > datosEnOrbita) {
            datosDescargados += datosEnOrbita;
            datosEnOrbita = 0;
            return;
        }

        datosEnOrbita -= mb;
        datosDescargados += mb;
    }

    public double getEnergiaDisponible() {
        return energiaDisponible;
    }
    public double getDatosEnOrbita() {
        return datosEnOrbita;
    }
    public double getDatosDescargados() {
        return datosDescargados;
    }
}
