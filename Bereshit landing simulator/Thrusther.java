package Bereshit;

public class Thrusther {

    private boolean working;
    private double thrust;
    private double burn_rate;

    public boolean isWorking() {
        return working;
    }
    public void turnOffOn(boolean flag) {
        if( ! this.working){
            this.working = flag;
        }

    }

    Thrusther(double thrust, double burn_rate){
        this.thrust = thrust;
        this.burn_rate = burn_rate;
        this.working = false;
    }
    

}
