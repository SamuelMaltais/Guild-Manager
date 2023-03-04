public class Bank {
    private int nbArmures;
    private double argent;

    // Initie argent initial
    public Bank(double argent, int armures) {
        this.nbArmures = armures;
        this.argent = argent;
    }

    // Regarde si la balance est respectée
    public boolean isBroke() {
        if (this.nbArmures < 0 || this.argent < 0) {
            return 1;
        } else
            return 0;
    }

}
