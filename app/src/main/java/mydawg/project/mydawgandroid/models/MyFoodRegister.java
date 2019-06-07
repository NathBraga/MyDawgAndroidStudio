package mydawg.project.mydawgandroid.models;

import android.util.Log;

import com.google.firebase.firestore.Exclude;

public class MyFoodRegister
{
    //Variables
    private String brand, store, flavour, dateOfOpening, quantity, amountADay, price;

    private String myDocument;

    //Default constructor
    public MyFoodRegister()
    {

    }

    //in order to not appear in the database, just in the object
    @Exclude
    public String getMyDocument() {
        return myDocument;
    }

    public void setMyDocument(String myDocument) {
        this.myDocument = myDocument;
        Log.d("teste", "setMyDocument: " + myDocument);
    }

    //constructor with parameters
    public MyFoodRegister(String brand, String store, String flavour,
                          String dateOfOpening, String quantity, String amountADay, String price)
    {
        this.brand = brand;
        this.store = store;
        this.flavour = flavour;
        this.dateOfOpening = dateOfOpening;
        this.quantity = quantity;
        this.amountADay = amountADay;
        this.price = price;
    }

    //Getters and Setters
    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getStore()
    {
        return store;
    }

    public void setStore(String store)
    {
        this.store = store;
    }

    public String getFlavour()
    {
        return flavour;
    }

    public void setFlavour(String flavour)
    {
        this.flavour = flavour;
    }

    public String getDateOfOpening()
    {
        return dateOfOpening;
    }

    public void setDateOfOpening(String dateOfOpening)
    {
        this.dateOfOpening = dateOfOpening;
    }

    public String getQuantity()
    {
        return quantity;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public String getAmountADay()
    {
        return amountADay;
    }

    public void setAmountADay(String amountADay)
    {
        this.amountADay = amountADay;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}
