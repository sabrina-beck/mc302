package prodPlan;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Conversor de XML para uma estrutura de Itens
 * 
 * @author Sabrina Beck Angelini <157240>
 **/
public class Lab5Builder extends DefaultHandler implements InterfaceLab5 {
	
	/**
	 * Mapa que organiza as partes pelo código
	 **/
	private Map<Integer, Parte> partes = new HashMap<Integer, Parte>();
	private List<Item> itens = new LinkedList<Item>();
	
	/**
	 * Parte temporária para processamento
	 **/
	private Parte parteTemp;
	
	/**
	 * Lê um arquivo xml e o converte para uma lista de itens
	 * 
	 * @param nome
	 *            do arquivo xml a ser processado
	 **/
	public List<Item> ItemsFromXml(String fileName) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse(fileName, this);
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		constructItensList(itens);
		
		return this.itens;
	}
	
	/**
	 * Substitui partes temporarias por partes reais armazenadas no mapa de
	 * partes
	 **/
	private void constructItensList(List<Item> itens) {
		for (Item item : itens) {
			int codigo = item.getParte().getCod();
			Parte parte = partes.get(codigo);
			item.setParte(parte);
			
			if (parte instanceof ParteComposta) {
				ParteComposta parteComposta = (ParteComposta) parte;
				constructItensList(parteComposta.listaDeItens());
			}
		}
	}
	
	/**
	 * Processa a abertura de tag xml
	 **/
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("item")) {
			processaItem(attributes);
		} else if (qName.equals("partecomposta")) {
			/** Armazena a parte composta para o processamento de seus itens **/
			this.parteTemp = processaParte(attributes, new Func() {
				/** Processa os dados específicos de uma parte composta **/
				
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					return new ParteComposta(codigo, nome, descricao, valor);
				}
			});
		} else if (qName.equals("parafuso")) {
			processaParte(attributes, new Func() {
				/** Processa os dados específicos de um parafuso **/
				
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					float comprimento = Float.parseFloat(attributes.getValue("comprimento"));
					float diametro = Float.parseFloat(attributes.getValue("diametro"));
					return new Parafuso(codigo, nome, descricao, valor, comprimento, diametro);
				}
			});
		} else if (qName.equals("parteespecifica")) {
			/**
			 * Armazena a parte especifica para o processamento de suas
			 * características
			 **/
			this.parteTemp = processaParte(attributes, new Func() {
				/** Processa os dados específicos de uma parte específica **/
				
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					return new ParteEspecifica(codigo, nome, descricao, valor);
				}
			});
		} else if (qName.equals("caracteristica")) {
			processaCaracteristica(attributes);
		} else if (qName.equals("motor")) {
			processaParte(attributes, new Func() {
				/** Processa os dados específicos de um motor **/
				
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					float potencia = Float.parseFloat(attributes.getValue("potencia"));
					float corrente = Float.parseFloat(attributes.getValue("corrente"));
					int rpm = Integer.parseInt(attributes.getValue("rpm"));
					return new Motor(codigo, nome, descricao, valor, potencia, corrente, rpm);
				}
			});
		}
	}
	
	/**
	 * Processa o fechamento de uma tag xml
	 **/
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		/**
		 * Ao final da leitura de uma parte, é preciso limpar o objeto
		 * temporario de parte
		 **/
		if (qName.contains("parte"))
			this.parteTemp = null;
	}
	
	/**
	 * Interface que representa uma função para processamento de uma parte, pode
	 * ser usada para cada um dos tipos de parte para processar apenas seus
	 * dados específicos e retornar uma instância, uma vez que os dados
	 * genericos de uma parte são passados por parametro
	 **/
	private interface Func {
		public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes);
	}
	
	/**
	 * Processa uma parte do xml
	 * 
	 * @param attributes
	 *            atributos da tag atual do xml parser
	 * @param func
	 *            função que processa os atributos especificos de uma instancia
	 *            de uma parte
	 * @return retorna a parte processada
	 **/
	private Parte processaParte(Attributes attributes, Func func) {
		int codigo = Integer.parseInt(attributes.getValue("cod"));
		float valor = Float.parseFloat(attributes.getValue("valor"));
		
		Parte parte = func.montaParte(codigo, valor, attributes.getValue("nome"), attributes.getValue("descricao"), attributes);
		partes.put(codigo, parte);
		
		return parte;
	}
	
	/**
	 * Processa uma caracteristica do xml. Utiliza a variavel de controle parteTemp para
	 * saber de qual parte a caracteristica pertence
	 * 
	 * @param attributes
	 *            atributos da tag atual do xml parser
	 **/
	private void processaCaracteristica(Attributes attributes) {
		if (this.parteTemp instanceof ParteEspecifica) {
			ParteEspecifica parteEspecifica = (ParteEspecifica) this.parteTemp;
			parteEspecifica.agregaCaracteristica(attributes.getValue("nome"), attributes.getValue("conteudo"));
		}
		// else erro na estrutura do xml
	}
	
	/**
	 * Processa um item do xml. Utiliza a variavel de controle parteTemp para
	 * saber se o item processado é da lista de itens ou de uma parte composta
	 * 
	 * @param attributes
	 *            atributos da tag atual do xml parser
	 **/
	private void processaItem(Attributes attributes) {
		int codigo = Integer.parseInt(attributes.getValue("cod"));
		int quantidade = Integer.parseInt(attributes.getValue("quant"));
		
		Parte parte = new ParteEspecifica(codigo, "", "", 0.0f);
		
		if (this.parteTemp == null)
			itens.add(new Item(parte, quantidade));
		else if (this.parteTemp instanceof ParteComposta) {
			ParteComposta parteComposta = (ParteComposta) this.parteTemp;
			parteComposta.agregaItem(parte, quantidade);
		}
		// else erro na estrutura do xml
	}
	
}
