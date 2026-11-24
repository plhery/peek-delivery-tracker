---
title: Seguimiento HayPost: códigos AM, estados y contactos
description: Rastrea un paquete de HayPost Armenia: números S10 AM, estados IPS internacionales y nacionales, servicios, contactos en Ereván y seguimiento en Peek.
slug: seguimiento-haypost
published: 2026-11-24
updated: 2026-11-24
---

HayPost (*«Haypost» Closed Joint-Stock Company*, en armenio *Հայփոստ*) es el operador postal nacional de la República de Armenia. Fundado en 1994 y reorganizado como sociedad anónima estatal cerrada (CJSC) en 1998, gestiona unas 777 oficinas de correos en Armenia, 23 centros regionales y un centro de intercambio de correo internacional, operando en el marco de la Unión Postal Universal (UPU) y PostEurop. Puedes consultar un envío en la [página Track & Trace de HayPost](https://www.haypost.am/en/track-trace), o pegar el número de seguimiento en el cuadro superior.

## Números de seguimiento de HayPost: formato S10 y dónde encontrarlos

Tu número de seguimiento aparece impreso en el resguardo de correos o en el correo de expedición de la tienda online. HayPost rastrea códigos de barras estándar UPU S10 de 13 caracteres con dígito de control:

| Formato (inventado) | Qué es |
| --- | --- |
| `RR482917367AM` | Carta certificada internacional o pequeño paquete enviado desde Armenia (`R` + segunda letra + 9 dígitos + `AM`). |
| `CP482917367AM` | Paquete postal enviado desde Armenia (`CP` + 9 dígitos + `AM`). |

El buscador de HayPost también consulta códigos internacionales UPU S10 entrantes procedentes de otros países (terminados en el código ISO de dos letras del país emisor, como `US`, `DE` o `CN`) una vez que el correo de origen transmite datos electrónicos de despacho o el envío entra en Armenia. El correo ordinario sin seguimiento no lleva código S10 y no puede rastrearse en línea. Consulta [formatos de números de seguimiento](guide:tracking-number-formats) si tu envío usa una mensajería privada.

## Qué significan los estados de seguimiento de HayPost

HayPost devuelve líneas de escaneo en inglés de la Unión Postal Universal (IPS) etiquetadas según el tramo del viaje: `(Otb)` salida desde el país de origen, `(Inb)` entrada en el país de destino o `(EDI-received)` datos electrónicos de despacho enviados por el correo remitente.

| HayPost muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Receive item from customer (Otb)` | Admitido en una oficina de correos por el remitente. | «Admitido por el transportista» |
| `Insert item into bag (Otb)`, `Send item abroad (EDI-received)`, `Receive item at office of exchange (Otb)` / `(Inb)` | Embolsado para despacho internacional, notificado al extranjero por datos electrónicos o llegado a la oficina internacional de cambio. | «En tránsito» |
| `Send item to customs (Otb)` | Despacho aduanero de exportación en la oficina de cambio de salida antes de dejar el país de origen. | «En tránsito» |
| `Send item to customs (Inb)`, `Record item customs information (Inb)` | Retenido o inspeccionado por la aduana de importación en destino. | «En aduanas» |
| `Return item from customs (Inb)` | Liberado por aduanas y devuelto a la red postal (no es una devolución al remitente). | «En tránsito» |
| `Send item to domestic location (Inb)`, `Receive item at location (Inb)`, `Receive item at sorting centre (Inb)`, `Item was delivered to the postal office (Inb)` | Circulando por centros de clasificación nacionales hasta llegar a la oficina postal local encargada de la entrega. | «En tránsito» |
| `Send item out for physical delivery (Inb)` | En ruta con el cartero para entrega hoy. | «En reparto» |
| `Unsuccessful item delivery attempt (Inb)`, `The notification was sent to the customer: ...` | Entrega no completada; HayPost suele añadir el motivo y el siguiente paso (por ejemplo `Addressee not available; Scheduled for further action/processing today`). | «Intento de entrega» |
| `Receive item at collection point for pick-up (Inb)` | Listo para recoger en la oficina de correos. | «Listo para recoger» |
| `Deliver item (Inb)` | Entregado y registrado como tal. | «Entregado» |

Presta atención a dos frases fáciles de malinterpretar. Primero, `Return item from customs (Inb)` es buena señal: la aduana terminó de revisar el paquete y lo devolvió al correo para continuar el viaje. Segundo, `Item was delivered to the postal office (Inb)` (a menudo retransmitido desde correos extranjeros) significa que la oficina postal local recibió el paquete bajo su custodia, no que te lo hayan entregado a ti.

## Servicios nacionales, internacionales y exprés de HayPost

En su red de aproximadamente 777 oficinas postales ([Sobre HayPost](https://www.haypost.am/en/about-us)), HayPost ofrece:

- **Servicios postales nacionales ([haypost.am/en/postal-services/domestic-postal-services](https://www.haypost.am/en/postal-services/domestic-postal-services)):** postales, cartas, pequeños paquetes hasta 2 kg, paquetes hasta 20 kg, **Yerevan Express Service (YES)** para entregas con seguimiento entre oficinas o a domicilio dentro de Ereván (pedidos de 09:00 a 16:00 procesados el mismo día; tras las 16:00 al siguiente día laborable; pedidos del sábado el lunes; línea de reservas `+374 10 514 513`) y **HayPost Sprint** para envíos nacionales de comercio electrónico con seguimiento hasta 2 kg.
- **Servicios postales internacionales ([haypost.am/en/postal-services/international-delivery](https://www.haypost.am/en/postal-services/international-delivery)):** cartas internacionales, pequeños paquetes hasta 2 kg (largo + ancho + grosor ≤ 0,9 m, lado más largo ≤ 0,6 m), paquetes hasta 20 kg (cualquier lado ≤ 1,05 m), sacas M hasta 30 kg y correo acelerado EMS.
- **HayPost Express a Estados Unidos ([haypost.am/en/postal-services/Haypost-Express](https://www.haypost.am/en/postal-services/Haypost-Express)):** envío aéreo de paquetes desde cualquier oficina de correos de Armenia a Los Ángeles (oficinas de recogida en Glendale, Van Nuys y Sylmar), hasta 30 kg, con un tránsito medio de 7 a 10 días laborables.

**Preguntas frecuentes de HayPost.** Las [FAQ de HayPost](https://www.haypost.am/en/faq) recogen preguntas sobre plazos de entrega nacional, tasas aduaneras, indemnizaciones y días de custodia de envíos EMS; para conocer las cifras exactas aplicables a tu envío, pregunta en tu oficina postal o a HayPost.

## Paquetes HayPost retrasados, en aduanas o extraviados

En compras online enviadas hacia o desde Armenia por vía postal, contacta primero con el vendedor para reembolsos o reenvíos.

**Silencios entre países.** Tras `Send item abroad (EDI-received)`, el correo internacional aguarda transporte aéreo, clasificación de llegada y aduana de importación en la oficina de cambio de destino, lo que puede dejar pausas de varios días. Consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Despacho aduanero.** Los paquetes que entran en Armenia y los envíos `AM` que llegan a destinos como la UE o EE. UU. pasan por aduanas de importación (`Send item to customs (Inb)`). Ten a mano la factura de compra y el número de seguimiento por si aduanas solicita justificante de valor. Consulta [paquetes retenidos en aduanas](guide:customs).

**Entregado, dañado o devuelto.** Si aparece `Deliver item (Inb)` pero no tienes el envío, comprueba con convivientes y tu oficina local (en el [directorio de red de HayPost](https://www.haypost.am/en/our-network)), y contacta después con el vendedor y soporte de HayPost. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y datos de contacto de HayPost

Contactos publicados en [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) (y [haypost.am/am/contact-us](https://www.haypost.am/am/contact-us)):

- **Teléfono de atención al cliente:** `+374 10 514-514`.
- **Ventas y servicios corporativos:** `+374 10 544-444`.
- **Línea de pedidos YES Yerevan Express:** `+374 10 514 513`.
- **Correo electrónico:** `info@haypost.am`.
- **Sede central:** 65/5 Arshakunyats Ave., Ereván 0061, República de Armenia.
- **Formulario online:** en [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us), junto con chat en vivo en [haypost.am](https://www.haypost.am/en).

> Desconfía de SMS, mensajes de WhatsApp o correos que pidan abrir un enlace externo e introducir tu tarjeta bancaria para pagar una pequeña tasa de «reentrega» o «confirmación de dirección» de un paquete HayPost. Verifica tu código S10 directamente en [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) o llama al `+374 10 514-514`. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de HayPost con Peek

Para códigos UPU S10 terminados en `AM` (como `RR482917367AM` o `CP482917367AM`, dígitos inventados), [Peek](/) sigue automáticamente a HayPost como correo de origen preferente, y también rastrea códigos S10 entrantes enviados a Armenia cuando seleccionas HayPost. Como HayPost no dispone de reglas de enlace dedicadas en Peek, pega directamente el código S10 de 13 caracteres; si necesitas elegir HayPost manualmente, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y selecciona HayPost (buscar «Armenia Post» o «Հայփոստ» también lo encuentra).

Peek lee directamente la API JSON pública de HayPost en inglés y muestra la fase («Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Intento de entrega», «Listo para recoger», «Entregado»), el texto IPS (incluidos motivos de fallo y siguientes pasos como `Addressee not available`), la oficina o país (sin códigos postales numéricos), el país de destino y la hora de entrega. Los escaneos armenios y las líneas `Send item abroad (EDI-received)` llevan hora de Ereván y los escaneos de un correo extranjero la zona horaria de ese lugar (hora francesa para París, por ejemplo); Peek los muestra en la hora de tu dispositivo. Cuando HayPost publica por adelantado una fila planificada `Send item abroad (EDI-received)` con fecha futura (a menudo programada horas antes), Peek la oculta hasta que llega su hora real. La API de HayPost no devuelve fecha estimada de entrega, peso, nombre de servicio, datos de remitente/destinatario ni foto de prueba de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que HayPost muestra `Send item out for physical delivery (Inb)`, cada 2 minutos desde las 6:00 según la hora del último escaneo de HayPost, mientras su propio sistema responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el seguimiento de HayPost no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a HayPost.», junto con un enlace «Pide ayuda a HayPost» a su página de contacto. Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a HayPost.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede despachar aduanas ni presentar una consulta ante HayPost por ti.

## Preguntas sobre el seguimiento de HayPost

### ¿Cómo es un número de seguimiento de HayPost?

HayPost usa códigos postales estándar UPU S10 de 13 caracteres: dos letras, nueve dígitos y `AM` al final para envíos depositados en Armenia (por ejemplo `RR482917367AM` o `CP482917367AM`, dígitos inventados). El correo internacional entrante conserva las dos letras del país remitente.

### ¿Por qué dice mi paquete HayPost `Return item from customs (Inb)`?

Ese estado IPS significa que la aduana despachó el envío y lo devolvió al correo para continuar su transporte; no significa que el paquete vuelva al remitente.

### ¿Puede HayPost rastrear paquetes enviados a Armenia desde el extranjero?

Sí. Cualquier código internacional UPU S10 con seguimiento destinado a Armenia puede consultarse en [haypost.am/en/track-trace](https://www.haypost.am/en/track-trace) o en Peek en cuanto lleguen los datos electrónicos de envío o HayPost lo escanee en Armenia.

### ¿Cómo contacto con atención al cliente de HayPost?

Llama al `+374 10 514-514` (o al `+374 10 544-444` para empresas), escribe a `info@haypost.am` o utiliza el formulario de contacto en [haypost.am/en/contact-us](https://www.haypost.am/en/contact-us) o el chat en vivo del sitio de HayPost.

:::sources
- [HayPost: Home](https://www.haypost.am/en) – portal del operador postal nacional
- [HayPost: Track & Trace](https://www.haypost.am/en/track-trace) – seguimiento de envíos online
- [HayPost: About Us](https://www.haypost.am/en/about-us) – fundado en 1994, CJSC desde 1998, ~777 oficinas de correos, 23 centros regionales, miembro de UPU y PostEurop
- [HayPost: Domestic Postal Services](https://www.haypost.am/en/postal-services/domestic-postal-services) – pequeños paquetes 2 kg, paquetes 20 kg, YES Yerevan Express Service (`+374 10 514 513`) y HayPost Sprint
- [HayPost: International Delivery](https://www.haypost.am/en/postal-services/international-delivery) – límites de pequeño paquete internacional (2 kg), paquete (20 kg), sacas M (30 kg) y EMS
- [HayPost: HayPost Express](https://www.haypost.am/en/postal-services/Haypost-Express) – envío aéreo a Los Ángeles (30 kg, 7–10 días laborables)
- [HayPost: Our Network](https://www.haypost.am/en/our-network) – directorio de oficinas postales de Armenia
- [HayPost: Contact Us](https://www.haypost.am/en/contact-us) – `+374 10 514-514`, `+374 10 544-444`, `info@haypost.am`, 65/5 Arshakunyats Ave., Ereván
- [HayPost: Contact Us (armenio)](https://www.haypost.am/am/contact-us) – URL oficial de ayuda enlazada por Peek
- [HayPost: FAQ](https://www.haypost.am/en/faq) – índice de temas frecuentes
- [Universal Parcel Scraper: haypost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/haypost) – detección S10 `AM`, API JSON `api.haypost.am`, mapeo de estados IPS, hora de Ereván, filtro EDI futuro y enlace de ayuda
:::
