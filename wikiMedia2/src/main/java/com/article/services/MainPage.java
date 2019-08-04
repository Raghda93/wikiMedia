package main.java.com.article.services;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import main.java.com.article.dao.DaoBase;
import main.java.com.article.dao.SQLiteDao;

@WebServlet("/mainPage")
public class MainPage extends HttpServlet {

	private static Logger logger = Logger.getLogger(MainPage.class);

	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			handleRequest(req, resp);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			handleRequest(req, resp);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Handle Request
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void handleRequest(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException, SQLException {

		ArticlesRetriever retriever = createArticleTableIfNotExist();
		req.setAttribute("SmallestSize", retriever.getSmallestSize());
		req.setAttribute("LargestSize", retriever.getLargestSize());
		req.setAttribute("MedianSize", retriever.getMedianSize());
		req.setAttribute("TotalWordcount", retriever.getTotalWordcount());
		req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
	}

	private ArticlesRetriever createArticleTableIfNotExist()
			throws ServletException, IOException, SQLException {
		DaoBase dao = new SQLiteDao();
		if (!isArticleTableExist(dao)) {
			try {
				dao.createArticlesTable();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new SQLException(e);
			}
		}
		ArticlesRetriever retriever = new ArticlesRetriever();
		retriever.retrievArticles();
		return retriever;
	}

	
	private boolean isArticleTableExist(DaoBase dao) throws SQLException {

		ResultSet resultSet = null;
		try {
			resultSet = dao.select("WIKIPEDIA_ARTICLES", null, null);
		} catch (SQLException e) {
			return false;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return true;
	}
}
