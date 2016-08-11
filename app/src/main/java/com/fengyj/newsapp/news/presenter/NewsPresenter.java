package com.fengyj.newsapp.news.presenter;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 16:06
 * Description:
 */
public interface NewsPresenter {

    /**
     *
     * @param mType 新闻类型
     * @param pageIndex 页数
     */
    void loadNews(int mType,int pageIndex);
}
