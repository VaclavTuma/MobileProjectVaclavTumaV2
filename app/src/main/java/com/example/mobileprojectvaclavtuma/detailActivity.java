package com.example.mobileprojectvaclavtuma;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class detailActivity extends AppCompatActivity {

    boolean chacked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.mobileprojectvaclavtuma.ITEM_INDEX", -1);
        chacked = in.getBooleanExtra("com.example.mobileprojectvaclavtuma.DARK_MODE",false);

        if (index > -1) {
            int pic = getImg(index);
            ImageView img = findViewById(R.id.imageView);
            scaleImg(img, pic);
        }

        ConstraintLayout layoutID = findViewById(R.id.layoutID);
        if(chacked){
            layoutID.setBackgroundColor(getResources().getColor(R.color.darkGray));
        }
        else{
            layoutID.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private int getImg(int index) {
        switch (index) {
            case 0:
                return R.drawable.karlstejn;
            case 1:
                return R.drawable.hluboka;
            case 2:
                return R.drawable.lednice;
            case 3:
                return R.drawable.bouzov;
            default:
                return -1;
        }
    }

    private void scaleImg(ImageView img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth) {
            int ratio = Math.round((float) imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);

        img.setImageBitmap(scaledImg);
    }
}