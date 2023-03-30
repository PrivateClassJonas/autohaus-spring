package com.accenture.remoterevolution.autohaus.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "autohaus")
public class Autohaus {
    @Id
    @Column(name = "autohaus_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guid;
    @Column(name = "autohaus_adresse")
    @NotBlank(message = "Adresse ist Pflicht!")
    @NotNull
    private String ahAdresse;

    public Long getId() {
        return id;
    }

    public Autohaus() {

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Autohaus(Long id, String ahAdresse) {
        this.id = id;
        this.ahAdresse = ahAdresse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAhAdresse() {
        return ahAdresse;
    }

    public void setAhAdresse(String ahAdresse) {
        this.ahAdresse = ahAdresse;
    }
}
