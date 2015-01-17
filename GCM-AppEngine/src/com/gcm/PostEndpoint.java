package com.gcm;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

@Api(name = "postendpoint", namespace = @ApiNamespace(ownerDomain = "gcm.com", ownerName = "gcm.com", packagePath = ""))
public class PostEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 * 
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listPost")
	public CollectionResponse<Post> listPost(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Post> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Post as Post");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Post>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and
			// accomodate
			// for lazy fetch.
			for (Post obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Post> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 * 
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getPost")
	public Post getPost(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Post post = null;
		try {
			post = mgr.find(Post.class, id);
		} finally {
			mgr.close();
		}
		return post;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 * 
	 * @param post
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertPost")
	public Post insertPost(Post post) {
		EntityManager mgr = getEntityManager();
		// Key k=
		try {
			/*
			 * if(containsPost(post)) { throw new
			 * EntityExistsException("Object already exists"); }
			 */
			mgr.persist(post);
		} finally {
			mgr.close();
		}
		return post;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does
	 * not exist in the datastore, an exception is thrown. It uses HTTP PUT
	 * method.
	 * 
	 * @param post
	 *            the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updatePost")
	public Post updatePost(Post post) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsPost(post)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(post);
		} finally {
			mgr.close();
		}
		return post;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 * 
	 * @param id
	 *            the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removePost")
	public void removePost(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Post post = mgr.find(Post.class, id);
			mgr.remove(post);
		} finally {
			mgr.close();
		}
	}

	private boolean containsPost(Post post) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Post item = mgr.find(Post.class, post.getKey());
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

	/* User Defined Endpoint methods */

	/**
	 * This method lists all the entities inserted in datastore that matches the
	 * given filters. It uses HTTP GET method and paging support.
	 * 
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "filterPosts")
	public CollectionResponse<Post> filterPosts(
			@Named("timeCreated_start") String timeCreated_start,
			@Named("timeCreated_end") String timeCreated_end,
			@Named("type") String type,
			@Nullable @Named("latitude") String latitude,
			@Nullable @Named("longitude") String longitude,
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Post> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("SELECT FROM Post AS Post WHERE timeCreated >= :param1 AND timeCreated <= :param2 AND type =:param3 ORDER BY timeCreated ASC");
			query.setParameter("param1", Long.parseLong(timeCreated_start));
			query.setParameter("param2", Long.parseLong(timeCreated_end));
			query.setParameter("param3", type);
			System.out.print(timeCreated_end);

			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Post>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and
			// accomodate
			// for lazy fetch.
			for (Post obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Post> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

}
