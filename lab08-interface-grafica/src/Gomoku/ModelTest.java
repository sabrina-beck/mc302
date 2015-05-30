package Gomoku;

public class ModelTest {
	
	static String[] plStr = { null, "preto", "branco" };
	static int[][] seq1 = { 
		                 // preto, branco 
		                    {0,0}, {1,0},
		                    {0,1}, {1,1},
		                    {0,2}, {1,2},
		                    {0,3}, {1,3},
		                    {0,4}, {1,4}
						   };              // jogador 1 (preto) ganha
	
	static int[][] seq2 = {
		                 // preto, branco
		                    {0,0}, {0, 1},
		                    {1,0}, {1, 1},
		                    {2,0}, {2, 1},
		                    {3,0}, {3, 1},
		                    {4,0}, {4, 1}
						  };                // preto ganha
	
	static int[][] seq3 = {
						 // preto, branco
		                    {0,0}, {0,1},
		                    {1,1}, {1,2},
		                    {2,2}, {2,3},
		                    {3,3}, {3,4},
		                    {4,4}, {4,5}
	                      };                // preto ganha
	
	static int[][]  seq4 = {
		                // preto, branco
		                   {0,4}, {0,5},
		                   {1,3}, {1,4},
		                   {2,2}, {2,3},
		                   {3,1}, {3,2},
		                   {4,0}, {4,1}
						 };                 // preto ganha
	
	static int[][]  seq5 = {
        					// preto, branco
           					{0,4}, {0,5},
           					{1,3}, {1,4},
           					{2,2}, {2,3},
           					{3,1}, {2,2},   // <==
           					{4,0}, {4,1}
		 					};              // jogada invalida

	static int[][] seq6= { 
        				 // preto, branco 
           				    {0,0}, {1,0},
           				    {0,1}, {1,1},
           				    {0,6}, {1,2},
           				    {0,3}, {1,3},
           				    {0,4}, {1,4}
		   				  };              // Branco ganha


	static int[][] seq7 = {
        				  // preto, branco
           					 {0,0}, {0, 1},
           					 {1,0}, {1, 1},
           					 {2,0}, {2, 1},
           					 {3,0}, {3, 1},
           					 {4,2}, {4, 1}
		  				   };                // branco ganha

	
	static int[][] seq8 = {
		                  // preto, branco
                             {0,0}, {0,1},
                             {1,1}, {1,2},
                             {2,2}, {2,3},
                             {3,2}, {3,4},
                             {4,4}, {4,5}
         					};                // branco ganha
	
	static int[][]  seq9 = {
        				   // preto, branco
           				      {0,4}, {0,5},
           				      {1,3}, {1,4},
           				      {2,2}, {2,3},
           				      {3,3}, {3,2},
           				      {4,0}, {4,1}
		 					};                 // branco ganha


	static int[][]  seq10 = {
		                    // preto, branco
			                   {0,4}, {0,5},
			                   {1,3}, {1,4},
			                   {2,2}, {2,3},
			                   {3,1}, {3,19},   // <==
			                   {4,0}, {4,1}
			 			     };              // jogada invalida
	
	static int[][] seq11 = {
		{1, 1}, {6, 8},
		{3, 3}, {6, 9},
		{1, 3}, {6,10},
		{6,11}, {7, 8},
		{3, 1}, {2, 1},
		{2, 2}, {4, 4},
		{3, 2}, {3, 4},
		{1, 2}, {1, 4},
		{2, 4}, {4, 2},
		{4, 0}, {0, 4},
		{4, 3}, {8, 7},
		{6, 6}, {2, 3},
		{6, 3}, {5,10},
		{6, 5}, {5, 6},
		{4, 5}, {8, 6},
		{9, 5}, {4,11}
	};

	static int[][] seq12 = {
		{1, 1}, {6, 8},
		{3, 3}, {6, 9},
		{1, 3}, {6,10},
		{6,11}, {7, 8},
		{3, 1}, {2, 1},
		{2, 2}, {4, 4},
		{3, 2}, {3, 4},
		{1, 2}, {1, 4},
		{2, 4}, {4, 2},
	};
	
	static int[][] seq13 = {
		{4, 0}, {0, 4},
		{4, 3}, {8, 7},
		{6, 6}, {2, 3},
		{6, 3}, {5,10},
		{6, 5}, {5, 6},
		{4, 5}, {8, 6},
		{9, 5}, {4,11}
		
	};
	
	


	static void test(String title, int[][] seq){
		System.out.println("==> Iniciando "+ title);
		Model model = new Model();
		model.iniciaPartida();
		int player = Model.BLACK;
		for(int i = 0; i < seq.length; i++){
			System.out.println(i+": "+  plStr[player] + " x:" + seq[i][0]+" y:" + seq[i][1]);
			if(! model.joga(player, seq[i][0], seq[i][1])){ 
				System.out.println("Jogada invalida!");
				return;
			}
			int v = model.verifica();
			if( v != Model.NONE) {
//				model.print();
				System.out.println("==> " + plStr[v] + " venceu !!! ");
				return;
			}
	
			if(player == Model.BLACK) player = Model.WHITE;
			else player = Model.BLACK;
		}
//		model.print();
		System.out.println("==> Nenhum vencedor !");
	}
	
	static boolean saveTest(String name, int[][] seq){
		System.out.println("==> Iniciando saveTest("+ name + ")");
		Model model = new Model();
		model.iniciaPartida();
		int player = Model.BLACK;
		for(int i = 0; i < seq.length; i++){
			System.out.println(i+": "+  plStr[player] + " x:" + seq[i][0]+" y:" + seq[i][1]);
			if(! model.joga(player, seq[i][0], seq[i][1])){ 
				System.out.println("Jogada invalida!");
				return false;
			}
			int v = model.verifica();
			if( v != Model.NONE) {
				System.out.println("==> " + plStr[v] + " venceu !!! ");
				return false ;
			}
	
			if(player == Model.BLACK) player = Model.WHITE;
			else player = Model.BLACK;
		}
		if (model.salvaPartida(name)) {
			System.out.println("==> partida salva !");
			return true;
		} else return false;
	}
	
	static void restoreTest(String name, int[][] seq){
		System.out.println("==> Iniciando restoreTest("+ name + ")");
		Model model = new Model();
		if(!model.carregaPartida(name)) {
			System.out.println("Erro ao carregar partida do arquivo:"+name);
			return;
		}
	
		int player = Model.BLACK;
		for(int i = 0; i < seq.length; i++){
			System.out.println(i+": "+  plStr[player] + " x:" + seq[i][0]+" y:" + seq[i][1]);
			if(! model.joga(player, seq[i][0], seq[i][1])){ 
				System.out.println("Jogada invalida!");
				return;
			}
			int v = model.verifica();
			if( v != Model.NONE) {
//				model.print();
				System.out.println("==> " + plStr[v] + " venceu !!! ");
				return;
			}
	
			if(player == Model.BLACK) player = Model.WHITE;
			else player = Model.BLACK;
		}
//		model.print();
		System.out.println("==> Nenhum vencedor !");	
	}
	
	public static void main(String[] args){
		System.out.println("inicio");
		test("Primeira Partida", seq1);
		test("Segunda Partida",  seq2);
		test("Terceira Partida", seq3);
		test("Quarta Partida",   seq4);
		test("Quinta Partida",   seq5);
		
		if(saveTest("partida5_b.gmk", seq12)) System.out.println("Partida 5_b salva");
		else System.out.println("Partida 5_b nao foi salva");
		
		test("Sexta Partida",    seq6);
		test("Setima Partida",   seq7);
		test("Oitava Partida",   seq8);
		test("Nona Partida",     seq9);
		test("Decima Partida",   seq10);
		test("Decima primeira",  seq11);
		
		restoreTest("partida5_b.gmk", seq13);
		System.out.println("fim");
	}

}
