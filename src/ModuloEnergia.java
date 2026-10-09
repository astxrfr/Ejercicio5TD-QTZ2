import java.util.Optional;

public class ModuloEnergia extends Modulo {
    private double energiaPorCiclo;
    private double energiaTotalGenerada;
    private int ciclos;

    public ModuloEnergia(String nombre, double salud, double costo, RecursosMision recursos, double energiaPorCiclo, int ciclos) {
        super(nombre, salud, costo, recursos);
        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclos = ciclos;
        this.energiaTotalGenerada = energiaPorCiclo * ciclos;
    }

    @Override
    public Respuesta<Double> procesarCiclo() {
        if (this.isInactivo()) return new Respuesta<>(false, "El módulo no está activo.");

        this.energiaTotalGenerada += this.energiaPorCiclo;
        this.ciclos++;
        recursos.agregarEnergia(this.energiaPorCiclo);
        return new Respuesta<>(
                true,
                "Energia generada en el ciclo: " + this.energiaPorCiclo,
                Optional.of(this.energiaPorCiclo));
    }

    @Override
    public String getPrefijoId() {
        return "ENR";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "%n  Tipo: Energía | Genera por ciclo: %.2f | Ciclos acumulados: %d | Total generado: %.2f",
                energiaPorCiclo, ciclos, energiaTotalGenerada);
    }
}
