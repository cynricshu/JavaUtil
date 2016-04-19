import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Cynric
 * Date: 9/11/15
 * Time: 11:10
 */
public class sFtpServer {


    public static void main(String[] args) {
        FtpServerFactory serverFactory = new FtpServerFactory();

        BaseUser user = new BaseUser();
        user.setName("test");
        user.setPassword("123456");
        user.setHomeDirectory("/Users/cynric/ftp/anonymous");

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        try {
            serverFactory.getUserManager().save(user);
        } catch (FtpException e) {
            e.printStackTrace();
        }


        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2221);
        serverFactory.addListener("default", listenerFactory.createListener());

        FtpServer server = serverFactory.createServer();
// start the server
        try {
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }
}
