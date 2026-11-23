---
title: Seguimiento Chronodiali: números CHPU, estados y contacto
description: Sigue un paquete de Chronodiali en Marruecos: el número CHPU, sus estados en francés explicados, pago contra entrega, tres intentos y contactos.
slug: seguimiento-chronodiali
published: 2026-11-23
updated: 2026-11-23
---

Chronodiali (también escrito Chrono Diali) es una empresa marroquí de entregas para tiendas online, sociedad conjunta de Barid Al-Maghrib (Poste Maroc) y Geopost. Reparte paquetes de comercio electrónico en todo Marruecos, a domicilio o en una de sus agencias. Si la tienda eligió pago contra entrega, abonas al repartidor cuando llega el paquete, en efectivo o con tarjeta bancaria. Síguelo en «Suivre mon colis» en [chronodiali.ma](https://chronodiali.ma/suivi_colis.html) o en la casilla de arriba. El número lo da la tienda que envió el paquete; el ejemplo de Chronodiali empieza por `CHPU`.

## Tu número de seguimiento de Chronodiali

Los formatos que Peek reconoce, con cifras inventadas:

| Número | Qué es |
| --- | --- |
| `CHPU4829103756182947305` | `CHPU` y 19 dígitos: un número de Chronodiali. Peek lo reconoce al instante. |
| `CD4829103756` | `CD` y 10 dígitos. Peek también puede seguirlo con Chronodiali, pero el mismo formato encaja con Colis Privé, Aramex Australia y [Aramex Nueva Zelanda](carrier:aramex-nz), así que Peek consulta a los cuatro (ver abajo). |
| `chronodiali.ma/suivi_colis.html?id=…` | Un enlace a la página de seguimiento de Chronodiali. Peek lo lee como Chronodiali sea cual sea el número incluido. |

Chronodiali no es [Chronopost](carrier:chronopost), la empresa francesa de mensajería urgente: sus números y rastreadores son distintos. ¿No tienes número? Pregunta a la tienda o consulta [dónde encontrar un número de seguimiento](guide:find-tracking-number).

## Estados del seguimiento de Chronodiali explicados

Chronodiali escribe su seguimiento en francés. En el orden en que suelen aparecer:

| Chronodiali dice | Peek muestra | Qué significa para ti |
| --- | --- | --- |
| `Les données du colis ont été intégrées dans nos systèmes.` | Etiqueta creada | La tienda ha enviado los datos del paquete a Chronodiali, pero Chronodiali aún no lo tiene. |
| `Le ramassage a été programmé.` | Etiqueta creada | Se ha programado la recogida en la tienda. |
| `Le ramassage est en attente, le livreur se présentera sous peu.` | Etiqueta creada | A la espera de que el repartidor recoja el paquete en el remitente. |
| `Le colis a été collecté par le livreur.` | Admitido por el transportista | El repartidor lo ha recogido. |
| `Le colis est arrivé au hub d'origine.` | En tránsito | Llegó al primer centro logístico de Chronodiali. |
| `Le colis a quitté notre hub.`, `Le colis est arrivé au hub.` | En tránsito | En movimiento entre los centros de Chronodiali. |
| `Delivery Scheduled` | En tránsito | Asignado a una ruta en el centro de destino. |
| `Out For Delivery`, `Le colis est en cours de livraison.` | En reparto | Lo lleva el repartidor hoy. Si pagas contra entrega, ten el importe listo. |
| `Le colis a été livré.` | Entregado | Entregado. |
| `La livraison a échoué : le client n'a pas répondu ou était injoignable.` | Intento de entrega | El repartidor no pudo localizarte por teléfono ni en la puerta. |
| `La livraison a échoué : le lieu de livraison est incorrect ou introuvable.` | Intento de entrega | La dirección es incorrecta o el repartidor no la encontró. |
| `La livraison a échoué : le client est indisponible.` | Intento de entrega | No estabas disponible. |
| `La livraison a échoué : le client n'a pas le montant COD requis.` | Intento de entrega | No tenías el importe contra reembolso (COD). |
| `La livraison a échoué : le client a refusé le colis.` | Intento de entrega | El paquete fue rechazado. |
| `La livraison a échoué.` | Intento de entrega | Entrega fallida, sin motivo indicado. |
| `Le colis est retenu au hub.` | Incidencia | El paquete está retenido en un centro. |
| `La livraison a été annulée : le colis est endommagé et ne peut pas être livré.` | Incidencia | El paquete está dañado y no se puede entregar. |
| `Le colis a été annulé.` | Incidencia | El envío fue cancelado. |
| `Le colis va être renvoyé à l'expéditeur.` | Devuelto al remitente | Va de vuelta a la tienda. |
| `Le colis a été retourné à l'adresse de l'expéditeur.` | Devuelto al remitente | Ya está de vuelta en la dirección de la tienda. |

**No sale en chronodiali.ma.** La página oficial omite algunas de estas líneas: `Delivery Scheduled`, `Out For Delivery`, entregas fallidas, entregas canceladas o reprogramadas y `Le colis a été confié à un partenaire tiers.` Peek sí las muestra, por lo que su historial puede ser más completo.

**Otras líneas.** `La livraison a été reprogrammée : une nouvelle date a été définie.` indica nueva fecha de entrega; `La livraison a été reprogrammée.` no detalla más. `La livraison a été annulée à la demande du client.` significa cancelación solicitada por el cliente. `Le colis est en attente au hub.` indica que espera en un centro. Para las dos frases con `partenaire tiers`, ver las preguntas abajo; Peek muestra ambas como «En tránsito».

**Paquetes del extranjero.** Algunos llegan a Marruecos desde otro país. Entonces puedes ver `arrivé dans le pays.` (llegado a Marruecos; Peek muestra «En tránsito»), `dédouanement en cours` (despacho aduanero en curso; «En aduanas») y `dédouanement terminé` (liberado por aduanas; «En tránsito»). El sitio de Chronodiali no menciona aranceles ni quién los paga: pregunta a la tienda y consulta [paquetes retenidos en aduanas](guide:customs).

## Entrega de Chronodiali: pago contra entrega, tres intentos y agencias

Chronodiali afirma entregar en todo Marruecos, en más de 300 destinos, a domicilio o en agencia.

- **Pago contra entrega:** la página de seguimiento muestra el importe a pagar (`Montant à payer`) en dírhams (DH). Según Chronodiali, puedes pagarlo al recibir en efectivo o con tarjeta bancaria.
- **Tres intentos:** Chronodiali incluye «3 Tentatives de livraison» entre sus servicios. En Peek, cada intento fallido aparece con una línea que empieza por `La livraison a échoué`, con o sin motivo.
- **Mantén el teléfono encendido:** uno de los motivos de entrega fallida es no contestar o estar ilocalizable.
- **Notificaciones:** su página de servicios lista avisos por SMS y correo electrónico y una notificación al cliente en cada paso.
- **Agencias:** Agadir, Casablanca, Fez, Marrakech, Rabat y Tánger, abiertas de lunes a viernes de 9:00 a 18:00 y sábados de 9:00 a 13:00 (hora de Marruecos). Las direcciones están en su página «Points de livraison».

El sitio no especifica cuántos días guarda una agencia un paquete ni qué ocurre tras el tercer intento fallido: pregunta a Chronodiali o a la tienda. ¿Faltaste a la entrega? Ver [entregas fallidas](guide:missed-delivery).

## Paquete de Chronodiali retrasado, rechazado, devuelto o no recibido

Para un reembolso o un nuevo envío, acude a la tienda. La tienda es el cliente de Chronodiali y solicita las recogidas desde su plataforma de cliente.

- **Sin movimiento tras `Les données du colis ont été intégrées dans nos systèmes.`:** la tienda creó el envío, pero Chronodiali aún no ha recogido el paquete. Pregunta a la tienda cuándo lo entregará. Si chronodiali.ma muestra `En cours de traitement.`, Chronodiali conoce el número pero todavía no tiene escaneos.
- **Retrasado:** Chronodiali no publica tiempos de entrega. Sus condiciones indican que se informan con el pedido y dependen del destino. Ver [seguimiento que no se actualiza](guide:tracking-not-updating).
- **Rechazado o impagado:** si rechazas el paquete o no puedes pagarlo, el repartidor registra un fallo de entrega y el paquete puede volver a la tienda: `Le colis va être renvoyé à l'expéditeur.` y después `Le colis a été retourné à l'adresse de l'expéditeur.` Ver [devuelto al remitente](guide:returned-to-sender).
- **Entregado pero no recibido:** Chronodiali facilita a las tiendas un comprobante digital de entrega en su área de clientes. Pide a la tienda que lo revise. Más en [entregado pero no recibido](guide:delivered-not-received).
- **Dañado o perdido:** avisa primero a la tienda. También puedes marcar «Réclamation ?» en el formulario de contacto de Chronodiali, o llamar o escribir con tu número. Ver [paquetes dañados](guide:damaged-parcel) y [paquetes perdidos](guide:lost-parcel).
- **Un mensaje pidiendo dinero:** en su web, Chronodiali describe el pago como uno realizado en la entrega, en efectivo o con tarjeta. Si un SMS o correo te pide pagar mediante un enlace, revisa primero el seguimiento y consulta [SMS falsos de entrega](guide:delivery-scam-texts).

## Teléfono de contacto y atención al cliente de Chronodiali

Datos publicados en chronodiali.ma:

- **Teléfono:** +212 5 20 61 62 63 (05 20 61 62 63 desde Marruecos).
- **Correo electrónico:** service-client@chronodiali.ma.
- **Formulario de contacto:** en la página [Contact](https://chronodiali.ma/contact_us.html), elige «Réclamation ?» para una reclamación o «Demande d'information ?» para una consulta. El formulario solo pide nombre, correo, teléfono y nombre de la tienda, sin campo para mensaje ni número de seguimiento; Chronodiali indica que se pondrá en contacto contigo.
- **Agencias:** las seis mencionadas arriba, con el mismo teléfono y correo.

El sitio no especifica un horario distinto para el teléfono ni ofrece número de WhatsApp.

## Seguir un paquete de Chronodiali con Peek

Pega un número `CHPU` y [Peek](/) identifica Chronodiali al momento en «Transportista detectado». Las minúsculas y los espacios no importan. También sirve un enlace a la página de seguimiento de chronodiali.ma.

Un número `CD` de 10 dígitos no identifica al transportista por sí solo. Pégalo igual: Peek sigue a más de 4.500 transportistas. Muestra «Comprobando servicios de seguimiento…» mientras consulta a Chronodiali y a los transportistas que comparten ese formato; si solo Chronodiali tiene el paquete, aparece su nombre con «tiene este paquete». O pulsa «Cambiar» y elige «Chronodiali» en «Encaja con este número». Si eliges Chronodiali para un número de otra forma, Peek indica que Chronodiali «no emite números como este» y el envío muestra «Revisa el número de seguimiento».

Peek consulta el seguimiento propio de Chronodiali: el estado, cada línea tal como la escribe Chronodiali (en francés, o en inglés en las dos líneas indicadas), el nombre del hub como lugar cuando lo hay, el país de destino, la hora de entrega y, cuando Chronodiali los incluye, el nombre del destinatario, el destino y el firmante. Peek muestra las horas en la zona horaria de tu dispositivo. Omite el nombre y la ciudad del remitente, el importe a pagar, la prueba de entrega y las llamadas del repartidor. El seguimiento de Chronodiali no facilita fecha prevista de entrega ni peso. Las líneas sobre un socio tercero no indican cuál es, y Peek no sigue el rastreador de ese socio.

Si Chronodiali aún no conoce el número, Peek muestra «Esperando a Chronodiali» y sigue comprobando: hasta cada 10 minutos, y cada 2 minutos desde las 6:00 hora local el día en que Chronodiali lo escanea en reparto. Tras dos días sin novedades (12 horas si sigue en «Etiqueta creada» o «Admitido por el transportista»), revisa cada hora. Puede notificarte en «Todas las novedades», «Solo importantes» o «Solo el día de entrega». No puede pagar, reprogramar una entrega ni contactar a Chronodiali o a la tienda por ti.

## Preguntas sobre el seguimiento de Chronodiali

### ¿Forma Chronodiali parte de Poste Maroc?

En parte. Su página de noticias la define como sociedad conjunta: Barid Al-Maghrib (Poste Maroc) posee el 51 % y Geopost el 49 %. Tiene su propio seguimiento en chronodiali.ma y su propia atención al cliente.

### ¿Cuántas veces intenta entregar Chronodiali?

Tres veces, según Chronodiali. En Peek, cada intento fallido aparece con una línea que empieza por `La livraison a échoué`, con o sin motivo.

### ¿Puedo pagar un paquete de Chronodiali con tarjeta?

Sí. Chronodiali indica que puedes pagar en la entrega en efectivo o con tarjeta bancaria.

### ¿Qué significa «partenaire tiers» en el seguimiento de Chronodiali?

`Le colis a été assigné à un partenaire tiers.` y `Le colis a été confié à un partenaire tiers.` significan que Chronodiali ha asignado o entregado el paquete a una empresa socia. El seguimiento no nombra a esa empresa: espera al siguiente movimiento o pregunta a Chronodiali con tu número.

### ¿Cómo contactar a Chronodiali sobre mi paquete?

Llama al +212 5 20 61 62 63 o escribe a service-client@chronodiali.ma con tu número de seguimiento. Para un reembolso o nuevo envío, habla con la tienda.

:::sources
- [Chronodiali: Les experts de la livraison e-commerce](https://chronodiali.ma/) – Barid Al-Maghrib y Geopost, pago contra entrega en efectivo o tarjeta, entrega a domicilio o en agencia, más de 300 destinos, agencias y horarios, teléfono, correo
- [Chronodiali: Suivre mon colis](https://chronodiali.ma/suivi_colis.html) – rastreador, ejemplo `CHPU`, importe a pagar, líneas de estado en francés, cuáles muestra la página y «En cours de traitement.» (en el script de la página)
- [Chronodiali: Nos services](https://chronodiali.ma/nos_service.html) – tres intentos de entrega, notificaciones por SMS y correo, efectivo o tarjeta, prueba digital de entrega
- [Chronodiali: Points de livraison](https://chronodiali.ma/point_de_livraison.html) – entrega en todo Marruecos, agencias y horarios
- [Chronodiali: Envoyer un colis](https://chronodiali.ma/envoyercolis.html) – cuentas y recogidas de tiendas, pruebas de entrega para tiendas
- [Chronodiali: Contact](https://chronodiali.ma/contact_us.html) – formulario de contacto con «Réclamation ?» y sus campos, teléfono, correo
- [Chronodiali: Gouvernance](https://chronodiali.ma/aboutus.html) – Barid Al-Maghrib (Poste Maroc)
- [Chronodiali: Actualités](https://chronodiali.ma/actualites.html) – sociedad conjunta, 51 % Barid Al-Maghrib y 49 % Geopost
- [Chronodiali: Mention légale](https://chronodiali.ma/mentions_legal.html) – Chrono Diali S.A., Casablanca; condiciones: contrato y recogidas de la tienda, plazos al hacer el pedido
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos de número, líneas de estado y sus etapas, qué muestran la página de Chronodiali y Peek
:::
