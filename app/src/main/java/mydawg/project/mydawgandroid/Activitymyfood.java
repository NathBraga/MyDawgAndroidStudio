package mydawg.project.mydawgandroid;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.Map;

import mydawg.project.mydawgandroid.models.MyFoodRegister;

public class Activitymyfood extends AppCompatActivity {

    //final variables
    private static final String TAG = "message";
    private static final String FBRAND_KEY = "Brand: ";
    private static final String FSTORE_KEY = "Store: ";
    private static final String FFLAVOUR_KEY = "Flavour";
    private static final String FDATEOPENING_KEY = "Open Date: ";
    private static final String FQUANTITY_KEY = "Quantity: ";
    private static final String FAMOUNT_KEY = "Amount per Day: ";
    private static final String FPRICE_KEY = "Price: ";
    private static final String FOODMESSAGE_KEY = "Remember that every dog is different.\n" +
            " As a general guide we advise offering " +
            "the daily amount of food recommended for" +
            " the ideal bodyweight of your dog in the" +
            " feeding guide on the pack.";

    //Variables
    private Button bjfoodMenu, bjfoodShow, bjAddFood;
    private EditText foodJBbrand, foodjStore, foodjFlavour,
                    foodJDatePurchase, foodjQuantity, foodjAmount, foodjPrice;
    private TextView showData;
    private ListView mylistview;


    //Call Firebase
    FirebaseFirestore foodFire = FirebaseFirestore.getInstance();
    private CollectionReference myFoodColRef = foodFire.collection("foods");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymyfood);

        //Linking with screen
        foodJBbrand = findViewById(R.id.editBrand);
        foodjFlavour = findViewById(R.id.editFlavour);
        foodjStore = findViewById(R.id.editStore);
        foodjPrice = findViewById(R.id.editPrice);
        foodjAmount = findViewById(R.id.editAmountADay);
        foodjQuantity = findViewById(R.id.editQuantity);
        foodJDatePurchase = findViewById(R.id.editDateOfPurchase);
        bjfoodMenu = findViewById(R.id.bFoodMenu);
        bjfoodShow = findViewById(R.id.bFoodShow1);
        bjAddFood = findViewById(R.id.buttonNew);
        showData = findViewById(R.id.textShowData);
        mylistview = findViewById(R.id.listviewFoods);

        //Calling the Object MyFoodRegister
        //  MyFoodRegister myFood = new MyFoodRegister();

        //Calling method with alert message when opening the Food Menu
        alertMessage();

        //calls method Button Menu clicked
        bMenuClicked();

        //Calling method to show Foods
        //bFoodShowClicked();

        bjAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create variable for get information
                final String foodAddBrand = foodJBbrand.getText().toString();
                final String foodAddFlavour = foodjFlavour.getText().toString();
                final String foodAddStore = foodjStore.getText().toString();
                final String foodAddPrice = foodjPrice.getText().toString();
                final String foodAddAmount = foodjAmount.getText().toString();
                final String foodAddQuantity = foodjQuantity.getText().toString();
                final String foodAddDatePurchase = foodJDatePurchase.getText().toString();

                //Method to get the info and create in the Firebase
                createFoodInfo( foodAddBrand, foodAddFlavour, foodAddStore,
                                foodAddDatePurchase, foodAddQuantity,
                                foodAddAmount, foodAddPrice);

                showMessage("New Food Added");
            }
        });

    }

    //Method to show the foods on firebase
    public void bFoodShowClicked(View view)
    {

                //Query to bring the collections in foods
                /*foodFire.collection("foods")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        showData.setText("Food" + document);
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });*/
        myFoodColRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                String emptyData = "";

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Log.d(TAG, "test: " + documentSnapshot);
                    MyFoodRegister myfood = documentSnapshot.toObject(MyFoodRegister.class);
                    Log.d(TAG, "test: " + myfood);
                    myfood.setMyDocument(documentSnapshot.getId());
                    Log.d(TAG, "test: " + myfood.getBrand());

                    String mydocument = myfood.getMyDocument();

                    String brandReg = myfood.getBrand();
                    String storeReg = myfood.getStore();
                    String flavourReg = myfood.getFlavour();
                    String dateOfOpeningReg = myfood.getDateOfOpening();
                    String quantityReg = myfood.getQuantity();
                    String amountADayReg = myfood.getAmountADay();
                    String priceReg = myfood.getPrice();
                    Log.d(TAG, "test: " + storeReg);

                    emptyData += "FoodID: " + mydocument + "\n"
                            + "Brand: " + brandReg + "\n"
                            + "Flavour: " + flavourReg + "\n"
                            + "Store: " + storeReg + "\n"
                            + "Quantity: " + quantityReg + "\n"
                            + "Price: " + priceReg + "\n"
                            + "Amount a day: " + amountADayReg + "\n"
                            + "Opening date: " + dateOfOpeningReg + "\n\n";

                    showData.setText(emptyData);
                }
            }
        });
        showMessage("Foods");

            }

    //method to take the getters and link with screen
    private void createFoodInfo(String brand, String flavour, String store,
                                String dateOfOpening, String quantity,
                                String amountADay, String price)
    {

        //Create new food with fields typed
        Map<String, Object> foodInformation = new HashMap<>();
        foodInformation.put(FBRAND_KEY, brand);
        foodInformation.put(FSTORE_KEY, store);
        foodInformation.put(FFLAVOUR_KEY, flavour);
        foodInformation.put(FDATEOPENING_KEY, dateOfOpening);
        foodInformation.put(FQUANTITY_KEY, quantity);
        foodInformation.put(FAMOUNT_KEY, amountADay);
        foodInformation.put(FPRICE_KEY, price);

        foodFire.collection("foods")
                .add(foodInformation).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Food Inserted!!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding food!!", e);
            }
        })
        ;
    }

    //Method for Button to go back to Menu screen
    private void bMenuClicked() {
        bjfoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Menu");
                finish();
            }
        });
    }

    //method to show the Alert message about food
    private void alertMessage() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Activitymyfood.this);
        alertBuilder.setMessage(FOODMESSAGE_KEY)
                .setCancelable(true)
                .setPositiveButton("Menu", new DialogInterface.OnClickListener() {
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
        alert.setTitle("Food Alert");
        alert.show();
    }


    //Method to show messages
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}


