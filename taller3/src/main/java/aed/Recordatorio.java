package aed;

public class Recordatorio {
    String _mensaje;
    Fecha _fecha;
    Horario _horario;
    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        Fecha fechaBuffer = new Fecha(fecha);
        _fecha = fechaBuffer;
        _horario = horario;
    }

    public Recordatorio(Recordatorio recordatorio){
        _mensaje = recordatorio.mensaje();
        _fecha = recordatorio.fecha();
        _horario = recordatorio.horario();
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        Fecha fechaBuffer = new Fecha(_fecha);
        return fechaBuffer;
    }

    public String mensaje() {
        String mensajebufer = new String(_mensaje);
        return mensajebufer;
    }

    @Override
    public String toString() {
        return "" + _mensaje + " @ " + _fecha.toString() + " " + _horario.toString();
    }

}
