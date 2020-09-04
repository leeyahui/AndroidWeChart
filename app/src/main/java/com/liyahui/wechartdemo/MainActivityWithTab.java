package com.liyahui.wechartdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.liyahui.wechartdemo.fragment.TabFragment;
import com.liyahui.wechartdemo.utils.L;
import com.liyahui.wechartdemo.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityWithTab extends AppCompatActivity {
    private static final String BUNDLE_KEY_POS = "bundle_key_pos";
    private static final String BUNDLE_KEY_FRAGMENTS = "bundle_key_fragments";

    @BindView(R.id.vp_activity_main)
    ViewPager2 vpActivityMain;
    @BindView(R.id.tab_wechart)
    TabView tabWechart;
    @BindView(R.id.tab_friend)
    TabView tabFriend;
    @BindView(R.id.tab_find)
    TabView tabFind;
    @BindView(R.id.tab_mine)
    TabView tabMine;

    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));
    private List<TabView> mTabs = new ArrayList<>();


    private int mCurrentTabPos;
//    private SparseArray<TabFragment> mTabFragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        L.d("MainActivityWithTab create");
        L.d(mTitles.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        if (savedInstanceState != null) {
            mCurrentTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
        }
        ButterKnife.bind(this);
        initViews();
    }

    @OnClick({R.id.tab_wechart, R.id.tab_friend, R.id.tab_find, R.id.tab_mine})
    void click(TabView tabView) {
        int index = mTabs.indexOf(tabView);
        vpActivityMain.setCurrentItem(index, false);
        setCurrentTab(index);

        mTitles.set(index, mTitles.get(index) + "liyahui");
        L.d(mTitles.toString());
        TabFragment fragment = (TabFragment) getSupportFragmentManager().getFragments().get(vpActivityMain.getCurrentItem());
        if (fragment != null) {
            fragment.changeTitle("liyahui" + tabView.getText());
        }
    }

    private void setCurrentTab(int pos) {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tab = mTabs.get(i);
            if (i == pos) {
                tab.setProgress(1);
            } else {
                tab.setProgress(0);
            }
        }
    }

    private void initViews() {
        tabWechart.setIconAndText(R.drawable.message_normal, R.drawable.message_press, "微信");
        tabFriend.setIconAndText(R.drawable.contacts_normal, R.drawable.contacts_press, "通讯录");
        tabFind.setIconAndText(R.drawable.discovery_normal, R.drawable.discovery_press, "发现");
        tabMine.setIconAndText(R.drawable.me_normal, R.drawable.me_press, "我");

        mTabs.add(tabWechart);
        mTabs.add(tabFriend);
        mTabs.add(tabFind);
        mTabs.add(tabMine);

        setCurrentTab(mCurrentTabPos);

        vpActivityMain.setOffscreenPageLimit(mTitles.size());
        vpActivityMain.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                L.d("create fragement" + position);
                TabFragment fragment = TabFragment.newInstance(mTitles.get(position));
                fragment.setOnTitleClickListener(new TabFragment.OnTitleClickListener() {
                    @Override
                    public void onClick(String title) {
                        changeWeChartTab(title);
                    }
                });
//                mTabFragments.put(position, fragment);
                return fragment;
            }

            @Override
            public int getItemCount() {
                return mTitles.size();
            }
        });

        vpActivityMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);
                    left.setProgress((1 - positionOffset));
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        L.d("MainActivity onSaveInstanceState");
        outState.putInt(BUNDLE_KEY_POS, vpActivityMain.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void changeWeChartTab(String title) {

    }
}