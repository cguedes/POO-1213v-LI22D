package ap1;

public class Uri
{ 
  private String host, path, queryString = "", fragment = "";
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
  public String getFragment() {
    return fragment;
  }

  public String toString() {
    return java.text.MessageFormat.format(
      "http://{0}{1}{2}{3}",
      host,
      path,
      queryString.length() == 0 ? "" : "?" + queryString,
      fragment.length() == 0 ? "" : "#" + fragment 
    );
  }

  public static Uri createUri(String uriString) {
  	Uri uri = new Uri();

    uriString = consumeHttp(uriString, uri);
    if(uriString == null) return null;

    uriString = consumeHost(uriString, uri);
    if(uriString == null) return null;

    uriString = consumePath(uriString, uri);
    uriString = consumeQueryString(uriString, uri);
    uriString = consumeFragment(uriString, uri);

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
              uriString.substring(questionMarkIdx);
  }

  /**
   * Receives: 
   * Receives: ?query=string&key=val&otherkey
   *                                         ^   
   *                                         |
   *                                        idx (-1)
   * uri.queryString = query=string&key=val&otherkey
   * Returns: 
   *
   * -- or --
   * 
   * Receives: ?query=string&key=val&otherkey#fragmentpart
   *                                         ^   
   *                                         |
   *                                        idx
   * uri.queryString = query=string&key=val&otherkey
   * Returns: #fragmentpart
   */
  private static String consumeQueryString(String uriString, Uri uri)
  {
    if(!uriString.startsWith("?")) {
      return uriString;
    }

    int idx = uriString.indexOf("#");
    uri.queryString = idx == -1 ? uriString.substring(1) : 
                                  uriString.substring(1, idx);
    return idx == -1 ? "" : 
                       uriString.substring(idx);
  }

  private static String consumeFragment(String uriString, Uri uri)
  {
    if(!uriString.startsWith("#")) {
      return uriString;
    }

    uri.fragment = uriString.substring(1);
    return "";
  }


}