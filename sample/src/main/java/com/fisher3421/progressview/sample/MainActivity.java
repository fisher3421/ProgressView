package com.fisher3421.progressview.sample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fisher3421.progressview.ProgressView;

public class MainActivity extends AppCompatActivity {

    private ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressView = (ProgressView) findViewById(R.id.progress_view);
        progressView.setRepeatLoading(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress(null);
            }
        });
    }

    public void showProgress(View view) {
        Task task = new Task();
        task.execute(false);
    }

    public void showProgressWithError(View view) {
        Task task = new Task();
        task.execute(true);
    }

    private class Task extends AsyncTask<Boolean, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Boolean... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {}
            return params[0];
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressView.showProgress();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                progressView.showError("simple error");
            } else {
                progressView.showContent();
            }
        }
    }
}
