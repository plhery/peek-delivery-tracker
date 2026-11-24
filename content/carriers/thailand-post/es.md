---
title: Seguimiento Thailand Post: códigos TH S10, EMS y 1545
description: Sigue un paquete de Thailand Post (ไปรษณีย์ไทย): códigos TH S10 de 13 caracteres, estados nacionales y EMS, reembolso COD, aduanas y teléfono 1545.
slug: seguimiento-thailand-post
published: 2026-11-24
updated: 2026-11-24
---

**Thailand Post** (*Thailand Post Co., Ltd.*, en tailandés *บริษัท ไปรษณีย์ไทย จำกัด*) es el operador postal nacional de Tailandia ([thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en)). Gestiona correo nacional, certificados, EMS, Same Day, paquetería pesada LogisPost y contra reembolso (COD) en Tailandia, además de servicios internacionales como [EMS](carrier:ems) World, Registered, ePacket, Courier Post (hasta 200 kg) y Courier Post Plus a todo el mundo. Ofrece seguimiento público en [track.thailandpost.co.th](https://track.thailandpost.co.th/) y avisos internacionales en [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update). Puedes consultar allí un envío postal tailandés o pegar tu código `TH` de 13 caracteres en el cuadro de arriba.

## Números de seguimiento de Thailand Post (formato `TH` S10 de 13 caracteres)

Los envíos con seguimiento nacionales e internacionales de Thailand Post usan códigos UPU S10 de 13 caracteres: dos letras de servicio en mayúsculas, 8 dígitos de serie, 1 dígito de control S10 y el código de país `TH`:

| Formato (inventado) | Qué es |
| --- | --- |
| `EE482917367TH` | Envío urgente [EMS](carrier:ems) de Thailand Post de 13 caracteres (prefijo `EE` + 9 dígitos con dígito de control S10 válido + `TH`). |
| `RR739184627TH` | Correo certificado o paquete pequeño de Thailand Post de 13 caracteres (prefijo `RR` + 9 dígitos con dígito de control S10 válido + `TH`). |

Los prefijos nacionales Express/EMS, Certificado (`R...TH`), ePacket (`L...TH`) y de paquetería nacional (`E`, `R`, `C`, `W`, etc. terminados en `TH`) siguen esta misma estructura S10 de 13 caracteres con dígito de control. Las cartas ordinarias sin código de barras de 13 caracteres no tienen seguimiento online. Consulta la guía de [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Thailand Post

El rastreador de Thailand Post combina códigos numéricos `statusID` alineados con la UPU y descripciones en inglés (con nombres de oficinas tailandesas), cubriendo tanto escaneos dentro de Tailandia como avisos EDI internacionales reenviados por el correo de destino:

| Código y texto de Thailand Post | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `3` `Posting/Collection` | Admitido en ventanilla de Thailand Post o recogido al remitente. | «Admitido por el transportista» |
| `9` `Arrival at outward office of exchange`, `15` `Departure From Outward OE`, `18` `Arrival at inward office of exchange` | Procesado en una oficina de cambio internacional para exportación o importación. | «En tránsito» |
| `23`–`27` `In transit`, `Item into sorting center`, `Arrival at post office` | Circulando entre centros de clasificación postal y llegando a la oficina local de entrega. | «En tránsito» |
| `31` `Item out for physical delivery` | En reparto hoy con el cartero de Thailand Post para entrega a domicilio. | «En reparto» |
| `57` `Contact recipient` | El repartidor intentó llamar al destinatario (gestión sin movimiento; mantiene la etapa actual). | Mantiene la etapa anterior |
| `32` `Item arrival at collection point for pick-up (by recipient)` | Depositado en una taquilla o punto de recogida de Thailand Post listo para retirar. | «Listo para recoger» |
| `34` `Scheduled for further delivery attempt today` / `Item held, addressee notified` (grupo 4) | Intento de entrega fallido (por ejemplo ausencia del destinatario o tasas aduaneras/COD pendientes). | «Intento de entrega» |
| `28` `Return to the origin post office` | Incidencia registrada; el envío regresa hacia la oficina postal de origen. | «Incidencia» |
| `35/L` `The sender received the returned item` | Paquete devuelto entregado de vuelta al remitente original. | «Devuelto al remitente» |
| `35/S` `Final delivery` | Entregado al destinatario (en pedidos COD puede seguir `36` `Successfully transfer money to seller`, sin cambiar la hora de entrega). | «Entregado» |

## Contra reembolso (COD), traspasos internacionales y aduanas

- **Exportaciones internacionales (`...TH` al extranjero):** tras `15` (`Departure From Outward OE`), el correo nacional de tu país — como [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl) / Deutsche Post o [Australia Post](carrier:australia-post) — gestiona el despacho de importación y la entrega local mientras envía escaneos EDI de vuelta a Thailand Post.
- **Aranceles e IVA de importación:** los paquetes exportados desde o importados hacia Tailandia están sujetos a las normas aduaneras, IVA y tasas de gestión del país de destino (incluidos los cargos de la UE en pedidos fuera de la UE). Si aparece `34` (`Item held, addressee notified due to Payment of charges`), revisa tu buzón o la oficina de correos para ver el aviso de pago o aduana. Consulta [paquetes retenidos en aduanas](guide:customs).
- **Pago contra reembolso (COD) en Tailandia:** en envíos nacionales COD, Thailand Post registra `35/S` (`Final delivery`) cuando el destinatario recibe y paga el paquete, seguido de `36` (`Successfully transfer money to seller`) al transferir los fondos al vendedor.

## Paquete de Thailand Post retrasado, retenido o perdido

- **Silencio tras salir de Tailandia:** los envíos aéreos internacionales (sobre todo certificados `R...TH` y paquetes económicos) suelen permanecer varios días o semanas sin cambios entre `Departure From Outward OE` y `Arrival at inward office of exchange`. Consultar el mismo código `TH` de 13 caracteres en la web del correo de destino suele mostrar detalles adicionales de aduanas o reparto local. Consulta [cuando el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Envíos perdidos, dañados o devueltos:** contacta primero con el remitente o la plataforma y conserva el código de 13 caracteres y el resguardo. Consulta también [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [entregado pero no recibido](guide:delivered-not-received) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente de Thailand Post y Contact Center 1545

Canales oficiales publicados en [Thailand Post Contact Us](https://www.thailandpost.co.th/un/contact_thp/un/41) y [thailandpost.co.th](https://www.thailandpost.co.th/index.php?page=index&language=en):

- **THP Contact Center (Tailandia):** **`1545`** (consultas generales, localización de envíos y reclamaciones).
- **Portal oficial de contacto y consultas:** [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).
- **Localizador online y avisos internacionales:** [track.thailandpost.co.th](https://track.thailandpost.co.th/) e [international.thailandpost.com](https://sites.google.com/thailandpost.com/interthp/update).

> Desconfía de SMS o mensajes de LINE falsos que afirmen que un paquete de Thailand Post requiere un pequeño pago en un enlace desconocido. Verifica siempre cualquier seguimiento o tasa directamente en [track.thailandpost.co.th](https://track.thailandpost.co.th/) o llamando al THP Contact Center `1545`. Consulta [mensajes SMS falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Thailand Post con Peek

[Peek](/) detecta automáticamente Thailand Post como único candidato cuando introduces cualquier código S10 `TH` válido de 13 caracteres (`EE482917367TH` o `RR739184627TH`, inventados) y reconoce directamente los enlaces pegados `track.thailandpost.co.th/?trackNumber=`. Si necesitas cambiar de transportista manualmente (por ejemplo para comparar los escaneos del correo de destino cuando llega al extranjero), pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete.

Peek lee el localizador de Thailand Post — y su servidor alternativo cuando el primero falla — y muestra la etapa («Admitido por el transportista», «En tránsito», «En reparto», «Listo para recoger», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), el texto en inglés del escaneo, la oficina postal y la hora de entrega. Los códigos de estado fijan la etapa y ninguna aplicación de Peek los muestra; el nombre del destinatario figura bajo «Destinatario» en tu propia página del paquete, nunca en un enlace compartido, y ninguna aplicación muestra quién firmó la entrega. Los escaneos propios de Thailand Post llevan el desfase respecto a UTC de Bangkok. Un escaneo EDI de un correo extranjero trae la hora local de esa oficina, que Thailand Post almacena como si fuera hora de Bangkok, de modo que Peek lo fecha en la zona horaria de esa oficina (hora de Tokio para `TOKYO INTERNATIONAL`), un escaneo sin lugar en la zona del escaneo vecino, y muestra todas las horas en la hora de tu dispositivo. Peek descarta el nombre del repartidor, teléfonos, firma y nota de contacto oculta; Thailand Post no ofrece fecha estimada de entrega.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Thailand Post muestra `31` (`Item out for physical delivery`), cada 2 minutos desde las 6:00 según el reloj del escaneo más reciente mientras responda el localizador de Thailand Post. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Thailand Post no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Thailand Post.», junto con un enlace «Pide ayuda a Thailand Post» hacia su página de contacto. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Thailand Post.»

Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek supervisa únicamente las actualizaciones de seguimiento y no puede despachar aduanas, pagar importes COD ni contactar con Thailand Post por ti.

## Preguntas sobre el seguimiento de Thailand Post

### ¿Qué aspecto tiene un número de seguimiento de Thailand Post?

Los envíos con seguimiento de Thailand Post usan códigos UPU S10 de 13 caracteres que empiezan por dos letras de servicio, seguidas de 9 dígitos (con un dígito de control final) y `TH` — por ejemplo `EE482917367TH` para EMS o `RR739184627TH` para correo certificado (inventados).

### ¿Qué significa el código `36` (`Successfully transfer money to seller`)?

En pedidos nacionales tailandeses contra reembolso (COD), el código `36` confirma que Thailand Post transfirió al vendedor el importe cobrado tras la entrega final (`35/S`). Es un movimiento financiero y no un desplazamiento del paquete.

### ¿Por qué dejó de actualizarse mi paquete de Thailand Post tras `Departure From Outward OE`?

El paquete salió de la oficina internacional de cambio saliente de Tailandia y se encuentra en tránsito aéreo internacional o a la espera de ingreso en el operador postal del país de destino.

### ¿Cómo contacto con atención al cliente de Thailand Post?

Llama al THP Contact Center en el **`1545`** dentro de Tailandia o utiliza la página oficial de contacto en [thailandpost.co.th/un/contact_thp/un/41](https://www.thailandpost.co.th/un/contact_thp/un/41).

:::sources
- [Thailand Post: Official Website](https://www.thailandpost.co.th/index.php?page=index&language=en) – *Thailand Post Co., Ltd.*, servicios nacionales/internacionales EMS, Registered, Courier Post, LogisPost y COD
- [Thailand Post: Contact Us & THP Contact Center 1545](https://www.thailandpost.co.th/un/contact_thp/un/41) – soporte oficial, portal de reclamaciones y línea `1545` (URL de ayuda enlazada por Peek)
- [Thailand Post: Public Tracker](https://track.thailandpost.co.th/) – consulta pública oficial de seguimiento
- [Thailand Post: International Service Advisories](https://sites.google.com/thailandpost.com/interthp/update) – avisos de seguimiento y servicio internacional
- [Universal Parcel Scraper: thailand-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/thailand-post) – validación de dígito de control S10 `TH`, respaldo en dos servidores, mapa `statusID` UPU (`3`–`36`, `57`), reloj de Bangkok frente a EDI extranjero y lectura de destinatario
:::
