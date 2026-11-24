---
title: Seguimiento Korea Post: códigos EMS KR, 13 dígitos y ayuda
description: Sigue un envío de Korea Post (ePOST): números S10 acabados en KR, códigos internos de 13 dígitos, estados EMS y coreanos, aduanas, plazos y soporte.
slug: seguimiento-korea-post
published: 2026-11-24
updated: 2026-11-24
---

Korea Post (*우정사업본부*, portal en línea *ePOST* / *인터넷우체국*) es el operador postal nacional de Corea del Sur, dependiente del Ministerio de Ciencia y TIC. Gestiona la entrega nacional de paquetes y correo certificado en toda Corea del Sur junto con los servicios internacionales **EMS** (Express Mail Service a más de 100 destinos, y a más de 70 países vía EMS Premium), **K-Packet** y correo certificado o paquetería aérea internacional. Puedes consultar envíos internacionales en [el rastreador EMS en inglés de Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), revisar paquetes nacionales en [el seguimiento nacional de ePOST](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) o pegar tu número de seguimiento en el recuadro de arriba.

## Números de seguimiento de Korea Post: formato S10 en KR y 13 dígitos nacionales

Busca el número en tu recibo postal, en la pantalla del pedido de la tienda coreana o en el correo de confirmación de envío. Korea Post utiliza dos formatos principales según si el paquete viaja fuera o dentro de Corea del Sur:

| Formato (inventado) | Qué es |
| --- | --- |
| `EM482917367KR` | Envío internacional **EMS** de mercancías (formato UPU S10 terminado en `KR`; los documentos suelen empezar por `EE`). |
| `RR482917367KR` | Correo certificado internacional o envío K-Packet (formato UPU S10: 2 letras + 9 dígitos con dígito de control + `KR`). |
| `6482917364058` | Número nacional surcoreano de 13 dígitos para correo certificado o paquetería (empieza por un dígito del `1` al `6`). Comparte la forma numérica de 13 dígitos con [Correos de Chile](carrier:correos-chile), [Delhivery](carrier:delhivery), [Econt](carrier:econt), [Paquetexpress](carrier:paquetexpress) y otros transportistas. |

En [la presentación EMS de Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), el prefijo `EE` corresponde a documentos EMS (sin inspección aduanera de mercancías), mientras que el prefijo `EM` identifica los envíos EMS de mercancías. Para otros códigos postales internacionales, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Korea Post

Los envíos internacionales `KR` muestran filas de escaneo UPU/EMS en inglés conservando la hora local que comunica el operador postal de cada país, mientras que los envíos nacionales de 13 dígitos muestran etiquetas en coreano en hora estándar de Corea (`Asia/Seoul`, UTC+9 todo el año):

| Korea Post indica (internacional / nacional) | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `운송장출력` *(nacional)* | Albarán de envío impreso antes de la admisión en la oficina postal. | «Etiqueta creada» |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Admitido en ventanilla de una oficina postal coreana o recogido por un repartidor de Korea Post. | «Admitido por el transportista» |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Procesado en la oficina de cambio internacional de Corea (como Incheon), embarcado por aire, entregado al operador postal del país de destino o movido entre centros de clasificación nacionales. | «En tránsito» |
| `Ready for customs clearance` | Presentado ante aduanas en el país de destino (o control aduanero de exportación/importación). | «En aduanas» |
| `배달준비` *(nacional)* | Preparado para el reparto final por el mensajero de la oficina postal local en Corea. | «En reparto» |
| `Unsuccessful delivery` | El intento de entrega en destino no pudo completarse. | «Intento de entrega» |
| `Delivery complete` / `배달완료` | Entregado; el rastreador de Korea Post puede mostrar el nombre de quien lo recibió. | «Entregado» |

## Plazos EMS de Korea Post, aduanas y entrega local

Según [la información del servicio EMS de Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), [las condiciones de envío por país](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) y [la calculadora de plazos y tarifas](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal):

- **Plazos de tránsito EMS:** para documentos (números `EE`) depositados en Seúl antes del mediodía, Korea Post indica el día siguiente o de 3 a 4 días a ciudades con vuelo directo, como Tokio, Hong Kong y Singapur, y de 4 a 6 días al resto; su resumen general menciona de 2 a 3 días y de 3 a 5 días para documentos depositados por la mañana en Seúl. Suma un día para envíos depositados después del mediodía o fuera de Seúl, o dirigidos fuera de la ciudad donde está la oficina de cambio de destino; desde una oficina de montaña o insular, añade los días necesarios para llegar al International Mail Logistics Center. Las mercancías (números `EM`) esperan además la inspección aduanera en destino. No cuentan ni el día de admisión ni los festivos.
- **Declaraciones de aduanas:** los documentos e impresos llevan declaración CN22, mientras que las mercancías y artículos sujetos a derechos requieren declaración CN23 (junto con factura comercial y los permisos exigidos para envíos comerciales). Los artículos prohibidos o restringidos figuran en [la guía de artículos prohibidos y restringidos de ePOST](https://ems.epost.go.kr/front.Introduction04.postal).
- **Traspaso al correo local:** cuando tu envío `KR` llega a tu país (`Delivered to Destination Post` / `Arrival at inward office of exchange`), tu operador postal nacional (por ejemplo [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) o [Australia Post](carrier:australia-post)) gestiona el cobro de gastos aduaneros, los intentos en domicilio y la custodia en oficina según sus normas locales. Consulta el mismo número `...KR` también en la web de tu correo local para ver más opciones de entrega. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de Korea Post retrasado, detenido en tránsito o extraviado

- **Pausa sin escaneos tras `Departure from the Airport`:** las sacas internacionales EMS y K-Packet suelen pasar de varios días a más de una semana sin escaneos entre su salida de Corea del Sur y su entrada en la oficina de cambio del país de destino (`Arrival at inward office of exchange`). Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Retenido en `Ready for customs clearance`:** comprueba el mismo número `...KR` en la web de tu correo nacional para ver si el IVA de importación, un arancel o una tarifa de gestión pendiente bloquean el levante.
- **Envíos perdidos, dañados o faltantes:** en compras online, contacta primero con el vendedor o plataforma coreana; ePOST admite solicitudes de búsqueda (*행방조사청구*) por internet o por escrito, y publica sus normas de indemnización (*손해배상규정*). Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Korea Post

Datos oficiales publicados en [el centro de atención de ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), [el portal principal de ePOST](https://www.epost.go.kr/main.retrieveMainPage.comm) y [la presentación del servicio EMS](https://ems.epost.go.kr/front.Introduction01.postal):

- **Centro de atención de Korea Post (dentro de Corea del Sur):** `1588-1300` (de lunes a viernes de `09:00` a `18:00` KST, cerrado sábados y festivos; con coste para quien llama).
- **Desde fuera de Corea del Sur:** `+82-42-609-4295`, en el mismo horario.
- **Correo electrónico:** `help@posa.or.kr`.
- **Preguntas frecuentes y consultas online:** [centro de atención de ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) y la opción *e-mail EMS Inquiry* en [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Dirección en el pie de página de ePOST:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Corea del Sur.

> Ignora los SMS falsos que afirmen que tu paquete de Korea Post no se puede entregar sin pagar una pequeña tasa en un enlace no oficial. Los cargos reales de importación de un envío entrante `KR` los cobra el servicio postal o portal aduanero de tu propio país, nunca un enlace de SMS sospechoso. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un paquete de Korea Post con Peek

[Peek](/) reconoce automáticamente con alta confianza cualquier número UPU S10 acabado en `KR` (`EM482917367KR` o `RR482917367KR`, dígitos inventados) como de Korea Post, y también detecta enlaces pegados de `trace.epost.go.kr` con `POST_CODE=` y de `service.epost.go.kr` con `sid1=`. Un número nacional de 13 dígitos (`6482917364058`) encaja con varios transportistas: Peek muestra Korea Post entre las opciones y te pide elegir. También puedes seleccionarlo después con «Cambiar transportista» en la pantalla del paquete (buscar «South Korea Post» o «ePOST» también lo encuentra), o pegar directamente el enlace de ePOST.

Peek lee el rastreador EMS en inglés de Korea Post para los códigos `KR` y el rastreador nacional en coreano de ePOST para los de 13 dígitos. Muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «En reparto» en envíos nacionales, «Intento de entrega», «Entregado»), el texto del escaneo con su oficina postal o aeropuerto y la hora de entrega en envíos nacionales. Los escaneos nacionales llevan hora de Corea y los internacionales la zona horaria del lugar donde se registran (la de Nueva York para un escaneo en Nueva York, por ejemplo); Peek los muestra en la hora de tu dispositivo. El nombre del destinatario que publica Korea Post aparece bajo «Destinatario» en tu propia página del paquete, nunca en un enlace compartido; el nombre de quien firma queda fuera de todas las apps de Peek, y Peek omite el nombre del servicio, el nombre del remitente y los teléfonos de las oficinas. El rastreador de Korea Post no ofrece fecha estimada de entrega, peso ni punto de recogida.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que un paquete nacional muestra `배달준비`, cada 2 minutos desde las 6:00 hora de Corea, mientras responda el rastreador de Korea Post. En el resto de casos, Peek consulta cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora sea cual sea tu seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de Korea Post no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Korea Post.», junto con un enlace «Pide ayuda a Korea Post» hacia el centro de atención de ePOST. Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Korea Post.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, redirigir un paquete ni presentar una reclamación EMS ante Korea Post en tu nombre.

## Preguntas frecuentes sobre el seguimiento de Korea Post

### ¿Qué significan `EE...KR`, `EM...KR` y `RR...KR`?

Son códigos postales internacionales UPU S10 de 13 caracteres enviados desde Corea del Sur: `EE` se usa para documentos EMS, `EM` para paquetes EMS con mercancías y `RR` (u otros prefijos `R`/`C`/`L`) para correo certificado, K-Packet y paquetería internacional.

### ¿Por qué mi paquete EMS de Korea Post dejó de actualizarse al salir del aeropuerto?

Cuando aparece `Departure from the Airport` en Corea del Sur, el seguimiento suele quedar en pausa hasta que el operador postal de tu país escanea la saca entrante en su oficina de cambio de entrada (`Arrival at inward office of exchange`). Revisa también el mismo número `...KR` en la web de tu correo nacional.

### ¿Korea Post entrega directamente fuera de Corea del Sur?

Korea Post gestiona la recogida, la clasificación de exportación y el despacho aéreo desde Corea del Sur, y después entrega el paquete (`Delivered to Destination Post`) al servicio postal nacional del país de destino para los trámites de importación y el reparto en domicilio.

### ¿Cómo contacto con el servicio de atención al cliente de Korea Post?

Llama al `1588-1300` dentro de Corea del Sur (lun.–vie. `09:00–18:00` KST, cerrado sábados y festivos), escribe a `help@posa.or.kr` o utiliza [el centro de atención de ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: seguimiento EMS en inglés](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – portal internacional de seguimiento EMS y correos de destino asociados
- [Korea Post (ePOST): seguimiento nacional](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – seguimiento de paquetes y correo certificado nacional de 13 dígitos
- [Korea Post (ePOST): presentación del servicio EMS](https://ems.epost.go.kr/front.Introduction01.postal) – prefijos `EE` frente a `EM`, plazos EMS, normas aduaneras CN22/CN23, más de 100 destinos EMS / más de 70 EMS Premium y `1588-1300`
- [Korea Post (ePOST): condiciones de envío por país](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – límites EMS y notas aduaneras por país
- [Korea Post (ePOST): calculadora de plazos y precios](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – calculadora de plazos EMS, EMS Premium y paquetería
- [Korea Post (ePOST): artículos prohibidos y restringidos](https://ems.epost.go.kr/front.Introduction04.postal) – restricciones de envío internacional
- [Korea Post (ePOST): centro de atención](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, horario laborable `09:00–18:00` KST y dirección en Naju (URL de ayuda enlazada por Peek)
- [Korea Post (ePOST): portal principal](https://www.epost.go.kr/main.retrieveMainPage.comm) – resumen de paquetería nacional, certificados, EMS y K-Packet, y `+82-42-609-4295` desde el extranjero
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – detección `KR` S10 y de 13 dígitos, reglas de enlaces `trace.epost.go.kr` / `service.epost.go.kr`, mapas de estados EMS en inglés y nacional en coreano, husos horarios, campos descartados y URL de ayuda
:::
