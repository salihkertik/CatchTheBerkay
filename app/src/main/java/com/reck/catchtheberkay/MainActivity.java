package com.reck.catchtheberkay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;  // heryerden çağırabilmek için evrensel kısıma yazıyoruz ve ->
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageArray;
    Handler handler;      //runnable ı kullanabilmemizi sağlayan sınıf
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize

        timeText = (TextView) findViewById(R.id.timeText); // burada 1 kere başlatıyoruz ve artık heryerden timeText. diyerek çağırabiliyoruz.
        scoreText = (TextView) findViewById(R.id.scoreText);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();
        score = 0;

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l)
            {
                timeText.setText("Time: "+l/1000);
            }

            @Override
            public void onFinish()
            {
                timeText.setText("Süre Bitti !");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrar Oyna?");
                alert.setMessage("Tekrar Oynamak İstediğine Emin misin?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        // restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Toast.makeText(MainActivity.this, "Oyun Bitti!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view)
    {
        score++;

        scoreText.setText("Score: "+score);

    }

    public void hideImages()
    {
        handler=new Handler();
        runnable=new Runnable()
        {
            @Override
            public void run()
            {
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);


    }

}