package server;

import common.Encoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;

public class MessageHandler {
    private final Socket socket;
    private byte[] sizeInBuffer;
    private byte[] payloadBuffer;
    private int sizeInBufferPos = 0;
    private int payloadBufferPos = 0;

    public MessageHandler(Socket socket) {
        this.socket = socket;
        this.sizeInBuffer = new byte[Integer.BYTES];
        this.payloadBuffer = null;
    }

    public boolean checkForMessage() throws IOException {
        try {
            if (payloadBuffer != null && payloadBufferPos >= payloadBuffer.length) {
                return true;
            }
            int readBytes = socket.getInputStream().read(sizeInBuffer, sizeInBufferPos, Integer.BYTES - sizeInBufferPos);
            sizeInBufferPos += readBytes;
            if (sizeInBufferPos < Integer.BYTES) {
                return false;
            }
            if (payloadBuffer == null) {
                payloadBuffer = new byte[ByteBuffer.wrap(sizeInBuffer).getInt()];
            }
            readBytes = socket.getInputStream().read(payloadBuffer, payloadBufferPos, payloadBuffer.length - payloadBufferPos);
            payloadBufferPos += readBytes;

            return payloadBufferPos >= payloadBuffer.length;
        } catch (SocketTimeoutException | IndexOutOfBoundsException e) {
            return false;
        }
    }
    public void sendResponse(Object object) throws IOException {
        socket.getOutputStream().write(Encoder.encode(object).array());
    }

    public Object getMessage() throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(payloadBuffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

    public void clearBuffer() {
        sizeInBuffer = new byte[Integer.BYTES];
        payloadBuffer = null;
        sizeInBufferPos = 0;
        payloadBufferPos = 0;
    }

    public Socket getSocket() {
        return socket;
    }
}
