package com.heiwait.tripagency.pricer.driver.exposition.rest.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hello")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloResource {

    @ApiOperation(value = "Compute name parameter to Hello", notes = "Returns a string saying Hello {{name}}")
    @GetMapping(value = {"/{name}"}, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> helloName(
            @PathVariable(value = "name") String name) {

        return new ResponseEntity<>("Hello " + name, HttpStatus.OK);
    }
}