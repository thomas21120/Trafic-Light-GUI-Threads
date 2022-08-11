import javax.swing.*;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class main_FINAL extends JFrame{
  boolean stop = false;
  final Timer timer = new Timer(1000, null);
  String localDir = System.getProperty("user.dir");
  ImageIcon red = new ImageIcon(localDir + "\\red.png");
  ImageIcon green = new ImageIcon(localDir + "\\green.png");
  ImageIcon yellow = new ImageIcon(localDir + "\\yellow.png");
  int numLights;
  int numCars;
  int location;
  Thread updateCars;
  Thread updateLights;
  SimpleDateFormat tf = new SimpleDateFormat("mm:ss");

  TimeTask timeTask;
  Date currentTime;
  TrafficLightColorTask[] arrayLights;
  CarPositionTask[] arrayCars;
  // Row One
  JLabel numCarsLB = new JLabel("Number of Cars:");
  JComboBox<Integer> numCarsJCB = new JComboBox<>();
  JLabel numLightsLB = new JLabel("Number of Lights:");
  JComboBox<Integer> numLightsJCB = new JComboBox<>();
  JButton enterBTN = new JButton("Enter");
  // Row two
  JLabel light1LB = new JLabel("Light 1(1000m)");
  JLabel light2LB = new JLabel("Light 2(2000m)");
  JLabel light3LB = new JLabel("Light 3(3000m)");
  JLabel light4LB = new JLabel("Light 4(4000m)");
  JLabel light5LB = new JLabel("Light 5(5000m)");
  // Row 3
  JLabel light1StatLB = new JLabel();
  JLabel light2StatLB = new JLabel();
  JLabel light3StatLB = new JLabel();
  JLabel light4StatLB = new JLabel();
  JLabel light5StatLB = new JLabel();
  // Row 4
  JLabel timeLB = new JLabel("Time Elapsed: ");
  JButton resumeBTN = new JButton("Resume");
  JButton stopBTN = new JButton("Stop / Clear");
  JButton startBTN = new JButton("Start");
  JButton pauseBTN = new JButton("Pause");
  // Row 5
  JLabel carLB = new JLabel("Car");
  JLabel speedLB = new JLabel("Speed(Meter/Second)");
  JLabel positionLB = new JLabel("Position(Meters[x,y])");
  JLabel blank1LB = new JLabel();
  JLabel blank2LB = new JLabel();
  // Row 6
  JLabel car1LB = new JLabel("Car 1");
  JLabel car1SpeedLB = new JLabel("40");
  static  JTextField car1PositionTF = new JTextField("");
  JLabel blank3LB = new JLabel();
  JLabel blank4LB = new JLabel();
  // Row 7
  JLabel car2LB = new JLabel("Car 2");
  JLabel car2SpeedLB = new JLabel("40");
  JTextField car2PositionTF = new JTextField("");
  JLabel blank5LB = new JLabel();
  JLabel blank6LB = new JLabel();
  // Row 8
  JLabel car3LB = new JLabel("Car 3");
  JLabel car3SpeedLB = new JLabel("40");
  JTextField car3PositionTF = new JTextField("");
  JLabel blank7LB = new JLabel();
  JLabel blank8LB = new JLabel();
  // Row 9
  JLabel car4LB = new JLabel("Car 4");
  JLabel car4SpeedLB = new JLabel("40");
  JTextField car4PositionTF = new JTextField("");
  JLabel blank9LB = new JLabel();
  JLabel blank10LB = new JLabel();
  // Row 10
  JLabel car5LB = new JLabel("Car 5");
  JLabel car5SpeedLB = new JLabel("40");
  JTextField car5PositionTF = new JTextField("");
  JLabel blank11LB = new JLabel();
  JLabel blank12LB = new JLabel();

  
  
  public main_FINAL(){
    setLayout(new GridLayout(0, 5));
    setLocationRelativeTo(null);
    setSize(700,700);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    // Row 1
    add(numCarsLB);
    add(numCarsJCB);
    add(numLightsLB);
    add(numLightsJCB);
    add(enterBTN);
    numCarsJCB.setModel(new javax.swing.DefaultComboBoxModel<>(new 
      Integer[]
        {1, 2, 3, 4, 5}));
    numLightsJCB.setModel(new javax.swing.DefaultComboBoxModel<>(new 
      Integer[]
        {1, 2, 3, 4, 5}));
    // Row 2
    add(light1LB);
    add(light2LB);
    add(light3LB);
    add(light4LB);
    add(light5LB);
    // Row 3
    add(light1StatLB);
    add(light2StatLB);
    add(light3StatLB);
    add(light4StatLB);
    add(light5StatLB);
    // Row 4
    add(timeLB);
    //add(curTimeLB);
    add(startBTN);
    add(stopBTN);
    add(pauseBTN);
    add(resumeBTN);
    // Row 5
    add(carLB);
    add(speedLB);
    add(positionLB);
    add(blank1LB);
    add(blank2LB);
    // row 6
    add(car1LB);
    add(car1SpeedLB);
    add(car1PositionTF);
    add(blank3LB);
    add(blank4LB);
    
    // row 7
    add(car2LB);
    add(car2SpeedLB);
    add(car2PositionTF);
    add(blank5LB);
    add(blank6LB);
    
    // row 8
    add(car3LB);
    add(car3SpeedLB);
    add(car3PositionTF);
    add(blank7LB);
    add(blank8LB);
    // row 9
    add(car4LB);
    add(car4SpeedLB);
    add(car4PositionTF);
    add(blank9LB);
    add(blank10LB);
    
    // row 10
    add(car5LB);
    add(car5SpeedLB);
    add(car5PositionTF);
    add(blank11LB);
    add(blank12LB);
    pack();

    startBTN.setVisible(false);
    stopBTN.setVisible(false);
    pauseBTN.setVisible(false);
    resumeBTN.setVisible(false);
    car1LB.setVisible(false);
    car1PositionTF.setVisible(false);
    car1PositionTF.setEditable(false);
    car1SpeedLB.setVisible(false);
    car2LB.setVisible(false);
    car2PositionTF.setVisible(false);
    car2PositionTF.setEditable(false);
    car2SpeedLB.setVisible(false);
    car3LB.setVisible(false);
    car3PositionTF.setVisible(false);
    car3PositionTF.setEditable(false);
    car3SpeedLB.setVisible(false);
    car4LB.setVisible(false);
    car4PositionTF.setVisible(false);
    car4PositionTF.setEditable(false);
    car4SpeedLB.setVisible(false);
    car5LB.setVisible(false);
    car5PositionTF.setVisible(false);
    car5PositionTF.setEditable(false);
    car5SpeedLB.setVisible(false);
    light1LB.setVisible(false);
    light2LB.setVisible(false);
    light3LB.setVisible(false);
    light4LB.setVisible(false);
    light5LB.setVisible(false);

    enterBTN.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent evt){
        enterBTNActionPerformed(evt);
      }
    });
    startBTN.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent evt){
        startBTNActionPerformed(evt);
      }
    });
    pauseBTN.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent evt){
        pauseBTNActionPerformed(evt);
      }
    });
    resumeBTN.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent evt){
        resumeBTNActionPerformed(evt);
      }
    });
    stopBTN.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent evt){
        stopBTNActionPerformed(evt);
      }
    });
  }
  
  
  public static void main (String[] args){
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new main_FINAL();
      }
    });
  }
    
  void enterBTNActionPerformed(java.awt.event.ActionEvent evt){
    startBTN.setVisible(true);
    stopBTN.setVisible(true);
    stopBTN.setEnabled(false);
    pauseBTN.setVisible(true);
    pauseBTN.setEnabled(false);
    resumeBTN.setVisible(true);
    resumeBTN.setEnabled(false);
    numLights = (int)numLightsJCB.getSelectedItem();
    numCars = (int)numCarsJCB.getSelectedItem();
    stop = false;
    if(numLights >=1){
      light1LB.setVisible(true);
      light1StatLB.setIcon(red);
      light1StatLB.setVisible(true);
    }
    if(numLights >=2){
      light2LB.setVisible(true);
      light2StatLB.setIcon(red);
      light2StatLB.setVisible(true);
    }
    if(numLights >=3){
      light3LB.setVisible(true);
      light3StatLB.setIcon(red);
      light3StatLB.setVisible(true);
    }
    if(numLights >=4){
      light4LB.setVisible(true);
      light4StatLB.setIcon(red);
      light4StatLB.setVisible(true);
    }
    if(numLights ==5){
      light5LB.setVisible(true);
      light5StatLB.setIcon(red);
      light5StatLB.setVisible(true);
    }
    if(numCars >=1){
      car1LB.setVisible(true);
      car1PositionTF.setVisible(true);
      car1SpeedLB.setVisible(true);
    }
    if(numCars >=2){
      car2LB.setVisible(true);
      car2PositionTF.setVisible(true);
      car2SpeedLB.setVisible(true);
    }
    if(numCars >=3){
      car3LB.setVisible(true);
      car3PositionTF.setVisible(true);
      car3SpeedLB.setVisible(true);
    }
    if(numCars >=4){
      car4LB.setVisible(true);
      car4PositionTF.setVisible(true);
      car4SpeedLB.setVisible(true);
    }
    if(numCars ==5){
      car5LB.setVisible(true);
      car5PositionTF.setVisible(true);
      car5SpeedLB.setVisible(true);
    }
  }
  public void startBTNActionPerformed(java.awt.event.ActionEvent evt) {
    new Thread(timeTask = new TimeTask()).start();
    startBTN.setEnabled(false);
    pauseBTN.setEnabled(true);
    stopBTN.setEnabled(true);
    java.util.Date start = timeTask.getCurrentTimeAsDate();
    arrayLights = new TrafficLightColorTask[numLights];
    arrayCars = new CarPositionTask[numCars];
        for(int i = 0; i < arrayLights.length; i++){
            location = ((i+1)*1000);
            arrayLights[i] = new TrafficLightColorTask(location);
            arrayLights[i].t.start();
        }
        for(int i = 0; i < numCars; i++){
            arrayCars[i] = new CarPositionTask(40, i);
            arrayCars[i].t.start();
        }
        TrafficLightColorTask.setCars(arrayCars);
        
        updateCars = new Thread(){ 
          
          public void run(){
              while(!stop){
              timeLB.setText("Time Elapsed: " + String.valueOf(tf.format(timeTask.getCurrentTimeAsDate().getTime() - start.getTime() )));
              if(numCars >= 1){
                car1PositionTF.setText(String.valueOf(arrayCars[0].getCurrentCarPosition()) + ", 0");
              }
              if(numCars >= 2){
                car2PositionTF.setText(String.valueOf(arrayCars[1].getCurrentCarPosition()) + ", 0");
              }
              if(numCars >= 3){
                car3PositionTF.setText(String.valueOf(arrayCars[2].getCurrentCarPosition()) + ", 0");
              }
              if(numCars >= 4){
                car4PositionTF.setText(String.valueOf(arrayCars[3].getCurrentCarPosition()) + ", 0");
              }
              if(numCars == 5){
                car5PositionTF.setText(String.valueOf(arrayCars[4].getCurrentCarPosition()) + ", 0");
              }
              try {
                Thread.sleep(1000);
              } catch (Exception e) {
                //TODO: handle exception
              }
              
              }
              
          }
          
        };
        updateLights = new Thread(){ 
         
          public void run(){
            
              while(!stop){
              if(numLights >= 1){
                if(arrayLights[0].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.GREEN){
                  light1StatLB.setIcon(green);
                }
                if(arrayLights[0].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.RED){
                  light1StatLB.setIcon(red);
                }
                if(arrayLights[0].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.YELLOW){
                  light1StatLB.setIcon(yellow);
                }
              }
              if(numLights >= 2){
                if(arrayLights[1].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.GREEN){
                  light2StatLB.setIcon(green);
                }
                if(arrayLights[1].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.RED){
                  light2StatLB.setIcon(red);
                }
                if(arrayLights[1].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.YELLOW){
                  light2StatLB.setIcon(yellow);
                }
              }
              if(numLights >= 3){
                if(arrayLights[2].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.GREEN){
                  light3StatLB.setIcon(green);
                }
                if(arrayLights[2].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.RED){
                  light3StatLB.setIcon(red);
                }
                if(arrayLights[2].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.YELLOW){
                  light3StatLB.setIcon(yellow);
                }
              }
              if(numLights >= 4){
                if(arrayLights[3].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.GREEN){
                  light4StatLB.setIcon(green);
                }
                if(arrayLights[3].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.RED){
                  light4StatLB.setIcon(red);
                }
                if(arrayLights[3].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.YELLOW){
                  light4StatLB.setIcon(yellow);
                }
              }
              if(numLights == 5){
                if(arrayLights[4].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.GREEN){
                  light5StatLB.setIcon(green);
                }
                if(arrayLights[4].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.RED){
                  light5StatLB.setIcon(red);
                }
                if(arrayLights[4].getCurrentTrafficLightColorAsEnum() == TrafficLightColor.YELLOW){
                  light5StatLB.setIcon(yellow);
                }
              }
              try {
                Thread.sleep(1000);
              } catch (Exception e) {
                //TODO: handle exception
              }
              }
              
          }
        };
        updateCars.start();
        updateLights.start();
  }

  public void pauseBTNActionPerformed(java.awt.event.ActionEvent evt){
    pauseBTN.setEnabled(false);
    resumeBTN.setEnabled(true);
    try {
      // timeTask.suspend();
      timeTask.stopTask();
      
      for (int i = 0; i < arrayLights.length; i++) {
        arrayLights[i].stopTask();
      }
      for (int i = 0; i < arrayCars.length; i++) {
        arrayCars[i].stopTask();
      }
    } catch (Exception e) {
      //TODO: handle exception
    }
  }
  public void stopBTNActionPerformed(java.awt.event.ActionEvent evt){
    pauseBTN.setEnabled(false);
    startBTN.setEnabled(true);
    resumeBTN.setEnabled(false);
    timeTask.stopTask();
    stop = true;
    timeLB.setText("Time Elapsed");
    for (int i = 0; i < arrayCars.length; i++) {
      arrayCars[i].stopTask();
    }
    for (int i = 0; i < arrayLights.length; i++) {
      arrayLights[i].stopTask();
    }
    startBTN.setVisible(false);
    stopBTN.setVisible(false);
    pauseBTN.setVisible(false);
    resumeBTN.setVisible(false);
    car1LB.setVisible(false);
    car1PositionTF.setVisible(false);
    car1PositionTF.setEditable(false);
    car1SpeedLB.setVisible(false);
    car2LB.setVisible(false);
    car2PositionTF.setVisible(false);
    car2PositionTF.setEditable(false);
    car2SpeedLB.setVisible(false);
    car3LB.setVisible(false);
    car3PositionTF.setVisible(false);
    car3PositionTF.setEditable(false);
    car3SpeedLB.setVisible(false);
    car4LB.setVisible(false);
    car4PositionTF.setVisible(false);
    car4PositionTF.setEditable(false);
    car4SpeedLB.setVisible(false);
    car5LB.setVisible(false);
    car5PositionTF.setVisible(false);
    car5PositionTF.setEditable(false);
    car5SpeedLB.setVisible(false);
    light1LB.setVisible(false);
    light2LB.setVisible(false);
    light3LB.setVisible(false);
    light4LB.setVisible(false);
    light5LB.setVisible(false);
    light1StatLB.setVisible(false);
    light2StatLB.setVisible(false);
    light3StatLB.setVisible(false);
    light4StatLB.setVisible(false);
    light5StatLB.setVisible(false);
  }

  public void resumeBTNActionPerformed(java.awt.event.ActionEvent evt){
    pauseBTN.setEnabled(true);
    resumeBTN.setEnabled(false);
      timeTask.startTask();
      for (int i = 0; i < arrayCars.length; i++) {
        arrayCars[i].startTask();
      }
      for (int i = 0; i < arrayLights.length; i++) {
        arrayLights[i].startTask();
      }
  }
}
