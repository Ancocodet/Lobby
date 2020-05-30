package de.ancozockt.lobby.configuration;

public class StringManager {

    private String prefix = "§7[§aLobby§7] ";
    private String error = "§c";
    private String bold = "§e";
    private String normal = "§a";

    public StringManager(){

    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setBold(String bold) {
        this.bold = bold;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getBold() {
        return bold;
    }

    public String getError() {
        return error;
    }

    public String getNormal() {
        return normal;
    }

    public String getPrefix() {
        return prefix;
    }
}
