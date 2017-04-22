package com.example.kritik.dralt.Adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kritik.dralt.Interfaces.RecyclerItemClickListener;
import com.example.kritik.dralt.Pojo.AdverseEffectResult;
import com.example.kritik.dralt.Pojo.Drug;
import com.example.kritik.dralt.Pojo.Reaction;
import com.example.kritik.dralt.R;
import com.example.kritik.dralt.Utility.Commons;

import java.util.ArrayList;

/**
 * Created by Kritik on 09-Apr-17.
 */

public class AdverseResponseAdapter extends RecyclerView.Adapter<AdverseResponseViewHolder>
{
    private ArrayList<AdverseEffectResult> results;
    private String drugSoughtFor;
    private Activity onActivity;
    private RecyclerItemClickListener listener;

    public AdverseResponseAdapter(ArrayList<AdverseEffectResult> results, String drugSoughtFor, Activity onActivity, RecyclerItemClickListener listener)
    {
        this.results = results;
        this.drugSoughtFor = drugSoughtFor;
        this.onActivity = onActivity;
        this.listener = listener;
    }

    @Override
    public AdverseResponseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adverse_effect, parent, false);
        return new AdverseResponseViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(final AdverseResponseViewHolder holder, int position)
    {
        String reactions = "";
        AdverseEffectResult result = results.get(position);
        ArrayList<Reaction> reactionsList = result.getPatient().getReaction();

        int listSize = reactionsList.size();
        for(int i = 0; i < listSize; i++)
        {
            reactions += reactionsList.get(i).getReactionMedDrapt();
            if(i < listSize - 1)
                reactions += ", ";
        }

        if(reactions.length() > Commons.MAX_STRING_DISP)
            reactions = reactions.substring(0, Commons.MAX_STRING_DISP) + "...";
        holder.reactions.setText(reactions);

        if(result.getSerious().equals("YES"))
        {
            if(result.getSeriousnessDeath() != null)
            {
                holder.riskIndicator.setBackgroundColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.red));
                holder.seriousness.setText(onActivity.getString(R.string.seriousness_death));
            }
            else if(result.getSeriousnessLifeThreatening() != null)
            {
                holder.riskIndicator.setBackgroundColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.dark_orange));
                holder.seriousness.setText(onActivity.getString(R.string.seriousness_life_threatening));
            }
            else if(result.getSeriousnessDisabling() != null)
            {
                holder.riskIndicator.setBackgroundColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.orange));
                holder.seriousness.setText(onActivity.getString(R.string.seriousness_disability));
            }
            else
            {
                holder.riskIndicator.setBackgroundColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.yellow));
                if (result.getSeriousnessHospitalization() != null)
                    holder.seriousness.setText(onActivity.getString(R.string.seriousness_hospitalisation));
                else
                    holder.seriousness.setText(onActivity.getString(R.string.seriousness_other));
            }
        }
        else
        {
            holder.riskIndicator.setBackgroundColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.green));
            holder.seriousness.setText(onActivity.getString(R.string.seriousness_none));
        }

        ArrayList<Drug> drugs = result.getPatient().getDrug();
        String suspected = "NA";

        holder.additionalLayout.setVisibility(View.VISIBLE);
        holder.additionalDrug.setTextColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.color_default_text));
        holder.suspectedDrug.setTextColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.color_default_text));

        for(Drug drug : drugs)
        {
            String character = drug.getDrugCharacterization();
            character = character.substring(0, character.indexOf(' '));
            if(character.equals("Suspect"))
            {
                suspected = drug.getMedicinalProduct();
                if(suspected.toLowerCase().contains(drugSoughtFor.toLowerCase()))
                {
                    holder.additionalLayout.setVisibility(View.GONE);
                    holder.suspectedDrug.setTextColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.red));
                    break;
                }
            }
            else if(drugSoughtFor.toLowerCase().contains(drug.getMedicinalProduct().toLowerCase()))
            {
                holder.additionalType.setText(character);
                holder.additionalDrug.setText((drug.getMedicinalProduct()));
                holder.additionalDrug.setTextColor(ContextCompat.getColor(onActivity.getBaseContext(), R.color.dark_orange));
            }
        }
        holder.suspectedDrug.setText(suspected);
    }

    @Override
    public int getItemCount() { return results.size(); }
}
