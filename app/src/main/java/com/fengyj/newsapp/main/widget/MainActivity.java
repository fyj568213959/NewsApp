package com.fengyj.newsapp.main.widget;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.fengyj.newsapp.R;
import com.fengyj.newsapp.about.widget.AboutFragment;
import com.fengyj.newsapp.common.Configs;
import com.fengyj.newsapp.images.widget.ImagesFragment;
import com.fengyj.newsapp.main.presenter.MainPresenter;
import com.fengyj.newsapp.main.presenter.MainPresenterImpl;
import com.fengyj.newsapp.main.view.MainView;
import com.fengyj.newsapp.news.widget.NewsFragment;
import com.fengyj.newsapp.utils.ToastUtil;
import com.fengyj.newsapp.weather.widget.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 采用MVP模式新闻客户端
 */
public class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter;
    private List<Fragment> mFragments = null;
    private int mPosition;
    private long preTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);
        setWindowTranslucentStatus();
        switchTitle(Configs.NEWS_POSITION);
        initFragments();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mMainPresenter.switchNavigation(menuItem.getItemId());
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setWindowTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //将侧边栏顶部延伸至status bar
                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                mDrawerLayout.setClipToPadding(false);
            }
        }

    }


    private void initFragments() {
        if (mFragments == null)
            mFragments = new ArrayList<>();
        mFragments.add(new NewsFragment());
        mFragments.add(new ImagesFragment());
        mFragments.add(new WeatherFragment());
        mFragments.add(new AboutFragment());
        // 默认显示第一页
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!mFragments.get(0).isAdded()) {
            ft.add(R.id.frame_content, mFragments.get(0));
        }
        ft.commit();
    }

    /**
     * 切换Fragment
     *
     * @param position
     */
    @Override
    public void switchFragment(int position) {
        if (mPosition == position) {
            return;
        }
        switchTitle(position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mFragments.get(mPosition));
        mFragments.get(mPosition).setUserVisibleHint(false);
        mFragments.get(mPosition).onPause();
        if (mFragments.get(position).isAdded()) {
            ft.show(mFragments.get(position));
            mFragments.get(position).setUserVisibleHint(true);
            mFragments.get(position).onResume();
        } else {
            ft.add(R.id.frame_content, mFragments.get(position));
        }
        ft.commit();
        mPosition = position; // 更新目标tab为当前tab
    }

    /**
     * 切换title
     *
     * @param position
     */
    private void switchTitle(int position) {
        switch (position) {
            case Configs.NEWS_POSITION:
                mToolbar.setTitle(R.string.navigation_news);
                break;
            case Configs.IMAGES_POSITION:
                mToolbar.setTitle(R.string.navigation_images);
                break;
            case Configs.WEATHER_POSITION:
                mToolbar.setTitle(R.string.navigation_weather);
                break;
            case Configs.ABOUT_POSITION:
                mToolbar.setTitle(R.string.navigation_about);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawers();
            } else {
                if (System.currentTimeMillis() - preTime > 2000) {
                    ToastUtil.show(this, "再按一次退出");
                    preTime = System.currentTimeMillis();
                    return true;
                } else {
                    MainActivity.this.finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
