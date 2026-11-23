---
title: Seguimento Matkahuolto: estados, pontos e contacto
description: Segue uma encomenda Matkahuolto na Finlândia: código de envio, estados em inglês, códigos de levantamento, guarda, cacifos e contacto.
slug: seguimento-matkahuolto
published: 2026-11-22
updated: 2026-11-22
---

A Matkahuolto é uma transportadora finlandesa com pontos de levantamento e cacifos por toda a Finlândia, incluindo alguns K-Market e quiosques R-kioski. Segue uma encomenda em matkahuolto.fi/tracking (em inglês) ou na app Paketit usando o identificador de envio do e-mail da loja ou do SMS da Matkahuolto, ou cola-o na caixa acima.

## Número de seguimento Matkahuolto e onde o encontrar

A Matkahuolto chama-lhe identificador de envio (*lähetystunnus* em finlandês, *shipment ID* ou *consignment ID* em inglês). Surge no e-mail de envio da loja e em todos os SMS ou e-mails da Matkahuolto. O código de levantamento no aviso de chegada é outra coisa: serve para abrir o cacifo ou mostrar na caixa do ponto de levantamento.

A Matkahuolto não publica formatos de número. Eis as formas que o catálogo do Peek conhece (dígitos inventados):

| Número | O que é |
| --- | --- |
| `MH482910372FI` | `MH`, 9 dígitos e `FI`. |
| `MA7394028156342019482` | `MA` seguido de 19 dígitos. |
| `00373482910375618296` | Um código de barras de 20 dígitos (SSCC). O seguimento encontra-o também em 18 dígitos, sem o `00`. |

Os códigos começados por `JJFI` e os [números postais](guide:tracking-number-formats) terminados em `FI` com letras diferentes de `MH` (`RR482910372FI`) pertencem à [Posti](carrier:posti), o correio finlandês. Se a Matkahuolto não encontrar o número, confirma que transportadora aparece no e-mail da loja.

Mudar uma encomenda para outro ponto cria um novo ID de envio. Uma encomenda GLS entregue à Matkahuolto na Finlândia mostra o código Matkahuolto no seguimento da GLS como «Finland parcel number», começado por `00`.

Sem código? Se estiveres registado na Matkahuolto ou usares a app Paketit, as encomendas enviadas para o teu telefone ou e-mail aparecem lá; caso contrário o remetente pode acrescentar os teus dados. Vê [como encontrar um número de seguimento](guide:find-tracking-number).

## O que significam os estados da Matkahuolto

O seguimento da Matkahuolto funciona em finlandês (por defeito), sueco e inglês, cada registo com data, hora e localidade. Eis os textos em inglês, por ordem:

| Estado da Matkahuolto | O que significa para ti |
| --- | --- |
| `Matkahuolto is waiting for the consignment from the sender` | O remetente [criou o envio](guide:tracking-statuses); a Matkahuolto ainda não tem a encomenda. |
| `Consignment is being processed` | Registada num terminal. |
| `The consignment is on its way` | Em trânsito entre terminais. |
| `Your parcel has been sorted and will be taken next to the pickup point.` | Triada no último terminal; segue agora para o ponto de levantamento. |
| `We will deliver the consignment to the pickup point during the day` | A caminho do ponto de levantamento hoje. |
| `Your pick up point has changed` | Vai para outro ponto, indicado nessa linha. |
| `The shipment is available for pickup with the pickup code at the pick up point` | Pronta para levantamento, com o nome do ponto e último dia para levantar. |
| `We have sent the recipient a message about the arrival of the consignment (sms).` | O aviso de chegada foi enviado. Não indica deslocação. |
| `The consignment has been delivered. Thank you for choosing Matkahuolto!` | Levantada no ponto ou cacifo. |

**Durante o dia.** A Matkahuolto refere que a primeira paragem da rota pode não ser a tua, mas a encomenda deve ficar disponível no próprio dia; recebes aviso assim que estiver pronta.

## Pontos de levantamento, cacifos e entrega ao domicílio

**Levantamento.** Só precisas do código de levantamento do aviso. Não é pedido documento de identificação salvo se o aviso indicar entrega em mão (geralmente álcool ou documentos). Qualquer pessoa com o código pode levantar, exceto em cacifos exteriores operados por app, que só abrem com o telemóvel do destinatário na app Paketit. Se o aviso não mencionar cacifo, pergunta na caixa do ponto de levantamento.

**Aviso de chegada.** Primeiro notificação na app Paketit, depois SMS ou WhatsApp, e e-mail se o remetente o forneceu.

**Prazo de guarda.** 5 dias e depois regressa ao remetente; 14 dias quando o aviso chega em papel por falta de telefone ou e-mail. Podes comprar 1 a 16 dias extra na app ou, estando registado, no site, salvo bloqueio do remetente. Como a página GLS Finlândia dá 10 dias às suas encomendas, segue sempre o último dia do teu aviso.

**Mudar o ponto.** Uma vez grátis na app: antes de a Matkahuolto receber a encomenda segundo as FAQ em inglês, ou no primeiro aviso segundo a página em sueco; não durante o trânsito. Após o aviso de chegada podes comprar uma transferência.

**Entrega ao domicílio.** As *Home Parcels* chegam em dias úteis das 8:00 às 18:00 (hora finlandesa); as *Business Parcels* e encomendas [FedEx](carrier:fedex) em dias úteis das 8:00 às 16:00 (hora finlandesa), sem aviso prévio. O aviso indica o dia previsto, que podes adiar para um dos cinco dias úteis seguintes no próprio dia em que o recebes. A encomenda fica num local seguro e abrigado junto à porta; se não for possível e não te conseguirem contactar, segue para um ponto próximo no dia útil seguinte.

**Åland.** Desde 1 de outubro de 2026, a Åland Post entrega as encomendas Matkahuolto em Åland, integrando estações, agentes postais, cacifos e entregas ao domicílio na rede.

**Devoluções.** Obtém um código de ativação com o botão `Palauta` na app ou no site, escreve-o na encomenda com o teu nome e telefone e entrega-a num cacifo ou ponto de levantamento.

## Encomenda Matkahuolto atrasada, parada ou danificada

A maioria das encomendas chega no dia seguinte à expedição, diz a Matkahuolto, e todas em até três dias após receção (três dias úteis nas FAQ finlandesas). Encomendas do estrangeiro podem passar antes por outras transportadoras e levar pouco mais de uma semana até chegarem à Matkahuolto.

**Sem atualizações.** O seguimento pode pausar enquanto aguarda saída ou verificação da embalagem. Se continuar parado, a Matkahuolto pede que contactes o remetente. Vê [seguimento sem atualizações](guide:tracking-not-updating).

**Sem aviso de chegada.** Normalmente uma gralha nos teus contactos: verifica o spam e pede ao remetente que os corrija.

**Danificada ou perdida.** Avisa sem demora a loja ou o remetente com fotografias. A reclamação à Matkahuolto cabe à empresa remetente, ou ao remetente entre particulares. Regista danos visíveis ao levantar; caso contrário comunica por escrito em 7 dias, ou 30 dias como consumidor. A responsabilidade da Matkahuolto é de 20 € por kg, ou o valor dos bens se for inferior.

## Apoio ao cliente e telefone Matkahuolto

Contactos publicados nas páginas da Matkahuolto:

- **Serviços de encomendas:** 0600 11300, de segunda a sexta-feira das 8:00 às 18:00 (hora finlandesa), 2,49 €/min mais tarifa local.
- **Apoio de encomendas:** +358 20 33 6161, de segunda a sexta-feira das 8:00 às 10:00 e das 14:00 às 16:00 (hora finlandesa), tarifa local, para cacifos, cacifos por app e entregas ao domicílio.
- **Chat:** com o chatbot Mauno na [página de apoio a encomendas](https://www.matkahuolto.fi/customer-service/parcels) ou na app Paketit, onde um assistente pode intervir.
- **Formulário de contacto:** em finlandês em matkahuolto.fi/palautelomake. Não é publicado e-mail para destinatários.

> A Matkahuolto alerta para mensagens falsas, remetentes falsificados e sites imitadores como matkahuoltoo.fi. Afirma nunca encaminhar pagamentos nem atuar como gateway de pagamento; a única ligação de pagamento que envia destina-se a remetentes por custos de devolução. Confirma qualquer mensagem introduzindo o ID de envio no seguimento ou na app Matkahuolto; se pagaste, liga ao teu banco.

## Seguir uma encomenda Matkahuolto com o Peek

Para um código `MH…FI`, o [Peek](/) pergunta à Matkahuolto, à [Posti](carrier:posti) e à [Chronopost](carrier:chronopost), mostrando «Matkahuolto tem esta encomenda.» quando só a Matkahuolto a conhece; se a Posti também a conhecer, escolhes tu. Um número `MA` vai apenas à Matkahuolto. Para um código de barras de 18 ou 20 dígitos com `64` (Finlândia) ou `73` (Suécia) após o primeiro dígito (ou após `00` e um dígito), o Peek consulta também a Matkahuolto a par das outras transportadoras compatíveis e escolhe Matkahuolto se só ela a conhecer. Se a [Posti](carrier:posti) conhecer também um `64` ou a Bring um `73`, o Peek seleciona essa transportadora: escolhe Matkahuolto em «Alterar». Se outra transportadora como a PostNord também a conhecer, escolhes tu. Outros códigos não são propostos à Matkahuolto, pelo que deves escolhê-la em «Alterar» (ou «Alterar transportadora»). Uma ligação de seguimento `matkahuolto.fi` (`/seuranta`, `/tracking` ou `/uppfoljning`) com `parcelNumber=` regista qualquer código na Matkahuolto, mas a pesquisa só aceita `MH…FI`, `MA` e números postais ou SSCC/GS1 com dígito de controlo válido.

Os registos da Matkahuolto não têm fuso horário: o Peek lê-os na hora da localidade do registo (hora finlandesa na Finlândia; uma linha sem local na de uma linha vizinha, ou então na hora finlandesa) e mostra-os no teu fuso horário («Horas no teu fuso horário:»). Os estados mantêm o texto inglês da Matkahuolto. O Peek mostra o ponto de levantamento ou cacifo com localidade e rua, mas não recebe data prevista nem peso, omite serviços adicionais e códigos postais e não separa o último dia de levantamento (consta do texto do estado e do aviso). Para um código `MH…FI` seguido a partir de um país que não a Finlândia, o Peek pergunta também aos correios desse país quando os conhece (não para os Países Baixos nem Åland) e passa para eles assim que mostrem um registo datado não mais antigo do que o último da Matkahuolto; daí em diante segue o ritmo deles e mantém o histórico da Matkahuolto. Com os números `MA` e os códigos de barras, o Peek não segue a encomenda noutra transportadora.

O Peek verifica até de 10 em 10 minutos, e de 2 em 2 minutos a partir das 6:00 no relógio do último registo (hora finlandesa) no dia em que entra «Em distribuição», enquanto a encomenda estiver aberta no Peek ou no dia da entrega com as notificações ligadas, quando o seguimento Matkahuolto responde. Após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora, e após sete dias sem novidades em «Etiqueta criada», de 6 em 6 horas. Nas notificações podes escolher «Todos os registos», «Passos importantes» ou «Só a entrega». Ao fim de quatro dias sem registo numa encomenda em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek mostra «Sem atualizações desde …», a nota «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Matkahuolto.» e «Pede ajuda a Matkahuolto». O Peek segue mais de 4500 transportadoras, mas não pode contactar a Matkahuolto nem o remetente por ti.

## Perguntas sobre o seguimento Matkahuolto

### A Matkahuolto é fiável?

Sim, a Matkahuolto é uma transportadora finlandesa legítima. Trata qualquer mensagem a pedir pagamento em nome dela como burla: a Matkahuolto indica que nunca encaminha pagamentos.

### Existe seguimento Matkahuolto em inglês?

Sim. Está disponível em matkahuolto.fi/tracking, e o idioma da app Paketit muda-se nas definições.

### Posso seguir uma encomenda Matkahuolto sem o código?

Sim, se estiveres registado ou usares a app Paketit e o remetente tiver dado o teu telefone ou e-mail: a encomenda aparece lá. Caso contrário pede o ID de envio à loja.

### Quanto tempo demora uma encomenda Matkahuolto?

Regra geral 1 a 2 dias úteis até cacifos e pontos de levantamento, e no máximo três dias após a Matkahuolto a receber (três dias úteis segundo as FAQ finlandesas). Encomendas do estrangeiro podem levar pouco mais de uma semana até lá chegar.

:::sources
- [Matkahuolto: How to receive a parcel?](https://www.matkahuolto.fi/customer-service/parcels/how-to-receive-a-parcel) – código de levantamento, identificação, avisos, troca de ponto, domicílio, danos, telefones
- [Matkahuolto: Frequently asked questions](https://www.matkahuolto.fi/frequently-asked-questions) – domicílio, tentativas falhadas, telefones
- [Matkahuolto: Track your parcel](https://www.matkahuolto.fi/customer-service/parcels/track-your-parcel) – vistas de seguimento, prazos, pausas, contactar o remetente
- [Matkahuolto: Receive your parcel easily](https://www.matkahuolto.fi/packages/receive-a-parcel) – 1 a 2 dias úteis, avisos, 1 a 16 dias extra de guarda
- [Matkahuolto: Pakettien säilytysaika muuttuu](https://www.matkahuolto.fi/news/pakettien-sailytysaika-muuttuu-tavoitteena-entistakin-sujuvampi) – 5 e 14 dias, prolongamentos
- [Matkahuolto: Domestic parcels](https://www.matkahuolto.fi/packages/domestic-parcels) – 5 dias de guarda, devolução ao remetente
- [Matkahuolto: Parcel lockers and parcel points in Finland](https://www.matkahuolto.fi/packages/service-point-network) – rede, cacifos por app
- [Matkahuolto: Transport damage guide for consumers](https://www.matkahuolto.fi/packages/damage-in-transit) – quem reclama, prazos, fotos
- [Matkahuolto: Kuljetusvahinko-ohje kuluttajille](https://www.matkahuolto.fi/paketit/kuljetusvahingot) – prazos, 20 € por kg
- [Matkahuolto: About scam messages](https://www.matkahuolto.fi/packages/about-scam-messages) – mensagens falsas, remetente legítimo, ID de envio
- [Matkahuolto: Return the arrived parcel easily](https://www.matkahuolto.fi/packages/return-parcel) – devoluções com código de ativação
- [Matkahuolto: Paketit app](https://www.matkahuolto.fi/packages/paketit-app) – idioma da app, chat
- [Matkahuolto: Customer service for parcel services](https://www.matkahuolto.fi/customer-service/parcels) – chatbot Mauno
- [Matkahuolto: Usein kysyttyä](https://www.matkahuolto.fi/usein-kysyttya) – preço da linha, três dias úteis, Business Parcels e FedEx 8:00–16:00, formulário
- [Matkahuolto: Seuraa pakettisi matkaa](https://www.matkahuolto.fi/asiakaspalvelu/paketit/seuraa-paketin-matkaa) – pouco mais de uma semana do estrangeiro, ligação de pagamento de devolução para remetentes
- [Matkahuolto: Track the parcel](https://www.matkahuolto.fi/tracking) – seguimento em inglês, finlandês e sueco, textos de estado
- [Matkahuolto: Ta emot paket](https://www.matkahuolto.fi/paket/ta-emot-paket) – em sueco: mudança gratuita de ponto no primeiro aviso
- [Matkahuolto: Spåra ditt paket](https://www.matkahuolto.fi/kundservice/paket/spara-ditt-paket) – em sueco: todas as encomendas em três dias
- [Matkahuolto: Åland Post blir Matkahuoltos nya transportpartner på Åland](https://www.matkahuolto.fi/news/%C3%85land%20Post%20blir%20Matkahuoltos%20nya%20transportpartner%20p%C3%A5%20%C3%85land) – em sueco: Åland Post em Åland desde 1 de outubro de 2026
- [GLS Finlândia: Lähetysseuranta](https://gls-group.com/FI/fi/laehetysseuranta.html) – pontos Matkahuolto, Finland parcel number, 10 dias
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que lê o Peek
:::
