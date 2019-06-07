package mydawg.project.mydawgandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class querymyfood extends AppCompatActivity {

    //Variables
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querymyfood);

        //link variables and screen
        mylistview = findViewById(R.id.listFood);


    }


}
