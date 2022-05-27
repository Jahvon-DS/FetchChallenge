package com.example.fetch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {



    private Context mContext;
    private List<ItemsListClass> mData;

    public Adaptery(Context mContext, List<ItemsListClass> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.itemslist, parent, false);

        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());
        holder.listId.setText(mData.get(position).getListId());
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView id;
        TextView name;
        TextView listId;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            listId = itemView.findViewById(R.id.listId);
        }
    }
}
