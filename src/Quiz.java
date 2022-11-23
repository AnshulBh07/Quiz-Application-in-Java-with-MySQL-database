import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quiz extends JFrame implements ActionListener, KeyListener {

    JButton Login,SignUp;
    JTextField tf1;
    JPasswordField tf2;
    JLabel l1,l2,l3,l4,l5;
    private static String username;

    public Quiz(){

        setTitle("Quiz");
        setBounds(400,200, 1200, 600);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(3);
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz_time.jpg"));
        l1 = new JLabel(i1);
        l1.setBounds(0,0,600,600);
        add(l1);

        setVisible(true);

        l2 = new JLabel("Test Your Knowledge!");
        l2.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        l2.setBackground(Color.black);
        l2.setForeground(Color.cyan);
        l2.setBounds(740, 140, 500, 43);
        add(l2);

        //label that appears on mouse hover
        l3 = new JLabel("Submit and proceed to the quiz!");
        l3.setFont(new Font("Dialog", Font.BOLD, 20));
        l3.setBackground(Color.black);
        l3.setForeground(Color.cyan);
        l3.setBounds(650, 500, 500, 40);
        add(l3);
        l3.setVisible(true);

        tf1 = new JTextField();
        tf1.setBounds(780, 250, 250, 35);
        tf1.setFont(new Font("Dialog", Font.BOLD, 18));
        add(tf1);
        tf1.setVisible(true);

        tf2 = new JPasswordField();
        tf2.setBounds(780, 300, 250, 35);
        tf2.setFont(new Font("Dialog", Font.BOLD, 18));
        add(tf2);
        tf2.setVisible(true);

        //make username and password labels
        l4 = new JLabel("Email");
        l4.setBackground(Color.gray);
        l4.setForeground(Color.white);
        l4.setFont(new Font("Dialog", Font.BOLD, 20));
        l4.setBounds(670, 250, 100, 35);
        l4.setVisible(true);
        add(l4);

        l5 = new JLabel("Password");
        l5.setBackground(Color.gray);
        l5.setForeground(Color.white);
        l5.setFont(new Font("Dialog", Font.BOLD, 20));
        l5.setBounds(670, 302, 100, 35);
        l5.setVisible(true);
        add(l5);

        Login = new JButton("Login");
        Login.setBounds(790, 370, 100, 35);
        Login.setBackground(Color.blue);
        Login.setForeground(Color.white);
        Login.addActionListener(this);
        Login.addKeyListener(this);
        add(Login);
        Login.setVisible(true);

        SignUp = new JButton("Sign Up");
        SignUp.setBackground(Color.blue);
        SignUp.setForeground(Color.white);
        SignUp.setBounds(920, 370, 100, 35);
        SignUp.setVisible(true);
        add(SignUp);
        SignUp.addActionListener(this);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == SignUp){
                this.setVisible(false);
                new Registration().setVisible(true);
            }

            if(e.getSource() == Login){
                LogIn();
            }
    }

    private void LogIn(){
        Connection con = ConnectionProvider.getConnection();
        String email = tf1.getText();
        char[] p = tf2.getPassword();
        String pass = new String(p);

        if(email.equals("anshulbh@xyz.com") && pass.equals("dhakkan666")){
            new AdminPanel().setVisible(true);
            this.setVisible(false);
        }
        else{
            try{
                PreparedStatement ps = con.prepareStatement("select username from users where email=? and password=?");
                ps.setString(1, email);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    username = rs.getString(1);
                    new UserProfile(username).setVisible(true);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect E-mail or Password\nPlease try again with correct details");
                }
            }
            catch(SQLException sq){
                sq.printStackTrace();
            }
        }
    }

    public static void setUsername(String username) {
        Quiz.username = username;
    }

    public static String getUsername() {
        return username;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            LogIn();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
