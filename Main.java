package guildcommands;

import java.io.Console;
import guildcommands.GuildCommandSystem;
import Guilde.Guilde;

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
        GuildCommandSystem guildCommandSystem = new GuildCommandSystem(args);

        Guilde maGuilde = makeGuilde(guildCommandSystem.actualCommand());

        while (guildCommandSystem.hasNextCommand()) {
            GuildCommand command = guildCommandSystem.nextCommand();
            switch (command.getName()) {
                case "buy-hero" -> {
                }
                case "buy-armor" -> {
                }
                case "do-quest" -> {
                }
                case "train-hero" -> {
                }
            }
        }
    }

    public static Guilde makeGuilde(GuildCommand command) {
        double montantInitial = command.nextDouble();
        int nbArmures = command.nextInt();
        return new Guilde(montantInitial, nbArmures);
    }
}