import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Character{

    public Rogue(String name , int level, int experience){
        super(name, level, experience);
        super.dex = level * 3 ;
        super.charisma = level * 2 ;
        super.strength = level * 2;
        super.inventar = new Inventory(60);
        super.foc = false;
        super.gheata = false;
        super.pamant = true;
        super.viata_curenta = 100;
        super.viata_maxima = 100;
        super.mana_curenta = 100;
        super.mana_maxima = 100;
        super.inventar.potiuni = new ArrayList<>();
    }
    @Override
    public void primestePaguba(int paguba) {
        int sansaEvitare = super.strength + super.charisma;
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
        int sansaDublu = super.dex;
        Random random = new Random();
        int sansa = random.nextInt(100);
        if (sansaDublu < sansa) return getRandomNumber(16, 30);
        else return getRandomNumber(8, 15);
    }
}
