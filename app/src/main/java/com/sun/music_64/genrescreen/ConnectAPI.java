package com.sun.music_64.genrescreen;

import android.os.AsyncTask;

import com.sun.music_64.data.model.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ConnectAPI extends AsyncTask<String, List<Track>, List<Track>> {

    private NetworkCallback mNetworkCallback;

    public ConnectAPI(NetworkCallback mNetworkCallback) {
        this.mNetworkCallback = mNetworkCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        List<Track> mTrack = new ArrayList<>();
        try {
            URL mURL = new URL(strings[0]);
            HttpsURLConnection mHttpConnection = (HttpsURLConnection) mURL.openConnection();
            mHttpConnection.setRequestProperty(Config.URL_USER_KEY, Config.URL_USER_VALUE);
            mHttpConnection.setReadTimeout(Config.URL_REQUEST_TIME);
            mHttpConnection.setConnectTimeout(Config.URL_REQUEST_TIME);
            mHttpConnection.setRequestMethod(Config.URL_REQUEST_METHOD);
            mHttpConnection.setDoInput(true);
            mHttpConnection.connect();
            if (mHttpConnection.getResponseCode() == Config.URL_REQUEST_CODE) {
                InputStream mInputStream = mHttpConnection.getInputStream();
                mTrack = readUserJSONFile(mInputStream);
                publishProgress(mTrack);
            } else {

            }
            mHttpConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mNetworkCallback.receiverUsersFail();
        } catch (IOException e) {
            e.printStackTrace();
            mNetworkCallback.receiverUsersFail();
        } catch (JSONException e) {
            e.printStackTrace();
            mNetworkCallback.receiverUsersFail();
        }
        return mTrack;
    }

    @Override
    protected void onProgressUpdate(List<Track>... values) {
        mNetworkCallback.receiverUsersSuccess(values[0]);
    }

    @Override
    protected void onPostExecute(List<Track> s) {

    }

    private static List<Track> readUserJSONFile(InputStream inputStream) throws IOException, JSONException {

        List<Track> tracks = new ArrayList<>();
        String jsonText = readText(inputStream);

        JSONObject mJSONObject = new JSONObject(jsonText);
        JSONArray jsonArray = new JSONArray(mJSONObject.getString(Config.URL_COLLECTION));
        JSONObject[] mJSONObject1 = new JSONObject[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            mJSONObject1[i] = jsonArray.getJSONObject(i);
            JSONObject mJSONObject2 = new JSONObject(mJSONObject1[i].getString(Config.URL_TRACK));
            JSONObject mJSONObject3 = new JSONObject(mJSONObject2.getString(Config.URL_USER));
            tracks.add(new Track(mJSONObject2.getInt(Config.URL_ID), mJSONObject2.getString(Config.URL_TITLE),mJSONObject3.getString(Config.URL_USER_NAME)));
        }

        return tracks;
    }

    private static String readText(InputStream inputStream) throws IOException {
        InputStream is = inputStream;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
