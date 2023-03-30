package com.accenture.remoterevolution.autohaus.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "kfz")
public class Kfz {

    @Id
    @Column(name = "kfz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kfz_bezeichnung")
    private String bezeichnung;
    @Column(name = "kfz_marke")
    private String marke;
    @Column(name = "kfz_milage")
    private int milage;

    private String guid;
    @ManyToOne
    @JoinColumn(name = "autohaus_id")
    private Autohaus autohaus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Autohaus getAutohaus() {
        return autohaus;
    }

    public void setAutohaus(Autohaus autohaus) {
        this.autohaus = autohaus;
    }
}
