/**
 * 长连接读写空闲检测机制
 * 主要事件处理器  new IdleStateHandler(6, 8,10,TimeUnit.SECONDS)
 * 检测channel的读 写 的时间信息 最后生成事件回调用户自定义的 事件方法
 * @author Administrator
 *
 */
package cn.dbw.netty.ssy3;

import java.util.concurrent.TimeUnit;

import io.netty.handler.timeout.IdleStateHandler;
