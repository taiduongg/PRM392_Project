package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page); // layout đã có NestedScrollView + BottomNav

        // Ánh xạ RecyclerView
        rvCategories = findViewById(R.id.rvCategories);
        rvProducts = findViewById(R.id.rvProducts);

        // Tạo dữ liệu danh mục
        categoryList = getCategoryData();

        // Khởi tạo Adapter cho Category
        categoryAdapter = new CategoryAdapter(this, categoryList, category -> {
            productAdapter.setProductList(category.getProducts());
        });

        // Thiết lập RecyclerView Category (ngang)
        rvCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCategories.setAdapter(categoryAdapter);

        // Khởi tạo danh sách Product ban đầu
        currentProductList = new ArrayList<>();

        // Adapter Product
        productAdapter = new ProductAdapter(this, currentProductList);

        // Thiết lập RecyclerView Product (grid 2 cột)
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvProducts.setNestedScrollingEnabled(false); // để scroll mượt trong NestedScrollView
        rvProducts.setHasFixedSize(true);
        rvProducts.setAdapter(productAdapter);
    }

    private List<Category> getCategoryData() {
        List<Category> list = new ArrayList<>();

        List<Product> robotProducts = new ArrayList<>();
        robotProducts.add(new Product("Robot A", R.drawable.robot, 99.99));
        robotProducts.add(new Product("Robot B", R.drawable.robot, 89.99));
        robotProducts.add(new Product("Robot B", R.drawable.robot, 89.99));
        robotProducts.add(new Product("Robot B", R.drawable.robot, 89.99));
        robotProducts.add(new Product("Robot B", R.drawable.robot, 89.99));

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
