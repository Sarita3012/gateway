package com.example.rest.webservices.restwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {

    @GetMapping("/filter-user")
    public MappingJacksonValue retrieveFilterUser(){
        FilterUser filterUser=new FilterUser("sam","pune","Maharashtra");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("city","state");
        FilterProvider filters=new SimpleFilterProvider().addFilter("FilterUser",filter);
        MappingJacksonValue mapping=new MappingJacksonValue(filterUser);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filter-user-list")
    public MappingJacksonValue retrieveFilterUserList(){
        List<FilterUser> list= Arrays.asList(new FilterUser("sam","pune","Maharashtra"),
                new FilterUser("jack","Mumbai","Maharashtra"));

        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("name","city");
        FilterProvider filters= new SimpleFilterProvider().addFilter("FilterUser",filter);
        MappingJacksonValue mapping=new MappingJacksonValue(list);
        mapping.setFilters(filters);
      //this is called Dynamic Filtering
        return mapping;
    }
}
