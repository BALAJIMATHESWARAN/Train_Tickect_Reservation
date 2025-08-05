public class Passanger {
    static int id = 1;    // To give the id for every new passanger
    String name ;
    int age;
    String berthpref ;    // U or L or M
    int passangerId ;     
    String alloted ;
    int number ;

    Passanger(String name, int age, String berthPref){
        this.name = name;
        this.age = age;
        this.berthpref = berthpref;
        this.passangerId = id++;
        alloted = "";        // initially not alloted berth
        number = -1;         // initially not alloted seat number
    }
}
