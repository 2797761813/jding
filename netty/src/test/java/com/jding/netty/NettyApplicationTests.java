package com.jding.netty;

import com.jding.netty.nettydemo.client.NettyClient;
import com.jding.netty.nettydemo.server.NettyServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyApplicationTests {

    @Test
    public void clientTest() throws Exception{
        new NettyClient().connect(8080,"127.0.0.1");
    }

    @Test
    public void serverTest() throws Exception {
        new NettyServer().bind(8080);
    }

}
