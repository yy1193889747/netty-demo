package com.cy.handler;

import com.cy.util.NumberUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author cy
 * @date 2018/4/12 10:56
 */
public class EchoDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 包长度2
        short pktSize =  in.readShortLE();
        // 消息类型2
        short msgType =  in.readShortLE();
        // 时间戳8
        long l = in.readLongLE();
        CharSequence charSequence = in.readCharSequence(3, StandardCharsets.UTF_8);
        CharSequence charSequences = in.readCharSequence(32, StandardCharsets.UTF_8);
        System.out.println(pktSize+"---"+msgType+"---"+in.readByte()+"---"+l+"---"+charSequence+"---"+charSequences);

    }
}
