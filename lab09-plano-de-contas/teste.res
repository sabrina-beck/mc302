O codigo: 1.2  ja existe em  1
O codigo: 1.1.2  ja existe em  1.1

conta raiz agregada ao plano

Plano:Plano de Contas
1 Contrato() saldo:10000.0
    1.1 Materiais(1) saldo:0.0
        1.1.1 Permanente(1.1) saldo:0.0
        1.1.2 Consumo(1.1) saldo:0.0
        1.1.3 Uso geral(1.1) saldo:0.0
    1.2 Servicos(1) saldo:0.0
        1.2.1 Apoio(1.2) saldo:0.0
        1.2.2 Transporte(1.2) saldo:0.0
        1.2.3 Manutencao(1.2) saldo:0.0
    1.3 Equipamentos(1) saldo:0.0


<plano nome="Plano de Contas">
    <conta cod="1" nome="Contrato" saldo="10000.0">
        <conta cod="1.1" nome="Materiais" saldo="0.0">
            <conta cod="1.1.1" nome="Permanente" saldo="0.0"/>
            <conta cod="1.1.2" nome="Consumo" saldo="0.0"/>
            <conta cod="1.1.3" nome="Uso geral" saldo="0.0"/>
        </conta>
        <conta cod="1.2" nome="Servicos" saldo="0.0">
            <conta cod="1.2.1" nome="Apoio" saldo="0.0"/>
            <conta cod="1.2.2" nome="Transporte" saldo="0.0"/>
            <conta cod="1.2.3" nome="Manutencao" saldo="0.0"/>
        </conta>
        <conta cod="1.3" nome="Equipamentos" saldo="0.0"/>
    </conta>
</plano>


erro ao agregar conta raiz ao plano