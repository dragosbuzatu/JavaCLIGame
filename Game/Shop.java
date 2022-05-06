import java.util.ArrayList;
import java.util.List;

public class Shop implements CellElement {
    List<Potion> listaPotiuniMagazin;

    Shop(Potion potion1, Potion potion2) {
        listaPotiuniMagazin = new ArrayList<>();
        listaPotiuniMagazin.add(potion1);
        listaPotiuniMagazin.add(potion2);
    }

    Shop(Potion potion1, Potion potion2, Potion potion3) {
        listaPotiuniMagazin = new ArrayList<>();
        listaPotiuniMagazin.add(potion1);
        listaPotiuniMagazin.add(potion2);
        listaPotiuniMagazin.add(potion3);
    }

    Shop(Potion potion1, Potion potion2, Potion potion3, Potion potion4) {
        listaPotiuniMagazin = new ArrayList<>();
        listaPotiuniMagazin.add(potion1);
        listaPotiuniMagazin.add(potion2);
        listaPotiuniMagazin.add(potion3);
        listaPotiuniMagazin.add(potion4);
    }

    public Potion stergePotiuneMagazin(int index) {
        Potion boughtPotion = listaPotiuniMagazin.get(index);
        listaPotiuniMagazin.remove(index);
        return boughtPotion;
    }

    @Override
    public String toCharacter() {
        return "Shop";
    }
}

