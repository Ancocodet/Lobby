package de.ancozockt.lobby.utilitys.background;

import de.ancozockt.lobby.Main;

public class AEStringBuilder {

    public static String replaceDefaults(String given){
        String response = given;

        response = response.replace("&", "§");

        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.ReplaceVowelMutations")) {
            response = response.replace("Oe", "Ö").replace("oe", "ö");
            response = response.replace("Ae", "Ä").replace("ae", "ä");
            response = response.replace("Ue", "Ü").replace("ue", "ü");
        }

        return response;
    }

    public static String replaceShortcuts(String given){
        String response = given;

        response = response.replace("%n", Main.getInstance().getStringManager().getNormal());
        response = response.replace("%e", Main.getInstance().getStringManager().getError());
        response = response.replace("%b", Main.getInstance().getStringManager().getBold());

        return response;
    }

    public static String buildMessage(String given){
        String response = given;

        response = replaceDefaults(response);
        response = replaceShortcuts(response);

        return Main.getInstance().getStringManager().getPrefix() + response;
    }

}
