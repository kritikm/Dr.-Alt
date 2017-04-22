package com.example.kritik.dralt.Pojo;

import com.example.kritik.dralt.Utility.Commons;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kritik on 08-Apr-17.
 */

public class Patient implements Serializable
{
    private String patientonsetage;
    private String patientonsetageunit;
    private String patientsex;
    private String patientweight;

    private ArrayList<Drug> drug;
    private ArrayList<Reaction> reaction;

    public String getPatientOnsetAge()
    {
        return patientonsetage;
    }

    public void setPatientOnsetAge(String patientonsetage)
    {
        this.patientonsetage = patientonsetage;
    }

    public String getPatientOnsetAgeUnit()
    {
        return Commons.intervals.get(patientonsetageunit);
    }

    public void setPatientOnsetAgeUnit(String patientonsetageunit)
    {
        this.patientonsetageunit = patientonsetageunit;
    }

    public String getPatientSex()
    {
        return Commons.sex.get(patientsex);
    }

    public void setPatientSex(String patientsex)
    {
        this.patientsex = patientsex;
    }

    public String getPatientWeight()
    {
        return patientweight;
    }

    public void setPatientWeight(String patientweight)
    {
        this.patientweight = patientweight;
    }

    public ArrayList<Drug> getDrug()
    {
        return drug;
    }

    public void setDrug(ArrayList<Drug> drug)
    {
        this.drug = drug;
    }

    public ArrayList<Reaction> getReaction()
    {
        return reaction;
    }

    public void setReaction(ArrayList<Reaction> reaction)
    {
        this.reaction = reaction;
    }
}
