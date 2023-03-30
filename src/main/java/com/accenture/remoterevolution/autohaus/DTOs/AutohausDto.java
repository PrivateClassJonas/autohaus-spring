package com.accenture.remoterevolution.autohaus.DTOs;


public class AutohausDto {
    private String adresse;
    private String guid;

    public String getAdresse() {
        return adresse;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
