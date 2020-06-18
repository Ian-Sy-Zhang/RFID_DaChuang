package com.example.rfid.blImpl.deviceConnection.MQTTConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.rfid.po.MqttConfiguration;

/**
 * @Description:回调函数
 */
public class ConnectionCallback implements MqttCallback {

    // private RedisTemplate<String, Object> redisDao;

    private static final Logger log = LoggerFactory.getLogger(ConnectionCallback.class);
    private MqttConnection mqttConnection;


    private MqttConfiguration mqttConfiguration;

    public ConnectionCallback(MqttConnection mqttConnection, MqttConfiguration mqttConfiguration) {
        this.mqttConnection = mqttConnection;
        this.mqttConfiguration = mqttConfiguration;
    }



    @Override
    public void connectionLost(Throwable cause) {
        /** 连接丢失后，一般在这里面进行重连 **/
        if (mqttConnection != null) {
            while (true) {
                try {
                    log.info("[MQTT] 连接断开，30S之后尝试重连...");
                    Thread.sleep(30000);
                    mqttConnection.connect(mqttConfiguration);
                    if (mqttConnection.getClient().isConnected()) {
                        System.out.println("重连成功");
                    }
                    break;
                } catch (Exception e) {
                    log.error("[MQTT] 连接断开，重连失败！");
                    continue;
                }
            }
        }
        log.info(cause.getMessage());
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //publish后会执行到这里
        log.info("pushComplete---------" + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        log.info("接收消息主题 : " + topic);
        log.info("接收消息Qos : " + message.getQos());
        log.info("接收消息内容 : " + new String(message.getPayload()));
        String msg = new String(message.getPayload());
        try {
            JSONObject jsonObject = JSON.parseObject(msg);
            String clientId = String.valueOf(jsonObject.get("clientid"));
            if (topic.endsWith("disconnected")) {
                log.info("客户端已掉线：{}", clientId);
            } else {
                log.info("客户端已上线：{}", clientId);
            }
        } catch (JSONException e) {
            log.error("JSON Format Parsing Exception : {}", msg);

        }
    }


}
