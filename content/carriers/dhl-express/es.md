---
title: DHL Express seguimiento: número de albarán y estados
description: Seguimiento de DHL Express: dónde está el albarán de 10 cifras, qué significan sus estados, aduanas, entregas fallidas y qué hacer si el paquete se pierde.
slug: dhl-express-seguimiento
published: 2026-10-13
updated: 2026-10-27
---

Pega un número de albarán de DHL Express, o un enlace de seguimiento de MyDHL+, en la casilla de arriba para seguir el paquete. El seguimiento propio de DHL está en su web (MyDHL+) y en la app de DHL Express.

## Número de seguimiento de DHL Express: el albarán de 10 cifras

El número de seguimiento de DHL Express es el número de albarán (en inglés, «waybill number»). Búscalo en el correo de envío de la tienda o en la página de tu pedido; un enlace de seguimiento de MyDHL+ también lo lleva, detrás de `id=`. ¿Sigues sin él? Más ideas en [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

| Número (inventado) | Qué es |
| --- | --- |
| `1234567891` | El albarán: 10 cifras, con el que sigues todo el envío. La última es un dígito de control. |
| `JD014600001234567890` | El identificador de pieza: `JD01` y 16 cifras, impreso como `(J)JD…` bajo el código de barras inferior de la etiqueta. Cada bulto del envío tiene el suyo, y lleva al albarán. |

Otras empresas también usan números de 10 cifras, como [TIPSA](carrier:tipsa), [Mondial Relay](carrier:mondial-relay), Relais Colis, Estafeta, Packeta, [DHL eCommerce Iberia](carrier:dhl-ecommerce-es) (DHL Parcel en España) y [DHL Paket](carrier:dhl), así que comprueba qué empresa nombra el correo de la tienda.

DHL reutiliza los números de albarán: con un número pueden aparecer escaneos de un envío anterior, que Peek ignora.

## Estados de DHL Express: qué significa cada uno

Estos son los textos de escaneo que Peek lee de DHL Express, en el inglés de DHL, más o menos en el orden en que los ve un paquete, con el estado que muestra Peek para cada uno. Muchos terminan con el centro donde está el paquete, por ejemplo `Processed at` seguido de una ciudad y un país.

| Escaneo de DHL Express | Peek muestra | Qué significa |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Etiqueta creada | El remitente ha enviado a DHL los datos del envío. |
| `Shipment picked up`, `Shipment accepted` | Admitido por el transportista | DHL Express ya tiene el paquete. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | En tránsito | Avanza por la red de DHL. |
| `Scheduled to depart on the next planned movement` | En tránsito | Espera en un centro su próximo camión o vuelo. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | En aduanas | Está pasando la aduana (más abajo). |
| `Payment is received and recorded for shipment related fees` | En aduanas | Ya se han pagado los aranceles o gastos; sigue en aduanas hasta que termine el despacho. |
| `Clearance processing complete at` | En tránsito | La aduana ha terminado con él y vuelve a moverse. |
| `Arrived at DHL Delivery Facility` | En tránsito | Ha llegado al centro de DHL que lo reparte. |
| `Shipment is out with courier for delivery` | En reparto | Un mensajero lo lleva hacia ti. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Intento de entrega | No se pudo entregar. El «consignee» eres tú, el destinatario. |
| `Shipment is scheduled for delivery` | En tránsito | Hay una entrega prevista en un reparto posterior, por ejemplo tras un intento fallido. |
| `Awaiting collection by the consignee` | Listo para recoger | Te espera en un centro de DHL para que lo recojas. |
| `Delivered` | Entregado | Ya está entregado. |
| `Shipment is on hold` | Incidencia | DHL ha parado el envío por ahora. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Incidencia | Se clasificó en un centro equivocado y sigue hacia el correcto, con retraso. |
| `Returned to shipper` | Devuelto al remitente | Vuelve al remitente. |

## Entrega fallida de DHL Express

Tras un intento fallido, el seguimiento dice `Delivery attempted but no response at Consignee address` o `Delivery attempt could not be completed`. Si después sale `Shipment is scheduled for delivery`, hay un nuevo intento previsto; `Awaiting collection by the consignee` significa que el paquete te espera en un centro de DHL para que lo recojas. Para cambiar la entrega, contacta con DHL Express en su web o su app: Peek no puede cambiarla. Más en [entrega fallida](guide:missed-delivery) y [paquete no recogido](guide:uncollected-parcel).

## Aduanas: qué se paga por un paquete de DHL Express

Un paquete que llega de fuera de la UE puede tener gastos de importación. Mientras está en la aduana, el seguimiento muestra `Customs clearance status updated…`, `Processed for clearance at` o `Clearance event`; `Payment is received and recorded for shipment related fees` cuando se han pagado los gastos, y `Clearance processing complete at` cuando la aduana lo libera.

Según la Agencia Tributaria, desde el 1 de julio de 2026 las compras por internet de hasta 150 euros enviadas desde fuera de la UE pagan, además del IVA, un arancel fijo de 3 euros por cada categoría de artículos. Si las condiciones de compra de la tienda dicen que el precio incluye el arancel, el transportista no te cobrará nada por él. Si no, puede pedirte el arancel y el IVA, y quizá unos gastos de gestión, antes de entregarte el paquete. Más en [paquete retenido en aduanas](guide:customs).

## Envío de DHL Express retrasado, retenido o perdido

Si el seguimiento no se mueve, o dice `Shipment is on hold`, lee [qué hacer si el seguimiento no se actualiza](guide:tracking-not-updating).

Si compraste en una tienda, es ella quien responde de que el paquete te llegue, aunque lo lleve DHL Express. Según Your Europe, el portal de la UE, el vendedor responde de los daños desde que envía el pedido hasta que lo recibes. Si no habéis acordado otro plazo, debe entregarlo en 30 días; si no llega, avísale y dale un plazo adicional razonable. Si tampoco lo cumple, tienes derecho a rescindir el contrato y a que te devuelva el dinero lo antes posible.

**¿Pone entregado y no lo tienes?** La página de seguimiento de DHL puede mostrar la firma, y Peek no la guarda. Míralo en la web de DHL y después habla con la tienda. Más en [paquete entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

## Seguimiento de DHL Express con Peek

[Peek](/) reconoce un albarán de 10 cifras cuyo dígito de control es correcto, pone DHL Express en primer lugar y pregunta a la vez a DHL Express y a otras empresas con números de 10 cifras, como ACS Courier, Geniki Taxydromiki y DHL eCommerce Iberia, y se queda con quien conoce el envío. ACS y Geniki usan el mismo dígito de control: si una de ellas también conoce el número, eliges tú. Un número de 10 cifras que no pasa el control no se ofrece a DHL Express: elígelo con «Cambiar transportista». Los identificadores `JD01` y las etiquetas `JJD0146` (`JJD014612345678901234`) se asignan a DHL Express al momento, y Peek los sigue con el albarán al que pertenecen. También puedes pegar un enlace de mydhl.express.dhl. Con un enlace de dhl.com, pega mejor el albarán solo; si el paquete acaba como «DHL», cámbialo a «DHL Express» con «Cambiar transportista».

Peek lee DHL Express a través del servicio de su app móvil o, si no, abre la página de seguimiento de dhl.com en un navegador de su servidor, que es más lento y que DHL puede rechazar; en ese caso, los datos llegan de servicios de seguimiento. Lo consulta cada 10 minutos de día, cada 2 a 5 minutos cuando el paquete está en reparto y cada una o dos horas por la noche. Las notificaciones las eliges tú: cada escaneo, solo los pasos importantes o solo el día de entrega.

Ves el estado, cada escaneo en un mapa con el centro donde se hizo, y el país de destino. La hora de cada escaneo se sitúa según la ciudad del centro o, si no basta, según los escaneos de alrededor. Los escaneos salen en el inglés de DHL, salvo `Delivered`, `Shipment picked up` y `Shipment information received`, que Peek muestra con sus propias palabras. Peek no guarda la firma, ignora los escaneos antiguos de un albarán reutilizado y no muestra día de entrega previsto, peso ni Service Point. Tampoco paga aranceles ni cambia la entrega: eso se hace con DHL.

## Preguntas sobre el seguimiento de DHL Express

### ¿El número de albarán es el número de seguimiento de DHL Express?

Sí. El albarán de 10 cifras sigue todo el envío; el identificador de pieza que empieza por `JD01` corresponde a un solo bulto y lleva al albarán.

### ¿Por qué no funciona mi número de DHL Express?

A menudo es el número de otro transportista de DHL: los albaranes de DHL Express tienen exactamente 10 cifras y la última es un dígito de control, así que un número con una errata no suele pasarlo. Un albarán recién creado puede mostrar solo `Shipment information received` hasta que DHL recoge el paquete.

### ¿Es lo mismo DHL que DHL Express?

En Peek, no: DHL Express es un transportista aparte de [DHL Paket](carrier:dhl) y de [DHL eCommerce Iberia](carrier:dhl-ecommerce-es), cada uno con sus números y su seguimiento. Un paquete sin albarán de 10 cifras ni identificador `JD01` suele ser de uno de ellos.

### ¿Puedo seguir un envío de DHL Express sin el albarán?

En Peek, no: hace falta el albarán de 10 cifras o un identificador `JD01` de la etiqueta. Pídele el albarán a la tienda o al remitente.

### ¿Es de DHL un SMS o un correo que me pide pagar para entregar el paquete?

Desconfía. INCIBE ha avisado de una campaña de SMS falsos que se hacen pasar por empresas de paquetería para quedarse con tus datos personales y los de tu tarjeta, y de correos falsos de DHL que hablan de un paquete devuelto y piden pagar una pequeña tarifa en 48 horas. No pulses el enlace: comprueba el envío tú mismo en la web de DHL o en Peek, y luego borra el mensaje y bloquea al remitente. Si ya diste datos bancarios, llama a tu banco cuanto antes. Si tienes dudas, llama al 017, la Línea de Ayuda en Ciberseguridad de INCIBE. Más en [estafa del paquete por SMS](guide:delivery-scam-texts).

:::sources
- [Agencia Tributaria: Envíos por un valor de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – arancel de 3 euros desde el 1 de julio de 2026, IVA y gastos de gestión al transportista
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – 30 días, plazo adicional, rescisión y reembolso, el vendedor responde de los daños
- [INCIBE: Campaña de smishing que suplanta a empresas de paquetería](https://www.incibe.es/ciudadania/avisos/campana-de-smishing-que-suplanta-empresas-de-paqueteria-con-la-excusa-de-que) – SMS falsos de entrega, no pulsar el enlace, borrar y bloquear, banco, 017
- [INCIBE: DHL no está avisando por correo de que un paquete ha sido devuelto](https://www.incibe.es/ciudadania/avisos/dhl-no-esta-avisando-por-correo-de-que-un-paquete-ha-sido-devuelto-y-que-se) – correos falsos de DHL, tarifa y plazo de 48 horas, contactar con el banco
- [Universal Parcel Scraper: DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – formas del albarán y del identificador de pieza, dígito de control, otros números de 10 cifras, albaranes reutilizados, escaneos de DHL Express y estado de Peek para cada uno, firma en la web de DHL, qué lee Peek de DHL Express
- [Universal Parcel Scraper: DHL eCommerce Iberia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-ecommerce-es) – DHL Parcel en España, números de 10 cifras
:::
