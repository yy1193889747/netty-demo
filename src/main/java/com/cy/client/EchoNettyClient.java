package com.cy.client;

import com.cy.handler.EchoClientHandler;
import com.cy.handler.EchoDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author ocly
 * @date 2018/4/8 14:16
 */
public class EchoNettyClient {
    private final String host;
    private final int port;

    public EchoNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(worker).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
                                    .addLast(new EchoDecoder())
                                    .addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();

            ByteBuf buffer = f.channel().alloc().buffer();
         /*   // 写入登陆信息
            buffer.writeShortLE(57);
            buffer.writeShortLE(100);
            buffer.writeZero(1);
            System.out.println(System.currentTimeMillis());
            buffer.writeLongLE(System.currentTimeMillis());
            // 登陆加密码
            String userName = "abc";
            String secretKey = "123";
            String sign = userName + secretKey;
            buffer.writeCharSequence(userName, StandardCharsets.UTF_8);
            buffer.writeCharSequence(DigestUtils.md5Hex(sign), StandardCharsets.UTF_8);
            System.out.println(DigestUtils.md5Hex(sign));

            f.channel().writeAndFlush(buffer);*/
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoNettyClient("localhost", 9999).start();
    }
}
