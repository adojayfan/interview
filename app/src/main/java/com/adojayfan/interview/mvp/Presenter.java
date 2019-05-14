package com.adojayfan.interview.mvp;

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
