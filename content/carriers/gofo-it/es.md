---
title: Seguimiento GOFO Italy: códigos GFIT, estados y reclamos
description: Sigue un paquete de GOFO en Italia: cómo son los números GFIT y CIIT, qué significan sus estados en italiano, reclamación ante AGCOM y Peek.
slug: seguimiento-gofo-italy
published: 2026-11-21
updated: 2026-11-21
---

GOFO Italy es la red italiana de GOFO, un transportista de paquetería presente en varios países, que entrega paquetes dentro de Italia. La lista de transportistas de Peek también lo recoge como CIRRO Parcel Italy. Sus códigos de seguimiento empiezan por `GFIT` o `CIIT`: consulta tu envío en la página italiana de seguimiento de GOFO, en gofo.com, o pega el código en la casilla de arriba. ¿Aún no tienes el código? Mira [cómo encontrar tu número de seguimiento](guide:find-tracking-number).

## Número de seguimiento GOFO Italy: cómo es

Estas son las formas que Peek asigna a GOFO Italy, en mayúsculas o minúsculas:

| Formato | Ejemplo inventado |
| --- | --- |
| `GFIT` y 13 cifras | `GFIT4829105736284` |
| `GFIT` y 14 cifras (la última es un dígito de control) | `GFIT48291057362848` |
| `CIIT` y 13 cifras | `CIIT4829105736284` |
| `CIIT` y 14 cifras | `CIIT48291057362841` |

Las demás redes de GOFO siguen el mismo esquema con el código de su país detrás de `GF` o `CI`. Los códigos `GFFR` y `CIFR` pertenecen a [GOFO France](carrier:gofo-fr), `GFNL` a GOFO Países Bajos y `GFES` a GOFO España. Los `GFUS` de 14 cifras, `GF` de 13 o `CR` de 12 pertenecen a [GOFO Express](carrier:gofo) en Estados Unidos, una red distinta, y `GFCA` con 14 cifras a GOFO Canada. Un código que empiece por `GFIT` con 12 o 15 cifras, o uno de 14 cifras con el dígito de control mal escrito, no será asignado a GOFO Italy: comprueba que no haya una errata. Más detalles en [formatos de números de seguimiento](guide:tracking-number-formats).

## Estados de seguimiento GOFO Italy: qué significan en italiano

Peek lee el seguimiento italiano de GOFO, por lo que los escaneos aparecen con los textos italianos originales sea cual sea tu idioma. Los más habituales, en orden de aparición:

| Qué dice GOFO | Qué significa para ti |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | El remitente está preparando el paquete y pronto lo entregará a GOFO. |
| `Registrato presso il centro di trasferimento` | Registrado en un centro de transferencia de GOFO. |
| `Il pacco ha lasciato il centro di smistamento` | Ha salido de un centro de clasificación de GOFO. |
| `Arrivato alla struttura di destinazione regionale GOFO` | Ha llegado a la instalación regional de GOFO de tu zona. |
| `Preparazione alla consegna` | En preparación en el centro antes de que salga el repartidor. |
| `Il corriere è fuori per la consegna` | En reparto con el mensajero. |
| `Consegna fallita, tipo:Destinatario assente` | No entregado: no había nadie. |
| `Consegna fallita, tipo:L'indirizzo è errato` | No entregado: la dirección es incorrecta. |
| `Il pacco è stato restituito al centro di smistamento` | Devuelto al centro de clasificación de GOFO tras una entrega fallida. |
| `Consegnato` | Entregado. |

Dos mensajes suelen confundir. Mientras no aparezca otro escaneo tras el primero, GOFO aún no tiene el paquete y Peek lo muestra como «Etiqueta creada». Además, `Il pacco è stato restituito al centro di smistamento` no significa que vuelva a la tienda: ha regresado al centro de clasificación de GOFO y Peek lo muestra como «Incidencia». Una entrega aparece simplemente como `Consegnato` en todos los idiomas: la línea de GOFO puede incluir detalles adicionales sobre el destinatario que Peek descarta.

El seguimiento de GOFO clasifica también los paquetes en categorías amplias. `Avviso` indica un problema señalado por GOFO, que Peek muestra como «Incidencia»; por sí solo no implica que hubiera intento de entrega. `Restituito` indica un paquete en devolución al remitente («Devuelto al remitente»).

Si [YunExpress](carrier:yunexpress) entregó tu paquete a GOFO Italy, el seguimiento de YunExpress puede retransmitir escaneos de GOFO en inglés: `Preparing for delivery` en el centro antes de salir a reparto y `Package departed from sorting center` junto al italiano `Il pacco ha lasciato il centro di smistamento`.

## Paquete GOFO retrasado, fallido, perdido o dañado

**Sin movimiento.** Peek sigue preguntando a GOFO. Si pasan cuatro días sin nuevos escaneos antes de salir a reparto, Peek lo avisa («Sin novedades desde hace cuatro días») y enlaza a la página de ayuda de GOFO Italy («Pide ayuda a GOFO Italy»). Más en [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

**Entrega fallida.** Tras cualquier línea `Consegna fallita` bajo el código de alerta de GOFO —sea `Destinatario assente`, `L'indirizzo è errato` u otro motivo tras `tipo:`— Peek muestra «Intento de entrega». Lo que ocurra después depende de GOFO: consulta [entrega fallida](guide:missed-delivery) para ver qué revisar.

**Retrasado.** Según la normativa europea de consumo, la tienda debe entregar en 30 días salvo acuerdo distinto. Si no cumple, concédele un plazo adicional razonable; si también vence sin entrega, puedes cancelar el pedido y pedir el reembolso íntegro.

**Marca `Consegnato` pero no ha llegado.** La tienda responde de la entrega: le corresponde contactar con el transportista y demostrar que se entregó; si no puede, debe reenviar el artículo o reembolsarte. Qué comprobar primero cerca de casa: [entregado pero no recibido](guide:delivered-not-received).

**Dañado.** La tienda responde de los daños desde el envío hasta la recepción, así que revisa la caja delante del repartidor siempre que puedas. Si el embalaje presenta daños visibles, la autoridad postal italiana AGCOM indica que puedes rechazarlo o aceptarlo con reserva. Más en [paquete dañado](guide:damaged-parcel).

## Cómo reclamar por una entrega de GOFO Italy

Empieza por la tienda que envió el paquete, pues responde de la entrega. Para reclamar contra el propio transportista, la autoridad italiana de comunicaciones AGCOM establece una vía para los operadores postales (los paquetes de hasta 31,5 kg cuentan como envíos postales):

1. Envía una reclamación por escrito al operador mediante el formulario de su web o uno solicitado por correo electrónico. Debe responder en un plazo de 45 días.
2. Si la respuesta no te convence o no llega en 45 días, solicita conciliación al operador, que concluye en un plazo máximo de 60 días.
3. Si tampoco prospera, dispones de 90 días para pedir a AGCOM la resolución de la disputa mediante el Formulario CP. AGCOM decide en un plazo de 150 días y su decisión vincula a ambas partes.

AGCOM puede ordenar reembolsos o indemnizaciones reglamentarias, pero no daños y perjuicios; para eso, y en cualquier momento, puedes acudir a los tribunales. Si el operador no responde a tu reclamación o solicitud de conciliación, denúncialo en el Portale delle segnalazioni de AGCOM. El centro de contacto de AGCOM atiende de lunes a viernes, de 10:00 a 14:00, en el `800.185060` desde teléfono fijo italiano o en el `081.750750` desde móvil o desde el extranjero.

## Atención al cliente de GOFO Italy

La página de ayuda de GOFO Italy está en su web italiana, gofo.com, y Peek la enlaza como «Pide ayuda a GOFO Italy» cuando pasan cuatro días sin nuevos escaneos antes de salir a reparto. La sociedad detrás del servicio, GOFO Italia s.r.l., tiene su domicilio social en Via Juan Manuel Fangio 11, 20045 Lainate (MI). Ten a mano tu código `GFIT` o `CIIT` y el número de pedido. AGCOM recuerda además que todo operador postal debe ofrecer canales de comunicación gratuitos a los usuarios.

## Seguir un paquete de GOFO Italy con Peek

[Peek](/) reconoce por sí solo los números `GFIT` y `CIIT` (verificando el dígito de control en los `GFIT` de 14 cifras). También puedes pegar un enlace de la página italiana de seguimiento de GOFO (en gofo.com o gofoexpress.it, con `id=` y el código).

Mientras tienes el paquete abierto en pantalla o el día de la entrega con las notificaciones activadas, y mientras responde el seguimiento de GOFO, Peek consulta hasta cada 10 minutos, y hasta cada 2 minutos desde las 6:00 (hora italiana) el día en que GOFO lo escanea en reparto; el resto del tiempo, cada hora. A través de servicios de seguimiento comprueba cada hora, y hasta cada 15 minutos el día de reparto mientras lo tienes abierto o con notificaciones activadas. Tras dos días sin novedades (12 horas mientras sigue en «Etiqueta creada» o «Admitido por el transportista»), Peek consulta cada hora.

Peek muestra el estado, el historial con el lugar que GOFO indica para cada escaneo, la hora de entrega y esos lugares en un mapa. Si GOFO solo da un código de depósito o el país, el escaneo aparece sin lugar. Las horas se leen en horario italiano y se muestran en tu zona horaria; en peektracker.com y en las apps para iPhone y Android, una línea al final del historial lo recuerda («Horas en tu zona horaria: …»). Puedes recibir notificaciones de «Cada escaneo», «Pasos importantes» o «Solo la entrega». Hasta que GOFO publique un primer escaneo, Peek muestra «Esperando a GOFO Italy» y sigue consultando. Si tras dos días GOFO sigue sin reconocer el número y ninguna otra fuente ha respondido, Peek muestra «Tras dos días, el transportista sigue sin reconocer este número. Revísalo, elige otro transportista o elimina el paquete.» y pasa a comprobarlo a diario, y una vez a la semana pasada una semana desde que lo añadiste. Si GOFO registra el envío sin haberlo escaneado aún, Peek sigue consultando con «Esperando a GOFO Italy» sin pedirte revisar el número. Peek muestra la fecha de entrega prevista por GOFO hasta que el paquete se entrega, se devuelve o queda listo para recoger; no muestra el peso y descarta las fotos de entrega y los avisos de GOFO. No puede contactar con GOFO ni con la tienda por ti.

## Preguntas sobre el seguimiento de GOFO Italy

### ¿Dónde está mi paquete de GOFO?

Lo indica la última línea del seguimiento. `Arrivato alla struttura di destinazione regionale GOFO` significa que está en la instalación regional de tu zona, `Preparazione alla consegna` que se prepara para el repartidor e `Il corriere è fuori per la consegna` que va con el mensajero. Si solo figura la línea de preparación del remitente, todavía no se ha entregado a GOFO.

### ¿Es fiable GOFO Italy?

La sociedad italiana de GOFO es GOFO Italia s.r.l., con domicilio social en Lainate (provincia de Milán). La autoridad postal AGCOM recoge en su Determina n. 12/26/DSP del 2 de septiembre de 2026 que el 17 de junio de 2026 le notificó tres presuntas infracciones: ejercer actividad postal en servicios incluidos en el servicio universal sin la licencia individual preceptiva, sin Carta dei servizi y sin procedimientos de resolución de disputas para usuarios. Según la resolución, la empresa admitió los cargos sobre la Carta dei servizi y los procedimientos de disputas y pagó la sanción reducida correspondiente, cerrando AGCOM esa parte del expediente; la empresa impugnó el cargo relativo a la autorización, que continuaría por la vía ordinaria.

### ¿GOFO y CIRRO Parcel son lo mismo?

En Italia, a efectos de seguimiento, sí: la lista de transportistas de Peek también incluye GOFO Italy como CIRRO Parcel Italy. Un rastreo que solo diga «Cirro» puede referirse a la red italiana, francesa o neerlandesa de GOFO, o a la línea transfronteriza de Cirro que entrega los paquetes a correos nacionales con sus propios códigos.

### ¿Qué hago si mi paquete de GOFO se ha perdido?

Contacta con la tienda. Con arreglo a las normas europeas responde de la entrega hasta que lo recibas: si no puede demostrar la entrega, debe volver a enviarlo o devolverte el dinero. Si la responsabilidad es del transportista, se aplica la vía de reclamación ante AGCOM explicada arriba. Más en [paquete perdido](guide:lost-parcel).

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., domicilio en Lainate, expediente AGCOM de 2026 y sanción abonada en importe reducido (2 de septiembre de 2026)
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – reclamación 45 días, conciliación 60 días, Formulario CP en 90 días, decisión en 150 días, paquetes hasta 31,5 kg, daños, canales gratuitos, centro de contacto
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – entrega en 30 días, plazo adicional, responsabilidad por daños hasta la recepción
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – la tienda prueba la entrega, comprobación en la entrega
- [Universal Parcel Scraper: GOFO Italy](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-it) – formatos `GFIT`/`CIIT` con dígito de control `gofo-europe`, enlaces, textos italianos y categorías, `Consegna fallita` bajo código `206`, CIRRO Parcel Italy, campos mostrados y descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – otras redes de GOFO, retransmisiones en inglés de YunExpress, fin de la fecha prevista (`GOFO_ENDED_STAGES`)
:::
