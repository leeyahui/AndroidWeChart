package com.liyahui.wechartdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.liyahui.wechartdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabFragment extends Fragment {

    private static final String BUNDLE_KEY_TITLE = "key_title";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String mTitle;

    public interface OnTitleClickListener {
        void onClick(String title);
    }

    private OnTitleClickListener mListener;

    public void setOnTitleClickListener(OnTitleClickListener listener) {
        mListener = listener;
    }

    public static TabFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE, title);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString(BUNDLE_KEY_TITLE, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        tvTitle.setText(mTitle);
    }

    @OnClick(R.id.tv_title)
    void click(View view) {
        if (mListener != null) {
            mListener.onClick("TabFragment " + mTitle);
        }
    }

    public void changeTitle(String title) {
        if (!isAdded()) {
            return;
        }
        if (!isResumed()) {
            return;
        }
        tvTitle.setText(title);
    }
}
