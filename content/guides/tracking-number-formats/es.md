---
title: ¿De qué empresa es este número de seguimiento?
description: Cómo saber de qué empresa es un número de seguimiento por sus letras y su longitud: Correos, MRW, NACEX, TIPSA, InPost, UPS, DHL, Amazon o AliExpress.
slug: que-empresa-es-numero-de-seguimiento
picture: Tres etiquetas con números de seguimiento divididos en colores, una lupa sobre el código de país CH y Pip, curioso.
published: 2026-10-09
updated: 2026-10-27
---

Las letras y la longitud de un número de seguimiento suelen decirte qué empresa tiene tu paquete. `1Z` seguido de 16 letras y cifras es UPS, y un código de 13 caracteres como `RR000000005CH` (dos letras, nueve cifras, dos letras) suele ser de Correos o de otro operador postal: las dos últimas letras dicen qué país lo emitió, y `ES` es España. Los números que solo llevan cifras son el caso difícil: 12, 14 o 22 cifras encajan con varias empresas, así que la última palabra la tiene el correo de envío de la tienda, o una web de seguimiento que pruebe con todas las candidatas.

:::checker
:::

## ¿Cómo saber de qué empresa es un número de seguimiento?

Cuatro comprobaciones, en este orden:

:::steps
- Lee las letras | Son la mejor pista. `1Z` al principio es UPS; dos letras al final suelen indicar un operador postal.
- Cuenta los caracteres | La longitud casi nunca decide por sí sola, pero descarta empresas. No cuentes los espacios: están para que leas mejor y no forman parte del número.
- Mira el correo de envío | El correo o el SMS de la tienda suele decir qué transportista lleva el paquete, o enlazar a su página de seguimiento. Eso vale más que cualquier suposición.
- Prueba con los candidatos | Introduce el número en la web de cada transportista posible. El que muestre movimientos de ese número tiene tu paquete.
:::

Si prefieres ahorrarte el trabajo de detective, pega el número, un enlace del transportista o el correo de envío entero en [Peek](/): encuentra el número y detecta el transportista entre más de 3500. Es lo que hace un [localizador universal de paquetes](guide:universal-tracker).

## El formato del número de seguimiento en cada empresa

Cada empresa lo llama a su manera: «código de envío» en Correos, «número de envío» en [MRW](carrier:mrw) y [TIPSA](carrier:tipsa), «número de expedición» en InPost, «número de seguimiento» en [GLS](carrier:gls-es) y Correos Express. Todos hablan de lo mismo: el número con el que se sigue tu paquete.

Esto es lo que dicen las empresas y la norma postal sobre los números que más vas a ver en España. Cuando la empresa no publica el formato, la tabla da el que reconocen los datos de transportistas que usa Peek: sirve de pista, no de prueba.

| Empresa | Cómo es el número |
| --- | --- |
| [Correos](carrier:correos-spain) | «código de envío». Su ayuda no da formato; según esos datos, es el formato postal acabado en `ES`, o 16 o 23 caracteres que empiezan por `P` o `D` más otra letra (como `PQ`) y acaban en letra. Te llega por correo electrónico, por SMS o por ambos, y en el aviso de llegada |
| [Correos Express](carrier:correos-express) | su ayuda no da formato; según esos datos, 16 o 23 cifras |
| [SEUR](carrier:seur) | no publica el formato; su buscador pide el «Identificador de envío». Suelen ser 14 o 21 cifras, y un código de 7 cifras también sirve; las de 14 también encajan con DPD, Relais Colis, BRT y otros |
| GLS | su página de seguimiento no da longitud y pide también el código postal de destino |
| MRW | «número de envío», en el justificante si enviaste desde una oficina MRW. En la etiqueta de ejemplo de MRW sale con 12 cifras bajo el código de barras y, junto a «N.º Etiqueta», partido en 5 cifras, una barra y 7 cifras: quita la barra antes de buscarlo. En paquetes de MRW se ha visto también la forma de 5 cifras, una letra y 6 cifras, que las páginas de MRW no muestran |
| [NACEX](carrier:nacex) | el localizador pide la expedición en dos casillas, «Agencia» y «Albarán»; su portada pone de ejemplo `0000/00000000` y esos datos reconocen 4 cifras, una barra y 8 cifras, aunque NACEX no publica una longitud fija |
| TIPSA | «número de envío compuesto por 22 dígitos», junto con el código postal de destino. Son dos códigos de agencia y el albarán de 10 cifras; el albarán suelto no sirve |
| [CTT Express](carrier:ctt-express) | «código de envío»; según esos datos, 22 cifras: `00`, cuatro cifras, `00` y 14 cifras más, o 25 cifras con un contador de bultos al final. Si empieza por `00001000`, puede ser también de TIPSA |
| [Mondial Relay](carrier:mondial-relay) | «n° d'expédition» (número de envío): 8 cifras, que su seguimiento pide junto con el código postal del destinatario; su web belga acepta también 10 cifras (un código de marca y ese número) y 12 (más el número de bulto). El código de barras de 26 cifras de la etiqueta solo es de Mondial Relay |
| InPost (Punto Pack, Locker) | «número de expedición»; el ejemplo de su localizador tiene 8 caracteres. La web de Mondial Relay en España ya lleva a la de InPost |
| Ecoscooting | según esos datos, `CNESP` y 20 cifras |
| [Paack](carrier:paack) | su seguimiento pide el número de pedido o el del código de barras de la etiqueta, junto con el código postal de entrega. Según esos datos, casi siempre es el número de pedido de la tienda o el código de barras, sin formato fijo, así que puede parecerse al número de otra empresa. Hay tres excepciones: `PK` y 18 cifras es de Paack (paquetes que entrega para Evri International), `5340` y 14 cifras apunta primero a Paack, y un número de paquete de SHEIN (`C` o `E`, una fecha de seis cifras y 8 cifras más) puede ser de Paack |
| [UPS](carrier:ups) | `1Z` y 16 letras o cifras, 18 caracteres en total, como `1Z999AA10123456784`, un ejemplo con un dígito de control de UPS válido |
| [DHL Express](carrier:dhl-express) | 10 cifras, nunca letras, según DHL Express en los Países Bajos |
| [4PX](carrier:4px) | `4PX`, 13 cifras y `CN`; 4PX no publica ningún número de ejemplo, y su web acepta de 5 a 50 letras y cifras. `LP`, 13 cifras y `CN` es probablemente de 4PX |
| [Yanwen](carrier:yanwen) | Yanwen no publica ningún número de ejemplo; sus paquetes llevan `BYS` y 9 cifras, o dos letras, 9 cifras y `YP` (suele empezar por `UL` o `UK`). `YW`, tres letras y 12 cifras es su reparto propio en Estados Unidos |
| AliExpress, [Cainiao](carrier:cainiao) | Cainiao no describe sus números; según esos datos, `DOFR` o `CNFR`, 13 cifras y `HD` es de Cainiao, y `LP` y `CNG` se explican justo abajo |
| [YunExpress](carrier:yunexpress) | Yuntrack pide el número que empieza por `YT`, sin decir cuántas cifras lleva; según esos datos, `YT` y 16 cifras. `YT` y 13 cifras es YTO Express; `YT`, 9 cifras y `GB` es un envío postal de Royal Mail |
| [China Post](carrier:china-post), EMS | el formato postal acabado en `CN`; los números EMS internacionales empiezan por `E` |
| Amazon | en los datos de transportistas de Peek, `ES` seguido de 10 cifras o `TBA` seguido de 12; se explican más abajo |

### Número de seguimiento que empieza por PQ, PK, LP o CNG

Según los datos de transportistas citados arriba, un código de 16 o 23 caracteres que empieza por `P` o `D` seguida de otra letra (como `PQ` o `PK`) y acaba en letra es de Correos. La ayuda de Correos no describe estos códigos, pero el único ejemplo que enseña encaja: en su página sobre el envío de equipaje para viajeros de Renfe, el código de la columna «PK» empieza por esas dos letras y tiene 23 caracteres.

En esos mismos datos, `LP` o `CNG` seguidos de 14 cifras apuntan a AliExpress y Cainiao, pero solo como posibilidad: Cainiao no describe sus números en su centro de ayuda.

## ¿Cuántas cifras tiene un número de seguimiento?

No hay una longitud única. Estas son las que dan las propias empresas en sus páginas, y las coincidencias explican por qué un número que solo lleva cifras puede encajar con varias:

| Longitud | Quién la usa |
| --- | --- |
| 8 caracteres | InPost, en el ejemplo de su localizador; Mondial Relay (8, 10 o 12 cifras, según su web belga) |
| 10 cifras | DHL Express; [DHL eCommerce Iberia](carrier:dhl-ecommerce-es); Mondial Relay |
| 12 cifras | MRW, en su etiqueta de ejemplo; Mondial Relay |
| 13 caracteres | el formato postal (`…ES`, `…CN`, `…PT`) |
| 18 caracteres | UPS (`1Z` y 16 letras o cifras) |
| 22 cifras | TIPSA; CTT Express y CTT en Portugal, que también usan 25 |

Correos, Correos Express y GLS no dan ninguna longitud en sus páginas de ayuda sobre el seguimiento, y MRW solo la enseña en su etiqueta de ejemplo. Para los números que solo llevan cifras, los datos de transportistas ofrecen candidatas, no certezas: 12 cifras encajan con MRW y Mondial Relay, entre otras; 14, con SEUR y varias redes de DPD; 16 o 23, con Correos Express.

A veces el número no basta. GLS, TIPSA y Paack piden también el código postal de destino, y GLS lo dice tal cual: «introduce el número de seguimiento de GLS y el código postal de destino».

## Número de seguimiento que empieza por ES o TBA: Amazon

En los datos de transportistas que usa Peek, `ES` seguido de 10 cifras es [Amazon Logistics](carrier:amazon-logistics), el servicio de reparto de Amazon, o [Amazon Shipping](carrier:amazon-shipping) si Amazon lo lleva por encargo de otra tienda. Lo mismo vale con el código de otro país europeo delante, como `FR` o `PT`, y con `TBA`, `TBC` o `TBM` seguidos de 12 cifras. El pedido se sigue en «Mis pedidos» de Amazon.

Cuenta los caracteres para no confundirlo con un número postal: `ES` y 10 cifras suman 12, y un número postal tiene 13, con dos letras al principio y dos al final. Puede acabar en `ES`, si es de Correos, y hasta empezar por `ES`, porque la serie `E` es la de los envíos EMS.

## El formato postal: dos letras, nueve cifras y dos letras

Los operadores postales nacionales comparten un formato de 13 caracteres, fijado por la Unión Postal Universal (UPU) en su norma S10. Aquí lo tienes por partes:

:::anatomy RR 00000000 5 CH
- RR | Tipo de envío: R de carta certificada
- 00000000 | Número de serie, ocho cifras
- 5 | Dígito de control, calculado a partir del número de serie
- CH | País que emitió el número: Suiza
:::

En los envíos que cruzan una frontera, la primera letra indica el tipo de envío. Dentro de su país, cada operador puede usar las letras de otra manera.

- `E`: EMS, el servicio urgente de los operadores postales
- `C`: paquete
- `R`: carta certificada
- `L`: carta con seguimiento
- `V`: carta con valor declarado
- `U`: mercancía enviada como carta, sin seguimiento para ti

Las dos últimas letras no siempre dicen de dónde salió el paquete. La UPU advierte que ese código no es un indicador fiable del origen geográfico de un envío: Swiss Post, el correo suizo, pone `CH` en los envíos que expide desde sus oficinas en otros países, y CTT, el correo portugués, da códigos acabados en `PT` a envíos que sus clientes con contrato mandan desde España, China y otros países.

El dígito de control sirve para detectar erratas. Para comprobar un número:

1. Multiplica las ocho cifras del número de serie por 8, 6, 4, 2, 3, 5, 9 y 7, y suma los resultados.
2. Divide la suma entre 11 y resta a 11 el resto de la división.
3. Si sale 10, el dígito es 0; si sale 11, es 5.

Para `RR000000005CH`, las ocho cifras del número de serie son 0: la suma da 0, al dividir entre 11 el resto es 0, y 11 − 0 = 11, así que el dígito es 5. Si un número postal que copiaste a mano no pasa la prueba, busca una cifra mal escrita o dos cifras cambiadas de sitio.

### Número de seguimiento que empieza por U

Si tiene 13 caracteres y acaba en dos letras, como `CN`, es un número postal de la serie `U`: mercancía enviada como carta. Las dos últimas letras dicen de qué país es el operador postal que lo emitió. Un número más largo que empieza por `UU` no tiene ese formato: busca el nombre del transportista en el correo de envío.

> En un envío que llega del extranjero, un número postal que empieza por `U` (de `UA` a `UZ`) no está pensado para que lo sigas. La UPU reserva esa serie para la mercancía enviada como carta sin seguimiento para el cliente, y bpost, el correo belga, lo dice sin rodeos: con un código así no podrás seguir tu paquete. No esperes a que el seguimiento arranque más tarde.

## Números que no son tu número de seguimiento

El número de pedido es la referencia de la tienda, no la del transportista: DHL recuerda que un número de seguimiento no es un número de pedido. Algunas referencias sí llevan al paquete, como la referencia alternativa de NACEX (hasta 10 cifras, en la etiqueta) o la «Referencia» de TIPSA, pero solo en su localizador y junto con los códigos de agencia y de cliente. Dónde está el número bueno, y qué hacer si no lo tienes: [dónde encontrar el número de seguimiento](guide:find-tracking-number).

## Por qué el número de seguimiento puede cambiar por el camino

Un paquete que cruza una frontera suele cambiar de manos, y cada empresa puede imprimir su propia etiqueta. La UPU permite que el operador postal de destino añada su propio código de barras junto al original, siempre que no tenga el formato postal de 13 caracteres.

:::journey
- shop | Vendedor | Imprime la etiqueta y el primer número
- plane | Largo recorrido | Viaja con ese primer número
- customs | Aduana | Control en tu país
- handover | Transportista local | Puede añadir su propia etiqueta y su número
- home | Tu casa | Los últimos movimientos pueden salir con el número nuevo
:::

- **Los envíos postales** son el caso fácil: la UPU solo permite un número S10 por envío, así que el mismo número suele funcionar en la web del correo de origen y en la de Correos.
- **Amazon:** reparte la última milla desde sus estaciones logísticas con ayuda de sus proveedores de transporte.
- **Paack:** a los paquetes que le pasa Evri International les da un número propio, `PK` y 18 cifras, que Evri International también conoce. Su propio seguimiento pide el número y el código postal de entrega.
- **AliExpress con envío económico:** Cainiao dice que el seguimiento termina al entregar el paquete al transportista local, y que el vendedor puede decirte cómo contactar con ese transportista. Más en [seguir un paquete que viene de China](guide:tracking-from-china).

Un número que no devuelve nada no siempre está mal escrito. Correos Express avisa de que un envío recién registrado puede tardar unas horas en mostrar información, y te pide comprobar que el número «corresponde a Correos Express». CTT, en Portugal, da otros dos motivos: el código solo existe para la [aduana](guide:customs), u otra empresa entrega el envío. Si un número válido simplemente no se mueve todavía, lee [por qué el seguimiento no se actualiza](guide:tracking-not-updating).

:::sources
- [UPU: norma S10, Identification of postal items](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – el formato postal, sus letras, el dígito de control, el código de país y el código de barras añadido en destino
- [Correos: Código de envío](https://www.correos.es/es/es/atencion-al-cliente/recibir/codigo-de-envio) – correo electrónico, SMS y aviso de llegada; el ejemplo de 23 caracteres que empieza por PK
- [Correos Express: Seguimiento y entrega de tu envío](https://www.correosexpress.es/es/atencion-al-cliente/seguimiento-envio) – número que no funciona, envíos recién registrados
- [GLS España: Seguimiento de envío](https://gls-group.com/ES/es/recibir-paquetes/seguimiento-envio/) – número de seguimiento y código postal de destino
- [MRW: Seguimiento de envíos](https://www.mrw.es/seguimiento/) – el número de envío en el justificante
- [MRW: ¿Cómo localizar su número de envío?](https://www.mrw.es/includes/numero_de_envio.html) – 12 cifras bajo el código de barras, 5 cifras, una barra y 7 cifras junto a «N.º Etiqueta»
- [SEUR: miSEUR, Mis envíos](https://www.seur.com/miseur/mis-envios) – identificador de envío
- [NACEX: Inicio](https://www.nacex.es/) – el ejemplo 0000/00000000 de la casilla de seguimiento
- [NACEX: Seguimiento de envíos](https://www.nacex.es/irSeguimiento.do) – expedición en Agencia y Albarán, referencia alternativa de hasta 10 dígitos
- [TIPSA: Localización de envíos](https://www.tip-sa.com/es/localizacion-envios) – 22 dígitos, código postal de destino, referencia e identificador
- [InPost: Seguimiento del envío](https://www.inpost.es/seguimiento-del-envio/) – número de expedición, ejemplo de 8 caracteres
- [Mondial Relay España](https://www.mondialrelay.es/) – la dirección lleva a la web de InPost
- [Mondial Relay: Suivi de colis](https://www.mondialrelay.fr/suivi-de-colis/) – número de envío y código postal
- [Mondial Relay: Étiquette](https://storage.mondialrelay.fr/etiquette-mondial-relay-v-24.pdf) – código de barras de 26 cifras
- [Mondial Relay Bélgica: Suivi de colis](https://www.mondialrelay.be/fr-be/suivi-de-colis/) – 8, 10 o 12 cifras
- [Universal Parcel Scraper: Paack](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/paack) – número de pedido o código de barras con el código postal de entrega, sin formato fijo; `PK` y 18 cifras en los paquetes de Evri International, `5340` y 14 cifras, números de paquete de SHEIN
- [About Amazon España: ¿Cómo llegan los paquetes de Amazon a su destino?](https://www.aboutamazon.es/noticias/inversiones-y-creacion-de-empleo/como-llegan-los-paquetes-de-amazon-a-su-destino) – estaciones logísticas y proveedores de transporte en la última milla
- [UPS: ejemplo de etiqueta](https://www.pld-certify.ups.com/CerttoolHelp/PLD0200/WebHelp_pld0200/LeadPackage.htm) – el número 1Z
- [DHL Express Países Bajos: DHL Express or DHL eCommerce number](https://www.dhlexpress.nl/en/consumer/faq/express-account-zendingsnummer/my-shipment-number-dhl-express-or-dhl-ecommerce) – 10 cifras
- [DHL: Sendungsverfolgung, Probleme und Lösungen](https://www.dhl.de/de/privatkunden/hilfe-kundenservice/sendungsverfolgung/probleme-loesungen.html) – el número de seguimiento no es un número de pedido
- [YunExpress: YunTrack](https://www.yuntrack.com/) – página de seguimiento de YunExpress
- [4PX: Plataforma de seguimiento global](https://track.4px.com/) – de 5 a 50 letras y cifras
- [Yanwen: YW Tracking (en inglés)](https://track.yw56.com.cn/en/index) – web de seguimiento de Yanwen, sin número de ejemplo
- [CTT Express: Localizador de envíos](https://www.cttexpress.com/localizador-de-envios/) – el código de envío
- [Cainiao: centro de ayuda](https://global.cainiao.com/helpDoc.htm) – sin seguimiento tras la entrega al transportista local en el envío económico
- [bpost: Puis-je suivre mon colis en ligne ?](https://www.bpost.be/fr/faq/puis-je-suivre-mon-colis-en-ligne) – los códigos que empiezan por U no se pueden seguir
- [CTT: Encontrar o código de envio](https://www.ctt.pt/ajuda/particulares/seguir-ou-alterar-entrega/seguir/encontrar-o-codigo-de-envio) – 22 o 25 cifras, códigos PT de envíos salidos de España, códigos que no devuelven nada
- [Universal Parcel Scraper: catálogo de transportistas](https://github.com/plhery/universal-parcel-scraper/blob/main/data/catalog.json) – las formas de Correos, Correos Express, SEUR, MRW, NACEX, CTT Express, Ecoscooting, Amazon, AliExpress, 4PX, Yanwen, YunExpress, YTO Express y Royal Mail en los datos de Peek
:::
