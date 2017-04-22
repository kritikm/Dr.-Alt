package com.example.kritik.dralt.Pojo;

import com.example.kritik.dralt.Utility.Commons;

import java.io.Serializable;

/**
 * Created by Kritik on 08-Apr-17.
 */

public class AdverseEffectResult implements Serializable
{
    private int reporttype;
    private String occurcountry;
    private String receivedate;
    private Patient patient;
    private String serious;
    private String seriousnessdeath;                //present only when reaction resulted in death
    private String seriousnessdisabling;            //present only when reaction was disabling
    private String seriousnesshospitalization;      //present only when reaction resulted in a hospitalization
    private String seriousnesslifethreatening;      //present only when reaction resulted in a life threatening condition
    private String seriousnessother;                //present when none of the above

    public int getReportType()
    {
        return reporttype;
    }

    public void setReportType(int reporttype)
    {
        this.reporttype = reporttype;
    }

    public String getOccurCountry()
    {
        return occurcountry;
    }

    public void setOccurCountry(String occurcountry)
    {
        this.occurcountry = occurcountry;
    }

    public String getReceiveDate()
    {
        return receivedate;
    }

    public void setReceiveDate(String receivedate)
    {
        this.receivedate = receivedate;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public String getSeriousnessDeath()
    {
        return seriousnessdeath;
    }

    public void setSeriousnessDeath(String seriousnessdeath)
    {
        this.seriousnessdeath = seriousnessdeath;
    }

    public String getSeriousnessDisabling()
    {
        return Commons.yesNo.get(seriousnessdisabling);

    }

    public void setSeriousnessDisabling(String seriousnessdisabling)
    {
        this.seriousnessdisabling = seriousnessdisabling;
    }

    public String getSeriousnessLifeThreatening()
    {
        return Commons.yesNo.get(seriousnesslifethreatening);
    }

    public void setSeriousnessLifeThreatening(String seriousnesslifethreatening)
    {
        this.seriousnesslifethreatening = seriousnesslifethreatening;
    }

    public String getSerious()
    {
        return Commons.yesNo.get(serious);
    }

    public void setSerious(String serious)
    {
        this.serious = serious;
    }

    public String getSeriousnessHospitalization()
    {
        return Commons.yesNo.get(seriousnesshospitalization);
    }

    public void setSeriousnessHospitalization(String seriousnesshospitalization)
    {
        this.seriousnesshospitalization = seriousnesshospitalization;
    }

    public String getSeriousnessOther()
    {
        return Commons.yesNo.get(seriousnessother);
    }

    public void setSeriousnessOther(String seriousnessother)
    {
        this.seriousnessother = seriousnessother;
    }
}
