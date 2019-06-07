package mydawg.project.mydawgandroid.models;



public class DogRegister
{
    //variables to create getter and setter - REGISTER
    private String ownerName;
    private String email;
    private String dogsName;
    private String breed;
    private String gender;
    private String chipInformation;
    private String bday;
    private String adoptionDate;
    private String password;

    //Constructor with parameters to call the data
    public DogRegister(String ownerName, String email, String dogsName, String breed, String gender, String chipInformation, String bday, String adoptionDate, String password) {
        this.ownerName = ownerName;
        this.email = email;
        this.dogsName = dogsName;
        this.breed = breed;
        this.gender = gender;
        this.chipInformation = chipInformation;
        this.bday = bday;
        this.adoptionDate = adoptionDate;
        this.password = password;
    }

    //Default constructor
    public DogRegister()
    {

    }

    //Getters and Setters
    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getEmail()
    {
        return email;

    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getDogsName()
    {
        return dogsName;
    }

    public void setDogsName(String dogsName)
    {
        this.dogsName = dogsName;
    }

    public String getBreed()
    {
        return breed;
    }

    public void setBreed(String breed)
    {
        this.breed = breed;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getChipInformation()
    {
        return chipInformation;
    }

    public void setChipInformation
            (String chipInformation)
    {
        this.chipInformation = chipInformation;
    }

    public String getBday()
    {
        return bday;
    }

    public void setBday(String bday)
    {
        this.bday = bday;
    }

    public String getAdoptionDate()
    {
        return adoptionDate;
    }

    public void setAdoptionDate(String  adoptionDate)
    {
        this.adoptionDate = adoptionDate;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
