---
title: Rastreio Serpost: códigos S10 do Peru, alfândega e ajuda
description: Rastreie encomendas dos Correios do Peru (Serpost): códigos S10 PE de 13 caracteres, status alfandegários SUNAT, retirada, prazos de reclamação e contato.
slug: serpost-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Serpost** (*Servicios Postales del Perú S.A.*) é o operador postal nacional estatal do Peru, vinculado ao Ministério dos Transportes e Comunicações (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Além de cartas e encomendas domésticas em todo o Peru, ela opera serviços internacionais de saída (como EMS e *Exporta Fácil*) e recebe remessas da União Postal Universal (UPU) vindas do exterior. O rastreamento on-line fica em [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea): consulte ali ou cole seu código S10 de 13 caracteres na caixa acima.

## Códigos de rastreio Serpost (padrão UPU S10 de 13 caracteres)

A Serpost acompanha códigos de barras UPU S10 de 13 caracteres: duas letras de serviço, 8 dígitos seriais, 1 dígito verificador S10 e 2 letras do país de origem (`PE` para envios postados no Peru, ou a sigla do país de origem como `US`, `CN`, `ES` ou `JP` em encomendas vindas do exterior):

| Formato (inventado) | O que é |
| --- | --- |
| `RR482917367PE` | Objeto registrado de 13 caracteres enviado do Peru (`RR` + 9 dígitos com dígito verificador S10 válido + `PE`). |
| `EE739184627PE` | Encomenda expressa internacional EMS de 13 caracteres enviada do Peru (`EE` + 9 dígitos com dígito verificador S10 válido + `PE`). |

Cartas econômicas sem código de barras S10 de 13 caracteres não aparecem no rastreador da Serpost. Em encomendas internacionais vindas de fora (terminadas por exemplo em `CN`, `US` ou `GB`), assim que o objeto entra no Peru, selecionar Serpost mostra as leituras locais da alfândega peruana (*Aduana*) e das agências sob o mesmo código S10. Veja [formatos de código de rastreio](guide:tracking-number-formats).

## Significado dos status de rastreio Serpost

A Serpost publica descrições em espanhol citando a administração postal peruana (*Administración Postal*) onde ocorreu cada leitura:

| A Serpost exibe | O que significa para você | O que o Peek mostra |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Postado no balcão da agência Serpost indicada no Peru. | "Recebida pela transportadora" |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Encomenda internacional recebida pela Serpost no Peru, em triagem na central ou em trânsito interno. | "Em trânsito" |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Selecionada pela alfândega peruana (SUNAT) para desembaraço, conferência de valor ou tributação. | "Na alfândega" |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Liberada pela alfândega peruana sem pendências e devolvida à Serpost. | "Em trânsito" |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Objeto internacional despachado do Peru rumo ao operador postal do país de destino. | "Em trânsito" |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | Chegou à agência local peruana responsável pela preparação da entrega. | "Em trânsito" |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | Saiu com o carteiro da Serpost para entrega em domicílio. | "Saiu para entrega" |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Disponível para retirada na agência Serpost designada. | "Pronta para retirada" |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | Não foi possível concluir a entrega (ausência ou dificuldade de acesso); verifique o aviso de retirada na agência. | "Tentativa de entrega" |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | Entregue ao destinatário ou retirado na agência. | "Entregue" |

## Prazos de entrega, alfândega e prazos oficiais de reclamação

De acordo com [Serpost Serviços de Envio](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) e [Serpost Reclamações e Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru):

- **Prazos divulgados para cartas:** **Expreso** nacional em **48–72 horas no máximo**; **Estándar** nacional em **5–7 dias úteis**; **Económico** nacional em **7–10 dias úteis**; **Expreso** internacional em **7–10 dias úteis**; **Económico** internacional em **10–30 dias úteis**. Apenas cartas Expreso e Estándar possuem rastreamento on-line.
- **Alfândega peruana (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`):** o objeto fica retido para trâmites aduaneiros e só segue para entrega após liberação (`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`). O site da Serpost pode exibir um aviso de chegada (*Aviso de llegada*). Veja [encomendas retidas na alfândega](guide:customs).
- **Prazos oficiais de reclamação:** para envios **EMS**, a reclamação pode ser registrada a partir do dia seguinte ao fim do prazo de entrega até **4 meses** após a postagem (resposta em até **30 dias corridos**); cartas registradas internacionais após **20 dias corridos** e encomendas internacionais após **30 dias corridos**; reclamações nacionais recebem resposta em até **16 dias úteis** (indenização nacional de **3× o valor da postagem**; internacional conforme regras da UPU).

## Encomenda Serpost atrasada, na alfândega ou sumida

- **Intervalo sem atualizações em envios internacionais:** pacotes de saída após `ENVÍO SALIÓ DE PERÚ` ou de entrada vindos do exterior costumam aguardar dias ou semanas entre o embarque aéreo e o registro aduaneiro. Consulte o correio nacional de destino (como [USPS](carrier:usps)) depois que um envio `...PE` deixar o Peru. Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).
- **Como abrir reclamação (*reclamo*) na Serpost:** envie seu formulário para `servicioalcliente@serpost.com.pe`, ligue para `(01) 511-5110` ou compareça à agência Serpost mais próxima — você receberá um número de processo (*número de expediente*) por e-mail para acompanhar. Veja [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Serpost em Lima

Canais oficiais publicados em [Serpost Contacto (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias):

- **Call Center (Peru):** `(01) 511-5110`.
- **Central telefônica:** `511-5000` (linhas da sede `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **E-mail de atendimento e reclamações:** `servicioalcliente@serpost.com.pe` (webmaster: `webmaster@serpost.com.pe`).
- **Sede Central:** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Peru.
- **Rastreador on-line e portal governamental:** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) e [gob.pe/serpost](https://www.gob.pe/serpost).

> Cuidado com SMS falsos dizendo que uma encomenda Serpost está bloqueada aguardando uma taxa em link desconhecido. Confirme qualquer aviso aduaneiro diretamente em [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) ou nos canais oficiais `gob.pe` / SUNAT. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Acompanhe sua encomenda Serpost no Peek

Ao informar um código S10 peruano de 13 caracteres (`RR482917367PE` ou `EE739184627PE`, inventados) no [Peek](/), ele valida o dígito verificador S10 e consulta a Serpost primeiro e depois a Chronopost. Para um código S10 estrangeiro chegando ao Peru, ou para selecionar a Serpost manualmente, toque em "Trocar" ao adicionar ou "Trocar transportadora" na tela do pacote e escolha **Serpost** (buscar "SERPOST", "Peru Post" ou "Servicios Postales del Perú" também funciona). O Peek mostra "Serpost está com esta encomenda." assim que a Serpost retorna registros.

O Peek lê o rastreador da Serpost e mostra a etapa ("Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Entregue"), o texto original em espanhol sem o convite de WhatsApp da agência, o nome da unidade e o horário de entrega. Quando a Serpost informa o link de mapa de uma agência no Peru, o Peek marca esse ponto no mapa; quando sai para entrega, fica pronta para retirada ou é entregue, exibe o Peru como destino. Os horários da Serpost seguem o fuso de Lima e o Peek os converte para o relógio do seu aparelho. A Serpost não informa data prevista de entrega, peso nem quem assinou.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Serpost exibir `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, a cada 2 minutos a partir das 6h no horário de Lima, enquanto o rastreador da Serpost responder. Nos demais casos, verifica de hora em hora. Após 12 horas somente em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta de hora em hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a Serpost não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Serpost.", com o link "Pedir ajuda a Serpost" para a página de contato da Serpost no gob.pe. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Serpost."

Você pode escolher alertas de "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek acompanha apenas as atualizações de rastreamento e não libera cargas na SUNAT, não registra reclamações nem contata a Serpost por você.

## Perguntas sobre o rastreio Serpost

### Como é um código de rastreio da Serpost?

A Serpost rastreia códigos UPU S10 de 13 caracteres — duas letras, 9 dígitos (terminando com dígito verificador) e duas letras do país de origem como `PE` (por exemplo, `RR482917367PE` ou `EE739184627PE`, inventados), além de códigos S10 estrangeiros que chegam ao Peru.

### O que significa `ENVÍO SUJETO A TRÁMITE ANTE ADUANA`?

Significa que a alfândega peruana (SUNAT) selecionou a encomenda internacional para desembaraço ou análise de tributos de importação antes que a Serpost possa liberá-la para entrega local ou retirada em agência.

### Quanto tempo a Serpost leva para entregar?

A Serpost publica os seguintes prazos para cartas em [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost): **48–72 horas** no Expreso nacional, **5–7 dias úteis** no Estándar nacional, **7–10 dias úteis** no Económico nacional ou Expreso internacional e **10–30 dias úteis** no Económico internacional.

### Como entrar em contato com a Serpost ou abrir uma reclamação?

Ligue para o Call Center da Serpost em `(01) 511-5110`, escreva para `servicioalcliente@serpost.com.pe` ou procure uma agência Serpost; todos os canais constam em [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – portal oficial de rastreamento on-line e Call Center `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – portal governamental oficial da *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Call Center `511-5110`, central `511-5000`, `servicioalcliente@serpost.com.pe` e sede em Los Olivos (URL de ajuda vinculada pelo Peek)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – prazos de reclamação (20d registrada, 30d encomendas, 4m EMS), prazos de resposta (16d úteis nacional / 30d corridos EMS) e regras de indenização
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – prazos publicados para cartas por modalidade
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – validação do dígito verificador UPU S10, consulta a `SegumientoLinea`, regras de status em espanhol, extração do nome da agência e coordenadas no Peru e horários em `America/Lima`
:::
