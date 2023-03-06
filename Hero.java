public class Hero {
    public String name;
    public double hp;
    public int level;
    private Guilde maGuilde;

    public void train() {
        double cost = 20 * Math.log(level + 10);
        int armourCost = (int) Math.floor(Math.log(level + 10) + 1);
        maGuilde.bank.modifyArgent(-cost);
        maGuilde.bank.modifyArmure(-armourCost);
        if (!maGuilde.bank.isBroke("Entrainer " + name)) {
            this.level += 1;
        } else {
            maGuilde.bank.modifyArgent(cost);
            maGuilde.bank.modifyArmure(armourCost);
        }

        hp = hp * 1.5;

    }

    public Hero(String name, double hp, int level, Guilde maGuilde) {
        this.name = name;
        this.hp = hp;
        this.level = level;
        this.maGuilde = maGuilde;
    }
}
