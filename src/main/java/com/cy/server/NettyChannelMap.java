package com.cy.server;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ocly
 * @date 2018/4/2 11:55
 */
public class NettyChannelMap {
    private static Map<String,SocketChannel> map = new ConcurrentHashMap<>();
    public static void add(String clientId,SocketChannel channel){
        map.put(clientId, channel);
    }

    public static Channel get(String clientId){
        return map.get(clientId);
    }

    public static void remove(SocketChannel channel){
        for (Map.Entry<String,SocketChannel> entry:map.entrySet()){
            if (entry.getValue()==channel){
                map.remove(entry.getKey());
            }
        }
    }
}
