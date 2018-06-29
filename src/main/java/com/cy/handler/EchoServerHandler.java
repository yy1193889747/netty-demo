package com.cy.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * @author ocly
 * @date 2018/4/8 13:40
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 心跳检测
     */
    private int loss_connect_time = 0;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String uuid = ctx.channel().id().asLongText();
        System.out.println("有用户连入:" + uuid);
        long l = System.currentTimeMillis();
        System.out.println("Server send: " + l);
        while (true) {
            ByteBuf b = ctx.alloc().buffer();
            b.writeLongLE(l);
            ctx.writeAndFlush(b);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent e = (IdleStateEvent) evt;
            Channel channel = ctx.channel();
/*            if (e.state() == IdleState.READER_IDLE) {
                // 读客户端心跳
                System.out.println("超过10s没读到客户端心跳，断开连接");
                channel.close();
            }*/
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf b = (ByteBuf) msg;
        long l = System.currentTimeMillis();
        System.out.println("Server send: " + l);
        b.writeLong(l);
        ctx.writeAndFlush(b);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
