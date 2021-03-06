package com.jding.netty.nettydemo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NettyClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    //private final ByteBuf firstMessage;
    //begin
    private static final String ECHO_REQ = "Hi, Welcome to Netty.$_";
    //end
    private int counter;

    private byte[] req;

    public NettyClientHandler() {
        //req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        /*firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);*/
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*ByteBuf firstMessage = null;
        for(int i =0; i < 100;i++) {
             firstMessage = Unpooled.buffer(req.length);
             firstMessage.writeBytes(req);
             ctx.writeAndFlush(firstMessage);
        }*/
        //begin
        for(int i =0;i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
        //end
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req,"UTF-8");*/
        /*String body = (String) msg;
        System.out.println("Now is:" + body + "; the counter is:" + ++counter);*/
        //
        System.out.println("This is" + ++counter + " times receive server:[" + msg + "]");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("....................." + cause.getMessage());
        ctx.close();
    }
}
