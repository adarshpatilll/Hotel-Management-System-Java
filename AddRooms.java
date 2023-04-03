
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("all")
public class AddRooms extends JFrame implements ActionListener {
    JButton addRoom, cancel;
    JTextField roomA,priceA;
    JComboBox  availA, cleanA,  bedTypeA;

    AddRooms(){

        //------Label - Room No
        JLabel roomL = new JLabel("ROOM NUMBER");
        roomL.setBounds(60, 50, 200, 30);
        roomL.setFont(new Font("arial", 0, 18));
        roomL.setForeground(Color.black);
        add(roomL);

            //------Area - Room No
            roomA = new JTextField();
            roomA.setBounds(250, 50, 200, 30);
            roomA.setFont(new Font("arial", 0, 18));
            add(roomA);
        
        
        //------Label - AVAILABILITY
        JLabel availL = new JLabel("AVAILABILITY");
        availL.setBounds(60, 100, 200, 30);
        availL.setFont(new Font("arial", 0, 18));
        availL.setForeground(Color.black);
        add(availL);
        
            //------Area - AVAILABILITY
            String avlStr[] = {"Occupied", "Vacant"};
            availA = new JComboBox<>(avlStr);
            availA.setBounds(250, 100, 200, 30);
            availA.setBackground(Color.white);
            availA.setFont(new Font("arial", 0, 17));
            add(availA);

        
        //------Label - CLEANING
        JLabel cleanL = new JLabel("CLEANING STATUS");
        cleanL.setBounds(60, 150, 200, 30);
        cleanL.setFont(new Font("arial", 0, 18));
        cleanL.setForeground(Color.black);
        add(cleanL);
        
            //------Area - CLEANING
            String clnStr[] = {"Cleaned", "Not Cleaned"};
            cleanA = new JComboBox<>(clnStr);
            cleanA.setBounds(250, 150, 200, 30);
            cleanA.setBackground(Color.white);
            cleanA.setFont(new Font("arial", 0, 17));
            add(cleanA);
        
        
        //------Label - PRICE
        JLabel priceL = new JLabel("PRICE");
        priceL.setBounds(60, 200, 200, 30);
        priceL.setFont(new Font("arial", 0, 18));
        priceL.setForeground(Color.black);
        add(priceL);
        
            //------Area - PRICE
            priceA = new JTextField();
            priceA.setBounds(250, 200, 200, 30);
            priceA.setFont(new Font("arial", 0, 18));
            add(priceA);
        
        
        //------Label - BED
        JLabel bedTypeL = new JLabel("BED TYPE");
        bedTypeL.setBounds(60, 250, 200, 30);
        bedTypeL.setFont(new Font("arial", 0, 18));
        bedTypeL.setForeground(Color.black);
        add(bedTypeL);
        
            //------Area - BED
            String bedStr[] = {"Single (AC)", "Single (Non AC)", "Double (AC)", "Queen Maharani (AC)", "King Raja (AC)", "Executive Special", "Business Paradise"};
            bedTypeA = new JComboBox<>(bedStr);
            bedTypeA.setBounds(250, 250, 200, 30);
            bedTypeA.setBackground(Color.white);
            bedTypeA.setFont(new Font("arial", 0, 17));
            add(bedTypeA);
        

        //------Button - Add Room
        addRoom = new JButton("ADD ROOM");
        addRoom.setBounds(60, 320, 150, 30);
        addRoom.setFont(new Font("arial", 0, 18));
        addRoom.setBackground(new Color(126, 255, 141));
        addRoom.setForeground(Color.black);
        addRoom.addActionListener(this);
        add(addRoom);
        
        //------Button - Cancel
        cancel = new JButton("CANCEL");
        cancel.setBounds(300, 320, 150, 30);
        cancel.setFont(new Font("arial", 0, 18));
        cancel.setBackground(new Color(255, 126, 126));
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);
        add(cancel);

        //------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/twelve.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel finalImage = new JLabel(i3);
        finalImage.setBounds(480, 14, 370, 370); 
        add(finalImage);

        //------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        setIconImage(logo);
        setTitle("Add Rooms");
        setBounds(500, 300, 900, 400);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cancel){
            setVisible(false);
        }
        else{
            String room = roomA.getText();
            String price = priceA.getText();
            String clean = (String) cleanA.getSelectedItem();
            String avail = (String) availA.getSelectedItem();
            String bedType = (String) bedTypeA.getSelectedItem();

            try {
                Conn c = new Conn();

                String query = "insert into room values ('"+room+"', '"+price+"', '"+clean+"', '"+avail+"', '"+bedType+"')";

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,  "Rooms Added Successfully","Success", 1);
                setVisible(false);
            } 
            catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Enter Valid Input (Room No., Price)", "Warning",  2);
            }
        }
    }

    public static void main(String... ap) {
        new AddRooms();
    }
}
