package com.gcm;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int userId;
	private String account;
	private long lastLat;
	private long lastLong;
	private int posts;
	private int postsReported;

	public User(int userId, String account, long lastLat, long lastLong,
			int posts, int postsReported) {
		super();
		this.userId = userId;
		this.account = account;
		this.lastLat = lastLat;
		this.lastLong = lastLong;
		this.posts = posts;
		this.postsReported = postsReported;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getLastLat() {
		return lastLat;
	}

	public void setLastLat(long lastLat) {
		this.lastLat = lastLat;
	}

	public long getLastLong() {
		return lastLong;
	}

	public void setLastLong(long lastLong) {
		this.lastLong = lastLong;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public int getPostsReported() {
		return postsReported;
	}

	public void setPostsReported(int postsReported) {
		this.postsReported = postsReported;
	}

}
