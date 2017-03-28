package vn.beautylife.progresslibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import net.bohush.geometricprogressview.GeometricProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeometricProgressActivity extends AppCompatActivity {
    @BindView(R.id.progress_triangle)
    GeometricProgressView progressView;

    public static Intent createIntent(Context context) {
        return new Intent(context, GeometricProgressActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometric_progress);
        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupViews() {
        setupToolbar();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
