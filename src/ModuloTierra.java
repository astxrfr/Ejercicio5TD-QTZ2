import java.util.Optional;

public class ModuloTierra extends ModuloConsumidor{
    private double capacidadDescarga;
    private double energiaPorDescarga;
    private double totalDescargado;
    private String estacion;

    public ModuloTierra(String nombre, double salud, double costo, RecursosMision recursos, double capacidadDescarga, double energiaPorDescarga, double totalDescargado, String estacion) {
        super(nombre, salud, costo, recursos);
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
        this.totalDescargado = totalDescargado;
        this.estacion = estacion;
    }

    @Override
    protected double getEnergiaRequerida() {
        return energiaPorDescarga;
    }

    @Override
    protected String operar(RecursosMision recursos) {
        double datosEnOrbita = recursos.getDatosEnOrbita();
        double datosADescargar = Math.min(capacidadDescarga, datosEnOrbita);
        recursos.consumirEnergia(energiaPorDescarga);
        recursos.registrarDescarga(datosADescargar);
        totalDescargado += datosADescargar;

        return String.format("Consumió %.2f de energía y descargó %.2f MB.", energiaPorDescarga, datosADescargar);
    }

    @Override
    protected Optional<String> motivoBloqueoAdicional(RecursosMision recursos) {
        return recursos.getDatosEnOrbita() == 0 ? Optional.of("No hay datos en órbita para descargar.") : Optional.empty();
    }

    @Override
    public String getPrefijoId() {
        return "TIE";
    }

    public String getEstacion() {
        return estacion;
    }
    public double getTotalDescargado() {
        return totalDescargado;
    }
    public double getCapacidadDescarga() {
        return capacidadDescarga;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "%n  Tipo: Tierra | Estación: %s | Capacidad de descarga: %.2f MB"
                        + "%n  Energía por descarga: %.2f | Total descargado: %.2f MB",
                estacion, capacidadDescarga, energiaPorDescarga, totalDescargado);
    }
}
