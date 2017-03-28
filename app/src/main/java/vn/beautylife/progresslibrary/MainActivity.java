package vn.beautylife.progresslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vuhongky on 3/27/17.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_acprogress_lib)
    AppCompatButton btnAcProgressLib;
    @BindView(R.id.btn_lib_geometric_progress)
    AppCompatButton btnGeometricProgressLib;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViews();
    }

    private void setupViews() {
        setupButtonViews();
    }

    private void setupButtonViews() {
        RxView.clicks(btnAcProgressLib)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> moveToACProgress());
        RxView.clicks(btnGeometricProgressLib)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> moveToGeometricProgress());
    }

    private void moveToACProgress() {
        Intent intent = ACProgressActivity.createIntent(this);
        startActivity(intent);
    }

    private void moveToGeometricProgress() {
        Intent intent = GeometricProgressActivity.createIntent(this);
        startActivity(intent);
    }

}
