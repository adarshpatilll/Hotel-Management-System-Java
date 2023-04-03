
import java.util.*;
import javax.swing.*;
import java.awt.*;

import java.sql.*;
import net.proteanit.sql.*;

import java.time.*;
import java.time.format.*;


public class Reception extends JFrame {

    private JButton bt[] = new JButton[13];

    Reception(){

        //------Button Panel
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setBounds(40,50,260,700);
        p.setLayout(new GridLayout(13,1,0,15));
        add(p);

        //------Button Declaration
        String name[] = {"New Customer Form", "Rooms", "Department", "All Employees", "Customer Info", "Manager Info", "Checkout", "Update Status", "Update Room Status", "Pickup Service", "Search Rooms", "Logout", "Cancel"};

        for(int i=0; i<13; i++){
            bt[i] = new JButton(name[i]);
            bt[i].setBackground(new Color(65416));
            bt[i].setForeground(Color.black);
            bt[i].setFont(new Font("Arial", 0, 18));
            p.add(bt[i]);
        }
        bt[12].setBackground(new Color(254,111,94));
        bt[12].setForeground(Color.black);

        //-------Button Working Function Call
        btnFunc();

        //------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/fourth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(150, 30, 1000, 735);
        add(img);
    
        //------Frame 
        setTitle("Reception");
        getContentPane().setBackground(Color.white);
        setBounds(500,150,1000,800);
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void btnFunc(){
        bt[0].addActionListener(a -> {setVisible(false);    newCustForm();});
        bt[1].addActionListener(a -> {setVisible(false);    rooms();});
        bt[2].addActionListener(a -> {setVisible(false);    depart();});
        bt[3].addActionListener(a -> {setVisible(false);    allEmp();});
        bt[4].addActionListener(a -> {setVisible(false);    custInfo();});
        bt[5].addActionListener(a -> {setVisible(false);    managerInfo();});
        bt[6].addActionListener(a -> {setVisible(false);    checkout();});
        bt[7].addActionListener(a -> {setVisible(false);    updateStatus();});
        bt[8].addActionListener(a -> {setVisible(false);    updateRoomStatus();});
        bt[9].addActionListener(a -> {setVisible(false);    pickupService();});
        bt[10].addActionListener(a -> {setVisible(false);    searchRooms();});
        bt[11].addActionListener(a -> {logout();});
        bt[12].addActionListener(a -> {setVisible(false);});
    }
    
    @SuppressWarnings("all")
    private void newCustForm(){
        
        //-------Frame
        JFrame ncf = new JFrame();
        
        //-------Panel 1 Labels
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(9, 1, 0, 30));
        p1.setBounds(30, 30, 150, 510);
        p1.setBackground(Color.white);
        ncf.add(p1);
        
        //-------Panel 2 Field
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(9, 1, 0, 25));
        p2.setBounds(230, 27, 230, 515);
        p2.setBackground(Color.white);
        ncf.add(p2);

        //-------Labels
        JLabel lb[] = new JLabel[9];

        String lblName[] = {"Customer ID", "Name", "Gender", "Country", "ID Proof", "ID Proof Number", "Room Number", "Check-in Time", "Deposit"};

        for(int i=0; i<9; i++){
            lb[i] = new JLabel(lblName[i]);
            lb[i].setForeground(Color.black);
            lb[i].setFont(new Font("Arial", 0, 18));
            p1.add(lb[i]);
        }

        //-------Fields
        JTextField fd[] = new JTextField[9];

        Random  rand = new Random();

        LocalDate dt = LocalDate.now();
        LocalTime lt = LocalTime.now();
        DateTimeFormatter fdate = DateTimeFormatter.ofPattern("E, dd-MMM-uuuu");
        DateTimeFormatter ftime = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fldName[] = {Integer.toString(rand.nextInt(10000, 11999)), "", "", "", "", "", "", " "+dt.format(fdate)+" / "+lt.format(ftime), ""};

        Font font = new Font("Arial", 0, 18);
        Color bg = Color.WHITE;
        Color fg = Color.BLACK;
        
        //--------TextField Loop
        for(int i=0; i<9; i++){
            if(i==0 || i==1 || i==3 || i==5 || i==7 || i==8){
                fd[i] = new JTextField();
                fd[i].setText(fldName[i]);
                fd[i].setForeground(Color.black);
                fd[i].setBackground(Color.white);
                fd[i].setFont(new Font("Arial", 0, 18));
            }
            if(i==0 || i==7){
                fd[0].setForeground(Color.black);
                fd[0].setBackground(Color.pink);
                fd[i].setEditable(false);
            }
        }
        fd[7].setFont(new Font("sans serif", 0, 17));

        //---------Combo Boxes
        String comboIdStr[] = {"Passport", "Aadhaar Card", "Pan Card", "Driving License"};
        
        JComboBox combo[] = new JComboBox[2];
        for(int i=0; i<2; i++){
            if(i==0)
                combo[i] = new JComboBox<>(comboIdStr);
            else {
                //-------Logic for Room No. Combo Box
                combo[i] = new JComboBox<>();
                try {
                    Conn c = new Conn();
                    ResultSet rs =  c.s.executeQuery("SELECT * FROM room WHERE avail = 'Vacant'");
                    while(rs.next()){
                        combo[i].addItem(rs.getString("room"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            combo[i].setFont(font);
            combo[i].setBackground(bg);
            combo[i].setForeground(fg);
        }

        //---------Gender - Radio Buttons
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1,2,10,0));
        p3.setBounds(230, 50, 230, 25);
        p3.setBackground(bg);

        String genStr[] = {"Male", "Female"};
        JRadioButton gender[] = new JRadioButton[2];
        for(int i=0; i<2; i++){
            gender[i] = new JRadioButton(genStr[i]);
            gender[i].setFont(font);
            gender[i].setBackground(bg);
            gender[i].setForeground(fg);
            p3.add(gender[i]);
        }

        ButtonGroup btnGrp = new ButtonGroup();
        btnGrp.add(gender[0]);
        btnGrp.add(gender[1]);
        
        //-------Adding all TextField in Panel-2
        p2.add(fd[0]);   p2.add(fd[1]);   p2.add(p3);   p2.add(fd[3]);   p2.add(combo[0]);   p2.add(fd[5]);   p2.add(combo[1]);   p2.add(fd[7]);   p2.add(fd[8]);

        //-------Buttons
        JButton bt[] = new JButton[2];
        
        bt[0] = new JButton("ADD CUSTOMER");
        bt[0].setFont(new Font("Arial", 0, 18));
        bt[0].setBounds(30, 600, 200, 30);
        bt[0].setBackground(new Color(126, 255, 141));
        bt[0].addActionListener(ac -> {

            String id = fd[0].getText();
            String name = fd[1].getText();
            String country = fd[3].getText();
            String idProof = (String) combo[0].getSelectedItem();
            String idProofNo = fd[5].getText().toUpperCase();
            String roomNo = (String) combo[1].getSelectedItem();
            String time = fd[7].getText();
            String deposit = fd[8].getText();
            String gen = null;

            if (gender[0].isSelected())
                gen = "Male";
            else if (gender[1].isSelected())
                gen = "Female";


            if (id.equals("") || name.equals("") || country.equals("") || idProof.equals("") || idProofNo.equals("") || roomNo.equals("") || time.equals("") || deposit.equals("")){
                JOptionPane.showMessageDialog(null, "All Fields Are Required...", "Warning", 2);
            }
            else
                try {
                    Conn c = new Conn();

                    c.s.executeUpdate("INSERT INTO customer values('"+id+"','"+name+"','"+gen+"','"+country+"','"+idProof+"','"+idProofNo+"','"+roomNo+"','"+time+"','"+deposit+"')");
                    
                    c.s.executeUpdate("update room set avail = 'Occupied' where room = '"+roomNo+"'");

                    JOptionPane.showMessageDialog(null,  "Customer Added Successfully","Success", 1);

                    c.s.close();

                    ncf.setVisible(false);
                    super.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Enter Valid Input (Name, Country, ID No., Deposit) ", "Warning",  2);
                }
        });

        bt[1] = new JButton("BACK");
        bt[1].setFont(new Font("Arial", 0, 18));
        bt[1].setBounds(310, 600, 150, 30);
        bt[1].setBackground(new Color(255, 250, 74)); 
        bt[1].addActionListener(b -> {ncf.setVisible(false);    super.setVisible(true);});

        ncf.add(bt[0]);   ncf.add(bt[1]);

        //-------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(500,60,400,500);
        ncf.add(img);

        //-------Frame
        ncf.getContentPane().setBackground(Color.white);
        ncf.setBounds(500,200,900,670);
        ncf.setLayout(null);
        ncf.setUndecorated(true);
        ncf.setVisible(true);
        
    }
    
    private void rooms() {
        
        //-------Frame
        JFrame rooms;
        rooms = new JFrame();

        //-------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 850, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(870, 30, 500, 800);
        rooms.add(img);        

        //-------Data Table
        String cname[] = {"Room No.", "Price", "Clean Status", "Availability", "Room Type"};
        String cnamea[][] = {{"Room No.", "Price", "Clean Status", "Availability", "Room Type"}};
        JTable headingTable = new JTable(cnamea, cname);
        headingTable.setBounds(30, 30, 800, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.setEnabled(false);
        rooms.add(headingTable);

        JTable dataTab = new JTable();
        dataTab.setBounds(30, 60, 800, 820);
        dataTab.setFont(new Font("verdana", 0, 14));
        dataTab.setEnabled(false);
        dataTab.setRowHeight(23);
        dataTab.setForeground(Color.black);
        rooms.add(dataTab);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from room");
            dataTab.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e){
            e.printStackTrace();
        }

        JButton back = new JButton("B A C K");
        back.setBounds(870, 845, 500, 40);
        back.setFont(new Font("Arial", 1, 23));
        back.setBackground(new Color(255, 250, 74)); 
        back.addActionListener(b -> {rooms.setVisible(false);   super.setVisible(true);});
        rooms.add(back);

        //-------Frame
        rooms.getContentPane().setBackground(Color.white);
        rooms.setBounds(270, 80, 1400, 900);    
        rooms.setLayout(null);
        rooms.setUndecorated(true);
        rooms.setVisible(true);
    }

    JFrame depart;
    private void depart() {
        System.out.println("Btn 3");
    }

    JFrame allEmp;
    private void allEmp(){
        System.out.println("Btn 4");
    }
    
    JFrame custInfo;
    private void custInfo() {
        System.out.println("Btn 5");
    }
    
    JFrame manInfo;
    private void managerInfo() {
        System.out.println("Btn 6");
    }
    
    JFrame checkout;
    private void checkout() {
        System.out.println("Btn 7");
    }

    JFrame upStatus;
    private void updateStatus() {
        System.out.println("Btn 8");
    }

    JFrame upRoomStatus;
    private void updateRoomStatus() {
        System.out.println("Btn 9");
    }

    JFrame pickService;
    private void pickupService() {
        System.out.println("Btn 10");
    }

    JFrame searchRooms;
    private void searchRooms() {
        System.out.println("Btn 11");
    }

    private void logout() {
        int res = JOptionPane.showConfirmDialog(null, "You really want to Quit..?", "Warning", 0);
        if(res==0){
            setVisible(false);
            Dashboard.fr.setVisible(false);
            new LandingPage();
            LoginLogoutTime.logoutFunc();
            LoginLogoutTime.duration();
        }
    }

    public static void main(String... ap) throws Exception{
        new Reception();
    }
}