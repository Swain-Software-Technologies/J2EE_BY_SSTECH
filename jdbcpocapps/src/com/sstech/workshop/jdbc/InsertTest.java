/*
 * @CopyRight to SSLab Pvt. Ltd. 2020. You should not disclose the information outside .
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
 * @author STRINATH Oct 4, 2023
 * 
 * @Description:
 */
public class InsertTest {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Scanner scanner = null;
		int studentId = 0;
		String studentName = null, studentAddress = null;
		float studentPercentage = 0.0f;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "j2eeAdmin";
		String dbPassword = "****";

		scanner = new Scanner(System.in);
		if (scanner != null) {
			System.out.println("Ener Student Id");
			studentId = scanner.nextInt();
			System.out.println("Enter Student Name");
			studentName = scanner.next();
			System.out.println("Enter student Address");
			studentAddress = scanner.next();
			System.out.println("Enter student Percentage");
			studentPercentage = scanner.nextFloat();
		}

		// Convert Users input data as per SQL query i.e insert into STUDENT values
		// (103, 'Bharat', 'Ayodha', 68.7);

		studentName = "'" + studentName + "'";
		studentAddress = "'" + studentAddress + "'";

		// Register JDBC Driver Software
//		Class.forName("oracle.jdbc.driver.OracleDriver"); ---> Optional

		// Establish connection
		try {
			connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

			// Create the Statement Object
			if (connection != null) {

				statement = connection.createStatement();

				// Send & Execute the SQL Query
				int count = 0;

				String insertQuery = "insert into STUDENT values(" + studentId + "," + studentName + ","
						+ studentAddress + "," + studentPercentage + ")";
				System.out.println(insertQuery);

				count = statement.executeUpdate(insertQuery);
				if (count == 0) {
					System.out.println("Record not inserted");
				} else {
					System.out.println("Record inserted");
				}
			}
		} catch (SQLException se) {
			if (se.getErrorCode() == 1) {
				System.out.println("You can not insert student details with Same Student Id");
			} else if (se.getErrorCode() == 12899) {
				System.out.println("You can not enter values more than column size");
			} else if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
				System.out.println("SQL query Syntax Error");
			} else {
				System.out.println("Getting some error related to JDBC");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
