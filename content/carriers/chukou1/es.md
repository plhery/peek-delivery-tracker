---
title: Seguimiento Chukou1: códigos EC-FirstClass, traspaso y ayuda
description: Sigue un paquete internacional de Chukou1 (CK1 / 出口易) en EC-FirstClass: estructura del código, estados de seguimiento, entrega local y soporte.
slug: seguimiento-chukou1
published: 2026-11-24
updated: 2026-11-24
---

Chukou1 (*CK1*, en chino *出口易*, operado por *Guangzhou Beifayi Information Technology Co., Ltd.* / *BFE*) es un gran operador chino de logística de comercio electrónico transfronterizo y almacenes en el extranjero con sede en Cantón (Guangzhou), sucursales en Shenzhen, Shanghái y Wuhan y centros de procesamiento en Dongguan y Yiwu. Opera almacenes de fulfillment en Estados Unidos, Reino Unido, Alemania, Francia, Polonia, Canadá, Australia, Perú y Argentina junto con líneas dedicadas de pequeños paquetes (superlíneas Europa/EE. UU., líneas económicas globales, Latinoamérica y TEMU-Y2) para vendedores en Amazon, eBay, AliExpress, SHEIN, TikTok Shop, Walmart y Wayfair, y ofrece seguimiento en su plataforma **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/), hasta 100 números a la vez). Puedes consultar un envío allí o pegar tu código en el recuadro de arriba.

## Números de seguimiento de Chukou1 (formato EC-FirstClass)

Busca el número de procesamiento de Chukou1 en tu pedido del marketplace o en el correo de envío. Los números propios de Chukou1 codifican un prefijo de servicio de 3 letras, una fecha de pedido `AAMMDD` de 6 dígitos, un código de comerciante de 3 o 4 caracteres y un número de serie de 6 o 7 dígitos (entre 18 y 20 caracteres en total):

| Formato (inventado) | Qué es |
| --- | --- |
| `CZN261015ABCD482917` | Número de procesamiento Chukou1 de 19 caracteres (`CZN` + fecha `261015` + código cliente 4 car. `ABCD` + serie 6 dígitos). |
| `CUE261015KLM4829173` | Número de procesamiento Chukou1 de 19 caracteres (`CUE` + fecha `261015` + código cliente 3 car. `KLM` + serie 7 dígitos). |

Tras el despacho de exportación, Chukou1 entrega el paquete a un transportista para el resto del trayecto (EC-FirstClass cita China Post, [USPS](carrier:usps), [DHL](carrier:dhl), [FedEx](carrier:fedex) y [UPS](carrier:ups) entre sus socios) y muestra tanto el primer tramo de Chukou1 como los escaneos reenviados por ese transportista bajo tu número de procesamiento. Consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Chukou1 (EC-FirstClass)

Chukou1 combina un estado global (`InProcess`, `InTransit`, `Delivered`) con hitos en inglés del primer tramo y escaneos reenviados del transportista de destino en la hora local del lugar de cada escaneo:

| Chukou1 indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Shipment information received`, `Carrier received the manifest` | Pedido electrónico recibido del vendedor antes de la recepción física en el centro de Chukou1. | «Etiqueta creada» |
| `Item received by Logistics Centre`, `Carrier received the shipment` | Paquete físico escaneado en el almacén de origen de Chukou1 en China. | «Admitido por el transportista» |
| `Item was sent out from Logistics Centre`, `Transfer to Airport of Origin`, `Departed from Airport of Origin`, `Arrived at Airport of destination`, `Shipment started the customs clearance process at origin` | Trasladado del centro al aeropuerto de origen, exportado y llegado al país de destino. | «En tránsito» |
| `Customs clearance in progress`, `Shipment started the customs clearance process at destination`, `Shipment is awaiting customs clearance at destination` | En inspección aduanera de importación en el país de destino. | «En aduanas» |
| `Customs declaration completed`, `Turned over to last mile facility`, `Last mile Ascan`, `Arrived at Post Office` | Despachado de aduanas, transferido al transportista local de última milla y escaneado en su red. | «En tránsito» |
| `Shipment is out for delivery` / `Out for Delivery` | Cargado para el reparto final por el transportista de destino. | «En reparto» |
| `Shipment is available for pickup` | Listo para recoger en una oficina postal o punto de recogida local. | «Listo para recoger» |
| `Wrong Address`, `Refused`, `Cannot be delivered` | Intento de entrega fallido por dirección errónea, rechazo o imposibilidad de entrega. | «Intento de entrega» |
| `Return To Sender`, `Delivered To Sender` | De vuelta hacia el remitente o ya entregado al remitente. | «Devuelto al remitente» |
| `Lost` | Incidencia marcada como extraviado en tránsito; contacta enseguida con tu vendedor. | «Incidencia» |
| `Shipment delivered to customer` / `Delivered` | Entregado por el socio de destino (con nombre de quien firmó cuando se registra). | «Entregado» |

## Envío internacional, traspasos locales y aduanas

En [chukou1.com](https://www.chukou1.com/) y [ec.chukou1.com](https://ec.chukou1.com/), Chukou1 opera como consolidador transfronterizo B2B y proveedor de almacenes en el extranjero, no como una flota de reparto al consumidor final.

- **El socio local controla la entrega final:** una vez aparecen `Turned over to last mile facility` y `Last mile Ascan`, los intentos de entrega, redirecciones, depósitos seguros y plazos de recogida dependen del correo o mensajero local que asuma el paquete en tu país.
- **Aduanas e impuestos de importación:** los envíos internacionales hacia la UE, EE. UU., Reino Unido, Canadá o Australia pasan por aduana en destino (`Customs clearance in progress`), con IVA de importación y derechos aplicables (incluidos el derecho de aduana UE de 3 € hasta 150 € y la tasa de gestión comunitaria de 2 € en pedidos de fuera de la UE) salvo que el vendedor los liquidara al comprar. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete de Chukou1 detenido, sin novedades o extraviado

Como las tiendas online son titulares de la cuenta de envío con Chukou1, contacta siempre primero con el vendedor o marketplace para reembolsos, reenvíos o cambios de dirección.

- **Pausas entre China y el destino:** el tramo aéreo internacional y los lotes aduaneros suelen dejar varios días sin novedades entre `Departed from Airport of Origin`, `Arrived at Airport of destination` y `Last mile Ascan`. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Sigue con el código local tras el traspaso:** una vez que EC-FirstClass vincula el código del socio de destino, consultarlo directamente suele mostrar franjas horarias u opciones de nueva entrega.
- **Entregado, dañado o perdido:** comprueba primero con tu familia, vecinos y transportista local, y abre una reclamación al vendedor antes de que caduque la protección al comprador. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contacto de Chukou1

Contactos oficiales publicados en [Contacto de Chukou1](https://www.chukou1.com/About/Contact.aspx) y [chukou1.com](https://www.chukou1.com/):

- **Línea nacional en China:** `4006 908 223`.
- **Línea de reclamaciones (lun.–vie.):** `13360562736`.
- **Oficinas:** sede de Cantón `020-23380666` (Units 2403–04, Tower A1, Hangxin Centre, No. 840 Huangpu Avenue East, Huangpu District, Cantón), Shenzhen `0755-28263408`, Shanghái `021-59793099`, Wuhan `027-59083347`.

> Desconfía de SMS falsos que pidan un pequeño pago online para «liberar» un paquete internacional en aduanas. Verifica cualquier cargo legítimo primero en la página de tu pedido o en la web oficial de tu correo nacional. Consulta [estafas por SMS de envíos](guide:delivery-scam-texts).

## Seguir un paquete de Chukou1 con Peek

[Peek](/) consulta automáticamente a Chukou1 como único candidato cuando introduces un número de procesamiento Chukou1 (`CZN261015ABCD482917` o `CUE261015KLM4829173`, inventados) y muestra «Chukou1 tiene este paquete.» cuando EC-FirstClass lo confirma; Peek también reconoce enlaces pegados de `ec.chukou1.com/track?track_number=` y `chukou1.com/LogistictsTrack.aspx?trackNo=` para números de Chukou1. Si alguna vez necesitas seleccionarlo manualmente, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «出口易» o «CK1» también lo encuentra).

Peek lee directamente el rastreador EC-FirstClass de Chukou1 y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «En reparto», «Listo para recoger», «Intento de entrega», «Incidencia», «Devuelto al remitente», «Entregado»), los escaneos de Chukou1 y los reenviados del transportista local sin números de teléfono ni nombre del repartidor, su lugar y el país de destino. El código postal de destino y el nombre de quien firmó se mantienen privados y ninguna app de Peek los muestra. EC-FirstClass ofrece las horas sin zona horaria: Peek fecha cada escaneo en la zona horaria de su lugar y lo muestra en la hora de tu dispositivo. Cuando EC-FirstClass indica el transportista de entrega y su número y Peek lo reconoce, comprueba que ese transportista tiene el paquete: el envío muestra entonces «Entrega con» seguido del nombre del transportista y lista ambos números. Peek omite el PDF de comprobante de entrega; EC-FirstClass no proporciona fecha estimada de entrega, peso, nombre del servicio ni hora de entrega propia.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos; el día en que Chukou1 muestra un escaneo en reparto, cada 2 minutos desde las 6:00 según la hora local del último escaneo, mientras responda EC-FirstClass. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, pasa a comprobar cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando EC-FirstClass no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día de reparto mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo señala con el día de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Chukou1.», con un enlace «Pide ayuda a Chukou1» hacia la página de contacto de Chukou1. Tras tres semanas sin ningún escaneo nuevo, dice «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Chukou1.»

Puedes elegir avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, abrir PDFs incrustados de comprobante de entrega ni contactar con Chukou1 o tu socio local por ti.

## Preguntas frecuentes sobre el seguimiento de Chukou1

### ¿Qué es Chukou1 (CK1 / EC-FirstClass)?

Chukou1 (*出口易*, operado por *Guangzhou Beifayi Information Technology Co., Ltd.*) es un operador chino de logística de comercio electrónico internacional y almacenes en el extranjero cuyo portal de seguimiento se llama **EC-FirstClass** ([ec.chukou1.com](https://ec.chukou1.com/)).

### ¿Qué aspecto tiene un número de seguimiento de Chukou1?

Los números de procesamiento de Chukou1 llevan 3 letras de prefijo, una fecha `AAMMDD` de 6 dígitos, un código de vendedor de 3 o 4 caracteres y una serie de 6 o 7 dígitos (por ejemplo `CZN261015ABCD482917`, inventado).

### ¿Qué significa `Last mile Ascan` en el seguimiento de Chukou1?

`Last mile Ascan` significa que el correo o mensajero local de tu país ha realizado su primer escaneo de admisión tras recibir el paquete del tramo internacional de Chukou1.

### ¿Cómo contacto con atención al cliente de Chukou1?

Contacta primero con tu tienda online para reembolsos o cambios de dirección. La línea oficial de Chukou1 en China es `4006 908 223` (reclamaciones lun.–vie. `13360562736`, sede de Cantón `020-23380666`) en [chukou1.com/About/Contact.aspx](https://www.chukou1.com/About/Contact.aspx).

:::sources
- [Chukou1: sitio web oficial](https://www.chukou1.com/) – líneas internacionales de pequeños paquetes, almacenes en el extranjero (US, UK, DE, FR, PL, CA, AU, PE, AR), primera milla FBA y sede de Cantón (*广州市贝法易信息科技有限公司*)
- [Chukou1: contacto](https://www.chukou1.com/About/Contact.aspx) – `4006 908 223`, reclamaciones `13360562736`, teléfonos y direcciones en Cantón/Shenzhen/Shanghái/Wuhan (URL de ayuda enlazada por Peek)
- [EC-FirstClass (plataforma de seguimiento de Chukou1)](https://ec.chukou1.com/) – rastreador global de 100 números y socios citados (China Post, USPS, DHL, FedEx, UPS)
- [Universal Parcel Scraper: chukou1](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/chukou1) – formato de número de procesamiento, reglas de enlaces `ec.chukou1.com`/`chukou1.com`, lectura de escaneos iniciales y de socios, extracción de firmante/código postal/traspaso, PDF POD descartado y URL de ayuda
:::
