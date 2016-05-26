import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;



public class TesteTarefa {

	private List<?> resultado;

	

	@Test
	public void testStringVazia() {
		resultado = Tarefa.converterCamelCase("");
		assertNotNull(resultado);
		assertEquals(resultado.size(),0);
	}
	
	@Test
	public void testStringSemCamelCase() {
		resultado = Tarefa.converterCamelCase("nome");
		assertNotNull(resultado);
		assertEquals(resultado.size(),1);
		assertEquals("nome", resultado.get(0));
		
		resultado = Tarefa.converterCamelCase("Nome");
		assertNotNull(resultado);
		assertEquals(resultado.size(),1);
		assertEquals("nome", resultado.get(0));
	}
	
	
	@Test
	public void testStringUmCamelCase() {
		resultado = Tarefa.converterCamelCase("nomeComposto");
		assertNotNull(resultado);
		assertEquals(resultado.size(),2);
		assertEquals("nome", resultado.get(0));
		assertEquals("composto", resultado.get(1));
	}
	
	@Test
	public void testStringSomenteLetrasMaiusculas() {
		resultado = Tarefa.converterCamelCase("CPF");
		assertNotNull(resultado);
		assertEquals(resultado.size(),1);
		assertEquals("CPF", resultado.get(0));
	}
	
	@Test
	public void testStringPalavraLowerCaseEPalavraUpperCase() {
		resultado = Tarefa.converterCamelCase("numeroCPF");
		assertNotNull(resultado);
		assertEquals(resultado.size(),2);
		assertEquals("numero", resultado.get(0));
		assertEquals("CPF", resultado.get(1));
	}
	

	
	@Test
	public void testStringPalavraLowerCaseNumeroEPalavra() {
		resultado = Tarefa.converterCamelCase("recupera10Primeiros");
		assertNotNull(resultado);
		assertEquals(resultado.size(),3);
		assertEquals("recupera", resultado.get(0));
		assertEquals("10", resultado.get(1));
		assertEquals("primeiros", resultado.get(2));
		
	}
	
	
	@Test(expected=CamelCaseInicioComNumeroException.class)
	public void testComecandoComNumeroDez() {
		Tarefa.converterCamelCase("10Primeiros");	
	}
	
	@Test(expected=CamelCaseInicioComNumeroException.class)
	public void testComecandoComNumeroZero() {
		Tarefa.converterCamelCase("0Primeiros");	
	}
	
	@Test(expected=CamelCaseCaracteresEspeciaisException.class)
	public void testCaracterInvalidoJogoDaVelha() {
		Tarefa.converterCamelCase("nome#Composto");	
	}
	@Test(expected=CamelCaseCaracteresEspeciaisException.class)
	public void testCaracterInvalidoExclamacao() {
		Tarefa.converterCamelCase("nome!Composto");	
	}

}
