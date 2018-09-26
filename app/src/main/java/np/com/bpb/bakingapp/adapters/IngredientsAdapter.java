package np.com.bpb.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.model.Ingredient;
import np.com.bpb.bakingapp.ui.RecipeDetailActivity;
import np.com.bpb.bakingapp.fragments.RecipeDetailFragment;
import np.com.bpb.bakingapp.ui.RecipeListActivity;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private final RecipeListActivity mParentActivity;
    private List<Ingredient> mIngredients;
    private Context mContext;
    private final boolean mTwoPane;

    public IngredientsAdapter(RecipeListActivity mParentActivity, List<Ingredient> mIngredients, boolean mTwoPane) {
        this.mParentActivity = mParentActivity;
        this.mIngredients = mIngredients;
        this.mTwoPane = mTwoPane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View ingredientsListView = LayoutInflater.from(mContext).inflate(R.layout.ingredients_list_item, parent, false);
        return new ViewHolder(ingredientsListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredientName = mIngredients.get(position).getIngredient();
        float quantity = mIngredients.get(position).getQuantity();
        String measure = mIngredients.get(position).getMeasure();

        holder.ingredient.setText(ingredientName);
        holder.ingredientQuantity.setText(String.valueOf(quantity));
        holder.ingredientUnit.setText(measure);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_ingredient_unit)
        TextView ingredientUnit;

        @BindView(R.id.tv_ingredient)
        TextView ingredient;

        @BindView(R.id.tv_ingredient_qt)
        TextView ingredientQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setClicker(int position,View view){
        Ingredient ingredient = mIngredients.get(position);
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putSerializable(RecipeDetailFragment.ARG_STEP,ingredient);
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_detail_container, fragment)
                    .commit();
        } else {
            Context context = view.getContext();
            String ingredString = ingredient.getIngredient()+"\n"+ingredient.getQuantity().toString()+" "+ingredient.getMeasure()+"\n";
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra(RecipeDetailFragment.ARG_INGREDIENT, ingredString);

            context.startActivity(intent);
        }
    }
}
