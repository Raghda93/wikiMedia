package main.java.com.article.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import main.java.com.article.beans.ArticleBean;
import main.java.com.article.beans.ArticleList;
import main.java.com.article.dao.SQLiteDao;

@Path(Search.SERVICE_NAME)
public class Search {

	public static final String SERVICE_NAME = "search";
	public static final String ARTICLE = "article";
	public static final String SNIPPET = "snippet";

	@GET
	@Path("article")
	@Produces(MediaType.APPLICATION_JSON)
	public ArticleList getArticles(@QueryParam(SNIPPET) String searchedItem) throws SQLException {
		ArticleList listOfArticles = getAvailableArticles(searchedItem);
		return null;
	}

	private ArticleList getAvailableArticles(String searchedItem) throws SQLException {
		
		SQLiteDao dao = new SQLiteDao();
		Map<String, String> keys = new HashMap<String, String>();
		keys.put(ArticlesRetriever.ARTICLE_SNIPPET_COLUMN_NAME, searchedItem);
		ResultSet result = dao.selectWhereLike(ArticlesRetriever.TABLE_NAME, keys );
		
		List<ArticleBean> list = new ArrayList<ArticleBean>();
		ArticleBean bean = null;
		while (result.next()) {
			int pageId = result.getInt(ArticlesRetriever.PAGEID_COLUMN_NAME);
			int size = result.getInt(ArticlesRetriever.ARTICLE_SIZE_COLUMN_NAME);
			int wordCount = result.getInt(ArticlesRetriever.ARTICLE_WORD_COUNT_COLUMN_NAME);
			String snippet = result.getString(ArticlesRetriever.ARTICLE_SNIPPET_COLUMN_NAME);

			bean = new ArticleBean(pageId, size, wordCount, snippet);
			list.add(bean);
		}
		return new ArticleList(list);
	}

}
