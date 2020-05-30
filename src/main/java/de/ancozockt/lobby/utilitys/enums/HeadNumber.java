package de.ancozockt.lobby.utilitys.enums;

public enum HeadNumber {

    ZERO(0, "http://textures.minecraft.net/texture/0ebe7e5215169a699acc6cefa7b73fdb108db87bb6dae2849fbe24714b27"),
    ONE(1, "http://textures.minecraft.net/texture/71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530"),
    TWO(2, "http://textures.minecraft.net/texture/4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847"),
    THREE(3, "http://textures.minecraft.net/texture/1d4eae13933860a6df5e8e955693b95a8c3b15c36b8b587532ac0996bc37e5"),
    FOUR(4, "http://textures.minecraft.net/texture/d2e78fb22424232dc27b81fbcb47fd24c1acf76098753f2d9c28598287db5"),
    FIVE(5, "http://textures.minecraft.net/texture/6d57e3bc88a65730e31a14e3f41e038a5ecf0891a6c243643b8e5476ae2"),
    SIX(6, "http://textures.minecraft.net/texture/334b36de7d679b8bbc725499adaef24dc518f5ae23e716981e1dcc6b2720ab"),
    SEVEN(7, "http://textures.minecraft.net/texture/6db6eb25d1faabe30cf444dc633b5832475e38096b7e2402a3ec476dd7b9"),
    EIGHT(8, "http://textures.minecraft.net/texture/59194973a3f17bda9978ed6273383997222774b454386c8319c04f1f4f74c2b5"),
    NINE(9, "http://textures.minecraft.net/texture/e67caf7591b38e125a8017d58cfc6433bfaf84cd499d794f41d10bff2e5b840");

    int value;
    String skinURL;

    HeadNumber(int value, String skinURL){
        this.value = value;
        this.skinURL = skinURL;
    }

    public String getSkinURL() {
        return skinURL;
    }

    public static HeadNumber getByValue(int value){
        HeadNumber response = ZERO;
        for(HeadNumber number : values()){
            if(value == number.value)
                response = number;
        }
        return response;
    }
}
