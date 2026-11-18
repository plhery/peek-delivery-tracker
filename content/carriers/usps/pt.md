---
title: Seguimento USPS: número, estados e encomendas dos EUA
description: Segue uma encomenda USPS vinda dos Estados Unidos: onde está o código, o que significam os estados em inglês, alfândega, extravios e contactos USPS.
slug: seguimento-usps
published: 2026-11-17
updated: 2026-11-17
---

A USPS, o United States Postal Service, é o serviço postal público dos Estados Unidos: chega a todas as moradas do país e inclui seguimento gratuito (USPS Tracking) na maioria dos seus serviços domésticos de encomendas e em muitos produtos internacionais, embora não em todos. Introduz o código no USPS Tracking em usps.com ou cola-o na caixa acima; nos Estados Unidos também podes enviá-lo por SMS para `28777`. Quem te dá o número é o remetente, normalmente no e-mail de envio da loja.

## Número de seguimento USPS: onde encontrar e como é

Na etiqueta é a sequência de caracteres por baixo do código de barras. Como destinatário, pede-o ao remetente: nem as estações de correios (Post Offices) nem o apoio ao cliente da USPS conseguem procurá-lo por ti. Nos Estados Unidos, quem usa o Informed Delivery vê no painel os códigos das encomendas a caminho. Mais ajuda em [como encontrar o número de seguimento](guide:find-tracking-number).

Os números da USPS têm entre 13 e 34 caracteres. Os formatos mais habituais, com algarismos fictícios:

| Número (exemplo fictício) | O que é |
| --- | --- |
| `9400 1000 4829 1057 3649 24` | 22 dígitos começados por `94`, `95`, `93`, `92` ou `91`: a etiqueta habitual de encomenda nos EUA. |
| `9205 5900 4829 1057 3649 2817 33` | 26 dígitos começados por `92`. |
| `420 10001 9400 1000 4829 1057 3649 24` | O código de barras completo tal como o leitor o lê: `420`, o código postal norte-americano (ZIP Code) e depois o número da encomenda. Podes omitir a parte inicial com o ZIP. |
| `CP482910576US` | Um código postal internacional: 2 letras, 9 dígitos e `US`. Para reclamações internacionais, a USPS refere códigos iniciados por `EA` a `EZ`, `CA` a `CZ`, `HC` a `HZ` ou `RA` a `RZ`. |

O número impresso sob o código de barras do aviso de entrega falhada PS Form 3849 deixado pelo carteiro nos EUA também funciona no USPS Tracking.

Alguns formatos são partilhados com outras redes: códigos de 22 dígitos começados por `9261` ou `9361` podem pertencer também à DHL eCommerce, Austrian Post ou Estafeta, entre outras, e números de 20 dígitos são usados por transportadoras como a NZ Post. Se a USPS não encontrar registo, confirma que transportadora vem indicada no e-mail da loja. Vê [formatos dos números de seguimento](guide:tracking-number-formats).

## Estados da USPS: o que significa cada um

A USPS escreve o seguimento em inglês. Quase todos estes estados são explicados na ajuda da USPS e descrevem o percurso normal até à entrega e eventuais imprevistos:

| A USPS escreve | O que significa para ti | No Peek |
| --- | --- | --- |
| `Merchant Order Receipt Notification` | Aviso eletrónico da loja de que recebeu a tua encomenda; ainda nada foi lido pela USPS. | «Etiqueta criada» |
| `Shipping Label Created, USPS Awaiting Item` | O remetente criou a etiqueta, mas a USPS ainda não tem a encomenda. Qualquer dúvida deve ir para a loja. | «Etiqueta criada» |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | Um parceiro de transporte recolheu o volume e vai entregá-lo à USPS. A data prevista surge quando a USPS o receber. | «Etiqueta criada» |
| `USPS in Possession of Item` | A USPS aceitou ou recolheu a encomenda. Não precisas de fazer nada. | «Aceite pela transportadora» |
| `Arrived at USPS Facility` | Está em triagem ou em trânsito para a estação distribuidora. A linha pode repetir-se no mesmo centro ou em dias diferentes. | «Em trânsito» |
| `Arrived at Post Office` | Chegou à estação de correios que faz a entrega: segundo a USPS, deve chegar hoje ou no dia útil postal seguinte. | «Em trânsito» |
| `Out for Delivery` | Um carteiro entrega-a hoje, talvez não o habitual da tua rua e por vezes mais tarde do que o correio normal. | «Em distribuição» |
| `Delivered` | Entregue, com data, hora e local registados. | «Entregue» |
| `Delivery Status Not Updated`, `Awaiting Delivery` | Até às 21:00 locais do dia de `Out for Delivery` não entrou leitura de entrega; a USPS passa a apontar para o dia útil seguinte. | `Awaiting Delivery`: «Em trânsito»; `Delivery Status Not Updated`: sem etapa própria, o Peek mantém a anterior, quase sempre «Em distribuição» |
| `No Access to Delivery Location` | O carteiro não conseguiu chegar à caixa de correio ou à porta. A USPS volta a tentar automaticamente no dia útil seguinte. | «Tentativa de entrega» |
| `Notice Left (No Authorized Recipient Available)` | Era necessária assinatura e não estava ninguém. Ficou o aviso PS Form 3849; não há segunda tentativa automática. | «Tentativa de entrega» |
| `Available for Pickup` | Não entregável na morada: levanta na estação de correios a partir do dia útil seguinte. | «Pronta para levantamento» |
| `Alert` | Um atraso causado, por exemplo, por mau tempo, catástrofes naturais ou outros eventos. | «Problema reportado» |

Em `Available for Pickup`, a USPS esclarece que não é possível marcar nova entrega: tens de ir levantar o volume com documento de identificação com fotografia. `Notice Left (No Secure Location Available)` significa que o carteiro não encontrou um local seguro e levou a encomenda para a estação, também sem nova tentativa automática. `Insufficient Address` e `No Such Number` indicam, segundo a USPS, que o envio volta para o remetente, que terá de o reenviar com novo porte; no Peek ambos contam como «Tentativa de entrega». Nas remessas para fora dos EUA, `Attempted Delivery Abroad` significa que o operador postal de destino tentou entregar e não conseguiu («Tentativa de entrega» no Peek), ficando o seguimento seguinte nas leituras desse operador. `Held at Delivery Depot/Delivery Office` indica que os correios de destino guardam o objeto na loja para levantamento, linha que surge poucos minutos após a chegada ali («Pronta para levantamento» no Peek). Por fim, `Payment of charges - Item being held, addressee being notified` quer dizer que o correio de destino retém a encomenda até ao pagamento dos encargos aduaneiros e notifica o destinatário («Na alfândega» no Peek).

A USPS regista a hora de cada leitura na hora local do sítio onde ocorreu.

## Encomenda vinda dos EUA: entrega em Portugal e no Brasil

**Quem entrega.** Num código postal internacional terminado em `US` com dígito de controlo válido, o Peek consulta também o operador postal do teu país com o mesmo número: os [CTT](carrier:ctt) em Portugal e os Correios no Brasil (conforme a região do iPhone ou o país de ligação no navegador). Mal esse operador mostre uma leitura pelo menos tão recente como a última da USPS, o Peek passa a segui-la aí mantendo o histórico da USPS. Em códigos compostos apenas por algarismos, o Peek não consulta outra posta.

**Alfândega.** Qualquer encomenda vinda dos EUA passa pela alfândega: vê [encomenda retida na alfândega](guide:customs). Em Portugal, como em toda a UE, as compras online vindas de fora da UE até 150 € pagam desde 1 de julho de 2026 um direito fixo de 3 € por categoria de artigo, mais desde 1 de novembro de 2026 uma taxa de manuseamento da UE (*Union handling fee*) de 2 € por categoria, independentemente do valor. O Brasil aplica as suas próprias regras federais de importação e tributação postal.

**Entrega falhada nos EUA.** Após uma [tentativa falhada](guide:missed-delivery) nos EUA, fica na caixa o aviso cor de pêssego PS Form 3849. A nova tentativa (*Redelivery*) é gratuita mas não existe em todas as moradas: um só pedido, para a morada original e sem escolha de hora (online até às 2:00 CST de segunda a sábado para possível entrega no próprio dia, por código QR, telefone ou aviso assinado na caixa). Em alternativa, levanta na estação com identificação com foto e o aviso, ou autoriza outra pessoa assinando o verso com o nome dela. Na maioria das encomendas registadas sai um segundo aviso aos 5 dias e a devolução ocorre aos 15 (5 dias após a primeira tentativa em Priority Mail Express; 30 dias na maioria das vindas do estrangeiro; 5 dias num USPS Smart Locker mais 10 ao balcão).

## Encomenda USPS atrasada, perdida ou devolvida

**Dos EUA para ti.** Só o remetente nos EUA pode abrir online uma averiguação internacional na USPS; se compraste online, fala com a loja. O extravio participa-se entre 3 e 90 dias após o envio em Priority Mail Express International e entre 7 dias e 6 meses em Priority Mail International ou Registered Mail (sem averiguação online em First-Class Package International Service não registado). Vê [encomenda perdida](guide:lost-parcel) e [seguimento sem atualização](guide:tracking-not-updating).

**De ti para os EUA.** A indemnização cabe ao remetente estrangeiro salvo cedência escrita ao destinatário nos EUA: reclama nos correios de origem, devendo qualquer volume danificado ser apresentado logo numa estação da USPS.

**Dentro dos EUA.** Prazos indicativos: 2–3 dias úteis em Priority Mail, 2–5 em Ground Advantage, 2–8 em Parcel Select; só Priority Mail Express é garantido. Entregas de segunda a sábado, em regra até às 17:00 locais. Se `Out for Delivery`, `Arrived at Post Office` ou `Delivery Status Not Updated` parar um dia útil postal inteiro — ou se `Delivered` tiver mais de um dia útil sem pacote no alpendre, caixa, garagem, moradores ou administração ([entregue mas não recebida](guide:delivered-not-received)) — abre um *service request* na USPS. Aos 7 dias após o envio, remetente ou destinatário podem abrir em usps.com uma procura Missing Mail.

**Indemnizações domésticas nos EUA.** Só para envios com seguro e recibo original de expedição: danos até 60 dias, perda entre 15 e 60 dias (7–60 em Priority Mail Express). Guarda caixa e conteúdo. Vê também [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente da USPS: telefone, contactos e SMS falsos

Em 17 de novembro de 2026, «Contact Us» da USPS indica:

- **1-800-ASK-USPS** (`1-800-275-8777`), segunda–sexta 8:00–20:30 e sábado 8:00–18:00 na hora da Costa Leste (ET, normalmente 5 horas atrás de Lisboa e 1 a 2 atrás de Brasília); atendimento automático de seguimento 24 horas.
- **1-800-222-1811** para seguimento nacional e internacional, no mesmo horário (os agentes veem os mesmos dados do USPS Tracking).
- O formulário «Email Customer Service» e chat para falhas técnicas em usps.com.
- Nos EUA, encaminhamento pelo `7-1-1` para pessoas surdas, com deficiência auditiva ou da fala.

A USPS não refere se os `1-800` funcionam fora da América do Norte; em compras vindas dos EUA fala primeiro com o remetente. Segundo o USPIS e a USPS, nos EUA a USPS só envia SMS ou e-mails de seguimento se os pediste com o número de rastreio, essas mensagens nunca trazem ligações e a nova tentativa é gratuita. Estas regras valem só para a USPS: fora dos EUA, os encargos de importação são pedidos por quem entrega, pelos seus próprios canais. Os CTT dizem não solicitar pagamentos por email ou SMS: todo o desalfandegamento é feito no portal de desalfandegamento dos CTT. Nos EUA, reencaminha SMS suspeitos para `7726` ou `spam@uspis.gov`; vê [SMS falsos de entregas](guide:delivery-scam-texts).

Páginas da USPS aqui citadas, lidas em 17 de novembro de 2026: em about.usps.com «Who we are»; em faq.usps.com «Where is my package? Tracking Status Help», «USPS Tracking - The Basics», «How to find your tracking number», «PS Form 3849 Redelivery Notice», «Redelivery - The Basics», «What are the Second and Final Notice and Return Dates for Redelivery?», «Picking Up Mail that is Being Held at Your Post Office», «USPS Smart Locker», «Delayed mail and packages?» e «Scams & Scheme Alerts»; em www.usps.com «Text Tracking FAQs», «Receive Mail & Packages», «Informed Delivery», «Missing Mail and Lost Packages», «File a Claim», «File a USPS Claim: International» e «Contact Us».

## Seguir uma encomenda USPS com o Peek

O [Peek](/) reconhece logo a USPS na maioria das etiquetas de 22 dígitos com validação certa, na maioria dos números de 26 dígitos em `92`, nos códigos `420` lidos por scanner (retirando o ZIP) e nos códigos `US` iniciados por `C` (exceto `CE` e `CV`), `E`, `L` ou `R` válidos. Nos formatos partilhados (números em `9261` ou `9361` e números de 20 dígitos), mostra «A verificar serviços de seguimento…», pergunta antes às restantes candidatas (DHL eCommerce, Austrian Post ou NZ Post) e aos serviços de seguimento, abrindo a página da USPS no fim. Se não aparecer, toca em «Alterar» em «Corresponde a este número» ou usa depois «Alterar transportadora». Uma ligação de seguimento usps.com com o código também funciona.

O Peek abre o seguimento da USPS num navegador no seu servidor; se a USPS desviar ou recusar o pedido, completam os serviços de seguimento, nem sempre com todo o histórico. Mostra estado, leituras com local e hora lida no estado dos EUA e convertida para o teu dispositivo («Horas no teu fuso horário: …»), além do dia previsto e eventual janela de 2 horas, omitindo quem assinou e instruções. Os eventos ficam em inglês exceto `Delivered`, mostrado como «Entregue».

O Peek consulta a USPS até a cada 30 minutos enquanto tens a encomenda aberta no ecrã ou no dia de entrega com notificações ativas (incluindo em distribuição), aguarda uma hora após falha e verifica de hora a hora nos restantes casos — também após dois dias sem leituras ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora» (a cada 3 horas após um dia parado só com serviços de seguimento). Alertas à escolha: «Todas as leituras», «Passos importantes» ou «Só a entrega».

Quando a [UPS](carrier:ups) ou a [FedEx](carrier:fedex) passam um envio à USPS, o Peek segue-o na USPS mal haja avanço. Num objeto internacional terminado em `PT` ou `BR` rumo aos EUA, o Peek consulta também a USPS quando o operador de origem indica os EUA como destino ou quando o teu país para o Peek são os EUA. Reentregas, Informed Delivery e reclamações ficam em usps.com.

## Perguntas frequentes sobre o seguimento da USPS

### O que significa «Tracking Not Available» na USPS?

A USPS não encontra dados para esse código: ainda não entrou no sistema, tem um algarismo trocado ou já expirou. Confere cada carácter; a USPS guarda o histórico do USPS Tracking durante 120 dias e o dos envios internacionais numerados até 2 anos.

### É possível seguir qualquer encomenda vinda dos EUA?

Não. Segundo a USPS, o seguimento está incluído em muitos mas não em todos os serviços internacionais e pode variar conforme o país de destino. Pergunta à loja se o envio tem número de seguimento.

### A USPS faz entregas ao domingo?

Apenas de envios Priority Mail Express e de encomendas Amazon, refere a USPS. Tudo o resto é entregue de segunda a sábado.

### A USPS indica uma hora exata de entrega?

Não. Pode apresentar uma janela estimada de 2 horas (*Expected Delivery Window*), sem garantia, e a própria USPS refere não saber a que hora o carteiro chega a cada porta. O Peek mostra o dia previsto e, quando a USPS a indica, também essa janela.

:::sources
- [U.S. Postal Inspection Service: Smishing – Package Tracking Text Scams (em inglês)](https://www.uspis.gov/news/scam-article/smishing/) – SMS apenas mediante pedido com código de seguimento, sem ligações nas mensagens reais, denúncia para 7726 e spam@uspis.gov
- [Comissão Europeia: EU customs reform (em inglês)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro de 3 € até 150 € desde 1 de julho de 2026 e taxa de manuseamento de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Customs Guidance on EUR 3 customs duty (em inglês)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por categoria de artigo e taxa independente do valor
- [CTT: Fim da isenção de direitos aduaneiros em compras até 150 € fora da UE](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia) – desalfandegamento em Portugal de encomendas vindas de fora da UE
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de números, estados e etapas no Peek, fusos horários dos EUA e transição para os correios nacionais
:::
