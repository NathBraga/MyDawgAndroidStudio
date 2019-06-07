package mydawg.project.mydawgandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //variables
    private EditText email, password;
    private TextView forgot;
    private ImageView paw;
    private Button register, bmenu;


    //Instance of Firebase for Authorization - cloud-hosted database. Data is store in Json and
    //synchronized in realtime. Even offline, the data is in the disk.
    private FirebaseAuth mAuth;

    //ON CREATE: App starts setting the things up. It will be loaded to the phone memory.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initialize the variables and screen
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.editPassword);
        forgot = findViewById(R.id.textForgot);
        paw = findViewById(R.id.imageMyPaw);
        register = findViewById(R.id.buttonRegister);
        bmenu = findViewById(R.id.bmenu);

        //Initialize the Firebase instance
        mAuth = FirebaseAuth.getInstance();

        //Alert box for FORGOT button/ textview
       forgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               alertMessage();
           }
       });

        //Register button to go to the Registration Screen
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(getApplicationContext(), Activitymyregister.class);
                startActivity(reg);
                finish();
            }
        });

        //The Paw draw is used as a button to confirm the login and access the Menu
        paw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailLogin = email.getText().toString();
                final String passwordLogin = password.getText().toString();

                //If it is the case to let the user know he forgot one field empty,
                // or else check and go to the menu
                if (emailLogin.isEmpty() || passwordLogin.isEmpty()) {
                    showMessage("Enter you email And Password!");
                } else {
                    signIn(emailLogin, passwordLogin);
                }

            }
        });


    }

    //SIGN IN EXISTING USERS: parameters in the Sign In method to takes in the email and password,
    // validates them and then signs a user in the with signInWithEmailAndPassword In method
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateUI();
                } else {
                    showMessage(task.getException().getMessage());
                }
            }
        });
    }

    //Method to change the screen for Menu
    private void updateUI() {
        Intent menuScreen = new Intent(this, MyDawgMenu.class);
        startActivity(menuScreen);
        finish();
    }

    //Method for messages
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    //Dialog message for FORGOT
    public void alertMessage() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setMessage("Send email to nathfainab@gmail.com")
                .setCancelable(true)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = alertBuilder.create();
        alert.setTitle("Forgot");
        alert.show();


    }

}





