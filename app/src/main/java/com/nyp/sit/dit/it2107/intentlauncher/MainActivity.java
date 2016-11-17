package com.nyp.sit.dit.it2107.intentlauncher;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton btnCamera, btnPlayer, btnDialer, btnSms;

    ImageView ivThumbnail;
    final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnPlayer = (ImageButton) findViewById(R.id.btnPlayer);
        btnDialer = (ImageButton) findViewById(R.id.btnDialer);
        btnSms = (ImageButton) findViewById(R.id.btnSms);

        ivThumbnail = (ImageView) findViewById(R.id.ivThumbnail);




        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivity(cameraIntent);

                startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);


            }
        });

        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 15) {
                    //Minimum api 15
                    Intent intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_MUSIC);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {
                    //minimum api 8
                    Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                    startActivity(intent);


                }


            }
        });


        btnDialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDialer = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6555-2468"));
                startActivity(intentDialer);
            }


        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+Uri.encode("97654321")));
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivThumbnail.setImageBitmap(imageBitmap);

        }



    }
}
