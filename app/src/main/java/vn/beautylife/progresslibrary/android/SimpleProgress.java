package vn.beautylife.progresslibrary.android;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import vn.beautylife.progresslibrary.R;

/**
 * Created by vuhongky on 3/28/17.
 */

public class SimpleProgress {
    private Dialog dialog;

    public SimpleProgress(Context context) {
        initProgress(context);
    }

    private void initProgress(Context context) {
        final int padding = 30;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final FrameLayout progressLayout = new FrameLayout(context);
        progressLayout.setLayoutParams(params);
        progressLayout.setPadding(padding, padding, padding, padding);
        progressLayout.setBackgroundResource(R.color.gray);
        final ProgressBar progress = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        progressLayout.addView(progress, params);

        // We can set style here or setDialogDim like below
        // dialog = new Dialog(context, R.style.SimpleProgress);
        dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(progressLayout);
        setDialogDim(dialog);
    }

    private void setDialogDim(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            // window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setDimAmount(0.3f); // range = 0 -> 1
        }
    }

    public void show() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hide() {
        if (dialog.isShowing()) {
            dialog.hide();
        }
    }

}
