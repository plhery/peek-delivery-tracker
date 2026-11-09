---
title: Estados de un envío en Correos, GLS y SEUR: qué significan
description: «Alta en la unidad de reparto» no quiere decir que vaya en la furgoneta. Qué significan «En reparto», «En tránsito» y los demás estados de Correos, GLS y SEUR.
slug: estados-de-envio-significado
picture: Una ruta con curvas y paradas ya completadas (etiqueta, almacén, avión), una furgoneta en la parada actual, una casa más adelante y Pip corriendo.
published: 2026-10-09
updated: 2026-11-05
---

Busca aquí el estado que ves en el seguimiento de Correos, SEUR, GLS, MRW, NACEX o CTT Express para saber qué significa y si tienes que hacer algo. Un estado es el último escaneo del transportista resumido en pocas palabras, y casi ninguno te pide nada. «Pre-admisión» o «Etiqueta creada» quieren decir que el transportista aún no tiene tu paquete; «En tránsito», que está en algún punto entre dos escaneos, y «En reparto», que va hoy con el repartidor. Solo unos pocos te necesitan: un intento de entrega fallido, un paquete que te espera en una oficina o un punto de recogida, un pago de aduanas y un «Entregado» cuando a ti no te ha llegado nada.

## ¿Cuáles son las fases del seguimiento de un paquete?

Todos los paquetes pasan por las mismas paradas, y los que vienen del extranjero, por tres más.

:::journey
- label | Etiqueta creada | El transportista tiene los datos, no el paquete.
- warehouse | En tránsito | Recogido y de centro en centro, sin escaneos en la carretera.
- plane | Sale del país | Solo del extranjero. En el aire no hay escaneos.
- customs | En aduanas | Solo de fuera de la UE o con Canarias, Ceuta y Melilla. A veces hay que pagar.
- handover | Transportista local | Solo del extranjero. Sus escaneos pueden salir solo en su web.
- truck | En reparto | En la furgoneta de hoy.
- locker | Listo para recoger | En una oficina, una tienda o un locker.
- home | Entregado | En tus manos, o donde el transportista lo da por entregado.
:::

## ¿Qué significan los estados de Correos, GLS, MRW, SEUR y NACEX?

Las mismas fases, con otras palabras:

| En Peek | Correos | GLS | MRW |
| --- | --- | --- | --- |
| Etiqueta creada | `Pre-admisión` | `Etiqueta creada` | `Pendiente de recoger` |
| Admitido por el transportista | `Admitido` | `Envío recogido por GLS` | `Envío recogido en origen` |
| En tránsito | `En tránsito`, `Clasificado`, `Alta en la unidad de reparto` | `En tránsito` | `En tránsito`, `Pendiente de recibir en destino` |
| En reparto | `En reparto` | `En reparto` | `Envío en reparto` |
| Listo para recoger | `A disposición del destinatario` | `Depositado en Parcel Shop` | `Depositado en punto MRW` |
| Entregado | `Entregado` | `Entregado` | `Envío entregado` |

[Correos](carrier:correos-spain) agrupa sus estados en cuatro fases: `Pre-admisión`, `En Camino`, `En entrega` y `Entregado`. [SEUR](carrier:seur) escribe frases enteras en mayúsculas: `EL ENVÍO HA SIDO REGISTRADO.` cuando solo tiene los datos, `EL ENVÍO ESTÁ EN REPARTO.` el día de la entrega y `EL ENVÍO ESTÁ ENTREGADO.` al final.

En [MRW](carrier:mrw), `Pendiente de recoger en Franquicia destino` no es lo mismo que `Pendiente de recoger`: se ha acordado que lo recojas tú en una oficina MRW. En [NACEX](carrier:nacex), `Solucionado sin OK` cierra una incidencia sin entregarte nada: pregunta a NACEX o a la tienda qué ha pasado.

## «Pre-admisión» o «etiqueta creada»: ¿lo tiene ya el transportista?

No: la tienda ha impreso una etiqueta y ha mandado los datos, nada más. Correos explica que `Pre-admisión` se genera cuando se prerregistra el envío: ya existe el número, pero el paquete está «pendiente de ser entregado a Correos». [GLS](carrier:gls-es) dice lo mismo de `Etiqueta creada`: tiene el prerregistro, pero el paquete todavía no está en su red ni en ninguna de sus instalaciones. Lo mismo vale para `Pendiente de recoger` en MRW y `Pendiente de recepción en CTT Express` en [CTT Express](carrier:ctt-express).

También ocurre al revés: si a Correos le llega un paquete sin prerregistro, lo deja «estacionado» y lo entrega cuando la empresa que lo envía le pasa los datos.

Si lleva varios días sin cambiar, pregunta a la tienda, no al transportista. GLS no puede darte plazos hasta tener el paquete físicamente, y te dice que hables con el remitente.

### ¿Y si el número no muestra nada?

Comprueba que está completo y que es del transportista que indica la tienda: cada uno tiene [su propio formato](guide:tracking-number-formats). Si todo cuadra, suele ser un envío nuevo, aún sin escaneos: mira [por qué no sale nada con tu número](guide:find-tracking-number).

## ¿Qué significa «en tránsito»?

Que el transportista tiene tu paquete en algún punto de su red. No quiere decir que se esté moviendo ahora mismo, ni que ya vaya con el repartidor: para eso está «en reparto». En SEUR, el estado que empieza por `EL ENVÍO ESTÁ EN TRÁNSITO.` indica que el paquete circula por su red y aún no ha llegado a la zona de destino. En Correos, `En tránsito` aparece al salir del centro logístico de origen y otra vez cuando va hacia la unidad que lo entregará; entre medias, `Clasificado` quiere decir que lo han clasificado y asignado a un transporte.

Entre un centro y otro puede haber algún rodeo: GLS explica que usa varios centros de distribución, así que la ruta no siempre es directa.

### ¿Cuánto tarda un paquete en tránsito?

GLS suele entregar los envíos nacionales en 24 horas laborables desde que tiene el paquete en su red (sin contar las islas), y los de Europa, en 24 a 96 horas. InPost intenta entregar en un plazo medio de 3 días laborables desde que el paquete entra en un Punto Pack o un Locker en España. ¿Lleva más tiempo parado del que da el transportista? Mira [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).

## Paquetes del extranjero: salida del país, aduanas y transportista local

- **Sale del país.** Lo cargan en un avión, un barco o un tren. DHL explica que viaja dentro de un contenedor y que los paquetes se vuelven a escanear uno a uno ya en el país de destino. En el seguimiento de Correos verás `En oficina de cambio` antes de que salga y `Llegada a oficina de cambio` en el país de destino.
- **Aduanas.** Según Correos, pasa por aduanas todo lo que llega a la Península y Baleares desde fuera de la UE o desde Canarias, Ceuta y Melilla, y todo lo que entra en esos tres territorios. En su seguimiento verás `Inicio de tramitación aduanera` o `Pendiente de tramitación aduanera` (en MRW, `Retenido en aduana`), y `Finalizada tramitación aduanera` al terminar. Casi siempre es un trámite: DHL, en su web alemana, habla de varios días laborables según la documentación, y ningún transportista da un máximo general. Desde el 1 de julio de 2026, una compra de hasta 150 € que llega de fuera de la UE a la Península, Baleares o Canarias paga un arancel de 3 € por cada tipo de producto, aunque la tienda ya te haya cobrado el IVA. Correos lo muestra como `Arancel UE`, con su importe, y no entrega el paquete sin cobrarlo, salvo que el vendedor lo pagara por adelantado. Desde el 1 de noviembre de 2026 se suma una tasa de tramitación de 2 € por tipo de producto, que se paga junto con el arancel. Paga solo lo que te pidan: entrando tú en la App de Correos o en la web Mi Oficina hasta que empiece la entrega, o después al cartero o en la oficina. El resto está en [paquetes retenidos en aduanas](guide:customs).
- **Transportista local.** Cuando otra empresa hace el último tramo, DHL te remite al seguimiento del transportista del país de destino. ¿Has pedido en AliExpress, Temu o Shein? Mira [cómo seguir un paquete que viene de China](guide:tracking-from-china). Si su seguimiento sale en inglés, las páginas de [Cainiao](carrier:cainiao) y [YunExpress](carrier:yunexpress) explican qué significan sus estados.

## ¿«En reparto» significa que llega hoy?

Normalmente, sí:

- **Correos:** `En reparto` quiere decir que el repartidor ya está haciendo su ruta. Si te llega un SMS de entrega en domicilio, Correos cuenta con entregarlo entre 24 y 72 horas después, según el producto.
- **GLS:** reparte de lunes a viernes, más o menos de 9 a 20 h; los sábados, antes de mediodía, solo con Express Saturday. Con `En reparto`, su seguimiento muestra una franja estimada, y cuando quedan menos de 10 paradas se activa el seguimiento en tiempo real.

Ojo con dos estados que despistan. En una entrega a domicilio, `Alta en la unidad de reparto` significa que el paquete ha llegado a la unidad de Correos que lo entregará «en cuanto sea posible», no que ya vaya en la furgoneta. Y en CTT Express, `Aceptado por la última milla` quiere decir que ya lo tiene la delegación que lo entregará, aunque quizá aún sin ruta asignada.

[Peek](/) consulta el seguimiento hasta una vez cada 2 minutos cuando un paquete está en reparto (y hasta una vez cada 10 minutos el resto del tiempo), y puedes dejar sus notificaciones en «Solo la entrega». Lo de cada 2 minutos vale solo el día del reparto, desde las 6:00, hora local, o, si la empresa da una franja, desde media hora antes de que empiece, y mientras responda el propio seguimiento de la empresa; a través de servicios de seguimiento como 17TRACK, es cada 15 minutos, y algunas empresas fijan un intervalo más largo (cada hora, GLS en Alemania, Austria, Suiza, Francia y Polonia, DHL eCommerce en Polonia, Royal Mail y Morning Global; cada 30 minutos, Poste Italiane, PostNL, USPS, GOFO y Cainiao). Si otra empresa toma el relevo para la entrega, Peek sigue el ritmo de la que reparte el paquete.

## «Intento de entrega» o «ausente»: ¿y ahora qué?

Este sí te necesita: [no había nadie para recibir el paquete](guide:missed-delivery) o el repartidor no pudo llegar a la puerta. En Correos aparece como `Realizado intento de entrega`, normalmente después de un primer intento que no se completó por ausencia, un problema con la dirección u otro inconveniente. En MRW es `Destinatario ausente o cerrado`. La «Incidencia de destinatario no contesta/ausente» de CTT Express puede salir aunque estuvieras en casa; vuelven a intentarlo el siguiente día laborable.

:::steps
- Lee el aviso | Papel en el buzón, SMS o email: dónde está el paquete y qué número dar.
- Mira el plazo | De 5 a 15 días según el transportista, y cada uno los cuenta desde un momento distinto.
- Cambia la entrega | En la web o la app del propio transportista, si lo permite: otro día o un punto de recogida.
- Recoge con tu DNI | Lleva el DNI y el aviso o el código. En Correos puede ir otra persona con tu autorización, su DNI y una fotocopia del tuyo.
:::

| Transportista | Tras un intento fallido | Tiempo para recogerlo |
| --- | --- | --- |
| Correos | Aviso de llegada en el buzón, o SMS o email. Si el envío incluye dos intentos, el segundo es en 24 horas; si no hay segundo o también falla, va a una oficina | 15 días en la oficina si te avisa por SMS, desde el día siguiente al mensaje; con aviso en papel, el plazo que indica el aviso. 5 días naturales en un Citypaq |
| GLS | Con EconomyParcel, un solo intento y luego al Parcel Shop más cercano; según el servicio, un segundo intento | Si el segundo falla, se queda en la agencia y vuelve al remitente 7 días naturales después del primer intento |
| InPost | A domicilio, su agencia de reparto se pone en contacto contigo para concertar una nueva cita | 5 días naturales en Locker y Punto Pack (envíos nacionales) |

> Correos nunca pide datos personales o bancarios ni pagos por SMS o correo electrónico, y avisa de que los engaños que más usan su nombre te piden un pago o que completes la dirección de entrega para recibir un paquete. Cambia la entrega o paga solo en la web o la app del transportista, entrando tú, nunca desde el enlace de un mensaje.

## «A disposición del destinatario» o «listo para recoger»: ¿dónde está?

En una oficina, una tienda o un locker, [hasta que acabe el plazo](guide:uncollected-parcel) de la tabla de arriba. Correos usa `A disposición del destinatario` tanto para la oficina como para la taquilla Citypaq; GLS, `Depositado en Parcel Shop`; NACEX, `Disponible en punto NACEX`, y CTT Express, `Disponible en Punto Collectt Express`. InPost te manda un email o un SMS cuando el paquete llega al Punto Pack o al Locker.

Espera a ese estado o al aviso antes de ir: en Correos, el envío no está en la oficina hasta el día siguiente al SMS, o hasta el siguiente día laborable si te dejaron el aviso en papel. En un Punto Pack de InPost puedes enseñar el DNI, el pasaporte o el carnet de conducir.

## ¿«Entregado» significa que lo tienes tú?

No siempre. Significa que el repartidor lo ha escaneado como entregado:

- **A otra persona.** A menudo, a un vecino: es una de las prácticas más habituales cuando no estás en casa.
- **En un lugar seguro.** En la puerta o en el jardín, por ejemplo, si lo autorizaste en las instrucciones de entrega.
- **En una tienda.** Si elegiste recogerlo en un Parcel Shop de GLS, GLS te dice que vayas cuando el seguimiento marque `Depositado en Parcel Shop` o `Entregado`: ahí, «entregado» quiere decir que está en la tienda, no en tus manos. Lleva un documento oficial.

¿No está? Revisa el historial completo por si da un nombre o un lugar, y después mira [paquete entregado pero no recibido](guide:delivered-not-received).

## ¿Una incidencia significa que se ha perdido?

No. Correos llama «envío estacionado» al que tiene una incidencia que impide entregarlo: un paquete roto o dañado, datos del destinatario insuficientes, una dirección incorrecta o desconocida, que nadie se haga cargo o que falte el prerregistro. Lo guarda 5 días naturales a la espera de que se corrijan los datos, y puedes corregirlos tú en «Gestión de estacionados» con el [número de envío](guide:find-tracking-number) y el PIN que te manda por SMS o email. Si no tienes PIN, habla con la empresa que te lo envía: ella se encarga del trámite.

En SEUR, el estado que empieza por `EL ENVÍO ESTÁ PENDIENTE DE LLEGAR A DESTINO.` avisa de una demora, no de una pérdida: el paquete aún no ha llegado a la instalación de destino.

Eso sí, fíjate bien si el motivo es la dirección o un pago. En Correos, corrige un estacionado dentro de esos 5 días naturales; si te lo envía un particular, es él quien tiene que llamar a Atención al Cliente antes de que pasen.

## ¿Por qué me han devuelto el paquete al remitente?

Los motivos que dan los transportistas: no lo recogiste a tiempo, la dirección estaba mal o incompleta, lo rehusaste, no se pagaron los gastos de aduanas o llegó dañado. Así lo cuenta cada uno:

- **Correos:** `Finalizado plazo retirada` no dice «devuelto», pero lo es: se acabó el plazo para recogerlo, y Correos lo devuelve al remitente. Y `Entregado al remitente` no es una entrega a ti: ya ha vuelto a quien lo envió.
- **GLS:** si el segundo intento falla, el paquete se queda en la agencia y vuelve 7 días naturales después del primer intento.
- **InPost:** pasados los 5 días en el Locker o el Punto Pack, vuelve al remitente (tienda online, vendedor o particular).

Si ya [va de vuelta](guide:returned-to-sender), habla con la tienda: pídele que te lo vuelva a enviar o que te devuelva el dinero.

:::sources
- [Correos: Sigue tu paquete con el Localizador de Envíos](https://www.correos.es/es/es/actualidad/2023/sigue-tu-paquete-en-tiempo-real-con-nuestra-herramienta-de-local) – estados y fases
- [Correos: Aviso de llegada y SMS](https://www.correos.es/es/es/atencion-al-cliente/recibir/aviso-de-llegada) – segundo intento, 24 a 72 horas, 15 días, DNI y autorización, devolución al acabar el plazo
- [Correos: Oficina o Citypaq](https://www.correos.es/es/es/actualidad/2026/oficina-o-citypaq--elige-el-metodo-de-recogida-que-mejor-se-adap) – 5 días naturales en Citypaq
- [Correos: Envíos estacionados](https://www.correos.es/es/es/atencion-al-cliente/recibir/envios-estacionados) – incidencias, prerregistro, 5 días naturales, Gestión de estacionados
- [Correos: Información aduanera](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera) – qué pasa por aduanas
- [Correos: Arancel para envíos de bajo valor en la UE](https://www.correos.es/es/es/atencion-al-cliente/informacion-aduanera/arancel-para-envios-de-bajo-valor-en-la-ue) – 3 € por tipo de producto, Arancel UE, IVA ya pagado, pago anticipado, formas de pago
- [Agencia Tributaria: Envíos de hasta 150 euros](https://sede.agenciatributaria.gob.es/Sede/aduanas/comercio-electronico-pipe-envios-particulares/compras-internet-envios-particulares/envios-valor-hasta-150-euros.html) – tasa de 2 €
- [Correos: Qué es el phishing](https://www.correos.es/es/es/atencion-al-cliente/seguridad-de-la-informacion/phishing) – nunca pide datos ni pagos por SMS o email
- [GLS: Preguntas frecuentes para recibir paquetes](https://gls-group.com/ES/es/faq/recibir-paquetes/) – estados, plazos, horario, intentos, 7 días, Parcel Shop
- [Universal Parcel Scraper: SEUR](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/seur) – estados de SEUR que lee Peek: registrado, en tránsito, pendiente de llegar a destino, en reparto, entregado
- [MRW: Conoce el estado de tu envío](https://blog.mrw.es/conoce-el-estado-de-tu-envio/) – estados, aduana, ausente, Franquicia destino
- [CTT Express: FAQs sobre los estados de envío](https://www.cttexpress.com/preguntas-frecuentes/estados-envio) – ausencia, nuevo intento
- [CTT Express: Estados de envío](https://www.cttexpress.com/blog/logistica/que-significa-aceptado-ultima-milla) – pendiente de recepción, última milla
- [InPost: Atención al cliente, contacto y ayuda](https://www.inpost.es/contacta-con-nosotros) – 3 días laborables, 5 días naturales, avisos, documentos, ausencia
- [DHL: International shipment status](https://www.dhl.de/en/privatkunden/hilfe-kundenservice/themen/international/sendungsverfolgung/was-bedeutet-mein-sendungsstatus.html) – contenedores, aduana, seguimiento del transportista de destino
- [Genbeta: Que entreguen tu paquete a un vecino puede ser ilegal](https://www.genbeta.com/a-fondo/que-entreguen-tu-paquete-a-vecino-puede-ser-ilegal-como-hacerlo-sea-legal) – entrega a un vecino, instrucciones para dejarlo en la puerta o el jardín
:::
