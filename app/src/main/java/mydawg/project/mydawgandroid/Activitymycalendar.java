package mydawg.project.mydawgandroid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Activitymycalendar extends AppCompatActivity
{

    //Variable
    private Button bjalarm, bjmenu;
    private String reminder = "MyDawg Reminder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymycalendar);

        //link the java and screen
        bjalarm = findViewById(R.id.bRemAlarm);
        bjmenu = findViewById(R.id.bRemMenu);

        //Method for Menu button
        bMenuClicked();

        //Button alarm to sends the App to the Alarm in the mobile
        bjalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
                intentAlarm.putExtra(AlarmClock.EXTRA_MESSAGE, String.valueOf(reminder));
                startActivity(intentAlarm);
                finish(); //used to bring the user back to the app after setting the alarm
            }
        });
    }

    //Method for Button Menu clicked
    public void bMenuClicked()
    {
        bjmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Menu");
                finish();
            }
        });
    }

    //Method to show messages
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}



