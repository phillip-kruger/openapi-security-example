package com.github.phillipkruger.company;

import java.util.List;
import java.util.Map;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * The company endpoint
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@Path("/jax-rs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Company information", description = "Information on the company")
@SecurityScheme(securitySchemeName = "Company Authentication",
        description = "Your JWT token",
        in = SecuritySchemeIn.HEADER, 
        type = SecuritySchemeType.HTTP, 
        scheme = "Bearer", 
        bearerFormat = "JWT")
@LoginConfig(authMethod = "MP-JWT")
public class CompanyEndpoint {
    
    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Freely available information", description = "Public information on the company")
    @PermitAll
    public String info() {
        return "This is an engineering company in Silicon Valley";
    }
    
    @GET
    @Path("/employees")
    @Operation(summary = "Internal information", description = "List of employees, only available to other employees")
    @RolesAllowed("employee")
    @SecurityRequirement(name = "Company Authentication")
    public List<String> employees(){
        return List.of("Dilbert", "Wally", "Alice", "Dogbert", "Catbert", "Asok", "Ted", "PHB");
    }
    
    @GET
    @Path("/employees/remuneration")
    @Operation(summary = "Sensitive information", description = "Remuneration of employees, only available to the boss")
    @RolesAllowed("boss")
    @SecurityRequirement(name = "Company Authentication")
    public Map<String,Double> remuneration(){
        return Map.of("Dilbert",100.00, "Wally", 110.00, "Alice", 120.00, "Dogbert", 150.00, "Catbert", 250.00, "Asok", 50.00, "Ted", 95.00, "PHB", 300.00);
    }
    
}
