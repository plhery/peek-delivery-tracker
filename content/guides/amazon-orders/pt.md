---
title: Seguir encomenda da Amazon: onde está o número de seguimento
description: Onde a Amazon.es mostra a transportadora e o número de seguimento da tua encomenda, no site e na app, e como a seguir no Peek, à mão ou com o Claude ou o ChatGPT.
slug: seguimento-encomenda-amazon
picture: Um telemóvel mostra o progresso de uma encomenda, duas carrinhas passam uma encomenda de uma para a outra e o Pip observa.
published: 2026-10-16
updated: 2026-11-20
---

A Amazon mostra a transportadora e o número de seguimento da tua encomenda num só sítio: «Mis pedidos» e depois «Localiza tu paquete». Copia o número para o [Peek](/) e segues a encomenda ao lado de todas as outras. Demora um minuto e funciona da mesma maneira em todos os sites da Amazon. A exceção é uma encomenda que a própria Amazon entrega: normalmente só a podes seguir na tua conta Amazon.

> Usas o Claude ou o ChatGPT no Chrome? [Pede-lhe que o faça por ti](#o-claude-ou-o-chatgpt-podem-adicionar-as-minhas-encomendas-da-amazon-por-mim).

## Onde encontro o número de seguimento na Amazon?

Na página «Localiza tu paquete» de cada encomenda. Em Portugal compras na Amazon.es, que mostra estes nomes em espanhol; entre parênteses, o nome que vês se puseste o site em português. Para lá chegares:

:::steps
- Abre «Mis pedidos» (Os meus pedidos) | No site, seleciona «Pedidos», em cima à direita. Na app da Amazon, toca no ícone da pessoa, em baixo, e depois em «Mis pedidos».
- Encontra o pedido | Os pedidos mais recentes aparecem primeiro. Quando um pedido vem em várias encomendas, cada uma tem o seu próprio botão «Localiza tu paquete».
- Seleciona «Localiza tu paquete» (Acompanhar embalagem) | No site, está ao lado do pedido. Na app, toca primeiro no artigo.
- Procura a transportadora e o número | A página diz qual é a transportadora, a CTT Express ou a SEUR, por exemplo, e mostra o número de seguimento dela. Se não o vires, seleciona «todas las actualizaciones» (todas as atualizações): a Amazon põe lá o número.
- Cola-o no Peek | O Peek reconhece a transportadora pelo número e começa a seguir a encomenda.
:::

### Quando aparece o número?

Normalmente, quando a encomenda já foi enviada. Um pedido recente pode ainda não ter número de seguimento: a encomenda ainda vai a caminho da transportadora, e o número aparece quando ela a lê pela primeira vez. Às vezes aparece antes disso. Cola-o na mesma, e o Peek mostra a primeira atualização assim que a transportadora a tiver.

Os e-mails da Amazon normalmente não trazem o número de seguimento. O botão para seguir a encomenda nesses e-mails leva à mesma página.

### Funciona em português e noutros sites da Amazon?

Sim. Todos os sites da Amazon funcionam da mesma maneira. Só mudam as palavras, com a língua do site. A Amazon.es também pode estar em português: escolhe-o nas definições de idioma.

| Site | Os teus pedidos | Seguir a encomenda |
| --- | --- | --- |
| amazon.es, em espanhol | Mis pedidos | Localiza tu paquete |
| amazon.es, em português | Os meus pedidos | Acompanhar embalagem |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

## Qual é o número de seguimento?

Um pedido da Amazon pode mostrar três tipos de números. Só o número de seguimento da transportadora segue sempre a tua encomenda fora da Amazon.

- **O número de seguimento da transportadora.** Vem da transportadora: CTT Express, SEUR, GLS, Correos Express e outras. A Amazon mostra-o ao lado do nome da transportadora; com o site em português, chama-lhe número de referência. O Peek segue-o como qualquer outra encomenda.
- **O número de pedido.** Três grupos de algarismos separados por hífenes, como `123-1234567-1234567`. Está no pedido, na fatura e nos e-mails da Amazon. Nunca é um número de seguimento: se o colares no Peek, o Peek diz «Parece um número de pedido da Amazon».
- **Um número da Amazon.** A Amazon dá um às encomendas que a sua própria rede entrega, e o Peek verifica-o junto da Amazon: [vê mais abaixo](#o-peek-consegue-seguir-um-numero-tba-da-amazon). É assim, aqui com algarismos inventados:

:::anatomy TBA 123456789012
- TBA | Prefixo da Amazon. Também `TBC` (Canadá) e `TBM` (México), ou `TBAU` com 9 algarismos (Austrália)
- 123456789012 | Doze algarismos
:::

Na Europa, um número da Amazon também pode ter duas letras do país, como `PT` ou `ES`, seguidas de 10 algarismos. Não sabes qual tens? Escreve-o aqui:

:::checker
:::

Uma encomenda pode mudar de transportadora pelo caminho: a Amazon passa algumas a um parceiro para a última etapa. Nesse caso, «Localiza tu paquete» mostra esse parceiro: copia o número dele.

## O Peek consegue seguir um número TBA da Amazon?

Às vezes. Quando colas um número da Amazon, o Peek verifica-o primeiro no seguimento público da Amazon:

- **A Amazon conhece-o.** É uma encomenda que a Amazon entrega para outra empresa, com a [Amazon Shipping](carrier:amazon-shipping). O Peek segue-a como qualquer outra.
- **A Amazon não o conhece.** Então é muito provavelmente a [Amazon Logistics](carrier:amazon-logistics) a entregar um pedido que fizeste na Amazon, e a Amazon só o mostra na tua conta. O Peek não o adiciona. Diz-te isso e propõe «Abrir os meus pedidos Amazon», que abre os teus pedidos no site da Amazon que corresponde ao número: a Amazon.es para `PT` e `ES`. O Peek nunca te pede a palavra-passe da Amazon.
- **Não é possível contactar a Amazon.** O Peek diz-te isso e propõe «Tentar novamente».

Quando é a própria Amazon a entregar, o pedido pode não mostrar número de seguimento nenhum. Aí não há nada para copiar: só a página «Localiza tu paquete» da Amazon segue essa encomenda.

## O Claude ou o ChatGPT podem adicionar as minhas encomendas da Amazon por mim?

Sim, se o assistente puder usar o teu navegador. Estes dois podem:

- **O Claude in Chrome,** a extensão do Claude para o Chrome, nos planos pagos do Claude.
- **A extensão do ChatGPT para o Chrome,** que instalas e usas a partir da app do ChatGPT para computador.

Os dois funcionam na janela do Chrome onde já tens sessão iniciada na Amazon. Basta pedires, e o assistente abre «Mis pedidos», percorre cada página «Localiza tu paquete» e anota cada transportadora e número de seguimento.

O que acontece a seguir depende de o Peek estar ligado ao assistente:

- **Se o Peek estiver ligado** e o assistente o puder usar nessa conversa, é ele que adiciona as encomendas à tua conta Peek, com o nome do artigo. No Claude, abre «Peek Parcel Tracker» no [diretório de conectores do Claude](https://claude.ai/directory/peek-parcel-tracker), seleciona «Connect», inicia sessão no Peek e seleciona «Permitir». No ChatGPT, podes adicionar o Peek como plugin num plano Business, Enterprise ou Edu. Tens todos os passos em [segue as tuas encomendas no ChatGPT ou no Claude](guide:ai-assistants).
- **Se não estiver,** o assistente lista os números na conversa. Copia a lista toda e cola-a no [Peek](/): o Peek encontra cada número. Com sessão iniciada, segue-os todos; sem sessão, um de cada vez.

Copia isto para o assistente:

> Abre os meus pedidos na Amazon.es neste navegador (Mis pedidos ou Os meus pedidos). Para cada encomenda a caminho ou acabada de entregar, abre Localiza tu paquete (ou Acompanhar embalagem) e anota a transportadora e o número de seguimento. Nunca uses o número de pedido (três grupos de algarismos separados por hífenes). Se o Peek estiver ligado, adiciona cada encomenda ao Peek, com o nome do artigo. Se não, lista aqui os números, um por linha. Diz-me que encomendas não mostram número de seguimento. Na Amazon, só lê: não compres, não canceles, não devolvas nem alteres nada, e não inicies sessão por mim.

Seja como for, o assistente só precisa de ler o que a Amazon mostra. Nunca deve comprar, cancelar ou devolver nada, nem iniciar sessão por ti. Se a Amazon te pedir para iniciares sessão, fá-lo tu nessa janela e volta a pedir. Quando uma encomenda não tem número de seguimento, ou o Peek a recusa, a Amazon só a segue na tua conta.

## E se a encomenda da Amazon estiver atrasada ou aparecer como entregue?

Quem te ajuda depende de quem a enviou. A página do produto diz quem envia, por baixo dos botões de compra. A Amazon trata dos seus próprios pedidos e dos que envia por conta dos vendedores. Um vendedor que envia ele próprio as encomendas responde primeiro às perguntas sobre a entrega.

:::steps
- Vê «Localiza tu paquete» | Mostra a última atualização e a data prevista de entrega.
- Procura à volta | Vê o sítio que indicaste nas instruções de entrega e a fotografia da entrega, se o estafeta tirou uma. Se escolheste um cacifo da Amazon ou um ponto da rede CTT, vê se a encomenda já lá está.
- Pergunta à transportadora | Se foi outra transportadora a entregar, a página «Información de contacto del transportista» (Informações de contacto das transportadoras) da Amazon diz como a contactar.
- Contacta o vendedor | Se foi um vendedor a vender e a enviar, abre o pedido em «Mis pedidos», seleciona «Problema con el pedido» (Problema com o pedido) e depois «Ponerse en contacto con el vendedor» (Contactar o vendedor).
- Fala com a Amazon | Nos pedidos enviados pela Amazon, contacta o apoio ao cliente da Amazon, que te atende em português por telefone e e-mail. Se um vendedor que envia ele próprio não te resolver o problema, a Garantia de A a Z cobre os atrasos na entrega e o estado do artigo.
:::

Mais ajuda: [entregue mas não recebida](guide:delivered-not-received), [encomendas perdidas](guide:lost-parcel) e [porque é que o seguimento deixa de atualizar](guide:tracking-not-updating).

:::sources
- [Ajuda da Amazon.es: Localizar un paquete](https://www.amazon.es/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) e [em português](https://www.amazon.es/-/pt/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Mis pedidos (Os meus pedidos), Localiza tu paquete (Acompanhar embalagem), todas as atualizações com os números de seguimento (números de referência), data prevista; Pedidos em cima à direita
- [Ajuda da Amazon.es: Información de contacto del transportista](https://www.amazon.es/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) e [em português](https://www.amazon.es/-/pt/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – o nome e o número de seguimento da transportadora em Mis pedidos; CTT Express, SEUR, GLS, Correos Express e outras
- [Ajuda da Amazon.es: Información de seguimiento no disponible](https://www.amazon.es/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) e [em português](https://www.amazon.es/-/pt/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – sem número enquanto a encomenda vai a caminho da transportadora, detalhes depois da primeira leitura; Problema con el pedido (Problema com o pedido), Ponerse en contacto con el vendedor (Contactar o vendedor)
- [Ajuda da Amazon.es: Entregas de Amazon Logistics](https://www.amazon.es/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – encomendas da Amazon Logistics seguidas em Mis pedidos
- [Amazon Help: About Deliveries Shipped with Amazon (em inglês)](https://www.amazon.com/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – pedidos entregues pela Amazon Logistics, indicados como enviados pela Amazon
- [Ajuda da Amazon.es: Devolver un regalo](https://www.amazon.es/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – o número de pedido de 17 algarismos
- [About Amazon (Europe): Amazon Prime now available to customers in Portugal (em inglês)](https://www.aboutamazon.eu/news/press-lounge/amazon-prime-now-available-to-customers-in-portugal) – clientes em Portugal na Amazon.es, site em português, apoio ao cliente por telefone e e-mail em português
- [About Amazon: How to track your packages (em inglês)](https://www.aboutamazon.com/news/retail/track-amazon-package) – o ícone da pessoa na app, Your Orders, Track package
- [About Amazon: 10 tips to protect your Amazon packages from porch pirates (em inglês)](https://www.aboutamazon.com/news/operations/amazon-porch-pirate-prevention-tips) – instruções de entrega, fotografias da entrega
- [About Amazon UK: How to change your delivery address on Amazon (em inglês)](https://www.aboutamazon.co.uk/news/retail/how-to-change-your-delivery-address-on-amazon) – quem vende e envia, por baixo do botão de compra; contactos das transportadoras
- [About Amazon: How do Amazon packages get delivered (em inglês)](https://www.aboutamazon.com/news/operations/how-do-amazon-packages-get-delivered) – centros de triagem, parceiros que fazem a última etapa
- [CTT: Os clientes Amazon podem agora recolher as suas encomendas em toda a rede dos CTT](https://www.ctt.pt/grupo-ctt/media/noticias/os-clientes-amazon-podem-agora-recolher-as-suas-encomendas-utilizando-toda-a-rede-dos-ctt-em-portugal) – levantar encomendas da Amazon na rede CTT, além de casa e dos cacifos da Amazon
- [About Amazon España: Diez cosas de Amazon que no sabías, mejoran tu experiencia de compra y te ayudan a ahorrar cada día](https://www.aboutamazon.es/noticias/noticias-de-la-compania/diez-cosas-amazon-mejoran-tu-experiencia-compra-ahorrar-cada-dia) – Garantia de A a Z para produtos de vendedores externos: entrega e estado do produto, reclamação diretamente à Amazon
- [About Amazon España: Amazon amplía la Garantía de la A a la Z en España para incluir las reclamaciones por daños materiales y personales](https://www.aboutamazon.es/noticias/politicas-publicas/amazon-amplia-la-garantia-de-la-a-a-la-z-en-espana-para-incluir-las-reclamaciones-por-danos-materiales-y-personales) – primeiro o vendedor, depois o reembolso da Amazon
- [About Amazon: Amazon CEO Andy Jassy's 2023 Letter to Shareholders (em inglês)](https://www.aboutamazon.co.uk/news/company-news/ceo-andy-jassys-2023-letter-to-shareholders) – Amazon Shipping, a rede de entregas da Amazon para encomendas de outras empresas
- [Claude Help Center: Get started with Claude in Chrome (em inglês)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – planos pagos do Claude, só no Chrome, instalar a extensão
- [Claude Help Center: Use Claude in Chrome safely (em inglês)](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – continuas responsável pelas compras; permissões por site
- [ChatGPT: Chrome extension (em inglês)](https://learn.chatgpt.com/docs/chrome-extension) – configurar a partir da app do ChatGPT para computador; funciona nos sites onde tens sessão iniciada
- [Peek: Ligar o Peek ao Claude, ao ChatGPT ou ao Gemini](https://peektracker.com/pt/assistants) – os passos do Peek para o Claude e o ChatGPT
- [Peek no GitHub: as ferramentas dos assistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – o que o Peek diz a um assistente sobre a Amazon: Your Orders, Track package, sem número de seguimento nos e-mails da Amazon, só ler
- [Peek no GitHub: textos em português](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pt.json) – a nota do número de pedido da Amazon, «Abrir os meus pedidos Amazon», «Tentar novamente», vários números num só texto
- [Universal Parcel Scraper: dados das transportadoras](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formas dos números da Amazon, `PT` e `ES` na Amazon.es, o que o Peek verifica
:::
