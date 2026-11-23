---
title: Seguimiento Latvijas Pasts: números, estados y taquillas
description: Sigue un envío de Latvijas Pasts (correos de Letonia): formato del número, significado de cada estado, taquillas, entregas fallidas y contacto.
slug: seguimiento-latvijas-pasts
published: 2026-11-23
updated: 2026-11-23
---

Latvijas Pasts es el operador postal estatal de Letonia y cuenta con la mayor red de taquillas de paquetes del país. Puedes seguir un envío en [mans.pasts.lv/track](https://mans.pasts.lv/track?locale=en-GB), que se abre en letón pero incluye selector de idioma en español e inglés, o pegar el número en el recuadro superior. Encontrarás el número en el correo de envío de la tienda o del remitente, o en el SMS que envía el mensajero antes de pasar.

## Número de seguimiento de Latvijas Pasts

Un envío depositado en Latvijas Pasts lleva un [número postal internacional](guide:tracking-number-formats) de 13 caracteres: dos letras, nueve cifras y `LV` al final. Las dos primeras letras indican el servicio:

| Prefijo (ejemplos inventados) | Tipo de envío |
| --- | --- |
| `RR482917367LV` | Envío certificado (Standard +): se entrega bajo firma. |
| `LA482917367LV` | Envío con seguimiento (Standard): registrado en cada etapa, se deja en el buzón sin firma. |
| `CA482917367LV` | Paquete: recibes un aviso para recogerlo en la oficina postal. |
| `EE482917367LV` | Express Mail Service (EMS / Premium). |

Los envíos Simple o Economy no se registran en ninguna fase, por lo que no tienen seguimiento ni indemnización por daños. Si no tienes código, es posible que se enviara así; consulta [cómo encontrar un número de seguimiento](guide:find-tracking-number).

Dónde mirar: el SMS del mensajero incluye el número de envío, la hora estimada y el enlace para redirigir el paquete. El SMS de taquilla indica la dirección, el plazo de recogida y el código de apertura.

Otras letras finales corresponden a otros correos: `…LT` es [Lietuvos paštas](carrier:lietuvos-pastas) y `…EE` es [Omniva](carrier:omniva). Un paquete procedente del extranjero conserva su número original y mans.pasts.lv/track también lo acepta cuando Latvijas Pasts recibe el envío o sus datos; los escaneos extranjeros muestran el país en lugar de una oficina letona.

## Qué significan los estados de Latvijas Pasts

El rastreador oficial muestra cada paso con fecha, hora y oficina postal letona. En los textos en inglés que lee Peek, estos son los mensajes habituales, en su orden normal:

| Estado de Latvijas Pasts | Qué significa para ti |
| --- | --- |
| `Electronic data about shipment received` | [Los datos del remitente](guide:tracking-statuses) han llegado a Latvijas Pasts, pero aún no tiene el paquete. |
| `Item information received` | El remitente ha registrado el envío; Latvijas Pasts todavía no lo ha recibido. |
| `Item received from sender` | Latvijas Pasts ya tiene el envío. |
| `Item posted / received from customer` | Depositado en una oficina postal; el escaneo de admisión de un correo extranjero usa el mismo texto. |
| `Item sent to the processing facility` | Salió de la oficina de depósito hacia el centro de clasificación. |
| `Item arrived at office of exchange` | Llegó a la oficina de cambio internacional, en Riga o en el extranjero. |
| `Item sent to the Customs` | Presentado a la aduana en la oficina de cambio al salir del país de origen. |
| `Item returned from Customs` | Liberado por la aduana: no significa devolución al remitente. |
| `Item sent abroad` | Salió de la oficina de cambio hacia el país de destino. |
| `Item sent abroad (EDI-received)` | El correo extranjero despachó el envío hacia Letonia; Latvijas Pasts lo sabe por los datos electrónicos. |
| `Item accepted for temporary storage` | Envío del extranjero guardado temporalmente bajo control aduanero hasta completar el despacho. |
| `Electonic notification (SMS) of customs clearance sent to recipient` | Te enviaron un SMS sobre el despacho aduanero (la errata es del propio Latvijas Pasts). Sigue en depósito temporal. |
| `Customs clearance completed, item to be delivered to recipient` | Aduana superada; pasa a la oficina que lo entregará. |
| `Item arrived at processing facility`, `Item arrived at sorting centre` | Llegó a un centro de clasificación. |
| `Item sent to next processing facility` | En camino al siguiente centro de clasificación. |
| `Item sent to delivery facility` | En camino a la oficina de entrega o recogida. |
| `Item received at delivery office` | En la oficina de reparto; desde allí aún puede salir a domicilio. |
| `Item redirected` | Redirigido a otro destino, por ejemplo otro punto de recogida (consulta la redirección más abajo). |
| `Item address re-labelled` | Reetiquetado para su nueva dirección, hacia donde continúa. |
| `Item handed over for delivery` | En reparto. |
| `Unsuccessful delivery attempt` | Intento de entrega fallido (ver abajo). Si coincide en el mismo segundo con `Item address re-labelled`, registra una redirección: el envío sigue su curso y Peek muestra «En tránsito», no «Intento de entrega». |
| `Notification - pick-up invitation printed`, `Electronic notification (SMS) - pick-up invitation sent to recipient` | Listo para recoger: la oficina ha impreso el aviso de recogida o te lo ha enviado por SMS. |
| `Item arrived at pick-up point` | Escaneo de un correo extranjero: espera en un punto de recogida en el exterior (muestra el país, no el punto concreto). |
| `Item delivered` | Entregado, también cuando el correo del país de destino entrega un envío salido de Letonia. |

`Item returned from Customs` suele asustar, pero indica que el paquete ha salido del control aduanero y sigue adelante, no que vuelva al remitente. `Item awaiting next planned cycle` significa que espera la siguiente expedición en la oficina de cambio. Los paquetes de tiendas que pasan por un centro de tránsito usan además textos propios (`Parcel notify received`, `Parcel data received`, `Parcel has arrived at transit location`, `Parcel sent to destination country`, `Parcel has arrived in last mile`, `Parcel has gone thought last mile customs`). Para aranceles y tasas de la UE en compras fuera de la UE, consulta [paquetes retenidos en aduanas](guide:customs).

Si el buscador responde `No information received on the tracking of the postal item. Please, try again!`, revisa el número o espera: puede que aún no haya sido escaneado, y los envíos extranjeros aparecen cuando el correo de origen envía sus datos. `Postal item number entered incorrectly. Please check it and enter it again.` indica un error al escribirlo. Si un número correcto sigue sin aparecer durante varios días, pregunta al remitente si contrató seguimiento: Simple y Economy nunca figuran.

## Taquillas, mensajería y entregas fallidas

**Taquillas (pakomāti).** Al llegar a la taquilla recibes un SMS con la dirección, la fecha límite y un código de seis dígitos (y un correo si el remitente incluyó tu dirección). Se guarda durante **7 días naturales**; al tercer día se reenvía el código si sigue sin recogerse, y llega un recordatorio antes de vencer el plazo. Pasados siete días, se considera no entregado y se devuelve al remitente, salvo que el envío tenga un plazo total de custodia más largo, en cuyo caso se traslada al punto de atención al cliente más cercano durante **30 días naturales**. Si nunca recibiste el código, pide al remitente que contacte con Latvijas Pasts: el teléfono solo se corrige a petición del remitente. Admiten hasta 30 kg y 38 × 38 × 58 cm (hasta 60 cm de largo en taquillas exteriores).

**Taquillas Unisend.** Un paquete enviado desde Letonia a una taquilla Unisend en Lituania o Estonia también se guarda 7 días naturales, con código de seis dígitos enviado por SMS y correo electrónico (a números letones, lituanos o estonios) y reenviado a las 48 horas. Si no se recoge, vuelve directamente al remitente.

**Mensajero a domicilio.** Latvijas Pasts indica que realiza hasta tres intentos de entrega. Si no estás y no redirigiste el paquete, el mensajero deja aviso y vuelve a intentarlo; si vuelve a fallar, queda en depósito y puedes recogerlo en la oficina principal o acordar otra opción con atención al cliente (el remitente fija el plazo de custodia). Si se pierde una entrega Express Mail (EMS), queda un aviso «You have received a shipment» en el buzón: llama al teléfono indicado con el número de envío a mano.

**Oficina postal o Customer Centre.** Recibirás un SMS o un aviso escrito indicando la oficina exacta. Lleva un documento de identidad válido; el permiso de conducir sirve para envíos certificados o asegurados de hasta 150 €. Puedes recogerlo sin el aviso si conoces el número de envío, o llamar al número del aviso para solicitar entrega a domicilio abonando la tarifa al recibirlo. Otra persona puede recogerlo con tu autorización y su pasaporte o documento de identidad. En envíos certificados o asegurados se manda un segundo aviso a los 5 días, y el envío regresa al remitente a los 30 días del primer aviso. Si la etiqueta de una tienda extranjera solo incluye parte de tu nombre, presenta tu documento y un justificante de compra.

**Redirección.** Si el remitente dio tu teléfono o estás suscrito a avisos por SMS, el mensaje incluye un enlace para redirigir el envío a una taquilla (0,00 €, IVA incluido), un punto de atención al cliente (1,89 €, IVA incluido), otra dirección por mensajero (4,99 €, IVA incluido) o el servicio gratuito «Postman at home» donde esté disponible. No se puede redirigir un paquete que ya está en una taquilla ni bajo control aduanero, ni mandar a taquilla un certificado o asegurado con prueba de entrega, un contrarreembolso mayor de 38 × 38 × 58 cm o envíos sin teléfono. Al registrarte para avisos por SMS también puedes fijar una taquilla favorita.

## Paquete de Latvijas Pasts perdido, dañado o retrasado

En taquillas, solo el remitente puede presentar la reclamación por pérdida o daño (dentro de un plazo de 6 meses según las condiciones de taquillas), así que si compraste en una tienda, escríbele. Debe detallar el contenido y su valor con justificante (factura, recibo o comprobante bancario) y fotos si hay daños; Latvijas Pasts resuelve en un mes, y el remitente puede ceder la indemnización al destinatario.

En envíos certificados o asegurados entregados por mensajero, revisa el contenido delante del repartidor para firmar un acta. Si lo abres solo y detectas daños, haz fotos y reclama a la tienda un reemplazo o reembolso; Latvijas Pasts señala que la indemnización deja de ser responsabilidad suya.

Si un envío al extranjero no llega, el remitente puede solicitar una búsqueda internacional: es de pago antes de cumplir un mes y gratuita pasados 30 días desde el envío.

¿Seguimiento parado o retenido en aduana? Consulta [seguimiento que no se actualiza](guide:tracking-not-updating) y [paquetes retenidos en aduanas](guide:customs).

## Contacto y atención al cliente de Latvijas Pasts

Canales publicados en pasts.lv:

- **Teléfono:** +371 27008001 (también +371 67008001 en las condiciones de taquillas).
- **Correo electrónico:** info@pasts.lv.
- **Horario:** de lunes a viernes de 8:00 a 19:00, sábados de 9:00 a 17:00, domingos cerrado.
- **Notificar una incidencia:** formulario en [mans.pasts.lv/complaint](https://mans.pasts.lv/complaint), atendido en horario de soporte.

> Latvijas Pasts alerta sobre SMS y correos falsos que piden confirmar tu dirección o pagar aduanas en webs imitadoras, así como supuestas ventas de «paquetes no reclamados». Sus correos siempre acaban en @pasts.lv, su web oficial es pasts.lv y nunca solicita datos bancarios o de tarjeta por SMS o correo ni confirmaciones con Smart-ID.

## Seguir Latvijas Pasts con Peek

[Peek](/) asigna de inmediato a Latvijas Pasts cualquier número postal que empiece por C, E, L o R y termine en `LV` si su dígito de control es válido. Para otros códigos válidos terminados en `LV`, como `VV482917367LV`, consulta a la vez a Latvijas Pasts y a [Chronopost](carrier:chronopost) y muestra «Latvijas Pasts tiene este paquete.» si lo reconoce, o «Aún no aparece en … Peek seguirá comprobando cuando lo añadas.». Si el dígito de control es incorrecto, no consulta a Latvijas Pasts y elegirlo a mano tampoco funciona (Peek indica que «no emite números como este»): confirma el código con el remitente. Un enlace a mans.pasts.lv/track con el número siempre selecciona Latvijas Pasts, y buscar «Latvian Post» en el selector también lo encuentra. Si Latvijas Pasts aún no reconoce el número, aparece «Esperando a Latvijas Pasts»; si dos días después sigue sin respuesta, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobar a diario y, pasada una semana, una vez por semana.

Un paquete del extranjero se sigue primero en su correo de origen. Cuando ese correo indica Letonia como destino (o no indica destino y estás en Letonia), Peek consulta también a Latvijas Pasts con el mismo número y cambia a él en cuanto aparece un escaneo nuevo, conservando el historial previo. Para seguirlo directamente en Latvijas Pasts, elige «Latvijas Pasts» en «Cambiar» al añadirlo o en «Cambiar transportista» si ya está guardado.

Las horas de Latvijas Pasts no llevan zona horaria: Peek las interpreta en la hora local del lugar del escaneo (hora de Letonia en oficinas letonas) y mantiene los textos en inglés de Latvijas Pasts, incluido `Item delivered`. Puede mostrar la oficina de recogida donde espera el envío, pero no ofrece fecha estimada ni peso.

Peek comprueba hasta cada 10 minutos mientras tienes el paquete abierto en Peek o el día de la entrega con avisos activos; el día en que Latvijas Pasts saca el paquete a reparto, a partir de las 6:00 hora local (hora letona para oficinas letonas), comprueba cada 2 minutos mientras el rastreador propio de Latvijas Pasts responda. Si no, comprueba cada hora. Tras 12 horas en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora incluso con el paquete abierto, y cada 6 horas cuando pasan siete días en «Etiqueta creada». Si el rastreador de Latvijas Pasts no responde y Peek debe acudir a servicios de seguimiento, comprueba cada hora, cada 3 horas cuando pasa un día sin nuevos escaneos y, el día del reparto, hasta cada 15 minutos mientras el paquete está abierto o tiene avisos activos. Si otro transportista asume la entrega, Peek adopta su ritmo.

Si pasan cuatro días sin ningún escaneo nuevo en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día del último escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». Verás el aviso «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Latvijas Pasts.» junto al enlace «Pide ayuda a Latvijas Pasts». Si pasan más de tres semanas sin cambios, Peek muestra: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Latvijas Pasts.»

Para los avisos puedes elegir entre «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre Latvijas Pasts

### ¿Qué es Latvijas Pasts?

Es el correo nacional estatal de Letonia, que ofrece servicios postales, de paquetería y financieros dentro del país y hacia el extranjero desde pasts.lv.

### ¿Unisend es Latvijas Pasts?

No. Unisend es la marca de paquetería de Lietuvos paštas, el correo lituano. Ambos operan una red compartida de taquillas para enviar desde Letonia a taquillas Unisend en Lituania y Estonia.

### ¿Puedo seguir un envío internacional de Latvijas Pasts?

Sí, si se envió con seguimiento. mans.pasts.lv/track muestra también los escaneos del correo extranjero indicando el país en vez de una oficina, e incluye un botón «Pārrobežu izsekošana» que lleva a un rastreador externo. En el país de destino entrega el correo local.

### ¿El correo ordinario desde Letonia tiene seguimiento?

No siempre. Los envíos Simple y Economy no están registrados y no se pueden rastrear; los certificados, Standard con seguimiento, paquetes y EMS sí.

:::sources
- [Latvijas Pasts: Customer support](https://pasts.lv/en/client-support) – prefijos por servicio, exclusión de indemnización Simple, búsqueda internacional, horarios, teléfono
- [Latvijas Pasts: Contacts](https://pasts.lv/en/about-us/contacts) – teléfono, horarios
- [Latvijas Pasts: Receive a parcel](https://pasts.lv/en/private-person/receive-parcel) – formas de recepción, autorización
- [Latvijas Pasts: Receive by courier](https://pasts.lv/en/private-person/receive-parcel/receive-by-courier) – SMS del mensajero, tres intentos, depósito, oficina principal, Postman at home
- [Latvijas Pasts: Receive item in a parcel locker](https://pasts.lv/en/useful/receiving-items-private/receive-in-parcel-locker) – SMS de taquilla, siete días, recordatorio, 30 kg y 38 × 38 × 58 cm, taquilla favorita
- [Latvijas Pasts: Delivery to address](https://pasts.lv/en/useful/receiving-items-private/delivery-to-address) – segundo aviso a los 5 días, devolución a los 30 días, aviso EMS, nombre incompleto en tienda extranjera
- [Latvijas Pasts: Receive at a Customer Centre](https://pasts.lv/en/private-person/receive-parcel/receive-at-customer-centre) – avisos, identificación, carné de conducir hasta 150 €, recogida sin aviso, entrega a domicilio con tarifa
- [Latvijas Pasts: Shipment redirection](https://pasts.lv/en/services/private-person/receiving/redirection-of-shipments) – enlace de redirección, opciones, tarifas IVA incluido, exclusiones
- [Latvijas Pasts: Lost or damaged parcel locker shipments](https://pasts.lv/en/services/private-person/shipment-management/lost-or-damaged-parcel-locker-shipments) – reclamación por el remitente, justificantes, revisión en un mes
- [Latvijas Pasts: Damaged shipment](https://pasts.lv/en/useful/faulty-shipment) – comprobación ante el mensajero, reclamación al remitente
- [Latvijas Pasts: Pakomātu pakalpojumu noteikumi](https://pasts.lv/media/789/download/Pakomatu_noteikumi.pdf) – código de seis cifras, correo, reenvío al 3.er día, devolución tras 7 días o 30 días en oficina, 6 meses para reclamar, taquillas Unisend, info@pasts.lv y +371 67008001
- [Latvijas Pasts: Pasargā sevi no krāpniekiem](https://pasts.lv/pasarga-sevi-no-krapniekiem) – aviso sobre SMS y correos fraudulentos
- [Latvijas Pasts: Mans pasts, track shipment](https://mans.pasts.lv/track?locale=en-GB) – buscador, selector de idioma, seguimiento internacional
- [Latvijas Pasts: Complaint form](https://mans.pasts.lv/complaint) – formulario de incidencias
- [Latvijas Pasts: About us](https://pasts.lv/en/about-us/par-pastu) – operador postal estatal, servicios
- [Latvijas Pasts: Joint parcel locker network with UNISEND](https://pasts.lv/en/news/latvijas-pasts-and-unisend-launch-their-joint-parcel-locker-network) – Unisend como marca de Lietuvos paštas, red conjunta
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – datos leídos y mostrados por Peek, estados y etapas, validación de números, cambio desde correo extranjero
:::
