package com.example.anjurawat.livematchscore;


import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.example.anjurawat.livematchscore.R.id.recycler_view;

public class MainActivity extends AppCompatActivity {

    private String url = "https://cricapi.com/api/matches?apikey=drEf4xUfyLbnn1L8NzX4tS8yMOJ3";

 private List<MatchList> mlist;
 private  RecyclerView.Adapter mAdapter;
   private RecyclerView mrecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerView =(RecyclerView) findViewById(R.id.recycler_view);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mlist = new ArrayList<>();



        findUrl();
    }


        private void findUrl() {
            StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //  pd.dismiss();
                    Log.d("Code",response);

                try {
                    JSONArray jsonArray = new JSONObject(response).getJSONArray("matches");

                    for (int i = 0; i < jsonArray.length(); i++) {


                        String uniquId = jsonArray.getJSONObject(i).getString("unique_id");
                        String team1 = jsonArray.getJSONObject(i).getString("team-1");
                        String team2 = jsonArray.getJSONObject(i).getString("team-2");
                        String matchstatus = jsonArray.getJSONObject(i).getString("matchStarted");
                        String matchtype = jsonArray.getJSONObject(i).getString("type");
                        if (matchstatus.equals("true"))
                            matchstatus = "Match Started";
                        else
                            matchstatus = "Match not  Started";

                        String timegmt = jsonArray.getJSONObject(i).getString("dateTimeGMT");
                        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                        SimpleDateFormat destFormat = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a"); //here 'a' for AM/PM


                        Date date = null;
                        try {
                            date = sourceFormat.parse(timegmt);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String formattedDate = destFormat.format(date);


                        MatchList matchlist = new MatchList(uniquId, team1, team2, matchstatus, matchtype, formattedDate);

                        mlist.add(matchlist);


                    }

                    mAdapter=new MyAdapter(getApplicationContext(),mlist);

                    mrecyclerView.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }


    }


