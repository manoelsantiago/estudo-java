package email;

/**
 * Unit test for simple App.
 */
public class AppTest{
	

	/*Junit Test serve para rodar teste sem precisar criar main*/
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail(
				"manoel.santiago1@gmail.com",
				"Manoel Santiago",
				"Email de teste da nova classe",
				"Isto é o corpo do email. Uma mensagem de teste. Mensagem criada de forma dinâmica.");
		
		enviaEmail.enviarEmail();		
		
	}
   
}
