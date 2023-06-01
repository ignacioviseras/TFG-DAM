package com.edix.grupo02_codigo_control_de_acceso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.edix.grupo02_codigo_control_de_acceso.helpers.EventsHelper;

public class Splash extends AppCompatActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EventsHelper.refresh(getApplicationContext());

        ImageView cargando = findViewById(R.id.cargando);
        Animation animacion = AnimationUtils.loadAnimation(this,R.anim.animation_splash);
        cargando.startAnimation(animacion);
        animacion.setAnimationListener(this);

    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(Splash.this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}