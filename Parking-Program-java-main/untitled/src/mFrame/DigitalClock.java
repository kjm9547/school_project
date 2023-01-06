package mFrame;

import javax.swing.*;
import java.time.LocalDateTime;

public class DigitalClock extends Thread{
    JLabel clock;
    public DigitalClock(JLabel clock){
        this.clock=clock;
    }
    public void run(){
        LocalDateTime time = LocalDateTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        while(true){
            clock.setText("<html><body><center>" + Integer.toString(hour) + ":" + Integer.toString(minute) + "<br>" + Integer.toString(second) + "초</center></body></html>");
            second++;
            if(second == 60){
                second = 0;
                minute ++;
            }
            if(minute == 60){
                minute = 0;
                hour ++;
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                return;
            }
        }
    }
}
