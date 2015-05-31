#Representa uma conta bancaria
class Conta:
	#Construtor da classe conta que recebe o nome, codigo e saldo da conta,
	#a classe de teste dessa atividade eh incoerente com o enunciado, o 
	#enunciado determina que essa classe declare nome, codigo e descricao, 
	#no entanto, a classe de teste e o resultado esperado ignoram a existencia 
	#da descricao e exigem o saldo.
	def __init__(self, nome, cod, saldo = 0):
		self.nome = nome
		self.cod = cod
		self.saldo = saldo
		self.mae = None
		self.filhas = []

	#Agrega uma conta-filha a conta: retorna True indicando sucesso da operacao ou
	#False indicando falha (p. ex. no caso de existir uma conta filha com o mesmo
	#codigo).
	def agregaFilha(self, filha):
		#metodo auxiliar que lista o codigo de todas as contas filhas de uma conta raiz, inclusive o dela
		def listarCodigos(conta, codigos):
			codigos.append(conta.cod)
			for filha in conta.filhas:
				listarCodigos(filha, codigos)
			return codigos
		#Lista os codigos das intancia atual, inclusive o dela
		codigos = listarCodigos(self, [])

		#Verifica se a nova conta jah nao foi adicionada a essa conta
		for codigo in codigos:
			if codigo == filha.cod:
				return False

		#Adiciona a conta filha e coloca a conta mae na conta filha
		self.filhas.append(filha)
		filha.mae = self
		return True

	#Retorna a representacao em forma de string de uma conta
	def toString(self):
		#Metodo auxiliar que gera uma representacao em string 
		#de uma conta com uma identacao com base na hierarquia passada
		def toString(conta, hierarquia):
			espacos = hierarquia * '    '
			codMae = ''
			if conta.mae != None:
				codMae = conta.mae.cod
			representacao = '%s%s %s(%s) saldo:%.1f\n' %(espacos, conta.cod, conta.nome, codMae, conta.saldo)
			for filha in conta.filhas[:]:
				representacao += toString(filha, hierarquia + 1)
			return representacao
		return toString(self, 0)

	#Retorna a representacao em forma de xml de uma conta
	def toXml(self):
		#Metodo auxiliar que gera uma representacao em xml 
		#de uma conta com uma identacao com base na hierarquia passada
		def toXml(conta, hierarquia):
			espacos = hierarquia * '    '
			xml = '%s<conta cod="%s" nome="%s" saldo="%.1f"' %(espacos, conta.cod, conta.nome, conta.saldo)
			#Tratamento de tags sem filhos
			if len(conta.filhas) > 0:
				xml += '>\n'
				for filha in conta.filhas:
					xml += '%s\n' %toXml(filha, hierarquia + 1)
				xml += '%s</conta>' %espacos
			else:
				xml += '/>'
			return xml

		return toXml(self, 1)
