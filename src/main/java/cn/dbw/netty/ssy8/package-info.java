/**
 *  1.bytebuffer和filechannel的学习
 *  2.直接缓冲区(堆外内存)和堆缓冲区的区别
 *    1）heapByteBuffer 堆缓冲区，当与操作系统进行io操作
 *      时候会在jvm内存模型外开拓一份内存，进行数据拷贝。
 *    2）DirectByteBuffer 若使用直接缓冲区，直接在jvm内存模型外开拓内存，io操作直接
 *      与操作系统打交道，少了一次数据拷贝的过程，zero copy。
 */
/**   
 * @author Administrator
 *
 */
package cn.dbw.netty.ssy8;