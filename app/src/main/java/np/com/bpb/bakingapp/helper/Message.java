package np.com.bpb.bakingapp.helper;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import np.com.bpb.bakingapp.R;

public class Message {
    public static void msgBar(View view, String msg, String action ){
        Context context = view.getContext();
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: Do something
                    }
                })
                .setActionTextColor(
                        context.getResources().getColor(R.color.colorPrimaryDark))
                .show();
    }

    public static void share(Context context){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Hey, try this app for recipes.";
        String shareSub = "Recipe";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
}
