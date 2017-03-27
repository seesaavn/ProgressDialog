package vn.beautylife.progresslibrary;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressCustom;
import cc.cloudist.acplibrary.ACProgressFlower;
import cc.cloudist.acplibrary.ACProgressPie;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_progress_flower)
    AppCompatButton btnProgressFlower;
    @BindView(R.id.btn_progress_pie)
    AppCompatButton btnProgressPie;
    @BindView(R.id.btn_progress_custom)
    AppCompatButton btnProgressCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViews() {
        setupToolbar();
        setupButtonViews();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own showFlowerProgress", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void setupButtonViews() {
        RxView.clicks(btnProgressFlower)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> showFlowerProgress());
        RxView.clicks(btnProgressPie)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> showPieProgress());
        RxView.clicks(btnProgressCustom)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> showCustomProgress());
    }

    private void showFlowerProgress() {
        ACProgressFlower dialog = new ACProgressFlower.Builder(this)
                .sizeRatio(0.08f) // ratio of bgSize/smaller edge of device; default = 0.25f, max = 1
                .bgColor(Color.TRANSPARENT)
                .bgAlpha(0.0f)

//                .petalCount(4)// default = 12
                .petalThickness(5)// default = 9
                .borderPadding(0.3f)// ratio of space between pental's outer point and bg border; default = 0.55f
                .centerPadding(0.36f)// ratio of space between pental's inner point and center of bg; default = 0.27f
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY)
//                .petalAlpha(60)// transparent of pental (canh' hoa)
//                .text("Title is here")

                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .speed(9)// frames count in each second; default = 9f
                .build();

        // dialog has almost attrs like normal dialog of android system
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showPieProgress() {
        ACProgressPie dialog = new ACProgressPie.Builder(this)
                .ringColor(Color.WHITE)
                .pieColor(Color.WHITE)
                .updateType(ACProgressConstant.PIE_AUTO_UPDATE)
                .build();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showCustomProgress() {
        ACProgressCustom dialog = new ACProgressCustom.Builder(this)
                .useImages(R.drawable.p0, R.drawable.p1, R.drawable.p2, R.drawable.p3)
                .build();
        dialog.show();
    }

}
