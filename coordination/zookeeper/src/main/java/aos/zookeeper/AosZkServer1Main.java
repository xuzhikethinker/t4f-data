package aos.zookeeper;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;

public class AosZkServer1Main {

    private static int CLIENT_PORT = 2181;
    private static int NUM_CONNECTIONS = 5000;
    private static int TICK_TIME = 2000;
    private static String DATA_DIRECTORY = System.getProperty("java.io.tmpdir");

    public static void main(String... args) throws IOException, InterruptedException {

        File dir = new File(DATA_DIRECTORY, "zookeeper").getAbsoluteFile();

        ZooKeeperServer server = new ZooKeeperServer(dir, dir, TICK_TIME);
        ServerCnxnFactory standaloneServerFactory = NIOServerCnxnFactory.createFactory(new InetSocketAddress(
                CLIENT_PORT), NUM_CONNECTIONS);

        standaloneServerFactory.startup(server);

    }

}
