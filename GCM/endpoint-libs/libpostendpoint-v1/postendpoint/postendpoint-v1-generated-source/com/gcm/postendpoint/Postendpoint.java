/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-10-16 at 06:19:55 UTC 
 * Modify at your own risk.
 */

package com.gcm.postendpoint;

/**
 * Service definition for Postendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link PostendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Postendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.18.0-rc of the postendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://sinuous-moment-658.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "postendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Postendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Postendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "filterPosts".
   *
   * This request holds the parameters needed by the postendpoint server.  After setting any optional
   * parameters, call the {@link FilterPosts#execute()} method to invoke the remote operation.
   *
   * @param timeCreatedStart
   * @param timeCreatedEnd
   * @param type
   * @return the request
   */
  public FilterPosts filterPosts(java.lang.String timeCreatedStart, java.lang.String timeCreatedEnd, java.lang.String type) throws java.io.IOException {
    FilterPosts result = new FilterPosts(timeCreatedStart, timeCreatedEnd, type);
    initialize(result);
    return result;
  }

  public class FilterPosts extends PostendpointRequest<com.gcm.postendpoint.model.CollectionResponsePost> {

    private static final String REST_PATH = "filterPosts/{timeCreated_start}/{timeCreated_end}/{type}";

    /**
     * Create a request for the method "filterPosts".
     *
     * This request holds the parameters needed by the the postendpoint server.  After setting any
     * optional parameters, call the {@link FilterPosts#execute()} method to invoke the remote
     * operation. <p> {@link
     * FilterPosts#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param timeCreatedStart
     * @param timeCreatedEnd
     * @param type
     * @since 1.13
     */
    protected FilterPosts(java.lang.String timeCreatedStart, java.lang.String timeCreatedEnd, java.lang.String type) {
      super(Postendpoint.this, "POST", REST_PATH, null, com.gcm.postendpoint.model.CollectionResponsePost.class);
      this.timeCreatedStart = com.google.api.client.util.Preconditions.checkNotNull(timeCreatedStart, "Required parameter timeCreatedStart must be specified.");
      this.timeCreatedEnd = com.google.api.client.util.Preconditions.checkNotNull(timeCreatedEnd, "Required parameter timeCreatedEnd must be specified.");
      this.type = com.google.api.client.util.Preconditions.checkNotNull(type, "Required parameter type must be specified.");
    }

    @Override
    public FilterPosts setAlt(java.lang.String alt) {
      return (FilterPosts) super.setAlt(alt);
    }

    @Override
    public FilterPosts setFields(java.lang.String fields) {
      return (FilterPosts) super.setFields(fields);
    }

    @Override
    public FilterPosts setKey(java.lang.String key) {
      return (FilterPosts) super.setKey(key);
    }

    @Override
    public FilterPosts setOauthToken(java.lang.String oauthToken) {
      return (FilterPosts) super.setOauthToken(oauthToken);
    }

    @Override
    public FilterPosts setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (FilterPosts) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public FilterPosts setQuotaUser(java.lang.String quotaUser) {
      return (FilterPosts) super.setQuotaUser(quotaUser);
    }

    @Override
    public FilterPosts setUserIp(java.lang.String userIp) {
      return (FilterPosts) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key("timeCreated_start")
    private java.lang.String timeCreatedStart;

    /**

     */
    public java.lang.String getTimeCreatedStart() {
      return timeCreatedStart;
    }

    public FilterPosts setTimeCreatedStart(java.lang.String timeCreatedStart) {
      this.timeCreatedStart = timeCreatedStart;
      return this;
    }

    @com.google.api.client.util.Key("timeCreated_end")
    private java.lang.String timeCreatedEnd;

    /**

     */
    public java.lang.String getTimeCreatedEnd() {
      return timeCreatedEnd;
    }

    public FilterPosts setTimeCreatedEnd(java.lang.String timeCreatedEnd) {
      this.timeCreatedEnd = timeCreatedEnd;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String type;

    /**

     */
    public java.lang.String getType() {
      return type;
    }

    public FilterPosts setType(java.lang.String type) {
      this.type = type;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String longitude;

    /**

     */
    public java.lang.String getLongitude() {
      return longitude;
    }

    public FilterPosts setLongitude(java.lang.String longitude) {
      this.longitude = longitude;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public FilterPosts setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public FilterPosts setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String latitude;

    /**

     */
    public java.lang.String getLatitude() {
      return latitude;
    }

    public FilterPosts setLatitude(java.lang.String latitude) {
      this.latitude = latitude;
      return this;
    }

    @Override
    public FilterPosts set(String parameterName, Object value) {
      return (FilterPosts) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getPost".
   *
   * This request holds the parameters needed by the postendpoint server.  After setting any optional
   * parameters, call the {@link GetPost#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetPost getPost(java.lang.Long id) throws java.io.IOException {
    GetPost result = new GetPost(id);
    initialize(result);
    return result;
  }

  public class GetPost extends PostendpointRequest<com.gcm.postendpoint.model.Post> {

    private static final String REST_PATH = "post/{id}";

    /**
     * Create a request for the method "getPost".
     *
     * This request holds the parameters needed by the the postendpoint server.  After setting any
     * optional parameters, call the {@link GetPost#execute()} method to invoke the remote operation.
     * <p> {@link
     * GetPost#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetPost(java.lang.Long id) {
      super(Postendpoint.this, "GET", REST_PATH, null, com.gcm.postendpoint.model.Post.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetPost setAlt(java.lang.String alt) {
      return (GetPost) super.setAlt(alt);
    }

    @Override
    public GetPost setFields(java.lang.String fields) {
      return (GetPost) super.setFields(fields);
    }

    @Override
    public GetPost setKey(java.lang.String key) {
      return (GetPost) super.setKey(key);
    }

    @Override
    public GetPost setOauthToken(java.lang.String oauthToken) {
      return (GetPost) super.setOauthToken(oauthToken);
    }

    @Override
    public GetPost setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetPost) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetPost setQuotaUser(java.lang.String quotaUser) {
      return (GetPost) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetPost setUserIp(java.lang.String userIp) {
      return (GetPost) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetPost setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetPost set(String parameterName, Object value) {
      return (GetPost) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertPost".
   *
   * This request holds the parameters needed by the postendpoint server.  After setting any optional
   * parameters, call the {@link InsertPost#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.gcm.postendpoint.model.Post}
   * @return the request
   */
  public InsertPost insertPost(com.gcm.postendpoint.model.Post content) throws java.io.IOException {
    InsertPost result = new InsertPost(content);
    initialize(result);
    return result;
  }

  public class InsertPost extends PostendpointRequest<com.gcm.postendpoint.model.Post> {

    private static final String REST_PATH = "post";

    /**
     * Create a request for the method "insertPost".
     *
     * This request holds the parameters needed by the the postendpoint server.  After setting any
     * optional parameters, call the {@link InsertPost#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertPost#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.gcm.postendpoint.model.Post}
     * @since 1.13
     */
    protected InsertPost(com.gcm.postendpoint.model.Post content) {
      super(Postendpoint.this, "POST", REST_PATH, content, com.gcm.postendpoint.model.Post.class);
    }

    @Override
    public InsertPost setAlt(java.lang.String alt) {
      return (InsertPost) super.setAlt(alt);
    }

    @Override
    public InsertPost setFields(java.lang.String fields) {
      return (InsertPost) super.setFields(fields);
    }

    @Override
    public InsertPost setKey(java.lang.String key) {
      return (InsertPost) super.setKey(key);
    }

    @Override
    public InsertPost setOauthToken(java.lang.String oauthToken) {
      return (InsertPost) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertPost setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertPost) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertPost setQuotaUser(java.lang.String quotaUser) {
      return (InsertPost) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertPost setUserIp(java.lang.String userIp) {
      return (InsertPost) super.setUserIp(userIp);
    }

    @Override
    public InsertPost set(String parameterName, Object value) {
      return (InsertPost) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listPost".
   *
   * This request holds the parameters needed by the postendpoint server.  After setting any optional
   * parameters, call the {@link ListPost#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListPost listPost() throws java.io.IOException {
    ListPost result = new ListPost();
    initialize(result);
    return result;
  }

  public class ListPost extends PostendpointRequest<com.gcm.postendpoint.model.CollectionResponsePost> {

    private static final String REST_PATH = "post";

    /**
     * Create a request for the method "listPost".
     *
     * This request holds the parameters needed by the the postendpoint server.  After setting any
     * optional parameters, call the {@link ListPost#execute()} method to invoke the remote operation.
     * <p> {@link
     * ListPost#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListPost() {
      super(Postendpoint.this, "GET", REST_PATH, null, com.gcm.postendpoint.model.CollectionResponsePost.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListPost setAlt(java.lang.String alt) {
      return (ListPost) super.setAlt(alt);
    }

    @Override
    public ListPost setFields(java.lang.String fields) {
      return (ListPost) super.setFields(fields);
    }

    @Override
    public ListPost setKey(java.lang.String key) {
      return (ListPost) super.setKey(key);
    }

    @Override
    public ListPost setOauthToken(java.lang.String oauthToken) {
      return (ListPost) super.setOauthToken(oauthToken);
    }

    @Override
    public ListPost setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListPost) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListPost setQuotaUser(java.lang.String quotaUser) {
      return (ListPost) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListPost setUserIp(java.lang.String userIp) {
      return (ListPost) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListPost setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListPost setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListPost set(String parameterName, Object value) {
      return (ListPost) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removePost".
   *
   * This request holds the parameters needed by the postendpoint server.  After setting any optional
   * parameters, call the {@link RemovePost#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemovePost removePost(java.lang.Long id) throws java.io.IOException {
    RemovePost result = new RemovePost(id);
    initialize(result);
    return result;
  }

  public class RemovePost extends PostendpointRequest<Void> {

    private static final String REST_PATH = "post/{id}";

    /**
     * Create a request for the method "removePost".
     *
     * This request holds the parameters needed by the the postendpoint server.  After setting any
     * optional parameters, call the {@link RemovePost#execute()} method to invoke the remote
     * operation. <p> {@link
     * RemovePost#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemovePost(java.lang.Long id) {
      super(Postendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemovePost setAlt(java.lang.String alt) {
      return (RemovePost) super.setAlt(alt);
    }

    @Override
    public RemovePost setFields(java.lang.String fields) {
      return (RemovePost) super.setFields(fields);
    }

    @Override
    public RemovePost setKey(java.lang.String key) {
      return (RemovePost) super.setKey(key);
    }

    @Override
    public RemovePost setOauthToken(java.lang.String oauthToken) {
      return (RemovePost) super.setOauthToken(oauthToken);
    }

    @Override
    public RemovePost setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemovePost) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemovePost setQuotaUser(java.lang.String quotaUser) {
      return (RemovePost) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemovePost setUserIp(java.lang.String userIp) {
      return (RemovePost) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemovePost setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemovePost set(String parameterName, Object value) {
      return (RemovePost) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updatePost".
   *
   * This request holds the parameters needed by the postendpoint server.  After setting any optional
   * parameters, call the {@link UpdatePost#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.gcm.postendpoint.model.Post}
   * @return the request
   */
  public UpdatePost updatePost(com.gcm.postendpoint.model.Post content) throws java.io.IOException {
    UpdatePost result = new UpdatePost(content);
    initialize(result);
    return result;
  }

  public class UpdatePost extends PostendpointRequest<com.gcm.postendpoint.model.Post> {

    private static final String REST_PATH = "post";

    /**
     * Create a request for the method "updatePost".
     *
     * This request holds the parameters needed by the the postendpoint server.  After setting any
     * optional parameters, call the {@link UpdatePost#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdatePost#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.gcm.postendpoint.model.Post}
     * @since 1.13
     */
    protected UpdatePost(com.gcm.postendpoint.model.Post content) {
      super(Postendpoint.this, "PUT", REST_PATH, content, com.gcm.postendpoint.model.Post.class);
    }

    @Override
    public UpdatePost setAlt(java.lang.String alt) {
      return (UpdatePost) super.setAlt(alt);
    }

    @Override
    public UpdatePost setFields(java.lang.String fields) {
      return (UpdatePost) super.setFields(fields);
    }

    @Override
    public UpdatePost setKey(java.lang.String key) {
      return (UpdatePost) super.setKey(key);
    }

    @Override
    public UpdatePost setOauthToken(java.lang.String oauthToken) {
      return (UpdatePost) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdatePost setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdatePost) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdatePost setQuotaUser(java.lang.String quotaUser) {
      return (UpdatePost) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdatePost setUserIp(java.lang.String userIp) {
      return (UpdatePost) super.setUserIp(userIp);
    }

    @Override
    public UpdatePost set(String parameterName, Object value) {
      return (UpdatePost) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Postendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Postendpoint}. */
    @Override
    public Postendpoint build() {
      return new Postendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link PostendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setPostendpointRequestInitializer(
        PostendpointRequestInitializer postendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(postendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}