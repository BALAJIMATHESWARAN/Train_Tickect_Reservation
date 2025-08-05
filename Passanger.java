public class Passanger {
    static int id = 1;
    String name ;
    int age;
    String berthpref ;
    int passangerId ;
    String alloted ;
    int number ;

    Passanger(String name, int age, String berthPref){
        this.name = name;
        this.age = age;
        this.berthpref = berthpref;
        this.passangerId = id++;
        alloted = "";
        number = -1;
    }
}
