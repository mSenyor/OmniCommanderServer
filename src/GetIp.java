import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by moran on 18/02/17.
 */
public class GetIp {

    public static void main(String[] args){
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements())
                {
                    InetAddress i = (InetAddress) ee.nextElement();
                    String ip = i.getHostAddress();
                    if(ip.contains("10.0.") || ip.contains("192.168.") || ip.contains("172.")) {
                        System.out.println(ip);
                    }
                }
            }
        }catch (IOException z){

        }

    }
}
