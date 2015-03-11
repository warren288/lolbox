package com.warren.lolbox.model.bean;

import java.util.List;

/**
 * 新闻专题
 * @author yangsheng
 * @date 2015年3月11日
 */
public class NewsTopic {
	private String id;
	private String title;
	private String photo;
	private String content;
	private List<NewsInfo> news;

	public NewsTopic() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the news
	 */
	public List<NewsInfo> getNews() {
		return news;
	}

	/**
	 * @param news the news to set
	 */
	public void setNews(List<NewsInfo> news) {
		this.news = news;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsTopic [id=" + id + ", title=" + title + ", photo=" + photo + ", content="
					+ content + ", news=" + news + "]";
	}
}
