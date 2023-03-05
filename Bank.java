import java.util.ArrayList;

public class Bank {
    private int nbArmures;
    private double argent;
    private ArrayList<String> errorStack;

    // Initie argent initial
    public Bank(double argent, int armures, ArrayList<String> errorStack) {
        this.nbArmures = armures;
        this.argent = argent;
        this.errorStack = errorStack;
    }

    public void modifyArmure(int amount) {
        this.nbArmures += amount;
    }

    public void modifyArgent(double amount) {
        this.argent += amount;
    }

    public int getNbArmures() {
        return nbArmures;
    }

    public double getArgent() {
        return argent;
    }

    // Regarde si la balance est respectée
    public boolean isBroke(String action) {
        if (this.nbArmures < 0 || this.argent < 0) {
            if (this.nbArmures < 0) {
                errorStack.add(action + " coute trop d'armure");
            }
            if (this.argent < 0) {
                errorStack.add(action + " coute trop d'or");
            }
            return true;
        } else
            return false;
    }

}
