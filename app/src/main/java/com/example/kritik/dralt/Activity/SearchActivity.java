package com.example.kritik.dralt.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kritik.dralt.Adapter.AdverseResponseAdapter;
import com.example.kritik.dralt.Interfaces.RecyclerItemClickListener;
import com.example.kritik.dralt.Pojo.AdverseApiResponse;
import com.example.kritik.dralt.Pojo.AdverseEffectResult;
import com.example.kritik.dralt.R;
import com.example.kritik.dralt.Utility.Commons;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements RecyclerItemClickListener
{

    private EditText searchBox = null;  //search box from search toolbar
    private OkHttpClient client = null;
    private int loaded = 0;             //number of results already shown
    private RecyclerView resultsRecycler;
    private ProgressBar searchProgress;
    private TextView noResults;
    private AdverseResponseAdapter adverseResponseAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<AdverseEffectResult> results;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchProgress = (ProgressBar)findViewById(R.id.pb_search_progress);
        noResults = (TextView)findViewById(R.id.tv_no_result);

        Commons.changeWindowColor(this, ContextCompat.getColor(this, R.color.colorPrimary));

        /************************Initialise toolbar************************/
        View searchTool = getLayoutInflater().inflate(R.layout.search_toolbar, null);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.addView(searchTool);
        toolbar.setContentInsetsAbsolute(0, 0);

        //Hide the dummy search box (text view)
        TextView dummySearch = (TextView)searchTool.findViewById(R.id.tv_search_starter);
        dummySearch.setVisibility(View.GONE);

        //Show the real search box (edit text)
        searchBox = (EditText)searchTool.findViewById(R.id.et_search_text);
        searchBox.setVisibility(View.VISIBLE);

        //set action listener to search box
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i == EditorInfo.IME_ACTION_SEARCH)
                {
                    InputMethodManager inputMethodManager = (InputMethodManager)getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    search();
                    return true;
                }
                return false;
            }
        });
        /******************************************************************/

        //setup the okhttp client
        client = new OkHttpClient();

        resultsRecycler = (RecyclerView)findViewById(R.id.rv_adverse_effects);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultsRecycler.setLayoutManager(layoutManager);
    }

    private void search()
    {
        resultsRecycler.setVisibility(View.GONE);
        searchProgress.setVisibility(View.VISIBLE);
        noResults.setVisibility(View.GONE);

        loaded = 0;
        final String searchParam = searchBox.getText().toString();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Commons.API_ADVERSE_EFFECTS).newBuilder();
        urlBuilder.addQueryParameter("limit", String.valueOf(Commons.LOAD_AT_ONCE));
        urlBuilder.addQueryParameter("skip", String.valueOf(loaded));

        String url = urlBuilder.build().toString();

        url = url + addSearchTerm(Commons.EXACT_SEARCH_MEDICINAL_PRODUCT, searchParam);

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String responseBody = response.body().string();

                AdverseApiResponse adverseApiResponse = new Gson().fromJson(responseBody, AdverseApiResponse.class);

                results = adverseApiResponse.getResults();

                //setup the recycler view here
                SearchActivity.this.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if(results == null)
                            noResults.setVisibility(View.VISIBLE);
                        else
                        {
                            noResults.setVisibility(View.GONE);
                            resultsRecycler.setVisibility(View.VISIBLE);
                            loaded += results.size();

                            adverseResponseAdapter = new AdverseResponseAdapter(
                                    results,
                                    searchParam,
                                    SearchActivity.this,    //Activity reference
                                    SearchActivity.this);   //RecyclerItemClickListener reference
                            resultsRecycler.setAdapter(adverseResponseAdapter);
                        }
                        searchProgress.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    /**
     * <h1>addSeachTerm</h1>
     * <h2>appends a search parameter to a url</h2>
     * @param searchObject  the object on which the search is to be made
     * @param searchParam   the search parameter
     * @return              returns a string of the form &search=searchObject:"searchParam"
     */
    private String addSearchTerm(final String searchObject, final String searchParam)
    {
        return "&search=" + searchObject + ":" + "\"" + searchParam.toUpperCase() + "\"";
    }

    @Override
    public void onItemClick(View view, int position, int clickType)
    {
        Log.d("CLICK", "Position " + position);
        Intent showDetails = new Intent(this, AdverseDetailsActivity.class);
        showDetails.putExtra("result", results.get(position));
        startActivity(showDetails);
    }
}
