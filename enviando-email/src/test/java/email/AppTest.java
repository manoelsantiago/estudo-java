package email;

/**
 * Unit test for simple App.
 */
public class AppTest{
	

	/*Junit Test serve para rodar teste sem precisar criar main*/
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá <br/><br/>");
		stringBuilderTextoEmail.append("Você está recebendo uma mensagem de teste em html <br/><br/>");
		stringBuilderTextoEmail.append("<br/>Atenciosamente,<br/>Manoel Santiago");
		
		
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail(
				"manoel.santiago1@gmail.com",
				"Manoel Santiago",
				"Email de teste da nova classe",
				stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmail(true);		
		
	}
   
}
