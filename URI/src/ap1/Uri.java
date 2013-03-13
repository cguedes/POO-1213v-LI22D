package ap1;

public class Uri
{ 
  private String schema, host, path, queryString = "", fragment = "";
  private short port = -1;

  private Uri() { }

  public Uri(String schema, String host, short port, String path, String queryString, String fragment) {
    this.schema = schema;
    this.host = host;
    this.port = port;
    this.path = path;
    this.queryString = queryString;
    this.fragment = fragment;
  }



  public String getSchema() {
    return schema;
  }

	public String getHost() {
		return host;
	}
  public short getPort() {
  	return port;
  }
  public String getPath() {
  	return path;
  }
  public String getQueryString() {
    return queryString;
  }
  public String getFragment() {
    return fragment;
  }

  // data for mailto schema
  private String user, domain;
  public Uri(String schema, String user, String domain) {
    this.schema = schema;
    this.user = user;
    this.domain = domain;
  }
  public String getUser() {
    return user;
  }
  public String getDomain() {
    return domain;
  } 

  public String toString() {
    System.out.println(schema);
    if(schema.equals("mailto")) {
      return java.text.MessageFormat.format(
        "mailto:{0}@{1}",
        user,
        domain
      );
    }
    return java.text.MessageFormat.format(
      "{0}://{1}{2}{3}{4}",
      schema,
      host,
      path,
      queryString.length() == 0 ? "" : "?" + queryString,
      fragment.length() == 0 ? "" : "#" + fragment 
    );
  }

  public static Uri createUri(String uriString) {
    String schema = getSchema(uriString);
    if(schema == null) return null;

    UriFactory uriFactory = getFactoryForSchema(schema);
    if(uriFactory == null) return null;
    Uri uri = uriFactory.createUri(uriString, schema);
    return uri;
  }

  private static UriFactory mailToFactory = new MailToUriFactory();
  private static UriFactory tcpIpFactory  = new TcpIpUriFactory();
  private static UriFactory geoFactory    = new GeoUriFactory();

  private static int MAX_FACTORIES = 2;
  private static int numFactories = 0;
  private static UriFactory[] uriFactories = new UriFactory[MAX_FACTORIES];
  public static boolean addUriFactory(UriFactory factory) {
    if(numFactories == MAX_FACTORIES) return false;
    uriFactories[numFactories++] = factory;
    return true;
  }

  private static UriFactory getFactoryForSchema(String schema)
  {
    for (int i = 0; i < uriFactories.length; ++i) {
      UriFactory factory = uriFactories[i];
      if(factory == null) break;

      if(factory.canCreate(schema))
        return factory;
    }
    return null;
  }

  private static String getSchema(String uriString) 
  {
    int idx = uriString.indexOf(":");
    if(idx == -1) return null;
    return uriString.substring(0, idx);
  }



}