---
title: Seguimento Emirates Post: números AE e estados
description: Segue um envio Emirates Post dos ou nos EAU: formato AE, estados explicados, alfândega UE e entrega pelo correio do teu país.
slug: seguimento-emirates-post
published: 2026-11-22
updated: 2026-11-22
---

A Emirates Post (بريد الإمارات) é o serviço postal dos Emirados Árabes Unidos; o catálogo do Peek também a conhece como Emirates Post Group. Os seus códigos postais terminam em `AE` e o seguimento indica o local da maioria das etapas, nos EAU e, para envios internacionais, também no país de destino. Cola o número na caixa acima ou consulta o seguimento próprio em emiratespost.ae. Como o Peek não conseguiu ler o site da Emirates Post para esta página, o que se segue baseia-se no que o código do Peek conhece do seu seguimento.

## Número de seguimento Emirates Post

Um número postal da Emirates Post tem 13 caracteres no [formato postal internacional](guide:tracking-number-formats): duas letras, nove dígitos e `AE`, o código dos EAU. O nono dígito é um dígito de controlo. Com dígitos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `EE482910372AE` | Começa por C, E, L ou R e termina em `AE`, com dígito de controlo válido: o Peek identifica logo a Emirates Post. |
| `UA482910372AE` | Termina em `AE` mas começa por outra letra. A forma por si só não chega, pelo que o Peek mostra primeiro «Operador postal desconhecido» (ver abaixo). |
| `EE482910371AE` | A primeira forma com dígito de controlo errado. O Peek não reconhece nenhuma transportadora: verifica se há gralhas. |
| `7394028156342` | 13 dígitos. O Peek pode consultá-los na Emirates Post, mas outras transportadoras também usam 13 dígitos, pelo que deves escolher Emirates Post manualmente. |
| `RR482910372CN` | Termina noutras letras, aqui `CN`: emitido por outro serviço postal, aqui a [China Post](carrier:china-post). |

Espaços e minúsculas não interessam ao colar o código no Peek. Não encontras o teu? Vê [como encontrar um número de seguimento](guide:find-tracking-number).

## Estados de seguimento Emirates Post explicados

O seguimento da Emirates Post dá data, hora e, quase sempre, local a cada passo. Eis os passos que o Peek conhece, pela ordem habitual:

| A Emirates Post indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Yet to be received` | A Emirates Post tem os dados do envio, mas ainda ninguém o entregou ao balcão. Pode aparecer dias antes da expedição. | «Etiqueta criada» |
| `Received at counter` | Entregue ao balcão de uma estação Emirates Post. | «Aceite pela transportadora» |
| `Received - shipment under processing`, `Shipment under Processing` | Em tratamento após a entrega. | «Em trânsito» |
| `Item into sorting centre` | Num centro de triagem. | «Em trânsito» |
| `Shipped to` | A caminho do local indicado a seguir: estação seguinte, cidade de destino no estrangeiro ou etapa seguinte após a alfândega de importação. | «Em trânsito» |
| `Arrived at` | Chegou ao local indicado a seguir: centro de distribuição ou país de destino em envios internacionais. | «Em trânsito» |
| `Item presented to export Customs/Security` | Em controlo aduaneiro ou de segurança de exportação antes de sair. | «Na alfândega» |
| `Item returned from export Customs/Security` | Libertado: de novo a caminho. | «Em trânsito» |
| `Item presented to import Customs` | Na alfândega do país de destino. | «Na alfândega» |
| `Import terminated` | O país de destino interrompeu a importação. Ainda podem surgir etapas posteriores. | «Problema reportado» |
| `Item held at delivery depot` | No depósito de distribuição do país de destino. Não significa ainda que possas levantar. | «Em trânsito» |
| `Ready for collection by customer` | À espera para levantamento. | «Pronta para levantamento» |
| `Out for delivery` | Em distribuição. | «Em distribuição» |
| `Delivered` | Entregue. | «Entregue» |

O Peek lê qualquer outra etapa pelo seu texto e apresenta-a nas palavras da Emirates Post. Mais em [o que significam os estados de seguimento](guide:tracking-statuses).

## Envios Emirates Post para o estrangeiro: entrega e alfândega

Assim que sai dos EAU, `Shipped to` indica o destino. O seguimento da Emirates Post pode continuar com etapas no país de destino: chegada, alfândega de importação e depósito de distribuição. Em muitos países, o Peek consulta também o teu correio nacional com o mesmo código `AE`, como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) ou [Australia Post](carrier:australia-post) (ver última secção). As tentativas de entrega e onde fica uma encomenda não entregue seguem então as regras do operador local: vê [entrega falhada](guide:missed-delivery) e [encomendas não levantadas](guide:uncollected-parcel).

**Alfândega da UE.** Os EAU ficam fora da UE, logo a encomenda passa pela alfândega no teu país. Desde 1 de julho de 2026, compras online fora da UE em encomendas até 150 € têm um direito aduaneiro da UE de 3 € por artigo. Desde 1 de novembro de 2026 aplica-se também uma taxa de tratamento da União de 2 € por artigo, qualquer que seja o valor. Um artigo corresponde aos bens da encomenda com igual classificação pautal, descrição e, quando exigida, origem. O Peek não conseguiu confirmar como estes valores são cobrados num envio Emirates Post: pergunta à loja se estavam incluídos no preço. Fora da UE aplicam-se as regras do teu país: vê [encomendas retidas na alfândega](guide:customs).

## Receber uma encomenda Emirates Post nos EAU

As etapas indicam a estação que tem o envio, como um centro de triagem ou de distribuição. Quando aguarda levantamento, surge `Ready for collection by customer`. Como o Peek não pôde consultar o site da Emirates Post nem regras aduaneiras dos EAU numa página oficial, esta página não indica prazo de guarda, regras de entrega nem taxas aduaneiras.

**Do estrangeiro.** Uma encomenda enviada para os EAU leva o número do correio de origem. O Peek segue-a nesse correio ou em serviços de seguimento e não a transfere automaticamente para a Emirates Post: só consulta a Emirates Post se a escolheres em «Alterar».

## Seguimento parado, encomenda perdida ou devolvida

**Sem novidades após sair dos EAU.** O voo e a alfândega levam tempo. Após quatro dias sem novo registo em trânsito, fora da alfândega ou de um problema reportado, o Peek mostra «Sem atualizações há quatro dias» (ou «Sem atualizações desde …»). Consulta também o correio do teu país com o mesmo número; o Peek fá-lo por ti onde consegue. Mais em [porque o seguimento para de atualizar](guide:tracking-not-updating).

**Entregue, mas não chegou nada.** Contacta quem entregou no teu país e avisa o remetente. Vê [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** Fala primeiro com o remetente ou a loja, que contratou a entrega. Vê [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto Emirates Post

O site da Emirates Post responde às verificações automáticas com uma validação de navegador, pelo que esta página não indica telefone, e-mail nem horários: o Peek só apresenta contactos lidos nas páginas oficiais de uma transportadora.

Para uma encomenda de loja online, contacta primeiro a loja. Quando o correio do teu país já tiver o volume, pergunta-lhe diretamente pela entrega.

## Seguir uma encomenda Emirates Post com o Peek

O [Peek](/) associa de imediato a Emirates Post a duas letras, nove dígitos e `AE` começados por C, E, L ou R com dígito de controlo válido, mostrando «Transportadora detetada»; «Alterar» escolhe outra. Se o dígito de controlo falhar, avisa «O dígito de controlo deste número não bate certo.»: confirma o código ou escolhe «Seguir tal como está». Uma ligação para o seguimento da Emirates Post também funciona: o Peek retira daí o número. Registada na Emirates Post, a encomenda mostra «À espera de Emirates Post» até surgir no portal.

Um número `AE` iniciado por outra letra, ou de 13 dígitos, não chega pela forma para identificar a Emirates Post. Cola-o mesmo assim: o Peek segue mais de 4500 transportadoras. Para esse `AE`, o Peek mostra «Operador postal desconhecido» e «A verificar serviços de seguimento…»; se nenhum serviço tiver a encomenda, pergunta à Emirates Post e mostra «Emirates Post tem esta encomenda.» se aparecer lá. Se um serviço a encontrar, o Peek segue-a aí; nos primeiros 30 dias, novas verificações podem ainda consultar a Emirates Post e passar a encomenda para lá assim que a Emirates Post mostrar movimento. Para 13 dígitos, ou para ir direto à Emirates Post, escolhe-a em «Alterar» (ou «Alterar transportadora»).

Como o portal da Emirates Post exige verificação de navegador, o Peek lê-o através de um navegador. Mostra cada passo no texto inglês da Emirates Post (ou em árabe se não houver inglês) com o local. Descarta dados de remetente e destinatário, contactos, referências de seguimento e ligações dos eventos; o portal não fornece data prevista de entrega nem peso. Etapas no Dubai, Abu Dhabi, Sharjah, Ajman, Fujairah, Umm Al Quwain ou Ras Al Khaimah são lidas na hora dos EAU, e etapas noutros locais no relógio local do lugar indicado ou das etapas próximas (o do destino após a chegada), convertendo tudo para o teu fuso horário («Horas no teu fuso horário:»). «Abrir o site de Emirates Post» abre o número diretamente lá. Quando o portal não responde, o Peek pergunta por ordem a ParcelsApp, Ship24 e 17TRACK e, para códigos `AE`, por último ao seguimento da União Postal Universal.

Quando as etapas da Emirates Post indicam o país de destino — ou não indicam nenhum e acompanhas fora dos EAU — o Peek consulta também o correio desse país com o mesmo `AE`: [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post), [Australia Post](carrier:australia-post), NZ Post, [DHL](carrier:dhl) na Alemanha, [La Poste](carrier:la-poste) em França, os correios de todos os outros países da UE e os da Suíça, Noruega, Arábia Saudita, Paquistão, Filipinas, Egito, Jordânia, Quénia e Bangladesh, entre outros. Quando esse correio apresenta um registo pelo menos tão recente quanto o último da Emirates Post, o Peek acompanha-a lá, mantém o histórico da Emirates Post e mostra, por exemplo, «Entrega: USPS». Nalguns países como Índia, Sri Lanka, China, Omã, Kuwait ou Barém o Peek não conhece correio a consultar e mantém-se na Emirates Post. Numa encomenda ainda em «Operador postal desconhecido», este passo só ocorre se um serviço de seguimento indicar o país de destino.

O Peek verifica até de 10 em 10 minutos, e até de 5 em 5 minutos a partir das 6:00 no relógio do último registo no dia em que entra «Em distribuição» enquanto tiveres a encomenda aberta no Peek ou as notificações ligadas, quando o seguimento da Emirates Post responde (cada leitura abre um navegador). Após a entrega ao correio do teu país, segue o ritmo desse correio. Verifica de hora a hora após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»). Nas notificações podes escolher «Todas as atualizações», «Só as importantes» ou «Só no dia da entrega». Ao fim de quatro dias sem registo em trânsito, fora da alfândega ou de um problema reportado, o Peek mostra «Sem atualizações desde …», a nota «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Emirates Post.» e «Pede ajuda a Emirates Post». O Peek não pode contactar a Emirates Post, o teu correio nem a loja por ti.

## Perguntas sobre o seguimento Emirates Post

### Como é um número de seguimento Emirates Post?

Duas letras, nove dígitos e `AE`, como `EE482910372AE`. O Peek identifica logo a Emirates Post quando começa por C, E, L ou R e o dígito de controlo bate certo.

### Porque o meu seguimento Emirates Post fica em «Yet to be received»?

A Emirates Post recebeu os dados do envio, mas o objeto ainda não foi entregue ao balcão: esta linha pode aparecer dias antes da expedição. Pergunta ao remetente quando vai enviar.

### O que significa «Import terminated» na Emirates Post?

O país de destino interrompeu a importação do envio. O Peek mostra-o como «Problema reportado». Ainda podem surgir etapas posteriores: continua a acompanhar e pergunta ao remetente ou à loja o que aconteceu.

### Posso seguir uma encomenda Emirates Post no correio do meu país?

Em muitos países sim, com o mesmo código `AE`. Numa encomenda acompanhada na Emirates Post, o Peek pergunta por ti ao correio dos EUA, Reino Unido, Irlanda, Canadá, Austrália, todos os países da UE e vários outros, mudando para lá assim que surge um novo registo.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – direito aduaneiro UE de 3 € por artigo até 150 € desde 1 de julho de 2026, taxa de tratamento da União de 2 € desde 1 de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por artigo qualquer que seja o valor, o que é um artigo
- [Universal Parcel Scraper: Emirates Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/emirates-post) – nomes, formatos e dígito de controlo, números de 13 dígitos, portal lido via navegador, estados e etapas, locais, relógios, destino, campos descartados, ligações de seguimento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números China Post, transportadoras consultadas para outros `AE`, ordem dos serviços de seguimento, passagem para o correio de destino, sem entrega à Emirates Post
:::
