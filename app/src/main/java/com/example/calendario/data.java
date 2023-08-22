package com.example.calendario;

public class data {
    String fechaI;
    String tarea;
    String importancia;
    String fechaF;

    public data() {
    }

    public data(String fechaI, String tarea, String importancia, String fechaF) {
        this.fechaI = fechaI;
        this.tarea = tarea;
        this.importancia = importancia;
        this.fechaF = fechaF;
    }


    public String getFechaI() {
        return fechaI;
    }

    public String getTarea() {
        return tarea;
    }

    public String getImportancia() {
        return importancia;
    }

    public String getFechaF() {
        return fechaF;
    }


    @Override
    public String toString() {
        return "data{" +
                "fechaI='" + fechaI + '\'' +
                ", tarea='" + tarea + '\'' +
                ", importancia='" + importancia + '\'' +
                ", fechaF='" + fechaF + '\'' +
                '}';
    }
}
