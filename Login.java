
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("all")
public class Login implements ActionListener{

    private JFrame fr = new JFrame("LOGIN");
    private JTextField nameArea;
    private JPasswordField passArea;
    private JButton loginButton, cancelButton;
    private JLabel att;

    Login(){
        //--------Name Label
        JLabel nameLbl = new JLabel("NAME");
        nameLbl.setBounds(80, 80, 100, 20);
        nameLbl.setFont(new Font("arial", Font.PLAIN, 18));
        fr.add(nameLbl);

            //--------Name TextField
            nameArea = new JTextField();
            nameArea.setBounds(200, 75, 200, 27);
            nameArea.setFont(new Font("arial", Font.PLAIN, 18));
            fr.add(nameArea);
        
        //--------Password Label
        JLabel passLbl = new JLabel("PASSWORD");
        passLbl.setBounds(80, 140, 200, 20);
        passLbl.setFont(new Font("arial", Font.PLAIN, 18));
        fr.add(passLbl);

            //--------Password TextField
            passArea = new JPasswordField();
            passArea.setBounds(200, 135, 200, 27);
            passArea.setFont(new Font("arial", Font.PLAIN, 18));
            fr.add(passArea);

        //--------Login Button
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(80,210,130,30);
        loginButton.setFont(new Font("arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(126, 255, 141));
        loginButton.addActionListener(this);
        fr.add(loginButton);

        //--------Cancel Button
        cancelButton = new JButton("CANCEL");
        cancelButton.setBounds(270,210,130,30);
        cancelButton.setFont(new Font("arial", Font.PLAIN, 18));
        cancelButton.setBackground(new Color(255, 126, 126 ));
        cancelButton.addActionListener(this);
        fr.add(cancelButton);

        //--------Attempt 
        String strAtt = "3";
        att = new JLabel("Attempts Left  >>  "+strAtt);
        att.setBounds(230, 165, 300, 30);
        att.setFont(new Font("calibri", 1, 17));
        if(attempt == 3)
            att.setForeground(Color.RED);
        fr.add(att);
        
        //--------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        fr.setIconImage(logo);
        fr.setBounds(700, 330, 500,320);
        // fr.getContentPane().setBackground(Color.WHITE);
        fr.setUndecorated(true);
        fr.setLayout(null);
        fr.setVisible(true);
    }
    
    private int attempt = 3;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == loginButton){
           
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
            String user = nameArea.getText();
            String pass = passArea.getText();

            try {
                Conn c = new Conn();
                String query = "select * from login where username = '"+ user +"' and password = '"+ pass+"'";
                
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()){
                    LandingPage.fr.setVisible(false);
                    fr.setVisible(false);
                    new Dashboard();
                }
                else {
                    attempt--;
                    att.setText("Attempts Left  >>  "+attempt);
                    if(attempt >= 1)
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password","Warning", 2);
                    nameArea.setText(null);
                    passArea.setText(null);
                }
                if (attempt == 0){
                    JOptionPane.showMessageDialog(null, "Attempts Exceeded!", "Blocked",0);
                    nameArea.setEnabled(false);
                    passArea.setEnabled(false);
                    loginButton.setEnabled(false);
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
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
    }
}
