package com.example.kritik.dralt.Pojo;

import com.example.kritik.dralt.Utility.Commons;

import java.io.Serializable;

/**
 * Created by Kritik on 08-Apr-17.
 */

public class Reaction implements Serializable
{
    private String reactionmeddrapt;    //patient reaction
    private String reactionoutcome;

    public String getReactionMedDrapt()
    {
        return reactionmeddrapt;
    }

    public void setReactionMedDrapt(String reactionmeddrapt)
    {
        this.reactionmeddrapt = reactionmeddrapt;
    }

    public String getReactionOutcome()
    {
        return Commons.outcomes.get(reactionoutcome);
    }

    public void setReactionOutcome(String reactionoutcome)
    {
        this.reactionoutcome = reactionoutcome;
    }
}
