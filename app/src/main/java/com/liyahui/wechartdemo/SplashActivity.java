package com.liyahui.wechartdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.liyahui.wechartdemo.view.transformer.ScaleTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.vp_activity_splash)
    ViewPager2 vpActivitySplash;

    private int[] mResIds = new int[]{
            R.drawable.splash,
            R.drawable.ic_launcher_background
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        vpActivitySplash.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return SplashFragment.newInstance(mResIds[position]);
            }

            @Override
            public int getItemCount() {
                return mResIds.length;
            }
        });

        vpActivitySplash.setPageTransformer(new ScaleTransformer());
    }
}