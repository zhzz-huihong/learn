package org.example.callafter;

import java.util.Optional;

/**
 * @author haoren
 * @date 2024/05/25 14:15
 **/
public abstract class Task {
    final void executeWith(CallBack callBack){
        execute();
        Optional.ofNullable(callBack).ifPresent(CallBack::call);
    }

    public abstract void execute();
}
