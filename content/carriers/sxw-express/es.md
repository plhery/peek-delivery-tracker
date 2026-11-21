---
title: Seguimiento de SXW Express: números SXW y estados
description: Rastrea un paquete de SXW Express (Shengxingwang) desde Shenzhen: formato del número SXW, buscador oficial, significado de cada estado, reparto y aduanas.
slug: seguimiento-sxw-express
published: 2026-11-21
updated: 2026-11-21
---

SXW Express es el nombre que emplea Peek para `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`), una empresa de logística transfronteriza con sede en **Shenzhen (China)** que envía mercancías al extranjero para exportadores y tiendas online. Si un vendedor chino ha despachado tu pedido con SXW, busca un código formado por `SXW` y doce cifras y pégalo en el cuadro de arriba. También puedes consultarlo en el buscador propio que se abre desde la pestaña *Express Tracking* de `sxwexpress.cn`, el mismo que lee Peek por ti.

## Cómo es el número de seguimiento de SXW Express

La web de SXW Express no explica el formato de sus códigos. El patrón que Peek reconoce como exclusivo de SXW Express tiene 15 caracteres: `SXW` seguido de doce dígitos. Ejemplos con cifras inventadas:

| Número (inventado) | Qué identifica |
| --- | --- |
| `SXW482910573649` | `SXW` y doce dígitos: el formato de SXW Express. Peek asigna SXW Express al instante. |
| `SXW48291057364` | Solo once dígitos: no coincide con el formato de SXW. Comprueba si falta una cifra en el aviso del vendedor. |
| `GFUS48291057364918` | `GFUS` y catorce dígitos: número de [GOFO Express](carrier:gofo), que el expediente de SXW puede incluir para el reparto en **Estados Unidos**. |

Los espacios, guiones y minúsculas no importan al pegar el número en Peek. El vendedor contrata el envío con SXW y te facilita el código; si no lo localizas, revisa [cómo encontrar el número de seguimiento](guide:find-tracking-number).

**Consulta en la web de SXW.** En la portada de `sxwexpress.cn`, la pestaña *Express Tracking* muestra un recuadro con *Enter your tracking number here!* y un botón *Track* que abre en otra pestaña el rastreador de SXW en chino, el cual cuenta también con una página en inglés (`sxwexpress.cn:8082/en/trackIndex.htm`). Al lado del botón hay un logotipo de 17TRACK que lleva a `17track.net`, un servicio de seguimiento externo ajeno al buscador propio de SXW.

## Qué significan los estados de seguimiento de SXW Express

En el localizador de SXW, su sistema interno escribe tres pasos en chino —que Peek traduce al inglés— y las líneas de transporte escriben en inglés. Estos son los mensajes habituales en orden:

| Localizador SXW / mostrado en Peek | Qué significa para tu envío | Peek muestra |
| --- | --- | --- |
| `Parcel Data Received`, `Electronic notification of order creation received.` | El vendedor ha enviado los datos del paquete; todavía nadie lo ha recibido físicamente. | «Etiqueta creada» |
| `货物电子信息已经收到` → `The electronic cargo information has been received` | El mismo registro electrónico en el sistema de SXW (traducido al inglés en Peek). | «Etiqueta creada» |
| `到达收货点` → `Arrived at the receiving point` | Primer escaneo físico en manos de SXW al llegar al punto de recepción (en inglés en Peek). | «Admitido por el transportista» |
| `Parcel picked up`, `Item has been collected` | Paquete recogido por la línea logística que lo transporta. | «Admitido por el transportista» |
| `货物离开操作中心` → `Goods leave the operation center` | La mercancía ha salido del centro operativo de SXW (en inglés en Peek). | «En tránsito» |
| `Parcel received at Shenzhen Sorting Centre.`, `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Entrada, clasificación y salida de un centro de clasificación en Shenzhen. | «En tránsito» |
| `Parcel departure in Transit Facility, Parcel Departure` | Salida de una instalación de tránsito en ruta. | «En tránsito» |
| `Customs declaration completed`, `Clearence processing completed - Export`, `Released From Export Customs` | Declaración aduanera de exportación completada en **China** antes del vuelo (a veces escrito `Clearence`). No está retenido en tu país. | «En tránsito» |
| `Parcel is leaving Airport, Parcel Leaving Port`, `International flight has departed` | El vuelo sale del aeropuerto en China. | «En tránsito» |
| `Parcel arrives at Airport, Parcel Arrival`, `International flight has arrived` | Llegada del avión al aeropuerto de destino. | «En tránsito» |
| `Start customs clearence` | Comienza el despacho aduanero de importación en destino (escrito `clearence`). | «En aduanas» |
| `Clearance processing completed - Import`, `Released From Import Customs` | Despacho de importación terminado; el envío continúa en ruta. | «En tránsito» |
| `Returned parcel has been recieved by shipper` | Paquete devuelto y recogido por el remitente en origen (escrito `recieved`). | «Devuelto al remitente» |

Al intervenir el sistema de SXW y el de la línea de transporte, pueden aparecer dos líneas equivalentes seguidas (`货物电子信息已经收到` junto a `Parcel Data Received`, o `到达收货点` junto a `Parcel picked up`). En algunas rutas SXW copia también eventos del transportista final (como `Arrived at GOFO Regional Hub` en EE. UU., `Outward MC - Handheld Acceptance` de Royal Mail en el Reino Unido o `Onboard for delivery` y `Delivered - Left in a safe place` de Australia Post). Más detalles en [qué significan los estados de seguimiento](guide:tracking-statuses).

**Horas y zonas horarias.** Peek lee los pasos registrados en China como hora de Pekín (`Asia/Shanghai`, UTC+8) y los convierte a la zona horaria de tu dispositivo; a partir de la primera línea cuyo lugar termina con el código de otro país (como `…, US`, `…, GB` o `AU`), Peek mantiene la hora local del país de destino. En peektracker.com y en las apps de iPhone y Android, debajo del historial aparece «Horas en tu zona horaria: …».

## Quién entrega los paquetes de SXW Express y qué aduanas se aplican

La web de SXW Express indica que ofrece entregas puerta a puerta `DDU/DDP` y reparto local de última milla (*local last-mile delivery*) en Europa, América, el Sudeste Asiático, Oriente Medio y África. Su catálogo menciona líneas de [UPS](carrier:ups) desde Hong Kong (*Hong Kong UPS Direct Line Double Clearing With Tax Included - F*, *Hong Kong UPS-5000 (H)*), una *ARAMEX Special Line*, un *Middle East and Europe small package* y un *US Express Mail Small Packet - F*, pero no especifica qué empresa reparte en cada país.

**Qué traspasos muestra Peek.** En envíos a **Estados Unidos**, el registro de SXW puede traer un número de [GOFO Express](carrier:gofo) (`GFUS` + 14 cifras): Peek muestra entonces «Entrega con GOFO Express» (salvo que el canal del registro nombre a otro operador como UPS), indica «Anunciado a GOFO Express, aún no recibido» mientras GOFO lo tenga registrado sin escaneos y pasa a seguirlo en GOFO cuando este registra un evento igual de reciente o posterior al último de SXW. Del mismo modo, si el canal nombra a [Swiss Post](carrier:swiss-post) con un número postal suizo válido hacia **Suiza** o **Liechtenstein**, Peek traspasa el seguimiento a Swiss Post en las mismas condiciones. Los intentos de entrega y puntos de recogida dependen de la empresa que reparta en destino: pide su nombre y su número local al vendedor y consulta [entrega fallida](guide:missed-delivery).

**Aduanas en España y la Unión Europea.** En la **Unión Europea**, desde el 1 de julio de 2026 las compras online procedentes de fuera de la UE en paquetes con valor intrínseco de hasta 150 € abonan un arancel aduanero de la UE de 3 € por artículo (*per item*), y desde el 1 de noviembre de 2026 se añade una tasa de tramitación de la Unión (*Union handling fee*) de 2 € por artículo cualquiera que sea el valor (un artículo agrupa mercancías del mismo envío con idéntica clasificación arancelaria, descripción y, cuando sea exigible, origen; por encima de 150 € rigen los aranceles ordinarios), además del IVA de importación en **España peninsular y Baleares**, salvo que ya se cobrara en la compra mediante IOSS. Según el ámbito territorial publicado por la Comisión Europea, las **Islas Canarias** pertenecen al territorio aduanero de la UE pero quedan fuera del área de IVA comunitaria, mientras que **Ceuta** y **Melilla** quedan fuera tanto del territorio aduanero como del ámbito de IVA de la UE. Pregunta al vendedor si envió el pedido en modalidad `DDP` (impuestos pagados) o `DDU`, y consulta [paquete retenido en aduanas](guide:customs).

## Paquete de SXW Express parado, retrasado o no recibido

**Solo aparece `Parcel Data Received` o `The electronic cargo information has been received`.** El vendedor ha generado el envío, pero aún no se ha escaneado el paquete. Si pasan varios días igual, pregunta a la tienda si ya lo entregó al transportista.

**«Esperando a SXW Express».** Mientras SXW tenga registrado el pedido sin escaneos reales (o únicamente con etapas escritas por adelantado) y los servicios de seguimiento tampoco tengan datos, Peek mantiene «Esperando a SXW Express»: en la app de iPhone o Android o con cuenta, Peek consulta SXW cada 6 horas tras las primeras horas; sin cuenta, durante las primeras 6 horas, luego al abrir el enlace (como máximo cada 6 horas, y una vez al día pasados dos días) y a diario hasta 30 días si activas las notificaciones. En cambio, si la página de SXW responde que no reconoce en absoluto ese número y ningún servicio de seguimiento lo encuentra, tras dos días Peek avisa: «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»

**`Customs declaration completed`.** Es la declaración de exportación en China previa al vuelo («En tránsito»), no una retención aduanera en tu país.

**Sin avances, marcado como entregado sin llegar, perdido o devuelto.** Si Peek muestra un número de GOFO Express o Swiss Post, revisa ese localizador; si no, guíate por el plazo estimado de la tienda y reclama al vendedor cuando venza. Más ayuda en [por qué no se actualiza el seguimiento](guide:tracking-not-updating), [seguir un paquete desde China](guide:tracking-from-china), [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Teléfono y atención al cliente de SXW Express

La web `sxwexpress.cn` no publica ningún número de teléfono, correo electrónico ni dirección postal: su página *Contact US* solo muestra el título y un panel *Online Support* vacío, y el acceso de miembros en `sxwexpress.cn:8082` es para cuentas de remitentes comerciales (no hace falta para rastrear).

El cliente de SXW Express es el vendedor, así que reclama primero a la tienda donde compraste y abre una disputa en la plataforma si no responde. En repartos dentro de EE. UU. con GOFO Express, contacta con GOFO para cuestiones de la entrega final.

## Seguir un paquete de SXW Express con Peek

[Peek](/) reconoce al instante un código de `SXW` y doce dígitos mostrando «Transportista detectado», ya que ningún otro operador del catálogo usa esa estructura (si te dieron otro formato, Peek abarca más de 4.000 transportistas; también puedes elegir SXW Express a mano pulsando «Cambiar» o «Cambiar transportista» y buscando `SXW Express`, `Shengxingwang` o `胜兴旺`).

Peek consulta el rastreador oficial de SXW sin cuenta, código postal ni teléfono. Muestra el estado, cada escaneo real (con las tres líneas chinas traducidas al inglés), la ubicación cuando consta, el servicio y el país de destino, y descarta nombres, dirección, códigos postales, número de bultos, peso, referencia de cliente, datos de pago, firmante, firma y prueba de entrega. Como SXW no ofrece fecha estimada, Peek no muestra ninguna.

**Etapas escritas por adelantado.** Peek retiene cualquier paso fechado en el futuro según la hora de Pekín, las líneas de trazado simulado (`CO_C_CR`) y cualquier paso grabado más de 12 horas antes de la hora que marca (o más de 6 horas antes en el mismo segundo que uno de ellos), porque son previsiones y no escaneos reales. Entonces, hasta que el paquete se entrega o se devuelve, el historial empieza con «Otras webs pueden mostrar más pasos, pero solo son previsiones.», y «Verlos» lista esas etapas en gris, cada una con «Previsto para» y su día. Estas etapas nunca cambian el estado ni envían alertas. Si el localizador de SXW falla o no conoce el código, Peek recurre a servicios de seguimiento.

Mientras tengas abierto el paquete o, el día de entrega, las notificaciones activadas, Peek comprueba cambios hasta cada 10 minutos —y hasta cada 2 minutos desde las 6:00 locales el día en que pasa a «En reparto» mientras responda el buscador directo de SXW (tras un traspaso a GOFO Express o Swiss Post, Peek sigue el ritmo del transportista de destino; solo mediante servicios de seguimiento: cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega con el paquete abierto o notificaciones activas)—. En los demás casos, y tras dos días sin novedades (o 12 horas en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta cada hora. Puedes activar «Cada escaneo», «Pasos importantes» (aduanas de importación como `Start customs clearence`, incidencias, reparto, intentos fallidos, recogida, entrega y devolución; `Customs declaration completed` no envía alerta) o «Solo la entrega» / «Solo el día de entrega». Peek no puede contactar con SXW Express ni con el vendedor por ti.

## Preguntas frecuentes sobre el seguimiento de SXW Express

### ¿Qué es SXW Express?

Es el nombre con el que Peek identifica a `Shenzhen Shengxingwang Supply Chain Management Co., Ltd.` (`深圳胜兴旺供应链管理有限公司`, `sxwexpress.cn`), un operador logístico transfronterizo de Shenzhen dedicado a carga marítima y aérea, tren China-Europa, exprés internacional y entregas puerta a puerta DDU/DDP.

### ¿Dónde puedo rastrear yo mismo un paquete de SXW Express?

En el recuadro *Express Tracking* de la página principal de `sxwexpress.cn`: escribe el código y pulsa *Track*. Se abrirá el buscador oficial de SXW en chino, que dispone también de página en inglés; el icono de 17TRACK contiguo lleva a una web externa independiente.

### ¿Qué significa «Customs declaration completed» en SXW Express?

Que ha concluido la declaración aduanera de exportación en China antes del vuelo. Peek lo clasifica como «En tránsito»: es la aduana de salida en China, no la de tu país, y el paquete no está retenido.

### ¿Qué empresa reparte los paquetes de SXW Express en mi país?

La web de SXW no publica una lista por países. Hacia Estados Unidos el registro puede incluir un número de GOFO Express al que Peek cambia en cuanto GOFO escanea el envío (y hacia Suiza, Swiss Post cuando el canal la nombra); en el resto de destinos, pide al vendedor el nombre del transportista final y su código.

### ¿A quién reclamo si no llega mi paquete de SXW Express?

Al vendedor al que hiciste el pedido, que fue quien contrató el envío con SXW (`sxwexpress.cn` no ofrece teléfono ni correo). Si la tienda no responde, abre una disputa en el marketplace.

:::sources
- [SXW Express: página principal](http://www.sxwexpress.cn/) – razón social, pestaña Express Tracking con botón Track, enlace a 17TRACK y servicio DDU/DDP
- [SXW Express: About US](http://www.sxwexpress.cn/about.html) – sede en Shenzhen, transporte marítimo, aéreo, ferroviario y exprés, DDU/DDP y reparto local de última milla
- [SXW Express: Products](http://www.sxwexpress.cn/products/default.html) – líneas UPS desde Hong Kong, ARAMEX Special Line y paquetería a Europa/Oriente Medio y EE. UU.
- [SXW Express: Contact US](http://www.sxwexpress.cn/contact.html) – página de contacto sin teléfono, correo ni dirección y panel Online Support vacío
- [SXW Express: 会员登录 (acceso de miembros)](http://www.sxwexpress.cn:8082/) – nombre en chino de la empresa en el sistema de seguimiento
- [SXW Express: trace (buscador en inglés)](http://www.sxwexpress.cn:8082/en/trackIndex.htm) – versión en inglés del rastreador de SXW
- [Comisión Europea: Reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € desde el 1 de julio de 2026 y tasa de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: Orientaciones sobre el arancel de 3 € y la tasa (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – cobro por artículo
- [Comisión Europea: Ámbito territorial del IVA y aduanas](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Canarias, Ceuta y Melilla
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato SXW, traducción al inglés de pasos chinos, husos horarios, etapas escritas por adelantado y traspaso a GOFO y Swiss Post
:::
