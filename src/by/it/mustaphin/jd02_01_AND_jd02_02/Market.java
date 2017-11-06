package by.it.mustaphin.jd02_01_AND_jd02_02;

import java.util.concurrent.ConcurrentLinkedQueue;

class Market implements Runnable {

    Market() {
        Thread thread = new Thread(this);
        thread.start();
    }

    static ConcurrentLinkedQueue<Buyer> buyers = new ConcurrentLinkedQueue<>();

    void letIn() {
        for (int i = 0; i < 60; i++) {
            for (int k = (int) (Math.random() * 3); k > 0; k--) {
                new Buyer(i + ":" + k);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Cashier c1 = new Cashier("Кассир 1");

    @Override
    public void run() {
        while (true) {//
            if (buyers.size() > 0) {
                c1.buyer = buyers.poll();
            }
        }
    }
}
