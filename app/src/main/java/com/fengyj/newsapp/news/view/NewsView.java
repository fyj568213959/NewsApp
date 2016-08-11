package com.fengyj.newsapp.news.view;

import com.fengyj.newsapp.bean.NewsBean;

import java.util.List;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 16:11
 * Description:
 */
public interface NewsView {
    void showProgress();

    void addNews(List<NewsBean> news);

    void hideProgress();

    void showLoadFailMsg();
}
