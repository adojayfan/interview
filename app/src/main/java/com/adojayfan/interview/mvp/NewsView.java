package com.adojayfan.interview.mvp;

import com.adojayfan.interview.model.News;

public interface NewsView extends MvpView {

    void showNews(News news);
}
