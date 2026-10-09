import java.util.Optional;

public abstract class ModuloConsumidor extends Modulo{
    public ModuloConsumidor(String nombre, double salud, double costo, RecursosMision recursos) {
        super(nombre, salud, costo, recursos);
    }

    @Override
    public Respuesta<Void> procesarCiclo() {
        if (this.isInactivo()) return new Respuesta<>(false, "El módulo no está activo.");

        if (recursos.getEnergiaDisponible() < getEnergiaRequerida())
            return new Respuesta<>(false, "No hay suficiente energía disponible para el módulo.");

        if (motivoBloqueoAdicional(this.recursos).isPresent()) return new Respuesta<>(false, motivoBloqueoAdicional(this.recursos).get());

        return new Respuesta<>(
                true,
                operar(this.recursos));
    }

    protected abstract double getEnergiaRequerida();

    protected abstract String operar(RecursosMision recursos);

    protected Optional<String> motivoBloqueoAdicional(RecursosMision recursos) {
        return Optional.empty();
    }
}
