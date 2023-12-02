package org.example;

public class LibraryCatalog extends Library{
    //attributes
    private String Title;
    private String Author;
    private String PDate;
    private String Genre;
    private int Amount;
    private Boolean Availability;

    //Methods

    //default LibraryCatalog
    public LibraryCatalog(){

    }
    //Overloaded LibraryCatalog constructor with parameters
    public LibraryCatalog(String ID, String Title, String Author, String PDate, String Genre, int Amount){
        super(ID);
        this.Title = Title;
        this.Author = Author;
        this.PDate = PDate;
        this.Genre = Genre;
        this.Amount = Amount;
        this.Availability = Amount > 0;

    }
    @Override
    public void setID (String ID){
        super.setID(ID);
    }
    public void setTitle(String title){
        this.Title = title;
    }
    public void setAuthor(String author){
        this.Author = author;
    }
    public void setPDate(String pdate){
        this.PDate = pdate;
    }
    public void setGenre(String genre){
        this.Genre = genre;
    }
    public void setAmount(int amount){
        this.Amount = amount;
    }
    public void setAvailability (Boolean availability){
        this.Availability = availability;
    }

    @Override
    public String getID(){
        return super.getID();
    }
    public String getTitle(){
        return Title;
    }
    public String getAuthor(){
        return Author;
    }
    public String getPDate(){
        return PDate;
    }
    public String getGenre(){
        return Genre;
    }
    public int getAmount(){
        return Amount;
    }
    public Boolean getAvailability(){
        return Availability;
    }
    //delete catalog method
    public void delCatalog(){
        super.setID(null);
        this.Title = null;
        this.Author = null;
        this.PDate = null;
        this.Genre = null;
        this.Amount = 0;
        this.Availability = null;
    }
}

