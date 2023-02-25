package runes;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;

public class Rune implements Comparable<Rune> {

    private Grade grade;
    private MainStat mainStat;
    private Set set;
    public Pos pos;
    private boolean innate;
    public ArrayList<SubStat> subs;
    public boolean isEquipped;



    private void setGrade(String x){
        int grade = Integer.parseInt(x);
        if(grade==6)this.grade = Grade.six;
        else this.grade = Grade.five;
    }
    public Grade getGrade(){
        return this.grade;
    }

    private void setMainStat(String stat, String g){
        if(stat.equals("ATK") || stat.equals("DEF") || stat.equals("HP"))
          this.mainStat = new MainStat(stat, g);
        else this.mainStat = new MainStat(stat.toLowerCase(), g);
    }
    public MainStat getMainStat(){ return this.mainStat; }

    public void setSet(String set){
        set = set.toLowerCase();
        switch(set){
            case "violent": this.set = Set.violent; break;
            case "swift": this.set = Set.swift; break;
            case "rage": this.set = Set.rage; break;
            case "blade": this.set = Set.blade; break;
            case "will": this.set = Set.will; break;
            case "nemesis": this.set = Set.nemesis; break;

            default: this.set = null; break;
        }

    }
    public Set getSet(){
        return this.set;
    }

    private void setPos(String pos){
        switch(pos){
            case "1": this.pos = Pos.one;break;
            case "2": this.pos = Pos.two;break;
            case "3": this.pos = Pos.three;break;
            case "4": this.pos = Pos.four;break;
            case "5": this.pos = Pos.five;break;
            case "6": this.pos = Pos.six;break;
        }
    }
    public Pos getPos(){
        return this.pos;
    }
    public int getPosInt(){
        String s = this.getPositionString();
        return Integer.parseInt(s);
    }
    private void setInnate(String inn){
        if(inn.compareTo("yes")==0) this.innate = true;
        else if(inn.compareTo("no")==0) this.innate = false;
    }
    public boolean getInnate(){
        return this.innate;
    }


    public void addSubstat(SubStat s){
        this.subs.add(s);
    }
    public void addSubstat(String sub, String val){
        this.subs.add(new SubStat(sub, val));
    }
    public ArrayList<SubStat> getSubStats(){
        return this.subs;
    }

    public String getInnateString(){
        if(this.innate == true)return "yes";
        return "no";
    }
    public String getSubstatString(){
        String s = "";
        for(int i = 0; i<this.subs.size(); i++){
            s += subs.get(i).getSubStat() + " " + subs.get(i).getSubValue() + " ";
        }
        return s;
    }
    public String getMainstatString(){
        return this.mainStat.getMainStatAttribute();
    }
    public String getGradeString(){return this.getGrade().toString();}
    public String getSetString(){
        return this.set.getSet();
    }
    public String getPositionString(){return this.getPos().toString();}

    public boolean getIsEquipped(){
        return this.isEquipped;
    }

    public void setIsEquipped(boolean b){
        this.isEquipped = b;
    }

    public Rune(String grade, String set, String pos, String innate, String stat){
        setGrade(grade);
        setSet(set);
        setPos(pos);
        setInnate(innate);
        setMainStat(stat, grade);
        subs = new ArrayList<>();
        this.isEquipped = false;
    }
    public Rune(String longstring){
        String[] arr;
        arr = longstring.split(" ");
        setGrade(arr[0]);
        setSet(arr[1]);
        setPos(arr[2]);
        setInnate(arr[3]);
        setMainStat(arr[4], arr[0]);
        subs = new ArrayList<>();
        if(this.getInnate()){
            addSubstat(arr[5], arr[6]);
            addSubstat(arr[7], arr[8]);
            addSubstat(arr[9], arr[10]);
            addSubstat(arr[11], arr[12]);
            addSubstat(arr[13], arr[14]);
        }else{
            addSubstat(arr[5], arr[6]);
            addSubstat(arr[7], arr[8]);
            addSubstat(arr[9], arr[10]);
            addSubstat(arr[11], arr[12]);
        }
        this.isEquipped = false;
    }

    public boolean hasMainStat(String s){
        if(this.getMainstatString().equals(s)) return true;

        return false;
    }

    public boolean hasSubStat(String s){
        for(SubStat sub : this.getSubStats()){
            if(sub.getSubStat().equals(s))return true;
        }
        return false;
    }

    public boolean hasSubStats(String a, String b){
        boolean t1 = this.hasSubStat(a);
        boolean t2 = this.hasSubStat(b);
        if(t1 && t2)return true;
        return false;
    }
    public boolean hasSubStats(String a, String b, String c){
        boolean t1 = this.hasSubStats(a, b);
        boolean t2 = this.hasSubStat(c);
        if(t1 && t2)return true;
        return false;
    }

    public String toString() {
        String s =  this.getGradeString() +" " +
                    this.getSetString() + " " +
                    this.getPositionString() + " " +
                this.getInnateString() + " " +
                this.getMainstatString() + " " +
                    this.getSubstatString();
        return s;
    }

    public String toStringDB(){
        String s =  this.getGradeString() +" " +
                this.getSetString() + " " +
                this.getPositionString() + " " +
                this.getInnateString() + " " +
                this.getMainstatString() + " ";
                //this.getSubstatString();
        if(!this.getInnate()){
            s += this.subs.remove(0);
        }
        return s;
    }

    public String toStringGUI(){
        StringBuilder b = new StringBuilder();
        //Violent 6*
        //Slot 2 SPD
        //Innate
        b.append(String.format("%s %s STAR\nSlot %s %s\n", this.getSet().toString().toUpperCase(),
                this.getGrade(), this.getPos(), this.getMainStat().toString().toUpperCase()));
        if(getInnate()){
            b.append(String.format("%s\n%s\n%s\n%s\n%s",this.getSubStats().get(0),
                    this.getSubStats().get(1),this.getSubStats().get(2),this.getSubStats().get(3)
                    ,this.getSubStats().get(4)).toUpperCase());
        }
        else{
            b.append(String.format("%s\n%s\n%s\n%s",this.getSubStats().get(0),
                    this.getSubStats().get(1),this.getSubStats().get(2),this.getSubStats().get(3)).toUpperCase());
        }

        return b.toString();
    }
    public void printJSON(){
        StringBuilder s = new StringBuilder();
        s.append("{\n");
        //s.append("\"Rune\":\n");
        //s.append(String.format( "\t\"RuneID\": %s, \n", 14 ));
        s.append(String.format( "\t\"RuneSet\": \"%s\", \n", this.getSet().getSet() ));
        s.append(String.format( "\t\"RunePos\": %s, \n", this.getPos().toString() ));
        s.append(String.format( "\t\"MainStat\": \"%s\", \n", this.getMainStat().getMainStatAttribute()));
        s.append(String.format( "\t\"Innate\": \"%s\", \n", this.getInnateString() ));
        s.append(String.format( "\t\"Substats\": [ \n\t\t{\n\t\t  " ));
        if(getInnate())s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(0).getSubStat(), subs.get(0).getSubValue() ));
        s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(1).getSubStat(), subs.get(1).getSubValue()));
        s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(2).getSubStat(), subs.get(2).getSubValue() ));
        s.append(String.format( "\t\"%s\": %s, \n\t\t", subs.get(3).getSubStat(), subs.get(3).getSubValue() ));
        s.append(String.format( "\t\"%s\": %s \n\t\t", subs.get(4).getSubStat(), subs.get(4).getSubValue() ));
        s.append(String.format("}\n\t]"));

        s.append("\n}\n");

        System.out.println(s);
    }


    public void createRuneFromFile(File f){

    }
    public void addSubsTEST(){


    }

    @Override
    public int compareTo(Rune o) {
        if(this.runeExists(o)) return 0; // 0 - rune exits
        return -1;
    }
    public boolean compareAttr(Rune r){
        if( this.getGradeString().equals(r.getGradeString()) )
        if( this.getSetString().equals(r.getSetString()) )
        if( this.getPositionString().equals(r.getPositionString()) )
        if( this.getInnateString().equals(r.getInnateString()) )
        if( this.getMainstatString().equals(r.getMainstatString()) )
        return true;
        return false;
    }
    public boolean compareSubs(Rune r, int i){
        if( this.getSubStats().get(i).equals(r.getSubStats().get(i))) return true;
        return false;
    }

    public boolean runeExists(Rune r){
        try{
            if(this.compareAttr(r))
            if(this.compareSubs(r, 0) )
            if(this.compareSubs(r, 1) )
            if(this.compareSubs(r, 2) )
            if(this.compareSubs(r, 3) ){
            if(this.getInnate() == r.getInnate() && this.getInnate() == true){
                if(this.compareSubs(r, 4) )
                    return true;
            }
            else return true;
    }


        }catch(Exception e){
            System.out.println("Error found. " + e.getLocalizedMessage());
        }


        return false;
    }
}


