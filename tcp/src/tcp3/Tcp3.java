package tcp3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tcp3 {
	public static void main(String[] args) {
		final AtomicBoolean shouldRun = new AtomicBoolean(true);
		try {
			Thread serverThread = new Thread() {
				@Override
				public void run() {
					int backlog = 3; // Anzahl simultaner offener Verbindungen
					try (ServerSocket server = new ServerSocket(2123, backlog)) {
						while (shouldRun.get()) {
							try (Socket s = server.accept()) {
								Thread.sleep(TimeUnit.SECONDS.toMillis(30));
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			};
			serverThread.start();

			long timeStart = System.currentTimeMillis();

			Socket[] clients = new Socket[150];
			for (int i = 0; i < clients.length; i++) {
				clients[i] = new Socket("localhost", 2123);
				long now = System.currentTimeMillis();
				System.out.printf("Client %2d: %s at %d%n", i, clients[i], (now - timeStart));
				clients[i].close();
			}
			shouldRun.set(false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			shouldRun.set(false);
		}

	}
}
