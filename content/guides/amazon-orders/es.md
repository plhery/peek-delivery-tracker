---
title: Seguimiento de un pedido de Amazon: dónde está el número
description: Dónde muestra Amazon el transportista y el número de seguimiento de tu paquete, en la web y en la app, y cómo seguirlo en Peek, a mano o con Claude o ChatGPT.
slug: seguimiento-pedido-amazon
picture: Un móvil muestra el avance de un pedido, dos furgonetas se pasan un paquete y Pip lo observa.
published: 2026-10-16
updated: 2026-11-20
---

Amazon muestra el transportista y el número de seguimiento de tu paquete en un solo sitio: Mis pedidos y luego Localiza tu paquete. Copia el número en [Peek](/) y sigues el paquete junto a todos los demás. Tardas un minuto, y funciona igual en todas las webs de Amazon. La excepción es un paquete que reparte la propia Amazon: normalmente solo puedes seguirlo en tu cuenta de Amazon.

> ¿Usas Claude o ChatGPT en Chrome? [Pídele que lo haga por ti](#pueden-claude-o-chatgpt-anadir-mis-paquetes-de-amazon-por-mi).

## ¿Dónde está el número de seguimiento en Amazon?

En la página de seguimiento de cada paquete, la que abre el botón Localiza tu paquete. Así llegas a ella:

:::steps
- Abre Mis pedidos | En la web, el enlace a tus pedidos está arriba a la derecha. En la app de Amazon, toca el icono de la persona, abajo, y luego Mis pedidos.
- Busca el pedido | Los más recientes salen primero. Si un pedido llega en varios paquetes, cada uno tiene su propio botón Localiza tu paquete.
- Selecciona Localiza tu paquete | En la web, está junto al pedido. En la app, toca antes el producto.
- Busca el transportista y el número | La página dice qué transportista lleva el paquete, por ejemplo Correos o SEUR, y muestra su número de seguimiento. Si no lo ves, consulta todas las actualizaciones: Amazon pone ahí el número.
- Pégalo en Peek | Peek reconoce el transportista por el número y empieza a seguir el paquete.
:::

### ¿Cuándo aparece el número?

Normalmente cuando el paquete ya ha salido. Un pedido nuevo puede no tener aún número de seguimiento: el paquete todavía va de camino al transportista, y el número aparece tras su primer escaneo. A veces sale antes. Pégalo de todos modos: Peek te enseña la primera novedad en cuanto el transportista la tenga.

Los correos de Amazon no suelen traer el número de seguimiento. El botón de seguimiento de esos correos lleva a la misma página.

### ¿Funciona igual en amazon.com, .fr, .de y otras webs de Amazon?

Sí. Todas las webs de Amazon funcionan igual. Solo cambian las palabras con el idioma de la web:

| Web | Mis pedidos | Localiza tu paquete |
| --- | --- | --- |
| amazon.es | Mis pedidos | Localiza tu paquete |
| amazon.com, amazon.co.uk | Your Orders | Track package |
| amazon.fr | Vos commandes | Suivre le colis |
| amazon.de | Meine Bestellungen | Sendung verfolgen |
| amazon.it | I miei ordini | Traccia il mio pacco |
| amazon.pl | Moje zamówienia | Śledź przesyłkę |

## ¿Qué número es el de seguimiento?

Un pedido de Amazon puede mostrar tres tipos de números. Solo el del transportista sirve siempre para seguir tu paquete fuera de Amazon.

- **El número de seguimiento del transportista.** Lo da el transportista: Correos, SEUR, GLS, CTT Express, UPS, etc. Amazon lo muestra junto al nombre del transportista. Peek lo sigue como cualquier otro paquete.
- **El número de pedido.** Tres grupos de cifras unidos por guiones, como `123-1234567-1234567`. Está en el pedido, en la factura y en los correos de Amazon. Nunca es un número de seguimiento: si lo pegas en Peek, Peek te dice «Parece un número de pedido de Amazon».
- **Un número de Amazon.** Amazon se lo da a los paquetes que reparte su propia red, y Peek lo consulta con Amazon: [mira más abajo](#puede-peek-seguir-un-numero-tba-de-amazon). Tiene esta forma, aquí con cifras inventadas:

:::anatomy TBA 123456789012
- TBA | Prefijo de Amazon. También `TBC` (Canadá) y `TBM` (México), o `TBAU` con 9 cifras (Australia)
- 123456789012 | Doce cifras
:::

En Europa, un número de Amazon también puede empezar por dos letras del país, como `ES` o `FR`, seguidas de 10 cifras. ¿No sabes cuál tienes? Escríbelo aquí:

:::checker
:::

Amazon no reparte todos sus pedidos con su propia red: muchos los entregan transportistas como Correos, SEUR o GLS. Localiza tu paquete nombra entonces a ese transportista: copia su número.

## ¿Puede Peek seguir un número TBA de Amazon?

A veces. Cuando pegas un número de Amazon, Peek lo consulta primero en el seguimiento público de Amazon:

- **Amazon lo conoce.** Es un paquete que Amazon reparte para otra empresa, con [Amazon Shipping](carrier:amazon-shipping). Peek lo sigue como cualquier otro.
- **Amazon no lo conoce.** Entonces lo más probable es que sea [Amazon Logistics](carrier:amazon-logistics) entregando tu propio pedido de Amazon, que Amazon solo muestra en tu cuenta. Peek no lo añade. Te lo dice y te ofrece «Abrir mis pedidos de Amazon», que abre tus pedidos en la web de Amazon que corresponde. Peek nunca te pide tu contraseña de Amazon.
- **Amazon no responde.** Peek te lo dice y te ofrece «Volver a comprobar».

Cuando el paquete lo reparte la propia Amazon, puede que el pedido no muestre ningún número de seguimiento. Entonces no hay nada que copiar: solo la página Localiza tu paquete de Amazon sigue ese paquete.

## ¿Pueden Claude o ChatGPT añadir mis paquetes de Amazon por mí?

Sí, si el asistente puede usar tu navegador. Estos dos pueden:

- **Claude in Chrome,** la extensión de Claude para Chrome, con los planes de pago de Claude.
- **La extensión de ChatGPT para Chrome,** que instalas y usas desde la app de escritorio de ChatGPT.

Los dos funcionan en la ventana de Chrome en la que ya has iniciado sesión en Amazon. Pídeselo y el asistente abre Mis pedidos, recorre cada página Localiza tu paquete y apunta cada transportista y cada número de seguimiento.

Lo que pasa después depende de si Peek está conectado al asistente:

- **Si Peek está conectado** y el asistente puede usarlo en ese chat, añade él mismo los paquetes a tu cuenta de Peek, con el nombre del producto. En Claude, abre «Peek Parcel Tracker» en el [directorio de conectores de Claude](https://claude.ai/directory/peek-parcel-tracker), selecciona «Connect», inicia sesión en Peek y selecciona «Permitir». En ChatGPT, Peek se puede añadir como plugin con un plan Business, Enterprise o Edu. Todos los pasos están en [sigue tus paquetes en ChatGPT o Claude](guide:ai-assistants).
- **Si no,** el asistente te pone los números en el chat. Copia toda la lista y pégala en [Peek](/): Peek encuentra cada número. Con la sesión iniciada, los sigue todos; sin sesión, de uno en uno.

Copia esto en el asistente:

> Abre Mis pedidos en Amazon en este navegador. Para cada paquete que esté en camino o se acabe de entregar, abre Localiza tu paquete y apunta el transportista y el número de seguimiento. No uses nunca el número de pedido (tres grupos de cifras unidos por guiones). Si Peek está conectado, añade cada paquete a Peek con el nombre de su producto. Si no, pon aquí los números, uno por línea. Dime qué paquetes no tienen número de seguimiento. En Amazon, solo lee: no compres, canceles, devuelvas ni cambies nada, y no inicies sesión por mí.

En los dos casos, al asistente le basta con leer Amazon. Nunca debe comprar, cancelar ni devolver nada, ni iniciar sesión por ti. Si Amazon te pide iniciar sesión, hazlo tú en esa ventana y vuelve a pedírselo. Si un paquete no tiene número de seguimiento, o Peek lo rechaza, Amazon solo lo sigue en tu cuenta.

## ¿Qué hago si mi paquete de Amazon llega tarde o sale como entregado?

Quién te ayuda depende de quién lo envió. Amazon se ocupa de sus propios pedidos y de los que envía en nombre de vendedores con Logística de Amazon. Un vendedor que envía sus propios pedidos responde primero de la entrega. Para saber si compraste a un vendedor externo, mira el nombre del vendedor en Mis pedidos.

:::steps
- Mira Localiza tu paquete | Ahí ves la última novedad. La mayoría de los paquetes con retraso llegan en las 48 horas siguientes a la fecha de entrega prevista.
- Busca alrededor | Mira en el lugar seguro de tus instrucciones de entrega, revisa la foto de entrega si el repartidor hizo una y pregunta a los vecinos.
- Pregunta al transportista | Si lo entregó otro transportista, la página Información de contacto del transportista, en la ayuda de Amazon, dice cómo contactar con él.
- Contacta con el vendedor | Si un vendedor lo vendió y lo envió, busca el pedido en Mis pedidos, selecciona Problema con el pedido y luego Ponerse en contacto con el vendedor.
- Acude a Amazon | Si lo envió Amazon, contacta con su atención al cliente: puedes pedir un reemplazo o un reembolso. Si un vendedor que envía sus propios pedidos no te lo resuelve, la Garantía de la A a la Z cubre la entrega y el estado del producto.
:::

Más ayuda: [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel) y [por qué no se actualiza el seguimiento](guide:tracking-not-updating).

:::sources
- [Ayuda de Amazon.es: Localizar un paquete](https://www.amazon.es/gp/help/customer/display.html?nodeId=GENAFPTNLHV7ZACW) – Mis pedidos, Localiza tu paquete, todas las actualizaciones con los números de seguimiento, el nombre del vendedor en Mis pedidos, la mayoría de los retrasos en 48 horas; la misma página en amazon.com, .co.uk, .fr, .de, .it y .pl da sus nombres
- [Ayuda de Amazon.es: Información de contacto del transportista](https://www.amazon.es/gp/help/customer/display.html?nodeId=GBPEXEXYULHB5WCH) – el nombre y el número de seguimiento del transportista; Correos, SEUR, GLS, CTT Express, UPS y los demás transportistas con los que Amazon entrega
- [Ayuda de Amazon.es: Información de seguimiento no disponible](https://www.amazon.es/gp/help/customer/display.html?nodeId=G6ZFEB9ZDU7QHMER) – sin número mientras el pedido va de camino al transportista, datos tras el primer escaneo; Problema con el pedido, Ponerse en contacto con el vendedor
- [Ayuda de Amazon.es: Entregas de Amazon Logistics](https://www.amazon.es/gp/help/customer/display.html?nodeId=GEW3XT9JEMBLTKRV) – seguimiento en Mis pedidos, foto de entrega, lugar seguro e instrucciones de entrega, vecinos
- [Ayuda de Amazon.es: Devolver un regalo](https://www.amazon.es/gp/help/customer/display.html?nodeId=GMTDJ6XPUMN7NWNB) – el número de pedido de 17 dígitos
- [About Amazon: How to track your packages (en inglés)](https://www.aboutamazon.com/news/retail/track-amazon-package) – tus pedidos arriba a la derecha, el icono de la persona en la app, Track package
- [About Amazon España: ¿Cómo llegan los paquetes de Amazon a su destino?](https://www.aboutamazon.es/noticias/inversiones-y-creacion-de-empleo/como-llegan-los-paquetes-de-amazon-a-su-destino) – la red de reparto de Amazon: centros logísticos y de distribución, estaciones logísticas, empresas externas de reparto
- [About Amazon España: Aumenta tus ventas con Logística de Amazon](https://www.aboutamazon.es/noticias/inversiones-y-creacion-de-empleo/aumenta-tus-ventas-con-logistica-de-amazon) – Amazon almacena, empaqueta y envía los productos de los vendedores; atención al cliente y devoluciones (2016)
- [About Amazon España: Amazon amplía la Garantía de la A a la Z en España para incluir las reclamaciones por daños materiales y personales](https://www.aboutamazon.es/noticias/politicas-publicas/amazon-amplia-la-garantia-de-la-a-a-la-z-en-espana-para-incluir-las-reclamaciones-por-danos-materiales-y-personales) – productos de vendedores, entrega y estado del producto, primero el vendedor, reembolso de Amazon
- [About Amazon España: A un 62 % de los españoles no le importaría recibir sus pedidos sin embalaje adicional](https://www.aboutamazon.es/noticias/sostenibilidad/a-un-62-de-los-espanoles-no-le-importaria-recibir-sus-pedidos-online-sin-embalaje-adicional) – problema con un pedido: atención al cliente, reemplazo o reembolso
- [About Amazon España: Carta anual a los accionistas de nuestro CEO, Andy Jassy](https://www.aboutamazon.es/noticias/noticias-de-la-compania/carta-anual-a-los-accionistas-2023-de-nuestro-ceo-andy-jassy) – Amazon Shipping, la red de entrega de Amazon para los envíos de otras empresas
- [Claude Help Center: Get started with Claude in Chrome (en inglés)](https://support.claude.com/en/articles/12012173-get-started-with-claude-in-chrome) – planes de pago de Claude, solo Chrome, instalar la extensión
- [Claude Help Center: Use Claude in Chrome safely (en inglés)](https://support.claude.com/en/articles/12902428-use-claude-in-chrome-safely) – las compras siguen siendo responsabilidad tuya; permisos por sitio
- [ChatGPT: Chrome extension (en inglés)](https://learn.chatgpt.com/docs/chrome-extension) – se configura desde la app de escritorio de ChatGPT; funciona en los sitios donde has iniciado sesión
- [Peek: Conectar Peek a Claude, ChatGPT o Gemini](https://peektracker.com/es/assistants) – los pasos de Peek para Claude y ChatGPT
- [Peek en GitHub: las herramientas de los asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/server/assistants/tools.ts) – lo que Peek le dice a un asistente sobre Amazon: Mis pedidos, Localiza tu paquete, sin número de seguimiento en los correos de Amazon, solo leer
- [Peek en GitHub: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – el aviso del número de pedido de Amazon, «Abrir mis pedidos de Amazon», «Volver a comprobar», varios números en un texto
- [Universal Parcel Scraper: datos de transportistas](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers) – formas de los números de Amazon y qué comprueba Peek
:::
