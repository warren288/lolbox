package com.warren.lolbox.model.bean;


public class NewsTopicOuter {
	private String type;
	private String topicId;
	private String title;
	private NewsTopic data;
	public NewsTopicOuter() {
		super();
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the topicId
	 */
	public String getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
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
	 * @return the data
	 */
	public NewsTopic getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(NewsTopic data) {
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsTopicOuter [type=" + type + ", topicId=" + topicId + ", title=" + title
					+ ", data=" + data + "]";
	}
}
