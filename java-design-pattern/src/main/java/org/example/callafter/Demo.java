package org.example.callafter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author haoren
 * @date 2024/05/25 14:21
 **/
@Slf4j
public class Demo {
    public static void main(String[] args) {
        SimpleTask simpleTask = new SimpleTask();
        simpleTask.executeWith(()-> log.info("I'm done now."));
    }
}
