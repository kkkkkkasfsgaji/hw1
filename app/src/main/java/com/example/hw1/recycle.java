package com.example.hw1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class recycle extends RecyclerView.Adapter<recycle.ViewHolder> {
    private List<scan> arrayList = new ArrayList<>();
    Button informa;
    public recycle(ArrayList<scan> arrayList) {
        this.arrayList = arrayList;
    }
    public void clearDevice(){
        this.arrayList.clear();
        notifyDataSetChanged();
    }
    /**若有不重複的裝置出現，則加入列表中*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAddress,tvRssi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textView_DeviceName);
            tvAddress = itemView.findViewById(R.id.textView_Address);
            tvRssi = itemView.findViewById(R.id.textView_Rssi);
            informa=itemView.findViewById(R.id.ButInfo);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview,parent,false);
        return new ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(arrayList.get(position).getDeviceName());
        holder.tvAddress.setText("裝置位址："+arrayList.get(position).getAddress());
        holder.tvRssi.setText("訊號強度："+arrayList.get(position).getRssi());
        informa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, information.class);
                Bundle bundle =new Bundle();
                bundle.putString("DN",arrayList.get(position).getDeviceName());
                bundle.putString("MA",arrayList.get(position).getAddress());
                bundle.putString("IN",arrayList.get(position).getRssi());
                intent.putExtras(bundle);
                context. startActivity(intent);
            }
        } );
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
