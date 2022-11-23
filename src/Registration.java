import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration extends JFrame implements ActionListener {

    JLabel l1,l2,l3,l4,l5,l6;
    JTextField tf_username,tf_email;
    JPasswordField tf_pass,tf_confirm_pass;
    JButton submit,back;

    public Registration(){
        setTitle("Register Yourself!");
        setBounds(400,200,1200,600);
        getContentPane().setBackground(new Color(204,255,229));
        setDefaultCloseOperation(3);
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/register.png"));
        l1 = new JLabel(i1);
        l1.setBounds(0, 0, 600, 600);
        add(l1);
        l1.setVisible(true);

        l2 = new JLabel("Please fill in your details.");
        l2.setBounds(650, 80, 900, 40);
        l2.setFont(new Font("Dialog", Font.BOLD, 30));
        add(l2);
        l2.setVisible(true);

        l3 = new JLabel("Username");
        l3.setBounds(600,180 , 150, 20);
        l3.setFont(new Font("Dialog", Font.BOLD, 20));
        add(l3);
        l3.setVisible(true);

        l4 = new JLabel("E-Mail");
        l4.setBounds(600,230 , 150, 20);
        l4.setFont(new Font("Dialog", Font.BOLD, 20));
        add(l4);
        l4.setVisible(true);

        l5 = new JLabel("Password");
        l5.setBounds(600,280 , 150, 20);
        l5.setFont(new Font("Dialog", Font.BOLD, 20));
        add(l5);
        l5.setVisible(true);

        l6 = new JLabel("Confirm Password");
        l6.setBounds(600,330 , 200, 20);
        l6.setFont(new Font("Dialog", Font.BOLD, 20));
        add(l6);
        l6.setVisible(true);

        //setting up textfields
        tf_username = new JTextField();
        tf_username.setBounds(820, 180, 250, 30);
        tf_username.setFont(new Font("Dialog", Font.BOLD, 16));
        add(tf_username);
        tf_username.setVisible(true);

        tf_email = new JTextField();
        tf_email.setBounds(820, 230, 250, 30);
        tf_email.setFont(new Font("Dialog", Font.BOLD, 16));
        add(tf_email);
        tf_email.setVisible(true);

        tf_pass = new JPasswordField();
        tf_pass.setBounds(820, 280, 250, 30);
        tf_pass.setFont(new Font("Dialog", Font.BOLD, 16));
        add(tf_pass);
        tf_pass.setVisible(true);

        tf_confirm_pass = new JPasswordField();
        tf_confirm_pass.setBounds(820, 330, 250, 30);
        tf_confirm_pass.setFont(new Font("Dialog", Font.BOLD, 16));
        add(tf_confirm_pass);
        tf_confirm_pass.setVisible(true);

        //submit button
        submit = new JButton("Submit");
        submit.setBounds(820, 400, 100, 40);
        submit.setBackground(new Color(0,102,0));
        submit.setForeground(Color.white);
        add(submit);
        submit.addActionListener(this);
        submit.setVisible(true);

        //back button
        back = new JButton("Back");
        back.setBounds(1050, 490, 100, 40);
        back.setForeground(Color.white);
        back.setBackground(new Color(52,152,219));
        add(back);
        back.addActionListener(this);
        back.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            SignUp();
            new Registration().setVisible(true);
            this.setVisible(false);
        }

        if(e.getSource() == back){
            this.setVisible(false);
            new Quiz().setVisible(true);
        }
    }

    private void SignUp(){
        int x = 0;

        Connection con = ConnectionProvider.getConnection();
        String username = tf_username.getText();
        String email = tf_email.getText();
        char[] s1 = tf_pass.getPassword();
        String pass = new String(s1);
        char[] s2 = tf_confirm_pass.getPassword();
        String confirm_pass = new String(s2);

        if(pass.equals(confirm_pass)){
            try{
                PreparedStatement ps = con.prepareStatement("insert into users(username,email,password) values(?,?,?)");
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, pass);
                ps.executeUpdate();
                x++;
                if(x>0){
                    JOptionPane.showMessageDialog(submit, "Data saved Successfully!");
                }
            }
            catch(SQLException s){
                s.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(submit, "Password does not match!");
        }
    }
}
