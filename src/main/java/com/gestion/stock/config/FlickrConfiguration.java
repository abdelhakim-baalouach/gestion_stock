package com.gestion.stock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private String apiKey;

    @Value("${flickr.apiSecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;

    /**
     * Cette fonction utiliser une seule fois pour autorisation dans flickr
     * Génère appKey et appSecret
     * Code :
    @Bean
    public Flickr getFlickr() throws InterruptedException, ExecutionException, IOException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));

        final Scanner scanner = new Scanner(System.in);
        final OAuth1RequestToken requestToken = service.getRequestToken();
        final String authUrl = service.getAuthorizationUrl(requestToken);
        System.out.println(authUrl);
        System.out.println("Paste it here >> ");
        final String authVerifier = scanner.nextLine();

        OAuth1AccessToken auth1AccessToken = service.getAccessToken(requestToken, authVerifier);
        System.out.println("appKey: " + auth1AccessToken.getToken());
        System.out.println("appSecret: " + auth1AccessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(auth1AccessToken);
        System.out.println("-------------------- check token is valid ----------------------------");
        System.out.println("appKey: " + auth.getToken());
        System.out.println("appSecret: " + auth.getTokenSecret());

        return flickr;
    } */

    @Bean
    public Flickr connect() {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);
        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);

        return flickr;
    }
}
