package ap1;

public class GeoUri extends Uri {
  
  private float latitude, longitude;
  public GeoUri(float latitude, float longitude) {
    super("geo");
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public float getLatitude() { return latitude; }
  public float getLongitude() { return longitude; }

  public String toString() {
    return java.text.MessageFormat.format(
      "geo:{0}, {1}",
      latitude,
      longitude
    );
  }

}
