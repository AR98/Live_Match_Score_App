package com.example.anjurawat.livematchscore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter  extends  RecyclerView.Adapter<MyAdapter.AdapterHolder>{
   private Context context;
   private List<MatchList> matchlist;
   MyAdapter(Context context,List<MatchList> list){
       this.context=context;
       matchlist=list;
   }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_layout,parent,false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
    final MatchList matchList=matchlist.get(position);
    holder.team1tv.setText(matchList.getTeam1());
        holder.team2tv.setText(matchList.getTeam2());
        holder.mstatustv.setText(matchList.getMatchstatus());
        holder.mtype.setText(matchList.getMatchtype());
        holder.datetv.setText(matchList.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String id=matchList.getUniquId();
                 final String date=matchList.getDate();
                String[] option={"Match Detail","Players List"};
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getRootView().getContext());
                builder.setTitle("Choose Option");
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    if(which==0){
                        Intent intent=new Intent(context,MachDetailActivity.class);
                        intent.putExtra("match_id",id);
                        intent.putExtra("date",date);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    if(which==1){
                        Intent intent=new Intent(context,PlayerDetail.class);
                        intent.putExtra("match_id",id);
                       // intent.putExtra("date",date);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    }
                });
                builder.create().show();


            }
        });

    }

    @Override
    public int getItemCount() {
       int a=0;
       if(matchlist.size()!=0)
        return  matchlist.size();
       else
           return  a;
    }



    public class AdapterHolder extends RecyclerView.ViewHolder{
     TextView team1tv,team2tv,datetv,mstatustv,mtype;
    CardView cardView;
        public AdapterHolder(View itemView) {
            super(itemView);
        team1tv= itemView.findViewById(R.id.team1tv);
           team2tv= itemView.findViewById(R.id.team2tv);
       datetv=itemView.findViewById(R.id.datetv);
          mstatustv= itemView.findViewById(R.id.matchStatustv);
           mtype=(TextView)itemView.findViewById(R.id.matchTypetv);
               cardView=itemView.findViewById(R.id.cardview);

        }
    }
}
