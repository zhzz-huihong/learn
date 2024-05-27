package org.example.callafter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author haoren
 * @date 2024/05/25 14:18
 **/
@Slf4j
public final class SimpleTask extends Task{

    @Override
    public void execute() {
        log.info("Perform some important activity and after call the callback method.");
    }
}
