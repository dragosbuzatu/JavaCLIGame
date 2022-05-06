public class HealthPotion implements Potion {
    int valoareRegenerare;
    int pret;
    int greutate;

    public HealthPotion(int valoareRegenerare, int pret, int greutate) {
        this.valoareRegenerare = valoareRegenerare;
        this.pret = pret;
        this.greutate = greutate;
    }

    @Override
    public void utilizarePotiune(Potion potiuneCurenta) {
        Grid.Init().caracterCurent.viata_curenta = Grid.Init().caracterCurent.viata_curenta + potiuneCurenta.getValoare();
    }

    @Override
    public int getPret() {
        return pret;
    }

    @Override
    public int getGreutate() {
        return greutate;
    }

    @Override
    public int getValoare() {
        return valoareRegenerare;
    }
}
