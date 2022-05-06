import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Enemy extends Entity implements CellElement {
    Random random = new Random();
    Enemy() {
        super.foc = random.nextBoolean();
        if (super.foc == true) {
            super.gheata = false;
            super.pamant = false;
        }
        else {
            super.gheata = random.nextBoolean();
            if (super.gheata == true)
                super.pamant = false;
            else
                super.pamant = random.nextBoolean();
        }
        super.viata_curenta = getRandomNumber(80, 110);
        super.viata_maxima = super.viata_curenta;
        super.mana_curenta = getRandomNumber(80, 100);
        super.mana_maxima = super.mana_curenta;
        super.abilitati = new ArrayList<>();
        vectorAdd();
        addSpell();
    }
    Enemy(int health) {
        super.viata_curenta = 0;
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    Vector<String> spell = new Vector<>();

    public void vectorAdd(){
        spell.add(0,"fire");
        spell.add(1,"ice");
        spell.add(2,"earth");
    }

    int random1 = random.nextInt(3);
    int random2 = random.nextInt(2);
    String firstSpell;
    String secondSpell;

    public void addSpell() {
        if (random1 == 0) {
            firstSpell = "fire";
            spell.remove(0);
            if (random2 == 0)
                secondSpell = "ice";
            else
                secondSpell = "earth";
            switch (secondSpell) {
                case "earth" -> super.abilitati.add(new Earth());
                case "ice" -> super.abilitati.add(new Ice());
            }
            super.abilitati.add(new Fire());
        }
        if (random1 == 1) {
            firstSpell = "ice";
            spell.remove(1);
            if (random2 == 0)
                secondSpell = "fire";
            else
                secondSpell = "earth";
            switch (secondSpell) {
                case "earth" -> super.abilitati.add(new Earth());
                case "fire" -> super.abilitati.add(new Fire());
            }
            super.abilitati.add(new Ice());
        }
        if (random1 == 2) {
            firstSpell = "earth";
            spell.remove(2);
            if (random2 == 0)
                secondSpell = "fire";
            else
                secondSpell = "ice";
            switch (secondSpell) {
                case "ice" -> super.abilitati.add(new Ice());
                case "fire" -> super.abilitati.add(new Fire());
            }
            super.abilitati.add(new Earth());
        }

    }


    @Override
    public String toCharacter() {
        return "Enemy";
    }

    @Override
    public void primestePaguba(int paguba) {
        int sansaEvitare = random.nextInt(2);
        if (sansaEvitare == 0)
            viata_curenta = viata_curenta - paguba;
        if (viata_curenta <= 0)
            viata_curenta =0;
    }
    @Override
    public int atacaNormal() {
        int sansaDubla = random.nextInt(2);
        int damage = getRandomNumber(6, 14);
        if (sansaDubla == 0) return damage;
        else return 2 * damage;
    }
}
