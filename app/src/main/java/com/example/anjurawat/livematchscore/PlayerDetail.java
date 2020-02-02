package com.example.anjurawat.livematchscore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayerDetail extends AppCompatActivity {
private String url="https://cricapi.com/api/fantasySquad?apikey=drEf4xUfyLbnn1L8NzX4tS8yMOJ3&unique_id=";
       TextView team1name,team2name,team1player,team2player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Match Details");


        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        String id=intent.getStringExtra("match_id");
        //String date=intent.getStringExtra("date");
        url=url+id;

        team1name=(TextView)findViewById(R.id.team1name);
        team2name=(TextView)findViewById(R.id.team2name);
        team1player=(TextView)findViewById(R.id.team1playern);
        team2player=(TextView)findViewById(R.id.team2playern);

        loadPlayes();
    }

    private void loadPlayes() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONObject(response).getJSONArray("squad");

                    JSONObject json0=jsonArray.getJSONObject(0);
                    JSONObject json1=jsonArray.getJSONObject(1);

                    String team1n=json0.getString("name");
                    String team2n=json1.getString("name");

                   JSONArray team1list=json0.getJSONArray("players");
                   JSONArray team2list=json1.getJSONArray("players");

                   team1name.setText(team1n);
                   team2name.setText(team2n);

                   for(int i=0;i<team1list.length();i++){
                       String player1=team1list.getJSONObject(i).getString("name");
                       team1player.append((i+1)+" "+player1+"\n");
                   }

                    for(int i=0;i<team2list.length();i++){
                        String player2=team2list.getJSONObject(i).getString("name");
                        team2player.append((i+1)+" "+player2+"\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PlayerDetail.this,"error"+error.getMessage(),Toast.LENGTH_SHORT).show();
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
