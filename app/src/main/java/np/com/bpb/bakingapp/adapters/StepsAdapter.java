package np.com.bpb.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import np.com.bpb.bakingapp.model.Step;
import np.com.bpb.bakingapp.ui.RecipeDetailActivity;
import np.com.bpb.bakingapp.fragments.RecipeDetailFragment;
import np.com.bpb.bakingapp.ui.RecipeListActivity;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

        private final RecipeListActivity mParentActivity;
        private final List<Step> mSteps;
        private final List<Ingredient> mIngredients;
        private final boolean mTwoPane;

        public static final String STEP = "step";


    public StepsAdapter(RecipeListActivity mParentActivity, List<Step> mValues, List<Ingredient> ingredients, boolean mTwoPane) {
        this.mParentActivity = mParentActivity;
        this.mSteps = mValues;
        this.mIngredients = ingredients;
        this.mTwoPane = mTwoPane;
    }

    @Override
        public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_list_content, parent, false);
            return new StepsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final StepsAdapter.ViewHolder holder, final int position) {

            holder.mIdView.setText(mSteps.get(position).id.toString());
            if(position == 0){
                mSteps.get(position).setShortDescription("Ingredients");
                mSteps.get(position).setDescription(getIngredientsString(mIngredients));
            }
            holder.mContentView.setText(mSteps.get(position).getShortDescription());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setClicker(position,view);
                }
            });

        }

        @Override
        public int getItemCount() {
        if(mSteps == null)
            return 0;
            return mSteps.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.id_text)
            TextView mIdView;
            @BindView(R.id.content)
            TextView mContentView;
            String str;
            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

        private void setClicker(int position,View view){
            Step step = mSteps.get(position);
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putSerializable(RecipeDetailFragment.ARG_STEP,step);
                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setArguments(arguments);

                mParentActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.recipe_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(STEP, step);
                context.startActivity(intent);
            }
        }

        private String getIngredientsString(List<Ingredient> ingredients){
            String string = "";
            for(Ingredient ingredient: mIngredients){
                string = string + ingredient.getIngredient()+"\n"+ingredient.getQuantity().toString()+" "+ingredient.getMeasure()+"\n\n";
            }
            return string;
        }
    }


