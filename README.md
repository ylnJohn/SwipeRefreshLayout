# SwipeRefreshLayout
自定义刷新控件（用法和SwipeRefreshLayout类似）</br>
支持下拉刷新和上拉加载更多</br>
非侵入式，对原来的ListView、RecyclerView没有任何影响,用法和SwipeRefreshLayout类似。</br>
支持RecyclerView，ListView，ScrollView，GridView等等。</br>
被包含的View(RecyclerView,ListView etc.)可跟随手指的滑动而滑动</br>
默认是跟随手指的滑动而滑动，也可以设置为不跟随：setTargetScrollWithLayout(false)</br>
回调方法很多 比如：onRefresh() onPullDistance(int distance)和onPullEnable(boolean enable)开发人员可以根据下拉过程中distance的值做一系列动画。</br>

1、布局中与CoordinatorLayout嵌套使用</br>
<com.yln.swiperefreshlayout.SuperSwipeRefreshLayout
    android:id="@+id/swipe_refresh_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    >
    <android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >
        <android.support.design.widget.AppBarLayout
            android:id="@+id/appbar_layout"
            android:layout_width="match_parent"
            android:layout_height="450dp"
            android:background="@android:color/transparent"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            app:elevation="0dp"
            >
            <android.support.design.widget.CollapsingToolbarLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:layout_scrollFlags="scroll|exitUntilCollapsed|snap"
                app:contentScrim="?attr/colorPrimary"
                app:statusBarScrim="@android:color/transparent"
                >
                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:scaleType="fitXY"
                    app:layout_collapseMode="parallax"
                    app:layout_collapseParallaxMultiplier="0.8"
                    android:src="@drawable/a_toolbar_bg"/>
                <android.support.v7.widget.Toolbar
                    android:layout_width="match_parent"
                    android:layout_height="100dp"
                    app:layout_collapseMode="pin"
                    app:contentInsetEnd="0dp"
                    app:contentInsetStart="0dp"
                    app:title="Title"
                    app:titleTextColor="#ffffff"
                    app:popupTheme="@style/ThemeOverlay.AppCompat.Light" >
                </android.support.v7.widget.Toolbar>
            </android.support.design.widget.CollapsingToolbarLayout>
        </android.support.design.widget.AppBarLayout>
        
        
2、与AppBarLayout联动使用</br>
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
        
 3、SuperSwipeRefreshLayout刷新样式支持图标或者其他图形，可以自己通过修改代码在canvas绘制，提供多种方法自定义。代码实现的刷新样式是图标跟随手势下拉扩大和旋转，并提供刷新文案提示，达到刷新触发条件时，图标会不断旋转，直至刷新结束。</br>
    defaultProgressView = new CircleProgressView(getContext());//刷新图标</br>
		mHeaderText = new TextView(getContext());//刷新提示文字</br>
		mHeaderText.setTextColor(0xffffffff);</br>
		mHeaderText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);</br>
		mHeaderText.setGravity(Gravity.CENTER);</br>
