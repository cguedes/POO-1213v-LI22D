package ap1;

public class UrlUri extends Uri
{ 
  private String host, path, queryString = "", fragment = "";
  private short port = -1;

  public UrlUri(String schema, String host, short port, String path, String queryString, String fragment) {
    super(schema);
    this.host = host;
    this.port = port;
    this.path = path;
    this.queryString = queryString;
    this.fragment = fragment;
  }

	public String getHost()        { return host;	}
  public short getPort()         { return port; }
  public String getPath()        { return path; }
  public String getQueryString() { return queryString; }
  public String getFragment()    { return fragment; }

  public String toString() {
    return java.text.MessageFormat.format(
      "{0}://{1}{2}{3}{4}",
      schema,
      host,
      path,
      queryString.length() == 0 ? "" : "?" + queryString,
      fragment.length() == 0 ? "" : "#" + fragment 
    );
  }


}