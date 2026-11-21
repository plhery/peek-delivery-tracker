---
title: Seguimento ECMS Express: códigos de 16 caracteres e entrega
description: Segue uma encomenda ECMS Express (易客满): como são os números de 16 caracteres, o que significa cada leitura, alfândega em Portugal e uso do Peek.
slug: seguimento-ecms-express
published: 2026-11-21
updated: 2026-11-21
---

A ECMS Express (`易客满`, também `ECMS Global` ou `ECMSGlobal`, denominação social na China `天津易客满国际物流有限公司`) é uma transportadora internacional de expresso e logística fundada em 2013 que envia encomendas de lojas online para mais de 200 países e regiões. Se uma loja expediu a tua compra pela ECMS, o teu número tem 16 caracteres e começa por `EC`, `ES`, `AP` ou `AX`: cola-o na caixa acima. A ECMS assegura o transporte aéreo internacional e o desalfandegamento, entregando depois a última milha através de uma transportadora local. No catálogo do Peek também a encontras pesquisando `ECMS`, `ECMS Global`, `ECMSGlobal`, `ECMS International Logistics Co.`, `易客满`, `易客满国际物流有限公司` ou `易客满国际快递`.

## Número de seguimento da ECMS Express: formato e exemplos

Os códigos da ECMS têm sempre 16 caracteres: `EC`, `ES`, `AP` ou `AX`, seguidos de três a cinco letras e algarismos no restante. Com caracteres inventados:

| Número (inventado) | O que é |
| --- | --- |
| `ECLAX48291057364` | `EC`, três letras e onze algarismos: um número da ECMS Express. |
| `ECSZXA4829105736` | `EC`, quatro letras e dez algarismos: também da ECMS. |
| `ECSZXAB482910573` | `EC`, cinco letras e nove algarismos: também da ECMS. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | As mesmas estruturas de 16 caracteres iniciadas por `ES`, `AP` ou `AX`. |
| `ECLAX4829105736` | Apenas 15 caracteres: não tem o formato da ECMS e o Peek não reconhece nenhuma transportadora (se escolheres a ECMS Express manualmente, o Peek indica que «não emite números como este»). Confirma se falta um algarismo. |

Nenhuma outra transportadora no catálogo do Peek utiliza estes formatos. Espaços e minúsculas não contam quando colas o código no Peek.

**Onde encontrar o número.** A ECMS imprime o número da carta de porte (*waybill*) na etiqueta da encomenda, e a loja envia-o no e-mail de expedição ou mostra-o na página do pedido. Se não o encontrares, consulta [como encontrar o número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

**Uma ligação de seguimento.** Uma ligação para o localizador `ecmsglobal.com` terminada em `?number=` preenche esse código e abre a verificação por imagem (*Security verification*). Ligações antigas da ECMS trazem o número depois de `orderNumber=`, `trackingno=` (incluindo `consignee.ecmsglobal.com`) ou `order_number=`. Podes colar qualquer uma no Peek: ele extrai o número ECMS automaticamente.

## Estados de seguimento da ECMS Express explicados

Em `ecmsglobal.com`, o localizador pede primeiro para passar uma verificação visual (*Security verification*: *Enter the characters shown below to continue tracking.*), resume o envio numa de oito etiquetas (de *Shipment Information Received* a *Delivered* ou *Exception*) e lista as leituras da mais recente para a mais antiga com data, hora e local. O Peek mostra cada leitura com as palavras do registo da ECMS. Pela ordem em que surgem:

| Registo da ECMS | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | A loja enviou os dados eletrónicos à ECMS, que ainda não recebeu a encomenda fisicamente. | «Etiqueta criada» |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | A ECMS recebeu os documentos para a alfândega ou validou a identificação do destinatário; ainda não houve leitura física do pacote. | «Etiqueta criada» |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | Primeira leitura real no armazém de origem da ECMS. | «Aceite pela transportadora» |
| `Parcel has arrived at export distribution warehouse` | Entrada no armazém de exportação da ECMS na origem. | «Em trânsito» |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | A encomenda saiu do armazém de origem. | «Em trânsito» |
| `Flight departure`, `Flight arrival` | Descolagem e aterragem do voo de carga. | «Em trânsito» |
| `Arriving notification received from terminal` | O terminal de carga do aeroporto de chegada confirma a receção da carga do voo. | «Em trânsito» |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Armazém de trânsito da ECMS no aeroporto de chegada, antes e depois do desalfandegamento de importação. | «Em trânsito» |
| `Shipment started customs clearance process.` | A alfândega do país de destino está a verificar a remessa. | «Na alfândega» |
| `Shipment completed customs clearance process.` | O desalfandegamento de importação terminou e a encomenda segue caminho. | «Em trânsito» |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | Nas instalações no país de destino, onde é pesada e aguarda a transportadora local. | «Em trânsito» |
| `Shipment is tendering to third party carrier for final delivery.` | A ECMS está a entregar a encomenda à transportadora local responsável pela distribuição final; o número próprio dela pode surgir a partir daqui. | «Em trânsito» |
| `Return to origin` | Problema registado nas instalações de destino, que nem sempre representa uma devolução efetiva: num histórico presente no código do Peek, a transportadora local aceitou o pacote no dia seguinte e concluiu a entrega. | «Problema reportado» |
| `Local carrier has accepted package for final delivery` | A transportadora local recebeu a encomenda. | «Em trânsito» |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Circulação na rede da transportadora local retransmitida pela ECMS (a segunda frase pode repetir-se em dias seguintes). | «Em trânsito» |
| `Shipment is out for delivery.` | Saiu para distribuição. | «Em distribuição» |
| `Available for pickup` | A transportadora local guarda a encomenda num ponto para levantares. | «Pronta para levantamento» |
| `Shipment delivered to customer` | Entregue. | «Entregue» |

**Linhas em chinês.** Alguns registos trazem estas etapas em chinês; o Peek exibe a frase chinesa e atribui-lhe a mesma etapa da correspondente em inglês: `发货预报已收到，正在等待货物到达我们的始发站仓库` («Etiqueta criada»), `干线航班起飞` e `干线航班已到达` («Em trânsito»), `货物已进入清关流程` («Na alfândega»), `货物已清关放行`, `货物已到达目的站仓库` e `货物出库前往配送中心安排最终派送` («Em trânsito»).

**Horas.** A ECMS grava as suas próprias leituras com o relógio e a diferença UTC da estação onde foram feitas, e o Peek mantém esse fuso. As leituras retransmitidas da transportadora local trazem diferença zero: o Peek lê-as em UTC, exceto quando uma leitura dessa origem ficaria mais de dez minutos depois do momento em que a ECMS a registou — nesse caso, o Peek trata essas horas como hora local datada pelo lugar. Uma linha de voo (`Flight departure`, `Flight arrival`) marcada exatamente à meia-noite indica apenas o dia do voo e aparece no Peek sem hora. Vê também [seguir uma encomenda vinda da China](guide:tracking-from-china).

## Quem entrega as encomendas da ECMS Express em Portugal

Para o troço final (*Final Mile Delivery*), a ECMS afirma na página *Parcel Delivery Service* trabalhar com transportadoras locais de confiança (*trusted local carriers*) em mais de 200 países e regiões, sem publicar uma lista fixa de parceiros por país na Europa. No localizador de `ecmsglobal.com`, a caixa *Last Mile Delivery* pode indicar a empresa responsável sob `LM Provider`, o número dela sob `LM Tracking No.` e uma ligação `Track with …` (nos históricos dos EUA presentes no código do Peek surge, por exemplo, a SpeedX).

**Quanto tempo demora.** Segundo as FAQ da ECMS, o prazo depende da origem, do destino e do serviço escolhido. A partir dos Estados Unidos (*U.S. Origin* em *Parcel Delivery Service*), a ECMS aponta `4–8 business days` para a **União Europeia** (bem como `3–6 business days` para o Reino Unido, `4–8 business days` para a Suíça e `3–11 business days` para o Canadá). As tentativas de entrega e o levantamento seguem as regras da transportadora local: lê [entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).

**Alfândega em Portugal e na União Europeia.** As FAQ da ECMS referem que nas remessas `DDU/DAP` o destinatário pode ter de pagar direitos e impostos antes da entrega, ao passo que em `DDP` esses encargos costumam ser pagos antecipadamente pelo remetente (alguns países pedem também cópia do documento de identificação para desalfandegar). Na **União Europeia**, desde 1 de julho de 2026 os bens comprados online fora da UE em remessas com valor intrínseco até 150 € pagam um direito aduaneiro da UE de 3 € por artigo (*per item*), e desde 1 de novembro de 2026 soma-se uma taxa de manuseamento da União (*Union handling fee*) de 2 € por artigo seja qual for o valor (um artigo reúne mercadorias da mesma encomenda com igual classificação pautal, descrição e, quando exigida, origem; acima de 150 € aplicam-se os direitos normais), além do IVA de importação em **Portugal** (incluindo os **Açores** e a **Madeira**, que segundo o âmbito territorial publicado pela Comissão Europeia fazem parte tanto do território aduaneiro como da área de IVA da UE), salvo se o IVA já tiver sido cobrado na compra através do IOSS. Mais informações em [encomenda retida na alfândega](guide:customs).

## Encomenda ECMS Express parada, não encontrada ou devolvida

**«We could not find a shipment for this tracking number.»** O localizador da ECMS ainda não conhece o número (gralha ou envio ainda não comunicado pela loja): o Peek mostra «À espera de ECMS Express» e continua a verificar. Se ao fim de dois dias a ECMS continuar sem reconhecer o código e nenhum serviço de seguimento o encontrar, o Peek apresenta: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»

**Parada em `Virtual Manifest has been received`.** A ECMS tem os dados eletrónicos, mas ainda não recebeu a caixa. Pergunta à loja quando entregou o pacote.

**Sem novidades após `Shipment is tendering to third party carrier for final delivery.`** A transportadora local já tem a encomenda (ou está prestes a recebê-la) e o seguimento próprio dela pode estar mais avançado. Procura o nome em `LM Provider` e o código em `LM Tracking No.` na caixa *Last Mile Delivery* e, se o Peek ainda não acompanhar a remessa lá, adiciona esse número separadamente ao Peek. Ao fim de quatro dias sem novas leituras, o Peek assinala «Sem atualizações há quatro dias» e mostra «Pede ajuda a ECMS Express» (ou a ligação da transportadora local quando o Peek já a segue lá e conhece a página de apoio). Vê mais em [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Dada como entregue mas não recebida, perdida, danificada ou devolvida.** Pergunta primeiro à transportadora local onde deixou a encomenda e fala logo com o vendedor: as FAQ da ECMS encaminham o comprador para a loja para substituições ou reembolsos, pois apenas o remetente pode apresentar reclamação por escrito à ECMS no prazo de 30 dias a contar da aceitação do envio (uma única reclamação por remessa), e a ECMS não pode cancelar nem alterar encomendas. Entre as razões de devolução indicadas pela ECMS contam-se artigos proibidos ou restritos, dados de envio incompletos ou incorretos, problemas aduaneiros, direitos e impostos por pagar ou falta de conclusão dos trâmites aduaneiros pelo destinatário; ainda assim, uma linha `Return to origin` isolada não prova por si só uma devolução definitiva. Consulta [entregue mas não recebido](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da ECMS Express

Dados lidos a 21 de novembro de 2026 na página *Contact Us* de `www.ecmsglobal.com` (sem fuso horário indicado para os horários; Portugal não tem número telefónico próprio na lista):

- **Formulário online**: em `www.ecmsglobal.com/en-us/contact-us.html`, seleciona `Shipment Support` em *Inquiry Type* e indica o código em *Tracking Number (Optional)*.
- **Europa (Reino Unido)**: `(+44) 01753528888`, `cs.uk@ecmsglobal.com`, de segunda a sexta-feira das `08:30` às `17:00`.
- **China continental**: `(+86) 400 086 1756`, `service@ecmsglobal.com`, de segunda a sexta-feira das `08:30` às `17:30`.
- **Estados Unidos**: `(+1) 626 956 5388`, `customerserviceus@ecmsglobal.com`, de segunda a sexta-feira das `09:00` às `18:00`.

**Cuidado com SMS fraudulentos.** Os domínios oficiais da ECMS para seguimento são `www.ecmsglobal.com` (`ecmsglobal.com`) e o antigo portal `consignee.ecmsglobal.com`. O Peek descarta ligações de pagamento que venham no registo; se receberes uma mensagem a pedir taxas aduaneiras ou de entrega, confirma se corresponde à transportadora local indicada em `LM Provider` e consulta [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da ECMS Express com o Peek

O [Peek](/) identifica de imediato um número ECMS de 16 caracteres («Transportadora detetada») e consulta o localizador da ECMS sem conta, código postal nem telefone.

A verificação por imagem da ECMS é uma pequena conta de somar ou subtrair de um algarismo: o Peek lê-a automaticamente e, se falhar, tenta mais uma vez com uma nova imagem. Quando a ECMS continua sem responder, o Peek recorre a serviços de seguimento e mostra o histórico que estes guardam, nas suas próprias palavras.

O Peek apresenta o estado, cada leitura com cidade, região e país e o país de destino, da mais recente para a mais antiga, no fuso horário do teu dispositivo; em `peektracker.com` e nas aplicações iPhone e Android, o histórico termina com «Horas no teu fuso horário: …». Deixa de fora a referência da loja, a localização nas linhas de manifesto eletrónico e eventuais ligações de pagamento, e não apresenta peso nem data prevista de entrega. O botão «Abrir o site de ECMS Express» abre o teu código em `www.ecmsglobal.com`.

Quando o registo da ECMS identifica uma transportadora local conhecida pelo Peek, o cartão mostra «Entrega: …» (com o número dela quando as leituras retransmitidas trazem uma referência única), ou «Anunciada a …, ainda não recebida» enquanto essa transportadora listar a encomenda sem leituras próprias. Se o Peek ler diretamente o localizador dessa transportadora sem dados adicionais, consulta-a também e muda o seguimento para lá mal exista uma leitura pelo menos tão recente como a última da ECMS, mantendo o histórico da ECMS; caso contrário, adiciona o `LM Tracking No.` à parte: o Peek segue mais de 3 500 transportadoras.

Enquanto mantiveres a encomenda aberta ou, no dia da entrega, tiveres notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 locais no dia em que passa a «Em distribuição» enquanto o localizador da ECMS responder (após transição para a transportadora local, o Peek acompanha o ritmo desta; apenas por serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ativas) —; nos outros casos, e após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes ativar alertas para «Todas as leituras», «Passos importantes» (início da alfândega de importação, `Return to origin`, distribuição, `Available for pickup` e entrega, sem voos nem passagens de armazém) ou «Só a entrega». O Peek não pode contactar a ECMS nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento da ECMS Express

### O que é a ECMS Express (易客满)?

Uma empresa internacional de transporte expresso e logística transfronteiriça fundada em 2013 (`天津易客满国际物流有限公司`), com filiais próprias na China continental, Hong Kong, Singapura, Estados Unidos, Reino Unido, Europa, Coreia do Sul e Japão.

### Onde posso seguir uma encomenda da ECMS Express?

Na caixa no topo desta página (sem teres de resolver o código de imagem) ou na página *Track Your Shipment* em `www.ecmsglobal.com` depois de passares a verificação visual.

### Quem entrega as encomendas da ECMS Express em Portugal?

Uma transportadora parceira local com a qual a ECMS trabalha, cujo nome e código próprio podem aparecer sob `LM Provider` e `LM Tracking No.` na caixa *Last Mile Delivery* do localizador da ECMS. Se ainda não constarem, pergunta ao vendedor.

### O que significa «Shipment is tendering to third party carrier for final delivery»?

A ECMS está a passar a tua encomenda à transportadora local responsável pela entrega final, cujo número pode surgir em `LM Tracking No.`. O Peek mostra «Em trânsito».

### Quem devo contactar se faltar uma encomenda da ECMS Express?

Fala primeiro com o vendedor onde fizeste a compra: as FAQ da ECMS remetem o comprador para a loja para reembolsos ou substituições, e só o remetente pode reclamar por escrito junto da ECMS no prazo de 30 dias após a aceitação. Sobre a entrega final, pergunta à transportadora local; também podes contactar a ECMS através do formulário *Shipment Support* em `ecmsglobal.com`.

:::sources
- [ECMS Express: página inicial](https://www.ecmsglobal.com/en-us/home.html) – expresso internacional, carga e logística para comércio eletrónico
- [ECMS Express: About ECMS](https://www.ecmsglobal.com/en-us/about.html) – fundada em 2013
- [易客满: 关于我们 (sobre nós)](https://www.ecmsglobal.com/zh-cn/about.html) – fundação em 2013, filiais próprias e denominação social 天津易客满国际物流有限公司
- [ECMS Express: Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – parceiros locais na última milha, 200+ países e prazos a partir dos EUA para a UE, Suíça, Reino Unido e Canadá
- [ECMS Express: Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – verificação por imagem, oito etiquetas de resumo, caixa Last Mile Delivery (`LM Provider`, `LM Tracking No.`), mensagem de não encontrado e `?number=`
- [ECMS Express: FAQ](https://www.ecmsglobal.com/en-us/faq.html) – carta de porte na etiqueta, DDU/DAP e DDP, motivos de devolução, impossibilidade de cancelar e reclamações pelo remetente em 30 dias
- [ECMS Express: Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – formulário Shipment Support, telefones regionais, e-mails e horários
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Orientações sobre o direito de 3 € e a taxa da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobrança por artigo e isenção de IVA via IOSS
- [Comissão Europeia: Âmbito territorial do IVA e das alfândegas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira
- [Universal Parcel Scraper: ECMS Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecms-express) – nomes alternativos, formato de 16 caracteres, ligações, cálculo visual e nova tentativa, estados em inglês e chinês, fusos horários e campos descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – transição para a transportadora local e recurso a serviços de seguimento
:::
