package aed;

public class Recordatorio {
    String _mensaje;
    Fecha _fecha;
    Horario _horario;
    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = fecha.clone();
        _horario = horario.clone();
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        return _fecha;
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        return "" + _mensaje + " @ " + _fecha.toString() + " " + _horario.toString();
    }

}
