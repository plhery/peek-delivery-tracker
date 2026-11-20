---
title: Seguir encomenda da Amazon: onde está o número de seguimento
description: Onde a Amazon.es mostra a transportadora e o número de seguimento da tua encomenda, e como a seguir no Peek, à mão ou com o Claude ou o ChatGPT a fazê-lo por ti.
slug: seguimento-encomenda-amazon
picture: Um telemóvel mostra o progresso de uma encomenda, duas carrinhas passam uma encomenda de uma para a outra e o Pip observa.
published: 2026-10-16
updated: 2026-11-20
---

Em Portugal compras na Amazon.es, que mostra a transportadora e o número de seguimento de cada encomenda em «Mis pedidos» e depois em «Localiza tu paquete». Copia o número para o [Peek](/) e segues a encomenda ao lado de todas as outras. Funciona da mesma maneira em todos os sites da Amazon.

## Onde encontro o número de seguimento na Amazon?

A Amazon.es mostra estes nomes em espanhol. Entre parênteses, o nome que vês se mudares o site para português.

:::steps
- Abre «Mis pedidos» (Os meus pedidos) | No site, está em cima à direita. Na app da Amazon, toca no ícone da pessoa e depois em «Mis pedidos».
- Seleciona «Localiza tu paquete» (Acompanhar embalagem) | O botão está ao lado de cada encomenda a caminho ou acabada de entregar. Se um pedido vier em várias encomendas, cada uma tem o seu.
- Copia o número de seguimento | A transportadora aparece a negrito, a CTT Express por exemplo, e logo abaixo o número de seguimento dela.
- Cola-o no Peek | O Peek reconhece a transportadora e segue a encomenda.
:::

:::picture amazon-orders/orders-pt.webp 480x300
«Mis pedidos» na Amazon.es: um pedido, com o botão «Localiza tu paquete» destacado ao lado. Um pedido de exemplo, não um verdadeiro.
:::

:::picture amazon-orders/track-pt.webp 480x300
A página «Localiza tu paquete» da Amazon.es: a transportadora CTT Express e, logo abaixo, o número de seguimento, ambos destacados. Valores de exemplo.
:::

Os mesmos botões, na Amazon.es e noutros sites da Amazon:

| Site | Os teus pedidos | Seguir a encomenda |
| --- | --- | --- |
| amazon.es, em espanhol | Mis pedidos | Localiza tu paquete |
| amazon.es, em português | Os meus pedidos | Acompanhar embalagem |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

### Sem número de seguimento?

- **Um pedido recente.** O número costuma aparecer quando a transportadora já tem a encomenda.
- **A própria Amazon entrega.** O pedido pode não mostrar número nenhum, ou um da Amazon: duas letras do país e 10 algarismos, como `PT1234567890`. Cola-o na mesma no Peek: o Peek segue-o se a Amazon o mostrar no seguimento público, e avisa-te se só a tua conta Amazon o mostra.
- **O número de pedido,** três grupos de algarismos como `123-1234567-1234567`, nunca é um número de seguimento.

## O Claude ou o ChatGPT podem fazê-lo por mim?

Sim, se o assistente puder usar o navegador onde tens sessão iniciada na Amazon: o Claude in Chrome num plano pago do Claude, ou a extensão de navegador do ChatGPT, que configuras a partir da app do ChatGPT para computador.

### Sem o conector do Peek

Copia isto para o assistente:

> Abre os meus pedidos na Amazon.es neste navegador (Mis pedidos ou Os meus pedidos). Para cada encomenda a caminho ou acabada de entregar, abre Localiza tu paquete (ou Acompanhar embalagem) e anota a transportadora e o número de seguimento, nunca o número de pedido. Lista-os aqui, uma encomenda por linha, e depois diz-me para colar a lista em peektracker.com. Na Amazon, só lê: não compres, não canceles, não devolvas nem alteres nada, e não inicies sessão por mim.

Depois cola a lista toda no [Peek](/): ele encontra cada número.

### Com o Peek no assistente

Liga o Peek uma vez. No Claude, adiciona «Peek Parcel Tracker» a partir do [diretório de conectores do Claude](https://claude.ai/directory/peek-parcel-tracker). No ChatGPT, adiciona o Peek como plugin, num plano Business, Enterprise ou Edu. [Tens aqui todos os passos](guide:ai-assistants).

Depois pede:

> Importa as minhas encomendas da Amazon para o Peek.

O Peek diz ao assistente onde a Amazon mostra os números. O assistente adiciona cada encomenda, com o nome do artigo, e diz-te quais é que a Amazon só segue na tua conta.

Em qualquer dos casos, o assistente só lê a Amazon. Se a Amazon te pedir para iniciar sessão, fá-lo tu nessa janela e depois volta a pedir-lhe.

Encomenda atrasada, ou dada como entregue mas não está lá? Lê [entregue mas não recebida](guide:delivered-not-received) e [encomendas perdidas](guide:lost-parcel).

:::sources
- [Ajuda da Amazon.es: Localizar un paquete](https://www.amazon.es/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) e [em português](https://www.amazon.es/-/pt/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Mis pedidos (Os meus pedidos), Localiza tu paquete (Acompanhar embalagem), os números de seguimento; a mesma página na amazon.com, .co.uk, .fr, .de, .it e .pl dá as palavras desses sites
- [Ajuda da Amazon.es: Información de contacto del transportista](https://www.amazon.es/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) e [em português](https://www.amazon.es/-/pt/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – o nome da transportadora e o número de seguimento em Localiza tu paquete, uma transportadora por encomenda
- [Ajuda da Amazon.es: Información de seguimiento no disponible](https://www.amazon.es/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) e [em português](https://www.amazon.es/-/pt/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – sem número enquanto a encomenda vai a caminho da transportadora
- [Amazon Help: About Deliveries Shipped with Amazon (em inglês)](https://www.amazon.com/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – pedidos que a própria Amazon entrega
- [Ajuda da Amazon.es: Devolver un regalo](https://www.amazon.es/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – o número de pedido de 17 algarismos
- [About Amazon: How to track your packages (em inglês)](https://www.aboutamazon.com/news/retail/track-amazon-package) – os pedidos em cima à direita no site, o ícone da pessoa na app, Track package
- [Claude Help Center: Get started with Claude in Chrome (em inglês)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – planos pagos do Claude, o painel lateral, o Claude in Chrome nos conectores de uma conversa
- [ChatGPT: Browser extension (em inglês)](https://learn.chatgpt.com/docs/chrome-extension) – configurar a partir da app do ChatGPT para computador; lê os sites onde tens sessão iniciada e também usa plugins
- [Peek: Ligar o Peek ao Claude, ao ChatGPT ou ao Gemini](https://peektracker.com/pt/assistants) – os passos do Peek para o Claude e o ChatGPT
- [Peek no GitHub: as ferramentas dos assistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – o que o Peek diz a um assistente sobre a Amazon: Your Orders, Track package, «Delivery By», «Tracking ID», só ler
- [Peek no GitHub: textos em português](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/pt.json) – vários números num só texto colado
- [Universal Parcel Scraper: dados das transportadoras](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – as formas dos números da Amazon
:::
