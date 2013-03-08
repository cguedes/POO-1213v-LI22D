package ap1;

public class TcpIpUriFactory
{ 
  private String schema, host, path, queryString = "", fragment = "";
  private short port = -1;

  public Uri createUri(String uriString, String schema) 
  {
    uriString = consumeSchema(uriString, schema);
    if(uriString == null) return null;

    uriString = consumeHost(uriString);
    if(uriString == null) return null;

    if(uriString.startsWith(":")) {
      uriString = consumePort(uriString);
      if(uriString == null) return null;
    }

    uriString = consumePath(uriString);
    uriString = consumeQueryString(uriString);
    uriString = consumeFragment(uriString);

    return new Uri(schema, host, port, path, queryString, fragment);
  }

  private String consumeSchema(String uriString, String schema)
  {
    schema = schema + "://";
    if (!uriString.startsWith(schema)) return null;
    return uriString.substring(schema.length());
  }

  private String consumeHost(String uriString)
  {
    int portIdx = uriString.indexOf(":");
    int slashIdx = uriString.indexOf("/");
    int idx = portIdx > 0 ? portIdx : slashIdx;

    if(idx == -1) return null;
    host = uriString.substring(0, idx);
    return uriString.substring(idx);
  }

  private String consumePort(String uriString)
  {
    int idx = uriString.indexOf("/");
    if(idx == -1) return null;

    port = Short.parseShort(uriString.substring(1, idx));
    return uriString.substring(idx);
  }

  private String consumePath(String uriString)
  {
    int idx = uriString.indexOf("?");
    path = idx == -1 ? uriString : uriString.substring(0, idx);
    return idx == -1 ? "" : uriString.substring(idx);
  }

  /**
   * Receives: 
   * Receives: ?query=string&key=val&otherkey
   *                                         ^   
   *                                         |
   *                                        idx (-1)
   * queryString = query=string&key=val&otherkey
   * Returns: 
   *
   * -- or --
   * 
   * Receives: ?query=string&key=val&otherkey#fragmentpart
   *                                         ^   
   *                                         |
   *                                        idx
   * queryString = query=string&key=val&otherkey
   * Returns: #fragmentpart
   */
  private String consumeQueryString(String uriString)
  {
    if(!uriString.startsWith("?")) {
      return uriString;
    }

    int idx = uriString.indexOf("#");
    queryString = idx == -1 ? uriString.substring(1) : 
                                  uriString.substring(1, idx);
    return idx == -1 ? "" : 
                       uriString.substring(idx);
  }

  private String consumeFragment(String uriString)
  {
    if(!uriString.startsWith("#")) {
      return uriString;
    }

    fragment = uriString.substring(1);
    return "";
  }


}