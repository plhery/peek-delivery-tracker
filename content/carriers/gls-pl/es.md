---
title: Seguimiento GLS Poland: estados, GLS Points y taquillas
description: Rastrea un paquete de GLS Poland: 11 o 12 dígitos y Track-ID, código postal opcional, estados, GLS Points, taquillas ORLEN y atención al cliente.
slug: seguimiento-gls-poland
published: 2026-11-24
updated: 2026-11-24
---

GLS Poland (*General Logistics Systems Poland Sp. z o.o.*, con sede en Głuchowo cerca de Poznan y centro de clasificación central en Stryków cerca de Łódź) gestiona unas 50 delegaciones y más de 26.000 puntos de recogida fuera de domicilio en toda Polonia, incluidos más de 18.000 GLS Points (entre ellos las 11.000 tiendas de conveniencia Żabka) y unas 8.000 taquillas compartidas 24/7 de [ORLEN Paczka](carrier:orlen-paczka). Sigue tu envío en la [página de seguimiento de GLS Poland](https://gls-group.com/PL/en/parcel-tracking/) con el número de paquete o Track-ID, o pega el número en el cuadro superior.

## Número de seguimiento de GLS Poland y Track-ID: formatos

Encuentra tu número de paquete en el correo de envío de la tienda online, en el aviso por correo/SMS de GLS o en una tarjeta de aviso (*awizo*). El buscador de GLS también acepta el Track-ID incluido en las notificaciones de GLS.

| Formato (inventado) | Qué es |
| --- | --- |
| `48291736405` | Número de paquete GLS de 11 dígitos. También lo utilizan [GLS Alemania](carrier:gls-de), [GLS Francia](carrier:gls-fr), [GLS Suiza](carrier:gls-ch), [GLS Austria](carrier:gls-at) y [PPL](carrier:ppl). |
| `482917364052` | Número de paquete de 12 dígitos acabado en dígito de control. También lo usan [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) y otros. |

En la [página de seguimiento de GLS Poland](https://gls-group.com/PL/en/parcel-tracking/), introducir el código postal del destinatario desbloquea los detalles completos de escaneo y los controles de entrega. Para otros formatos, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de GLS Poland

GLS Poland muestra descripciones en polaco en su web polaca y descripciones en inglés del grupo a través de la API internacional de GLS que lee Peek.

| GLS Poland / GLS Group muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Preadvice`, `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | El remitente generó la etiqueta electrónicamente; GLS todavía no ha recibido el paquete físico. | «Etiqueta creada» |
| `Paczka została przekazana GLS` | GLS recogió o admitió el paquete en su red. | «Admitido por el transportista» |
| `In transit`, `Final parcel center`, `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS` | Viajando por los centros de clasificación de GLS y delegaciones polacas hacia la base de reparto. | «En tránsito» |
| `In delivery`, `Paczka w doręczeniu` | El mensajero cargó tu paquete para entregarlo hoy. | «En reparto» |
| Llegada a un GLS Point o taquilla, que GLS marca como entregado | Esperando recogida en un GLS Point o taquilla 24/7. | «Listo para recoger» |
| `The parcel has been delivered.`, `Paczka została doręczona`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | Entregado a ti, a una persona autorizada o a un vecino, o recogido en un GLS Point. | «Entregado» |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | El destinatario rechazó el paquete. | «Intento de entrega» |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Rechazado y retenido en la delegación de reparto. | «Incidencia» |

El día de reparto, [Live Tracking de GLS Poland](https://gls-group.com/PL/en/live-tracking/) envía un correo con un enlace que ajusta la hora prevista a un margen de 15 minutos y muestra al mensajero en el mapa cuando faltan 20 paradas o menos. La barra de progreso de GLS marca como entregado un paquete dejado en un GLS Point o taquilla mientras te espera; Peek muestra ese paso como «Listo para recoger» y como «Entregado» una vez que lo recoges.

## Plazos de entrega, GLS Points, taquillas ORLEN y FlexDeliveryService

**Días y rapidez de entrega.** GLS Poland reparte de lunes a viernes (entrega en sábado solo si la contrata expresamente el remitente). Los paquetes nacionales estándar suelen llegar el siguiente día laborable (sin garantía; el remitente puede contratar Express 10:00 o 12:00). Desde Polonia, GLS indica de 2 a 7 días laborables al resto de países de la UE según destino ([GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)).

**Peso, medidas y CashService (reembolso).** Los envíos nacionales a domicilio admiten hasta 31,5 kg (40 kg en envíos internacionales a domicilio; 31,5 kg en GLS Points seleccionados; 20 kg en los demás GLS Points, taquillas y myGLS), con máximo de 200 × 80 × 60 cm y suma de largo más contorno de hasta 300 cm. El pago contra reembolso (`CashService`, hasta 6.000 PLN al día) puede abonarse al repartidor en efectivo, con tarjeta o mediante BLIK.

**Entrega fallida y segundo intento.** Según la [página de recepción de GLS Poland](https://gls-group.com/PL/en/receiving/), si no estás en casa, el mensajero puede dejar el paquete a un vecino (con tu consentimiento) o desviar un paquete elegible sin reembolso a un GLS Point cercano. Si no, vuelve a la delegación para un segundo intento el siguiente día laborable; si sigue sin entregarse, permanece en el depósito antes de devolverse al remitente (5 días laborables para recogida en depósito FlexDeliveryService, o devolución tras 6 días laborables según la guía de recepción).

**Custodia en GLS Points.** Conforme a las [condiciones de GLS Poland](https://gls-group.com/PL/pl/regulamin/) para GLS Points y taquillas (en vigor desde el 9 de noviembre de 2026) y sus [preguntas frecuentes](https://gls-group.com/PL/en/faq/), los paquetes esperan en un GLS Point durante **7 días naturales** antes de volver al remitente (las páginas de [recepción](https://gls-group.com/PL/en/receiving/) y GLS Points todavía dicen siete días laborables). Recógelo con el PIN enviado por SMS o correo; GLS puede pedir tu documento de identidad.

**Taquillas 24/7 de ORLEN Paczka.** Los paquetes dirigidos a [taquillas compartidas ORLEN Paczka](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) se guardan **72 horas** y se devuelven automáticamente al remitente al vencer el plazo. Abre el compartimento con tu PIN por SMS/correo, código QR o apertura remota en la app myGLS: no se pide identificación, así que cualquier persona con el código puede recogerlo. Asistencia técnica 24/7 para taquillas: `801 167 536` o `502 167 536`.

**Redirección FlexDeliveryService.** Desde el enlace del correo de aviso de GLS (sin crear cuenta), [redirección de paquetes](https://gls-group.com/PL/en/parcel-redirection/) permite elegir un GLS Point, taquilla ORLEN, nueva dirección, nueva fecha, recogida en delegación, autorización de depósito o rechazo del paquete.

## Aduanas, daños, reclamaciones y paquetes desaparecidos de GLS Poland

En compras online, el vendedor mantiene el contrato de transporte con GLS Poland y gestiona reembolsos o sustituciones.

**Paquetes desde fuera de la UE.** Los envíos dentro de la UE no pasan aduana. Para pedidos online llegados de fuera de la UE, según las [normas aduaneras de la Comisión Europea](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), se aplica un arancel fijo de 3 € por categoría de artículo hasta 150 € (en vigor desde el 1 de julio de 2026) y una tasa europea de tramitación de 2 € (en vigor desde el 1 de noviembre de 2026), además del IVA polaco de importación. Consulta [paquetes retenidos en aduanas](guide:customs).

**Daños visibles y ocultos.** Puedes rechazar un paquete visiblemente dañado en la entrega para que el mensajero lo registre y lo devuelva al remitente. Según las [Condiciones de Servicio de GLS Poland](https://gls-group.com/PL/pl/regulamin/), los daños ocultos descubiertos tras la aceptación deben notificarse en **7 días** (por escrito a la sede de GLS o mediante el [formulario de reclamaciones de consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Los plazos generales de reclamación de consumidores son 30 días desde la admisión por GLS (45 días por pérdida, dentro de los 12 meses desde el envío), y GLS resuelve en 30 días. Consulta [paquete dañado](guide:damaged-parcel) y [paquete perdido](guide:lost-parcel).

**Seguimiento parado o entregado pero ausente.** Si el seguimiento se detiene varios días, consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating). Si figura entregado y no lo tienes, comprueba si espera en un GLS Point, pregunta a los vecinos y avisa a la tienda; consulta [entregado pero no recibido](guide:delivered-not-received) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de GLS Poland y aviso contra fraudes

Contacta primero con la tienda online para reembolsos. Para dirigirte directamente a GLS Poland (datos en [GLS Contact](https://gls-group.com/PL/en/gls-contact/) y [Corporate Information](https://gls-group.com/PL/en/corporate-information/)):

- **Teléfono de atención:** `+48 46 814 82 20` (lunes a viernes 07:00–19:00, sábado 07:00–15:00).
- **Correo electrónico:** `gls@gls-poland.com` (oficina corporativa `office@gls-poland.com`).
- **Formularios online:** [formulario de contacto de GLS Poland](https://gls-group.com/PL/en/gls-contact/) y [formulario de reclamaciones de consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **Asistencia técnica 24/7 para taquillas:** `801 167 536` / `502 167 536`.
- **Sede central:** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Polonia.

> **Aviso contra estafas en los [consejos de seguridad de GLS Poland](https://gls-group.com/PL/en/security-advices/):** GLS nunca envía enlaces por correo pidiendo pagar mercancías con tarjeta o transferencia (CashService/ExWorksService se paga directamente al repartidor en efectivo, tarjeta o BLIK al entregar) y nunca manda avisos operativos desde cuentas gratuitas como Gmail u Outlook. Reenvía mensajes sospechosos a `security@gls-poland.com` (o `dataprotection@gls-poland.com`) y denuncia enlaces fraudulentos polacos ante CSIRT NASK CERT (`incydent.cert.pl`, `cert@cert.pl` o SMS `8080`). Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de GLS Poland con Peek

Pega el número en [Peek](/). Los números de 11 y 12 dígitos son compartidos por varios transportistas, así que Peek pregunta a los más probables guiándose por tu país (la región de tu móvil o, en la web, desde dónde te conectas). En Polonia consulta primero a GLS Poland, antes que [GLS Francia](carrier:gls-fr), [GLS Alemania](carrier:gls-de), [GLS Suiza](carrier:gls-ch) y otros como [PPL](carrier:ppl) para 11 dígitos o [BRT](carrier:brt) y [Colis Privé](carrier:colis-prive) para 12, y normalmente verás «GLS Poland tiene este paquete.». En otros países solo pregunta a GLS Poland cuando los demás candidatos no reconocen el código. Un enlace pegado de `gls-group.com` o `gls-group.eu` con `/PL/` en la ruta y el número en `match=`, `parcelNumber=` o `matchParcelNumber=` se sigue directamente como GLS Poland. Si no, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en un paquete ya añadido y elige GLS Poland.

El campo «Código postal de entrega» figura como «Opcional», pero GLS solo muestra sus escaneos con él, tal como avisa la línea bajo el campo: «GLS Poland solo muestra los escaneos con el código postal de entrega.». Sin él, Peek obtiene el estado de GLS pero no los escaneos: consulta mientras tanto a otros servicios de seguimiento y la ficha del paquete ofrece «Añadir código postal». Con cuenta, Peek prueba una vez el último código postal que diste para un paquete de GLS Poland o, en su defecto, el de tu último paquete. Si GLS no encuentra el paquete con ese código postal, Peek te avisa y ofrece «Cambiar código postal». Mientras GLS espera el código postal o no encuentra el paquete con el tuyo y nada responde durante un día, Peek comprueba una vez al día; añadir o cambiar el código postal lo comprueba al instante. El código postal se envía a GLS y, si hace falta, a servicios de seguimiento, y nunca aparece en enlaces compartidos.

Con el código postal ves el estado, el lugar de cada escaneo en el mapa, el día previsto, el peso, la hora de entrega y, en un GLS Point, su nombre y dirección. Se descartan tu nombre, dirección, datos de contacto, la firma y la referencia del remitente. Peek consulta el servicio en inglés de GLS en lugar de la web polaca: allí `W doręczeniu` aparece como `In delivery` y `Paczka została doręczona` como `The parcel has been delivered.`. La llegada a un GLS Point o taquilla aparece como «Listo para recoger». Las horas se leen en horario polaco.

Peek consulta a GLS aproximadamente una vez por hora, día de reparto incluido, y actualizar a mano no consulta antes (4 horas tras un fallo); cada 6 horas pasados siete días sin novedades en «Etiqueta creada». Mediante servicios de seguimiento únicamente, comprueba cada hora y cada 3 horas tras un día sin cambios.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun, 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a GLS Poland.», con un enlace «Pide ayuda a GLS Poland» a su página de contacto. Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a GLS Poland.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede redirigir paquetes ni presentar reclamaciones ante GLS Poland en tu nombre.

## Preguntas sobre el seguimiento de GLS Poland

### ¿Cuánto tiempo guarda GLS Poland un paquete en un GLS Point o taquilla ORLEN?

Según las [Condiciones de Servicio](https://gls-group.com/PL/pl/regulamin/) y las [FAQ](https://gls-group.com/PL/en/faq/) vigentes de GLS Poland, los GLS Points guardan los paquetes 7 días naturales (las páginas de recepción y GLS Points todavía mencionan siete días laborables), mientras que las taquillas 24/7 ORLEN Paczka los conservan 72 horas antes de la devolución automática al remitente.

### ¿Por qué me pide GLS Poland el código postal para mostrar todo el seguimiento?

GLS protege los detalles del envío devolviendo el historial completo de escaneos, las opciones de entrega y los datos del ParcelShop solo cuando el número de paquete coincide con el código postal del destinatario. Introduce el código postal de entrega en Peek para recibir el flujo directo de GLS.

### ¿Puedo pagar un paquete CashService (reembolso) de GLS Poland con tarjeta o BLIK?

Sí. GLS Poland indica que las entregas CashService (hasta 6.000 PLN diarios) se pueden pagar al mensajero en la entrega en efectivo, con tarjeta o mediante BLIK, nunca a través de un enlace en un correo inesperado.

### ¿Cómo redirijo un paquete de GLS Poland a una tienda Żabka o a una taquilla?

Abre el enlace FlexDeliveryService en el correo de notificación de GLS (no hace falta cuenta) o usa [redirección de paquetes](https://gls-group.com/PL/en/parcel-redirection/) para enviarlo a un GLS Point (incluidas las tiendas Żabka), a una taquilla ORLEN Paczka, a otra dirección o a otro día.

:::sources
- [GLS Poland: Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – número de paquete, Track-ID y consulta con código postal
- [GLS Poland: About GLS](https://gls-group.com/PL/en/about-gls/) – centro central de Stryków, delegaciones y visión general de la red
- [GLS Poland: Corporate Information](https://gls-group.com/PL/en/corporate-information/) – razón social, dirección en Głuchowo y `office@gls-poland.com`
- [GLS Poland: Receiving Parcels](https://gls-group.com/PL/en/receiving/) – reparto entre semana, entrega a vecinos, segundo intento, custodia en depósito y CashService
- [GLS Poland: Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – opciones FlexDeliveryService sin cuenta
- [GLS Poland: GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – más de 18.000 GLS Points (incluidas 11.000 tiendas Żabka), más de 26.000 puntos fuera de domicilio, límites de peso/tamaño y plazos UE
- [GLS Poland: Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – ~8.000 taquillas compartidas 24/7 ORLEN Paczka, custodia de 72 horas, PIN/QR/app myGLS, límite de 20 kg y teléfono técnico
- [GLS Poland: Live Tracking](https://gls-group.com/PL/en/live-tracking/) – vista de mapa y hora prevista con margen de 15 minutos
- [GLS Poland: FAQ](https://gls-group.com/PL/en/faq/) – 7 días naturales en GLS Point, CashService efectivo/tarjeta/BLIK, dudas sobre entregas fallidas
- [GLS Poland: Condiciones de Servicio (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – condiciones, las de GLS Points y taquillas vigentes desde el 9 de noviembre de 2026: custodia de 7 días naturales, aviso de daños ocultos en 7 días, plazos de reclamación de 30/45 días y resolución en 30 días
- [GLS Poland: Contact](https://gls-group.com/PL/en/gls-contact/) – línea `+48 46 814 82 20` (lun–vie 07:00–19:00, sáb 07:00–15:00), `gls@gls-poland.com` y formulario de contacto
- [GLS Poland: Reclamaciones de Consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – presentación online de reclamaciones
- [GLS Poland: Security Advices](https://gls-group.com/PL/en/security-advices/) – alertas de phishing, `security@gls-poland.com` y reporte a CERT NASK
- [GLS Group: Security Advice](https://gls-group.com/GROUP/en/security-advice/) – dominios oficiales del grupo y reglas CashService
- [Comisión Europea: Reforma Aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € hasta 150 € (desde el 1 de julio de 2026) y tasa UE de 2 € (desde el 1 de noviembre de 2026)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – detección de 11/12 dígitos, prioridad en Polonia, código postal opcional, mapeo de estados, frecuencia horaria/4 horas y enlace de ayuda
:::
