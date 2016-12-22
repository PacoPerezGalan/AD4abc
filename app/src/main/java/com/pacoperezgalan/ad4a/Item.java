package com.pacoperezgalan.ad4a;

/**
 * Created by 2dam on 22/12/2016.
 */

public class Item {
    String es;
    String id;
    String nombre;
    String edad;
    String ciclo;
    String curso;
    String notadespacho;

    public Item(String ser, String ide, String nom, String eda, String cic, String cur, String notdes) {
        es = ser; //si es alumno o profesor
        id = ide;
        nombre = nom;
        edad = eda;
        ciclo = cic;
        curso = cur;
        notadespacho = notdes;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNotadespacho() {
        return notadespacho;
    }

    public void setNotadespacho(String notadespacho) {
        this.notadespacho = notadespacho;
    }



}