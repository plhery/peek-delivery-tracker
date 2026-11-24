---
title: Rastreio Redur: códigos de 14 dígitos, Espanha e apoio
description: Acompanha uma encomenda Redur em Espanha, Portugal ou Europa: códigos de 14 dígitos, registos de delegação e entrega, levantamento, Eurodis e apoio.
slug: rastreio-redur
published: 2026-11-24
updated: 2026-11-24
---

A **Redur** (*Redur S.A.*) é um operador espanhol de transporte expresso e logística com mais de 50 anos de história, sediado em Algete, Madrid ([redur.es](https://redur.es/)). Operando uma rede 100 % própria de mais de 61 delegações (*delegaciones*) e 3 000 colaboradores em Espanha continental, ilhas espanholas e Portugal, a Redur é também membro fundador da **Eurodis**, a rede europeia de transporte combinado de encomendas e paletes. Além de envios nacionais urgentes B2B/B2C e partidas rodoviárias internacionais diárias pela Europa, a Redur assegura distribuição farmacêutica certificada GDP (*Farma 15-25*) e logística contratual. Podes acompanhar um envio em [redur.es/track-trace/](https://redur.es/track-trace/) ou colar o teu número Redur de 14 dígitos ou ligação de rastreio na caixa acima.

## Números de rastreio Redur (formato de 14 dígitos)

Os números de rastreio Redur (*N° Tracking*) têm 14 dígitos sem letras nem espaços:

| Formato (inventado) | O que é |
| --- | --- |
| `58492716380419` | Número padrão de rastreio de encomenda Redur de 14 dígitos (*NTRACKING*). |
| `61738492051628` | Outro código de rastreio Redur de 14 dígitos para rotas em Espanha, Portugal ou Eurodis. |

Em [redur.es/track-trace/](https://redur.es/track-trace/), o formulário pesquisa também por número de expedição e por referências do remetente ou parceiro, enquanto o rastreio automático no Peek usa o número de 14 dígitos (`NTRACKING`) ou uma ligação direta `redur.es/track-trace/?...&valor=`. O Peek não reconhece a Redur apenas pelo número: um número isolado de 14 dígitos vai para a DPD, [SEUR](carrier:seur) e outras transportadoras (SEUR primeiro em Espanha), pelo que deves colar a ligação Redur ou escolher Redur. Consulta os [formatos de números de rastreio](guide:tracking-number-formats).

## O que significam os estados de rastreio da Redur

A página de rastreio da Redur apresenta uma barra de progresso de 7 passos juntamente com linhas de registo por delegação (em espanhol em `redur.es/track-trace/`, ou em inglês na página inglesa):

| A Redur indica (ES / EN) | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `DOCUMENTACION` / `DATA ENTERING` | Documentação eletrónica de envio transmitida pelo remetente antes da entrada física na delegação (datada apenas ao dia). | «Etiqueta criada» |
| `ENTRADA PLAZA ORIGEN` / `INBOUND ORIGIN DEPOT` | Primeiro registo físico de entrada na delegação de origem Redur. | «Aceite pela transportadora» |
| `SALIDA PLAZA ORIGEN` (`OUTBOUND ORIGIN DEPOT`), `ENTRADA/SALIDA PLAZA TRANSITO` (`INBOUND/OUTBOUND TRANSIT DEPOT`), `ENTRADA PLAZA DESTINO` (`INBOUND FINAL DEPOT`) | Em circulação entre delegações de origem, trânsito e destino em Espanha ou Portugal. | «Em trânsito» |
| `CARGA CAMION T. INTERNACIONAL` / `TRUCK LOAD INTERNATIONAL TRAFFIC` | Carregada num camião para outro país, onde um parceiro faz a entrega. | «Em trânsito» |
| `PENDIENTE DE FECHA CONCERTADA`, `FECHA DE ENTREGA CONCERTADA` / `BOOKING AGREED DATE` | A aguardar marcação de data de entrega com o destinatário, ou data de entrega acordada confirmada. | «Em trânsito» |
| `SALIDA A REPARTO` / `OUT FOR DELIVERY` | Em distribuição hoje na carrinha local Redur. | «Em distribuição» |
| `RECOGERÁN EN AGENCIA` | Retida na delegação Redur de destino pronta para levantamento pelo destinatário. | «Pronta para levantamento» |
| `DESTINATARIO AUSENTE`, `INCORRECT ADDRESS`, `CERRADO PERMANENTE/TEMPORAL` | Tentativa de entrega falhada por ausência, morada incorreta ou estabelecimento fechado. | «Tentativa de entrega» |
| `DEVOLUCIÓN A ORIGEN` | Em devolução ao remetente após falha na entrega ou recusa. | «Devolvida ao remetente» |
| `ENTREGA` / `DELIVERY` | Entregue ao destinatário (ou confirmada pelo passo final ativo na barra de progresso Redur). | «Entregue» |

## Serviços em Espanha, Portugal e Europa pela Eurodis

De acordo com [Redur](https://redur.es/) e [Serviços Redur](https://redur.es/servicios/):

- **Doméstico Ibéria (*Servicio Doméstico*):** distribuição urgente de encomendas, paletes e carga combinada em Espanha continental, ilhas e Portugal através de mais de 61 delegações próprias, apoiada por avisos de entrega por e-mail e SMS *Redur Connected* e pela ferramenta *Maia*.
- **Internacional Europa (*Servicio Internacional*):** partidas diárias para toda a Europa em envios B2B e B2C através da rede parceira **Eurodis**. Assim que surge `CARGA CAMION T. INTERNACIONAL`, uma transportadora parceira local da Eurodis no país de destino completa a entrega final.
- **Farmacêutica com temperatura controlada (*Farma 15-25*):** distribuição certificada GDP a 15–25 °C para envios de saúde e farmácia.

## Entrega falhada, marcação pendente e encomendas desaparecidas

- **`PENDIENTE DE FECHA CONCERTADA` (a aguardar data combinada):** a Redur aguarda combinar contigo um dia de entrega antes de expedir o volume; `FECHA DE ENTREGA CONCERTADA` ou `BOOKING AGREED DATE` indica data combinada. Verifica telefone e e-mail ou liga para a Redur com o teu número de 14 dígitos.
- **`DESTINATARIO AUSENTE`, `INCORRECT ADDRESS` ou `RECOGERÁN EN AGENCIA`:** contacta rapidamente o vendedor ou o apoio ao cliente Redur com o teu código de 14 dígitos para confirmar a morada, marcar nova tentativa ou consultar horários de levantamento na delegação.
- **Envios atrasados, danificados ou extraviados:** avisa primeiro a loja online ou o remetente (que detém o contrato com a Redur e pode apresentar reclamação formal) e confirma com a Redur em que delegação está a encomenda. Consulta [entregue mas não recebida](guide:delivered-not-received), [encomenda danificada](guide:damaged-parcel), [encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender) e [por que o rastreio deixa de atualizar](guide:tracking-not-updating).

## Apoio ao cliente Redur e sede em Madrid

Contactos oficiais publicados em [Redur Contacto](https://redur.es/contacto/) e [Serviços Redur](https://redur.es/servicios/):

- **Telefone central (Espanha):** `(+34) 916 20 46 10` (fax `91 628 15 60`).
- **E-mail:** `info@redur.es`.
- **Formulário de contacto e diretório de delegações:** [redur.es/contacto/](https://redur.es/contacto/).
- **Sede (*Servicios Centrales Redur*):** *Redur S.A.*, Carretera Algete–Madrid Km 2.5 (P.I. Redur), 28110 Algete, Madrid, Espanha.

> Tem cuidado com SMS falsas a pedir uma pequena taxa online para reagendar uma entrega em Espanha ou Portugal. Confirma sempre primeiro avisos de entrega em [redur.es/track-trace/](https://redur.es/track-trace/) ou junto do vendedor. Consulta [SMS falsas de entrega](guide:delivery-scam-texts).

## Seguir uma encomenda Redur com o Peek

Cola uma ligação direta `redur.es/track-trace/?...&valor=` no [Peek](/) e o Peek reconhece a Redur automaticamente. Como números isolados de 14 dígitos (`58492716380419` ou `61738492051628`, inventados) são partilhados por muitas transportadoras europeias e a Redur não tem prefixo exclusivo, introduz o teu número de 14 dígitos e toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda para escolher **Redur** (pesquisar «Redur Spain», «Redur Portugal» ou «Redur S.A.» também a encontra); o Peek confirma-o diretamente no rastreio Redur e mostra «Redur tem esta encomenda.» assim que confirmado.

O Peek lê o rastreio da Redur e mostra a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Devolvida ao remetente», «Entregue»), o texto Redur e a delegação. Quando o último passo da barra de progresso da Redur se ilumina, o Peek apresenta a encomenda como entregue. A Redur indica horas sem fuso: o Peek data cada registo no fuso horário da delegação (Madrid para `ALGETE`, Lisboa para `LISBOA`, Canárias para `LAS PALMAS`) e um registo de delegação não identificada no fuso do registo vizinho, mostrando tudo na hora do teu dispositivo; `DOCUMENTACION` mantém apenas o dia. O Peek não guarda o número de volumes. O rastreio Redur não indica data estimada de entrega, peso, nome do serviço, hora própria de entrega nem número de parceiro.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a Redur mostra `SALIDA A REPARTO` ou `OUT FOR DELIVERY`, de 2 em 2 minutos a partir das 6h00 no relógio da delegação do registo mais recente enquanto o rastreio Redur responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio Redur não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia da distribuição enquanto tens a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg., 12/10». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Redur.», com uma ligação «Pede ajuda a Redur» para a página de contacto da Redur. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a Redur.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas atualizações de rastreio e não pode marcar data de entrega, alterar moradas nem contactar a Redur por ti.

## Perguntas sobre o rastreio Redur

### Como é um número de rastreio Redur?

Os números de rastreio Redur têm 14 dígitos sem letras nem espaços (por exemplo `58492716380419`, inventado).

### O que significa `PENDIENTE DE FECHA CONCERTADA` na Redur?

Significa que a Redur está à espera de combinar contigo um dia de entrega antes de expedir a encomenda.

### O que significa `RECOGERÁN EN AGENCIA`?

Significa que a encomenda está guardada na delegação local da Redur (*delegación*), pronta para a levantares.

### Como contactar o apoio ao cliente da Redur?

Liga para a central da Redur em Espanha através do `(+34) 916 20 46 10`, escreve para `info@redur.es` ou usa a página de contacto em [redur.es/contacto/](https://redur.es/contacto/).

:::sources
- [Redur: site oficial](https://redur.es/) – 50+ anos, 61+ delegações Espanha e Portugal, 3 000 colaboradores, membro fundador Eurodis, *Maia* e *Redur Connected*
- [Redur: Track & Trace](https://redur.es/track-trace/) – rastreio público de envios
- [Redur: Servicios](https://redur.es/servicios/) – *Servicio Doméstico*, *Servicio Internacional*, *Farma 15-25* (GDP) e *Logística integral*
- [Redur: Contacto](https://redur.es/contacto/) – `(+34) 916 20 46 10`, `info@redur.es` e sede em Algete (Madrid) (URL de ajuda ligado pelo Peek)
- [Universal Parcel Scraper: redur](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/redur) – formato 14 dígitos, regra de ligação `redur.es/track-trace/`, mapa de textos espanhol/inglês, deteção de entrega pela barra de progresso e registos datados no fuso horário de cada delegação
:::
