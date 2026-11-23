---
title: Seguimiento A Tu Hora Express: códigos y código postal
description: Rastrea un paquete de A Tu Hora Express en España: códigos de 10, 16 y 24 dígitos, por qué pide código postal, estados, incidencias y contacto.
slug: seguimiento-a-tu-hora-express
published: 2026-11-23
updated: 2026-11-23
---

A Tu Hora Express (*A TU HORA EXPRESS, S.L.*) es una empresa española de reparto de última milla con sede central en San Fernando de Henares (Madrid) y delegaciones repartidas por la península, entre ellas Madrid, Barcelona, Valencia, Sevilla, Málaga, Alicante, Murcia, Almería, Cádiz, Castellón, Córdoba, Girona, Granada, Huelva, Jaén, Lleida y Tarragona. Gestiona pedidos de comercio electrónico y envíos B2B mediante sus modalidades *Express 24h*, *Economy 48h*, *Masivo 72h*, *Logística Inversa* y *B2B Premium*. Puedes consultar tu envío en el [portal de seguimiento de A Tu Hora Express](https://mydelivery.atuhoraexpress.com/#/login-form-cli) o pegar el número en el campo superior.

## Códigos de seguimiento de A Tu Hora Express y código postal

Busca tu número de servicio o expedición en el correo de envío de la tienda online o en el detalle de tu pedido. Los códigos de A Tu Hora Express aparecen en tres formatos numéricos:

| Formato (inventado) | Qué es |
| --- | --- |
| `0000004829173640` | Número de servicio de 16 dígitos (`000000` + 10 dígitos). Requiere tu código postal español de 5 dígitos para consultarlo. |
| `4829173640` | Código corto de 10 dígitos (el rastreador añade seis ceros delante). También requiere el código postal de 5 dígitos. |
| `000000482917364000128013` | Código largo de 24 dígitos: número de servicio de 16 dígitos + `001` + código postal de entrega de 5 dígitos (aquí `28013`). Comparte esta longitud con [Ciblex](carrier:ciblex), [InPost](carrier:inpost), [bpost](carrier:bpost), [Austrian Post](carrier:austrian-post) y [Express One](carrier:express-one). |

**Por qué hace falta el código postal.** En [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli), los códigos de 10 y 16 dígitos solo devuelven el historial si introduces también el **código postal español de entrega (5 dígitos)**. El código de 24 dígitos ya incluye el código postal al final, por lo que no hace falta teclearlo aparte. Si tu envío usa otro formato, consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de A Tu Hora Express

El rastreador de A Tu Hora Express da cada escaneo en español, con un código de estado (`cod`) y la hora de Madrid:

| A Tu Hora Express indica (código) | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Documentado` (`0`) | El remitente dio de alta el envío electrónicamente antes de la entrada en agencia. | «Etiqueta creada» |
| `En tránsito` (`1`) | El paquete viaja por la red de A Tu Hora Express entre delegaciones. | «En tránsito» |
| `En agencia distribución` (`11`) | Ha llegado a la delegación local encargada de repartir en tu código postal. | «En tránsito» |
| `En reparto` (`2`) / `En realización` | El repartidor lo lleva en ruta para intentar la entrega hoy. | «En reparto» |
| `Incidencia - Pendiente nuevo reparto` (`3`), `Ausente`, `Dirección incorrecta o incompleta`, `Rechazado` | No se pudo completar la entrega (ausencia, error en la dirección, rechazo o envío pendiente de nuevo intento). | «Intento de entrega» |
| `Incidencia - Falta parcial` (`3`), `Incidencia - No cumplimiento agencia` / `Incumplimiento de la agencia` | Incidencia operativa, como bulto faltante en una expedición o falta de entrega por parte de la agencia. | «Incidencia» |
| `Pendiente de realizar` (`5`) | Aparece junto a una incidencia a la misma hora indicando que queda pendiente un nuevo reparto; la incidencia marca la etapa. | *(mantiene la etapa de la incidencia)* |
| `Realizado con éxito` (`4`) / `Entregado con éxito` | Paquete entregado por A Tu Hora Express. | «Entregado» |
| `Devuelto` | Envío devuelto hacia el remitente. | «Devuelto al remitente» |

## Servicios de entrega y condiciones públicas

En [atuhoraexpress.com](https://atuhoraexpress.com/) y [atuhoraexpress.com/home/](https://atuhoraexpress.com/home/), A Tu Hora Express detalla cinco servicios principales —*Express 24h*, *Economy 48h*, *Masivo 72h*, *Logística Inversa* y *B2B Premium*— a través de su red de delegaciones, con acceso al seguimiento desde [atuhoraexpress.com/tracking/](https://atuhoraexpress.com/tracking/) y [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli).

La web de A Tu Hora Express no publica un número de intentos de entrega, límites de peso o medidas ni días de custodia. Si el pedido llegó de fuera de la Unión Europea, paga 3 € de derecho de aduana por artículo si vale 150 € o menos, y la tasa de tramitación de la UE de 2 € por artículo, salvo que la tienda los cobrara al comprar; consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de A Tu Hora Express retrasado, con incidencia o no recibido

En compras por internet, el contrato de transporte corresponde a la tienda, que debe gestionar cambios de dirección, reenvíos o reembolsos si el paquete encadena incidencias o supera la fecha prevista.

- **Incidencias repetidas o `Pendiente nuevo reparto`:** comprueba con la tienda que tu dirección, piso/puerta y teléfono sean correctos y escribe a `info@atuhoraexpress.com` indicando tu código de 16 o 24 dígitos y el código postal de entrega.
- **Aparece `Realizado con éxito` pero no lo tienes:** pregunta primero a familiares, vecinos y en la portería o conserjería, y avisa cuanto antes tanto a la tienda como a A Tu Hora Express.
- Para más pasos, consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating), [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de A Tu Hora Express

Datos oficiales publicados en [atuhoraexpress.com](https://atuhoraexpress.com/), el [Aviso Legal](https://atuhoraexpress.com/aviso-legal/) y el formulario [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/):

- **Teléfono:** `911 631 356`.
- **Correo de atención:** `info@atuhoraexpress.com`.
- **Formulario web:** [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/).
- **Protección de datos:** `protecciondedatos@atuhoraexpress.com`.
- **Sede en Madrid:** Calle Artesanos 10, 28830 San Fernando de Henares (Madrid) (domicilio social de *A TU HORA EXPRESS, S.L.*, NIF `B19300748`: C/ Espliego 16, 19208 Alovera, Guadalajara).

> Desconfía de SMS o mensajes de WhatsApp que pidan pagar con tarjeta una pequeña tasa para reprogramar una entrega de A Tu Hora Express. Comprueba tu código directamente en [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) o consulta en `info@atuhoraexpress.com`. Ver [mensajes falsos de entrega](guide:delivery-scam-texts).

## Seguir un paquete de A Tu Hora Express en Peek

[Peek](/) reconoce automáticamente cualquier código de 16 dígitos que empieza por seis ceros (`0000004829173640`, dígitos inventados) como A Tu Hora Express con alta confianza. Un código de 24 dígitos (`000000482917364000128013`) encaja con varios transportistas: Peek pregunta a A Tu Hora Express, con el código postal que lleva el código, a [Austrian Post](carrier:austrian-post), [InPost](carrier:inpost) y [Ciblex](carrier:ciblex) si lo conocen, y a [Express One](carrier:express-one) solo si ninguno lo conoce, y muestra «A Tu Hora Express tiene este paquete.» cuando A Tu Hora Express responde. Si tienes un código corto de 10 dígitos (`4829173640`), pulsa «Cambiar» al añadir o «Cambiar transportista» en la pantalla del paquete y elige A Tu Hora Express (buscar «A Tu Hora» también lo encuentra).

Para códigos de 10 y 16 dígitos, Peek ofrece el campo opcional «Código postal de entrega», con la línea «A Tu Hora Express solo muestra el paquete con el código postal de entrega.» Hasta que lo añades, la pantalla del paquete ofrece «Añadir código postal», y Peek sigue el paquete a través de servicios de seguimiento, una vez al día tras un día sin respuesta. Al introducir el código postal, Peek lo revisa al momento. El código de 24 dígitos lleva el código postal en sus cinco últimas cifras, así que no lo necesita.

Cuando A Tu Hora Express responde, Peek muestra la etapa («Etiqueta creada», «En tránsito», «En reparto», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), el texto en español de cada escaneo con su población, la fecha prevista de entrega mientras A Tu Hora Express la muestra en un envío abierto, y el peso. Los códigos de estado fijan la etapa; Peek no los muestra. Los escaneos van fechados con la hora de Madrid y Peek los muestra con la hora de tu dispositivo. Peek omite las coordenadas GPS junto a los escaneos, la barra de progreso, la fecha de salida, la referencia de cliente, los nombres de remitente y destinatario, la dirección de destino, el número de bultos, el volumen, las observaciones, el justificante de entrega y las opciones de entrega; el rastreador no muestra punto de recogida ni nombre del servicio.

Mientras tengas el paquete abierto en Peek, o el día de su entrega con las notificaciones activadas, Peek revisa hasta cada 10 minutos, y el día en que A Tu Hora Express muestra `En reparto` o `En realización`, cada 2 minutos desde las 6:00, hora de Madrid, mientras el rastreador de A Tu Hora Express responda. Si no, revisa cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, revisa cada hora sigas como sigas el paquete, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el rastreador de A Tu Hora Express no responde y Peek sigue el paquete solo a través de servicios de seguimiento, revisa cada hora, cada 3 horas tras un día sin novedades y hasta cada 15 minutos el día en que el paquete sale a reparto.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete dice entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a A Tu Hora Express.» Tras tres semanas sin un escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a A Tu Hora Express.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo sigue las actualizaciones y no puede reprogramar entregas, cambiar tu dirección ni contactar con A Tu Hora Express por ti.

## Preguntas frecuentes sobre A Tu Hora Express

### ¿Por qué A Tu Hora Express me pide el código postal?

A Tu Hora Express solo muestra un código de 10 o 16 dígitos con su código postal español de entrega de 5 dígitos, en [mydelivery.atuhoraexpress.com](https://mydelivery.atuhoraexpress.com/#/login-form-cli) como en Peek, donde el campo es opcional, pero A Tu Hora Express no responde sin él. Si tu código tiene 24 dígitos, los últimos cinco ya son el código postal.

### ¿Qué significa `Realizado con éxito` en A Tu Hora Express?

`Realizado con éxito` (código `4`) significa que A Tu Hora Express ha registrado el paquete como entregado. Si no lo tienes, pregunta en casa, a tus vecinos o en portería y avisa cuanto antes a la tienda y a `info@atuhoraexpress.com`.

### ¿Qué significa `Incidencia - Pendiente nuevo reparto`?

Indica que un intento de entrega no se pudo completar y el paquete queda pendiente de salir de nuevo a reparto (suele acompañarse de `Pendiente de realizar` a la misma hora). Si se repite, verifica dirección y teléfono con la tienda.

### ¿Cómo contacto con A Tu Hora Express?

Puedes llamar al `911 631 356`, escribir a `info@atuhoraexpress.com` o enviar el formulario [Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) indicando tu número de seguimiento y tu código postal de 5 dígitos.

:::sources
- [A Tu Hora Express: Inicio](https://atuhoraexpress.com/) – reparto de última milla en España, delegaciones, sede en Madrid, `911 631 356` e `info@atuhoraexpress.com`
- [A Tu Hora Express: Inicio (inglés)](https://atuhoraexpress.com/home/) – Express 24h, Economy 48h, Masivo 72h, Logística Inversa, B2B Premium y listado de delegaciones
- [A Tu Hora Express: Página de seguimiento](https://atuhoraexpress.com/tracking/) – acceso al seguimiento desde la web
- [A Tu Hora Express: Portal MyDelivery](https://mydelivery.atuhoraexpress.com/#/login-form-cli) – consulta directa de envíos
- [A Tu Hora Express: Aviso Legal](https://atuhoraexpress.com/aviso-legal/) – *A TU HORA EXPRESS, S.L.*, NIF `B19300748`, domicilio social en Alovera (Guadalajara) y `protecciondedatos@atuhoraexpress.com`
- [A Tu Hora Express: Ayúdanos a mejorar](https://atuhoraexpress.com/ayudanos-a-mejorar/) – formulario de atención
- [Universal Parcel Scraper: a-tu-hora-express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/a-tu-hora-express) – detección de 10/16/24 dígitos, requisito opcional de código postal de 5 dígitos, códigos de estado (`0`, `1`, `11`, `2`, `3`, `4`, `5`), hora local `Europe/Madrid`, fecha prevista, peso y campos omitidos
:::
