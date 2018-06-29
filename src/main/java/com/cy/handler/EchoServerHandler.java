package com.cy.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutor;

import java.util.concurrent.*;

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
        String uuid = ctx.channel().id().asLongText();
        System.out.println("有用户连入:" + uuid);
        while (true) {
            ByteBuf buffer = ctx.alloc().buffer();
            long l = System.currentTimeMillis();
            buffer.writeLongLE(l);
            ctx.writeAndFlush(buffer);
            Thread.sleep(100);
            System.out.println(ctx.isRemoved());
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
