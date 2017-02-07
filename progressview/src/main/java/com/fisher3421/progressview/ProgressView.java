package com.fisher3421.progressview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;


/**
 * Created by Vladimir Molodkin on 07/02/2017.
 * fisher3421@gmail.com
 */

public class ProgressView extends ViewFlipper {
    protected int mContentViewIndex = -1;
    protected int mProgressViewIndex = -1;
    protected int mEmptyViewIndex = -1;
    protected int mErrorViewIndex = -1;

    private int progressLayoutId = R.layout.progress_view_progress;
    private int emptyLayoutId = 0;
    private int errorLayoutId = R.layout.progress_view_error;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ProgressView,
                0, 0);
        try {
            emptyLayoutId = a.getResourceId(R.styleable.ProgressView_emptyView, 0);
            progressLayoutId = a.getResourceId(R.styleable.ProgressView_progressView, R.layout.progress_view_progress);
            errorLayoutId = a.getResourceId(R.styleable.ProgressView_errorView, R.layout.progress_view_error);
        } finally {
            a.recycle();
        }

    }

    public void showContent() {
        setDisplayedChild(mContentViewIndex);
    }

    public void showProgress() {
        setDisplayedChild(mProgressViewIndex);
    }

    public void showEmpty() {
        setDisplayedChild(mEmptyViewIndex);
    }

    public void showError(String error) {
        setDisplayedChild(mErrorViewIndex);
        TextView errorTextView = (TextView) findViewById(R.id.progress_view_error);
        if (errorTextView != null) {
            errorTextView.setText(error);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater inflater = LayoutInflater.from(getContext());

        mContentViewIndex = getChildCount() - 1;

        inflater.inflate(progressLayoutId, this);
        mProgressViewIndex = getChildCount() - 1;

        if (emptyLayoutId != 0) {
            inflater.inflate(emptyLayoutId, this);
            mEmptyViewIndex = getChildCount() - 1;
        }

        inflater.inflate(errorLayoutId, this);
        mErrorViewIndex = getChildCount() - 1;
    }

    public void setRepeatLoading(View.OnClickListener listener) {
        findViewById(R.id.progress_view_repeat).setOnClickListener(listener);
    }
}
