package ap1;

public interface UriFactory 
{ 
  boolean canCreate(String schema);
  Uri createUri(String uriString, String schema);
}
