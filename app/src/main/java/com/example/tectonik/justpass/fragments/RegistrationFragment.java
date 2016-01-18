package com.example.tectonik.justpass.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tectonik.justpass.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegistrationFragment extends Fragment {
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);


        return rootView;
    }

    private class LoadDataClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FetchDataTask forecastTask = new FetchDataTask();
            forecastTask.execute("94043");
        }
    }

    public class FetchDataTask extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchDataTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {

            String[] str = null;
            NetworkHelper networkHelper = new NetworkHelper();

            String json = "{\"Name\":\"Johan Strauss\", \"Difficulty\":3}";

            JSONObject obj = new JSONObject();
            try {
                obj.put("Name", "Hello");
                obj.put("Difficulty", "10");
                obj.put("Comments", "Hi");
                obj.put("HelpfulBooks", "The Book");
                obj.put("HelpfulVideos", "https://www.youtube.com/watch?v=Da4DL5-9JOI&index=10&list=PLF4lVL1sPDSn8rCh8DLlP5BJmOAXqe74x");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            networkHelper.post("http://192.168.199.61:3001/api/telerik-courses", obj.toString(), new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String responseStr = response.body().string();
                    final String messageText = "Status code : " + response.code() +
                            "\n" +
                            "Response body : " + responseStr;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), messageText, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });

            return str;
        }

        @Override
        protected void onPostExecute(String[] results) {
            if (results != null) {
                adapter.clear();
                adapter.addAll(results);
            }
        }
    }
}