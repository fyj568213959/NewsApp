package com.fengyj.newsapp.main.presenter;

import com.fengyj.newsapp.R;
import com.fengyj.newsapp.common.Configs;
import com.fengyj.newsapp.main.view.MainView;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 14:15
 * Description:switch navigation
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
              //  mMainView.switchToNews();
                mMainView.switchFragment(Configs.NEWS_POSITION);
                break;
            case R.id.navigation_item_images:
              //  mMainView.switchToImages();
                mMainView.switchFragment(Configs.IMAGES_POSITION);
                break;
            case R.id.navigation_item_weather:
               // mMainView.switchToWeather();
                mMainView.switchFragment(Configs.WEATHER_POSITION);
                break;
            case R.id.navigation_item_about:
                //mMainView.switchToAbout();
                mMainView.switchFragment(Configs.ABOUT_POSITION);
                break;
        }
    }
}
