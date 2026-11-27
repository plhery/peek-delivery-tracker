---
title: Rastreamento Redur: códigos de 14 dígitos, status e ajuda
description: Rastreie encomendas da Redur na Espanha, em Portugal ou na Europa: códigos de 14 dígitos, status de depósitos e entrega, retirada em agência, Eurodis e ajuda.
slug: redur-tracking
published: 2026-11-26
updated: 2026-11-26
---

A **Redur** (*Redur S.A.*) é uma operadora espanhola de transporte expresso e logística com mais de 50 anos de história, sediada em Algete, Madri ([redur.es](https://redur.es/)). Operando uma rede 100% própria com mais de 61 filiais (*delegaciones*) e 3.000 colaboradores na Espanha continental, ilhas espanholas e Portugal, a Redur também é membro fundador da **Eurodis**, a rede europeia de transporte combinado de encomendas e paletes. Além das entregas urgentes B2B/B2C na Península Ibérica e saídas rodoviárias diárias pela Europa, a Redur oferece distribuição farmacêutica certificada GDP (*Farma 15-25*) e logística contratual. Você pode rastrear uma remessa em [redur.es/track-trace/](https://redur.es/track-trace/) ou colar seu código Redur de 14 dígitos ou link de rastreamento na caixa acima.

## Códigos de rastreamento da Redur (formato de 14 dígitos)

Os números de rastreamento da Redur (*N° Tracking*) têm 14 dígitos numéricos, sem letras nem espaços:

| Formato (inventado) | O que é |
| --- | --- |
| `58492716380419` | Código padrão de rastreamento de 14 dígitos da Redur (*NTRACKING*). |
| `61738492051628` | Exemplo alternativo de código de 14 dígitos da Redur para Espanha, Portugal ou rotas Eurodis. |

Em [redur.es/track-trace/](https://redur.es/track-trace/), o formulário também permite buscar por número de expedição e referências do remetente ou de parceiros, enquanto o rastreamento automático no Peek usa o número de 14 dígitos (`NTRACKING`) ou um link direto `redur.es/track-trace/?...&valor=`. O Peek não identifica a Redur apenas pelo código: um número isolado de 14 dígitos é enviado para DPD, [SEUR](carrier:seur) e outras transportadoras (SEUR primeiro na Espanha); portanto, cole o link da Redur ou escolha Redur. Veja [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da Redur

A página de rastreamento da Redur exibe uma barra de progresso em 7 etapas acompanhada de leituras por filial (em espanhol em `redur.es/track-trace/` ou em inglês na página em inglês):

| A Redur exibe (ES / EN) | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Documentação eletrônica enviada pelo remetente antes da entrada física na filial (com data apenas do dia). | "Etiqueta criada" |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | Primeira leitura física de entrada no depósito de origem da Redur. | "Recebida pela transportadora" |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | Em movimentação entre depósitos de origem, trânsito e destino na Espanha ou em Portugal. | "Em trânsito" |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Carregada em caminhão para outro país, onde uma parceira concluirá a entrega. | "Em trânsito" |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | Aguardando agendamento da data de entrega com o destinatário, ou data de entrega combinada confirmada. | "Em trânsito" |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | No veículo de entrega local da Redur hoje. | "Saiu para entrega" |
| `RECOGERÁN EN AGENCIA` | Aguardando retirada pelo destinatário na filial de destino da Redur. | "Pronta para retirada" |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Entrega não realizada por ausência, erro no endereço ou estabelecimento fechado. | "Tentativa de entrega" |
| `DEVOLUCIÓN A ORIGEN` | Em retorno ao remetente após falha na entrega ou recusa. | "Devolvida ao remetente" |
| `ENTREGA` / `DELIVERY` | Entregue ao destinatário (ou confirmada pela etapa final ativa na barra de progresso da Redur). | "Entregue" |

## Serviços na Espanha, em Portugal e na rede europeia Eurodis

De acordo com [Redur](https://redur.es/) e [Serviços da Redur](https://redur.es/servicios/):

- **Serviço Doméstico Ibérico (*Servicio Doméstico*):** distribuição urgente de encomendas, paletes e carga combinada na Espanha continental, ilhas e Portugal através das mais de 61 filiais próprias da Redur, com notificações por e-mail e SMS *Redur Connected* e a plataforma de gestão *Maia*.
- **Serviço Internacional Europa (*Servicio Internacional*):** saídas diárias pela Europa para remessas B2B e B2C pela rede parceira **Eurodis**. Após aparecer `CARGA CAMION T. INTERNACIONAL`, uma transportadora parceira local da Eurodis realiza a entrega final no país de destino.
- **Farma com temperatura controlada (*Farma 15-25*):** distribuição entre 15 e 25 °C em conformidade com GDP para remessas farmacêuticas e de saúde.

## Entrega não realizada, agendamento pendente e extravios

- **`PENDIENTE DE FECHA CONCERTADA` (aguardando data combinada):** a Redur aguarda combinar um dia de entrega com você antes de o pacote sair; `FECHA DE ENTREGA CONCERTADA` ou `BOOKING AGREED DATE` indica que a data já foi agendada. Confira seu telefone e e-mail ou ligue para a Redur com seu código de 14 dígitos.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` ou `RECOGERÁN EN AGENCIA`:** contate rapidamente o vendedor ou o atendimento da Redur informando seu código de 14 dígitos para confirmar o endereço, agendar nova tentativa ou conferir o horário de retirada na filial.
- **Remessas atrasadas, danificadas ou desaparecidas:** avise primeiro a loja virtual ou o remetente (que detém o contrato com a Redur e pode abrir um pedido de indenização formal) e confirme com a Redur em qual filial o pacote está. Veja [entregue, mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender) e [por que o rastreamento para de atualizar](guide:tracking-not-updating).

## Atendimento ao cliente da Redur e sede em Madri

Contatos oficiais divulgados em [Redur Contacto](https://redur.es/contacto/) e [Serviços da Redur](https://redur.es/servicios/):

- **Telefone central (Espanha):** `(+34) 916 20 46 10` (fax `91 628 15 60`).
- **E-mail:** `info@redur.es`.
- **Formulário de contato e diretório de filiais:** [redur.es/contacto/](https://redur.es/contacto/).
- **Sede (*Servicios Centrales Redur*):** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madri, Espanha.

> Desconfie de mensagens SMS falsas cobrando uma pequena taxa on-line para reagendar uma entrega na Espanha ou em Portugal. Verifique sempre qualquer aviso em [redur.es/track-trace/](https://redur.es/track-trace/) ou junto ao vendedor. Veja [golpes de SMS de entrega](guide:delivery-scam-texts).

## Acompanhe uma encomenda da Redur com o Peek

Cole um link direto `redur.es/track-trace/?...&valor=` no [Peek](/) para que o aplicativo reconheça a Redur automaticamente. Como números isolados de 14 dígitos (`58492716380419` ou `61738492051628`, inventados) são compartilhados por muitas transportadoras europeias e a Redur não possui prefixo exclusivo, informe seu código de 14 dígitos e toque em "Trocar" ao adicionar (ou "Trocar transportadora" na tela da encomenda) para escolher **Redur** (pesquisar "Redur Spain", "Redur Portugal" ou "Redur S.A." também encontra); o Peek consulta então diretamente o rastreador da Redur e exibe "Redur está com esta encomenda." ao confirmar.

O Peek lê o rastreador da Redur e mostra a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Devolvida ao remetente", "Entregue"), a redação original da Redur e a filial. Quando a última etapa da barra de progresso da Redur aparece ativa, o Peek marca o volume como entregue. A Redur envia horários sem fuso: o Peek interpreta cada leitura no fuso horário da filial (Madri para `ALGETE`, Lisboa para `LISBOA`, Ilhas Canárias para `LAS PALMAS`) e usa o fuso da leitura vizinha se não conseguir localizar o depósito, convertendo tudo para o relógio do seu aparelho; `DOCUMENTACION` mantém apenas o dia. O Peek não armazena a quantidade de volumes. O rastreador da Redur não informa data estimada de entrega, peso, nome do serviço, horário próprio de entrega nem código de parceiro.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek verifica até a cada 10 minutos; no dia em que a Redur registra `SALIDA A REPARTO` ou `OUT FOR DELIVERY`, consulta a cada 2 minutos a partir das 6h no horário do depósito da leitura mais recente, enquanto o rastreador da Redur responder. Fora isso, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidade, consulta de hora em hora de qualquer forma, e a cada 6 horas depois de sete dias em "Etiqueta criada". Quando o rastreador da Redur não responde e o Peek acompanha somente por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia quieto e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza a data dessa leitura, como "Sem atualizações desde seg 12 out". A página do pacote então exibe "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Redur.", com o link "Pedir ajuda a Redur" para a página de contato da Redur. Após três semanas sem novas leituras, mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando Redur."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas monitora atualizações de rastreamento e não pode agendar entregas, alterar o endereço nem contatar a Redur em seu nome.

## Perguntas sobre o rastreamento da Redur

### Qual é o formato do código de rastreamento da Redur?

Os códigos da Redur têm 14 dígitos numéricos sem letras nem espaços (por exemplo `58492716380419`, inventado).

### O que significa `PENDIENTE DE FECHA CONCERTADA` na Redur?

Significa que a Redur está aguardando combinar uma data de entrega com você antes de enviar a encomenda para a rota.

### O que significa `RECOGERÁN EN AGENCIA`?

Significa que sua encomenda está guardada na filial local da Redur (*delegación*), pronta para você retirar.

### Como entrar em contato com o atendimento da Redur?

Ligue para a central da Redur na Espanha em `(+34) 916 20 46 10`, escreva para `info@redur.es` ou use a página de contato em [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: Official Website](https://redur.es/) – mais de 50 anos, mais de 61 filiais na Espanha e em Portugal, 3.000 funcionários, membro fundador da Eurodis, *Maia* e *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – rastreador público de remessas
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) e *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` e sede em Algete (Madri) (URL de ajuda vinculada pelo Peek)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – formato de 14 dígitos, regra de link `redur.es/track-trace/`, mapeamento de textos espanhol/inglês, leitura de entrega na barra de progresso e horários datados no fuso de cada filial
:::
