
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("all")
public class AddEmployee extends JFrame implements ActionListener {
    JTextField idArea, nameArea, ageArea, salArea, phoneArea, emailArea, aadharArea;
    JRadioButton genRbMale, genRbFemale;
    JComboBox jobArea;
    JButton submit,cancel;

    AddEmployee(){
        //------Label - ID
        JLabel idLbl = new JLabel("UNIQUE ID");
        idLbl.setBounds(60, 30, 120, 30);
        idLbl.setFont(new Font("arial", 0, 18));
        idLbl.setForeground(Color.black);
        add(idLbl);

            //------Area - ID
            Random rand = new Random();
            idArea = new JTextField(Integer.toString(rand.nextInt(1000, 4999)));
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

        //------Label - Job
        JLabel jobLbl = new JLabel("JOB");
        jobLbl.setBounds(60, 230, 120, 30);
        jobLbl.setFont(new Font("arial", 0, 18));
        jobLbl.setForeground(Color.black);
        add(jobLbl);

            //------Area - Job
            String str[] = {"Front Desk Clerks", "Porters", "Spa Manager", "Housekeeping", "Kitchen Staff", "Room Service", "Chefs", "Waiter/Waitress", "Manager", "Accountant", "Event Planner", "Casino Host", "Back Office Assistant"};
            Arrays.sort(str);

            jobArea = new JComboBox<>(str);
            jobArea.setBounds(200, 230, 200, 30);
            jobArea.setForeground(Color.black);
            jobArea.setBackground(Color.WHITE);
            jobArea.setFont(new Font("arial", 0, 17));
            add(jobArea);

        //------Label - Salary
        JLabel salLbl = new JLabel("SALARY");
        salLbl.setBounds(60, 280, 120, 30);
        salLbl.setFont(new Font("arial", 0, 18));
        salLbl.setForeground(Color.black);
        add(salLbl);

            //------Area - Salary
            salArea = new JTextField();
            salArea.setBounds(200, 280, 200, 30);
            salArea.setFont(new Font("arial", 0, 18));
            add(salArea);
        
        //------Label - Contact
        JLabel phoneLbl = new JLabel("CONTACT NO");
        phoneLbl.setBounds(60, 330, 120, 30);
        phoneLbl.setFont(new Font("arial", 0, 18));
        phoneLbl.setForeground(Color.black);
        add(phoneLbl);

            //------Area - Contact
            phoneArea = new JTextField();
            phoneArea.setBounds(200, 330, 200, 30);
            phoneArea.setFont(new Font("arial", 0, 18));
            add(phoneArea);
        
        //------Label - Email
        JLabel emailLbl = new JLabel("EMAIL ID");
        emailLbl.setBounds(60, 380, 120, 30);
        emailLbl.setFont(new Font("arial", 0, 18));
        emailLbl.setForeground(Color.black);
        add(emailLbl);

            //------Area - Email
            emailArea = new JTextField();
            emailArea.setBounds(200, 380, 200, 30);
            emailArea.setFont(new Font("arial", 0, 18));
            add(emailArea);
        
            //------Label - Aadhar
        JLabel aadharLbl = new JLabel("AADHAR");
        aadharLbl.setBounds(60, 430, 120, 30);
        aadharLbl.setFont(new Font("arial", 0, 18));
        aadharLbl.setForeground(Color.black);
        add(aadharLbl);

            //------Area - Aadhar
            aadharArea = new JTextField();
            aadharArea.setBounds(200, 430, 200, 30);
            aadharArea.setFont(new Font("arial", 0, 18));
            add(aadharArea);

        //------Button - Submit
        submit = new JButton("SUBMIT");
        submit.setBounds(60,490,150,30);
        submit.setFont(new Font("arial", 0, 18));
        submit.setBackground(new Color(126, 255, 141));
        submit.setForeground(Color.black);
        submit.addActionListener(this);
        add(submit);

        //------Button - Cancel
        cancel = new JButton("CANCEL");
        cancel.setBounds(250, 490, 150, 30);
        cancel.setFont(new Font("arial", 0, 18));
        cancel.setBackground(new Color(255, 126, 126));
        cancel.setForeground(Color.black);
        cancel.addActionListener(     a -> setVisible(false)    );
        add(cancel);

        //------Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(420, 90, 450, 370);
        add(image);

        //------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        setTitle("Add Employee");
        setIconImage(logo);
        setBounds(500, 250, 900, 600);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String id = idArea.getText();
        String name = nameArea.getText();
        String age = ageArea.getText();
        String sal = salArea.getText();
        String phone = phoneArea.getText();
        String email = emailArea.getText();
        String aadhar = aadharArea.getText();
        String job = (String) jobArea.getSelectedItem();
        String gender = null;

        if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Name Should Not Be Empty!", "Warning", 2);
            return;
        }
        if(age.equals("")){
            JOptionPane.showMessageDialog(null, "Age Should Not Be Empty!", "Warning", 2);
            return;
        }
        if(sal.equals("")){
            JOptionPane.showMessageDialog(null, "Salary Should Not Be Empty!", "Warning", 2);
            return;
        }
        if(phone.equals("")){
            JOptionPane.showMessageDialog(null, "Contact No. Should Not Be Empty!", "Warning", 2);
            return;
        }
        if(email.equals("")){
            JOptionPane.showMessageDialog(null, "Email Should Not Be Empty!", "Warning", 2);
            return;
        }
        if(aadhar.equals("")){
            JOptionPane.showMessageDialog(null, "Aadhaar Should Not Be Empty!", "Warning", 2);
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

            String query = "insert into employee values ('"+id+"','"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+sal+"', '"+phone+"', '"+email+"', '"+aadhar+"')";

            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null,  "Employee Added Successfully","Success", 1);

            setVisible(false);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Enter Valid Input (Age, Salary, Contact, Aadhar) ", "Warning",  2);
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
