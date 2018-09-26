package np.com.bpb.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.ui.MainActivity;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidgetProvider extends AppWidgetProvider {
    public static final String DATA_FETCHED = "np.com.bpb.bakingapp.DATA_FETCHED";

    public static final String ACTION_TOAST = "actionToast";
    public static final String EXTRA_ITEM_POSITION = "extraItemPosition";
   public static RemoteViews views;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
         views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget_provider);
        views.setTextViewText(R.id.appwidget_text, widgetText);


        Intent serviceIntent = new Intent(context, IngredientWidgetRemoteViewsService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

//        Intent intent = new Intent(context, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
//        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);

        Intent intent = new Intent(context, IngredientsWidgetProvider.class);
        intent.setAction(ACTION_TOAST);
        PendingIntent ingredientPendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
//        PendingIntent ingredientPendingIntent = PendingIntent.getActivity(context,0,intent,0);

        views.setRemoteAdapter(R.id.appwidget_list,serviceIntent);
        views.setEmptyView(R.id.appwidget_list,R.id.appwidget_empty);

        views.setPendingIntentTemplate(R.id.appwidget_list,ingredientPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ACTION_TOAST.equals(intent.getAction())){
            int clickedPosition = intent.getIntExtra(EXTRA_ITEM_POSITION,0);

            Intent intent1 = new Intent(context,MainActivity.class);
            startActivity(context,intent1,null);

//            Toast.makeText(context, "Clicked "+clickedPosition, Toast.LENGTH_SHORT).show();
        }
//        new IngredientWidgetRemoteViewsFactory(context,intent);
        super.onReceive(context, intent);
    }

}

