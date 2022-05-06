import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List<Potion> potiuni = new ArrayList<>();
    public int max_weight;
    public int monede = 200;

    Inventory(int max_weight) {
        this.max_weight = max_weight;
    }
    public void adaugaPotiune(Potion potiune) {
        potiuni.add(potiune);
    }
    public void stergePotiune(Potion potiune) {
        potiuni.remove(potiune);
    }

    public int calculGreutateRamasa() {
        int greutateCurenta = 0;
        for (Potion potiuneCurenta : potiuni) {
            greutateCurenta = greutateCurenta + potiuneCurenta.getGreutate();
        }
        return max_weight - greutateCurenta;
    }

}
