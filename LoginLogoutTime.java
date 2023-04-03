
import java.sql.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;


public class LoginLogoutTime {

    public static void loginFunc(){
        LocalTime lt = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        String login = lt.format(df);

        try {
            Conn c = new Conn();
            c.s.executeUpdate("update login_time set loginTime = '0', logoutTime = '0' where id = '0';");
            c.s.executeUpdate("update login_time set loginTime = '"+login+"' where id = '0';");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logoutFunc(){
        LocalTime lt = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        String logout = lt.format(df);

        try {
            Conn c = new Conn();
            c.s.executeUpdate("update login_time set logoutTime = '"+logout+"' where id='0';");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void duration() {
        String op = "", in = "", out = "";

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login_time where id = '0'");
            while(rs.next()){
                in = rs.getString("loginTime");
                out = rs.getString("logoutTime");
            }
            LocalTime ltin = LocalTime.parse(in);
            LocalTime ltout = LocalTime.parse(out);

            Duration d = Duration.between(ltin, ltout);
            
            int s = (int) (d.toMillis() / 1000) % 60 ;
            int m = (int) ((d.toMillis() / (1000*60)) % 60);
            int h = (int) ((d.toMillis() / (1000*60*60)) % 24);

            op = "  You logged in for "+h+" Hrs "+m+" Mins "+s+" Secs.";

        } catch (Exception e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, op, "Logged Out Successfully", -1);
    }
}
