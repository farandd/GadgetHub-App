package com.example.tugas4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoViewHolder> {
    private List<Integer> promoImages;
    private Context context;

    public PromoAdapter(Context context, List<Integer> promoImages) {
        this.context = context;
        this.promoImages = promoImages;
    }

    @NonNull
    @Override
    public PromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new PromoViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoViewHolder holder, int position) {
        holder.imageView.setImageResource(promoImages.get(position));
    }

    @Override
    public int getItemCount() {
        return promoImages.size();
    }

    public static class PromoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PromoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}

