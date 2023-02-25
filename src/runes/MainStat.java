package runes;

public class MainStat{

    private Stat mainstat;
    private int value;
    private Grade grade;
    private boolean percent;

    public MainStat(String s, String g){
        setMainStatGrade(g);
        setMainStatAttribute(s);
        setMainStatValue(g, s);
    }
    public void setMainStatGrade(String s){
        if(Integer.parseInt(s)==6)this.grade = Grade.six;
        else this.grade = Grade.five;
    }
    public void setMainStatAttribute(String s){
        this.mainstat = Stat.valueOf(s);
    }
    private void setMainStatValue(String g, String s){
        int grade = Integer.parseInt(g);
        if(grade == 6){
            switch (mainstat){
                case spd:
                    this.value = 42; break;
                case atk, def:
                    this.value = 160; break;
                case hp:
                    this.value = 2448; break;
                case acc, res:
                    this.value = 64; break;
                case crte:
                    this.value = 58; break;
                case cdmg:
                    this.value = 80; break;
                case HP, DEF, ATK:
                    this.value = 63; this.percent = true; break;
            }
        }
        else{
            switch (mainstat){
                case spd:
                    this.value = 39; break;
                case atk, def:
                    this.value = 135; break;
                case hp:
                    this.value = 2088; break;
                case acc, res:
                    this.value = 51; break;
                case crte:
                    this.value = 47; break;
                case cdmg:
                    this.value = 65; break;
                case HP, DEF, ATK:
                    this.value = 51; this.percent = true; break;
            }
        }
    }

    public Stat getGrade(){return this.mainstat;}
    public int getValue(){return this.value;}
    public String getMainStatAttribute(){return this.mainstat.toString();}
    public boolean getPc(){
        return this.percent;
    }

    public String toString(){
        String toReturn = "";
        if(this.percent == true) toReturn = mainstat + ": " + value + "%";
        else if(this.percent == false)toReturn = mainstat + ": " + value;
        return toReturn;
    }

//    public static void main(String[] args) {
//        MainStat a = new MainStat("SPD", 6);
//        System.out.println(a);
//    }
}
