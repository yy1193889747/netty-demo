package com.cy.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author cy
 * @date 2018/4/12 10:56
 */
public class EchoDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 时间戳8
        long l = in.readLongLE();
        System.out.println("延时：" + (System.currentTimeMillis() - l));

    }
}
