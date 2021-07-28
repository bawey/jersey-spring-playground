package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

/**
 * Root resource (exposed at "myresource" path)
 */
@Resource
@Component
@Slf4j
//@AllArgsConstructor(onConstructor_ = {@Inject})
@Path("myresource")
public class MyResource {


    private MyRepo repo;

    @Inject
    public MyResource(MyRepo repo) {
        this.repo = repo;
    }


    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it: " + repo.getStuff().stream().collect(Collectors.joining(", "));
    }

    @PostConstruct
    public void foo() {
        log.info("Resource bean created!");
    }
}
