package com.adojayfan.interview.mvp;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void showToast(String message);
}
