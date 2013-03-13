package ap1;

public class MailToUri extends Uri
{ 
  private String user, domain;
  public MailToUri(String user, String domain) {
    super("mailto");
    this.user = user;
    this.domain = domain;
  }
  public String getUser()   { return user; }
  public String getDomain() { return domain; } 

  public String toString() {
    return java.text.MessageFormat.format(
      "mailto:{0}@{1}",
      user,
      domain
    );
  }

}