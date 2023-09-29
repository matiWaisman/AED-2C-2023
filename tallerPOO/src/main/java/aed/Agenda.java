package aed;
import java.util.Vector;

public class Agenda {
    private Fecha _fecha;
    private Recordatorio[] _recordatorios;
    public Agenda(Fecha fechaActual) {
        Fecha fechaActualBuffer = new Fecha(fechaActual);
        _fecha = fechaActualBuffer;
        _recordatorios  = new Recordatorio[0];
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        Recordatorio [] recordatoriosAux = new Recordatorio[_recordatorios.length + 1];
        for(int i = 0; i < _recordatorios.length; i++){
            recordatoriosAux[i] = _recordatorios[i];
        }
        recordatoriosAux[_recordatorios.length] = recordatorio;
        _recordatorios = recordatoriosAux;
    }

    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(_fecha);
        sbuffer.append("\n=====\n");
        for(int i = 0; i < _recordatorios.length;i++){
           if (_recordatorios[i].fecha().equals(_fecha)){
           sbuffer.append(_recordatorios[i].toString());
           sbuffer.append("\n");
           }
        }
        return sbuffer.toString();
    }

    public void incrementarDia() {
        _fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        return _fecha;
    }

}
