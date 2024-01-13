package com.cahrypt.client;

import com.cahrypt.protocol.netty.PacketDuplexHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

// temporary for testing TODO make more general
public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());

                        pipeline.addLast(new PacketDuplexHandler());

                        // handle packets
                    }
                });

        ChannelFuture future = bootstrap.connect("localhost", 8080).sync();

        // handle

        future.channel().closeFuture().sync();

        eventLoopGroup.shutdownGracefully();
    }
}
