package com.example.kritik.dralt.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.kritik.dralt.Pojo.AdverseEffectResult;
import com.example.kritik.dralt.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<AdverseEffectResult> results = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /************************Change color of status bar*************************/
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        /***************************************************************************/

        /************************Initialise toolbar************************/
        View searchTool = getLayoutInflater().inflate(R.layout.search_toolbar, null);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.addView(searchTool);
        toolbar.setContentInsetsAbsolute(0, 0);

        TextView startSearch = (TextView)searchTool.findViewById(R.id.tv_search_starter);

        //Click listener for search text view
        startSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent goToSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(goToSearch);
            }
        });
        /******************************************************************/
    }
}
