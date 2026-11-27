---
title: Rastreamento GLS US (GSO): códigos, status e entrega
description: Rastreie uma encomenda GLS US (também listada como GSO): código SH, significado dos status, dias e horários de entrega, assinatura, tentativas e contatos.
slug: gls-us-tracking
published: 2026-11-27
updated: 2026-11-27
---

A GLS US (General Logistics Systems US, Inc.) é a empresa de encomendas do Grupo GLS nos Estados Unidos, com entregas por todo o oeste americano. O catálogo do Peek também a conhece como GLS USA, GLS United States, General Logistics Systems US, GSO e Golden State Overnight — empresa comprada pela GLS em 2016. Para rastrear uma encomenda da GLS US, cole o código na caixa acima ou consulte na [página de rastreamento da GLS nos EUA](https://gls-group.com/US/en/send-and-receive/track-a-parcel/).

## Código de rastreamento GLS US: como identificar

O catálogo do Peek reconhece um formato para códigos da GLS US. Veja exemplos com dígitos fictícios:

| O que você cola (fictício) | O que o Peek faz |
| --- | --- |
| `SH605182704936815247` | `SH` seguido de 18 dígitos, totalizando 20 caracteres. Só a GLS US usa esse formato no catálogo do Peek, mas a correspondência é fraca; por isso o Peek consulta a GLS US antes de confirmar o nome. |
| `sh 6051 8270 4936 8152 47` | O mesmo código. Letras minúsculas, espaços e hifens não fazem diferença. |
| Código GLS US em outro formato | O Peek não o reconhece sozinho. Selecione GLS US manualmente (veja "Como rastrear GLS US no Peek" abaixo). |
| Link de rastreamento de gls-group.com com `/US/`, ou de gls-us.com ou gso.com | O Peek extrai o código do link e identifica GLS US imediatamente ("Transportadora detectada"). |

**Onde encontrar.** No e-mail de envio da loja, na página do pedido ou nos e-mails e SMS da própria GLS caso a loja tenha cadastrado você. Cada caixa leva uma etiqueta própria; várias caixas podem significar vários códigos. A busca sem código no site da GLS exige o número de conta do remetente e a referência: peça à loja. Veja [como encontrar um código de rastreamento](guide:find-tracking-number), em inglês. O número do pedido não funciona.

**Não é a GLS europeia.** A GLS US faz parte do Grupo GLS, mas o Peek acompanha suas encomendas no rastreador próprio da GLS US. Um link de gls-group.com com `/EU/` ou `/DE/` direciona o Peek para a [GLS Germany](carrier:gls-de), não para a GLS US. Se o e-mail da loja indicar uma filial GLS da Europa ou outra transportadora, rastreie por ela. Em [formatos de código de rastreamento](guide:tracking-number-formats), em inglês, você vê os formatos de outras empresas.

## Significado dos status de rastreamento da GLS US

Cada leitura no rastreador da GLS US traz data e hora, e quase todas indicam cidade, estado e país. Os textos reconhecidos pelo código do Peek aparecem em inglês e em letras maiúsculas. Na ordem habitual:

| A GLS US mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `LABEL CREATED` | O remetente gerou a etiqueta. A GLS US talvez ainda não tenha o pacote. Esta linha não mostra local. | "Etiqueta criada" |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | A GLS US registrou a chegada da encomenda. A data no fim, com mês primeiro, é o dia previsto para a entrega. | "Em trânsito" |
| `SCANNED AT DELIVERY DEPOT` | Registrada no centro de distribuição que faz a entrega. | "Em trânsito" |
| `ON ROUTE FOR DELIVERY` | Em rota de entrega hoje. | "Saiu para entrega" |
| `SHIPMENT DELIVERED` | Entregue. | "Entregue" |

**Status geral da remessa.** Acima das leituras, o rastreador da GLS US resume o envio em poucas palavras: `label created`, `in transit`, `out for delivery`, `delivered` ou `returned`. Quando esse resumo está mais avançado que as leituras, o Peek segue por ele: um envio resumido como `delivered` aparece como "Entregue" mesmo sem linha correspondente, e `returned` aparece como "Devolvida ao remetente".

**Outras linhas.** O código do Peek não conhece textos específicos da GLS US para tentativa sem sucesso, problema, retenção ou ponto de retirada. Qualquer linha desconhecida é exibida exatamente como a GLS US escreve e classificada pelas regras gerais do Peek. Veja mais em [status de rastreamento explicados](guide:tracking-statuses), em inglês.

Se nada aparecer depois de `LABEL CREATED` por um dia ou mais, o Peek informa que o remetente ainda não entregou o pacote à GLS US e que o rastreamento começa quando isso ocorrer. Fale com a loja.

**E-mails e SMS da GLS.** Quando a loja cadastra seus dados ao gerar a etiqueta, a GLS avisa na entrada no depósito (com a data prevista), em caso de atraso ou falha na entrega, ao sair para entrega (com horário estimado) e na entrega concluída.

## Prazos, assinaturas e tentativas de entrega da GLS US

**Endereço.** A GLS não entrega em caixas postais (P.O. boxes) e faz a triagem pelo código postal americano (ZIP code), então um erro nele pode atrasar o envio. Redirecionamento só ocorre com autorização do remetente: peça à loja. Veja [como alterar o endereço de entrega](guide:change-delivery-address), em inglês.

**Dias.** Segunda a sexta; sábado apenas em algumas áreas, mediante solicitação do remetente; nunca aos domingos. A GLS não opera em seis feriados dos EUA: Ano Novo, Memorial Day, Independence Day, Labor Day, Thanksgiving e Natal. Entregas previstas para feriado passam para o próximo dia útil.

**Horários.** Variam conforme o serviço contratado. O Priority chega até 10h30 do dia útil seguinte na maioria das regiões, e o Ground até 17h (18h em áreas rurais). Em trajetos mais longos, o Priority chega até 10h30 do dia agendado e o Ground até o fim do dia. Entregas de sábado chegam até 10h30 na maior parte das áreas.

**Assinatura.** A opção é definida pelo remetente. Por padrão, o motorista coleta assinatura em endereços comerciais. Em casas, o pacote pode ser deixado se ninguém puder assinar e o local for seguro e seco; em prédios de apartamentos, na porta ou na administração/portaria caso o motorista não tenha acesso. Com Signature Required, a GLS não deixa sem assinatura; com Adult Signature Required (exigido para bebidas alcoólicas e tabaco), quem assina precisa ter 21 anos ou mais.

**Tentativa sem sucesso.** Perdeu a entrega ou recebeu um aviso na porta? Reagende pelo link nos e-mails ou SMS da GLS, ou ligue para a linha de destinatários abaixo. As páginas da GLS não informam número de tentativas nem tempo de guarda, e os GLS Points servem apenas para postagem por remetentes. Veja [tentativa de entrega](guide:missed-delivery), em inglês, e [encomendas não retiradas](guide:uncollected-parcel), em inglês.

## Encomenda GLS US não encontrada, parada, extraviada ou devolvida

**Não encontrada.** O rastreador da GLS US indica quando desconhece um código. O Peek então mostra "Aguardando a primeira movimentação de GLS US" para um código SH detectado por ele, ou "Aguardando GLS US" se você escolheu GLS US manualmente, e segue tentando. Etiquetas recém-criadas costumam levar um tempo para aparecer. Se após dois dias um código SH continuar sem resposta, o Peek alerta que ainda aguarda a primeira atualização da transportadora; se você escolheu GLS US manualmente e nada aparecer após dois dias em nenhum serviço, ele sugere conferir o número, trocar de transportadora ou remover a encomenda, passando a consultar diariamente e depois semanalmente após uma semana. Confira o número no e-mail da loja e confirme se já foi despachado.

**Sem novidades por dias.** Após quatro dias sem nova leitura em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia da última leitura ("Sem atualização de rastreamento desde …"), observa que encomendas costumam ficar quietas no fim de semana, continua consultando a GLS US e exibe o link "Obter ajuda da GLS US". Após três semanas sem novidade, passa a recomendar que você procure o vendedor. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating), em inglês.

**Entregue, mas não chegou.** `SHIPMENT DELIVERED` e nada na porta? Confira no rastreador da GLS se houve assinatura. Verifique arredores da entrada, sala de correspondência, administração e vizinhos; depois ligue para a linha de destinatários da GLS e avise a loja. Veja [entregue mas não recebida](guide:delivered-not-received), em inglês.

**Extraviada ou danificada.** Avise a loja primeiro: ela contratou o frete e pode acionar a GLS US ou enviar outro produto. Somente o remetente pode abrir reclamação, em até 90 dias do envio, e a GLS busca concluir em até 45 dias. Em caso de dano, a GLS pede ao remetente fotos do item, da proteção interna e da caixa; tire fotos antes de descartar qualquer embalagem. Veja [encomendas extraviadas](guide:lost-parcel), em inglês, e [encomendas danificadas](guide:damaged-parcel), em inglês.

**Devolvida.** "Devolvida ao remetente" significa que o pacote voltou para a loja. Peça reembolso ou novo envio. Veja [devolvida ao remetente](guide:returned-to-sender), em inglês.

## Atendimento ao cliente e contato da GLS US

Conforme a [página de suporte ao cliente da GLS US](https://gls-group.com/US/en/support/customer-support/):

- **Telefone:** 1-800-322-5555, opção 2 (suporte a destinatários: rastreamento, problemas e ajuda na entrega).
- **Horário:** segunda a sexta, das 7h às 19h, e sábado, das 7h às 16h, no horário do Pacífico.
- **Chat ao vivo:** disponível no FAQ da GLS. A empresa não divulga e-mail para destinatários.

> **Golpes.** A GLS nunca pede a destinatários por e-mail que paguem por mercadorias com cartão, pagamento on-line ou transferência bancária — embora, em envios internacionais, ela ou seu agente possam cobrar impostos e taxas aduaneiras. Os sites legítimos são gls-group.com e os links a partir dele. Denuncie falsificações em security@gls-us.com e veja [mensagens falsas de entrega](guide:delivery-scam-texts), em inglês.

## Como rastrear GLS US no Peek

**Código SH.** Cole-o na caixa acima. Enquanto o Peek consulta a GLS US, a linha da transportadora exibe "Consultando serviços de rastreamento…". Quando a GLS US reconhece o código, a linha exibe GLS US e confirma que ela tem a encomenda. Quando ainda não aparece, mostra "ainda não encontrado · O Peek continua consultando": adicione assim mesmo, e ele aparecerá como "Provavelmente GLS US" até a primeira leitura.

**Outro formato.** Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Em seguida, escolha GLS US manualmente:

1. Toque em "Alterar" ao lado da linha da transportadora e busque por GLS US ("GSO", "Golden State Overnight", "General Logistics Systems US" ou "United States" também funcionam).
2. Selecione GLS US: a linha passa a indicar escolha manual. Inicie o rastreamento.

Em uma encomenda já adicionada, use "Alterar transportadora". O Peek consulta a GLS US apenas para códigos de 5 a 20 letras e dígitos.

**O que você vê.** O Peek consulta o rastreador da GLS US só pelo código, sem conta nem código postal. Exibe a etapa, cada leitura com cidade e estado no mapa e, quando disponível, a data prevista até a entrega ou devolução. Na sua página pessoal da encomenda, mostra o nome do destinatário fornecido pela GLS US (em "Destinatário") e encerra a rota do mapa na cidade de destino; links compartilhados ocultam ambos. Não exibe quem assinou, assinatura, comprovante de entrega nem referência do remetente, e não transfere envios da GLS US para outra transportadora. Se a GLS US registrar mais de um envio no mesmo código, o Peek segue o que tiver a leitura mais recente. O botão "Abrir o site de GLS US" abre o código direto no rastreador americano.

**Horários.** A GLS US informa cada leitura sem fuso horário. O Peek interpreta pelo fuso da cidade informada ou, se desconhecida, pelo fuso principal do estado. Sem cidade nem estado, usa o fuso da leitura anterior mais próxima com local, ou da posterior; sem ambas, aplica o horário do Pacífico. Tudo aparece convertido para o fuso do seu dispositivo.

**Quando a GLS US não responde.** Se o rastreador da GLS US ficar fora do ar, o Peek recorre a [serviços de rastreamento](guide:universal-tracker), em inglês, e mostra o histórico nas palavras deles.

**Frequência.** Com a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, a consulta ocorre a cada 10 minutos no máximo, e a cada 2 minutos a partir das 6h no horário local no dia em que sai para entrega; fora disso, de hora em hora. Após dois dias sem novidade (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias parados em "Etiqueta criada". As notificações cobrem "Cada atualização", "Etapas importantes" ou "Somente na entrega".

O Peek não pode contatar a GLS US ou a loja por você, alterar a entrega nem abrir reclamação.

## Perguntas frequentes sobre o rastreamento GLS US

### A GSO é a mesma empresa que a GLS US?

A GLS comprou a Golden State Overnight (GSO) em 2016, e hoje gso.com redireciona para o site da GLS US. A GSO foi fundada em 1995 para entregas na Califórnia, Arizona, Nevada e Novo México. O Peek trata ambas como uma só: lista GSO, Golden State Overnight, GLS USA, GLS United States e General Logistics Systems US como nomes alternativos da GLS US e reconhece links de gso.com e gls-us.com automaticamente.

### Como é um código de rastreamento GLS US?

O formato reconhecido pelo Peek é `SH` seguido de 18 dígitos, como `SH605182704936815247` (fictício). As páginas da GLS não publicam um padrão fixo e pode haver outros formatos: cole mesmo assim e escolha GLS US em "Alterar".

### O que significa a leitura ARRIVAL SCAN da GLS US?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` significa que a GLS US registrou a chegada da encomenda e agendou a entrega para a data no final (no formato mês/dia/ano). No Peek, isso aparece como "Em trânsito".

### Por que o Peek diz "Aguardando a primeira movimentação de GLS US"?

Seu código tem o formato da GLS US e o Peek já consultou o sistema dela, mas o envio ainda não consta lá. Isso é comum em etiquetas novas e o Peek segue verificando. Se continuar ausente após uma semana, o Peek avisa e sugere confirmar o número com o remetente ou escolher outra transportadora.

### A GLS US é a mesma GLS da Europa?

Ela pertence ao mesmo Grupo GLS, mas o Peek a trata como transportadora separada e consulta o rastreador próprio dos EUA. As empresas europeias da GLS, como a [GLS Germany](carrier:gls-de), têm rastreadores próprios: acompanhe pela filial GLS informada no e-mail da loja.

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – US tracker, search without a number, who signed
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – GLS Group's US company, Western U.S., GSO's history
- [GSO: gso.com](https://www.gso.com/) – redirects to GLS US's site
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – delivery times, Saturday in some areas
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – delivery days, holidays
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – no Sunday, holidays, signatures, ZIP codes, one label per box, claims within 90 days
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – P.O. boxes, rerouting, rescheduling, door tags, notifications, live chat, claims
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – shipper files, 45 days, photos
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – recipient line, hours
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – drop-off only
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – payment requests, duties and taxes, genuine sites, reporting
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – names, the SH shape and its weak match, tracking links, the tracker's scans and shipment statuses with their stages, not-found answer, expected delivery day, several shipments under one number, scan clocks, what Peek leaves out
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – GLS's European networks and their links, tracking services as a fallback, scans dated on the clock of their town
:::
