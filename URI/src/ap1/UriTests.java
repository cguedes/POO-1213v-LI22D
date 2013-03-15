package ap1;

public class UriTests {

	public static void should_fail_if_asserts_are_enabled() {
	
		boolean condition = false;
		assert condition; // O programa deve parar com excepção "AssertionError" neste ponto
	}

	public static void should_not_parse_malformed_host() {
		Uri malformedUri = Uri.createUri("httxp://www.isel");
		assert malformedUri == null;
	}
	
	public static void should_parse_uri_with_host_and_default_path() {
		Uri iselUri = Uri.createUri("http://www.isel.pt/");
		assert iselUri != null;	// assertNotNull(iselUri);
		UrlUri url = (UrlUri)iselUri;
		assertEquals(url.getHost(), "www.isel.pt");
		assertEquals(url.getPath(), "/");
		assertEquals(url.getPort(), (short)-1);
		assertEquals(url.getQueryString(), "");

		System.out.println("URL: " + iselUri.toString());
	}

	public static void should_parse_uri_with_host_and_path_without_querystring() {
		Uri iselUri = Uri.createUri("http://www.isel.pt/alunos");
		assert iselUri != null;
		UrlUri url = (UrlUri)iselUri;
		assertEquals(url.getHost(), "www.isel.pt");
		assertEquals(url.getPath(), "/alunos");
		assertEquals(url.getPort(), (short)-1);
		assertEquals(url.getQueryString(), "");

		System.out.println("URL: " + iselUri.toString());
	}

	public static void should_parse_uri_with_host_and_path_with_querystring() {
		Uri iselUri = Uri.createUri("http://www.isel.pt/alunos?num=22");
		assert iselUri != null;
		UrlUri url = (UrlUri)iselUri;
		assertEquals(url.getHost(), "www.isel.pt");
		assertEquals(url.getPath(), "/alunos");
		assertEquals(url.getPort(), (short)-1);
		assertEquals(url.getQueryString(), "num=22");

		System.out.println("URL: " + iselUri.toString());
	}

	public static void should_parse_uri_with_fragment() {
		Uri iselUri = Uri.createUri("http://www.isel.pt/alunos?num=22#fragment");
		assert iselUri != null;
		UrlUri url = (UrlUri)iselUri;
		assertEquals(url.getHost(), "www.isel.pt");
		assertEquals(url.getPath(), "/alunos");
		assertEquals(url.getPort(), (short)-1);
		assertEquals(url.getQueryString(), "num=22");
		assertEquals(url.getFragment(), "fragment");

		System.out.println("URL: " + iselUri.toString());
	}

	public static void should_display_uri_with_fragment()
	{
		String uriText = "http://www.isel.pt/alunos?num=22#fragment";
		Uri iselUri = Uri.createUri(uriText);
		assertEquals(iselUri.toString(), uriText);
	}

	public static void should_parse_uri_with_port()
	{
		Uri iselUri = Uri.createUri("http://www.isel.pt:8080/alunos");
		UrlUri url = (UrlUri)iselUri;
		assertEquals(url.getHost(), "www.isel.pt");
		assertEquals(url.getPort(), (short)8080);
		assertEquals(url.getPath(), "/alunos");
		System.out.println("URL: " + iselUri.toString());
	}	

	public static void should_parse_ftp_uri() {
		Uri iselUri = Uri.createUri("ftp://www.isel.pt/software");
		assert iselUri != null;
		UrlUri url = (UrlUri)iselUri;
		assertEquals(url.getHost(), "www.isel.pt");
		assertEquals(url.getPort(), (short)-1);
		assertEquals(url.getPath(), "/software");
		System.out.println("URL: " + iselUri.toString());
	}

	public static void should_parse_mailto_uri() {
		Uri mailtoUri = Uri.createUri("mailto:cguedes@cc.isel.ipl.pt");
		assert mailtoUri != null;
		MailToUri mailto = (MailToUri)mailtoUri;
		assertEquals(mailto.getUser(), "cguedes");
		assertEquals(mailto.getDomain(), "cc.isel.ipl.pt");
		System.out.println("URL: " + mailtoUri.toString());
	}

	public static void should_parse_geo_uri() {
		Uri uri = Uri.createUri("geo:38.755611, -9.116464");
		assert uri != null;
		GeoUri geo = (GeoUri)uri; 
		assertEquals(geo.getLatitude(), 38.755611f);
		assertEquals(geo.getLongitude(), -9.116464f);

		System.out.println(geo);
	}

	private static void assertEquals(String value, String expected)
	{
		if(!value.equals(expected)) {
			System.err.println(
				java.text.MessageFormat.format("Assert Error: expected value \"{0}\" instead of \"{1}\"", expected, value)
			); 
			assert value.equals(expected);
		}
	}

	private static void assertEquals(short value, short expected)
	{
		if(value != expected) {
			System.err.println(
				java.text.MessageFormat.format("Assert Error: expected value {0} instead of {1}", expected, value)
			); 
			assert value == expected;
		}
	}

	private static void assertEquals(float value, float expected)
	{
		if(value != expected) {
			System.err.println(
				java.text.MessageFormat.format("Assert Error: expected value {0} instead of {1}", expected, value)
			); 
			assert value == expected;
		}
	}

	public static void main(String[] args) {
		//should_fail_if_asserts_are_enabled();

		Uri.addUriFactory(new TcpIpUriFactory());
		should_not_parse_malformed_host();
		should_parse_uri_with_host_and_default_path();
		should_parse_uri_with_host_and_path_without_querystring();
		should_parse_uri_with_host_and_path_with_querystring();
		should_parse_uri_with_fragment();
		should_display_uri_with_fragment();
		should_parse_uri_with_port();
		should_parse_ftp_uri();

		Uri.addUriFactory(new MailToUriFactory());
		should_parse_mailto_uri();

		Uri.addUriFactory(new GeoUriFactory());
		should_parse_geo_uri();

		System.out.println("Success");
	}
	

}
