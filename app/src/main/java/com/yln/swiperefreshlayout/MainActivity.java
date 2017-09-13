package com.yln.swiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;

public class MainActivity extends Activity {

    private static final String TAG="MainActivity";
    private SuperSwipeRefreshLayout mRefreshLayout;
    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefreshLayout=(SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mAppBarLayout=(AppBarLayout) findViewById(R.id.appbar_layout);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int y=Math.abs(verticalOffset);
                if(y==0){
                    //完全展开状态
                    mRefreshLayout.setEnabled(true);
                }else{
                    //处于折叠状态
                    mRefreshLayout.setEnabled(false);
                }
            }
        });
        mRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener(){

            @Override
            public void onRefresh() {
                //刷新处理事件
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshText(getResources().getString(R.string.refresh_success));
                        mRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }

            @Override
            public void onPullDistance(int distance) {
                //监听刷新控件拖动距离
                Log.i(TAG,"pull distance:"+distance);
            }

            @Override
            public void onPullEnable(boolean enable) {

            }

            @Override
            public void onPullFinish() {
                //刷新事件结束，控件消失
                Log.i(TAG,"pull finish");
            }
        });
    }
}
