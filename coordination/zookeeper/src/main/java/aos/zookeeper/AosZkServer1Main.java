package aos.zookeeper;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AosZkServer1Main {
    private final static Logger LOGGER = LoggerFactory.getLogger(AosZkServer1Main.class);

    private static int CLIENT_PORT = 2181;
    private static int NUM_CONNECTIONS = 5000;
    private static int TICK_TIME = 2000;
    private static String DATA_DIRECTORY = System.getProperty("target/zookeeper-data");

    public static void main(String... args) throws InterruptedException, IOException {

        File dir = new File(DATA_DIRECTORY, "zookeeper").getAbsoluteFile();

        final ZooKeeperServer server = new ZooKeeperServer(dir, dir, TICK_TIME);
        final ServerCnxnFactory standaloneServerFactory = NIOServerCnxnFactory.createFactory(new InetSocketAddress(
                CLIENT_PORT), NUM_CONNECTIONS);

        new Thread() {
            public void run() {
                try {
                    standaloneServerFactory.startup(server);
                    while (true) {
                        try {
                            Thread.sleep(Long.MAX_VALUE);
                        } catch (InterruptedException e) {
                            LOGGER.error("ZooKeeper Failed", e);
                            throw new RuntimeException("ZooKeeper Failed", e);
                        }
                    }
                } catch (IOException e) {
                    LOGGER.error("ZooKeeper Failed", e);
                    throw new RuntimeException("ZooKeeper Failed", e);
                } catch (InterruptedException e) {
                    LOGGER.error("ZooKeeper Failed", e);
                    throw new RuntimeException("ZooKeeper Failed", e);
                }
            }
        }.start();

        LOGGER.info("Zookeeper Server is started.");

        // TimeUnit.MINUTES.sleep(3);

    }

}
