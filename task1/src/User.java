import java.util.Random;

public class User {
    private final int TO_MIN = 500;
    private final int TO_MAX = 1500;
    private final int TO_WAIT = 100;

    private final UselessBox uselessBox;
    private int switchAttempts = 5;

    public User(UselessBox uselessBox) {
        this.uselessBox = uselessBox;
    }

    public void curiosity() {
        System.out.println("Нашел коробку с тумблером");
        Random random = new Random();
        try {
            while (switchAttempts-- > 0) {
                Thread.sleep(random.nextInt(TO_MAX - TO_MIN) + TO_MIN);
                uselessBox.switchOn();
                System.out.println("Включил тумблер");
                while (uselessBox.isSwitcher()) {
                    Thread.sleep(TO_WAIT);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Решил потерять коробку обратно");
    }
}
