package com.fengyj.newsapp.news.model;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 16:24
 * Description:
 */
public interface NewsModel {

    void loadNews(String url, int type, NewsModelImpl.OnLoadNewsListListener listener);

    //void loadNewsDetail(String docid, NewsModelImpl.OnLoadNewsDetailListener listener);
}
