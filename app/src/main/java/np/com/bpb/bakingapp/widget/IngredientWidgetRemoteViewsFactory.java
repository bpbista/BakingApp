package np.com.bpb.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.interfaces.OnWidgetClick;
import np.com.bpb.bakingapp.model.Ingredient;
import np.com.bpb.bakingapp.model.Recipe;
import np.com.bpb.bakingapp.ui.MainActivity;
import np.com.bpb.bakingapp.utils.Parser;

import static android.support.constraint.Constraints.TAG;
import static np.com.bpb.bakingapp.widget.IngredientsWidgetProvider.EXTRA_ITEM_POSITION;

public class IngredientWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private int appWidgetId;
    private Recipe mRecipe;
    RemoteViews remoteViews;

    private List<Ingredient> ingredients;// = new ArrayList<Ingredient>();

    public IngredientWidgetRemoteViewsFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        this.ingredients = getIngredients();
        this.mRecipe = IngredientWidgetRemoteViewsService.mRecipe;
        this.remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.ingredients_list_widget);
    }

    @Override
    public void onCreate() {
        if(mRecipe != null) {
            mRecipe = IngredientWidgetRemoteViewsService.mRecipe;
            ingredients = IngredientWidgetRemoteViewsService.mIngredientList;
            Log.d(TAG, "onCreate: Not null:"+ingredients.get(0).getIngredient());
        }else{
            ingredients = getIngredients();
            Log.d(TAG, "onCreate: Null"+ingredients.get(0).getIngredient());
        }
    }

    @Override
    public void onDataSetChanged() {
//        ingredients = RecipeDetailActivity.mIngredients;
        mRecipe = IngredientWidgetRemoteViewsService.mRecipe;
        ingredients = IngredientWidgetRemoteViewsService.mIngredientList;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients == null ? 0 : ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

        remoteViews.setTextViewText(R.id.tv_ingredient_widget, ingredients.get(i).getIngredient());
        remoteViews.setTextViewText(R.id.tv_quantity_widget, ingredients.get(i).getQuantity() + "");
        remoteViews.setTextViewText(R.id.tv_measure_widget, ingredients.get(i).getMeasure());

//        Intent intent = new Intent(mContext, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,0,intent,0);
//        remoteViews.setOnClickPendingIntent(R.id.tv_ingredient_widget,pendingIntent);

        Intent fillIntent = new Intent();
        fillIntent.putExtra(EXTRA_ITEM_POSITION,i);
        remoteViews.setOnClickFillInIntent(R.id.tv_ingredient_widget,fillIntent);
        return remoteViews;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private List<Ingredient> getIngredients(){
        Recipe recipe;
        List<Ingredient> ingreds;
        Resources res = mContext.getResources();
        String jsonStr = res.getString(R.string.jsonData);
        recipe = Parser.parse(jsonStr);
        ingreds = recipe.getIngredients();
        return ingreds;
    }

}
