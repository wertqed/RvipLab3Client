import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by VBelov on 03.11.2017.
 */
public class MessageSource implements Runnable {

    private Integer num;
    private Integer numMessages;
    static Socket socket;

    public MessageSource(Integer num, Integer numMessages) {
        this.num = num;
        this.numMessages = numMessages;
        try {
            socket = new Socket("localhost", 3345);
            System.out.println("Клиент №" + num + " подключился к серверу");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try (
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream())) {
            for (int i = 0; i < numMessages; i++) {
                if(!socket.isClosed()) {
                    oos.writeUTF("Я " + num + "написал письмо"+ i);
                    System.out.println("Я " + num + "написал письмо"+ i);
                    oos.flush();
                    Thread.sleep(5000);
                }else{
                    return;
                }
            }
            oos.writeUTF("quit");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
