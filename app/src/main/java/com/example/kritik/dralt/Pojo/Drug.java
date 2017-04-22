package com.example.kritik.dralt.Pojo;

import android.support.v4.content.ContextCompat;

import com.example.kritik.dralt.Utility.Commons;

import java.io.Serializable;

/**
 * Created by Kritik on 08-Apr-17.
 */

public class Drug implements Serializable
{
    private String actiondrug;
    private String drugadditional;                      //only present when data was provided;
    private String drugcumulativedosagenumb;
    private String drugcumulativedosageunit;
    private String drugdosageform;
    private String drugintervaldosagedefinition;
    private String drugintervaldosageunitnumb;
    private String drugrecurreadministration;           //whether the reaction occured after readministration of the drug.
    private String drugseparatedosagenumb;
    private String drugstructuredosagenumb;
    private String drugstructuredosageunit;
    private String drugadministrationroute;
    private String drugcharacterization;
    private String drugdosagetext;
    private String drugenddate;
    private String drugstartdate;
    private String drugindication;
    private String drugtreatmentduration;
    private String drugtreatmentdurationunit;
    private String medicinalproduct;

    public String getActionDrug()
    {
        String action = Commons.action.get(actiondrug);

        if(action == null)
            return "Not Available";
        return action;
    }

    public void setActionDrug(String actiondrug)
    {
        this.actiondrug = actiondrug;
    }

    public String getDrugAdditional()
    {
        return drugadditional;
    }

    public void setDrugAdditional(String drugadditional)
    {
        this.drugadditional = drugadditional;
    }

    public String getDrugCumulativeDosageNumb()
    {
        return drugcumulativedosagenumb;
    }

    public void setDrugCumulativeDosageNumb(String drugcumulativedosagenumb)
    {
        this.drugcumulativedosagenumb = drugcumulativedosagenumb;
    }

    public String getDrugCumulativeDosageUnit()
    {
        return drugcumulativedosageunit;
    }

    public void setDrugCumulativeDosageUnit(String drugcumulativedosageunit)
    {
        this.drugcumulativedosageunit = drugcumulativedosageunit;
    }

    public String getDrugDosageForm()
    {
        return drugdosageform;
    }

    public void setDrugDosageForm(String drugdosageform)
    {
        this.drugdosageform = drugdosageform;
    }

    public String getDrugIntervalDosageDefinition()
    {
        return drugintervaldosagedefinition;
    }

    public void setDrugIntervalDosageDefinition(String drugintervaldosagedefinition)
    {
        this.drugintervaldosagedefinition = drugintervaldosagedefinition;
    }

    public String getDrugIntervalDosageUnitNumb()
    {
        return drugintervaldosageunitnumb;
    }

    public void setDrugIntervalDosageUnitNumb(String drugintervaldosageunitnumb)
    {
        this.drugintervaldosageunitnumb = drugintervaldosageunitnumb;
    }

    public String getDrugRecurreAdministration()
    {
        return drugrecurreadministration;
    }

    public void setDrugRecurreAdministration(String drugrecurreadministration)
    {
        this.drugrecurreadministration = drugrecurreadministration;
    }

    public String getDrugSeparateDosageNumb()
    {
        return drugseparatedosagenumb;
    }

    public void setDrugSeparateDosageNumb(String drugseparatedosagenumb)
    {
        this.drugseparatedosagenumb = drugseparatedosagenumb;
    }

    public String getDrugStructureDosageNumb()
    {
        return drugstructuredosagenumb;
    }

    public void setDrugStructureDosageNumb(String drugstructuredosagenumb)
    {
        this.drugstructuredosagenumb = drugstructuredosagenumb;
    }

    public String getDrugStructureDosageUnit()
    {
        return drugstructuredosageunit;
    }

    public void setDrugStructureDosageUnit(String drugstructuredosageunit)
    {
        this.drugstructuredosageunit = drugstructuredosageunit;
    }

    public String getDrugAdministrationRoute()
    {
        return drugadministrationroute;
    }

    public void setDrugAdministrationRoute(String drugadministrationroute)
    {
        this.drugadministrationroute = drugadministrationroute;
    }

    public String getDrugCharacterization()
    {
        return Commons.drugCharacter.get(drugcharacterization);
    }

    public void setDrugCharacterization(String drugcharacterization)
    {
        this.drugcharacterization = drugcharacterization;
    }

    public String getDrugDosageText()
    {
        if(drugdosagetext == null)
            return "Not available";
        return drugdosagetext;
    }

    public void setDrugDosageText(String drugdosagetext)
    {
        this.drugdosagetext = drugdosagetext;
    }

    public String getDrugEndDate()
    {
        return drugenddate;
    }

    public void setDrugEndDate(String drugenddate)
    {
        this.drugenddate = drugenddate;
    }

    public String getDrugStartDate()
    {
        return drugstartdate;
    }

    public void setDrugStartDate(String drugstartdate)
    {
        this.drugstartdate = drugstartdate;
    }

    public String getDrugIndication()
    {
        return drugindication;
    }

    public void setDrugIndication(String drugindication)
    {
        this.drugindication = drugindication;
    }

    public String getDrugTreatmentDuration()
    {
        return drugtreatmentduration;
    }

    public void setDrugTreatmentDuration(String drugtreatmentduration)
    {
        this.drugtreatmentduration = drugtreatmentduration;
    }

    public String getDrugTreatmentDurationUnit()
    {
        return drugtreatmentdurationunit;
    }

    public void setDrugTreatmentDurationUnit(String drugtreatmentdurationunit)
    {
        this.drugtreatmentdurationunit = drugtreatmentdurationunit;
    }

    public String getMedicinalProduct()
    {
        return medicinalproduct;
    }

    public void setMedicinalProduct(String medicinalproduct)
    {
        this.medicinalproduct = medicinalproduct;
    }
}
