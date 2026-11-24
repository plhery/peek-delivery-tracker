---
title: Rastreamento USPS: códigos, status, entrega e contato
description: Rastreie encomendas dos correios dos EUA (USPS): formatos de código, significado dos status em inglês, retirada em agência, extravio e telefone.
slug: rastreamento-usps
published: 2026-11-24
updated: 2026-11-24
---

O USPS (U.S. Postal Service, serviço postal dos Estados Unidos) entrega em todos os endereços dos EUA, e o rastreamento é gratuito na maioria das modalidades domésticas de encomendas. Você pode digitar o número no USPS Tracking em `usps.com`, enviá-lo por SMS para `28777` (nos EUA) ou colá-lo na caixa acima. O código é enviado pelo remetente, geralmente no e-mail de envio da loja.

## Onde encontrar e como identificar um código USPS

Na etiqueta do pacote, o código aparece abaixo do código de barras. Como destinatário, peça o número ao remetente: agências dos correios e a central de atendimento não conseguem buscá-lo sem o código. Usuários do Informed Delivery veem os códigos das encomendas recebidas no próprio painel. Veja [como encontrar o código de rastreamento](guide:find-tracking-number).

Os códigos USPS têm de 13 a 34 caracteres. Os formatos mais comuns (com dígitos inventados):

| Formato (inventado) | O que é |
| --- | --- |
| `9400 1482 9173 6058 2917 46` | 22 dígitos começando por `94`, `95`, `93`, `92` ou `91`: etiqueta padrão de encomenda nos EUA. |
| `9205 5948 2917 3605 8291 7406 88` | 26 dígitos começando por `92`. |
| `420 90210 9400 1482 9173 6058 2917 46` | Código de barras completo lido por scanner: `420`, o ZIP Code (CEP americano) e o número da remessa (o bloco do ZIP pode ser omitido). |
| `RR482917367US` | Código postal internacional: 2 letras, 9 dígitos e `US` no final. |

O código de barras impresso no aviso de entrega não realizada (PS Form 3849) também funciona no USPS Tracking.

Alguns formatos são compartilhados: códigos de 22 dígitos iniciados por `9261` ou `9361` também podem ser DHL eCommerce, códigos FedEx Ground Economy podem vir precedidos por `92` e códigos de 20 dígitos pertencem a várias transportadoras. Se o USPS não reconhecer o número, confira no e-mail da loja qual transportadora foi usada. Veja [formatos de códigos de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento do USPS

O USPS publica o rastreamento em inglês. Estas são as etapas mais comuns, na ordem habitual:

| Status do USPS | O que significa para você |
| --- | --- |
| `Merchant Order Receipt Notification` | Aviso eletrônico da loja de que recebeu o pedido, antes de qualquer bipagem; o USPS ainda não está com o pacote. |
| `Shipping Label Created, USPS Awaiting Item` | O remetente criou a etiqueta; o USPS ainda não recebeu o pacote. Dúvidas devem ir à loja. |
| `Picked Up by Shipping Partner, USPS Awaiting Item` | Um parceiro logístico coletou o pacote e o entregará ao USPS; a previsão surge quando o USPS o recebe. |
| `USPS in Possession of Item` | O USPS recebeu ou coletou a encomenda fisicamente. |
| `Arrived at USPS Facility` | Em triagem ou deslocamento rumo à agência local (pode repetir no mesmo centro ou em dias diferentes). |
| `Arrived at Post Office` | Chegou à agência dos correios que atende seu endereço, para entrega hoje ou no próximo dia útil postal. |
| `Out for Delivery` | O carteiro saiu para entrega hoje (pode chegar em horário diferente das cartas habituais). |
| `Delivered` | Entregue, com data, hora e local registrados. |
| `Delivery Status Not Updated`, `Awaiting Delivery` | Sem registro de entrega até as 21h locais no dia da saída; o USPS passa a prever entrega no próximo dia útil. |
| `No Access to Delivery Location` | O carteiro não conseguiu acessar a caixa de correio ou a porta; há nova tentativa automática no próximo dia útil. |
| `Notice Left (No Authorized Recipient Available)` | Exigia assinatura e não havia ninguém autorizado; foi deixado aviso, sem nova tentativa automática. |
| `Available for Pickup` | Não pode ser entregue no endereço: retire na agência a partir do próximo dia útil. |
| `Alert` | Alerta de atraso, por exemplo devido ao clima ou outra ocorrência. |

`Available for Pickup` não permite agendar nova entrega: você precisa retirar pessoalmente com documento com foto. `Notice Left (No Secure Location Available)` funciona como o aviso de assinatura, sem segunda tentativa automática. `Insufficient Address` e `No Such Number` indicam devolução ao remetente. Em remessas internacionais enviadas dos EUA para outro país (como o Brasil), `Attempted Delivery Abroad` significa tentativa de entrega sem sucesso pelo correio de destino (o Peek mostra "Tentativa de entrega"), `Held at Delivery Depot/Delivery Office` significa guarda para retirada na agência local, e `Payment of charges - Item being held, addressee being notified` significa retenção aguardando pagamento de tributos de importação pelo destinatário (o Peek mostra "Na alfândega").

## Entrega não realizada: reentrega, retirada na agência e lockers

Após uma [tentativa sem sucesso](guide:missed-delivery), o carteiro deixa o aviso pêssego PS Form 3849 explicando o motivo. Suas opções:

- **Reentrega gratuita (Redelivery):** em `usps.com/redelivery`, no USPS Tracking, pelo QR code do aviso, por telefone ou assinando e deixando o aviso na caixa de correio. Permite um pedido gratuito para o endereço original antes da data de devolução (sem escolha de horário); pedidos online feitos até as 2h CST (segunda a sábado) podem sair no mesmo dia onde o serviço está disponível.
- **Retirada na agência (Post Office):** leve documento oficial com foto e o aviso (terceiros podem retirar com sua autorização por escrito e documento próprio). O USPS Tracking informa endereço e horário da agência.
- **Devolução ao remetente:** caso não deseje receber a encomenda.

**Prazo de guarda:** na maioria das encomendas rastreadas (como Priority Mail e Ground Advantage), há aviso final após 5 dias e devolução após 15 dias; Priority Mail Express retorna 5 dias após a primeira tentativa; remessas vindas do exterior aguardam geralmente 30 dias. Em USPS Smart Lockers, o pacote fica 5 dias no armário e mais 10 dias no balcão.

## Encomenda USPS atrasada, perdida ou não recebida

Confira primeiro os prazos de referência do USPS: Priority Mail leva de 2 a 3 dias úteis, Ground Advantage de 2 a 5, Parcel Select de 2 a 8, e apenas Priority Mail Express possui garantia de prazo. As entregas ocorrem de segunda a sábado, normalmente até as 17h locais.

Se `Out for Delivery`, `Arrived at Post Office` ou `Delivery Status Not Updated` ficar parado por um dia útil postal inteiro — ou se constar `Delivered` há mais de um dia útil sem que o pacote apareça na porta, caixa de correio, garagem, portaria ou com moradores — envie um pedido pelo [formulário de consulta de encomendas do USPS](https://emailus.usps.com/s/package-inquiry). Veja [entregue, mas não recebido](guide:delivered-not-received).

A partir de 7 dias após a postagem, remetente ou destinatário podem abrir uma busca em [Missing Mail search](https://www.usps.com/help/missing-mail.htm) com endereços, descrição e fotos.

**Indenização:** cobre apenas envios com seguro e exige o recibo original de postagem. Avarias devem ser reclamadas em até 60 dias da postagem; extravios, de 15 dias (7 no Priority Mail Express) até 60 dias. Guarde a embalagem e o conteúdo até o encerramento.

## Telefone e atendimento ao cliente do USPS

Os canais oficiais divulgados em `usps.com` são:

- **`1-800-ASK-USPS` (`1-800-275-8777`):** atendimento humano de segunda a sexta, das `8h` às `20h30` ET, e sábado das `8h` às `18h` ET; sistema automático 24 horas.
- **`1-800-222-1811`:** para outras questões de rastreamento, nos mesmos horários (os atendentes consultam o mesmo histórico exibido online).
- **Atendimento online:** formulário de e-mail e chat para problemas em `usps.com` na página [Contact Us](https://www.usps.com/help/contact-us.htm).
- **Serviço de retransmissão:** disque `7-1-1` nos EUA.

> O USPS só envia SMS ou e-mail se você tiver solicitado alertas com um código de rastreamento, e suas mensagens nunca contêm links. A reentrega é gratuita. Encaminhe SMS falsos para `7726` (nos EUA) ou denuncie em `spam@uspis.gov`. Em remessas para fora dos EUA, eventuais tributos de importação são cobrados pelos canais oficiais do correio ou transportadora do país de destino.

## Rastrear encomendas USPS com o Peek

O [Peek](/) identifica o USPS imediatamente para a maioria dos códigos válidos de 22 dígitos, códigos de 26 dígitos iniciados por `92` e códigos de barras `420` (removendo automaticamente o prefixo do ZIP). Para formatos compartilhados (`9261`, `9361` ou 20 dígitos), o Peek consulta primeiro outras transportadoras prováveis e serviços de rastreamento, abrindo o USPS por último. Links de `usps.com` também funcionam.

O Peek abre a página de rastreamento do USPS em navegador no próprio servidor; quando o USPS recusa ou demora, serviços de rastreamento preenchem as informações (embora nem sempre guardem todo o histórico do USPS). O Peek mostra a etapa, cada leitura no mapa com horário convertido do estado de ocorrência para o fuso do seu dispositivo e a data prevista de entrega, com alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega", omitindo nome de quem assinou e instruções de entrega.

As descrições permanecem no inglês do USPS, com entregas exibidas como "Entregue". Quando [UPS](carrier:ups) ou [FedEx](carrier:fedex) repassam um pacote ao USPS, ou quando uma remessa internacional entra nos EUA, o Peek passa a acompanhá-la no USPS assim que surgem novas leituras. Agendamentos de reentrega e o Informed Delivery continuam sendo feitos em `usps.com`.

## Perguntas frequentes sobre o rastreamento USPS

### Por que o USPS mostra `Tracking Not Available`?

O USPS exibe essa mensagem para códigos que ainda não constam na base ou que já expiraram. Confira os dígitos; o USPS mantém a maioria dos históricos por 120 dias.

### O USPS faz entregas aos domingos?

Segundo o USPS, apenas Priority Mail Express e encomendas selecionadas da Amazon são entregues aos domingos. Os demais serviços entregam de segunda a sábado.

### Um SMS do USPS sobre meu pacote é golpe?

Geralmente sim, caso você não tenha pedido notificações. [Mensagens reais do USPS](guide:delivery-scam-texts) só são enviadas após cadastro com o código de rastreamento e nunca trazem links. Fora dos EUA, eventuais taxas de importação são informadas pelo correio local pelos seus canais oficiais.

### O rastreamento do USPS informa a hora exata da entrega?

Não. O sistema pode indicar uma janela estimada de 2 horas (sem garantia), mas o USPS não informa o minuto exato em que o carteiro chegará ao endereço.

:::sources
- [USPS: Where is my package? Tracking Status Help](https://faq.usps.com/articles/FAQ/Where-is-my-package) – significado dos status, pedidos de serviço e encomenda marcada como entregue
- [USPS: USPS Tracking - The Basics](https://faq.usps.com/articles/Knowledge/USPS-Tracking-The-Basics) – rastreamento gratuito, janela estimada, 120 dias e Alert
- [USPS: How to find your tracking number](https://faq.usps.com/articles/Knowledge/How-to-find-your-tracking-number) – solicitar ao remetente, Informed Delivery e código do aviso
- [USPS: Text Tracking FAQs](https://www.usps.com/text-tracking/welcome.htm) – consulta por SMS `28777` e prefixo ZIP `420`
- [USPS: Redelivery - The Basics](https://faq.usps.com/articles/Knowledge/Redelivery-The-Basics) – reentrega gratuita, limite de um pedido e horário de corte
- [USPS: PS Form 3849 Redelivery Notice](https://faq.usps.com/articles/Knowledge/PS-Form-3849-Redelivery-Notice) – opções do aviso de entrega
- [USPS: Second and Final Notice and Return Dates](https://faq.usps.com/articles/Knowledge/What-are-the-Second-and-Final-Notice-and-Return-Dates-for-Redelivery) – prazos de guarda de 5, 15 e 30 dias
- [USPS: Picking Up Mail Held at Your Post Office](https://faq.usps.com/articles/Knowledge/Picking-Up-Mail-that-is-Being-Held-at-Your-Post-Office) – documento com foto, autorização para terceiros e dados da agência
- [USPS: USPS Smart Locker](https://faq.usps.com/articles/Knowledge/USPS-Smart-Locker) – 5 dias no armário e mais 10 dias no balcão
- [USPS: Receive Mail & Packages](https://www.usps.com/manage/) – Delivery Instructions e Package Intercept
- [USPS: Delayed mail and packages?](https://faq.usps.com/articles/Knowledge/Delayed-Mail-and-Packages) – prazos de referência, entregas até as 17h e domingos
- [USPS: Missing Mail and Lost Packages](https://www.usps.com/help/missing-mail.htm) – busca a partir de 7 dias
- [USPS: File a Claim](https://www.usps.com/help/claims.htm) – elegibilidade de seguro, prazos e 13 a 34 caracteres
- [USPS: Contact Us](https://www.usps.com/help/contact-us.htm) – telefones, horários, formulário e serviço `7-1-1`
- [USPS: Scams & Scheme Alerts](https://faq.usps.com/articles/Knowledge/Scams-Scheme-Alerts) – golpes comuns e reentrega sem cobrança
- [U.S. Postal Inspection Service: Smishing](https://www.uspis.gov/news/scam-article/smishing/) – SMS somente sob pedido e encaminhamento para `7726`
- [USPS: Who we are](https://about.usps.com/who/profile/) – cobertura em todos os endereços dos EUA
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dados lidos do USPS pelo Peek e status de remessas internacionais
:::
