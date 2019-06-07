package mydawg.project.mydawgandroid.models;



public class MyDog  {

    //Variables
    private String myDogName;
    private String myDogBreed;
    private String myDogGender;
    private String myDogChip;
    private String myDogBirth;
    private String myDogAdoption;
    private String myDogWeight;

    //Empty Constructor
    public MyDog()
    {

    }

    //Constructor with parameters
    public MyDog(String myDogName, String myDogBreed, String myDogGender, String myDogChip,
                 String myDogBirth, String myDogAdoption, String myDogWeight)
    {
        this.myDogName = myDogName;
        this.myDogBreed = myDogBreed;
        this.myDogGender = myDogGender;
        this.myDogChip = myDogChip;
        this.myDogBirth = myDogBirth;
        this.myDogAdoption = myDogAdoption;
        this.myDogWeight = myDogWeight;
    }

    //Getters and Setters
    public String getMyDogName() {
        return myDogName;
    }

    public void setMyDogName(String myDogName) {
        this.myDogName = myDogName;
    }

    public String getMyDogBreed() {
        return myDogBreed;
    }

    public void setMyDogBreed(String myDogBreed) {
        this.myDogBreed = myDogBreed;
    }

    public String getMyDogGender() {
        return myDogGender;
    }

    public void setMyDogGender(String myDogGender) {
        this.myDogGender = myDogGender;
    }

    public String getMyDogChip() {
        return myDogChip;
    }

    public void setMyDogChip(String myDogChip) {
        this.myDogChip = myDogChip;
    }

    public String getMyDogBirth() {
        return myDogBirth;
    }

    public void setMyDogBirth(String myDogBirth) {
        this.myDogBirth = myDogBirth;
    }

    public String getMyDogAdoption() {
        return myDogAdoption;
    }

    public void setMyDogAdoption(String myDogAdoption) {
        this.myDogAdoption = myDogAdoption;
    }

    public String getMyDogWeight() {
        return myDogWeight;
    }

    public void setMyDogWeight(String myDogWeight) {
        this.myDogWeight = myDogWeight;
    }
}