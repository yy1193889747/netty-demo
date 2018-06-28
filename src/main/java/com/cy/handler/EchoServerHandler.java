package com.cy.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * @author ocly
 * @date 2018/4/8 13:40
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 心跳检测
     */
    private int loss_connect_time = 0;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有用户连入" + ctx.name());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent e = (IdleStateEvent) evt;
            Channel channel = ctx.channel();
            if (e.state() == IdleState.READER_IDLE) {
                // 读客户端心跳
                System.out.println("超过10s没读到客户端心跳，断开连接");
                channel.close();
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf b = (ByteBuf) msg;
        while (true) {
            long l = System.currentTimeMillis();
            System.out.println("Server send: " + l);
            b.writeLongLE(l);
            ctx.writeAndFlush(b);
            Thread.sleep(200);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
