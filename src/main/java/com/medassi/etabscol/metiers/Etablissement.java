package com.medassi.etabscol.metiers;

public class Etablissement {

    private String uai;
    private String libelle;
    private String adr;
    private String cp;
    private String lieu_dit;
    private Statut status;
    private final Commune laCommune;

    public Etablissement(String uai, String libelle, Commune laCommune) {
        this.uai = uai;
        this.libelle = libelle;
        this.laCommune = laCommune;
    }

    public String getAdrComp() {
        return this.adr + " " + this.cp + " " + this.laCommune.getLibelle();
    }

    

    public Commune getLaCommune() {
        return laCommune;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getUai() {
        return uai;
    }

    public Statut getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.libelle;
    }

    public void setStatus(Statut status) {
        this.status = status;
    }

    public void setLieu_dit(String lieu_dit) {
        this.lieu_dit = lieu_dit;
    }



    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }
    

}
