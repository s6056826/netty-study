package cn.dbw.netty.ssy11.inter;

import io.netty.channel.ChannelHandler;

/**
 * 
 * �ͻ��˵�ChannelHandler���ϣ�������ʵ�֣��������ĺô���
 * �̳�����ӿڵ�����������Ժܷ���ػ�ȡChannelPipeline�е�Handlers
 * ��ȡ��handlers֮�󷽱�ChannelPipeline�е�handler�ĳ�ʼ������������ʱ��Ҳ�ܷܺ���
 * �ػ�ȡ���е�handlers
 */
public interface ChanelHandlerHodler {
	
	ChannelHandler[] handlers();

}
