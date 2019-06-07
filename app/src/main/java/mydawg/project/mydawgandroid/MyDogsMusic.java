package mydawg.project.mydawgandroid;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mydawg.project.mydawgandroid.models.MyDog;

public class MyDogsMusic extends AppCompatActivity {

    //Constants variables
    private final int NR_OF_SOUNDS_KEY = 7;
    private final float VOLUME_LEFT_KEY = 1.0f;
    private final float VOLUME_RIGHT_KEY = 1.0f;
    private final int LOOP_KEY = 0;
    private final int PRIORITY_KEY = 0;
    private final float PLAY_KEY = 1.0f;

    //variables sound
    private int dog1Sound;
    private int dog2Sound;
    private int dog3Sound;
    private int dog4Sound;
    private int dog5Sound;
    private int dog6Sound;
    private int dog7Sound;

    //variables screen
    Button bjEvent, bjEventDonation, bjEventmenu;

    private SoundPool soundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dogs_music);


        //link with screen
        bjEvent = findViewById(R.id.bEvent);
        bjEventDonation = findViewById(R.id.bDonation);
        bjEventmenu = findViewById(R.id.bEventMenu);

        //Initialize the soundpool - it is crossed because it is the old way deprecated
        soundPool = new SoundPool(NR_OF_SOUNDS_KEY, AudioManager.STREAM_MUSIC, 0);


        //It is not just bring the sound, we have to load() using more code. This code is
        // available on Developer website however with a new form
        dog1Sound = soundPool.load(getApplicationContext(), R.raw.note1_c, 1);
        dog2Sound = soundPool.load(getApplicationContext(), R.raw.note2_d, 1);
        dog3Sound = soundPool.load(getApplicationContext(), R.raw.note3_e, 1);
        dog4Sound = soundPool.load(getApplicationContext(), R.raw.note4_f, 1);
        dog5Sound = soundPool.load(getApplicationContext(), R.raw.note5_g, 1);
        dog6Sound = soundPool.load(getApplicationContext(), R.raw.note6_a, 1);
        dog7Sound = soundPool.load(getApplicationContext(), R.raw.note7_b, 1);


        //Method to go back to the menu
        bMenuClicked();

        //Method to go to the Events Website
        bEventsClicked();

        //Method to go to the donation website
        bDonationClicked();

    }

    //Method to make the draw plays
    public void playC(View v)
    {
        Log.d("MyDawg", "First picture clicked");
        soundPool.play(dog1Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }

    //Method to make the draw plays
    public void playD(View v)
    {
        Log.d("MyDawg", "Second picture clicked");
        soundPool.play(dog2Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }

    //Method to make the draw plays
    public void playE(View v)
    {
        Log.d("MyDawg", "Third picture clicked");
        soundPool.play(dog3Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }

    //Method to make the draw plays
    public void playF(View v)
    {
        Log.d("MyDawg", "Fourth picture clicked");
        soundPool.play(dog4Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }

    //Method to make the draw plays
    public void playG(View v)
    {
        Log.d("MyDawg", "Fifth picture clicked");
        soundPool.play(dog5Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }

    //Method to make the draw plays
    public void playA(View v)
    {
        Log.d("MyDawg", "Sixth picture clicked");
        soundPool.play(dog6Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }

    //Method to make the draw plays
    public void playB(View v)
    {
        Log.d("MyDawg", "Seventh picture clicked");
        soundPool.play(dog7Sound, VOLUME_LEFT_KEY, VOLUME_RIGHT_KEY,
                PRIORITY_KEY, LOOP_KEY, PLAY_KEY);
    }


    //Method go back to the menu
    private void bMenuClicked() {
        bjEventmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Menu");
                finish();
            }
        });
    }

    //Method go to the Events Website
    private void bEventsClicked() {
        bjEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyDogsMusic.this, Activitymyevents.class);
                startActivity(intent);
                showMessage("Redirecting...");
                finish();
            }
        });
            }

    //Method go to the Events Website
    private void bDonationClicked() {
        bjEventDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(MyDogsMusic.this, Activitymydonation.class);
             startActivity(intent);
             showMessage("Donation");
        }
        });
    }

    //Method to show messages
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
