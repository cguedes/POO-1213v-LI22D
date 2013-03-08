package ap1;

public class TcpIpUriFactory
{ 
  
  public static Uri createUri(String uriString, Uri uri) {
    uriString = consumeSchema(uriString, uri, uri.getSchema());
    if(uriString == null) return null;

    uriString = consumeHost(uriString, uri);
    if(uriString == null) return null;

    if(uriString.startsWith(":")) {
      uriString = consumePort(uriString, uri);
      if(uriString == null) return null;
    }

    uriString = consumePath(uriString, uri);
    uriString = consumeQueryString(uriString, uri);
    uriString = consumeFragment(uriString, uri);

    return uri;

  }

  private static String getSchema(String uriString) 
  {
    int idx = uriString.indexOf(":");
    if(idx == -1) return null;
    return uriString.substring(0, idx);
  }

  private static String consumeSchema(String uriString, Uri uri, String schema)
  {
    schema = schema + "://";
    if (!uriString.startsWith(schema)) return null;
    return uriString.substring(schema.length());
  }

  private static String consumeHost(String uriString, Uri uri)
  {
    int portIdx = uriString.indexOf(":");
    int slashIdx = uriString.indexOf("/");
    int idx = portIdx > 0 ? portIdx : slashIdx;

    if(idx == -1) return null;
    uri.host = uriString.substring(0, idx);
    return uriString.substring(idx);
  }

  private static String consumePort(String uriString, Uri uri)
  {
    int idx = uriString.indexOf("/");
    if(idx == -1) return null;

    uri.port = Short.parseShort(uriString.substring(1, idx));
    return uriString.substring(idx);
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