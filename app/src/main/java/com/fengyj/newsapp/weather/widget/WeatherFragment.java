package com.fengyj.newsapp.weather.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengyj.newsapp.R;

/**
 * Author: fengyj
 * Date: 2016-08-11
 * Time: 09:53
 * Description:
 */
public class WeatherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather,null);
        return view;
    }
}
