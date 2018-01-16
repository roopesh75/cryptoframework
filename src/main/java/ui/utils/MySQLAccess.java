package ui.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;

	public String getSecurityRoleSeq() throws Exception {
		System.out.println("I WAS CALLED FROM SOMEWHERE!!!");
		String roleId = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://selenium01:3306/apptestdb", "root", "root");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select SecurityRoleSeq from sequenceentity");

			while (resultSet.next()) {
				roleId = resultSet.getString("SecurityRoleSeq");
				break;
			}
			String updateRoleId = "0000" + (Long.parseLong(roleId) - 1);
			String updateTableSQL = "update sequenceentity set SecurityRoleSeq=? where Id=?";
			preparedStatement = connect.prepareStatement(updateTableSQL);
			preparedStatement.setString(1, updateRoleId);
			preparedStatement.setInt(2, 1);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return roleId;
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
