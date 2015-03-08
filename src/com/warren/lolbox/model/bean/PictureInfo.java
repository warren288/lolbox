package com.warren.lolbox.model.bean;

/**
 * 资讯页中图片信息
 * @author yangsheng
 * @date 2015年3月7日
 */
public class PictureInfo {
	
	private String galleryId;
	private String title;
	private String description;
	private String coverUrl;
	private String coverWidth;
	private String coverHeight;
	private String created;
	private String updated;
	private String picsum;
	private String commentCount;
	private String clicks;
	private String destUrl;
	
	public PictureInfo() {
	}
	/**
	 * @return the galleryId
	 */
	public String getGalleryId() {
		return galleryId;
	}
	/**
	 * @param galleryId the galleryId to set
	 */
	public void setGalleryId(String galleryId) {
		this.galleryId = galleryId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the coverUrl
	 */
	public String getCoverUrl() {
		return coverUrl;
	}
	/**
	 * @param coverUrl the coverUrl to set
	 */
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	/**
	 * @return the coverWidth
	 */
	public String getCoverWidth() {
		return coverWidth;
	}
	/**
	 * @param coverWidth the coverWidth to set
	 */
	public void setCoverWidth(String coverWidth) {
		this.coverWidth = coverWidth;
	}
	/**
	 * @return the coverHeight
	 */
	public String getCoverHeight() {
		return coverHeight;
	}
	/**
	 * @param coverHeight the coverHeight to set
	 */
	public void setCoverHeight(String coverHeight) {
		this.coverHeight = coverHeight;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 * @return the updated
	 */
	public String getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	/**
	 * @return the picsum
	 */
	public String getPicsum() {
		return picsum;
	}
	/**
	 * @param picsum the picsum to set
	 */
	public void setPicsum(String picsum) {
		this.picsum = picsum;
	}
	/**
	 * @return the commentCount
	 */
	public String getCommentCount() {
		return commentCount;
	}
	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * @return the clicks
	 */
	public String getClicks() {
		return clicks;
	}
	/**
	 * @param clicks the clicks to set
	 */
	public void setClicks(String clicks) {
		this.clicks = clicks;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PictureInfo [galleryId=" + galleryId + ", title=" + title + ", description="
					+ description + ", coverUrl=" + coverUrl + ", coverWidth=" + coverWidth
					+ ", coverHeight=" + coverHeight + ", created=" + created + ", updated="
					+ updated + ", picsum=" + picsum + ", commentCount=" + commentCount
					+ ", clicks=" + clicks + ", destUrl=" + destUrl + "]";
	}
	
}
