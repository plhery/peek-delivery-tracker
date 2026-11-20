---
title: Seguimento Evri: número, estados, alfândega e entrega
description: Seguir uma encomenda Evri em Portugal: código de 16 caracteres, estados GlobalEco e Reino Unido, alfândega, pontos de recolha e contactos oficiais.
slug: seguimento-evri
published: 2026-11-17
updated: 2026-11-20
---

A Evri é a transportadora britânica de encomendas conhecida como Hermes UK até março de 2022 (distinta da Hermes na Alemanha). Se encomendares numa loja do Reino Unido — como ASOS, Boohoo, Gymshark, Lookfantastic, eBay UK ou Vinted — é frequente a Evri recolher o envio na origem e entregá-lo em Portugal através de uma transportadora parceira. Os envios internacionais seguem-se no portal [GlobalEco da Evri International](https://globaleco.app/track/), ao passo que as encomendas domésticas no Reino Unido se consultam em [evri.com](https://www.evri.com/track-a-parcel) com o código de 16 caracteres ou os 8 dígitos do aviso de passagem (*calling card*). Também podes colar o código de 16 caracteres ou a ligação de `evri.com` diretamente na caixa acima.

## Número de seguimento da Evri: formatos e onde encontrá-lo

A Evri chama-lhe ora código de 16 caracteres ora número de 16 dígitos, embora os seus próprios exemplos incluam letras. Encontras o código no e-mail de expedição da loja ou nos SMS (remetente «Evri») e e-mails enviados pela Evri.

| Formato (exemplo inventado) | O que identifica |
| --- | --- |
| `H00AAC0000000008` | Código de barras Evri de 16 caracteres (`H` ou por vezes `T`, quatro letras ou algarismos, `C` na 6.ª posição e 10 dígitos com algarismo de controlo), habitual na Evri International (como `H04G0C…` nos envios via Post Office ou `H0595C…` reservados em evri.com). |
| `H00AAA0000000006` | Código de barras Evri de 16 caracteres com `A`, `D` ou outro carácter na 6.ª posição (por ex., `H00AAB0000000007`), usado na Evri UK e partilhado entre os dois serviços. |
| `C00HHA0000000000` | `C00HHA` seguido de 10 algarismos: código da Evri UK presente em várias ligações de seguimento de `evri.com`. |
| `1000000000000007` | 16 algarismos com dígito de controlo GS1: usado pela Evri UK, mas também por [Correos Express](carrier:correos-express), DHL eCommerce, Canada Post e TNT. |
| `12340000` | Número britânico de aviso de passagem (*calling card*) com 8 dígitos; funciona apenas em evri.com. |

Um `H` seguido de 15 algarismos (`H000000000000008`) coincide igualmente com a numeração da Hermes na Alemanha: confirma sempre qual foi a transportadora indicada pelo vendedor. Em [globaleco.app/track/](https://globaleco.app/track/), depois de a encomenda passar para o parceiro responsável pela distribuição final, o campo `Carrier` indica essa rede e `Carrier Tracking` mostra o número local (por exemplo, quando a entrega cabe à [Paack](carrier:paack)).

**Compraste no eBay UK?** Quando o vendedor envia através do *Global Shipping Programme* (GSP) do eBay, a Evri faz apenas o trajeto interno no Reino Unido até ao centro de expedição britânico do eBay com um código da Evri UK; o transporte internacional fica a cargo do programa do eBay. Sem código? Vê [como encontrar o número de seguimento](guide:find-tracking-number).

## O que significam os estados de seguimento da Evri

Nas encomendas vindas do Reino Unido para Portugal, o portal GlobalEco da Evri International apresenta os estados em inglês:

| O GlobalEco mostra | O que significa para a tua encomenda |
| --- | --- |
| `Created` | Envio registado; a Evri ainda não recebeu a encomenda. |
| `Received at Evri`, `Accepted` | A Evri recebeu a encomenda na sua rede no Reino Unido. |
| `Consolidated`, `Cross Docked`, `Processed at Export Gateway` | Triagem e preparação para saída no centro de exportação britânico da Evri. |
| `Departed Origin Country`, `In transit` | Saiu do Reino Unido ou segue em trânsito para o país de destino. |
| `Arrived In Country` | Chegou ao país de destino. |
| `In customs`, `Customs cleared` | Em controlo aduaneiro e, depois, desalfandegada (consulta [encomendas retidas na alfândega](guide:customs)). |
| `Handed to Partner Carrier`, `Received by carrier`, `At the local depot` | Entregue à transportadora parceira em Portugal e triada no armazém local; consulta `Carrier` e `Carrier Tracking` no GlobalEco para acompanhar a entrega final. |
| `Scheduled For Delivery` | Surge em encomendas entregues pela Paack após a chegada ao país quando o dia ou horário de entrega é reagendado enquanto a encomenda aguarda no armazém. |
| `Out for delivery` | Em distribuição hoje com o estafeta local. |
| `Delivery Attempted` | Houve tentativa de entrega sem sucesso. |
| `Delivery awaiting collection` | Aguarda levantamento num ponto de recolha ou estação postal. |
| `Delivered` | Entregue ou levantada. |
| `Delayed`, `Delivery Exception`, `Carrier label error` | Atraso, incidente na entrega ou problema na etiqueta: verifica o seguimento local ou contacta a loja. |

Para envios internos no Reino Unido acompanhados em `evri.com`, a Evri utiliza estas mensagens:

| A Evri mostra (Reino Unido) | O que significa |
| --- | --- |
| `We're expecting it` | O remetente ainda não entregou a encomenda à Evri. |
| `on its way` (entrega em ParcelShop) | Segue pelos armazéns britânicos da Evri até ao ParcelShop escolhido; recebes SMS ou e-mail assim que estiver pronta a levantar. |
| `Evri gateway` | Encomenda internacional acabada de chegar ao Reino Unido, retida num centro da Evri durante o desalfandegamento britânico antes de seguir para um hub. |
| `We're sorry we haven't been able to deliver today. We'll update your tracking when it's on the way on the next working day` | Volta adiada sem passagem nesse dia; sai novamente no dia útil seguinte. |
| `On its way back to the retailer` | Último registo de uma devolução: a Evri já entregou a encomenda de volta ao comerciante e não envia mais atualizações. |

Se o seguimento em `evri.com` te pedir para contactares a Evri, faltam dados para concluir a entrega: inicia um chat em `evri.com`.

## Entrega em Portugal, alfândega a partir do Reino Unido e recolha no UK

**Prazos e distribuição para Portugal.** Embora a FAQ internacional geral da Evri refira 3 a 7 dias úteis a nível mundial, a página da Evri dedicada a Portugal indica um prazo estimado de 7 a 10 dias úteis no serviço Standard (a que podem acrescer feriados ou formalidades aduaneiras). Nos termos e condições da Evri, os serviços internacionais incluem pelo menos uma tentativa de entrega; o que acontece a seguir — nova passagem ou depósito num ponto de levantamento — segue as regras da transportadora parceira em Portugal.

**Alfândega, IVA e direitos aduaneiros vindos do Reino Unido.** Como o Reino Unido está fora da união aduaneira da UE, qualquer compra expedida de lá passa pela alfândega:

- A página da Evri para Portugal avisa que, no serviço internacional standard, o destinatário pode ter de pagar impostos e direitos antes de os artigos saírem do Reino Unido ou quando a encomenda chega ao destino; havendo encargos, a Evri ou as autoridades competentes enviam ao destinatário uma ligação de pagamento antes da entrega.
- Além disso, desde 1 de julho de 2026 terminou na UE a isenção de direitos aduaneiros para remessas até 150 €: aplica-se um direito aduaneiro de 3 € por tipo de produto nas remessas até 150 €, seja qual for o regime de IVA (com ou sem IOSS), e desde 1 de novembro de 2026 soma-se uma taxa de tratamento da UE de 2 € por tipo de produto, seja qual for o valor. A Evri conta ambos por grupo aduaneiro (os artigos que partilham os primeiros 6 dígitos do código SH contam como um só grupo — por exemplo, 2 t-shirts = 1 grupo, mas 1 livro + 1 t-shirt = 2 grupos; nunca por unidade individual). Consulta todos os pormenores no guia sobre [taxas e encomendas na alfândega](guide:customs).

**Entregas dentro do Reino Unido (Calling Cards, ParcelShops e Lockers).** Em moradas britânicas, o estafeta define a janela horária de manhã (1 hora segundo a página inicial da Evri; 2 horas segundo o centro de ajuda). Se não estiver ninguém, deixa a encomenda num local seguro (*safe place*) ou na caixa do correio quando cabe; caso contrário, deixa uma *calling card* de 8 dígitos e faz até três tentativas no total (após a última, o guia *safe place* indica regresso ao armazém, os termos e condições devolução ao remetente e `evri.com/send` envio para o ParcelShop mais próximo). Com o código postal britânico em `evri.com`, podes desviar a entrega para um vizinho, local seguro, ParcelShop ou cacifo Locker se o remetente o permitir e não for exigida assinatura; não é possível mudar o dia, só o remetente pode alterar a morada e não se pode levantar em mão uma encomenda que já esteja com o estafeta. No Reino Unido, os ParcelShops (lojas locais e balcões Post Office) guardam a encomenda durante 10 dias mediante documento de identificação ou PIN enviado pelo remetente, e os cacifos Lockers abertos 24/7 dão 3 dias para levantar com o código ou código de barras recebido por e-mail (botão «help» no ecrã em caso de avaria).

## Encomenda Evri parada, atrasada, perdida ou danificada

- **Seguimento sem alterações:** se continuar em `We're expecting it` ou `Created`, a loja ainda não entregou a encomenda à Evri; fala com o vendedor. Se outro estado no Reino Unido não mudar durante 24 horas (ou 5 dias em envio Standard do lado do remetente), a Evri pede para iniciar um chat. Vê [porque é que o seguimento não atualiza](guide:tracking-not-updating).
- **Mais de 7 dias à espera:** avisa o vendedor, que detém o contrato de transporte com a Evri. Nas compras na UE, o comerciante dispõe de 30 dias para entregar a encomenda (salvo acordo em contrário) e é responsável por qualquer dano sofrido pelos bens desde a expedição até à receção.
- **Dada como entregue mas não aparece:** pergunta em casa e aos vizinhos diretos. No Reino Unido, um chat aciona a averiguação do armazém local; em Portugal, verifica também o seguimento da transportadora parceira e contacta a loja. Mais passos em [encomenda dada como entregue mas não recebida](guide:delivered-not-received).
- **Perdida ou danificada:** a Evri não reembolsa diretamente o destinatário; [apenas o remetente pode apresentar reclamação](guide:damaged-parcel), o que a Evri recomenda fazer no prazo de 28 dias após a encomenda para serviços no Reino Unido e 45 dias para serviços internacionais.

## Contactos da Evri e como identificar SMS fraudulentos

A Evri apenas disponibiliza canais britânicos em inglês (verificados a 17 de novembro de 2026), sem linha telefónica portuguesa:

- **Chat 24/7** em [evri.com/contact-us](https://www.evri.com/contact-us) através do assistente virtual Ezra, com resposta até 24 horas. Tens de indicar o número de seguimento, o talão ParcelShop (`P` mais 7 algarismos) ou os 8 dígitos da *calling card*. Para destinos internacionais sem código postal, a Evri instrui a introduzir `0` quando o Ezra pedir o código postal.
- **Telefone no Reino Unido:** `0330 808 5456`, atendimento automático 24/7 e operadores de segunda a sexta-feira das 8h00 às 16h00 e ao sábado das 8h00 às 14h00 (hora do Reino Unido). Pede para marcar no teclado os últimos 8 algarismos do código de seguimento, os 7 algarismos após o `P` do recibo ParcelShop ou os 8 dígitos da *calling card*. Dentro do Reino Unido os números `03` têm o custo de uma chamada geográfica `01`/`02`; a partir de Portugal aplica-se a tarifa internacional do teu operador para o Reino Unido.
- **Tell us straight:** se o apoio ao cliente já tentou ajudar sem resolver a questão, o botão «Email us» na página de contacto abre um e-mail para `noreply.priority@evri.com` dirigido à equipa sénior (confirmação de receção numa hora dentro do horário de expediente britânico e ponto de situação diário durante a averiguação).

> Os e-mails oficiais da Evri vêm habitualmente de `@evri.com`, `@hermes-europe.co.uk` ou `@myhermes.co.uk`, bem como de `@custhelp.com`, `@globalco.uk`, `@globaleco.app` e subdomínios destes seis endereços (como `@international.evri.com`). Os SMS britânicos surgem apenas com o remetente «Evri» (nunca de um número de telemóvel), nunca pedem pagamentos para reagendar uma entrega nem para instalar aplicações e só incluem ligações para `evri.com` ou `evri.link` (que os burlões ainda assim podem falsificar). Em caso de dúvida, não respondas e consulta o código diretamente no GlobalEco ou em `evri.com`; no Reino Unido, os SMS suspeitos podem ser reencaminhados gratuitamente para o `7726`. Sabe mais em [como reconhecer SMS falsos de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda Evri com o Peek

O [Peek](/) consulta tanto a **Evri International** como a **Evri UK** sempre que introduzes um código de 16 caracteres iniciado por `H` ou `T` cujo algarismo de controlo seja válido: coloca a Evri International em primeiro lugar quando o 6.º carácter é `C` e a Evri UK quando é `A` ou `D`, sem fixar nenhuma delas antes de uma confirmar a encomenda (se ambas a conhecerem, o Peek fica com a Evri International quando é `C` e com a Evri UK nos outros casos). Num código `H` seguido de 15 algarismos com controlo Evri válido, interroga também a Hermes na Alemanha; se o controlo da Evri falhar, só corresponde à Hermes na Alemanha. Se nenhum dos serviços Evri reconhecer ainda o código, toca em «Alterar» e escolhe **Evri International** ou **Evri UK** em «Corresponde a este número», ou cola uma ligação `evri.com/track/parcel/…/details`, que o Peek associa de imediato à Evri UK. Para 16 algarismos, o Peek verifica a Evri UK a par da [Correos Express](carrier:correos-express) e de outras transportadoras quando o dígito GS1 bate certo, e apenas as restantes quando não bate; um número `C00HHA` com 10 algarismos segue diretamente para a Evri UK. Não é preciso código postal, mas o Peek não acompanha números de *calling card* de 8 dígitos.

Na **Evri International**, o Peek mostra o histórico do GlobalEco (a maioria das leituras permanece em inglês, enquanto estados gerais como «Em trânsito», «Em distribuição» ou «Entregue» aparecem em português), os locais de leitura, o peso da encomenda e, quando um comentário inclui `Estimated delivery date is: …`, a data estimada até à entrega ou devolução; o remetente e o destino são omitidos. Como o GlobalEco não indica fuso horário nas horas registadas, o Peek mantém essas horas locais tal como surgem.

Na **Evri UK**, vês o estado e o histórico. A Evri UK não envia locais de leitura, pelo que o mapa fica vazio. Qualquer entrega por estafeta ou levantamento num ParcelShop ou Locker surge como «Entregue» sem indicar onde ficou deixada; uma encomenda a aguardar num ParcelShop ou Locker aparece como «Pronta para levantamento» e os restantes mantêm a frase em inglês da Evri. O teu nome, morada, fotografia de entrega e códigos de recolha são descartados. Em peektracker.com e nas aplicações para iPhone e Android, os registos com fuso horário terminam com «Horas no teu fuso horário: …», e podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega».

## Perguntas frequentes sobre o seguimento da Evri

### A Evri é a mesma empresa que a Hermes?

No Reino Unido, sim: a Evri é a antiga Hermes UK, que mudou de nome em março de 2022. Já a Hermes na Alemanha é uma transportadora independente; se o teu número começar por `H` seguido de 15 algarismos, confirma no e-mail da loja qual das duas expediu a encomenda.

### A Evri pertence à DHL ou à Royal Mail?

A nenhuma das duas (nem à [Royal Mail](carrier:royal-mail) nem à [DHL](carrier:dhl)). Na operação aprovada pela autoridade da concorrência britânica em setembro de 2025 e concluída a 1 de outubro de 2025 com a criação do Evri Group, a Evri (então detida por fundos geridos pela Apollo) adquiriu a DHL eCommerce UK ao DHL Group, que ficou com uma participação minoritária na Evri. A DHL eCommerce UK passou em 2026 a chamar-se **Evri Premium – a network of DHL**, mantendo-se como uma rede separada para encomendas volumosas ou de elevado valor; os envios normais da DHL não são seguidos no portal corrente da Evri.

### Quanto tempo demora uma encomenda da Evri a chegar a Portugal?

Enquanto a FAQ internacional geral da Evri refere 3 a 7 dias úteis no mundo, a página da Evri dedicada a Portugal aponta para 7 a 10 dias úteis no serviço Standard, aos quais podem somar-se feriados ou o desalfandegamento. Dentro do Reino Unido, o centro de ajuda da Evri indica 1 a 2 dias em *Next Day* e 3 a 5 dias em *Standard* (outras páginas da marca referem 1 a 3 dias).

### Um e-mail vindo de noreply.priority@evri.com é verdadeiro?

A Evri indica esse endereço para receber mensagens: na página de contacto, o botão «Email us» em *Tell us straight* abre um e-mail para `noreply.priority@evri.com` destinado à equipa sénior depois de o apoio ao cliente já ter tentado ajudar, com confirmação prometida numa hora durante o horário de expediente britânico. Como as páginas de segurança da Evri listam apenas domínios como `@evri.com`, sem mencionar esse endereço específico e o remetente de um e-mail pode ser forjado ou conter uma letra trocada, confere-o letra a letra e, na dúvida, não respondas: acede diretamente a `evri.com`.

### Posso seguir uma encomenda da Evri apenas pelo código postal?

Não. Quer no GlobalEco, quer em `evri.com` ou no Peek, tens sempre de introduzir primeiro o código de seguimento de 16 caracteres (ou, apenas em `evri.com`, os 8 dígitos de uma *calling card* britânica); o código postal só entra depois em `evri.com` para ver detalhes adicionais ou desviar uma entrega no Reino Unido.

:::sources
- [Evri: Track a parcel](https://www.evri.com/track-a-parcel) – 16 caracteres, calling card e acesso internacional
- [GlobalEco: Tracking](https://globaleco.app/track/) – portal da Evri International
- [Evri: International services](https://www.evri.com/our-services/international-courier-services) – mais de 200 destinos
- [Evri: Europe](https://www.evri.com/our-services/international-courier-services/europe) – IOSS e alfândega
- [Evri: Portugal](https://www.evri.com/our-services/international-courier-services/portugal) – 7 a 10 dias úteis e importação
- [Evri: EU Customs Update Nov 2026](https://www.evri.com/news/eu-customs-update-november-2026) – 3 € e 2 € por grupo SH
- [Evri: International deliveries FAQ](https://www.evri.com/faqs/sending-a-parcel/international-deliveries) – 3 a 7 dias úteis, eBay GSP
- [Evri: International contact FAQ](https://www.evri.com/faqs/sending-a-parcel/how-to-contact-customer-services-for-international-parcels) – `H04G0C`, `H0595C`, Ezra e `0`
- [Evri: Evri gateway](https://www.evri.com/faqs/receiving-a-parcel/what-is-an-evri-gateway) – alfândega britânica
- [Evri: How do I track my parcel?](https://www.evri.com/faqs/receiving-a-parcel/how-do-i-track-my-parcel) – «on its way», calling card e Lockers
- [Evri: Tracking status](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/tracking-status) – «We're expecting it», 24 h
- [Evri: Sender status](https://www.evri.com/help-and-support/help-centre/sending/delivery-problem/status-has-not-changed) – 24 h e 5 dias
- [Evri: Update on my delivery](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/update-delivery) – prazos UK, janela de 2 h
- [Evri: Contact you](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/contact-you) – dados adicionais por chat
- [Evri: Change delivery](https://www.evri.com/help-and-support/help-centre/receiving/awaiting-delivery/change-delivery) – morada e data
- [Evri: Can't find parcel](https://www.evri.com/help-and-support/help-centre/receiving/not-find-parcel/all-correct) – entregue e não encontrada
- [Evri: Haven't received parcel](https://www.evri.com/faqs/receiving-a-parcel/what-can-i-do-if-i-havent-received-my-parcel) – 7 dias
- [Evri: Return received](https://www.evri.com/faqs/returning-a-parcel/how-will-i-know-the-retailer-has-received-my-return) – último registo de devolução
- [Evri: Home](https://www.evri.com/) – janela de 1 h e 3 tentativas
- [Evri: Safe place](https://www.evri.com/guides/safe-place) – local seguro e armazém
- [Evri: Send](https://www.evri.com/send) – ParcelShop após 3 tentativas
- [Evri: Diversions](https://www.evri.com/diversions) – desvios em evri.com
- [Evri: Expected time](https://www.evri.com/faqs/receiving-a-parcel/expected-delivery-time) – manhã e assinatura
- [Evri: Collect parcel](https://www.evri.com/faqs/receiving-a-parcel/can-i-collect-my-parcel) – sem recolha em distribuição
- [Evri: ParcelShop delivery](https://www.evri.com/our-services/parcelshop-delivery) – 10 dias, documento e Post Office
- [Evri: Lockers](https://www.evri.com/parcelshops/lockers) – 24/7, 3 dias e «help»
- [Evri: Terms](https://www.evri.com/terms-and-conditions) – tentativas e prazos 28/45 dias
- [Evri: Claims](https://www.evri.com/faqs/account/how-do-i-submit-a-claim-for-a-lost-or-damaged-parcel) – reclamação pelo remetente
- [Evri: Contact us](https://www.evri.com/contact-us) – chat, 0330 808 5456 e Tell us straight
- [GOV.UK: Call charges](https://www.gov.uk/call-charges) – números 03
- [Evri: Cyber security](https://www.evri.com/cyber-security) – domínios, SMS e 7726
- [Evri: How will Evri contact me?](https://www.evri.com/faqs/receiving-a-parcel/how-will-evri-contact-me) – `evri.link`
- [Evri: Genuine message?](https://www.evri.com/faqs/receiving-a-parcel/is-this-evri-text-email-genuine) – endereços falsificados
- [Evri: About us](https://www.evri.com/about-us) – antiga Hermes UK e lojas parceiras
- [Evri: Evri Group](https://www.evri.com/press/introducing-the-evri-group-the-uks-premier-parcel-delivery-company) – fusão 1 out. 2025
- [Evri: Evri Premium](https://www.evri.com/our-services/evri-premium-dhl) – rede separada da DHL
- [GOV.UK: Merger inquiry](https://www.gov.uk/cma-cases/evri-slash-dhl-merger-inquiry) – Apollo, DHL e CMA
- [Comissão Europeia: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – fim da isenção de 150 €, direito de 3 € e taxa da UE
- [Your Europe: Expedição e entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm) – 30 dias e responsabilidade
- [Universal Parcel Scraper: Evri International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri) – `C` na 6.ª posição e GlobalEco
- [Universal Parcel Scraper: Evri UK](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/evri-uk) – formatos, dados e códigos UK
:::
