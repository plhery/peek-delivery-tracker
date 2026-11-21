---
title: Seguimento SpeedX: números SPX, estados e contactos
description: Segue uma encomenda da SpeedX nos EUA: formato do código SPX, significado dos estados em inglês, fotografia com código ZIP, tentativas de entrega e apoio.
slug: seguimento-speedx
published: 2026-11-21
updated: 2026-11-21
---

A SpeedX (Speed Xpress Inc., sediada em New Hyde Park, no estado de Nova Iorque) é uma transportadora de última milha que entrega compras online ao domicílio nos **Estados Unidos**. Podes acompanhar uma encomenda em [tracking.speedx.io](https://tracking.speedx.io/) ou colar o código na caixa acima. O número vem da loja onde compraste: a SpeedX gera a etiqueta para o comerciante, pelo que este já dispõe do código antes de a SpeedX receber fisicamente o volume.

## Número de seguimento da SpeedX: formato e onde encontrá-lo

A SpeedX indica que todos os seus códigos começam por `SPX`. Na prática são compostos por `SPX`, três letras e 12 ou 18 algarismos. Formatos reconhecidos (com caracteres inventados):

| Formato inventado | O que identifica |
| --- | --- |
| `SPXLGA482910573649` | `SPX`, 3 letras e 12 algarismos (18 caracteres). |
| `SPXLGA482910573649182736` | `SPX`, 3 letras e 18 algarismos (24 caracteres). |
| `SPXPH482910573649` | `SPXPH` seguido de algarismos: não é a SpeedX nos Estados Unidos, mas sim a SPX Express nas Filipinas, outra empresa. |

O Peek ignora minúsculas e espaços.

**Onde está.** Procura-o no e-mail de confirmação de expedição da loja ou na página da encomenda. Se não aparecer, a SpeedX recomenda pedi-lo ao comerciante; [como encontrar o número de seguimento](guide:find-tracking-number) reúne outros sítios onde procurar. Nos Estados Unidos, a SpeedX disponibiliza também alertas por SMS quando a encomenda sai para distribuição, subscritos através do seu site.

**Códigos parecidos.** `SPX` seguido apenas de algarismos não corresponde a nenhum dos dois formatos da SpeedX. Se faltar ou sobrar um algarismo num código `SPX…`, compara-o com a mensagem do vendedor. Os códigos da SpeedX também não coincidem com os da UniUni, da [GOFO](carrier:gofo) ou da [USPS](carrier:usps) nos Estados Unidos. Se ainda assim não for encontrado, a SpeedX sugere confirmar com a loja, pois o envio pode seguir por outra empresa de última milha. Vê mais em [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de seguimento da SpeedX

A página de seguimento da SpeedX está apenas em inglês. Estas são as leituras habituais pela ordem em que surgem:

| Estado na SpeedX | O que significa para a encomenda | O Peek mostra |
| --- | --- | --- |
| `Shipping Label Created` | A loja criou a etiqueta; a SpeedX ainda não tem o volume. | «Etiqueta criada» |
| `Pickup Requested` | O comerciante pediu a recolha à SpeedX, que ainda não recebeu a encomenda. | «Etiqueta criada» |
| `Parcel Picked Up` | Um motorista da SpeedX recolheu o volume nas instalações do remetente. | «Aceite pela transportadora» |
| `Arrived at Origin Facility` / `Processing at Origin Facility` | Entrada e triagem no primeiro centro da SpeedX. | «Em trânsito» |
| `In transit to destination facility` | Em viagem entre instalações da SpeedX nos Estados Unidos. | «Em trânsito» |
| `Processing at Transit Facility` | Triada num centro intermédio do percurso. | «Em trânsito» |
| `Arrival at Destination Facility` | Chegou ao centro que serve a tua zona nos Estados Unidos. | «Em trânsito» |
| `Processing at Designated Facility` | Triada nesse centro para atribuição à volta de um estafeta. | «Em trânsito» |
| `Out for Delivery` | Segue com um motorista para entrega hoje. | «Em distribuição» |
| `Attempted Delivery: No access` | O estafeta não conseguiu chegar à porta: portão fechado, prédio sem acesso, obras ou mau tempo. | «Tentativa de entrega» |
| `Delivered` | Entregue. A página da SpeedX indica onde ficou (por exemplo, sala de correio) e junta uma fotografia. | «Entregue» |
| `Delivered to PUDO` / `Waiting for collection from Parcelshop` | Após a volta, a encomenda ficou depositada num ponto parceiro (*parcelshop*) a aguardar levantamento. | «Pronta para levantamento» |
| `Self picked up from Parcelshop` | O volume foi levantado no *parcelshop*. | «Entregue» |
| `Parcel Void` | A SpeedX assinalou um problema no envio. Fala com a loja. | «Problema reportado» |
| `Exception- Parcel not found at the sort facility` | Encomenda não localizada no centro de triagem. Contacta o vendedor. | «Problema reportado» |
| `Parcel Disposal` | A SpeedX registou uma ocorrência sem detalhar o motivo. Pergunta à loja os passos seguintes. | «Problema reportado» |

Enquanto apenas surgir `Shipping Label Created` (ou uma recolha sem sucesso no remetente como `Pickup Failure:Closed Business Hours`), a SpeedX ainda não recebeu a mercadoria: continua na loja ou no respetivo armazém.

Uma tentativa falhada pode indicar outras causas, como `Attempted Delivery:Building Closed/Weekend/Holiday` para um espaço comercial encerrado («Tentativa de entrega»). Por baixo da tentativa, a SpeedX acrescenta uma nota sobre o passo seguinte: `The package is undeliverable and schedule to redelivery` indica nova tentativa de entrega, enquanto `The package is undeliverable and return to facility` assinala o regresso ao armazém. O Peek mostra essa nota a seguir à descrição.

Quando o último evento é uma anomalia (`Parcel Void`, `Parcel Disposal` ou volume não encontrado), o cabeçalho na página da SpeedX muda para `Issue Reported` com uma categoria como `Last Mile Intercept` ou `Last Mile Undelivered`. A SpeedX não publica explicação para essas categorias: contacta o vendedor.

## Entrega da SpeedX nos Estados Unidos, tentativas e levantamento

Todas as regras seguintes dizem respeito à rede da SpeedX nos **Estados Unidos**:

**Encomendas enviadas do estrangeiro.** A SpeedX também entrega nos Estados Unidos compras expedidas de outros países: o seu serviço transfronteiriço recebe-as em grandes hubs aeroportuários norte-americanos e trata do desalfandegamento junto da alfândega dos EUA com despacho próprio antes de as encaminhar para a rede de distribuição. Sobre direitos aduaneiros consulta [encomendas retidas na alfândega](guide:customs).

**Fins de semana.** Nos Estados Unidos, a SpeedX entrega aos sábados das 8h00 às 20h00 (hora local) em todas as zonas servidas e aos domingos, no mesmo horário, em áreas selecionadas. Não disponibiliza janelas horárias marcadas nem avisos de proximidade do motorista.

**Assinatura e fotografia de entrega.** A maioria das encomendas da SpeedX nos Estados Unidos não exige assinatura; artigos com restrição de idade, como bebidas alcoólicas, podem obrigar à assinatura de um adulto com 21 ou mais anos. Se não estiver ninguém em casa, o motorista deixa o volume e tira uma fotografia: para a veres, entra no seguimento em `speedx.io` e introduz o código postal norte-americano (*ZIP code*) de entrega.

**Instruções e alteração de morada.** A SpeedX recebe instruções de entrega (códigos de portão, local seguro onde deixar o volume, horário comercial ou telefone) através do seu chatbot Miles mediante o número de seguimento; os dados chegam em tempo real ao motorista, com quem não é possível falar diretamente. Avisando a tempo, a SpeedX pode corrigir nos Estados Unidos a rua ou o número de apartamento; para mudar de cidade, estado ou código ZIP tens de contactar o comerciante.

**Tentativa de entrega falhada.** Se discordares de uma tentativa registada, confirma primeiro a morada no site da loja e envia depois a correção ou instruções de acesso pelo chat Miles: a SpeedX indica que a nova tentativa deverá ocorrer em 1 a 2 dias úteis. A empresa não divulga quantas tentativas faz no total nem durante quantos dias guarda o volume. Vê também [entrega não conseguida](guide:missed-delivery).

**Levantamento.** A SpeedX esclarece que nos Estados Unidos não permite levantar encomendas nos seus próprios armazéns: têm de ser entregues. Ainda assim, alguns envios seguem após a volta para um estabelecimento parceiro (*parcelshop*) com o estado `Waiting for collection from Parcelshop`, onde podes ir levantá-los.

## Encomenda SpeedX parada, dada como entregue ou extraviada

Nas remessas geridas pela SpeedX nos **Estados Unidos**:

**Sem movimento.** A SpeedX pede que contactes o vendedor se o estado não mudar durante três dias úteis. Mais detalhes em [seguimento que não atualiza](guide:tracking-not-updating).

**Marcada como entregue mas não aparece.** A própria página de apoio da SpeedX refere que por vezes um volume fica marcado como entregue pouco antes de chegar fisicamente, ou é deixado num canto menos visível da propriedade. Nos Estados Unidos, vê primeiro a fotografia de entrega e verifica alpendre frontal e traseiro, caixa ou sala de correio (*mailroom*), garagem, laterais da casa e locais abrigados, perguntando também a familiares ou vizinhos. Avisa depois a SpeedX pelo Miles e contacta o comerciante para os passos seguintes. Consulta [entregue mas não recebida](guide:delivered-not-received).

**Perda, danos ou morada errada.** A SpeedX não aceita reclamações apresentadas por destinatários: para pedir reclamação, reembolso ou substituição dirige-te ao comerciante com o número da encomenda, o código de seguimento e as mensagens trocadas (bem como fotografias em caso de dano). A SpeedX não publica um prazo limite. Vê [encomendas perdidas](guide:lost-parcel).

**Devoluções.** Depois de entregue, qualquer devolução é tratada com a loja onde compraste; a SpeedX não recolhe devoluções ao domicílio nos Estados Unidos. Se ainda não tiver sido entregue, podes pedir ao Miles para travar a entrega e devolver o volume ao comerciante. Vê [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente da SpeedX: chat, telefone e e-mail

Canais para destinatários nos **Estados Unidos** indicados na [página de suporte da SpeedX](https://speedx.io/customer-service-and-support/):

- **Chat:** Miles, o chatbot da SpeedX, todos os dias das 8h00 às 20h00 ET (hora da costa Leste dos EUA), acessível no portal de seguimento após introduzires o código.
- **Telefone nos EUA:** `1-949-667-6769`, de segunda a sexta-feira das 9h00 às 21h00 EST e aos fins de semana das 11h00 às 20h00 EST. Outra resposta na mesma página afirma contudo que a SpeedX não dispõe atualmente de telefone e remete para o Miles, pelo que é habitual seres encaminhado para o chat.
- **E-mail:** `speedxops@speedx.io`.

## Seguir encomendas da SpeedX com o Peek

O [Peek](/) identifica de imediato a SpeedX a partir de um código `SPX` de qualquer dos dois formatos (`SPX` + 3 letras + 12 ou 18 algarismos) ou de uma ligação de `tracking.speedx.io` com o número, apresentando «Transportadora detetada» com «Alterar».

O Peek lê diretamente a página de seguimento da SpeedX e apresenta o estado atual, cada leitura com a respetiva localidade no mapa na hora registada pela SpeedX (convertida para o fuso do teu dispositivo, com «Horas no teu fuso horário: …» no final do histórico em `peektracker.com` e nas aplicações para iPhone e Android), bem como o dia estimado de entrega publicado pela SpeedX e a hora exata da entrega. Numa ligação partilhada, a leitura de entrega mostra apenas `Delivered`. O Peek descarta a tua morada, a nota sobre onde o volume foi deixado e a fotografia de entrega: para veres a fotografia acede a `speedx.io` com o teu código ZIP norte-americano. Não há transição posterior para outra transportadora.

Enquanto a SpeedX ainda não reconhecer o código, o Peek mostra «À espera de SpeedX»; se ao fim de dois dias nem a SpeedX nem outra fonte responderem, apresenta «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» Se passarem quatro dias sem qualquer nova leitura enquanto a encomenda permanece em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek disponibiliza «Pede ajuda a SpeedX» com ligação para a página de apoio.

Enquanto mantiveres a encomenda aberta no ecrã ou, no dia da entrega, com as notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6h00 (hora local) no dia em que a SpeedX lê o volume em distribuição (`Out for Delivery`), enquanto o rastreador da SpeedX responder (através apenas de serviços de seguimento externos: de hora a hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega com a encomenda aberta ou alertas ativos) —; no resto do tempo, e após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), consulta de hora a hora. Podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». Quando o site da SpeedX não responde, outras fontes externas raramente têm dados da SpeedX, pelo que pode não haver novidades até voltar a responder.

## Perguntas frequentes sobre o seguimento SpeedX

### O seguimento da SpeedX é fidedigno?

Sim: a SpeedX é a marca comercial da Speed Xpress Inc., uma empresa norte-americana de distribuição de última milha fundada em 2022 em Nova Iorque que entrega encomendas de comércio eletrónico nos Estados Unidos.

### Posso levantar a minha encomenda SpeedX pessoalmente?

Não nos armazéns da SpeedX nos Estados Unidos: a empresa não permite levantamentos nas suas instalações e exige tentar a entrega. Porém, se o histórico mostrar `Waiting for collection from Parcelshop`, o envio ficou depositado após a volta num ponto parceiro (*parcelshop*) e podes levantá-lo aí.

### Quanto tempo demora a SpeedX a entregar?

A SpeedX não publica prazos garantidos para destinatários. Aos expedidores vende entregas nos Estados Unidos no dia seguinte (*next-day*) e em dois dias (*2nd-day*), mas qualquer data estimada provém da loja e a SpeedX avisa que pode não coincidir com a data real.

### Porque não funciona o meu número de seguimento da SpeedX?

Quando `tracking.speedx.io` ainda não devolve dados (`No tracking information for … is available at this time`), o remetente pode ainda não ter enviado os dados da encomenda à SpeedX ou o código está incorreto. A SpeedX aconselha confirmar o número com a loja, uma vez que o pedido pode seguir por outra transportadora.

:::sources
- [SpeedX: Customer Service and Support](https://speedx.io/customer-service-and-support/) – prefixo SPX, glossário, SMS, entregas ao sábado e domingo, assinatura aos 21 anos, fotografias com código ZIP, instruções e alterações de morada no Miles, sem levantamento em armazém, 3 dias úteis sem mudanças, reclamações junto do comerciante, devoluções, telefone, e-mail e horários
- [SpeedX: Tracking](https://tracking.speedx.io/) – portal de seguimento em inglês da SpeedX
- [SpeedX: Tech-Enabled Last Mile Delivery](https://speedx.io/) – distribuição de última milha nos EUA, etiquetas criadas pela SpeedX, serviços next-day e 2nd-day e fotografias de prova de entrega
- [SpeedX: Cross-Border Shipping](https://speedx.io/cross-border-shipping/) – receção em hubs aeroportuários dos EUA, despacho aduaneiro próprio e triagem para a rede SpeedX
- [SpeedX: About Us](https://speedx.io/about-us/) – fundação em 2022 em Nova Iorque e cobertura em cidades dos EUA
- [SpeedX: Privacy Policy](https://speedx.io/privacy-policy/) – denominação Speed Xpress Inc. e morada em New Hyde Park
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos SPX reconhecidos, leitura de estados, notas de tentativa, aviso de número sem dados e privacidade em ligações partilhadas
:::
