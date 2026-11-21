---
title: Seguimiento de un pedido de Amazon: dónde está el número
description: Dónde muestra Amazon el transportista y el número de seguimiento de tu paquete, y cómo seguirlo en Peek, a mano o dejando que lo hagan Claude o ChatGPT.
slug: seguimiento-pedido-amazon
picture: Un móvil muestra el avance de un pedido, dos furgonetas se pasan un paquete y Pip lo observa.
published: 2026-10-16
updated: 2026-11-21
---

Amazon muestra el transportista y el número de seguimiento de cada paquete en Mis pedidos, y luego en Localiza tu paquete. Copia el número en [Peek](/) y sigues el paquete junto a todos los demás. Funciona igual en todas las webs de Amazon.

## ¿Dónde está el número de seguimiento en Amazon?

:::steps
- Abre Mis pedidos | En la web, está arriba a la derecha. En la app de Amazon, toca el icono de la persona y luego Mis pedidos.
- Selecciona Localiza tu paquete | El botón está junto a cada paquete en camino o recién entregado. Si un pedido llega en varios paquetes, cada uno tiene el suyo.
- Copia el número de seguimiento | En negrita aparece el transportista, SEUR por ejemplo, y justo debajo su número de seguimiento.
- Pégalo en Peek | Peek reconoce el transportista y sigue el paquete.
:::

:::picture amazon-orders/orders-es.webp 480x300
Mis pedidos en Amazon: un pedido, con el botón Localiza tu paquete resaltado al lado. Un pedido de ejemplo, no uno real.
:::

:::picture amazon-orders/track-es.webp 480x300
La página Localiza tu paquete de Amazon: el transportista SEUR y, justo debajo, el número de seguimiento, ambos resaltados. Valores de ejemplo.
:::

En otras webs de Amazon, los mismos botones se llaman así:

| Web | Mis pedidos | Localiza tu paquete |
| --- | --- | --- |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

### ¿No hay número de seguimiento?

- **Un pedido reciente.** El número suele aparecer cuando el transportista ya tiene el paquete.
- **Lo entrega Amazon.** El pedido puede no mostrar ningún número, o uno de Amazon: dos letras del país y 10 cifras, como `ES1234567890`. Pégalo igualmente en Peek: Peek lo sigue si Amazon lo muestra en su seguimiento público, y te avisa si solo lo muestra tu cuenta de Amazon.
- **El número de pedido,** tres grupos de cifras como `123-1234567-1234567`, nunca es un número de seguimiento.

## ¿Pueden Claude o ChatGPT hacerlo por mí?

Sí, si el asistente puede usar el navegador en el que has iniciado sesión en Amazon: Claude in Chrome con un plan de pago de Claude, o la extensión de navegador de ChatGPT, que configuras desde la app de escritorio de ChatGPT. Para eso hace falta un ordenador: desde su app del móvil, o con un navegador que ejecuta en sus propios servidores, el asistente no llega a tus pedidos, y los correos de Amazon no traen el número de seguimiento.

### Sin el conector de Peek

Copia esto en el asistente:

> Abre Mis pedidos en Amazon en este navegador. Para cada paquete en camino o recién entregado, abre Localiza tu paquete y apunta el transportista y el número de seguimiento, nunca el número de pedido. Ponlos aquí, un paquete por línea, y luego dime que pegue la lista en peektracker.com. En Amazon, solo lee: no compres, canceles, devuelvas ni cambies nada, y no inicies sesión por mí.

Después pega toda la lista en [Peek](/): Peek encuentra cada número.

### Con Peek en el asistente

Conecta Peek una vez. En Claude, añade «Peek Parcel Tracker» desde el [directorio de conectores de Claude](https://claude.ai/directory/peek-parcel-tracker). En ChatGPT, añade Peek como plugin, con un plan Business, Enterprise o Edu. [Aquí tienes todos los pasos](guide:ai-assistants).

Luego pide:

> Importa mis paquetes de Amazon a Peek.

Peek le dice al asistente dónde muestra Amazon los números. El asistente añade cada paquete, con el nombre de su producto, y te dice cuáles sigue Amazon solo en tu cuenta.

En los dos casos, el asistente solo lee Amazon. Si Amazon te pide iniciar sesión, hazlo tú en esa ventana y vuelve a pedírselo.

¿Paquete con retraso, o marcado como entregado pero no ha llegado? Lee [entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

:::sources
- [Ayuda de Amazon.es: Localizar un paquete](https://www.amazon.es/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Mis pedidos, Localiza tu paquete, los números de seguimiento; la misma página en amazon.com, .co.uk, .fr, .de, .it y .pl da sus palabras
- [Ayuda de Amazon.es: Información de contacto del transportista](https://www.amazon.es/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – el nombre del transportista y el número de seguimiento en Localiza tu paquete, un transportista por paquete
- [Ayuda de Amazon.es: Información de seguimiento no disponible](https://www.amazon.es/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – sin número mientras el pedido va de camino al transportista
- [Ayuda de Amazon.es: Entregas de Amazon Logistics](https://www.amazon.es/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – los paquetes que entrega Amazon, seguidos en Mis pedidos
- [Ayuda de Amazon.es: Devolver un regalo](https://www.amazon.es/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – el número de pedido de 17 dígitos
- [About Amazon: How to track your packages (en inglés)](https://www.aboutamazon.com/news/retail/track-amazon-package) – tus pedidos arriba a la derecha, el icono de la persona en la app, Track package
- [Claude Help Center: Get started with Claude in Chrome (en inglés)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – planes de pago de Claude, el panel lateral, Claude in Chrome entre los conectores de un chat
- [ChatGPT: Browser extension (en inglés)](https://learn.chatgpt.com/docs/chrome-extension) – se configura desde la app de escritorio de ChatGPT; lee los sitios donde has iniciado sesión y también usa plugins
- [Peek: Conectar Peek a Claude, ChatGPT o Gemini](https://peektracker.com/es/assistants) – los pasos de Peek para Claude y ChatGPT
- [Peek en GitHub: las herramientas de los asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – lo que Peek le dice a un asistente sobre Amazon: Your Orders, Track package, «Delivery By», «Tracking ID», solo leer
- [Peek en GitHub: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – varios números en un texto pegado
- [Universal Parcel Scraper: datos de transportistas](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – las formas de los números de Amazon
:::
