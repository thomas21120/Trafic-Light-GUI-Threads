import java.util.Calendar;
import java.util.Date;

public class TimeTask implements Runnable {
    private Date currentTimeAsDate = new Date();
    private boolean stop = false; // set to true to stop the task 
    
    public void run() {
        while(true){
            if(stop){
                synchronized(this){try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }}}
            while (!stop) {
            try {
                Thread.sleep(1000);
                updateTime();
            } catch(InterruptedException exc) { 
                System.out.println(exc); 
            }
        }
    }
    }
    
    synchronized void updateTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentTimeAsDate);
        cal.add(Calendar.SECOND, 1);
        currentTimeAsDate = cal.getTime();     
    }
    
    synchronized void stopTask() {
        stop = true;
    }
    synchronized void startTask() {
        stop = false;
        synchronized(this){
            notify();
        }
    }
    
    synchronized Date getCurrentTimeAsDate() {
        return currentTimeAsDate;
    }
}
