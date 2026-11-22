---
title: Seguimiento euShipments: estados y mensajería local
description: Sigue un pedido de euShipments: origen del código, qué significan New, Loaded hub u On delivery, qué mensajería entrega y contacto.
slug: seguimiento-eushipments
published: 2026-11-22
updated: 2026-11-22
---

euShipments es una empresa de logística de comercio electrónico con sede en Ruse, Bulgaria. Almacena, prepara y envía pedidos de tiendas online a los 28 países de su lista «We deliver to» —los 27 de la UE y el Reino Unido— y entrega cada paquete a una mensajería local para la última milla. Sigue tu código en su página «Track my order», [eushipments.com/tracking](https://eushipments.com/tracking), o pégalo arriba. El número es el que te envía la tienda.

## Número de seguimiento de euShipments

euShipments no publica un formato fijo. Indica que, una vez procesado el pedido, recibes tu «tracking code, AWB number or tracking ID» para introducirlo donde el formulario dice «Enter your tracking number to track packages.» Su rastreador muestra el pedido con el código que te dio la tienda, que puede ser el de la mensajería final (con dígitos inventados):

| Número | Qué es |
| --- | --- |
| `74829103` | Ocho dígitos: el único formato visto por Peek en el rastreador de euShipments, para un pedido entregado en Bulgaria bajo el mismo código por la mensajería local. |
| El número tras «Tracking Number:» | El número propio de la mensajería local, mostrado en el resultado de euShipments en cuanto esa empresa tiene el paquete. Su propio rastreador lo acepta. |

Ocho dígitos también encajan con [Mondial Relay](carrier:mondial-relay), Colisweb y [Heppner](carrier:heppner), así que el número solo no demuestra que sea euShipments: comprueba qué empresa figura en el correo de la tienda. Un enlace a la página de seguimiento de euShipments no abre tu pedido, porque solo admite el número en el formulario. ¿Sin código? Consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number).

## Estados de seguimiento de euShipments explicados

El resultado muestra «My order N» con tu código y una tabla de tres columnas: «Date/Time», «Tracking Status» y «Tracking Information». El estado usa los textos en inglés de euShipments, los mismos sea cual sea la mensajería. «Tracking Information» recoge al lado los textos de la mensajería en cuanto tiene el paquete. Cada línea lleva fecha y hora al minuto, como «10 August, 2026 15:39», sin zona horaria.

| euShipments dice | Qué significa para ti |
| --- | --- |
| `New` | La tienda ha registrado el pedido en euShipments. Puede que aún no esté embalado. |
| `Warehouse` | El pedido está en el almacén logístico de euShipments nombrado a continuación. |
| `Loaded hub` | Cargado en un centro de euShipments rumbo al siguiente. Sigue el nombre del centro con su país entre paréntesis, como (HR). |
| `Unloaded hub` | Descargado en un centro, indicado igual. |
| `Picked up` | La mensajería local ha recogido el paquete. |
| `In transit` | En tránsito por la red de la mensajería. |
| `On delivery` | En reparto con el mensajero. |
| `Delivered` | Entregado. Es el estado final de un pedido. |

`Loaded hub` y `Unloaded hub` pueden repetirse en cada centro intermedio. Tras `Picked up`, aparecen tres líneas bajo la tabla: «Courier Name:», «Tracking Number:» y «Tracking Link:», con la mensajería que entrega, su código y el enlace a su rastreador.

Peek no ha visto un estado propio de euShipments para entregas fallidas, puntos de recogida o devoluciones. Para eso, lee el texto de la mensajería en «Tracking Information» o abre su enlace.

## Qué mensajería entrega tu paquete de euShipments

euShipments deja la última milla a mensajerías locales. Traslada los envíos por transporte de línea desde sus centros de fulfillment y cross-docking (Sofía y Ruse en Bulgaria, Oradea en Rumanía, Zagreb en Croacia, Liubliana en Eslovenia, Senec en Eslovaquia y Budapest en Hungría) hasta el depósito de una mensajería local. Su FAQ afirma que colabora con más de 60 mensajerías locales, al menos dos en cada país europeo, y su lista «We deliver to» abarca 28 países: los 27 de la UE y el Reino Unido.

Su portada muestra logotipos de socios clave («Key eCommerce partnerships»), entre ellos Speedy, Sameday, [FAN Courier](carrier:fan-courier), Express One, GLS, DPD, BOX NOW, Pošta Slovenije, [Slovenská pošta](carrier:slovenska-posta), PPL, ACS y [Geniki Taxydromiki](carrier:geniki-taxydromiki), sin indicar cuál entrega en cada país. Su historia corporativa menciona una alianza estratégica con Austrian Post en 2026 sin concretar su alcance. Qué empresa trae tu pedido depende del país y del método elegido al comprar: te lo dice «Courier Name:».

**Dónde puede llegar.** En Bulgaria, euShipments entrega en domicilio o empresa, o fuera de casa: oficina de mensajería, punto de recogida (PUDO) o taquilla. Sus páginas de Croacia y Rumanía recogen opciones parecidas. Cuenta con más de 150 000 puntos fuera de casa en su red e indica que, cuando el envío está listo para recoger, recibes un aviso con las instrucciones.

**Intentos, custodia y nueva entrega.** Rigen las normas de la mensajería local: euShipments no publica ninguna. Usa «Tracking Link:» y «Tracking Number:» para consultar su web y pedir otro intento o un cambio de dirección.

**Contra reembolso.** euShipments ofrece pago contra reembolso en numerosos países europeos: la mensajería local cobra el importe y lo transfiere a la tienda.

## Paquete de euShipments sin cambios, perdido o devuelto

**Parado en `New`.** La tienda registró el pedido en euShipments, pero aún no hay escaneo de almacén ni de centro. Pregunta a la tienda cuándo enviará.

**Parado entre centros.** Tras `Loaded hub`, el paquete viaja por carretera hacia el siguiente centro y la siguiente línea suele llegar al descargarlo. Si tarda más que el plazo estimado por la tienda, consúltale. Ver [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Parado tras `Picked up`.** Lo tiene la mensajería local. Abre «Tracking Link:» y revisa su rastreador con su código.

**Entregado, pero no ha llegado.** Entregó la mensajería local: empieza por ella y luego avisa a la tienda. Ver [entregado pero no recibido](guide:delivered-not-received).

**Devuelto al remitente.** La página de devoluciones de euShipments clasifica como envío no entregado cualquier paquete imposible de entregar por datos incorrectos o problemas de contacto; vuelve al almacén de euShipments o a la tienda, no a ti. Pide un nuevo envío o reembolso a la tienda. Más en [devuelto al remitente](guide:returned-to-sender).

**Devolverlo tú mismo.** euShipments gestiona devoluciones de tres modos para las tiendas: solicitud de devolución en la web de la tienda eligiendo día, hora y lugar de recogida; recogida reservada por la tienda; o envío por tu cuenta, con cualquier mensajería, a la dirección local de devolución que indique la tienda (un almacén de euShipments). Las condiciones de la tienda aclaran cuál corresponde. Ver [seguir una devolución](guide:returns-tracking).

**Perdido o dañado.** euShipments no publica un proceso de reclamación para destinatarios: el contrato es con la tienda. En la UE, el vendedor responde por daños desde el envío hasta la recepción y debe entregar en un máximo de 30 días salvo acuerdo distinto. Si no cumple, recuérdaselo y dale un plazo adicional razonable; si sigue sin entregar, puedes cancelar y recuperar tu dinero. Ver [paquete perdido](guide:lost-parcel) y [paquete dañado](guide:damaged-parcel).

## Teléfono y atención al cliente de euShipments

euShipments publica los siguientes datos de contacto:

- **Teléfono:** +359 700 70 755, número búlgaro visible en sus páginas de contacto, FAQ y seguimiento. No indica horario.
- **Correo electrónico:** office@eushipments.com.
- **Formulario de contacto:** un cuestionario de presupuesto para tiendas (volumen mensual, mercados), no pensado para destinatarios.

Su FAQ para tiendas señala que ofrece soporte por teléfono, correo y chat en vivo a compradores finales bajo petición («when requested»). Empieza por tanto por la tienda; para la entrega en sí, la mensajería indicada en «Courier Name:» suele responder antes.

## Seguir un paquete de euShipments con Peek

[Peek](/) no puede reconocer un código de euShipments por su forma: no hay regla de formato para euShipments y un número de 8 dígitos coincide débilmente con otros transportistas. Pégalo igualmente: Peek sigue a más de 4500 transportistas. Luego pulsa «Cambiar» y elige «euShipments», o usa «Cambiar transportista» en un paquete ya añadido.

Peek lee el rastreador de euShipments. Muestra el estado, el historial y, en líneas de almacén y centro, el lugar (con el país en los centros). Conserva el estado en inglés de euShipments y descarta el texto de la mensajería de «Tracking Information». Cuando «Tracking Link:» apunta a una mensajería que Peek sigue —como [Econt](carrier:econt), Sameday, [FAN Courier](carrier:fan-courier), [Packeta](carrier:packeta), [Slovenská pošta](carrier:slovenska-posta) o [Geniki Taxydromiki](carrier:geniki-taxydromiki)— con su código en el enlace, Peek muestra «Entrega con …» y ese código si difiere del tuyo. Mientras esa mensajería solo tenga el anuncio sin escaneo propio, muestra «Anunciado a …, aún no recibido». Cuando registra progreso fechado al menos tan reciente como la última línea de euShipments, Peek pasa a seguirlo allí conservando las líneas anteriores; de lo contrario se queda en euShipments.

euShipments no facilita fecha estimada de entrega ni justificante de entrega, así que Peek no muestra ninguno. Las horas sin zona horaria se leen en la hora local (hora búlgara en Bulgaria) y se convierten a tu zona horaria («Horas en tu zona horaria:»). Peek consulta hasta cada 10 minutos, y cada 2 minutos desde las 6:00 según el reloj del último escaneo el día en que entra «En reparto» mientras tienes el paquete abierto en Peek o tienes las notificaciones activadas, siempre que responda el rastreador de euShipments. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), consulta cada hora. En notificaciones puedes elegir «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Si pasan cuatro días sin escaneo en tránsito, fuera de aduanas o de una incidencia, Peek muestra «Sin novedades desde el …», el aviso «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a euShipments.» y «Pide ayuda a euShipments». Peek no puede contactar con euShipments, la tienda ni la mensajería por ti.

## Preguntas sobre el seguimiento de euShipments

### ¿Es fiable euShipments?

Sí. Es una empresa logística de Ruse, Bulgaria, fundada en 2012 como InOut Trade y renombrada euShipments.com en 2019. Realiza envíos para tiendas online, incluidos vendedores en Temu y eMAG mediante integraciones propias.

### ¿Qué mensajería entrega mi paquete de euShipments?

Una mensajería local de tu país. En cuanto tiene el paquete, el resultado de euShipments muestra «Courier Name:», «Tracking Number:» y «Tracking Link:» debajo de la tabla.

### ¿Cuánto tarda en entregar euShipments?

Depende del almacén donde esté el producto. Desde Sofía y Ruse, euShipments indica entrega al día siguiente en Bulgaria y Rumanía, y de 2 a 4 días a Croacia, Eslovenia, Eslovaquia y Chequia. Desde Zagreb, Liubliana o Senec indica 1 día laborable dentro de su país. Guíate por el plazo de tu tienda. Más en [plazos de entrega](guide:delivery-times).

### ¿Qué significa Loaded hub en euShipments?

El paquete se ha cargado en un centro de euShipments para el siguiente tramo, indicado con su país entre paréntesis. `Unloaded hub` es la llegada al siguiente centro.

### ¿A qué países entrega euShipments?

Su lista «We deliver to» recoge 28 países: los 27 de la UE y el Reino Unido. En cada uno, una mensajería local realiza la entrega final.

:::sources
- [euShipments: Track Your Order](https://eushipments.com/tracking) – formulario de seguimiento y textos, teléfono, correo electrónico
- [euShipments: Parcel Tracking For End Customers](https://eushipments.com/ecommerce-delivery/new-parcel-tracking/parcel-tracking-for-end-customers) – código de seguimiento, número AWB o ID, más de 60 socios de última milla
- [euShipments: We deliver to](https://eushipments.com/we-deliver-to) – lista de 28 países: los 27 de la UE y Reino Unido
- [euShipments: FAQ](https://eushipments.com/faq) – más de 60 mensajerías, al menos dos por país, soporte a destinatarios bajo petición, teléfono, correo
- [euShipments: Contact Us](https://eushipments.com/contact) – teléfono, correo, Ruse, formulario de presupuesto para tiendas
- [euShipments: About us](https://eushipments.com/about-us) – InOut Trade, 2012, cambio de nombre en 2019, alianza con Austrian Post
- [euShipments: Home](https://eushipments.com/) – logotipos de socios clave de comercio electrónico
- [euShipments: eCommerce Deliveries To Bulgaria](https://eushipments.com/countries/bulgaria) – Sofía y Ruse, transporte al depósito del mensajero, entrega a domicilio y fuera de casa, plazos, contra reembolso
- [euShipments: International eCommerce Delivery To Romania](https://eushipments.com/countries/romania) – Oradea, Budapest, opciones de entrega
- [euShipments: Domestic and International eCommerce Delivery To Croatia](https://eushipments.com/countries/croatia) – Zagreb y otros centros, entrega al día siguiente, taquillas, puntos de recogida, oficinas
- [euShipments: International Parcel Delivery To Slovenia](https://eushipments.com/countries/slovenia) – Liubliana, 1 día laborable
- [euShipments: International Delivery To Slovakia](https://eushipments.com/countries/slovakia) – Senec, 1 día laborable
- [euShipments: Out-of-home Delivery](https://eushipments.com/ecommerce-delivery/out-of-home-delivery) – 150 000 ubicaciones, notificaciones de recogida
- [euShipments: Returns Management](https://eushipments.com/returns-management) – envíos no entregados, tres formas de devolución
- [euShipments: Temu Integration](https://eushipments.com/integration/ecommerce-platform-integration/temu) – vendedores de Temu
- [euShipments: eMAG Integration](https://eushipments.com/integration/ecommerce-platform-integration/emag-integration) – vendedores de eMAG
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – 30 días, responsabilidad del vendedor hasta la recepción, recordatorio y reembolso
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – etiquetas de la página de resultados, estados, números, lo que Peek lee y muestra
:::
