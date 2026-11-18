---
title: Seguimento R+L Carriers: número PRO, estados e reclamações
description: Segue uma remessa de carga da R+L Carriers nos EUA: formato do número PRO, significado de cada linha de rastreio e prazos em caso de atraso, dano ou perda.
slug: seguimento-rl-carriers
published: 2026-11-18
updated: 2026-11-18
---

A R+L Carriers é uma transportadora rodoviária de mercadorias nos **Estados Unidos**. No código do Peek, recolhe a carga nas instalações do expedidor, transporta-a de terminal em terminal e entrega-a a partir do terminal de destino nos EUA. Se uma encomenda de grande porte ou outra carga te está a ser enviada nos Estados Unidos pela R+L, segues o envio pelo número PRO, indicado pela loja ou pela empresa que reservou o transporte: cola-o na caixa acima. O rastreador próprio da R+L é a página *Shipment Tracing* em rlcarriers.com: aceita um número PRO ou um identificador de pedido de recolha (*Pickup Request ID*), até 25 números de uma só vez, e, com uma conta MyRLC, também outras referências.

## Número de seguimento da R+L Carriers: o número PRO

O rastreador da R+L pesquisa remessas pelo número PRO. O Peek reconhece estes formatos, exemplificados abaixo com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `48291057-3` | Nove algarismos, sendo o último um dígito de controlo escrito aqui depois de um hífen. |
| `482910573` | O mesmo número PRO escrito sem hífen. |
| `RL482910-5` | Duas letras e sete algarismos, o último deles um dígito de controlo. Também pode aparecer sem hífen (`RL4829105`). |
| `I482910573` | Um número PRO tal como o escreve o rastreador da R+L, com um `I` no início. Esse `I` não faz parte do número: o Peek aceita o PRO com ou sem ele. |

O rastreador da R+L valida o último algarismo, pelo que um erro de digitação é detetado (ver abaixo). O Peek retira espaços e hífenes antes de consultar a R+L. No catálogo do Peek, a R+L Carriers é igualmente conhecida como R&L Carriers, R and L Carriers e RL Carriers.

**Onde encontrá-lo.** No e-mail de expedição da loja ou na página da encomenda; para carga enviada entre empresas, pede-o à empresa remetente. Vê [como encontrar o teu número de seguimento](guide:find-tracking-number).

**Outras transportadoras com o mesmo formato.** Nove algarismos isolados encaixam também na [TNT](carrier:tnt), na TForce Freight e na XPO, enquanto um `I` seguido de nove algarismos coincide com a Australia Post. Se o aviso de envio indicar uma delas, acompanha a encomenda aí. Mais pormenores em [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de seguimento da R+L Carriers

O rastreador da R+L resume o estado da remessa acima do histórico e lista por baixo cada registo com data e hora. Identifica os terminais norte-americanos pela cidade e pela sigla do estado. As linhas não trazem código numérico: o Peek interpreta o texto em inglês. Pela ordem habitual de uma remessa nos **Estados Unidos** (as reticências representam uma cidade e estado, ou um terminal):

| A R+L escreve | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Picked Up in …` | A R+L recolheu a carga no expedidor, na localidade deste. O Peek mantém a frase completa sem usar a cidade do remetente como localização da leitura. | «Aceite pela transportadora» |
| `Arrived at origin terminal` | A mercadoria chegou ao terminal da R+L na área de origem. | «Em trânsito» |
| `Departed … in route to …` | Saiu de um terminal rumo ao seguinte ou ao terminal de destino. | «Em trânsito» |
| `Arrived at …` | Chegou a um terminal intermédio do percurso, identificado pela cidade e estado nos EUA. | «Em trânsito» |
| `Arrived at destination terminal` | Encontra-se no terminal da tua região, última paragem antes da entrega. | «Em trânsito» |
| `Shipment Delayed` | Algum imprevisto reteve o envio. O resumo da R+L classifica então a remessa como atrasada (*Delayed*) e mantém o dia previsto de entrega. | «Em trânsito» |
| `Delivered` | Entregue. | «Entregue» |

**O resumo no topo.** Acima das linhas do histórico, o rastreador da R+L classifica a remessa como *In Transit*, *Delayed* ou *Delivered*. Quando esse resumo está mais adiantado do que as linhas detalhadas, o Peek segue o resumo: uma remessa resumida como entregue surge como «Entregue» mesmo que a linha mais recente ainda não o diga. Quando o rastreador devolve resumo mas ainda sem linhas de histórico, o Peek mostra apenas o estado.

O código do Peek não conhece formulações próprias da R+L para saída para distribuição, tentativa falhada, incidente ou devolução. Outras frases passam pelas regras gerais que o Peek aplica a todas as transportadoras. Uma linha que nenhuma regra reconheça continua visível tal como foi escrita e conta como sinal de movimento («Em trânsito») antes da entrega; depois de entregue, já nada altera. Mais informações em [estados de seguimento explicados](guide:tracking-statuses).

## Como a R+L Carriers entrega nos Estados Unidos

Enquanto a carga circula nos **Estados Unidos**, o rastreador da R+L indica um dia previsto de entrega, que o Peek apresenta até à receção. O rastreador da R+L não refere nenhuma outra transportadora para a entrega final e o código do Peek não transfere o envio para terceiros. A R+L Carriers não opera distribuição doméstica em Portugal nem no Brasil: esta página diz respeito apenas a remessas de carga nos Estados Unidos.

Esta página não indica janelas horárias, marcações de entrega, número de tentativas nem regras para levantar carga num terminal norte-americano. Pergunta à loja ou à empresa que reservou o transporte como e quando a mercadoria chega à tua porta: são elas que detêm a reserva junto da R+L.

## Remessa da R+L Carriers não encontrada, atrasada, danificada ou perdida

**Não encontrada.** Para um número PRO que não conste no sistema, o rastreador da R+L responde `No records found for Number:` seguido do PRO com um `I` à frente. Em peektracker.com, o Peek mostra então «À espera de R+L Carriers» para um PRO associado à R+L Carriers e volta a verificar mais tarde. Confere cada algarismo com o e-mail da loja; se o número estiver certo, pergunta ao expedidor se a R+L já recolheu a carga.

**Algarismo errado.** Se o dígito de controlo de um PRO falhar a validação, o rastreador da R+L devolve o PRO seguido de `Pro number failed check digit validation.` O Peek apresenta nesse caso «Verifica o número de seguimento». Compara o código algarismo a algarismo com o e-mail da loja ou com os documentos de transporte.

**Atrasada ou sem atualizações.** Após `Shipment Delayed`, o Peek mantém a remessa «Em trânsito» com o dia estimado pela R+L. Ao fim de quatro dias sem nova linha numa remessa em «Aceite pela transportadora» ou «Em trânsito», o Peek assinala «Sem atualizações há quatro dias». Fala com a loja ou com o remetente. Vê também [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Mercadoria danificada, em falta ou perdida nos EUA.** Comunica primeiro à loja ou à empresa expedidora que contratou o transporte. Para carga transportada por rodovia **entre estados dos EUA**, a lei federal dos **Estados Unidos** (`49 U.S.C. 14706` e `49 CFR Part 370`) fixa estas regras:

- O transportador responde pela perda ou dano real causado à mercadoria perante a pessoa legitimada pelo recibo ou conhecimento de embarque (*bill of lading*). A responsabilidade pode ser limitada a um valor declarado por escrito ou por via eletrónica pelo expedidor, ou acordado por escrito entre o transportador e o expedidor.
- O transportador não pode conceder menos de 9 meses para apresentar uma reclamação nem menos de 2 anos para recorrer a tribunal, contados da data em que notifique por escrito a recusa de qualquer parte da reclamação. O prazo concreto consta no conhecimento de embarque ou contrato.
- Uma reclamação é um pedido escrito apresentado ao transportador dentro desses prazos que identifique a remessa, responsabilize o transportador e exija uma quantia em dinheiro determinada ou determinável. Uma simples nota de avaria ou falta na guia de entrega não vale, por si só, como reclamação.
- Sempre que necessário para a averiguação, a reclamação deve ser acompanhada do conhecimento de embarque, do comprovativo dos encargos de frete e da fatura. Na perda total de um volume ou de toda a remessa que não possa ser confirmada de outro modo, o transportador obtém do destinatário (*consignee*) uma declaração escrita certificada de que a carga não chegou por qualquer outra via.
- O transportador acusa por escrito a receção da reclamação no prazo de 30 dias. Dispõe de 120 dias para pagar, recusar ou apresentar por escrito uma proposta firme de acordo; se ainda não puder encerrar o processo, informa por escrito sobre o estado do pedido nesse momento e a cada 60 dias seguintes.

Se o seguimento indicar `Delivered` e nada tiver chegado, consulta [entregue mas não recebida](guide:delivered-not-received). Lê também [encomendas danificadas](guide:damaged-parcel) e [encomendas perdidas](guide:lost-parcel).

## Apoio ao cliente e contactos da R+L Carriers

No site norte-americano da R+L Carriers encontram-se estes contactos:

- **Telefone:** `800-543-5589`, linha gratuita nos EUA exibida no topo das páginas da R+L.
- **R+L Cares:** formulário no site da R+L para questões pendentes, dúvidas ou sugestões; a R+L refere que o pedido segue diretamente para a sua direção executiva (*R+L Executive Leadership*).
- **Reclamações de carga:** o menu do site remete para *Cargo Claims*, com as instruções da R+L para participar sinistros de mercadoria.

A R+L efetua o transporte por conta de quem o contratou: contacta primeiro a loja nas compras online ou a empresa remetente na carga empresarial.

## Seguir uma remessa da R+L Carriers com o Peek

O formato de um PRO de nove algarismos não basta para identificar a R+L, pois outras transportadoras também usam nove dígitos. Cola-o na mesma: o Peek acompanha mais de 3500 transportadoras. Para duas letras e sete algarismos, o Peek consulta de imediato a R+L e mostra «R+L Carriers tem esta encomenda.» quando a R+L reconhece o PRO. Se introduzires nove algarismos com hífen antes do último dígito, o Peek lista a R+L Carriers em «Corresponde a este número» e também a consulta; contudo, depois de guardada a encomenda (o Peek retira o hífen ao guardar) ou se colares nove algarismos sem hífen, o Peek interroga a TNT, a TForce Freight e a XPO quando passam na validação delas, mas não a R+L: toca em «Alterar» e procura R+L Carriers pelo nome. Numa remessa já adicionada, usa «Alterar transportadora». Se entretanto um serviço de seguimento mostrar a carga em andamento e indicar a R+L Carriers, o Peek arquiva o envio na R+L e passa a consultar o rastreador da R+L nas verificações seguintes.

Com o `I` antes de nove algarismos, o código ajusta-se à R+L Carriers e à Australia Post: o Peek consulta ambas e guarda o envio naquela que o conhecer. Uma ligação para a página *Shipment Tracing* da R+L com `pro=` também funciona: o Peek identifica logo a R+L («Transportadora detetada»).

O Peek consulta o rastreador da R+L apenas pelo número PRO, sem conta nem código postal. Apresenta o estado, cada linha do histórico (com cidade e estado do terminal quando a linha os menciona), o dia previsto de entrega, o nome do destinatário na tua própria vista («Destinatário», oculto em ligações partilhadas) e o peso nas unidades do teu dispositivo (libras nos dispositivos com sistema de medida norte-americano, quilogramas nos restantes; a R+L fornece libras). Deixa de fora a morada do destinatário, o remetente e a entidade faturada, a contagem de volumes, as referências, o tipo de serviço, o número do conhecimento de embarque e os documentos. O rastreador da R+L não fornece comprovativo de entrega ao Peek. Na página do envio, «Abrir o site de R+L Carriers» abre diretamente o PRO no rastreador da R+L.

Quando o rastreador da R+L está inacessível ou não consegue ler o envio, o Peek recorre a [serviços de seguimento](guide:universal-tracker) e mostra o histórico destes nas suas próprias palavras.

As linhas da R+L indicam data e hora sem fuso horário. O Peek lê cada registo pelo relógio da cidade do terminal, na falta dele pelo do estado do terminal (o fuso principal quando um estado norte-americano tem mais de um), senão pelo da linha anterior ou seguinte e, em último caso, na hora da Costa Leste dos EUA (`America/New_York`), convertendo todas as horas para o fuso do teu dispositivo («Horas no teu fuso horário: …»).

O Peek verifica novidades até de 10 em 10 minutos enquanto manténs o envio aberto, ou no dia da entrega com as notificações ativas; no resto do tempo, e após dois dias sem novas linhas (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes ativar alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a R+L, a loja ou o expedidor por ti, agendar entregas nem submeter reclamações.

## Perguntas frequentes sobre o seguimento da R+L Carriers

### O que é um número PRO na R+L Carriers?

É o número de seguimento de carga da R+L Carriers nos Estados Unidos: nove algarismos, ou duas letras e sete algarismos, sendo o último um dígito de controlo por vezes escrito após um hífen. O rastreador da R+L acrescenta-lhe um `I` no início. É fornecido pela loja ou empresa que contratou o frete.

### Porque é que a R+L Carriers indica «No records found for Number»?

O rastreador da R+L não encontra esse número PRO. Confere cada algarismo com o e-mail de envio, confirma se a transportadora indicada é mesmo a R+L Carriers e pergunta ao expedidor se a R+L já levantou a mercadoria.

### O que significa «Shipment Delayed» na R+L Carriers?

Algum contratempo atrasou a mercadoria durante o percurso nos EUA. A linha não indica o motivo; o resumo da R+L passa a classificar o envio como atrasado mantendo o dia previsto de entrega, e o Peek conserva-o «Em trânsito». Pergunta o motivo à loja ou ao expedidor.

### Qual é o prazo para apresentar uma reclamação de carga à R+L Carriers?

No transporte rodoviário de carga entre estados dos EUA, a lei federal norte-americana assegura pelo menos 9 meses para apresentar reclamação escrita à transportadora e pelo menos 2 anos para recorrer aos tribunais após a recusa escrita de qualquer parte do pedido. O limite exato consta no conhecimento de embarque ou contrato; esta página não reproduz o regulamento interno da R+L. Começa por falar com quem reservou o frete; as instruções da R+L estão em *Cargo Claims* no respetivo site.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – nomes, formatos PRO e prefixo `I`, ligações de seguimento, mensagens do rastreador e estados, resumo, respostas de inexistência e dígito de controlo, dia previsto, destinatário, peso, fusos horários e campos omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras que partilham o formato, ausência de transferência para terceiros e recurso a serviços de seguimento
- [R+L Carriers: Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – pesquisa por número PRO ou Pickup Request ID até 25 envios e outras referências com conta MyRLC
- [R+L Carriers: R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – formulário encaminhado diretamente à direção executiva da R+L, telefone 800-543-5589 no topo da página e ligação Cargo Claims
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilidade perante o titular do conhecimento de embarque, valor limitado e prazos mínimos de 9 meses e 2 anos nos EUA
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – elementos obrigatórios da reclamação escrita, reservas na guia de entrega, documentos de suporte e prazos de 30, 120 e 60 dias
:::
