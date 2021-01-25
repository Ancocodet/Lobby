package de.ancozockt.lobby.utilitys.enums;

public enum CommandBlock {

    NO_BLOCK(0),
    ONLY_TEAM(1),
    ONLY_ADMIN(2),
    BLOCK(3);

    private int identifier;

    CommandBlock(int identifier){
        this.identifier = identifier;
    }

    public static CommandBlock getBlockByID(int ID){
        CommandBlock response = NO_BLOCK;
        for(CommandBlock blocks : values()){
            if(blocks.identifier == ID)
                response = blocks;
                break;

        }
        return response;
    }

}
