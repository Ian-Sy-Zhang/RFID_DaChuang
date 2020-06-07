package com.example.rfid.vo;

public class ChemcasVO {
    int CAS_id;
    String Name;
    double FusionPoint;
    double BoilingPoint;
    int ExistType;
    int IsOrganic;
    int Oxidation;
    int Reducibility;
    int Inflammability;
    int Explosion;

    public int getCAS_id() {
        return CAS_id;
    }

    public void setCAS_id(int CAS_id) {
        this.CAS_id = CAS_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getFusionPoint() {
        return FusionPoint;
    }

    public void setFusionPoint(double fusionPoint) {
        FusionPoint = fusionPoint;
    }

    public double getBoilingPoint() {
        return BoilingPoint;
    }

    public void setBoilingPoint(double boilingPoint) {
        BoilingPoint = boilingPoint;
    }

    public int getExistType() {
        return ExistType;
    }

    public void setExistType(int existType) {
        ExistType = existType;
    }

    public int getIsOrganic() {
        return IsOrganic;
    }

    public void setIsOrganic(int isOrganic) {
        IsOrganic = isOrganic;
    }

    public int getOxidation() {
        return Oxidation;
    }

    public void setOxidation(int oxidation) {
        Oxidation = oxidation;
    }

    public int getReducibility() {
        return Reducibility;
    }

    public void setReducibility(int reducibility) {
        Reducibility = reducibility;
    }

    public int getInflammability() {
        return Inflammability;
    }

    public void setInflammability(int inflammability) {
        Inflammability = inflammability;
    }

    public int getExplosion() {
        return Explosion;
    }

    public void setExplosion(int explosion) {
        Explosion = explosion;
    }
}
