---
title: DPD Suiza seguimiento: rastrear paquete, Pickup y contacto
description: Rastrea un paquete de DPD Suiza (DPD Switzerland): número de 14 dígitos que empieza por 060, estados de myDPD, puntos DPD Pickup en sábado y teléfono.
slug: dpd-suiza-seguimiento
published: 2026-11-17
updated: 2026-11-17
---

DPD Suiza es **DPD (Schweiz) AG**, con sede en Buchs ZH: forma parte de la red Geopost y cuenta con más de 1000 puntos **DPD Pickup Location** en toda Suiza. Puedes seguir tu envío introduciendo el número de paquete de 14 dígitos en el portal [myDPD](https://www.dpdgroup.com/ch/mydpd/?lang=de) o pegándolo en el cuadro de arriba. Lo habitual es recibirlo por correo electrónico de la tienda, y también aparece en la tarjeta de aviso que deja el repartidor en tu buzón si no estabas en casa.

## Número de seguimiento de DPD Suiza: dónde encontrarlo y cómo es

DPD lo llama «número de paquete» (*parcel number* / *Paketnummer* / *numéro de colis*). Casi siempre llega en el correo de envío del remitente o de DPD; tras un intento fallido también figura en la tarjeta de aviso del buzón, cuyo código de barras puede escanear la app myDPD. ¿No tienes ningún correo? Consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number).

| Número (inventado) | Qué es |
| --- | --- |
| `06090000000000` | Número de paquete de 14 dígitos. En sus formularios para remitentes, DPD Suiza indica que los números suizos empiezan por `060` (Peek reconoce también el rango suizo `061`). |
| `06090000000000U` | El mismo número seguido de un 15.º carácter de control (dígito o letra mayúscula), aceptado por Peek si el dígito o la letra coincide. |

El código que empieza por `P00` en la tarjeta de aviso **no** es el número de seguimiento: es el número de notificación (*notification number*), que sirve para concertar una nueva entrega. Si myDPD no encuentra nada, puede que el paquete aún no haya llegado a manos de DPD, que tenga más de **31 días** (y esté ya archivado) o que falte alguna cifra. Las secuencias de 14 dígitos también se usan en [SEUR](carrier:seur) en España, en DPD Alemania, DPD Francia, DPD Reino Unido o BRT en Italia: comprueba en el correo de la tienda qué empresa reparte tu pedido y revisa [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de DPD Suiza

El portal suizo myDPD está disponible en inglés, alemán, francés e italiano (no en español). Estos son los estados en inglés de myDPD —los mismos que muestra Peek— en el orden habitual del envío, con ambas variantes cuando DPD emplea dos:

| Estado en DPD Suiza | Qué significa para ti |
| --- | --- |
| `Order created` | Etiqueta creada: DPD todavía no tiene físicamente el paquete. |
| `Parcel handed to DPD` | DPD ha recibido el paquete del remitente. |
| `Your parcel is on its way`, `In transit` | Circula entre centros de la red de DPD; aún no ha salido a reparto. |
| `At delivery center`, `At delivery centre` | Ha llegado a la delegación desde la que saldrá tu reparto. |
| `Parcel out for delivery` | Va en la furgoneta del conductor para entregarse hoy. |
| `Unsuccessful delivery attempt` | No se ha podido entregar: busca la tarjeta de aviso en el buzón. |
| `Ready for collection`, `Available for collection` | Listo para recoger en una tienda DPD Pickup Location. |
| `Delivered`, `Your parcel has been delivered successfully` | Entregado (en Peek, `Delivered` aislado se muestra como «Entregado»). |
| `Return to sender`, `Return to shipper` | De camino de vuelta al remitente. |
| `Delivered (Returned to Sender)` | Entregado de vuelta al remitente, no en tu domicilio. |

Si consultas myDPD en francés, cuidado con dos falsos amigos frecuentes en la Suiza francófona: `En cours de livraison` significa solo «en tránsito» entre almacenes (el reparto real del día es `Colis en cours de livraison`), y `Disponible pour enlèvement` indica que el paquete te espera en un punto DPD Pickup. Más detalles en [estados de seguimiento](guide:tracking-statuses).

En el apartado de protección de datos («Data protection»), myDPD te pide el código postal de destino o te permite continuar sin verificación («Continue without verification»): sin él solo ves una línea resumida por etapa; con él se desbloquean los escaneos detallados (`Your parcel arrived at our hub`), los avisos de entrega (`Your parcel will be delivered to a safe place according to your instructions`) y la franja horaria.

## Entrega de DPD en Suiza: Predict, puntos Pickup, aduanas y ausencias

**Predict.** Cuando el remitente contrata este servicio, DPD te envía por SMS o correo electrónico el día previsto y, la misma mañana del reparto, una ventana horaria de **1 hora**; durante el recorrido puedes seguir el vehículo en directo y cambiar la opción de entrega hasta **15 minutos antes** de la llegada.

**Cambios y autorización de depósito.** En myDPD puedes elegir otro día, desviar el paquete al trabajo, a un vecino o a un punto DPD Pickup Location, o conceder permiso para dejarlo sin firma en un «lugar seguro» (*Safe place* / *Abstellgenehmigung*); desde el 12 de octubre de 2026 esa autorización exige verificar tu número de teléfono móvil. Si el remitente exige firma y no hay autorización de depósito activa, el conductor no puede dejarlo en la puerta.

**Si no estás en casa.** El repartidor deja una tarjeta de aviso; consulta también [entrega fallida](guide:missed-delivery). Según el servicio contratado por el remitente, DPD puede volver a intentarlo (hasta **tres intentos**, según un artículo de ayuda de DPD Suiza), entregarlo a un vecino en un radio de **50 metros**, llevarlo a un punto DPD Pickup Location o devolverlo al remitente. Puedes programar una nueva entrega en la [página de reentrega de myDPD](https://www.dpdgroup.com/ch/mydpd/fmp/login) con el código del SMS o el número `P00` de la tarjeta.

**Puntos DPD Pickup Location.** Hay más de 1000 comercios asociados en Suiza, muchos abiertos por la tarde y los **sábados**; consulta horarios en el [buscador de puntos DPD Pickup](https://www.dpd.com/ch/en/dpd-pickup-location/). Cuando llega el paquete, DPD te avisa por correo o SMS y la tienda lo guarda durante **7 días naturales** (fin de semana incluido) antes de devolverlo al remitente; lee también [paquete no recogido](guide:uncollected-parcel). Lleva un documento oficial con foto; si va otra persona por ti, necesitará su propio documento con foto más tu autorización digital o el reverso firmado de la tarjeta de aviso.

**Aduanas en Suiza.** En envíos procedentes del extranjero (por ejemplo, desde España hacia Suiza), la Oficina Federal de Aduanas y Seguridad Fronteriza (BAZG) indica que correos y transportistas declaran cada envío: el IVA suizo es del **8,1 %** (**2,6 %** en el tipo reducido, como libros o alimentación), los importes de aranceles o IVA de hasta **5 CHF** no se cobran, y el transportista puede aplicar gastos de gestión aduanera. Tienes más contexto en [paquete retenido en aduanas](guide:customs).

## Paquete de DPD Suiza parado, no recibido o dañado

El contrato de transporte lo firma el remitente con DPD, por lo que ante daños, pérdida o falta de artículos DPD Suiza remite al destinatario a la tienda o remitente, que tramita el expediente ante el departamento de siniestros de DPD:

- **Sin actualizaciones durante días:** utiliza el formulario de contacto de DPD indicando tu número de 14 dígitos; mira antes [por qué no se actualiza el seguimiento](guide:tracking-not-updating).
- **Consta como entregado, pero no está:** pide al vendedor que solicite la comprobación de entrega a DPD; revisa los pasos de [entregado pero no recibido](guide:delivered-not-received).
- **Paquete dañado:** si el daño es visible en el momento de la entrega, puedes rechazar el paquete para que el conductor se lo lleve. Si ya lo has recibido, avisa al remitente —a ser posible el mismo día—: según la guía de DPD Suiza, el remitente dispone de **7 días naturales** desde la entrega para declarar el daño, y debes conservar fotos, embalaje y contenido hasta **3 meses**. Consulta [paquete dañado](guide:damaged-parcel) y [paquete perdido](guide:lost-parcel).
- **Mediación postal:** tras reclamar sin acuerdo ante la empresa, en Suiza puedes acudir al órgano de conciliación **Ombud-PostCom** (tasa de procedimiento de `CHF 20.–`).

## Teléfono y atención al cliente de DPD Suiza

Canales publicados en las páginas suizas de DPD en noviembre de 2026 para todo el país:

- **Teléfono:** `058 311 13 13` (desde fuera de Suiza: `+41 58 311 13 13`), de lunes a viernes de `08:00` a `18:00`, máximo `CHF 0.08`/min desde Suiza. El 24 y el 31 de diciembre de 2026: de `08:00` a `12:00`.
- **Formulario en línea:** botón «Contact DPD» en la [página de soporte de DPD Suiza](https://www.dpd.com/ch/en/support-centre/your-request/).
- **Correo electrónico:** no existe buzón de atención para destinatarios (el aviso legal *Impressum* indica usar únicamente el formulario de contacto).

> DPD Suiza advierte de intentos de estafa por SMS, WhatsApp y correo electrónico (como mensajes desde prefijos `+63` o `+212`, o correos que reclaman gastos de envío). No abras enlaces ni pagues nada. Como remitentes auténticos cita `no-replyinvoicing@dpd.ch`, `import@dpd.ch`, `export@dpd.ch` y `faktura@dpd.ch`, y recomienda denunciar webs fraudulentas en `antiphishing.ch`. Más información en [SMS falsos de entregas](guide:delivery-scam-texts).

## Seguir un paquete de DPD Suiza con Peek

[Peek](/) acepta los 14 dígitos con o sin el 15.º carácter de control. En los números de 14 dígitos que empiezan por `060` o `061` —y en los códigos DPD neutros de 15 caracteres con carácter de control válido— consulta en primer lugar a **DPD Switzerland** mientras muestra «Comprobando servicios de seguimiento…». En secuencias neutras de 14 dígitos sin prefijo suizo, desde Suiza pregunta primero a **DPD Switzerland**, mientras que desde España consulta primero a [SEUR](carrier:seur) y, en la misma primera ronda, también a DPD Belgium y a **DPD Switzerland**; en prefijos de otras filiales DPD (como `1550` del Reino Unido o `0150` de Alemania) pregunta primero a la red de ese país y, en la misma primera ronda, también a **DPD Switzerland**. DPD Switzerland solo confirma paquetes situados en Suiza («DPD Switzerland tiene este paquete.»); si un número de 14 dígitos `060` o `061` aún no consta en su sistema, verás «Esperando el primer escaneo de DPD Switzerland». Para números de 14 dígitos que empiezan por `10` (DPD France), Peek no interroga a DPD Switzerland: selecciónala manualmente pulsando «Cambiar» o «Cambiar transportista». Los enlaces de `dpd.com` o `dpdgroup.com` con `parcelNumber=` también funcionan: una ruta con `/ch/` elige directamente **DPD Switzerland** (en cambio, una con `/es/` no asigna transportista, ya que Geopost opera en España mediante [SEUR](carrier:seur)).

El campo opcional «Código postal de entrega» admite el formato de cualquier país y se envía a DPD y, cuando hace falta, a servicios de seguimiento. Sin código postal ves una línea por etapa y la fecha prevista si DPD la facilita; con él se añaden los escaneos en el mapa con localidad y país, los avisos de DPD, la franja horaria (en hora suiza), el nombre del remitente, el peso y, en entregas en tienda Pickup, la dirección completa del punto (sin código postal suele verse solo el nombre del comercio cuando DPD lo indica). Si DPD rechaza el código postal, Peek avisa con «No encontrado con tu código postal» y ofrece «Cambiar código postal».

Los estados se muestran en el inglés de DPD (salvo `Delivered` aislado, traducido como «Entregado») y las horas en tu zona horaria («Horas en tu zona horaria: …» en peektracker.com y en la app de iPhone); tu nombre, tu dirección y el enlace al justificante de entrega se descartan. Peek comprueba el envío hasta una vez cada 10 minutos, y en el día de reparto hasta una vez cada 2 minutos desde las 6:00 hora local o media hora antes de abrirse la ventana horaria (cada 5 minutos cuando debe abrir el localizador de DPD en un navegador; cada 15 minutos tras un fallo o mediante servicios de seguimiento), con alertas para «Todas las novedades», «Solo importantes» o «Solo el día de entrega». Tras dos días sin novedades —o 12 horas en «Etiqueta creada» o «Admitido por el transportista»— pasa a comprobar cada hora; si pasados dos días ningún transportista reconoce el código, muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» No hay cambio automático a otro transportista.

## Preguntas frecuentes sobre el seguimiento de DPD Suiza

### ¿Cuántos días guarda un paquete un punto DPD Pickup en Suiza?

Siete días naturales, fin de semana incluido, desde el aviso por correo electrónico o SMS según la ayuda de myDPD; pasado ese plazo se devuelve al remitente.

### ¿Reparte DPD Suiza los sábados?

Para entregas a domicilio DPD no publica una norma general, pero muchos puntos DPD Pickup Location abren los sábados según el horario de cada comercio, y el calendario festivo de DPD Suiza incluye reparto el sábado 28 de noviembre de 2026.

### ¿Cuánto tarda en llegar un paquete de DPD en Suiza?

Por regla general **1 día** desde el inicio del envío para paquetes nacionales dentro de Suiza, según la ayuda de myDPD; en envíos internacionales depende del país de origen, y las condiciones generales advierten que los plazos de tránsito son orientativos, no garantizados.

### ¿Cómo devolver un paquete con DPD en Suiza?

Llévalo a un punto DPD Pickup Location con la etiqueta de devolución proporcionada por el remitente ya pegada en la caja: sin etiqueta de devolución del remitente, el punto Pickup no puede aceptarlo.

:::sources
- [DPD Switzerland: Parcel tracking (inglés)](https://www.dpd.com/ch/en/mydpd/) – Predict, cambios de entrega y puntos Pickup en sábado
- [DPD Switzerland: Contact us (inglés)](https://www.dpd.com/ch/en/support-centre/your-request/) – teléfono 058 311 13 13, formulario, código P00 y devoluciones con etiqueta
- [DPD Switzerland: DPD Pickup Location (inglés)](https://www.dpd.com/ch/en/dpd-pickup-location/) – más de 1000 puntos, documento con foto y autorización
- [DPD Switzerland: DPD liability for recipients (inglés)](https://www.dpd.com/ch/en/support-centre/liability-overview/recipient/) – reclamaciones a través del remitente
- [DPD Switzerland: DPD liability for senders (inglés)](https://www.dpd.com/ch/en/support-centre/liability-overview/shipper/) – números suizos que empiezan por 060
- [DPD Switzerland: Parcel service Switzerland (inglés)](https://www.dpd.com/ch/en/) – Buchs ZH, red Geopost y calendario festivo 2026
- [DPD Switzerland: IT Security, Fraud (inglés)](https://www.dpd.com/ch/en/security-at-dpd/) – estafas +63/+212, remitentes legítimos @dpd.ch y antiphishing.ch
- [DPD Switzerland: Parcel not delivered, what to do? (inglés)](https://www.dpd.com/ch/en/news/parcel-not-delivered-what-to-do/) – hasta tres intentos y firma obligatoria
- [myDPD: Help (inglés)](https://www.dpdgroup.com/ch/mydpd/api/help?lang=en) – 7 días naturales en Pickup y plazo nacional de 1 día
- [myDPD: English translations (inglés)](https://www.dpdgroup.com/ch/mydpd/api/translations/DPD-CH/en) – 14 dígitos, estados en inglés, código postal y archivo tras 31 días
- [myDPD: Tracking (alemán)](https://www.dpdgroup.com/ch/mydpd/?lang=de) – teléfono móvil verificado para autorización de depósito desde octubre de 2026
- [DPD Schweiz: Paket kaputt angekommen (alemán)](https://www.dpd.com/ch/de/news/paket-kaputt-angekommen-was-tun/) – daños visibles, 7 días naturales y conservar pruebas 3 meses
- [DPD Schweiz: AGB Business, PDF (alemán)](https://www.dpd.com/wp-content/uploads/sites/223/2022/04/202204-AGB-Business-2022-DE.pdf) – entrega a vecino hasta 50 m y plazos orientativos
- [DPD Schweiz: Impressum (alemán)](https://www.dpd.com/ch/de/impressum/) – sin correo electrónico de atención
- [DPD Schweiz: Ombud-PostCom (alemán)](https://www.dpd.com/ch/de/schlichtungsstelle-ombud-postcom/) – órgano suizo de conciliación postal
- [Ombud-PostCom: Vorgehen (alemán)](https://www.ombud-postcom.ch/vorgehen-1) – tasa de CHF 20.–
- [BAZG: Postversand und Kurierverkehr (alemán)](https://www.bazg.admin.ch/de/postversand-interneteinkauf-kurierverkehr-einfuhr-firmen) – IVA suizo 8,1 % / 2,6 % y franquicia de cobro de 5 CHF
- [BAZG: Verzollungskosten (alemán)](https://www.bazg.admin.ch/de/verzollungskosten-und-paketverfolgung) – tasas de despacho del transportista
- [Universal Parcel Scraper: DPD Switzerland (inglés)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dpd) – detección, orden de consulta y datos mostrados en Peek
:::
