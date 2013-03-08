package ap1;

public class Uri
{ 
  private String schema, host, path, queryString = "", fragment = "";
  private short port = -1;

  private String user, domain;

  private Uri() { }

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
  public String getUser() {
    return user;
  }
  public String getDomain() {
    return domain;
  } 

  public String toString() {
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
  	Uri uri = new Uri();

    String schema = getSchema(uriString);
    if(schema == null) return null;
    uri.schema = schema;

    if(schema.equals("mailto")) {
      MailToUriFactory factory = new MailToUriFactory();
      return factory.createUri(uriString, uri);
    }
    TcpIpUriFactory factory = new TcpIpUriFactory();
    return factory.createUri(uriString, uri);
  }

}