---
title: Seguimento TForce Freight: números PRO e reclamações
description: Segue uma carga TForce Freight nos EUA ou Canadá: formato do número PRO, significado dos estados, entregas ao domicílio e reclamações.
slug: seguimento-tforce-freight
published: 2026-11-22
updated: 2026-11-22
---

A TForce Freight é uma transportadora de carga fracionada (LTL): transporta paletes e carga pesada entre os seus centros de serviço nos Estados Unidos e em todo o Canadá, bem como para mais de 600 pontos no México. Se uma encomenda grande ou outra carga seguir pela TForce, acompanha-a com o número PRO fornecido pela loja ou empresa que contratou o frete, colando-o na caixa acima. O seguimento oficial fica na página Tracking de www.tforcefreight.com.

## Número de seguimento TForce Freight: o número PRO

A página de seguimento da TForce pesquisa por número PRO, ou por conhecimento de embarque (BOL) ou número de nota de encomenda (PO) com um intervalo de datas de envio. O Peek segue por PRO (dígitos inventados):

| Número (inventado) | O que é |
| --- | --- |
| `482917363` | O PRO: nove dígitos. O último é um dígito de controlo — o resto da divisão dos primeiros oito por 7, logo sempre de 0 a 6. |
| `48291736` | Um PRO sem o 0 inicial. O seguimento da TForce acrescenta o 0, e o Peek também assim que escolhes TForce Freight. |
| `2205482917363` | Um número PARS usado por despachantes aduaneiros em cargas para o Canadá: 2205 seguido do PRO. Cola-o inteiro: o Peek pergunta à TForce pelo PRO contido nele. |

Um PRO de nove dígitos acabado em 7, 8 ou 9 tem gralha. O catálogo do Peek também reconhece TForce Freight como UPS Freight e TForce.

**Onde encontrar.** No e-mail de envio da loja ou na página da encomenda; em carga empresarial, pergunta ao remetente. Vê [como encontrar o teu número de seguimento](guide:find-tracking-number).

**Mesmo formato, outras transportadoras.** Nove dígitos com o mesmo dígito de controlo também servem na [TNT](carrier:tnt) e na XPO. A TForce Logistics (TForce Final Mile), cujos códigos começam por `TF`, é outra transportadora no Peek. Se o teu e-mail indicar uma delas, escolhe essa transportadora. Mais em [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados da TForce Freight

O seguimento da TForce apresenta um estado junto ao PRO, como `In Transit` ou `Delivered`, e depois o histórico do mais recente para o mais antigo, cada linha com localidade e estado do centro de serviço, data e hora. As linhas não têm código: o Peek lê o texto. Pela ordem habitual:

| A TForce Freight indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | O motorista recolheu a carga no remetente. | «Aceite pela transportadora» |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | Está no centro de serviço do lado do remetente. | «Em trânsito» |
| `DEPARTURE` | Saiu de um centro de serviço. | «Em trânsito» |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | Chegou a um centro de serviço no percurso ou ao que faz a entrega. | «Em trânsito» |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | A TForce enviou e-mail ao destinatário para combinar marcação de entrega. Responde. | «Em trânsito» |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | Foi agendado um dia de entrega. Por vezes fica registado após a entrega. | «Em trânsito» |
| `OUT FOR DELIVERY` | No camião em distribuição. | «Em distribuição» |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Não entregue nesta volta. Qualquer linha começada por `Shipment not delivered.` lê-se assim, com o motivo a seguir. | «Tentativa de entrega» |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Entregue. A página da TForce mostra quem assinou. | «Entregue» |

Um estado `Delivered` junto ao PRO aparece sempre como «Entregue»; nos restantes casos decide a linha mais recente que o Peek compreende, ficando as outras tal como a TForce as escreve. A página da TForce também pode enviar atualizações por e-mail. Mais em [significado dos estados de seguimento](guide:tracking-statuses).

## Como a TForce Freight entrega

A TForce transporta a carga até ao centro de serviço da tua zona e entrega-a de camião. O seguimento mostra um dia estimado de entrega, que o Peek mantém até à entrega, devolução ou passagem da data. O código do Peek não transfere o envio para nenhuma outra transportadora.

A tarifa regulamentar da TForce (LTL Rules Tariff UPGF 102-T, em vigor desde 17 de agosto de 2026) define como funciona a entrega:

- **Residências.** Onde os camiões possam aceder legalmente, a TForce tem de acordar primeiro data e hora aproximada contigo ou com o remetente. Um negócio numa residência conta como residência. Entrega à entrada do rés-do-chão, entrada da garagem ou telheiro, ou levantas a carga no terminal. A entrega residencial é serviço extra cobrado a quem o pediu ou paga o frete.
- **Junto ao camião.** A entrega normal significa descarregar ao lado do camião. Entrega no interior e plataforma elevatória custam à parte: confirma com quem contratou o frete se estão incluídas.
- **Aviso de chegada.** Quando os documentos o pedem, a TForce avisa o destinatário até ao dia útil seguinte à chegada ao centro de entrega, normalmente por telefone ou e-mail.
- **Não entregue.** Se a carga for recusada ou o destinatário não for encontrado, a TForce avisa o remetente do armazenamento e podem aplicar-se taxas de guarda. Nova tentativa é cobrada salvo se levantares no centro de serviço; numa residência, só se tiveres falhado uma data combinada.

**Para o Canadá.** Como explica a página Canadá da TForce, a carga vinda dos EUA desalfandega na fronteira através de um despachante. A carga que não desalfandegar fica num armazém alfandegado no destino, sujeita a encargos de guarda. O importador oficial paga honorários do despachante, direitos e impostos.

## Envio TForce Freight não encontrado, atrasado, danificado ou perdido

**Não encontrado.** Num PRO desconhecido, o seguimento da TForce mostra `Not Available` e `Please check ProNumber` com o número. O Peek mostra «À espera de TForce Freight» se tiveres escolhido TForce Freight, ou «Ainda sem histórico de seguimento» caso contrário, voltando a tentar mais tarde. Confirma cada dígito e pergunta ao remetente se a TForce já recebeu a carga.

**Dígito errado.** Um PRO cujo último dígito falhe o controlo não pertence à TForce e o Peek nunca pergunta à TForce por ele. Se escolheres TForce Freight ainda assim, o Peek diz que «não emite números como este» e o envio mostra «Verifica o número de seguimento».

**Sem atualizações.** Após quatro dias sem registos numa carga em «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde…», explica que «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a TForce Freight.» e disponibiliza «Pede ajuda a TForce Freight». Pergunta à loja ou remetente. Vê [seguimento que não atualiza](guide:tracking-not-updating).

**Entrega falhada.** Após uma linha `Shipment not delivered.`, o Peek assinala o envio («Consulta as instruções da transportadora»). Liga para a TForce ou pede a quem reservou o frete para combinar nova entrega. Mais em [entregas falhadas](guide:missed-delivery).

**Falta ou dano na entrega.** A página Cargo Claims Information da TForce pede para verificar sempre a carga e avisar o motorista no momento:

- Conta os volumes face ao recibo de entrega e abre caixas danificadas com o motorista.
- Regista exatamente o que falta ou está danificado na tua cópia e na cópia da TForce.
- Não recuses o envio salvo se o dano tiver tornado os bens inúteis.
- Pede ao responsável de Over, Short & Damage (OS&D) do teu centro de serviço local para localizar volumes em falta.

**Danos descobertos depois.** Comunica-os em 5 dias úteis após a entrega e pede inspeção, mantendo a embalagem como a encontraste. O relatório de inspeção não é uma reclamação.

**Apresentar reclamação.** A reclamação é um pedido escrito de pagamento pelo proprietário da carga. Numa compra online, começa pela loja, que detém o contrato. Regras da TForce:

- Apresenta-a em nove meses a contar da entrega, ou da data de envio se nada chegou.
- Usa o Standard Claim Form da TForce ou uma carta com identificação do envio e montante, juntando fatura do vendedor, fatura de frete, conhecimento de embarque e faturas de reparação. Envia por correio ou fax (abaixo), não ambos.
- A TForce acusa receção em 30 dias. A responsabilidade limita-se a 25,00 $ por libra e volume — menos em certos bens — salvo cobertura adicional contratada no conhecimento de embarque.

Se o seguimento indicar entregue sem ter chegado nada, consulta [entregue mas não recebida](guide:delivered-not-received). Mais em [encomenda danificada](guide:damaged-parcel) e [encomenda perdida](guide:lost-parcel).

## Apoio ao cliente e contacto da TForce Freight

Dados de www.tforcefreight.com (páginas Tracking, Fact Sheet, Rules Tariffs com LTL Rules Tariff UPGF 102-T, Canada, Cargo Claims Information, Contact Us com Email TForce Freight Customer Support e Service Information):

- **Apoio ao cliente:** (800) 333-7400, gratuito nos EUA, Canadá, Guam, Porto Rico e Ilhas Virgens Americanas, para seguimento, prova de entrega e problemas. De fora: (804) 231-8555.
- **E-mail:** formulário Email TForce Freight Customer Support, ligado a partir da página Contact Us da TForce, com campo para o PRO.
- **Departamento de reclamações:** (888) 653-9720 para saber o estado de uma reclamação. As reclamações seguem para Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218, ou por fax para (866) 580-1944.
- **O teu centro de serviço:** o Service Center Directory, em Service Information de www.tforcefreight.com.

A loja ou remetente costuma ser mais rápida por ser titular do contrato com a TForce.

## Seguir um envio TForce Freight com o Peek

O formato de um PRO não indica só a TForce: nove dígitos servem também na [TNT](carrier:tnt), XPO e noutras transportadoras. Cola-o na mesma: o Peek segue mais de 4500 transportadoras. Enquanto aparece «A verificar serviços de seguimento…», o Peek pergunta às transportadoras compatíveis; se só a TForce conhecer a carga, surge o nome com «tem esta encomenda». Para um número PARS (`2205` + PRO), o Peek pergunta à TForce ao mesmo tempo que à FAN Courier; para um PRO de oito dígitos sem zero inicial, escolhe TForce Freight em «Alterar» sob «Corresponde a este número» (ou usa «Alterar transportadora» depois). Um link para o seguimento TForce (`proNumbers=`) ou UPS Freight (`TrackProNumber=`) identifica a TForce de imediato («Transportadora detetada»).

O Peek consulta a TForce apenas pelo PRO. Mostra estado, cada linha com cidade e estado, dia estimado de entrega, país de destino no mapa, peso nas tuas unidades e, só para ti, a localidade do destinatário no mapa e o nome em «Destinatário»; omite remetente, marcação, números BOL e PO, número de volumes, reboques, códigos de terminal e o documento de prova de entrega. «Abrir o site de TForce Freight» abre o teu número (ou o PRO de um PARS) no site da TForce. Se o seguimento falhar ou pedir CAPTCHA, o Peek pode consultar [serviços de seguimento](guide:universal-tracker).

A TForce dá cada linha na hora local do centro de serviço, sem fuso horário. O Peek lê-a no relógio da localidade — ou do estado/província, de uma linha vizinha ou do destino, e na falta disso pela hora do Leste (Eastern Time) — e mostra-a no teu fuso horário («Horas no teu fuso horário:»).

O Peek consulta diretamente a TForce até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6:00 no relógio do último registo (hora do Leste por omissão) no dia em que a TForce a regista em distribuição, enquanto estiver aberta no Peek ou no dia da entrega com as notificações ligadas. Após dois dias sem novidades (12 horas enquanto ainda mostrar «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Para as notificações, podes escolher «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a TForce, a loja ou o remetente por ti nem apresentar reclamações.

## Perguntas frequentes sobre TForce Freight

### O que é um número PRO da TForce Freight?

O número de seguimento da TForce: nove dígitos, sendo o último um dígito de controlo de 0 a 6. É fornecido pela loja ou empresa que contratou o frete.

### Posso seguir a TForce Freight sem número PRO?

Na página de seguimento da TForce, sim: por conhecimento de embarque (BOL) ou número de nota de encomenda (PO) com um intervalo de datas de envio. O Peek segue apenas por PRO.

### A TForce Freight entrega ao domicílio?

Sim, onde os camiões possam legalmente entrar, com custo extra. A TForce combina primeiro data e hora aproximada contigo ou com o remetente e entrega à entrada do rés-do-chão, garagem ou telheiro. Entrega no interior e plataforma elevatória também custam à parte.

### Quanto tempo tenho para apresentar reclamação à TForce Freight?

Nove meses a partir da entrega, ou da data de envio se nada chegou. Comunica danos descobertos após a entrega no prazo de 5 dias úteis.

### Qual é o número de apoio ao cliente da TForce Freight?

(800) 333-7400, gratuito a partir dos EUA e do Canadá. De outros países: (804) 231-8555.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – nomes, formatos PRO, links, textos e etapas, resposta não encontrado, dados lidos pelo Peek, horários
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transportadoras com o mesmo formato, sem passagem a outro operador, serviços de seguimento alternativos
:::
