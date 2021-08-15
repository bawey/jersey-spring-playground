package com.example;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.extension.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class JerseyExtension implements ParameterResolver, BeforeAllCallback, AfterAllCallback {

    private HttpServer server;
    private final String uri;
    private ResourceConfig config;


    @Override
    public void afterAll(ExtensionContext extensionContext) {
        server.shutdown();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        this.server = GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), config);
        this.server.start();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(WebTarget.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType().equals(WebTarget.class)) {
            return getWebTarget();
        }
        return null;
    }

    protected WebTarget getWebTarget() {
        return ClientBuilder.newClient().target(this.uri);
    }
}
