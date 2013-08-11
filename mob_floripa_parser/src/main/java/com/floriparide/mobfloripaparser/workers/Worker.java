package com.floriparide.mobfloripaparser.workers;

import com.floriparide.mobfloripaparser.model.Task;

/**
 * @author mikhail.bragin
 * @since 8/10/13
 */
public interface Worker {

    public void addTask(Task task);
}
