package mydawg.project.mydawgandroid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import mydawg.project.mydawgandroid.models.MyVetRegister;


public class Activitymyvet extends AppCompatActivity {

    //static final are not going to change ever, they are the title
    private static final String TAG = "message";

    //Variables
    private Button bjVetMenu;
    private EditText myCompany, myVetName, myPhone, myPhoneOption, myEmail;
    private TextView textViewVet;

    //Initialize the path to the Collection on Cloud and using Document Path
    //Vet1
    private FirebaseFirestore myVetFire = FirebaseFirestore.getInstance();
    private CollectionReference myVetColRef = myVetFire.collection("MyVetsCollection");
    private DocumentReference myVetDocRef = myVetFire
            .document("MyVetsCollection/MyVetsDocument");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymyvet);

        //Link variables and screen
        bjVetMenu = findViewById(R.id.bVetMenu);
        myVetName = findViewById(R.id.editVetsName);
        myCompany = findViewById(R.id.editVetsCompany);
        myPhone = findViewById(R.id.editPhone);
        myPhoneOption = findViewById(R.id.editPhoneOption);
        myEmail = findViewById(R.id.loginEmail);
        textViewVet = findViewById(R.id.textView);

        //calls method Button Menu clicked
        bMenuClicked();

    }

    public void addVet(View v)
    {
        String vetReg = myVetName.getText().toString();
        String companyReg = myCompany.getText().toString();
        String phoneReg = myPhone.getText().toString();
        String phoneOptionReg = myPhoneOption.getText().toString();
        String emailReg = myEmail.getText().toString();

        MyVetRegister myvet = new MyVetRegister(vetReg, companyReg, phoneReg,
                                phoneOptionReg, emailReg);

        myVetColRef.add(myvet).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                showMessage("Vet added");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, e);
                    }
                });

    }

    public void loadVets(View v)
    {
        myVetColRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                String emptyData = "";

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    MyVetRegister myvet = documentSnapshot.toObject(MyVetRegister.class);
                    myvet.setMyDocument(documentSnapshot.getId());

                    String mydocument = myvet.getMyDocument();

                    String vetReg = myvet.getMyVet();
                    String companyReg = myvet.getMyCompany();
                    String phoneReg = myvet.getMyPhone();
                    String phoneOptionReg = myvet.getMyPhoneOption();
                    String emailReg = myvet.getMyEmail();

                    emptyData += "VetID: " + mydocument + "\n"
                            + "Vet Name: " + vetReg + "\n"
                            + "Company: " + companyReg + "\n"
                            + "Phone: " + phoneReg + "\n"
                            + "Phone optional: " + phoneOptionReg + "\n"
                            + "Email: " + emailReg + "\n\n";

                    textViewVet.setText(emptyData);
                }
            }
        });
        showMessage("Vets");
    }

    //Method for Button Menu clicked
    private void bMenuClicked()
    {
        bjVetMenu.setOnClickListener(new View.OnClickListener() {
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