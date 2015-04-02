package prodPlan;

import java.util.LinkedList;
import java.util.List;

/**
 * Cria massa de dados para testes
 *
 */

public class Dados {

	static Parte m1 = new Motor(112,"motor m112","motor de avanco do cabecote",100.0f,1.2f,1.1f,1250);
	static Parte m2 = new Motor(114,"motor m114","motor auxiliar",60.0f,0.6f,0.8f,1250);
	static Parte m3 = new Motor(111,"motor m111","motor de ventilador",70.0f,1.0f,1.0f,3000);
	static Parte m4 = new Motor(110,"motor m110","motor principal",120.0f,1.8f,1.5f,1250);
	static Parte p1 = new Parafuso(231,"parafuso p1","parafuso de fixacao do cabecote",2.5f,100.0f,8.0f);
	static Parte p2 = new Parafuso(232,"parafuso p2","parafuso de fixacao do motor",2.5f,80.0f,6.0f);
	static Parte p3 = new Parafuso(233,"parafuso p3","parafuso de fixacao do ventilador",2.0f,60.0f,6.0f);
	static Parte p4 = new Parafuso(234,"parafuso p4","parafuso de uso geral",3.0f,120.0f,12.0f);
	
	static ParteEspecifica h1 = new ParteEspecifica(321,"haste h1","haste de uso geral 1",4.0f);
	static ParteEspecifica h2 = new ParteEspecifica(322,"haste h2","haste de uso geral 2",3.0f);
	static ParteEspecifica h3 = new ParteEspecifica(323,"haste h3","haste de uso geral 3",4.5f);
	
	static ParteEspecifica e1 = new ParteEspecifica(401,"eixo e1","eixo basico 1",6.0f);
	static ParteEspecifica e2 = new ParteEspecifica(402,"eixo e2","eixo basico 2",4.5f);
	
	static ParteEspecifica b1 = new ParteEspecifica(511,"placa pk1","placa principal",6.0f);
	static ParteEspecifica b2 = new ParteEspecifica(512,"placa pk2","placa auxiliar",4.0f);
	
	static ParteComposta bf = new ParteComposta(1021,"base fixa bf1","base fixa de uso geral",20.0f);
	static ParteComposta bg = new ParteComposta(1022,"base giratoria bgx","base giratoria de uso geral",25.0f);
	static ParteComposta pf = new ParteComposta(2043,"plataforma p1","plataforma principal",48.0f);
	static ParteComposta k1 = new ParteComposta(1023,"kit de reposicao k1","kit de reposicao da plataforma p1",0.0f);

	private static void agregaPartesECaracteristicas() {
		try {
			h1.agregaCaracteristica("comprimento", "500");
			h1.agregaCaracteristica("largura", "45");
			h1.agregaCaracteristica("espessura", "12");
			
			h2.agregaCaracteristica("comprimento", "600");
			h2.agregaCaracteristica("largura", "45");
			h2.agregaCaracteristica("espessura", "12");
			
			h3.agregaCaracteristica("comprimento", "800");
			h3.agregaCaracteristica("largura", "45");
			h3.agregaCaracteristica("espessura", "12");
			
			e1.agregaCaracteristica("comprimento", "350");
			e1.agregaCaracteristica("diametro", "45");
			
			e2.agregaCaracteristica("comprimento", "450");
			e2.agregaCaracteristica("diametro", "30");
			
			b1.agregaCaracteristica("comprimento", "500");
			b1.agregaCaracteristica("largura", "450");
			b1.agregaCaracteristica("espessura", "3");
			
			b2.agregaCaracteristica("comprimento", "400");
			b2.agregaCaracteristica("largura", "320");
			b2.agregaCaracteristica("espessura", "3");
			b2.agregaCaracteristica("obs","recorte especial");
		} catch (Exception e) {
			System.err.println("Erro ao tentar agregar caracteristica:"+e.getMessage());
			e.printStackTrace();
		}
		
		try {
			bf.agregaItem(p1, 1);
			bf.agregaItem(p2, 4);
			bf.agregaItem(p3, 3);
			bf.agregaItem(h1, 5);
			bf.agregaItem(h2, 6);
			bf.agregaItem(b1, 2);
			
			bg.agregaItem(p2, 2);
			bg.agregaItem(p1, 1);
			bg.agregaItem(e1, 3);
			bg.agregaItem(m1, 5);
			bg.agregaItem(h1, 7);
			bg.agregaItem(b1, 6);
			bg.agregaItem(b2, 4);
			
			pf.agregaItem(p1, 10);
			pf.agregaItem(p2, 1);
			pf.agregaItem(bf, 3);
			pf.agregaItem(h1, 2);
			pf.agregaItem(e1, 4);
			pf.agregaItem(e2, 7);
			pf.agregaItem(bg, 5);
			pf.agregaItem(b2, 6);
			pf.agregaItem(m1, 8);
			
			k1.agregaItem(p1, 2);
			k1.agregaItem(p2, 6);
			k1.agregaItem(h1, 1);
			k1.agregaItem(e1, 4);
			k1.agregaItem(e2, 5);
			k1.agregaItem(b2, 3);
		} catch (Exception e) {
			System.err.println("Erro ao tentar agregar item:"+e.getMessage());
			e.printStackTrace();
		}
		
	}

	public static List<Item> criaListaItens(){
		agregaPartesECaracteristicas();
		List<Item> lista = new LinkedList<Item>();
		lista.add(new Item(bf,1));
		lista.add(new Item(bg,1));
		lista.add(new Item(pf,1));
		lista.add(new Item(k1,1));
        return lista;
	}

}
