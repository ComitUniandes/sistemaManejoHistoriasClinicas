package com.asnauj.aplicacionpaciente.entities;


import java.io.Serializable;
import java.util.ArrayList;

public class Paciente  implements Serializable{
    private String name;
    private String lastName;
    private String id;
    private String[] medicosAceptados;
    private String bebedor;
    private String fumador;
    private String nr;
    private String donante;
    private String sangre;

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public Paciente(String name, String lastName, String id, String[] medicosAceptados, String bebedor, String fumador, String nr, String donante) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.medicosAceptados = medicosAceptados;
        this.bebedor = bebedor;
        this.fumador = fumador;
        this.nr = nr;
        this.donante = donante;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getMedicosAceptados() {
        return medicosAceptados;
    }

    public void setMedicosAceptados(String[] medicosAceptados) {
        this.medicosAceptados = medicosAceptados;
    }

    public String getBebedor() {
        return bebedor;
    }

    public void setBebedor(String bebedor) {
        this.bebedor = bebedor;
    }

    public String getFumador() {
        return fumador;
    }


    public void setFumador(String fumador) {
        this.fumador = fumador;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getDonante() {
        return donante;
    }

    public void setDonante(String donante) {
        this.donante = donante;
    }
}
