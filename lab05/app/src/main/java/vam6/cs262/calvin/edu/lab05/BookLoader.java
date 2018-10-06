package vam6.cs262.calvin.edu.lab05;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

public class BookLoader extends AsyncTaskLoader<String> {
    private String mQueryString;

public BookLoader(Context context, String queryString){
    super(context);
    mQueryString = queryString;
}

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading(){forceLoad();}


}
