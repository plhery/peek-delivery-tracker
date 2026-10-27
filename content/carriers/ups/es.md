---
title: UPS seguimiento en España: número 1Z, estados y aduanas
description: Seguimiento de UPS en España: cómo es el número 1Z, qué significan Exception y los demás escaneos, los Access Point, el arancel de aduanas y tus derechos.
slug: ups-seguimiento
published: 2026-10-13
updated: 2026-10-27
---

Los números de seguimiento de UPS (United Parcel Service) suelen empezar por `1Z`. Pega el número, o un enlace de seguimiento de UPS, en la casilla de arriba para seguir el paquete; la página de seguimiento de la propia UPS está en ups.com.

## Número de seguimiento de UPS: dónde está y cómo es

Te lo da la tienda que envía el paquete, normalmente en el correo de envío o en la página del pedido. Un enlace de seguimiento de ups.com también lo lleva, detrás de `tracknum=`. ¿No aparece por ningún lado? Lee [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

| Número (inventado) | Qué es |
| --- | --- |
| `1Z1234567812345674` | El número de bulto de UPS: `1Z` y 16 letras y cifras, 18 caracteres en total. El último es un dígito de control calculado a partir de los 15 anteriores. |
| `H1234567890` | Una letra (H, J, K o V) y 10 cifras. Puede ser un número de UPS, pero otras empresas usan la misma forma. |

## Estados del seguimiento de UPS: qué significa «Exception»

La página de seguimiento de UPS en inglés tiene una barra de progreso (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) y, debajo, los escaneos. Peek lee esos escaneos en inglés y encima te muestra el estado en español. Estos son los principales, más o menos en el orden en que los encuentra un paquete:

| UPS escribe | Peek muestra | Qué significa para ti |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Etiqueta creada | El remitente ha creado la etiqueta. UPS todavía no tiene el paquete. |
| `Drop-Off`, `Pickup Scan` | Admitido por el transportista | UPS ya lo tiene: el remitente lo dejó en un Access Point o UPS pasó a recogerlo. El primer `Arrived at Facility` también cuenta aquí. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | En tránsito | Se mueve por la red de UPS. `Export Scan` e `Import Scan` marcan el paso de una frontera. |
| `Your package is prepared for clearance.` | En aduanas | Está pasando la aduana. |
| `Duties or taxes are due on this package.` | En aduanas | Tienes que pagar gastos de importación antes de que UPS lo entregue (más abajo). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | En reparto | Lo lleva un repartidor. |
| `The receiver was not available at the time of the final delivery attempt.` | Intento de entrega | También ha fallado el último intento de entrega. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Listo para recoger | Te espera en un UPS Access Point. |
| `DELIVERED` | Entregado | Entregado, o recogido por ti en un Access Point. |
| `Return to Sender` | Devuelto al remitente | Vuelve al remitente. |

`Exception` es el estado genérico de UPS y puede indicar un simple retraso. Lo que cuenta es el escaneo de debajo: `We're sorry this package may experience a temporary delay.` es solo eso, mientras que `The company or receiver name is incorrect. This will delay delivery.` significa que la entrega depende de tus datos (Peek muestra «Incidencia»). Peek se guía por el escaneo más reciente, no por el estado genérico.

## Entrega fallida de UPS y UPS Access Point

Si el repartidor no puede entregarlo, el escaneo dice que el destinatario no estaba o que el negocio estaba cerrado, y UPS vuelve a intentarlo. Tras el último intento, un escaneo posterior indica qué pasa: UPS guarda el paquete, lo lleva a un Access Point o lo devuelve al remitente.

UPS también puede llevar el paquete a un UPS Access Point. Allí los escaneos dicen `Delivered to UPS Access Point™` y luego `UPS Access Point™ possession`, y Peek muestra el nombre y la dirección del punto. No lo dejes para el último momento: cuando un paquete no recogido vuelve, el escaneo dice `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Si un Access Point cierra, UPS recoge sus paquetes y el escaneo indica que la entrega en otro punto está pendiente. Más en [paquete no recogido](guide:uncollected-parcel).

## Aduanas: qué se paga por un paquete de UPS

Un paquete que llega de fuera de la UE puede tener gastos de importación que hay que pagar antes de que UPS lo entregue; el seguimiento muestra entonces `Duties or taxes are due on this package.` y `Your package is being held until duties and taxes are paid. Awaiting customer action.`

Según la Agencia Tributaria, desde el 1 de julio de 2026 las compras por internet de hasta 150 euros enviadas desde fuera de la UE (China, Estados Unidos o Reino Unido, por ejemplo) pagan, además del IVA, un arancel fijo de 3 euros por cada categoría de artículos. Cuándo se paga depende de la tienda: si sus condiciones de compra dicen que el precio incluye el arancel, el transportista no te cobrará nada por él. Si no, el transportista puede pedirte el arancel y el IVA, y quizá unos gastos de gestión, antes de entregarte el paquete. A partir del 1 de noviembre de 2026 se suma una tasa de tramitación de 2 euros por categoría de artículos, que se paga junto con el arancel. Lo que decide si pagas es el lugar desde el que se envía la mercancía, no el dominio de la web ni que los precios estén en euros. Más en [paquete retenido en aduanas](guide:customs).

## Paquete de UPS retrasado, perdido o entregado sin recibirlo

Si el seguimiento no se mueve, lee [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating).

Si compraste en una tienda, es ella quien responde de que el paquete te llegue, aunque lo lleve UPS. Según Your Europe, el portal de la UE, el vendedor responde de los daños desde que envía el pedido hasta que lo recibes. Si no habéis acordado otro plazo, debe entregarlo en 30 días; si no llega, avísale y dale un plazo adicional razonable. Si tampoco lo cumple, tienes derecho a rescindir el contrato y a que te devuelva el dinero lo antes posible.

Según la guía de la CNMC sobre los derechos de los usuarios postales, el envío de paquetes es un servicio postal, también el urgente, y son usuarios tanto el remitente como el destinatario. La reclamación por pérdida, robo o daños se presenta ante el operador con el que se contrató el envío, en los seis meses siguientes a su admisión, y el operador tiene un mes para responder, o tres si el envío es internacional. Si no hay respuesta o no te convence, puedes acudir a una Oficina Municipal de Información al Consumidor, a la dirección de consumo de tu comunidad autónoma, a una asociación de consumidores o a una Junta Arbitral de Consumo.

**¿Pone `DELIVERED` y no está?** La página de UPS puede mostrar dónde se dejó el paquete (`Left at: Front Door`, por ejemplo) y una prueba de entrega, que Peek no guarda. Míralo en ups.com y después habla con la tienda. Más en [paquete entregado pero no recibido](guide:delivered-not-received) y [paquete perdido](guide:lost-parcel).

## Seguimiento de UPS con Peek

[Peek](/) reconoce UPS al momento con un número `1Z` cuyo dígito de control es correcto. Si no lo es, propone solo UPS, que lo rechaza por estar mal escrito. Puedes pegar también un enlace de ups.com o cualquier enlace que lleve dentro un número `1Z`. A UPS Peek solo le pregunta por números `1Z`: con otras formas, como `H1234567890`, responden únicamente servicios de seguimiento, también después de «Cambiar transportista».

Para leer los datos, el servidor de Peek abre la página de seguimiento de ups.com en un navegador, cada 10 minutos de día, cada 5 minutos cuando el paquete está en reparto y cada una o dos horas por la noche. Si UPS rechaza la consulta, o solo se puede leer el estado, los servicios de seguimiento completan el historial. Ves el estado, cada escaneo en un mapa con su lugar y su hora exacta, el día de entrega previsto (nunca una franja horaria), la hora de entrega, el país de destino y, mientras el paquete espera, el nombre y la dirección del Access Point. Las notificaciones las eliges tú: cada escaneo, los pasos importantes o solo el día de entrega.

Peek no muestra quién firmó, tu dirección, la prueba de entrega, dónde se dejó el paquete ni la foto, y UPS no le da ni el peso ni el remitente. Los escaneos salen en el inglés de UPS: la entrega se lee «DELIVERED». Si en Estados Unidos UPS pasa un paquete Ground Saver al servicio postal estadounidense ([USPS](carrier:usps)) e indica su número, Peek lo sigue allí en cuanto este muestra movimiento («Entrega con USPS»).

## Preguntas sobre el seguimiento de UPS

### ¿Por qué UPS dice que mi número de seguimiento no es válido?

Lo más frecuente es un error al copiarlo: un carácter mal escrito en un número `1Z` suele romper su dígito de control, y UPS lo rechaza. O el número es de otra empresa: mira cuál nombra el correo de la tienda.

### ¿Cuánto tarda en aparecer el seguimiento de UPS?

El primer escaneo, `Shipper created a label, UPS has not received the package yet.`, aparece en cuanto el remitente crea la etiqueta. No avanza hasta que el remitente entrega el paquete o UPS lo recoge (`Drop-Off`, `Pickup Scan`).

### ¿Puedo cambiar la entrega de UPS desde Peek?

No: Peek solo lee el seguimiento de UPS. Los cambios que hagas con UPS aparecen en los escaneos, como `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### ¿Es de UPS un SMS que me pide pagar para entregar el paquete?

Desconfía. INCIBE ha avisado de una campaña de SMS falsos que se hacen pasar por empresas de paquetería: hablan de un problema con la entrega y llevan a una web que pide tus datos personales y los de tu tarjeta. No pulses el enlace: comprueba el envío tú mismo en ups.com o en Peek, y luego borra el SMS y bloquea al remitente. Si tienes dudas, llama al 017, la Línea de Ayuda en Ciberseguridad de INCIBE; si ya diste los datos de la tarjeta, avisa enseguida a tu banco. Y la Aduana no se dirige al consumidor para cobrarle el arancel. Más en [estafa del paquete por SMS](guide:delivery-scam-texts).

:::sources
- [Agencia Tributaria: Envíos por un valor de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – arancel de 3 euros desde el 1 de julio de 2026, IVA y gastos de gestión al transportista, tasa de 2 euros a partir del 1 de noviembre de 2026, la Aduana no cobra al consumidor
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – 30 días, plazo adicional, rescisión y reembolso, el vendedor responde de los daños
- [CNMC: Guía sobre los derechos de los usuarios postales](https://www.cnmc.es/sites/default/files/editor_contenidos/Postal/202412_STP_DTSP_070_24_Guia%20usuarios%20postales%20CNMC.pdf) – paquetes y envío urgente como servicio postal, remitente y destinatario como usuarios, reclamación en seis meses, respuesta en uno o tres meses, vías de consumo
- [INCIBE: Campaña de smishing que suplanta a empresas de paquetería](https://www.incibe.es/ciudadania/avisos/campana-de-smishing-que-suplanta-empresas-de-paqueteria-con-la-excusa-de-que) – SMS falsos de entrega, 017, banco
- [Universal Parcel Scraper: UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – formas del número, dígito de control, escaneos de UPS, qué lee Peek de UPS
:::
