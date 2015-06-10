class Regra:
    def __init__(self, nome, gatilho, dest, perc, tipo):
        self.nome = nome
        self.gatilho = gatilho
        self.dest = dest
        self.perc = perc
        self.tipo = tipo

    def toXML(self):
        tipo = ''
        if(self.tipo == 0):
            tipo = "Cr"
        else:
            tipo = "Db"
        return '<regra nome="%s" dest="%s" perc="%.2f" tipo="%s"/>' %(self.nome, self.dest,self.perc, tipo)
        
    def aplica(self, plano, lancamento):
        if(lancamento.valor >= self.gatilho):
            #qual a data e a descrição?
            novoLancamento = Lancamento(lancamento.data, lancamento.descricao, lancamento.valor * self.perc, self.tipo)
            #TODO: aplicar novoLancamentoa ao dest