#Representa um plano de contas
#@author Sabrina Beck Angelini<157240>
class Plano:
	contaRaiz = None

	#inicializa os atributos do plano
	def __init__(self, nome, descricao = ''):
		self.nome = nome
		self.descricao = descricao

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

	#Retorna uma representacao em string do plano de contas
	def toString(self):
		return 'Plano:' + self.nome + '\n' + self.contaRaiz.toString()

	#Retorna uma representacao em xml do plano de contas
	def toXml(self):
		return '<plano nome="%s">\n%s\n</plano>\n' %(self.nome, self.contaRaiz.toXml())



