package vn.beautylife.progresslibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Window;
import android.view.WindowManager;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressCustom;
import cc.cloudist.acplibrary.ACProgressFlower;
import cc.cloudist.acplibrary.ACProgressPie;

public class ACProgressActivity extends AppCompatActivity {
    @BindView(R.id.btn_progress_flower)
    AppCompatButton btnProgressFlower;
    @BindView(R.id.btn_progress_pie)
    AppCompatButton btnProgressPie;
    @BindView(R.id.btn_progress_custom)
    AppCompatButton btnProgressCustom;

    public static Intent createIntent(Context context) {
        return new Intent(context, ACProgressActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_progress);
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

        // dialog has almost attrs like normal dialog, maybe we need to custom bg alpha of this dialog
        dialog.setCanceledOnTouchOutside(true);
        setDialogDim(dialog);
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
        final Integer[] seriesImg = new Integer[] {
                R.drawable.ic_heart_0, R.drawable.ic_heart_25, R.drawable.ic_heart_50,
                R.drawable.ic_heart_75, R.drawable.ic_heart_100};
        ACProgressCustom dialog = new ACProgressCustom.Builder(this)
                .useImages(seriesImg)
                .speed(5f)// default = 6.67f
                .build();
        dialog.setCanceledOnTouchOutside(true);
        setDialogDim(dialog);
        dialog.show();
    }

    private void setDialogDim(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            // window.setBackgroundDrawableResource(android.R.color.white);
            // window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setDimAmount(0.2f); // range = 0 -> 1
        }
    }

}
