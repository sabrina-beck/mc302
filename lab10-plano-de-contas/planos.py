#Representa um plano de contas
#@author Sabrina Beck Angelini<157240>
class Plano:

	#inicializa os atributos do plano
	def __init__(self, nome, descricao = ''):
		self.nome = nome
		self.descricao = descricao
		self.contaRaiz = None

	def agregaContaRaiz(self, contaRaiz):
		#se o plano jah tem uma conta, nao eh possivel agregar mais contas
		#como nao eh possivel agregar contas filhas com codigo duplicado a
		#uma conta, nunca sera possivel receber uma conta raiz com contas de
		#codigo duplicado
		if self.contaRaiz != None:
			return False	

		#agrega a conta raiz
		self.contaRaiz = contaRaiz
		return True

	def buscarConta(self, codConta):
		if self.contaRaiz != None and self.contaRaiz.cod == codConta:
			return self.contaRaiz
		return self.contaRaiz.buscarContaFilha(codConta)

	def agregaLancamento(self, lancamento, codConta):
		conta = self.buscarConta(codConta)
		if conta == None:
			return False
		return conta.agregaLancamento(self, lancamento)

	def historico(self, codConta):
		conta = self.buscarConta(codConta)
		if conta == None:
			return None
		return conta.historico()

	def historicoToXML(self, codConta):
		conta = self.buscarConta(codConta)
		if conta == None:
			return '<extrato codConta="%s" ></extrato>\n' %codConta
		
		xmlLancamentos = ''
		for lancamento in conta.historico():
			xmlLancamentos += '    ' + lancamento.toXML() + '\n'
		xmlTotal = '%s<total>%.1f</total>' %('    ', conta.total())
		return '<extrato codConta="%s" nome="%s" >\n%s%s\n</extrato>\n' %(codConta, conta.nome, xmlLancamentos, xmlTotal)

	def total(self, codConta):
		conta = self.buscarConta(codConta)
		if conta == None:
			return None
		return conta.total()

	def agregaRegra(self, regra, codConta):
		conta = self.buscarConta(codConta)
		if conta == None:
			return False
		conta.agregaRegra(regra)
		return True

	#Retorna uma representacao em string do plano de contas
	def toString(self):
		return 'Plano:' + self.nome + '\n' + self.contaRaiz.toString()

	#Retorna uma representacao em xml do plano de contas
	def toXml(self):
		return '<plano nome="%s">\n%s\n</plano>\n' %(self.nome, self.contaRaiz.toXml())



