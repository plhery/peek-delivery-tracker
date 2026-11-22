---
title: Seguimiento Bring (Posten): números, estados y plazos
description: Sigue un paquete de Bring o Posten en Noruega, Suecia o Dinamarca: formato del número, significado de estados, plazos de recogida y contacto.
slug: seguimiento-bring
published: 2026-11-22
updated: 2026-11-22
---

Bring es la marca de paquetería de Posten Bring, el grupo postal público de Noruega; Posten es su marca para particulares en Noruega. Ambas comparten el mismo seguimiento: usa `sporing.bring.no`, `sporing.posten.no` o la app de Posten, o pega el número en el cuadro de arriba. El número llega de la tienda o por SMS o correo de Bring o Posten.

## Formato del número de seguimiento Bring

Bring indica que un número de envío o paquete suele tener 13 caracteres (dos letras, nueve cifras y dos letras), o bien 18 cifras empezando por `3`, o 17 cifras empezando por `7`. Introduce la combinación completa. Con cifras inventadas:

| Número (inventado) | Qué es |
| --- | --- |
| `RR482917367NO` | Dos letras, nueve cifras y `NO`: número de envío postal. |
| `370748291736482919` | 18 cifras empezando por `3`. |
| `00370748291736482919` | `00` seguido de 18 cifras que empiezan por `370`: 20 cifras en total. |
| `70482917364829174` | 17 cifras empezando por `7`. |

**Dónde encontrarlo.** En el correo de envío de la tienda, en un SMS o correo de Bring o Posten, o como código de barras en el aviso que Posten deja en el buzón. El rastreador de Bring admite también la referencia del remitente o tu número de móvil.

**¿Sendingsnummer o kollinummer?** Un envío (*sending*) puede incluir varios bultos (*kolli*): el *sendingsnummer* abarca todo el envío, cada *kollinummer* identifica un paquete concreto y el seguimiento de Bring muestra todos los bultos. Ambos son *sporingsnummer* (números de seguimiento) y el rastreador de Bring acepta cualquiera de los dos.

**¿Sin resultados?** Comprueba que el envío sea realmente de Posten o Bring y que no hayas introducido un número de pedido. Formatos parecidos: un código postal terminado en `SE` o `DK` corresponde a PostNord, y uno acabado en `FI` a [Posti](carrier:posti) de Finlandia.

## Significado de los estados de seguimiento Bring

El rastreador de Bring se abre en noruego en `sporing.posten.no` y `sporing.bring.no`, con opción de cambio a inglés; `tracking.bring.se` muestra sueco y `tracking.bring.dk` danés. No existe versión en finés. Los textos en inglés, en orden habitual:

| Bring indica | Qué significa para ti |
| --- | --- |
| `The sender has let us know that a parcel is on its way.` | La tienda ha registrado el envío. Bring aún no tiene el paquete. |
| `The shipment has been handed in to Bring.` | El paquete ya ha sido entregado a Bring. |
| `The shipment was delivered after the deadline and will be sent the next working day.` | Entregado después de la hora límite del día. Saldrá el siguiente día laborable; no pasa nada. |
| `The shipment has arrived at the terminal.` | Ha llegado a una terminal de clasificación de Bring. |
| `The shipment being processed for import.` | En trámite de aduanas de importación (redacción exacta de Bring). |
| `The shipment has been temporarily placed in warehouse.` | En espera en el depósito local antes de la ruta de reparto. |
| `The shipment is  unfortunately delayed and will be forwarded by first possible dispatch.` | Retrasado; saldrá en el primer transporte disponible. |
| `The parcel is now on its way to the delivery address.` | En reparto (*utkjøring*): cargado en el vehículo de entrega. |
| `The parcel is ready for pickup` | Te espera en el punto de recogida indicado en el seguimiento. |
| `The parcel has been delivered in your mailbox or outside your door.` | Entregado en tu buzón o junto a tu puerta. |
| `The shipment has been returned.` | De vuelta al remitente, por ejemplo tras vencer el plazo de recogida. |

`The shipment has been temporarily placed in warehouse.` suena a almacenaje, pero el original noruego designa el depósito desde donde salen las furgonetas de reparto. Líneas como `Home delivery date is changed.` o `The recipient has selected a desired delivery location.` solo registran un cambio en la entrega; el paquete no se ha movido.

**Las pausas son normales.** Bring y Posten señalan que no hay escaneos entre terminales, lo que puede tardar de 1 a 4 días. Una vez registrado en tu terminal local, suele salir a reparto ese mismo día o llegar al punto de recogida al día siguiente. Ver [por qué deja de actualizarse el seguimiento](guide:tracking-not-updating).

## Punto de recogida, taquilla Pakkeboks y entrega a domicilio

Estas normas rigen en Noruega; para Suecia y Dinamarca consulta `bring.se` o `bring.dk`.

**Dónde espera.** En una oficina postal, un *Post i Butikk* (punto postal en tienda) o una taquilla *Pakkeboks*, como suele indicar el seguimiento; las terminales no son puntos de recogida. Las taquillas admiten paquetes de hasta 10 kg. Recibes aviso por la app de Posten, SMS o correo, con hasta dos recordatorios.

**Plazos de recogida.** Plazos vigentes de Posten en Noruega:

- Taquilla Pakkeboks: 4 días, ampliables gratis hasta 7 días.
- Otros puntos de recogida: 7 días, ampliables gratis hasta 14 días en la app de Posten o por SMS, durante un periodo limitado.
- Paquetes con solo aviso en papel (como algunos envíos extranjeros): 14 días.

Algunos remitentes no permiten prórrogas, y la página de seguimiento muestra el límite exacto de cada paquete. Un paquete no recogido vuelve al remitente. Lleva documento de identidad: algunos envíos lo exigen.

**Entrega a domicilio.** Si la elegiste en la tienda, Posten entrega el día laborable que escojas, de 17:00 a 21:00 en ciudades (15:00 a 22:00 en torno a Oslo, Stokke y Drammen) y durante el día en otras zonas; para entregas vespertinas, un SMS indica una franja de una hora. Para llevar a domicilio un paquete destinado a punto de recogida, usa el enlace del mensaje: 75 NOK antes de llegar al punto de recogida y 100 NOK después; no sirve para taquillas ni paquetes con aduanas.

**Paquetes en buzón.** Los paquetes pequeños enviados como *Pakke i postkassen* llegan con el correo. Si no caben, se dejan ante tu puerta (pon tu nombre en la puerta), o van a tu punto de recogida si eliges esa opción al recibir el aviso de Posten; desde allí ya no pueden redirigirse a domicilio.

**Bultos grandes.** El servicio *Home Delivery* de Bring transporta artículos de más de 35 kg (muebles o electrodomésticos) según la modalidad contratada en la tienda: *Curbside* (a pie de calle o límite de la parcela), *Indoor* (subido al interior hasta el lugar que indiques) o *PickUp Point* (recogida en una terminal Home Delivery). Sin franja reservada, Bring envía un SMS para acordar día y franja horaria; el conductor avisa antes de llegar y normalmente se firma la entrega.

**Suecia y Dinamarca.** En Suecia, un paquete espera normalmente 7 días en un *ombud* (punto asociado), y el primer recordatorio ofrece 7 días más; en una taquilla *paketbox* espera 4 días, ampliables a 7. La *paketbox* se abre con la app de Bring o el PIN enviado por SMS. En Dinamarca, una entrega a domicilio fallida suele pasar al *pakkeshop* (punto de recogida) más cercano y Bring avisa por SMS o correo; si no, solicita nueva entrega en el formulario online de Bring o por teléfono.

## Paquete Bring retrasado, perdido o dañado

**Sin movimientos.** Si nada cambia en el plazo previsto, Bring pide contactar con el remitente para comprobar si entregó el paquete.

**Daños o falta de contenido.** Bring indica que la reclamación debe presentarla el remitente o quien pagó el porte; como destinatario, avisa a la tienda cuanto antes. Plazos de Bring: daños visibles o faltantes en el momento de la entrega; daños ocultos en 7 días naturales; otras incidencias como retrasos en 14 días tras la entrega. Toda reclamación prescribe al año. Haz fotos y conserva la mercancía. ¿Aparece entregado pero no está? Ver [entregado pero no recibido](guide:delivered-not-received).

**Aduanas en Noruega.** Con tiendas inscritas en el régimen noruego VOEC (artículos por debajo de 3000 NOK), no pagas nada a la llegada. En caso contrario, Posten cobra 46 NOK de gestión aduanera hasta 500 NOK, 78 NOK hasta 3000 NOK y 278 NOK por encima. Se paga en la app de Posten, en Digipost o por carta. Más en [paquetes retenidos en aduanas](guide:customs).

## Teléfono y atención al cliente de Bring y Posten

Datos publicados en las páginas de Bring y Posten (horarios en hora local de Noruega, Suecia y Dinamarca):

- **Posten, Noruega:** `22 03 00 00`, de lunes a viernes de 08:00 a 17:00; chat las 24 horas.
- **Bring, Noruega:** `04045`, o `+47 22030045` desde el extranjero, de lunes a viernes de 08:00 a 17:00; chat las 24 horas.
- **Reclamaciones por escrito:** Posten and Bring customer service, Kjøita 19, 4630 Kristiansand.
- **Suecia:** `0770-11 33 00`, de lunes a viernes de 08:00 a 17:00; chat las 24 horas.
- **Dinamarca:** `70 28 60 70` (`+45 70 28 60 70`), de lunes a viernes de 08:00 a 17:00; chat las 24 horas.

> Posten nunca envía un SMS ni un correo pidiéndote pagar por un paquete: usa únicamente la app de Posten, Digipost y cartas. Borra esos mensajes.

## Seguir un paquete Bring con Peek

[Peek](/) sigue a más de 4500 transportistas y asigna al instante Bring a un código postal acabado en `NO` si su dígito de control es válido («Transportista detectado»). Como las series de 18 cifras empezadas en `370`/`373`, los códigos de barras de 20 cifras con `00370`/`00373` y las de 17 cifras con `70` se comparten con otros transportistas, Peek pregunta primero a Bring junto con los demás transportistas compatibles (incluidos Matkahuolto para `373`, PostNord y otros para `00370`), y muestra Bring en cuanto Bring reconoce el paquete. Sin embargo, Peek no consulta a Bring por códigos que empiecen por `00373402216`, propios de la empresa sueca Early Bird. Si Bring no aparece, pulsa «Cambiar» y elige Bring en «Encaja con este número». Excepción: un número de 18 cifras empezado en `370` o `373` cuyas cifras 6.ª a 9.ª son ceros se asigna directamente a [Ecoscooting](carrier:ecoscooting) sin preguntar a Bring; usa «Cambiar» y busca Bring. Los enlaces de `sporing.bring.no`, `sporing.posten.no` y `tracking.bring.com`, `.se` o `.dk` identifican Bring sea cual sea el número, aunque en Bring Peek solo sigue números de 17 o 18 cifras, de 20 cifras empezados en `00` (quitando `00` para obtener el número de paquete de 18 cifras) y códigos postales S10 válidos de cualquier país. Un *sendingsnummer* de varios bultos no se sigue; introduce cada *kollinummer*.

Peek muestra el estado, la localidad de cada escaneo en el mapa, el remitente, el servicio, el peso, las dimensiones, la fecha prevista mientras el paquete está en camino, la hora de entrega y el nombre del punto de recogida mientras espera y una vez recogido. En lugar de las frases largas de Bring, cada paso aparece con una etiqueta breve como `Handed in`, `At terminal` o `Ready for pickup`. Las horas con zona horaria se muestran en tu zona horaria («Horas en tu zona horaria:» seguido de su nombre), y las demás según la hora local del lugar del escaneo. Peek omite direcciones, fotos y el código de recogida.

Mientras tengas el paquete abierto en Peek —o el día de entrega con notificaciones activadas— Peek consulta directamente a Bring hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 en la hora del último escaneo (hora de Oslo en Noruega) el día en que Bring registra «En reparto», mientras el rastreador de Bring responda; fuera de ese caso y tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), comprueba cada hora. Para notificaciones, elige «Cada escaneo», «Pasos importantes» o «Solo la entrega». Tras cuatro días sin escaneos en tránsito, fuera de aduanas o incidencias, Peek muestra la fecha del último escaneo («Sin novedades desde el …») y avisa «Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Bring.», con el enlace «Pide ayuda a Bring» hacia su servicio al cliente. Peek no puede contactar con Bring ni reclamar por ti.

## Preguntas frecuentes sobre Bring

### ¿Bring es lo mismo que Posten?

No exactamente: ambas son marcas de Posten Bring con un seguimiento compartido. Posten atiende a particulares en Noruega; Bring atiende a empresas en los países nórdicos y a particulares fuera de Noruega. Las empresas siguen sus envíos en Mybring, el portal de Bring con inicio de sesión, hasta 50 números a la vez.

### ¿Cuánto tarda Posten o Bring en entregar?

Posten señala que *Norgespakke* y los envíos a punto de recogida suelen tardar de 2 a 5 días laborables en Noruega. Bring Dinamarca entrega la mayoría de paquetes en 1 o 2 días.

### ¿A qué hora reparten Posten y Bring?

El correo llega entre las 08:00 y las 17:00, con ligera variación diaria, y las cartas cada dos días laborables. Los paquetes a domicilio llegan en días laborables, de 17:00 a 21:00 en ciudades y durante el día en el resto.

### ¿Cuánto tiempo guarda Bring un paquete en una taquilla?

En Noruega, 4 días en una taquilla Pakkeboks, ampliables gratis hasta 7 días. Después se devuelve al remitente.

### ¿Qué pasa si se pierde un paquete de Bring?

Contacta con la tienda: el remitente presenta la reclamación ante Bring, y todas las reclamaciones prescriben pasado un año.

:::sources
- [Bring: Tracking](https://www.bring.no/en/services/tracking) – formatos de número, procedencia del número, referencia del remitente, reparto el mismo día
- [Bring: Sporing av pakker, post og gods](https://www.bring.no/tjenester/spore) – sendingsnummer o kollinummer, contactar al remitente, 1 a 4 días sin novedades
- [Posten: Help with Posten tracking](https://www.posten.no/en/tracking-help-page) – aviso de buzón, pausas, puntos de recogida, horarios de entrega
- [Posten: Sporing hjelpeside og kundeservice](https://www.posten.no/sporing-kundeservice) – sendingsnummer y kollinummer, reparto el mismo día
- [Bring: Slik sporer du flere sendinger i Mybring](https://www.bring.no/radgivning/mybring/sporing-slik-sporer-du-flere-sendinger-i-mybring) – hasta 50 números
- [Bring: Spor sendingene dine](https://sporing.bring.no/sporing/) – textos de estados, idiomas, plazos de recogida
- [Posten: Hentefrister](https://www.posten.no/motta/fleksibelt/hentefrister) – plazos de custodia, prórrogas, devoluciones, documento de identidad
- [Bring: Pakke til hentested](https://www.bring.no/tjenester/pakker-og-gods/private-nasjonalt/pakke-til-hentested) – puntos de recogida, avisos, recordatorios
- [Bring: Pakkeboks](https://www.bring.no/tjenester/pakker-og-gods/private-nasjonalt/pakkeboks) – taquillas, tamaño y peso
- [Posten: Levering hjem med Posten alle hverdager](https://www.posten.no/motta/fleksibelt/hjemlevering) – tarifas de redirección, excepciones
- [Posten: Home delivery with Posten](https://www.posten.no/en/receive/flexibility/home-delivery) – horarios, zona de Oslo, franja SMS de una hora, excepciones
- [Posten: Dette bør du vite om levering av post](https://www.posten.no/kundeservice/sendinger-i-norge/sporsmal-om-levering-av-post) – horario del cartero, días de cartas
- [Bring: Vilkår for Pakke hjem i postkassen](https://www.bring.no/vilkar/pakke-hjem-postkassen) – envío con correo, entrega ante la puerta, nombre en la puerta
- [Bring: Home Delivery](https://www.bring.no/tjenester/pakker-og-gods/private-nasjonalt/hjemlevering-tyngre-gods) – más de 35 kg, niveles de servicio
- [Bring: Tjenesteunike vilkår for Home Delivery](https://www.bring.no/vilkar/tjenesteunike-vilkar-for-home-delivery) – franja SMS, contacto del conductor, firma
- [Bring: Generelle leveringsvilkår for Home Delivery](https://www.bring.no/vilkar/vilkar-for-home-delivery) – sending y kolli
- [Posten: Download the Posten app](https://www.posten.no/en/tracking-help-page/tracking-app) – la app sigue a Posten y Bring, código de recogida, apertura de taquilla
- [Posten: Customs clearance of shipments from abroad](https://www.posten.no/en/customs/receiving-from-abroad) – VOEC, tarifas, pago, sin SMS de cobro
- [Bring: Claims and complaints](https://www.bring.no/en/customer-service/claims-and-complaints) – quién reclama, plazos
- [Posten: How to complain or make claims](https://www.posten.no/en/customer-service/complaint-and-claims) – canales de reclamación, dirección, plazos
- [Bring: Contact us](https://www.bring.no/en/customer-service/contact-us) – teléfonos, horarios, chat
- [Posten: Contact us](https://www.posten.no/en/customer-service/contact-us) – teléfono, horarios, chat
- [Posten: Online fraud and fake inquiries](https://www.posten.no/en/customer-service/online-fraud) – aviso de phishing
- [Bring: Svindelforsøk](https://www.bring.no/kundeservice/svindelforsok) – nunca envía enlaces de pago así
- [Bring Sweden: Kontakta oss](https://www.bring.se/privat/kundservice/kontakta-oss) – teléfono, horarios, chat
- [Bring Sweden: Vanliga frågor](https://www.bring.se/privat/kundservice/vanliga-fragor) – prórroga en un ombud
- [Bring Sweden: Bring Paketbox](https://www.bring.se/privat/ta-emot/paketbox) – apertura con app o PIN por SMS
- [Bring Sweden: Tjänstevillkor](https://www.bring.se/villkor/Tj%C3%A4nstevillkor-bring-ecommerce-logistics_01102026.pdf) – 7 días en ombud, 4 en paketbox, prórrogas
- [Bring Denmark: Kontakt os](https://www.bring.dk/privat/kundeservice/kontakt-os) – teléfono, horarios, chat
- [Bring Denmark: Ofte stillede spørgsmål](https://www.bring.dk/privat/kundeservice/ofte-stillede-sporgsmal) – entrega fallida, plazos, documento, número erróneo
- [Bring Denmark: Pakker til genlevering](https://www.bring.dk/privat/kundeservice/genlevering) – nueva entrega por formulario o teléfono
- [Bring Denmark: Tjenestevilkår](https://www.bring.dk/vilkar/Tjenestevilk%C3%A5r-bring-ecommerce-logistics_01102026.pdf) – una entrega a domicilio fallida pasa a punto de recogida
- [Posten Bring: About the group](https://www.postenbring.no/en/about-us/about-the-group) – propiedad, marcas Posten y Bring
- [Bring: Tracking API](https://developer.bring.com/api/tracking/) – significado de los códigos de evento, bultos de un envío
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – campos que lee Peek, detección y frecuencia de comprobación
:::
