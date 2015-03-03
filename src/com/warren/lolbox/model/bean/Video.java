package com.warren.lolbox.model.bean;

public class Video {
	private String video_id;
	private String title;
	private String cover_url;
	private String introduction;
	private String video_length;
	private String upload_time;
	private String channelId;
	private String udb;
	private String editorId;
	private String amount_play;
	private String letv_video_id;
	private String letv_video_unique;
	private int totlaPage;

	/**
	 * @return the video_id
	 */
	public String getVideo_id() {
		return video_id;
	}

	/**
	 * @param video_id the video_id to set
	 */
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
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
	 * @return the cover_url
	 */
	public String getCover_url() {
		return cover_url;
	}

	/**
	 * @param cover_url the cover_url to set
	 */
	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the video_length
	 */
	public String getVideo_length() {
		return video_length;
	}

	/**
	 * @param video_length the video_length to set
	 */
	public void setVideo_length(String video_length) {
		this.video_length = video_length;
	}

	/**
	 * @return the upload_time
	 */
	public String getUpload_time() {
		return upload_time;
	}

	/**
	 * @param upload_time the upload_time to set
	 */
	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the udb
	 */
	public String getUdb() {
		return udb;
	}

	/**
	 * @param udb the udb to set
	 */
	public void setUdb(String udb) {
		this.udb = udb;
	}

	/**
	 * @return the editorId
	 */
	public String getEditorId() {
		return editorId;
	}

	/**
	 * @param editorId the editorId to set
	 */
	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

	/**
	 * @return the amount_play
	 */
	public String getAmount_play() {
		return amount_play;
	}

	/**
	 * @param amount_play the amount_play to set
	 */
	public void setAmount_play(String amount_play) {
		this.amount_play = amount_play;
	}

	/**
	 * @return the letv_video_id
	 */
	public String getLetv_video_id() {
		return letv_video_id;
	}

	/**
	 * @param letv_video_id the letv_video_id to set
	 */
	public void setLetv_video_id(String letv_video_id) {
		this.letv_video_id = letv_video_id;
	}

	/**
	 * @return the letv_video_unique
	 */
	public String getLetv_video_unique() {
		return letv_video_unique;
	}

	/**
	 * @param letv_video_unique the letv_video_unique to set
	 */
	public void setLetv_video_unique(String letv_video_unique) {
		this.letv_video_unique = letv_video_unique;
	}

	/**
	 * @return the totlaPage
	 */
	public int getTotlaPage() {
		return totlaPage;
	}

	/**
	 * @param totlaPage the totlaPage to set
	 */
	public void setTotlaPage(int totlaPage) {
		this.totlaPage = totlaPage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Video [video_id=" + video_id + ", title=" + title + ", cover_url=" + cover_url
					+ ", introduction=" + introduction + ", video_length=" + video_length
					+ ", upload_time=" + upload_time + ", channelId=" + channelId + ", udb=" + udb
					+ ", editorId=" + editorId + ", amount_play=" + amount_play
					+ ", letv_video_id=" + letv_video_id + ", letv_video_unique="
					+ letv_video_unique + ", totlaPage=" + totlaPage + "]";
	}
}
