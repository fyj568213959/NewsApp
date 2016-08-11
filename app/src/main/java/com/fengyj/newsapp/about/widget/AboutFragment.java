package com.fengyj.newsapp.about.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengyj.newsapp.R;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 17:32
 * Description:关于
 */
public class AboutFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, null);
        return view;
    }
}
