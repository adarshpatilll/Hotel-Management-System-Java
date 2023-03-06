
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class Login implements ActionListener{

    JFrame fr = new JFrame("Login");
    JTextField nameArea;
    JPasswordField passArea;
    JButton loginButton, cancelButton;

    Login(){
        //--------Name Label
        JLabel nameLbl = new JLabel("Name");
        nameLbl.setBounds(80, 80, 100, 20);
        nameLbl.setFont(new Font("arial", Font.PLAIN, 20));
        fr.add(nameLbl);

        //--------Name TextField
        nameArea = new JTextField();
        nameArea.setBounds(200, 75, 200, 27);
        nameArea.setFont(new Font("arial", Font.PLAIN, 20));
        fr.add(nameArea);
        
        //--------Password Label
        JLabel passLbl = new JLabel("Password");
        passLbl.setBounds(80, 140, 100, 20);
        passLbl.setFont(new Font("arial", Font.PLAIN, 20));
        fr.add(passLbl);

        //--------Password TextField
        passArea = new JPasswordField();
        passArea.setBounds(200, 135, 200, 27);
        passArea.setFont(new Font("arial", Font.PLAIN, 20));
        fr.add(passArea);

        //--------Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(80,210,130,30);
        loginButton.setFont(new Font("arial", Font.PLAIN, 20));
        loginButton.setBackground(new Color(126, 255, 141));
        loginButton.addActionListener(this);
        fr.add(loginButton);

        //--------Forgot Password Button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(270,210,130,30);
        cancelButton.setFont(new Font("arial", Font.PLAIN, 20));
        cancelButton.setBackground(new Color(255, 126, 126 ));
        cancelButton.addActionListener(this);
        fr.add(cancelButton);
        
        //--------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        fr.setIconImage(logo);
        fr.setBounds(700, 250, 500,370);
        fr.setLayout(null);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String user = nameArea.getText();
            String pass = passArea.getText();

            try {
                Conn c = new Conn();
                String query = "select * from login where username = '"+ user +"' and password = '"+ pass+ "'";
                
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()){
                    LandingPage.fr.setVisible(false);;
                    fr.setVisible(false);
                    new Dashboard();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            fr.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
