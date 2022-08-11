import java.util.Random;

// An enumeration of the state of a car. 
enum CarState {  
    STOPPED, RUNNING 
} 

public class CarPositionTask implements Runnable{
    public Thread t;
    private CarState currentCarState = CarState.RUNNING;   
    private int currentCarPosition = 0;
    private int speedMeterPerSecond = 40; // meter/second
    public boolean stop = false; // set to true to stop the task 
    Random r = new Random();
    int id;

    public CarPositionTask(int speedMeterPerSecond, int id) {
        t = new Thread(this);
        this.speedMeterPerSecond = speedMeterPerSecond;
        this.id = id;
    }

    public void run() {
        try {
            Thread.sleep(r.nextInt(5001));
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
            e.printStackTrace();
        }
        while(true){
            
            if(stop){
               
                synchronized(this){try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }}}
            while (!stop) {
                updatePosition();
    
            try {
                switch(currentCarState){ 
                    case RUNNING:
                        Thread.sleep(1000); // run for 1 seconds 
                        break;
                    case STOPPED: 
                        break;
                } 
            } catch(InterruptedException exc) { 
                System.out.println(exc); 
            } 
            
            
        } }
    }
    
    synchronized void updatePosition() {
        if (currentCarState == CarState.RUNNING)
            currentCarPosition += speedMeterPerSecond;
    }
    
    synchronized void stopCar(int location) {
        currentCarState = CarState.STOPPED;
        while(currentCarPosition < location){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("car sleep interrupted");
                e.printStackTrace();
            }
            if (location - currentCarPosition < speedMeterPerSecond) {
                currentCarPosition = location;
            }else{
                currentCarPosition += speedMeterPerSecond;
            }
            
            
        }
    }

    synchronized void startCar() {
        currentCarState = CarState.RUNNING;
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
    synchronized int getCurrentCarPosition() {
        return currentCarPosition;
    }    
    
    synchronized CarState getCurrentCarState() {
        return currentCarState;
    }
}
