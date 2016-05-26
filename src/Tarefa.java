import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tarefa {
	private static List removeStringEmptyAndTransformToLowerCase(String[] lista){
		List<String> novaLista = new ArrayList<String>();
		for(int i =0 ; i< lista.length; i++)
			if(!lista[i].isEmpty())
				if(lista[i].equals(lista[i].toUpperCase())) novaLista.add(lista[i]);
				else novaLista.add(lista[i].toLowerCase());
		return novaLista;
	}
	
	private static List CamelCaseWithNumber(String original){
		Pattern p = Pattern.compile("[A-Za-z]+|[0-9]+");
		Matcher m = p.matcher(original);
		List<String> lista = new ArrayList<String>();
		while (m.find())lista.add(m.group().trim());	
		
		String[] splitArray =  new String[lista.size()];;
		return removeStringEmptyAndTransformToLowerCase(lista.toArray(splitArray));
	}
	
	public static List converterCamelCase(String original){		
		if(original.matches("(^[\\d])(.*)")) throw new CamelCaseInicioComNumeroException("Erro: A palavra está começando com numero.");
		else if(original.matches("((.*)\\p{Punct}(.*))")) throw new CamelCaseCaracteresEspeciaisException("Erro: A palavra possui um ou mais caracters especiais.");
		else if(original.matches("((.*)([0-9]+)(.*))")) return CamelCaseWithNumber(original);

		String[] splitArray = original.split("(?<=[a-z])(?=[A-Z])");
		return removeStringEmptyAndTransformToLowerCase(splitArray);
	}
}
