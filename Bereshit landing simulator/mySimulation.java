package Bereshit;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class mySimulation extends JFrame{
    // for fields side
    private JLabel Altitude;
    private JLabel Fuel;
    private JLabel Battery;
    private JLabel Vertical;
    private JLabel Horizontal;
    private JLabel mainEng;
    private JLabel[] secondaryEngArray;
    private JLabel time;
    private spaceship bereshit;
    int h = 100;
    private myMoon moon;
    public PID pid;

    private DecimalFormat df = new DecimalFormat("0.00");
    // for fields side
    public mySimulation(spaceship apolo) {
        super();
        bereshit = apolo;
        this.moon = new myMoon();
        this.secondaryEngArray = new JLabel[8];
        pid= new PID(0.9,0.0,0.0) ;
    }
    @Override
    public void update(Graphics g){
        super.update(g);
        if( !this.bereshit.getMainThruster().isWorking()){
            this.mainEng.setBackground(new Color(255, 178, 104));
        }else {
            System.out.println(this.bereshit.getMainThruster().isWorking());
            this.mainEng.setBackground(new Color(160, 160, 160));
        }
        for (int i = 0; i < this.bereshit.getSecondaryThrusters().size(); i++) {
            try {
                if(this.bereshit.getSecondaryThruster(i).isWorking()){
                    this.secondaryEngArray[i].setBackground(new Color(255, 178, 104));
                }else{
                    this.secondaryEngArray[i].setBackground(new Color(160, 160, 160));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.Altitude.setText(df.format(this.bereshit.getAltitude()));
        this.Horizontal.setText(df.format(this.bereshit.getHorizontalSpeed()));
        this.Vertical.setText(df.format(this.bereshit.getVerticalSpeed()));
        this.Fuel.setText(df.format(this.bereshit.getFuel()));
        this.Battery.setText(df.format(this.bereshit.getBattery()));
        this.time.setText("time is "+df.format(this.bereshit.getTime()));
    }


    public void initialize() {
        this.setSize(1600,900);
        this.setTitle("Bereshit Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.getContentPane().setLayout(null);
        this.setBackground(Color.BLACK);

        // local labels. :
        JLabel headline1 = new JLabel("");
        headline1.setBounds(100, 30, 100, 50);
        headline1.setBackground(new Color(0,128,255));
        headline1.setOpaque(true);
        headline1.setText("       Fields :");

        JLabel logo = new JLabel("");       // just for kidding
        logo.setBounds(130, 600, 100, 50);
        logo.setBackground(new Color(0,128,255));
        logo.setOpaque(true);
        logo.setText("   MGE SPACE ");

        JLabel headline2 = new JLabel("");
        headline2.setBounds(100, 30, 100, 50);
        headline2.setBackground(new Color(0,128,255));
        headline2.setOpaque(true);
        headline2.setText("   Engines fire :");

        // fields for spaceship
        JLabel atext = new JLabel();
        atext.setBounds(450,105,250,50);
        atext.setText("Altitude (m):");
        atext.setForeground(Color.white);
        atext.setFont(new Font("Ariel", Font.BOLD, 14));
        this.add(atext);

        this.Altitude = new JLabel("");
        Border border = BorderFactory.createLineBorder(new Color(192,192,192), 2);
        Altitude.setBounds(50, 120, 250, 40);
        Altitude.setBackground(new Color(160, 160, 160));
        Altitude.setOpaque(true);
        Altitude.setForeground(new Color(0,0,51));
        Altitude.setBorder(border);
        Altitude.setFont(new Font("Ariel", Font.BOLD, 18));
        Altitude.setText("");

        Altitude.setHorizontalAlignment(SwingConstants.CENTER);
        Altitude.setVerticalAlignment(SwingConstants.CENTER);

        JLabel ftext = new JLabel();
        ftext.setBounds(450,180,250,40);
        ftext.setText("Fuel (Kg):");
        ftext.setForeground(Color.white);
        ftext.setFont(new Font("Ariel", Font.BOLD, 14));
        this.add(ftext);

        this.time = new JLabel();
        time.setBounds(500,500,250,40);
        time.setText("");
        time.setForeground(Color.white);
        time.setFont(new Font("Ariel", Font.BOLD, 14));
        this.add(time);

        this.Fuel = new JLabel();
        Fuel.setBorder(border);
        Fuel.setBounds(50, 190, 250, 40);
        Fuel.setBackground(new Color(160, 160, 160));
        Fuel.setOpaque(true);
        Fuel.setForeground(new Color(0,0,51));
        Fuel.setFont(new Font("Ariel", Font.BOLD, 18));
        Fuel.setText("");


        Fuel.setHorizontalAlignment(SwingConstants.CENTER);
        Fuel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel btext = new JLabel();
        btext.setBounds(450,250,250,40);
        btext.setText("Battery Charge (%) :");
        btext.setForeground(Color.white);
        btext.setFont(new Font("Ariel", Font.BOLD, 14));
        this.add(btext);

        this.Battery = new JLabel("");
        Battery.setBounds(50, 260, 250, 40);
        Battery.setBackground(new Color(160, 160, 160));
        Battery.setOpaque(true);
        Battery.setForeground(new Color(0,0,51));
        Battery.setBorder(border);
        Battery.setFont(new Font("Ariel", Font.BOLD, 18));
        Battery.setText("");


        Battery.setHorizontalAlignment(SwingConstants.CENTER);
        Battery.setVerticalAlignment(SwingConstants.CENTER);

        JLabel vtext = new JLabel();
        vtext.setBounds(450,320,250,40);
        vtext.setText("Vertical speed (m/s^2) :");
        vtext.setForeground(Color.white);
        vtext.setFont(new Font("Ariel", Font.BOLD, 14));
        this.add(vtext);

        this.Vertical = new JLabel("");
        Vertical.setBounds(50, 330, 250, 40);
        Vertical.setBackground(new Color(160, 160, 160));
        Vertical.setOpaque(true);
        Vertical.setForeground(new Color(0,0,51));
        Vertical.setBorder(border);
        Vertical.setFont(new Font("Ariel", Font.BOLD, 18));
        Vertical.setText("");


        Vertical.setHorizontalAlignment(SwingConstants.CENTER);
        Vertical.setVerticalAlignment(SwingConstants.CENTER);

        JLabel htext = new JLabel();
        htext.setBounds(450,390,250,40);
        htext.setText("Horizontal speed (m/s^2) :");
        htext.setForeground(Color.white);
        htext.setFont(new Font("Ariel", Font.BOLD, 14));
        this.add(htext);

        this.Horizontal = new JLabel("");
        Horizontal.setBounds(50, 400, 250, 40);
        Horizontal.setBackground(new Color(160, 160, 160));
        Horizontal.setOpaque(true);
        Horizontal.setForeground(new Color(0,0,51));
        Horizontal.setBorder(border);
        Horizontal.setFont(new Font("Ariel", Font.BOLD, 18));
        Horizontal.setText("");      // the real info we will get later

        Horizontal.setHorizontalAlignment(SwingConstants.CENTER);
        Horizontal.setVerticalAlignment(SwingConstants.CENTER);

        // engines side :
        this.mainEng = new JLabel("");
        mainEng.setBounds(120, 240, 100, 100);
        mainEng.setBackground(Color.orange);
        mainEng.setOpaque(true);
        mainEng.setText("          MMT");

        this.secondaryEngArray[0]= new JLabel("");
        this.secondaryEngArray[0].setBounds(170, 120, 50, 50);
        this.secondaryEngArray[0].setBackground(Color.orange);
        this.secondaryEngArray[0].setOpaque(true);
        this.secondaryEngArray[0].setText("     1");

        this.secondaryEngArray[1] = new JLabel("");
        this.secondaryEngArray[1].setBounds(240, 180, 50, 50);
        this.secondaryEngArray[1].setBackground(Color.orange);
        this.secondaryEngArray[1].setOpaque(true);
        this.secondaryEngArray[1].setText("      2");

        this.secondaryEngArray[2] = new JLabel("");
        this.secondaryEngArray[2].setBounds(80, 130, 50, 50);
        this.secondaryEngArray[2].setBackground(Color.orange);
        this.secondaryEngArray[2].setOpaque(true);
        this.secondaryEngArray[2].setText("      3");

        this.secondaryEngArray[3] = new JLabel("");
        this.secondaryEngArray[3].setBounds(30, 190, 50, 50);
        this.secondaryEngArray[3].setBackground(Color.orange);
        this.secondaryEngArray[3].setOpaque(true);
        this.secondaryEngArray[3].setText("      4");

        this.secondaryEngArray[4] = new JLabel("");
        this.secondaryEngArray[4].setBounds(170, 410, 50, 50);
        this.secondaryEngArray[4].setBackground(Color.orange);
        this.secondaryEngArray[4].setOpaque(true);
        this.secondaryEngArray[4].setText("      5");

        this.secondaryEngArray[5] = new JLabel("");
        this.secondaryEngArray[5].setBounds(240, 350, 50, 50);
        this.secondaryEngArray[5].setBackground(Color.orange);
        this.secondaryEngArray[5].setOpaque(true);
        this.secondaryEngArray[5].setText("      6");

        this.secondaryEngArray[6] = new JLabel("");
        this.secondaryEngArray[6].setBounds(80, 400, 50, 50);
        this.secondaryEngArray[6].setBackground(Color.orange);
        this.secondaryEngArray[6].setOpaque(true);
        this.secondaryEngArray[6].setText("      7");

        this.secondaryEngArray[7] = new JLabel("");
        this.secondaryEngArray[7].setBounds(30, 340, 50, 50);
        this.secondaryEngArray[7].setBackground(Color.orange);
        this.secondaryEngArray[7].setOpaque(true);
        this.secondaryEngArray[7].setText("      8");

        JLabel engain_side = new JLabel("");
        engain_side.setBackground(Color.BLACK);
        engain_side.setOpaque(true);
        engain_side.setBounds(50, 20, 340, 800);
        this.getContentPane().add(engain_side);

        JLabel simulation = new JLabel("");
        simulation.setBackground(Color.BLACK);
        simulation.setOpaque(true);
        simulation.setBounds(750, 20, 340, 800);

        //adding fields to frame
        this.getContentPane().add(simulation);
        JLabel fields_side = new JLabel("");
        fields_side.setBackground(Color.black);
        fields_side.setOpaque(true);
        fields_side.setBounds(400, 20, 340, 800);
        fields_side.add(headline1);
        fields_side.add(Altitude);
        fields_side.add(Fuel);
        fields_side.add(Battery);
        fields_side.add(Vertical);
        fields_side.add(Horizontal);
        fields_side.add(logo);
        this.getContentPane().add(fields_side);

        //adding engines to frame
        engain_side.add(headline2);
        engain_side.add(mainEng);
        engain_side.add(this.secondaryEngArray[0]);
        engain_side.add(this.secondaryEngArray[1]);
        engain_side.add(this.secondaryEngArray[2]);
        engain_side.add(this.secondaryEngArray[3]);
        engain_side.add(this.secondaryEngArray[4]);
        engain_side.add(this.secondaryEngArray[5]);
        engain_side.add(this.secondaryEngArray[6]);
        engain_side.add(this.secondaryEngArray[7]);
    }
    public void setAltitude(JLabel altitude) {
        Altitude = altitude;
    }

    public void setFuel(JLabel fuel) {
        Fuel = fuel;
    }

    public void setBattery(JLabel battery) {
        Battery = battery;
    }

    public void setVertical(JLabel vertical) {
        Vertical = vertical;
    }

    public myMoon getMoon() {
        return moon;
    }

    public void setHorizontal(JLabel horizontal) {
        Horizontal = horizontal;
    }

    public void setMainEng(JLabel mainEng) {
        this.mainEng = mainEng;
    }

    public void setSecondaryEngArray(JLabel[] secondaryEngArray) {
        this.secondaryEngArray = secondaryEngArray;
    }
    public JLabel getAltitude() {
        return Altitude;
    }

    public JLabel getFuel() {
        return Fuel;
    }

    public JLabel getBattery() {
        return Battery;
    }

    public JLabel getVertical() {
        return Vertical;
    }

    public JLabel getHorizontal() {
        return Horizontal;
    }

    public JLabel getMainEng() {
        return mainEng;
    }
    public JLabel[] getSecondaryEngArray() {
        return secondaryEngArray;
    }
    public JLabel getSecondaryEng(int index) throws Exception {
        if(index > 7 || index < 0) throw new Exception("index out of bounds");
        return secondaryEngArray[index];
    }
    public spaceship getBereshit() {
        return bereshit;
    }

    public JLabel getTimetext() {
        return this.time;
    }
}
