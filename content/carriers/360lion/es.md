---
title: Seguimiento 360Lion: códigos WSH y AR, estados y ayuda
description: Seguimiento 360Lion Express (纬狮物流) desde China a Latinoamérica y EE. UU.: códigos WSH…YQ y AR, estados WSO/WSD, entrega local y correo customerservice@360lion.com.
slug: seguimiento-360lion
published: 2026-11-24
updated: 2026-11-27
---

Para rastrear un paquete de 360Lion (*360Lion Express*, *纬狮物流*, fundado en Shenzhen en 2015), pega tu código `WSH…YQ` (por ejemplo `WSH` + país de 2 letras + 10 dígitos + `YQ`) o `AR` + 10 dígitos (Argentina) en el cuadro superior o en [360lion.com/en/](https://www.360lion.com/en/) (hasta 50 guías a la vez). 360Lion opera vuelos dedicados desde China a 15 países de Latinoamérica y EE. UU. para vendedores en Mercado Libre, Amazon, AliExpress, Shopee, SHEIN y Temu, y traspasa la entrega final a un socio local (`handover to lastmile`); su correo de consultas de paquetes es `customerservice@360lion.com`.

## Números de seguimiento de 360Lion: formatos WSH y AR

Busca el número de seguimiento en tu pantalla de pedido del marketplace o en el correo de confirmación de envío. Los paquetes de 360Lion suelen aparecer con dos formatos:

| Formato (inventado) | Qué es |
| --- | --- |
| `WSHMX4829173640YQ` | Guía de línea dedicada de 360Lion: `WSH`, código de país de destino de dos letras (por ejemplo `MX` para México), 10 dígitos y `YQ`. |
| `AR4829173640` | `AR` seguido de 10 dígitos (utilizado en paquetes con destino a Argentina). Comparte formato con [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) y [Aramex New Zealand](carrier:aramex-nz). |

Si tu pedido hacia Brasil o México lleva el código de barras de otro transportista — como `AJ` de [Anjun Logistics](carrier:anjun), `8880` de [J&T Express Brazil](carrier:j-and-t-br) o `JMX` de [J&T Express Mexico](carrier:j-and-t-mx) — consulta [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de 360Lion

360Lion agrupa los envíos en estados generales (`Processing`, `Pre-alert`, `In transit.`, `Returned`, `Delivered`) y añade códigos de escaneo (`WSO*` en origen en China, `WSD*` en destino). Cuando el paquete pasa al transportista local de última milla en el país de destino, el seguimiento puede mostrar los textos propios de ese transportista.

| 360Lion muestra (código / grupo) | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `Processing` (`PRECESSING`) | El vendedor creó la orden de envío con 360Lion antes de que empiecen los escaneos troncales. | «Etiqueta creada» |
| Paso `HUB` | Admitido en el centro de clasificación de origen de 360Lion en China. | «Admitido por el transportista» |
| `departed from airport` (`WSO04`), `arrived at airport` (`WSD00`), `In transit.` (`IN_TRANSIT`) | Salió en avión desde China (con código de aeropuerto como PVG) y aterrizó en el aeropuerto de destino. | «En tránsito» |
| Paso `CUSTOMS` | En trámite aduanero de exportación o de importación en destino. | «En aduanas» |
| `handover to lastmile` (`WSD02`), `Arrived at sorting center` (`WSD051`), `package on the way to delivery office` (`WSD050`) | Entregado al socio local de última milla del país de destino, clasificado y despachado hacia la oficina local de reparto. | «En tránsito» |
| `Returned` (`RETURNED_BACK`) | Devuelto hacia el remitente o almacén de origen. | «Devuelto al remitente» |
| `Delivered` (`DELIVERED`) | Entregado al destinatario por el socio local de última milla. | «Entregado» |

Ten en cuenta que el estado `Pre-alert` (`WARNING` / *预警*) de 360Lion puede indicar tanto un preaviso como una alerta, por lo que Peek deja que las líneas de escaneo decidan la fase en lugar de tratar `Pre-alert` por sí solo como un fallo.

## Cómo funciona la entrega transfronteriza de 360Lion

En [360lion.com/en/](https://www.360lion.com/en/) y [360lion.com](https://www.360lion.com/), 360Lion se describe como transportista troncal transfronterizo y agente general de ventas y servicios aéreos (GSSA) con vuelos chárter regulares desde China hacia Latinoamérica, respaldado por almacenes en México, Chile, Brasil, Colombia y Estados Unidos.

**Entrega de última milla.** Tras el aeropuerto de destino (`WSD00`) y la aduana de importación, 360Lion entrega el paquete (`WSD02`) a un transportista local en tu país, que realiza el reparto final.

**Normas para destinatarios y aduanas.** Como 360Lion atiende a remitentes empresariales y no a compradores particulares, su web no publica límites de intentos de entrega, plazos de custodia en puntos de recogida ni garantías de tránsito para paquetes pequeños (su [página de FAQ y condiciones](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) cubre normas comerciales para vendedores como carga aérea SEND, artículos prohibidos y un plazo de reclamación para vendedores de 30 días). El IVA y los aranceles siguen las normas aduaneras de tu país. Consulta [paquetes retenidos en aduanas](guide:customs).

## Paquete 360Lion parado, sin novedades o no entregado

Como 360Lion contrata con la tienda online o marketplace y no con el comprador, contacta siempre primero con el vendedor o la plataforma ante pedidos retrasados, errores de dirección o reembolsos.

**Sin cambios entre China y el destino.** Los vuelos de larga distancia de China a Latinoamérica y las colas aduaneras suelen dejar huecos de varios días entre `departed from airport` (`WSO04`) y `arrived at airport` (`WSD00`). Consulta [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Entrega local sin segundo código.** Peek sigue los avisos del transportista local mediante el rastreador de 360Lion bajo tu código `WSH` o `AR` y no muestra un segundo número local. Si falla el reparto y necesitas el contacto o la referencia del socio local, pregúntaselo al vendedor o escribe al área de consultas de paquetes de 360Lion.

**Entregado, dañado o perdido.** Consulta primero con tu familia, vecinos y conserjería si el paquete marca `Delivered`; si falta o llega dañado, documenta todo y abre una disputa con el vendedor o marketplace antes de que venza la protección al comprador. Consulta [entregado pero no recibido](guide:delivered-not-received), [paquete dañado](guide:damaged-parcel), [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos de 360Lion

Contactos publicados en la [página de contacto de 360Lion](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) y en [360lion.com/en/](https://www.360lion.com/en/):

- **Correo de consulta de paquetes (*查件业务*):** `customerservice@360lion.com`.
- **Correo general / comercial:** `info@360lion.com`.
- **Teléfono sede Shenzhen:** `+86 755 2360 1325` (Shanghái `+86 21 6142 1360`, Cantón `+86 20 8653 4133`).
- **Dirección de la sede:** 7/F, Block A, Star River World, No. 1 Yabao Road, Bantian Street, Longgang District, Shenzhen, Guangdong, China.

> Desconfía de falsos SMS o mensajes de WhatsApp que pidan abrir un enlace de pago urgente para «liberar» un paquete internacional en aduanas. Comprueba primero cualquier cobro arancelario legítimo en la página de pedido de tu marketplace o en el portal oficial postal o aduanero de tu país. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de 360Lion con Peek

[Peek](/) consulta automáticamente a 360Lion al introducir un código `WSH...YQ` (como `WSHMX4829173640YQ`, dígitos inventados) — donde 360Lion es el único candidato — o un código `AR` + 10 dígitos (como `AR4829173640`), en el que Peek consulta a 360Lion junto con [Colis Privé](carrier:colis-prive), [Aramex Australia](carrier:aramex-au) y [Aramex New Zealand](carrier:aramex-nz) y muestra «360Lion tiene este paquete.» cuando 360Lion responde. Pegar un enlace `toms.360lion.com/track?trackNumber=` selecciona 360Lion directamente; si no, pulsa «Cambiar» al añadirlo o «Cambiar transportista» en la ficha del paquete y elige 360Lion (buscar «360Lion Express», «WSH» o «纬狮物流» también lo encuentra).

Peek lee directamente el seguimiento de 360Lion y muestra la fase («Etiqueta creada», «Admitido por el transportista», «En tránsito», «En aduanas», «Devuelto al remitente» o «Entregado»), el texto de cada escaneo (el del transportista de última milla cuando 360Lion lo transmite, o el inglés de 360Lion), el lugar como nombre de país o código de aeropuerto de tres letras, el país de destino y la hora de entrega. Cada escaneo incluye el huso horario de su ubicación, como `+8` en China y `-3` en Argentina, y se muestra en la hora de tu dispositivo. Peek descarta la barra de progreso de la web, el país de origen, el contador de días en tránsito y el comprobante de entrega; el rastreador de 360Lion no da fecha prevista, nombre de servicio ni peso.

Mientras tienes el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek comprueba hasta cada 10 minutos mientras el seguimiento de 360Lion responda. En el resto de casos comprueba cada hora. Tras 12 horas solo en «Etiqueta creada» o «Admitido por el transportista», o tras dos días sin novedades, comprueba cada hora sea cual sea tu forma de seguirlo, y cada 6 horas tras siete días en «Etiqueta creada». Cuando el seguimiento de 360Lion no responde y Peek sigue el paquete únicamente mediante servicios de seguimiento, comprueba cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de reparto mientras lo tengas abierto o tengas las notificaciones activadas.

Cuando el último escaneo tiene cuatro días y el paquete sigue en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek lo marca con la fecha de ese escaneo, por ejemplo «Sin novedades desde el lun 12 oct». La ficha muestra entonces: «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a 360Lion.», con un enlace «Pide ayuda a 360Lion» a su página de contacto. Tras tres semanas sin ningún escaneo nuevo, dice: «Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a 360Lion.»

Puedes elegir los avisos «Cada escaneo», «Pasos importantes» o «Solo la entrega». Peek solo supervisa el seguimiento y no puede despachar aduanas ni contactar con 360Lion o tu repartidor local por ti.

## Preguntas sobre el seguimiento de 360Lion

### ¿Qué es 360Lion Express (纬狮物流)?

360Lion es una operadora china de logística transfronteriza fundada en 2015 en Shenzhen, con centros en Shanghái, Cantón, Dongguan, Yiwu y Hong Kong y almacenes en México, Chile, Brasil, Colombia y EE. UU., que mueve paquetería aérea para vendedores de Mercado Libre, Amazon, AliExpress, Shopee, SHEIN y Temu.

### ¿Qué significan los códigos `WSH…YQ` y `AR` de 360Lion?

`WSH` + dos letras de país + 10 dígitos + `YQ` (por ejemplo `WSHMX4829173640YQ`, dígitos inventados) es la guía dedicada internacional de 360Lion, y `AR` + 10 dígitos se usa para envíos a Argentina. Peek reconoce ambos y acepta enlaces `toms.360lion.com/track?trackNumber=`.

### ¿Qué significan `Pre-alert` y `handover to lastmile` (`WSD02`)?

`Pre-alert` (`WARNING`) puede ser solo un preaviso, no necesariamente un error (Peek se guía por las líneas de escaneo). `handover to lastmile` (`WSD02`) indica que 360Lion ya entregó el paquete al transportista local en tu país tras el aterrizaje (`WSD00`) y la aduana.

### ¿Quién reparte localmente los paquetes de 360Lion?

Un servicio postal o mensajería local del país de destino, cuyos eventos suelen aparecer dentro del mismo código en 360Lion sin mostrar un segundo número local. Si necesitas el dato del repartidor local tras un intento fallido, pídeselo al vendedor o a `customerservice@360lion.com`.

### ¿Cómo contactar con 360Lion por un paquete atrasado o perdido?

Reclama primero al vendedor o marketplace para mantener la protección al comprador (las reclamaciones ante 360Lion las presenta el vendedor en 30 días). Para consultas de paquetes escribe a `customerservice@360lion.com` o llama a Shenzhen (`+86 755 2360 1325`).

:::sources
- [360Lion Express: English Home](https://www.360lion.com/en/) – fundada en 2015, oficinas en Shenzhen/Shanghái/Cantón/Yiwu/Dongguan/Hong Kong, rastreador de 50 números, socios marketplace y dirección de la sede
- [360Lion: Chinese Home](https://www.360lion.com/) – rutas dedicadas de paquetería pequeña a 15 países latinoamericanos y EE. UU., vuelos chárter y almacenes en destino
- [360Lion: Contact Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=79) – `customerservice@360lion.com`, `info@360lion.com`, teléfonos y direcciones en Shenzhen/Cantón/Shanghái (URL de ayuda enlazada por Peek)
- [360Lion: About Us](https://www.360lion.com/?m=home&c=Lists&a=index&tid=78) – descripción de la empresa
- [360Lion: Dedicated Small Parcels](https://www.360lion.com/?m=home&c=Lists&a=index&tid=90) – servicio B2C transfronterizo de paquetería pequeña
- [360Lion: FAQ & Terms](https://www.360lion.com/?m=home&c=Lists&a=index&tid=82) – condiciones para vendedores, artículos prohibidos y reclamación de vendedores en 30 días
- [360Lion: Tracking Portal](https://toms.360lion.com/track) – portal público de seguimiento
- [Universal Parcel Scraper: 360lion](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/360lion) – detección de formatos `WSH` y `AR`, reglas de enlace `toms.360lion.com`, códigos `WSO`/`WSD`, husos horarios explícitos, campos descartados y enlace de ayuda
:::
