package aed;

public class Horario {
    private int _hora;
    private int _minutos;
    public Horario(int hora, int minutos) {
        _hora = hora;
        _minutos = minutos;
    }

    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minutos;
    }

    @Override
    public String toString() {
        return "" + _hora + ":" + _minutos;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (otro == null || !(otro instanceof Horario)) {
            return false; 
        }
        Horario otroHorario = (Horario) otro; 
        return this.toString().equals(otroHorario.toString());
    }

    public Horario clone() {
        return new Horario(_hora, _minutos);
    }

}
