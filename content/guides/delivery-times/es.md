---
title: ¿A qué hora reparte Correos? Sábados y otras empresas
description: Correos reparte paquetes de lunes a viernes, sin horario publicado. A qué hora reparten GLS, MRW, NACEX y Amazon, y quién entrega los sábados y los domingos.
slug: a-que-hora-reparte-correos
picture: Un reloj que marca las ocho de la tarde bajo una luna creciente, un calendario con los fines de semana marcados, una furgoneta con los faros encendidos y Pip mirando desde una ventana.
published: 2026-10-14
updated: 2026-10-28
---

Un paquete puede llegarte desde primera hora de la mañana hasta bien entrada la tarde, y casi ninguna empresa te promete una hora. Correos no publica horario de reparto, GLS reparte más o menos de 9:00 a 20:00, CTT Express en general de 9:00 a 19:00, y Amazon, con «Entrega hoy», hasta las 22:00. La mayoría reparte de lunes a viernes: el sábado depende casi siempre del servicio que pagó quien envía, y el domingo solo lo anuncia Amazon, en determinados códigos postales.

## ¿A qué hora reparte Correos y hasta qué hora reparten los demás?

| Empresa | De lunes a viernes | Sábado | Domingo |
| --- | --- | --- | --- |
| [Correos](carrier:correos-spain) | No publica horario. Con Paq Premium, quien envía puede elegir franja, la última de 18:00 a 21:00 | Paq Premium y Paq Estándar no lo ofrecen | No lo ofrece |
| [Correos Express](carrier:correos-express) | No publica horario; el seguimiento te da una hora estimada | Opción «Entrega en sábados» de Paq 24, en la Península, si la elige quien envía | No lo ofrece |
| [GLS](carrier:gls-es) | «De 9 a 20h aproximadamente» | Antes de mediodía, solo con Express Saturday | No lo menciona |
| [MRW](carrier:mrw) | Envíos contratados en su web, en la Península: antes de las 19:00 del día laborable siguiente | Sus páginas no lo dicen | Sus páginas no lo dicen |
| [NACEX](carrier:nacex) | Con NACEX 19:00 H, antes de las 19:00 del día laborable siguiente | De 9:00 a 13:00, si quien envía contrata la Entrega en sábado | No lo ofrece |
| [CTT Express](carrier:ctt-express) | En general, de 9:00 a 19:00 | Solo en la temporada navideña | No lo ofrece |
| [Amazon](carrier:amazon-logistics) | Con «Entrega hoy», antes de las 22:00 en algunas ciudades | Con Prime, en determinados códigos postales | Con Prime, en determinados códigos postales |
| InPost (a Punto Pack y Locker) | De lunes a viernes | No | No |

Dos cosas que la tabla no cuenta:

- **El horario depende del servicio.** CTT Express avisa de que la hora concreta cambia según el servicio y la zona, y Correos Express calcula su hora estimada con el historial de entregas en tu código postal; aun así, puede cambiar con la ruta, el volumen de envíos o cualquier incidencia.
- **Amazon llega hasta las 22:00 con «Entrega hoy».** Lo ofrece en 16 ciudades, y en algunas, como Madrid, un pedido hecho hasta las 17:00 puede entregarse antes de las 22:00 del mismo día.

## ¿Reparten paquetes los sábados y los domingos?

**Los sábados**, casi siempre solo si quien envía lo ha pagado. La Ley 43/2010 obliga a Correos, como operador del servicio postal universal, a entregar «al menos, todos los días laborables, de lunes a viernes», y sus páginas de Paq Premium y Paq Estándar no ofrecen entrega en sábado. GLS, NACEX y Correos Express reparten en sábado solo como un servicio añadido que contrata el remitente, y CTT Express solo en Navidad.

**Los domingos**, solo Amazon dice que reparte: sus envíos Prime en un día incluyen «entregas en fin de semana en determinados códigos postales». Ninguna otra empresa de la tabla lo ofrece en sus páginas.

Así que un reparto en fin de semana depende de lo que compró **quien envía**, y tú casi nunca puedes añadirlo.

## ¿A qué hora llega mi paquete? La franja de entrega

La franja es el intervalo en el que la empresa calcula que llegará a tu casa, según la ruta del repartidor. No la eliges tú: te la dicen, casi siempre el mismo día.

| Empresa | La franja que te dan | Cuándo |
| --- | --- | --- |
| GLS | Una franja estimada, «orientativa» | Con el estado `En reparto`; el seguimiento en tiempo real empieza cuando quedan menos de 10 paradas |
| Correos Express | Una hora estimada | En el seguimiento |

Las demás no describen una franja en sus páginas de ayuda: cuenta con un día, no con una hora.

Una franja es una estimación. GLS lo dice claro: la jornada del repartidor «puede variar en función del volumen de envíos o de la ruta de reparto del día». Moverla tampoco suele ser posible.

Si quieres una hora fija, la paga quien envía. Con Paq Premium de Correos, quien lo lleva a la oficina elige entre cuatro franjas, de 9:00 a 12:00, de 12:00 a 15:00, de 15:00 a 18:00 o de 18:00 a 21:00, solo en capitales de provincia y en poblaciones con reparto de tarde. La Entrega concertada de NACEX acuerda contigo una franja de al menos dos horas entre las 9:00 y las 19:00, con un suplemento de 9 € en las poblaciones con agencia NACEX y hasta 15 km a la redonda. En [Peek](/) puedes añadir a tu calendario la franja que dé la empresa.

## Mi paquete está en reparto: ¿por qué no llega hasta la noche?

Porque tu casa no es la única parada. `En reparto` quiere decir que el paquete va hoy en la furgoneta, y que seas la parada 15 o la 115 de la ruta decide la hora. Correos Express y GLS lo explican igual: la hora cambia con la ruta, la zona y el volumen de envíos del día.

:::journey
- warehouse | Delegación | Clasificado y cargado en la furgoneta.
- truck | En reparto | De la primera parada a la última, todo el día.
- home | Tu puerta | A cualquier hora, hasta la última de la empresa.
:::

Las horas más tardías normales son las 19:00 de CTT Express y de los servicios «antes de las 19:00» de MRW y NACEX, las 20:00 más o menos de GLS, las 21:00 de la última franja de Paq Premium y las 22:00 de «Entrega hoy» de Amazon. Si un repartidor te lo deja a las 20:45, no es nada raro. Para saber qué significa cada estado por el camino, mira [los estados de un envío](guide:tracking-statuses).

A veces el repartidor no termina la ruta: DHL, en su web alemana, cita como causas que se ponga enfermo o se le acabe la jornada, las carreteras cortadas y los accidentes. El paquete suele salir de nuevo el siguiente día laborable. Si Correos no puede entregarlo y el envío incluye dos intentos, te avisa por SMS de un segundo intento en tu domicilio en 24 horas.

## Paquete en reparto que no llega: qué hacer

Llamar esa misma tarde rara vez sirve: DHL en Alemania, por ejemplo, dice que no puedes contactar directamente con sus repartidores, y el siguiente intento suele ser el siguiente día laborable.

:::steps
- Mira la hora | Hasta la última hora de reparto de la empresa, que llegue tarde es normal.
- Lee el seguimiento | Busca una franja, las paradas que quedan o un aviso de ausente.
- Espera un día laborable | Dale una oportunidad al siguiente reparto.
- Después, pregunta | Contacta con la tienda o con la empresa cuando pase su plazo.
:::

Cada una da su propio plazo. Correos dice que, tras su SMS de entrega a domicilio, el paquete llega entre 24 y 72 horas después, según el producto. Y Correos Express te pide que, si pasan 24-48 horas sin cambios en el seguimiento, contactes con el remitente.

¿Te han dejado un aviso porque no estabas? Mira [qué hacer si no estabas cuando pasó el repartidor](guide:missed-delivery). ¿Varios días sin un escaneo nuevo? Mira [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

Peek consulta el seguimiento hasta una vez cada 2 minutos cuando el paquete está en reparto con las empresas que lee directamente, y puedes dejar sus notificaciones en «Solo la entrega» para no tener que refrescar.

## ¿Reparten paquetes los festivos?

Normalmente no. La ley solo obliga a Correos a repartir los días laborables, e InPost no lleva paquetes a sus Punto Pack y Lockers los festivos. Muchos festivos en España son autonómicos o locales, así que un paquete puede pararse en tu ciudad un día que en otra se reparte con normalidad.

Los festivos tampoco cuentan en los plazos: una entrega en «24/48 horas laborables» se los salta. En la temporada navideña, CTT Express reparte también los sábados. Para las fechas límite de Navidad, mira [hasta cuándo enviar un paquete para que llegue en Navidad](guide:christmas-posting-dates).

## ¿Puedo recoger el paquete fuera del horario de reparto?

Sí, si va a un locker o a una tienda:

- La mayoría de los Lockers de InPost están en la calle, y puedes recoger tus paquetes las 24 horas del día, los 7 días de la semana.
- Correos promete con sus taquillas Citypaq recoger «cuando y donde quieras», «sin horarios».
- Muchos puntos NACEX.shop abren sábados y domingos.
- Algunos Amazon Lockers están disponibles las 24 horas del día, siete días a la semana.

Eso sí: un locker que abres a cualquier hora solo se llena los días de reparto. InPost no lleva paquetes a sus Punto Pack y Lockers ni sábados, ni domingos, ni festivos. Para que un paquete ya enviado vaya a otro sitio, mira [cómo cambiar la dirección de entrega](guide:change-delivery-address).

:::sources
- [BOE: Ley 43/2010 del servicio postal universal](https://www.boe.es/buscar/act.php?id=BOE-A-2010-20139) – entregas al menos todos los días laborables, de lunes a viernes
- [Correos: Paq Premium](https://www.correos.es/es/es/particulares/enviar/envios-nacionales/envio-rapido/paq-premium) – cuatro franjas, de 18:00 a 21:00, capitales y reparto de tarde; sin entrega en sábado entre sus opciones
- [Correos: Paq Estándar](https://www.correos.es/es/es/particulares/enviar/envios-nacionales/envios-economicos/paquete-estandar) – sin entrega en sábado entre sus opciones
- [Correos: Aviso de llegada y SMS](https://www.correos.es/es/es/atencion-al-cliente/recibir/aviso-de-llegada) – 24 a 72 horas, segundo intento en 24 horas
- [Correos: Citypaq](https://www.correos.es/es/es/particulares/recibir/nuestros-puntos-de-entrega-y-recogida/citypaq) – sin horarios
- [Correos Express: Seguimiento de envíos](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – hora estimada, 24-48 horas sin cambios
- [Correos Express: Paq 24](https://www.correosexpress.es/es/particulares/enviar/peninsulares/paq-24) – Entrega en sábados
- [GLS: Preguntas frecuentes para recibir paquetes](https://gls-group.com/ES/es/faq/recibir-paquetes/) – de 9 a 20 h, Express Saturday, franja orientativa, 10 paradas
- [MRW: Preguntas frecuentes de envíos nacionales](https://www.mrw.es/enviar/nacional/faqs/index.asp) – antes de las 19 h del día laborable siguiente en la Península
- [NACEX: Servicios urgentes](https://www.nacex.es/irServicioNacional.do?seccion=urgentes) – NACEX 19:00 H, Entrega en sábado de 9:00 a 13:00
- [NACEX: Servicios complementarios](https://www.nacex.es/irServicioNacional.do?seccion=complementarios) – Entrega concertada, 9 €, 15 km
- [NACEX: NACEX.shop](https://www.nacex.es/irServicioNacional.do?seccion=mep) – puntos abiertos sábados y domingos
- [CTT Express: Horarios de reparto](https://www.cttexpress.com/preguntas-frecuentes/horarios) – 9:00 a 19:00, sábados en Navidad
- [About Amazon España: Nuevas inversiones en robótica, entregas rápidas y formación profesional](https://www.aboutamazon.es/noticias/logistica/amazon-nuevas-inversiones-robotica-entregas-rapidas-formacion-profesional) – pedidos hasta las 17:00 entregados antes de las 22:00 del mismo día, también en Madrid
- [About Amazon España: Amazon bate su récord de entregas rápidas en España](https://www.aboutamazon.es/noticias/noticias-de-la-compania/amazon-bate-record-entregas-rapidas-espana) – «Entrega hoy» en 16 ciudades
- [About Amazon España: Prime a mitad de precio para los jóvenes](https://www.aboutamazon.es/noticias/noticias-de-la-compania/amazon-prime-mitad-precio-los-jovenes) – entregas en fin de semana en determinados códigos postales
- [About Amazon España: Amazon Lockers](https://www.aboutamazon.es/noticias/innovacion/amazon-lockers) – algunos lockers disponibles las 24 horas, siete días a la semana (2017)
- [InPost: Atención al cliente, contacto y ayuda](https://inpost.es/contacta-con-nosotros) – reparto de lunes a viernes, sin festivos, Lockers 24 horas
- [DHL: ¿Qué significa el estado de mi envío?](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/sendungsverfolgung/was-bedeutet-mein-sendungsstatus.html) – rutas sin terminar, sin contacto directo con el repartidor
:::
