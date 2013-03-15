package ap1;

public class Uri
{ 
  protected String schema;
  public String getSchema() { return schema; }

  public Uri(String schema) {
    this.schema = schema;
  }

  public static Uri createUri(String uriString) {
    String schema = getSchema(uriString);
    if(schema == null) return null;

    UriFactory uriFactory = getFactoryForSchema(schema);
    if(uriFactory == null) return null;
    Uri uri = uriFactory.createUri(uriString, schema);
    return uri;
  }

  private static int MAX_FACTORIES = 10;
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