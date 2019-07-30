package com.android.flightapp.View;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.flightapp.R;

public class NoSchedulesGotten extends AppCompatActivity {

    @BindView(R.id.button_try)
    Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_schedules_gotten);

        ButterKnife.bind(this);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        this.getWindow().setFlags(uiOptions,0);

        Window window = this.getWindow();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            //This single line of code sets the status bar to alert
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
    }

    @OnClick(R.id.button_try)
    public void openMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
