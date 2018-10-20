package vam6.cs262.calvin.edu.homework02;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//This class is for connecting to the internet
public class NetworkUtils {
    private static final String PLAYER_BASE_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/players";
    private static final String ID_PARAM = "";
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    static String getPlayerInfo(String player_idString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String playerJSONString = null;

        try{
            Uri builtURI;
            if(player_idString.equals("")) {
                builtURI = Uri.parse(PLAYER_BASE_URL.concat("s"));
                }
                else{
                builtURI = Uri.parse(PLAYER_BASE_URL.concat("/").concat(player_idString));

            }
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();




            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            String line;

            if (inputStream == null) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
   /* Since it's JSON, adding a newline isn't necessary (it won't affect
      parsing) but it does make debugging a *lot* easier if you print out the
      completed buffer for debugging. */
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            playerJSONString = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            Log.d(LOG_TAG, playerJSONString);
            return playerJSONString;
        }



    }




