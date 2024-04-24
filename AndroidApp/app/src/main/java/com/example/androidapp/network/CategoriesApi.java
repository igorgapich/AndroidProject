package com.example.androidapp.network;

import com.example.androidapp.dto.category.CategoryItemDTO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface CategoriesApi {
    @GET("/api/Category/CategoryGetAsync")
    public Call<List<CategoryItemDTO>> list();

    @Multipart
    @POST("/api/category/createcategory")
    public Call<Void> create(@PartMap Map<String, RequestBody> params,
                             @Part MultipartBody.Part image);

    @DELETE("/api/category/DeleteCategoryByID/{id}")
    public Call<Void> delete(@Path("id") int id);
}