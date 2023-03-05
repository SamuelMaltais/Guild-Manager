import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class Guilde {
    public Bank bank;
    public ArrayList<Hero> heros = new ArrayList<Hero>();
    private ArrayList<String> errorStack;
    // Hashmap pour trouver les heros plus vite selon niveau ou nom
    private HashMap<String, Hero> herosParNom = new HashMap<String, Hero>();
    private HashMap<String, ArrayList<Hero>> herosParNiveau = new HashMap<String, ArrayList<Hero>>();

    public Guilde(double montantInitial, int nbArmures, ArrayList<String> errorStack) {
        // Initie la banque
        this.bank = new Bank(montantInitial, nbArmures, errorStack);
        this.errorStack = errorStack;
    }

    public void addHero(String name, int level, double cost, int armourCost, double hp) {
        // On regarde si l'argent est suffisant
        bank.modifyArgent(-cost);
        bank.modifyArmure(-armourCost);
        // Puis on add le hero
        boolean status = !bank.isBroke("Acheter le héro " + name);
        if (status) {
            Hero hero = new Hero(name, hp, level, this);
            this.heros.add(hero);
            herosParNom.put(name, hero);
            String levelString = Integer.toString(level);
            if (herosParNiveau.containsKey(levelString)) {

            }
        } else {
            bank.modifyArgent(cost);
            bank.modifyArmure(armourCost);
        }
    }

    public ArrayList<Hero> trouverHeroParNiveau() {
        return herosParNiveau.get("1");
    }

    public Hero trouverHeroParNom(String nom) {
        if (herosParNom.containsKey(nom)) {
            return herosParNom.get(nom);
        } else {
            errorStack.add("Le hero " + nom + " n'apparait pas dans la liste !");
            return null;
        }
    }

    // Sert a causer des erreurs quand hero nexiste pas ou argent insuffisant ..
}
