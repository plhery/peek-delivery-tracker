---
title: Seguimento Econt: número de envio, estados e balcões
description: Segue uma encomenda Econt na Bulgária: onde encontrar o número de 13 algarismos, estados em inglês e búlgaro, balcões, Econtomat, reclamações e contactos.
slug: seguimento-econt
published: 2026-11-21
updated: 2026-11-21
---

A Econt Express (*Econt Express AD*) é uma transportadora búlgara fundada em 1993 que entrega ao domicílio, nos seus balcões Econt e nos cacifos automáticos Econtomat. Podes acompanhar o envio em `Track a shipment` no site [econt.com](https://www.econt.com/en/services/track-shipment), pela ligação recebida por e-mail ou SMS da Econt, ou colando o número da remessa na caixa acima.

## Número de seguimento Econt: formato e onde o encontrar

A Econt chama-lhe número de envio (*shipment number*) ou número de carta de porte (*waybill number*). As notificações por e-mail (gratuitas) e por SMS (opção paga de `0,11 €`, IVA incluído, contratada pelo remetente, pelo que nem todas as encomendas a têm) trazem uma ligação direta para o seguimento, o local, o prazo e o valor a pagar quando aplicável. Sem mensagem da Econt, procura no e-mail de expedição da loja ou vê [onde mais encontrar o número de seguimento](guide:find-tracking-number).

A Econt não publica uma regra formal para o público, mas os números de carta de porte de exemplo no manual de integração XML têm **13 algarismos**, e o serviço de seguimento da Econt valida-os como inteiros positivos de 13 dígitos que não começam por `0`, como o número inventado `1048291057364`.

Treze algarismos também são usados por outras transportadoras — como DPD France, Ukrposhta na Ucrânia, FAN Courier na Roménia ou Delhivery na Índia —, por isso confirma qual a empresa indicada pela loja. Se escolheres manualmente a Econt para um código começado por `0` ou com outro comprimento, o Peek avisa que «não emite números como este».

## O que significam os estados de seguimento da Econt

O site `econt.com` abre em búlgaro (`Проследи пратка`) com opção para inglês (`Track a shipment`). Estas são as descrições em inglês que o Peek apresenta, pela ordem habitual do percurso:

| Estado da Econt | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Awaiting delivery to Econt` | O remetente [criou a etiqueta](guide:tracking-statuses), mas ainda não entregou o pacote à Econt. | «Etiqueta criada» |
| `Submitted to courier` | No início do histórico: um estafeta recolheu a encomenda no remetente («Aceite pela transportadora»). Após passar por balcão ou linha: saiu para entrega («Em distribuição»). | «Aceite pela transportadora» / «Em distribuição» |
| `Arrival at an office` | Chegada ao balcão Econt ou centro logístico indicado ao lado (incluindo o teu balcão de levantamento). | «Em trânsito» (ou «Pronta para levantamento») |
| `Received by line-haul` | Segue numa ligação interurbana entre centros; o texto indica o trajeto entre localidades. | «Em trânsito» |
| `Change delivery terms` | Alteração das instruções de entrega (por exemplo, balcão ou data); o assunto surge após `Change delivery terms:`, sem deslocação física. | *(nota sem etapa)* |
| `Shipment forwarding` | Reencaminhamento para outra localidade ou morada. | «Em trânsito» |
| `Failed delivery` | Entrega não conseguida (motivo entre parênteses) e regresso ao balcão indicado. | «Tentativa de entrega» |
| `Delivered` | Entregue ou levantada (seguida do nome de quem a recebeu quando a Econt o regista). | «Entregue» |
| `Delivery return` | A encomenda está a voltar para o remetente. | «Devolvida ao remetente» |
| `Shipment destruction` | Envio marcado para destruição; fala já com a loja ou com a Econt. | «Problema reportado» |
| `Information` | Nota informativa sem mudança de fase. | *(nota sem etapa)* |
| `Return receipt: Delivered` | Registo do aviso de receção assinado que regressa ao remetente; não altera o estado da tua encomenda. | *(recibo, sem etapa)* |

Repara em `Submitted to courier`: antes de qualquer balcão, linha interurbana, reencaminhamento ou tentativa de entrega significa recolha na origem; depois disso, distribuição final até ti.

O localizador da Econt devolve também um **estado resumido em búlgaro**, que o Peek usa quando define claramente a fase atual: `Очаква предаване към Еконт` e `Подготвена в eEcont` («Etiqueta criada»), `Приета в Еконт` e `Приета в офис` («Aceite pela transportadora»), `Пътува по линия` («Em trânsito»), `В офис` e `Пристигнала в офис` («Pronta para levantamento» assim que a Econt indica o balcão onde o pacote aguarda), `Доставена` («Entregue»), `Върната` e `Върната и доставена към подател` («Devolvida ao remetente»), bem como `Връща се към подател`, `Анулирана след изпращане` e `Анулирана преди изпращане` («Problema reportado»). Quando o resumo diz `В куриер` (com estafeta), decide a leitura mais recente.

## Entrega ao domicílio, balcões Econt e cacifos Econtomat

**Ao domicílio.** Nas localidades com balcão Econt, as encomendas standard (até `50 kg` e `90 x 90 x 90 cm`, documentos A4 até `0,5 kg`, remessas não paletizadas de `50` a `1 000 kg`) chegam no dia útil seguinte — até às `18:00` dentro da mesma cidade — e até **3 dias úteis** segundo o calendário de distribuição nas povoações sem balcão. O remetente pode escolher janelas horárias gratuitas (`Delivery Time Slots`), hora fixa paga (`1,88 €` a `6,50 €`) ou entrega ao sábado (`1,88 €`), com IVA incluído.

**Num balcão Econt.** Uma remessa enviada para o dia útil seguinte fica disponível logo de manhã, à abertura do balcão. Após o **7.º dia** de permanência, a Econt cobra armazenagem de **`3,78 €` por dia e por envio** (IVA incluído).

**Num cacifo Econtomat (24/7).** Os cacifos aceitam volumes até **`61 x 44 x 37 cm`** e **`30,5 kg`** (três alturas de compartimento: `8 cm`, `18 cm` e `37 cm`), com entrega em **1 a 2 dias úteis** consoante a hora de envio. Para levantar: paga previamente qualquer valor em falta na app Econt com cartão bancário ou carteira digital `ecoints`, toca em `Receive a shipment`, lê o código QR ou introduz `PIN 1` e `PIN 2` da app ou SMS, retira a encomenda e fecha a porta.

**Alterar a entrega ou verificar os artigos.** Podes mudar a qualquer momento o destinatário, o local (outra morada, outro balcão, deixar num amigo ou vizinho, ou reencaminhar para outra localidade) e o horário por telefone, pela ligação do e-mail ou no perfil `e-Econt` ([alterar a morada de entrega](guide:change-delivery-address)). O tarifário inclui ainda três opções gratuitas que o vendedor pode ativar: `Review` (ver), `Review and Test` (ver e testar) e `Review, Test and Select` (ver, testar e escolher).

## Encomenda Econt atrasada, danificada ou extraviada

Na Bulgária, a Econt aponta o dia útil seguinte onde há balcão Econt, até 3 dias úteis onde não há e 1 a 2 dias úteis a partir de um Econtomat. Se o rasto parar vários dias, consulta o guia sobre [seguimento sem atualizações](guide:tracking-not-updating) e contacta a loja ou a Econt.

Em caso de dano, falta ou extravio ([encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel)), apresenta reclamação em `I want to file a claim` no centro de apoio ao cliente da Econt e informa o vendedor.

## Apoio ao cliente e contactos da Econt

Na sua [página do centro de apoio ao cliente](https://www.econt.com/en/client-center), a Econt disponibiliza:

- **A partir da Bulgária e da Grécia:** `0 7001 7300` (ao preço de uma chamada local ou conforme o teu tarifário).
- **A partir de outros países:** `+359 88 565 43 21` (conforme o tarifário do teu operador).
- **Online:** assistente virtual `Ask Ena`, pesquisa de balcões e horários (`I want to find offices and working time`) e formulário de reclamação (`I want to file a claim`).

Se receberes um SMS suspeito a pedir pagamentos fora da app oficial ou de `econt.com`, confirma-o no guia sobre [SMS fraudulentos de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda Econt com o Peek

Como 13 algarismos pertencem a várias transportadoras, o [Peek](/) só consulta automaticamente a Econt pelo número isolado quando o adicionas no site a partir da Bulgária ou na app num telemóvel com região Bulgária. Fora da Bulgária, escolhe a Econt em «Alterar» — ou cola uma ligação `econt.com` com `/services/track-shipment/`, `/en/services/track-shipment/`, `/tracking/` ou `/tracking/?num=`, que seleciona logo a Econt (enquanto a Econt ainda não tiver registo ou se o histórico antigo já tiver sido limpo, o Peek mostra «À espera de Econt» e, ao fim de dois dias, pede-te que verifiques o número ou escolhas outra transportadora).

O Peek consulta o serviço da Econt sem conta nem código postal, apresenta o estado atual, posiciona os balcões no mapa, mostra em que balcão a encomenda aguarda quando está «Pronta para levantamento» e guarda até às 100 leituras mais recentes. Para quem adicionou a encomenda, mantém o nome do destinatário e de quem assinou após `Delivered:` (ocultos numa ligação partilhada), deixando de fora número de volumes, nome do estafeta, remetente e referências de instrução; a Econt não indica data prevista, peso nem foto de entrega.

As horas são lidas no fuso de Sófia (`Europe/Sofia`, `UTC+2` no inverno e `UTC+3` no verão) e convertidas para o fuso do teu dispositivo («Horas no teu fuso horário: …»). Com a encomenda aberta ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos desde as `06:00` de Sófia no dia em que a Econt regista «Em distribuição» enquanto o localizador da Econt responder (por serviços de seguimento: de hora a hora, a cada 3 h após um dia calmo e até a cada 15 minutos no dia da entrega com a encomenda aberta ou alertas ativos); nos restantes casos, e após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem leituras, de hora a hora («Todos os registos», «Passos importantes» ou «Só a entrega»). O Peek não altera instruções na Econt nem submete reclamações por ti.

## Perguntas frequentes sobre o seguimento da Econt

### Posso ver o seguimento da Econt sem saber búlgaro?

Sim: o Peek apresenta as leituras da Econt com os textos em inglês da tabela acima e traduz o estado geral para português. O site `econt.com` também tem versão inglesa em `Track a shipment`.

### Quanto tempo demora uma entrega da Econt na Bulgária?

No dia útil seguinte nas localidades com balcão Econt (até às `18:00` ao domicílio urbano, logo à abertura no balcão), até 3 dias úteis nas localidades sem balcão conforme o calendário e 1 a 2 dias úteis a partir de um Econtomat.

### Como seguir uma encomenda Econt fora da Bulgária pelo número de 13 algarismos?

Cola os 13 algarismos na caixa acima e escolhe a Econt em «Alterar» (ou cola a ligação completa `econt.com/en/services/track-shipment/…`), porque um número de 13 algarismos isolado só é testado automaticamente na Econt a partir da Bulgária.

:::sources
- [Econt: Track a shipment](https://www.econt.com/en/services/track-shipment) – localizador em inglês em econt.com
- [Econt: Проследяване на пратка](https://www.econt.com/services/track-shipment) – página de seguimento em búlgaro
- [Econt: About Econt Express](https://www.econt.com/en/econt-express) – Econt Express AD, fundada em 1993 na Bulgária
- [Econt: Customer service center](https://www.econt.com/en/client-center) – 0 7001 7300 (Bulgária e Grécia), +359 88 565 43 21, Ask Ena, balcões e reclamações
- [Econt: Courier services](https://www.econt.com/en/services/courier-services) – volumes até 50 kg e 90 x 90 x 90 cm, prazos, janelas horárias, sábado, armazenagem a 3,78 €/dia após o 7.º dia, SMS a 0,11 € e opções gratuitas Review / Test / Select
- [Econt: Econtomat](https://www.econt.com/en/services/econtomat) – cacifos 24/7 até 61 x 44 x 37 cm e 30,5 kg, pré-pagamento, código QR e PIN 1 / PIN 2
- [Econt: Имейл или SMS известяване](https://www.econt.com/developers/29-sms-izvestyavane.html) – notificações por e-mail (gratuitas) ou SMS com ligação de seguimento
- [Econt: Instructions form](https://instruction.econt.com/5cZyLrYsBZH) – formulário para entrega a vizinho, escolha de balcão, reencaminhamento e horário
- [Econt: Integration of e-shops with the information system of Econt Express](https://www.econt.com/e-econt/doc_templates/XML_manual_engl.pdf) – números de carta de porte de exemplo com 13 algarismos
- [Universal Parcel Scraper: Econt](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/econt) – validação inteira de 13 dígitos, deteção na Bulgária, estados em inglês e resumos búlgaros, fuso Europe/Sofia, assinatura e campos omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras transportadoras de 13 algarismos e serviços de seguimento de reserva
:::
