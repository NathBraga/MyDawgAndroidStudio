package mydawg.project.mydawgandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SuportedDog extends AppCompatActivity {

    //Variables
    private Button bjSMenu, bjSupSox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suported_dog);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //link
                bjSMenu = findViewById(R.id.bSupMenu);
                bjSupSox = findViewById(R.id.bSupportSox);

                //Method to call menu
                bMenuSupClicked();
                supDogs();

            }
        });
    }


    public void supDogs()
    {
     bjSupSox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuportedDog.this, "Redirecting...",
                        Toast.LENGTH_LONG).show();

                Intent supDogWeb = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.dundalkdogrescue.ie/donate/"));
                startActivity(supDogWeb);
            }
        });
    }

    //Method for Button to go back to Menu screen
    private void bMenuSupClicked() {
        bjSMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Menu");
                finish();
            }
        });
    }

    //Method to show messages
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
