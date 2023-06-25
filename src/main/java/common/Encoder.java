package common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Encoder {
    public static ByteBuffer encode(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);

        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES + baos.size());

        buffer.putInt(baos.size());
        buffer.put(baos.toByteArray());

        return buffer;
    }

}
