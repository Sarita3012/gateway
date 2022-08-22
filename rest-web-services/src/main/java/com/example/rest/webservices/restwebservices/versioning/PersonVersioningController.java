package com.example.rest.webservices.restwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    // this is called URI Versioning
    @GetMapping("V1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Charlie ");
    }
    @GetMapping("V2/person")
    public PersonV2 personV2(){
        return new PersonV2 (new Name("Bob","Charlie"));
    }

    //Using Param Version "Request Parameter Versioning"
    @GetMapping(value="/person/param",params="version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Bob Charlie ");
    }
    @GetMapping(value="/person/param",params="version=2")
    public PersonV2 paramV2(){
        return new PersonV2 (new Name("Bob","Charlie"));
    }

    //Using Header Version
    @GetMapping(value="/person/header",headers="Version=1")
    public PersonV1 headerV1(){
        return new PersonV1("Bob Charlie ");
    }
    @GetMapping(value="/person/header",headers="Version=2")
    public PersonV2 headerV2(){
        return new PersonV2 (new Name("Bob","Charlie"));
    }

    //Using Accept Version Header  "Produces Versioning"
    @GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Bob Charlie ");
    }
    @GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2 (new Name("Bob","Charlie"));
    }
}
