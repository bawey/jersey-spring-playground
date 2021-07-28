package com.example;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.ContextLoaderListener;

import javax.ws.rs.core.Application;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:appCtx.xml")
@Slf4j
public class TestTheJerseyWay extends JerseyTest {

    TestContainerFactory testContainerFactory = new CustomGrizzlyTestContainerFactory();


    @Override
    protected Application configure() {
        ResourceConfig rc = new ResourceConfig();
        rc.property("contextConfigLocation", "classpath:appCtx.xml");
        rc.register(MyResource.class);
        rc.register(MyRepo.class);
//        rc.register(ContextLoaderListener.class);
//        rc.packages("com.example");
//        rc.property("listener-class", ContextLoaderListener.class.getName());


        return rc;

    }


//    @Override
//    protected DeploymentContext configureDeployment() {
////        DeploymentContext ctx = super.configureDeployment();
////        return ctx;
////        DeploymentContext.Builder contextBuilder = DeploymentContext.builder(configure());
//        ServletDeploymentContext.Builder contextBuilder = ServletDeploymentContext.builder(configure()).addListener(ContextLoaderListener.class);
//        if (getSslContext().isPresent() && getSslParameters().isPresent()) {
//            contextBuilder.ssl(getSslContext().get(), getSslParameters().get());
//        }
//        contextBuilder.contextParam("contextConfigLocation", "classpath:appCtx.xml");
//        return contextBuilder.build();
//
//    }


    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

//    @Override
//    protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
//        return testContainerFactory;
//    }

    public static class CustomGrizzlyTestContainerFactory extends GrizzlyWebTestContainerFactory {
        @Override
        public TestContainer create(URI baseUri, DeploymentContext context) {
            return super.create(baseUri, context);
        }
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void configureClient(ClientConfig config) {
        super.configureClient(config);
    }


    @Test
    public void testFetchingStuff() {
        String responseMsg = target().path("myresource").request().get(String.class);
        assertEquals("Got it: A, b, see", responseMsg);
        log.info("Received: " + responseMsg);
    }


}
