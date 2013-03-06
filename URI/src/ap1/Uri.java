package ap1;

public class Uri
{ 
  private String host, path, queryString = "";
  short port = -1;

  private Uri() { }

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

  public String toString() {
    return java.text.MessageFormat.format(
      "http://{0}{1}{2}",
      host,
      path,
      queryString
    );
  }

  public static Uri createUri(String uriString) {
  	Uri uri = new Uri();

    uriString = consumeHttp(uriString, uri);
    if(uriString == null) return null;

    uriString = consumeHost(uriString, uri);
    if(uriString == null) return null;

    uriString = consumePath(uriString, uri);
    if(uriString == null) return null;

    return uri;
  }

  private static String consumeHttp(String uriString, Uri uri)
  {
    if (!uriString.startsWith("http://")) return null;
    return uriString.substring("http://".length());
  }

  private static String consumeHost(String uriString, Uri uri)
  {
    int slashIdx = uriString.indexOf("/");
    if(slashIdx == -1) return null;
    String host = uriString.substring(0, slashIdx);
    uri.host = host;
    return uriString.substring(slashIdx);
  }

  private static String consumePath(String uriString, Uri uri)
  {
    int questionMarkIdx = uriString.indexOf("?");

    String path = questionMarkIdx == -1 ? 
                      uriString : 
                      uriString.substring(0, questionMarkIdx);
    uri.path = path;
    return questionMarkIdx == -1 ?
              "" : 
              uriString.substring(0, questionMarkIdx);
  }

}