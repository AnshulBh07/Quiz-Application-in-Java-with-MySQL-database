import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyPermission;

public class noOfQuestions {
    public int count;

    public int getCount() {
        return count;
    }

    public noOfQuestions(){
        Connection con = ConnectionProvider.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select count(*) from questions");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                count = rs.getInt(1);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
