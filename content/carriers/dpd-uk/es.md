---
title: Seguimiento de DPD UK: número de 14 dígitos y estados
description: Rastrea un paquete de DPD UK: cómo es el número de 14 dígitos, qué significan sus estados en inglés, opciones Predict y Pickup en Reino Unido y aduanas.
slug: seguimiento-dpd-uk
published: 2026-11-21
updated: 2026-11-21
---

DPD UK (`DPDgroup UK Ltd`) es la empresa británica de paquetería del grupo Geopost, perteneciente al grupo francés [La Poste](carrier:la-poste) (*La Poste Groupe*). En el **Reino Unido** reparte pedidos de numerosas tiendas online y también envía paquetes hacia Europa y otros destinos internacionales. Puedes consultar un envío en [Follow My Parcel de DPD UK](https://track.dpd.co.uk/) con el número de referencia y el código postal de entrega, o pegar directamente el número de 14 dígitos en el cuadro de arriba. El código te llega desde la tienda, en el SMS o correo electrónico de DPD UK o junto al código de barras de la tarjeta de aviso (*sorry we missed you*).

## ¿Dónde está el número de seguimiento de DPD UK y qué formato tiene?

El comercio remitente tiene el número de paquete; también puedes abrir el enlace del SMS o del correo de DPD UK. En el **Reino Unido**, si el repartidor dejó una tarjeta *sorry we missed you*, el número aparece al lado del código de barras, y la web `track.dpd.co.uk` admite igualmente el número de esa tarjeta (*Calling card number*) junto con el código postal. ¿No das con él? Mira [cómo encontrar el número de seguimiento](guide:find-tracking-number).

Las páginas de ayuda de DPD UK no detallan el formato oficial del código. Los números de paquete que se ven en el localizador de DPD UK tienen 14 cifras, a menudo empezando por `1550` o `1597`, y a veces la página muestra al final un decimoquinto carácter de control (letra o cifra):

| Número (inventado) | Qué identifica |
| --- | --- |
| `15504829105710` | Número de paquete de DPD UK: 14 dígitos que empiezan por `1550` (o `1597`). |
| `15504829105710S` | El mismo número con el carácter de control de DPD añadido al final. |
| `18429105736482` | Otros 14 dígitos: formato compartido entre DPD UK, otras filiales de DPD y otros transportistas. |

Otros operadores usan también 14 cifras —como [SEUR](carrier:seur), [BRT](carrier:brt), [Chronopost](carrier:chronopost), [DPD Alemania](carrier:dpd-de) y DHL eCommerce UK—, así que las cifras por sí solas no siempre bastan para saber quién lleva tu envío. En el Reino Unido, los SMS auténticos muestran como remitente `DPD UK` (o `DPD Local` / `DPDgroup UK`) e incluyen el nombre de la tienda dentro del texto. DPD Local es la marca hermana de DPD UK (la app de DPD muestra envíos de ambas). En **Irlanda**, los paquetes viajan con DPD Ireland, que utiliza su propia numeración.

## Qué significan los estados de seguimiento de DPD UK

El seguimiento de DPD UK solo está en inglés. Estas son las líneas más habituales en el orden normal del envío:

| DPD UK muestra | Qué significa para tu paquete | Peek muestra |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | La tienda ha registrado el envío, pero DPD UK aún no tiene el paquete. | «Etiqueta creada» |
| `We've received your order details, and we're expecting your parcel shortly` | La etiqueta está creada y DPD UK espera que el remitente entregue el paquete en una tienda DPD Pickup Shop. | «Etiqueta creada» |
| `Your parcel has been dropped off at a DPD Pickup Shop` | El remitente ha depositado el paquete en un punto DPD Pickup Shop. | «Admitido por el transportista» |
| `We have your parcel and it's on its way to our depot` | DPD UK ha recogido el paquete y lo lleva a su delegación. | «Admitido por el transportista» (recogida directa) o «En tránsito» (tras depósito en Pickup Shop) |
| `We have your parcel and it's on its way to you` | El paquete viaja por la red de DPD UK hacia tu zona. | «En tránsito» |
| `Your parcel has arrived at our depot`, `Your parcel is at our depot` | En una delegación de DPD UK, en clasificación. | «En tránsito» |
| `Your parcel is at our depot ready for delivery` | En la delegación, preparado para salir a reparto; más tarde la misma línea vuelve a leerse `Your parcel is at our depot`. | «En tránsito» |
| `A notification has been sent to advise you when the delivery will take place` | DPD UK te ha enviado el SMS o correo con el día o la franja de entrega. | Se mantiene el estado anterior |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | Has cambiado la entrega a otro día (a veces con hora); la fecha y la hora varían. | Se mantiene el estado anterior |
| `Your parcel will be with you today`, `Your parcel will be with you today between 14:18 and 15:18` | En reparto hoy, con la franja de una hora cuando está disponible (las horas cambian). | «En reparto» |
| `As requested, your parcel will now be left in your selected safe place` | Tú (o tus preferencias en la app de DPD) habéis elegido un lugar seguro donde dejarlo. | Se mantiene el estado anterior |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | No se pudo entregar y volverán el día indicado. Peek omite el nombre del conductor que incluye la frase de DPD UK. | «Intento de entrega» |
| `Your parcel has been delivered` | Entregado (el encabezado en la web de DPD UK añade hora y fecha). | «Entregado» |
| `Your parcel is waiting for you at home (left in safe place)` | Entregado en el lugar seguro indicado. | «Entregado» |
| `Your delivery has been cancelled` | El remitente ha cancelado el envío; DPD UK pide contactar con la tienda indicada. | «Incidencia» |
| `We have your parcel and it's on its way back to the sender` | Devolución en curso al remitente (salvo antes de pasar por delegación en etiquetas entregadas en un Pickup Shop, donde va rumbo a la cuenta que contrató la etiqueta: «En tránsito»). | «Incidencia» |

`We have X-rayed your parcel as part of our normal security procedures` es un escaneo rutinario de seguridad («En tránsito» en Peek), mientras que `There has been a delay with your delivery` es un aviso de demora que conserva la etapa previa. Cuando DPD UK envía un paquete fuera del **Reino Unido**, `Your parcel has left our sortation facility and is on it's onwards journey` (con la grafía `it's` de DPD UK) indica la salida del centro británico, y después aparecen los estados en inglés del socio de reparto en destino: `The parcel is in transit to its final destination` («En tránsito»), `The parcel is undergoing customs clearance` y `Duties and Taxes paid` («En aduanas»), `The parcel has now cleared customs` y `The parcel has arrived at the delivery depot` («En tránsito»), `The parcel is on the vehicle for delivery` («En reparto») y `The parcel has been delivered` («Entregado»). Más información en [qué significan los estados de seguimiento](guide:tracking-statuses).

## Predict, puntos Pickup en Reino Unido y aduanas hacia España y la UE

Las opciones de Predict, entrega a vecino y tiendas Pickup descritas aquí rigen para entregas dentro del **Reino Unido**:

**Ventana Predict.** En el Reino Unido, DPD UK envía por SMS y correo electrónico una ventana de entrega de una hora el mismo día del reparto, cuando el conductor ha escaneado todos los paquetes en la furgoneta —normalmente antes de las 12 del mediodía (*usually by 12 noon*)—. Un mapa en tiempo real acota después la llegada a un tramo de 15 minutos, y los SMS y correos indican el nombre del repartidor y tu puesto en su ruta. No se puede cambiar la franja horaria en sí.

**Cambiar la entrega.** La noche anterior y el propio día del reparto, el SMS, el correo o la app de DPD permiten en el Reino Unido elegir otra fecha, un vecino designado, un lugar seguro (*safe place*) o una tienda DPD Pickup Shop, o pagar una mejora para recibirlo antes de las 10:30, antes de las 12:00, por la tarde, en sábado o en domingo. Si el remitente exigió firma, algunas opciones no estarán disponibles. Para enviarlo a [otra dirección distinta](guide:change-delivery-address), contacta con el vendedor.

**Si no estás en casa.** En el Reino Unido, DPD UK intenta automáticamente dejar el paquete a un vecino; sus condiciones generales de transporte definen *Neighbour* como quien vive o trabaja en un inmueble a un máximo de 50 metros a pie de la dirección de entrega (*within 50 metres walking distance of the Delivery Address*). El conductor no puede volver el mismo día. Con tu número de referencia y código postal en `track.dpd.co.uk` verás dónde dejó la tarjeta *sorry we missed you* y podrás pedir nueva fecha, lugar seguro, vecino o recogida en tienda Pickup; DPD UK no publica un número fijo de intentos. Si figura como entregado pero no aparece, revisa primero tu lugar seguro y a los vecinos cercanos, y consulta [entregado pero no recibido](guide:delivered-not-received) y [entrega fallida](guide:missed-delivery).

**Tiendas DPD Pickup y taquillas en el Reino Unido.** DPD UK cuenta con más de 6.000 puntos Pickup en el Reino Unido (entre ellos tiendas Sainsbury's, Post Office, Co-op, Morrisons, Argos y Matalan). Un paquete desviado allí suele estar listo al día siguiente. Para recogerlo piden dos documentos (*2 pieces of ID*): un documento con foto y otro con tu dirección, como una factura de suministros, además del *DPD Pickup Pass* disponible en el seguimiento. Las páginas de ayuda de DPD UK no fijan un número concreto de días de custodia en tienda Pickup; la app de DPD ofrece también entrega en taquilla (*Locker*).

**Cargos de importación en el Reino Unido.** Los paquetes que llegan a Gran Bretaña desde fuera del Reino Unido pueden devengar IVA británico (salvo regalos de hasta 39 £) y aranceles, facturados por el transportista (abonables en `track.dpd.co.uk` bajo *import charges*); según GOV.UK, la empresa de reparto suele guardar el paquete unas 3 semanas (*about 3 weeks*) antes de devolverlo si no se paga.

**Paquetes desde el Reino Unido hacia España y la Unión Europea.** Como Gran Bretaña está fuera de la UE, los envíos que llegan desde allí pasan por aduana; Irlanda del Norte, en cambio, se trata como territorio de la UE a efectos de aduanas y de IVA sobre bienes, según la Comisión Europea. En la **Unión Europea**, desde el 1 de julio de 2026 los bienes comprados online fuera de la UE en paquetes con valor intrínseco de hasta 150 € pagan un arancel aduanero de la UE de 3 € por artículo (*per item*), y desde el 1 de noviembre de 2026 se suma una tasa de tramitación de la Unión (*Union handling fee*) de 2 € por artículo cualquiera que sea su valor (un artículo agrupa las mercancías de un mismo envío con igual clasificación arancelaria, descripción y, cuando deba declararse, origen; por encima de 150 € se aplican los aranceles ordinarios), además del IVA de importación en **España peninsular y Baleares**. Según el ámbito territorial publicado por la Comisión Europea, las **Islas Canarias** forman parte del territorio aduanero de la UE pero quedan fuera del ámbito del IVA comunitario, mientras que **Ceuta** y **Melilla** están fuera tanto del territorio aduanero como del área de IVA de la UE. Más detalles en [paquete retenido en aduanas](guide:customs).

## Paquete de DPD UK retrasado, perdido, dañado o mensajes falsos

**Retrasos.** Según las condiciones de transporte de DPD UK, todos los plazos de entrega son estimaciones (*estimates only*). Para repartos en el **Reino Unido**, la página [Service Disruption de DPD UK](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) muestra los códigos postales británicos afectados por incidencias. Si el seguimiento lleva días parado, consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y habla con la tienda.

**Pérdida o daños.** El contrato con DPD UK lo tiene el remitente (*Customer*), que es quien debe presentar la reclamación. Conforme a las condiciones generales de DPD UK, el remitente debe notificar la pérdida o el daño en un plazo de 14 días desde la fecha de expedición (*within 14 days of the date of despatch*), aportar las pruebas solicitadas en 28 días y formalizar cualquier reclamación dentro de un año desde el envío (*within one year from the date of despatch*). Guarda el embalaje, haz fotos y avisa al vendedor: consulta [paquete perdido](guide:lost-parcel) y [paquete dañado](guide:damaged-parcel).

**Devolución al remitente.** Según la política de devolución y disposición de mercancías de DPD UK, si no puede entregar un envío y este permanece en sus instalaciones, DPD UK se reserva el derecho de devolverlo al remitente transcurrido un mínimo de 5 días naturales (*after a minimum of 5 calendar days*); la mercancía que no pueda devolverse ni al destinatario ni al remitente puede destruirse o enajenarse tras 4 semanas (12 semanas si son efectos personales). Mira [devuelto al remitente](guide:returned-to-sender).

> DPD UK advierte de que nunca pide pagar una tasa de nueva entrega (*redelivery fee*) mediante un enlace por correo, SMS o iMessage. En el Reino Unido, sus avisos automáticos llegan por SMS (nunca por iMessage) con el remitente `DPD UK`, `DPD Local` o `DPDgroup UK` —jamás desde una dirección de correo ni un número móvil cualquiera—, sus correos legítimos terminan siempre en `@dpd.co.uk`, `@dpdlocal.co.uk`, `@dpdgroup.co.uk` o `@dpd.uk` y sus enlaces solo usan sus propios sitios: `www.dpd.co.uk`, `www.dpdlocal.co.uk` o `www.dpdgroup.co.uk`, y `track.dpd.co.uk` para seguir un paquete. En el **Reino Unido** puedes reenviar SMS sospechosos al `7726` y denunciar correos falsos en `report@phishing.gov.uk`; consulta también [SMS falsos de entregas](guide:delivery-scam-texts).

## Teléfono y atención al cliente de DPD UK

Canales oficiales de atención de DPD UK en el **Reino Unido**:

- **Teléfono:** `0121 275 0500` (número británico), de lunes a viernes de 8:00 a 18:30, sábados de 8:00 a 16:00 y domingos de 9:00 a 15:00 (hora del Reino Unido), excepto festivos bancarios británicos (*bank holidays*). La página de contacto calcula entre 5 y 10 minutos de espera (la *Complaints Charter* fija como objetivo responder en 2 minutos y 30 segundos). Ten a mano el número de paquete y el código postal de entrega.
- **Chat online:** en la [página de contacto de DPD UK](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) introduciendo el número de referencia y el código postal de entrega; esa página anuncia atención `24/7`, mientras que la *Complaints Charter* de DPD UK recoge el mismo horario que el teléfono (lun.–vie. 8:00–18:30, sáb. 8:00–16:00, dom. 9:00–15:00).
- **App de DPD:** botones de chat y llamada debajo del mapa de seguimiento; DPD UK indica que al llamar desde la app pasas al primer lugar de la cola.
- **Correo postal y reclamaciones:** `Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY`, Reino Unido. Según su *Complaints Charter*, DPD UK busca resolver las quejas en 5 días laborables (hasta 28 días en casos complejos).

## Seguir paquetes de DPD UK con Peek

[Peek](/) reconoce un número DPD de 14 dígitos (con o sin carácter de control final), pero al ser un formato compartido pregunta a los transportistas compatibles cuál tiene el envío: **primero a DPD UK** cuando empieza por `1550` o `1597`, y en los demás casos primero a los transportistas de 14 dígitos que operan en tu país. Cuando DPD UK confirma el paquete, aparece «tiene este paquete» y Peek lo sigue allí. Si ves «aún no encontrado · Peek seguirá comprobando» o se asigna otro operador, pulsa «Cambiar» (o «Cambiar transportista») y elige **DPD UK**. Pegar un enlace de `track.dpd.co.uk` o `tracking.dpd.co.uk` con `reference=` o `parcelNumber=`, o de `track.dpdlocal.co.uk` con `reference=`, selecciona DPD UK al instante. Si un número guardado bajo DPD UK sigue sin figurar ni en DPD UK ni en los servicios de seguimiento, Peek muestra primero «Esperando a DPD UK» y, pasados dos días, «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

Los escaneos de la red propia de DPD UK no incluyen zona horaria: Peek los interpreta en hora británica (`Europe/London`) y los convierte a la zona horaria de tu dispositivo. En peektracker.com y en las apps para iPhone y Android, al pie del historial figura «Horas en tu zona horaria: …». Los estados conservan su redacción original en inglés, sin el nombre del repartidor en los intentos fallidos ni nombres de receptor o firmante tras `delivered to …` o `signed for by …`.

Peek no muestra población ni delegación en los eventos de DPD UK, y no lee la fecha estimada ni la ventana Predict de una hora; descarta identificadores internos, referencias comerciales, datos del destinatario y fotos de entrega. Peek muestra el comercio que contrató el envío como «Remitente: …» tal como aparece en DPD UK, **salvo** cuando el historial indica que el paquete se depositó en un DPD Pickup Shop: en esas etiquetas (como devoluciones a tienda), el titular de la cuenta no es quien envía el paquete y Peek oculta ese dato. En envíos internacionales Peek sigue leyendo el historial de DPD UK sin traspaso automático: si el vendedor te da además un número local, añádelo por separado en Peek.

Mientras mantengas abierto el paquete o, el día del reparto, tengas activadas las notificaciones, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 (hora del Reino Unido) el día en que DPD UK marca el envío «En reparto», mientras responda el localizador directo de DPD UK (solo mediante servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—. En el resto de casos, así como tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta cada hora. Puedes elegir alertas de «Cada escaneo», «Pasos importantes» o «Solo la entrega» / «Solo el día de entrega». Para cambiar opciones de entrega en el Reino Unido usa la web o la app de DPD UK; Peek no puede modificar la entrega ni contactar con DPD UK por ti.

## Preguntas frecuentes sobre el seguimiento de DPD UK

### ¿DPD UK forma parte de Royal Mail?

No. DPD UK está operada por `DPDgroup UK Ltd` y pertenece a Geopost, la división internacional de paquetería del grupo francés [La Poste](carrier:la-poste) (*La Poste Groupe*).

### ¿Cuánto tarda DPD UK en entregar en el Reino Unido?

Depende del servicio contratado por la tienda: en el Reino Unido, `DPD NEXT DAY` entrega al siguiente día laborable antes del cierre comercial (con opciones antes de las 10:30, antes de las 12:00, sábados y domingos). Las Islas del Canal, la Isla de Man y las Islas Sorlingas (*Scilly Islands*) tardan dos días como estándar, e Irlanda del Norte un día por avión o dos por carretera y mar.

### ¿Cómo sé por dónde va mi repartidor de DPD UK?

En entregas dentro del Reino Unido con Predict, el SMS o correo de la mañana del reparto (habitualmente antes de las 12:00) indica el nombre del conductor y tu posición en su cola de entregas, y un mapa en vivo ajusta luego la franja a 15 minutos.

### ¿Cuántos días guarda DPD UK un paquete en una tienda Pickup?

Las páginas de ayuda de DPD UK no publican un plazo único de custodia en sus tiendas Pickup británicas; consulta el aviso de llegada o tu *DPD Pickup Pass* en `track.dpd.co.uk`. Con carácter general, la política de devolución de DPD UK prevé que un envío no entregado almacenado en sus instalaciones pueda devolverse al remitente tras al menos 5 días naturales.

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – número y tarjeta de aviso, ventana antes del mediodía, vecino, más de 6.000 tiendas Pickup y documentos
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – teléfono 0121 275 0500, horarios, espera, chat y llamada desde la app
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – ventana de una hora, mapa a 15 min, conductor y opciones
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – búsqueda con referencia o aviso y código postal, Pickup Pass y cargos de importación
- [DPD UK: Cyber Security](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – remitentes SMS, sin tasas por enlace, dominios, 7726 y report@phishing.gov.uk
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – desvío a punto DPD Pickup y devoluciones
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – preferencias y taquilla (Locker)
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – paquetes de DPD UK y DPD Local en la app
- [DPD UK: Standard Terms & Conditions of Carriage (PDF)](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – vecino a 50 m, plazos estimados y reclamaciones (14 días, 28 días, un año)
- [DPD UK: Complaints Charter (PDF)](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – horarios, dirección en Smethwick y respuesta de 5 a 28 días
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – devolución tras 5 días naturales y custodia de 4 o 12 semanas
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – Next Day, 10:30, 12:00, fin de semana, islas e Irlanda del Norte
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – códigos postales afectados en Reino Unido
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – cobro de tributos y retención de unas 3 semanas
- [Geopost: About us](https://www.geopost.com/en/about-us/) – La Poste Groupe
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € y tasa de 2 €
- [Comisión Europea: Orientaciones sobre el arancel de 3 € y la tasa (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobro por artículo
- [Comisión Europea: Ámbito territorial del IVA y aduanas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias, Ceuta, Melilla e Irlanda del Norte
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – números, reconocimiento, estados, zona horaria y remitente
:::
