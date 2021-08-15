package com.example;


import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import javax.ws.rs.client.WebTarget;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestWithExtension {

    @RegisterExtension
    static JerseyExtension jerseyExtension = new JerseyExtension.JerseyExtensionBuilder()
            .uri("http://localhost:8080")
            .config(
                    new ResourceConfig().packages("com.example").
                            property("contextConfigLocation", "classpath:appCtx.xml")).build();

    @Test
    public void fooTest(WebTarget target) {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it: A, b, see", responseMsg);
    }
}
