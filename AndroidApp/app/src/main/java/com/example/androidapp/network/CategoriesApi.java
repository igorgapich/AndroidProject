package com.example.androidapp.network;

import com.example.androidapp.dto.category.CategoryItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesApi {
    // CALL це асинхронний метод
    // відправляє на сервер ліст
    @GET("api/CategoryControllers/CategoryGetAsync")
    public Call<List<CategoryItemDTO>> list();
}
