package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import test.User;
import test.DBUtil;

public class UserDAO {

	private final String USER_TABLE = "user";
	
	private Connection connection;
	//private Statement statement;
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public User getUser(int UID)
	{
		ResultSet rs = null;
		User user = null;
		PreparedStatement pstmt = null;
		
	    String query = "SELECT * FROM user WHERE uid = ?";
		//int uid;
		//String userName;
		//String sex;
		
		try {
			user = new User();
			connection = ConnectionFactory.getConnection();
			//statement = connection.createStatement();
			
			pstmt = connection.prepareStatement(query);
			//pstmt.setString(1, USER_TABLE);
			pstmt.setInt(1, UID);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				user.setUID(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));

				System.out.print("UID:"+Integer.toString(user.getUID()) + " Name:"+ user.getName() + " Sex:"+user.getSex());
				System.out.println();
			}

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			//DBUtil.close(statement);
			DBUtil.close(connection);
			System.out.println("CLOSE SQL");
		}

		return user;
	}
}
