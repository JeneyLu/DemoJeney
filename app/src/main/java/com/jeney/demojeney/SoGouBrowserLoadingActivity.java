package com.jeney.demojeney;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jeney.uicomponent.SoGouBrowserLoading;


public class SoGouBrowserLoadingActivity extends ActionBarActivity {

    private SoGouBrowserLoading loadingView;
    private Button btn_changeColor;
    private int colors[] = new int[]{
            Color.parseColor("#ff0000"), Color.parseColor("#00ff00"),
            Color.parseColor("#0000ff"), Color.parseColor("#ffff00")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_gou_browser_loading);
        btn_changeColor = (Button) findViewById(R.id.btn_changeColor);
        loadingView = (SoGouBrowserLoading) findViewById(R.id.loadingView);
        btn_changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) (Math.random() * 4);
                loadingView.setColor(colors[index]);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_so_gou_browser_loading, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
