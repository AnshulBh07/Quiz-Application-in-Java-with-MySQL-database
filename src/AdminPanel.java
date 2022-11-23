import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame implements ActionListener{

    private static JLabel l1,l2;
    private static JButton b1,b2,back;
    String name;

    public AdminPanel(){
        setTitle("Admin Panel");
        setBounds(400,200, 1200, 600);
        getContentPane().setBackground(new Color(204,255,230));
        setDefaultCloseOperation(3);
        setLayout(null);

        l1 = new JLabel("Welcome Anshul");
        l1.setBounds(380, 100, 800, 55);
        l1.setFont(new Font("Recoleta", Font.BOLD, 50));
        add(l1);
        l1.setVisible(true);

        l2 = new JLabel("Please choose any one to proceed");
        l2.setBounds(250, 200, 800, 45);
        l2.setFont(new Font("Dialog", Font.BOLD, 35));
        add(l2);
        l2.setVisible(true);

        b1 = new JButton("Take Quiz");
        b1.setBounds(250, 350, 250, 50);
        b1.setBackground(new Color(0,153,0));
        b1.setForeground(Color.white);
        b1.setFont(new Font("Dialog", Font.BOLD, 25));
        add(b1);
        b1.addActionListener(this);
        b1.setVisible(true);

        b2 = new JButton("Add Questions");
        b2.setBounds(650, 350, 250, 50);
        b2.setBackground(new Color(0,153,0));
        b2.setForeground(Color.white);
        b2.setFont(new Font("Dialog", Font.BOLD, 25));
        add(b2);
        b2.addActionListener(this);
        b2.setVisible(true);

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
        if(e.getSource() == b1){
            new test().setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == b2){
            new addQuestions().setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == back){
            new Quiz().setVisible(true);
            this.setVisible(false);
        }
    }
}
