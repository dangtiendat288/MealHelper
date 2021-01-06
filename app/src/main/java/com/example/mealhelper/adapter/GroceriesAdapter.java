package com.example.mealhelper.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealhelper.R;
import com.example.mealhelper.model.Ingredient;

public class GroceriesAdapter extends ListAdapter<Ingredient, GroceriesAdapter.ViewHolder> {
    //    List<Word> words;
    OnItemClickedListener listener;
    Context mContext;

    private static final DiffUtil.ItemCallback<Ingredient> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Ingredient>() {
                @Override
                public boolean areItemsTheSame(Ingredient oldItem, Ingredient newItem) {
                    return oldItem.getId() == (newItem.getId());

                }

                @Override
                public boolean areContentsTheSame(Ingredient oldItem, Ingredient newItem) {
                    return oldItem.getIngredient().equals(newItem.getIngredient()) &&
                            oldItem.getMeasure().equals(newItem.getMeasure()) &&
                            oldItem.isChecked() == newItem.isChecked();

                }
            };

    public interface OnItemClickedListener {
        void onCheckBoxChecked(Ingredient ingredient);

        void onCheckBoxUnchecked(Ingredient ingredient);
//
//        void onDeleteButtonClicked(Meal meal);
    }


    public GroceriesAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
//        this.words = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_groceries, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Word currentItem = words.get(position);
        Ingredient currentItem = getItem(position);
        if (position == 0) holder.vSpacer.setVisibility(View.GONE);
        if (currentItem.isChecked()) {
            holder.cbIngredient.setPaintFlags(holder.cbIngredient.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvMeasure.setPaintFlags(holder.tvMeasure.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            holder.cbIngredient.setChecked(true);
        } else {
            holder.cbIngredient.setChecked(false);
            holder.cbIngredient.setPaintFlags(0);
            holder.tvMeasure.setPaintFlags(0);
        }

        holder.cbIngredient.setText(currentItem.getIngredient());
        holder.tvMeasure.setText(currentItem.getMeasure());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMeasure;
        CheckBox cbIngredient;
        View vSpacer;
        LinearLayout layoutGroceries;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cbIngredient = itemView.findViewById(R.id.cb);
            tvMeasure = itemView.findViewById(R.id.tvMeasure);
            vSpacer = itemView.findViewById(R.id.spacerGroceries);
            layoutGroceries = itemView.findViewById(R.id.layoutGroceries);

            cbIngredient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        cbIngredient.setPaintFlags(cbIngredient.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                        tvMeasure.setPaintFlags(tvMeasure.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                        listener.onCheckBoxChecked(getIngredientAt(getAdapterPosition()));
                    }
                    else {
                        cbIngredient.setPaintFlags(0);
                        tvMeasure.setPaintFlags(0);
                        listener.onCheckBoxUnchecked(getIngredientAt(getAdapterPosition()));
                    }
                }
            });
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


    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public Ingredient getIngredientAt(int position) {
        return getItem(position);
    }

}
