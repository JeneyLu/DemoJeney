package com.jeney.demojeney;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;


public class TransparentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Transparent);
        setContentView(R.layout.activity_transparent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MaterialDialog materialDialog =
                new MaterialDialog.Builder(this)
                        .content("test")
                        .dismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                finish();
                            }
                        })
                        .cancelable(true).build();
        materialDialog.show();
    }
}
