---
title: PostNL seguimiento: números, estados y entrega en España
description: Seguimiento de PostNL desde España: cómo son los números NL y 3S, qué dicen los estados de postnl.post, quién te entrega el paquete y a quién preguntar si se para.
slug: postnl-seguimiento
published: 2026-11-01
updated: 2026-11-01
---

PostNL es el operador postal de los Países Bajos. Lleva al extranjero los paquetes y cartas de tiendas neerlandesas y, a través de Spring GDS, su filial internacional, los de vendedores online de todo el mundo. Puedes seguirlos en [postnl.post](https://postnl.post/), la web de seguimiento internacional de PostNL (en inglés), o en la casilla de arriba. En España te lo entrega un transportista local, a menudo el servicio postal. El número está en el correo de envío de la tienda o en la página de tu pedido.

## Número de seguimiento de PostNL: cómo es

Según su documentación para desarrolladores, PostNL usa dos tipos de código de barras. Con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `3SABCD0000000` | 3S, un código de cliente en letras y luego cifras: 13 caracteres. PostNL lo usa para Parcel EU, su servicio de paquetes de los Países Bajos a otros países de la UE; en España, a la península y Baleares. |
| `3SABCD000000000` | El mismo código de barras de PostNL en su forma de 15 caracteres. |
| `CW000000005NL` | Dos letras, 9 cifras y NL: el número postal internacional de PostNL. La C es un paquete con seguimiento fuera de Parcel EU: a Canarias, PostNL solo manda paquetes así. Ceuta y Melilla también quedan fuera de Parcel EU. |
| `LA000000005NL` | L: un paquete pequeño o una carta con seguimiento. `LW` es el mismo servicio. |
| `RI000000005NL` | R: una carta certificada. `RF` es el mismo servicio. |
| `UL000000005NL` | U: un paquete pequeño sin seguimiento. No cuentes con ver escaneos en España. |

La última de las nueve cifras de un número NL es un dígito de control, así que una cifra mal copiada suele invalidarlo.

postnl.post admite hasta 150 números a la vez. Responde a los números NL y a los 3S de 15 caracteres; a los de 13 contesta `The shipment barcode was incorrect.` Si el tuyo es un código Parcel EU, pide a la tienda el número del socio: al preparar la etiqueta, PostNL le da también el código de barras de la empresa que entregará el paquete en destino, y con ese código puedes seguirlo en su web. Mientras el paquete siga en los Países Bajos, ese código aún puede cambiar.

## Estados del seguimiento de PostNL: qué significa cada uno

postnl.post muestra una barra de progreso (`Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing`, `Delivered`) y, debajo, los escaneos en inglés. Casi todos indican el país donde se hicieron. Estas son las líneas que verás, más o menos en este orden:

| postnl.post dice | Qué significa para ti |
| --- | --- |
| `Pre-advised`, `Preparing` | El remitente ha registrado el envío y ha mandado los datos a PostNL. Puede que PostNL aún no tenga el paquete. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | PostNL ha recibido el envío del remitente; el escaneo de tu paquete puede llegar después. |
| `The item is received by PostNL` | PostNL ha escaneado tu paquete. Puede aparecer días después de la línea del centro de admisión. |
| `Departed`, `Transit` | Viaja de un país a otro, a menudo en avión. |
| `Arrived` | Ha llegado a un país de su ruta, normalmente España. |
| `Customs` | Está en la aduana del país de destino. |
| `The item has arrived at the domestic sorting centre`, `The item is on transport to the local sorting centre`, `The item is at the local sorting centre` | En un centro de clasificación, o de camino a uno, en el país que aparece junto al escaneo. |
| `The item is at the local delivery office` | En la oficina que te lo va a entregar. |
| `The item is out for delivery` | En reparto: está de camino. |
| `Driver is en route to the pickup location` | Va hacia un punto de recogida, no a tu puerta. |
| `Pick-up point` | Te espera en un punto de recogida. |
| `Unsuccesfull`, `Undelivered` | Ha fallado un intento de entrega. La errata es de PostNL. |
| `Undeliverable item, has been returned to shipper` | No se ha podido entregar y vuelve al remitente. |
| `The item has been returned and arrived at PostNL` | La devolución ya ha llegado a PostNL. |
| `Delivered` | Entregado. |

`The shipment barcode was not found.` quiere decir que postnl.post aún no tiene nada con ese número o que hay un carácter mal. Si la página de resultados pone `Destination: unknown`, ningún escaneo ha indicado aún el destino.

## Quién entrega los paquetes de PostNL en España

PostNL o Spring GDS llevan el paquete hasta España. Spring GDS dice que se ocupa del tramo internacional y que la entrega final la hace un socio local; al entrar en tu país, añade, el paquete pasa a menudo al servicio postal local, y cita a Correos como ejemplo. Quién te lo entrega depende del número:

- **Número NL**: sigue la norma S10 de la UPU, la numeración que comparten los operadores postales para los envíos que se intercambian. Por eso, cuando el paquete esté ya en España, puedes probar el mismo número en el localizador de [Correos](carrier:correos-spain). Un paquete pequeño `UL`, sin seguimiento, puede no aparecer.
- **Código 3S de 13 caracteres (Parcel EU)**: lo entrega el socio de PostNL en el país de destino, que PostNL no nombra. El número del socio lo tiene la tienda.

Los intentos de entrega, el aviso de llegada y los días que te guardan el paquete dependen de quien te lo entregue: mira [qué hacer si no estabas en casa](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel). Para aranceles y tasas, consulta [paquete retenido en aduanas](guide:customs).

## Paquete de PostNL parado, perdido o devuelto

**Todavía sin escaneos.** `Pre-advised` o `Preparing` quiere decir que el vendedor ha registrado el envío, pero quizá aún no lo ha entregado. Spring GDS dice que el primer escaneo puede tardar de 24 a 48 horas desde que recibes el número.

**Parado en tránsito.** Después de `Departed` o `Transit`, un paquete que viaja en avión o en barco no tiene escaneos hasta que llega, y la aduana a menudo retiene los paquetes varios días. Más en [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating).

**Entregado pero no lo tienes.** Spring GDS aconseja preguntar a los vecinos y en conserjería o recepción, si la hay, y mirar en sitios seguros como el porche o el garaje. También avisa de que a veces un transportista marca un paquete como entregado hasta 24 horas antes de que llegue de verdad. Si aun así no aparece, habla con el vendedor. Mira [paquete entregado pero no recibido](guide:delivered-not-received).

**Perdido o dañado.** Avisa enseguida al vendedor y haz fotos del embalaje dañado: el vendedor las necesita para reclamar a Spring GDS. Mira [paquete perdido](guide:lost-parcel).

**Devuelto.** Pide al vendedor que te lo vuelva a enviar o que te devuelva el dinero. Spring GDS cita como motivos habituales una dirección incorrecta y un paquete que nadie recoge en el plazo, y dice que una devolución ya iniciada no se puede anular. Mira [paquete devuelto al remitente](guide:returned-to-sender).

## PostNL: contacto y atención al cliente

Así lo indicaban sus webs el 22 de octubre de 2026:

- **postnl.post**: si tienes dudas sobre una entrega fuera de los Países Bajos, te remite a la tienda («Please contact your webshop»).
- **Spring GDS**: no puede hablar de los envíos con los consumidores, por la protección de datos y por sus contratos con los vendedores. La cuenta es del vendedor, y es él quien abre las investigaciones con Spring GDS.
- **Quien te lo entrega en España**: para un intento de entrega fallido o una nueva fecha, contacta directamente con él; Spring GDS no puede organizar nuevas entregas locales. Si es Correos, sus canales están en la página de [Correos](carrier:correos-spain).

Para todo lo demás, lo más rápido es la tienda o el marketplace donde compraste.

## Seguir un paquete de PostNL con Peek

[Peek](/) reconoce al momento los números NL con el dígito de control correcto y los códigos 3S con un código de cliente de cuatro letras. Un 3S con tres letras lo asigna a DHL eCommerce Netherlands, aunque las directrices de PostNL también muestran códigos de tres letras. Con un código de cliente más corto, Peek no puede saber el transportista por la forma: pégalo igualmente, Peek sigue más de 3500 transportistas. Mientras lo comprueba verás «Comprobando servicios de seguimiento…»; si sale otro transportista, pulsa «Cambiar» y elige PostNL. También puedes pegar un enlace de postnl.post (`/track?barcodes=`) o de mailingtechnology.com, el seguimiento de Spring GDS (`/tracking?tn=`).

Peek lee postnl.post: muestra el estado, el historial con el país de cada escaneo, la hora de entrega y el país de destino. Lee la hora de cada escaneo como hora local del país donde se hizo (en España, la de la península) y te la muestra en la zona horaria de tu dispositivo. PostNL no da fecha prevista de entrega ni el remitente. Con un número NL, en cuanto PostNL indica España como destino, Peek pregunta a Correos por el mismo número; si Correos muestra avances más recientes, Peek pasa a seguirlo allí y verás «Entrega con Correos». Si PostNL aún no indica el destino, Peek toma tu país: la región de tu iPhone en la app o, en el navegador, el país desde el que te conectas. postnl.post da por incorrectos los 3S de 13 caracteres, así que para esos, si no rellenas el campo opcional «Código postal de entrega» (con el país de entrega), Peek busca el paquete en [servicios de seguimiento universales](guide:universal-tracker); si lo rellenas, pregunta antes al seguimiento propio de PostNL.

Peek consulta PostNL cada 30 minutos, también con el paquete en reparto, y puede avisarte en cada escaneo, solo en los pasos importantes o solo el día de entrega. No puede contactar con PostNL, con Correos ni con la tienda por ti.

## Preguntas sobre el seguimiento de PostNL

### ¿Dónde está mi paquete de PostNL?

El último escaneo dice en qué fase está y en qué país. Si tienes un número NL y ya aparece España, pruébalo en el localizador de Correos: suele tener los escaneos locales con más detalle.

### ¿Qué significa «The shipment barcode was incorrect» en PostNL?

Que postnl.post no acepta ese código de barras. Suele ser un 3S de 13 caracteres, el formato de Parcel EU de PostNL. Pide a la tienda el número del socio que te lo entrega y síguelo en su web.

### ¿PostNL entrega con Correos en España?

A menudo sí. Spring GDS, la filial internacional de PostNL, dice que al entrar en tu país el paquete pasa con frecuencia al servicio postal local, y pone a Correos como ejemplo. Un código 3S de Parcel EU, en cambio, lo entrega un socio de PostNL que PostNL no nombra.

:::sources
- [PostNL: International Tracking](https://postnl.post/) – 150 números, barra de progreso, textos de los escaneos, respuestas «not found» e «incorrect», destino, remisión a la tienda
- [PostNL Developer: Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – códigos 3S de 13 o 15 caracteres, código de cliente en letras, números S10 con NL
- [PostNL Developer: Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU con 3S de 13 caracteres (en España, sin Canarias, Ceuta ni Melilla), código de barras del socio, C/CW para Canarias, LA/LW, RI/RF, UL
- [Spring GDS: We are Spring GDS](https://www.spring-gds.com/about-us/) – parte del grupo PostNL, comercio electrónico transfronterizo
- [Spring GDS: Track and Trace](https://www.spring-gds.com/track/) – servicio postal local (Correos entre los ejemplos), socio local, primer escaneo, pausas en tránsito y en aduana, entregado sin recibir, reclamaciones, devoluciones, nuevas entregas, sin contacto con los consumidores
- [Spring GDS: Contact us](https://www.spring-gds.com/contact-us/) – las consultas de seguimiento, a la tienda
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – numeración compartida entre operadores postales, dígito de control
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lo que lee y muestra Peek, consulta a Correos con el mismo número
:::
