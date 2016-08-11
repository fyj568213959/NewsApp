package com.fengyj.newsapp.news.model;

import android.text.TextUtils;

import com.fengyj.newsapp.bean.NewsBean;
import com.fengyj.newsapp.common.Urls;
import com.fengyj.newsapp.news.NewsJsonUtils;
import com.fengyj.newsapp.news.widget.NewsFragment;
import com.fengyj.newsapp.utils.OkHttpUtils;

import java.util.List;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 16:26
 * Description:新闻业务处理类
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                if (!TextUtils.isEmpty(response)) {
                    List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
                    listener.onSuccess(newsBeanList);
                }
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news list failure.", e);
            }
        };
        OkHttpUtils.get(url,callback);
    }

    /**
     * 获取ID
     *
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);

        void onFailure(String msg, Exception e);
    }

}
