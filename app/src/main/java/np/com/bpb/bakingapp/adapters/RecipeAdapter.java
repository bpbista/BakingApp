package np.com.bpb.bakingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.helper.ImageLoader;
import np.com.bpb.bakingapp.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    public List<Recipe> recipes;
    private RecipeListener mRecipeListener;

    public RecipeAdapter(List<Recipe> recipes, RecipeListener mRecipeListener) {
        this.recipes = recipes;
        this.mRecipeListener = mRecipeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);

       final ViewHolder vh = new ViewHolder(v);
        vh.recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecipeListener.onRecipeClickListener(vh.getAdapterPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = recipes.get(position).getName();
        String imageUrl = recipes.get(position).getImage();

        holder.recipeName.setText(name);
        ImageLoader.loadImage(imageUrl,holder.recipeImage);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_recipe_list_item)
        ImageView recipeImage;
        @BindView(R.id.cv_recipe_list_item)
        CardView recipeCard;
        @BindView(R.id.tv_recipe_list_item)
        TextView recipeName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface RecipeListener {
        void onRecipeClickListener(int position);
    }
}
