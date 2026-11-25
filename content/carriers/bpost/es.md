---
title: Seguimiento bpost: código de barras, estados y recogida
description: Rastrea un paquete de bpost desde o hacia Bélgica: formatos del código de barras, estados en inglés de Track & Trace, plazos en taquillas bbox y contacto.
slug: bpost-seguimiento
published: 2026-11-17
updated: 2026-11-20
---

bpost es el operador postal de Bélgica y la marca del grupo bnode (antes bpostgroup desde diciembre de 2025) para el reparto de última milla en Bélgica y los Países Bajos. Sigue tu paquete en el [Track & Trace de bpost](https://track.bpost.cloud/), en la app My bpost o pegando el código arriba. El código de barras aparece en el correo de envío de la tienda, en el mensaje de bpost, en la etiqueta o en el aviso del buzón.

## Número de seguimiento de bpost: dónde está el código de barras

bpost llama código de barras (*barcode*) al número de seguimiento. Figura en la etiqueta de envío, el resguardo postal, el aviso de ausencia y el correo de confirmación. Las tiendas suelen enviarlo por correo electrónico, a menudo como enlace al Track & Trace. Sin él, bpost no puede localizar el envío: pídeselo al vendedor. Más pistas en [cómo localizar un paquete sin número de seguimiento](guide:find-tracking-number).

Formatos más habituales según bpost (ejemplos inventados):

| Formato | Ejemplo | Qué identifica |
| --- | --- | --- |
| 24 cifras que empiezan por `3232` o `3299` | `323248291057364918273649` | Código de barras de paquete de bpost |
| 18 cifras que empiezan por `3232` | `323212345000000001` | Código de barras de paquete de bpost |
| 23 o 27 caracteres con `JJBE` + letra | `JJBEA4829105736491827364918` | Matrícula nacional de bpost (paquete o certificado) |
| 2 letras (salvo `U`), 9 cifras y `BE` | `CE482910576BE` | Paquete postal internacional enviado desde Bélgica |
| `R` + letra, 9 cifras y `BE` | `RR482910576BE` | Certificado internacional desde Bélgica |
| 30 cifras que empiezan por `010` | `010482910573649182736491827364` | Certificado nacional dentro de Bélgica |

Un código que empieza por `U` (`UA…BE`) solo sirve para aduanas: no tiene Track & Trace, viaja como correo ordinario y llega en un máximo de 5 días laborables tras el despacho aduanero.

**Números parecidos.** Un número postal acabado en las letras de otro país pertenece a ese correo — por ejemplo, `…ES` es de [Correos](carrier:correos-spain). Si escribes una letra `O` en vez de cero entre las nueve cifras de un código `…BE`, Peek la lee como `0`; pero una cifra errónea que rompa el dígito de control S10 impide reconocerlo hasta corregirlo. Algunos números de 24 cifras son de [InPost](carrier:inpost), Ciblex o Austrian Post, y los de 18 cifras con `323200000` coinciden con [Ecoscooting](carrier:ecoscooting).

## Estados del Track & Trace de bpost explicados

El portal Track & Trace de bpost solo está en inglés, francés, neerlandés y alemán, y pide el código postal de destino en paquetes estándar. Peek consulta directamente a bpost sin código postal y muestra los escaneos originales en inglés:

| bpost escribe | Qué significa para ti |
| --- | --- |
| `Confirmation of preparation of the shipment received` | El remitente anunció el envío, pero bpost aún no tiene el paquete. |
| `Item accepted after deposit by sender` | Depositado por el remitente en un punto de bpost y admitido en red. |
| `Item is announced / Landmark Global (bpost) received the information` | Paquete internacional anunciado a [Landmark Global](carrier:landmark-global), la marca transfronteriza del grupo bnode. |
| `Landmark Global (bpost) has received the item` | Landmark Global ya tiene el paquete. |
| `Arrival at facilities` / `Arrival at international depot` | Llegada a una planta de origen o al centro internacional de bpost. |
| `Your item has been sorted` | Clasificado y rumbo a la siguiente etapa. |
| `Awaiting presentation to Customs` / `No extra costs` | Esperando control aduanero, o liberado sin cargos. |
| `Departure to country of destination` | Salió de Bélgica hacia el país de destino. |
| `Your shipment has arrived at the postal operator of the country of destination and will be delivered in the coming days` | El correo de destino (en España, [Correos](carrier:correos-spain)) ya tiene el paquete para entregarlo. |
| `The postman's delivery round for your item has started` | Hoy va en la ruta de reparto del cartero. |
| `Delivery interrupted due to unforeseen circumstances` | La ruta se detuvo del lado de bpost antes de llegar a tu domicilio; se fija otro día (`New delivery scheduled`). |
| `Item on it's way to bbox locker` | De camino a una taquilla bbox, aún no disponible para recoger. |
| `Item available at Pick-up point` / `Item available at the bbox locker` | Listo para recoger en un Punto de recogida o en una taquilla bbox. |
| `Item delivered` / `Item delivered in mailbox` / `Item delivered at safeplace` | Entregado en mano o en el punto, en el buzón o en el lugar seguro. |
| `Your parcel was delivered at one of your neighbours. You can find all delivery details in your mailbox.` | Entregado a un vecino; el aviso en tu buzón indica cuál. |

Si aparece `Item arrived at the distribution center - Item can't be assigned to a delivery round - missing information`, faltan datos de dirección; y `Returned to Landmark Global (bpost), Incorrect / illegible / incomplete address` indica devolución desde el extranjero por dirección incorrecta o incompleta. En Bélgica, el objetivo habitual tras recibir el paquete es el siguiente día laborable (95 % dentro de la franja orientativa); con acuerdo de la tienda, también hay reparto el sábado (8:00–17:00 en punto o taquilla) y el domingo (9:00–19:00 a domicilio).

## Reparto en Bélgica, Puntos de recogida y taquillas bbox

**Si no estás en casa.** Sin preferencias fijadas, el cartero prueba con un vecino o un lugar seguro. Si no puede, recoges el paquete en el Punto de recogida indicado desde el día laborable siguiente (desde las 11:00 si viene del extranjero). bpost avisa siempre por correo electrónico y solo deja papel si no puede contactarte digitalmente; revisa el punto en Track & Trace antes de acudir. No hay segundo pase el mismo día, salvo tras ausencia en sábado (reintento el siguiente día laborable).

**Plazos y recogida.** Son 14 días naturales en un Punto de recogida y 5 días naturales en una taquilla bbox sin prórroga (al sexto día vuelve al remitente), y no cabe trasladar un paquete ya llegado a otro punto. Cualquiera con el código de barras retira un paquete ordinario; un certificado (`010…` o `R…`) exige al destinatario con documento de identidad y código de barras, o mandato firmado en el aviso.

**Taquillas bbox y preferencias.** Las más de 2.500 taquillas bbox abren 24/7 sin cuenta: con pantalla usas el QR del correo o de la app My bpost (con código de 6 cifras en envíos directos); sin pantalla se abren solo desde la app con Bluetooth y ubicación. Los bultos demasiado grandes pasan a otro Punto de recogida, y los que caben en el buzón se depositan allí primero. En MyPreferences o en la app cambias vecino o lugar seguro hasta 5 minutos antes (salvo contra reembolso, código seguro, certificados `R` y, en lugar seguro, envíos con garantía o seguro), punto o taquilla hasta la medianoche anterior, o aplazas una vez 1 día laborable antes de la ruta matinal.

## Paquete parado, en aduanas o no recibido

**Seguimiento sin movimiento y reclamaciones.** Espera varios días laborables dentro de Bélgica, 5 días laborables hacia o desde países vecinos, o 10 días laborables hacia o desde España, el Reino Unido u otros países; después pide al remitente que contacte con bpost, ya que solo el remitente puede abrir investigación formal (mira [el seguimiento de mi paquete no se actualiza](guide:tracking-not-updating)). En compras a comerciantes de la UE, el plazo legal supletorio de entrega es de 30 días ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm)).

**Dañado, no recibido o devuelto.** Puedes rechazar en el acto un paquete dañado en un Punto de recogida; tras recibirlo, envía fotos al vendedor en 7 días (y también a bpost si llegó del extranjero a Bélgica; mira [paquete dañado](guide:damaged-parcel)). Si figura como entregado pero no está, revisa vecinos, buzón, la foto del lugar seguro en la app y el [formulario de envío no recibido de bpost](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1), además de [paquete entregado pero no recibido](guide:delivered-not-received). Si vuelve al remitente por dirección incompleta, rechazo o plazo vencido, pide reenvío o reembolso a la tienda ([paquete devuelto al remitente](guide:returned-to-sender)).

**Aduanas en Bélgica.** Abonas los cargos de importación en la app My bpost o en Track & Trace en un plazo de 14 días (una inspección física añade de 3 a 7 días). En pedidos fuera de la UE de menos de 150 € (sin envío), bpost aplica **3 € de arancel por tipo de producto** (desde el 1 de julio de 2026) más **2 € de tasa europea de tramitación por tipo de producto** (desde el 1 de noviembre de 2026) — dos camisetas idénticas pagan 3 € + 2 € = 5 €, una camiseta y un bañador pagan 6 € + 4 € = 10 € — más el IVA (normalmente del 21 %) y **21,50 €** de gastos administrativos de bpost si la tienda no recaudó IVA y derechos al comprar. Por encima de 150 €, pagas aranceles según producto, IVA, **40 €** de gestión y los **2 €** por tipo de producto. Los regalos entre particulares hasta 45 € están exentos (salvo alcohol, perfumes y tabaco). Si te piden el IVA por segunda vez por falta de datos IOSS, puedes rechazar en la app o pagar y reclamar el IVA duplicado a la tienda. Revisa [compras fuera de la UE en bpost](https://www.bpost.be/en/shopping-outside-eu) y la guía de [aduanas](guide:customs).

> Para envíos dentro de la UE, bpost nunca pide pagos en tránsito, nunca solicita datos personales por correo o SMS ni amenaza con un agente judicial. Cuando un paquete de fuera de la UE tiene cargos de importación pendientes, en cambio, bpost sí envía una petición de pago por correo electrónico, SMS, notificación en la app My bpost o carta: págala solo en la app My bpost, en Track & Trace o en una oficina de correos. Reenvía mensajes sospechosos a `abuse@bpost.be` y consulta [mensajes falsos de entrega](guide:delivery-scam-texts).

## Atención al cliente de bpost y mediación postal

- **Teléfono:** `02 278 51 26` en las páginas en inglés/neerlandés (`+32 2 278 51 26` desde fuera) y `02 278 51 27` en francés/alemán; lun.–vie. 08:00–12:30 y 13:00–18:00, sáb. 09:00–13:00 (tarifa zonal).
- **Chat y oficinas:** chat en páginas como Track & Trace (sin horario publicado) y atención presencial en Post Office, Post Point o Parcel Point.
- **Correo postal (sin sello en Bélgica):** `bpost, Box 5000, 1000 Brussels`.

bpost no publica correo de atención al cliente. Tras reclamar sin éxito ante bpost, remitentes y destinatarios pueden acudir gratis al [Defensor del Sector Postal belga](https://www.ombudsmanposte.be/en/) (`Boulevard Roi Albert II 8 box 4, 1000 Brussels`, `+32 2 221 02 21`).

## Seguir un paquete de bpost con Peek

[Peek](/) detecta bpost al momento («Transportista detectado») con un número válido de dos letras (salvo `U`), nueve cifras y `BE`, y con `JJBE` + letra + 18 o 22 cifras. Con 24 cifras que empiezan por `3232` o `3299`, pregunta a bpost, [InPost](carrier:inpost), Austrian Post y Ciblex («Comprobando servicios de seguimiento…») y elige bpost automáticamente cuando responde («bpost tiene este paquete.»). Con 18 cifras que empiezan por `3232`, consulta a bpost y también a [Ecoscooting](carrier:ecoscooting) si las cifras 6.ª a 9.ª son ceros (`323200000…`), pidiendo «Elegir» solo si ambos lo conocen. Los certificados belgas de 30 cifras (`010…`) se añaden eligiendo bpost con «Cambiar»; los códigos con `U` solo se prueban en [Chronopost](carrier:chronopost). Admite además enlaces de `bpost.be` o Track & Trace con `itemCodes=`. En envíos S10 entre Bélgica y España, Peek enlaza automáticamente bpost y [Correos](carrier:correos-spain) conservando el historial.

Peek consulta a bpost sin código postal y muestra los escaneos en inglés bajo «Lo que dice bpost» (con etapas como «En tránsito», «En aduanas», «En reparto», «Listo para recoger», «Incidencia» o «Devuelto al remitente»), localidad, peso, dimensiones, nombre y dirección del punto o taquilla (incluso tras recogerlo) y horas convertidas («Horas en tu zona horaria: …»). Comprueba hasta cada 10 minutos, hasta cada 2 minutos desde las 06:00 locales «En reparto» directo con bpost (15 minutos vía servicios), y cada hora tras dos días sin cambios o 12 horas en «Etiqueta creada» / «Admitido por el transportista». Si tras dos días con «Esperando a bpost» sigue desconocido, muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.»; tras cuatro días parado antes de reparto aparece «Pide ayuda a bpost». No muestra fecha estimada y descarta remitente, destinatario, direcciones personales, contactos, preferencias y justificante de entrega, con avisos en «Cada escaneo», «Pasos importantes» o «Solo la entrega».

## Preguntas frecuentes sobre el seguimiento de bpost

### ¿Cuánto tiempo guarda bpost un paquete para recogerlo?

14 días naturales en un Punto de recogida y 5 días naturales en una taquilla automática bbox, sin prórroga. Al sexto día en taquilla, o tras 14 días en punto, se devuelve al remitente.

### ¿Reparte bpost los sábados y los domingos?

Sí, cuando la tienda online tiene acuerdo de fin de semana con bpost: sábados de 8:00 a 17:00 en Punto de recogida o taquilla, y domingos de 9:00 a 19:00 a domicilio. Cartas y certificados solo circulan entre semana.

### ¿Quién puede recoger un certificado de bpost?

Solo el destinatario con documento de identidad y código de barras, o un autorizado con mandato firmado en el aviso de ausencia. Un paquete ordinario puede recogerlo cualquiera que lleve el código de barras.

### ¿Por qué no funciona mi código de barras de bpost?

Casi siempre el vendedor aún no entregó el paquete a bpost («Esperando el primer escaneo de bpost» en la mayoría de formatos compartidos). Los códigos con `U` no tienen Track & Trace, y un `…BE` mal copiado falla la validación S10.

:::sources
- [bpost: What does a barcode look like and where can I find it?](https://www.bpost.be/en/faq/what-does-barcode-look-and-where-can-i-find-it) – formatos y códigos U
- [bpost: Track & Trace, follow a parcel](https://www.bpost.be/en/faq/can-i-follow-my-parcel-online) – canales y correo extranjero
- [bpost: When can you follow a parcel with Track & Trace?](https://www.bpost.be/en/faq/when-can-i-follow-my-parcel-track-trace) – inicio del seguimiento
- [bpost: Parcel in transit but didn't receive a barcode](https://www.bpost.be/en/faq/i-am-expecting-parcel-have-not-received-barcode-what-do-i-do-now) – pedir código a la tienda
- [bpost: When will my parcel be delivered?](https://www.bpost.be/en/faq/when-will-my-parcel-be-delivered) – día laborable siguiente y franja del 95 %
- [bpost: Track & Trace](https://track.bpost.cloud/) – portal oficial
- [bpost: Track & Trace (en.json)](https://track.bpost.cloud/assets/i18n/en.json) – idiomas y textos en inglés
- [bpost: Not home, where and when can I pick it up?](https://www.bpost.be/en/faq/my-parcel-could-not-be-delivered-my-home-where-and-when-can-i-pick-it) – día laborable siguiente y aviso por correo
- [bpost: Missed parcel, can the postman pass again today?](https://www.bpost.be/en/faq/i-just-missed-my-parcel-can-postman-pass-again-today) – sin segundo pase el mismo día
- [bpost: Second pass after Saturday notice](https://www.bpost.be/en/faq/why-will-postman-be-passing-second-time-after-leaving-failed-delivery-notice) – reintento tras sábado
- [bpost: Pick-up point](https://www.bpost.be/en/receive-parcel/pick-up-point) – 14 y 5 días, certificados y sin traslado
- [bpost: Remove parcel from locker](https://www.bpost.be/en/faq/how-do-i-remove-parcel-parcel-locker) – QR, 6 cifras, Bluetooth y sexto día
- [bpost: Bbox](https://www.bpost.be/en/bbox) – +2.500 taquillas 24/7
- [bpost: Can I change the time of delivery?](https://www.bpost.be/en/faq/can-i-change-time-delivery) – aplazar 1 día laborable
- [bpost: Delivery preferences](https://www.bpost.be/en/mybpostapp-pref-parcel) – plazos y excepciones en MyPreferences
- [bpost: Receive a parcel from abroad](https://www.bpost.be/en/receive-parcel/abroad) – 3–7 días en aduanas, IOSS y recogida a las 11:00
- [bpost: Parcel stopped moving](https://www.bpost.be/en/faq/my-parcel-seems-be-stuck-somewhere-it-has-not-moved-while-track-trace) – 5 y 10 días laborables
- [bpost: File a complaint](https://www.bpost.be/en/faq/how-do-i-file-complaint-if-my-parcel-damaged-or-lost-or-hasnt-been-delivered) – reclama el remitente y 7 días por daños
- [bpost: Delivered, haven't received anything](https://www.bpost.be/en/faq/i-get-message-my-parcel-has-been-delivered-i-havent-received-anything-what-now) – comprobaciones y foto del lugar seguro
- [bpost: Formulaire colis pas reçu](https://www.bpost.be/fr/formulaires/colis/pas_recu/step1) – formulario online
- [bpost: Colis renvoyé à l'expéditeur](https://www.bpost.be/fr/faq/mon-colis-aurait-du-etre-livre-domicile-mais-ete-renvoye-lexpediteur-pourquoi-et-comment-puis-je) – causas de devolución
- [bpost: Customs](https://www.bpost.be/en/receive-parcel/customs) – 14 días para pagar y regalos hasta 45 €
- [bpost: Shopping outside the EU](https://www.bpost.be/en/shopping-outside-eu) – 3 € y 2 € por tipo de producto, IVA, 21,50 € y 40 €
- [bpost: Saturday and Sunday delivery](https://www.bpost.be/en/faq/which-mail-or-shipments-does-bpost-deliver-saturday-and-sunday) – horarios de fin de semana
- [bpost: Contact customer services](https://www.bpost.be/en/faq/how-do-i-contact-bposts-customer-services) – 02 278 51 26, horarios, chat y Box 5000
- [bpost: Contacter le service clientèle](https://www.bpost.be/fr/faq/comment-contacter-le-service-clientele-de-bpost) – 02 278 51 27
- [Ombudsman for the Postal Sector](https://www.ombudsmanposte.be/en/) – mediación gratuita
- [Ombudsman: Qui peut introduire une plainte ?](https://www.ombudsmanposte.be/fr/a-propos-de-nous/notre-fonctionnement/qui-peut-introduire-une-plainte/) – remitentes y destinatarios
- [bpost: Phishing](https://www.bpost.be/en/faq/i-received-suspicious-text-message-or-email-allegedly-bpost-what-should-i-do) – alertas en la UE y abuse@bpost.be
- [Your Europe: Envíos y entregas](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_es.htm) – plazo de 30 días en la UE
- [bnode: bpostgroup becomes bnode](https://press.bnode.com/bpostgroup-becomes-bnode) – grupo bnode y marcas
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reglas de seguimiento en Peek
:::
