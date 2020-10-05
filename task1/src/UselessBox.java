import java.util.Random;

public class UselessBox {
    private final int TO_MIN = 300;
    private final int TO_MAX = 1000;
    private volatile boolean switcher = false;

    private void switchOff() {
        switcher = false;
        System.out.println("Коробка выключила тумблер");
    }

    public void switchOn() {
        switcher = true;
    }

    public boolean isSwitcher() {
        return switcher;
    }

    public void work() {
        System.out.println("Полезная коробка включена!");
        Random random = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            if (switcher) {
                try {
                    Thread.sleep(random.nextInt(TO_MAX - TO_MIN) + TO_MIN);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
                switchOff();
            }
        }
    }
}
