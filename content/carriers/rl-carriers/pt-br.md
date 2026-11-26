---
title: Rastreamento R+L Carriers: código PRO, status e indenizações
description: Rastreie uma carga R+L Carriers nos EUA: formato do código PRO, significado de cada etapa e o que fazer em caso de atraso ou dano no frete.
slug: rastreamento-rl-carriers
published: 2026-11-26
updated: 2026-11-26
---

A R+L Carriers é uma transportadora de cargas nos Estados Unidos. No código do Peek, ela coleta a carga com o expedidor, transfere de terminal em terminal e realiza a entrega a partir do terminal de destino. Se um pedido grande ou outro frete vier pela R+L, você rastreia pelo número PRO fornecido pela loja ou empresa que contratou o frete: cole-o no campo acima. O próprio rastreador da R+L fica na página de rastreamento em rlcarriers.com: ele aceita número PRO ou ID de solicitação de coleta, até 25 números por vez, e com conta MyRLC também aceita outras referências.

## Código de rastreamento R+L Carriers: o número PRO

O rastreador da R+L localiza cargas pelo número PRO. O Peek conhece estes formatos (com dígitos inventados):

| Código (inventado) | O que é |
| --- | --- |
| `48291057-3` | Nove dígitos, sendo o último um dígito verificador, aqui escrito após um hífen. |
| `482910573` | O mesmo número PRO sem o hífen. |
| `RL482910-5` | Duas letras e sete dígitos, sendo o último um dígito verificador. Também pode vir sem hífen. |
| `I482910573` | Um número PRO como o rastreador da R+L escreve, com um `I` na frente. O `I` não faz parte do código: o Peek aceita o PRO com ou sem ele. |

O rastreador da R+L valida o último dígito, então um erro de digitação pode ser detectado (veja abaixo). O Peek remove espaços e hífens antes de consultar a R+L. Seu catálogo também reconhece R+L Carriers como R&L Carriers, R and L Carriers e RL Carriers.

**Onde encontrar.** No e-mail de envio ou na página do pedido da loja; em fretes entre empresas, peça à empresa remetente. Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Outras transportadoras com o mesmo formato.** Nove dígitos sozinhos também servem para [TNT](carrier:tnt), TForce Freight e XPO, e um `I` seguido de nove dígitos serve para a [Australia Post](carrier:australia-post). Se o seu e-mail mencionar uma delas, rastreie lá. Saiba mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da R+L Carriers

O rastreador da R+L mostra um resumo da carga acima do histórico e depois lista cada linha com data e hora, identificando os terminais por cidade e estado. As linhas não têm código próprio: o Peek interpreta o texto. Na ordem em que a carga passa por elas, com as reticências representando cidade e estado ou terminal:

| A R+L diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Picked Up in …` | A R+L coletou a carga com o expedidor, na cidade dele. O Peek exibe o texto completo, mas não usa a cidade como local da linha. | "Recebida pela transportadora" |
| `Arrived at origin terminal` | Chegou ao terminal da R+L do lado do expedidor. | "Em trânsito" |
| `Departed … in route to …` | Saiu de um terminal rumo ao próximo ou ao terminal de destino. | "Em trânsito" |
| `Arrived at …` | Chegou a um terminal no caminho, identificado pela cidade. | "Em trânsito" |
| `Arrived at destination terminal` | Está no terminal do seu lado, a última parada antes da entrega. | "Em trânsito" |
| `Shipment Delayed` | Algo atrasou o transporte. O resumo da R+L passa a classificar a carga como atrasada e mantém um dia previsto de entrega. | "Em trânsito" |
| `Delivered` | Entregue. | "Entregue" |

**O resumo.** Acima das linhas, o rastreador da R+L classifica a remessa como em trânsito, atrasada ou entregue. Quando esse resumo está mais avançado que as linhas, o Peek segue o resumo: uma carga resumida como entregue aparece como "Entregue" mesmo que a última linha não diga isso. Quando o rastreador traz resumo mas ainda sem linhas, o Peek mostra apenas o status.

O código do Peek não conhece textos específicos da R+L para saída para entrega, entrega não realizada, problema ou devolução. Outras linhas seguem as regras gerais do Peek para todas as transportadoras. Uma linha que nenhuma regra classifica continua aparecendo como escrita e conta como carga em movimento ("Em trânsito"); após a entrega, não altera o status. Saiba mais em [status de rastreamento](guide:tracking-statuses).

## Como a R+L Carriers entrega

Enquanto a carga circula, o rastreador da R+L informa uma data prevista de entrega, exibida pelo Peek até a conclusão. O rastreador da R+L não indica outra transportadora para a entrega, e o código do Peek não transfere a remessa para nenhuma outra.

Esta página não informa prazos de entrega, agendamentos, número de tentativas nem regras de retirada em terminal. Pergunte à loja ou à empresa que contratou o frete como e quando ele chegará ao seu endereço: são elas que detêm a reserva junto à R+L.

## Carga R+L Carriers não encontrada, atrasada, danificada ou extraviada

**Não encontrada.** Para um número PRO desconhecido, o rastreador da R+L responde `No records found for Number:` seguido do PRO com um `I` na frente. O Peek exibe "Aguardando R+L Carriers" para um PRO classificado em R+L Carriers e tenta novamente depois. Confira cada dígito no e-mail da loja. Se o número estiver certo, pergunte ao expedidor se a R+L já coletou a carga.

**Um dígito errado.** Para um PRO cujo dígito verificador não fecha, o rastreador da R+L responde com o PRO seguido de `Pro number failed check digit validation.` O Peek mostra então "Confira o código de rastreamento". Compare o número, dígito por dígito, com o e-mail da loja ou os documentos de envio.

**Atrasada ou sem atualizar.** Após `Shipment Delayed`, o Peek mantém a remessa em "Em trânsito" com o dia previsto da R+L. Após quatro dias sem nova linha, uma carga em "Recebida pela transportadora" ou "Em trânsito" recebe o aviso com o dia da última linha ("Sem atualizações desde seg 12 out"). Procure a loja ou o expedidor. Veja [rastreamento que não atualiza](guide:tracking-not-updating).

**Danificada, faltando volumes ou extraviada.** Avise primeiro a loja ou o remetente: eles contrataram o frete. Para fretes rodoviários entre estados nos EUA, a lei federal define estas regras:

- A transportadora responde pela perda real ou dano à carga perante quem o recibo ou conhecimento de transporte (bill of lading) autoriza a pedir a indenização. A responsabilidade pode ser limitada a um valor declarado pelo expedidor ou acordado por escrito com a transportadora.
- A transportadora não pode dar prazo inferior a 9 meses para registrar o pedido de indenização nem inferior a 2 anos para acionar a justiça, contados do dia em que recusa por escrito qualquer parte do pedido. O conhecimento de transporte ou contrato define o prazo exato.
- O pedido de indenização é uma solicitação por escrito, protocolada na transportadora dentro desses prazos, que identifica a remessa, responsabiliza a transportadora e pede um valor definido ou apurável. Uma ressalva de dano ou falta no recibo de entrega não vale sozinha como pedido de indenização.
- Quando exigido pela transportadora, o pedido deve vir acompanhado do conhecimento de transporte, comprovante do frete e nota fiscal. Para um volume inteiro ou remessa que nunca chegou, se a transportadora não puder confirmar o extravio de outro modo, o destinatário fornece declaração escrita certificada de que a carga não chegou por outra via.
- A transportadora confirma o recebimento do pedido por escrito em até 30 dias. Ela paga, recusa ou apresenta proposta firme por escrito em até 120 dias; se não conseguir, escreve informando a situação nesse prazo e a cada 60 dias depois.

Se o rastreamento indicar `Delivered` e nada tiver chegado, veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received). Veja também [encomendas danificadas](guide:damaged-parcel) e [encomendas extraviadas](guide:lost-parcel).

## Atendimento ao cliente e contato da R+L Carriers

Canais listados no site da R+L Carriers:

- **Telefone:** 800-543-5589, exibido pela R+L no topo de suas páginas.
- **R+L Cares:** formulário no site da R+L para pendências, dúvidas ou comentários; a R+L afirma que ele segue direto à liderança executiva.
- **Indenizações (Claims):** o site da R+L traz link para Cargo Claims, com instruções sobre como fazer um pedido de indenização de carga.

A R+L transporta a carga para quem a contratou, então procure essa parte primeiro: a loja em compras online, ou a empresa remetente em fretes comerciais. Elas detêm a reserva e podem tratar diretamente com a R+L.

## Acompanhe uma carga R+L Carriers com o Peek

O formato de um PRO de nove dígitos não confirma ao Peek que é da R+L: nove dígitos servem para outras transportadoras também. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Para duas letras e sete dígitos, o Peek consulta a R+L na hora e mostra "R+L Carriers está com esta encomenda." quando a R+L reconhece o PRO. Para nove dígitos digitados com o hífen antes do dígito verificador, o Peek lista a R+L entre as transportadoras compatíveis e a consulta junto com TNT, TForce Freight e XPO. O Peek remove o hífen ao adicionar o código; já para nove dígitos sem hífen ele consulta TNT, TForce Freight e XPO quando o número passa na validação delas, mas não a R+L: toque em "Trocar" e busque por R+L Carriers pelo nome. Em uma carga já adicionada, use "Trocar transportadora". Se um serviço de rastreamento mostrar a carga em movimento e indicar R+L Carriers, o Peek a classifica em R+L e consulta o rastreador da R+L nas verificações seguintes.

Com o `I` na frente, o código serve para R+L Carriers e Australia Post: o Peek consulta ambas e classifica na que o reconhecer. Um link da página de rastreamento da R+L contendo o PRO (`pro=`) também funciona: o Peek identifica R+L na hora ("Transportadora detectada").

O Peek consulta o rastreador da R+L apenas pelo PRO, sem conta nem código postal. Mostra o status, cada linha (com cidade e estado do terminal quando informados), o dia previsto de entrega e o peso na sua unidade (a R+L informa em libras). Exibe o nome do destinatário só para você (links compartilhados ocultam). Ficam de fora quantidade de volumes, referências, expedidor, endereço do destinatário, número do bill of lading e documentos. O rastreador da R+L não entrega comprovante de entrega ao Peek. "Abrir o site de R+L Carriers", na página da remessa, abre o PRO no rastreador da R+L.

Quando o rastreador da R+L não está acessível ou não consegue ler a remessa, o Peek consulta [serviços de rastreamento](guide:universal-tracker) e exibe o histórico deles nas palavras deles.

As linhas da R+L trazem data e hora sem fuso horário. O Peek lê cada uma no fuso da cidade do terminal, senão do estado do terminal (o principal nos estados com mais de um fuso), senão da linha anterior ou seguinte e, por último, no horário do Leste dos EUA (Eastern time), convertendo tudo para o fuso do seu dispositivo.

O Peek consulta até a cada 10 minutos enquanto você mantiver a carga aberta, ou no dia da entrega com as notificações ativadas; fora isso, e quando a carga fica dois dias sem nova linha (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), verifica a cada hora. Ele pode avisar você sobre "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a R+L, com a loja ou com o expedidor por você, nem agendar entrega ou fazer um pedido de indenização.

## Perguntas sobre o rastreamento da R+L Carriers

### O que é um número PRO na R+L Carriers?

É o código de rastreamento da R+L: nove dígitos, ou duas letras e sete dígitos, com o último dígito como verificador, às vezes escrito após um hífen. O rastreador da R+L o exibe com um `I` na frente. Ele é fornecido pela loja ou pela empresa que contratou o frete.

### Por que a R+L Carriers diz "No records found for Number"?

O rastreador da R+L não reconhece esse PRO. Confira cada dígito no e-mail da loja, verifique se o e-mail realmente indica R+L e pergunte ao expedidor se a R+L já fez a coleta.

### O que significa "Shipment Delayed" na R+L Carriers?

Algo atrasou a carga no trajeto. A linha não informa o motivo; o resumo da R+L classifica a remessa como atrasada mantendo a data prevista, e o Peek mantém em "Em trânsito". Pergunte à loja ou ao expedidor o motivo.

### Qual é o prazo para fazer um pedido de indenização de carga na R+L Carriers?

Para cargas transportadas entre estados dos EUA, a lei federal garante ao menos 9 meses para registrar o pedido de indenização junto à transportadora e ao menos 2 anos para recorrer à justiça após recusa escrita de qualquer parte do pedido. O conhecimento de transporte ou contrato fixa o prazo real; esta página não traz regras próprias da R+L. Comece por quem contratou o frete; as instruções da R+L estão em Cargo Claims no site dela.

:::sources
- [Universal Parcel Scraper: R+L Carriers](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/rl-carriers) – nomes, formatos PRO e o `I` na frente, links de rastreamento, textos e fases do rastreador, resumo, respostas de não encontrado e erro de dígito verificador, dia previsto, peso, fusos horários, o que o Peek omite
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras que compartilham formatos, ausência de transferência, serviços de rastreamento como alternativa
- [R+L Carriers: Shipment Tracing](https://www.rlcarriers.com/freight/shipping/shipment-tracing) – número PRO ou ID de solicitação de coleta, até 25 números, outras referências com MyRLC
- [R+L Carriers: R+L Cares](https://www.rlcarriers.com/contact/rl-cares) – formulário para problemas, dúvidas e feedback enviado à liderança executiva; 800-543-5589 no topo da página; link Cargo Claims com instruções para pedidos de indenização
- [GovInfo: 49 U.S.C. 14706, Liability of carriers under receipts and bills of lading](https://www.govinfo.gov/content/pkg/USCODE-2024-title49/html/USCODE-2024-title49-subtitleIV-partB-chap147-sec14706.htm) – responsabilidade perante o titular do bill of lading, valor limitado, 9 meses para pedir indenização e 2 anos para ação judicial
- [GovInfo: 49 CFR Part 370, Investigation and voluntary disposition of loss and damage claims and processing salvage](https://www.govinfo.gov/content/pkg/CFR-2025-title49-vol5/xml/CFR-2025-title49-vol5-part370.xml) – conteúdo do pedido de indenização, ressalvas no recibo de entrega, documentos de suporte, prazos de 30, 120 e 60 dias
:::
