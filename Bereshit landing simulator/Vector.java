package Bereshit;

import java.util.Objects;

public class Vector {

    private myCordinate x;
    private myCordinate y;
    private myCordinate z;
    Vector(){
        this.x = new myCordinate("x",0.0) ;
        this.y = new myCordinate("y",0.0) ;
        this.z = new myCordinate("z",0.0) ;

    }
    Vector(double x , double y , double z){
        this.x = new myCordinate("x",x) ;
        this.y = new myCordinate("y",y) ;
        this.z = new myCordinate("z",z) ;
    }
    public double getCord(String name){
        switch(name) {
            case "x":
                return this.getX();
            case "y":
                return this.getY();
            case "z":
                return this.getZ();
        }
        return -1.0;
    }
    public double getX() {
        return x.getValue();
    }

    public double getY() {
        return y.getValue();
    }

    public double getZ() {
        return z.getValue();
    }
    public double getDistanceAxie(myCordinate cord){
        return this.getCord(cord.getName());
    }
    public double getDistance(Vector other){
        return Math.sqrt(Math.pow(this.x.getValue() - other.getX(),2) + Math.pow(this.y.getValue() - other.getY(),2)+ Math.pow(this.z.getValue() - other.getZ(),2));
    }
    public double calculateDistanceCircel2d(Vector A, Vector B){
        return Math.sqrt(Math.pow( Math.abs(A.getX() - B.getX()),2) +Math.pow(Math.abs(A.getX() - B.getX()),2));
    }
}