package com.seu.biz.event

/**
 * 游戏事件接口
 * 任何一件游戏中发生的事情都用事件来实现
 * 设计事件类与直接执行事件内容有两个不同
 * 1.事件类与记录进行挂钩，每个事件执行后可以添加至游戏记录中
 * 2.有时产生一个事件未必是立即执行的，我们可以注册在某个时机然后在这个时机触发事件
 */
interface IGameEvent {
    /* 事件返回结果 */
    val resultSet: Any?
    /* 事件发起者 */
    var source: Any?
    /* 事件类型 */
    val eventType: String
    /* 事件的核心对象 - 与事件执行紧密挂钩 */
    val obj: Any
    /* 事件执行函数 */
    fun execute():IGameEvent
    /* 修改事件发起者并返回自己 */
    fun modifySource(s: Any):IGameEvent {
        source = s
        return this
    }
}