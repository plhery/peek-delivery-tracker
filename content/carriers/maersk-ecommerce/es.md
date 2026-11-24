---
title: Seguimiento Maersk eCommerce: códigos 1M, estados y ayuda
description: Sigue un paquete de Maersk eCommerce (B2C Europe / InterPost): código 1M de 19 caracteres, códigos 70000–77200, relevos locales y soporte Maersk.
slug: seguimiento-maersk-ecommerce
published: 2026-11-24
updated: 2026-11-24
---

**Maersk eCommerce** (*Maersk Logistics & Services USA Inc.*, que integra las antiguas plataformas de paquetería *B2C Europe* e *InterPost*) es la red nacional e internacional de entrega de paquetes B2C de A.P. Moller–Maersk ([maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)). Con más de 80 millones de paquetes al año y más de 75 transportistas integrados en Norteamérica y Europa, Maersk eCommerce clasifica los pedidos de comercios electrónicos en sus propias instalaciones, despacha aduanas B2C en rutas internacionales y entrega el reparto final a transportistas locales y correos como [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) y [DPD Austria](carrier:dpd-at). Puedes pegar tu código `1M` de 19 caracteres en el cuadro de arriba para seguir tanto los escaneos en centros de Maersk como los hitos retransmitidos del transportista local.

## Números de seguimiento de Maersk eCommerce (formato `1M`)

Maersk eCommerce emite números alfanuméricos de 19 caracteres que empiezan por `1M`, seguidos de un código de cliente/comercio de 7 caracteres (con al menos una letra) y 10 dígitos:

| Formato (inventado) | Qué es |
| --- | --- |
| `1MAB4829C7360514928` | Código de paquete Maersk eCommerce de 19 caracteres (`1M` + código de cliente de 7 caracteres + 10 dígitos). |
| `1MKX7391E4829173605` | Otro número de paquete `1M` de 19 caracteres usado en rutas de Norteamérica o Europa. |

Busca el número `1M` en el correo de envío de tu tienda o en el enlace de seguimiento (`maersk.com/tracking/parcel/...`). El localizador de Maersk puede indicar el transportista de última milla y el número asignado por este. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Maersk eCommerce

Los escaneos propios de Maersk llevan códigos del `70000` al `72004`, y los del transportista de última milla a partir de `77100`:

| Código y texto de Maersk | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `70000` `Shipment Manifest Received`, `70001` `Maersk Shipping Label Created` | `70000`: el manifiesto del vendedor incluye el paquete antes de que Maersk lo tenga. `70001`: etiqueta propia de Maersk, generada al entrar el paquete en su red, a menudo minutos antes de `70002`. | «Etiqueta creada» |
| `70002` `Parcel Received by Maersk` | Paquete físico recibido y escaneado en un centro de recepción de Maersk eCommerce. | «Admitido por el transportista» |
| `72001` `Arrived at Maersk Facility`, `72002` `Processing at Maersk Facility`, `72004` `Departed from Maersk Facility` | Clasificado en un hub de consolidación de Maersk y despachado hacia la región de destino o pasarela. | «En tránsito» |
| `77100` `In-Transit Scan`, `77101` `Enroute to Facility`, `77102` `Arrival at Facility`, `77103` `Processing at Facility`, `77105` `Departed from Facility`, `77106` `Parcel In-transit` | Eventos de tránsito nacional retransmitidos por el transportista local de última milla (como OnTrac, Amazon Logistics o DPD). | «En tránsito» |
| `77203` `Out for Delivery` | Cargado en el vehículo del repartidor local para su entrega hoy. | «En reparto» |
| `77200` `Parcel Successfully Delivered` | Entregado en la dirección del destinatario por el socio de última milla. | «Entregado» |

Un escaneo sin ninguno de estos códigos muestra «Incidencia» cuando Maersk lo señala como excepción y, de lo contrario, toma su fase del paso bajo el que aparece en el localizador de Maersk.

## Cobertura, devoluciones y aduanas transfronterizas

Maersk E-Commerce ofrece cobertura completa en 32 países de Norteamérica y Europa, con puntos de inyección en origen en China y el Sudeste Asiático ([Maersk E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics)):

- **Instalaciones y socios:** en Europa, 8 centros de clasificación y cross-docking, hubs de inyección en Londres, Taulov y Ámsterdam, y más de 40 socios de última milla y operadores postales con entrega a domicilio y puntos de recogida. En Norteamérica, centros de clasificación y pasarelas en JFK, LAX y ORD; Standard tarda 2–7 días en EE. UU. y 1–10 días en Canadá, Expedited 2–5 y 1–5 días.
- **Despacho aduanero internacional:** Maersk despacha paquetes B2C hacia Reino Unido, la UE y Estados Unidos. El pago de aranceles e IVA de importación a la llegada depende de si el vendedor los cobró al finalizar la compra. Consulta [paquetes retenidos en aduanas](guide:customs).
- **Devoluciones e-commerce:** devoluciones nacionales e internacionales mediante entrega en punto o recogida a domicilio para vendedores adheridos, sin papel y exentas de aranceles en algunas rutas.

## Paquete de Maersk eCommerce retrasado, sin novedades o perdido

Como Maersk eCommerce es un consolidador B2B contratado por marcas de comercio electrónico, **contacta siempre primero con la tienda en línea** para cambios de dirección, artículos faltantes, sustituciones o reembolsos.

- **Silencio durante el transporte troncal o relevo:** el seguimiento suele detenerse unos días entre `72004` (`Departed from Maersk Facility`) y el primer escaneo `77100+` del transportista local mientras camiones o vuelos transfieren los paquetes al mensajero local. Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Usa el número del transportista final:** en cuanto Maersk indica el transportista y su código, pasan a regir su propio localizador y soporte. Peek muestra «Entrega con» para [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) y [DPD Austria](carrier:dpd-at).
- **Marcado como entregado o dañado:** revisa entradas, cuartos de paquetería y vecinos, consulta el seguimiento del socio local y avisa enseguida al comercio. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Soporte de Maersk eCommerce y portales oficiales

La página de soporte de Maersk ofrece chat en directo y gestión de incidencias, principalmente para clientes empresariales; los vendedores reservan mediante el acceso empresarial MyParcel de Maersk.

- **Información Maersk E-Commerce y consultas comerciales:** [maersk.com/supply-chain-logistics/e-commerce-logistics](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics).
- **Soporte general y preguntas frecuentes de Maersk:** [maersk.com/support](https://www.maersk.com/support) y [maersk.com](https://www.maersk.com/).
- **Vía de ayuda para destinatarios:** contacta con la tienda donde compraste el artículo (o con el transportista local una vez haya escaneado el paquete).

> Desconfía de SMS falsos que piden pulsar un enlace desconocido y pagar una pequeña tasa para liberar un «paquete Maersk». Comprueba siempre cualquier aviso en la página de tu pedido o en el sitio oficial del transportista local. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Seguir un paquete de Maersk eCommerce con Peek

[Peek](/) consulta automáticamente a Maersk eCommerce como único candidato cuando introduces un código `1M` de 19 caracteres (`1MAB4829C7360514928` o `1MKX7391E4829173605`, inventados) y muestra «Maersk eCommerce tiene este paquete.» en cuanto la API de paquetería de Maersk lo confirma; Peek reconoce además directamente enlaces pegados `maersk.com/tracking/parcel/`. Si alguna vez necesitas seleccionarlo manualmente, toca «Cambiar» al añadirlo o «Cambiar transportista» en la pantalla del paquete (buscar «B2C Europe», «InterPost», «Maersk Parcel» o «Maersk E-Delivery» también lo encuentra).

Peek lee el localizador de paquetería de Maersk y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En reparto», «Incidencia», «Entregado»), el texto de los escaneos de Maersk y del transportista local, la localidad y región, el día estimado de entrega cuando Maersk lo indica, el país de destino y la hora de entrega. Los códigos numéricos y el nombre del servicio no aparecen en ninguna aplicación de Peek. Maersk incluye desfase UTC en sus propios escaneos y en la mayoría de socios; un escaneo de socio sin desfase (como los de DPD) se fecha en la zona horaria de su lugar (Viena para un escaneo en Graz) o en la del escaneo contiguo, y Peek los muestra todos en la hora de tu dispositivo. Si el localizador repite dos veces un escaneo de socio, Peek conserva una sola copia. Cuando Maersk menciona a [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac) o [DPD Austria](carrier:dpd-at) con un número que encaja en su formato, Peek comprueba que tiene el paquete: la ficha muestra entonces «Entrega con» seguido del nombre del transportista e incluye ambos números. Peek nunca lee el número de pedido del cliente, la localidad y código postal de destino ni el enlace del socio.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta hasta cada 10 minutos; el día en que Maersk muestra `77203` (`Out for Delivery`), cada 2 minutos desde las 6:00 en la hora del último escaneo mientras el localizador de Maersk responda. El resto del tiempo Peek comprueba cada hora. Tras 12 horas con solo «Etiqueta creada» o «Admitido por el transportista», o dos días sin novedades, consulta cada hora lo sigas como lo sigas, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el localizador de Maersk no responde y Peek sigue el paquete únicamente a través de servicios de seguimiento, consulta cada hora, cada 3 horas tras un día tranquilo y hasta cada 15 minutos el día en que el paquete está en reparto, mientras tengas el paquete abierto o las notificaciones activadas.

Cuando el escaneo más reciente tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con el día de ese escaneo, como «Sin novedades desde el lun 12 oct». La página del paquete muestra entonces «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Maersk eCommerce.» Tras tres semanas sin nuevos escaneos, indica «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Maersk eCommerce.»

Puedes elegir ajustes de alertas para «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa las actualizaciones de seguimiento y no puede despachar aduanas, desviar paquetes ni contactar con Maersk eCommerce o sus socios de última milla por ti.

## Preguntas sobre el seguimiento de Maersk eCommerce

### ¿Qué es Maersk eCommerce (B2C Europe / InterPost)?

Maersk eCommerce es la red de consolidación y entrega de paquetes B2C de A.P. Moller–Maersk en Norteamérica y Europa (que integra las antiguas redes B2C Europe e InterPost) y mueve más de 80 millones de paquetes al año con más de 75 transportistas socios.

### ¿Qué aspecto tiene un número de seguimiento de Maersk eCommerce?

Los códigos de Maersk eCommerce tienen 19 caracteres: `1M`, seguido de un código alfanumérico de cliente de 7 caracteres (con al menos una letra) y 10 dígitos — por ejemplo `1MAB4829C7360514928` (inventado).

### ¿Quién entrega mi paquete de Maersk eCommerce en mi domicilio?

Maersk consolida y transporta el paquete entre hubs y cede la entrega final a un socio regional en tu país, como [Amazon Logistics](carrier:amazon-logistics), [OnTrac](carrier:ontrac), [DPD Austria](carrier:dpd-at) o correos locales.

### ¿Por qué permanece días mi paquete de Maersk eCommerce en `70000 Shipment Manifest Received`?

`70000` significa que el manifiesto del vendedor registra el paquete antes de que Maersk lo tenga, por lo que nada avanza hasta que el vendedor lo entregue: pregúntale si sigue ahí. Maersk añade su propia etiqueta `70001` al recibirlo en su red, a menudo minutos antes de `70002` (`Parcel Received by Maersk`).

:::sources
- [Maersk: servicios de entrega de paquetería E-Commerce](https://www.maersk.com/supply-chain-logistics/e-commerce-logistics) – 80M+ paquetes anuales, 75+ transportistas integrados, redes nacionales, internacionales y devoluciones en Norteamérica y Europa
- [Maersk: sitio oficial](https://www.maersk.com/) – portal logístico global y acceso al seguimiento de paquetes
- [Maersk: soporte y FAQ](https://www.maersk.com/support) – portal oficial de soporte Maersk
- [Universal Parcel Scraper: maersk-ecommerce](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/maersk-ecommerce) – formato `1M` de 19 caracteres, regla de enlace `maersk.com/tracking/parcel/`, códigos 70000–77200, fecha estimada y país, y reglas de relevo Amazon/OnTrac/DPD-AT
:::
