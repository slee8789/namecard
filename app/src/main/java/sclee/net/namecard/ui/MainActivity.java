package sclee.net.namecard.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import sclee.net.namecard.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView nameList;
    Button getB;
    Button postB;
    EditText urlE;
    TextView urlT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = (ListView) findViewById(R.id.list_name);
        urlE = (EditText) findViewById(R.id.urlE);
        urlT = (TextView) findViewById(R.id.urlT);
        getB = (Button) findViewById(R.id.getB);
        postB = (Button) findViewById(R.id.postB);
        getB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"getB Clicked");
                String strUrl = urlE.getText().toString();
                checkURL(strUrl, "GET");
            }
        });
        postB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"postB Clicked");
//                String strUrl = urlE.getText().toString();
//                checkURL(strUrl, "POST");



            }
        });

        ArrayList<String> items = new ArrayList<>();
        items.add("leesc");
        items.add("leesa");
        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        nameList.setAdapter(adapter);

    }

    private void checkURL (String strUrl, String request) {
        try {
            if (strUrl != null && strUrl.length() > 0) {
                ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    new DownloadWebpageText().execute(strUrl, request);        // html 다운로드 쓰레드 기동
                } else {
                    Log.d(TAG,"network error");
                    throw new Exception("network error");
                }
            } else {
                Log.d(TAG,"bad url");
                throw new Exception("bad url");
            }
        } catch (Exception e) {
            urlT.setText("exception "+e.getMessage());
        }
    }

    private class DownloadWebpageText extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                return (String)downloadUrl((String)arg0[0], (String)arg0[1]);
            } catch (IOException e) {
                Log.d(TAG, "The msg is : " + e.getMessage());
                return "download failed";
            }
        }
        protected void onPostExecute(String result) {
            urlT.setText(result);
            doJsonParser(result);
        }
        private String downloadUrl(String strUrl, String request) throws IOException {
            InputStream is = null;
            Reader reader = null;
            int len = 500;
            try {
                URL url = new URL(strUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod(request);
                conn.setDoInput(true);
                conn.connect();
                int resp = conn.getResponseCode();
                Log.d(TAG, "The response is: " +resp);
                is = conn.getInputStream();
                reader = new InputStreamReader(is, "UTF-8");
                char[] buff = new char[len];
                reader.read(buff);
                return new String(buff);
            }
            finally {
                if (is != null) {
                    is.close();
                }
            }
        }
    }

    private void doJsonParser(String str) {
        StringBuffer sb = new StringBuffer();

        try {
            JSONArray jarray = new JSONArray(str);   // JSONArray 생성
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String no = jObject.getString("no");
                String name = jObject.getString("name");
                String position = jObject.getString("position");
                String phone = jObject.getString("phone");
                Log.d(TAG,"no : "+no+" , name : "+name+" , position : "+position+", phone : "+phone);
            }
           // tv.setText(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void setCustomActionbar() {

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

    }

    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.listview_name, null);
            }

            // ImageView 인스턴스
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

            // 리스트뷰의 아이템에 이미지를 변경한다.
            if ("leesc".equals(items.get(position)))
                imageView.setImageResource(R.drawable.noimage2);
            else {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }


            TextView textView = (TextView) v.findViewById(R.id.name);
            textView.setText(items.get(position));


            return v;
        }
    }
}
