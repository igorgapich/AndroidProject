package com.example.androidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidapp.category.CategoriesAdapter;
import com.example.androidapp.dto.category.CategoryItemDTO;
import com.example.androidapp.services.ApplicationNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    RecyclerView rcCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        ImageView ivAvatar = findViewById(R.id.imageView);
//        String url = "https://i.pinimg.com/564x/a8/4b/94/a84b94d816597cea9c83cb64d9d7e0e7.jpg";
//        String url = "http://10.0.2.2:5094/images/1.jpg";
        String url = "https://pv116.itstep.click/images/1.jpg";
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().override(400))
                .into(ivAvatar);
*/

        rcCategories = findViewById(R.id.rcCategories);
        rcCategories.setHasFixedSize(true);
        rcCategories.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));

        loadList();
    }
    void loadList() {
        ApplicationNetwork
                .getInstance()
                .getCategoriesApi()
                .list()
                .enqueue(new Callback<List<CategoryItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                        List<CategoryItemDTO> items = response.body();
                        //Log.d("--List categories--", String.valueOf(items.size()));
                        CategoriesAdapter ca = new CategoriesAdapter(items, MainActivity.this::onClickDeleteCategory);
                        rcCategories.setAdapter(ca);
                    }
                    @Override
                    public void onFailure(Call<List<CategoryItemDTO>> call, Throwable throwable) {

                    }
                });
    }

    private void onClickDeleteCategory(CategoryItemDTO category) {
        //Toast.makeText(this, category.getName(), Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Видалити "+ category.getName()+"?")
                .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApplicationNetwork.getInstance()
                                .getCategoriesApi()
                                .delete(category.getId())
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful()) {
                                            loadList();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable throwable) {

                                    }
                                });

                    }
                })
                .setNegativeButton("Ні", null) // No action when user clicks No
                .show();
    }
}