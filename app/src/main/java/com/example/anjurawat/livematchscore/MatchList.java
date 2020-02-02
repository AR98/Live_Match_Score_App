package com.example.anjurawat.livematchscore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchList {
    String team1,team2,matchstatus,datee,uniquId,matchtype;
  MatchList(String uniquId, String team1, String team2, String matchstatus,String matchType, String date ){
      this.datee=date;
      this.team1=team1;
      this.team2=team2;
      this.matchstatus=matchstatus;
      this.matchtype=matchType;
      this.uniquId=uniquId;
  }

    public String getUniquId() {
        return uniquId;
    }

    public void setUniquId(String uniquId) {
        this.uniquId = uniquId;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getMatchstatus() {
        return matchstatus;
    }

    public void setMatchstatus(String matchstatus) {
        this.matchstatus = matchstatus;
    }

    public String getDate() {
        return datee;
    }

    public String getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype;
    }

    public void setDate(String date) {
        this.datee = date;
    }
}


