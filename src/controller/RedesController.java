package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RedesController {

	private String os = System.getProperty("os.name");

	public String getOS() {
		return os;
	}

	public void ip(String process) {
		String ip;
		if (os.contains("Windows")) {
			ip = "ipconfig";
			callProcess(ip);
		}
		else {
			ip = "ifconfig";
			callProcess(ip);
		}

	}

	public void ping(String process) {

		String ping;

		if (os.contains("Windows")) {

			ping = "PING -4 -n 10 www.google.com.br";

			callProcess(ping);

		}

		else {

			ping = "ping -4 -c 10 www.google.com.br";

			callProcess(ping);

		}

	}

	public void callProcess(String process) {

		JFrame popup = new JFrame();

		try {

			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			while (linha != null) {
				if (process.contains("PING")) {
					String[] pm1 = linha.split("ms,");
					for (String pm2 : pm1) {
						if (pm2.contains("M�dia")) {
							JOptionPane.showMessageDialog(popup, "Resultado " + pm2);
						}
						else {
							System.out.print("");
						}
					}
					linha = buffer.readLine();
				}
				else if (process.contains("-c")) {
					String[] pm1 = linha.split("mdev");
					for (String pm2 : pm1) {
						if (pm2.contains(" = ")) {
							String[] pm3 = pm2.split("/");
							JOptionPane.showMessageDialog(popup, "Resultado Média = " + pm3[2] + " ms");
						}
						else {
							System.out.print("");
						}
					}
					linha = buffer.readLine();
				}
				else if (process.contains("ipconfig")) {

					if (linha.contains("Adaptador")) {
						String adp = linha;
						linha = buffer.readLine();
						linha = buffer.readLine();
						while (linha != null) {
							if (linha.contains("Adaptador")) {
								adp = linha;
							} else if (linha.contains("IPv4")) {
								System.out.println(adp + "\n");
								System.out.println(linha + "\n");
							} else {
								System.out.print("");
							}
							linha = buffer.readLine();
						}
					}
					else {
						System.out.print("");
					}
					linha = buffer.readLine();
				}
				else {
					System.out.println(linha);
					linha = buffer.readLine();
				}
			}
			buffer.close();
			fluxo.close();
			leitor.close();
		} catch (IOException e) {
			String msgErr = e.getMessage();
			System.err.println(msgErr);
		}
	}
}