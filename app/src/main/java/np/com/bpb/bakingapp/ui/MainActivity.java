package np.com.bpb.bakingapp.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import np.com.bpb.bakingapp.adapters.RecipeAdapter;
import np.com.bpb.bakingapp.fragments.RecipeFragement;
import np.com.bpb.bakingapp.interfaces.NetResult;
import np.com.bpb.bakingapp.model.Recipe;
import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.utils.NetUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_RECIPE = "Recipe";
    private int id = R.id.container_recipe_fragment;;
    @BindView(R.id.main_recipe_list)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecipeAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.main_recipe_list);
        if(savedInstanceState == null){initRecylerView();}
    }

    public void initRecylerView(){
//        mRecyclerView.setHasFixedSize(true);
        final boolean isTab = getResources().getBoolean(R.bool.isTablet);
        if (isTab) {
            mLayoutManager = new GridLayoutManager(this,3);
        } else {
            mLayoutManager = new LinearLayoutManager(this);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);

        NetUtils.getRecipes(this, new NetResult() {
            @Override
            public void getRecipes(final List<Recipe> recipeList) {
                // specify an adapter
                mRecipeAdapter = new RecipeAdapter(recipeList, new RecipeAdapter.RecipeListener() {
                    @Override
                    public void onRecipeClickListener(int position) {
                        getNextScreen(position, recipeList);
                    }
                });
                mRecyclerView.setAdapter(mRecipeAdapter);
            }
        });
    }

    private void getNextScreen(int position, List<Recipe> recipes){
        //(COMPLETED)TODO Tranistion to next activity
//        Context context = getBaseContext();
        Intent intent = new Intent(this, RecipeListActivity.class);
        intent.putExtra("Recipe", recipes.get(position));
        startActivity(intent);
    }

    /*
    // The following fragments related methods work perfectly
    public void initFragment(){
//        id = R.id.container_recipe_fragment;
        RecipeFragement fragment = new RecipeFragement();
        setFragment(fragment);
    }
    public void setFragment(Fragment fragment) {
//        int id = R.id.container_recipe_fragment;;
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment, TAG_RECIPE)
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(id, fragment)
                .commit();
    }
    */

}
