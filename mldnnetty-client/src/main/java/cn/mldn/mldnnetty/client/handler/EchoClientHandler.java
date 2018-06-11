package cn.mldn.mldnnetty.client.handler;

import cn.mldn.util.InputUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends ChannelHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {// 客户端的连接激活
		String data = "userid:mldnjava" ; // 当前建立连接时用户的身份信息
		ctx.writeAndFlush(data) ;
	}	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String content = (String) msg ; // 接收数据
		if ("quit".equalsIgnoreCase(content)) {	// 服务器端要结束处理
			System.out.println("##### 本次操作结束，已退出 #####");
			ctx.close() ; // 关闭连接通道，和服务器端端口
		} else {	// 需要进行进一步的处理操作
			System.out.println("｛客户端｝" + content); // 服务器端的回应信息
			String inputStr = InputUtil.getString("请输入要发送的信息：") ;
			ctx.writeAndFlush(inputStr) ; // 发送数据
		}
	}
}