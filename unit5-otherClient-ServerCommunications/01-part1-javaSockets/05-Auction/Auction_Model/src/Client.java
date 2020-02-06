// Adrián Navarro Gabino

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Objects;

public class Client implements Serializable
{
    InetAddress address;
    String name;
    int port;
    float bid;

    public Client(InetAddress address, int port)
    {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

}