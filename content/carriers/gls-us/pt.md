---
title: Seguimento GLS US (GSO): números, estados e entregas
description: Segue uma encomenda da GLS US (também GSO): formato SH, significado de cada registo, dias de entrega, assinaturas e contactos.
slug: seguimento-gls-us
published: 2026-11-23
updated: 2026-11-23
---

A GLS US (General Logistics Systems US, Inc.) é a empresa de encomendas do GLS Group nos Estados Unidos, entregando no Oeste dos EUA. O catálogo do Peek também a conhece como GLS USA, GLS United States, General Logistics Systems US, GSO e Golden State Overnight, empresa comprada pela GLS em 2016. Para seguir uma encomenda GLS US, cola o número na caixa acima ou introdu-lo na [página de seguimento da GLS US](https://gls-group.com/US/en/send-and-receive/track-a-parcel/).

## Número de seguimento GLS US: como é

O catálogo do Peek conhece um formato para a GLS US (dígitos inventados):

| O que colas (inventado) | O que faz o Peek |
| --- | --- |
| `SH605182704936815247` | SH e 18 dígitos, 20 carateres no total. Só a GLS US usa esta forma no catálogo do Peek, mas a correspondência é fraca, pelo que o Peek pergunta à GLS US antes de a nomear. |
| `sh 6051 8270 4936 8152 47` | O mesmo número. Minúsculas, espaços e hífenes não importam. |
| Um número GLS US de outra forma | O Peek não o reconhece sozinho. Escolhe GLS US manualmente (vê «Seguir uma encomenda GLS US com o Peek» abaixo). |
| Ligação de seguimento de gls-group.com com `/US/`, ou de gls-us.com ou gso.com | O Peek extrai o número da ligação e identifica logo a GLS US («Transportadora detetada»). |

**Onde encontrar.** No e-mail de envio ou página do pedido da loja, ou nos e-mails e SMS da GLS caso a loja te tenha inscrito. Cada caixa tem a sua etiqueta, pelo que várias caixas significam vários números. A pesquisa sem número na GLS exige número de conta e referência do remetente: pergunta à loja. Vê [como encontrar o teu número de seguimento](guide:find-tracking-number). Um número de pedido não funciona.

**Diferente da GLS europeia.** A GLS US pertence ao GLS Group, mas o Peek segue as suas encomendas no seguimento próprio da GLS US. Uma ligação gls-group.com com `/EU/` ou `/DE/` leva o Peek para a [GLS Germany](carrier:gls-de), não para a GLS US. Se o e-mail indicar uma empresa GLS europeia ou outra transportadora, segue-a lá. [Formatos de números de seguimento](guide:tracking-number-formats) mostra outros formatos.

## O que significam os estados de seguimento da GLS US

Cada registo no seguimento GLS US traz data e hora, e a maioria indica local: cidade, estado e país. Os registos conhecidos pelo código do Peek surgem em inglês e maiúsculas, pela ordem habitual:

| A GLS US mostra | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `LABEL CREATED` | O remetente criou a etiqueta. A GLS US pode ainda não ter a encomenda. Este registo não indica local. | «Etiqueta criada» |
| `ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` | A GLS US registou a chegada da encomenda. A data final (mês primeiro) é o dia previsto para entrega. | «Em trânsito» |
| `SCANNED AT DELIVERY DEPOT` | Registada no depósito que faz a entrega. | «Em trânsito» |
| `ON ROUTE FOR DELIVERY` | Em rota de distribuição hoje. | «Em distribuição» |
| `SHIPMENT DELIVERED` | Entregue. | «Entregue» |

**Estado global do envio.** Acima dos registos, o seguimento resume a encomenda em poucas palavras: `label created`, `in transit`, `out for delivery`, `delivered` ou `returned`. Quando esse resumo está mais adiantado que os registos, o Peek segue-o: `delivered` mostra «Entregue» mesmo sem último registo correspondente, e `returned` mostra «Devolvida ao remetente».

**Outras linhas.** O código do Peek não conhece textos próprios da GLS US para entrega falhada, problema, retenção ou ponto de levantamento. Linhas desconhecidas surgem exatamente como escritas pela GLS US e seguem as regras gerais do Peek. Mais em [significado dos estados de seguimento](guide:tracking-statuses).

Se nada se seguir a `LABEL CREATED` durante um dia ou mais, o Peek mostra: «O remetente ainda não a entregou a GLS US. O seguimento começa quando a entregar.» Pergunta à loja.

**E-mails e SMS da GLS.** Se a loja te inscrever ao criar a etiqueta, a GLS escreve quando o depósito regista a encomenda (com data prevista), em caso de atraso ou tentativa falhada, ao sair para distribuição (com hora estimada) e ao entregar.

## Prazos, assinaturas e ausências na GLS US

**Morada.** A GLS não entrega em apartados (*P.O. boxes*) e tria por código ZIP, pelo que um código errado pode atrasar a entrega. Só redireciona com autorização do remetente: fala com a loja. Vê [alterar morada de entrega](guide:change-delivery-address).

**Dias.** Segunda a sexta; sábado apenas em certas áreas quando o remetente pede; nunca ao domingo. A GLS encerra em seis feriados dos EUA (Ano Novo, Memorial Day, Dia da Independência, Labor Day, Ação de Graças e Natal). Uma encomenda prevista para feriado chega no dia útil seguinte.

**Horários.** Dependem do serviço escolhido pelo remetente. Priority chega até às 10:30 do dia útil seguinte na maioria das áreas, Ground até às 17:00 (18:00 em zonas rurais). Em percursos longos, Priority chega até às 10:30 do dia previsto e Ground até ao fim do dia. Entregas de sábado chegam até às 10:30 na maioria das áreas.

**Assinatura.** Escolhida pelo remetente. Por predefinição o motorista recolhe assinatura em empresas. Numa moradia deixa a encomenda se ninguém puder assinar e o local for seguro e seco; em prédios de apartamentos, à porta ou no escritório de gestão/arrendamento se não conseguir aceder. Com *Signature Required* não deixa sem assinatura; com *Adult Signature Required* (obrigatória para álcool e tabaco), quem assina deve ter 21 anos ou mais.

**Entrega falhada.** Perdeste uma tentativa ou encontraste um aviso na porta? Remarca pela ligação nos e-mails ou SMS da GLS, ou liga para a linha de destinatários abaixo. As páginas da GLS não indicam número de tentativas nem tempo de guarda, apresentando os GLS Points apenas como pontos de entrega para remetentes. Vê [entregas falhadas](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

## Encomenda GLS US não encontrada, parada, perdida ou devolvida

**Não encontrada.** O seguimento da GLS US avisa quando não conhece um número. O Peek mostra «À espera do primeiro registo de GLS US» num número SH detetado ou «À espera de GLS US» quando a escolheste manualmente, e continua a verificar. Uma etiqueta nova demora muitas vezes a aparecer. Se dois dias depois de adicionares um número SH continuar sem dados, o Peek sinaliza «À espera da primeira atualização da transportadora». Se escolheste GLS US e ao fim de dois dias continua sem a reconhecer (sem outras fontes), mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a verificar diariamente, e semanalmente após uma semana. Confirma o número no e-mail da loja e pergunta se já foi enviada.

**Dias sem novidades.** Após quatro dias sem novos registos, uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» recebe o aviso «Sem atualizações há quatro dias». O Peek lembra que encomendas ficam muitas vezes quietas ao fim de semana e continua a consultar a GLS US. Vê [seguimento sem atualizações](guide:tracking-not-updating).

**Entregue, mas nada chegou.** `SHIPMENT DELIVERED` sem encomenda à porta? Vê no seguimento da GLS quem assinou, confirma junto à porta, sala de correio, administração e vizinhos, depois liga para a linha de destinatários e avisa a loja. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Avisa primeiro a loja, que contratou a entrega e pode tratar com a GLS US ou enviar substituição. Só o remetente pode reclamar, até 90 dias após envio; a GLS procura resolver em 45 dias. Para danos, a GLS pede ao remetente fotos do artigo, embalagem interior e caixa: tira-as antes de deitar fora. Vê [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Devolvida.** «Devolvida ao remetente» significa que voltou à loja. Pede reembolso ou novo envio. Vê [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto da GLS US

Na [página de suporte ao cliente da GLS US](https://gls-group.com/US/en/support/customer-support/):

- **Telefone:** 1-800-322-5555, opção 2 (apoio a destinatários: seguimento, problemas e ajuda na entrega).
- **Horário:** segunda a sexta das 7:00 às 19:00 e sábado das 7:00 às 16:00, hora do Pacífico.
- **Chat em direto**, disponível nas FAQ da GLS. A GLS não divulga e-mail para destinatários.

> **Fraudes.** A GLS nunca pede a destinatários por e-mail que paguem bens por cartão, pagamento online ou transferência, embora em envios internacionais a GLS ou o seu agente possam pedir direitos e impostos. Os sites legítimos são gls-group.com e os ligados a partir dele. Denuncia fraudes para security@gls-us.com e lê [SMS fraudulentos de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da GLS US com o Peek

**Um número SH.** Cola-o na caixa acima. Enquanto o Peek consulta a GLS US, aparece «A verificar serviços de seguimento…». Quando a GLS US o conhece, a linha mostra GLS US com «tem esta encomenda» e arquiva-a aí. Se ainda não o conhece, surge «ainda não encontrada · o Peek continua a verificar». Adiciona-a na mesma: mostrará «Provavelmente GLS US» até ao primeiro registo.

**Outro número.** Cola-o na mesma: o Peek segue mais de 4500 transportadoras. Depois escolhe GLS US manualmente:

1. Prime «Alterar» junto à linha da transportadora e pesquisa GLS US («GSO», «Golden State Overnight», «General Logistics Systems US» ou «United States» também a encontram).
2. Seleciona GLS US: a linha passa a «Escolhida por ti». Inicia o seguimento.

Numa encomenda já adicionada usa «Alterar transportadora». O Peek consulta a GLS US apenas para códigos de 5 a 20 letras e dígitos.

**O que vês.** O Peek consulta o seguimento da GLS US só pelo número: sem conta nem código ZIP. Mostra o estado, cada registo com cidade e estado no mapa e, quando fornecido, o dia previsto até à entrega ou devolução. Omite nome e morada do destinatário, quem assinou, assinatura, prova de entrega e referência do remetente. O Peek não transfere encomendas GLS US para outra transportadora. Se a GLS US agrupar vários envios sob um número, o Peek segue o do registo mais recente. «Abrir o site de GLS US», na página da encomenda, abre o número no seguimento americano da GLS.

**Horas.** A GLS US fornece horas sem fuso horário. O Peek lê-as no fuso da cidade do registo ou, se desconhecida, no do estado (o principal caso tenha vários). Sem cidade nem estado, usa o fuso do registo anterior mais próximo ou do seguinte; sem ambos, hora do Pacífico. O Peek exibe todas as horas no fuso do teu dispositivo.

**Quando a GLS US não responde.** Se o seguimento estiver indisponível, o Peek consulta [serviços de seguimento](guide:universal-tracker) e mostra o histórico nas palavras deles.

**Frequência.** Enquanto mantiveres a encomenda aberta, ou no dia de entrega com notificações ligadas, e a GLS US responder, o Peek verifica até de 10 em 10 minutos, ou de 2 em 2 a partir das 6:00 hora local no dia em que entra em rota; nos restantes casos de hora a hora. Via serviços de seguimento, de hora a hora e até de 15 em 15 minutos nesse dia com a página aberta ou notificações ativas. Após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. As notificações cobrem «Todos os registos», «Passos importantes» ou «Só a entrega».

O Peek não pode contactar a GLS US nem a loja, alterar entregas nem apresentar reclamações.

## Perguntas sobre o seguimento da GLS US

### A GSO é a mesma empresa que a GLS US?

A GLS comprou a Golden State Overnight (GSO) em 2016 e gso.com redireciona hoje para o site da GLS US. Fundada em 1995, a GSO entregava na Califórnia, Arizona, Nevada e Novo México. O Peek trata-as como uma só: lista GSO, Golden State Overnight, GLS USA, GLS United States e General Logistics Systems US como outros nomes da GLS US e lê ligações de gso.com e gls-us.com como GLS US.

### Como é um número de seguimento da GLS US?

O formato conhecido pelo Peek é SH seguido de 18 dígitos, como `SH605182704936815247` (inventado). As páginas da GLS não definem formato e pode haver outros: cola-os na mesma e escolhe GLS US com «Alterar».

### O que significa o arrival scan da GLS US?

`ARRIVAL SCAN - DELIVERY SCHED FOR 07/15/2026` significa que a GLS US registou a chegada e agendou a entrega para a data final, mês primeiro. O Peek mostra «Em trânsito».

### Porque é que o Peek diz «À espera do primeiro registo de GLS US»?

O número tem o formato da GLS US e o Peek perguntou à GLS US, que ainda não o lista. É normal para etiquetas novas e o Peek continua a perguntar. Se continuar ausente após uma semana, o Peek avisa e sugere confirmar o número com o remetente ou escolher outra transportadora.

### A GLS US é igual à GLS na Europa?

Pertence ao mesmo GLS Group, mas o Peek trata a GLS US como transportadora própria no seu seguimento dos EUA. As empresas europeias da GLS, como a [GLS Germany](carrier:gls-de), têm o seu próprio sistema: segue a encomenda na filial GLS indicada pela loja.

:::sources
- [GLS US: Track a parcel](https://gls-group.com/US/en/send-and-receive/track-a-parcel/) – seguimento EUA, pesquisa sem número, quem assinou
- [GLS US: About GLS US](https://gls-group.com/US/en/about-gls-us/) – empresa dos EUA do GLS Group, Oeste dos EUA, história da GSO
- [GSO: gso.com](https://www.gso.com/) – redireciona para o site da GLS US
- [GLS US: Domestic shipping services](https://gls-group.com/US/en/Shipping/shipping-services/domestic/) – prazos, sábado em certas zonas
- [GLS US: Holiday schedule](https://gls-group.com/US/en/resources/holiday-schedule/) – dias de entrega, feriados
- [GLS US: Parcel Tariff](https://gls-group.com/US/en/parcel-tariff/) – sem domingo, feriados, assinaturas, códigos ZIP, uma etiqueta por caixa, reclamações em 90 dias
- [GLS US: FAQ](https://gls-group.com/US/en/support/faq/) – apartados, redirecionamento, reagendamento, avisos de porta, notificações, chat em direto, reclamações
- [GLS US: Claims](https://gls-group.com/US/en/support/claims/) – reclama o remetente, 45 dias, fotos
- [GLS US: Customer support](https://gls-group.com/US/en/support/customer-support/) – linha de destinatários, horários
- [GLS US: Find a GLS Point](https://gls-group.com/US/en/Shipping/shipping-tools/find-drop-point/) – apenas entrega por remetentes
- [GLS US: Security advice](https://gls-group.com/US/en/security-advice/) – pedidos de pagamento, direitos e impostos, sites legítimos, denúncia
- [Universal Parcel Scraper: GLS US](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-us) – nomes, formato SH e correspondência fraca, ligações, registos e estados, resposta não encontrado, dia previsto, vários envios por número, fusos, dados omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – redes europeias GLS e ligações, serviços de seguimento de reserva, horas por cidade
:::
