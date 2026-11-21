---
title: Seguimento ePost Global: números EPG, estados e entrega
description: Segue uma encomenda ePost Global: formato dos códigos EPG, significado dos estados em inglês, quem entrega em Portugal e como acompanhar no Peek.
slug: seguimento-epost-global
published: 2026-11-21
updated: 2026-11-21
---

A ePost Global (`epostglobalshipping.com`) é um consolidador internacional de encomendas e correio sediado nos Estados Unidos (`11137 Warland Drive, Cypress, CA 90630, USA`). Criada a partir da divisão *Express Postal Options (XPO)* da R.R. Donnelley e com mais de 25 anos de operação transfronteiriça, recebe as encomendas nos seus centros de processamento norte-americanos, trata do transporte aéreo e do desalfandegamento e entrega a última milha através de mais de 100 operadores postais e transportadoras em mais de 200 países e territórios (além de envios domésticos nos EUA e no Canadá através da rede `eDGE`). Os seus números começam por `EPG`: cola o teu na caixa acima. Os portais próprios de seguimento são `epgtrack.com` (*EPG Track*) e `ppxtrack.com` (*PPX Track*); no catálogo do Peek também encontras a ePost Global pesquisando `ePostGlobal`, `EPG`, `PPX Track`, `PPXTrack`, `RR Donnelley`, `RRD Donnelley` ou `RRD International Logistics`.

## Número de seguimento da ePost Global: como é

O Peek reconhece um único formato próprio da ePost Global: `EPG` seguido de 10 a 15 algarismos (as etiquetas atuais usam 15). Com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `EPG482910573649182` | `EPG` e 15 algarismos: um número atual da ePost Global. |
| `EPG4829105736` | `EPG` e 10 algarismos: também da ePost Global, o comprimento mínimo aceite pelo Peek. |
| `EPG482910573` | Apenas 9 algarismos: não é o formato da ePost Global. Três letras e nove algarismos coincidem com a Colis Privé, a Purolator e a TNT Australia, que o Peek consulta nesse caso. Se escolheres a ePost Global manualmente, o Peek indica que «não emite números como este». Confirma se falta um algarismo. |
| `EPG4829105736491827` | 16 algarismos, um a mais: o Peek não reconhece nenhuma transportadora. Verifica se há um dígito extra. |
| `LZ482910576US` | Duas letras, nove algarismos e `US`: um código postal norte-americano. O Peek indica a [USPS](carrier:usps) para a maioria dos começados por `C`, `E`, `L` ou `R` (segundo as FAQ da ePost Global, o seguimento faz-se no portal PPX ou no da USPS, consoante o produto). Para veres também as leituras internas da ePost Global no Peek, adiciona o número `EPG`. |

Espaços, traços e minúsculas não interferem quando colas o código no Peek. Em `epgtrack.com` podes pesquisar até 20 números de uma vez; em `ppxtrack.com`, até 10.

**Onde encontrar o número.** No e-mail de confirmação de envio da loja ou na página da encomenda. Se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento da ePost Global explicados

O localizador da ePost Global apresenta cada linha em inglês com data, hora, descrição e quase sempre local. Primeiro surgem as linhas da própria ePost Global, pela ordem normal do envio:

| A ePost Global diz | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Data Received` | A loja enviou os dados eletrónicos da encomenda à ePost Global, que ainda não tem o pacote fisicamente. | «Etiqueta criada» |
| `Shipment in Transit to the ePost Global Processing Center` | Gravado um minuto após `Data Received` em todas as remessas que o têm: faz parte da etiqueta, não significa que o pacote já esteja em estrada. | «Etiqueta criada» |
| `Parcel Received and Processing` | Um centro de processamento da ePost Global nos EUA leu a encomenda (com cidade e estado). A ePost Global indica processar e expedir os pacotes em 24 horas ou menos. | «Aceite pela transportadora» |
| `Parcel Shipped` | A encomenda saiu do centro de processamento norte-americano. | «Em trânsito» |
| `In transit to … tracking #: …` | Entrega ao parceiro que assegura a etapa seguinte, um minuto após a expedição, indicando o serviço e o número do parceiro (por vezes `In transit via …` ou `tracking#:`). | «Em trânsito» |
| `In transit` | A mesma passagem ao parceiro, sem nomear o serviço nem o segundo número. | «Em trânsito» |

**As linhas retransmitidas dos parceiros.** Depois dessa passagem, `epgtrack.com` retransmite os eventos do parceiro. Os que o código do Peek conhece diretamente:

| Após a passagem | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | O parceiro norte-americano OSM Worldwide recebeu os dados da encomenda. | sem recuo |
| `Arrived at OSM facility`, `Departed OSM facility`, `Package accepted at courier partner`, `Package processed by courier partner` | Passagem por uma instalação da OSM Worldwide e entrega ao respetivo parceiro de distribuição. | «Em trânsito» |
| `Electronic manifest received` | Manifesto eletrónico registado pela ECMS Express após a receção na ePost Global: registo de dados sem local que mantém o estado anterior. | sem alteração |
| `Parcel has arrived at export distribution warehouse` | Chegada ao armazém de exportação da ECMS Express na origem. | «Em trânsito» |
| `Customer Information Manifested` | A DAI Post, parceira para a Nova Zelândia, registou os dados. | sem recuo |
| `Checked in at origin hub`, `Airline Delay`, `Collected from the airline for processing` | Hub da DAI Post nos EUA, atraso do voo (`Airline Delay` significa voo atrasado, não problema com o pacote) e recolha do avião no destino. | «Em trânsito» |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport`, `The item is tied to a MAWB and ready for shipment`, `The item is received by PostNL`, `The item is on transport to the country of destination` | A [PostNL](carrier:postnl), através da Spring, recebeu a encomenda e encaminha-a por avião para o país de destino. | «Em trânsito» |
| `The consignment has arrived in the country of destination`, `The item has arrived in the country of destination`, `The item is at the PostNL sorting center`, `The item is processed at the PostNL sorting center`, `The item has been processed in the country of destination` | Chegada ao país de destino e triagem na rede da PostNL. | «Em trânsito» |
| `The item is at customs`, `The item is being processed by customs` | Controlo aduaneiro de importação numa remessa gerida pela PostNL. | «Na alfândega» |
| `Item released by customs` | Desalfandegamento concluído na PostNL; a encomenda segue caminho. | «Em trânsito» |
| `The item is out for delivery` | Em distribuição segundo a leitura da PostNL. | «Em distribuição» |
| `The item will be scheduled for a new delivery attempt` | Tentativa falhada indicada pela PostNL; será agendada nova tentativa. | «Tentativa de entrega» |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Avisos da [La Poste](carrier:la-poste) para encomendas encaminhadas para França via PostNL, ainda antes da chegada. | «Em trânsito» |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Rede europeia da GLS: saída do hub e chegada ao centro de distribuição. | «Em trânsito» |
| `The parcel is expected to be delivered during the day.` | Rede europeia da GLS: entrega prevista durante o dia. | «Em distribuição» |
| `The parcel has been delivered.` | Rede europeia da GLS: entregue. | «Entregue» |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Parceiro britânico da [Royal Mail](carrier:royal-mail): a caminho do Reino Unido ou em armazém. | «Em trânsito» |
| `Arrived in customs` | O mesmo parceiro britânico: entrada na alfândega britânica. | «Na alfândega» |
| `Ready for Collection` | O mesmo parceiro britânico: pronta num ponto de recolha. | «Pronta para levantamento» |
| `Duty &/or tax fees requested` | A transportadora de destino pede o pagamento de direitos ou impostos antes da entrega. | «Na alfândega» |
| `Received payment` | Esses encargos foram pagos e o envio prossegue. | «Em trânsito» |
| `The item is available at the pickuppoint` | A encomenda aguarda levantamento num ponto de recolha local. | «Pronta para levantamento» |

Uma receção de parceiro após `Parcel Shipped` conta como «Em trânsito», não como novo começo. O Peek lê outras frases de parceiros pelas suas regras gerais; um texto desconhecido aparece tal como vem e não faz recuar uma encomenda já em distribuição ou entregue.

## Quem entrega as encomendas da ePost Global em Portugal

A ePost Global trabalha com mais de 100 operadores postais e de última milha em seis continentes. Nos históricos presentes no código do Peek surgem a rede europeia da GLS, a [PostNL](carrier:postnl) via Spring (com passagem à [La Poste](carrier:la-poste) em França), a OSM Worldwide, a ECMS Express, a DAI Post e a [Royal Mail](carrier:royal-mail). Quando a ePost Global não nomeia parceiro mas indica um código postal internacional válido (duas letras, nove algarismos, duas letras), o Peek consulta o operador postal do país de destino, como os [CTT](carrier:ctt) em **Portugal**.

**Prazos indicativos e alcance do seguimento.** A partir dos Estados Unidos, a página *Cross-Border Parcel Shipping* da ePost Global aponta `1-5 days` porta a porta na maioria das rotas para *Expedited Parcel with Delivery Confirmation* e `4 to 8 days` para o Canadá ou `5 to 10 days` para a Europa Ocidental em *Priority Parcel with Delivery Confirmation* (`4 to 8 days` para o Canadá e Europa Ocidental em *Priority Packet Tracked*, com seguimento porta a porta para 48 países principais). Cinco dos sete produtos de encomendas têm seguimento porta a porta; os dois produtos económicos dão seguimento dentro do país após a chegada ao destino. As tentativas de entrega e o prazo de levantamento seguem as regras da transportadora final: consulta [entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).

**Alfândega em Portugal e na União Europeia.** Na **União Europeia**, desde 1 de julho de 2026 os bens comprados online fora da UE em remessas com valor intrínseco até 150 € pagam um direito aduaneiro da UE de 3 € por artigo (*per item*), e desde 1 de novembro de 2026 soma-se uma taxa de manuseamento da União (*Union handling fee*) de 2 € por artigo seja qual for o valor (um artigo reúne mercadorias da mesma encomenda com igual classificação pautal, descrição e, quando exigida, origem; acima de 150 € aplicam-se os direitos normais), além do IVA de importação em **Portugal** (incluindo os **Açores** e a **Madeira**, que segundo o âmbito territorial publicado pela Comissão Europeia fazem parte tanto do território aduaneiro como da área de IVA da UE), salvo se o IVA já tiver sido cobrado na compra através do IOSS. Segundo a nota aduaneira da ePost Global, a taxa de 2 € aplica-se tanto a remessas `DDP` (encargos cobrados no checkout) como `DDU` (pagas pelo destinatário na entrega, podendo a encomenda ficar retida até ao pagamento). Lê mais em [encomenda retida na alfândega](guide:customs).

## Encomenda ePost Global parada, perdida ou devolvida

**«À espera de ePost Global».** Quando `epgtrack.com` devolve `We haven't received any tracking updates`, o Peek mostra «À espera de ePost Global» e continua a verificar. Confirma que o número tem `EPG` e entre 10 e 15 algarismos; se ao fim de dois dias a ePost Global continuar sem reconhecer o código e nenhum serviço de seguimento o encontrar, o Peek avisa: «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.»

**Sem novidades após `In transit to …`.** A encomenda já passou ao parceiro, cujo seguimento próprio pode estar mais adiantado. Se o Peek ainda não tiver mudado automaticamente para lá, procura o código depois de `tracking #:` e adiciona-o também ao Peek.

**Parada há vários dias.** Ao fim de quatro dias sem nova leitura, o Peek assinala o dia da última leitura («Sem atualizações desde seg 12 out»), lembra que as encomendas costumam ficar sem novidades em voos longos, na alfândega ou ao fim de semana (passadas três semanas sem nada de novo, sugere contactar o vendedor) e mostra a ligação «Pede ajuda a ePost Global» (ou a do parceiro assim que o Peek acompanha a encomenda numa transportadora cuja página de ajuda conhece; a OSM Worldwide e a DAI Post não têm página de ajuda no catálogo, pelo que se mantém a da ePost Global). Podes também ver avisos de rota em `epostglobalshipping.com/service-updates` e ler [porque é que o seguimento não atualiza](guide:tracking-not-updating).

**Dada como entregue mas não recebida, perdida ou devolvida.** Fala primeiro com a transportadora local indicando o número dela e depois contacta a loja onde compraste, que contratou o envio com a ePost Global. Consulta [entregue mas não recebido](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contactos da ePost Global

Segundo `epostglobalshipping.com`:

- **Telefone (EUA)**: `+1 866-784-8444` (gratuito nos EUA) e `+1 310-784-8485` (número local), sem horário nem e-mail de apoio divulgados na página.
- **Assistente online ePost Assist**: em `epostassist.epgtrack.com` (através de *Track a package* ou *Chat with our online support*), com as opções *Check package status*, *Create a support ticket* e *Get support ticket status*.
- **Formulário de suporte**: em `epostglobalshipping.com/contact-support` (*Looking for support beyond tracking a package?*), com campo opcional para o número de seguimento.
- **Sede**: `11137 Warland Drive, Cypress, CA 90630, USA`.

**Cuidado com SMS fraudulentos.** Os domínios oficiais da ePost Global para seguimento e suporte são `epgtrack.com`, `epostassist.epgtrack.com`, `ppxtrack.com` e `epostglobalshipping.com`. Numa remessa `DDU`, qualquer pedido legítimo de pagamento aduaneiro vem dos CTT ou da transportadora que entrega em Portugal: confirma sempre o endereço antes de pagar e vê [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda da ePost Global com o Peek

O [Peek](/) identifica de imediato um código composto por `EPG` e 10 a 15 algarismos («Transportadora detetada») e consulta `epgtrack.com` sem conta nem código postal. Também aceita ligações de `epgtrack.com` ou `ppxtrack.com`, extraindo o número (o primeiro se `?id=` tiver vários). Se só tiveres o número da transportadora parceira, cola-o na mesma: o Peek acompanha mais de 4 500 transportadoras.

O Peek mostra o estado, cada leitura com o seu local, o país de destino e o parceiro com o respetivo número quando a ePost Global os indica. Deixa de fora a barra de progresso, códigos postais, números de armazém ou porta, a referência do cliente e cartas de porte aéreo (`MAWB`); como `epgtrack.com` não dá data prevista de entrega, peso nem comprovativo de entrega, o Peek não apresenta nenhum. O botão «Abrir o site de ePost Global» abre o teu código diretamente em `epgtrack.com`.

O localizador apresenta as horas sem fuso horário. O Peek lê `Parcel Received and Processing` e `Parcel Shipped` pelo relógio do estado norte-americano do centro quando todo o estado partilha o mesmo fuso, os registos sem país da PostNL pela hora da PostNL, as linhas da rede europeia da GLS pela hora da Europa Central e as do parceiro britânico da Royal Mail pela hora do Reino Unido; as restantes linhas são datadas pelo local ou pela etapa da viagem. Todas as horas aparecem no fuso horário do teu dispositivo e, em `peektracker.com` bem como nas aplicações iPhone e Android, o histórico termina com «Horas no teu fuso horário: …».

Quando a ePost Global nomeia um parceiro conhecido pelo Peek, o cartão mostra por exemplo «Entrega: PostNL» — ou «Anunciada a PostNL, ainda não recebida» enquanto esse parceiro tiver apenas o anúncio sem leituras próprias — e lista o número do parceiro a seguir ao `EPG`. O Peek consulta então esse parceiro (pelo número dele ou, se não houver, pelo `EPG`); sem parceiro nomeado, pergunta ao operador postal do país de destino perante um código postal válido ou à única transportadora indicada pelo formato do segundo número (entre aquelas cujo localizador direto o Peek lê sem dados adicionais). Assim que essa transportadora apresenta uma leitura pelo menos tão recente como a última da ePost Global, o Peek passa a segui-la mantendo o histórico anterior (para a rede europeia da GLS, como a ePost Global não indica que empresa nacional da GLS entrega, o Peek mantém-se nas linhas retransmitidas pela ePost Global).

Enquanto mantiveres a encomenda aberta ou, no dia da entrega, tiveres notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 locais no dia em que passa a «Em distribuição» enquanto o localizador da ePost Global responder (quando outra transportadora assume a entrega, o Peek acompanha o ritmo dessa transportadora; apenas por serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ativas) —; nos restantes casos, e após dois dias sem novidades (12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes receber alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a ePost Global nem a loja por ti.

## Perguntas frequentes sobre o seguimento da ePost Global

### O que é o PPX Track?

É outro portal de seguimento da ePost Global (`ppxtrack.com`, até 10 números por pesquisa) e um dos nomes pelos quais o catálogo do Peek reconhece a ePost Global. O Peek lê ligações de `ppxtrack.com` tal como as de `epgtrack.com`.

### O que significa «In transit to» no seguimento da ePost Global?

A ePost Global entregou a encomenda ao parceiro que continua o transporte. A linha indica o serviço utilizado e, a seguir a `tracking #:`, o número de seguimento próprio desse parceiro.

### Porque é que a minha encomenda não sai de «Data Received»?

A loja enviou os dados eletrónicos à ePost Global, mas nenhum centro de processamento nos Estados Unidos leu ainda o volume fisicamente. Pergunta ao vendedor quando entregou a encomenda ao transportador.

### Quem entrega as encomendas da ePost Global em Portugal?

O parceiro indicado na linha de transição: nos históricos que o código do Peek conhece surgem redes como a europeia da GLS ou a PostNL através da Spring. Quando a remessa traz um código postal internacional sem parceiro nomeado, o Peek consulta os CTT em Portugal.

### Como posso contactar a ePost Global?

Fala primeiro com a loja onde fizeste a compra. Para contactar diretamente a ePost Global, usa o assistente *ePost Assist* em `epostassist.epgtrack.com`, o formulário em `epostglobalshipping.com/contact-support` ou os números dos EUA `+1 866-784-8444` e `+1 310-784-8485`.

:::sources
- [ePost Global: página inicial](https://epostglobalshipping.com/) – consolidador internacional, 20M+ envios anuais, 100+ parceiros, 200+ países, morada em Cypress e telefones
- [ePost Global: About us](https://epostglobalshipping.com/about-us) – mais de 25 anos de atividade, origem na R.R. Donnelley (XPO) e parceiros em seis continentes
- [ePost Global: Cross-Border Parcel Shipping](https://epostglobalshipping.com/cross-border-parcel-shipping) – processamento em 24 horas ou menos, prazos para o Canadá e Europa Ocidental, seguimento porta a porta ou no destino, DDP e DDU, portais PPX e USPS
- [ePost Global: eDGE](https://epostglobalshipping.com/edge) – rede doméstica nos EUA e no Canadá com mais de 100 transportadoras
- [ePost Global: EU customs fee ecommerce](https://epostglobalshipping.com/eu-customs-fee-ecommerce) – taxa de 2 € por artigo em DDP e DDU, direito de 3 € até 150 €, IOSS e retenção até pagamento em DDU
- [ePost Global: Contact support](https://epostglobalshipping.com/contact-support) – formulário de apoio, acesso ao ePost Assist, telefones e morada
- [ePost Global: Contact us](https://epostglobalshipping.com/contact-us) – ligação para o suporte online em caso de encomenda em falta
- [ePost Global: Service updates](https://epostglobalshipping.com/service-updates) – suspensões e avisos operacionais por destino
- [EPG Track](https://epgtrack.com/) – localizador oficial até 20 números
- [PPX Track](https://ppxtrack.com/) – portal PPX Track até 10 números
- [ePost Assist](https://epostassist.epgtrack.com/) – consulta de estado e abertura ou acompanhamento de pedidos de suporte
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Orientações sobre o direito de 3 € e a taxa da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – valor fixo de 2 € independentemente do preço, definição de artigo e isenção de IVA via IOSS
- [Comissão Europeia: Âmbito territorial do IVA e das alfândegas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Açores e Madeira
- [Universal Parcel Scraper: ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – nomes alternativos, formato EPG, ligações, estados próprios e de parceiros, relógios, campos descartados e página de ajuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos da Colis Privé, Purolator e USPS e transição para o parceiro ou para os CTT
:::
