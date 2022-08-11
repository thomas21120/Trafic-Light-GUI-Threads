
import java.util.Random;

// An enumeration of the colors of a traffic light. 
enum TrafficLightColor {  
    RED, GREEN, YELLOW 
} 

public class TrafficLightColorTask implements Runnable{
    public Thread t;
    private static CarPositionTask[] carsX;
    private TrafficLightColor currentTrafficLightColor = TrafficLightColor.RED;
    public boolean stop = false; // set to true to stop the task 
    int location;
    Random r = new Random();
    
    public TrafficLightColorTask(int locationIn){
        this.location = locationIn;
        t = new Thread(this);
        
    }

    public static void setCars(CarPositionTask[] cars){
        carsX = cars;
    }

    public void run() {
        try {
            Thread.sleep(r.nextInt(20001));
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
            e.printStackTrace();
        }
        while(true){
            if(stop){
                synchronized(this){
                    try {
                        wait();
                        Thread.sleep(r.nextInt(5001));
                    } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }
                }
            }
            while (!stop) {
                changeColor();
                try { 
                    switch(currentTrafficLightColor) { 
                        case GREEN:
                            Thread.sleep(10000); // green for 10 seconds
                            
                            break; 
                        case YELLOW: 
                            Thread.sleep(2000);  // yellow for 2 seconds
                            
                            break; 
                        case RED: 
                            Thread.sleep(12000); // red for 12 seconds\
                            
                            break; 
                    } 
                } catch(InterruptedException exc) { 
                    System.out.println(exc); 
                }
                
            }
        } 
    }
    
    synchronized void changeColor() { 
        switch(currentTrafficLightColor) { 
        case RED: 
            currentTrafficLightColor = TrafficLightColor.GREEN;
            for (int i = 0; i < carsX.length; i++){
                if(carsX[i].getCurrentCarPosition() == (location) && carsX[i].getCurrentCarState() == CarState.STOPPED){
                    carsX[i].startCar();
                    
                }
            }
            
        break; 
        case YELLOW: 
            currentTrafficLightColor = TrafficLightColor.RED;
            
            for (int i = 0; i < carsX.length; i++){
                int stopLocation = carsX[i].getCurrentCarPosition();
            
                if(stopLocation > (location-480) && stopLocation <= location){
                    carsX[i].stopCar(location);
                    
                }
            }

            
        break; 
        case GREEN: 
            currentTrafficLightColor = TrafficLightColor.YELLOW; 
        } 
        
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

    synchronized TrafficLightColor getCurrentTrafficLightColorAsEnum() {
        return currentTrafficLightColor;
    }
}
