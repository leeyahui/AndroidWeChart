package com.liyahui.wechartdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.liyahui.wechartdemo.fragment.TabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_activity_main)
    ViewPager2 vpActivityMain;
    @BindView(R.id.btn_wechart)
    Button btnWechart;
    @BindView(R.id.btn_friend)
    Button btnFriend;
    @BindView(R.id.btn_find)
    Button btnFind;
    @BindView(R.id.btn_mine)
    Button btnMine;

    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));
    private List<Button> mTabs = new ArrayList<>();
//    private SparseArray<TabFragment> mFragments = new SparseArray<>();
//    private TabFragment mTabFragment;
//    private FragmentStateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    @OnClick({R.id.btn_wechart, R.id.btn_friend, R.id.btn_find, R.id.btn_mine})
    void click(View view) {
//        if (mTabFragment != null) {
//            mTabFragment.changeTitle("liyahui");
//        }
    }

    private void initViews() {
        mTabs.add(btnWechart);
        mTabs.add(btnFriend);
        mTabs.add(btnFind);
        mTabs.add(btnMine);

        vpActivityMain.setOffscreenPageLimit(mTitles.size());
        vpActivityMain.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                TabFragment fragment = TabFragment.newInstance(mTitles.get(position));
                fragment.setOnTitleClickListener(new TabFragment.OnTitleClickListener() {
                    @Override
                    public void onClick(String title) {
                        changeWeChartTab(title);
                    }
                });
//                mFragments.put(position, fragment);
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
                    Button left = mTabs.get(position);
                    Button right = mTabs.get(position + 1);
                    left.setText((1 - positionOffset) + "");
                    right.setText(positionOffset + "");
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

    private void changeWeChartTab(String title) {
        btnWechart.setText(title);
    }
}