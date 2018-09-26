package np.com.bpb.bakingapp.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import butterknife.BindView;
import np.com.bpb.bakingapp.R;
import np.com.bpb.bakingapp.helper.MediaLoader;
import np.com.bpb.bakingapp.model.Step;
import np.com.bpb.bakingapp.ui.RecipeDetailActivity;
import np.com.bpb.bakingapp.ui.RecipeListActivity;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_STEP = "item_step";
    public static final String ARG_INGREDIENT = "item_ingredient";

    Step mStep;
    PlayerView playerView;
    android.app.ActionBar actionBar;
    View rootView;
    @BindView(R.id.recipe_detail)
    View detailView;
    View decorView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_STEP)) {
            Bundle args = getArguments();
            mStep = (Step) args.getSerializable(ARG_STEP);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mStep.getShortDescription());
            }
        }

         decorView = getActivity().getWindow().getDecorView();
         actionBar = getActivity().getActionBar();
    }
/* //perfectly working code
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
        View rootView = inflater.inflate(R.layout.recipe_detail, container, false);
        // Show the dummy content as text in a TextView.

        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4";

        if(mStep.getVideoURL() != null && mStep.getVideoURL() != "")
            url = mStep.getVideoURL();

        SimpleExoPlayer player = MediaLoader.initializePlayer(getContext(),url);

        //Initialize simpleExoPlayerView

        PlayerView playerView = rootView.findViewById(R.id.exoplayer);
        if(mStep.getShortDescription() == "Ingredients")
        {
            playerView.setVisibility(View.GONE);
        }
        else if(playerView != null) {
            playerView.setVisibility(View.VISIBLE);
            playerView.setPlayer(player);
        }

        if (mStep != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(mStep.getDescription());
        }

        return rootView;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity activity = getActivity();
       rootView = inflater.inflate(R.layout.recipe_detail, container, false);
        // Show the dummy content as text in a TextView.

        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4";

        if(mStep.getVideoURL() != null && mStep.getVideoURL() != "")
            url = mStep.getVideoURL();

        SimpleExoPlayer player = MediaLoader.initializePlayer(getContext(),url);

        //Initialize simpleExoPlayerView

        playerView = rootView.findViewById(R.id.exoplayer);
        if(mStep.getShortDescription() == "Ingredients")
        {
            playerView.setVisibility(View.GONE);
        }
        else if(playerView != null) {
            playerView.setVisibility(View.VISIBLE);
            playerView.setPlayer(player);
        }

        if (mStep != null) {
            ((TextView) rootView.findViewById(R.id.recipe_detail)).setText(mStep.getDescription());
        }

        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE && playerView != null) {
            //First Hide other objects (listview or recyclerview), better hide them using Gone.
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
            params.width=params.MATCH_PARENT;
            params.height=params.MATCH_PARENT;
            playerView.setLayoutParams(params);
            hideActionBar();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT && playerView != null){
            //unhide your objects here.
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
            params.width=params.MATCH_PARENT;
            params.height=400;
            playerView.setLayoutParams(params);
            showActionBar();
        }
//To show the action bar
    }

    void showActionBar(){
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);
        actionBar.show();
        detailView.setVisibility(View.VISIBLE);
    }
    void hideActionBar(){

// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        detailView.setVisibility(View.GONE);
        actionBar.hide();
    }
}
