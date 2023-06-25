package client.backend;

import common.*;
import common.data.LabWork;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Sender {
    private SocketChannel socket;
    private ByteBuffer sizeIntBuffer = ByteBuffer.allocate(Integer.BYTES);
    private ByteBuffer payloadBuffer = null;

    public void sendMessageWithUpdate(String[] command, Authentication client) throws IOException {
        ByteBuffer buffer = Encoder.encode(new RequestUpdate(command, client));
        buffer.flip();
        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }
    }

    public void sendMessage(String[] command, Authentication client) throws IOException {
        ByteBuffer buffer = Encoder.encode(new Request(command, client));
        buffer.flip();
        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }
    }

    public void sendMessageWithLabWork(String command, LabWork labWork, Authentication client) throws IOException {
        ByteBuffer buffer = Encoder.encode(new RequestWithLabWork(command, labWork, client));
        buffer.flip();
        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }
    }

    public void sendMessageWithCommands(ArrayList<String> commands, Authentication client) throws IOException {
        ByteBuffer buffer = Encoder.encode(new RequestWithCommands(commands, client));
        buffer.flip();
        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }
    }

    public void sendAuth(String command, Authentication client) throws IOException {
        ByteBuffer buffer = Encoder.encode(new RequestWithClient(command, client));
        buffer.flip();
        while (buffer.hasRemaining()) {
            socket.write(buffer);
        }
    }

    public boolean checkForMessage() throws IOException {
        if (payloadBuffer != null && !payloadBuffer.hasRemaining()) {
            return true;
        }
        socket.read(sizeIntBuffer);
        if (sizeIntBuffer.hasRemaining()) {
            return false;
        }

        if (payloadBuffer == null) {
            payloadBuffer = ByteBuffer.allocate(sizeIntBuffer.getInt(0));
        }

        socket.read(payloadBuffer);
        return !payloadBuffer.hasRemaining();
    }

    public Object getPayload() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(payloadBuffer.array());
        ObjectInputStream ois = new ObjectInputStream(bais);
        try {
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void clearInBuffer() {
        ((Buffer) sizeIntBuffer).clear();
        payloadBuffer = null;
    }

    public void setSocket(SocketChannel socket) {
        this.socket = socket;
    }
}
