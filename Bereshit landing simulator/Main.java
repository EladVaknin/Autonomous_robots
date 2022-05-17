package Bereshit;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;

class myThread extends Thread{
    String name;
    mySimulation window;
    PID pid;


    AtomicBoolean flag = new AtomicBoolean(false);
    public myThread(String name, mySimulation window){
        this.window = window;
        this.window.initialize();
        this.name = name;

    }
    public void run() {
        synchronized(this) {
            while( !flag.get() ) {
                if (name.equals("gui")) {
                    window.setVisible(true);
                    window.setResizable(true);
                    window.update(window.getGraphics());
                    if (flag.get()) {
                        window.setVisible(false);
                        Thread.currentThread().stop();
                    }
                } else if (name.equals("cpu")) {
                    if (window.getBereshit().getAltitude() < 5)//landed
                    {
                        System.out.println("time :"+window.getBereshit().getTime()+" alt is: "+window.getBereshit().getAltitude() +" vs is: "+ window.getBereshit().getVerticalSpeed()+" fuel :"+window.getBereshit().getFuel()+" NN: "+window.getBereshit().getNN());
                        flag.set(true);
                        Thread.currentThread().stop();
                    }
                    if(window.getBereshit().getTime() % 10 == 0 ) {
                        System.out.println("time :"+window.getBereshit().getTime()+" alt is: "+window.getBereshit().getAltitude() +" vs is: "+ window.getBereshit().getVerticalSpeed()+" fuel :"+window.getBereshit().getFuel()+" NN: "+window.getBereshit().getNN());
                    }
//                     over 2 km above the ground
                    if(window.getBereshit().getAltitude()>2000) {	// maintain a vertical speed of [20-25] m/s

                        if(window.getBereshit().getVerticalSpeed() >25) {
                            //option for our change -can also update here
                            window.getBereshit().setNN(window.getBereshit().getNN()+0.003*window.getBereshit().getDt());} // more power for braking

                    if(window.getBereshit().getVerticalSpeed() <20) {window.getBereshit().setNN(window.getBereshit().getNN()-0.003*window.getBereshit().getDt());;} // less power for braking
//                        System.out.println("NN: "+window.getBereshit().getNN());
                    }
                    // lower than 2 km - horizontal speed should be close to zero
                    else {
                        if((window.getBereshit().getAngelFromGround() > 3)){
                            window.getBereshit().setAngelFromGround(window.getBereshit().getAngelFromGround() - 3 );
                        } // rotate to vertical position.
                        else {window.getBereshit().setAngelFromGround(0);}
                        //main change
                        window.getBereshit().setNN(0.5); // brake slowly, a proper PID controller here is needed!

                        double tmp_mm=window.pid.update(window.getBereshit().getNN(),window.getBereshit().getDt());
                        window.getBereshit().setNN( tmp_mm);
                        if(window.getBereshit().getHorizontalSpeed()<2) {
                            window.getBereshit().setHorizontalSpeed(0);
                        }
                        if(window.getBereshit().getAltitude()<125) { // very close to the ground!
                            window.getBereshit().setNN(1); // maximum braking!
                            if(window.getBereshit().getVerticalSpeed()<5) {window.getBereshit().setNN(0.7);} // if it is slow enough - go easy on the brakes
                        }
                    }
                    if(window.getBereshit().getAltitude()<5) { // no need to stop
                        window.getBereshit().setNN(0.0);
                    }
                    // main computations
                    double ang_rad = Math.toRadians(window.getBereshit().getAngelFromGround());
                    double h_acc = Math.sin(ang_rad)*window.getBereshit().getAcceleration();
                    double v_acc = Math.cos(ang_rad)*window.getBereshit().getAcceleration();
                    double vacc = window.getMoon().getAcc(window.getBereshit().getHorizontalSpeed());
                    window.getBereshit().setTime(window.getBereshit().getTime() + window.getBereshit().getDt());
                    double dw = window.getBereshit().getDt()*window.getBereshit().getAll_burn()*window.getBereshit().getNN();
                    if(window.getBereshit().getFuel()>0) {
                        window.getBereshit().setFuel(window.getBereshit().getFuel() - dw);
                        window.getBereshit().setWieght( window.getBereshit().weight_emp +  window.getBereshit().getFuel());
                        window.getBereshit().setAcceleration( window.getBereshit().getNN() *  window.getBereshit().accMAX( window.getBereshit().getWieght()));
                    }
                    else { // ran out of fuel
                        window.getBereshit().setAcceleration(0);
                    }
                    v_acc -= vacc;
                    if( window.getBereshit().getHorizontalSpeed()>0) {
                        window.getBereshit().setHorizontalSpeed( window.getBereshit().getHorizontalSpeed() -(h_acc *  window.getBereshit().getDt()));
                    }
                    window.getBereshit().setDist( window.getBereshit().getDt() -  window.getBereshit().getHorizontalSpeed() *  window.getBereshit().getDt());
                    window.getBereshit().setVerticalSpeed( window.getBereshit().getVerticalSpeed()-(v_acc* window.getBereshit().getDt()));
                    window.getBereshit().setAltitude( window.getBereshit().getAltitude()- ( window.getBereshit().getVerticalSpeed() *  window.getBereshit().getDt()));
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

public class Main{
    private int FPS = 60;
    private static mySimulation window;
    public static void main(String args[]) {
        window = new mySimulation(new spaceship());
        myThread cpu = new myThread("cpu",window);
        myThread gui = new myThread("gui",window);
        cpu.start();
        gui.start();
        try {
            cpu.join();
            gui.join();
            System.out.println("done");
        }
        catch(Exception e)
        {
            System.out.println("Interrupted");
        }

    }
}


