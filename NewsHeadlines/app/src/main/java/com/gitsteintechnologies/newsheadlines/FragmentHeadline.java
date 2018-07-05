package com.gitsteintechnologies.newsheadlines;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;


public class FragmentHeadline extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_fragment_headline,container,false);
        textView= v.findViewById(R.id.textView);

        Bundle bundle=getArguments();


            textView.setText(bundle.getString("headline"));




        return v;
    }
}
