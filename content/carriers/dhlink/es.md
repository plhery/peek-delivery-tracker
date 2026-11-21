---
title: Seguimiento DHLink: número de DHgate, estados y reparto
description: Sigue tu paquete de DHLink de un pedido en DHgate: qué números acepta su buscador, qué significan los estados en inglés, quién entrega y a quién reclamar.
slug: dhlink-seguimiento
published: 2026-11-16
updated: 2026-11-20
---

DHLink (驼飞侠) es la plataforma logística transfronteriza de DHgate (敦煌网). Fundada en 2014, envía pedidos de vendedores de DHgate y de otros clientes desde China o desde sus almacenes en el extranjero y los entrega a un transportista del país de destino para el último tramo. Consulta tu código en la [página de seguimiento de DHLink](https://www.dhlink.com/trackQuery?lang=en) o pégalo en el recuadro de arriba. Encontrarás el número en los detalles de tu pedido de DHgate; si no aparece, pídeselo al vendedor.

## Número de seguimiento de DHLink: cómo es

DHLink no publica un formato fijo de numeración. Su buscador admite hasta 40 códigos a la vez y acepta lo que llama *Global Tracking No.*, *Logistics Delivery No.* y *DHLink pickup No.* Estas son las formas vistas en envíos de DHLink, con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `FC9999999999980` | `FC` y 13 cifras: el número interno de DHLink («DHLink No.»). En algunas líneas sirve también como número de seguimiento. |
| `XZG999999980WL` | `XZG`, 9 cifras y `WL`: número de un socio de línea que DHLink reconoce como número de seguimiento. |
| `YLUS9999999980` | `YLUS` y 10 cifras: una línea hacia Estados Unidos repartida por [GOFO Express](carrier:gofo). |
| `QH999999999980FS` | `QH`, 12 cifras y `FS`: otra línea hacia Estados Unidos repartida por GOFO. |
| `EYT9999999980HZ` | `EYT`, 10 cifras y `HZ`: una línea hacia Estados Unidos repartida por SwiftX. |
| `CYS99999999980` | `CYS` y 11 cifras: una línea hacia Estados Unidos repartida por UniUni. |
| `JXCYI9999999990YQ` | `JXC`, `YI`, `ZO`, `US` o `ZI`, 10 cifras y `YQ`: un formato de albarán de Juxi Express al que el buscador de DHLink también responde en algunos envíos hacia Estados Unidos. Peek pregunta primero a DHLink por esta forma; los demás números `JXC` los asigna a Juxi Express. |
| `RR482910576CN` | Dos letras, 9 cifras y `CN`: un número de [China Post](carrier:china-post), usado cuando el paquete viaja por correo postal. |
| `GFUS99999999999980` | No es de DHLink: es un número de [GOFO Express](carrier:gofo) para el último tramo de algunas líneas estadounidenses. |

En cada resultado, DHLink muestra el «DHLink No.», el «Tracking No.» y, cuando otra empresa hace la entrega final, el «Last Leg No.» de ese transportista. Si el número de seguimiento no coincide con el número DHLink, busca por el número de seguimiento: consultado solo por el código `FC`, un envío con otro número de seguimiento puede no aparecer. Sin `lang=en` en la dirección, la página se abre en chino.

## Estados del seguimiento de DHLink explicados

La página agrupa los envíos bajo `In transit`, `Delivered` o `Not found`, indica los códigos de país de origen y destino y la hora de cada lectura, y añade `Signed for` cuando consta la entrega. Como las líneas proceden de varias fuentes, un mismo paso suele salir repetido en la web de DHLink. Estos son sus mensajes habituales en inglés, en el orden del recorrido:

| DHLink dice | Qué significa para ti |
| --- | --- |
| `Data Received; Data transferred and received by carrier` | En una línea hacia el sureste de Europa: Pactic, la plataforma en Hungría por la que pasa esa ruta, ha recibido los datos del envío. Antes de la lectura en el almacén de DHLink, Peek muestra «Etiqueta creada»; si la línea vuelve a salir tras el vuelo, el paquete sigue «En tránsito». |
| `Arrive at the collection warehouse` | El paquete ha llegado al almacén de recogida de DHLink en China. |
| `The collection warehouse has completed sorting` | Ya se ha clasificado en ese almacén. |
| `Package received & scanned at warehouse` | Registrado y escaneado en el almacén de DHLink. |
| `Parcel handed over to carrier,waiting for transportation` | Entregado en el punto de recogida, a la espera de transporte. |
| `Package departed from the warehouse`, `The package has sent from the warehouse` | Ha salido del almacén. |
| `Warehouse scanning bagging`, `Master airway bill number received`, `Leave the warehouse` | En algunas líneas: ensacado en el almacén en China para el vuelo, recepción del conocimiento aéreo principal (*master air waybill*) y salida del almacén («En tránsito» en Peek). |
| `Customs export clearance completed`, `Package handed over to airline` | La aduana china de exportación lo ha despachado y la aerolínea se ha hecho cargo de él. |
| `Flight departure`, `The flight has taken off` | El avión ha despegado de China. |
| `Shipment Departed From Original Country at …`, `Departed from airport of origin` | El mismo paso: el vuelo ha salido de China («En tránsito» en Peek). |
| `The flight has arrived and the board is being dismantled`, `Dismantling the board` | El vuelo ha aterrizado y están desmontando los palés aéreos en el aeropuerto de llegada («En tránsito» en Peek). |
| `Shipment has arriveved at destination Country at …` | Ha llegado al país de destino (la errata en *arriveved* es de DHLink). |
| `Arrived to … hub`, `Linehaul transit; Parcel has entered the linehaul network.` | Tras el vuelo, paso por el centro europeo de un socio (identificado con un código donde van los puntos suspensivos, como `HU` en Hungría para Pactic) y transporte hacia la siguiente etapa («En tránsito» en Peek). |
| `Customs clearance completed`, `Customs import clearance completed, awaiting pickup by local carrier` | Despacho de importación terminado; el transportista local recogerá el paquete a continuación. |
| `UPS/USPS/Fedex` | Se acerca la entrega al transportista estadounidense. |
| `货物已交UPS/USPS/Fedex,The goods have been delivered to UPS/USPS/Fedex` | Entregado a [UPS](carrier:ups), [USPS](carrier:usps) o [FedEx](carrier:fedex) en Estados Unidos. Aún no te lo han entregado a ti. |
| `Package picked up by last-mile carrier` | El transportista de última milla de tu país ya ha recogido el envío. |
| `Scheduled for Delivery` | En reparto. |
| `Shipment has delivered at …` | Entregado. |

En las líneas hacia Estados Unidos aparecen además lecturas como `Arrived at Origin Hub`, `Departed Destination Hub` o `Shipping Order Created.` («Etiqueta creada» en Peek, alrededor de una hora antes de entrar en el almacén). Una instalación del transportista final puede anotar `Parcel received by ABC01.` (con su propio código en lugar de `ABC01`) y, minutos después, `Parcel departed from Facility.`, ambos «En tránsito». Después llegan las lecturas de quien reparte, como `Arrived at GOFO Delivery Station` en GOFO, `No Access to Delivery Location` en USPS (intento fallido) o, en UniUni, `Parcel received at UniUni sorting hub.`, `Parcel processed.` y `Parcel scanned and in transit.` En las líneas de [China Post](carrier:china-post) salen también avisos solo en chino: `中国邮政 已收取邮件` (admitido por China Post), `送交出口海关` (presentado a la aduana de exportación) y `已妥投` tras el código del país (entregado). Cuando DHLink no reconoce el número, la página responde `There is no track for this waybill number`.

## Quién reparte los paquetes de DHLink

La web de DHLink está pensada para vendedores y no publica qué empresa reparte en cada país: quien toma el relevo lo indica el propio seguimiento. En las líneas estadounidenses, un escaneo puede anunciar el paso a «UPS/USPS/Fedex» sin especificar cuál antes de que aparezcan los eventos de ese transportista, mientras que otras rutas terminan en [GOFO Express](carrier:gofo) (cuyo localizador también encuentra los paquetes de DHgate que reparte usando el código `FC` de 13 cifras) o en SwiftX; en Suiza, el «Last Leg No.» puede ser de DPD Suiza. Los envíos cursados con [China Post](carrier:china-post) conservan su número acabado en `CN` hasta la entrega: mira en la página de China Post qué operador postal los reparte en España.

En cuanto sepas qué transportista local lleva el paquete y cuál es su número, su propio seguimiento te dará el detalle del reparto, los avisos de llegada o el punto de recogida: consulta también [entrega fallida](guide:missed-delivery).

**Aduanas y cargos en España y la UE.** Según la Agencia Tributaria (AEAT), no hay derechos de aduana si la mercancía ya está en España o en otro país de la UE cuando haces el pedido; en cambio, en las compras por internet enviadas desde fuera de la UE por un importe de hasta 150 €, desde el 1 de julio de 2026 desapareció la franquicia arancelaria y se aplica, además del IVA, un arancel de 3 € por categoría de artículos. A ello se suma desde el 1 de noviembre de 2026 una tasa de tramitación europea de 2 € por categoría de artículos, contada del mismo modo en las ventas a distancia importadas cualquiera que sea su valor y exigible al aceptarse la declaración aduanera. Si la plataforma no los cobró al comprar, el transportista puede pedirte el arancel y el IVA para entregarte el paquete, así como posibles gastos de gestión. Más detalles en [paquetes retenidos en aduanas](guide:customs).

## Paquete de DHLink sin actualizar, no encontrado o entregado pero no recibido

**No encontrado.** Revisa el código en tu pedido de DHgate y prueba con el número de seguimiento (*Tracking No.*) en lugar del número `FC` de DHLink. Un código del transportista final no devuelve nada en la web de DHLink: búscalo directamente en ese transportista. Si has elegido DHLink con «Cambiar» para un número que DHLink no conoce, Peek muestra «Esperando a DHLink» y sigue comprobándolo. Si dos días después DHLink sigue sin conocer el número y ninguna otra fuente responde por él, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a consultarlo una vez al día, y una vez por semana cuando ha pasado una semana desde que lo añadiste.

**Parado tras el vuelo o la aduana.** Comprueba si ha aparecido un «Last Leg No.» y consúltalo en el transportista local, cuyas lecturas suelen ir por delante de las de DHLink. Más información en [por qué el seguimiento no se actualiza](guide:tracking-not-updating) y [seguir un paquete desde China](guide:tracking-from-china).

**Entregado pero no está, con retraso o perdido.** Las reglas de reembolso e indemnización de DHLink están dirigidas a los vendedores: es la tienda quien reclama a DHLink, mientras que tú gestionas el problema con el vendedor o con DHgate. Esas reglas excluyen las incidencias causadas por el comprador, como una entrega tardía por no recoger o firmar el envío o un despacho aduanero fallido por no colaborar a tiempo. Responde pronto a cualquier solicitud de la aduana o del transportista y avisa cuanto antes al vendedor: solo dispone de 90 días naturales desde la creación de la orden logística para presentar su reclamación. Mira [entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

## Contacto y atención al cliente de DHLink

Consultada el 16 de noviembre de 2026, la página de contacto de DHLink muestra una única dirección de correo de atención al cliente, `dhlinkservice@dhlink.com`, un acceso de consulta en la plataforma y el asistente de chat «AI 小飞». La página está en chino y no incluye ningún teléfono. Para dudas sobre tu compra, escribe primero al vendedor desde DHgate; si el problema está en el reparto final, acude al transportista que entrega en tu país.

## Seguir un paquete de DHLink con Peek

[Peek](/) no puede confirmar DHLink solo por la forma del código: las series `FC`, `XZG`, `YLUS`, `QH`, `EYT`, `CYS`, `JXCYI`, `JXCZO`, `JXCUS` y `JXCZI` son coincidencias débiles. Pégalo igualmente: Peek sigue más de 4000 transportistas, muestra «Comprobando servicios de seguimiento…» y, cuando DHLink reconoce el envío, indica «DHLink tiene este paquete.»; si no, pulsa «Cambiar» para elegir DHLink. Un código `JXCYI`, `JXCZO`, `JXCUS` o `JXCZI` también puede pertenecer a Juxi Express: Peek consulta primero a DHLink y, mientras ninguno responda, muestra el envío como «Probablemente DHLink»; si el vendedor envió con Juxi, cámbialo a Juxi Express con «Cambiar». Un número de China Post (dos letras, 9 cifras y `CN`) pasa directamente a [China Post](carrier:china-post).

Peek lee el seguimiento de DHLink, muestra el estado, el país de destino y la localidad de cada evento, y lista cada escaneo una sola vez (tal como lo escribe DHLink, casi siempre en inglés), descartando duplicados entre fuentes además de códigos postales y referencias de pedido. Las lecturas en China se interpretan en hora de Pekín, las de [USPS](carrier:usps) en la hora del estado estadounidense correspondiente y las de Pactic, en la ruta vía Hungría, en hora de Budapest, mostrándose en la zona horaria de tu dispositivo (en peektracker.com y en las apps para iPhone y Android, una línea al pie del historial lo recuerda: «Horas en tu zona horaria: …»); las demás conservan la hora que da DHLink. DHLink no facilita peso, nombre del servicio ni hora de entrega, y la única estimación que muestra Peek procede de un escaneo reenviado de USPS del tipo `Out for Delivery, Expected Delivery …` cuando sigue siendo el más reciente e incluye una franja horaria para ese mismo día. Cuando el «Last Leg No.» es un código de [GOFO Express](carrier:gofo) (`GFUS` y 14 cifras), de SwiftX (`SWX` y 18 cifras) o de DPD Suiza (14 cifras que empiezan por `060` o `061`), Peek muestra ese transportista con su número, lo consulta y cambia a él en cuanto ofrece avances más recientes; el resto de números de última milla permanecen en el historial de DHLink.

Mientras responda el propio seguimiento de DHLink, Peek comprueba hasta una vez cada 10 minutos, o hasta una vez cada 2 minutos el día en que el paquete sale a reparto (desde las 6:00, hora local, o desde media hora antes si hay una franja anunciada); a través de servicios de seguimiento, cada hora, o hasta una vez cada 15 minutos el día del reparto. Una vez que sigue el envío en GOFO, consulta como mucho cada 30 minutos, también en reparto. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), Peek pasa a comprobar una vez por hora. Puedes activar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek no puede contactar por ti con DHLink, con DHgate ni con el vendedor.

## Preguntas frecuentes sobre el seguimiento de DHLink

### ¿Qué es DHLink?

Es la plataforma logística transfronteriza de DHgate, operada en Pekín por 驼飞侠科技（北京）有限公司. Creada en 2014, envía pedidos desde China y desde almacenes en el extranjero mediante líneas propias y transportistas colaboradores.

### ¿Quién entrega mi paquete de DHLink?

Un transportista local en tu país. En las rutas hacia Estados Unidos, DHLink pasa los envíos a UPS, USPS o FedEx, o bien a GOFO, SwiftX o UniUni; en Suiza, a veces a DPD Suiza; y en los envíos postales acabados en `CN`, al correo nacional que recibe la línea de China Post. Fíjate en el campo «Last Leg No.» del resultado de DHLink.

### ¿Qué significa «The goods have been delivered to UPS/USPS/Fedex»?

Que DHLink ha entregado el paquete al transportista estadounidense encargado del tramo siguiente. Todavía no ha llegado a tu domicilio: a continuación irán saliendo las lecturas de ese transportista.

:::sources
- [DHLink: Sobre nosotros (关于我们)](https://www.dhlink.com/about/introduce) – fundada en 2014, plataforma logística transfronteriza de DHgate, almacenes en el extranjero y entrega en el país de destino
- [DHLink: Página de inicio (首页)](https://www.dhlink.com/) – envío directo desde China y almacenes en el extranjero
- [DHLink: Seguimiento (轨迹查询)](https://www.dhlink.com/trackQuery?lang=en) – búsqueda de hasta 40 códigos, tipos de número admitidos, campos DHLink No., Tracking No., Last Leg No., Signed for, aviso cuando no hay seguimiento e idioma por defecto
- [DHLink: Contacto (联系我们)](https://www.dhlink.com/about/connect) – correo dhlinkservice@dhlink.com, consulta en la plataforma, asistente AI 小飞, sin teléfono
- [DHLink: Acuerdo de servicio (服务协议)](https://www.dhlink.com/agreementPage/service) – operador 驼飞侠科技（北京）有限公司
- [DHgate Seller Help: Reglas de indemnización de DHLink (DHLink物流异常件赔付规则)](https://seller.dhgate.com/help/c7502/516002.html) – indemnización para vendedores, exclusiones por causas del comprador y plazo de 90 días naturales tras crear la orden logística
- [Agencia Tributaria: Envíos por un valor de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – ausencia de arancel cuando la mercancía ya está en España o en la UE, arancel de 3 € por categoría desde el 1 de julio de 2026, tasa de tramitación de 2 € por categoría desde el 1 de noviembre de 2026 y cobro por el transportista si no iba incluido
- [Comisión Europea: EU Customs Reform (en inglés)](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – aplicación desde el 1 de noviembre de 2026 de la tasa de tramitación de la UE de 2 €
- [Comisión Europea: Customs Guidance on EUR 3 customs duty, PDF (en inglés)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – tasa de 2 € por categoría de artículos igual que los 3 €, cualquiera que sea el valor, devengada al aceptarse la declaración aduanera
- [Universal Parcel Scraper: DHLink (en inglés)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhlink) – formas de número, textos de los escaneos (Pactic, UniUni, China Post, USPS), zonas horarias, cambio a GOFO, SwiftX y DPD Suiza y qué muestra Peek
- [Universal Parcel Scraper: GOFO Express (en inglés)](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo) – admisión de los códigos FC de DHLink en el seguimiento de GOFO e intervalo de consulta de 30 minutos
:::
