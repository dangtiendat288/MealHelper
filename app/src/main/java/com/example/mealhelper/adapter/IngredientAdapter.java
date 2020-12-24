package com.example.mealhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealhelper.R;
import com.example.mealhelper.model.Ingredient;
import com.example.mealhelper.model.Meal;

public class IngredientAdapter extends ListAdapter<Ingredient, IngredientAdapter.ViewHolder> {
    //    List<Word> words;
//    OnItemClickedListener listener;
    Context mContext;

    private static final DiffUtil.ItemCallback<Ingredient> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Ingredient>() {
                @Override
                public boolean areItemsTheSame(Ingredient oldItem, Ingredient newItem) {
//                    return oldItem.getIdMeal() == (newItem.getIdMeal());
                    return true;
                }

                @Override
                public boolean areContentsTheSame(Ingredient oldItem, Ingredient newItem) {
//                    return oldItem.getStrMeal().equals(newItem.getStrMeal()) &&
//                            oldItem.getStrMealThumb().equals(newItem.getStrMealThumb());
                    return true;
                }
            };

    public interface OnItemClickedListener {
        void onClicked(Meal meal);

        void onAddClicked(Meal meal);

        void onDeleteButtonClicked(Meal meal);
    }


    public IngredientAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
//        this.words = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Word currentItem = words.get(position);
        Ingredient currentItem = getItem(position);
        if(position==0) holder.vSpacer.setVisibility(View.GONE);

        holder.tvIngredient.setText(currentItem.getIngredient());
        holder.tvMeasure.setText(currentItem.getMeasure());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIngredient,tvMeasure;
        View vSpacer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredient = itemView.findViewById(R.id.tvIngredient);
            tvMeasure = itemView.findViewById(R.id.tvMeasure);
            vSpacer = itemView.findViewById(R.id.spacer);
//            if(getAdapterPosition()==0) vSpacer.setVisibility(View.GONE);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onClicked(getMealAt(getAdapterPosition()));
//                }
//            });
//
//            ivAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!view.isSelected()) {
//                        ivAdd.setImageResource(R.drawable.ic_check);
//                        view.setSelected(true);
//                        listener.onAddClicked(getMealAt(getAdapterPosition()));
//                    }
//                    else{
//                        ivAdd.setImageResource(R.drawable.ic_add);
//                        view.setSelected(false);
//                        listener.onDeleteButtonClicked(getMealAt(getAdapterPosition()));
//                    }
//
//                }
//            });
        }
    }


//    public void setOnItemClickedListener(OnItemClickedListener listener) {
//        this.listener = listener;
//    }

    public Ingredient getMealAt(int position) {
        return getItem(position);
    }

}
