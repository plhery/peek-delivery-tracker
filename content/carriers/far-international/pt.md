---
title: Rastreio FAR International: números, estados e contacto
description: Segue uma encomenda FAR International (泛远国际) da China: formato FAR…YQ, significado dos estados, quem entrega, alfândega UE e contactos.
slug: rastreio-far-international
published: 2026-11-22
updated: 2026-11-22
---

A FAR International (泛远国际, FAR International Logistics) é uma empresa de logística transfronteiriça sediada em Hangzhou, na China, criada em 2004. Lojas online e marketplaces usam-na para enviar encomendas para o estrangeiro: a FAR recolhe e inspeciona os volumes na China, enquanto os seus parceiros tratam do transporte internacional, do desalfandegamento e da entrega através de uma transportadora no teu país. Podes seguir o número na [página Track and Trace da FAR](https://www.far800.com/en/logistics), em inglês, ou colando-o na caixa acima. O código vem da loja, nos dados de envio da encomenda.

## Número de seguimento FAR International: como é

A FAR não publica um formato oficial. Este é o formato que o Peek reconhece como pertencente à FAR (dígitos inventados):

| Número | O que é |
| --- | --- |
| `FARAA4829173654YQ` | `FAR`, duas letras, 10 dígitos e `YQ`: um código FAR International. |
| `FARAA482917365YQ` | Apenas 9 dígitos: não corresponde ao formato FAR. O Peek não seleciona a FAR International; verifica se falta um dígito. |

No Peek, minúsculas, espaços e hífenes não importam. O seguimento da FAR exige o código completo e aceita até 50 números de cada vez, um por linha.

**Onde encontrar.** No e-mail de envio ou na página da encomenda. Caso contrário, consulta [onde encontrar um número de seguimento](guide:find-tracking-number), ou os guias de [encomendas Temu](guide:temu-orders) e [encomendas SHEIN](guide:shein-orders).

**Um segundo número.** A página de resultados da FAR pode também apresentar um número de última milha (尾程单号), atribuído pela transportadora que faz a entrega no teu país e que podes acompanhar no site dessa transportadora.

## Estados de seguimento FAR International explicados

Os registos de armazém e voo da FAR surgem muitas vezes em chinês seguido de inglês, como `仓库收到包裹 Arrived at Warehouse`: o Peek mantém o texto em inglês. Passos seguintes podem surgir só em inglês ou só em chinês. Cada linha indica hora e local, e o tracker da FAR inclui uma coluna para o fuso horário. Os estados habituais por ordem:

| Texto da FAR | O que significa para ti | O que o Peek mostra |
| --- | --- | --- |
| `Arrived at Warehouse` | O armazém da FAR na China recebeu a encomenda. | «Aceite pela transportadora» |
| `Inspection by Warehouse` | O armazém verifica a mercadoria (a FAR abre encomendas para inspeção na recolha). | «Aceite pela transportadora» |
| `Await Release from Sender` | O armazém retém a encomenda até confirmação do vendedor. | «Aceite pela transportadora» |
| `Departed Warehouse`, `Pickup Scan` | Saiu do armazém FAR e foi recolhida para o troço seguinte. | «Em trânsito» |
| `In Transit to Export Port` | A caminho do aeroporto ou porto na China. | «Em trânsito» |
| `Export Custom Clearance Processing and Flight Booking` | Despacho de exportação na China e reserva de voo — passo habitual antes do voo, não é a alfândega do teu país. | «Em trânsito» |
| `Scheduled to Take off`, `Export Scan` | A aguardar voo e registada para exportação. | «Em trânsito» |
| `Arrived at departure transport hub`, `Leaving from departure country`, `Flight has departed` | Chegou ao centro de partida e partiu de avião. | «Em trânsito» |
| `Arrived at Facility`, `Departed from Facility` | Passou por um centro de transferência e seguiu viagem. | «Em trânsito» |
| `Last Mile Label Create` | Foi criada a etiqueta da transportadora local (aparece muitas vezes após o centro de partida: não é um recuo). | o estado anterior |
| `At destination country` | Chegou ao teu país; seguem-se alfândega e triagem. | «Em trânsito» |
| `Picked up by Customs clearance company`, `Clearance started`, `开始清关` | Um despachante aduaneiro assumiu a encomenda e iniciou o desalfandegamento. | «Na alfândega» |
| `Customs Released`, `Import Scan`, `进口清关完成` | Libertada pela alfândega de importação. | «Em trânsito» |
| `Handover To Last Mile`, `Received by Last Mile Provider`, `Received by local delivery company` | A FAR entregou a encomenda à transportadora que entrega no teu país. | «Em trânsito» |
| `包裹开始派送` | Essa transportadora assumiu o volume; os registos dela surgem a seguir (ainda não indica saída imediata para entrega). | «Em trânsito» |
| `Transferred to Next Facility`, `In transit`, `In Transit to Destination` | Em circulação entre centros de triagem. | «Em trânsito» |
| `Out For Delivery` | Em distribuição. | «Em distribuição» |
| `DELIVERED` | Entregue. | «Entregue» |

Após a entrega à transportadora local, o histórico da FAR pode mostrar textos próprios dessa empresa, como os da FedEx (`Left FedEx origin facility`, `At local FedEx facility`, `On FedEx vehicle for delivery` como «Em distribuição» e `Package delivered` como «Entregue»). Noutras frases, o Peek lê palavras comuns como delivered ou customs; uma linha não reconhecida permanece no histórico mantendo o estado anterior.

As FAQ da FAR referem que `RETURN` indica um problema na entrega. Numa linha que diga apenas `RETURN`, o Peek mantém o estado anterior: lê o registo e fala com o vendedor.

## Quem entrega encomendas FAR International

A FAR descreve-se como uma empresa «light asset»: as suas unidades na China tratam de receção, inspeção, embalagem, etiquetagem e triagem, enquanto os seus fornecedores (operadores aeroportuários e portuários, companhias aéreas, marítimas, despachantes e operadores logísticos terceiros) asseguram alfândega, transporte internacional e entrega final. As páginas da FAR não dizem qual a transportadora em cada país: procura o número de última milha na página de resultados da FAR.

A FAR não publica regras para tentativas de entrega, avisos nem pontos de levantamento: essas regras são da transportadora local. Segue o número de última milha no site dela e vê [entrega falhada](guide:missed-delivery).

**Alfândega na UE.** As compras online fora da UE pagam desde 1 de julho de 2026 um direito aduaneiro de 3 € por artigo em encomendas até 150 €, e desde 1 de novembro de 2026 uma taxa de tratamento da UE de 2 € por artigo qualquer que seja o valor. Um artigo corresponde a mercadorias com a mesma classificação pautal, descrição e, quando aplicável, origem. A FAR não indica como são pagos. Fora da UE depende do teu país: vê [encomenda retida na alfândega](guide:customs).

**Quando a alfândega precisa de ti.** As FAQ da FAR indicam que uma anomalia aduaneira no destino requer a ajuda do destinatário junto das autoridades locais. Se o desalfandegamento falhar após o prazo, a encomenda é abandonada ou devolvida segundo as regras locais e o remetente suporta os custos.

## Encomenda FAR International sem atualizações, perdida ou devolvida

A FAR serve as lojas e marketplaces que a contratam, remetendo-os nas FAQ para o seu gestor de conta. Por isso, em caso de atraso, perda, dano ou devolução, contacta primeiro o vendedor.

**Ainda sem dados.** Se a FAR ainda não devolver registos, o Peek mostra «À espera de FAR International» e continua a verificar. Se passarem dias sem novidades, confirma o código com o vendedor.

**Dias sem novidades.** As FAQ da FAR referem que os registos de voo podem atrasar-se. Quando uma encomenda em viagem fica quatro dias sem novas linhas, o Peek mostra na tua lista o dia do último registo («Sem atualizações desde …») e no ecrã da encomenda «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a FAR International.». Vê [seguimento que não atualiza](guide:tracking-not-updating) e [seguir encomendas da China](guide:tracking-from-china).

**Entregue mas não recebida.** As FAQ da FAR recomendam procurar à volta de casa (cantos, entrada, apartado, cacifo ou outros locais possíveis) e perguntar a familiares e vizinhos. Se continuar desaparecida, avisa logo o vendedor: a investigação de reclamação tem de começar até **14 dias** após a data de assinatura, aberta pelo vendedor junto da FAR. Vê [entregue mas não recebida](guide:delivered-not-received).

**Danificada.** Guarda a caixa exterior e o artigo danificado, como pedem as FAQ da FAR, e avisa o vendedor. Vê [encomendas danificadas](guide:damaged-parcel).

**Devolvida ou perdida.** Pede reenvio ou reembolso à loja. Vê [devolvida ao remetente](guide:returned-to-sender) e [encomendas perdidas](guide:lost-parcel).

## Apoio ao cliente e contactos FAR International

Contactos publicados no [site inglês da FAR](https://www.far800.com/en):

- **Linha global:** 400-128-5656 (número de serviço chinês; a página de serviço menciona uma linha 24/7 para clientes, sem indicar se funciona a partir do estrangeiro nem que idiomas atende).
- **E-mail:** csc@far800.com.
- **Morada:** Room 201, No. 22 Great Wall Street, Gongshu District, Hangzhou, Zhejiang Province, China, código postal 310022.

Sobre a tua encomenda, pergunta primeiro ao vendedor que contratou a FAR.

## Seguir uma encomenda FAR International com o Peek

O [Peek](/) identifica logo a FAR International por qualquer número `FAR…YQ` («Transportadora detetada»). Colar uma ligação de resultados far800.com com `key=` e um código FAR também funciona. Para qualquer outro número, como o de última milha, cola-o na mesma: o Peek segue mais de 4500 transportadoras e identifica a certa quando o formato é único. Quando o formato serve várias transportadoras, o Peek mostra «A verificar serviços de seguimento…» enquanto consulta; quando não serve nenhuma, procura-o depois de adicionares. Se preciso, toca em «Alterar» e escolhe em «Todas as transportadoras», ou usa «Alterar transportadora» numa encomenda já adicionada. A FAR International responde apenas a códigos `FAR…YQ`.

O Peek lê diretamente o seguimento da FAR: estado, cada linha com local e hora, hora de entrega e número de última milha. Onde a FAR fornece fuso horário, o Peek converte a hora para o teu fuso horário (o histórico termina com «Horas no teu fuso horário:» e o respetivo nome). A FAR não indica data prevista de entrega nem peso.

Quando a FAR fornece um número de última milha, o Peek consulta a transportadora correspondente: o correio do país onde pesquisas se for um número postal (duas letras, nove dígitos, duas letras), como os [Correos](carrier:correos-spain) em Espanha, ou a transportadora única daquele formato. Assim que essa transportadora mostrar um registo pelo menos tão recente como o último da FAR, o Peek passa a segui-la mantendo o histórico da FAR e indica quem entrega (por exemplo, «Entrega: Correos»). Sem essa transportadora, mantém-se no seguimento da FAR.

Enquanto tiveres o Peek aberto no ecrã — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a FAR até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6h locais no dia em que sai para distribuição; fora disso, e após dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora. Só através de serviços de seguimento verifica de hora em hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega se tiveres a encomenda aberta no ecrã ou as notificações ligadas. Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a FAR nem o vendedor por ti.

## Perguntas sobre a FAR International

### A FAR International é de confiança?

Sim. A FAR International Logistics foi criada em Hangzhou em 2004. A casa-mãe, FAR International Holdings Group, foi fundada em novembro de 2022 e, segundo notícias da FAR, entrou na Bolsa de Hong Kong em dezembro de 2023. A FAR afirma ter-se tornado prestadora da UPS China em 2005, lançado um projeto transfronteiriço com a Temu em 2023 e iniciado cooperação estratégica com a TikTok e a SHEIN em 2024.

### Quanto tempo demora a FAR International a entregar?

Aos seus clientes indica estes prazos: Express 3 a 5 dias úteis, Standard 6 a 9 e Economy 10 a 30 dias úteis, variáveis consoante o código postal de destino. Guia-te pela estimativa dada pela loja.

### O que significa «Await Release from Sender»?

O armazém da FAR na China já tem a encomenda e aguarda confirmação do vendedor para a libertar. Se o estado não mudar, pergunta à loja.

### Como contacto a FAR International?

Pela linha global 400-128-5656 ou por e-mail para csc@far800.com. Sobre a tua compra, fala primeiro com o vendedor.

:::sources
- [FAR International: Home](https://www.far800.com/en) – empresa, 泛远国际, 2004 e 2022, entrada em bolsa em Hong Kong (22 de dezembro de 2023), linha global, e-mail, morada
- [FAR International: Track and Trace](https://www.far800.com/en/logistics) – código completo e até 50 números um por linha
- [FAR International: About FAR](https://www.far800.com/en/about) – 2004, 2022, prestadora UPS China em 2005, Temu 2023, TikTok e SHEIN 2024
- [FAR International: Cross-border End-to-end Delivery](https://www.far800.com/en/service/info) – modelo light asset, fornecedores para alfândega e última milha, inspeção na recolha, prazos Express/Standard/Economy, linha 24/7
- [FAR International: FAQs](https://www.far800.com/en/service/qa) – atrasos de voo, RETURN, entregue mas não recebida (14 dias), danos, alfândega no destino
- [FAR International: Privacy Policy](https://www.far800.com/en/support/privacy) – denominação social, telefone, e-mail, morada
- [Comissão Europeia: reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito de 3 € desde 1 de julho de 2026 e taxa de tratamento de 2 € desde 1 de novembro de 2026
- [Comissão Europeia: guia sobre o direito de 3 € e taxa de tratamento (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artigo qualquer que seja o valor e definição de artigo
- [Universal Parcel Scraper: adaptador FAR International](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/far-international) – formato FAR…YQ, ligações, textos, número de última milha, fusos horários e passagem de entrega
:::
