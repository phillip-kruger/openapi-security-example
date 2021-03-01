package com.github.phillipkruger.company;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.openapi.OASFactory;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.eclipse.microprofile.openapi.models.security.OAuthFlow;
import org.eclipse.microprofile.openapi.models.security.OAuthFlows;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;

/**
 * Add the SecurityScheme from Config
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class SecurityConfigFilter implements OASFilter {
    private final Config config = ConfigProvider.getConfig();
    
    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        
        // Get the authorizationUrl and refreshUrl from Config
        String authorizationUrl = config.getValue("company.security.oauth.authorizationUrl", String.class);
        String refreshUrl = config.getValue("company.security.oauth.refreshUrl", String.class);
        
        // Make sure components are created
        if (openAPI.getComponents() == null) {
            openAPI.setComponents(OASFactory.createComponents());
        }
        
        // Add company security scheme
        Map<String, SecurityScheme> securitySchemes = new HashMap();
        SecurityScheme securityScheme = OASFactory.createSecurityScheme();
        
        OAuthFlows oAuthFlows = OASFactory.createOAuthFlows();
        OAuthFlow oAuthFlow = OASFactory.createOAuthFlow();
        oAuthFlow.authorizationUrl(authorizationUrl);
        oAuthFlow.refreshUrl(refreshUrl);
        oAuthFlow.setScopes(OASFactory.createScopes());
        oAuthFlows.setImplicit(oAuthFlow);
        securityScheme.setType(SecurityScheme.Type.OAUTH2);
        securityScheme.setFlows(oAuthFlows);
        securitySchemes.put("Company Authentication", securityScheme);
        openAPI.getComponents().setSecuritySchemes(securitySchemes);
    }
    
}
