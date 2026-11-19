---
title: Sigue tus paquetes en Home Assistant
description: La integración de Peek para Home Assistant trae tus paquetes como sensores, un calendario de entregas y una tarjeta para tu panel. Qué necesitas y cómo instalarla.
slug: seguimiento-paquetes-home-assistant
picture: Una tableta en la pared de una cocina con el panel de la casa: una lista de paquetes, un pequeño calendario de entregas y Pip, el paquete con cara, sonriendo desde una de las tarjetas.
published: 2026-11-19
updated: 2026-11-19
---

Para seguir tus paquetes en Home Assistant, instala Peek for Home Assistant: trae tu lista de paquetes de [Peek](/) como sensores, un calendario de entregas, una tarjeta para tus paneles y una herramienta para Assist. Necesitas una cuenta gratuita de Peek y Home Assistant 2026.11 o posterior, donde la añades desde el Marketplace con su enlace; en la versión 2026.10, el README de la integración explica cómo hacerlo con HACS.

## ¿Qué muestra Peek en Home Assistant?

Tus paquetes de Peek, en las formas que usa Home Assistant:

| Qué | Para qué sirve |
| --- | --- |
| Sensores | Cuentan los paquetes en camino, los que llegan hoy, los que te necesitan (esperando en un punto de recogida, una entrega fallida, aduanas) y las entregas de hoy, y dan el próximo día de entrega |
| Un calendario «Deliveries» | Eventos de día completo: cada paquete en camino en su día previsto, cada entrega el día en que llegó |
| Un evento «Parcel updates» | Se dispara cuando se añade un paquete, cambia de estado, sale a reparto, te necesita, se entrega o cambia su día previsto |
| Tres acciones | Añadir paquetes a partir de un texto, archivar un paquete o leer la lista |
| La tarjeta «Peek parcels» | Tus paquetes en un panel, como lista o como tarjetas con Pip |
| Una herramienta para Assist | Responde a preguntas como «¿qué llega hoy?» |

Home Assistant consulta Peek cada 30 minutos, cada 10 minutos los días en que un paquete está en reparto o previsto, y justo después de añadir o archivar un paquete. Para consultarlo en el momento, llama a `homeassistant.update_entity` sobre cualquier entidad de Peek. «Hoy» es el día en la zona horaria de Home Assistant.

## ¿Qué necesito?

- **Home Assistant 2026.11 o posterior**, como dice la página de Peek, y una cuenta de administrador: el Marketplace de Home Assistant es solo para administradores. En la versión 2026.10, el README de la integración explica cómo hacerlo con HACS.
- **Una cuenta de Peek.** Al configurar la integración, se abre Peek para que inicies sesión, con un código de seis cifras que Peek te envía por correo, o con Google o Apple si la pantalla lo ofrece. Tu primer inicio de sesión crea la cuenta; un invitado no puede conectar Home Assistant.
- **Nada más.** Peek es gratis, y no hay que escribir ningún ID de cliente, secreto ni «Application Credentials». Cada Home Assistant puede conectarse a una sola cuenta de Peek.

La integración se conecta como lo hacen los asistentes, y las conexiones de Peek para asistentes están en beta. Si Peek dice «Peek aún no puede conectar asistentes a esta cuenta.», todavía no se puede configurar la integración.

## ¿Cómo instalo la integración de Peek?

Desde el Marketplace de Home Assistant, con su enlace, porque Peek aún no está en el catálogo del Marketplace. En la versión 2026.10, usa HACS o instálala a mano.

### Desde el Marketplace (Home Assistant 2026.11 y posteriores)

:::steps
- Abre «Settings › Marketplace» | Selecciona «Add from link», escribe `https://github.com/plhery/peek-ha` y selecciona «Add». La primera vez, el Marketplace te pide que conectes GitHub.
- Instala Peek | Abre Peek en la lista y selecciona «Install» y luego «Set up».
- Inicia sesión en Peek | Tu navegador abre Peek. Comprueba lo que pide Home Assistant (ver tus paquetes, y añadirlos y archivarlos) y selecciona «Permitir».
- Vuelve a Home Assistant | Peek te devuelve a través de My Home Assistant (my.home-assistant.io). La primera vez, te pide la dirección de tu Home Assistant.
:::

### Con HACS (Home Assistant 2026.10)

:::steps
- Añade un repositorio personalizado | En HACS, añade `https://github.com/plhery/peek-ha` como repositorio personalizado de tipo «Integration».
- Descarga Peek | Luego reinicia Home Assistant.
- Añade la integración | Ve a «Settings › Devices & services › Add integration», elige Peek, inicia sesión en Peek y selecciona «Permitir».
:::

También puedes instalarla a mano: copia la carpeta `custom_components/peek` en la carpeta `custom_components` de tu configuración, reinicia y añade la integración como arriba.

## ¿Cómo añado la tarjeta Peek parcels a mi panel?

La integración carga por sí sola su tarjeta en todos los paneles: no hay ningún recurso que añadir. Una página que ya estaba abierta mientras configurabas Peek aún no tiene la tarjeta: recárgala antes, o cierra la app de Home Assistant y vuelve a abrirla.

:::steps
- Edita un panel | Ábrelo y selecciona el lápiz. En un panel que Home Assistant creó por ti, primero te pide que tomes el control.
- Selecciona «Add card» | Luego la pestaña «By card». La pestaña «By entity» busca entidades, no tarjetas.
- Elige «Peek parcels» | Busca «Peek», elige «Peek parcels» y guarda.
:::

La tarjeta tiene dos estilos: una lista, el predeterminado, con una fila por paquete que muestra la furgoneta de su transportista, su etapa y su día; o una tarjeta por paquete con los colores de su transportista, como los widgets de Peek para iPhone y Android, con Pip, la ciudad del último escaneo, el nombre del paquete, su etapa y su día. Los dos siguen las secciones de Peek, empezando por los paquetes que te necesitan, y terminan con «+N more» cuando hay más paquetes que filas.

La tarjeta sigue tu tema, claro u oscuro, y tu idioma, si es inglés o francés. En YAML es `type: custom:peek-parcels-card`; el README de la integración enumera sus opciones.

## ¿Puedo usarla en automatizaciones o preguntar a Assist?

Sí. El README trae cuatro automatizaciones listas para usar:

- una notificación en tu móvil cuando un paquete sale a reparto;
- añadir los números de seguimiento de tus correos de envío, leídos con la integración IMAP de Home Assistant;
- un mensaje por la mañana con lo que llega hoy;
- archivar lo que se ha entregado, cada noche.

Cuando añades paquetes a partir de un texto, Peek añade cada número de seguimiento que encuentra, hasta 10 a la vez, y solo guarda los números y el nombre que le das, así que, cuando puedas, envía solo la parte del correo sobre el envío.

Cuando una de las entidades de Peek está expuesta a Assist («Settings › Voice assistants › Expose»), puedes preguntar «¿qué llega hoy?» o dónde está tu paquete de una tienda. La herramienta de Peek en Assist solo lee: nunca añade ni archiva paquetes. Lo que devuelve (nombres, tiendas, transportistas, etapas, días previstos y el lugar del último escaneo) va al agente de conversación que hayas elegido, que puede ser un servicio en la nube.

## ¿Qué no puede hacer?

- **Borrar o editar paquetes.** Home Assistant solo pide ver tus paquetes, y añadirlos y archivarlos. Puedes restaurar un paquete archivado en Peek.
- **Conectar Claude, ChatGPT o Gemini.** Se conectan a Peek por su cuenta: consulta la guía [Sigue tus paquetes en ChatGPT, Claude o Gemini](guide:ai-assistants).

## ¿Qué guarda Home Assistant y cómo quito la integración?

Home Assistant solo habla con Peek, en peektracker.com, y guarda los tokens del inicio de sesión en su configuración, como cualquier integración. Su historial registra los recuentos de los sensores y los eventos «Parcel updates», con el nombre del paquete, su transportista, su etapa y su último lugar, pero no las listas de paquetes de los sensores. La tarjeta funciona en tu navegador con los datos del propio Home Assistant y no carga nada de ningún otro sitio.

Para quitarla, borra Peek en «Settings › Devices & services»: Home Assistant le devuelve su acceso a Peek, que olvida la conexión, y tus paquetes siguen en Peek. Si en ese momento no se puede contactar con Peek, o para cortar el acceso de Home Assistant desde el lado de Peek, selecciona «Desconectar» junto a Home Assistant en la web de Peek (no en sus apps), en «Ajustes» → «Entregas» → «Asistentes». Una integración que conserves después muestra «Authentication expired for Peek»; al reconfigurarla empieza el mismo inicio de sesión.

:::sources
- [Peek for Home Assistant: README (en inglés)](https://github.com/plhery/peek-ha) – qué añade, versiones, instalación, inicio de sesión, la tarjeta, automatizaciones, Assist, privacidad, cómo quitarla
- [Peek for Home Assistant: hacs.json](https://github.com/plhery/peek-ha/blob/main/hacs.json) – la versión más antigua de Home Assistant en la que se instala, 2026.10
- [Home Assistant: Marketplace (en inglés)](https://www.home-assistant.io/integrations/marketplace/) – Settings › Marketplace, Add from link, conectar GitHub, Install, solo administradores, sustituye a HACS desde la 2026.11
- [Peek: Conectar Peek a Claude, ChatGPT o Gemini](https://peektracker.com/es/assistants) – los pasos de Home Assistant en la página de Peek, 2026.11 o posterior
- [Peek en GitHub: la configuración de cada asistente](https://github.com/plhery/peek-delivery-tracker/blob/main/src/assistants/setups.tsx) – los nombres de los menús de Home Assistant
- [Peek en GitHub: textos en español](https://github.com/plhery/peek-delivery-tracker/blob/main/shared/locales/es.json) – versión, pasos, cuenta, beta
- [Peek en GitHub: Ajustes, Asistentes](https://github.com/plhery/peek-delivery-tracker/blob/main/src/components/Assistants.tsx) – asistentes conectados, Desconectar
- [Peek: ayuda (en inglés)](https://peektracker.com/support.html) – iniciar sesión con un código de seis cifras
- [Peek en GitHub: condiciones (en inglés)](https://github.com/plhery/peek-delivery-tracker/blob/main/TERMS.md) – gratis, sin tarifas ni suscripción
:::
