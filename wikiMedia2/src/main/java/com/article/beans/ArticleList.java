package main.java.com.article.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This will represents the JSON object that will be returned from the service.
 * Array list of "ArticleBean".
 *
 */
public class ArticleList {

	public static final String SEARCH = "search";

	
	@JsonProperty(SEARCH)
	private ArrayList<ArticleBean> articleList;


	public ArticleList(List<ArticleBean> articeList) {
		this.articleList = articleList;
	}

	/**
	 * get list of articles.
	 * @return
	 */
	@JsonProperty(SEARCH)
	public ArrayList<ArticleBean> getArticleList() {
		return articleList;
	}

	/**
	 * set list of articles
	 * @param articleList
	 */
	@JsonProperty(SEARCH)
	public void setArticleList(ArrayList<ArticleBean> articleList) {
		this.articleList = articleList;
	}
}
