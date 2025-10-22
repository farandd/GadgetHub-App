package com.example.tugas4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvHp, rvLaptop, rvHeadset;

    private List<Product> allProducts;

    private ViewPager2 viewPagerPromo;
    private PromoAdapter promoAdapter;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerPromo = findViewById(R.id.viewPagerPromo);

        // List gambar promo (drawable)
        List<Integer> promoImages = Arrays.asList(
                R.drawable.promo1,
                R.drawable.promo2,
                R.drawable.promo4
        );

        promoAdapter = new PromoAdapter(this, promoImages);
        viewPagerPromo.setAdapter(promoAdapter);

        // Auto slide tiap 3 detik
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == promoAdapter.getItemCount()) {
                    currentPage = 0;
                }
                viewPagerPromo.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);

        // Dots indicator
        DotsIndicator dotsIndicator = findViewById(R.id.dotsIndicator);
        dotsIndicator.attachTo(viewPagerPromo);

        initViews();
        setupDummyData();
        setupRecyclerViews();
    }

    private void initViews() {
        rvHp = findViewById(R.id.recyclerView1);
        rvLaptop = findViewById(R.id.recyclerView2);
        rvHeadset = findViewById(R.id.recyclerView3);
    }

    private void setupDummyData() {
        allProducts = new ArrayList<>();

        // HP
        allProducts.add(new Product("Samsung Galaxy S24", "Rp 12.999.000", 15, "HP", R.drawable.samsungs24));
        allProducts.add(new Product("iPhone 15 Pro", "Rp 18.999.000", 8, "HP", R.drawable.iphone15pro));
        allProducts.add(new Product("Xiaomi 14", "Rp 8.999.000", 22, "HP", R.drawable.xiaomi14));
        allProducts.add(new Product("OPPO Find X7", "Rp 10.999.000", 12, "HP", R.drawable.oppofindx7));
        allProducts.add(new Product("Vivo X100 Pro", "Rp 9.999.000", 18, "HP", R.drawable.vivox100));

        // Laptop
        allProducts.add(new Product("MacBook Air M2", "Rp 18.999.000", 6, "Laptop", R.drawable.macbookm2));
        allProducts.add(new Product("ASUS ROG Strix", "Rp 25.999.000", 4, "Laptop", R.drawable.rogstrix));
        allProducts.add(new Product("Dell XPS 13", "Rp 22.999.000", 7, "Laptop", R.drawable.dellxps13));
        allProducts.add(new Product("HP Pavilion Gaming", "Rp 15.999.000", 10, "Laptop", R.drawable.hppavilion));
        allProducts.add(new Product("Lenovo ThinkPad X1", "Rp 28.999.000", 5, "Laptop", R.drawable.thinkpad));

        // Headset
        allProducts.add(new Product("Sony WH-1000XM5", "Rp 4.999.000", 25, "Headset", R.drawable.sony));
        allProducts.add(new Product("Bose QuietComfort", "Rp 4.299.000", 18, "Headset", R.drawable.bose));
        allProducts.add(new Product("AirPods Pro 2", "Rp 3.799.000", 30, "Headset", R.drawable.airpods));
        allProducts.add(new Product("SteelSeries Arctis 7P", "Rp 2.499.000", 15, "Headset", R.drawable.steelseries));
        allProducts.add(new Product("Razer BlackShark V2", "Rp 1.999.000", 20, "Headset", R.drawable.razer));
    }


    private void setupRecyclerViews() {
        // Filter data per kategori
        List<Product> hpProducts = filterProductsByCategory("HP");
        List<Product> laptopProducts = filterProductsByCategory("Laptop");
        List<Product> headsetProducts = filterProductsByCategory("Headset");

        // LayoutManager horizontal
        rvHp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvLaptop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHeadset.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Adapter dengan onClick lengkap
        ProductAdapter hpAdapter = new ProductAdapter(this, hpProducts, new ProductAdapter.OnProductClickListener() {
            @Override
            public void onBuyClick(Product product) {
                showBuyDialog(product);
            }

            @Override
            public void onProductClick(Product product) {
                showProductDetail(product);
            }
        });

        ProductAdapter laptopAdapter = new ProductAdapter(this, laptopProducts, new ProductAdapter.OnProductClickListener() {
            @Override
            public void onBuyClick(Product product) {
                showBuyDialog(product);
            }

            @Override
            public void onProductClick(Product product) {
                showProductDetail(product);
            }
        });

        ProductAdapter headsetAdapter = new ProductAdapter(this, headsetProducts, new ProductAdapter.OnProductClickListener() {
            @Override
            public void onBuyClick(Product product) {
                showBuyDialog(product);
            }

            @Override
            public void onProductClick(Product product) {
                showProductDetail(product);
            }
        });

        rvHp.setAdapter(hpAdapter);
        rvLaptop.setAdapter(laptopAdapter);
        rvHeadset.setAdapter(headsetAdapter);
    }

    private List<Product> filterProductsByCategory(String category) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getCategory().equals(category)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    // ==================== METHOD ONCLICK BARU ====================

    /**
     * Menampilkan dialog detail produk ketika card diklik
     */
    private void showProductDetail(Product product) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Detail Produk");

        String message = "Nama: " + product.getName() + "\n\n" +
                "Harga: " + product.getPrice() + "\n\n" +
                "Stok Tersedia: " + product.getStock() + " unit\n\n" +
                "Kategori: " + product.getCategory();

        builder.setMessage(message);
        builder.setPositiveButton("Beli Sekarang", (dialog, which) -> showBuyDialog(product));
        builder.setNegativeButton("Tutup", null);
        builder.show();
    }

    /**
     * Menampilkan dialog konfirmasi pembelian
     */
    private void showBuyDialog(Product product) {
        if (product.getStock() <= 0) {
            Toast.makeText(this, "Maaf, stok habis!", Toast.LENGTH_SHORT).show();
            return;
        }

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Pembelian");

        String message = "Apakah Anda yakin ingin membeli?\n\n" +
                product.getName() + "\n" +
                product.getPrice() + "\n\n" +
                "Stok tersedia: " + product.getStock() + " unit";

        builder.setMessage(message);
        builder.setPositiveButton("Ya, Beli", (dialog, which) -> processPurchase(product));
        builder.setNegativeButton("Batal", null);
        builder.show();
    }

    /**
     * Memproses pembelian produk
     */
    private void processPurchase(Product product) {
        // Kurangi stok
        product.setStock(product.getStock() - 1);

        // Update tampilan semua RecyclerView
        updateAllRecyclerViews();

        // Tampilkan pesan sukses
        String successMessage = "Berhasil membeli " + product.getName() + "!\n" +
                "Sisa stok: " + product.getStock();
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show();

        // Jika stok habis, tampilkan notifikasi tambahan
        if (product.getStock() == 0) {
            Toast.makeText(this, "Stok " + product.getName() + " telah habis!", Toast.LENGTH_SHORT).show();
        } else if (product.getStock() <= 5) {
            Toast.makeText(this, "Stok " + product.getName() + " tinggal " + product.getStock() + " unit!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Update semua RecyclerView setelah pembelian
     */
    @SuppressLint("NotifyDataSetChanged")
    private void updateAllRecyclerViews() {
        // Notify adapter untuk update tampilan
        if (rvHp.getAdapter() != null) {
            rvHp.getAdapter().notifyDataSetChanged();
        }
        if (rvLaptop.getAdapter() != null) {
            rvLaptop.getAdapter().notifyDataSetChanged();
        }
        if (rvHeadset.getAdapter() != null) {
            rvHeadset.getAdapter().notifyDataSetChanged();
        }
    }

    // ==================== END METHOD ONCLICK BARU ====================

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); // stop ketika keluar activity
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000); // jalan lagi ketika balik
    }
}