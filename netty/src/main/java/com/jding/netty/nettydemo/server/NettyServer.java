package com.jding.netty.nettydemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {

    public void bind(int port) throws Exception {
        //Nio线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChanelHandler());
            //绑定端口
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监控端口关闭
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChanelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //使用LineBasedFrameDecoder 可以解决出现的粘包和拆包问题
            //客户端和服务端都可能出现此问题
            //socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));

            /**
             * 基于DelimiterBasedFrameDecoder的TCP粘包。拆包解决方案
             * 使用DelimiterBasedFrameDecoder 可以自动一分隔符作为码流结束标志
             */
            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new StringEncoder());
            //如何去读取客户端的数据,以及返回给客户端的数据
            socketChannel.pipeline().addLast(new NettyServerHandler());
        }
    }
}
