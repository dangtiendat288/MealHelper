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
import com.example.mealhelper.model.Meal;

public class MealPlanFragmentAdapter extends ListAdapter<Meal, MealPlanFragmentAdapter.ViewHolder> {
    //    List<Word> words;
    OnItemClickedListener listener;
    Context mContext;

    private static final DiffUtil.ItemCallback<Meal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Meal>() {
                @Override
                public boolean areItemsTheSame(Meal oldItem, Meal newItem) {
                    return oldItem.getIdMeal() == (newItem.getIdMeal());
                }

                @Override
                public boolean areContentsTheSame(Meal oldItem, Meal newItem) {
                    return oldItem.getStrMeal().equals(newItem.getStrMeal()) &&
                            oldItem.getStrMealThumb().equals(newItem.getStrMealThumb()) &&
                            oldItem.getIsAdded()==newItem.getIsAdded();
                }
            };

    public interface OnItemClickedListener {
        void onClicked(Meal meal);

//        void onAddClicked(Meal meal);
//
//        void onDeleteButtonClicked(Meal meal);
    }


    public MealPlanFragmentAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
//        this.words = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_meal_plan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Word currentItem = words.get(position);
        Meal currentItem = getItem(position);
        Glide.with(mContext)
                .load(currentItem.getStrMealThumb())
                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(holder.ivMeal);
        holder.tvMeal.setText(currentItem.getStrMeal());

//        int colorRes = 0;
//        if (currentItem.getIsmemorized().equals("0")) {
//            holder.tvMemorized.setText("Forget");
//            colorRes = R.color.forget;
//        } else {
//            colorRes = R.color.memorized;
//            holder.tvMemorized.setText("Memorized");
//        }
//        holder.cvMemorized.setCardBackgroundColor(ContextCompat.getColor(mContext,colorRes));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMeal;
        TextView tvMeal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMeal = itemView.findViewById(R.id.ivMeal);
            tvMeal = itemView.findViewById(R.id.tvMeal);


            itemView.setOnClickListener(view -> listener.onClicked(getMealAt(getAdapterPosition())));


        }
    }


    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public Meal getMealAt(int position) {
        return getItem(position);
    }

}
