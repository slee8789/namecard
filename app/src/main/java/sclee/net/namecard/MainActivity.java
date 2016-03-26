package sclee.net.namecard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = (ListView) findViewById(R.id.list_name);


        ArrayList<String> items = new ArrayList<>();
        items.add("leesc");
        items.add("leesa");
        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        nameList.setAdapter(adapter);

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
