package np.com.bpb.bakingapp.interfaces;

import java.util.List;

import np.com.bpb.bakingapp.model.Recipe;

public interface NetResult {
    void getRecipes(List<Recipe> recipeList);
}
