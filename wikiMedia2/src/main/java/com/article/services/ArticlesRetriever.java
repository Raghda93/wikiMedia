package main.java.com.article.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import main.java.com.article.beans.ArticleBean;
import main.java.com.article.dao.SQLiteDao;
import main.java.com.article.utility.Utilities;

public class ArticlesRetriever {

	public static final String TABLE_NAME = "WIKIPEDIA_ARTICLES";
	public static final String PAGEID_COLUMN_NAME = "PAGEID";
	public static final String ARTICLE_SIZE_COLUMN_NAME = "SIZE";
	public static final String ARTICLE_WORD_COUNT_COLUMN_NAME = "WORDCOUNT";
	public static final String ARTICLE_SNIPPET_COLUMN_NAME = "SNIPPET";
	
	private static Logger logger = Logger.getLogger(MainPage.class);
	private int smallestSize = 0;
	private int largestSize = 0;
	private int totalWordcount= 0;
	private int totalArticlesCount = 0;

	/**
     * Handle Request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	public void retrievArticles() throws ServletException, IOException {

		Response response = ArticalService.invoke();
		String responseString = response.readEntity(String.class);
		ArrayList<ArticleBean> articeList = getArticleListFromJsonObject(responseString);
		insertArticlesIntoDatabase(articeList);

	}

	private void insertArticlesIntoDatabase(ArrayList<ArticleBean> articeList) {
		StringBuilder sb = new StringBuilder();
		SQLiteDao dao = new SQLiteDao();
		for (ArticleBean bean : articeList) {
			sb.append("INSERT INTO " + TABLE_NAME + " (" + PAGEID_COLUMN_NAME + ", " + ARTICLE_SIZE_COLUMN_NAME + ", "
					+ ARTICLE_WORD_COUNT_COLUMN_NAME + ", " + ARTICLE_SNIPPET_COLUMN_NAME + ") VALUES (");
			sb.append(bean.getPageId() + ", ");
			sb.append(bean.getSize() + ", ");
			int wordCount = bean.getWordCount();
			sb.append(wordCount + ", ");
			
			articlesStatistics(wordCount);
			
			String snippet = Utilities.escapeMetaCharacters(bean.getSnippet());
			sb.append("\'" + snippet + "\');\n \n ");
		}
		try {
			dao.insertArticles(sb.toString());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	

	private void articlesStatistics(int wordCount) {
		
		if(wordCount < smallestSize || smallestSize == 0) {
			smallestSize = wordCount;
		}
		if(wordCount > largestSize) {
			largestSize = wordCount;
		}
		totalWordcount = totalWordcount + wordCount;
		totalArticlesCount++;
		
	}

	private ArrayList<ArticleBean> getArticleListFromJsonObject(String responseString) {

		JSONObject jsonObject = new JSONObject(responseString);
		JSONArray jsonArray = (JSONArray) ((JSONObject) jsonObject.get("query")).get("search");
		ArrayList<ArticleBean> articeList = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jo = jsonArray.getJSONObject(i);

			int pageId = jo.getInt("pageid");
			int articleSize = jo.getInt("size");
			int articleWordcount = jo.getInt("wordcount");
			String snipple = jo.getString("snippet");

			ArticleBean bean = new ArticleBean(pageId, articleSize, articleWordcount, snipple);
			articeList.add(bean);
		}
		return articeList;
	}
	
	public int getSmallestSize() {
		return smallestSize;
	}

	public void setSmallestSize(int smallestSize) {
		this.smallestSize = smallestSize;
	}

	public int getLargestSize() {
		return largestSize;
	}

	public void setLargestSize(int largestSize) {
		this.largestSize = largestSize;
	}

	public double getMedianSize() {
		return (totalArticlesCount != 0 ? totalWordcount / totalArticlesCount : 0);
	}

	public int getTotalWordcount() {
		return totalWordcount;
	}

	public void setTotalWordcount(int totalWordcount) {
		this.totalWordcount = totalWordcount;
	}

	public int getTotalArticles() {
		return totalArticlesCount;
	}

	public void setTotalArticles(int totalArticlesCount) {
		this.totalArticlesCount = totalArticlesCount;
	}

}