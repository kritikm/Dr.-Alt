package com.example.kritik.dralt.Utility;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;

/**
 * Created by Kritik on 08-Apr-17.
 */

public class Commons
{
    public static final String OPEN_FDA = "https://api.fda.gov/";
    public static final String ENDPOINT_DRUGS = OPEN_FDA + "drug/";
    public static final String API_ADVERSE_EFFECTS = ENDPOINT_DRUGS + "event.json";
    public static final String SEARCH_MEDICINAL_PRODUCT = "patient.drug.medicinalproduct";
    public static final String EXACT_SEARCH_MEDICINAL_PRODUCT = SEARCH_MEDICINAL_PRODUCT + ".exact";

    public static final int LOAD_AT_ONCE = 25;      //number of results to load at once

    public static final int MAX_STRING_DISP = 40;   //maximum characters to be displayed at once in a string
    public static final int MAX_STRING_DISP_TITLE = 35;

    public static final String dateFormat = "YYYYMMDD";

    /****************To map integer codes to their string counterparts****************/

    public static HashMap<String, String> outcomes;           //Reaction Outcomes
    public static HashMap<String, String> intervals;          //Time Unit
    public static HashMap<String, String> sex;                //Patient's Sex
    public static HashMap<String, String> action;             //Action Take With Drug
    public static HashMap<String, String> yesNo;              //1: Yes    2: No   3: Does Not Apply
    public static HashMap<String, String> doseUnit;           //Dosage Unit
    public static HashMap<String, String> dosageRoute;        //Drug's Route of Administration
    public static HashMap<String, String> drugCharacter;      //Whether the drug is the suspect or not

    /*********************************************************************************/

    public Commons()
    {
        initialiseMaps();
    }

    private void initialiseMaps()
    {
        initialiseOutcomes();
        initialiseIntervals();
        initialiseSex();
        initialiseAction();
        initialiseYesNo();
        initialiseDoseUnits();
        initialiseDosageRoute();
        initialiseDrugCharacter();
    }

    public static void changeWindowColor(Activity activity, int color)
    {
        /************************Change color of status bar*************************/
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(color);
        /***************************************************************************/

    }
    private void initialiseOutcomes()
    {
        outcomes = new HashMap<>();
        outcomes.put("1", "Recovered/Resolved");
        outcomes.put("2", "Recovering/Resolving");
        outcomes.put("2", "Not Recovered/Not Resolved");
        outcomes.put("4", "Recovered/Resolved with consequent health issues");
        outcomes.put("5", "Fatal");
        outcomes.put("6", "Unknown");
    }

    private void initialiseIntervals()
    {
        intervals = new HashMap<>();
        intervals.put("800", "Decade");
        intervals.put("801", "Year");
        intervals.put("802", "Month");
        intervals.put("803", "Week");
        intervals.put("804", "Day");
        intervals.put("805", "Hour");
        intervals.put("806", "Minute");
        intervals.put("807", "Trimester");
        intervals.put("810", "Cyclical");
        intervals.put("811", "Trimester");
        intervals.put("812", "As Necessary");
        intervals.put("813", "Total");
    }

    private void initialiseSex()
    {
        sex = new HashMap<>();
        sex.put("0", "Unknown");
        sex.put("1", "Male");
        sex.put("2", "Female");
    }

    private void initialiseAction()
    {
        action = new HashMap<>();
        action.put("1", "Drug Withdrawn");
        action.put("2", "Dose Reduced");
        action.put("3", "Dose Increased");
        action.put("4", "Dose Not Changed");
        action.put("5", "Unknown");
        action.put("6", "Not Applicable");
    }

    private void initialiseYesNo()
    {
        yesNo = new HashMap<>();
        yesNo.put("1", "YES");
        yesNo.put("2", "NO");
        yesNo.put("3", "UNKNOWN");
    }

    private void initialiseDoseUnits()
    {
        doseUnit = new HashMap<>();
        doseUnit.put("001", "kilograms");
        doseUnit.put("002", "grams");
        doseUnit.put("003", "milligrams");
        doseUnit.put("004", "micrograms");
    }

    private void initialiseDosageRoute()
    {
        dosageRoute = new HashMap<>();
        dosageRoute.put("001", "Auricular");
    }

    private void initialiseDrugCharacter()
    {
        drugCharacter = new HashMap<>();
        drugCharacter.put("1", "Suspect (the drug was considered by the reporter to be the cause)");
        drugCharacter.put("2", "Concomitant (the drug was reported as being taken along with the suspect drug)");
        drugCharacter.put("3", "Interacting (the drug was considered by the reporter to have interacted with the suspect drug)");
    }
}
