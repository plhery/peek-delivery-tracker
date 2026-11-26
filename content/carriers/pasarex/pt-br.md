---
title: Rastreamento PasarEX: códigos AMZPSR, alfândega e suporte
description: Rastreie importações PasarEX na Colômbia, Chile, Panamá e Barbados: códigos AMZPSR da Amazon, status aduaneiros, prazos PQR e telefones de suporte.
slug: pasarex-tracking
published: 2026-11-26
updated: 2026-11-26
---

A PasarEX (marca comercial da *PASAR EXPRESS SAS*, empresa colombiana fundada em 1993) oferece courier expresso, carga aérea e entrega de última milha para plataformas de e-commerce. Seus sites cobrem **Colômbia**, **Chile**, **Panamá** e **Barbados**, e seus serviços incluem um centro logístico em Miami e transporte multimodal a partir da China.

A PasarEX atua como despachante aduaneira das encomendas, de modo que seu rastreamento percorre desde o manifesto até a chegada consolidada de importação, alfândega, armazéns e entrega local.

## Formatos de código de rastreamento da PasarEX

O Peek aceita códigos PasarEX de 6 a 30 letras e dígitos com pelo menos um dígito. Um formato pertence exclusivamente à PasarEX:

| Formato (fictício) | O que é |
| --- | --- |
| `AMZPSR482917360` | `AMZPSR` + 9 dígitos (15 caracteres) — código da Amazon consultado no rastreador da PasarEX; nenhuma outra transportadora no Peek usa esse padrão. |

Se a confirmação do pedido incluir uma URL direta de rastreamento (`tracking.pasarex.com/?awb=...`), colá-la no Peek extrai o conhecimento e seleciona a PasarEX na hora. Para outros padrões de e-commerce, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats) e o [guia de pedidos da Amazon](guide:amazon-orders).

## O que significam os status de rastreamento da PasarEX

A PasarEX agrupa as leituras sob categorias amplas em seu rastreador (`COMMENTS`, `IN CUSTOMS PROCESS`, `RECEIVED FOR DELIVERY`, `IN TRANSIT`, `IN WAREHOUSE`, `OUT FOR DISTRIBUTION`, `DELIVERED`, `SHIPPING WITH DELIVERY ISSUE`), trazendo cada evento em espanhol ou inglês junto com a cidade e data/hora.

| Texto da leitura PasarEX | O que significa |
| --- | --- |
| `Orden de pedido recibida - paquete no arribado` / `Information (Manifest) of the shipment received.` | O manifesto eletrônico chegou à PasarEX antes de o pacote físico dar entrada no centro de importação. |
| `Carga consolidada para importación recibida` / `Reception of consolidated cargo in import.` | A carga aérea consolidada de importação chegou à unidade de entrada da PasarEX (como Bogotá) antes da alfândega. |
| `Entry to station for customs processing.` | O pacote deu entrada no setor aduaneiro para desembaraço de importação. |
| `Cleared by customs by PasarEx.` / `Cleared by customs and transferred to OPS.` | A PasarEX liberou a remessa na alfândega e a transferiu para as operações domésticas. |
| `Ingresó a bodega` / `Package received in warehouse.` / `Reception of consolidated cargo at a station.` | Registrada em um armazém ou unidade de destino da PasarEX entre trechos da rota doméstica. |
| `Package in transit` / `Package in ground transit` | Em trânsito rodoviário ou transferência doméstica entre unidades da PasarEX. |
| `Applies to cargo that may experience delivery delays due to disturbances or protests` | Aviso operacional alertando sobre possíveis atrasos por distúrbios ou protestos locais; o envio continua em seguida. |
| `Shipment left to local route for final delivery` | Saiu em rota local com o entregador para entrega final hoje. |
| `Shipment delivered in perfect condition` | Entregue no endereço do destinatário. |

## Desembaraço aduaneiro, reclamações PQR e expectativas de entrega

Como a PasarEX realiza o desembaraço aduaneiro de importações internacionais antes da distribuição local:

- **Antes da chegada física:** enquanto o rastreamento mostrar apenas `Information (Manifest) of the shipment received.` ou `Orden de pedido recibida - paquete no arribado`, o pedido foi registrado mas o volume ainda não chegou à PasarEX. É normal haver uma pausa até surgir `Reception of consolidated cargo in import.` ou `Carga consolidada para importación recibida`. Veja o [guia de alfândega](guide:customs) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Regras de PQR e indenização na Colômbia (Resolução CRC 5050 de 2016):** em [Suporte PasarEX Colômbia](https://pasarex.com/co/soporte/), a Pasar Express SAS publica os prazos legais previstos na Lei 1369/2009 e na Resolução 5050/2016. Destinatários devem registrar reclamações por violação ou dano em até **5 dias úteis** após o recebimento, enquanto remetentes têm **10 dias corridos** (envios domésticos) ou **6 meses** (internacionais) para perda, violação ou dano (contratos corporativos podem ter regras próprias). A reclamação exige cópia do conhecimento aéreo (*Guía Aérea*), fatura comercial, fatura da Pasar Express, lista de itens, descrição da embalagem e fotos. A PasarEX normalmente responde PQRs em até **15 dias úteis** (prorrogáveis se for necessária investigação adicional), e o usuário tem **10 dias úteis** após a notificação para recorrer (*recurso de reposición y en subsidio de apelación*).

## Rastreando PasarEX com o Peek

Cole seu código ou um link `tracking.pasarex.com/?awb=...` no [Peek](/), que acompanha mais de 4.500 transportadoras. O link vai direto para a PasarEX. Para um código isolado, o Peek nunca consulta a PasarEX de imediato: pode consultar outras transportadoras compatíveis e serviços de rastreamento ("Consultando serviços de rastreamento…"), vinculando à PasarEX somente se um serviço indicar exclusivamente a PasarEX com etapas datadas. Por isso, toque em "Alterar" e escolha PasarEX (em "Compatível com este código" para códigos `AMZPSR`), ou use "Alterar transportadora" em um pacote já adicionado.

O rastreador da PasarEX exige confirmação de reCAPTCHA em cada consulta, então o Peek faz a leitura pelo [adaptador open-source PasarEX do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex) com apoio de navegador. O Peek coleta o texto, a cidade e a hora de cada leitura e ordena do mais recente ao mais antigo, em vez de separar pelos cabeçalhos da PasarEX. Como a PasarEX informa horários locais sem fuso, o Peek aplica o fuso da cidade (Nova York para Miami; Bogotá para Bogotá, Medellín e Cali; Santiago, Panamá ou Barbados para esses locais), usa o fuso da leitura vizinha quando não há cidade ou o de Bogotá por padrão, exibindo tudo no relógio do seu dispositivo. O rastreador da PasarEX não publica data estimada de entrega, peso nem comprovante de entrega.

O Peek organiza os eventos da PasarEX em etapas limpas: **Etiqueta criada** para recebimento de manifesto antes da chegada física, **Em trânsito** da chegada consolidada de importação até liberação aduaneira, entrada em armazém e trânsito terrestre (o fluxo da PasarEX pula direto do manifesto para a chegada consolidada, sem etapa separada de **Recebida pela transportadora**), **Na alfândega** durante o processamento aduaneiro, **Saiu para entrega** ao sair em rota local e **Entregue** na entrega final.

O Peek consulta o rastreador da PasarEX no máximo uma vez por hora — o menor intervalo permitido pela PasarEX, inclusive após falha, estando o pacote aberto ou não — e não faz verificações a cada 2 minutos no dia da entrega. Após sete dias em "Etiqueta criada", consulta a cada 6 horas. Quando o rastreador da PasarEX não responde e o Peek acompanha apenas por serviços de rastreamento, consulta de hora em hora e a cada 3 horas após um dia sem novidades.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualização de rastreamento desde seg 12 out". A página então informa "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando PasarEX." Após três semanas sem novas leituras, mostra "Nenhuma novidade há mais de três semanas. Se ainda não chegou, fale com o vendedor. O Peek continua consultando PasarEX."

Escolha **Cada leitura**, **Etapas importantes** ou **Apenas entrega** nas configurações de alertas do Peek.

## Como contatar a PasarEX

A PasarEX divulga telefones e e-mails por país:

- **Colômbia ([pasarex.com/co/soporte](https://pasarex.com/co/soporte/)):** `+57 601 384 7000`, `+57 601 390 6000` (também linha PQR em Bogotá), `+57 601 291 6500`, `+57 300 919 7521`, `+57 300 919 7532`; e-mails `sac@pasar.net` (suporte) e `pqr@pasar.net` (PQR/reclamações)
- **Chile ([pasarex.com/cl](https://pasarex.com/cl/)):** `+56 2 2571 7500`; e-mail `clgestionpe@pasar.net`
- **Panamá ([pasarex.com/pa](https://pasarex.com/pa/)):** `+507 836 0399`; e-mails `pagestionpe@pasar.net` e `pqr@pasar.net`
- **Barbados:** `+1 246 4370277`, `+1 305 7042435`
- **Institucional:** [PasarEX Colômbia](https://pasarex.com/co/) e [Nosotros](https://pasarex.com/co/nosotros/)

## Perguntas frequentes

### O que é um código de rastreamento `AMZPSR`?

`AMZPSR` seguido por 9 dígitos (15 caracteres ao todo, como `AMZPSR482917360`) é um número da Amazon consultado no rastreador da PasarEX. Nenhuma outra transportadora no Peek usa esse formato, mas o Peek não consulta a PasarEX sozinho: escolha PasarEX em "Compatível com este código".

### Por que meu pacote PasarEX diz `Orden de pedido recibida - paquete no arribado`?

Significa que a PasarEX recebeu o manifesto da remessa, mas o pacote físico ainda não chegou à unidade de importação de destino da PasarEX. As leituras voltam assim que o voo consolidado chega.

### Por que o Peek atualiza a PasarEX com menos frequência do que outras transportadoras?

A PasarEX permite consultar seu rastreador no máximo uma vez por hora, inclusive após falha, por isso o Peek consulta de hora em hora. Além disso, o rastreador exige verificação reCAPTCHA a cada consulta.

### Qual é o prazo para abrir reclamação por dano na PasarEX na Colômbia?

Pela Resolução CRC 5050/2016 da Colômbia publicada em [pasarex.com/co/soporte](https://pasarex.com/co/soporte/), destinatários têm **5 dias úteis** após a entrega para reclamar por dano ou conteúdo faltante, enquanto remetentes têm 10 dias corridos (doméstico) ou 6 meses (internacional) por perda ou dano.

:::sources
- [PasarEX Colômbia](https://pasarex.com/co/)
- [PasarEX Colômbia — Soporte y PQR](https://pasarex.com/co/soporte/)
- [PasarEX Colômbia — Nosotros](https://pasarex.com/co/nosotros/)
- [PasarEX Chile](https://pasarex.com/cl/)
- [PasarEX Panamá](https://pasarex.com/pa/)
- [Adaptador PasarEX do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/pasarex)
:::
