class Lancamento:
    numeroProximoLancamento = 1
    def __init__(self, data, descricao, valor, tipo):
        self.numero = numeroProximoLancamento
        numeroProximoLancamento += 1
        self.data = data
        self.descricao = descricao
        self.valor = valor
        self.tipo = tipo
    
    def toXML(self):
        tipo = ''
        if(self.tipo == 0):
            tipo = "Cr"
        else:
            tipo = "Db"
        return '<lancamento numero="%d" data="%s" valor="%.1f" tipo="%s>%s</lancamento>"' %(self.numero, self.data, self.valor, tipo, self.descricao)