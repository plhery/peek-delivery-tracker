---
title: Seguimiento Canada Post: números, estados y aviso postal
description: Rastrea un paquete de Canada Post: formato de códigos CA y de 16 dígitos, estados en inglés, avisos de entrega, entrega con Correos y aduanas.
slug: canada-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Canada Post (Canada Post Corporation / Postes Canada) es la corporación federal de la Corona que gestiona el servicio postal público de Canadá. Puedes consultar un envío en la [página Track de Canada Post](https://www.canadapost-postescanada.ca/track-reperage/en), en su aplicación móvil gratuita o pegando el código en el buscador de arriba. El número lo proporciona únicamente el remitente —por lo general en el correo de envío de la tienda—, ya que Canada Post no puede buscar paquetes por tu nombre ni por tu dirección. Si el código termina en `CA` y lo sigues desde fuera de Canadá, Peek también puede consultar al correo nacional de tu país con ese mismo número.

## Número de seguimiento de Canada Post: formato y ejemplos

Peek asocia dos formatos principales a Canada Post, mostrados aquí con dígitos inventados:

| Ejemplo (inventado) | Qué identifica |
| --- | --- |
| `4018 2930 5746 1826` | 16 dígitos numéricos; el último es un dígito de control. También usan 16 dígitos DHL eCommerce, [Correos Express](carrier:correos-express), [TNT](carrier:tnt) y [Evri](carrier:evri). |
| `RR482910576CA` | Código postal internacional de 13 caracteres: 2 letras (como `RR` o `EE482910576CA`), 9 dígitos y `CA`; el último dígito antes de `CA` es de control. |

Canada Post también localiza envíos en su territorio mediante el número de 15 dígitos de una tarjeta de aviso de entrega (*Delivery Notice Card*, por ejemplo `482910573649182`) o mediante referencias compuestas solo por cifras; para usarlos en Peek, elige Canada Post manualmente como se explica más abajo.

Otro operador canadiense habitual, Purolator, utiliza códigos propios de 12 dígitos o de 3 letras y 9 dígitos: Peek no consulta a Canada Post por esos formatos. Si Canada Post no reconoce tu código, revisa qué transportista nombra la tienda en su correo y consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number) y [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento de Canada Post en inglés

Peek lee los eventos de Canada Post en inglés. Estos son los mensajes más frecuentes, palabra por palabra, con la etapa equivalente en Peek:

**Dentro de Canadá y envíos salientes**

| Canada Post muestra | Qué significa para tu envío | En Peek |
| --- | --- | --- |
| `Electronic information submitted by shipper` | El remitente generó la etiqueta, pero Canada Post aún no tiene el paquete. | «Etiqueta creada» |
| `Item accepted at the Post Office` | Depositado en una oficina postal canadiense. | «Admitido por el transportista» |
| `Item picked up by Canada Post` | Recogido por Canada Post en las instalaciones del remitente. | «Admitido por el transportista» |
| `Item processed` | Avanza por los centros de clasificación de Canada Post (puede repetirse varias veces). | «En tránsito» |
| `International item being forwarded to destination country` | Despachado desde Canadá hacia el país de destino (`Item has arrived in foreign country` al aterrizar). | «En tránsito» |
| `Item presented to customs` | Presentado ante la aduana del país de destino. | «En aduanas» |
| `Item out for delivery` | En reparto hoy con el cartero en Canadá. | «En reparto» |
| `Delivered to your community mailbox, parcel locker or apt./condo mailbox` | Depositado en buzón comunitario, casillero de paquetes o buzón del edificio. | «Entregado» |
| `Delivered to recipient's safe drop location` | Dejado en un lugar seguro de tu domicilio (igual que `Delivered to mailroom` o `concierge`). | «Entregado» |
| `Notice card left indicating where and when to pick up item` | Intento de entrega: dejaron tarjeta indicando en qué oficina y desde cuándo recogerlo. | «Intento de entrega» |
| `Item available for pickup at Post Office` | Listo para retirar en la oficina postal indicada. | «Listo para recoger» |
| `Final Notice; Item will be returned to sender if not collected within 10 days` | Último aviso: quedan 10 días naturales antes de devolverse al remitente. | «Listo para recoger» |
| `Recipient not located at address provided. Item being returned to sender.` | Destinatario no localizado en esa dirección; comienza la devolución. | «Incidencia» |
| `Item has been returned and is enroute to the Sender` | En camino de vuelta al remitente. | «En tránsito» |

Los avisos `Item re-routed due to processing error; Possible delay` y `Delivery may be delayed due to transportation delay` advierten de una demora («Incidencia»), mientras que `Expected delivery date updated` actualiza la fecha prevista sin movimiento físico. En los envíos **desde el extranjero hacia Canadá**, `International item mailed in originating country` figura como «Admitido por el transportista», `International item has left originating country and is en route to Canada` como «En tránsito», `Item has arrived in Canada and will be presented for review` como «En aduanas» y `Item was released by Customs and is now with Canada Post for processing` vuelve a «En tránsito». En el historial, las entregas se muestran como «Entregado» y el resto conserva el inglés original.

## Paquete enviado desde Canadá a España o Latinoamérica: entrega y aduanas

**Quién entrega.** Cuando el envío lleva un código postal internacional acabado en `CA` con dígito de control válido, Peek consulta también con ese mismo número al correo nacional del país desde el que sigues el paquete —por ejemplo [Correos](carrier:correos-spain) desde España, Correo Argentino desde Argentina o [USPS](carrier:usps) desde Estados Unidos—. Como Canada Post no informa el país de destino en sus datos, manda la región configurada en tu teléfono (iPhone o Android) o el país desde el que añadiste el envío en web. En cuanto tu correo nacional publica un escaneo igual o más reciente que el último de Canada Post, Peek sigue el paquete allí conservando el historial canadiense, y durante un día más sigue consultando también a Canada Post como máximo cada 55 minutos. Un código de 16 dígitos permanece siempre en Canada Post (y en México o Chile la consulta final se hace directamente en Correos de México o CorreosChile).

**Aduanas en España.** Canadá está fuera de la UE, así que las mercancías pasan control aduanero. En **España peninsular y Baleares** se devenga el IVA de importación desde el primer euro (salvo cobro previo por IOSS); además, desde el 1 de julio de 2026 los pedidos comprados en línea fuera de la UE de hasta 150 € pagan un arancel de la UE de 3 € por categoría de artículo, y desde el 1 de noviembre de 2026 se suma una tasa de gestión de la Unión de 2 € por artículo cualquiera que sea su valor, aparte de la gestión postal. Canarias queda fuera del ámbito del IVA comunitario (aplica IGIC) y Ceuta y Melilla quedan fuera del territorio aduanero y de IVA de la UE; en Latinoamérica rigen las normas aduaneras de cada país. Más en [paquetes retenidos en aduanas](guide:customs).

## Entregas fallidas, casilleros y aduanas dentro de Canadá

Si recibes o envías un paquete **en Canadá**, estas son las reglas de Canada Post y de la Agencia de Servicios Fronterizos de Canadá (CBSA):

- **Intento en puerta y tarjeta de aviso**: si requiere firma o no cabe en el buzón, llaman a tu puerta (en edificios con interfono, timbrar al interfono cuenta como intento). Si no hay nadie, dejan una *Delivery Notice Card* (tras `Delivery Attempt Made / Rescheduled for delivery next business day` reintentan al siguiente día hábil). A los 5 días naturales emiten un aviso final y tras **15 días naturales** en total en oficina lo devuelven al remitente. Para recogerlo lleva un documento oficial con foto y dirección (o autoriza a otra persona firmando la tarjeta con su nombre). En casilleros de paquetes (*parcel lockers*), dejan la llave en tu buzón y tras **5 días** sin retirarlo lo trasladan a la oficina postal. Ver [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).
- **Aduanas en Canadá (CBSA)**: los envíos por correo de hasta **20 CAN$** están exentos de aranceles e impuestos (hasta **60 CAN$** en regalos enviados por familiares o amigos). Por encima se aplica el 5 % de GST (o HST/PST según la provincia) más los aranceles que correspondan en el formulario postal **E14**, junto con una tarifa de gestión de Canada Post de **9,95 CAN$**. Puedes pagar en línea con el botón *Pay* en Track o en la app (tarjeta, Apple Pay o Google Pay) antes de que salga a reparto, con tarjeta en la puerta o en la oficina postal; para impugnar el importe puedes rechazar la entrega pidiendo reevaluación o pagar y reclamar con el formulario **B2G** impreso al dorso del E14.

## Envío retrasado, sin actualizarse, perdido o no recibido

**Cuatro días sin escaneos.** Tras cuatro días sin cambios en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde hace cuatro días» y en la ficha explica: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Canada Post.», añadiendo el enlace «Pide ayuda a Canada Post». Según la CBSA, el correo en revisión aduanera en Canadá no ofrece seguimiento detallado; revisa [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Retrasado o perdido.** Canada Post indica que la mayoría de los envíos demorados llegan dentro de los **5 días hábiles** posteriores a la fecha prevista. Pasado ese margen, abre un ticket de investigación en su [página de paquetes perdidos](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) dentro de los **90 días** siguientes al envío (o **6 meses** para envíos hacia EE. UU. o internacionales). Solo el remitente cobra indemnización, así que en compras en línea acude también a la tienda; ver [paquete perdido](guide:lost-parcel).

**Entregado pero no está.** Según la *Canada Post Corporation Act*, el correo dejado del modo habitual en tu domicilio, buzón o con alguien razonablemente autorizado en la dirección cuenta como entregado. Comprueba primero en Track el lugar exacto (`Delivered to mailroom`, conserjería o lugar seguro) y si hubo firma; si entregó [Correos](carrier:correos-spain) u otro correo nacional, revisa su justificante y consulta [entregado pero no recibido](guide:delivered-not-received). Cuando una devolución llega al remitente, Peek muestra «Devuelto al remitente» ([devuelto al remitente](guide:returned-to-sender)).

## Atención al cliente y teléfonos de Canada Post

- **Teléfono**: `1-866-607-6301` dentro de Canadá, `+1-416-979-3033` desde fuera de Canadá, TTY `1-800-267-2797`. Lunes a viernes de 7:00 a 23:00 ET; sábados y domingos de 9:00 a 21:00 ET.
- **En línea**: asistente virtual y tickets de soporte en la [página de soporte de Canada Post](https://www.canadapost-postescanada.ca/cpc/en/support.page).

> Los SMS que reclaman tasas pendientes con un enlace de pago son fraudulentos. Los mensajes auténticos de Canada Post proceden de `272727` o `55555` (previa suscripción) o del `613-734-1002` y `613-734-1004`, siempre hacia `canadapost-postescanada.ca`. En Canadá puedes reenviar SMS sospechosos al `7726`.

## Seguir un paquete de Canada Post con Peek

[Peek](/) reconoce al instante como Canada Post cualquier código internacional terminado en `CA` con dígito de control válido. Con un código de 16 dígitos válido, muestra «Comprobando servicios de seguimiento…», consulta a la vez a Canada Post, DHL eCommerce, TNT, Correos Express y Evri, y se queda con quien lo reconozca. Para un aviso de 15 dígitos o una referencia numérica, pulsa «Cambiar» y elige Canada Post en «Todos los transportistas». También funcionan los enlaces con `searchFor=` (los enlaces con `#/search` solo sirven para códigos `CA`, así que pega aparte los de 16 dígitos). En envíos postales hacia Canadá, Peek también consulta a Canada Post cuando el correo de origen indica Canadá como destino (salvo los códigos `L…CH` seguidos en Swiss Post, que nunca se traspasan a Canada Post).

Peek consulta directamente a Canada Post y muestra el estado, el historial con localidad (ciudad y provincia en Canadá), la fecha prevista, la franja horaria cuando está en reparto en Canadá y Canada Post la ofrece, la hora de entrega y el nombre del remitente cuando aparece completo, descartando opciones de entrega, direcciones, firmas y fotos. Las horas se muestran en tu zona («Horas en tu zona horaria: {{zone}}.»).

Mientras mantienes el paquete abierto o el día de entrega con notificaciones activas, Peek comprueba hasta cada 10 minutos, y hasta cada 2 minutos cuando Canada Post informa que está «En reparto» ese día —desde las 6:00 hora local o desde 30 minutos antes de que empiece la franja horaria anunciada—; el resto del tiempo, y tras 12 horas sin cambios en «Etiqueta creada» o «Admitido por el transportista» (48 horas en las demás etapas), revisa cada hora. Mediante servicios de seguimiento comprueba hasta cada 15 minutos ese día en «En reparto» bajo las mismas condiciones, y si no cada hora, o cada 3 horas tras un día sin cambios. Al pasar al correo de destino, sigue el ritmo de ese operador. Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre el seguimiento de Canada Post

### ¿Por qué Peek muestra «Esperando a Canada Post» con mi número?

Canada Post devuelve la misma respuesta cuando un código aún no se ha escaneado, no existe o caducó (más de un año). Si pasan dos días sin respuesta de ninguna fuente, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo a diario, y semanalmente tras una semana desde que lo añadiste. Revisa el dígito de control al final de las 16 cifras o antes de `CA`.

### ¿Quién entrega un paquete de Canada Post en España?

Si el código termina en `CA`, lo reparte [Correos](carrier:correos-spain) y Peek lo consulta automáticamente con el mismo número. Si es un código de 16 dígitos, el seguimiento permanece en Canada Post.

### ¿Puedo rastrear con los 15 dígitos de la tarjeta de aviso?

Sí: pega los 15 dígitos de la tarjeta, pulsa «Cambiar» y selecciona Canada Post dentro de «Todos los transportistas».

:::sources
- [Canada Post: Track a package by tracking number](https://www.canadapost-postescanada.ca/track-reperage/en) – app gratuita, avisos de recogida, documento de identidad y motivos de código no encontrado
- [Canada Post: How to use Track](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/how-to-use-track.page) – código facilitado solo por el remitente, sin búsqueda por nombre/dirección y tarjetas de 15 dígitos
- [Canada Post: Tracking statuses](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-statuses.page) – estados de aviso, autorización a terceros, reintento y aduanas
- [Canada Post: Delivery options](https://www.canadapost-postescanada.ca/cpc/en/support/articles/parcel-services-shipping-in-canada/delivery-options.page) – intento por interfono, lugar seguro, aviso final a los 5 días y custodia de 15 días naturales
- [Canada Post: Parcel lockers FAQ](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/parcel-lockers/why-wasnt-my-parcel-delivered.page) – llave en buzón y plazo de 5 días en casillero
- [Canada Post: How to pay duty and taxes](https://www.canadapost-postescanada.ca/cpc/en/support/kb/receiving/delivery-faq/how-to-pay-duty-and-taxes-online-canada-post.page) – tasa de 9,95 CAN$, pago en línea y Apple Pay / Google Pay en la app
- [Canada Post: Missing packages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/claims/missing-packages.page) – margen de 5 días hábiles y plazos de 90 días y 6 meses
- [Canada Post: Support and contact](https://www.canadapost-postescanada.ca/cpc/en/support.page) – teléfonos en Canadá y en el extranjero, horarios y chat
- [Canada Post: Recognizing spam and fraudulent messages](https://www.canadapost-postescanada.ca/cpc/en/support/kb/company-policies/privacy-security/what-do-to-if-you-get-suspicious-email.page) – remitentes reales de SMS y código 7726
- [Canada Post: Responsible business practices](https://www.canadapost-postescanada.ca/cpc/en/our-company/corporate-sustainability/responsible-business-practices.page) – corporación federal de la Corona
- [CBSA: Importing by mail](https://www.cbsa-asfc.gc.ca/import/personal-personnel/postal-postale-eng.html) – revisión aduanera postal y formulario E14
- [CBSA: Paying duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dtytx-drttx-eng.html) – exenciones de 20 CAN$ y 60 CAN$, GST/HST/PST
- [CBSA: Disputing duty and taxes](https://www.cbsa-asfc.gc.ca/import/postal-postale/dispute-contestation-eng.html) – reevaluación y formulario B2G
- [Justice Laws Website: Canada Post Corporation Act](https://laws-lois.justice.gc.ca/eng/acts/C-10/page-1.html) – definición legal de entrega postal
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – arancel de 3 € por categoría de artículo hasta 150 €
- [Comisión Europea: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicación desde el 1 de julio de 2026
- [Comisión Europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tasa de gestión de 2 € por artículo desde el 1 de noviembre de 2026
- [Comisión Europea: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – régimen de Canarias, Ceuta y Melilla
- [Universal Parcel Scraper: Canada Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/canada-post) – formatos de código, mapeo de estados y traspaso postal
:::
