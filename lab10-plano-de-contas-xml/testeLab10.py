from contas import Conta
from planos import Plano
from lancamentos import Lancamento
from regras import Regra

'''
    MC 302 EF - 1s 2015
    Programa de testes p/ atividade 10
'''
           
'''
    Cria estrutura de contas a ser usada no teste
'''
def criaContas():    
    def agrega(mae,filha):
        if not mae.agregaFilha(filha):
            print 'O codigo:',filha.cod,' ja existe em ', mae.cod
    r1 = Conta('Receitas','r1')
    agrega(r1,Conta('Produtos','r1.p'))
    agrega(r1,Conta('Servicos','r1.s'))
    d1 = Conta('Despesas', 'd1')
    agrega(d1,Conta('Pessoal','d1.p'))
    agrega(d1,Conta('Servicos','d1.s'))
    m = Conta('Material','d1.m')
    agrega(m,Conta('Consumo','d1.m.c'))
    agrega(m,Conta('Permanente','d1.m.p'))
    agrega(d1,m)
    im = Conta('Impostos','d1.i')
    agrega(im,Conta('IR','d1.i.ir'))
    agrega(im,Conta('ISS','d1.i.iss'))
    agrega(im,Conta('ICMS','d1.i.icms'))
    agrega(d1,im)
    c = Conta('Principal','p')
    agrega(c,r1)
    agrega(c,d1)
    return c

'''
    agrega uma regra a uma conta de um plano de contas
'''
def agregaRegra(plano, regra, codConta):
    if not plano.agregaRegra(regra, codConta):
        print 'testLab10: erro ao agregar regra a conta - codConta:',codConta

'''
    cria um conjunto de regras que sao associadas a contas de um dado plano
'''    
def criaRegras(plano):
    agregaRegra(plano,Regra('IR s/ produto', 0.0, 'd1.i.ir', 0.3, 0), 'r1.p')
    agregaRegra(plano,Regra('ICMS s/ produto', 0.00, 'd1.i.icms', 0.05, 0), 'r1.p')
    agregaRegra(plano,Regra('IR s/ servicos', 0.0, 'd1.i.ir', 0.2, 0), 'r1.s')
    agregaRegra(plano,Regra('ISS s/ servicos', 0.0, 'd1.i.iss', 0.05, 0), 'r1.s')
    agregaRegra(plano,Regra('abat. IR s/ pessoal', 0.0, 'd1.i.ir', 0.1, 1), 'd1.p')
    agregaRegra(plano,Regra('abat. ISS s/ servicos', 0.0, 'd1.i.iss', 0.01, 1), 'd1.s')
    agregaRegra(plano,Regra('abat. ICMS s/ mat. perm.', 0.0, 'd1.i.icms', 0.01, 1), 'd1.m.p')
    agregaRegra(plano,Regra('abat. ICMS s/ mat. perm.', 0.0, 'd1.i.icms', 0.01, 1), 'xBluft')
   
'''
    agrega uma 'conta raiz' a um plano
'''
def agregaRaiz(plano, raiz):
    if plano.agregaContaRaiz(raiz):
        print 'tstLab10: conta raiz agregada ao plano'
    else:
        print 'testLab10: erro ao agregar conta raiz ao plano'

# seq. de datas usadas nos lancamentos
data = ['10/05/15','15/05/15','20/05/15','25/05/15','30/05/15','3/05/15']

'''
    agrega um lancamento a um dado plano
'''    
def agregaLancamento(plano, lancamento, codConta):
    if not plano.agregaLancamento(lancamento, codConta):
        print 'testeLab10: erro ao agregar lancamento - codConta:'+codConta

'''
    cria uma sequencia de lancamentos de receitas (R) que sao agregados a um dado plano
'''    
def criaLancamentosR(plano):
    #produtos
    agregaLancamento(plano,Lancamento(data[0], '100 caixas ProX Plus',   1200.0, 0), 'r1.p')
    agregaLancamento(plano,Lancamento(data[1], '200 caixas Limp Max',   30000.0, 0), 'r1.p')
    agregaLancamento(plano,Lancamento(data[2], '50 pacotes XMais Pro',   5000.0, 0), 'r1.p')
    agregaLancamento(plano,Lancamento(data[3], '40 galoes  Det Max XX',  4000.0, 0), 'r1.p')
    agregaLancamento(plano,Lancamento(data[4], '150 barris Super SolvX',24000.0, 0), 'r1.p')
    agregaLancamento(plano,Lancamento(data[5], '12 caixas Limp Pro',     2400.0, 0), 'r1.p')
    #servicos
    agregaLancamento(plano,Lancamento(data[0], 'Curso R4',              12000.0, 0), 'r1.s')
    agregaLancamento(plano,Lancamento(data[1], 'Treinamento T3',        15000.0, 0), 'r1.s')
    agregaLancamento(plano,Lancamento(data[2], 'Curso R2',              12000.0, 0), 'r1.s')
    agregaLancamento(plano,Lancamento(data[3], 'Consultoria',           10500.0, 0), 'r1.s')
    agregaLancamento(plano,Lancamento(data[3], 'Alocacao',              20200.0, 0), 'r1.s')

'''
    cria uma sequencia de lancamentos de despesas (D) e os agrega a um dado plano
'''
def criaLancamentosD(plano):
    agregaLancamento(plano, Lancamento(data[0], 'Suporte TI',     700.0, 0), 'd1.s') # servicos
    agregaLancamento(plano, Lancamento(data[1], 'Folha Total',  32000.0, 0), 'd1.p') # pessoal
    agregaLancamento(plano, Lancamento(data[2], 'Papel A4',       200.0, 0), 'd1.m.c') # mat. consumo
    agregaLancamento(plano, Lancamento(data[3], 'Toner',          300.0, 0), 'd1.m.c')
    agregaLancamento(plano, Lancamento(data[4], 'Impressora',     340.0, 0), 'd1.m.p') # mat permanente
    agregaLancamento(plano, Lancamento(data[5], 'LapTop XBluft', 2400.0, 0), 'd1.m.p')
    agregaLancamento(plano, Lancamento(data[4], 'Lixeira',        120.0, 0), 'd1.m.p')
    agregaLancamento(plano, Lancamento(data[4], 'Lixeira',        120.0, 0), 'xBluft')

''' 
    escreve na console o historico de uma conta de um dado plano
'''    
def prHist(plano, codConta, hdr):
    print '========== ', hdr, ' ==========='
    print plano.historicoToXML(codConta)
    print
   
'''
    escreve o 'resumo' do movimento das contas
'''
def prResumo(plano):    
    despesas = plano.total('d1')
    receita  = plano.total('r1')
    impostos = plano.total('d1.i')
    print '======== Resumo =========='
    print 'receita:', str(receita)
    print 'despesas:', str(despesas)
    print 'saldo:', str(receita-despesas)
    print 'impostos:', str(impostos)
    print
   
'''
    Cria um plano de contas e associa ao mesmo regras e lancamentos
'''
def criaPlanoContasLancamentos():    
    raiz = criaContas()
    print
    plano = Plano('Plano de Contas')
    agregaRaiz(plano,raiz)
    criaRegras(plano)
    criaLancamentosR(plano)
    criaLancamentosD(plano)
    return plano
   
'''
    Teste geral
'''
def testaPlano():
    plano = criaPlanoContasLancamentos()
    print "======== Plano de Contas ======="
    print plano.toXml()
    print
    prHist(plano,'r1.p', 'lancamentos: Produtos')
    prHist(plano,'r1.s',  'lancamentos: Servicos')
    prHist(plano,'r1',    'lancamentos: Receita')
    prHist(plano,'d1',    'lancamentos: Despesas')
    prHist(plano,'d1.i',  'lancamentos: Impostos')
    prHist(plano,'xBluft','lancamentos: xBluft')
    prResumo(plano)

testaPlano()
#testaLancamentos()    
#testaRegras()