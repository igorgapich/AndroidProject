package com.example.androidapp.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidapp.R;
import com.example.androidapp.constants.Urls;
import com.example.androidapp.dto.category.CategoryItemDTO;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {
    private List<CategoryItemDTO> items;

    private final OnItemClickListener deleteCategory;

    public CategoriesAdapter(List<CategoryItemDTO> items, OnItemClickListener deleteCategory) {
        this.items = items;
        this.deleteCategory = deleteCategory;
    }

    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCardViewHolder holder, int position) {
        if(items!=null && position<items.size()) {
            CategoryItemDTO item = items.get(position);
            holder.getCategoryName().setText(item.getName());
            holder.getCategoryDescription().setText(item.getDescription());
            String url = "https://t4.ftcdn.net/jpg/00/98/24/45/360_F_98244593_cdLTsTEBzLpC1DdPnkYFeowosQpKGqFi.jpg";
            if(item.getImagePath()!=null) {
                url = Urls.BASE+"/images/320_"+item.getImagePath();
            }
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    //.apply(new RequestOptions().override(400))
                    .into(holder.getIvCategoryImage());

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCategory.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}