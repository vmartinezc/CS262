package vam6.cs262.calvin.edu.homework02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class FetchPlayer extends AsyncTaskLoader {


    private String player_id;

    /* Fetchplayer()
       @params: context, qString
       this function assigns player_id
       to be the querystring
     */
    public FetchPlayer(@NonNull Context context, String qString) {
        super(context);
        player_id = qString;
    }

    @Nullable
    @Override
    public Object loadInBackground() { //loads the player's information in the background
        return NetworkUtils.getPlayerInfo(player_id);
    }
}
