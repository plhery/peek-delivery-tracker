---
title: Seguimento Quickpac: número de 18 dígitos e contacto
description: Segue uma encomenda Quickpac na Suíça com o número de 18 dígitos: no seguimento inglês da Planzer, Shipped significa entregue. Recolhas e contactos.
slug: seguimento-quickpac
published: 2026-11-23
updated: 2026-11-23
---

A Quickpac é um serviço suíço de encomendas integrado na Planzer Paket (Planzer Parcel em inglês) desde 2024, que entrega compras de lojas ao domicílio em zonas postais selecionadas da Suíça. O número de seguimento de 18 dígitos chega através do remetente — normalmente no e-mail de envio — e aparece abaixo do código de barras da encomenda. Introduz o número na [página de seguimento da Quickpac](https://quickpac.ch/en/tracking) ou cola-o na caixa acima.

## Número de seguimento Quickpac e ID de envio

Apenas empresas expedem com a Quickpac, pelo que o número chega pelo remetente: no e-mail ou ligação de envio, nos e-mails e SMS enviados pela Quickpac em seu nome e no cartão «Abholinformationen» após ausência. As mensagens da Planzer Paket têm o título «A delivery is on its way to you».

A Quickpac chama-lhe número de encomenda ou de expedição, e o site da Planzer «Shipment Number». Não servem para seguir o «Reference number» (referência própria do remetente), um código de levantamento nem o código de entrega do cartão da Planzer Paket.

Os exemplos abaixo usam dígitos inventados; os dois últimos pertencem a [outras transportadoras](guide:tracking-number-formats):

| Número | A quem pertence |
| --- | --- |
| `448291736405829173` | Quickpac: 18 dígitos a começar por `44`. |
| `44.82.917364.05829173` | O mesmo com pontos, tal como a caixa de pesquisa da Quickpac escreve. |
| `994829173640582917` | [Swiss Post](carrier:swiss-post): 18 dígitos a começar por `99` ou `98`. |
| `91346097482917364058` | Planzer: 20 dígitos a começar por `91346097`. |

Se o remetente autorizou, a Quickpac entrega à Swiss Post encomendas que não consegue entregar, surgindo então uma ligação «Post tracking». Um código iniciado por `44` com 17 ou 19 dígitos costuma ter um dígito a menos ou a mais. Sem número? Pergunta ao remetente ou consulta [como encontrar um número de seguimento](guide:find-tracking-number).

## Estados de seguimento da Quickpac e o seu significado

As encomendas Quickpac são consultadas no portal da Planzer e na caixa de quickpac.ch. Ambos seguem o idioma do navegador (inglês, alemão, francês ou italiano), recorrendo o da Planzer ao inglês por omissão. Nenhum publica glossário oficial. Os estados em inglês:

| Estado | O que significa para ti |
| --- | --- |
| `Recorded` | A Quickpac recebeu os dados da encomenda do remetente. |
| `Delivery day unknown` | Ainda não há dia de entrega definido. |
| `Estimated delivery` | Dia previsto de entrega. |
| `Transferred` | [Em trânsito](guide:tracking-statuses). |
| `In delivery` | Em distribuição. |
| `Your consignment is at the following pick-up point` | A aguardar nesse ponto de levantamento; `Latest collection date` indica o último dia. |
| `Shipped` | Entregue, e não «enviada pela loja». |
| `Delivered`, `Parcel delivered` | Entregue. |

`Shipped` induz facilmente em erro: no seguimento inglês da Planzer significa entregue. Em alemão, francês e italiano, a Planzer assinala encomendas entregues como `Ausgeliefert`, `Livré` e `Consegnato`.

Se a caixa da Quickpac apresentar «Unfortunately, parcel tracking is not available at the moment.», o próprio módulo falhou ao carregar: tenta mais tarde.

## Entregas Quickpac, ausências e pontos de levantamento

A Quickpac entrega apenas na Suíça, em áreas postais selecionadas. A rapidez depende do remetente: SameDay e Priority+ chegam no próprio dia, Priority no dia útil seguinte e Economy no máximo até dois dias úteis depois. Entrega noturna até às 21h00 (hora suíça) e entrega ao sábado são extras contratados pelo remetente, e os termos da Quickpac esclarecem que os prazos são indicativos e não garantidos.

**À tua porta.** Encomendas que caibam e possam ser deixadas entram no compartimento de encomendas da caixa de correio sem tocar à campainha. Encomendas com assinatura são entregues em mão; em caso de recusa, voltam ao remetente. A Quickpac não entrega em PickPost, MyPost24, apartados nem posta restante: envia e-mail ou SMS e o seguimento pede uma morada privada.

**Alterar a entrega.** Após verificação com apelido e código postal, o seguimento Quickpac permite autorizar gratuitamente o depósito num local abrigado da tua propriedade até cerca de 15 minutos antes da entrega (sob a tua responsabilidade) e alterar a morada até duas vezes quando disponível. Aceita também e-mail ou telemóvel para avisos gratuitos. Sem internet, o 0800 363 363 regista uma autorização de depósito.

**Ausência.** Há dois conjuntos de regras publicados e nenhuma página indica qual se aplica em cada caso, pelo que deves seguir o cartão ou mensagem recebida:

- **Cartão «Abholinformationen» da Quickpac:** indica um ponto de recolha próximo disponível a partir do dia útil seguinte. A Quickpac indica guarda durante 14 dias antes de devolver ao remetente, mediante o código de recolha do cartão ou do seguimento. Algumas encomendas vão para a Coop Pick-up, que avisa por SMS ou e-mail e devolve após 7 dias.
- **Cartão de entrega da Planzer Paket:** contém um código para o portal de destinatários, onde podes escolher outro dia, indicar local seguro ou ver em que Planzer Parcel shop está a encomenda. A loja guarda normalmente 7 dias: leva o código de levantamento e documento de identificação. Alguns pontos são cacifos abertos 24 horas. O remetente pode desativar opções.

Encomendas passadas à Swiss Post têm nova tentativa e depois ficam na estação de correios.

## Encomenda Quickpac atrasada, perdida ou danificada

**Número não encontrado.** Se surgir «Consignment … not found!» com o número correto, a Quickpac ainda não recebeu os dados do remetente: tenta mais tarde ou contacta o remetente.

**Dias sem atualização.** Pergunta ao remetente ou usa o formulário da Planzer Paket. Vê [seguimento que não atualiza](guide:tracking-not-updating).

**Entregue mas não recebida.** A Quickpac pede para confirmares se ninguém a recolheu e verificares o compartimento de encomendas, escadas e entrada. A Planzer Paket pede para confirmares o estado `Parcel delivered`, consultares «Delivery confirmation» no portal (por vezes com foto) e usares o formulário. Vê [entregue mas não recebida](guide:delivered-not-received).

**Danificada ou perdida.** A Planzer Paket encaminha-te para o remetente, que precisa de fotos do produto, da caixa e da etiqueta.

**Prazo do remetente.** O contrato da Quickpac é com o remetente. Os termos limitam a responsabilidade a 500 CHF por encomenda salvo acordo em contrário, e o remetente tem de reclamar por escrito nos 30 dias seguintes à entrega à Quickpac. Encomendas depositadas com autorização não estão cobertas. Avisa rapidamente a loja.

**Devoluções.** A Quickpac não disponibiliza devoluções atualmente: pergunta ao remetente.

## Apoio ao cliente e contactos Quickpac

A Quickpac encaminha todas as questões sobre encomendas para a Planzer Paket:

- **Apoio ao cliente Planzer Paket:** 0848 00 19 36, segunda a sexta das 8h00 às 18h00 (hora suíça), 0,08 CHF por minuto a partir de redes fixas suíças. Tem o número de seguimento pronto.
- **Formulário de contacto:** na [página de contacto da Planzer Paket](https://www.planzer-paket.ch/en/contact/) para extravio, nova entrega, nova data, alteração de morada ou queixas. Para danos ou perda, contacta primeiro o remetente.
- **Linhas na página inicial alemã da Quickpac:** telefones da Planzer Paket por idioma — 044 438 50 40 (alemão e inglês), 021 821 02 02 (francês) e 091 611 54 84 (italiano), sem horários indicados.
- **Linha gratuita Quickpac:** 0800 363 363, ainda presente nas suas páginas com horários divergentes.
- **E-mail:** info@quickpac.ch para reclamações de entrega.

Não é possível ligar diretamente aos motoristas, e nenhuma página indica se o 0848 funciona do estrangeiro.

## Seguir uma encomenda Quickpac com o Peek

O [Peek](/) reconhece de imediato um número de 18 dígitos iniciado por `44` como Quickpac e lê-o no portal da Planzer; ligações de quickpac.ch ou tracking.app.planzer.ch também funcionam. Mostra estado, histórico, entrega prevista, peso, dimensões, hora de entrega e país de destino, omitindo a morada do destinatário. `Shipped` aparece corretamente como entregue.

Enquanto mantiveres a encomenda aberta no Peek, ou no dia da entrega com as notificações ligadas, o Peek verifica até de 10 em 10 minutos — e a cada 2 minutos a partir das 6h00 (hora suíça) no dia em que sai para entrega, enquanto o site de seguimento da Planzer responder. Após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora, e a cada 6 horas após sete dias em «Etiqueta criada». Se uma encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito» passar quatro dias sem novos registos, o Peek mostra «Sem atualizações há quatro dias» (ou «Sem atualizações desde…» com a data), «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Quickpac.» e a ligação «Pede ajuda a Quickpac». O Peek pode avisar-te com «Todos os registos», «Passos importantes» ou «Só a entrega». Não pode contactar a Quickpac por ti.

## Perguntas sobre o seguimento Quickpac

### O ID de seguimento Quickpac é igual ao número da encomenda?

Sim. Segues uma encomenda Quickpac com o número de 18 dígitos, também chamado número de envio pela Quickpac e «Shipment Number» no portal da Planzer.

### A Quickpac tem seguimento em tempo real?

Por vezes. A caixa de seguimento da Quickpac pode mostrar «Follow the delivery of your consignment live», sem especificar que encomendas incluem esta opção.

### A Quickpac entrega no mesmo dia?

Apenas quando o remetente contrata SameDay ou Priority+.

### A Quickpac pertence à Planzer?

Sim. A Quickpac refere que faz parte da empresa familiar suíça Planzer Paket desde 2024.

### A Quickpac entrega fora da Suíça?

Não. As FAQ da Quickpac indicam que atualmente entrega apenas na Suíça.

:::sources
- [Quickpac: FAQ](https://quickpac.ch/en/faq) – número, entrega, pontos de levantamento, problemas, modalidades
- [Quickpac: Tracking](https://quickpac.ch/en/tracking) – etiquetas e opções da caixa de pesquisa
- [Quickpac: Kontakt Empfangen](https://quickpac.ch/de/empfangen/kontakt-empfangen) – encaminhamento para a Planzer Paket
- [Quickpac: Home](https://quickpac.ch/de) – integração na Planzer Paket desde 2024, linhas telefónicas
- [Quickpac: Postal Services Ordinance](https://quickpac.ch/en/postal-services-ordinance) – queixas, linha gratuita
- [Quickpac: AGB für Paketbeförderung](https://quickmail-ag.ch/assets/images/AGB-Quickpac-DE-ab-19.03.2024.pdf) – prazos indicativos, 500 CHF, 30 dias
- [Planzer Paket: Contact](https://www.planzer-paket.ch/en/contact/) – cartão, portal, problemas, telefone
- [Planzer Paket: Kontakt](https://www.planzer-paket.ch/de/kontakt/) – fotos, formulário, cacifos
- [Planzer Paket: Paket Shop](https://www.planzer-paket.ch/de/paket-shop/) – 7 dias, código, documento de identificação
- [Planzer: Tracking](https://tracking.app.planzer.ch/) – idiomas, números, etiquetas
- [Coop Pick-up: Home](https://www.pickup.ch/de.html) – avisos, 7 dias
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – dados lidos e mostrados pelo Peek
:::
