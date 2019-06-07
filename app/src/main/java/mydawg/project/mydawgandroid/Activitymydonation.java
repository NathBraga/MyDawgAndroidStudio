package mydawg.project.mydawgandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activitymydonation extends AppCompatActivity {

    //Variables
    TextView textDon, textSup, textDDR;

    Button bmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymydonation);

        //link variables with screen
        textDon = findViewById(R.id.textDonate);
        textSup = findViewById(R.id.textSupport);
        textDDR = findViewById(R.id.textDDRInfo);

        bmenu = findViewById(R.id.bDonateMenu);

        //calls method Donation, Support and Monthly
        webDonate();
        ddrContact();

        //calls method Button Menu clicked
        bMenuClicked();


    }

    //Method for Button Menu clicked
    public void bMenuClicked()
    {
        bmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activitymydonation.this, "Menu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activitymydonation.this, MyDawgMenu.class);
                startActivity(intent);
            }
        });
    }

    //Method for Donation webpage
    public void webDonate()
    {
        textDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activitymydonation.this, "Redirecting...",
                        Toast.LENGTH_LONG).show();

                Intent ddrWebDonate = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.dundalkdogrescue.ie/donate/"));
                startActivity(ddrWebDonate);
            }
        });

    }

    //Method for Donation webpage
    public void webDonSupport(View view)
    {

        /*Intent intent = new Intent(this, SuportedDog.class);
        startActivity(intent);*/
        textSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activitymydonation.this, "Redirecting...",
                        Toast.LENGTH_LONG).show();

                Intent ddrWebSupport = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.dundalkdogrescue.ie/donate/"));
                startActivity(ddrWebSupport);
            }
        });
    }

    //Method for DDR contact
    public void ddrContact()
    {
        textDDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activitymydonation.this, "Redirecting...",
                        Toast.LENGTH_LONG).show();

                Intent ddrWebMonthly = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.dundalkdogrescue.ie/contact/"));
                startActivity(ddrWebMonthly);
            }
        });
    }

    }
