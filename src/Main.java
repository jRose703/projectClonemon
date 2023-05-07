import Worlds.World;

public class Main {

    public static void main(String[] args) {

        System.out.println("Das wird ein Pokemonclone");
        World W1 = new World(10, 10);


        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][0].getClass());
        System.out.println(W1.tileArr[0][0].getTexture_id());
        System.out.println(W1.tileArr[0][0].getAccessible());

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][1].getClass());
        System.out.println(W1.tileArr[0][1].getTexture_id());
        System.out.println(W1.tileArr[0][1].getAccessible());

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][2].getClass());
        System.out.println(W1.tileArr[0][2].getTexture_id());
        System.out.println(W1.tileArr[0][2].getAccessible());

        System.out.println("-------------------------------------------");

        W1.printWorld_ids();

        Worlds.WriteToJsonFile.saveArr(W1);
    }
}