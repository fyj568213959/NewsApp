package com.fengyj.newsapp.news.presenter;

import com.fengyj.newsapp.bean.NewsBean;
import com.fengyj.newsapp.common.Urls;
import com.fengyj.newsapp.news.model.NewsModel;
import com.fengyj.newsapp.news.model.NewsModelImpl;
import com.fengyj.newsapp.news.view.NewsView;
import com.fengyj.newsapp.news.widget.NewsFragment;

import java.util.List;

/**
 * Author: fengyj
 * Date: 2016-08-10
 * Time: 16:08
 * Description:
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModelImpl.OnLoadNewsListListener {

    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int mType, int pageIndex) {
        String url = getUrl(mType, pageIndex);

        //只有第一页的或者刷新的时候才显示刷新进度条
        if (pageIndex == 0)
            mNewsView.showProgress();
        mNewsModel.loadNews(url, mType, this);
    }

    private String getUrl(int type, int pageindex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageindex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> news) {
        mNewsView.hideProgress();
        mNewsView.addNews(news);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}
