package ap1;

public class GeoUriFactory implements UriFactory
{ 
  public boolean canCreate(String schema) {
    return schema.equals("geo");
  }

  public Uri createUri(String uriString, String schema) {
    
    uriString = consumeSchema(uriString, schema);
    if(uriString == null) return null;

    String[] parts = uriString.split(",");
    if(parts.length != 2) return null;

    float latitude  = Float.parseFloat(parts[0]);
    float longitude = Float.parseFloat(parts[1]);

    return new Uri("geo", latitude, longitude);
  }


  private String consumeSchema(String uriString, String schema) {
    if(!uriString.startsWith(schema + ":")) return null;
    return uriString.substring((schema + ":").length());
  }  

}