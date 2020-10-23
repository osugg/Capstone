package com.mygdx.game.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.Packets;
import com.mygdx.game.Rogue99;

public class MPClient {

    Rogue99 game;

    int portSocket = 5000;
    String ipAddress = "100.34.155.72";

    public Client client;
    private ClientNetworkListener cnl;

    public MPClient(Rogue99 game){
        this.game = game;

        client = new Client();
        cnl = new ClientNetworkListener();

        cnl.init(client);

        registerPackets();

        client.addListener(cnl);

        new Thread(client).start();

        try{
            client.connect(5000, ipAddress, portSocket);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void registerPackets(){
        Kryo kryo = client.getKryo();
        kryo.register(Packets.Packet000ConnectionAnswer.class);
        kryo.register(Packets.Packet001Connection.class);
        kryo.register(Packets.Packet002Map.class);
        kryo.register(Packets.Packet003Movement.class);
        kryo.register(Packets.Packet004Potion.class);
        kryo.register(Packets.Packet005Stats.class);
    }

//    public static void main(String[] args) {
//        new MPClient();
//    }
}
