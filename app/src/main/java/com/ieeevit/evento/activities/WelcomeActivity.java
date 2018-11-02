package com.ieeevit.evento.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.ieeevit.evento.R;

import io.fabric.sdk.android.Fabric;

import static maes.tech.intentanim.CustomIntent.customType;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_welcome);
        final SharedPreferences sharedPreferences = getSharedPreferences("Details", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("InEvent", false)) {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            customType(WelcomeActivity.this, "bottom-to-top");
            finishAfterTransition();
        }

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, EventIDActivity.class);
                startActivity(intent);
                customType(WelcomeActivity.this, "left-to-right");
                finishAfterTransition();
            }
        });
    }
}
