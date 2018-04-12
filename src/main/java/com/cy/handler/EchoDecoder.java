package com.cy.handler;

import com.cy.util.NumberUtils;
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

        // 消息类型2
        byte[] msgTypeByte = new byte[2];
        in.readBytes(msgTypeByte);
        short msgType = NumberUtils.byte2Short4C(msgTypeByte);

        if (msgType == 1) {
            ctx.writeAndFlush(in);
        }
    }
}
