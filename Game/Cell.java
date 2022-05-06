public class Cell {
    int Ox;
    int Oy;
    enum cellenum {
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }
    cellenum tipCelula;
    CellElement inamicMagazin;
    boolean visitedCell;
    public Cell(int Ox, int Oy, CellElement tipCelula) {
        this.Ox = Ox;
        this.Oy = Oy;
        this.visitedCell = false;
        if (tipCelula instanceof Shop) {
            this.inamicMagazin = tipCelula;
            this.tipCelula = cellenum.SHOP;
        }
        else if (tipCelula instanceof Enemy) {
            this.inamicMagazin = tipCelula;
            if ( ((Enemy)tipCelula).viata_curenta == 0) {
                if (Grid.Init().lungime - 1 == Ox && Grid.Init().inaltime - 1 == Oy)
                    this.tipCelula = cellenum.FINISH;
                else
                    this.tipCelula = cellenum.EMPTY;
                 }
            else
                this.tipCelula = cellenum.ENEMY;
        }
    }
}

