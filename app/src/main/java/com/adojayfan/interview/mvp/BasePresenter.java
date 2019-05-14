package com.adojayfan.interview.mvp;

import android.content.Context;

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mView;
    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mContext = null;
    }

    public T getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (mView == null) {
            throw new RuntimeException("Please call attachView(MvpView) before requesting data to the presenter");
        }
    }
}
