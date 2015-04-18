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

public class Lab5Builder extends DefaultHandler implements InterfaceLab5 {
	
	private Map<Integer, Parte> partes = new HashMap<Integer, Parte>();
	private List<Item> itens = new LinkedList<Item>();
	
	private Parte parteTemp;
	
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
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("item")) {
			processaItem(attributes);
		} else if (qName.equals("partecomposta")) {
			this.parteTemp = processaParte(attributes, new Func() {
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					return new ParteComposta(codigo, nome, descricao, valor);
				}
			});
		} else if (qName.equals("parafuso")) {
			processaParte(attributes, new Func() {
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					float comprimento = Float.parseFloat(attributes.getValue("comprimento"));
					float diametro = Float.parseFloat(attributes.getValue("diametro"));
					return new Parafuso(codigo, nome, descricao, valor, comprimento, diametro);
				}
			});
		} else if (qName.equals("parteespecifica")) {
			this.parteTemp = processaParte(attributes, new Func() {
				@Override
				public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes) {
					return new ParteEspecifica(codigo, nome, descricao, valor);
				}
			});
		} else if (qName.equals("caracteristica")) {
			processaCaracteristica(attributes);
		} else if (qName.equals("motor")) {
			processaParte(attributes, new Func() {
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
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.contains("parte"))
			this.parteTemp = null;
	}
	
	private Parte processaParte(Attributes attributes, Func func) {
		int codigo = Integer.parseInt(attributes.getValue("cod"));
		float valor = Float.parseFloat(attributes.getValue("valor"));
		
		Parte parte = func.montaParte(codigo, valor, attributes.getValue("nome"), attributes.getValue("descricao"), attributes);
		partes.put(codigo, parte);
		
		return parte;
	}
	
	private void processaCaracteristica(Attributes attributes) {
		if (this.parteTemp instanceof ParteEspecifica) {
			ParteEspecifica parteEspecifica = (ParteEspecifica) this.parteTemp;
			parteEspecifica.agregaCaracteristica(attributes.getValue("nome"), attributes.getValue("conteudo"));
		}
		// else erro na estrutura do xml
	}
	
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
	
	private interface Func {
		public Parte montaParte(int codigo, float valor, String nome, String descricao, Attributes attributes);
	}
	
}
