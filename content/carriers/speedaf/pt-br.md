---
title: Rastreamento Speedaf Express: códigos, status e contatos
description: Rastreie um pacote da Speedaf Express da China para Nigéria, Quênia, Gana, Paquistão ou EAU: formatos de código, status, tentativas de entrega e contatos.
slug: rastreamento-speedaf
published: 2026-11-24
updated: 2026-11-24
---

A Speedaf Express (速达非) é uma rede de entregas fundada em 2019 que transporta encomendas por via aérea de seus armazéns na China para a África, o Oriente Médio, o Paquistão e alguns países europeus, fazendo ela mesma a entrega final. Rastreie uma encomenda em "Track" no site [speedaf.com](https://www.speedaf.com/cn-en/send-parcel), no aplicativo de clientes da Speedaf ou na caixa acima. O código fica nos dados de envio da loja.

## Código de rastreamento Speedaf: como reconhecer

O código da Speedaf começa com a sigla do país de destino: `NG` para Nigéria, `KE` para Quênia e assim por diante. Dois formatos, com dígitos inventados:

| Código | O que é |
| --- | --- |
| `NG482910573R` | Sigla do país, 9 dígitos e uma letra — normalmente `R`, às vezes `E`. |
| `KE482910573649` | Sigla do país e 12 dígitos. |
| `PK482910573649T` | O mesmo formato com um `T` ao final. |
| `LP48291057364918` | Não é Speedaf: referência da [Cainiao](carrier:cainiao) em pedidos do AliExpress. |

O rastreador da Speedaf aceita até 20 códigos por vez, um por linha. Digite sempre em maiúsculas: minúsculas são tratadas como código desconhecido. Sem número? Veja [como encontrar um código de rastreamento](guide:find-tracking-number) ou [pedidos do AliExpress](guide:aliexpress-orders).

## O que significam os status da Speedaf

A Speedaf registra seus eventos em inglês — ou em chinês e, às vezes, no idioma do seu país. Os nomes das unidades aparecem entre colchetes 【assim】 na linha. Na ordem de um pacote vindo da China:

| A Speedaf mostra | O Peek mostra | O que significa para você |
| --- | --- | --- |
| `processing, order has been inbounded` | Recebida pela transportadora | O armazém da Speedaf na China, citado antes, recebeu o pacote. |
| `processing assembling operation`, `processing, order has been outbounded` | Em trânsito | Ensacado e depois despachado do armazém. |
| `clearance in progress` | Na alfândega | Declaração de exportação na China. |
| `Delivery to the airport`, `Cargo flight departured`, `Cargo flight arrived` | Em trânsito | A caminho do aeroporto, voo partiu e voo pousou no seu país. |
| `Clearance handover`, `Customs clearance in progress` | Na alfândega | Levado do aeroporto à alfândega do seu país. |
| `pending Tax` | Na alfândega | Há imposto a pagar antes de a alfândega liberar (veja abaixo). |
| `Customs clearance tax paid` | Na alfândega | Imposto pago; o desembaraço continua. |
| `Customs Clearance Completed` | Em trânsito | Liberado pela alfândega. |
| `Arrived at DC Warehouse`, `Arrival at Speedaf DC` | Em trânsito | No armazém ou centro de distribuição da Speedaf no seu país. |
| `Received in` | Em trânsito | A primeira unidade da Speedaf no seu país recebeu o pacote. Não é uma nova coleta. |
| `Loaded at`, `Departed for`, `Arrive at Distribution`, `Arrived at Site` | Em trânsito | Circulando entre os centros da Speedaf e a unidade que entrega a você. |
| `scanned for delivery` | Saiu para entrega | Com o entregador para entrega. |
| `Issue Parcel Reason【Rescheduled delivery】` | Tentativa de entrega | A entrega foi remarcada para outra data, indicada entre colchetes depois. |
| `Issue Parcel Reason【Receiver Rejected Parcel】` | Precisa de atenção | O pacote foi recusado. |
| `Issue Parcel Reason【Delayed payment of tax by customer】` | Na alfândega | O entregador voltou com o pacote porque o imposto não foi pago. |
| `Please come to collect with the Pick-Up Code` | Pronta para retirada | Está na unidade da Speedaf nomeada antes. Retire usando o código de retirada. |
| `Parcel delivered 【Collected】`, `【Parcel delivered】` | Entregue | Entregue ou retirado. A linha pode informar o ponto de retirada. |
| `Transported to the warehouse，【Return Overdue】` | Precisa de atenção | Voltando ao armazém da Speedaf. |
| `Warehouse received` | Devolvida ao remetente | O pacote devolvido chegou de volta ao armazém da Speedaf. |

Um simples `In Transit` pode esconder uma retenção: o código interno da Speedaf por trás dele indica pacote retido para conferência de estoque. `Products not for personal use` e `Clearance Exception` significam que a alfândega levantou uma dúvida ou problema; `Long Time Tax No Paid,Email To Sender` indica que o imposto ficou muito tempo sem pagamento e a Speedaf avisou o remetente por e-mail. Em chinês, observe `进行处理，订单已操作入库` ("Recebida pela transportadora"), `清关中` ("Na alfândega"), `已做派件扫描` ("Saiu para entrega") e `包裹【已签收】` ("Entregue").

## Entrega da Speedaf: porta a porta, retirada e tentativas

A Speedaf opera entregadores próprios na última milha, com entrega porta a porta e pagamento na entrega (COD). A página "Look for Service Site" mapeia suas unidades.

As regras de entrega constam dos termos de cada país (escritos para o remetente) e variam entre eles; os da Nigéria e dos EAU cobrem as entregas domésticas nesses países:

- **Emirados Árabes Unidos:** cada remessa tem 3 tentativas gratuitas de entrega; não sendo possível entregar, volta ao remetente.
- **Quênia, Uganda e Paquistão:** o pacote retorna ao remetente se não puder ser entregue até a terceira tentativa, ou se você recusar o pacote ou o pagamento de taxas. Os três acrescentam que a Speedaf entrega no endereço, mas não necessariamente em mãos à pessoa citada.
- **Nigéria e EAU:** um pacote é considerado não entregável se você o recusar ou não pagar o valor COD, se o endereço estiver errado ou fora da área atendida, ou se o dia pedido por você ultrapassar o prazo de guarda da Speedaf (não informado por nenhum dos dois).

A Speedaf não publica prazo de retirada para suas unidades. Perdeu o entregador? Veja [entrega não realizada](guide:missed-delivery).

## Alfândega na Speedaf: pending Tax

Quando há imposto devido, o rastreamento para em `pending Tax`. Em envios para a África do Sul, a página da Speedaf exibe então um link "I want to pay taxes" ao lado do código, mudando para "Taxes paid" após o pagamento.

Se um SMS ou e-mail cobrar pagamento, confira primeiro o status oficial em speedaf.com: veja [golpes de SMS de entrega](guide:delivery-scam-texts) e [encomendas paradas na alfândega](guide:customs).

## Pacote Speedaf atrasado, extraviado ou entregue sem chegar

**Parado.** Se o rastreamento não muda há dias, veja [como rastrear encomendas da China](guide:tracking-from-china) e [rastreamento que não atualiza](guide:tracking-not-updating).

**Quem pode reclamar.** Os termos do Quênia, Uganda e Paquistão dizem que a reclamação existe apenas entre o remetente e a SPEEDAF; os da Nigéria e dos EAU afirmam que indenizações só são acertadas com o remetente. Em compras online o remetente é a loja, portanto peça reembolso ou reenvio à loja ou marketplace.

**Avise a loja rápido.** Os prazos dos termos da Speedaf são curtos:

- **Avaria:** por escrito em até 7 dias corridos da entrega no Quênia e em Uganda, 72 horas na Nigéria e nos EAU e 48 horas na Arábia Saudita.
- **Entregue, mas não recebido:** contestação do comprovante em até 5 dias corridos na Nigéria e nos EAU, e 5 dias úteis no Paquistão.
- **Extravio:** na Nigéria e nos EAU, reclamação de rastreamento em até 14 dias corridos da coleta. Com mais de 24 horas sem movimentação, a Speedaf procura por 7 dias úteis e então declara extravio se não localizar.

A página da Speedaf informa quem assinou pelo pacote: verifique primeiro. Mais em [entregue, mas não recebido](guide:delivered-not-received) e [encomendas extraviadas](guide:lost-parcel).

## Telefone e atendimento da Speedaf

Cada site nacional da Speedaf lista seus próprios contatos no rodapé (os da Irlanda e da Zâmbia aparecem no seletor de países: escolha o país e depois English):

| País | Telefone | E-mail | Horário |
| --- | --- | --- | --- |
| Nigéria | +234 800 070 7070, +234 708 063 6400, +234 9073064940 (também WhatsApp) | ng.info@speedaf.com, nigeria.info@speedaf.com | Seg–Sex 8h–17h, Sáb 8h–14h |
| Gana | +233 30 824 9100 | gh.info@speedaf.com | Seg–Sáb 8h30–17h30 |
| Quênia | +254 741 000 666, +254 741 000 888; WhatsApp +254 741000666 | kenya.info@speedaf.com | Seg–Sex 8h–18h, Sáb 8h–14h |
| Uganda | +256 709 198 795 | ug.info@speedaf.com | Seg–Sex 9h–17h, Sáb 9h–14h |
| Zâmbia | +260 971269390 | zm.info@speedaf.com | Seg–Sex 7h45–17h15, Sáb 8h–13h |
| África do Sul | +27 108247184, +27 726393849; WhatsApp +27 664562613 | cs.southafrica@speedaf.com | não informado |
| Paquistão | +92 311 118 7654; WhatsApp +92 3450010817 | pk.info@speedaf.com | Seg–Dom 9h–18h |
| EAU | +971 800 178 | ae.info@speedaf.com | Seg–Sáb 9h–18h |
| Arábia Saudita | não listado | sa.info@speedaf.com | não informado |
| Irlanda | +35312633068; WhatsApp +353 834281088 | info_customerserviceie@speedaf.com | Seg–Sex 8h30–17h30 |
| Egito | +20 15818, +20 1050383370 | eg.info@speedaf.com | Sáb–Qui 9h–18h |
| Marrocos | +212 522 100 888 | ma.info@speedaf.com | Seg–Sex 9h–18h |
| China | +86 400 888 0975 | info@speedaf.com | Seg–Sex 9h30–18h30 |

A Central de Ajuda da Speedaf acrescenta telefones para os demais países da lista: Cazaquistão +7 778 371 9358, Maurício 4687316, Maldivas +960 7678788, Suíça e Sérvia +41 798559737, Montenegro +382 60021040 e Albânia +355 45620111. Cada site também conta com um formulário "Customer Support".

## Como rastrear um pacote da Speedaf no Peek

O [Peek](/) identifica a Speedaf pelo formato quando o código começa com NG, GH, KE, UG, RW, ZM, ZA, EG, MA, SA, AE, PK, KZ, MU, RE, MV ou TR seguido de 9 dígitos e R ou E. Quando traz 12 dígitos após um desses códigos ou IE, o formato sozinho não basta: o Peek consulta a Speedaf mostrando "Consultando serviços de rastreamento…" e depois "Speedaf está com esta encomenda." se ela reconhecer o código. O Peek não tem formato próprio para Suíça, Albânia, Montenegro ou Sérvia: nesses casos, ou em códigos fora desses padrões, escolha "Speedaf" em "Trocar". Um link de speedaf.com com `tradeNo=` identifica a Speedaf qualquer que seja o número. O Peek envia o número em maiúsculas, então pode digitar em minúsculas.

O Peek lê o rastreamento próprio da Speedaf: status, histórico, país de destino e horário de entrega. Cada evento traz o relógio da unidade (horário da China nos armazéns e depois do seu país), e o Peek converte para o fuso do seu aparelho; em peektracker.com e nos aplicativos para iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele. A Speedaf só cita unidades dentro dos textos, então não há mapa, e ela não dá previsão de entrega. Se a Speedaf reconhece o número mas não mostra leituras (como em alguns pacotes mais antigos), o Peek exibe "Aguardando Speedaf" — ou "Aguardando a primeira movimentação de Speedaf" em códigos de 12 dígitos não atribuídos à Speedaf — e segue verificando. Se um pacote atribuído à Speedaf continuar sem leitura após dois dias e nenhum outro serviço responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar diariamente (semanalmente após uma semana da adição).

Os eventos aparecem como a Speedaf escreve, em inglês ou chinês, removendo nomes de entregadores/funcionários e telefone do entregador. Quem assinou continua visível para você na linha de entrega; links compartilhados ocultam esse dado e mostram apenas `Delivered`. O Peek também omite comprovante de entrega, link de imposto e quantidade de volumes, acompanhando apenas o número adicionado (não outros volumes do mesmo conhecimento). Como a Speedaf entrega por conta própria, não há segunda transportadora.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia em que a Speedaf registra saída para entrega, enquanto o próprio rastreador da Speedaf responder (por meio de serviços de rastreamento, até a cada 15 minutos); fora disso, de hora em hora. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Ele pode avisar em "Cada movimentação", "Etapas importantes" ou "Só a entrega", mas não pode pagar impostos, reagendar entrega nem falar com a Speedaf ou a loja por você.

## Perguntas sobre o rastreamento Speedaf

### A Speedaf Express é confiável?

Sim. A Speedaf é uma empresa de logística fundada em 2019 com a ZTO entre seus acionistas originais. Opera rastreamento próprio em speedaf.com e entregadores próprios no que chama de mercados emergentes.

### Para quais países a Speedaf entrega?

A lista de países da Speedaf inclui Egito, África do Sul, Marrocos, EAU, Gana, Nigéria, Quênia, Uganda, Arábia Saudita, Paquistão, Cazaquistão, Maurício, Maldivas, Suíça, Montenegro, Albânia, Irlanda, Sérvia e Zâmbia, além da China. O Peek também reconhece códigos iniciados por RE (Reunião), RW (Ruanda) e TR (Turquia), que não constam da lista.

### Quanto tempo a Speedaf leva para entregar?

A página do Speedaf eParcel — para pacotes da China para Nigéria, Gana, Quênia, Uganda, Marrocos, Egito, Arábia Saudita, EAU e Paquistão — indica tempo de trânsito de referência de 5 a 10 dias úteis. A central de ajuda diz que varia conforme o destino, e os termos de Quênia, Uganda e Paquistão esclarecem que prazos não são garantidos.

### O que significa pending Tax no rastreamento da Speedaf?

Que há imposto ou taxa a pagar antes de a alfândega do seu país liberar o pacote. Após o pagamento, aparecem `Customs clearance tax paid` e depois `Customs Clearance Completed`.

:::sources
- [Speedaf: Enterprise Introduction](https://www.speedaf.com/ng-en/business/about-us) – fundada em 2019 e ZTO
- [Speedaf: Select your location and language](https://www.speedaf.com/cn-en/user-opt/locale-select) – países e contatos de Irlanda, Zâmbia e China
- [Speedaf: Speedaf eParcel](https://www.speedaf.com/ke-en/product/intl-straight-small) – pacotes da China para lojas online, 5 a 10 dias úteis
- [Speedaf: Warehouse & Fulfillment](https://www.speedaf.com/ke-en/product/warehouse-promise) – entregadores próprios e pagamento na entrega
- [Speedaf: Last-mile delivery](https://www.speedaf.com/ke-en/product/warehouse-delivery) – pagamento na entrega e porta a porta
- [Speedaf: Help Center](https://www.speedaf.com/ng-en/help) – prazo, rastreamento no site/app e telefones por país
- [Speedaf: Track](https://www.speedaf.com/ng-en/send-parcel) – até 20 números e link de impostos na África do Sul
- [Speedaf: Look for Service Site](https://www.speedaf.com/ke-en/location-map) – mapa de unidades
- [Speedaf: Customer Support](https://www.speedaf.com/ng-en/service/support) – formulário de contato
- [Speedaf Nigeria: Home](https://www.speedaf.com/ng-en) – telefones, e-mails, horários e WhatsApp
- [Speedaf Ghana: Home](https://www.speedaf.com/gh-en) – telefone, e-mail e horários
- [Speedaf Kenya: Home](https://www.speedaf.com/ke-en) – telefones, e-mail, horários e WhatsApp
- [Speedaf Uganda: Home](https://www.speedaf.com/ug-en) – telefone, e-mail e horários
- [Speedaf South Africa: Home](https://www.speedaf.com/za-en) – telefones, e-mail e WhatsApp
- [Speedaf Pakistan: Home](https://www.speedaf.com/pk-en) – telefone, e-mail, horários e WhatsApp
- [Speedaf UAE: Home](https://www.speedaf.com/ae-en) – telefone, e-mail e horários
- [Speedaf Saudi Arabia: Home](https://www.speedaf.com/sa-en) – e-mail
- [Speedaf Egypt: Home](https://www.speedaf.com/eg-en) – telefones, e-mail e horários
- [Speedaf Morocco: Home](https://www.speedaf.com/ma-en) – telefone, e-mail e horários
- [Speedaf Kenya: Terms and Conditions](https://www.speedaf.com/ke-en/service-rule) – tentativas, recusa, destinatário, reclamações e prazos
- [Speedaf Uganda: Terms and Conditions](https://www.speedaf.com/ug-en/service-rule) – o mesmo para Uganda
- [Speedaf Pakistan: Terms and Conditions](https://www.speedaf.com/pk-en/service-rule) – tentativas, destinatário, reclamações e entrega contestada
- [Speedaf UAE: Terms & Conditions](https://www.speedaf.com/ae-en/service-rule) – entregas domésticas: tentativas, pacotes não entregáveis, prazos de reclamação e indenizações com o remetente
- [Speedaf Nigeria: Service Agreement](https://www.speedaf.com/ng-en/service-rule) – entregas domésticas: pacotes não entregáveis, prazos de reclamação e indenizações com o remetente
- [Speedaf Saudi Arabia: Express Shipping Terms](https://www.speedaf.com/sa-en/service-rule) – avaria em até 48 horas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – rede, formatos de código (RE, RW e TR conferidos em 8 de novembro de 2026), textos e significados dos eventos, o que a página da Speedaf e o Peek mostram
:::
