---
title: Seguimiento EMS: códigos S10, estados y ayuda postal
description: Sigue un paquete internacional EMS (Express Mail Service): códigos S10 de 13 caracteres con E, estados de aduana y entrega UPU, retrasos y ayuda postal.
slug: seguimiento-ems
published: 2026-11-24
updated: 2026-11-24
---

**EMS** (*Express Mail Service*) es el servicio postal urgente internacional para documentos y mercancías. La **Unión Postal Universal (UPU)** creó la **Cooperativa EMS** ([ems.post](https://www.ems.post/en)) para desarrollarlo; en junio de 2026 la Cooperativa contaba con 179 miembros. En lugar de operar sus propios aviones o furgonetas, EMS conecta a los operadores postales nacionales designados de todo el mundo — como [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) y [Australia Post](carrier:australia-post) — para que documentos y mercancías urgentes viajen bajo un único código normalizado de 13 caracteres. Puedes consultar un envío en el localizador global EMS en [items.ems.post](https://items.ems.post/) o pegar tu número de envío EMS en el cuadro de arriba.

## Números de seguimiento EMS (formato UPU S10 de 13 caracteres)

Todo envío EMS lleva un código de barras UPU S10 de 13 caracteres que empieza por la letra **`E`** (de Express), seguida de una segunda letra de servicio, 8 dígitos de serie, 1 dígito de control S10 y el código ISO de 2 letras del país de origen:

| Formato (inventado) | Qué es |
| --- | --- |
| `EA482917367JP` | Envío EMS de 13 caracteres expedido desde Japón (prefijo `EA` + 9 dígitos con dígito de control S10 válido + `JP`). |
| `EE739184627CN` | Envío EMS de 13 caracteres expedido desde China (prefijo `EE` + 9 dígitos con dígito de control S10 válido + `CN`). |

Las dos últimas letras identifican siempre al **operador postal de origen**, nunca al país de destino. Un paquete enviado por EMS de Tokio a París termina en `JP` durante todo el trayecto, incluso cuando [La Poste](carrier:la-poste) asume la entrega final en Francia. Los códigos postales que no empiezan por `E` (como cartas certificadas `RR...` o paquetes ordinarios `CP...`) son rechazados por el localizador EMS por no corresponder a un envío EMS. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento EMS

La tabla pública de seguimiento de la Cooperativa EMS en [items.ems.post](https://items.ems.post/) traduce los eventos EDI de los operadores postales participantes a descripciones normalizadas en inglés:

| EMS muestra | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Posted` | Admitido en ventanilla o recogido por el operador postal de origen. | «Admitido por el transportista» |
| `Arrived at export office`, `Departed from export office` | Procesado en la oficina de cambio internacional del país de origen y despachado al extranjero. | «En tránsito» |
| `Presented to export customs and security`, `Held for export customs inspection` | Sometido a inspección aduanera de exportación o seguridad aérea en el país de origen. | «En aduanas» |
| `Released from export customs and security` | Superó el control de exportación y está listo para su despacho internacional. | «En tránsito» |
| `Export cancelled` | El envío de exportación se canceló o devolvió antes de salir del país de origen; contacta con el remitente. | «Incidencia» |
| `Arrived at destination import office`, `Departed from destination import office` | Llegó a la oficina de cambio internacional del país de destino o salió de ella hacia clasificación nacional. | «En tránsito» |
| `Held at destination import office`, `Presented to import customs` | Retenido en la oficina de cambio fronteriza o presentado a la aduana de importación en destino. | «En aduanas» |
| `Released from import customs` | Despachado por la aduana de destino y entregado a la red postal nacional. | «En tránsito» |
| `Arrived at sorting center`, `Arrived at post office` | Circulando por los centros de clasificación nacionales del correo de destino o llegado a la oficina postal de reparto. | «En tránsito» |
| `Out for delivery` | En reparto con el cartero local o el mensajero EMS para la entrega final. | «En reparto» |
| `Delivery attempted` | Se intentó la entrega sin éxito; comprueba si hay aviso de llegada o notificación de tu oficina de correos. | «Intento de entrega» |
| `Returned to sender` | En devolución hacia el país de origen tras entrega fallida, rechazo o denegación aduanera. | «Devuelto al remitente» |
| `Delivered` | Entregado al destinatario o en un punto de recogida autorizado por el operador de destino. | «Entregado» |

## Cómo funcionan los relevos internacionales y la aduana en EMS

Como EMS es una red cooperativa de correos nacionales reunidos en el [directorio de operadores EMS](https://www.ems.post/en/global-network/ems-operators), dos organizaciones postales distintas gestionan cada envío transfronterizo:

- **Correo de origen frente a correo de destino:** el operador postal de origen gestiona la admisión, el despacho de exportación y la expedición internacional; en cuanto aparece `Arrived at destination import office`, tu operador postal nacional (o su rama urgente, como Parcelforce en Reino Unido) controla el despacho de importación, los intentos de entrega, la reprogramación y los plazos de custodia en oficina.
- **Consulta tanto EMS como tu correo local:** la fuente global EMS difunde hitos EDI normalizados, mientras que la página de seguimiento de tu correo de destino suele añadir datos nacionales más completos, como enlaces de pago aduanero, dirección de la oficina de recogida, franjas de entrega o concertación de nueva entrega.
- **Aranceles e IVA de importación:** los paquetes EMS internacionales están sujetos a las normas aduaneras de destino, al IVA de importación, a gastos postales de despacho y a aranceles (incluidos los cargos de la UE para envíos desde fuera de la UE). Si el seguimiento se detiene en `Presented to import customs` o `Held at destination import office`, revisa si recibes carta, correo electrónico o aviso oficial de tu correo local. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete EMS retrasado, retenido o perdido

- **Silencio entre exportación e importación:** los despachos aéreos internacionales suelen pasar varios días sin escaneos entre `Departed from export office` y `Arrived at destination import office`, sobre todo en temporadas altas o en cola para la recepción aduanera. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Te ayuda tu correo local, no la UPU:** como señala [EMS Tracking](https://www.ems.post/en/global-network/tracking), la Unidad EMS y la UPU no ofrecen seguimiento telefónico ni intervienen en disputas entre operadores postales y clientes. Para un envío EMS extraviado, la [página de contactos EMS](https://www.ems.post/en/about-us/contacts-0) te dirige al servicio de atención al cliente o centro de llamadas EMS de tu país, que responde dudas generales sobre EMS, ayuda a encontrar el código y colabora para localizar el envío. Consulta [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [entregado pero no recibido](guide:delivered-not-received) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente EMS y directorio oficial de operadores

Dado que el servicio EMS de cada país está gestionado por su operador postal nacional designado, [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) publica un directorio buscable de la A a la Z de las páginas de atención al cliente y centros de llamadas EMS nacionales filtrados por región (África, Países Árabes, Asia-Pacífico, Caribe, Europa, América Latina y América del Norte):

- **Encontrar el teléfono y soporte de tu operador EMS local:** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) y [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Localizador público global:** [items.ems.post](https://items.ems.post/) (instrucciones en [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **Sin soporte telefónico central de la UPU:** ni la Unidad EMS ni la Unión Postal Universal resuelven consultas sobre paquetes concretos por teléfono.

> Desconfía de SMS falsos que afirman que un «paquete EMS» está bloqueado hasta que pagues una pequeña tasa en un enlace desconocido. Verifica siempre cualquier cargo aduanero directamente en la web oficial de tu operador postal nacional con tu código EMS de 13 caracteres. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete EMS con Peek

Cuando introduces un código S10 de 13 caracteres que empieza por `E` (`EA482917367JP` o `EE739184627CN`, inventados) en [Peek](/), Peek lo dirige normalmente primero al operador postal de origen indicado por las dos últimas letras (como [Japan Post](carrier:japan-post) o [China Post](carrier:china-post)), porque EMS no tiene regla independiente de detección automática. Para consultar en su lugar el localizador global de la Cooperativa **EMS**, toca «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete y elige **EMS** (buscar «Express Mail Service» o «EMS Post» también lo encuentra). Peek reconoce además directamente como EMS los enlaces pegados `items.ems.post/api/publicTracking/track?itemId=`.

Peek consulta `items.ems.post` directamente tras comprobar que el código es un S10 con `E` y dígito de control válido, y muestra la fase («Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), el texto EMS en inglés y la oficina. EMS facilita las horas de escaneo sin zona horaria: Peek fecha cada escaneo en la zona horaria de su oficina (Tokio para `TOKYO INTERNATIONAL`, París para `ROISSY CDG`), y un escaneo sin lugar en la zona del escaneo contiguo, mostrándolos todos en el reloj de tu dispositivo. La tabla EMS no ofrece fecha estimada de entrega, peso, nombre de quien firma ni hora de entrega aparte.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que EMS muestra `Out for delivery`, cada 2 minutos desde las 6:00 en la hora del lugar del último escaneo mientras el localizador EMS responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora sea cual sea tu modo de seguimiento, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador EMS no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tienes el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun, 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a EMS.», con un enlace «Pide ayuda a EMS» al directorio de contactos de EMS. Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a EMS.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, pagar impuestos de importación ni contactar con tu operador postal local por ti.

## Preguntas sobre el seguimiento EMS

### ¿Qué aspecto tiene un número de seguimiento EMS válido?

Un número de seguimiento EMS es un código UPU S10 de 13 caracteres que empieza por `E`, una segunda letra, 9 dígitos (acabados en un dígito de control) y el código ISO de 2 letras del país remitente — por ejemplo `EA482917367JP` o `EE739184627CN` (inventados).

### ¿Quién entrega mi paquete EMS en mi país?

Los paquetes EMS los entrega el operador postal nacional designado de tu país (o su división urgente), como [USPS](carrier:usps) en Estados Unidos, [Royal Mail](carrier:royal-mail) / Parcelforce en Reino Unido, [La Poste](carrier:la-poste) en Francia, [Poste Italiane](carrier:poste-italiane) en Italia o [Australia Post](carrier:australia-post) en Australia.

### ¿Por qué dejó de actualizarse mi paquete EMS tras `Departed from export office`?

Después de `Departed from export office`, el paquete está en tránsito aéreo internacional o esperando entrada en la oficina de cambio postal internacional del país de destino. El seguimiento se reanuda en cuanto el correo de destino escanea `Arrived at destination import office`.

### ¿Con quién contacto si un paquete EMS está bloqueado o perdido?

La UPU y la Unidad EMS no ofrecen seguimiento por teléfono ni intervienen en conflictos entre correos y clientes. Entra en [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) para encontrar el servicio de atención al cliente o centro de llamadas EMS de tu país, que puede ayudarte a localizar el envío.

:::sources
- [Cooperativa EMS: sitio oficial](https://www.ems.post/en) – resumen de la Cooperativa Express Mail Service de la UPU y red postal global
- [Cooperativa EMS: guía de seguimiento](https://www.ems.post/en/global-network/tracking) – formato S10 de 13 caracteres, no intervención de la UPU/Unidad EMS y ausencia de seguimiento telefónico
- [Cooperativa EMS: contactos globales de operadores](https://www.ems.post/en/about-us/contacts-0) – directorio A–Z de atención al cliente EMS nacional por región (URL de ayuda enlazada por Peek)
- [Cooperativa EMS: operadores A–Z](https://www.ems.post/en/global-network/ems-operators) – red de operadores postales nacionales designados
- [Portal público de seguimiento EMS](https://items.ems.post/) – consulta pública mundial de envíos EMS
- [Universal Parcel Scraper: ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – validación del dígito de control S10 con prefijo `E`, regla de enlace `items.ems.post`, mapa de 18 estados de la Cooperativa EMS y fechado de cada escaneo en la zona horaria de su oficina
:::
