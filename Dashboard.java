
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Dashboard extends JFrame implements ActionListener{

    Dashboard(){
        //------Main Image
        ImageIcon declareMainImg = new ImageIcon(ClassLoader.getSystemResource("images/third.jpg"));
        Image setImgSize = declareMainImg.getImage().getScaledInstance(1925, 1030, Image.SCALE_DEFAULT);
        ImageIcon finalImg = new ImageIcon(setImgSize);
        JLabel imgLbl = new JLabel(finalImg);
        imgLbl.setBounds(0,-38,1925,1030);
        add(imgLbl);

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
                hotel.add(rec);

            //------Menu -> Admin
            JMenu admin = new JMenu("ADMIN");
            admin.setForeground(Color.blue);
            admin.setFont(new Font("arial", 1, 14));
            mb.add(admin);
                
                //------Menu Items -> Add Employee
                JMenuItem addEmp = new JMenuItem("ADD EMPLOYEE");
                addEmp.setForeground(Color.black);
                addEmp.setFont(new Font("arial", 1, 13));
                admin.add(addEmp);
                admin.addSeparator();
                
                //------Menu Items -> Add Rooms
                JMenuItem addRooms = new JMenuItem("ADD ROOMS");
                addRooms.setForeground(Color.black);
                addRooms.setFont(new Font("arial", 1, 13));
                admin.add(addRooms);
                admin.addSeparator();
                
                //------Menu Items -> Add Driver
                JMenuItem addDriver = new JMenuItem("ADD DRIVER");
                addDriver.setForeground(Color.black);
                addDriver.setFont(new Font("arial", 1, 13));
                admin.add(addDriver);

        //------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        setIconImage(logo);
        setTitle("Dashboard");
        setBounds(0, 5, 1925, 1030);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
    }

    public static void main(String... ap) {
        new Dashboard();
    }
}
