package com.example.tugas4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> products;
    private OnProductClickListener listener;

    // Interface untuk onClick dengan 2 method
    public interface OnProductClickListener {
        void onBuyClick(Product product);
        void onProductClick(Product product);
    }

    public ProductAdapter(Context context, List<Product> products, OnProductClickListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productStock.setText("Stok: " + product.getStock());
        holder.productImage.setImageResource(product.getImageResource());

        // Ubah warna stok dan status tombol berdasarkan jumlah
        if (product.getStock() == 0) {
            // Stok habis - Merah
            holder.productStock.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
            holder.buyButton.setEnabled(false);
            holder.buyButton.setAlpha(0.5f);
            holder.buyButton.setText("STOK HABIS");
        } else if (product.getStock() <= 5) {
            // Stok menipis - Orange
            holder.productStock.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            holder.buyButton.setEnabled(true);
            holder.buyButton.setAlpha(1.0f);
            holder.buyButton.setText("🛒 BELI SEKARANG");
        } else {
            // Stok aman - Hijau
            holder.productStock.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
            holder.buyButton.setEnabled(true);
            holder.buyButton.setAlpha(1.0f);
            holder.buyButton.setText("🛒 BELI SEKARANG");
        }

        // Click listener untuk tombol beli
        holder.buyButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBuyClick(product);
            }
        });

        // Click listener untuk card/item produk (gambar dan card)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(product);
            }
        });

        // Click listener khusus untuk gambar produk
        holder.productImage.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateProducts(List<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView productStock;
        Button buyButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productStock = itemView.findViewById(R.id.productStock);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}