package main.java.com.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import main.java.com.article.beans.ArticleBean;
import main.java.com.article.beans.ArticleList;
import main.java.com.article.utility.Utilities;

public class SQLiteDao extends DaoBase {

	private static final String DATABASE_URL = "jdbc:sqlite:";
	private static final String DATABASE_NAME = "wikipedia.db";// articles
	private static final String JDBC_DRIVER = "org.sqlite.JDBC";
	
	private static Logger logger = Logger.getLogger(SQLiteDao.class);

	/**
	 * Creates articles table, which contains the following columns (PAGEID,
	 * SIZE, WORDCOUNT, SNIPPET)
	 * 
	 * @param args
	 * @throws SQLException
	 */
	@Override
	public void createArticlesTable() throws SQLException {
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = getConnection();
			stmt = connection.createStatement();
			String sql = "CREATE TABLE WIKIPEDIA_ARTICLES " + "(PAGEID INT PRIMARY KEY," + " SIZE INT , "
					+ " WORDCOUNT INT, " + " SNIPPET VARCHAR NOT NULL)";

			stmt.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			closeResources(connection, stmt);
		}
	}

	/**
	 * Insert the sql statement
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	@Override
	public void insertArticles(String sql) throws SQLException {
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.createStatement();

			stmt.executeUpdate(sql);
			connection.commit();
		} catch (SQLException | ClassNotFoundException ex) {
			logger.error(ex.getMessage());
		} finally {
			closeResources(connection, stmt);
		}
	}

	/**
	 * Select statement, the query will be (SELECT COLUMN1, COLUMN2 ... FROM
	 * tableName WHERE KEY1 = KEY1_VALUE ....)
	 * 
	 * @param tableName
	 * @param columns
	 * @param keys
	 * @throws SQLException 
	 */
	@Override
	public ResultSet select(String tableName, List<String> columns, Map<String, Object> keys) throws SQLException {

		if(Utilities.isEmptyString(tableName)) {
			return null;
		}
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();

			stmt = connection.createStatement();
			String sql = getSqlStatement(tableName, columns, keys);
			rs = stmt.executeQuery(sql);
		} catch (ClassNotFoundException ex) {
			logger.error(ex.getMessage());
		} finally {
			closeResources(connection, stmt);
		}
		
		return rs;
	}
	
	public ResultSet selectWhereLike(String tableName, Map<String, String> keys) throws SQLException {
		
		if(Utilities.isEmptyString(tableName)) {
			return null;
		}
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();

			stmt = connection.createStatement();
			String sql = getSelectWhereLikeSqlStatement(tableName, keys);
			rs = stmt.executeQuery(sql);
		} catch (ClassNotFoundException ex) {
			logger.error(ex.getMessage());
		} finally {
			closeResources(connection, stmt);
		}
		
		return rs;
	}

	private String getSelectWhereLikeSqlStatement(String tableName, Map<String, String> keys) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(tableName);

		if (!Utilities.isEmptyMap(keys)) {
			sb.append(" WHERE ");
			for (Map.Entry key : keys.entrySet()) {
				sb.append(key.getKey() + " LIKE '%");
				sb.append(key.getValue());
				sb.append("%'");
				sb.append(" and ");
			}
			sb.delete(sb.length() - 5, sb.length());
		}

		return sb.toString();

	}

	private void closeResources(Connection connection, Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	private String getSqlStatement(String tableName, List<String> columns, Map<String, Object> keys) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");

		if (Utilities.isEmptyCollection(columns)) {
			sb.append(" * ");
		} else {
			for (String column : columns) {
				sb.append(column + ", ");
			}
			sb.delete(sb.length() - 3, sb.length());
		}

		sb.append(" FROM " + tableName);

		if (!Utilities.isEmptyMap(keys)) {
			sb.append(" WHERE ");
			for (Map.Entry key : keys.entrySet()) {
				sb.append(key.getKey() + " = ");
				if(key.getValue() instanceof String) {
					sb.append("'");
					sb.append(key.getValue());
					sb.append("'");
				} else {
					sb.append(key.getValue());
				}
				sb.append(" and ");
			}
			sb.delete(sb.length() - 5, sb.length());
		}

		return sb.toString();
	}

	/**
	 * returns the connection
	 * 
	 * @return
	 * @throws ClassNotFoundException 
	 */
	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(DATABASE_URL + DATABASE_NAME);
	}
}
