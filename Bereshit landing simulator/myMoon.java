package Bereshit;

public class myMoon {
    public Vector center;
    private double radius = 1737000.5; // in meters
    private double circumference  = 10917000; // in meters
    private double mass = 7.35* Math.pow(10.0,22.0);
    private double moonSpeed = 1700; //in m/s
    private double acceleration = 1.622; // im m/s/s

    public double getMoonSpeed() {
        return moonSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    myMoon(){
        center = new Vector();
    }
    public double getAcc(double speed) {
        double n = Math.abs(speed)/moonSpeed;
        double ans = (1-n)*acceleration;
        return ans;
    }

    public Vector getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public double getCircumference() {
        return circumference;
    }
    public double calculateGravityForce(double distance){
        //by formula 6.67 is g constant - > m/s/s
        return ((6.67* Math.pow(10.0,-11.0)*this.mass)/Math.pow(distance,2));
    }
}
