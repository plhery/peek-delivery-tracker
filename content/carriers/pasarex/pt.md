---
title: Rastreio PasarEX: códigos Amazon AMZPSR, alfândega e apoio
description: Acompanha importações PasarEX (Pasar Express) na Colômbia, Chile, Panamá e Barbados: códigos AMZPSR, alfândega, regras PQR e linhas telefónicas.
slug: rastreio-pasarex
published: 2026-11-24
updated: 2026-11-24
---

A PasarEX (marca comercial da *PASAR EXPRESS SAS*, empresa colombiana fundada em 1993) presta serviços de correio expresso, carga aérea e entrega de última milha para plataformas de comércio eletrónico. Os seus sites cobrem a **Colômbia**, o **Chile**, o **Panamá** e os **Barbados**, incluindo um centro logístico em Miami e transporte multimodal desde a China.

A PasarEX desalfandega encomendas diretamente como despachante aduaneiro, pelo que o seu rastreio acompanha o percurso desde o manifesto até à chegada da carga consolidada, alfândega, armazéns e entrega local.

## Formatos de números de rastreio PasarEX

O Peek aceita números PasarEX de 6 a 30 letras e dígitos com pelo menos um dígito. Um formato pertence exclusivamente à PasarEX:

| Formato (inventado) | O que é |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 dígitos (15 carateres) — um código Amazon pesquisado no rastreio PasarEX; nenhuma outra transportadora no Peek usa esta forma. |

Se a confirmação da encomenda incluir um URL direto de rastreio (`tracking.pasarex.com/?awb=...`), colar essa ligação no Peek extrai a guia e atribui imediatamente a PasarEX. Para outros formatos de comércio eletrónico, consulta o [guia de formatos de números de rastreio](guide:tracking-number-formats) e o [guia de encomendas Amazon](guide:amazon-orders).

## O que significam os estados de rastreio da PasarEX

A PasarEX agrupa as linhas de registo sob categorias gerais no seu rastreio (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), apresentando frases individuais em espanhol ou inglês juntamente com cidade e data/hora.

| Texto de registo PasarEX | O que significa |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | Os dados eletrónicos do manifesto chegaram à PasarEX antes de a encomenda física dar entrada no hub de importação. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | A carga aérea consolidada chegou à estação de entrada da PasarEX (como Bogotá) antes da alfândega. |
| `Entry to station for customs processing.` | A encomenda deu entrada na estação aduaneira para desalfandegamento de importação. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | A PasarEX desalfandegou o envio e transferiu-o para as operações nacionais. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Registada num armazém PasarEX ou estação de destino entre troços do percurso nacional. |
| `Package in transit` / `Package in ground transit` | Em transporte rodoviário ou ligação interna entre estações PasarEX. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Aviso de serviço sobre possíveis atrasos por perturbações locais; o envio prossegue depois. |
| `Shipment left to local route for final delivery` | Saiu hoje em rota local com o estafeta para entrega final. |
| `Shipment delivered in perfect condition` | Entregue na morada do destinatário. |

## Desalfandegamento, reclamações PQR e expectativas de entrega

Como a PasarEX desalfandega diretamente as importações transfronteiriças antes da distribuição nacional:

- **Antes de a encomenda chegar:** enquanto o rastreio mostrar apenas `Information (Manifest) of the shipment received.` ou `Orden de pedido recibida - paquete no arribado`, o pedido foi registado mas o volume ainda não chegou à PasarEX. Conta com uma pausa até surgir `Reception of consolidated cargo in import.` ou `Carga consolidada para importación recibida`. Consulta o [guia da alfândega](guide:customs) e [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Regras PQR e indemnização na Colômbia (Resolução CRC 5050 de 2016):** em [PasarEX Colombia Support](https://pasarex.com/co/soporte/), a Pasar Express SAS publica os prazos legais ao abrigo da Lei 1369/2009 e Resolução 5050/2016. Os destinatários devem apresentar reclamação por espoliação ou dano no prazo de **5 dias úteis** após a receção, enquanto os remetentes dispõem de **10 dias de calendário** (nacional) ou **6 meses** (internacional) por perda, espoliação ou dano (contratos empresariais podem ter termos próprios). Os pedidos exigem cópias da guia aérea (*Guía Aérea*), fatura comercial, fatura Pasar Express, lista de artigos, descrição da embalagem e fotografias. A PasarEX resolve normalmente os PQR em **15 dias úteis** (prorrogáveis se for necessária investigação), e os utilizadores têm **10 dias úteis** após notificação para recorrer (*recurso de reposición y en subsidio de apelación*).

## Seguir a PasarEX com o Peek

Cola o teu número ou uma ligação `tracking.pasarex.com/?awb=...` no Peek, que acompanha mais de 4 500 transportadoras. Uma ligação segue logo para a PasarEX. Para um número isolado, o Peek nunca pergunta à PasarEX sozinho: pode consultar outras transportadoras com a mesma forma, consulta depois os serviços de rastreio («A verificar serviços de seguimento…») e só atribui a encomenda à PasarEX se um serviço de rastreio a indicar, sem outra transportadora, com passos datados. Toca portanto em «Alterar» e escolhe PasarEX (em «Corresponde a este número» para um código `AMZPSR`), ou usa «Alterar transportadora» numa encomenda já adicionada.

Como o rastreio da PasarEX exige marcar um reCAPTCHA em cada pesquisa, o Peek lê-o através do [adaptador aberto PasarEX do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) com um navegador auxiliar. O Peek recolhe o texto, a cidade e a hora de cada registo, ordenando-os do mais recente para o mais antigo em vez de seguir os títulos da PasarEX. A PasarEX indica horas locais sem desfasamento em relação a UTC: o Peek data cada registo no fuso horário da respetiva cidade (hora de Nova Iorque para Miami, hora de Bogotá para Bogotá, Medellín e Cali, e hora de Santiago, Panamá ou Barbados para esses locais), um registo sem cidade no fuso do registo vizinho ou, na falta dele, na hora de Bogotá, apresentando todos na hora do teu dispositivo. O rastreio da PasarEX não publica data estimada de entrega, peso nem comprovativo de entrega.

O Peek traduz as frases da PasarEX em etapas claras: **Etiqueta criada** para receção do manifesto antes da chegada física, **Em trânsito** da chegada da carga consolidada até à libertação aduaneira, entrada em armazém e trânsito rodoviário (os textos da PasarEX passam diretamente do manifesto para a chegada consolidada sem registo separado de **Aceite pela transportadora**), **Na alfândega** durante o tratamento aduaneiro, **Em distribuição** quando sai em rota local, e **Entregue** após a entrega final.

O Peek verifica o rastreio da PasarEX no máximo de hora a hora — o menor intervalo permitido pela PasarEX entre duas consultas, também após uma tentativa falhada, tenhas ou não a encomenda aberta; não faz verificações de 2 em 2 minutos no dia em que a encomenda está em distribuição. Após sete dias em «Etiqueta criada», verifica de 6 em 6 horas. Quando o rastreio da PasarEX não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, e de 3 em 3 horas após um dia sem novidades.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a PasarEX.» Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a PasarEX.»

Escolhe **Todos os registos**, **Passos importantes** ou **Só a entrega** nas definições de alertas do Peek para escolher que atualizações te notificam.

## Contactar a PasarEX

A PasarEX disponibiliza números de telefone e endereços de e-mail para cada país:

- **Colômbia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (também linha PQR de Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; e-mail `sac@pasar.net` (apoio) e `pqr@pasar.net` (PQR/reclamações)
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; e-mail `clgestionpe@pasar.net`
- **Panamá ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; e-mail `pagestionpe@pasar.net` e `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Apresentação da empresa:** [PasarEX Colombia](https://pasarex.com/co/) e [Nosotros](https://pasarex.com/co/nosotros/)

## Perguntas frequentes

### O que é um número de rastreio `AMZPSR`?

`AMZPSR` seguido de 9 dígitos (15 carateres no total, como `AMZPSR482917360`) é um número Amazon pesquisado pelo rastreio PasarEX. Nenhuma outra transportadora no Peek tem este formato, mas o Peek não pergunta à PasarEX sozinho: escolhe PasarEX em «Corresponde a este número».

### Por que diz a minha encomenda PasarEX `Orden de pedido recibida - paquete no arribado`?

Significa que a PasarEX recebeu o manifesto do envio, mas a encomenda física ainda não chegou à estação de importação da PasarEX no destino. Os registos retomam quando o voo consolidado chega.

### Por que atualiza o Peek a PasarEX com menos frequência do que outras transportadoras?

A PasarEX permite consultar o seu rastreio no máximo uma vez por hora, incluindo após falhas, pelo que o Peek consulta de hora a hora. O seu rastreio exige ainda confirmar um reCAPTCHA em cada pesquisa.

### Quanto tempo tenho para apresentar uma reclamação por danos na PasarEX na Colômbia?

Segundo a Resolução colombiana CRC 5050/2016 publicada em [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), os destinatários dispõem de **5 dias úteis** após a entrega para reclamar por danos ou falta de conteúdo, enquanto os remetentes têm 10 dias de calendário (nacional) ou 6 meses (internacional) por perda ou dano.

:::sources
- [PasarEX Colombia](https://pasarex.com/co/)
- [PasarEX Colombia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colombia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [adaptador PasarEX do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
