import java.util.ArrayList;

public class Quete {

    public int categorie;
    public double coutHp;
    public int argent;
    public int armure;
    public Guilde maGuilde;
    private ArrayList<String> errorStack;

    public Quete(int categorie, double coutHp, int argent, int armure, Guilde maGuilde, ArrayList<String> errorStack) {

        this.categorie = categorie;
        this.coutHp = coutHp;
        this.argent = argent;
        this.armure = armure;
        this.maGuilde = maGuilde;
        this.errorStack = errorStack;

    }

    public void commencer() {

    }

}
