import java.awt.*;
import javax.swing.*;


public class Loading extends JFrame {
    JProgressBar p;
    JLabel l1,l2,l3,l4,l5, status;

    Loading(){

        //Calling Title Showing Funtion
        titleDisplay();

        //--------Status
        status = new JLabel("Loading...");
        status.setBounds(570, 560, 300, 200);
        status.setForeground(Color.white);
        status.setFont(new Font("Arial",1,20));
        add(status);

        //--------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/loading.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel finalImg = new JLabel(i3);
        finalImg.setBounds(0,0,800,700); 
        add(finalImg);

        //--------Progress Bar
        p = new JProgressBar();
        p.setValue(0);
        p.setBounds(-4,685,805,16);
        p.setBackground(Color.BLACK);
        p.setForeground(Color.WHITE);
        finalImg.add(p);

        //---------Frame
        setBounds(600,150,800,700);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setVisible(true);

        //Calling Progress Bar Running Function
        runProgressBar();
    }

    private void titleDisplay() {
        //-------Label - V
        l1 = new JLabel("V");
        l1.setBounds(130,100,350,130);
        l1.setFont(new Font("Arial Black",1,110));
        l1.setForeground(Color.white);
        add(l1);
        
        //-------Label - A
        l2 = new JLabel("A");
        l2.setBounds(210,100,350,130);
        l2.setFont(new Font("Arial Black",1,110));
        l2.setForeground(Color.white);
        add(l2);
        
        //-------Label - Y
        l3 = new JLabel("Y");
        l3.setBounds(290,100,350,130);
        l3.setFont(new Font("Arial Black",1,110));
        l3.setForeground(Color.white);
        add(l3);
        
        //-------Label - TO
        l4 = new JLabel("TO");
        l4.setBounds(500,100,350,130);
        l4.setFont(new Font("Arial Black",1,110));
        l4.setForeground(Color.white);
        add(l4);
        
        //-------Label - RELAX
        l5 = new JLabel("RELAX");
        l5.setBounds(180,200,500,300);
        l5.setFont(new Font("Arial Black",1,110));
        l5.setForeground(Color.white);
        add(l5);
    }

    private void blinking(){
        Thread t = new Thread(
            ()->{
                while(true)
                    try{
                    l1.setForeground(Color.black);
                    Thread.sleep(150);
                    l1.setForeground(Color.MAGENTA);
                    Thread.sleep(150);
                    l1.setForeground(Color.white);
                    
                    l2.setForeground(Color.black);
                    Thread.sleep(150);
                    l2.setForeground(Color.MAGENTA);
                    Thread.sleep(150);
                    l2.setForeground(Color.white);

                    l1.setForeground(Color.green);
                    Thread.sleep(150);
                    l1.setForeground(Color.white);
                    
                    l3.setForeground(Color.black);
                    Thread.sleep(150);
                    l3.setForeground(Color.MAGENTA);
                    Thread.sleep(150);
                    l3.setForeground(Color.white);

                    l2.setForeground(Color.green);
                    Thread.sleep(150);
                    l2.setForeground(Color.white);
                    
                    l4.setForeground(Color.black);
                    Thread.sleep(150);
                    l4.setForeground(Color.MAGENTA);
                    Thread.sleep(150);
                    l4.setForeground(Color.white);

                    l3.setForeground(Color.green);
                    Thread.sleep(150);
                    l3.setForeground(Color.white);
                    
                    l5.setForeground(Color.black);
                    Thread.sleep(150);
                    l5.setForeground(Color.MAGENTA);
                    Thread.sleep(150);
                    l5.setForeground(Color.white);

                    l4.setForeground(Color.green);
                    Thread.sleep(150);
                    l4.setForeground(Color.MAGENTA);
                    Thread.sleep(150);
                    l4.setForeground(Color.white);

                    l5.setForeground(Color.green);
                    Thread.sleep(150);
                    l5.setForeground(Color.white);
                    
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        );

        t.start(); 
    }

    private void runProgressBar(){
        blinking();   

        for(int i=0; i<=100; i++){    
            p.setValue(i);
            try {
                Thread.sleep(45);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i>=20 && i<50)    status.setText("Connecting Database...");
            else if(i>=40 && i<80)    status.setText("Loading Modules...");
            else if(i>=80)    status.setText("Starting...");
        }
        setVisible(false);
        new LandingPage();
    }
}
