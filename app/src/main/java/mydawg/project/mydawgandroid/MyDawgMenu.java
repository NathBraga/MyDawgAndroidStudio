package mydawg.project.mydawgandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MyDawgMenu extends AppCompatActivity {


    //variables
    TextView xclose;
    ImageView myfood, mydog, myvet, myevents, mydonation, myalarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dawg_menu);

        //link the variables and images
        myfood = findViewById(R.id.imagemyfood);
        mydog = findViewById(R.id.imagemydog);
        myvet = findViewById(R.id.imagemyvet);
        myevents = findViewById(R.id.imageevents);
        mydonation = findViewById(R.id.imagemydonation);
        myalarm = findViewById(R.id.imagemyalarm);
        xclose = findViewById(R.id.textMenuClose);

        //method X to close App
        appClose();
    }

    //in the activity Menu, design, set for each image the OnClick method below
    public void menuOptions(View v) {

        switch (v.getId()) {
            //it sends to MyFood
            case R.id.imagemyfood:
                Intent intentfood = new Intent(this, Activitymyfood.class);
                startActivity(intentfood);
                break;

            //it sends to dog's information
            case R.id.imagemydog:
                Intent intentdog = new Intent(this, Activitymydog.class);
                startActivity(intentdog);
                break;

            //it sends to my vet information
            case R.id.imagemyvet:
                Intent intentvet = new Intent(this, Activitymyvet.class);
                startActivity(intentvet);
                break;

            //it sends to my events information
            case R.id.imageevents:
                Intent intentevents = new Intent(this, MyDogsMusic.class);
                startActivity(intentevents);
                break;

            //it sends to my donation plan
            case R.id.imagemydonation:
                Intent intentdonation = new Intent(this, Activitymydonation.class);
                startActivity(intentdonation);
                break;

            //it sends to the alarm
            case R.id.imagemyalarm:
                Intent intentalarm = new Intent(this, Activitymycalendar.class);
                startActivity(intentalarm);
                break;

        }

    }


    //Method to close the APP
    public void appClose()
    {
        xclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}

