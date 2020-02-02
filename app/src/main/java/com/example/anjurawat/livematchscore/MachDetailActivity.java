package com.example.anjurawat.livematchscore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.zip.Inflater;

public class MachDetailActivity extends AppCompatActivity {
 private String url="https://cricapi.com/api/cricketScore/?apikey=drEf4xUfyLbnn1L8NzX4tS8yMOJ3&unique_id=";
TextView mteam1tv,mteam2tv,mscore,mdescription,mdate,mstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mach_detail);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Match Details");


        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        String id=intent.getStringExtra("match_id");
        String date=intent.getStringExtra("date");
          url=url+id;

          mteam1tv=(TextView)findViewById(R.id.team1tv);
          mteam2tv=(TextView)findViewById(R.id.team2tv);
        mscore=(TextView)findViewById(R.id.matchScoretv);
        mdescription=(TextView)findViewById(R.id.descriptiontv);
        mdate=(TextView)findViewById(R.id.datetv);
        mstatus=(TextView)findViewById(R.id.matchStatustv);

        mdate.setText(date);
        loadDate();
    }

    private void loadDate() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String team1=jsonObject.getString("team-1");
                    String team2=jsonObject.getString("team-2");
                    String matchstatus=jsonObject.getString("matchStarted");
                    if(matchstatus.equals("true"))
                        matchstatus="Match Started";
                    else
                        matchstatus="Match Not Started";
                    mteam1tv.setText(team1);
                    mteam2tv.setText(team2);
                    mstatus.setText(matchstatus);

                try {

                    String score=jsonObject.getString("score");
                    String description=jsonObject.getString("description");

                    mscore.setText(score);
                    mdescription.setText(description);

                }catch (Exception e){

                    Toast.makeText(MachDetailActivity.this,"error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }


                }catch (Exception e){

                    Toast.makeText(MachDetailActivity.this,"error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MachDetailActivity.this,"error"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue=Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
