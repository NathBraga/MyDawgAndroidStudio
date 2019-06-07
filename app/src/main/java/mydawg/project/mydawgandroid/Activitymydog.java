package mydawg.project.mydawgandroid;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import java.util.HashMap;
import java.util.Map;
import mydawg.project.mydawgandroid.models.MyDog;


public class Activitymydog extends AppCompatActivity
{
    //static final are not going to change ever, they are the title
    private static final String TAG = "message";
    private static final String DOGNAME_KEY = "Dog's Name: ";
    private static final String BREED_KEY = "Breed: ";
    private static final String CHIP_KEY = "Chip Information: ";
    private static final String GENDER_KEY = "Gender: ";
    private static final String DATEBIRTH_KEY = "Birthday Date: ";
    private static final String DATEADOPTION_KEY = "Adoption Date: ";
    private static final String WEIGHT_KEY = "Weight: ";

    //Variables
    private Button bjDogmenu, bjsave, bjDogShow;
    private EditText    editjDogName, editjDogBreed, editjDogChip,  editjDogBirth,
                        editjDogGender, editjDogAdoption, editjDogWeight;

    //Initialize the path to the Collection on Cloud and using Document Path
    //Vet1
    private DocumentReference myDogRef = FirebaseFirestore.getInstance()
              .document("MyDog/MyDog1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymydog);

        //link screen and java
        bjDogmenu = findViewById(R.id.bDogMenu);
        bjDogShow = findViewById(R.id.bDogShow);
        bjsave = findViewById(R.id.bDogSave);
        editjDogName = findViewById(R.id.editDogName);
        editjDogBreed = findViewById(R.id.editDogBreed);
        editjDogChip = findViewById(R.id.editChipInfo);
        editjDogGender = findViewById(R.id.editDogGender);
        editjDogBirth = findViewById(R.id.editBirthDate);
        editjDogAdoption = findViewById(R.id.editAdoptionDate);
        editjDogWeight = findViewById(R.id.editWeight);

        //Call the Object MyDog
        MyDog mydog = new MyDog();

        //Button for Menu
        bMenuClicked();

        bjsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting final input on Vet register
                final String dogname = editjDogName.getText().toString();
                final String dogbreed = editjDogBreed.getText().toString();
                final String doggender = editjDogGender.getText().toString();
                final String dogchip = editjDogChip.getText().toString();
                final String dogbirth = editjDogBirth.getText().toString();
                final String dogadoption = editjDogAdoption.getText().toString();
                final String dogweight = editjDogWeight.getText().toString();

                //Calling method to create the Vet in the Cloud Firebase
                CreateDogInfo(  dogname, dogbreed, doggender, dogchip, dogbirth,
                                dogadoption, dogweight);

            }
        });
    }

    private void CreateDogInfo(String dogname, String dogbreed, String doggender,
                               String dogchip, String dogbirth, String dogadoption,
                               String dogweight)
    {
        showMessage("Dog Saved");
        // Create a new dog with the fields
        Map<String, Object> dogInformation = new HashMap<>();
        dogInformation.put(DOGNAME_KEY, dogname);
        dogInformation.put(BREED_KEY, dogbreed);
        dogInformation.put(GENDER_KEY, doggender);
        dogInformation.put(CHIP_KEY, dogchip);
        dogInformation.put(DATEBIRTH_KEY, dogbirth);
        dogInformation.put(DATEADOPTION_KEY, dogadoption);
        dogInformation.put(WEIGHT_KEY, dogweight);


        myDogRef.set(dogInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Dog Information saved");
            }

        })
                .addOnFailureListener(new OnFailureListener() {
                                          @Override
                                          public void onFailure(@NonNull Exception e) {
                                              Log.w(TAG, "Error, Dog not Saved");
                                          }
    });

}

    //Method to edit the weight... trick is to insert the name and you also can change
    public void updateDogsInfo(View v)
    {
        String dogUpdate = editjDogName.getText().toString();
        String weightUpdate = editjDogWeight.getText().toString();

        Map<String, Object> dogNew = new HashMap<>();
        dogNew.put(DOGNAME_KEY, dogUpdate);
        dogNew.put(WEIGHT_KEY, weightUpdate);

        myDogRef.set(dogNew, SetOptions.merge());
        showMessage("Information updated!!!");
    }

    //buttom to show/set the Vet 1
    public void showInf(View view) {

        bjDogShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDogRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            String showDogName = documentSnapshot.getString(DOGNAME_KEY);
                            String showBreed = documentSnapshot.getString(BREED_KEY);
                            String showChip = documentSnapshot.getString(CHIP_KEY);
                            String showGender = documentSnapshot.getString(GENDER_KEY);
                            String showBirth = documentSnapshot.getString(DATEBIRTH_KEY);
                            String showAdoption = documentSnapshot.getString(DATEADOPTION_KEY);
                            String showWeight = documentSnapshot.getString(WEIGHT_KEY);
                            editjDogName.setText(showDogName);
                            editjDogBreed.setText(showBreed);
                            editjDogChip.setText(showChip);
                            editjDogGender.setText(showGender);
                            editjDogBirth.setText(showBirth);
                            editjDogAdoption.setText(showAdoption);
                            editjDogWeight.setText(showWeight);

                        }
                    }
                });

        }
    });
    }

    //Method for Button Menu clicked
    public void bMenuClicked()
    {
        bjDogmenu.setOnClickListener(new View.OnClickListener() {
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

