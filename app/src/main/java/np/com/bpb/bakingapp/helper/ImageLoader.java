package np.com.bpb.bakingapp.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;

import np.com.bpb.bakingapp.R;


public class ImageLoader extends AppGlideModule {
    public static void loadImage(String imageURL, ImageView imageView){
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imageURL)
                .into(imageView);
    }
}
