package vn.beautylife.progresslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
    }

    private void moveToACProgress() {
        Intent intent = ACProgressActivity.createIntent(this);
        startActivity(intent);
    }

}
