---
title: Seguimiento Delivengo: paquete de La Poste desde Francia
description: Rastrea un paquete Delivengo enviado desde Francia: código terminado en FR, estados de La Poste, entrega con Correos en España, aduanas y contacto.
slug: delivengo-tracking
published: 2026-11-21
updated: 2026-11-21
---

Delivengo es el servicio de exportación de [La Poste](carrier:la-poste) francesa para pequeños paquetes internacionales. Si una tienda francesa envió tu pedido con Delivengo, el paquete sale de Francia con La Poste y el operador postal de tu país realiza el último tramo —en España, [Correos](carrier:correos-spain)—. Puedes seguirlo en el localizador de La Poste en `laposte.fr` con el código que te dio el comercio o pegándolo en el cuadro superior.

## Tu número de seguimiento Delivengo: formato y ejemplos

Los envíos de Delivengo usan códigos postales internacionales de 13 caracteres: dos letras, nueve dígitos (el último es un dígito de control) y `FR`.

**De dónde sale.** Te lo facilita la tienda, normalmente en el correo de confirmación de envío. La web `mydelivengo.laposte.fr` (MyDelivengo) es el área privada para empresas remitentes, no un buscador para destinatarios. Si no encuentras el código, consulta [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

**Códigos parecidos** (todos con cifras inventadas):

| Ejemplo (inventado) | A qué operador pertenece |
| --- | --- |
| `LD482910576FR` | [La Poste](carrier:la-poste): Delivengo, Colissimo y otros envíos postales franceses comparten este formato. |
| `RR482910576ES` | [Correos](carrier:correos-spain) (sufijo `ES`), no Delivengo. |
| `LD482910576GB` | [Royal Mail](carrier:royal-mail) en Reino Unido (sufijo `GB`), no Delivengo. |

Las dos letras finales identifican al correo emisor. Dentro de un código terminado en `…FR` nada distingue por fuera a Delivengo de otro envío de La Poste, pero el seguimiento es exactamente el mismo. Al llevar dígito de control, una sola cifra mal escrita invalida el número y Peek deja de reconocerlo: cópialo tal cual.

## Estados de seguimiento de Delivengo en La Poste

El sistema de La Poste responde a los códigos Delivengo igual que al resto de su red. Peek agrupa cada evento en una etapa clara:

| Etapa en Peek | Qué indica el escaneo de La Poste |
| --- | --- |
| «Etiqueta creada» | Envío anunciado: el comercio generó la etiqueta, pero La Poste aún no tiene el paquete. |
| «Admitido por el transportista» | La Poste se ha hecho cargo del envío en Francia. |
| «En tránsito» | El paquete avanza por Francia, sale hacia el extranjero o ya llegó al país de destino. |
| «En aduanas» | En despacho aduanero (fuera del territorio aduanero/fiscal común); al liberarse vuelve a «En tránsito». |
| «En reparto» | En el centro de distribución preparando el reparto del día. |
| «Intento de entrega» / «Listo para recoger» | Entrega fallida en domicilio o paquete esperando en una oficina postal o punto de recogida. |
| «Entregado» | Entregado. Si no aparece, revisa [entregado pero no recibido](guide:delivered-not-received). |
| «Incidencia» | Problema en ruta (daño, rechazo, dirección incompleta, plazo vencido) o devolución en marcha hacia la tienda. |
| «Devuelto al remitente» | El paquete ya ha sido devuelto al vendedor. |

Tras la exportación desde Francia, el correo de destino asume la entrega final y el historial en `laposte.fr` puede quedarse sin nuevos eventos después de salir de Francia.

## Entrega, ausencias y aduanas en España y Latinoamérica

**Quién entrega y qué ocurre si no estás.** La entrega final la hace el correo nacional de tu país ([Correos](carrier:correos-spain) en España, o el operador postal oficial en Latinoamérica), no La Poste. Sus normas rigen los avisos de llegada, los nuevos intentos y los días de custodia en oficina; consulta [entrega fallida](guide:missed-delivery).

**Aduanas.** Entre la Francia metropolitana y **España peninsular y Baleares** no hay aranceles aduaneros gracias a la unión aduanera de la UE. Sí existen trámites fiscales específicos si el envío procede de departamentos o colectividades francesas de ultramar (Guadalupe, Martinica, Guayana Francesa, Reunión, Mayotte y San Martín están fuera del área de IVA de la UE) o si va dirigido a **Canarias** (fuera del ámbito del IVA comunitario, con IGIC) o a **Ceuta y Melilla** (fuera del territorio aduanero y de IVA de la UE). En envíos hacia países de Latinoamérica se aplican las normas de importación postal de cada país; más detalles en [paquetes retenidos en aduanas](guide:customs).

## Paquete Delivengo retrasado, parado o perdido

Empieza siempre contactando con la tienda donde compraste, titular de la cuenta Delivengo.

**Días sin actualizarse.** Al salir de Francia es frecuente que `laposte.fr` deje de añadir pasos mientras tu correo local encamina el paquete. Prueba el mismo código `…FR` en [Correos](carrier:correos-spain) (o tu correo nacional) y revisa [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Extraviado, dañado o devuelto.** Solicita al vendedor un reenvío o el reembolso. Si resides en la UE, Your Europe recuerda que el comerciante responde de cualquier daño sufrido por la mercancía desde la expedición hasta que la recibes; si no entrega en 30 días (o en el plazo acordado), debes darle un plazo adicional razonable y, si sigue sin cumplir, tienes derecho a resolver el contrato y recuperar tu dinero. Consulta [paquete perdido](guide:lost-parcel) y [devuelto al remitente](guide:returned-to-sender).

## Contacto de Delivengo

- **La tienda primero**: expidió el paquete desde su cuenta MyDelivengo.
- **La Poste, para reclamaciones**: según Arcep (el regulador postal francés), puedes presentar una reclamación ante La Poste por teléfono en el `36 31` (lunes a viernes de 8:30 a 19:00 y sábados de 8:30 a 13:00) o desde fuera de Francia en el `+33 810 821 821` (lunes a viernes de 8:00 a 19:00 hora francesa), en su web, por carta o en un punto postal; Arcep advierte de que los derechos del remitente y del destinatario no son idénticos y no detalla si estos canales gestionan Delivengo por separado.
- **Tu correo nacional** cuando el envío ya está en tu país, indicando el mismo número `…FR`.

## Seguir un paquete Delivengo con Peek

[Peek](/) no usa una conexión aparte para Delivengo: lo rastrea mediante el seguimiento de [La Poste](carrier:la-poste), igual que cualquier Colissimo o carta certificada francesa. Al pegar un código `…FR` válido, Peek asigna al instante «La Poste / Colissimo»; puedes cambiarlo a «Delivengo» con «Cambiar» o «Cambiar transportista» y obtendrás los mismos datos. También acepta enlaces de seguimiento de `laposte.fr` (incluidos los procedentes de `mydelivengo.laposte.fr`).

Peek muestra el estado, el historial con el país de cada evento y la fecha estimada de entrega cuando La Poste la publica, omitiendo tu nombre y dirección. Los mensajes conservan el francés de La Poste y las horas se adaptan a tu huso («Horas en tu zona horaria: {{zone}}.»). Cuando La Poste informa el país de destino o un socio extranjero, Peek consulta también al correo nacional compatible —como [Correos](carrier:correos-spain) en España, [USPS](carrier:usps) en EE. UU. o Canada Post en Canadá— y cambia a él en cuanto muestra avances más recientes, conservando el historial francés.

Mientras tienes el envío abierto en pantalla, o el día de reparto con notificaciones activas, Peek comprueba el seguimiento directo hasta cada 10 minutos, y hasta cada 2 minutos cuando pasa a «En reparto» (ese día desde las 6:00 hora local mientras responda directamente el seguimiento de La Poste); el resto del tiempo, y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Mediante servicios de seguimiento comprueba hasta cada 15 minutos ese día en «En reparto» bajo las mismas condiciones, y si no cada hora, o cada 3 horas tras un día sin cambios. Al pasar el relevo al correo de destino, Peek sigue la frecuencia de ese operador. Puedes configurar avisos para «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre el seguimiento de Delivengo

### ¿Delivengo es lo mismo que Colissimo?

No, aunque ambos pertenecen a [La Poste](carrier:la-poste) y comparten su sistema de seguimiento: Delivengo es su servicio comercial de exportación para pequeños paquetes internacionales.

### ¿Por qué el seguimiento de Delivengo se detiene al salir de Francia?

Porque el último kilómetro lo realiza el correo de tu país y el historial de La Poste puede quedarse mudo tras la exportación. Comprueba el mismo código `…FR` en [Correos](carrier:correos-spain) (Peek lo consulta automáticamente cuando La Poste indica España como destino).

### ¿A quién reclamo si mi pedido Delivengo no llega?

A la tienda donde hiciste la compra, que contrató el envío en MyDelivengo y es responsable frente al consumidor de que el pedido llegue a tus manos.

:::sources
- [Universal Parcel Scraper: Delivengo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/delivengo) – servicio de exportación de La Poste, entrega por el correo de destino, portal MyDelivengo y seguimiento compartido
- [Universal Parcel Scraper: La Poste](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/la-poste) – formato S10 con dígito de control, códigos de evento y relevo internacional
- [Unión Europea: La unión aduanera de la UE en acción](https://european-union.europa.eu/priorities-and-actions/actions-topic/customs_es) – libre circulación sin aranceles entre países de la UE
- [Comisión Europea: Territorial scope of EU VAT and customs](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – régimen fiscal de Canarias, Ceuta, Melilla y territorios franceses de ultramar
- [Your Europe: Envío y entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – responsabilidad del vendedor hasta la entrega y plazo de 30 días
- [Arcep: La résolution des litiges postaux](https://www.arcep.fr/mes-demarches-et-services/consommateurs/fiches-pratiques/la-resolution-des-litiges-postaux.html) – vías de reclamación ante La Poste (36 31, +33 810 821 821 y horarios)
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – datos que Peek lee y muestra
:::
