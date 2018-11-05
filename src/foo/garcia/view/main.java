package foo.garcia.view;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import foo.garcia.contracts.BancoRemoto;

public class main {

	public static void main(String[] args) {
		try {
			BancoRemoto banco = (BancoRemoto) Naming.lookup("rmi://localhost:2126/banco");
			System.out.println("Cliente conectado");
			int opcao = 0;
			String retorno;
			
			while (opcao != 9) {
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu:" + "\n Digite 1 para cadastrar novo cliente"
						+ "\n Digite 2 para cadastrar uma conta simples"
						+ "\n Digite 3 para cadastrar uma conta especial"
						+ "\n Digite 4 para verificar as contas de um cliente" + "\n Digite 6 para fazer um depósito"
						+ "\n Digite 7 para fazer um saque" + "\n Digite 8 para fazer uma transferência"
						+ "\n Digite 9 para sair do sistema"));
				switch (opcao) {
				case 1:
					int idUnico = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID único"));
					String nome = JOptionPane.showInputDialog("Informe o nome");
					retorno = banco.adicionarCliente(nome, idUnico);
					JOptionPane.showMessageDialog(null, retorno);
					break;
				case 2:
					int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cliente"));
					int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o Número da Conta"));
					double saldo = Double.parseDouble(JOptionPane.showInputDialog("Informe o Saldo da conta"));
					retorno = banco.cadastrarContaSimples(idCliente, numeroConta, saldo);
					JOptionPane.showMessageDialog(null, retorno);
					break;
				case 3:
					idCliente = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cliente"));
					numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Informe o Número da Conta"));
					saldo = Double.parseDouble(JOptionPane.showInputDialog("Informe o Saldo da conta"));
					double limiteCredito = Double.parseDouble(JOptionPane.showInputDialog("Informe o limite de crédito"));
					retorno = banco.cadastrarContaEspecial(idCliente, numeroConta, saldo, limiteCredito);
					JOptionPane.showMessageDialog(null, retorno);
					break;
				case 4:
					idCliente = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cliente"));
					retorno = banco.retornaContasDoCliente(idCliente);
					JOptionPane.showMessageDialog(null, retorno);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					break;
				}
			}
			JOptionPane.showMessageDialog(null, "Saindo do sistema, até logo");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
