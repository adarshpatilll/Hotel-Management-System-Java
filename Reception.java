
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.*;

import java.time.*;
import java.time.format.*;

@SuppressWarnings("all")
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
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void btnFunc(){
        bt[0].addActionListener(a -> {setVisible(false);    newCustForm();});
        bt[1].addActionListener(a -> {setVisible(false);    rooms();});
        bt[2].addActionListener(a -> {setVisible(false);    depart();});
        bt[3].addActionListener(a -> {setVisible(false);    empInfo("All");});
        bt[4].addActionListener(a -> {setVisible(false);    custInfo();});
        bt[5].addActionListener(a -> {setVisible(false);    empInfo("Manager");});
        bt[6].addActionListener(a -> {setVisible(false);    checkout();});
        bt[7].addActionListener(a -> {setVisible(false);    updateStatus();});
        bt[8].addActionListener(a -> {setVisible(false);    updateRoomStatus();});
        bt[9].addActionListener(a -> {setVisible(false);    pickupService();    spPickup.setColumnHeaderView(null);});
        bt[10].addActionListener(a -> {setVisible(false);    searchRooms();     sp.setColumnHeaderView(null);});
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

        String fldName[] = {Integer.toString(rand.nextInt(10000, 11999)), "", "", "", "", "", "", dt.format(fdate)+" / "+lt.format(ftime), ""};

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
                JOptionPane.showMessageDialog(ncf, "All Fields Are Required...", "Warning", 2);
            }
            else
                try {
                    Conn c = new Conn();

                    c.s.executeUpdate("INSERT INTO customer values('"+id+"','"+name+"','"+gen+"','"+country+"','"+idProof+"','"+idProofNo+"','"+roomNo+"','"+time+"','"+deposit+"')");
                    
                    c.s.executeUpdate("update room set avail = 'Occupied' where room = '"+roomNo+"'");

                    JOptionPane.showMessageDialog(ncf,  "Customer Added Successfully","Success", 1);

                    c.s.close();

                    ncf.setVisible(false);
                    super.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(ncf, "Enter Valid Input (Name, Country, ID No., Deposit) ", "Warning",  2);
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
        ncf.setAlwaysOnTop(true);
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

        //-------Table - Heading
        String col[] = {"Room No.", "Price", "Clean Status", "Availability", "Room Type"};
        String row[][] = {{"Room No.", "Price", "Clean Status", "Availability", "Room Type"}};
        JTable headingTable = new JTable(row, col);
        headingTable.setBounds(30, 30, 800, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.setEnabled(false);
        rooms.add(headingTable);

        //-------Table - Data
        JTable dataTable = new JTable();
        dataTable.setBounds(30, 60, 800, 820);
        dataTable.setFont(new Font("verdana", 0, 14));
        dataTable.setEnabled(false);
        dataTable.setRowHeight(23);
        dataTable.setForeground(Color.black);
        rooms.add(dataTable);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from room");
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e){
            e.printStackTrace();
        }

        //------Button - Back
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
        rooms.setAlwaysOnTop(true);
        rooms.setUndecorated(true);
        rooms.setVisible(true);
    }

    private void depart() {
        //-------Frame
        JFrame dep;
        dep = new JFrame();

        //-------Table - Heading
        String col[] = {"Department", "Budget"};
        String row[][] = {{"Department", "Budget"}};
        JTable headingTable = new JTable(row, col);
        headingTable.setBounds(30, 30, 600, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.setEnabled(false);
        dep.add(headingTable);

        //-------Table - Data
        JTable dataTable = new JTable();
        dataTable.setBounds(30, 60, 600, 700);
        dataTable.setFont(new Font("verdana", 0, 14));
        dataTable.setEnabled(false);
        dataTable.setRowHeight(23);
        dataTable.setForeground(Color.black);
        dep.add(dataTable);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from department");
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e){
            e.printStackTrace();
        }

        //------Button - Back
        JButton back = new JButton("B A C K");
        back.setBounds(20, 840, 620, 40);
        back.setFont(new Font("Arial", 1, 23));
        back.setBackground(new Color(255, 250, 74)); 
        back.addActionListener(b -> {dep.setVisible(false);   super.setVisible(true);});
        dep.add(back);

        //-------Frame
        dep.getContentPane().setBackground(Color.white);
        dep.setBounds(600, 80, 660, 900);    
        dep.setLayout(null);
        dep.setAlwaysOnTop(true);
        dep.setUndecorated(true);
        dep.setVisible(true);
    }
    
    private void custInfo() {
        //-------Frame
        JFrame custInfo;
        custInfo = new JFrame();

        //-------Table - Heading
        String col[] = {"Id", "Name", "Gender", "Country", "Id Proof", "Id Number", "Room Number", "Time", "Deposit"};
        String row[][] = {{"Id", "Name", "Gender", "Country", "Id Proof", "Id Number", "Room Number", "Time", "Deposit"}};
        JTable headingTable = new JTable(row, col);
        headingTable.setBounds(30, 30, 1540, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.getColumnModel().getColumn(7).setPreferredWidth(170);
        headingTable.setEnabled(false);
        custInfo.add(headingTable);

        //-------Table - Data
        JTable dataTable = new JTable();
        dataTable.setBounds(30, 60, 1540, 700);
        dataTable.setFont(new Font("verdana", 0, 14));
        dataTable.setEnabled(false);
        dataTable.setRowHeight(23);
        dataTable.setForeground(Color.black);
        custInfo.add(dataTable);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from customer");
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
            dataTable.getColumnModel().getColumn(7).setPreferredWidth(170);
            
        } catch (Exception e){
            e.printStackTrace();
        }

        //------Button - Back
        JButton back = new JButton("B A C K");
        back.setBounds(600, 840, 300, 40);
        back.setFont(new Font("Arial", 1, 23));
        back.setBackground(new Color(255, 250, 74)); 
        back.addActionListener(b -> {custInfo.setVisible(false);   super.setVisible(true);});
        custInfo.add(back);

        //-------Frame
        custInfo.getContentPane().setBackground(Color.white);
        custInfo.setBounds(150, 80, 1600, 900);    
        custInfo.setLayout(null);
        custInfo.setAlwaysOnTop(true);
        custInfo.setUndecorated(true);
        custInfo.setVisible(true);
    }

    private void empInfo(String param) {

        //-------Frame
        JFrame mngInfo;
        mngInfo = new JFrame();

        //-------Table - Heading
        String col[] = {"Id", "Name", "Age", "Gender", "Job", "Salary", "Phone", "Email", "Aadhar"};
        String row[][] = {{"Id", "Name", "Age", "Gender", "Job", "Salary", "Phone", "Email", "Aadhar"}};
        JTable headingTable = new JTable(row, col);
        headingTable.setBounds(30, 30, 1540, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.setEnabled(false);
        mngInfo.add(headingTable);

        //-------Table - Data
        JTable dataTable = new JTable();
        dataTable.setBounds(30, 60, 1540, 700);
        dataTable.setFont(new Font("verdana", 0, 14));
        dataTable.setEnabled(false);
        dataTable.setRowHeight(23);
        dataTable.setForeground(Color.black);
        mngInfo.add(dataTable);

        try{
            Conn c1 = new Conn();
            ResultSet rs = null;

            if(param.equals("Manager"))         
                rs = c1.s.executeQuery("select * from employee where job = '"+param+"'");
            else if(param.equals("All"))       
                rs = c1.s.executeQuery("select * from employee");

            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e){
            e.printStackTrace();
        }

        //------Button - Back
        JButton back = new JButton("B A C K");
        back.setBounds(600, 840, 300, 40);
        back.setFont(new Font("Arial", 1, 23));
        back.setBackground(new Color(255, 250, 74)); 
        back.addActionListener(b -> {mngInfo.setVisible(false);   super.setVisible(true);});
        mngInfo.add(back);

        //-------Frame
        mngInfo.getContentPane().setBackground(Color.white);
        mngInfo.setBounds(150, 80, 1600, 900);    
        mngInfo.setLayout(null);
        mngInfo.setAlwaysOnTop(true);
        mngInfo.setUndecorated(true);
        mngInfo.setVisible(true);
    }
    
    private void checkout() {

        //------JFrame Declare
        JFrame out = new JFrame();

        //------Label - Checkout
        JLabel headingL = new JLabel("Checkout");
        headingL.setBounds(400, 20, 150, 50);
        headingL.setForeground(Color.BLUE);
        headingL.setFont(new Font("Tahoma", 1, 22));
        out.add(headingL);

        //------Labels
        JLabel lbl[] = new JLabel[4];
        
        String strLbl[] = {"Customer Id", "Room Number", "Checkin Time", "Checkout Time"};

        int yLbl = 100;
        for (int i = 0; i < 4; i++) {
            lbl[i] = new JLabel(strLbl[i]);
            lbl[i].setBounds(30, yLbl, 150, 50);
            lbl[i].setForeground(Color.black);
            lbl[i].setFont(new Font("Arial", 0, 18));
            out.add(lbl[i]);
            yLbl += 70;
        }

        //------TextFields
        JTextField tf[] = new JTextField[3];

        int yTf = 180;
        for (int i = 0; i < 3; i++) {
            tf[i] = new JTextField();
            tf[i].setBounds(250, yTf, 250, 25);
            tf[i].setFont(new Font("Arial", 0, 18)); 
            tf[i].setForeground(Color.black);
            tf[i].setBackground(Color.white);
            tf[i].setEditable(false);
            out.add(tf[i]);
            yTf += 70;
        }

        //------ComboBox
        JComboBox <String> idCombo = new JComboBox<>();
        idCombo.setBounds(250, 110, 250, 25);
        idCombo.setForeground(Color.black);
        idCombo.setBackground(Color.white);
        idCombo.setFont(new Font("Arial", 0, 18));
        idCombo.addActionListener(i -> {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from customer where id = '"+idCombo.getSelectedItem()+"'");
                
                while(rs.next()){
                    tf[0].setText(rs.getString("roomNumber"));
                    tf[1].setText(rs.getString("time"));
                }

                LocalDate dt = LocalDate.now();
                LocalTime lt = LocalTime.now();
                DateTimeFormatter fdate = DateTimeFormatter.ofPattern("E, dd-MMM-uuuu");
                DateTimeFormatter ftime = DateTimeFormatter.ofPattern("HH:mm:ss");

                tf[2].setText(dt.format(fdate)+" / "+lt.format(ftime));

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        out.add(idCombo);

        //------Try Catch Details Fetching for ComboBox
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            while(rs.next()){
                idCombo.addItem(rs.getString("id"));
            }

            idCombo.setSelectedIndex(-1);
            for (int i = 0; i < tf.length; i++) {
                tf[i].setText("");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //-----Buttons
        JButton btn[] = new JButton[2];

        String btnStr[] = {"CHECKOUT", "BACK"};
        int xBtn = 30;

        for(int i=0; i<2; i++){
            btn[i] = new JButton(btnStr[i]);
            btn[i].setBounds(xBtn, 430, 150, 40);
            btn[i].setFont(new Font("Arial", 0, 20));
            btn[i].setForeground(Color.BLACK);
            out.add(btn[i]);
            xBtn += 320;
        }
        btn[0].setBackground(new Color(225, 168, 255));
        btn[1].setBackground(new Color(255, 251, 94));

        btn[0].addActionListener(u -> {

            try {
                Conn c = new Conn();

                if(idCombo.getSelectedIndex() <= -1){
                    JOptionPane.showMessageDialog(out, "Select The Customer!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int cnf = JOptionPane.showConfirmDialog(out, "Are You Sure!!", "Confirmation", JOptionPane.YES_NO_OPTION);

                if(cnf == 0){
                    JOptionPane.showMessageDialog(out, "Checkout Done Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                    c.s.executeUpdate("delete from customer where id = '"+idCombo.getSelectedItem()+"'"); // delete the customer
                    c.s.executeUpdate("update room set avail = 'Vacant' where room = '"+tf[0].getText()+"'"); // update the availability of rooms to vacant

                    out.setVisible(false);
                    super.setVisible(true);
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        });

        btn[1].addActionListener(b -> {
            out.setVisible(false);    
            super.setVisible(true);
        });

        //------JFrame 
        out.setBounds(500, 250, 900, 500);
        out.setUndecorated(true);
        out.getContentPane().setBackground(Color.WHITE);
        out.setLayout(null);
        out.setAlwaysOnTop(true);
        out.setVisible(true);
    }

    private void updateStatus() {
        
        //-----JFrame Declare 
        JFrame updSt = new JFrame();

        //-----JLable - Update Status
        JLabel headingL = new JLabel("Update Status");
        headingL.setBounds(420, 20, 200, 30);
        headingL.setForeground(Color.BLUE);
        headingL.setFont(new Font("Tahoma", 1, 25));
        updSt.add(headingL);

        //-----JLabels
        String str[] = {"Customer Id", "Room Number", "Name", "Checkin Time", "Amount Paid", "Pending Amount"};
        JLabel lbl[] = new JLabel[6];
        int ylbl = 100;
        for(int i=0; i<6; i++){
            lbl[i] = new JLabel(str[i]);
            lbl[i].setBounds(30, ylbl, 150, 20);
            lbl[i].setFont(new Font("Arial", 0, 18));
            lbl[i].setForeground(Color.BLACK);
            updSt.add(lbl[i]);
            ylbl += 80;
        }

        //-----Textfields from 2nd Labels
        JTextField tf[] = new JTextField[5];
        int ytf = 180;
        for(int i=0; i<5; i++){
            tf[i] = new JTextField();
            tf[i].setBounds(250, ytf, 250, 25);
            tf[i].setFont(new Font("Arial", 0, 18));
            tf[i].setBackground(Color.WHITE);
            tf[i].setForeground(Color.BLACK);
            updSt.add(tf[i]);
            ytf += 80;
        }
        tf[4].setEditable(false);
        tf[4].setBackground(new Color(207, 255, 211));

        //-----Choice for 1st Label
        JComboBox <String> custChoice = new JComboBox<>();
        custChoice.setBounds(250, 100, 250, 25);
        custChoice.setFont(new Font("Arial", 0, 16));
        custChoice.setBackground(Color.WHITE);
        custChoice.setForeground(Color.BLACK);
        custChoice.addActionListener(c -> {
            try {
                Conn c1 = new Conn();

                ResultSet rs1 = c1.s.executeQuery("select * from customer where id = '"+custChoice.getSelectedItem()+"'");

                int deposit = 0, pending = 0;

                if(rs1.next()){
                    String custStr[] = {"roomNumber", "name", "time", "deposit"};

                    for(int i=0; i<4; i++){
                        tf[i].setText(rs1.getString(custStr[i]));
                    }

                    deposit = Integer.parseInt(rs1.getString("deposit"));

                    rs1 = c1.s.executeQuery("select * from room where room = '"+rs1.getString("roomNumber")+"'");

                    if(rs1.next()){
                        pending = Integer.parseInt(rs1.getString("price")) - deposit;

                        if (pending != 0)
                            tf[4].setText(Integer.toString(pending));
                        else if (pending == 0)
                            tf[4].setText("Full Payment Done");
                    }
                }
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        updSt.add(custChoice);

        try {
            Conn c = new Conn();
            ResultSet rsChoice = c.s.executeQuery("select * from customer");

            while(rsChoice.next()){
                custChoice.addItem(rsChoice.getString("id"));
            }

            custChoice.setSelectedIndex(-1);
            for (int i = 0; i < tf.length; i++) {
                tf[i].setText("");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        //-----Buttons
        JButton btn[] = new JButton[2];

        String btnStr[] = {"UPDATE", "BACK"};
        int xBtn = 30;

        for(int i=0; i<2; i++){
            btn[i] = new JButton(btnStr[i]);
            btn[i].setBounds(xBtn, 580, 150, 40);
            btn[i].setFont(new Font("Arial", 0, 20));
            btn[i].setForeground(Color.BLACK);
            updSt.add(btn[i]);
            xBtn += 320;
        }
        btn[0].setBackground(new Color(225, 168, 255));
        btn[1].setBackground(new Color(255, 251, 94));

        btn[0].addActionListener(u -> {
            String roomNumber = tf[0].getText();
            String name = tf[1].getText();
            String time = tf[2].getText();
            String deposit = tf[3].getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set roomNumber = '"+roomNumber+"', name = '"+name+"', time = '"+time+"', deposit = '"+deposit+"' where id = '"+custChoice.getSelectedItem()+"'");

                int cnf = JOptionPane.showConfirmDialog(updSt, "Are You Sure!!", "Confirmation", JOptionPane.YES_NO_OPTION);

                if(cnf == 0){
                    JOptionPane.showMessageDialog(updSt, "Details Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updSt.setVisible(false);
                    super.setVisible(true);
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(updSt, "Please Enter Valid Input!!", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        });

        btn[1].addActionListener(b -> {
            updSt.setVisible(false);    
            super.setVisible(true);
        });
        
        //-----Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/nine.jpg"));
        Image i2 = i1.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(570, 200, 350, 250);
        updSt.add(img);

        //-----JFrame 

        updSt.setBounds(450, 200, 1000, 650);
        updSt.getContentPane().setBackground(Color.WHITE);
        updSt.setLayout(null);
        updSt.setAlwaysOnTop(true);
        updSt.setUndecorated(true);
        updSt.setVisible(true);
    }

    private void updateRoomStatus() {
        //-----JFrame Declare 
        JFrame updSt = new JFrame();

        //-----JLable - Update Status
        JLabel headingL = new JLabel("Update Room Status");
        headingL.setBounds(370, 20, 300, 30);
        headingL.setForeground(Color.BLUE);
        headingL.setFont(new Font("Tahoma", 1, 25));
        updSt.add(headingL);

        //-----JLabels
        String str[] = {"Customer Id", "Room Number", "Availability", "Cleaning Status"};
        JLabel lbl[] = new JLabel[4];
        int ylbl = 100;
        for(int i=0; i<4; i++){
            lbl[i] = new JLabel(str[i]);
            lbl[i].setBounds(30, ylbl, 150, 20);
            lbl[i].setFont(new Font("Arial", 0, 18));
            lbl[i].setForeground(Color.BLACK);
            updSt.add(lbl[i]);
            ylbl += 80;
        }

        //-----TextField - Room Number
        JTextField roomNo = new JTextField();
        roomNo.setBounds(250, 180, 250, 25);
        roomNo.setFont(new Font("Arial", 0, 18));
        roomNo.setBackground(Color.WHITE);
        roomNo.setForeground(Color.BLACK);
        updSt.add(roomNo);

        //-----ComboBox from 3rd Labels
        JComboBox <String> tf[] = new JComboBox[2];
        int ytf = 260;
        for(int i=0; i<2; i++){
            tf[i] = new JComboBox<>();
            tf[i].setBounds(250, ytf, 250, 25);
            tf[i].setFont(new Font("Arial", 0, 18));
            tf[i].setBackground(Color.WHITE);
            tf[i].setForeground(Color.BLACK);
            updSt.add(tf[i]);
            ytf += 80;
        }
        tf[0].addItem("Occupied");
        tf[0].addItem("Vacant");
        tf[1].addItem("Cleaned");
        tf[1].addItem("Not Cleaned");

        //-----Choice for 1st Label
        JComboBox <String> custChoice = new JComboBox<>();
        custChoice.setBounds(250, 100, 250, 25);
        custChoice.setFont(new Font("Arial", 0, 16));
        custChoice.setBackground(Color.WHITE);
        custChoice.setForeground(Color.BLACK);
        custChoice.addActionListener(c -> {
            try {
                Conn c1 = new Conn();

                ResultSet rs1 = c1.s.executeQuery("select * from customer where id = '"+custChoice.getSelectedItem()+"'");
                
                if(rs1.next()){
                    roomNo.setText(rs1.getString("roomNumber"));
                }

                ResultSet rs2 = c1.s.executeQuery("select * from room where room = '"+roomNo.getText()+"'");

                if(rs2.next()){
                    tf[0].setSelectedItem(rs2.getString("avail"));
                    tf[1].setSelectedItem(rs2.getString("clean"));
                }

            }
            catch (Exception e) {
                System.out.println(e);
            }
        });
        updSt.add(custChoice);

        try {
            Conn c = new Conn();
            ResultSet rsChoice = c.s.executeQuery("select * from customer");

            while(rsChoice.next()){
                custChoice.addItem(rsChoice.getString("id"));
            }

            custChoice.setSelectedIndex(-1);
            roomNo.setText("");
            tf[0].setSelectedIndex(-1);
            tf[1].setSelectedIndex(-1);

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        //-----Buttons
        JButton btn[] = new JButton[2];

        String btnStr[] = {"UPDATE", "BACK"};
        int xBtn = 30;

        for(int i=0; i<2; i++){
            btn[i] = new JButton(btnStr[i]);
            btn[i].setBounds(xBtn, 410, 150, 40);
            btn[i].setFont(new Font("Arial", 0, 20));
            btn[i].setForeground(Color.BLACK);
            updSt.add(btn[i]);
            xBtn += 320;
        }
        btn[0].setBackground(new Color(225, 168, 255));
        btn[1].setBackground(new Color(255, 251, 94));

        btn[0].addActionListener(u -> {
            String available = (String) tf[0].getSelectedItem();
            String cleanSts = (String) tf[1].getSelectedItem();
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update room set clean = '"+cleanSts+"', avail = '"+available+"' where room = '"+roomNo.getText()+"'");

                int cnf = JOptionPane.showConfirmDialog(updSt, "Are You Sure!!", "Confirmation", JOptionPane.YES_NO_OPTION);

                if(cnf == 0){
                    JOptionPane.showMessageDialog(updSt, "Details Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    updSt.setVisible(false);
                    super.setVisible(true);
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        });

        btn[1].addActionListener(b -> {
            updSt.setVisible(false);    
            super.setVisible(true);
        });
        
        //-----Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(550, 100, 400, 300);
        updSt.add(img);

        //-----JFrame 

        updSt.setBounds(450, 250, 1000, 480);
        updSt.getContentPane().setBackground(Color.WHITE);
        updSt.setLayout(null);
        updSt.setAlwaysOnTop(true);
        updSt.setUndecorated(true);
        updSt.setVisible(true);
    }

    JScrollPane spPickup;
    private void pickupService() {
        //-------Frame
        JFrame searchRoom;
        searchRoom = new JFrame();

        //-------Label - Pickup Service
        JLabel searchRoomHeading = new JLabel("Pickup Service");
        searchRoomHeading.setBounds(400, 20, 500, 40);
        searchRoomHeading.setForeground(Color.BLACK);
        searchRoomHeading.setFont(new Font("Arial", 1, 30));
        searchRoom.add(searchRoomHeading);

        //-------Label - Choose Car Type
        JLabel btypeL = new JLabel("Choose The Car");
        btypeL.setBounds(30, 100, 200, 20);
        btypeL.setForeground(Color.black);
        btypeL.setFont(new Font("Tahoma", 0, 18));
        searchRoom.add(btypeL);       

        //-------Table - Heading
        String col[] = {"Id", "Name", "Age", "Gender", "Car", "Avail"};
        String row[][] = {{"Id", "Name", "Age", "Gender", "Car", "Avail"}};
        JTable headingTable = new JTable(row, col);
        headingTable.setBounds(30, 180, 930, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.setForeground(Color.black);
        headingTable.setBackground(Color.white);
        headingTable.setEnabled(false);
        searchRoom.add(headingTable);

        //-------Table - Data
        JTable dataTable = new JTable();
        dataTable.setFont(new Font("verdana", 0, 14));
        dataTable.setEnabled(false);
        dataTable.setRowHeight(23);
        dataTable.setForeground(Color.black);

        spPickup = new JScrollPane(dataTable);
        spPickup.setBounds(30, 210, 930, 600);
        spPickup.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchRoom.add(spPickup);

        //-------CheckBox - Initialzation
        JCheckBox avlCheck = new JCheckBox("Only Available Cars");

        //-------ComboBox - Bed Type
        JComboBox <String> btypeCombo = new JComboBox<>();
        btypeCombo.setBounds(200, 100, 180, 25);
        btypeCombo.setFont(new Font("Calibri", 0, 18));
        btypeCombo.setBackground(Color.white);
        btypeCombo.setForeground(Color.black);
        btypeCombo.addActionListener(a -> {
            try{
                avlCheck.setSelected(false);
                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("select * from driver where car = '"+btypeCombo.getSelectedItem()+"'");
                dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch (Exception e){
                e.printStackTrace();
            }

        });

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driver");

            while(rs.next()){
                btypeCombo.addItem(rs.getString("car"));
            }

            btypeCombo.setSelectedIndex(-1);
        }
        catch (Exception e) {
            e.printStackTrace();    
        }

        searchRoom.add(btypeCombo);

        //-------Button - Clear ComboBox Selection
        JButton comboClr = new JButton("CLEAR");
        comboClr.setBounds(400, 100, 80, 25);
        comboClr.setFont(new Font("tahoma", 0, 15));
        comboClr.setBackground(new Color(252, 169, 169));
        comboClr.setForeground(Color.black);
        comboClr.addActionListener(c -> {
            btypeCombo.setSelectedIndex(-1);
            try{
                avlCheck.setSelected(false);
                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("select * from driver");
                dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        searchRoom.add(comboClr);

        //-------CheckBox - Available Rooms
        avlCheck.setBounds(770, 100, 200, 20);
        avlCheck.setForeground(Color.black);
        avlCheck.setBackground(Color.white);
        avlCheck.setFont(new Font("Tahoma", 0, 18));
        avlCheck.addActionListener(a -> {
            if(btypeCombo.getSelectedIndex() == -1)
                if(avlCheck.isSelected())
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from driver where avail = 'Available'");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                else
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from driver");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
            else
                if(avlCheck.isSelected())
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from driver where avail = 'Available' and car = '"+btypeCombo.getSelectedItem()+"'");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                else
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from driver where car = '"+btypeCombo.getSelectedItem()+"'");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
        });
        searchRoom.add(avlCheck);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from driver");
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e){
            e.printStackTrace();
        }

        //------Button - Back
        JButton back = new JButton("B A C K");
        back.setBounds(350, 830, 300, 40);
        back.setFont(new Font("Arial", 1, 23));
        back.setBackground(new Color(255, 250, 74)); 
        back.addActionListener(b -> {searchRoom.setVisible(false);   super.setVisible(true);});
        searchRoom.add(back);

        //-------Frame
        searchRoom.getContentPane().setBackground(Color.white);
        searchRoom.setBounds(450, 80, 1000, 900);    
        searchRoom.setLayout(null);
        searchRoom.setUndecorated(true);
        searchRoom.setAlwaysOnTop(true);
        searchRoom.setVisible(true);
    }

    JScrollPane sp;
    private void searchRooms() {

        //-------Frame
        JFrame searchRoom;
        searchRoom = new JFrame();

        //-------Label - Search Rooms
        JLabel searchRoomHeading = new JLabel("Search Rooms");
        searchRoomHeading.setBounds(400, 20, 500, 40);
        searchRoomHeading.setForeground(Color.BLACK);
        searchRoomHeading.setFont(new Font("Arial", 1, 30));
        searchRoom.add(searchRoomHeading);

        //-------Label - Choose Bed Type
        JLabel btypeL = new JLabel("Choose Bed Type");
        btypeL.setBounds(30, 100, 200, 20);
        btypeL.setForeground(Color.black);
        btypeL.setFont(new Font("Tahoma", 0, 18));
        searchRoom.add(btypeL);       

        //-------Table - Heading
        String col[] = {"Room No.", "Price", "Clean Status", "Availability", "Room Type"};
        String row[][] = {{"Room No.", "Price", "Clean Status", "Availability", "Room Type"}};
        JTable headingTable = new JTable(row, col);
        headingTable.setBounds(30, 180, 930, 30);
        headingTable.setFont(new Font("Arial", 1, 14));
        headingTable.setForeground(Color.black);
        headingTable.setBackground(Color.white);
        headingTable.setEnabled(false);
        searchRoom.add(headingTable);

        //-------Table - Data
        JTable dataTable = new JTable();
        dataTable.setFont(new Font("verdana", 0, 14));
        dataTable.setEnabled(false);
        dataTable.setRowHeight(23);
        dataTable.setForeground(Color.black);

        sp = new JScrollPane(dataTable);
        sp.setBounds(30, 210, 930, 600);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchRoom.add(sp);

        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from room");
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e){
            e.printStackTrace();
        }

        //-------CheckBox - Initialzation
        JCheckBox avlCheck = new JCheckBox("Only Available Rooms");

        //-------ComboBox - Bed Type
        String items[] = {"Single (AC)", "Single (Non AC)", "Double (AC)", "Queen Maharani (AC)", "King Raja (AC)", "Executive Special", "Business Paradise"};
        JComboBox btypeCombo = new JComboBox<>(items);
        btypeCombo.setBounds(200, 100, 180, 25);
        btypeCombo.setFont(new Font("Calibri", 0, 18));
        btypeCombo.setSelectedIndex(-1);
        btypeCombo.setBackground(Color.white);
        btypeCombo.setForeground(Color.black);
        btypeCombo.addActionListener(a -> {
            try{
                avlCheck.setSelected(false);
                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("select * from room where bedType = '"+btypeCombo.getSelectedItem()+"'");
                dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch (Exception e){
                e.printStackTrace();
            }

        });
        searchRoom.add(btypeCombo);

        //-------Button - Clear ComboBox Selection
        JButton comboClr = new JButton("CLEAR");
        comboClr.setBounds(400, 100, 80, 25);
        comboClr.setFont(new Font("tahoma", 0, 15));
        comboClr.setBackground(new Color(252, 169, 169));
        comboClr.setForeground(Color.black);
        comboClr.addActionListener(c -> {
            btypeCombo.setSelectedIndex(-1);
            try{
                avlCheck.setSelected(false);
                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("select * from room");
                dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        searchRoom.add(comboClr);

        //-------CheckBox - Available Rooms
        avlCheck.setBounds(770, 100, 200, 20);
        avlCheck.setForeground(Color.black);
        avlCheck.setBackground(Color.white);
        avlCheck.setFont(new Font("Tahoma", 0, 18));
        avlCheck.addActionListener(a -> {
            if(btypeCombo.getSelectedIndex() == -1)
                if(avlCheck.isSelected())
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from room where avail = 'Vacant'");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                else
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from room");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
            else
                if(avlCheck.isSelected())
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from room where avail = 'Vacant' and bedType = '"+btypeCombo.getSelectedItem()+"'");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                else
                    try{
                        Conn c1 = new Conn();
                        ResultSet rs = c1.s.executeQuery("select * from room where bedType = '"+btypeCombo.getSelectedItem()+"'");
                        dataTable.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    } catch (Exception e){
                        e.printStackTrace();
                    }
        });
        searchRoom.add(avlCheck);

        //------Button - Back
        JButton back = new JButton("B A C K");
        back.setBounds(350, 830, 300, 40);
        back.setFont(new Font("Arial", 1, 23));
        back.setBackground(new Color(255, 250, 74)); 
        back.addActionListener(b -> {searchRoom.setVisible(false);   super.setVisible(true);});
        searchRoom.add(back);

        //-------Frame
        searchRoom.getContentPane().setBackground(Color.white);
        searchRoom.setBounds(450, 80, 1000, 900);    
        searchRoom.setLayout(null);
        searchRoom.setUndecorated(true);
        searchRoom.setAlwaysOnTop(true);
        searchRoom.setVisible(true);
    }

    private void logout() {
        int res = JOptionPane.showConfirmDialog(this, "You really want to Quit..?", "Warning", 0);
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