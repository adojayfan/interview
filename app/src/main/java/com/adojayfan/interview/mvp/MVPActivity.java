package com.adojayfan.interview.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.adojayfan.interview.R;
import com.adojayfan.interview.model.News;

public class MVPActivity extends AppCompatActivity implements NewsView {

    private NewsPresenter mPresenter;

    private TextView tvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        tvNews = findViewById(R.id.tv_news);

        mPresenter = new NewsPresenter(getApplicationContext());
        mPresenter.attachView(this);
    }

    public void getNews(View view) {
        mPresenter.getNews("323232");
    }

    @Override
    public void showNews(News news) {
        tvNews.setText(news.getTitle() + "\n" + news.getAuthor() + "  " + news.getContent());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
