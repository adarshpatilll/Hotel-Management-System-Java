
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Hotel Name -> VAY To Relax
 * Founder -> Adarsh
 * Co-Founders -> Vikram, Yusuf
 */

 class LandingPage implements ActionListener {
    static JFrame fr = new JFrame("Welcome");
    ImageIcon mainImg;
    JLabel imgLbl, titleLbl;
    JButton next, exit;

    LandingPage(){
        //------Main Image
        mainImg = new ImageIcon(ClassLoader.getSystemResource("images/landing.jpg"));
        imgLbl = new JLabel(mainImg);
        imgLbl.setBounds(-10, 0, 1925, 1280);
        fr.add(imgLbl);

        //------Title Display -> VAY TO Relax
        titleDisplay();

        //------Next Button
        ImageIcon nextImg = new ImageIcon(ClassLoader.getSystemResource("images/next.png"));
        next = new JButton(nextImg); 
        next.setBounds(1730, 900, 150, 65);
        next.addActionListener(this);
        imgLbl.add(next);

        //------Frame
        ImageIcon decLogo = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
        Image logo = decLogo.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        fr.setIconImage(logo);
        fr.setBounds(0, 5, 1925, 1030);
        fr.setLayout(null);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
    
    void titleDisplay(){
        //------Title - VAY
        titleLbl = new JLabel("VAY");
        titleLbl.setFont(new Font("Arial Black", Font.BOLD, 150));
        titleLbl.setForeground(new Color(255, 60, 0));
        titleLbl.setBounds(1200,150,500,200);
        imgLbl.add(titleLbl);

        //------Title - To
        titleLbl = new JLabel("To");
        titleLbl.setFont(new Font("Arial Black", Font.BOLD, 150));
        titleLbl.setForeground(new Color(254, 254, 254));
        titleLbl.setBounds(1270,350,500,200);
        imgLbl.add(titleLbl);
        
        //------Title - Relax
        titleLbl = new JLabel("Relax");
        titleLbl.setFont(new Font("Arial Black", Font.BOLD, 150));
        titleLbl.setForeground(new Color(0, 223, 20));
        titleLbl.setBounds(1150,550,500,200);
        imgLbl.add(titleLbl);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // fr.setVisible(false);
        new Login();
    }
    
    public static void main(String[] args) {
        new LandingPage();
    
    }
}
