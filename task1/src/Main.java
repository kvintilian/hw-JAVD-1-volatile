public class Main {

    public static void main(String[] args) {
        UselessBox uselessBox = new UselessBox();
        User user = new User(uselessBox);
        Thread boxThread = new Thread(null, uselessBox::work, "Полезная коробка");
        boxThread.start();
        Thread userThread = new Thread(null, user::curiosity, "Пользователь");
        userThread.start();

        while (true) {
            if (!userThread.isAlive()) {
                boxThread.interrupt();
                break;
            }
        }
    }
}
