package by.it.meshchenko.jd02_01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cashier implements Runnable {
    private int num;
    private boolean isOpen = false;
    final Lock lockCashier = new ReentrantLock();

    public Cashier(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        boolean open = true;
            // Открываем кассу и работаем пока диспетчер не отпустит на "обед"
            Dispatcher.printWork(printStatus("** Cash desk is open"));
            while (open) {
                serviceBuyer();
                try{
                    lockCashier.lock();
                    open = isOpen();
                }
                finally {
                    lockCashier.unlock();
                }
            }
            Dispatcher.printWork(printStatus("** Cash desk is closed"));
            Helper.sleep(100);
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    private void serviceBuyer(){
        Buyer b;
        synchronized (Dispatcher.buyerQueue) {
            b = Dispatcher.buyerQueue.poll();
            PrintView pv = new PrintView();
            pv.setQueueSize(Dispatcher.buyerQueue.size());
            synchronized (Dispatcher.totalSynchr) {
                pv.setTotal(Dispatcher.total);
            }
            Dispatcher.printWork(pv);
        }
        if(b != null) {
            int timeout = Helper.random(2000, 5000);
            Helper.sleep(timeout);
            //печать обслуживания клиента
            Dispatcher.printWork(printStatus(printService(b, timeout)));
            synchronized (b) {
                b.notify();
            }
        }
    }

    private PrintView printStatus(String str){
        PrintView pv = new PrintView();
        switch (num){
            case 1: pv.setCashiers(0,str); break;
            case 2: pv.setCashiers(1,str); break;
            case 3: pv.setCashiers(2,str); break;
            case 4: pv.setCashiers(3,str); break;
            case 5: pv.setCashiers(4,str); break;
        }
        return pv;
    }

    private String printService(Buyer b, int timeout){
        StringBuilder str = new StringBuilder();
        str.append("StartService " + b + "\n");
        double sum = 0;
        if(b.basket.size() > 0){
            ChooseGood good;
            int i = 1;
            while((good = b.basket.poll()) != null){
                str.append(String.format("%-3d %-12s %4.1f\n", i, good.getGood(), good.getPrice()));
                i++;
                sum = sum + good.getPrice();
            }
            synchronized (Dispatcher.totalSynchr) {
                Dispatcher.total = Dispatcher.total + sum;
            }
        }
        str.append(String.format("%12s %10s\n","", "----------"));
        str.append(String.format("%16s %4.1f\n","Summa check: ", sum));
        str.append("TimeoutService: " + Integer.toString(timeout) + "\n");
        str.append("StopService " + b);
        return str.toString();
    }
}
