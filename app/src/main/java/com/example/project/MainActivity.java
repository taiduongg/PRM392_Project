package com.example.project;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.adapters.CategoryAdapter;
import com.example.project.adapters.ProductAdapter;
import com.example.project.models.Category;
import com.example.project.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategories, rvProducts;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;
    private List<Category> categoryList;
    private List<Product> currentProductList;
    private ImageView imgAvatar;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page); // Layout có NestedScrollView + BottomNav

        // Ánh xạ view
        rvCategories = findViewById(R.id.rvCategories);
        rvProducts = findViewById(R.id.rvProducts);
        imgAvatar = findViewById(R.id.imgAvatar);
        etSearch = findViewById(R.id.etSearch);

        // Load danh sách danh mục và sản phẩm
        categoryList = getCategoryData();
        currentProductList = new ArrayList<>();

        // Adapter sản phẩm
        productAdapter = new ProductAdapter(this, currentProductList);
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvProducts.setNestedScrollingEnabled(false);
        rvProducts.setHasFixedSize(true);
        rvProducts.setAdapter(productAdapter);

        // Adapter danh mục
        categoryAdapter = new CategoryAdapter(this, categoryList, category -> {
            // Khi chọn danh mục, cập nhật sản phẩm
            currentProductList.clear();
            currentProductList.addAll(category.getProducts());
            productAdapter.setProductList(currentProductList);
        });

        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategories.setAdapter(categoryAdapter);

        // Set sản phẩm mặc định ban đầu là của danh mục đầu tiên (nếu có)
        if (!categoryList.isEmpty()) {
            currentProductList.addAll(categoryList.get(0).getProducts());
            productAdapter.setProductList(currentProductList);
        }

        // Xử lý avatar click
        imgAvatar.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, imgAvatar);
            popupMenu.getMenuInflater().inflate(R.menu.menu_profile, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.menu_profile) {
                    return true;
                } else if (id == R.id.menu_settings) {
                    return true;
                } else if (id == R.id.menu_logout) {
                    return true;
                }
                return false;
            });
            popupMenu.show();
        });

        // Tìm kiếm sản phẩm theo tên
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }
        });
    }

    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product p : currentProductList) {
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(p);
            }
        }
        productAdapter.setProductList(filteredList);
    }

    private List<Category> getCategoryData() {
        List<Category> list = new ArrayList<>();

        List<Product> robotProducts = new ArrayList<>();
        robotProducts.add(new Product("Robot A", R.drawable.robot, 99.99));
        robotProducts.add(new Product("Robot B", R.drawable.robot, 89.99));
        robotProducts.add(new Product("Robot C", R.drawable.robot, 79.99));

        List<Product> carProducts = new ArrayList<>();
        carProducts.add(new Product("Car A", R.drawable.car, 79.99));
        carProducts.add(new Product("Car B", R.drawable.car, 69.99));

        List<Product> planeProducts = new ArrayList<>();
        planeProducts.add(new Product("Plane A", R.drawable.plane, 109.99));

        List<Product> dinosaurProducts = new ArrayList<>();
        dinosaurProducts.add(new Product("Dino A", R.drawable.dinosaur, 59.99));

        List<Product> legoProducts = new ArrayList<>();
        legoProducts.add(new Product("Lego A", R.drawable.lego, 129.99));

        list.add(new Category("Robot", R.drawable.robot, robotProducts));
        list.add(new Category("Car", R.drawable.car, carProducts));
        list.add(new Category("Plane", R.drawable.plane, planeProducts));
        list.add(new Category("Dinosaur", R.drawable.dinosaur, dinosaurProducts));
        list.add(new Category("Lego", R.drawable.lego, legoProducts));

        return list;
    }
}
