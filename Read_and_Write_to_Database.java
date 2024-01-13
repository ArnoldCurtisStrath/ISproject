import java.sql.*;

public class Read_and_Write_to_Database extends HandlingAdjectives{

    public void setAllAdjectives(HandlingAdjectives adj)
    {
        try {
            String host = "jdbc:mysql://localhost:3306/rating";
            String uName = "root";
            String uPass = "1234";
            Connection con = DriverManager.getConnection(host, uName, uPass);



            String sql =  "SELECT * FROM all adjectives WHERE ID = ? AND ALL ADJECTIVES = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, adj.getAdjectiveID());
                preparedStatement.setString(2, adj.getAdjectives());


                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        //user.setID(rs.getInt("ID"));
                        adj.setAdjectiveID(rs.getInt("ID"));
                        adj.setAdjectives(rs.getString("ALL ADJECTIVES"));
                        System.out.println("Adjective Found");
                        adj.setIfAdjectives(true);



                    } else {
                        // Handle the case where no matching user is found
                        adj.setIfAdjectives(false);
                        System.out.println("Adjective not found in the User Input");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
