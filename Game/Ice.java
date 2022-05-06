import java.util.Random;

public class Ice extends Spell {
    Random random = new Random();
    Ice() {
        super.paguba = getRandomNumber(20, 40);
        super.manaAbilitate = getRandomNumber(25, 30);
    }
}
