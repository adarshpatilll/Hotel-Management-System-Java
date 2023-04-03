
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("all")
public class AddDriver extends JFrame implements ActionListener {
    JTextField idArea, nameArea, ageArea;
    JRadioButton genRbMale, genRbFemale;
    JComboBox carArea, availA;
    JButton addDriver, cancel;

    AddDriver(){
        //------Label - ID
        JLabel idLbl = new JLabel("UNIQUE ID");
        idLbl.setBounds(60, 30, 120, 30);
        idLbl.setFont(new Font("arial", 0, 18));
        idLbl.setForeground(Color.black);
        add(idLbl);

            //------Area - ID
            Random rand = new Random();
            idArea = new JTextField(Integer.toString(rand.nextInt(5000, 9999)));
            idArea.setBounds(200, 30, 200, 30);
            idArea.setFont(new Font("arial", 3, 18));
            idArea.setBackground(Color.pink);
            idArea.setEditable(false);
            add(idArea);

        //------Label - Name
        JLabel nameLbl = new JLabel("NAME");
        nameLbl.setBounds(60, 80, 120, 30);
        nameLbl.setFont(new Font("arial", 0, 18));
        nameLbl.setForeground(Color.black);
        add(nameLbl);

            //------Area - Name
            nameArea = new JTextField();
            nameArea.setBounds(200, 80, 200, 30);
            nameArea.setFont(new Font("arial", 0, 18));
            add(nameArea);

        //------Label - Age
        JLabel ageLbl = new JLabel("AGE");
        ageLbl.setBounds(60, 130, 120, 30);
        ageLbl.setFont(new Font("arial", 0, 18));
        ageLbl.setForeground(Color.black);
        add(ageLbl);

            //------Area - Age
            ageArea = new JTextField();
            ageArea.setBounds(200, 130, 200, 30);
            ageArea.setFont(new Font("arial", 0, 18));
            add(ageArea);

        //------Label - Gender
        JLabel genderLbl = new JLabel("GENDER");
        genderLbl.setBounds(60, 180, 120, 30);
        genderLbl.setFont(new Font("arial", 0, 18));
        genderLbl.setForeground(Color.black);
        add(genderLbl);

            //-------Area - Gender - MALE
            genRbMale = new JRadioButton("MALE");
            genRbMale.setBounds(200, 180, 80, 30);
            genRbMale.setFont(new Font("arial", 0, 18));
            genRbMale.setBackground(Color.white);
            genRbMale.setForeground(Color.black);
            add(genRbMale);
            
            //-------Area - Gender - FEMALE
            genRbFemale = new JRadioButton("FEMALE");
            genRbFemale.setBounds(300, 180, 100, 30);
            genRbFemale.setFont(new Font("arial", 0, 18));
            genRbFemale.setBackground(Color.white);
            genRbFemale.setForeground(Color.black);
            add(genRbFemale);

            ButtonGroup bg = new ButtonGroup();
            bg.add(genRbMale);  bg.add(genRbFemale);

        //------Label - Car
        JLabel jobLbl = new JLabel("CARS");
        jobLbl.setBounds(60, 230, 120, 30);
        jobLbl.setFont(new Font("arial", 0, 18));
        jobLbl.setForeground(Color.black);
        add(jobLbl);

            //------Area - Car
            String str[] = {"Honda City", "Mercedes-Benz GLA", "BMW X7", "Porsche 911", "Lexus NX", "Audi Q8", "Volvo XC90", "Jaguar F-Pace", "Tata Harrier", "MG Gloster", "Rolls-Royce Phantom 8"};
            Arrays.sort(str);

            carArea = new JComboBox<>(str);
            carArea.setBounds(200, 230, 200, 30);
            carArea.setBackground(Color.white);
            carArea.setForeground(Color.black);
            carArea.setBackground(Color.WHITE);
            carArea.setFont(new Font("arial", 0, 17));
            add(carArea);

        //------Label - AVAILABILITY
        JLabel availL = new JLabel("AVAILABILITY");
        availL.setBounds(60, 280, 200, 30);
        availL.setFont(new Font("arial", 0, 18));
        availL.setForeground(Color.black);
        add(availL);
        
            //------Area - AVAILABILITY
            String avlStr[] = {"Available", "Not Available"};
            availA = new JComboBox<>(avlStr);
            availA.setBounds(200, 280, 200, 30);
            availA.setFont(new Font("arial", 0, 17));
            add(availA);
        
        //------Button - addDriver
        addDriver = new JButton("ADD DRIVER");
        addDriver.setBounds(60,350,150,30);
        addDriver.setFont(new Font("arial", 0, 18));
        addDriver.setBackground(new Color(126, 255, 141));
        addDriver.setForeground(Color.black);
        addDriver.addActionListener(this);
        add(addDriver);

        //------Button - Cancel
        cancel = new JButton("CANCEL");
        cancel.setBounds(250, 350, 150, 30);
        cancel.setFont(new Font("arial", 0, 18));
        cancel.setBackground(new Color(255, 126, 126));
        cancel.setForeground(Color.black);
        cancel.addActionListener(this);
        add(cancel);

        //------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/elevens.png"));
        Image i2 = i1.getImage().getScaledInstance(350, 470, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel finalImage = new JLabel(i3);
        finalImage.setBounds(450, 40, 370, 500); 
        add(finalImage);

        //------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        setTitle("Add Driver");
        setIconImage(logo);
        setBounds(530, 300, 870, 410);
        getContentPane().setBackground(Color.white);
        setUndecorated(true);
        setLayout(null);
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addDriver){
            String id = idArea.getText();
            String name = nameArea.getText();
            String age = ageArea.getText();
            String car = (String) carArea.getSelectedItem();
            String avail = (String) availA.getSelectedItem();
            String gender = null;

            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name Should Not Be Empty!", "Warning", 2);
                return;
            }
            if(age.equals("")){
                JOptionPane.showMessageDialog(null, "Age Should Not Be Empty!", "Warning", 2);
                return;
            }
            if(car.equals("")){
                JOptionPane.showMessageDialog(null, "Cars Should Not Be Empty!", "Warning", 2);
                return;
            }

            if(genRbMale.isSelected())
                gender = "Male";
            else if (genRbFemale.isSelected())
                gender = "Female";
            else if (gender == null){
                JOptionPane.showMessageDialog(null, "Please Select Gender!", "Warning", 2);
                return;
            }

            try {
                Conn c = new Conn();

                String query = "insert into driver values ('"+id+"', '"+name+"', '"+age+"', '"+gender+"', '"+car+"', '"+avail+"')";

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null,  "Driver Added Successfully","Success", 1);

                setVisible(false);
                
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Enter Valid Input (Age) ", "Warning",  2);
            }
        }
        else
            setVisible(false);
    }

    public static void main(String[] args) {
        new AddDriver();
    }
}
