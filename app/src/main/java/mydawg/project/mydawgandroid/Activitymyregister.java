package mydawg.project.mydawgandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mydawg.project.mydawgandroid.models.DogRegister;

public class  Activitymyregister extends AppCompatActivity {

    //Variables
    EditText ownerName, email, dog, breed, gender, chip, birth, adoption, password;
    Button menu, save;

    //Firebase database and authentication
    DatabaseReference mFirebaseDatabase;
    FirebaseAuth mAuth;

    //ON CREATE: App starts setting the things up. It will be loaded to the phone memory.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymyregister);


        //linking variables and screen
        ownerName = findViewById(R.id.editOwner);
        email = findViewById(R.id.loginEmail);
        dog = findViewById(R.id.editDogName);
        breed = findViewById(R.id.editBreed);
        gender = findViewById(R.id.editGender);
        chip = findViewById(R.id.editChipInfo);
        birth = findViewById(R.id.editBday);
        adoption = findViewById(R.id.editAdoptionDate);
        password = findViewById(R.id.editPassword);
        save = findViewById(R.id.buttonSave);
        menu = findViewById(R.id.bmenu);

        //Initialize the Firebase instances
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //calling Object DogRegister with Getter and Setter
        final DogRegister dogRegister = new DogRegister();

        //Button calling method updateUI() to finish the activity and send back to the Login Screen
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMenu();
            }
        });

        //Button save the fields in the Strings
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ownerReg = ownerName.getText().toString();
                final String emailReg = email.getText().toString();
                final String dogReg = dog.getText().toString();
                final String breedReg = breed.getText().toString();
                final String genderReg = gender.getText().toString();
                final String chipReg = chip.getText().toString();
                final String birthReg = birth.getText().toString();
                final String adoptionReg = adoption.getText().toString();
                final String passwordReg = password.getText().toString();

                //Message letting user know that is missing some information
                // or else, creating userAccount
                if(ownerReg.isEmpty() || emailReg.isEmpty() || passwordReg.isEmpty())
                {
                    showMessage("Please, fill in name, email and password!!");
                } else{
                    CreateUserAccount(emailReg, passwordReg, ownerReg);
                }
            }
        });


    }

    //method with parameters to add in Firebase and confirm
    // or else, showing error
    public void CreateUserAccount(final String email, final String password, final String name)
    {
        //Auth in Firebase request parameters
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        showMessage("Account Created");
                        updateUserInfo(mAuth.getCurrentUser(), name);
                    } else
                        {
                        showMessage("Failed!");
                        }
                    }
                });
    }


    //method taking the new user to the Firebase
   public void updateUserInfo(final FirebaseUser currentUser, final String name)
    {

        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

         currentUser.updateProfile(profileChangeRequest)
                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {

                         if (task.isSuccessful()){

                             DogRegister createUser = new DogRegister(ownerName.getText().toString(),email.getText().toString(),dog.getText().toString(),
                                     breed.getText().toString(),gender.getText().toString(),chip.getText().toString(),birth.getText().toString(),adoption.getText().toString(),
                                     password.getText().toString());

                             addUser(createUser);
                         }
                     }
                 });
    }

    //Method using PUSH to store data as JSON object and complete the registration on Firebase
    private void addUser(DogRegister createUser) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users").push();

        databaseReference.setValue(createUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            showMessage("Registration Completed!");
            buttonMenu();
            }
        });
    }


    //Method to change the screen from Register back to Login
    private void buttonMenu()
    {
        Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(loginIntent);
        finish();
    }

    //Method to show messages
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
