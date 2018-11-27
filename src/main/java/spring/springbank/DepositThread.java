package spring.springbank;

public class DepositThread extends Thread {

    public void run() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);

                    //UserServices.newMessagesShow(loginEmail);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
