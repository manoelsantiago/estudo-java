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

public class ObjetoEnviaEmail {

	private String userName = "mspneto10@gmail.com";
	private String senha = "mcuk rwny ubhl aqhs";
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
		
	}

	public void enviarEmail() throws Exception {

		/* Olhe as configurações smtp do seu email */

		/* CRIAÇÃO DAS PROPRIEDADES */
		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); // autorização
		properties.put("mail.smtp.starttls", "true"); // autenticação
		properties.put("mail.smtp.host", "smtp.gmail.com"); // servidor do gmail
		properties.put("mail.smtp.port", "465"); // porta do servidor
		properties.put("mail.smtp.socketFactory.port", "465"); // Especifica a porta ser conectada pelo socket
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao SMTP */

		/* CRIAÇÃO DA SESSÃO QUE VAI CONECTAR AO SERVIDOR DO GMAIL */
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});

		/* AGORA VEM A CRIAÇÃO DO EMAIL EM SI */

		// cria a lista de destinatários
		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		// cria a mensagem
		Message message = new MimeMessage(session);
		// remetente - recebe um objeto InternetAddress com email do remetente
		message.setFrom(new InternetAddress(userName, nomeRemetente));
		// destinatário
		message.setRecipients(Message.RecipientType.TO, toUser);
		// assunto
		message.setSubject(assuntoEmail);
		// mensagem
		message.setText(textoEmail);

		// executar o envio
		Transport.send(message);

	}

}
