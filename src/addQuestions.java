import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addQuestions extends JFrame implements ActionListener {

    private static JLabel ques,op1,op2,op3,op4,answer;
    private static JButton back,submit;
    private static JTextField tf_op1,tf_op2,tf_op3,tf_op4,tf_answer;
    private static JTextArea ta_ques;

    public addQuestions(){
        setTitle("Add Questions");
        getContentPane().setBackground(new Color(102,178,255));
        setBounds(400,200,1100,700);
        setLayout(null);
        setDefaultCloseOperation(3);

        //add labels
        ques = new JLabel("Question : ");
        ques.setBounds(150, 50, 100, 35);
        ques.setOpaque(true);
        ques.setBackground(new Color(204,230,255));
        ques.setHorizontalTextPosition(SwingConstants.CENTER);
        ques.setFont(new Font("Dialog", Font.PLAIN, 20));
        ques.setOpaque(true);
        add(ques);
        ques.setVisible(true);

        op1 = new JLabel("Option 1 : ");
        op1.setBounds(150, 180, 100, 35);
        op1.setOpaque(true);
        op1.setBackground(new Color(204,230,255));
        op1.setHorizontalTextPosition(SwingConstants.CENTER);
        op1.setFont(new Font("Dialog", Font.PLAIN, 20));
        op1.setOpaque(true);
        add(op1);
        op1.setVisible(true);

        op2 = new JLabel("Option 2 : ");
        op2.setBounds(150, 250, 100, 35);
        op2.setOpaque(true);
        op2.setBackground(new Color(204,230,255));
        op2.setHorizontalTextPosition(SwingConstants.CENTER);
        op2.setFont(new Font("Dialog", Font.PLAIN, 20));
        op2.setOpaque(true);
        add(op2);
        op2.setVisible(true);

        op3 = new JLabel("Option 3 : ");
        op3.setBounds(150, 320, 100, 35);
        op3.setOpaque(true);
        op3.setBackground(new Color(204,230,255));
        op3.setHorizontalTextPosition(SwingConstants.CENTER);
        op3.setFont(new Font("Dialog", Font.PLAIN, 20));
        op3.setOpaque(true);
        add(op3);
        op3.setVisible(true);

        op4 = new JLabel("Option 4 : ");
        op4.setBounds(150, 390, 100, 35);
        op4.setOpaque(true);
        op4.setBackground(new Color(204,230,255));
        op4.setHorizontalTextPosition(SwingConstants.CENTER);
        op4.setFont(new Font("Dialog", Font.PLAIN, 20));
        op4.setOpaque(true);
        add(op4);
        op4.setVisible(true);

        answer = new JLabel("Answer : ");
        answer.setBounds(150, 460, 100, 35);
        answer.setOpaque(true);
        answer.setBackground(new Color(204,230,255));
        answer.setHorizontalTextPosition(SwingConstants.CENTER);
        answer.setFont(new Font("Dialog", Font.PLAIN, 20));
        answer.setOpaque(true);
        add(answer);
        answer.setVisible(true);

        back = new JButton("Back");
        back.setBounds(950, 570, 100, 40);
        back.setFont(new Font("Dialog", Font.BOLD, 20));
        back.setForeground(Color.white);
        back.setBackground(new Color(51,51,255));
        add(back);
        back.addActionListener(this);
        back.setVisible(true);

        //setting up textareas and fields
        ta_ques = new JTextArea();
        ta_ques.setBounds(300, 50, 600, 80);
        ta_ques.setFont(new Font("Dialog", 0, 20));
        add(ta_ques);
        ta_ques.setVisible(true);

        tf_op1 = new JTextField();
        tf_op1.setBounds(300, 180, 600, 40);
        tf_op1.setFont(new Font("Dialog", 0, 20));
        add(tf_op1);
        tf_op1.setVisible(true);

        tf_op2 = new JTextField();
        tf_op2.setBounds(300, 250, 600, 40);
        tf_op2.setFont(new Font("Dialog", 0, 20));
        add(tf_op2);
        tf_op2.setVisible(true);

        tf_op3 = new JTextField();
        tf_op3.setBounds(300, 320, 600, 40);
        tf_op3.setFont(new Font("Dialog", 0, 20));
        add(tf_op3);
        tf_op3.setVisible(true);

        tf_op4 = new JTextField();
        tf_op4.setBounds(300, 390, 600, 40);
        tf_op4.setFont(new Font("Dialog", 0, 20));
        add(tf_op4);
        tf_op4.setVisible(true);

        tf_answer = new JTextField();
        tf_answer.setBounds(300, 460, 600, 40);
        tf_answer.setFont(new Font("Dialog", 0, 20));
        add(tf_answer);
        tf_answer.setVisible(true);

        submit = new JButton("Submit");
        submit.setForeground(Color.white);
        submit.setBounds(550, 520, 100, 40);
        submit.setFont(new Font("Dialog", Font.BOLD, 20));
        submit.setBackground(new Color(51,51,255));
        submit.addActionListener(this);
        add(submit);
        submit.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            new AdminPanel().setVisible(true);
            this.setVisible(false);
        }
        if(e.getSource() == submit){
            insertQues();
            this.setVisible(false);
            new addQuestions().setVisible(true);
        }
    }

    private void insertQues(){
        int x=0;

        Connection con = ConnectionProvider.getConnection();
        String question = ta_ques.getText();
        String option1 = tf_op1.getText();
        String option2 = tf_op2.getText();
        String option3 = tf_op3.getText();
        String option4 = tf_op4.getText();
        String answer = tf_answer.getText();

        try{
            PreparedStatement ps = con.prepareStatement("insert into questions(question,option1,option2,option3,option4,answer) values (?,?,?,?,?,?)");
            ps.setString(1,question);
            ps.setString(2,option1);
            ps.setString(3,option2);
            ps.setString(4,option3);
            ps.setString(5,option4);
            ps.setString(6,answer);
            ps.executeUpdate();
            x++;

            if(x>0){
                JOptionPane.showMessageDialog(submit, "Data saved successfully!");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
