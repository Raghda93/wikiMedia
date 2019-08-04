package main.java.com.article.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This bean used to hold data for articles retriever request.
 *
 */
public class ArticleBean {
	
	public static final String PAGE_ID = "pageid";
	public static final String SIZE = "size";
	public static final String WORD_COUNT = "wordcount";
	public static final String SNIPPET = "snippet";

	@JsonProperty(PAGE_ID)
	private int pageId;

	@JsonProperty(SIZE)
	private int size;

	@JsonProperty(WORD_COUNT)
	private int wordCount;

	@JsonProperty(SNIPPET)
	private String snippet;

	public ArticleBean(int pageId, int size, int wordCount, String snippet) {
		super();
		this.pageId = pageId;
		this.size = size;
		this.wordCount = wordCount;
		this.snippet = snippet;
	}

	@JsonProperty(PAGE_ID)
	public int getPageId() {
		return pageId;
	}

	@JsonProperty(SIZE)
	public int getSize() {
		return size;
	}

	@JsonProperty(WORD_COUNT)
	public int getWordCount() {
		return wordCount;
	}

	@JsonProperty(SNIPPET)
	public String getSnippet() {
		return snippet;
	}
}
