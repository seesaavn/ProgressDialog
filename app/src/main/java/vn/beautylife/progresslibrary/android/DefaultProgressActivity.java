package vn.beautylife.progresslibrary.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.beautylife.progresslibrary.R;

public class DefaultProgressActivity extends AppCompatActivity {
    @BindView(R.id.btn_simple_progress)
    AppCompatButton btnSimpleProgress;

    public static Intent createIntent(Context context) {
        return new Intent(context, DefaultProgressActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_progress);
        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupViews() {
        setupToolbar();
        setupButtonViews();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupButtonViews() {
        RxView.clicks(btnSimpleProgress)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> showSimpleProgress());
    }

    private void showSimpleProgress() {
        SimpleProgress dialog = new SimpleProgress(this);
        dialog.show();
    }

}
