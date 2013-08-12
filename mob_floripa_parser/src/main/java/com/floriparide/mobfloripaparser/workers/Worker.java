package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.model.Task;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public interface Worker<T> {

    public void addTask(Task<T> task);
}
