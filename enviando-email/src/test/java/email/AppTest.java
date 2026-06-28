package email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest{
	
	private String userName = "mspneto10@gmail.com";
	private String senha = "mcuk rwny ubhl aqhs";
	
	/*Junit Test serve para rodar teste sem precisar criar main*/
	@org.junit.Test
	public void testeEmail(){
		
		/*Olhe as configurações smtp do seu email*/
		
		try {
			
		/*CRIAÇÃO DAS PROPRIEDADES*/
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); //autorização
		properties.put("mail.smtp.starttls", "true"); //autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); //servidor do gmail
		properties.put("mail.smtp.port", "465"); //porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465"); //Especifica a porta ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /*Classe socket de conexão ao SMTP*/
		
		/*CRIAÇÃO DA SESSÃO QUE VAI CONECTAR AO SERVIDOR DO GMAIL*/
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		/*AGORA VEM A CRIAÇÃO DO EMAIL EM SI*/
		
		//cria a lista de destinatários
		Address[] toUser = InternetAddress.parse("manoel.santiago1@gmail.com, patriciasquinello@gmail.com, mspneto10@outlook.com, moniquepinheiro1@gmail.com");
		
		//cria a mensagem
		Message message = new MimeMessage(session);
		//remetente - recebe um objeto InternetAddress com email do remetente
		message.setFrom(new InternetAddress(userName,"Manoel usando Java"));
		//destinatário
		message.setRecipients(Message.RecipientType.TO, toUser);
		//assunto
		message.setSubject("Chegou o email enviado em java do Manoel Santiago");
		//mensagem
		message.setText("Fazendo terceiro teste usando somente programação. Agora mudei uma configuração que, ao invés de no remetente ter o endereço do email, vai meu nome.");
		
		//executar o envio
		Transport.send(message);
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
   
}
