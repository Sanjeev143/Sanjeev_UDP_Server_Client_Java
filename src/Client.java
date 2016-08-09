import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public Client() {
		// TODO Auto-generated constructor stub
	}public static void main(String args[])
    {
        DatagramSocket datagram_socket = null;
        int port = 7777;
        String message_from_client;
         
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
         
        try
        {
            datagram_socket = new DatagramSocket();
             
            InetAddress host = InetAddress.getByName("localhost");
             
            while(true)
            {
                //take input and send the packet
                echo("Enter message to send : ");
                message_from_client = (String)cin.readLine();
                byte[] b = message_from_client.getBytes();
                 
                DatagramPacket  dp = new DatagramPacket(b , b.length , host , port);
                datagram_socket.send(dp);
                 
                //now receive reply
                //buffer to receive incoming data
                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                datagram_socket.receive(reply);
                 
                byte[] data = reply.getData();
                message_from_client = new String(data, 0, reply.getLength());
                 
                //echo the details of incoming data - client ip : client port - client message
                echo(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - " + message_from_client);
            }
        }
         
        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }
     
    //simple function to echo data to terminal
    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}