package com.levo.dockerexample.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	/*
	 * MYSQL_USER MYSQL_PASSWORD MYSQL_ROOT_PASSWORD MYSQL_DATABASE
	 */

	@RequestMapping("/")
	public int index() {
		int result = 0;

		try {
			String databaseURL = "jdbc:mysql://";
			databaseURL += System.getenv("MYSQL_SERVICE_HOST");
			databaseURL += "/" + System.getenv("MYSQL_DATABASE");
			String username = System.getenv("MYSQL_USER");
			String password = System.getenv("MYSQL_PASSWORD");
			Connection connection = DriverManager.getConnection(databaseURL, username, password);
			if (connection != null) {
				String SQL = "select * from tutorials_tbl;";
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				while (rs.next()) {
					result = rs.getInt("tutorial_id");

				}
				rs.close();
				connection.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}
}
