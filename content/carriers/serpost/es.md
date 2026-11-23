---
title: Seguimiento de Serpost: códigos S10, aduanas y contacto
description: Sigue un envío de Serpost (Servicios Postales del Perú): códigos S10 PE, estados de aduanas, recojo en oficina, plazos de reclamo y central telefónica.
slug: seguimiento-serpost
published: 2026-11-23
updated: 2026-11-23
---

**Serpost** (*Servicios Postales del Perú S.A.*) es el operador postal nacional estatal del Perú, adscrito al Ministerio de Transportes y Comunicaciones (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Gestiona correspondencia y encomiendas nacionales junto con envíos internacionales salientes (como EMS y *Exporta Fácil*) y correo internacional entrante de la Unión Postal Universal (UPU). Puedes consultar tu paquete en [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) o pegar tu código postal S10 de 13 caracteres en el cuadro de arriba.

## Códigos de seguimiento de Serpost (formato UPU S10 de 13 caracteres)

Serpost utiliza códigos internacionales estándar UPU S10 de 13 caracteres: dos letras de servicio, 8 dígitos de serie, 1 dígito verificador S10 y el código de país de origen de 2 letras (`PE` para envíos que salen del Perú, o el país extranjero de origen como `US`, `CN`, `ES` o `JP` para envíos que llegan al Perú):

| Formato (inventado) | Qué es |
| --- | --- |
| `RR482917367PE` | Envío certificado de 13 caracteres despachado desde el Perú (`RR` + 9 dígitos con verificador S10 válido + `PE`). |
| `EE739184627PE` | Envío internacional expreso EMS de 13 caracteres despachado desde el Perú (`EE` + 9 dígitos con verificador S10 válido + `PE`). |

Los envíos simples sin código S10 de 13 caracteres no figuran en el rastreo en línea. Para paquetes internacionales entrantes desde el extranjero (por ejemplo terminados en `CN`, `US` o `GB`), una vez que llegan al Perú, seleccionar Serpost muestra los eventos de aduana y oficinas postales peruanas con ese mismo código S10. Consulta los [formatos de números de seguimiento](guide:tracking-number-formats).

## Qué significan los estados de seguimiento de Serpost

El rastreador de Serpost publica descripciones en español que indican la administración postal peruana donde se registró cada movimiento:

| Serpost indica | Qué significa para ti | Peek muestra |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Admitido en ventanilla en la administración postal de Serpost indicada. | "Admitido por el transportista" |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Envío internacional recibido por Serpost en el Perú, en procesamiento central o en tránsito interno. | "En tránsito" |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Retenido o seleccionado por Aduanas (SUNAT) para trámite aduanero, valoración o pago de tributos. | "En aduanas" |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Liberado por Aduanas sin observaciones y devuelto a la red de Serpost. | "En tránsito" |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Envío internacional saliente despachado fuera del Perú hacia el correo del país de destino. | "En tránsito" |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | Llegó a la oficina postal local de distribución en el Perú. | "En tránsito" |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | En ruta con el cartero de Serpost para entrega a domicilio. | "En reparto" |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Listo para recoger en la oficina de Serpost asignada (lleva tu DNI o documento de identidad). | "Listo para recoger" |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | No se pudo concretar la entrega; revisa si dejaron aviso o acude a la oficina postal. | "Intento de entrega" |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | Entregado al destinatario o recogido en oficina. | "Entregado" |

## Plazos de entrega, aduanas y reclamos oficiales

Según [Enviar cartas con Serpost](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) y [Reclamos y consultas de Serpost](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru):

- **Plazos orientativos publicados:** **Expreso** local y nacional **48 a 72 horas como máximo**; **Estándar** local y nacional **5 a 7 días hábiles**; **Económico** nacional **7 a 10 días hábiles**; **Expreso internacional (EMS)** **7 a 10 días hábiles**; **Económico internacional** **10 a 30 días hábiles**.
- **Aduanas del Perú (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`):** los envíos entrantes sujetos a control de SUNAT requieren trámite aduanero y *Aviso de Llegada* antes de su liberación o recojo. Consulta [paquetes retenidos en aduanas](guide:customs).
- **Plazos para presentar reclamos e indemnizaciones:** los reclamos por envíos **EMS** se aceptan desde el día siguiente de vencido el plazo de entrega y hasta **4 meses** desde el depósito (respuesta en hasta **30 días calendario**); en **Envíos Internacionales Certificados** pasados **20 días calendario** y en **encomiendas** pasados **30 días calendario**; para envíos nacionales la respuesta demora hasta **16 días hábiles** (indemnización nacional de **3 veces el porte pagado**; internacional según montos de la UPU).

## Envío de Serpost retrasado, en aduanas o extraviado

- **Pausas en correo internacional:** los envíos salientes tras `ENVÍO SALIÓ DE PERÚ` o los paquetes que llegan del exterior pueden pasar varios días o semanas sin escaneos entre el vuelo internacional y el ingreso a aduana. Si un envío `...PE` ya salió del Perú, consulta también al correo de destino (por ejemplo [USPS](carrier:usps)). Consulta [por qué el seguimiento deja de actualizarse](guide:tracking-not-updating).
- **Cómo presentar un reclamo en Serpost:** envía tu solicitud a `servicioalcliente@serpost.com.pe`, llama al `(01) 511-5110` o preséntala en la oficina postal más cercana; recibirás un número de expediente por correo para darle seguimiento. Consulta [paquete perdido](guide:lost-parcel), [paquete dañado](guide:damaged-parcel), [paquete entregado pero no recibido](guide:delivered-not-received) y [paquete devuelto al remitente](guide:returned-to-sender).

## Atención al cliente y contactos de Serpost

Canales oficiales publicados en [Contacto Serpost (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias):

- **Call Center (Perú):** `(01) 511-5110`.
- **Central telefónica:** `511-5000` (sede central `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **Correo de atención al cliente y reclamos:** `servicioalcliente@serpost.com.pe` (webmaster: `webmaster@serpost.com.pe`).
- **Sede Central:** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Perú.
- **Rastreo en línea y portal estatal:** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) y [gob.pe/serpost](https://www.gob.pe/serpost).

> Desconfía de mensajes SMS falsos que pidan un pago en un enlace desconocido para liberar un paquete de Serpost. Verifica cualquier trámite aduanero solo en [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) o los portales oficiales de `gob.pe` y SUNAT. Consulta [estafas por SMS de entrega](guide:delivery-scam-texts).

## Sigue un paquete de Serpost con Peek

Cuando introduces un código postal S10 peruano de 13 caracteres (`RR482917367PE` o `EE739184627PE`, inventados) en [Peek](/), Peek valida el dígito verificador S10 y puede consultar directamente a Serpost — y si sigues un código S10 extranjero que llega al Perú o quieres seleccionar Serpost manualmente, pulsa "Cambiar" al añadirlo o "Cambiar transportista" en la pantalla del paquete y elige **Serpost** (también aparece buscando "SERPOST", "Peru Post" o "Servicios Postales del Perú"); Peek muestra `"Serpost tiene este paquete."` en cuanto Serpost devuelve movimientos.

Peek consulta `serpost.com.pe/Cliente/SegumientoLinea` directamente y muestra la fase mappada ("Admitido por el transportista", "En tránsito", "En aduanas", "En reparto", "Listo para recoger", "Intento de entrega", "Entregado"), las descripciones de los eventos en español (eliminando textos promocionales de WhatsApp de oficina), el nombre de la oficina postal peruana, las coordenadas verificadas de la oficina cuando Serpost enlaza su mapa dentro del Perú, la hora de entrega al completarse, el país de destino (`PE`) en entregas locales y las fechas en hora de Lima (`America/Lima`). El rastreador en línea de Serpost no ofrece fecha estimada de entrega, peso ni nombre de quien firmó.

Peek comprueba hasta cada 10 minutos mientras tienes el paquete abierto o el día de la entrega con avisos activados, y hasta cada 2 minutos desde las 6:00 hora local el día en que llega un escaneo de reparto. El resto del tiempo revisa cada hora, mantiene el ritmo horario con la pantalla abierta tras 12 horas en "Etiqueta creada" o "Admitido por el transportista" o 2 días sin novedades, y baja a cada 6 horas tras 7 días aún en "Etiqueta creada". Cuando los servicios de seguimiento actúan de respaldo, revisan cada hora, cada 3 horas tras un día sin cambios y hasta cada 15 minutos el día de entrega. Tras 4 días en tránsito sin escaneos nuevos, Peek señala la pausa (por ejemplo "Sin novedades desde el lun, 12 oct"), muestra "Los paquetes suelen quedarse sin novedades en vuelos largos, en la aduana o el fin de semana. Peek sigue preguntando a Serpost." y ofrece "Pide ayuda a Serpost", que abre la página oficial de contacto en `gob.pe`. Tras más de tres semanas sin novedades, Peek muestra "Nada nuevo desde hace más de tres semanas. Si el paquete aún no ha llegado, contacta con el vendedor. Peek sigue preguntando a Serpost."

Puedes elegir los avisos entre "Cada escaneo", "Pasos importantes" o "Solo la entrega". Peek solo monitoriza el seguimiento y no puede gestionar trámites ante SUNAT, presentar reclamos ni contactar con Serpost por ti.

## Preguntas frecuentes sobre el seguimiento de Serpost

### ¿Cómo es un código de seguimiento de Serpost?

Serpost rastrea códigos UPU S10 de 13 caracteres — dos letras, 9 dígitos (con dígito verificador) y el código de país de origen de dos letras como `PE` (por ejemplo `RR482917367PE` o `EE739184627PE`, inventados), además de códigos S10 extranjeros que ingresan al Perú.

### ¿Qué significa `ENVÍO SUJETO A TRÁMITE ANTE ADUANA`?

Significa que Aduanas del Perú (SUNAT) seleccionó el envío internacional para trámite aduanero o revisión tributaria antes de que Serpost pueda entregarlo o habilitar su recojo en oficina.

### ¿Cuánto tarda un envío de Serpost?

Según los plazos oficiales en [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost), el servicio nacional Expreso tarda **48 a 72 horas**, Estándar **5 a 7 días hábiles**, Económico nacional y EMS internacional **7 a 10 días hábiles**, y Económico internacional **10 a 30 días hábiles**.

### ¿Cómo contactar con Serpost o presentar un reclamo?

Llama al Call Center `(01) 511-5110`, escribe a `servicioalcliente@serpost.com.pe` o acude a una oficina postal de Serpost; todos los canales oficiales figuran en [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – rastreador oficial en línea y Call Center `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – portal oficial de *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Call Center `511-5110`, central `511-5000`, `servicioalcliente@serpost.com.pe` y sede Los Olivos (URL de ayuda enlazada por Peek)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – plazos de reclamo (20 días certificados, 30 días encomiendas, 4 meses EMS), respuesta e indemnizaciones
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – tiempos estimados de entrega por servicio
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – validación S10, consulta a `SegumientoLinea`, mapa de estados en español, extracción de oficinas/mapa y hora `America/Lima`
:::
