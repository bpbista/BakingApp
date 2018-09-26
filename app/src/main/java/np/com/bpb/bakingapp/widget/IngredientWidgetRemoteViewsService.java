package np.com.bpb.bakingapp.widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

import java.util.List;
import java.util.Random;

import np.com.bpb.bakingapp.interfaces.NetResult;
import np.com.bpb.bakingapp.model.Ingredient;
import np.com.bpb.bakingapp.model.Recipe;
import np.com.bpb.bakingapp.utils.NetUtils;

import static android.support.constraint.Constraints.TAG;

public class IngredientWidgetRemoteViewsService extends RemoteViewsService {
    static List<Ingredient> mIngredientList;
    static Recipe mRecipe;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        NetUtils.getRecipes(this.getApplicationContext(), new NetResult() {
            @Override
            public void getRecipes(List<Recipe> recipeList) {
                Random rand = new Random();
                int  n = rand.nextInt(recipeList.size());
                mRecipe = recipeList.get(n);
                mIngredientList=mRecipe.getIngredients();
            }
        });

        return new IngredientWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
