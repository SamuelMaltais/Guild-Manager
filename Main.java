import java.util.ArrayList;

import guildcommands.GuildCommand;
import guildcommands.GuildCommandSystem;

public class Main {
    /**
     * Args: array with
     * <ol>
     * <li>guild:&lt;montant initial&gt;,&lt;armures initiales&gt;</li>
     * </ol>
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] ok = { "guild:100.0,10", "buy-hero:Berserker,2,52.5,6,30.5", "buy-hero:Zorro,1,36.2,2,15.0",
                "do-quest:2,5.3,60,3", "train-hero:Zorro" };
        ArrayList<String> errorStack = new ArrayList<String>();

        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(ok);

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand(), errorStack);

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();

            switch (command.getName()) {

                case "buy-hero" -> {
                    maGuilde.addHero(command.nextString(), command.nextInt(), command.nextDouble(),
                            command.nextInt(), command.nextDouble());
                }
                case "buy-armor" -> {

                    int qteArmures = command.nextInt();
                    int prix = command.nextInt();
                    // Le manque d'argent est handles dans ces fonctions
                    maGuilde.bank.modifyArgent(-prix * qteArmures);
                    maGuilde.bank.modifyArmure(qteArmures);

                }
                case "do-quest" -> {
                    int categorie = command.nextInt();
                    double coutHp = command.nextDouble();
                    int argent = command.nextInt();
                    int armure = command.nextInt();
                    // Une quete est cree.
                    Quete quete = new Quete(categorie, coutHp, argent, armure, maGuilde, errorStack);
                    quete.commencer();

                }
                case "train-hero" -> {
                    Hero hero = maGuilde.trouverHeroParNom(command.nextString());
                    if (hero != null) {
                        hero.train();
                    }
                }
            }
        }
        // Print les resultats
        System.out.println("Guild Bank account: " + maGuilde.bank.getArgent() + " gold & "
                + maGuilde.bank.getNbArmures() + " armours");
        System.out.println("Heroes");
        for (int i = 0; i < maGuilde.heros.size(); i++) {
            double hp = maGuilde.heros.get(i).hp;
            int level = maGuilde.heros.get(i).level;
            System.out.print("-" + maGuilde.heros.get(i).name);
            System.out.print("  level=" + level + ",   ");
            System.out.println("HP=" + hp);
        }
        if (errorStack.size() > 0) {
            System.out.println("Erreurs:");
            for (int i = 0; i < errorStack.size(); i++) {
                System.out.println(errorStack.get(i));
            }
        }

    }

    public static Guilde makeGuilde(GuildCommand command, ArrayList<String> errorStack) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures, errorStack);
    }
}