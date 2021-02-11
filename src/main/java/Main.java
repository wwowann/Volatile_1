public class Main {
    static volatile boolean volatileVol;

    public static void main(String[] args) {
        final int ITERATION = 5;
        final int TIME = 4000;
        ToggleSwitch toggleSwitch = new ToggleSwitch();
        Thread threadUser = new Thread(() -> {
            int count = 0;
            while (count < ITERATION) {
                if (!toggleSwitch.getSwitch()) {
                    volatileVol = toggleSwitch.clickSwitch();
                    System.out.println("Пользователь нажал на рычаг ");
                }
                try {
                    Thread.sleep(TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        });
        threadUser.setName("User");
        Thread threadGame = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (volatileVol) {
                    volatileVol = toggleSwitch.clickSwitch();
                    System.out.println("Игра вернула рычаг в обратное положение");
                }
            }
        });
        threadGame.setName("Game");

        threadUser.start();
        threadGame.start();

        try {
            threadUser.join();
            System.out.println("Игрок устал и закончил игру");
            threadGame.interrupt();
            threadGame.join();
            System.out.println("Игра смирилась и тоже перестала играть");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
