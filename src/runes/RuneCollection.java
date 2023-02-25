package runes;

import java.util.ArrayList;

public class RuneCollection {
    private ArrayList<Rune> collection;
    private int userid;
    public RuneCollection(int userid){
        this.userid = userid;
        this.collection = new ArrayList<Rune>();
    }
    private int getUserId(){
        return this.userid;
    }
    public void displayCollection(){
        for(Rune r : this.collection)
            System.out.print(r);
    }

    public ArrayList<Rune> getUserCollection(int u){
        if(u != this.getUserId()) return null;
        return this.collection;
    }
    public void addRune(Rune r){this.collection.add(r);}
    public Rune getRune(int i){
        try{
            return this.collection.get(i);
        }catch(Exception e){
            System.out.println("Error retrieving rune: " + e);
        }
        return null;
        }

}

