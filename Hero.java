public class Hero {
    public String name;
    public double hp;
    public int level;

    public void levelUp() {
        this.level += 1;
    }

    public Hero(String name, double hp, int level) {
        this.name = name;
        this.hp = hp;
        this.level = level;
    }
}
