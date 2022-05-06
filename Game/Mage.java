import java.util.Random;

public class Mage extends Character{

    public Mage(String name , int level, int experience){
        super(name, level, experience);
        super.dex = level* 2 ;
        super.charisma = level * 3 ;
        super.strength = level * 2;
        super.inventar = new Inventory(30);
        super.foc = false;
        super.gheata = true;
        super.pamant = false;
    }
    @Override
    public void primestePaguba(int paguba) {
        int sansaEvitare = super.dex + super.strength;
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
        int sansaDublu = super.charisma;
        Random random = new Random();
        int sansa = random.nextInt(100);
        if (sansaDublu < sansa) return getRandomNumber(16, 30);
        else return getRandomNumber(8, 15);
    }
}
