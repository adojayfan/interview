package com.adojayfan.interview.mvp;

import android.content.Context;
import com.adojayfan.interview.model.DataManager;
import com.adojayfan.interview.model.News;

public class NewsPresenter extends BasePresenter<NewsView> {

    private DataManager dataManager;

    public NewsPresenter(Context context) {
        super(context);
        dataManager = new DataManager(context);
    }

    public void getNews(String newsId) {
        checkViewAttached();
        dataManager.getNews(newsId, new DataManager.OnModelListener<News>() {
            @Override
            public void onSuccess(News news) {
                getView().showNews(news);
            }

            @Override
            public void onFailed(String errMsg) {
                getView().showToast(errMsg);
            }
        });
    }
}
