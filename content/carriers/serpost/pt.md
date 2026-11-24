---
title: Rastreio Serpost: códigos S10 do Peru, alfândega e apoio
description: Acompanha uma encomenda Serpost (Servicios Postales del Perú): códigos S10 PE de 13 caracteres, alfândega SUNAT, levantamento em estação, prazos e Lima.
slug: rastreio-serpost
published: 2026-11-24
updated: 2026-11-24
---

A **Serpost** (*Servicios Postales del Perú S.A.*) é o operador postal nacional estatal do Peru, tutelado pelo Ministério dos Transportes e Comunicações (MTC) ([gob.pe/serpost](https://www.gob.pe/serpost)). Além do correio e encomendas domésticas no Peru, assegura envios internacionais de saída (incluindo [EMS](carrier:ems) e *Exporta Fácil*) e correio internacional de entrada da União Postal Universal (UPU), com seguimento online em [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea). Podes consultar aí um objeto postal S10 peruano ou internacional a chegar ao Peru, ou colar o teu código de 13 caracteres na caixa acima.

## Números de rastreio Serpost (formato UPU S10 de 13 caracteres)

A Serpost acompanha códigos de barras padrão UPU S10 de 13 caracteres: duas letras de serviço, 8 dígitos sequenciais, 1 dígito de controlo S10 e o código de país de origem de 2 letras (`PE` para objetos enviados do Peru, ou o código do país estrangeiro como `US`, `CN`, `ES` ou `JP` para encomendas internacionais que entram no Peru):

| Formato (inventado) | O que é |
| --- | --- |
| `RR482917367PE` | Correio registado de 13 caracteres enviado do Peru (`RR` + 9 dígitos com dígito de controlo S10 válido + `PE`). |
| `EE739184627PE` | Encomenda expresso internacional [EMS](carrier:ems) de 13 caracteres enviada do Peru (`EE` + 9 dígitos com dígito de controlo S10 válido + `PE`). |

Cartas económicas sem registo e sem código de barras S10 de 13 caracteres não surgem no rastreio online da Serpost. Em encomendas internacionais vindas do estrangeiro (por exemplo terminadas em `CN`, `US` ou `GB`), assim que o objeto chega ao Peru, selecionar a Serpost mostra os registos locais da alfândega peruana (*Aduana*) e da estação postal sob esse mesmo código S10 de 13 caracteres. Consulta o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da Serpost

A Serpost publica descrições em espanhol que indicam a administração postal peruana específica (*Administración Postal*) onde cada registo foi feito:

| A Serpost indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `ENVÍO ADMITIDO EN ...` | Aceite ao balcão da estação Serpost peruana indicada. | «Aceite pela transportadora» |
| `ENVÍO LLEGÓ A SERPOST`, `ENVÍO EN PROCESAMIENTO`, `ENVÍO EN TRÁNSITO` | Encomenda internacional recebida pela Serpost no Peru, em triagem na sede ou em trânsito doméstico. | «Em trânsito» |
| `ENVÍO SUJETO A TRÁMITE ANTE ADUANA` | Selecionada pela alfândega peruana (SUNAT) para desalfandegamento, avaliação ou verificação de impostos de importação. | «Na alfândega» |
| `ENVÍO SIN OBSERVACIÓN POR LA ADUANA` | Desalfandegada sem observações pela alfândega peruana e devolvida à Serpost. | «Em trânsito» |
| `ENVÍO SALIÓ DE PERÚ`, `ENVÍO RUMBO AL PAÍS DE DESTINO` | Objeto internacional de saída que deixou o Peru rumo ao operador postal do país de destino. | «Em trânsito» |
| `ENVÍO LLEGÓ A LA ADMINISTRACIÓN POSTAL ...` | Chegou à estação postal local peruana que prepara a distribuição. | «Em trânsito» |
| `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, `SALIÓ A DISTRIBUCIÓN` | Em distribuição com um carteiro Serpost para entrega na morada. | «Em distribuição» |
| `ENVÍO DISPONIBLE PARA ENTREGA O RECOJO` | Pronta para levantamento na estação Serpost atribuída. | «Pronta para levantamento» |
| `INTENTO FALLIDO DE ENTREGA`, `SE INTENTÓ ENTREGAR PERO FUE IMPOSIBLE` | Não foi possível concluir a entrega (destinatário ausente ou problema de acesso); verifica se há aviso para levantamento na estação. | «Tentativa de entrega» |
| `ENTREGADO`, `ENVÍO ENTREGADO`, `FUE ENTREGADO` | Entregue ao destinatário ou levantada na estação postal. | «Entregue» |

## Prazos, alfândega e prazos oficiais de reclamação

Segundo [Serviços de Envio Serpost](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) e [Reclamações e Consultas Serpost](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru):

- **Prazos publicados para cartas:** **Expreso** nacional **48–72 horas máx.**; **Estándar** nacional **5–7 dias úteis**; **Económico** nacional **7–10 dias úteis**; **Expreso** internacional **7–10 dias úteis**; **Económico** internacional **10–30 dias úteis**. Apenas cartas Expreso e Estándar podem ser seguidas online.
- **Alfândega peruana (`ENVÍO SUJETO A TRÁMITE ANTE ADUANA`):** o objeto fica retido para trâmites aduaneiros e só avança para entrega depois de a alfândega o libertar (`ENVÍO SIN OBSERVACIÓN POR LA ADUANA`). O rastreio da Serpost pode apresentar um aviso de chegada (*Aviso de llegada*). Consulta o guia de [encomendas retidas na alfândega](guide:customs).
- **Prazos oficiais de reclamação e resposta:** reclamações de envios **EMS** podem ser apresentadas desde o dia seguinte ao prazo previsto até **4 meses** após a expedição (resposta em **30 dias de calendário**); reclamações de correio registado internacional são aceites após **20 dias de calendário** e de encomendas internacionais após **30 dias de calendário**; reclamações domésticas têm resposta em **16 dias úteis** (indemnização doméstica igual a **3× o porte pago**; indemnização internacional segundo regras da UPU).

## Encomenda Serpost atrasada, na alfândega ou desaparecida

- **Pausas silenciosas no correio internacional:** envios de saída parados após `ENVÍO SALIÓ DE PERÚ` ou envios de entrada vindos do estrangeiro aguardam frequentemente vários dias ou semanas entre a expedição aérea e a entrada na alfândega. Consulta o operador postal nacional de destino (como o [USPS](carrier:usps)) quando um envio `...PE` sair do Peru. Vê [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Apresentar uma reclamação (*reclamo*) à Serpost:** envia o formulário por email para `servicioalcliente@serpost.com.pe`, liga para `(01) 511-5110` ou dirige-te à estação Serpost mais próxima — receberás um número de processo (*número de expediente*) por email para acompanhar o andamento. Vê também [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel), [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente Serpost e contactos em Lima

Canais oficiais publicados em [Serpost Contacto (gob.pe)](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias):

- **Call Center (Peru):** `(01) 511-5110`.
- **Central principal:** `511-5000` (linhas da sede `(511) 533-2001` / `533-2002` / `533-2003` / `533-2004`).
- **Email de apoio ao cliente e reclamações:** `servicioalcliente@serpost.com.pe` (webmaster: `webmaster@serpost.com.pe`).
- **Sede (*Sede Central*):** Av. Tomás Valle cdra. 7 s/n, Los Olivos, Lima 15302, Peru.
- **Rastreio online e portal governamental:** [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) e [gob.pe/serpost](https://www.gob.pe/serpost).

> Desconfia de SMS falsos que dizem que uma encomenda Serpost está bloqueada até pagares uma pequena taxa através de uma ligação desconhecida. Confirma sempre os avisos aduaneiros diretamente em [serpost.com.pe/Cliente/SegumientoLinea](https://www.serpost.com.pe/Cliente/SegumientoLinea) ou nos canais oficiais `gob.pe` / SUNAT. Vê [mensagens SMS fraudulentas de entrega](guide:delivery-scam-texts).

## Acompanhar uma encomenda Serpost com o Peek

Quando introduzes um código S10 peruano de 13 caracteres (`RR482917367PE` ou `EE739184627PE`, inventados) no [Peek](/), o Peek valida o dígito de controlo S10 e consulta primeiro a Serpost e depois a Chronopost. Para um código S10 estrangeiro que chega ao Peru, ou para mudar manualmente para a Serpost, toca em «Alterar» ao adicionar ou em «Alterar transportadora» no ecrã da encomenda e escolhe **Serpost** (pesquisar «SERPOST», «Peru Post» ou «Servicios Postales del Perú» também funciona). O Peek apresenta «Serpost tem esta encomenda.» assim que a Serpost devolve registos.

O Peek lê o rastreio da Serpost e mostra a etapa («Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Entregue»), o texto em espanhol da Serpost sem o convite WhatsApp da estação, o nome da estação e a hora de entrega. Quando a Serpost associa o mapa de uma estação no Peru, o Peek coloca o registo nesse ponto do mapa; quando o objeto está em distribuição, pronto para levantamento ou entregue, o Peek indica o Peru como destino. A Serpost fornece as horas no relógio de Lima: o Peek data-as na hora de Lima e mostra-as no relógio do teu dispositivo. O rastreio da Serpost não indica data estimada de entrega, peso nem nome de quem assinou.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Serpost apresenta `ENVÍO EN PROCESO DE DISTRIBUCIÓN`, de 2 em 2 minutos a partir das 6h00 hora de Lima enquanto o rastreio da Serpost responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da Serpost não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Serpost.», com a ligação «Pede ajuda a Serpost» para a página de contactos da Serpost em gob.pe. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Serpost.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode desalfandegar na SUNAT, abrir reclamações nem contactar a Serpost por ti.

## Perguntas sobre o rastreio Serpost

### Como é um número de rastreio da Serpost?

A Serpost acompanha códigos UPU S10 de 13 caracteres — duas letras, 9 dígitos (terminando num dígito de controlo) e o código de país de origem de 2 letras como `PE` (por exemplo `RR482917367PE` ou `EE739184627PE`, inventados), bem como códigos S10 estrangeiros a entrar no Peru.

### O que significa `ENVÍO SUJETO A TRÁMITE ANTE ADUANA`?

Significa que a alfândega peruana (SUNAT) selecionou a encomenda internacional para desalfandegamento ou verificação de impostos de importação antes de a Serpost a poder libertar para entrega local ou levantamento na estação.

### Quanto tempo demora a Serpost a entregar?

A Serpost publica prazos para cartas em [gob.pe](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost): **48–72 horas** em Expreso nacional, **5–7 dias úteis** em Estándar nacional, **7–10 dias úteis** em Económico nacional ou Expreso internacional, e **10–30 dias úteis** em Económico internacional.

### Como contactar o apoio ao cliente da Serpost ou apresentar reclamação?

Liga para o Call Center da Serpost através do `(01) 511-5110`, escreve para `servicioalcliente@serpost.com.pe` ou dirige-te a uma estação postal Serpost; todos os canais constam em [gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias).

:::sources
- [Serpost: Seguimiento en Línea](https://www.serpost.com.pe/Cliente/SegumientoLinea) – portal oficial de rastreio online e Call Center `(01) 511-5110`
- [Serpost: Portal Institucional (gob.pe)](https://www.gob.pe/serpost) – portal governamental oficial *Servicios Postales del Perú S.A.* (MTC)
- [Serpost: Contacto y Números de Atención](https://www.gob.pe/institucion/serpost/contacto-y-numeros-de-emergencias) – Call Center `511-5110`, central `511-5000`, `servicioalcliente@serpost.com.pe` e sede em Los Olivos (página de ajuda ligada pelo Peek)
- [Serpost: Reclamos y Consultas](https://www.gob.pe/institucion/serpost/pages/33693-reclamos-y-consultas-servicios-postales-del-peru) – janelas de reclamação (20 d registado, 30 d encomendas, 4 m EMS), tempos de resposta (16 d nacional / 30 d EMS) e regras de indemnização
- [Serpost: Envíos Nacionales e Internacionales](https://www.gob.pe/institucion/serpost/pages/20800-enviar-cartas-dentro-y-fuera-del-pais-con-serpost) – prazos de entrega publicados para cartas, por serviço
- [Universal Parcel Scraper: serpost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/serpost) – validação do dígito de controlo UPU S10, consulta `SegumientoLinea`, regras de estados em espanhol, extração do nome de estação e coordenadas no Peru e horas `America/Lima`
:::
