---
title: Seguimiento Chunghwa Post: número TW, estados y aduanas
description: Rastrea un paquete de Chunghwa Post desde o hacia Taiwán: formato del código terminado en TW, estados en chino, entrega con Correos y aduanas.
slug: chunghwa-post-tracking
published: 2026-11-21
updated: 2026-11-21
---

Chunghwa Post (中華郵政, Chunghwa Post Co., Ltd.) es el operador postal nacional de Taiwán, una empresa pública propiedad del Ministerio de Transporte y Comunicaciones que también presta servicios de ahorro postal y seguros de vida. Puedes consultar un envío en su portal [Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201), en inglés o en chino, o pegar el número directamente en el buscador de arriba. El código figura en el resguardo de admisión del remitente: si esperas una compra enviada desde Taiwán, pídeselo al vendedor o a la tienda. El localizador oficial pide un código gráfico (Captcha) y solo muestra envíos depositados en los últimos seis meses.

## Número de seguimiento de Chunghwa Post: formato y ejemplos

Un envío internacional procedente de Taiwán lleva un código de 13 caracteres: dos letras, nueve dígitos (el último es un dígito de control) y `TW`. Las notas del buscador de Chunghwa Post explican qué indica la primera letra. Con dígitos inventados:

| Ejemplo (inventado) | Qué tipo de envío es |
| --- | --- |
| `EE482910576TW` | `E`: EMS, servicio exprés internacional (o `EE`/`EZ` y `FT`/`FZ`, como `FT482910576TW`, hacia China continental). |
| `RR482910576TW` | `R`: carta certificada internacional. |
| `LX482910576TW` | `L`: ePacket internacional (pequeño paquete con seguimiento de hasta 2 kg, entregado en buzón sin firma). |
| `CP482910576TW` | `C`: paquete postal internacional. |
| `48291057364918` | 14 dígitos (o 20): envío nacional dentro de Taiwán, consultable en la pestaña doméstica de Chunghwa Post. |

Chunghwa Post advierte de que los pequeños paquetes ordinarios cuyo código empieza por `U` (como `UH482910576TW`) no disponen de seguimiento en línea. Si aún no tienes la referencia, revisa [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Estados de Chunghwa Post: qué significan los mensajes en chino

Estos son los escaneos en chino tradicional de Chunghwa Post que Peek reconoce y clasifica a lo largo del recorrido:

| Chunghwa Post muestra | Qué significa para tu envío |
| --- | --- |
| `交寄郵件` | Depositado en la ventanilla de una oficina postal («Admitido por el transportista»). |
| `運輸途中` | En camino entre centros de clasificación de Chunghwa Post («En tránsito»). |
| `離開進口局` | Ha salido de una oficina postal durante el encaminamiento. |
| `到達出口互換局` | Ha llegado a la oficina de cambio de exportación que lo despacha al extranjero. |
| `已出口－資料詳如右方` | Exportado: el envío ya ha salido de Taiwán. |
| `郵袋接收` | Se ha recibido la saca postal en la que viaja. |
| `到達寄達國互換局` | Ha llegado a la oficina de cambio del país de destino. |
| `海關查驗` | En revisión aduanera («En aduanas»). |
| `離開寄達國互換局` | Ha salido de la oficina de cambio del país de destino. |
| `到達寄達國投遞局` | Ha llegado a la unidad de reparto del país de destino. |
| `安排投遞中` | En la oficina de reparto, pendiente de salir en ruta. |
| `郵件投遞中` | En reparto con el cartero («En reparto»). |
| `投遞不成功` | Entrega fallida («Intento de entrega»). El motivo concreto o una eventual devolución aparecen en una columna de observaciones que Peek omite: consúltala en el portal de Chunghwa Post. |
| `投遞成功` | Entregado («Entregado»). |

`安排投遞中` y `郵件投遞中` corresponden al reparto propio de Chunghwa Post en envíos que entran en Taiwán. En los paquetes enviados desde Taiwán hacia España o Latinoamérica, los últimos pasos provienen del correo de destino.

Si el buscador devuelve `查無此號碼資料！！` (sin datos para ese número), comprueba los 13 caracteres y que no hayan pasado más de seis meses desde el envío.

## Envíos de Chunghwa Post a España y Latinoamérica: quién entrega y aduanas

Chunghwa Post saca el paquete de Taiwán y, a la llegada, el operador postal del país de destino asume el reparto con el mismo código terminado en `TW`. En su tabla de enlaces a correos extranjeros, Chunghwa Post remite a:

- **España (incluida la mención a Ceuta y Melilla)**: [Correos](carrier:correos-spain) (`correos.es`) tanto para EMS como para paquetes internacionales y cartas certificadas.
- **México y Chile**: Correos de México y CorreosChile para EMS, paquetes y cartas.
- **Argentina**: la tabla de Chunghwa Post no ofrece enlace directo (aunque el correo designado es Correo Argentino).

Cuando veas `已出口－資料詳如右方`, introduce ese mismo código `TW` en [Correos](carrier:correos-spain) o en el operador postal de tu país. España, México, Chile y Argentina no figuran en la lista de trece países que según Chunghwa Post dejaron de ofrecer seguimiento web para cartas certificadas internacionales (lista en la que sí están Estados Unidos, Canadá, Brasil, Francia, Italia, Países Bajos o Reino Unido). Los avisos de llegada y plazos de recogida en oficina dependen del correo local; consulta [entrega fallida](guide:missed-delivery).

**Aduanas en España.** Taiwán está fuera de la Unión Europea, por lo que las compras con mercancías pasan despacho de importación. En **España peninsular y Baleares** se aplica el IVA de importación desde el primer euro (salvo que la tienda ya lo haya cobrado mediante ventanilla IOSS). Además, desde el 1 de julio de 2026 los pedidos comprados en línea fuera de la UE de hasta 150 € pagan un arancel aduanero de la UE de 3 € por categoría de artículo, y desde el 1 de noviembre de 2026 se suma una tasa de gestión de la Unión de 2 € por artículo cualquiera que sea el valor del pedido, aparte de la tarifa de tramitación aduanera del operador. Canarias forma parte del territorio aduanero de la UE pero queda fuera del ámbito del IVA comunitario (aplica IGIC), mientras que Ceuta y Melilla quedan fuera tanto del territorio aduanero como del área de IVA de la UE; en Latinoamérica cada país aplica sus propios tributos de importación. Más información en [paquetes retenidos en aduanas](guide:customs).

## Enviar un paquete a Taiwán: reparto y normas aduaneras

El correo de tu país transporta el envío hasta Taiwán y Chunghwa Post realiza la entrega allí. Chunghwa Post señala que todo paquete importado pasa inspección de la aduana taiwanesa, que decide si corresponde liquidar impuestos. La Aduana de Taipéi (Taipei Customs) establece tres tramos para paquetes postales:

- **Hasta 2.000 NT$** (valor CIF: mercancía, seguro y flete): libre de aranceles e impuestos.
- **Entre 2.000 NT$ y 5.000 US$**: la aduana suele encomendar a la oficina postal el cobro en el momento de la entrega junto con el recibo *Duty Memo for Foreign Imports by Parcel Post*.
- **Más de 5.000 US$** (valor FOB): el destinatario debe tramitar el despacho y abonar los tributos directamente en la oficina de aduanas.

La Aduana de Taipéi advierte también de mercancías prohibidas o restringidas en compras transfronterizas, entre ellas productos sanitarios (como preparados con ácido hialurónico o aparatos estéticos), semillas, productos cárnicos, tabaco calentado, cigarrillos electrónicos y falsificaciones.

## Seguimiento de Chunghwa Post sin cambios, retrasos o extravíos

**Sin novedades tras `已出口－資料詳如右方`.** El envío ya salió de Taiwán y viaja hacia el país de destino o espera control aduanero. Consulta el mismo número en [Correos](carrier:correos-spain) (o tu correo nacional) y revisa [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Paquete retrasado o perdido.** La reclamación escrita debe presentarla el remitente en la oficina postal de Taiwán donde depositó el envío, aportando el resguardo original: dispone de **4 meses** desde el día siguiente a la admisión para EMS y de **6 meses** para paquetes y correo certificado. Pasado ese plazo, Chunghwa Post puede rechazar la búsqueda y no abona indemnización. Pide al vendedor que la tramite a tiempo y consulta [paquete perdido](guide:lost-parcel).

**Consta como entregado pero no lo tienes.** En España o Latinoamérica la entrega final la efectúa tu correo nacional: revisa su justificante de entrega y consulta [entregado pero no recibido](guide:delivered-not-received).

## Atención al cliente y teléfonos de Chunghwa Post

Los horarios corresponden a la hora oficial de Taiwán (`UTC+8`, sin cambio estacional):

- **Línea de atención 24 horas**: `0800-700-365` (gratuita desde fijo en Taiwán; pulsa `3` para atención en inglés). Desde móvil o desde el extranjero hay que marcar el número de pago `(04) 2354-2030`, es decir, `+886 4 2354 2030` quitando el `0` inicial del prefijo.
- **Envíos que entran en Taiwán**: `(03) 3180-700`, extensión `2431` (EMS) y `2432` (paquetes) de lunes a viernes no festivos de 8:00 a 12:00 y de 13:30 a 16:30; extensión `2430` (certificados por avión) de 8:00 a 12:00 y de 13:00 a 16:00.
- **Paquetes aéreos que salen de Taiwán**: `(03) 383-3776`, extensión `271` o `272`, de lunes a viernes no festivos de 8:00 a 16:30.
- **En línea**: formulario *Opinion Box* en [Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601), con respuesta en un plazo de 7 días (indica el código del envío y nombre y dirección tanto del remitente como del destinatario, y confirma el enlace enviado a tu correo).

## Seguir un envío de Chunghwa Post con Peek

[Peek](/) reconoce al instante como Chunghwa Post cualquier código de dos letras, nueve dígitos y `TW` cuya primera letra sea `C`, `E`, `L` o `R` (o cuyo prefijo sea `FT` o `FZ`) siempre que el dígito de control sea válido; si falla, revisa el recibo. También admite enlaces `CSController?MAILNO=`. Un número `UH…TW` se clasifica por su forma bajo correo internacional (puedes elegir Chunghwa Post manualmente con «Cambiar» o «Cambiar transportista»), mientras que otros códigos `U…TW` y los números domésticos de 14 o 20 dígitos son rechazados por el adaptador de Chunghwa Post («no emite números como este»).

Peek consulta el localizador internacional de Chunghwa Post y muestra cada escaneo en chino tal como lo publica el operador, junto con la oficina (convertida al nombre de su ciudad cuando Peek puede ubicarla). Los eventos registrados en oficinas taiwanesas se interpretan en `Asia/Taipei` y se pasan a tu huso («Horas en tu zona horaria: {{zone}}.»); los escaneos hechos en el extranjero conservan la hora local del correo correspondiente. Chunghwa Post no facilita fecha estimada de entrega ni peso, y Peek descarta la nota lateral de cada evento.

Para un código acabado en `TW`, Peek consulta además al correo nacional del país desde el que miras el envío —por ejemplo [Correos](carrier:correos-spain) desde España o Correo Argentino desde Argentina— y pasa a seguirlo allí en cuanto publica eventos nuevos, conservando el historial de Taiwán. A la inversa, cuando tu correo indica Taiwán como destino, Peek consulta a Chunghwa Post con tu mismo número postal salvo que empiece por `U`.

Mientras tienes el envío abierto en pantalla, o el día de entrega con las notificaciones activadas, Peek comprueba el seguimiento directo hasta cada 10 minutos, y hasta cada 2 minutos cuando pasa a «En reparto» (ese día desde las 6:00 hora local mientras responda directamente el portal de Chunghwa Post); el resto del tiempo, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Cuando consulta mediante servicios de seguimiento, comprueba hasta cada 15 minutos ese día en «En reparto» bajo las mismas condiciones, y si no cada hora, o cada 3 horas tras un día sin cambios. Una vez que el envío pasa al correo de destino, Peek aplica el ritmo de ese operador. Puedes elegir alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede presentar reclamaciones ante Chunghwa Post ni ante tu correo por ti.

## Preguntas frecuentes sobre el seguimiento de Chunghwa Post

### ¿Chunghwa Post es lo mismo que Taiwan Post?

Sí. Chunghwa Post (中華郵政) es el servicio postal oficial de Taiwán y Peek lo reconoce por ambos nombres. Sus códigos internacionales terminan en `TW`.

### ¿Dónde sigo un paquete de Chunghwa Post en España?

En el buscador de Chunghwa Post hasta que aparezca `已出口－資料詳如右方` y después con el mismo código `TW` en [Correos](carrier:correos-spain), al que Chunghwa Post enlaza para EMS, paquetes y cartas registradas hacia España. Si consultas desde España, Peek comprueba Correos automáticamente.

### ¿Qué significa el estado `海關查驗`?

Que el envío está en inspección aduanera («En aduanas»). En [paquetes retenidos en aduanas](guide:customs) puedes ver cómo funcionan el IVA, el arancel de 3 € (pedidos hasta 150 €) y la tasa de gestión de 2 € en España.

### ¿Cuánto tiempo conserva Chunghwa Post el seguimiento?

Su portal web solo muestra envíos de los últimos seis meses. Para abrir una búsqueda escrita en Taiwán con el recibo original, el remitente tiene 4 meses en EMS y 6 meses en paquetes y certificados desde el día siguiente al depósito.

:::sources
- [Chunghwa Post: Company Profile](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=1637282383451) – empresa postal estatal, correo, ahorro y seguros de vida
- [Chunghwa Post: Tracking Service](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=351402) – acceso al seguimiento de EMS, cartas y paquetes
- [Chunghwa Post: Track & Trace Service](https://postserv.post.gov.tw/pstmail/main_mail.html?targetTxn=EB500201) – Captcha, seis meses de consulta, prefijos E/R/L/C/U, plazos de reclamación, enlaces de correos de destino y códigos domésticos de 14/20 cifras
- [Chunghwa Post: 國際e小包 (ePacket)](https://www.post.gov.tw/post/internet/Postal/index.jsp?ID=1499060698896) – límite de 2 kg, entrega sin firma ni reclamación escrita
- [Chunghwa Post: 國際郵件查詢電話](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160101) – teléfonos, extensiones y horarios de correo internacional
- [Chunghwa Post: English home page](https://www.post.gov.tw/post/internet/U_english2/) – línea 0800-700-365, teléfono desde móvil y prefijo +886
- [Chunghwa Post: 客服專線語音操作說明](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=160103) – atención 24 horas y opción 3 para inglés
- [Chunghwa Post: Contact Us](https://www.post.gov.tw/post/internet/U_english2/index.jsp?ID=35090601) – formulario Opinion Box, respuesta en 7 días y datos necesarios
- [Chunghwa Post: 國際包裹 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020307&defaultAllOpen=1&sn=24FA12A9-11A6-4365-A8F5-C154C379AF02) – revisión aduanera obligatoria de los paquetes importados en Taiwán
- [Chunghwa Post: 郵件查詢的期限 FAQ](https://www.post.gov.tw/post/internet/Customer_service/index.jsp?ID=16020315&defaultAllOpen=1&sn=44431DCF-5197-43D5-95B6-5EFE13F5D67B) – exclusión de búsqueda e indemnización fuera de plazo
- [Taipei Customs: International Parcel](https://web.customs.gov.tw/etaipei/singlehtml/1342) – umbrales de 2.000 NT$ y 5.000 US$, Duty Memo
- [Taipei Customs: Cross-border online purchases](https://web.customs.gov.tw/etaipei/singlehtml/1373?cntId=1a74ff69fdd0483085a6a8c9eda54b07) – artículos restringidos o prohibidos
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – arancel de 3 € por artículo en pedidos extracomunitarios de hasta 150 €
- [Comisión Europea: Customs Guidance on EUR 3 customs duty](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – aplicación del arancel de 3 € desde el 1 de julio de 2026
- [Comisión Europea: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tasa de gestión de 2 € por artículo desde el 1 de noviembre de 2026
- [Comisión Europea: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – régimen aduanero y de IVA de Canarias, Ceuta y Melilla
- [Universal Parcel Scraper: Chunghwa Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chunghwa-post) – detección de números TW, estados en chino y relevo postal
:::
