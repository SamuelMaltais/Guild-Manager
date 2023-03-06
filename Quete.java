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

        boolean heroTrouve = false;
        // Chercher dans la liste pour un hero du meme niveau en allant vers le haut
        for (int i = categorie; i <= 4; i++) {
            if (AssignerHero(i)) {
                heroTrouve = true;
                break;
            }
        }
        if (!heroTrouve) {
            for (int i = categorie - 1; i <= 0; i--) {
                if (AssignerHero(i)) {
                    heroTrouve = true;
                    break;
                }
            }
        }
        if (!heroTrouve) {
            errorStack.add("Aucun Hero ne peut completer cette quete !");
        } else {
            maGuilde.bank.modifyArgent(argent);
            maGuilde.bank.modifyArmure(armure);
        }

    }

    public boolean AssignerHero(int i) {

        double perteSelonNiveau = 1.5;

        ArrayList<Hero> heros = maGuilde.trouverHeroParNiveau(i);
        if (heros != null && heros.size() == 1) {
            Hero hero = heros.get(0);
            // On enleve le hp au heros de la liste
            hero.hp += -(coutHp - (i - categorie) * perteSelonNiveau);
            // On retire le hero si il est mort !!!!! :):):)
            if (heros.get(0).hp <= 0) {
                this.retirerHero(heros.get(0));
            }
            return true;
        }
        if (heros != null && heros.size() > 1) {
            Hero hero = healthiestHero(heros);
            // On retire le hero si il est mort !!!!! :):):)
            hero.hp += -(coutHp - (i - categorie) * perteSelonNiveau);
            if (heros.get(0).hp <= 0) {
                this.retirerHero(heros.get(0));
            }
            return true;
        }
        return false;
    }

    public Hero healthiestHero(ArrayList<Hero> heros) {
        Hero maxHpHero = heros.get(0);
        for (int i = 1; i < heros.size(); i++) {
            Hero currentHero = heros.get(i);
            if (currentHero.hp > maxHpHero.hp) {
                maxHpHero = currentHero;
            }
        }
        return maxHpHero;
    }

    public void retirerHero(Hero hero) {

        for (int i = 0; i < maGuilde.heros.size(); i++) {
            if (hero.name == maGuilde.heros.get(i).name) {
                maGuilde.heros.remove(i);
                break;
            }
        }

    }

}
