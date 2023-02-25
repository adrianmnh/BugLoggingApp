package runes;

enum Stat {
//    ATK("ATK%"), HP("HP%"), DEF("DEF%"), SPD("SPD"), Crte, CD("Cdmg"),
//     atk("atk"),  hp("hp"),  def("def"), ACC("ACC"), RES("RES"), NULL("");
    ATK, HP, DEF, spd, crte, cdmg,
    atk, hp, def, acc, res;

    //private String stat;

//    Stat(final String stat){ this.stat = stat; }
    //public String toString(){ return this.toString(); }


}

enum Set {
//    violent, swift, rage, blade, will,nemesis;
    violent("Violent"), swift("Swift"), rage("Rage"), blade("Blade"), will("Will"),nemesis("Nemesis");
    private String set;
    Set(final String s) { this.set = s; }
    public String getSet(){ return this.set;
    }
}

enum Grade {
    five("5"), six("6");
    private String grade;
    Grade(String g){
        this.grade = g;
    }
    public String toString(){ return this.grade; }
}

enum Pos {
    one("1"), two("2"), three("3"), four("4"), five("5"), six("6");
    private String pos;
    Pos(String p){ this.pos = p; }
    public String toString(){ return this.pos; }
}

