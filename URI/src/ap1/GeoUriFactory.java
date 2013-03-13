package ap1;

public class GeoUriFactory implements UriFactory
{ 

  public boolean canCreate(String schema) {
    return schema.equals("geo");
  }

  public Uri createUri(String uriString, String schema) {
    return null;    
  }

}