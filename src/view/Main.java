package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		
		RedesController Rc = new RedesController();
		
		JFrame popup = new JFrame ();
		
		String process = "";
		int n = 0;
		
		while(n != 4) {
			n = Integer.parseInt(JOptionPane.showInputDialog("Selecione uma opção:1 para consultar o sistema operacional | 2 para PING | 3 para IP | 4 para finalizar"));
			switch (n) {
			case 1:
				JOptionPane.showMessageDialog(popup, "O sistema operacional atual é: "+Rc.getOS());
				break;
			case 2:
				JOptionPane.showMessageDialog(popup, "Seu PING: ");
				Rc.ping(process);
				break;
			case 3:
				JOptionPane.showMessageDialog(popup, "Seu IP: ");
				Rc.ip(process);
				break;
			case 4:
				JOptionPane.showMessageDialog(popup, "O programa finalizou! :)");
				break;
			default:
				JOptionPane.showMessageDialog(popup, "Por favor, insira um valor válido!");
				break;
			}
		
			}
		}

	}

