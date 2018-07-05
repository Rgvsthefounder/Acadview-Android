package com.gitsteintechnologies.newsheadlines;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    String news,headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        RelativeLayout rel=findViewById(R.id.first);


        //Input using jason
        String json = null;
        try {
            InputStream is = getApplication().getAssets().open("news.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("TEST",json);
            JSONObject obj = new JSONObject(json);

             news=obj.getString("news");
             headline=obj.getString("headline");

        } catch (Exception ex) {
            ex.printStackTrace();}


        FragmentHeadline fragmentHeadline=new FragmentHeadline();
        Fragment fragment=fragmentHeadline;
        Bundle bundle = new Bundle();
        bundle.putString("headline",headline);
        fragmentHeadline.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.first,fragmentHeadline).commit();
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNews fragmentNews=new FragmentNews();
                Bundle bundle = new Bundle();
                Fragment fragment=fragmentNews;
                bundle.putString("news",news);
                fragmentNews.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.second,fragmentNews).commit();
            }
        });
    }
}
