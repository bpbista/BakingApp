package np.com.bpb.bakingapp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import np.com.bpb.bakingapp.model.Recipe;

public class Parser {
    //Direct call
    public static Recipe parse(String json){
        Recipe recipe;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> recipeList = gson.fromJson(json,listType);
        // recipe = gson.fromJson(json,Recipe.class);

        //Random recipe
        Random rand = new Random();
        int  n = rand.nextInt(recipeList.size());

        return recipeList.get(n);
    }
}
