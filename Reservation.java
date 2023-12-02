package org.example;

public class Reservation extends LibraryCatalog{
    private String Title;
    private String Name;
    private String ReserveDate;
    private String ReturnDate;

    public Reservation(){

    }

    public Reservation (String Title, String Name, String ReserveDate, String ReturnDate){
        this.Title = Title;
        this.Name = Name;
        this.ReserveDate = ReserveDate;
        this.ReturnDate = ReturnDate;
    }

    public void setTitle(String Title){
        this.Title = Title;
    }

    public void setName (String Name){
        this.Name = Name;
    }

    public void setReserveDate(String ReserveDate){
        this.ReserveDate = ReserveDate;
    }
    public void setReturnDate(String ReturnDate){
        this.ReturnDate = ReturnDate;
    }

    public String getTitle(){
        return Title;
    }
    public String getName(){
        return Name;
    }
    public String getReserveDate(){
        return ReserveDate;
    }
    public String getReturnDate(){
        return ReturnDate;
    }
}

