package com.winterbitegames.demoschoolnotice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NoticeDataModel> dataModelList;
    private NoticeDataModel noticeData;
    private OnItemClickListener onItemClickListener;

    public CustomAdapter (Context context, ArrayList<NoticeDataModel> dataModelList, OnItemClickListener clickListener) {
        this.context = context;
        this.dataModelList = dataModelList;
        this.onItemClickListener = clickListener;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        noticeData = dataModelList.get(holder.getAdapterPosition());
        holder.tv_title.setText(noticeData.getTitle());
        holder.tv_time.setText(noticeData.getDate());
        holder.cardView.setOnClickListener(v -> onItemClickListener.clickOnItem(0,holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return this.dataModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_time;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_date);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}