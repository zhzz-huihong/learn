package org.example.value;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetValueTest {
    @Autowired
    private GetValue getValue;

    @Test
    public void testGetValue() {
        getValue.testGetValue();
    }
}