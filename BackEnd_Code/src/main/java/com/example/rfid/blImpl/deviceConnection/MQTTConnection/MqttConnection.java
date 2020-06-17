package com.example.rfid.blImpl.deviceConnection.MQTTConnection;

import com.example.rfid.bl.Connection.MQTTConnectionService;
import com.example.rfid.po.MqttConfiguration;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttConnection implements MQTTConnectionService {
    private static final Logger log = LoggerFactory.getLogger(MqttPushClient.class);

    private static MqttClient client;//连接客户端

    private static MemoryPersistence memoryPersistence;

    public static MqttClient getClient() {
        return client;
    }

    public static void setClient(MqttClient client) {
        MqttConnection.client = client;
    }

    private MqttConnectOptions getOption(String userName, String password, int outTime, int KeepAlive) {
        //MQTT连接设置
        MqttConnectOptions option = new MqttConnectOptions();
        //设置是否清空session,false表示服务器会保留客户端的连接记录，true表示每次连接到服务器都以新的身份连接
        option.setCleanSession(false);
        //设置连接的用户名
        option.setUserName(userName);
        //设置连接的密码
        option.setPassword(password.toCharArray());
        //设置超时时间 单位为秒
        option.setConnectionTimeout(outTime);
        //设置会话心跳时间 单位为秒 服务器会每隔(1.5*keepTime)秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        option.setKeepAliveInterval(KeepAlive);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        //option.setWill(topic, "close".getBytes(), 2, true);
        option.setMaxInflight(1000);
        //        try
        //        {
        //            option.setSocketFactory(SslUtil
        //                    .getSocketFactory("E:\\emq\\emqttd\\etc\\certs\\cacert.pem", "E:\\emq\\emqttd\\etc\\certs\\client-cert.pem", "E:\\emq\\emqttd\\etc\\certs\\client-key.pem", ""));
        //        }
        //        catch (Exception e)
        //        {
        //            e.printStackTrace();
        //        }

        return option;
    }

    /**
     * 连接
     */
    public void connect(MqttConfiguration mqttConfiguration){
        MqttClient client;
        try {
            memoryPersistence = new MemoryPersistence();
            client = new MqttClient(mqttConfiguration.getHost(), mqttConfiguration.getClientId(),memoryPersistence );
            MqttConnectOptions options = getOption(mqttConfiguration.getUsername(),mqttConfiguration.getPassword(),
                    mqttConfiguration.getTimeout(),mqttConfiguration.getKeepAlive());
            MqttPushClient.setClient(client);
            try {
                client.setCallback(new ConnectionCallback(this,mqttConfiguration));
                if (!client.isConnected()) {
                    client.connect(options);
                    log.info("MQTT连接成功");

                }else {//这里的逻辑是如果连接成功就重新连接
                    client.disconnect();
                    client.connect(options);
                    log.info("MQTT断连重连成功");
                }
                //订阅默认主题，监听客户端信息
                MqttRecieveClient mqttRecieveClient = new MqttRecieveClient();
                mqttRecieveClient.subscribe(new String[]{"$SYS/brokers/+/clients/+/connected", "$SYS/brokers/+/clients/+/disconnected"}, new int[]{1, 1});
                //订阅主题
                mqttRecieveClient.subscribe(mqttConfiguration.getTopic(),mqttConfiguration.getQos());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 断线重连
     * @throws Exception
     */
    public Boolean reConnect() throws Exception {
        Boolean isConnected = false;
        if(null != client) {
            client.connect();
            if(client.isConnected()){
                isConnected = true;
            }
        }
        return isConnected;
    }


    public void closeConnect() {
        //关闭存储方式
        if(null != memoryPersistence) {
            try {
                memoryPersistence.close();
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("memoryPersistence is null");
        }

//		关闭连接
        if(null != client) {
            if(client.isConnected()) {
                try {
                    client.disconnect();
                    client.close();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("mqttClient is not connect");
            }
        }else {
            System.out.println("mqttClient is null");
        }
    }

}
