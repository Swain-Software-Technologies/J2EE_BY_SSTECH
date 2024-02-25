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
public class FetchEmployee {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*Scanner sc = new Scanner(System.in);
		System.out.println("For how many Roles  you want to now the emp details");
		Integer noOfRole = sc.nextInt();
		String[] empRole = new String[noOfRole];
		for(int i=0; i<empRole.length;i++) {
		    System.out.println("Enter Employee Designation");
			empRole[i] = sc.next();
		}
		String[] empDesg1 = 
		System.out.println(empRole);*/
		
	   fetchEmployeeBasedRole();
	}
	
	public static void fetchEmployeeBasedRole() {
		
		
		Scanner sc = new Scanner(System.in);
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUserName = "j2eeAdmin";
		String dbPassword = "*****";
		Statement statement=null;
		ResultSet resultSet=null;
		
		String desg1 = null;
		String desg2 = null;
		String desg3 = null;
		
		boolean flag = false;
		
		if(sc!=null) {
			System.out.println("Enter Employee Desg1");
			desg1=sc.next().toUpperCase();
			System.out.println("Enter Employee Desg2");
			desg2=sc.next().toUpperCase();
			System.out.println("Enter Employee Desg3");
			desg3=sc.next().toUpperCase();
		}
		String cond ="('"+desg1+"','"+desg2+"','"+desg3+"')";
		
		String fetchEmpDtlsOnRole = "SELECT  * FROM EMP WHERE job in"+cond+" order by job";
		try {
			
			
			
//			Register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Establish Connection
			Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			if(connection!=null) {
				statement = connection.createStatement();
				if(statement!=null) {
					resultSet = statement.executeQuery(fetchEmpDtlsOnRole);
				}
			}
			if(resultSet!=null) {
				while(resultSet.next()) {
					flag=true;
					System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+
							" "+resultSet.getInt(4)+
							" "+resultSet.getString(5)+" "+resultSet.getString(6)+" "+resultSet.getInt(7));
				}
				if(flag==false) {
					System.out.println("No Record Found");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cfe) {
			cfe.printStackTrace();
		}
	}
	
}
