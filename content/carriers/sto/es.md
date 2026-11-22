---
title: Seguimiento STO Express: números 申通快递, estados y contacto
description: Rastrea un paquete de STO Express (申通快递) en China: formato de sus números, qué significan 签收 y los demás escaneos, puntos de recogida y soporte.
slug: seguimiento-sto-express
published: 2026-11-22
updated: 2026-11-22
---

STO Express (申通快递, Shentong) es una empresa china de paquetería urgente fundada en 1993 que se define como la primera compañía privada de mensajería urgente de China. Un número STO suele corresponder a un paquete dentro de China, a menudo un pedido online enviado a ti, a un conocido o al almacén de un transitario. Puedes rastrearlo en [la web de STO](https://www.sto.cn/pc), en la casilla `查快递` (solo en chino), o en la casilla de arriba.

## Número de seguimiento STO Express: qué aspecto tiene

Los números de STO (`快递单号`) contienen únicamente dígitos. La consulta de STO en Peek acepta 12, 13 o 15 dígitos; las guías más recientes tienen 15. Con dígitos inventados:

| Número (inventado) | Qué es |
| --- | --- |
| `774829173654281` | `77` y 13 dígitos más: una guía de STO Express. [BRT](carrier:brt) y Yunda también usan 15 dígitos, así que Peek consulta a STO. |
| `482917365428` | 12 dígitos: un formato antiguo de STO, compartido con [FedEx](carrier:fedex), [SF Express](carrier:sf-express), [ZTO Express](carrier:zto) y muchos otros. |
| `4829173654281` | 13 dígitos: otro formato antiguo de STO, que Peek no vincula automáticamente a STO. |

Los espacios no importan en Peek.

**Dónde encontrarlo.** El vendedor lo incluye en los datos de envío del pedido y figura en la etiqueta (`快递面单`) pegada al paquete. Si no lo encuentras, mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**En la web de STO.** Escribe el número en la casilla `查快递` de `sto.cn` y pulsa `查询` (buscar). El contrato de servicio de STO indica que su web, app y cuenta de WeChat ofrecen seguimiento durante un año desde la fecha de envío.

## Estados de seguimiento de STO Express explicados

Cada escaneo del seguimiento de STO incluye un tipo en chino, una hora en hora de China (UTC+8) y la instalación de STO que lo registró. Peek muestra estos tipos en inglés:

| Escaneo de STO | En Peek | Estado en Peek | Qué significa para ti |
| --- | --- | --- | --- |
| `收件` | `Picked up` | Admitido por el transportista | STO recogió el paquete al remitente. |
| `发件` | `Departed`, con `for` y el siguiente centro cuando STO lo nombra | En tránsito | Salió de una instalación de STO rumbo a la siguiente. |
| `到件` | `Arrived` | En tránsito | Llegó a un centro de clasificación o delegación. |
| `派件` | `Out for delivery` | En reparto | Un mensajero lo lleva en ruta. |
| `驿站代收` | `At a parcel station for collection` | Listo para recoger | Una estación de paquetería (`驿站`) lo recibió por ti. Ve a recogerlo. |
| `柜机代收` | `In a parcel locker for collection` | Listo para recoger | Está en una taquilla automática. Ve a recogerlo. |
| `客户签收` | `Delivered` | Entregado | Alguien firmó la recepción. STO también registra esto cuando firma una estación o taquilla. |
| `签收` | `Delivered` | Entregado | Firmado. |

Otros tipos de escaneo mantienen en Peek el texto en chino de STO. Un tipo que indique devolución (`退回` o `退件`) aparece como `Return to the sender started` («Incidencia»). Los escaneos de entrega posteriores corresponden al viaje de vuelta: `Out for delivery back to the sender`, `In a parcel locker or station on its way back` y `Returned to the sender`.

**¿Enviado a un transitario?** Entonces `签收` significa que el almacén del transitario firmó la recepción y el tramo de STO terminó. A partir de ahí toma el relevo el número del transitario.

## Entrega de STO Express en China: repartidores, estaciones y taquillas

STO indica que su red alcanza el 99,7 % de los municipios chinos. Su contrato de servicio le permite entregar un paquete de cuatro maneras:

- a ti personalmente;
- a alguien que conviva contigo o a una persona designada por ti;
- en un punto de recogida (`代收点`) o taquilla (`自提柜`) en la zona de la dirección;
- de otro modo solicitado por ti o por el remitente.

Una vez entregado el paquete de alguna de estas formas con el embalaje exterior intacto, el contrato da por cumplido el servicio de STO. Por eso «Listo para recoger» en Peek es normal: el paquete espera en una estación o taquilla cercana. La dirección exacta de la estación aparece en el seguimiento propio de STO; mientras el paquete aguarda allí, Peek extrae el código de recogida (`取件码`) cuando figura en el último escaneo.

Para modificar los datos del destinatario, la atención online de STO tiene una opción específica (`修改收件人信息`): solicítalo ahí (contacto abajo) o pídeselo al vendedor.

## Paquete STO sin actualizar, perdido o devuelto

**Nada todavía.** El seguimiento de STO no responde nada ante un número que aún no conoce ni ante uno que haya superado el límite de un año. Si sigue vacío, comprueba el número con el vendedor. Mientras tanto, un paquete asignado a STO Express muestra «Esperando a STO Express».

**Sin actualización.** Cuando un paquete aún en camino lleva 4 días sin nuevos escaneos, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a STO Express.», junto al enlace «Pide ayuda a STO Express» hacia la atención online de STO, donde la falta de actualizaciones (`快递单号没有记录更新`) es uno de los temas disponibles. Ver [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating) y [seguir paquetes desde China](guide:tracking-from-china).

**Perdido o dañado.** Pregunta primero al vendedor: fue él quien contrató a STO, no tú. Según el contrato de STO, el remitente — o quien este designe — reclama en un plazo de 30 días desde conocer la pérdida o daño, con justificantes. Sin seguro (`保价`), STO abona la pérdida real hasta 500 yuanes, o hasta 100 yuanes en documentos. Un envío asegurado se indemniza hasta el valor declarado. Sin seguro ni prueba de valor, STO paga tres veces la tarifa de envío. Si el retraso es culpa de STO, condona los gastos de envío. Ver [paquetes perdidos](guide:lost-parcel).

**Devuelto.** STO puede devolver un paquete cuando la dirección es confusa, el destinatario lo rechaza o fallan dos intentos de entrega sin culpa de STO; el remitente corre con el coste. En Peek verás `Return to the sender started` y luego el trayecto de vuelta. Ver [devuelto al remitente](guide:returned-to-sender).

**Entregado, pero no está.** `签收` y `客户签收` indican que alguien firmó: tú, un conviviente, una persona designada o una estación o taquilla que lo recogió por ti. Comprueba estaciones y taquillas cercanas, la recepción del edificio y, si corresponde, el transitario. Para reclamar, usa el botón de quejas (`投诉反馈`) del servicio online de STO. Ver [entregado pero no recibido](guide:delivered-not-received).

## Contacto y atención al cliente de STO Express

Canales publicados por STO:

- **Atención al cliente online** en `page.sto.cn`, enlazada desde `sto.cn` como `在线咨询` (consulta online): asistente de chat con agentes humanos de 8:30 a 22:00 (hora de Pekín), con botones para enviar un paquete, consultar tarifas y presentar reclamaciones (`投诉反馈`).
- **WeChat:** cuenta de servicio y miniprograma para envíos. STO también está en Douyin. Los tres enlaces están al pie de `sto.cn`.

Esta página no incluye teléfono: Peek solo muestra contactos leídos en el texto de las páginas oficiales de STO, y ninguna publica un número. Para dudas sobre tu pedido, pregunta al vendedor.

## Seguir un paquete de STO Express con Peek

La forma de un número STO por sí sola no permite a Peek asegurar que pertenece a STO. Pégalo igualmente: [Peek](/) sigue a más de 4500 transportistas.

Con un número de 15 dígitos empezado por `77`, Peek consulta al instante a STO Express y a BRT mostrando «Comprobando servicios de seguimiento…». Si STO tiene el paquete, aparece «STO Express tiene este paquete.» y Peek lo asigna ahí. Si no, indica «aún no encontrado · Peek seguirá comprobando». Añádelo de todos modos: el paquete mostrará «Probablemente STO Express» y «Esperando el primer escaneo de STO Express», y Peek seguirá preguntando a STO cada 6 horas pasadas las primeras horas en la app de iPhone o Android o en tu cuenta. En una consulta sin cuenta, Peek comprueba durante las primeras 6 horas, luego al abrir el enlace (como máximo cada 6 horas, y una vez al día tras dos días) y, con notificaciones activas, una vez al día durante 30 días. Tras una semana sin resultados, te pide revisar el número con el remitente o elegir otro transportista.

Peek solo consulta automáticamente a STO con números empezados por `77`. Con uno de 12 dígitos consulta primero a otros transportistas con ese formato (como DHL y BRT) y después a servicios de seguimiento; si encuentran el paquete y nombran a STO Express, Peek lo asigna a STO y le pregunta en la siguiente comprobación.

Para que Peek lea el seguimiento propio de STO desde el principio, pulsa «Cambiar» y elige STO Express en «Encaja con este número» (12 dígitos, o 15 empezados por `77`). Para 13 dígitos o 15 que no empiecen por `77`, elígelo en «Todos los transportistas» o busca `STO` o `申通`. En un paquete ya añadido usa «Cambiar transportista». La consulta STO de Peek solo admite 12, 13 o 15 dígitos: para cualquier otra longitud avisa «no emite números como este».

Peek lee el seguimiento propio de STO, el mismo que usa su atención online. En cada escaneo muestra el paso (tabla superior), la ciudad del centro STO (su nombre en chino si Peek no puede ubicarla) y la hora, leída en hora de China y mostrada en tu zona horaria («Horas en tu zona horaria:»). Un escaneo repetido se muestra una sola vez, y aparece el peso cuando las básculas de STO lo registraron. También lee el código de recogida (`取件码`) del último escaneo de estación o taquilla mientras el paquete espera allí y el nombre de quien firmó la entrega, pero omite el texto completo de STO, nombres y teléfonos de repartidores y estaciones y las direcciones de recogida. STO no ofrece fecha estimada de entrega, por lo que Peek no la muestra. Peek sigue solo el número de STO: el del transitario se añade aparte.

Mientras tengas el paquete abierto en Peek — o el día de la entrega con las notificaciones activadas — Peek consulta directamente a STO hasta cada 10 minutos, y hasta cada 2 minutos a partir de las 6:00 (hora de Pekín) el día en que STO lo escanea en reparto, mientras el rastreador de STO responda; fuera de ese caso, y tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Puede enviarte avisos de «Cada escaneo», «Pasos importantes» o «Solo la entrega». No puede contactar con STO, el transitario ni el vendedor por ti.

## Preguntas sobre el seguimiento de STO Express

### ¿Es fiable STO Express?

Sí. STO Express nació en 1993, se define como la primera empresa privada china de mensajería urgente y pionera de la franquicia en el sector. Cotiza en el mercado chino de acciones A y declara 75 centros de clasificación, 5.075 delegaciones independientes y más de 98.000 puntos locales.

### ¿Qué significa 签收 en el seguimiento de STO?

Firmado: Peek muestra `签收` y `客户签收` como «Entregado». Firmaste tú, alguien en tu domicilio, una persona designada o una estación o taquilla. `驿站代收` y `柜机代收` significan que una estación o taquilla guarda el paquete para que lo recojas.

### ¿Por qué mi número de STO no muestra seguimiento?

STO aún no ha escaneado el paquete, el número es erróneo o el envío tiene más de un año: STO ofrece seguimiento durante un año. Comprueba el número con el vendedor o añádelo a Peek, que seguirá comprobando.

### ¿Dónde está el código de recogida de mi paquete STO?

Mientras el paquete espera en una estación o taquilla, Peek lee el código de recogida (`取件码`) del último escaneo cuando consta en él, y muestra `At a parcel station for collection` o `In a parcel locker for collection`. La dirección exacta de la estación o taquilla está en el seguimiento de STO en `sto.cn`.

### ¿Cómo contacto con STO Express?

Mediante la atención al cliente online en la web de STO: atienden agentes humanos de 8:30 a 22:00 (hora de Pekín) y dispone de botón de reclamaciones (`投诉反馈`). Para un pedido de tienda, pregunta primero al vendedor.

:::sources
- [STO Express: 申通快递官网 (home)](https://www.sto.cn/pc) – casilla de seguimiento, cuenta de servicio y miniprograma de WeChat, Douyin
- [STO Express: 关于我们 (about STO)](https://www.sto.cn/pc/about) – fundada en 1993, primera mensajería urgente privada, modelo de franquicia, cotización en acciones A, centros de clasificación, delegaciones, puntos locales, cobertura municipal
- [STO Express: 申通快递服务协议 (express service agreement)](https://www.sto.cn/h5/standard) – seguimiento un año, modalidades de entrega, devoluciones, indemnización con y sin seguro, reclamación en 30 días, retraso
- [STO Express: 在线客服 (online customer service)](https://page.sto.cn/ued-projects/sto-customer-onlinekf?fromSource=pc-stocn&pc=true) – chat, atención humana 8:30–22:00, quejas, temas de falta de actualización y cambio de datos del destinatario
- [Universal Parcel Scraper: STO Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/sto) – formatos de números, tipos de escaneo, devoluciones, hora de China, peso, código de recogida, firmante, campos mostrados y omitidos, sin fecha prevista
:::
