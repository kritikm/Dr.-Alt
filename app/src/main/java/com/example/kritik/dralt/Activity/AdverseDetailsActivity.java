package com.example.kritik.dralt.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kritik.dralt.Pojo.AdverseEffectResult;
import com.example.kritik.dralt.Pojo.Drug;
import com.example.kritik.dralt.Pojo.Patient;
import com.example.kritik.dralt.Pojo.Reaction;
import com.example.kritik.dralt.R;
import com.example.kritik.dralt.Utility.Commons;

import java.util.ArrayList;

public class AdverseDetailsActivity extends AppCompatActivity
{
    private AdverseEffectResult result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adverse_details);

        Intent receiving = getIntent();
        result = (AdverseEffectResult)receiving.getSerializableExtra("result");

        int seriousnessColor = getSeriousnessColor();
        setupToolbar(seriousnessColor);
        setupThreatText(seriousnessColor);
        setupPatientInfo();
        setupDrugInfo();
        setupOutcomes();
    }

    void setupToolbar(int titleColor)
    {
        Commons.changeWindowColor(this, titleColor);

        Toolbar titleBar = (Toolbar)findViewById(R.id.tb_title);
        View toolbarView = getLayoutInflater().inflate(R.layout.toolbar_regular, null);
        ImageView backButton = (ImageView) toolbarView.findViewById(R.id.iv_back);
        TextView title = (TextView)toolbarView.findViewById(R.id.tv_title);

        int red = ContextCompat.getColor(this, R.color.red);
        int orange = ContextCompat.getColor(this, R.color.orange);
        int dark_orange = ContextCompat.getColor(this, R.color.dark_orange);

        if(titleColor == red || titleColor == orange || titleColor == dark_orange)
        {
            title.setTextColor(ContextCompat.getColor(this, R.color.white));
            backButton.setBackground(ContextCompat.getDrawable(this, R.drawable.back_button_light_selector));
        }
        else
        {
            title.setTextColor(ContextCompat.getColor(this, R.color.black));
            backButton.setBackground(ContextCompat.getDrawable(this, R.drawable.back_button_dark_selector));
        }

        String titleText = "";
        ArrayList<Reaction> reactions = result.getPatient().getReaction();
        int listSize = reactions.size();
        for(int i = 0; i < listSize; i++)
        {
            titleText += reactions.get(i).getReactionMedDrapt();
            if(i < listSize - 1)
                titleText += ", ";
        }

        if(titleText.length() > Commons.MAX_STRING_DISP_TITLE)
            titleText = titleText.substring(0, Commons.MAX_STRING_DISP_TITLE) + "...";
        title.setText(titleText);


        titleBar.addView(toolbarView);
        titleBar.setContentInsetsAbsolute(0, 0);
        titleBar.setBackgroundColor(titleColor);

        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });

    }

    void setupThreatText(int seriousnessColor)
    {
        String threatText = getSeriousnessText();
        TextView threatView = (TextView) findViewById(R.id.tv_threat);
        threatView.setText(threatText);
        threatView.setBackgroundColor(seriousnessColor);

        int red = ContextCompat.getColor(this, R.color.red);
        int orange = ContextCompat.getColor(this, R.color.orange);
        int dark_orange = ContextCompat.getColor(this, R.color.dark_orange);

        Drawable info;
        if(seriousnessColor == red || seriousnessColor == orange || seriousnessColor == dark_orange)
        {
            threatView.setTextColor(ContextCompat.getColor(this, R.color.white));
            info = ContextCompat.getDrawable(this, R.drawable.vector_info_white);
        }
        else
        {
            threatView.setTextColor(ContextCompat.getColor(this, R.color.black));
            info = ContextCompat.getDrawable(this, R.drawable.vector_info_black);
        }

        threatView.setCompoundDrawablesWithIntrinsicBounds(info, null, null, null);


    }

    void setupPatientInfo()
    {
        String patientText = "";

        Patient patient = result.getPatient();

        patientText += "Age: " + patient.getPatientOnsetAge() + " " + patient.getPatientOnsetAgeUnit() + "\n";
        patientText += "Sex: " + patient.getPatientSex();

        TextView patientView = (TextView)findViewById(R.id.tv_patient_info);
        patientView.setText(patientText);
    }

    void setupOutcomes()
    {
        String outcomeText = "";

        for(Reaction reaction : result.getPatient().getReaction())
        {
            outcomeText += reaction.getReactionMedDrapt().toUpperCase() + "\n";
            outcomeText += "Outcome: " + reaction.getReactionOutcome() + "\n";

            //empty line between two reactions
            outcomeText += "\n";
        }

        //remove empty lines after the last reaction
        outcomeText = outcomeText.substring(0, outcomeText.length() - 2);

        TextView reactionView = (TextView)findViewById(R.id.tv_outcomes);
        reactionView.setText(outcomeText);
    }

    void setupDrugInfo()
    {
        String drugsText = "";

        for(Drug drug : result.getPatient().getDrug())
        {
            drugsText += drug.getMedicinalProduct().toUpperCase() + "\n";
            drugsText += "Characterisation: " + drug.getDrugCharacterization() + "\n";
            drugsText += "Dosage: " + drug.getDrugDosageText() + "\n";
            drugsText += "Action: " + drug.getActionDrug() + "\n";

            //empty line between two drugs
            drugsText += "\n";
        }

        //remove empty lines after last drug
        drugsText = drugsText.substring(0, drugsText.length() - 2);

        TextView drugsView = (TextView)findViewById(R.id.tv_drug_info);
        drugsView.setText(drugsText);
    }

    int getSeriousnessColor()
    {
        if(result.getSerious().equals("YES"))
        {
            if(result.getSeriousnessDeath() != null)
                return ContextCompat.getColor(this, R.color.red);
            else if(result.getSeriousnessLifeThreatening() != null)
                return ContextCompat.getColor(this, R.color.dark_orange);
            else if(result.getSeriousnessDisabling() != null)
                return ContextCompat.getColor(this, R.color.orange);
            else
                return ContextCompat.getColor(this, R.color.yellow);
        }
        return ContextCompat.getColor(this, R.color.green);
    }

    String getSeriousnessText()
    {
        if(result.getSerious().equals("YES"))
        {
            if(result.getSeriousnessDeath() != null)
                return getString(R.string.seriousness_death);
            else if(result.getSeriousnessLifeThreatening() != null)
                return getString(R.string.seriousness_life_threatening);
            else if(result.getSeriousnessDisabling() != null)
                return getString(R.string.seriousness_disability);
            else if(result.getSeriousnessHospitalization() != null)
                return getString(R.string.seriousness_hospitalisation);
            else
                return getString(R.string.seriousness_other);
        }
        return getString(R.string.seriousness_none);
    }

}
