---
title: Seguimiento de ZTO Express: números 中通快递 y estados
description: Rastrea un paquete de ZTO Express (中通快递) en China: formato de sus números, qué significan 已签收 y sus estados, estaciones y taquillas, plazos y teléfono 95311.
slug: seguimiento-zto-express
published: 2026-11-21
updated: 2026-11-21
---

ZTO Express (`中通快递`, Zhongtong) es una empresa china de mensajería urgente fundada en **Shanghái** el 8 de mayo de 2002; en 2025 gestionó 38.520 millones de paquetes. Un número de ZTO Express suele corresponder a un envío dentro de **China continental**, a menudo una compra online (ZTO transporta pedidos de Taobao desde 2008) rumbo a una dirección en China o al almacén de un transitario internacional. Puedes consultarlo en el [buscador en chino de ZTO](https://www.zto.com/check) o pegarlo en el cuadro de arriba. Los paquetes que el grupo envía al extranjero viajan con ZTO International (`中通国际`), que usa números propios empezados por `771` en `ztoglobal.com`.

## Cómo es el número de seguimiento de ZTO Express

ZTO lo llama número de albarán o guía (`运单号`). Peek reconoce dos formatos para ZTO Express. Ejemplos con cifras inventadas:

| Número (inventado) | Qué identifica |
| --- | --- |
| `784920573280` | 12 dígitos: guía de ZTO Express, un formato que también emplean [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [DHL](carrier:dhl), [BRT](carrier:brt) y otros operadores. |
| `73184920573649` | 14 dígitos empezados por `7`: también ZTO Express, compartido con [SEUR](carrier:seur), [BRT](carrier:brt), [Chronopost](carrier:chronopost), [DPD UK](carrier:dpd-uk) y otras redes DPD. |
| `77148291057364` | `771` y 11 dígitos más: formato de ZTO International (`中通国际`). Peek pregunta primero a ZTO International. |

Los espacios no importan al pegarlo en Peek. El buscador propio de ZTO admite hasta 10 números a la vez separados por comas o espacios (`最多可查询10条，以逗号、空格隔开`).

**Dónde encontrarlo.** El remitente (normalmente la tienda) conserva su copia del albarán (`发件联`) y te pasa el código en los datos de envío. ¿No lo encuentras? Consulta [cómo encontrar el número de seguimiento](guide:find-tracking-number).

**En la web de ZTO.** Escribe el número en `zto.com/check` y pulsa `查询` (buscar). Puede aparecer antes un puzle de verificación; escaneando el código QR de esa página con WeChat puedes suscribirte a los avisos en tiempo real.

## Qué significan los estados de seguimiento de ZTO Express

Cada evento lleva un nombre breve de etapa en chino que Peek muestra en inglés:

| ZTO dice | Peek muestra | Estado en Peek | Qué significa para tu paquete |
| --- | --- | --- | --- |
| `已取件` | `Collected` | «Admitido por el transportista» | Un mensajero de ZTO lo ha recogido en origen. |
| `已收件` | `Received` | «Admitido por el transportista» | Una delegación de ZTO ha recepcionado el paquete. |
| `运输中` | `In transit` | «En tránsito» | En camino entre centros de clasificación y delegaciones de ZTO. |
| `派件中` | `Out for delivery` | «En reparto» | Un repartidor de ZTO lo lleva en su ruta de entrega. |
| `已代收` | `Held at a collection point for the recipient` | «Listo para recoger» | Un punto de recogida (como una estación de paquetería) lo guarda para que vayas a buscarlo. |
| `已签收` | `Signed for` | «Entregado» | Alguien ha firmado la recepción (`签收`): tú, otra persona en tu nombre o el almacén de destino. |
| `异常通知` | `Exception notice` | «Incidencia» | ZTO ha anotado un problema. Peek muestra solo la etapa; el buscador de ZTO incluye el texto completo. |

Otras etapas, como devoluciones (`退回件`) o reenvíos, conservan en Peek sus palabras chinas originales. Más información en [qué significan los estados de seguimiento](guide:tracking-statuses).

**¿Va dirigido a un transitario?** Entonces `已签收` indica que el almacén del transitario en China ha firmado la entrega y la parte nacional de ZTO Express ha concluido: a partir de ahí manda el número internacional del transitario. ZTO cuenta también con un servicio propio de consolidación internacional (`中通国际集运`) que agrupa varios paquetes en almacén para enviarlos juntos; en China continental se atiende llamando al `95311` y pidiendo internacional (`95311转国际`).

## Entrega de ZTO Express en China: repartidores, estaciones y taquillas

Estas normas se aplican a los repartos realizados dentro de **China continental**:

El mensajero de ZTO entrega el paquete en mano o lo deposita en una estación de paquetería (`驿站`), un punto colaborador (`代收点`) o una taquilla inteligente (`智能快件箱`) cercana, opciones previstas en el acuerdo de servicio de ZTO con los remitentes. Las *Medidas de Administración del Mercado Exprés* de China (`快递市场管理办法`), vigentes desde el 1 de marzo de 2024, exigen no obstante tu consentimiento previo para dejarlo en taquilla o estación y te reconocen el derecho a revisar el envío frente al albarán al recibirlo, pudiendo abrirlo o rechazarlo si el embalaje exterior está visiblemente dañado o el contenido es frágil.

En entregas sin contacto personal (puerta, taquilla o estación), el acuerdo de ZTO pide grabar en vídeo (`录制视频`) la recogida, apertura y comprobación del contenido como prueba; una entrega efectuada según lo pactado queda confirmada si nadie presenta objeción por escrito en tres días (`三日`).

Según las preguntas frecuentes oficiales de ZTO en China:

- **¿Sin código de recogida para la taquilla?** Busca la oficina que atiende tu dirección en el [buscador de oficinas de ZTO](https://www.zto.com/service/station) (`服务网点查询`) y llámala, o facilita el número de guía en el `95311` para que te den el teléfono del repartidor o de la oficina.
- **¿Piden pagar por exceso de tiempo en la taquilla?** ZTO indica pulsar omitir (`跳过`) al recogerlo para no pagar nada.
- **¿Piden comisión de entrega?** La oficina de reparto no puede cobrar ninguna tasa de entrega al destinatario (`派件网点不可以向收件客户收取任何派送费`).
- **¿Fuera de la zona de reparto?** Recógelo en la oficina (`自提`) o pide al remitente que lo reenvíe con otra mensajería.
- **¿Dirección errónea?** No puede cambiarse cuando ya está en reparto o entregado; antes de eso, el remitente debe solicitarlo a la oficina de origen según dónde esté el envío.

## Paquete de ZTO sin cambios, perdido o devuelto

**Aún sin datos.** Si el buscador de ZTO no devuelve nada para ese número, confírmalo con el vendedor. Mientras tanto, un envío guardado bajo ZTO Express muestra «Esperando a ZTO Express», y uno sin transportista asignado muestra «Todavía no hay historial de seguimiento».

**Sin actualizaciones.** Cuando un paquete aún en camino lleva 4 días sin escaneos nuevos, Peek indica «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a ZTO Express.» e incluye el enlace «Pide ayuda a ZTO Express» hacia su página de contacto. Consulta [por qué no se actualiza el seguimiento](guide:tracking-not-updating) y [seguir un paquete desde China](guide:tracking-from-china).

**Perdido o dañado (en China continental).** Habla con el vendedor: bajo el contrato de ZTO, el remitente (o quien este designe) debe reclamar por escrito dentro de los 30 días (`三十日`) siguientes a conocer la pérdida o el daño. Sin seguro de valor declarado (`保价`), ZTO abona como máximo 500 yuanes (`500元`); con seguro, hasta el valor asegurado. Quedan excluidas las faltas o daños internos cuando el embalaje exterior está intacto. Mira [paquete perdido](guide:lost-parcel).

**Devuelto.** En China continental, ZTO puede devolver un envío si la dirección es confusa, el destinatario lo rechaza o fallan dos intentos de entrega por causas ajenas a ZTO (`两次派送未成功的`). Mira [devuelto al remitente](guide:returned-to-sender).

**Figura entregado pero no aparece.** `已代收` significa que espera en un punto de recogida, y `已签收` que alguien firmó (portería, estación o almacén de transitario). Para reclamar en China continental, da el número de guía a atención al cliente en el `95311` o usa el [formulario de quejas de ZTO](https://www.zto.com/after/complaint/problemTypes) (`投诉反馈`, requiere iniciar sesión); si la respuesta no te convence o no llega a tiempo, la normativa china permite elevar una reclamación (`申诉`) ante la autoridad postal (`邮政管理部门`). Mira [entregado pero no recibido](guide:delivered-not-received).

## Teléfono y atención al cliente de ZTO Express

Canales oficiales de ZTO Express en **China continental**:

- **`95311`:** línea nacional de atención al cliente (`全国统一客服热线`, *Customer Service Hotline* en la web inglesa de ZTO), de `08:30` a `17:30` (hora de China) según su FAQ.
- **`95720`:** número saliente exclusivo desde el que llaman los repartidores de ZTO (`中通业务员专属外呼号码`).
- **Atención online** (`在线客服`) en `zto.com`, además del miniprograma de WeChat (`微信小程序`), su cuenta oficial de WeChat y la app de ZTO.

Sobre el pedido en sí, pregunta primero al vendedor donde hiciste la compra.

## Seguir un paquete de ZTO Express con Peek

Pega el código en [Peek](/): sigue a más de 4.500 transportistas. Pegar un número de ZTO por sí solo no hace que Peek consulte automáticamente a ZTO Express: primero pregunta a otros operadores que usan ese formato (como [DHL](carrier:dhl) y [BRT](carrier:brt) en 12 cifras; DPD, [Chronopost](carrier:chronopost), [Hermes](carrier:hermes-de), [SEUR](carrier:seur) y [BRT](carrier:brt) en 14 cifras; ZTO International primero en números `771`) y después a servicios de seguimiento, mostrando mientras tanto «Comprobando servicios de seguimiento…». Si esos servicios localizan el envío y nombran a ZTO Express, Peek lo archiva bajo ZTO Express y consulta a ZTO en la siguiente comprobación; si no aparece nada, la línea indica «aún no encontrado · Peek seguirá comprobando». Para leer el buscador propio de ZTO Express desde el principio, toca «Cambiar» y elige **ZTO Express** dentro de «Encaja con este número», o busca `ZTO` o `中通快递` (`中通` muestra también ZTO International); en un paquete ya añadido, usa «Cambiar transportista». La consulta directa de ZTO Express solo admite 12 dígitos o 14 dígitos empezados por `7`; con cualquier otro formato indica «no emite números como este».

Peek lee el buscador oficial de ZTO y abre un navegador cuando ZTO pide verificación. De cada evento muestra la etapa en inglés (tabla superior), la ciudad o el nombre en chino del centro ZTO y la hora en la zona horaria de tu dispositivo (en peektracker.com y en las apps de iPhone y Android, debajo del historial consta «Horas en tu zona horaria: …»). Omite la frase completa del escaneo y, con ella, contactos de repartidores, teléfonos y el nombre y dirección del destinatario, y no muestra fecha estimada. Como el buscador web público de ZTO puede omitir los primeros registros del pedido, también pueden faltar en Peek. El código internacional de un transitario debe añadirse como otro paquete aparte.

Mientras mantengas abierto el paquete o, el día de entrega, tengas activadas las notificaciones, Peek comprueba novedades hasta cada 10 minutos —y hasta cada 2 minutos (cada 5 minutos cuando la consulta a ZTO abrió un navegador) desde las 6:00 locales el día en que ZTO escanea el paquete «En reparto», mientras responda el buscador de ZTO (solo mediante servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—. En los demás casos, y tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta cada hora. Alertas disponibles: «Cada escaneo», «Pasos importantes» o «Solo la entrega» / «Solo el día de entrega». Peek no puede contactar con ZTO, el transitario ni el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de ZTO Express

### ¿Es fiable ZTO Express?

Sí. ZTO Express se fundó en Shanghái el 8 de mayo de 2002, salió a bolsa en Nueva York el 27 de octubre de 2016 y en Hong Kong el 29 de septiembre de 2020 (primera empresa de mensajería urgente cotizada en ambas plazas). En 2025 su red superaba los 31.000 puntos de servicio y llegaba a más del 99 % de los distritos y condados de China.

### ¿Son lo mismo ZTO Express y ZTO International?

Pertenecen al mismo grupo, pero tienen servicios y buscadores distintos. El contrato de ZTO Express cubre solo envíos con origen y destino en China continental (sin Hong Kong, Macao ni Taiwán), rastreados en `zto.com/check`; ZTO International, la división internacional creada en 2014, gestiona envíos transfronterizos con números `771` en `ztoglobal.com`.

### ¿Cuánto tarda ZTO Express dentro de China?

Según el FAQ de ZTO (plazos orientativos), suele tardar un día dentro de la misma provincia y entre Jiangsu, Zhejiang, Shanghái y Anhui, de 3 a 5 días entre otras provincias y de 5 a 7 días hacia zonas remotas. Si va al almacén de un transitario, suma después el tramo internacional de este.

### ¿Qué significa «已签收» en el seguimiento de ZTO?

Firmado en recepción: Peek muestra `Signed for` y estado «Entregado». Pudo firmar el destinatario, otra persona autorizada, conserjería o el almacén de un transitario. `已代收` es distinto («Listo para recoger»): una estación o punto de recogida guarda el paquete para que pases a retirarlo.

### ¿Cuál es el teléfono de ZTO Express?

El `95311`, su línea nacional de atención al cliente en China continental, operativa de `08:30` a `17:30` (hora de China). Allí, las llamadas desde el `95720` proceden de los repartidores de ZTO.

:::sources
- [ZTO Express: 运单查询 (consulta de envíos)](https://www.zto.com/check) – buscador en chino, hasta 10 códigos y avisos por WeChat
- [ZTO Express: 关于中通 (sobre ZTO)](https://www.zto.com/introduce/about/home) – fundación en 2002, salidas a bolsa, 38.520 millones de paquetes y red en 2025, Taobao desde 2008
- [ZTO Express: 首页 (inicio)](https://www.zto.com/) – 95311, 95720, app y WeChat
- [ZTO Express: Home (inglés)](https://en.zto.com/) – mención Customer Service Hotline: 95311
- [ZTO Express: 联系我们 (contacto)](https://www.zto.com/introduce/about/contact) – atención al cliente online
- [ZTO Express: 常见问题 (FAQ)](https://www.zto.com/after/answers) – horario 08:30–17:30, taquillas, prohibición de cobrar entrega, fuera de zona, cambio de dirección y plazos
- [ZTO Express: 服务网点查询 (buscador de oficinas)](https://www.zto.com/service/station) – oficina correspondiente a una dirección
- [ZTO Express: 投诉反馈 (reclamaciones)](https://www.zto.com/after/complaint/problemTypes) – formulario de quejas tras iniciar sesión
- [ZTO Express: 快递服务协议 (acuerdo de servicio)](https://mtoc.zto.com/agreement/service-protocol-pc.html) – ámbito en China continental, entrega, vídeo y plazo de 3 días, devoluciones, tope de 500 yuanes y reclamación en 30 días
- [ZTO Express: 中通国际 (ZTO International)](https://www.zto.com/introduce/service/international) – rama internacional del grupo desde 2014
- [ZTO Express: 中通集运 (consolidación)](https://www.zto.com/introduce/service/concentrated) – consolidación internacional y teléfono 95311
- [Ministerio de Transporte de China: 快递市场管理办法](https://xxgk.mot.gov.cn/gz/202401/t20240104_3980684.html) – revisión al recibir, consentimiento para taquillas/estaciones y reclamación postal
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos numéricos, nombres de etapa en inglés, consulta con navegador, sin reconocimiento automático y datos descartados
:::
