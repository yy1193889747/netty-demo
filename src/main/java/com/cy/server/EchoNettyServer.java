package com.cy.server;

import com.cy.handler.EchoDecoder;
import com.cy.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


/**
 * @author ocly
 * @date 2018/4/8 11:49
 */
public class EchoNettyServer {

    private final int port;

    public EchoNettyServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(worker).channel(NioServerSocketChannel.class).localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            // first heart check
                            channel.pipeline().addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
                            channel.pipeline().addLast(new EchoDecoder());
                            channel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoNettyServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();

        }finally {
            worker.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new EchoNettyServer(9999).start();
    }
}
