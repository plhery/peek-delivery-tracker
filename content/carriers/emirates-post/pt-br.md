---
title: Rastreamento Emirates Post: códigos AE e status explicados
description: Rastreie um objeto da Emirates Post dos Emirados Árabes Unidos: formato do código AE, significado de cada status e acompanhamento até o correio do seu país.
slug: rastreamento-emirates-post
published: 2026-11-26
updated: 2026-11-26
---

A Emirates Post (بريد الإمارات) é o serviço postal dos Emirados Árabes Unidos; o catálogo do Peek também a conhece como Emirates Post Group. Seus números postais terminam em `AE`, e seu rastreador informa o local da maioria das etapas — nos Emirados Árabes Unidos e, em objetos enviados ao exterior, também no país de destino. Cole o código no campo acima ou consulte no próprio rastreador da Emirates Post em emiratespost.ae. O Peek não conseguiu consultar o site da Emirates Post para esta página, então o conteúdo abaixo vem do que o código do Peek sabe sobre o rastreador dela.

## Código de rastreamento Emirates Post: como ele é

Um número postal da Emirates Post tem 13 caracteres no [formato postal internacional](guide:tracking-number-formats): duas letras, nove dígitos e `AE`, o código dos Emirados Árabes Unidos. O último dos nove dígitos é um dígito verificador. Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `EE482910372AE` | Começa com C, E, L ou R e termina em `AE`, com dígito verificador correto: o Peek identifica Emirates Post na hora. |
| `UA482910372AE` | Termina em `AE`, mas começa com outra letra. O formato sozinho não confirma que seja da Emirates Post, então o Peek mostra inicialmente "Operador postal desconhecido" (veja abaixo). |
| `EE482910371AE` | O primeiro formato com dígito verificador incorreto. O Peek não reconhece transportadora: confira se há erro de digitação. |
| `7394028156342` | 13 dígitos. O Peek pode consultá-los no rastreador da Emirates Post, mas outras transportadoras também usam 13 dígitos, então escolha Emirates Post manualmente. |
| `RR482910372CN` | Termina em outras letras, aqui `CN`: emitido por outro correio, aqui a [China Post](carrier:china-post). |

Espaços e letras minúsculas não importam ao colar o código no Peek. Não encontra o seu? Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

## Status de rastreamento da Emirates Post explicados

O rastreador da Emirates Post dá data, hora e, na maioria das vezes, local para cada etapa. As etapas que o Peek conhece, aproximadamente em ordem:

| A Emirates Post diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Yet to be received` | A Emirates Post tem os dados do objeto, mas ninguém o entregou ainda. Pode aparecer dias antes da postagem. | "Etiqueta criada" |
| `Received at counter` | Postado em uma agência da Emirates Post. | "Recebida pela transportadora" |
| `Received - shipment under processing`, `Shipment under Processing` | Em processamento após a postagem. | "Em trânsito" |
| `Item into sorting centre` | Em um centro de triagem. | "Em trânsito" |
| `Shipped to` | A caminho do local citado em seguida: próxima unidade, cidade de destino para envios ao exterior ou próxima unidade após a alfândega de importação. | "Em trânsito" |
| `Arrived at` | Chegou ao local citado em seguida: um centro de entrega ou o país de destino em envios ao exterior. | "Em trânsito" |
| `Item presented to export Customs/Security` | Na alfândega de exportação ou inspeção de segurança antes de sair. | "Na alfândega" |
| `Item returned from export Customs/Security` | Liberado: novamente em movimento. | "Em trânsito" |
| `Item presented to import Customs` | Na alfândega do país de destino. | "Na alfândega" |
| `Import terminated` | O destino interrompeu a importação. Etapas posteriores ainda podem aparecer. | "Precisa de atenção" |
| `Item held at delivery depot` | O depósito de entrega do país de destino está com o objeto. A linha não significa que já pode ser retirado. | "Em trânsito" |
| `Ready for collection by customer` | Aguardando você ir retirar. | "Pronta para retirada" |
| `Out for delivery` | Saiu para entrega. | "Saiu para entrega" |
| `Delivered` | Entregue. | "Entregue" |

O Peek interpreta qualquer outra etapa pelo texto e a exibe nas palavras da Emirates Post. Veja mais em [significado dos status de rastreamento](guide:tracking-statuses).

## Objetos da Emirates Post enviados ao exterior: quem entrega e alfândega

Depois que um objeto sai dos Emirados Árabes Unidos, `Shipped to` indica para onde está indo. O rastreador da Emirates Post pode continuar exibindo etapas no país de destino: chegada, alfândega de importação e depósito de entrega. Em muitos países, o Peek também consulta seu correio nacional com o mesmo código `AE`, como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) ou [Australia Post](carrier:australia-post) (veja a última seção). Tentativas de entrega e local de guarda de encomendas perdidas seguem então as regras de quem faz a entrega no seu país: veja [entrega perdida](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

**Alfândega na UE.** Os Emirados Árabes Unidos ficam fora da União Europeia, então o pacote passa pela alfândega no seu país. Desde 1º de julho de 2026, bens comprados online fora da UE em pedidos de até €150 pagam imposto aduaneiro da UE de €3 por item. Desde 1º de novembro de 2026, aplica-se também uma taxa de manuseio da União de €2 por item, independentemente do valor. Um item corresponde às mercadorias de uma encomenda que compartilham classificação tarifária, descrição e, quando exigido, origem. O Peek não pôde verificar como essas cobranças são feitas numa encomenda da Emirates Post: pergunte à loja se o preço já as incluiu. Fora da UE, valem as regras do seu país: veja [encomendas retidas na alfândega](guide:customs).

## Recebendo um objeto da Emirates Post nos Emirados Árabes Unidos

As etapas da Emirates Post indicam a unidade onde o objeto está, como um centro de triagem ou de entrega. Quando fica aguardando retirada, a etapa mostra `Ready for collection by customer`. Como o Peek não conseguiu verificar o site da Emirates Post nem regras alfandegárias dos EAU numa página oficial, esta página não informa prazo de guarda, regras de entrega nem taxas alfandegárias.

**Do exterior.** Uma encomenda postada para você nos EAU traz o código do correio de origem. O Peek a acompanha por aquele correio ou por serviços de rastreamento, e não a transfere para a Emirates Post. Ele só consulta o rastreador da Emirates Post sobre esse código se você selecionar Emirates Post em "Trocar".

## Rastreamento Emirates Post parado, extraviado ou devolvido

**Sem novidades depois de sair dos EAU.** O voo e a alfândega levam tempo. Após quatro dias sem movimentação em um objeto em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia da última etapa ("Sem atualizações desde seg 12 out") e o link "Pedir ajuda a Emirates Post". Consulte o correio do seu país com o mesmo código; o Peek faz isso por você onde é suportado. Veja mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Marcada como entregue, mas não chegou.** Pergunte a quem fez a entrega no seu país e avise o remetente. Veja [encomenda marcada como entregue mas não recebida](guide:delivered-not-received).

**Extraviada ou devolvida.** Fale primeiro com o remetente ou a loja: eles contrataram o envio. Veja [encomendas extraviadas](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da Emirates Post

O site da Emirates Post responde à verificação do Peek com um desafio de navegador, portanto esta página não lista telefone, e-mail nem horários de atendimento: o Peek só informa contatos verificados nas páginas da própria transportadora.

Em pedidos de loja virtual, fale primeiro com a loja: ela contratou a entrega. Assim que o correio do seu país estiver com o pacote, trate dúvidas de entrega com esse correio.

## Acompanhe uma encomenda Emirates Post com o Peek

O [Peek](/) identifica a Emirates Post na hora para duas letras, nove dígitos e `AE` iniciados por C, E, L ou R quando o dígito verificador está correto, exibindo "Transportadora detectada"; "Trocar" permite escolher outra. Com dígito verificador incorreto, ele diz "O dígito verificador deste código não confere.": confira o código ou escolha "Rastrear como digitado". Um link para o rastreador da Emirates Post também funciona: o Peek extrai o código dele. Classificada em Emirates Post, a encomenda mostra "Aguardando Emirates Post" até aparecer no rastreador.

Um código `AE` começado por outra letra ou um número de 13 dígitos não confirma a Emirates Post só pelo formato. Cole-o mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Para esse código `AE`, o Peek exibe "Operador postal desconhecido", consulta primeiro a [Chronopost](carrier:chronopost) (se a Chronopost o conhecer, classifica lá), depois mostra "Consultando serviços de rastreamento…" e, se nenhum serviço tiver a encomenda, consulta a Emirates Post, exibindo "Emirates Post está com esta encomenda." caso ela a encontre. Se um serviço a encontrar, o Peek acompanha por lá; nos primeiros 30 dias da encomenda, novas consultas ainda podem perguntar à Emirates Post, e o Peek transfere para lá assim que a Emirates Post mostrar movimentação. Para 13 dígitos, ou para ir direto ao rastreador da Emirates Post, escolha Emirates Post em "Trocar".

O rastreador da Emirates Post coloca uma verificação de navegador antes da página, então o Peek o lê via navegador. O Peek mostra cada etapa no texto em inglês da Emirates Post — ou em árabe quando não há versão em inglês — junto com o local. Ele omite dados de remetente e destinatário, contatos e links. O rastreador não informa data prevista de entrega nem peso, então o Peek não mostra nenhum dos dois. O Peek lê etapas em unidades de Dubai, Abu Dhabi, Sharjah, Ajman, Fujairah, Umm Al Quwain ou Ras Al Khaimah no horário dos EAU, e etapas fora no fuso do local citado ou das etapas vizinhas (o do destino assim que uma etapa indicar chegada). "Abrir o site de Emirates Post" na página da encomenda abre o código no rastreador da Emirates Post. Quando o rastreador não pode ser lido, o Peek consulta serviços de rastreamento no lugar — ParcelsApp, Ship24 e 17TRACK, sem ordem fixa, e para códigos `AE` o rastreamento da União Postal Universal por último.

Quando as etapas da Emirates Post indicam o país de destino, ou não indicam nenhum país e você rastreia de fora dos EAU, o Peek consulta também o correio daquele país com o mesmo código `AE`: USPS, Royal Mail (Parcelforce para EMS iniciado por E), An Post, Canada Post, Australia Post, NZ Post, [DHL](carrier:dhl) na Alemanha, [La Poste](carrier:la-poste) na França e os correios de todos os outros países da UE exceto os Países Baixos, além dos correios de Suíça, Noruega, Arábia Saudita, Paquistão, Filipinas, Egito, Jordânia, Quênia e Bangladesh, entre outros. Assim que esse correio mostrar uma movimentação pelo menos tão recente quanto a mais nova da Emirates Post, o Peek acompanha lá, mantém o histórico da Emirates Post e o cartão mostra, por exemplo, "Entrega por USPS". Em alguns países — como Países Baixos, Índia, Sri Lanka, China, Omã, Kuwait ou Bahrein — o Peek não consulta o correio local e permanece no rastreador da Emirates Post. Uma encomenda ainda exibida como "Operador postal desconhecido" só recebe essa transferência quando um serviço de rastreamento que a encontrou informa o país de destino: o Peek então consulta esse correio da mesma maneira.

Enquanto você mantiver a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até a cada 10 minutos, e até a cada 5 minutos a partir das 6h no relógio do local da última leitura no dia em que entra em "Saiu para entrega", enquanto o rastreador da Emirates Post responder (cada consulta abre um navegador). Depois que passa a acompanhar pelo correio do seu país, vale o ritmo daquele correio. Ele verifica a cada hora após dois dias sem novidades (12 horas enquanto a encomenda ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"); após sete dias sem novidades em "Etiqueta criada", a cada 6 horas. Você pode receber avisos para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode falar com a Emirates Post, com o seu correio nem com a loja por você.

## Perguntas sobre o rastreamento da Emirates Post

### Como é um código de rastreamento da Emirates Post?

Duas letras, nove dígitos e `AE`, como `EE482910372AE`. O Peek identifica a Emirates Post imediatamente quando o código começa com C, E, L ou R e o dígito verificador confere.

### Por que meu rastreamento da Emirates Post está parado em "Yet to be received"?

A Emirates Post recebeu os dados do objeto, mas ele ainda não foi entregue no balcão: essa etapa pode aparecer dias antes da postagem. Pergunte ao remetente quando será postado.

### O que significa "Import terminated" no rastreamento da Emirates Post?

O país de destino interrompeu a importação do objeto. O Peek exibe como "Precisa de atenção". Etapas posteriores ainda podem surgir, então continue acompanhando e pergunte ao remetente ou à loja o que aconteceu.

### Posso rastrear uma encomenda da Emirates Post no correio do meu país?

Em muitos países, sim, com o mesmo código `AE`. Para uma encomenda acompanhada no rastreador da Emirates Post, o Peek consulta seu correio por você nos EUA, Reino Unido, Irlanda, Canadá, Austrália, todos os países da UE exceto Países Baixos e alguns outros, transferindo o acompanhamento assim que esse correio mostrar uma movimentação pelo menos tão recente quanto a última da Emirates Post.

:::sources
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto aduaneiro da UE de €3 por item até €150 desde 1º de julho de 2026, taxa de manuseio da União de €2 desde 1º de novembro de 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 por item independentemente do valor, definição de item
- [Universal Parcel Scraper: Emirates Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/emirates-post) – nomes, formatos de código e dígito verificador, números de 13 dígitos, rastreador lido via navegador, textos de etapas e fases, locais, fusos horários, destino, o que fica de fora, links de rastreamento
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números da China Post, transportadoras consultadas para outros códigos `AE`, ordem dos serviços de rastreamento, transferência para o correio do seu país e ausência de transferência para a Emirates Post
:::
