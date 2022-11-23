import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test extends JFrame implements ActionListener {

    noOfQuestions nques = new noOfQuestions();
    public int numberOfQues = nques.getCount();
    JButton save,submit;
    JLabel question,qno;
    JRadioButton option1,option2,option3,option4;
    String Questions[][] = new String[numberOfQues][5];
    String stuAnswer[] = new String[numberOfQues];
    String Answer[] = new String[numberOfQues];
    ButtonGroup options;
    public static int count = 0;
    public static int i = 0;
    public static int score = 0;

    public test(){
        setBounds(400, 200, 900, 600);
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(3);
        setLayout(null);
        ImageIcon im = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        JLabel l = new JLabel(im);
        l.setBounds(0, 0, 900, 200);
        add(l);

        question = new JLabel("");
        question.setFont(new Font("Tahoma", Font.PLAIN, 20));
        question.setForeground(Color.white);
        question.setBounds(100,200,800,60);
        add(question);

        qno = new JLabel("");
        qno.setFont(new Font("Tahoma", Font.PLAIN, 20));
        qno.setForeground(Color.white);
        qno.setBounds(50,200,30,60);
        add(qno);

        option1 = new JRadioButton("");
        option1.setBounds(100, 280, 350, 50);
        option1.setBackground(Color.darkGray);
        option1.setForeground(Color.white);
        option1.setFont(new Font("Dialog", Font.BOLD, 20));
        option1.addActionListener(this);
        add(option1);

        option2 = new JRadioButton("");
        option2.setBounds(450, 280, 350, 50);
        option2.setBackground(Color.darkGray);
        option2.setForeground(Color.white);
        option2.setFont(new Font("Dialog", Font.BOLD, 20));
        option2.addActionListener(this);
        add(option2);

        option3 = new JRadioButton("");
        option3.setBounds(100, 380, 350, 50);
        option3.setBackground(Color.darkGray);
        option3.setForeground(Color.white);
        option3.setFont(new Font("Dialog", Font.BOLD, 20));
        option3.addActionListener(this);
        add(option3);

        option4 = new JRadioButton("");
        option4.setBounds(450, 380, 350, 50);
        option4.setBackground(Color.darkGray);
        option4.setForeground(Color.white);
        option4.setFont(new Font("Dialog", Font.BOLD, 20));
        option4.addActionListener(this);
        add(option4);

        //buttons
        save = new JButton("Save");
        save.setBounds(620, 470, 100, 40);
        save.setBackground(Color.blue);
        save.setForeground(Color.WHITE);
        save.setFont(new Font("Dialog", Font.BOLD, 20));
        save.addActionListener(this);
        add(save);

        submit = new JButton("Submit");
        submit.setBounds(730, 470, 100, 40);
        submit.setBackground(Color.blue);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Dialog", Font.BOLD, 20));
        submit.addActionListener(this);
        add(submit);

        options = new ButtonGroup();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        Connection con = ConnectionProvider.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from questions");
            ResultSet rs = ps.executeQuery();

            //Populating Questions and Answer array
            while(rs.next()){
                System.out.println("i"+i);
                Questions[i][0] = rs.getString(2);
                Questions[i][1] = rs.getString(3);
                Questions[i][2] = rs.getString(4);
                Questions[i][3] = rs.getString(5);
                Questions[i][4] = rs.getString(6);
                Answer[i] = rs.getString(7);
                i++;
            }
        }
        catch(SQLException s){
            s.printStackTrace();
        }

        update(0);
    }

    public void update(int count){
        qno.setText(""+(count+1)+".");
        question.setText(Questions[count][0]);
        option1.setText(Questions[count][1]);
        option1.setActionCommand(Questions[count][1]);
        option2.setText(Questions[count][2]);
        option2.setActionCommand(Questions[count][2]);
        option3.setText(Questions[count][3]);
        option3.setActionCommand(Questions[count][3]);
        option4.setText(Questions[count][4]);
        option4.setActionCommand(Questions[count][4]);
        options.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);

            if (options.getSelection() == null) {
                stuAnswer[count] = "";
            } else {
                stuAnswer[count] = options.getSelection().getActionCommand();
            }

            if (count == (numberOfQues - 2)) {
                save.setEnabled(false);
                submit.setEnabled(true);
            }

            count++;
            update(count);
        }
        else if (e.getSource() == submit) {
            //adding score for last question
            if (options.getSelection() == null) {
                stuAnswer[count] = "";
            } else {
                stuAnswer[count] = options.getSelection().getActionCommand();
            }

            //updating score finally
            for (int j = 0; j < stuAnswer.length; j++) {
                System.out.println(j);
                if (stuAnswer[j].equals(Answer[j])) {
                    score += 10;
                } else {
                    score += 0;
                }
            }

            for (String x : Answer) {
                System.out.print(x + ",");
            }
            System.out.println();
            for (String x : stuAnswer) {
                System.out.print(x + ",");
            }
            System.out.println();
            System.out.println("Score : " + score);
            new Score().setVisible(true);
            this.setVisible(false);
        }
    }

    public static void setScore(int score) {
        test.score = score;
    }

    public static int getScore() {
        return score;
    }
}
