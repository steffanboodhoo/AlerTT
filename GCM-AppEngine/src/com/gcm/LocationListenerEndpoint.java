package com.gcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


@Api(name = "locationlistenerendpoint", namespace = @ApiNamespace(ownerDomain = "gcm.com", ownerName = "gcm.com", packagePath = ""))
public class LocationListenerEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 * 
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */
	/*
	 * @SuppressWarnings({"unchecked", "unused"})
	 * 
	 * @ApiMethod(name = "listLocationListener") public
	 * CollectionResponse<LocationListener> listLocationListener(
	 * 
	 * @Nullable @Named("cursor") String cursorString,
	 * 
	 * @Nullable @Named("limit") Integer limit) {
	 * 
	 * EntityManager mgr = null; Cursor cursor = null; List<LocationListener>
	 * execute = null;
	 * 
	 * try{ mgr = getEntityManager(); Query query =
	 * mgr.createQuery("select from LocationListener as LocationListener"); if
	 * (cursorString != null && cursorString != "") { cursor =
	 * Cursor.fromWebSafeString(cursorString);
	 * query.setHint(JPACursorHelper.CURSOR_HINT, cursor); }
	 * 
	 * if (limit != null) { query.setFirstResult(0); query.setMaxResults(limit);
	 * }
	 * 
	 * execute = (List<LocationListener>) query.getResultList(); cursor =
	 * JPACursorHelper.getCursor(execute); if (cursor != null) cursorString =
	 * cursor.toWebSafeString();
	 * 
	 * // Tight loop for fetching all entities from datastore and accomodate //
	 * for lazy fetch. for (LocationListener obj : execute); } finally {
	 * mgr.close(); }
	 * 
	 * return CollectionResponse.<LocationListener>builder() .setItems(execute)
	 * .setNextPageToken(cursorString) .build(); }
	 */

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 * 
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getLocationListener")
	public LocationListener getLocationListener(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		LocationListener locationlistener = null;
		try {
			locationlistener = mgr.find(LocationListener.class, id);
		} finally {
			mgr.close();
		}
		return locationlistener;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 * 
	 * @param locationlistener
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */

	private double getDistance(double lat1, double long1, double lat2, double long2) {
		double R = 6378137; // Earth’s mean radius in meter
		double dLat = rad(lat2 - lat1);
		double dLong = rad(long2 - long1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(rad(lat1)) * Math.cos(rad(lat2))
				* Math.sin(dLong / 2) * Math.sin(dLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d; // returns the distance in meter
	}

	private double rad(double x) {
		return x * Math.PI / 180;
	}

	@ApiMethod(name = "insertLocationListener")
	public LocationListener insertLocationListener(
			LocationListener locationlistener) {
		EntityManager mgr = getEntityManager();
		try {
			/*
			 * if(containsLocationListener(locationlistener)) { throw new
			 * EntityExistsException("Object already exists"); }
			 */
			mgr.persist(locationlistener);
		} finally {
			mgr.close();
		}
		return locationlistener;
	}

	@ApiMethod(name = "alertPeople")
	public List<String> alertPeople(@Named("message") String message,
			@Named("subject") String subject,
			@Named("type") String type,
			@Named("latitude") Double latitude,
			@Named("longitude") Double longitude,
			@Named("time")Long time)
			{

		Sender sender = new Sender("AIzaSyAhnEXcjsgjpBdNr4w4G_Rl8HKtzlt1p8w");
		Message gcmMessage = new Message.Builder().addData("message", message)
				.addData("subject",subject)
				.addData("type",type)
				.addData("latitude", Double.toString(latitude))
				.addData("longitude", Double.toString(longitude))
				.addData("time", Long.toString(time)).build();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query getListeners = new Query("LocationListener");
		PreparedQuery fetchedDevices = datastore.prepare(getListeners);
		List<Entity> eDevices = fetchedDevices.asList(FetchOptions.Builder
				.withDefaults());
		List<String> Listeners = new ArrayList<String>();
		Iterator<Entity> i = eDevices.iterator();
		while (i.hasNext()) {
			Entity e = i.next();
			String id = (String) e.getProperty("deviceCode");
			double recLatitude= Double.parseDouble(""+e.getProperty("latitude"));
			double recLongitude= Double.parseDouble(""+e.getProperty("longitude"));		
			double distance=getDistance(recLatitude, recLongitude, latitude, longitude);
			System.out.println("*** Distance: "+distance);
			gcmMessage = new Message.Builder().addData("message", message)
			.addData("subject",subject)
			.addData("type",type)
			.addData("latitude", Double.toString(latitude))
			.addData("longitude", Double.toString(longitude))
			.addData("distance", Double.toString(distance))
			.addData("time", Long.toString(time)).build();
			try {
//				if(distance<=5000)
					sender.send(gcmMessage, id, 2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Listeners.add(id);
		}
		return Listeners;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does
	 * not exist in the datastore, an exception is thrown. It uses HTTP PUT
	 * method.
	 * 
	 * @param locationlistener
	 *            the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateLocationListener")
	public LocationListener updateLocationListener(
			LocationListener locationlistener) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsLocationListener(locationlistener)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(locationlistener);
		} finally {
			mgr.close();
		}
		return locationlistener;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 * 
	 * @param id
	 *            the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeLocationListener")
	public void removeLocationListener(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			LocationListener locationlistener = mgr.find(
					LocationListener.class, id);
			mgr.remove(locationlistener);
		} finally {
			mgr.close();
		}
	}

	private boolean containsLocationListener(LocationListener locationlistener) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			LocationListener item = mgr.find(LocationListener.class,
					locationlistener.getListenerId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
