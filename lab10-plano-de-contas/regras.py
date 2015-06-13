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
        return '<regra nome="%s" gatilho="%.1f" dest="%s" perc="%g tipo="%s"/>' %(self.nome, self.gatilho, self.dest,self.perc, tipo)
        
    def aplica(self, plano, lancamento):
        if(lancamento.valor >= self.gatilho):
            from lancamentos import Lancamento
            novoLancamento = Lancamento(lancamento.data, self.nome, lancamento.valor * self.perc, self.tipo)
            contaDestino = plano.buscarConta(self.dest)
            contaDestino.agregaLancamento(plano, novoLancamento)