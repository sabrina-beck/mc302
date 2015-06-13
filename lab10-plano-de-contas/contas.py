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
		self.lancamentos = []
		self.regras = []

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

	def buscarContaFilha(self, codConta):
		for conta in self.filhas:
			if conta.cod == codConta:
				return conta
			possivelFilha = conta.buscarContaFilha(codConta)
			if possivelFilha != None:
				return possivelFilha
		return None

	def agregaLancamento(self, plano, lancamento):
		for lanc in self.lancamentos:
			if lanc.numero == lancamento.numero:
				return False
		for regra in self.regras:
			regra.aplica(plano, lancamento)
		#FIXME: pq recebe o plano?
		self.lancamentos.append(lancamento)
		return True

	def agregaRegra(self, regra):
		self.regras.append(regra)

	def total(self):
		soma = 0.0
		for lancamento in self.lancamentos:
			if lancamento.tipo == 0:
				soma += lancamento.valor
			else:
				soma -= lancamento.valor
		for conta in self.filhas:
			soma += conta.total()
		return soma

	def historico(self):
		def buscarTodosLancamentos (conta, lancamentos):
			lancamentos += conta.lancamentos
			for filha in conta.filhas:
				buscarTodosLancamentos(filha, lancamentos)
			return lancamentos
		hist = buscarTodosLancamentos(self, [])
		return sorted(hist, key=lambda l: l.numero, reverse=False)

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
			espacosNoFilho = (hierarquia + 1) * '    '
			xml = '%s<conta cod="%s" nome="%s">\n' %(espacos, conta.cod, conta.nome)
			
			#if len(conta.filhas) != 0 or len(conta.regras) != 0:
			#	xml += '>\n'
				
			if len(conta.regras) > 0:
				for regra in conta.regras:
					xml += '%s%s\n' %(espacosNoFilho, regra.toXML())
			
			if len(conta.filhas) > 0:
				for filha in conta.filhas:
					xml += '%s\n' %toXml(filha, hierarquia + 1)
					
			#if len(conta.filhas) == 0 and len(conta.regras) == 0:
			#	xml += '/>'
			#else:
			xml += '%s</conta>' %espacos

			return xml

		return toXml(self, 1)
