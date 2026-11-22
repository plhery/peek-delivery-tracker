---
title: Seguimiento Parcelforce Worldwide: estados y aduanas
description: Rastrea un paquete de Parcelforce Worldwide: dónde está el número, qué significa cada estado, opciones de entrega, aduanas y contacto con Royal Mail.
slug: seguimiento-parcelforce-worldwide
published: 2026-11-22
updated: 2026-11-22
---

Parcelforce Worldwide forma parte de [Royal Mail](carrier:royal-mail) Group, por lo que puedes seguir un paquete de Parcelforce en la página Track your item de Royal Mail o pegando el número en la casilla de arriba. El número lo facilita la tienda o el remitente, normalmente en el correo de confirmación del pedido.

## Número de seguimiento Parcelforce: dónde encontrarlo

Búscalo en el correo de confirmación de la tienda o pídeselo al vendedor. ¿Lo perdiste? Consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number). Tres formatos posibles:

| Número (inventado) | Qué es |
| --- | --- |
| `EA482917367GB` | Dos letras (`E` y otra letra, o `CP` o `GI`), nueve dígitos y `GB`, con dígito de control antes de `GB`. |
| `PBTR4829173654` | Número de paquete de 14 caracteres: `PB`, el número de envío y la posición del bulto dentro del envío. |
| `TR4829173` | Un número de envío aislado: el rastreador de Royal Mail pide en su lugar el número propio de cada bulto. |

El formato de 13 caracteres también lo usa Royal Mail (`RR482917367GB` es de Royal Mail, no de Parcelforce), y algunos paquetes express48 llevan número de Royal Mail. Como ambos se rastrean en la misma página, apenas cambia nada para ti.

## Estados de seguimiento de Parcelforce explicados

El rastreador de Royal Mail muestra estas líneas en inglés. En el orden habitual:

| El seguimiento dice | Qué significa para ti |
| --- | --- |
| `Sender has despatched item` | El remitente registró el paquete. Parcelforce aún no lo tiene. |
| `Item Collected` | Recogido al remitente. |
| `Accepted at Parcelshop` | El remitente lo dejó en una oficina de correos o tienda de paquetería. |
| `Item received` | Ya está en la red. |
| `Customs charges raised` | Una importación desde fuera del Reino Unido debe abonar IVA, aranceles o tasas antes de entregarse. |
| `Despatched to delivery office` | En camino hacia tu depósito u oficina de reparto local. |
| `Received at Delivery Depot` | En tu depósito local. Aún no está listo para recoger. |
| `Out for delivery` | Con el repartidor hoy. |
| `Delivery attempted` | El repartidor no pudo entregarlo. |
| `Delivered by` | Entregado. La línea puede nombrar la oficina que lo entregó. |
| `Delivered to Neighbour/Reception by` | Dejado con un vecino o en recepción, indicado en la línea. |
| `Returned to sender` | De vuelta al remitente. |

En paquetes enviados al extranjero, el historial continúa con líneas como `Item Leaving the UK` y `Due to be delivered today` del socio encargado de la entrega.

## Opciones de entrega de Parcelforce y entregas fallidas

**Antes de la entrega.** En la app de Royal Mail o a través de Royal Mail Customer Services puedes elegir otro día de entrega para un paquete de Parcelforce, pedir que lo entreguen a un vecino, o solicitar que el repartidor llame más fuerte o te dé más tiempo para abrir. También puedes fijar un lugar seguro con Royal Mail para envíos de Parcelforce.

**Entrega fallida.** El seguimiento mostrará `Delivery attempted`, o `Delivered to Neighbour/Reception by` con el nombre de quien lo recogió. Si sigues sin localizar el paquete, llama a Royal Mail Customer Services (abajo).

## Seguimiento de Parcelforce sin actualizar, retrasado o perdido

**Sin escaneos todavía.** Puede que el remitente creara el número antes de entregar el paquete: `Sender has despatched item` significa que Parcelforce aún no lo tiene. Revisa el número e inténtalo más tarde. Más en [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

**Retrasado, perdido o dañado.** Si compraste por internet, contacta primero con el vendedor: Citizens Advice señala que suele dar mejor resultado y puedes pedir reembolso o reenvío. Para solicitar indemnización, consulta cómo reclamar en la web de Parcelforce.

**Gastos de aduana.** Parcelforce despacha los envíos procedentes de fuera del Reino Unido y te contacta si corresponde pagar IVA, aranceles u otras tasas, con una factura que indica exactamente cuánto abonar ([más sobre aduanas](guide:customs)). Normalmente retiene el paquete unas 3 semanas; si no se paga en ese plazo, regresa al remitente. Las consultas sobre la mercancía también van a Parcelforce.

> ¿Dudas de si un correo o SMS que te pide pagar por un paquete es real? No pulses sus enlaces ni facilites datos bancarios. Reenvía correos fraudulentos a report@phishing.gov.uk y mensajes SMS sospechosos al 7726 en el Reino Unido, gratis.

## Teléfono y atención al cliente de Parcelforce

Citizens Advice remite a los clientes de Parcelforce a Royal Mail para necesidades de entrega y lugares seguros:

- **Royal Mail Customer Services:** 03457 740 740, de lunes a viernes de 8:00 a 18:30, sábados de 8:00 a 15:00 y domingos de 9:00 a 14:00 (hora del Reino Unido). **Relay UK:** 18001 y después 0345 266 0075.
- **App de Royal Mail:** fecha de entrega, vecino, lugar seguro y preferencias de entrega también para paquetes de Parcelforce.

Para reclamaciones o quejas, entra en la propia web de Parcelforce.

## Seguir paquetes de Parcelforce con Peek

[Peek](/) identifica a Parcelforce al instante en números como `EA482917367GB` con dígito de control válido y en números `PB` de 14 caracteres. Un número de envío aislado no se rastrea. Un enlace de Parcelforce con `trackNumber=` funciona, igual que un enlace de Royal Mail acabado en `#/tracking-results/` más un número de Parcelforce.

Peek consulta Parcelforce en la web de Royal Mail abriéndola en un navegador real en su servidor, por lo que cada comprobación tarda más y puede fallar si el sitio bloquea visitas automatizadas. Entonces Peek prueba servicios universales de seguimiento, que guardan poca información sobre Parcelforce.

Peek muestra el estado, cada escaneo con su lugar (sin códigos postales), el día previsto de entrega, la hora de entrega y el país de destino. Los estados mantienen el texto en inglés de Royal Mail, salvo algunos que Peek traduce, como `Out for delivery` («En reparto»), `Delivered` («Entregado») y `Returned to sender` («Devuelto al remitente»). Un escaneo en el extranjero sin zona horaria no se fecha; los que la traen aparecen en tu zona horaria («Horas en tu zona horaria:»). En una exportación con número tipo `EA482917367GB`, Peek también puede consultar el correo nacional del país de destino que indique Parcelforce (si no indica ninguno, el del país en el que te sitúa, salvo el Reino Unido) y pasar a su seguimiento en cuanto muestre un escaneo no más antiguo que el último de Parcelforce.

Mientras tengas el paquete abierto en Peek — o el día de la entrega con las notificaciones activadas — Peek comprueba hasta cada 10 minutos, y hasta cada 5 minutos a partir de las 6:00 hora del Reino Unido (o desde media hora antes de abrirse la franja horaria cuando Parcelforce indique una) el día en que Parcelforce lo escanea en reparto, mientras su rastreador responda; fuera de ese caso, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), cada hora; tras siete días sin novedades en «Etiqueta creada», cada 6 horas. Tras cuatro días sin nuevos escaneos en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra la fecha del último escaneo («Sin novedades desde el …») y «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Parcelforce Worldwide.», con el enlace «Pide ayuda a Parcelforce Worldwide» a la página de contacto de Parcelforce. Sitúa cada escaneo en un mapa y te avisa según elijas («Cada escaneo», «Pasos importantes» o «Solo la entrega»).

## Preguntas sobre el seguimiento de Parcelforce

### ¿Parcelforce forma parte de Royal Mail?

Sí. GOV.UK incluye a Parcelforce en Royal Mail Group y sus envíos se rastrean en la página Track your item de Royal Mail.

### ¿Por qué no se reconoce mi número de Parcelforce?

Puede que el paquete no haya llegado aún a Parcelforce o que el número tenga un error. Un número de envío aislado no se rastrea: pide al remitente el número `PB` de 14 caracteres de cada bulto e inténtalo más tarde.

### ¿Cuánto tiempo retiene Parcelforce un paquete con gastos de aduana?

Alrededor de 3 semanas. Si la factura no se abona en ese plazo, el paquete vuelve al remitente.

### ¿Puedo elegir otro día para una entrega de Parcelforce?

Sí, en la app de Royal Mail o mediante Royal Mail Customer Services. También puedes pedir que se entregue a un vecino.

:::sources
- [GOV.UK: Check when you can account for import VAT on your VAT Return](https://www.gov.uk/guidance/check-when-you-can-account-for-import-vat-on-your-vat-return) – Parcelforce forma parte de Royal Mail Group
- [Citizens Advice: Telling delivery companies about your delivery or collection needs](https://www.citizensadvice.org.uk/consumer/post-and-parcels/telling-delivery-companies-about-your-delivery-or-collection-needs/) – app de Royal Mail y Customer Services para Parcelforce, otro día, vecino, lugar seguro, llamar más fuerte, teléfono y horarios
- [Citizens Advice: Claiming compensation from Royal Mail](https://www.citizensadvice.org.uk/consumer/post-and-parcels/claiming-compensation-from-royal-mail/) – número Relay UK
- [Citizens Advice: Damaged, lost or delayed post or parcel](https://www.citizensadvice.org.uk/consumer/post-and-parcels/damaged-lost-delayed-post-or-parcel/) – vendedor primero, reclamaciones en la web del transportista
- [GOV.UK: Tax and customs for goods sent from abroad: Overview](https://www.gov.uk/goods-sent-from-abroad) – Parcelforce tramita la aduana, consultas sobre la mercancía
- [GOV.UK: Tax and customs for goods sent from abroad: Tax and duty](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – factura, unas 3 semanas, devolución al remitente
- [GOV.UK: Avoid and report internet scams and phishing](https://www.gov.uk/report-suspicious-emails-websites-phishing) – 7726, report@phishing.gov.uk
- [UPU: S10 standard, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – dígito de control antes del código de país
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – lo que Peek lee, estados y formatos de números
:::
