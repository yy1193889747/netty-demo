package com.cy.metaq;

import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;

/**
 * @author congyang.guo
 */
public class MessageListener extends DefaultMessageListener<Message> {
    @Override
    public void onReceiveMessages(MetaqMessage<Message> metaqMessage) {
        Message body = metaqMessage.getBody();
        System.out.println("receive trade message:" + body);
    }
}
