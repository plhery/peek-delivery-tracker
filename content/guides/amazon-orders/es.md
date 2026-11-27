---
title: Seguimiento de un pedido de Amazon: dónde está el número
description: Dónde encontrar el número de seguimiento de Amazon en Mis pedidos y Localiza tu paquete, cómo distinguir el número de pedido y seguirlo en Peek o con IA.
slug: seguimiento-pedido-amazon
picture: Un móvil muestra el avance de un pedido, dos furgonetas se pasan un paquete y Pip lo observa.
published: 2026-10-16
updated: 2026-11-27
---

El número de seguimiento de Amazon no viene en el correo del pedido: entra en tu cuenta, abre **Mis pedidos** y pulsa **Localiza tu paquete** para ver el transportista y su número de seguimiento justo debajo. El número de pedido de 17 cifras no sirve para rastrear. Copia el número de seguimiento en [Peek](/) para verlo junto a todos tus demás envíos; si reparte Amazon Logistics, puede llevar un código propio de dos letras de país y 10 cifras.

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

### ¿Por qué no aparece el número de seguimiento en Amazon?

- **Un pedido reciente.** El número suele aparecer cuando el transportista ya tiene el paquete.
- **Lo entrega Amazon.** El pedido puede no mostrar ningún número, o uno de Amazon: dos letras del país y 10 cifras, como `ES1234567890`. Pégalo igualmente en Peek: Peek lo sigue si Amazon lo muestra en su seguimiento público, y te avisa si solo lo muestra tu cuenta de Amazon.
- **El número de pedido,** tres grupos de cifras como `123-1234567-1234567`, nunca es un número de seguimiento.

## ¿Cómo pueden Claude o ChatGPT importar mis paquetes de Amazon?

Sí, en un ordenador: el asistente tiene que controlar el navegador en el que has iniciado sesión en Amazon. Claude lo hace con Claude in Chrome, con un plan de pago de Claude; ChatGPT, desde un chat de Work en su app de escritorio, con su extensión de navegador. En el móvil, o en un chat que solo puede leer tus pestañas abiertas o que navega desde sus propios servidores, el asistente no llega a tus pedidos, y los correos de Amazon no traen el número de seguimiento.

### Con Peek en el asistente

Conecta Peek una vez. En Claude, añade «Peek Parcel Tracker» desde el [directorio de conectores de Claude](https://claude.ai/directory/peek-parcel-tracker). En ChatGPT, añade Peek como plugin, con un plan Plus, Pro, Business, Enterprise o Edu. [Aquí tienes todos los pasos](guide:ai-assistants).

Luego pide:

> Importa mis paquetes de Amazon a Peek.

Peek le dice al asistente dónde muestra Amazon los números. El asistente añade cada paquete, con el nombre de su producto, y te dice cuáles sigue Amazon solo en tu cuenta.

### Sin el conector de Peek

Copia esto en el asistente:

> Importa mis paquetes de Amazon a Peek en este navegador, como muestra https://peektracker.com/es/guides/seguimiento-pedido-amazon: copia el número de seguimiento de cada paquete desde Mis pedidos, luego Localiza tu paquete, y añádelo en peektracker.com. En Amazon, solo lee: no compres, canceles, devuelvas ni cambies nada, y no inicies sesión por mí.

Antes, inicia sesión en Peek en ese navegador: sin cuenta, Peek busca solo unos pocos paquetes al día.

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
- [ChatGPT: Browser extension (en inglés)](https://learn.chatgpt.com/docs/chrome-extension) – se configura desde la app de escritorio de ChatGPT; lee y maneja los sitios donde has iniciado sesión desde un chat de Work allí, y también usa plugins
- [ChatGPT: Browser (en inglés)](https://learn.chatgpt.com/codex/browser) – ChatGPT Work en la web y en el móvil navega en la nube, sin tus sesiones
- [Peek: Conectar Peek a Claude, ChatGPT o Gemini](https://peektracker.com/es/assistants) – los pasos de Peek para Claude y ChatGPT
- [Peek en GitHub: las herramientas de los asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – lo que Peek le dice a un asistente sobre Amazon: Your Orders, Track package, «Delivery By», «Tracking ID», solo leer
- [Peek en GitHub: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – sin cuenta, unas pocas búsquedas al día
- [Universal Parcel Scraper: datos de transportistas](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – las formas de los números de Amazon
:::
