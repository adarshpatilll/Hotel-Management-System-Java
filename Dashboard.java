
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;


public class Dashboard implements ActionListener{
    
    JLabel imgLbl;     //------Globally declared -> imgLbl
    JMenu admin;
    static JFrame fr;

    Dashboard(){

        // Main Frame Declaration
        fr = new JFrame("Dashboard");

        //------Main Image
        ImageIcon declareMainImg = new ImageIcon(ClassLoader.getSystemResource("images/third.jpg"));
        Image setImgSize = declareMainImg.getImage().getScaledInstance(1925, 1030, Image.SCALE_DEFAULT);
        ImageIcon finalImg = new ImageIcon(setImgSize);
        imgLbl = new JLabel(finalImg);
        imgLbl.setBounds(0,-38,1925,1030);
        fr.add(imgLbl);

        //------Clock
        clock();

        //------Title -> VAY To Relax Welcomes You
        JLabel titleLbl = new JLabel("\"VAY To Relax\" Welcomes You");
        titleLbl.setBounds(450,830,1500, 200);
        titleLbl.setFont(new Font("verdana", Font.BOLD, 60));
        titleLbl.setForeground(Color.white);    
        imgLbl.add(titleLbl);

        //------MenuBar
        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 38 , 1925, 30);
        imgLbl.add(mb);

            //------Menu -> Hotel Management
            JMenu hotel = new JMenu("HOTEL MANAGEMENT");
            hotel.setForeground(Color.RED);
            hotel.setFont(new Font("arial", 1, 14));
            mb.add(hotel);
        
                //------Menu Items -> Reception
                JMenuItem rec = new JMenuItem("RECEPTION");
                rec.setForeground(Color.black);
                rec.setFont(new Font("arial", 1, 13));
                rec.addActionListener(this);
                hotel.add(rec);

            //------Menu -> Admin
            admin = new JMenu("ADMIN");
            admin.setForeground(Color.blue);
            admin.setFont(new Font("arial", 1, 14));
            mb.add(admin);
                
                //------Menu Items -> Add Employee
                JMenuItem addEmp = new JMenuItem("ADD EMPLOYEE");
                addEmp.setForeground(Color.black);
                addEmp.setFont(new Font("arial", 1, 13));
                addEmp.addActionListener(this);
                admin.add(addEmp);
                admin.addSeparator();
                
                //------Menu Items -> Add Rooms
                JMenuItem addRooms = new JMenuItem("ADD ROOMS");
                addRooms.setForeground(Color.black);
                addRooms.setFont(new Font("arial", 1, 13));
                addRooms.addActionListener(this);
                admin.add(addRooms);
                admin.addSeparator();
                
                //------Menu Items -> Add Driver
                JMenuItem addDriver = new JMenuItem("ADD DRIVER");
                addDriver.setForeground(Color.black);
                addDriver.setFont(new Font("arial", 1, 13));
                addDriver.addActionListener(this);
                admin.add(addDriver);

        //------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        fr.setIconImage(logo);
        fr.setTitle("Dashboard");
        fr.setBounds(0, 5, 1925, 1030);
        fr.setLayout(null);
        fr.setResizable(false);
        fr.setAlwaysOnTop(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

    //------Globally declared -> timeLbl[]
    private JLabel timeLbl[] = new JLabel[6];

    private void clock(){

        //-------Panel
        JPanel p[] = new JPanel[6];
        int x=70, y=150, w=250, h=80;

        for(int i=0; i<6; i++){
            p[i] = new JPanel(new GridLayout(2,1));
            p[i].setBounds(x, y, w, h);
            p[i].setBackground(new Color(13, 13, 13));
            imgLbl.add(p[i]);
            x+=300;
        }

        //-------Country Label
        JLabel date[] = new JLabel[6];

        String countryName[] = {"   United Arab Emirates", "        U.S. Of America", "                Japan", "        United Kingdom", "               Europe", "              Thailand"};

        for(int i=0; i<6; i++){
            date[i] = new JLabel();
            date[i].setText(countryName[i]);
            date[i].setFont(new Font("tahoma",1, 20));
            date[i].setForeground(Color.ORANGE);
            p[i].add(date[i]);
        }
        
        //-------Time Label
        for(int i=0; i<6; i++){
            timeLbl[i] = new JLabel();
            timeLbl[i].setText("");
            timeLbl[i].setFont(new Font("SansSerif",1, 25));
            timeLbl[i].setForeground(Color.cyan);
            p[i].add(timeLbl[i]);
        }

        //------Update Time Function
        updateTime();
    }


    private void updateTime(){
        String counName[] = {"Asia/Dubai", "US/Mountain", "Asia/Tokyo", "Europe/London", "Europe/Monaco", "Asia/Bangkok"};
        LocalTime lt[] = new LocalTime[6];

        for(int i=0; i<6; i++){
            lt[i] = LocalTime.now(ZoneId.of(counName[i]));
            DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeLbl[i].setText("           "+lt[i].format(f));
        }

        Timer delay = new Timer(1000, d -> {updateTime();});
        delay.start();
    }

    
    @Override
    public void actionPerformed(ActionEvent e){

        switch(e.getActionCommand()){
            case "ADD EMPLOYEE":
                new AddEmployee();
                break;
                
            case "ADD ROOMS":
                new AddRooms();
                break;
                
            case "ADD DRIVER":
                new AddDriver();
                break;
                
            case "RECEPTION":
                new Reception();
                break;
        }
    }


    public static void main(String... ap) {
        new Dashboard();
    }
}
