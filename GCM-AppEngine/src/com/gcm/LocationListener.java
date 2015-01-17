package com.gcm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class LocationListener {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key listenerId;
	private String deviceCode;
	private String userId;
	private double latitude;
	private double longitude;
	private int distance;

//	public LocationListener(String deviceCode, String userId, int latitude,
//			int longitude) {
//		super();
//		this.deviceCode = deviceCode;
//		this.userId = userId;
//		this.latitude = latitude;
//		this.longitude = longitude;
//	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Key getListenerId() {
		return listenerId;
	}

	public void setListenerId(Key listenerId) {
		this.listenerId = listenerId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
