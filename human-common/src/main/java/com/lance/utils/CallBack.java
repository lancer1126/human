package com.lance.utils;

/**
 * 针对某初始化方法，在SpringContextHolder初始化前时可提交一个回调任务。
 * 在SpringContextHolder 初始化后，进行回调使用
 *
 * @author lancer1126
 */
public interface CallBack {
    /**
     * 回调执行方法
     */
    void executor();

    /**
     * 本回调任务名称
     * @return /
     */
    default String getCallBackName() {
        return Thread.currentThread().getId() + ":" + this.getClass().getName();
    }
}
