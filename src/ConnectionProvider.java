import java.sql.*;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection(){
        Connection con = null;
        try{
            if(con==null){
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/quiz", "postgres","2009anshulab");
            }
            if(con!=null){
                System.out.println("Connection Established!");
            }else{
                System.out.println("Connection Failed");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
