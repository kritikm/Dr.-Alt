package com.example.kritik.dralt.Pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kritik on 08-Apr-17.
 */

public class AdverseApiResponse implements Serializable
{
    private ArrayList<AdverseEffectResult> results;

    public ArrayList<AdverseEffectResult> getResults()
    {
        return results;
    }

    public void setResults(ArrayList<AdverseEffectResult> results)
    {
        this.results = results;
    }
}
