import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    List<Spell> abilitati = new ArrayList<>();
    int viata_curenta;
    int viata_maxima;
    int mana_curenta;
    int mana_maxima;
    boolean foc;
    boolean gheata;
    boolean pamant;
    public void regenerareViata(int v) {
        if (viata_curenta + v > viata_maxima)
            Grid.Init().caracterCurent.viata_curenta = viata_maxima;
        else
            Grid.Init().caracterCurent.viata_curenta = Grid.Init().caracterCurent.viata_curenta + v;
    }

    public void regenerareMana(int m){
        if (mana_curenta + m > mana_maxima)
            Grid.Init().caracterCurent.mana_curenta = mana_maxima;
        else
            Grid.Init().caracterCurent.mana_curenta = Grid.Init().caracterCurent.mana_curenta + m;
    }

    public boolean folosireAbilitate(Spell s, Entity e){
        if (s.manaAbilitate < mana_curenta) {
            if (s instanceof Fire) {
                mana_curenta = mana_curenta - s.manaAbilitate;
                if (mana_curenta < 0)
                    mana_curenta = 0;
                if (e.foc == false)
                    e.viata_curenta = e.viata_curenta - s.paguba;
            }
            else if (s instanceof Ice) {
                mana_curenta = mana_curenta - s.manaAbilitate;
                if (mana_curenta < 0)
                    mana_curenta = 0;
                if (e.gheata == false)
                    e.viata_curenta = e.viata_curenta - s.paguba;
            }
            else if (s instanceof Earth) {
                mana_curenta = mana_curenta - s.manaAbilitate;
                if (mana_curenta < 0)
                    mana_curenta = 0;
                if (e.pamant == false) {
                    e.viata_curenta = e.viata_curenta - s.paguba;
                }
            }
            return true;
        }
        else return false;
    }

    public abstract void primestePaguba(int paguba);
    public abstract int atacaNormal();
}
