package mydawg.project.mydawgandroid.models;

import com.google.firebase.firestore.Exclude;

public class MyVetRegister {

    //variables to create getter and setter - REGISTER
    private String myVet;
    private String myCompany;
    private String myPhone;
    private String myPhoneOption;
    private String myEmail;
    private String myDocument;

    //Default Constructor
    public MyVetRegister() {
    }

    //in order to not appear in the database, just in the object
    @Exclude
    public String getMyDocument() {
        return myDocument;
    }

    public void setMyDocument(String myDocument) {
        this.myDocument = myDocument;
    }

    //Constructor with Parameters
    public MyVetRegister(String myVet, String myCompany, String myPhone, String myPhoneOption, String myEmail) {
        this.myVet = myVet;
        this.myCompany = myCompany;
        this.myPhone = myPhone;
        this.myPhoneOption = myPhoneOption;
        this.myEmail = myEmail;
    }

    //Getters and Setters
    public String getMyVet() {
        return myVet;
    }

    public void setMyVet(String myVet) {
        this.myVet = myVet;
    }

    public String getMyCompany() {
        return myCompany;
    }

    public void setMyCompany(String myCompany) {
        this.myCompany = myCompany;
    }

    public String getMyPhone() {
        return myPhone;
    }

    public void setMyPhone(String myPhone) {
        this.myPhone = myPhone;
    }

    public String getMyPhoneOption() {
        return myPhoneOption;
    }

    public void setMyPhoneOption(String myPhoneOption) {
        this.myPhoneOption = myPhoneOption;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }
}