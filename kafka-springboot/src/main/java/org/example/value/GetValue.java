package org.example.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author haoren
 * @date 2024/05/26 22:29
 **/
@Configuration
public class GetValue {
    @Value("${hub.example.city}")
    private String city;

    public void testGetValue(){
        System.out.println(city);
    }
}
