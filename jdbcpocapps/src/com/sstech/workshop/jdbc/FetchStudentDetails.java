/*
 * @CopyRight to Swain Software Lab Pvt. Ltd. 2020. You should not disclose the information outside .
 * Otherwise Terms & Condition will be apply .
 */
package com.sstech.workshop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author STRINATH Feb 24, 2024
 * 
 * @Description:
 */
public class FetchStudentDetails {

	static Scanner stdScanner = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner  scanner = new Scanner(System.in);
		System.out.println("Enter Student city name");
		String STD_ADDRS = scanner.next();
//		Convert Users input data as per SQL query
		STD_ADDRS = "'"+STD_ADDRS+"'";
		fetchStdntDtlsBasedOnCity(STD_ADDRS);

	}

	public static void fetchStdntDtlsBasedOnCity(String STD_ADDRS) {

		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "j2eeAdmin";
		String dbPassword = "*****";
		String fetchStudentOnCity="SELECT * FROM STUDENT WHERE STD_ADDRS="+STD_ADDRS;
		Connection con = null;
		ResultSet resultSet = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			if (con == null) {
				System.out.println("Unable to connect with database");
			} else {
				System.out.println("Connection Established");
			}
			
			if(con != null) {
//				create Staement Object
				Statement statement = con.createStatement();
//				prepare the Result Set object & Execute the Query 
				resultSet = statement.executeQuery(fetchStudentOnCity);
				
					if(resultSet != null) {
						while(resultSet.next()) {
							System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getFloat(4));
						}
					}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

	}

}
