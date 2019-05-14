package com.adojayfan.interview.model;

import android.content.Context;

public class DataManager {

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    public void getUserInfo(String userId, OnModelListener<User> onModelListener) {
        if (onModelListener == null) {
            throw new RuntimeException("OnModelListener can not be null");
        }
        //从网络获取数据
        User user = new User();
        user.setAge(25);
        user.setName("James");
        onModelListener.onSuccess(user);
    }

    public void getNews(String newsId, OnModelListener<News> onModelListener) {
        if (onModelListener == null) {
            throw new RuntimeException("OnModelListener can not be null");
        }
        News news = new News();
        news.setTitle("利物浦获得最强亚军");
        news.setAuthor("腾讯体育");
        news.setContent(
                "北京时间5月12日晚，2018-19赛季英超联赛第38轮，利物浦坐镇安菲尔德球场2比0击败狼队，但因为曼城4比1大胜布莱顿，利物浦落后曼城一分获得英超亚军。第17和81分钟，马内梅开二度。");
        onModelListener.onSuccess(news);
    }

    public interface OnModelListener<T> {

        void onSuccess(T t);

        void onFailed(String errMsg);
    }
}
