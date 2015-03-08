package com.warren.lolbox.model.bean;

/**
 * 资讯页中新闻信息
 * @author yangsheng
 * @date 2015年3月7日
 */
public class NewsInfo {
	private String id;
	private String title;
	private String content;
	private String weight;
	private String time;
	private String readCount;
	private String photo;
	private String artId;
	private int commentSum;
	private String commentUrl;
	private int hasVideo;
	private String destUrl;
	private String type;
	private String ymz_id;
	private String comment_id;

	public NewsInfo() {
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
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the readCount
	 */
	public String getReadCount() {
		return readCount;
	}

	/**
	 * @param readCount the readCount to set
	 */
	public void setReadCount(String readCount) {
		this.readCount = readCount;
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
	 * @return the artId
	 */
	public String getArtId() {
		return artId;
	}

	/**
	 * @param artId the artId to set
	 */
	public void setArtId(String artId) {
		this.artId = artId;
	}

	/**
	 * @return the commentSum
	 */
	public int getCommentSum() {
		return commentSum;
	}

	/**
	 * @param commentSum the commentSum to set
	 */
	public void setCommentSum(int commentSum) {
		this.commentSum = commentSum;
	}

	/**
	 * @return the commentUrl
	 */
	public String getCommentUrl() {
		return commentUrl;
	}

	/**
	 * @param commentUrl the commentUrl to set
	 */
	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}

	/**
	 * @return the hasVideo
	 */
	public int getHasVideo() {
		return hasVideo;
	}

	/**
	 * @param hasVideo the hasVideo to set
	 */
	public void setHasVideo(int hasVideo) {
		this.hasVideo = hasVideo;
	}

	/**
	 * @return the destUrl
	 */
	public String getDestUrl() {
		return destUrl;
	}

	/**
	 * @param destUrl the destUrl to set
	 */
	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
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
	 * @return the ymz_id
	 */
	public String getYmz_id() {
		return ymz_id;
	}

	/**
	 * @param ymz_id the ymz_id to set
	 */
	public void setYmz_id(String ymz_id) {
		this.ymz_id = ymz_id;
	}

	/**
	 * @return the comment_id
	 */
	public String getComment_id() {
		return comment_id;
	}

	/**
	 * @param comment_id the comment_id to set
	 */
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsInfo [id=" + id + ", title=" + title + ", content=" + content + ", weight="
					+ weight + ", time=" + time + ", readCount=" + readCount + ", photo=" + photo
					+ ", artId=" + artId + ", commentSum=" + commentSum + ", commentUrl="
					+ commentUrl + ", hasVideo=" + hasVideo + ", destUrl=" + destUrl + ", type="
					+ type + ", ymz_id=" + ymz_id + ", comment_id=" + comment_id + "]";
	}

}
