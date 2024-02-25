/*
 * @CopyRight to SSLab Pvt. Ltd. 2020. You should not disclose the information outside .
 * Otherwise Terms & Condition will be apply .
 */
package com.sstech.workshop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author STRINATH Oct 1, 2023
 * 
 * @Description: 
 */
public class ConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection connection ;
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "j2eeAdmin";
		String dbPassword = "*****";
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			if (connection == null) {
				System.out.println("Unable to connect with Oracle Database");
			} else {
				System.out.println("Connection Established");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
