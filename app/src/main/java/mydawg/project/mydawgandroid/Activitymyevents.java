package mydawg.project.mydawgandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class Activitymyevents extends AppCompatActivity {

    //Internet webview
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymyevents);


        //Link
        webview = findViewById(R.id.webview);

        //Call DDR Events website
        ddrEvents();

    }

    //Method to log in with DDR website Events
    public void ddrEvents()
    {
        Toast.makeText(Activitymyevents.this, "Redirecting...",
                Toast.LENGTH_LONG).show();

        Intent ddrWebpage = new Intent(Intent.ACTION_VIEW,
               Uri.parse("https://www.dundalkdogrescue.ie/events/"));
               startActivity(ddrWebpage);
               finish(); //if I do not use finish, the page in blank will be shown
    }

}
