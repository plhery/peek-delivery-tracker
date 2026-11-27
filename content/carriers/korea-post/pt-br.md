---
title: Rastreamento Korea Post: códigos EMS KR, 13 dígitos e ajuda
description: Rastreie encomendas Korea Post (ePOST): códigos S10 com final KR, números domésticos de 13 dígitos, status EMS e coreanos, alfândega, prazos e suporte.
slug: korea-post-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Korea Post (*우정사업본부*, portal online *ePOST* / *인터넷우체국*) é a operadora postal nacional da Coreia do Sul, vinculada ao Ministério de Ciência e TIC. Ela realiza entregas domésticas de encomendas e correspondências registradas em toda a Coreia do Sul, além de envios internacionais **EMS** (Express Mail Service para mais de 100 destinos, mais 70+ países via EMS Premium), **K-Packet** e cartas/encomendas aéreas registradas. Você pode acompanhar remessas internacionais no [rastreador EMS em inglês da Korea Post](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp), envios domésticos no [ePOST Domestic Tracking](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp), ou colar o código na caixa acima.

## Códigos de rastreamento Korea Post: formatos KR S10 e 13 dígitos domésticos

Verifique o recibo da agência postal, a página do pedido na loja sul-coreana ou o e-mail de envio. A Korea Post usa dois formatos principais conforme o envio seja internacional ou dentro da Coreia do Sul:

| Formato (fictício) | O que é |
| --- | --- |
| `EM482917367KR` | Envio internacional **EMS** de mercadorias (formato UPU S10 terminado em `KR`; documentos normalmente começam com `EE`). |
| `RR482917367KR` | Correspondência registrada internacional ou item K-Packet (formato UPU S10: 2 letras + 9 dígitos com dígito verificador + `KR`). |
| `6482917364058` | Número doméstico sul-coreano de 13 dígitos para encomenda ou registrado (começando com os dígitos `1`–`6`). Compartilha o formato de 13 dígitos numéricos com Correos de Chile, [Delhivery](carrier:delhivery), [Econt](carrier:econt), Paquetexpress e outras. |

Na [visão geral EMS da Korea Post](https://ems.epost.go.kr/front.Introduction01.postal), prefixos `EE` identificam documentos EMS (dispensados de inspeção aduaneira de mercadorias), enquanto prefixos `EM` indicam envios EMS de mercadorias. Para outros códigos postais internacionais, veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Korea Post

Envios internacionais `KR` exibem linhas UPU/EMS em inglês preservando o horário local informado por cada correio nacional, enquanto envios domésticos de 13 dígitos mostram rótulos em coreano no Horário Padrão da Coreia (`Asia/Seoul`, UTC+9 o ano todo):

| A Korea Post informa (internacional / doméstico) | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `운송장출력` *(doméstico)* | Etiqueta de envio impressa antes da postagem na agência. | "Etiqueta criada" |
| `Posting/Collection` / `접수`, `집하완료`, `인수완료` | Aceito no balcão dos correios coreanos ou coletado por um carteiro da Korea Post. | "Recebida pela transportadora" |
| `Arrival at outward office of exchange`, `Ready for dispatch`, `Handed over to a transport company`, `Received by Air carriers`, `Departure from the Airport`, `Arrival at Destination Airport`, `Delivered to Destination Post`, `Arrival at inward office of exchange`, `Departure from inward office of exchange` / `발송`, `도착` | Processado no centro de intercâmbio internacional da Coreia (como Incheon), embarcado por via aérea, entregue ao correio de destino ou movimentado entre centros domésticos de triagem. | "Em trânsito" |
| `Ready for customs clearance` | Apresentado à alfândega no país de destino (ou fiscalização aduaneira de exportação/importação). | "Na alfândega" |
| `배달준비` *(doméstico)* | Preparado para entrega final pelo carteiro da agência local coreana. | "Saiu para entrega" |
| `Unsuccessful delivery` | Tentativa de entrega no destino não pôde ser concluída. | "Tentativa de entrega" |
| `Delivery complete` / `배달완료` | Entregue; o rastreador da Korea Post pode informar o nome de quem recebeu. | "Entregue" |

## Prazos EMS da Korea Post, alfândega e repasse local

Segundo a [Visão Geral do Serviço EMS](https://ems.epost.go.kr/front.Introduction01.postal), as [Condições de Envio por País](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) e a [Calculadora de Prazo e Preço](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) da Korea Post:

- **Prazos de trânsito EMS:** para documentos (códigos `EE`) postados em Seul até o meio-dia, a Korea Post indica dia seguinte ou 3–4 dias para cidades com voos diretos — como Tóquio, Hong Kong e Singapura — e 4–6 dias para demais destinos; sua página de visão geral indica 2–3 e 3–5 dias para documentos postados em Seul pela manhã. Acrescente um dia para postagens após o meio-dia ou fora de Seul, ou para endereços fora da cidade do centro de permuta de destino; de agências em regiões montanhosas ou ilhas, some os dias até o Centro Internacional de Logística Postal. Mercadorias (códigos `EM`) aguardam ainda a inspeção aduaneira no destino. O dia da postagem e feriados não contam.
- **Declarações aduaneiras:** documentos e impressos usam declaração CN22, enquanto mercadorias e bens tributáveis exigem declaração CN23 (além de fatura comercial e licenças aplicáveis em remessas comerciais). Itens proibidos e restritos constam no [Guia de Itens Proibidos/Restritos do ePOST](https://ems.epost.go.kr/front.Introduction04.postal).
- **Repasse ao correio de destino:** assim que seu item `KR` chega ao seu país (`Delivered to Destination Post` / `Arrival at inward office of exchange`), o correio nacional local (por exemplo [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [DHL](carrier:dhl), [Japan Post](carrier:japan-post) ou [Australia Post](carrier:australia-post)) assume cobrança de tributos, tentativas de entrega e guarda em agência pelas regras locais. Acompanhe o mesmo código `...KR` também no site do seu correio local para opções completas de entrega. Veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Korea Post atrasada, parada em trânsito ou extraviada

- **Pausa sem atualizações após `Departure from the Airport`:** malas internacionais EMS e K-Packet frequentemente ficam vários dias ou mais de uma semana sem novas leituras entre a partida da Coreia do Sul e a entrada no centro de permuta de destino (`Arrival at inward office of exchange`). Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Parado em `Ready for customs clearance`:** consulte o site do correio nacional do seu país com o mesmo código `...KR` para verificar se há impostos de importação, IVA ou taxa postal pendentes para liberação.
- **Itens perdidos, danificados ou extraviados:** em compras online, fale primeiro com o vendedor ou marketplace na Coreia; o ePOST aceita pedido de busca (*행방조사청구*) online ou por escrito e publica suas regras de indenização (*손해배상규정*). Veja [entregue mas não recebido](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contatos da Korea Post

Contatos oficiais publicados na [Central de Atendimento ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm), no [Portal Principal ePOST](https://www.epost.go.kr/main.retrieveMainPage.comm) e na [Visão Geral EMS](https://ems.epost.go.kr/front.Introduction01.postal):

- **Central de Atendimento Korea Post (dentro da Coreia do Sul):** `1588-1300` (dias úteis `09:00–18:00` KST, fechado aos sábados e feriados; tarifa paga pelo chamador).
- **De fora da Coreia do Sul:** `+82-42-609-4295`, no mesmo horário.
- **E-mail:** `help@posa.or.kr`.
- **FAQ online e consultas:** [Central de Atendimento ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) e a opção *e-mail EMS Inquiry* em [trace.epost.go.kr](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp).
- **Endereço no rodapé do ePOST:** 1 Jeongbohwa-gil (Bitgaram-dong), Naju, 58323, Coreia do Sul.

> Ignore mensagens de texto falsas afirmando que sua encomenda Korea Post não pode ser entregue sem pagar uma pequena taxa por um link não oficial. Eventuais tributos de importação sobre um envio `KR` são cobrados pelo correio ou portal aduaneiro do seu país, nunca por links aleatórios via SMS. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Rastreie uma encomenda Korea Post com o Peek

O [Peek](/) reconhece automaticamente com alta confiança qualquer número postal UPU S10 terminado em `KR` (`EM482917367KR` ou `RR482917367KR`, dígitos fictícios) como Korea Post, além de links colados de `trace.epost.go.kr` com `POST_CODE=` e de `service.epost.go.kr` com `sid1=`. Um número doméstico de 13 dígitos (`6482917364058`) é compatível com várias transportadoras: o Peek lista a Korea Post entre elas e pede para você escolher. Você também pode selecioná-la depois em "Trocar transportadora" na tela da encomenda (buscar "South Korea Post" ou "ePOST" também encontra), ou colar diretamente o link do ePOST.

O Peek consulta o rastreador EMS em inglês da Korea Post para números `KR` e o rastreador doméstico coreano do ePOST para códigos de 13 dígitos. Exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega" em envios domésticos, "Tentativa de entrega", "Entregue"), o texto da leitura com a agência postal ou aeroporto e o horário de entrega em pacotes domésticos. Leituras domésticas usam o horário da Coreia, e internacionais o fuso horário do local da leitura (por exemplo horário de Nova York em Nova York); o Peek converte para o relógio do seu dispositivo. O nome do destinatário informado pela Korea Post aparece em "Destinatário" na sua própria página do pacote, nunca em links compartilhados; o nome de quem assinou não aparece nos apps do Peek, que também omite nome do serviço, nome do remetente e telefones das agências. O rastreador da Korea Post não fornece data prevista de entrega, peso nem ponto de retirada.

Enquanto você mantém o pacote aberto no Peek, ou no dia da entrega com notificações ativadas, o Peek verifica até a cada 10 minutos; no dia em que uma encomenda doméstica mostra `배달준비`, a cada 2 minutos a partir das 6h no horário da Coreia enquanto o rastreador próprio da Korea Post responder. Nos demais casos, consulta de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, passa a consultar de hora em hora em qualquer modo, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da Korea Post não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia de saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Korea Post.", com o link "Pedir ajuda a Korea Post" para a Central de Atendimento do ePOST. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Korea Post."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek monitora apenas atualizações de rastreamento e não pode desembaraçar alfândega, redirecionar encomendas nem registrar solicitações EMS junto à Korea Post em seu nome.

## Perguntas sobre o rastreamento da Korea Post

### O que significam `EE...KR`, `EM...KR` e `RR...KR`?

São códigos postais internacionais UPU S10 de 13 caracteres enviados da Coreia do Sul: `EE` identifica documentos EMS, `EM` encomendas EMS de mercadorias e `RR` (ou outros prefixos `R`/`C`/`L`) correspondência registrada, K-Packet e encomendas internacionais.

### Por que minha encomenda EMS da Korea Post parou de atualizar depois de sair do aeroporto?

Depois que `Departure from the Airport` aparece na Coreia do Sul, o rastreamento permanece quieto até o correio do país de destino registrar a chegada da mala postal no seu centro de permuta de entrada (`Arrival at inward office of exchange`). Consulte o mesmo número `...KR` também no site do correio nacional do seu país.

### A própria Korea Post faz a entrega internacional na minha casa?

A Korea Post realiza a coleta, a triagem de exportação e o embarque aéreo a partir da Coreia do Sul, entregando depois o pacote (`Delivered to Destination Post`) ao serviço postal nacional do país de destino para o desembaraço aduaneiro e entrega final.

### Como entro em contato com o suporte da Korea Post?

Ligue para `1588-1300` dentro da Coreia do Sul (seg–sex `09:00–18:00` KST, fechado aos sábados e feriados), envie e-mail para `help@posa.or.kr` ou acesse a [Central de Atendimento ePOST](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm).

:::sources
- [Korea Post: English EMS Track/Confirm](https://trace.epost.go.kr/xtts/tt/epost/ems/ems_eng.jsp) – portal internacional de rastreamento EMS e correios parceiros
- [Korea Post (ePOST): Domestic Tracking](https://service.epost.go.kr/iservice/usr/trace/usrtrc001k01.jsp) – rastreamento doméstico de encomendas e registrados de 13 dígitos
- [Korea Post (ePOST): EMS Service Overview](https://ems.epost.go.kr/front.Introduction01.postal) – prefixos `EE` vs `EM`, prazos EMS, regras CN22/CN23, 100+ destinos EMS / 70+ EMS Premium e telefone `1588-1300`
- [Korea Post (ePOST): Shipping Conditions by Country](https://ems.epost.go.kr/front.EmsApplyGoCondition.postal) – limites EMS por país e observações aduaneiras
- [Korea Post (ePOST): Expected Delivery Days & Price Calculator](https://ems.epost.go.kr/front.EmsDeliveryDelivery09.postal) – calculadora de prazos e tarifas EMS, EMS Premium e encomendas
- [Korea Post (ePOST): Prohibited & Restricted Items](https://ems.epost.go.kr/front.Introduction04.postal) – restrições para envios internacionais
- [Korea Post (ePOST): Customer Centre](https://www.epost.go.kr/bbs.RetrieveBbsMain.comm) – `1588-1300`, `help@posa.or.kr`, horário seg–sex `09:00–18:00` KST e endereço em Naju (URL de ajuda vinculada pelo Peek)
- [Korea Post (ePOST): Main Portal](https://www.epost.go.kr/main.retrieveMainPage.comm) – visão geral de encomendas domésticas, registrados, EMS e K-Packet, e `+82-42-609-4295` para chamadas do exterior
- [Universal Parcel Scraper: korea-post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/korea-post) – detecção `KR` S10 e 13 dígitos, regras de link `trace.epost.go.kr` / `service.epost.go.kr`, mapas de status EMS em inglês e doméstico em coreano, fusos, campos removidos e URL de ajuda
:::
