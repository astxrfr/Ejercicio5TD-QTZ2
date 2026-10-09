import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public abstract class Modulo {
    private static final Set<Integer> IDS = new HashSet<>();

    private String id;
    private String nombre;
    private double salud;
    private boolean activo;
    private double costo;

    protected RecursosMision recursos;

    private static int generarId() {
        int nueva;
        do {
            nueva = new Random().nextInt(10000);
        } while (IDS.contains(nueva));
        IDS.add(nueva);
        return nueva;
    }

    protected Modulo(String nombre, double salud, double costo, RecursosMision recursos) {
        this.id = this.getPrefijoId() + generarId();
        this.nombre = nombre;
        this.salud = salud;
        this.activo = true;
        this.costo = costo;
        this.recursos = recursos;
    }

    public abstract Respuesta<?> procesarCiclo();

    public abstract String getPrefijoId();

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalud() {
        return salud;
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean isInactivo() {
        return !activo;
    }

    public double getCosto() {
        return costo;
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s%n  Salud: %.1f | Estado: %s | Costo: %.2f",
                id, nombre, salud, activo ? "ACTIVO" : "INACTIVO", costo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Modulo other)) return false;

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
