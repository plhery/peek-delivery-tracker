---
title: Rastreio WanbExpress: números WNB…YQ, estados e entrega
description: Segue uma encomenda WanbExpress (Wanb Express) da China: formato do número WNB…YQ, significado dos estados em inglês, transportadora final e a quem reclamar.
slug: rastreio-wanbexpress
published: 2026-11-21
updated: 2026-11-21
---

A WanbExpress (também conhecida como Wanb Express, WanBang Express ou WANB) é uma transportadora chinesa que envia encomendas para o estrangeiro, onde uma transportadora local faz a entrega. Se uma loja enviou a tua compra pela WanbExpress, o número começa por `WNB` e acaba em `YQ`: cola-o na caixa acima. O rastreio próprio da WanbExpress, tracking.wanbexpress.com, também pode indicar qual é a transportadora que entrega no teu país e o respetivo número.

## Número de seguimento WanbExpress: como é

As guias da WanbExpress têm `WNB`, duas letras, dez algarismos e `YQ`, num total de 17 carateres. Exemplos com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, duas letras, dez algarismos e `YQ`: um número WanbExpress. |
| `WNBKG482910573YQ` | Apenas nove algarismos: não segue o formato WanbExpress. Verifica se falta um algarismo. |
| `JSEKG4829105736YQ` | Também acaba em `YQ`, mas começa por `JSE`: é um número da [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Começa por `JXC`: é um número da [Juxi Express](carrier:juxi-express). |

Vários transitários chineses terminam as suas guias em `YQ`, cada um com letras próprias no início: `WNB` identifica a WanbExpress. Ao colar o número no Peek, espaços, hífenes e minúsculas não importam.

**Onde encontrar.** A loja indica o número no e-mail de envio ou na página da encomenda. Se não o encontrares, vê [onde encontrar o número de seguimento](guide:find-tracking-number).

**Uma ligação de seguimento.** Uma ligação para o rastreio da WanbExpress que termine em `?trackingNumbers=` seguido de um número abre essa encomenda lá. O número `WNB` é a parte a seguir ao sinal de igual: copia-o daí para o Peek.

**Um segundo número.** O rastreio da WanbExpress também pode mostrar o número que a transportadora de entrega usa para a encomenda. Esse número pertence à outra transportadora: consulta-o no site dela ou adiciona-o no Peek como uma encomenda à parte.

## Estados de seguimento da WanbExpress explicados

Os registos da WanbExpress vêm em inglês, cada um com hora e por vezes um local. Eis os textos presentes no código do Peek:

| O que diz a WanbExpress | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | A loja registou o envio e enviou os dados. A WanbExpress ainda não tem a encomenda. | «Etiqueta criada» |
| `Parcel received at Shenzhen Sorting Centre.` | O centro de triagem de Shenzhen, na China, recebeu a encomenda: é o primeiro registo físico. | «Aceite pela transportadora» |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Triada e depois expedida desse centro. | «Em trânsito» |
| `A-Scan: shipment in international transit` | Registo de aceitação retransmitido entre o processamento no centro de triagem e a partida. | «Em trânsito» |
| `Customs declaration completed` | Foi concluída uma declaração aduaneira. O texto não indica de que país e não corresponde ao desalfandegamento de importação, que surge como `Custom clearance completed`. | «Em trânsito» |
| `Customs inspection has not yet released` | Inspeção de exportação na origem, entre a declaração e o voo. | «Em trânsito» |
| `Parcel departure in Transit Facility, Parcel Departure` | Saiu de uma instalação de trânsito. | «Em trânsito» |
| `Parcel is leaving Airport, Parcel Leaving Port` | Partida no voo para o país de destino. | «Em trânsito» |
| `Parcel arrives at Airport, Parcel Arrival` | Aterrou no teu país ou perto. Segue-se a alfândega. | «Em trânsito» |
| `Custom clearance completed` | A alfândega de importação libertou a encomenda. | «Em trânsito» |
| `Parcel received by in JFK01.` | Registado pela WanbExpress como receção para o último troço, sem indicar quem recebeu. | «Em trânsito» |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Aviso de um parceiro retransmitido sem código WanbExpress: a encomenda ainda não lhe foi entregue. | «Etiqueta criada», ou uma etapa posterior se um registo já tiver feito avançar a encomenda |
| `In transit along Poste Italiane’s logistic network` | A caminho através da rede da transportadora final, aqui a [Poste Italiane](carrier:poste-italiane). | «Em trânsito» |
| `Parcel is out for delivery.` | Em distribuição. | «Em distribuição» |
| `Consignee absent, Consignee could not be found.` | O estafeta parceiro não encontrou ninguém. | «Tentativa de entrega» |
| `Placed in Parcelshop/Parcel locker` | Após a tentativa falhada, o parceiro deixou a encomenda num ponto de recolha ou cacifo para levantares. | «Pronta para levantamento» |
| `Parcel has been delivered.(Front door)` | Entregue. `(Front door)` indica onde foi deixada. | «Entregue» |

**Os registos da transportadora final.** Depois do voo, o rastreio da WanbExpress retransmite os registos da transportadora local nas palavras dela, quase sempre sem código WanbExpress. Numa encomenda para a Alemanha, o histórico mostra a [iMile](carrier:imile) a entregar, com o nome do centro ou estação entre parênteses `【】`:

| O que diz a iMile | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Received.` | O centro da iMile no país de destino recebeu a encomenda. | «Em trânsito» |
| `Arrived.` | Chegou à estação de entrega da tua zona. | «Em trânsito» |
| `Dispatch task assigned to DA.` | Foi atribuído um estafeta (DA, delivery associate) à rota, por vezes horas antes de sair. | «Em trânsito» |
| `Shipment out for delivery.` | Em distribuição. | «Em distribuição» |
| `<Route not completed>`, `Back To DS.` | A rota terminou sem entrega e a encomenda regressou à estação (DS). | «Tentativa de entrega» (sozinha após a rota; depois de uma tentativa falhada, `Back To DS.` fica como aviso, e após «Em trânsito» mantém-se «Em trânsito») |
| `Scheduled to <2026-11-02> .` | Dia marcado para a nova tentativa após o regresso à estação. | Continua em «Tentativa de entrega» |

Os movimentos de outros parceiros chegam da mesma forma. Em envios para os Estados Unidos, `Parcel received by Transit Facility.` é a receção numa instalação de trânsito do parceiro local, `In Processing at Transit Facility.` a triagem, `In Transit to Next Facility.` a deslocação para a instalação local e `Parcel received by local Facility.` a entrada antes da distribuição. `It is currently in transit to the next facility` e `The package is being transferred to the next facility` indicam transferências entre instalações após o voo, e `Shipment arrived to sorting centre / Shipment in the sorting centre` a entrada num centro de triagem parceiro. O Peek mostra os sete como «Em trânsito». Numa encomenda para o Reino Unido, podem surgir linhas da [InPost](carrier:inpost) antes mesmo do primeiro registo no centro de triagem da WanbExpress: `Your parcel is on its way to InPost. Please check back for updates` («Etiqueta criada») e `We have your parcel. Please check back for updates` («Aceite pela transportadora»). `We'll apply your My Account preferences to this parcel delivery` é uma nota da InPost sobre as preferências da conta, não um registo: o Peek mantém-na no histórico sem mudar a etapa. O mesmo acontece com `Consignee Contacted`, uma chamada do estafeta registada no mesmo minuto de uma tentativa sem resposta.

**Horas.** Cada registo traz o seu desfasamento em relação a UTC. O Peek lê esse instante e mostra todas as horas no fuso horário do teu dispositivo, das mais recentes para as mais antigas. Para as pausas e passagens habituais em encomendas da China, consulta [seguir uma encomenda da China](guide:tracking-from-china).

## Quem entrega as encomendas da WanbExpress

Os registos da WanbExpress acompanham a encomenda desde a China, pela alfândega, até uma transportadora local que a entrega. O rastreio da WanbExpress pode indicar essa transportadora, o respetivo número e uma foto de entrega.

**O que mostra o código do Peek.** Numa encomenda para a Alemanha, o histórico mostra a iMile a entregar, e um registo cita a rede da Poste Italiane. Numa encomenda para o Reino Unido surgem linhas da InPost. O código regista também reetiquetagens com números da Cainiao Express em França (`DOFR…HD` ou `CNFR…HD`) e linhas retransmitidas da J&T Express Australia.

**Tentativas de entrega e levantamento.** Aplicam-se sempre as regras da transportadora que entrega: quantas tentativas faz, se deixa aviso, onde guarda a encomenda e por quantos dias. O Peek não encontrou regras próprias da WanbExpress sobre estes pontos. Assim que souberes qual é a transportadora e o seu número, consulta o rastreio dela e combina a entrega com ela. Vê [entrega falhada](guide:missed-delivery).

## Encomenda WanbExpress atrasada ou seguimento parado

**Número não encontrado.** Se o rastreio da WanbExpress não reconhece o número, o Peek mostra «À espera de WanbExpress» e continua a verificar. Se ao fim de dois dias a WanbExpress continuar sem reconhecer o número e nenhuma outra fonte tiver respondido, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» Confere o número carácter a carácter com a mensagem do vendedor: `WNB`, duas letras, dez algarismos, `YQ`. Se nada surgir após alguns dias, pergunta ao vendedor se a encomenda foi mesmo enviada.

**Parada em `Shipping Order Created.` ou `Parcel Data Received`.** A loja reservou o envio, mas a WanbExpress ainda não leu a encomenda. Pergunta à loja quando a entregou à transportadora.

**Parada após `Parcel arrives at Airport, Parcel Arrival`.** É provável que aguarde desalfandegamento de importação. Para a União Europeia, as compras online vindas de fora da UE pagam desde 1 de julho de 2026 um direito aduaneiro temporário de 3 € por adição (tipo de produto) em encomendas até 150 € e, desde 1 de novembro de 2026, uma taxa de tratamento da União de 2 € por adição, seja qual for o valor. Se te pedirem o pagamento de taxas de importação, vê [encomendas na alfândega](guide:customs); se ficar parada muito tempo, avisa o vendedor. Mais em [porque o seguimento não atualiza](guide:tracking-not-updating).

**Sem novidades após `Custom clearance completed`.** A transportadora final já tem a encomenda ou vai recebê-la em breve, e o rastreio dela pode ir adiantado em relação ao da WanbExpress. Procura o nome e o número dela em tracking.wanbexpress.com ou pergunta ao vendedor.

**Tentativa de entrega.** Depois de uma linha como `<Route not completed>`, cabe à transportadora local decidir quando volta a tentar ou onde fica a encomenda. Contacta essa transportadora, não a WanbExpress.

**Marcada como entregue, mas nada chegou.** Verifica se tracking.wanbexpress.com mostra uma foto de entrega e contacta depois a transportadora local e o vendedor. Mais em [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Quem contratou a WanbExpress foi o vendedor, por isso é ao vendedor ou à plataforma que deves pedir reembolso ou novo envio. Vê [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto da WanbExpress

O Peek não conseguiu verificar o site da WanbExpress, pelo que esta página não lista contactos da WanbExpress. Sobre a encomenda, contacta o vendedor que contratou o envio, ou o marketplace se o vendedor não responder. Para a entrega em si, fala com a transportadora local.

## Seguir uma encomenda WanbExpress com o Peek

O [Peek](/) reconhece um número WanbExpress pela forma (`WNB`, duas letras, dez algarismos e `YQ`, exclusiva no catálogo) e consulta o rastreio da WanbExpress apenas com esse número, sem conta, código postal nem telefone.

Como o rastreio da WanbExpress exige uma verificação antes de responder, o Peek consulta-o através de um navegador. Quando o rastreio não responde, o Peek recorre a serviços de seguimento e mostra o histórico que eles guardam, nas palavras deles; esse histórico pode estar atrasado face ao da WanbExpress.

Do rastreio da WanbExpress, o Peek mostra o estado, os registos com o local, bem como os países de origem e destino do registo. Quando o registo marca a encomenda em trânsito, em distribuição ou entregue, o Peek segue esse estado mesmo que o último registo diga menos. Se a WanbExpress devolver o número da transportadora final e o painel de última milha identificar uma transportadora do catálogo cujo formato coincida com esse número, o Peek pode mostrar «Entrega: …» (ou «Anunciada a …, ainda não recebida») e passar a segui-la aí. O Peek não mostra data prevista de entrega e deixa de fora as fotos de entrega e a morada do destinatário; consulta tracking.wanbexpress.com para os ver, ou adiciona à parte no Peek o número da transportadora local. O Peek segue mais de 4500 transportadoras.

Enquanto tens a encomenda aberta no ecrã ou no dia da entrega com as notificações ativas, e enquanto o rastreio da WanbExpress responde, o Peek verifica até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6h na hora da transportadora no dia em que sai para distribuição (de 5 em 5 minutos quando a consulta abre um navegador); caso contrário, de hora a hora. Através de serviços de seguimento verifica de hora a hora, de 3 em 3 horas após um dia sem movimento e até de 15 em 15 minutos no dia da distribuição enquanto a tens aberta ou com notificações ativas. Depois de dois dias sem novidades (12 horas enquanto ainda mostra «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora. Pode notificar-te de «Todos os registos», «Passos importantes» ou «Só a entrega». «Passos importantes» (entrega, levantamento, alfândega, tentativas falhadas e devoluções) inclui a distribuição, uma tentativa falhada e a entrega, mas não as linhas aduaneiras da WanbExpress, que o Peek classifica como «Em trânsito». O Peek não pode contactar a WanbExpress nem o vendedor por ti.

## Perguntas sobre o rastreio WanbExpress

### O que é a WanbExpress?

Uma transportadora chinesa, também chamada Wanb Express, WanBang Express ou WANB. No código do Peek, os seus registos começam num centro de triagem em Shenzhen, seguem o voo e a alfândega e terminam com os registos da transportadora que entrega no país de destino.

### Onde posso rastrear uma encomenda WanbExpress?

Na caixa acima ou no rastreio da própria WanbExpress em tracking.wanbexpress.com. Esse site faz uma verificação antibot e também pode indicar a transportadora final. Usa o número `WNB…YQ` completo.

### O que significa «Customs declaration completed» na WanbExpress?

Que foi concluída uma declaração aduaneira para a encomenda. O texto não diz de que país é nem significa que a alfândega de importação libertou a encomenda: essa etapa aparece como `Custom clearance completed`. O Peek mostra ambos como «Em trânsito».

### Quem entrega as encomendas WanbExpress no meu país?

Uma transportadora local, que o rastreio da WanbExpress pode indicar com o respetivo número. O código do Peek mostra a iMile numa encomenda para a Alemanha, linhas da InPost para o Reino Unido e um registo que refere a rede da Poste Italiane, bem como passagens para a J&T Express Australia ou números `DOFR…HD`/`CNFR…HD` em França; para outros países, verifica tracking.wanbexpress.com ou pergunta ao vendedor.

### Quem devo contactar se a minha encomenda WanbExpress se perdeu?

O vendedor onde compraste: foi ele quem contratou a WanbExpress. O Peek não conseguiu consultar o site da WanbExpress para obter contactos.

:::sources
- [Universal Parcel Scraper: WanbExpress](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/wanbexpress) – nomes, formato `WNB…YQ`, consulta por navegador, textos dos registos e etapas (incluindo `Customs inspection has not yet released`, `A-Scan: shipment in international transit`, linhas da iMile, InPost, Poste Italiane e parceiros dos EUA), horas com desfasamento UTC, identificação da transportadora final (`TrackLastMileCarrier`), fotos e morada excluídas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outros formatos terminados em `YQ` (`JS Express`, `Juxi Express`), serviços de seguimento de recurso e passagem para a transportadora local
- [Comissão Europeia: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro temporário de 3 € por adição até 150 € desde 1 de julho de 2026, taxa de tratamento da União de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – taxa de tratamento de 2 € por adição, seja qual for o valor
:::
