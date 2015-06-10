from contas import Conta
from planos import Plano
    
def criaContas():    
    def agrega(mae,filha):
        if not mae.agregaFilha(filha):
            print ('O codigo:',filha.cod,' ja existe em ', mae.cod)
    c1 = Conta('Contrato','1', 10000.0)
    c1_1 = Conta('Materiais','1.1')
    c1_1_1 = Conta('Permanente','1.1.1')
    c1_1_2 = Conta('Consumo','1.1.2')
    c1_1_3 = Conta('Uso geral','1.1.3')
    c1_2 = Conta('Servicos','1.2')
    c1_2_1 = Conta('Apoio','1.2.1')
    c1_2_2 = Conta('Transporte','1.2.2')
    c1_2_3 = Conta('Manutencao','1.2.3')
    c1_3 = Conta('Equipamentos','1.3')

    agrega(c1,c1_1)
    agrega(c1,c1_2)
    agrega(c1,c1_3)
    agrega(c1,c1_2)
    agrega(c1_1,c1_1_1)
    agrega(c1_1,c1_1_2)
    agrega(c1_1,c1_1_3)
    agrega(c1_2,c1_2_1)
    agrega(c1_1,c1_1_2)
    agrega(c1_2,c1_2_2)
    agrega(c1_2,c1_2_3)
    return c1

def agregaRaiz(plano, raiz):
    if plano.agregaContaRaiz(raiz):
        print ('conta raiz agregada ao plano')
    else:
        print ('erro ao agregar conta raiz ao plano')

def testaPlano():
    raiz = criaContas()
    print()
    plano = Plano('Plano de Contas')
    agregaRaiz(plano,raiz)
    print()
    print (plano.toString())
    print()
    print (plano.toXml())
    print()
    agregaRaiz(plano,raiz)


testaPlano()
    