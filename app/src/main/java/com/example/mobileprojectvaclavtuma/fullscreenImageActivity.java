package com.example.mobileprojectvaclavtuma;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class fullscreenImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        ImageView fullscreenImageView = findViewById(R.id.imageView);

        // Načtení obrázku z intentu
        int imageResId = getIntent().getIntExtra("imageResId", -1);
        if (imageResId != -1) {
            fullscreenImageView.setImageResource(imageResId);
        }

        // Kliknutí pro zavření
        fullscreenImageView.setOnClickListener(v -> finish());
    }
}
