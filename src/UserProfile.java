import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfile extends JFrame implements ActionListener {

    JButton takeTest,back;
    JLabel l1;

    public UserProfile(String name){
        setTitle("User Profile");
        setBounds(400,200, 1200, 600);
        getContentPane().setBackground(new Color(204,255,229));
        setDefaultCloseOperation(3);
        setLayout(null);

        takeTest = new JButton("Take Quiz");
        takeTest.setFont(new Font("Dialog", Font.BOLD, 30));
        takeTest.setBackground(new Color(153,0,0));
        takeTest.setForeground(Color.white);
        takeTest.setBounds(500, 220, 200, 55);
        takeTest.addActionListener(this);
        add(takeTest);
        takeTest.setVisible(true);

        //label for username
        l1 = new JLabel("Are you ready to take the quiz, "+name);
        l1.setFont(new Font("Dialog", Font.BOLD, 30));
        l1.setForeground(Color.black);
        l1.setBounds(350, 100, 1000, 50);
        add(l1);

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
        if(e.getSource() == takeTest){
            new test().setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == back){
            new Quiz().setVisible(true);
            this.setVisible(false);
        }
    }
}
