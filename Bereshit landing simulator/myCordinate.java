package Bereshit;

    public class myCordinate{
        private String name;
        double value;
        myCordinate(String n, double v){
            this.name = n;
            this.value = v;
        }

        public double getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
