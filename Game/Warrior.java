import java.util.ArrayList;
import java.util.Random;

public class Warrior extends Character {

    public Warrior(String name , int level, int experience){
        super(name, level, experience);
        super.dex = level * 2 ;
        super.charisma = level * 2 ;
        super.strength = level * 3;
        super.inventar = new Inventory(100);
        super.foc = true;
        super.gheata = false;
        super.pamant = false;
        super.inventar.potiuni = new ArrayList<>();
    }

    @Override
    public void primestePaguba(int paguba) {
        int sansaEvitare = super.dex + super.charisma;
        Random random = new Random();
        int evitare = random.nextInt(100);
        if (evitare + 1 < sansaEvitare)
            viata_curenta = viata_curenta - paguba / 2;
        else
            viata_curenta = viata_curenta - paguba;
        if (viata_curenta <= 0)
            viata_curenta = 0;
    }

    @Override
    public int atacaNormal() {
        int sansaDublu = super.strength;
        Random random = new Random();
        int sansa = random.nextInt(100);
        if (sansaDublu < sansa) return getRandomNumber(16, 30);
        else return getRandomNumber(8, 15);
    }
}