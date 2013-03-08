package ap1;

public class MailToUriFactory
{ 
  private String user, domain;
  public Uri createUri(String uriString, String schema) {
    
    uriString = consumeSchema(uriString);
    if(uriString == null) return null;

    uriString = consumeUser(uriString);
    if(uriString == null) return null;

    uriString = consumeDomain(uriString);
    return new Uri(schema, user, domain);
  }

  private String consumeSchema(String uriString) {
    if(!uriString.startsWith("mailto:")) return null;
    return uriString.substring("mailto:".length());
  }

  private String consumeUser(String uriString) {
    int idx = uriString.indexOf("@");
    if(idx == -1) return null;
    user = uriString.substring(0, idx);
    return uriString.substring(idx + 1);
  }

  private String consumeDomain(String uriString) {
    domain = uriString;
    return "";
  }

}