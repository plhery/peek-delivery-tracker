---
title: Rastreamento BFE Express: códigos CK, WYB e status
description: Rastreie encomendas BFE Express para Argentina, Peru, Uruguai e EUA: códigos CK e WYB, desembaraço aduaneiro, status e e-mails regionais de atendimento.
slug: rastreamento-bfe-express
published: 2026-11-26
updated: 2026-11-26
---

A BFE Express (*BFEexpress*, operada pela Hongkong Bfe Info Tech Co Limited e fundada em 2005) é uma rede logística de e-commerce internacional e entrega de última milha que conecta vendedores internacionais — incluindo lojistas da Temu Argentina — a destinatários na Argentina, no Peru, no Uruguai e nos Estados Unidos. A empresa oferece frete internacional, desembaraço aduaneiro, armazenagem local e entrega hiperlocal de última milha rastreada em [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking).

Como a BFE Express gerencia a chegada internacional e o desembaraço aduaneiro antes de repassar os pacotes a depósitos regionais de última milha, uma mesma linha do tempo costuma combinar leituras de aeroporto e alfândega em inglês com leituras locais de entrega em espanhol.

## Formatos de código de rastreamento da BFE Express

O Peek aceita códigos da BFE Express de 6 a 50 letras e dígitos. O formulário de contato da BFE Express aceita estes formatos, por destino:

| Formato (inventado) | Destino e detalhes |
| --- | --- |
| `CK54829173604518` | `CK54` + 12 dígitos (16 caracteres) — Argentina; nenhuma outra transportadora no Peek usa este formato. |
| `CK548291736045182` | `CK54` + 13 dígitos (17 caracteres) — formato alternativo da Argentina (coincide em tamanho com [Colis Privé](carrier:colis-prive)). |
| `CK512829173604518` | `CK512` + 12 dígitos (17 caracteres) — formato do Peru (coincide em tamanho com [Colis Privé](carrier:colis-prive)). |
| `CK598829173604518` | `CK598` + 12 dígitos (17 caracteres) — formato do Uruguai (coincide em tamanho com [Colis Privé](carrier:colis-prive)). |
| `WYB482917360` | `WYB` + 9 dígitos (12 caracteres) — conhecimento de transporte do Peru (coincide com Purolator e [TNT Australia](carrier:tnt-au)). |

Os dígitos após `CK` indicam o destino: `54` para Argentina, `512` para Peru e `598` para Uruguai. Se você colar um link direto no formato `bfeexpress.com/track?track_number=...`, o Peek extrai o código e seleciona a BFE Express imediatamente. Para outros padrões internacionais, veja o [guia de formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da BFE Express

O rastreador da BFE Express exibe dois blocos de registros — o primeiro trecho internacional e a última milha — e o Peek lê ambos.

| Texto da BFE Express | O que significa |
| --- | --- |
| `Arrived at Airport of destination` | O voo com a sua encomenda pousou no país de destino. |
| `Transport to next facility` | Em deslocamento entre o terminal do aeroporto e o depósito local de desembaraço ou triagem. |
| `Customs declaration completed` | Desembaraço aduaneiro no destino concluído, antes do repasse para a última milha. |
| `Turned over to last mile facility` | Repassada da etapa de desembaraço da BFE para o depósito local entregador. |
| `Se ha recibido el paquete.` / `Tu paquete fue recibido por en: ...` | Recebida no depósito local de última milha da Argentina ou do Uruguai. |
| `En embarque` / `En transporte` / `Tu paquete está en viaje a ...` | Carregada e viajando entre depósitos domésticos locais rumo à sua cidade. |
| `Tu paquete se encuentra en ...` / `En espera para salir a reparto en ...` | Na agência entregadora local aguardando saída em rota de entrega. |
| `Reparto en curso` | Saiu hoje com o entregador local. |
| `Entregado` | Entregue ao destinatário. O status próprio da BFE traz `Delivered` ao lado. |

As linhas em inglês da BFE Express colocam o local primeiro, antes de uma vírgula (como `<Cidade>, Customs declaration completed`); as linhas em espanhol na Argentina colocam o país primeiro, e a última milha uruguaia informa o local após `en` ou `a`. Quando o próprio status da BFE marca `Delivered`, o Peek classifica a encomenda como entregue mesmo se a linha mais recente disser outra coisa.

## Desembaraço aduaneiro, transferência para última milha e atrasos

O site da BFE Express oferece envio de encomendas, frete internacional, desembaraço aduaneiro, armazenagem local e entrega de última milha, atendendo também vendedores da Temu Argentina. Alguns pontos ajudam a entender o ritmo do rastreamento:

- **Antes do aeroporto de destino:** se o rastreador da BFE Express ainda não mostrar nada, confira a página do pedido com o vendedor e leia [quando o rastreamento para de atualizar](guide:tracking-not-updating).
- **Alfândega e última milha:** `Customs declaration completed` e `Turned over to last mile facility` podem aparecer com uma hora de intervalo no mesmo local, seguidos um dia depois pelo recebimento no depósito local (`Se ha recibido el paquete.` ou `Tu paquete fue recibido por en: ...`). Se o pacote pausar na alfândega de destino, consulte o [guia de alfândega](guide:customs).
- **Dia previsto de entrega:** a BFE Express pode informar um dia previsto de entrega enquanto o pacote está a caminho, e o Peek exibe essa data. O Peek remove a previsão assim que a encomenda é entregue, devolvida ou fica pronta para retirada, ou quando surge uma leitura posterior àquela data.
- **Marcada como entregue ou danificada:** a [página de Customer Support](https://www.bfeexpress.com/contactUS) da BFE Express trata ambos os casos no FAQ e recomenda ter o código de rastreamento em mãos ao contatar a equipe local. Veja também o [guia de entregue mas não recebida](guide:delivered-not-received).

Para encomendas domésticas na Argentina entregues diretamente por transportadoras locais, compare com a [OCA](carrier:oca) e a [Andreani](carrier:andreani).

## Acompanhe a BFE Express com o Peek

Cole seu código `CK...` ou `WYB...` no Peek, que acompanha mais de 4.500 transportadoras. Só pelo formato, o Peek não sabe que o código é da BFE Express; por isso, ao adicionar, ele consulta a BFE Express ("Consultando serviços de rastreamento…") e mostra "BFE Express está com esta encomenda." quando a BFE Express reconhece o código. Para códigos `CK54` de 16 caracteres, consulta só a BFE Express. Para códigos `CK` de 17 caracteres, consulta também a [Colis Privé](carrier:colis-prive): primeiro na França e, nos outros países, só quando nem a BFE Express nem os serviços de rastreamento reconhecem o código. Para códigos `WYB`, consulta BFE Express, [TNT Australia](carrier:tnt-au) e Purolator: primeiro a BFE Express na Argentina, no Peru, no Uruguai e nos EUA, primeiro a Purolator no Canadá e primeiro a TNT Australia nos outros países. Para acompanhar a encomenda na BFE Express desde o início, toque em "Trocar" e selecione BFE Express em "Compatível com este código", ou use "Trocar transportadora" em uma encomenda já adicionada. Um link `bfeexpress.com/track?track_number=...` vai direto para a BFE Express.

O Peek consulta o rastreamento da própria BFE Express por meio do [adaptador BFE Express do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express), de código aberto. Ele lê os dois blocos de registros em ordem cronológica, usa a cidade, unidade ou país como local e mantém o dia previsto de entrega e o país de destino. Horários com fuso GMT preservam esse instante exato; horários sem fuso usam o fuso do local (Buenos Aires para Ezeiza, Lima para Lima, Montevidéu para Montevidéu), leituras sem local usam o fuso da leitura vizinha e, na falta dele, o horário de Buenos Aires, sempre convertidos para o relógio do seu dispositivo. Endereços completos de rua e fotos de comprovante de entrega do portal da BFE ficam de fora.

O Peek exibe as linhas da BFE Express como **Em trânsito**, exceto `Reparto en curso`, exibida como **Saiu para entrega**, e `Entregado`, exibida como **Entregue**. Ele também marca a encomenda como **Entregue** quando o próprio status da BFE indica `Delivered`.

Enquanto você mantiver a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até a cada 10 minutos; no dia em que a BFE Express mostra `Reparto en curso`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador da BFE Express responder. Fora isso, o Peek verifica a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, verifica a cada hora de qualquer forma, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a BFE Express não responde e o Peek acompanha a encomenda apenas por serviços de rastreamento, verifica a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando a leitura mais recente tem quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca o dia dessa leitura, como "Sem atualizações desde seg 12 out". A página da encomenda mostra então "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando BFE Express.", com o link "Pedir ajuda a BFE Express" para a página Contact Us da BFE Express. Após três semanas sem nova movimentação, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando BFE Express."

Use as opções de alerta do Peek — **Cada movimentação**, **Etapas importantes** ou **Só a entrega** — para escolher quais eventos enviam notificações.

## Como entrar em contato com a BFE Express

A BFE Express lista e-mails regionais de atendimento na [página Contact Us](https://www.bfeexpress.com/contactUS), todos com atendimento de **segunda a sexta, das 9h às 18h** (exceto feriados):

- **Argentina:** `info@ar.bfeexpress.com`
- **Peru:** `info@pe.bfeexpress.com`
- **Uruguai:** `info@uy.bfeexpress.com`
- **Estados Unidos:** `info@us.bfeexpress.com`
- **Paraguai:** `info_py@bfeexpress.com`
- **Outras regiões atendidas (Colômbia, Equador, Sérvia, Dominica):** `info@other.bfeexpress.com`
- **Site oficial e rastreamento:** [bfeexpress.com](https://www.bfeexpress.com/) e [bfeexpress.com/tracking](https://www.bfeexpress.com/tracking)

Tenha seu código completo `CK...` ou `WYB...` em mãos ao escrever para a equipe do seu país.

## Perguntas frequentes

### Por que meu rastreamento da BFE Express começa no aeroporto de destino sem leituras anteriores?

O rastreador da BFE Express pode iniciar em `Arrived at Airport of destination`, na chegada do voo ao país de destino. Para a etapa anterior, confira o despacho na página do pedido junto ao vendedor.

### Por que as movimentações da BFE Express misturam inglês e espanhol?

A BFE Express registra a chegada ao aeroporto internacional, o desembaraço aduaneiro e o repasse para última milha em inglês, enquanto o depósito doméstico responsável pela entrega final lança as movimentações em espanhol (terminando em `Reparto en curso` e `Entregado`).

### O que significam os números `54`, `512` e `598` nos códigos `CK`?

Eles identificam o país de destino: `CK54...` é usado para a Argentina (16 ou 17 caracteres), `CK512...` para o Peru (17 caracteres) e `CK598...` para o Uruguai (17 caracteres). Envios ao Peru também podem usar o formato `WYB` + 9 dígitos (12 caracteres).

### E se a BFE Express não encontrar meu código?

Verifique se falta algum caractere no código `CK` ou `WYB` e pergunte ao vendedor se a encomenda já foi entregue à BFE Express.

:::sources
- [BFE Express](https://www.bfeexpress.com/)
- [BFE Express Tracking](https://www.bfeexpress.com/tracking)
- [BFE Express Customer Support](https://www.bfeexpress.com/contactUS)
- [universal-parcel-scraper BFE Express adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/bfe-express)
:::
