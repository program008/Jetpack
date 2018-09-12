package com.canbot.paging

import java.util.concurrent.Executors

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) {
        IO_EXECUTOR.execute(f)
}