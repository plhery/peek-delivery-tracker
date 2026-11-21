---
title: Seguimiento DPD Polonia (DPD Polska): estados y Pickup
description: Rastrea un paquete de DPD Polonia con su código de 14 caracteres: qué significa cada estado, puntos DPD Pickup, reclamaciones y contacto.
slug: dpd-poland-tracking
published: 2026-11-21
updated: 2026-11-21
---

DPD Poland (DPD Polska), con sede en Varsovia, es la filial polaca de paquetería del grupo Geopost, propiedad de la francesa [La Poste](carrier:la-poste). Puedes consultar un envío en la [página Track & Trace de DPD Polonia](https://tracktrace.dpd.com.pl/EN/findParcel) con su número de paquete de 14 caracteres, o pegarlo directamente en el cuadro de arriba. La tienda suele facilitarlo en el correo de confirmación de envío o dentro de tu pedido.

## Número de seguimiento de DPD Polonia: formato y ejemplos

DPD Polonia utiliza códigos de paquete de 14 caracteres: en envíos polacos, 13 dígitos seguidos de una letra, o bien 14 dígitos numéricos dentro de la red europea de DPD. Además, la aplicación DPD Mobile muestra los paquetes vinculados al teléfono o correo electrónico con el que te registraste sin necesidad de teclear el código. Si no lo encuentras, revisa [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

| Ejemplo (inventado) | Qué identifica |
| --- | --- |
| `4829105736491A` | 13 dígitos y una letra: número de paquete propio de DPD Polonia. |
| `13482910573649` | 14 dígitos empezados por `13`: número de paquete numérico de DPD Polonia. |
| `482910573` | Código de admisión de 9 caracteres (*kod nadania*) para remitentes en taquillas: no sirve como número de seguimiento en Peek. |

Catorce dígitos también encajan con [SEUR](carrier:seur), [Chronopost](carrier:chronopost), [Mondial Relay](carrier:mondial-relay), [DPD Germany](carrier:dpd-de), [DPD Switzerland](carrier:dpd-ch), [DPD UK](carrier:dpd-uk) o [BRT](carrier:brt), así que comprueba qué transportista menciona el vendedor. Si el portal responde `There is no trace for this parcel`, el código tiene un error o aún no ha entrado en el sistema: DPD recomienda volver a probar a la mañana siguiente del envío.

## Estados de DPD Polonia en inglés: qué significa cada uno

En cualquier idioma distinto del polaco, Peek lee la versión en inglés del localizador de DPD Polonia. Estos son sus estados habituales en orden cronológico:

| DPD Polonia muestra | Qué significa para tu envío |
| --- | --- |
| `Registered parcel data, parcel not dispatched yet` | Datos registrados («Etiqueta creada»): DPD todavía no tiene el paquete (igual que `Collection request created` y `Collection request dispatched to Courier`). |
| `Parcel dropped-off in pickup point` | El remitente depositó el paquete en una tienda o taquilla DPD Pickup («Admitido por el transportista»). |
| `Parcel collected by courier` | Recogido por el repartidor en origen, o traspasado en ruta si ya hubo un escaneo previo en almacén. |
| `Parcel received by DPD depot` | Escaneado en una delegación de DPD («En tránsito»; `Parcel stored in depot` indica estancia breve en nave). |
| `Sent outside Poland` | Ha salido de Polonia hacia otro país de la red Geopost/DPD encargado de la entrega. |
| `Parcel dispatched to be delivered` | En reparto con el mensajero («En reparto»). |
| `Parcel not delivered - recipient not available` | Intento fallido por ausencia («Intento de entrega»; `wrong address` señala dirección errónea). |
| `Parcel dispatched to be collected by pickup point` | De camino al punto de recogida o taquilla que lo guardará. |
| `Parcel collected by pickup point` | Recibido en la tienda DPD Pickup: ya puedes pasar a recogerlo («Listo para recoger»). |
| `The Parcel has been placed in the DPD Pickup Station` | Depositado en una taquilla automática DPD Pickup («Listo para recoger», igual que `To be collected personally`). |
| `Waiting for pickup timeout` | Venció el plazo de recogida («Incidencia») y el paquete vuelve al remitente. |
| `Parcel return` | Devolución al remitente bajo un número nuevo («Devuelto al remitente»). |
| `Parcel delivered` | Entregado («Entregado»). |

Ojo con el término inglés *collected*: en `Parcel collected by pickup point` significa que el punto de recogida ha recepcionado el paquete del mensajero, no que alguien se lo haya llevado. `Parcel not delivered - recipient resigned` indica rechazo del destinatario (vuelve a costa del remitente), mientras que `Mail notification`, `SMS notification` y `Sent notification` son avisos enviados por DPD sin movimiento físico.

## Entrega en Polonia, red DPD Pickup y envíos hacia España

**Avisos y cambios.** En Polonia la entrega habitual es al siguiente día laborable (garantizada en 3 días laborables en servicio nacional; los sábados solo si el remitente contrató el suplemento *Sobota*). Con DPD Predict recibes la franja por correo o SMS, y el día de reparto puedes ver al mensajero en el mapa y su teléfono en DPD Mobile. Una vez recogido el envío, DPD Mobile o [Moja Paczka](https://mojapaczka.dpd.com.pl/login) (con número de paquete y PIN) permiten aplazar la entrega hasta 3 días laborables, cambiar dirección, elegir vecino o lugar seguro, desviar a un punto DPD Pickup o rechazarlo. Los envíos de ciertos remitentes (como Temu, AliExpress y Shein) solo admiten desvío a un punto DPD Pickup dentro de la ruta del repartidor.

**Puntos y taquillas DPD Pickup.** DPD suma más de 35.000 puntos y taquillas en Polonia (incluidas más de 12.500 estaciones automáticas y comercios como Żabka, Lidl o Auchan). El paquete aguarda **2 días laborables completos**, sin opción de prórroga antes de devolverse. Para abrir una taquilla basta con DPD Mobile o teclear en el panel el código de recogida recibido por correo (otra persona puede usarlo si no hay que firmar documentos). Si el punto elegido está lleno (`Not delivered to pickup point - overloaded`), DPD lo desvía a uno cercano activo y te avisa. Los envíos *Allegro Delivery* solo se ven en las herramientas de Allegro, no en DPD Mobile. Más detalles en [entrega fallida](guide:missed-delivery).

**Paquetes desde Polonia hacia España.** Tras `Sent outside Poland`, la entrega en España pasa al socio del grupo Geopost —[SEUR](carrier:seur)— con el mismo código de 14 cifras. Entre Polonia y **España peninsular y Baleares** no se pagan aranceles aduaneros gracias a la unión aduanera de la UE; ten en cuenta que Canarias queda fuera del ámbito del IVA comunitario (aplica IGIC) y Ceuta y Melilla están fuera tanto del territorio aduanero como del área de IVA de la UE. Consulta [paquetes retenidos en aduanas](guide:customs) si tu envío cruza esas fronteras fiscales.

## Paquete de DPD Polonia retrasado, perdido o dañado

El contrato lo mantiene el remitente (la tienda), aunque el consumidor con derecho a disponer del envío también puede reclamar según las condiciones de DPD Polonia:

- **Sin avances o pérdida**: DPD da un paquete por perdido si no llega en los **30 días** posteriores al vencimiento del plazo del servicio. Mientras tanto, escribe al vendedor y consulta [por qué el seguimiento no se actualiza](guide:tracking-not-updating) y [paquete perdido](guide:lost-parcel).
- **Daños**: si el daño es visible al recibirlo, levanta acta ese mismo día con el repartidor o en el [parte electrónico de daños](https://protokol.dpd.com.pl/) adjuntando fotos; los daños ocultos deben comunicarse en un máximo de **7 días** desde la entrega. El acta por sí sola no abre reclamación: hay que enviarla después mediante el formulario de *Moje DPD* en dpd.com.pl.

DPD resuelve en un máximo de **30 días naturales** desde que la reclamación está completa. En servicios postales, el consumidor puede acudir al Presidente de la UKE (regulador polaco de comunicaciones). Si figura entregado y no lo tienes, revisa [entregado pero no recibido](guide:delivered-not-received).

## Teléfono y atención al cliente de DPD Polonia

- **Contact Center**: `801 400 373` o `22 577 55 55` (`+48 22 577 55 55` desde fuera de Polonia, según tarifa de tu operador), de lunes a viernes de 9:00 a 17:00 y sábados de 8:00 a 14:00 hora polaca (sus páginas de contacto no garantizan atención en español ni en inglés).
- **Soporte técnico de taquillas DPD Pickup**: `+48 22 571 52 52`, de lunes a viernes de 8:00 a 20:00 y sábados de 8:00 a 14:00.
- **Correo y chat**: las páginas de contacto de DPD Polonia no publican correo de atención ni chat en vivo.

> DPD Polonia alerta sobre SMS y correos falsos que piden recargos por exceso de peso o error en la dirección: DPD nunca envía enlaces de pago por SMS ni por correo electrónico. Si hiciste clic, cambia tus contraseñas, avisa a tu banco e informa al Contact Center.

## Seguir un paquete de DPD Polonia con Peek

[Peek](/) identifica al instante como DPD Poland cualquier código de 13 dígitos y una letra (`4829105736491A`). Con 14 dígitos empezados por `13` (`13482910573649`), comprueba los transportistas compatibles priorizando a DPD Poland y muestra «DPD Poland tiene este paquete.» cuando su sistema reconoce el envío; en otros códigos de 14 cifras puedes elegir DPD Poland pulsando «Cambiar» o «Cambiar transportista». También admite enlaces de `tracktrace.dpd.com.pl` o `tt.dpd.com.pl` (`p1=`) y de `dpd.com/pl` (`parcelNumber=`), pero rechaza los códigos de envío de 9 cifras («no emite números como este»).

Peek lee la página inglesa de DPD Polonia y muestra el estado, el historial y la hora de entrega. Las filas con código de delegación o punto Pickup polaco se leen en hora de Varsovia (`Europe/Warsaw`) y se convierten a tu zona («Horas en tu zona horaria: {{zone}}.»); las filas sin código polaco (como el alta inicial o escaneos en otro país) conservan su hora local sin zona asumida. El buscador no informa ciudad del escaneo, fecha prevista, remitente ni peso, y Peek descarta el nombre del destinatario, la dirección del punto y el número de bultos. Peek no continúa el seguimiento automáticamente tras `Sent outside Poland` ni guarda el nuevo número generado en `Parcel return`.

Mientras mantienes el paquete abierto en pantalla, o el día de reparto con notificaciones activas, Peek consulta el seguimiento directo hasta cada 10 minutos, y hasta cada 2 minutos cuando pasa a «En reparto» (ese día desde las 6:00 hora local mientras responda directamente el portal de DPD Polonia); el resto del tiempo, y tras dos días sin novedades (12 horas en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Mediante servicios de seguimiento comprueba hasta cada 15 minutos ese día en «En reparto» bajo las mismas condiciones, y si no cada hora, o cada 3 horas tras un día sin cambios. Puedes activar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre el seguimiento de DPD Polonia

### ¿Cuántos días guarda DPD Polonia un paquete en un punto o taquilla Pickup?

Dos días laborables completos, sin posibilidad de ampliar el plazo. La fecha límite exacta aparece en el aviso de DPD y en DPD Mobile; después se devuelve al remitente.

### ¿Qué quiere decir `Parcel collected by pickup point`?

Que el comercio o punto Pickup ha recibido tu envío de manos del repartidor y ya está «Listo para recoger» por ti, no que otra persona lo haya retirado.

### ¿Hace falta código PIN para rastrear un envío de DPD Polonia?

No: tanto `tracktrace.dpd.com.pl` como Peek solo piden el código de 14 caracteres. El PIN únicamente se usa en Moja Paczka para cambiar opciones de entrega.

### ¿Cómo sigo mi pedido cuando aparece `Sent outside Poland`?

Introduce el mismo número de 14 cifras en el transportista de Geopost del país de destino —por ejemplo, en [SEUR](carrier:seur) si viaja a España.

:::sources
- [DPD Polska: FAQ (polaco)](https://www.dpd.com/pl/pl/najczesciej-zadawane-pytania-faq/) – códigos de 14 caracteres, entrega en 1–3 días laborables, cambios y custodia de 2 días laborables
- [DPD Polska: FAQs in English](https://www.dpd.com/pl/en/frequently-asked-questions-faq/) – límites en envíos Temu/AliExpress/Shein y teléfono de taquillas +48 22 571 52 52
- [DPD Polska: Dział obsługi klienta](https://www.dpd.com/pl/pl/o-dpd/kontakt-2/dzial-obslugi-klienta/) – teléfonos 801 400 373 y 22 577 55 55 y horarios
- [DPD Polska: Track the parcel](https://tracktrace.dpd.com.pl/EN/findParcel) – consulta sin PIN y respuesta ante números desconocidos
- [DPD Polska: Moja Paczka](https://mojapaczka.dpd.com.pl/login) – gestión de la entrega con código de paquete y PIN
- [DPD Polska: Receive your parcel](https://www.dpd.com/pl/en/receive-your-parcel/) – avisos por correo y SMS con DPD Predict
- [DPD Polska: DPD Pickup](https://www.dpd.com/pl/pl/dpd-pickup/) – más de 35.000 puntos y taquillas (Żabka, Lidl, Auchan)
- [DPD Polska: DPD Pickup parcel lockers](https://www.dpd.com/pl/en/dpd-polska-parcel-lockers/) – código de recogida en teclado y nota sobre Allegro Delivery
- [DPD Polska: Submit a claim](https://www.dpd.com/pl/en/moje-dpd/submit-a-claim-at-dpd/) – pérdida tras 30 días y resolución en 30 días
- [DPD Polska: Elektroniczny protokół szkody](https://protokol.dpd.com.pl/) – parte de daños en línea
- [DPD Polska: Fałszywe SMS-y i e-maile](https://www.dpd.com/pl/pl/o-dpd/falszywe-maile/) – advertencia frente a enlaces de pago falsos
- [DPD Polska: O DPD Polska](https://www.dpd.com/pl/pl/o-dpd/o-dpd-polska/) – integración en Geopost y La Poste
- [DPD Polska: Szczegółowe Warunki Świadczenia Usług](https://www.dpd.com/wp-content/uploads/sites/260/2026/08/SWSU_01032026_PL.pdf) – servicio de sábado y desvío cuando un punto Pickup está lleno
- [DPD Polska: Regulamin w obrocie krajowym](https://www.dpd.com/wp-content/uploads/sites/260/2025/01/RUK_01.01.2024PL.pdf) – legitimación para reclamar, rechazos, plazo de 7 días en daños ocultos y UKE
- [Unión Europea: La unión aduanera de la UE en acción](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_es) – ausencia de aranceles entre países miembros de la UE
- [Comisión Europea: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – régimen aduanero y de IVA de Canarias, Ceuta y Melilla
- [Universal Parcel Scraper: DPD Poland](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dpd-pl) – detección de números, husos horarios y clasificación de estados
:::
