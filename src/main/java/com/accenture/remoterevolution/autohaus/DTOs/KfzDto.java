package com.accenture.remoterevolution.autohaus.DTOs;

public class KfzDto {
    private String bezeichnung;
    private String marke;
    private int milage;

    private String guid;
    //private AutohausDto autohaus;
    private String autohausGuid;
    private String autohausAdresse;

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

   /* public AutohausDto getAutohaus() {
        return autohaus;
    }

    public void setAutohaus(AutohausDto autohaus) {
        this.autohaus = autohaus;
    }*/

    public String getAutohausGuid() {
        return autohausGuid;
    }

    public void setAutohausGuid(String autohausGuid) {
        this.autohausGuid = autohausGuid;
    }

    public String getAutohausAdresse() {
        return autohausAdresse;
    }

    public void setAutohausAdresse(String autohausAdresse) {
        this.autohausAdresse = autohausAdresse;
    }
}
