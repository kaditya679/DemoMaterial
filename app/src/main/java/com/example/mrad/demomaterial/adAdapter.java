package com.example.mrad.demomaterial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by MR.AD on 026-Mar-3-26-2017.
 */

public class adAdapter extends RecyclerView.Adapter<adAdapter.MyViewHolder> {
    LayoutInflater inflater;
    Context context;
    List<SingleRow> data = Collections.emptyList();

    public adAdapter(Context context, List<SingleRow> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        SingleRow current = data.get(position);

        holder.title.setText(current.title);
        holder.icon.setImageResource(current.imageID);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void delete(int i) {
        data.remove(i);
        notifyItemRemoved(i);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listImage);
            title.setOnClickListener(this);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            delete(getPosition());
            //Toast.makeText(context, "item clicked! "+getPosition(), Toast.LENGTH_SHORT).show();


        }
    }
}

class SingleRow {

    int imageID;
    String title;

}
