package prodPlan;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface InterfaceLab5 {
	
	public List<Item>  ItemsFromXml(String fileName) throws Exception;

}
