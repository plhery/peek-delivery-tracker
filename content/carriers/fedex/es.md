---
title: FedEx seguimiento: número, estados del envío y aduanas
description: Seguimiento de FedEx en España: cómo es el número de 12 cifras, qué significan «Delivery exception» y otros escaneos, aduanas y tus derechos si se pierde.
slug: fedex-seguimiento
published: 2026-10-13
updated: 2026-10-28
---

Pega un número de seguimiento de FedEx, o un enlace de seguimiento de FedEx, en la casilla de arriba para seguir el paquete. El seguimiento propio de FedEx está en fedex.com y en la app FedEx Mobile.

## Número de seguimiento de FedEx: dónde está y cuántas cifras tiene

Míralo en el correo de envío de la tienda o en la página de tu pedido. Un enlace de seguimiento de fedex.com también lo lleva, detrás de `trknbr=` o `tracknumbers=`. ¿Sigues sin él? Lee [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

| Número (inventado) | Qué es |
| --- | --- |
| `123456789012` | 12 cifras: un número de seguimiento de FedEx. La última cifra es un dígito de control calculado con las 11 anteriores. |
| `123456789012343` | 15 cifras: otra forma de número de FedEx. |
| `61290000000000000014` | 20 cifras que empiezan por `6129` o `7489`: FedEx Ground Economy (antes SmartPost), en Estados Unidos. |

Las 12 cifras también las usan [MRW](carrier:mrw), [Mondial Relay](carrier:mondial-relay) y varias empresas europeas y asiáticas. Si fedex.com no encuentra el número, comprueba qué empresa nombra el correo de la tienda. Un número que empieza por `1Z` es de [UPS](carrier:ups), y los números de 10 cifras de [DHL Express](carrier:dhl-express) tampoco son de FedEx.

## Estados del seguimiento de FedEx: qué significa «Delivery exception»

Peek lee los escaneos de FedEx en inglés, tal como los escribe FedEx, y encima muestra el estado en español. Estos son los principales, más o menos en el orden en que los encuentra un paquete:

| FedEx escribe | Peek muestra | Qué significa para ti |
| --- | --- | --- |
| `Shipment information sent to FedEx` | Etiqueta creada | El remitente ha enviado a FedEx los datos del envío. |
| `Picked up`, `Tendered at FedEx location` | Admitido por el transportista | FedEx ya lo tiene: lo recogió un mensajero o el remitente lo entregó en un centro de FedEx. |
| `Arrived at FedEx location`, `Departed FedEx location`, `At local FedEx facility`, `On the way` | En tránsito | Avanza por la red de FedEx. |
| `Clearance in progress`, `Clearance delay` | En aduanas | Está pasando la aduana (más abajo). |
| `International shipment release - Import` | En tránsito | La aduana lo ha liberado en el país de destino. |
| `On FedEx vehicle for delivery` | En reparto | Está en la furgoneta de reparto. |
| `Delivery exception`, `Customer not available or business closed` | Intento de entrega | La entrega no se pudo hacer. |
| `Ready for pickup` | Listo para recoger | Te espera en un punto de recogida. |
| `Delivered` | Entregado | Está entregado. |
| `Shipment exception` | Incidencia | FedEx informa de un problema con el envío. |
| `Returning to shipper` | Devuelto al remitente | Vuelve al remitente. |

Cuando FedEx marca un paquete como retrasado, Peek muestra «Incidencia», salvo si el último escaneo es `Package received after FedEx cutoff`: FedEx lo recibió después de la hora de corte del día y sale el siguiente día hábil.

## Entrega fallida de FedEx y puntos de recogida

Si la entrega no se hace, un escaneo puede decir por qué: `Customer not available or business closed` si no había nadie, o `Local delivery restriction - Delivery not attempted` si una restricción local impidió intentarlo.

Si pides a FedEx que guarde el paquete en un punto de recogida, el escaneo puede decir `Delivery option requested` mientras va de camino, y `Ready for pickup` cuando ya ha llegado. Peek muestra entonces el nombre y la dirección del punto si FedEx los da: cuando la retención se pide después del envío, la web de FedEx solo enseña la localidad a quien no ha iniciado sesión. La web de FedEx puede llamar al último tramo `Heading to pickup location`, que Peek cuenta como en reparto. Esos cambios los haces con FedEx, no en Peek. Más en [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).

## Aduanas: qué se paga por un paquete de FedEx

Un paquete que llega de fuera de la UE puede tener gastos de importación. Mientras está en la aduana, el seguimiento muestra `Clearance in progress` o `Clearance delay`, y `International shipment release - Import` cuando la aduana lo libera.

Según la Agencia Tributaria, desde el 1 de julio de 2026 las compras por internet de hasta 150 euros enviadas desde fuera de la UE pagan, además del IVA, un arancel fijo de 3 euros por cada categoría de artículos. Si las condiciones de compra de la tienda dicen que el precio incluye el arancel, el transportista no te cobrará nada por él. Si no, puede pedirte el arancel y el IVA, y quizá unos gastos de gestión, antes de entregarte el paquete. Más en [paquete retenido en aduanas](guide:customs).

## Paquete de FedEx retrasado, perdido o entregado sin recibirlo

Si el seguimiento no se mueve, lee [qué hacer si el seguimiento no se actualiza](guide:tracking-not-updating).

Si compraste en una tienda, es ella quien responde de que el paquete te llegue, aunque lo lleve FedEx. Según Your Europe, el portal de la UE, el vendedor responde de los daños desde que envía el pedido hasta que lo recibes. Si no habéis acordado otro plazo, debe entregarlo en 30 días; si no llega, avísale y dale un plazo adicional razonable. Si tampoco lo cumple, tienes derecho a rescindir el contrato y a que te devuelva el dinero lo antes posible.

**¿Pone entregado y no lo tienes?** El escaneo de entrega de FedEx nombra a quien firmó, y Peek no lo guarda. Míralo en fedex.com y después habla con la tienda. Más en [paquete entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

## Seguimiento de FedEx con Peek

[Peek](/) reconoce los números de 12 y 15 cifras de FedEx cuando su dígito de control es correcto. Otras empresas comparten esas formas, así que a menudo es un servicio de seguimiento el que identifica a FedEx, o Peek lo hace tras una breve espera. Un número que falla el control no se ofrece a FedEx: elige la empresa con «Cambiar». Los números Ground Economy de 20 cifras se asignan a FedEx al momento, los códigos de barras escaneados de la etiqueta se leen por el número que llevan y también valen los enlaces de fedex.com con `trknbr=` o `tracknumbers=`. Peek sigue como FedEx Poland los números de 13 cifras de los envíos nacionales de FedEx en Polonia.

Para leer los datos, el servidor de Peek abre la página de seguimiento de fedex.com en un navegador. Cuando FedEx la rechaza, o pide verificar al destinatario, los servicios de seguimiento completan el historial.

Ves el estado, cada escaneo en un mapa con su lugar y su hora exacta, el día de entrega previsto, la hora de entrega y, mientras el paquete espera, el nombre y la dirección del centro de FedEx, si FedEx los da. El paquete se consulta cada 10 minutos de día, cada 5 minutos cuando está en reparto y cada una o dos horas por la noche, y recibes notificaciones de cada escaneo, de los pasos importantes o solo el día de entrega. No muestra tu nombre ni tu dirección, el remitente, la firma, el peso ni las medidas. Los escaneos salen en el inglés de FedEx, salvo tres: la entrega, que nombra a quien firmó y Peek muestra como «Entregado», y `Ready for pickup` y `Shipment exception`, que Peek muestra con sus propias palabras.

## Preguntas sobre el seguimiento de FedEx

### ¿Qué significa «delivery exception» en FedEx?

Peek clasifica este escaneo de FedEx como un intento de entrega fallido y muestra «Intento de entrega». El motivo puede aparecer en un escaneo como `Customer not available or business closed` (no había nadie o el negocio estaba cerrado).

### ¿Por qué el seguimiento de FedEx solo dice «Shipment information sent to FedEx»?

Ese primer escaneo solo indica que el remitente ha enviado a FedEx los datos del envío. El seguimiento avanza cuando FedEx ya tiene el paquete: `Picked up` o `Tendered at FedEx location`.

### ¿Es de FedEx un SMS que me pide pagar para entregar el paquete?

Desconfía. INCIBE ha avisado de una campaña de SMS falsos que se hacen pasar por empresas de paquetería: hablan de un problema con la entrega y llevan a una web que pide tus datos personales y los de tu tarjeta. No pulses el enlace: comprueba el envío tú mismo en fedex.com o en Peek, y luego borra el SMS y bloquea al remitente. Si tienes dudas, llama al 017, la Línea de Ayuda en Ciberseguridad de INCIBE. Y la Aduana no se dirige al consumidor para cobrarle el arancel. Más en [estafa del paquete por SMS](guide:delivery-scam-texts).

:::sources
- [Agencia Tributaria: Envíos por un valor de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – arancel de 3 euros desde el 1 de julio de 2026, IVA y gastos de gestión al transportista, la Aduana no cobra al consumidor
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – 30 días, plazo adicional, rescisión y reembolso, el vendedor responde de los daños
- [INCIBE: Campaña de smishing que suplanta a empresas de paquetería](https://www.incibe.es/ciudadania/avisos/campana-de-smishing-que-suplanta-empresas-de-paqueteria-con-la-excusa-de-que) – SMS falsos de entrega, 017
- [Universal Parcel Scraper: FedEx](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/fedex) – formas del número, dígitos de control, escaneos de FedEx, qué lee Peek de FedEx
:::
