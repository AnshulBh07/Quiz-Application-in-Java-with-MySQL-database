import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Score extends JFrame implements ActionListener {
    JButton b;
    private Quiz quiz;
    private test Test;
    JLabel hs;

    public Score(){
        setBounds(400, 200, 900, 600);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        JLabel l1 = new JLabel("Congratulations,");
        l1.setFont(new Font("Dialog", Font.BOLD, 20));
        l1.setForeground(Color.green);
        l1.setBackground(Color.black);
        l1.setBounds(100, 130, 600, 40);
        add(l1);

        JLabel l2 = new JLabel(quiz.getUsername());
        l2.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        l2.setForeground(Color.green);
        l2.setBackground(Color.black);
        l2.setBounds(280, 130, 600, 40);
        add(l2);

        JLabel l3 = new JLabel(",You have completed the quiz!");
        l3.setFont(new Font("Dialog", Font.BOLD, 20));
        l3.setForeground(Color.green);
        l3.setBackground(Color.black);
        l3.setBounds(500, 130, 600, 40);
        add(l3);

        JLabel l4 = new JLabel("Your Score is\n ");
        l4.setFont(new Font("Dialog", Font.BOLD, 30));
        l4.setForeground(Color.green);
        l4.setBackground(Color.black);
        l4.setBounds(340, 200, 600, 40);
        add(l4);

        JLabel l5 = new JLabel( "" + Test.getScore());
        l5.setFont(new Font("Viner Hand ITC", Font.BOLD, 100));
        l5.setForeground(Color.green);
        l5.setBackground(Color.black);
        l5.setBounds(400, 280, 600, 100);
        add(l5);

        highScore();

        b = new JButton("Play Again");
        b.setFont(new Font("Dialog", Font.BOLD, 20));
        b.setBounds(680, 430, 150, 55);
        b.setBackground(Color.blue);
        b.setForeground(Color.white);
        b.addActionListener(this);
        add(b);

        setVisible(true);
        setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b){
            new Quiz().setVisible(true);
            this.setVisible(false);
        }
    }

    private void highScore(){
        Connection con = ConnectionProvider.getConnection();

        try{
            //saving current player score in database
            PreparedStatement ps1 = con.prepareStatement("insert into scores(username,score) values(?,?)");
            ps1.setString(1, quiz.getUsername());
            ps1.setInt(2, Test.getScore());
            ps1.executeUpdate();

            //getting high score
            PreparedStatement ps2 = con.prepareStatement("select username,score from scores order by score desc limit 1");
            ResultSet rs = ps2.executeQuery();

            while(rs.next()){
                hs = new JLabel("High Score : "+rs.getString(1)+" - "+rs.getInt(2));
                hs.setFont(new Font("Dialog", Font.BOLD, 30));
                hs.setForeground(Color.green);
                hs.setBounds(180, 420, 800, 50);
                hs.setVisible(true);
                add(hs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
