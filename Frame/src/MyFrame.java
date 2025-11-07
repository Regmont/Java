import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MyFrame extends JFrame {
    private String name = "";
    private DatagramSocket socket = null;
    private MyListener listener = null;
    public static InetAddress GROUP = null;
    public static int PORT = 6711;

    private JTextArea taScreen;
    private JTextField taMessage;
    private JButton btnSend;
    private JButton btnConnect;

    public MyFrame() {
        initComponents();
        try {
            MyFrame.GROUP = InetAddress.getByName("192.168.0.255");
        } catch (UnknownHostException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {
        setTitle("UDP Broadcast Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 300);


        taScreen = new JTextArea();
        taScreen.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taScreen);
        scrollPane.setBounds(10, 10, 380, 200);
        add(scrollPane);


        taMessage = new JTextField();
        taMessage.setBounds(10, 220, 300, 25);
        add(taMessage);


        btnSend = new JButton("Send");
        btnSend.setBounds(320, 220, 70, 25);
        btnSend.setEnabled(false);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        add(btnSend);


        btnConnect = new JButton("Connect");
        btnConnect.setBounds(10, 250, 100, 25);
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToChat();
            }
        });
        add(btnConnect);

        setVisible(true);
    }

    private void connectToChat() {

        name = JOptionPane.showInputDialog(this, "Введите ваше имя:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Имя обязательно!");
            return;
        }


        try {
            this.socket = new DatagramSocket(MyFrame.PORT);
            taScreen.append("Подключен к чату как: " + name + "\n");
        } catch (IOException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ошибка создания сокета: " + ex.getMessage());
            return;
        }


        this.listener = new MyListener(this.socket, this.taScreen, this);
        Thread t = new Thread(listener);
        t.setDaemon(true);
        t.start();


        btnSend.setEnabled(true);
        btnConnect.setEnabled(false);
        taMessage.setEditable(true);
        taScreen.append("Слушатель запущен. Можете отправлять сообщения.\n");
    }

    private void sendMessage() {
        String str = this.taMessage.getText().trim();
        this.taMessage.setText("");
        if (str.isEmpty()) {
            return;
        }

        str = this.name + ": " + str;
        byte[] out = new byte[4096];
        try {
            out = str.getBytes("UTF-8");
            DatagramPacket pout = new DatagramPacket(out, out.length, MyFrame.GROUP, MyFrame.PORT);
            this.socket.send(pout);
            taScreen.append("Вы: " + str + "\n");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void dispose() {
        if (listener != null) {
            listener.stopListening();
        }
        if (socket != null) {
            socket.close();
        }
        super.dispose();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MyFrame();
    }
}

class MyListener implements Runnable {
    private DatagramSocket socket = null;
    private JTextArea ta = null;
    private MyFrame frame = null;
    public boolean isAlive = true;

    public MyListener(DatagramSocket s, JTextArea a, MyFrame f) {
        this.socket = s;
        this.ta = a;
        this.frame = f;
    }

    @Override
    public void run() {
        System.out.println("Listening started!");
        byte[] buff = new byte[4096];
        String str = "";
        DatagramPacket pin = null;
        while (this.isAlive) {
            buff = new byte[4096];
            pin = new DatagramPacket(buff, buff.length);
            try {
                this.socket.receive(pin);
                byte[] data = new byte[pin.getLength()];
                System.arraycopy(pin.getData(), 0, data, 0, pin.getLength());
                str = new String(data, "UTF-8").trim();
                String displayStr = str + System.getProperty("line.separator");
                ta.append(displayStr);
            } catch (IOException ex) {
                Logger.getLogger(MyListener.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
        System.out.println("Listening is over");
    }

    public void stopListening() {
        this.isAlive = false;
    }
}
