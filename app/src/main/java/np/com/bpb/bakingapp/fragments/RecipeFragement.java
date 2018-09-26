package np.com.bpb.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.adapters.RecipeAdapter;
import np.com.bpb.bakingapp.interfaces.NetResult;
import np.com.bpb.bakingapp.model.Recipe;
import np.com.bpb.bakingapp.ui.RecipeListActivity;
import np.com.bpb.bakingapp.utils.NetUtils;

public class RecipeFragement extends Fragment { //implements RecipeAdapter.RecipeListener {
    @BindView(R.id.rv_recipes)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);

        boolean isTab = getResources().getBoolean(R.bool.isTablet);
        mRecyclerView.setHasFixedSize(true);
        mContext = getContext();
        if (isTab) {
            mLayoutManager = new GridLayoutManager(mContext,3);
        } else {
            mLayoutManager = new LinearLayoutManager(mContext);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);

        NetUtils.getRecipes(mContext, new NetResult() {
            @Override
            public void getRecipes(final List<Recipe> recipeList) {
                // specify an adapter
                mAdapter = new RecipeAdapter(recipeList, new RecipeAdapter.RecipeListener() {
                    @Override
                    public void onRecipeClickListener(int position) {
                        getNextScreen(position, recipeList);
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        return view;
    }


    private void getNextScreen(int position, List<Recipe> recipes){
        //(COMPLETED)TODO Tranistion to next activity

            Context context = getContext();
            Intent intent = new Intent(context, RecipeListActivity.class);
            intent.putExtra("Recipe", recipes.get(position));
            startActivity(intent);
    }
}
