package com.example.pixabaystudio.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixabaystudio.R;
import com.example.pixabaystudio.model.ImagesPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter {
    List<ImagesPojo> imagesPojos;

    public ImagesAdapter(List<ImagesPojo> imagesPojos) {
        this.imagesPojos = imagesPojos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_view,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1=(ViewHolder)holder;
        ImagesPojo pojo1= imagesPojos.get(position);
        String tag=pojo1.getTags();
        String img=pojo1.getWebformatURL();
        holder1.textView.setText(tag);
        Picasso.get().load(img).into(holder1.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesPojos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView);
            this.imageView=view.findViewById(R.id.imageView);
        }
    }
}