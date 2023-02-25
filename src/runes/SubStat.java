package runes;

public class SubStat {

    private Stat stat;
    private int value;
    private boolean percent;

    public SubStat(){this.stat = null; this.value = 0; this.percent=false;}

    public SubStat(String s, String v){
        setSubStat(s);
        setSubStatValue(v);
        if(this.stat == Stat.ATK || this.stat == Stat.DEF || this.stat == Stat.HP) this.percent = true;
    }

    public void setSubStatValue(String v){
        this.value = Integer.parseInt(v);
    }

    public void setSubStat(String s){
        this.stat = Stat.valueOf(s);
    }

    public String getSubStat(){
        return this.stat.name();
        //return this.stat.toString();
    }
    public String getSubValue(){
        return String.valueOf(value);
    }
    public boolean getPc(){
        return this.percent;
    }
    public int getSubValueInt(){
        return this.value;
    }

    public String toString(){
        if(this.stat==Stat.atk||this.stat==Stat.def||this.stat==Stat.hp || this.stat==Stat.spd) return stat + ": +" + value;
        else return stat + ": +" + value + "%";
    }

    public boolean equals(SubStat s){
        if(this.getSubStat().equals(s.getSubStat()))
        if(this.getSubValue().equals(s.getSubValue()))return true;
        return false;
    }



}
