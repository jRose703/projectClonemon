import World.World;

public class Main {

    public static void main(String[] args) {

        System.out.println("Das wird ein Pokemonclone");
        World W1 = new World(10,10);

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][0].getClass());
        System.out.println(W1.tileArr[0][0].texture_id);
        System.out.println(W1.tileArr[0][0].accessible);

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][1].getClass());
        System.out.println(W1.tileArr[0][1].texture_id);
        System.out.println(W1.tileArr[0][1].accessible);

        System.out.println("-------------------------------------------");

        System.out.println(W1.tileArr[0][2].getClass());
        System.out.println(W1.tileArr[0][2].texture_id);
        System.out.println(W1.tileArr[0][2].accessible);

        System.out.println("-------------------------------------------");

        W1.printWorld_ids();
    }
}