package com.example.rfid.bl.Connection;

import java.net.HttpURLConnection;

public interface HTTPConnectService {
    public HttpURLConnection connect();

    public void closeConnect();

    public String doGet();

    public String doPost(String param);
}
