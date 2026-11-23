---
title: Seguimiento Packlink: referencia, estados y contacto
description: Sigue un envío de Packlink: formato ES…COM, significado de cada estado, entregas fallidas, reclamaciones por daños y cómo contactar con Packlink.
slug: seguimiento-packlink
published: 2026-11-23
updated: 2026-11-23
---

Packlink no es una empresa de mensajería. Es una plataforma de contratación con sede en Madrid que se define como comparador de envíos: un particular o una tienda compara allí transportistas como SEUR, Correos, Correos Express o UPS, paga, imprime la etiqueta y ese transportista recoge y entrega el paquete. Las tiendas utilizan Packlink PRO, su servicio para empresas conectado con tiendas online y marketplaces como eBay y Amazon. Para seguir un envío, introduce la referencia de Packlink o el número del transportista en [la página de seguimiento de Packlink](https://www.packlink.es/seguimiento-envios/) o en el recuadro de arriba.

## Número de seguimiento de Packlink: cómo es

Packlink asigna a cada envío una referencia propia: dos letras del mercado, el año, `COM` o `PRO` y diez dígitos. La web española de Packlink indica que sus referencias empiezan por ES. El transportista también asigna al paquete su propio número, y la página de seguimiento de Packlink admite cualquiera de los dos. Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `ES2026COM4829173650` | ES, el año, COM y 10 dígitos: una referencia de Packlink. |
| `ES2026PRO6194028375` | Lo mismo con PRO: también una referencia de Packlink. |
| `48291736405829` | 14 dígitos: uno de los formatos de SEUR, que comparten DPD y otros transportistas. |
| `1Z9X482W0348291737` | 1Z y 16 letras o dígitos: un número de UPS. |

**Dónde encontrarlo.** Packlink envía la referencia en el correo de confirmación al contratar el envío. Si compraste algo a otra persona y lo envió mediante Packlink, esa persona tiene ese correo: pídele la referencia o el número del transportista. Más sitios donde buscar: [cómo encontrar tu número de seguimiento](guide:find-tracking-number) y [compras de segunda mano](guide:second-hand-orders).

## Estados de seguimiento de Packlink explicados

Packlink lee cada escaneo del transportista y lo agrupa bajo su propio código de estado, con un texto en inglés en su página de seguimiento. Muchos escaneos conservan además las palabras del propio transportista, como las mayúsculas de SEUR o Correos Express. Líneas frecuentes, por orden:

| Línea de seguimiento | Qué significa para ti |
| --- | --- |
| `Waiting for carrier to collect the order` | El remitente contrató el envío y tiene la etiqueta. El transportista aún no tiene el paquete. |
| `SIN RECEPCION` | Lo mismo en Correos Express: tiene el registro, todavía no el paquete. |
| `RECIBIDO EN ORIGEN` | La primera delegación de SEUR ya tiene el paquete. Packlink sigue clasificándolo en espera de recogida. |
| `Shipment accepted by carrier (scanned)` | El transportista escaneó el paquete. Ya está en marcha. |
| `EN ARRASTRE` | Viajando entre delegaciones del transportista. |
| `DELEGACION DESTINO` | En la delegación que reparte en tu zona. |
| `TU ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO` | SEUR: aún no ha llegado a tu delegación local. Packlink lo clasifica como incidencia de entrega, pero simplemente sigue en camino. |
| `EN REPARTO` | En reparto. |
| `REPARTO FALLIDO` | Falló un intento de entrega. |
| `Package at pick-up point and ready for collection` | Esperándote en un punto de recogida. Packlink lo incluye entre sus códigos de entrega, pero todavía tienes que recogerlo. |
| `ENTREGADO` | Entregado. |
| `Package returned to sender` | El paquete ha sido devuelto al remitente. |

Otras líneas están en inglés claro: `Recipient not present at indicated address` y `Unsuccessful delivery and final attempt` indican entregas fallidas, y `Packet delivered to customs for clearance` corresponde a aduanas en envíos que cruzan frontera aduanera.

Dos líneas no dicen nada sobre el paquete en sí. `Pending, there is no information available on the carrier’s page or the tracking number has not yet been activated` indica que Packlink todavía no ha encontrado datos en el transportista. `Expired, there is no tracking information for the last 30 days` indica que el transportista no dio novedades durante un mes y Packlink dejó de seguirlo.

## Quién entrega un paquete de Packlink

El transportista elegido por el remitente. La página de seguimiento de Packlink enumera [SEUR](carrier:seur), [Correos](carrier:correos-spain), [Correos Express](carrier:correos-express), [UPS](carrier:ups), GLS, DHL, [TNT](carrier:tnt), [InPost](carrier:inpost), [Mondial Relay](carrier:mondial-relay), [NACEX](carrier:nacex) y Zeleris, entre otros. Las condiciones de Packlink establecen:

- **Domicilio o punto de recogida.** El remitente decide al contratar si el paquete va a una dirección o a un punto de recogida.
- **Entrega fallida.** El transportista puede dejar un aviso para concertar nueva entrega y llevar el paquete de vuelta a su delegación. No todos dejan aviso, por lo que Packlink pide al remitente vigilar el seguimiento y avisarte.
- **Intentos.** En la mayoría de los casos hay un primer intento y un segundo gratuito. Cada intento adicional cuesta al remitente entre 2 € y 15 € más IVA, según el transportista y el vehículo. En algunos países el transportista puede dejarlo a un vecino o en un punto de recogida.
- **Recogida.** Un paquete en la delegación del transportista debe recogerse dentro del plazo de ese transportista. Pasado ese plazo, el remitente puede tener que pagar almacenaje, devolución o destrucción.
- **Rechazado o no entregable.** Packlink avisa al remitente mediante el seguimiento o por correo. El remitente tiene entonces 2 días laborables para indicar si desea recuperarlo o enviarlo a otra dirección.

Ver también [entregas fallidas](guide:missed-delivery) y [paquetes no recogidos](guide:uncollected-parcel).

**Aduanas.** Si tu paquete llega desde fuera de la UE con mercancías compradas online, desde el 1 de julio de 2026 se aplica un arancel de la UE de 3 € por tipo de producto en pedidos de hasta 150 €. Desde el 1 de noviembre de 2026 corresponde también una tasa de gestión de la Unión de 2 € por tipo de producto, sea cual sea el valor. Las condiciones de Packlink indican que el destinatario asume los derechos e impuestos de importación, pero no explican cómo se te cobran estos dos importes. Ver [paquetes retenidos en aduanas](guide:customs).

## Paquete de Packlink que no llega, dañado o devuelto

**Quién puede actuar.** Según las condiciones de Packlink, solo quien contrató y pagó el envío puede presentar reclamaciones y consultas, desde el correo electrónico usado en la contratación. Un tercero solo accede a una reclamación con una declaración jurada del remitente autorizándolo. Si eres el comprador, es el vendedor quien debe contactar con Packlink.

**Sin novedades.** Mientras aparezca `Waiting for carrier to collect the order`, el transportista no ha recogido el paquete: pregunta al remitente si llegó a entregarlo. Ante cualquier retraso o falta de actualización, el remitente contacta con Packlink. Más en [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

**Perdido.** Packlink solo tramita una reclamación por extravío cuando el transportista ha buscado en sus delegaciones y lo ha declarado perdido. Esa búsqueda suele llevar hasta 30 días desde la fecha prevista de entrega. Ver [paquetes perdidos](guide:lost-parcel).

**Dañado.** Si hay daños visibles en la entrega, escribe *Con Daños* en el albarán o dispositivo del repartidor, tal como pide Packlink. Sin esa anotación, Packlink señala que no puede tramitar la reclamación de forma satisfactoria. Conserva el artículo y todo su embalaje para inspección: Packlink rechaza la reclamación si el objeto se manipuló o reparó o si se tiró el embalaje. Para pérdida o daños ocultos, el remitente debe abrir reclamación en 7 días en transporte terrestre (en España o internacional), 10 días en transporte aéreo nacional y 14 días en transporte aéreo internacional, desde el día siguiente a la entrega. Ver [paquetes dañados](guide:damaged-parcel).

**Devuelto.** `Package returned to sender` significa que el paquete volvió al remitente. Pídele un nuevo envío o un reembolso. Ver [devuelto al remitente](guide:returned-to-sender).

**Entregado pero no está.** Consulta con vecinos y en el punto de recogida y avisa al remitente de inmediato. Ver [entregado pero no recibido](guide:delivered-not-received).

## Teléfono y contacto de Packlink

Canales publicados por Packlink:

- **Teléfono:** ninguno. Packlink afirma que opera exclusivamente online y no dispone de soporte telefónico. Advierte de que los teléfonos de Packlink que figuran en otras webs no son reales ni están verificados por Packlink: no los uses.
- **Formulario de contacto:** en el centro de ayuda de Packlink, enlazado desde su página de teléfono. Elige la categoría adecuada y, para un envío ya realizado, ten a mano la referencia del correo de confirmación. Packlink ofrece también chat en directo con atención al cliente.
- **Reclamaciones:** desde el mismo centro de ayuda, únicamente por quien contrató el envío.

## Seguir un paquete de Packlink con Peek

Pega la referencia de Packlink en [Peek](/). Su formato pertenece solo a Packlink, así que la pantalla de añadir muestra Packlink al instante como «Transportista detectado». También puedes pegar el número propio del transportista: Peek sigue a más de 4.500 transportistas. Si ese número encaja con varios transportistas —como los 14 dígitos de SEUR—, Peek muestra «Comprobando servicios de seguimiento…» mientras les pregunta; pulsa «Cambiar» para elegir tú mismo el transportista.

Mientras tengas el paquete abierto en Peek, o el día de entrega con las notificaciones activadas, Peek consulta directamente a Packlink hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 hora de Madrid el día en que sale a reparto, mientras responda el seguimiento propio de Packlink; si no, cada hora. Tras dos días sin novedades (12 horas mientras siga en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora; tras siete días sin novedades en «Etiqueta creada», cada 6 horas. Si pasan cuatro días sin escaneo en un envío en «Etiqueta creada», «Admitido por el transportista» o «En tránsito», Peek muestra «Sin novedades desde el …», el aviso «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Packlink.» y «Pide ayuda a Packlink». Muestra cada escaneo en las palabras del transportista cuando Packlink las ofrece, o en el texto en inglés de Packlink, con la hora leída en hora de Madrid y mostrada en la zona horaria de tu dispositivo. Ante una línea de punto de recogida, Peek muestra «Listo para recoger»; ante `RECIBIDO EN ORIGEN`, «Admitido por el transportista». El día estimado de entrega de Packlink aparece durante el tránsito, y un envío multibulto se muestra como un único historial. Peek omite las localidades —por lo que no hay mapa— y el número de bultos. El seguimiento de Packlink no da peso, remitente ni destinatario.

El seguimiento de Packlink incluye también el número propio del transportista. Cuando la forma de ese número identifica con certeza a un transportista, como un código de UPS que empieza por 1Z, Peek consulta también a ese transportista. En cuanto este muestra un escaneo al menos tan reciente como el último de Packlink, Peek sigue el paquete allí conservando el historial de Packlink: entonces muestra «Entrega con UPS» y ambos números en los detalles. Ningún formato de SEUR ni de Correos Express es tan unívoco, de modo que sus envíos siguen leyéndose en Packlink.

Peek puede avisarte en «Cada escaneo», «Pasos importantes» o «Solo la entrega», pero no puede abrir reclamaciones, cambiar la entrega ni contactar con nadie por ti.

## Preguntas sobre el seguimiento de Packlink

### ¿Packlink es una empresa de mensajería?

No. Packlink es una plataforma donde particulares y tiendas comparan y contratan transportistas. El transportista elegido —como SEUR, Correos Express o UPS— recoge y entrega el paquete.

### ¿Cuál es el teléfono de Packlink?

Ninguno: Packlink funciona solo online y advierte de que los teléfonos publicados en otras webs no están verificados por ellos. Usa el formulario de su centro de ayuda o el chat en directo.

### ¿Dónde está mi número de seguimiento de Packlink?

En el correo de confirmación que envió Packlink al contratar el envío: dos letras como ES, el año, COM o PRO y diez dígitos. Si eres el comprador, pídeselo al remitente.

### ¿Por qué mi seguimiento de Packlink dice Expired?

`Expired, there is no tracking information for the last 30 days` significa que el transportista no aportó novedades durante un mes y Packlink dejó de seguir el envío. No significa que el paquete se haya perdido. Pide al remitente que contacte con Packlink.

:::sources
- [Packlink: Seguimiento envios](https://www.packlink.es/seguimiento-envios/) – seguimiento con referencia Packlink o número del transportista, referencias que empiezan por ES, transportistas listados, Packlink PRO
- [Packlink: ¿Y cómo funciona esto de Packlink?](https://www.packlink.es/como-funciona/) – comparación y contratación de transportistas, etiquetas, integraciones Packlink PRO con eBay y Amazon
- [Packlink: Teléfono Packlink](https://www.packlink.es/telefono-packlink/) – comparador, sin atención telefónica, teléfonos no verificados en otras webs, formulario de contacto, chat en directo, referencia en el correo de confirmación
- [Packlink: Términos y Condiciones](https://www.packlink.es/terminos-condiciones/) – empresa en Madrid, solo el contratante puede reclamar, domicilio o punto de recogida, avisos, intentos y coste, plazo de recogida, paquetes rechazados, derechos de importación, plazos de reclamación, reservas por daños, búsqueda de 30 días
- [Comisión Europea: reforma aduanera de la UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – arancel de 3 € por tipo de producto hasta 150 € desde el 1 de julio de 2026, tasa de gestión de la Unión de 2 € desde el 1 de noviembre de 2026
- [Comisión Europea: guía sobre el arancel de 3 € y la tasa de gestión de la Unión (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € por tipo de producto sea cual sea el valor, para bienes vendidos a distancia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato de referencia, formatos de transportistas, textos y códigos de estado, hora de Madrid, qué lee Peek de Packlink, paso al transportista
:::
