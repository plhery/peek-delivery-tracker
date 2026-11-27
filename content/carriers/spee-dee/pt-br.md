---
title: Rastreio Spee-Dee Delivery: códigos SP, estados e ajuda
description: Rastreie encomendas Spee-Dee Delivery no Meio-Oeste dos EUA: códigos SP de 18 e 20 caracteres, códigos reutilizados, área atendida e telefone.
slug: spee-dee-tracking
published: 2026-11-27
updated: 2026-11-27
---

A Spee-Dee Delivery (*Spee-Dee Delivery Service, Inc.*) é uma transportadora familiar de segunda geração fundada por Don Weeres em 1978 como serviço de entregas sob demanda. Ela se apresenta como líder em entregas de última milha no Meio-Oeste dos Estados Unidos, com rede para o dia seguinte, além de carga fracionada (LTL), armazenagem, postos presenciais de postagem e devoluções EZR Returns ([speedeedelivery.com](https://speedeedelivery.com/)).

Você pode acompanhar um envio na [página Track a Shipment da Spee-Dee](https://speedeedelivery.com/track-a-shipment/) ou colar seu código `SP` na caixa acima.

## Formatos de código de rastreio Spee-Dee

O Peek reconhece os códigos da Spee-Dee como `SP` seguidos de 16 ou 18 dígitos:

| Formato (inventado) | O que é |
| --- | --- |
| `SP4829173605148291` | `SP` + 16 dígitos (18 caracteres no total) — código padrão Spee-Dee; note que a Spee-Dee recicla seriais de 16 dígitos ao longo do tempo. |
| `SP482917360514829173` | `SP` + 18 dígitos (20 caracteres no total) — formato mais longo da Spee-Dee. |

Ambos os formatos `SP` identificam a Spee-Dee com alta confiança e não conflitam com grandes transportadoras dos EUA como [UPS](carrier:ups), [FedEx](carrier:fedex) ou [USPS](carrier:usps). Se você colar um link `speedeedelivery.com/...?tracking=...`, o Peek extrai o código `SP` diretamente. Para outros padrões regionais e nacionais dos EUA, veja o [guia de formatos de código de rastreio](guide:tracking-number-formats).

## Área atendida pela Spee-Dee no Meio-Oeste

Segundo os [Mapas de Área de Serviço da Spee-Dee](https://speedeedelivery.com/services/service-area-maps/), a empresa faz entregas terrestres regulares em **todo** o território de seis estados do Meio-Oeste e em cidades e códigos postais selecionados de quatro estados vizinhos:

- **Cobertura estadual completa:** Illinois, Iowa, Minnesota, Dakota do Norte, Dakota do Sul e Wisconsin.
- **Cidades e códigos postais selecionados:** Kansas (incluindo os prefixos expandidos `660` e `666`), Michigan, Missouri (incluindo `634–635`, `644–646` e `650–653`) e Nebraska.

Para estimar prazos, a página Service Area Maps exibe um mapa *Days in Transit* para cada código postal de origem, e o arquivo Zip Transit File lista os dias de trânsito para todos os códigos postais atendidos.

## Significado dos status de rastreio Spee-Dee

A tabela de progresso da Spee-Dee traz três colunas — `Date / Time`, `Location` e `Activity` — da mais recente para a mais antiga, junto com um resumo superior exibindo `Status:`, `Delivered on:`, `Signed by:` e `Delivered to:` após a conclusão.

| Atividade Spee-Dee | O que significa |
| --- | --- |
| `ARRIVAL SCAN` | Registrada em um terminal ou centro de triagem da Spee-Dee na rede terrestre do Meio-Oeste. |
| `OUT FOR DELIVERY` *(frequentemente seguida do número da rota entre parênteses)* | Carregada em um caminhão local da Spee-Dee para entrega residencial ou comercial hoje. |
| `Delivered` | Entrega concluída; o resumo na página oficial também informa horário, endereço e assinatura quando coletada. |
| `No packages were found matching the barcode supplied.` | O código ainda não foi lido no sistema da Spee-Dee ou contém erro de digitação. |

### Códigos de 16 dígitos reutilizados

A Spee-Dee reaproveita códigos `SP` antigos de 16 dígitos em novos envios. Na página oficial bruta da Spee-Dee, linhas de uma encomenda entregue anteriormente ainda podem aparecer abaixo das leituras do seu pacote atual. Ao consultar um código reutilizado no site da Spee-Dee, observe as datas no topo da tabela e pare de ler ao encontrar um `Delivered` antigo de um envio anterior.

## Atrasos, ausência de leituras e reclamações

Alguns pontos úteis quando o rastreamento parece estranho:

- **Sem leituras ainda:** se a página responder `No packages were found matching the barcode supplied.`, a Spee-Dee ainda não escaneou o pacote ou há erro no número. Confira o código e pergunte ao vendedor se já houve postagem, ou consulte [quando o rastreio para de atualizar](guide:tracking-not-updating).
- **Limite de consultas por minuto:** o servidor da Spee-Dee limita buscas repetidas em sequência e pode devolver HTTP 503 temporariamente. Aguardar um minuto ou deixar o Peek atualizar automaticamente evita esse bloqueio.
- **Reclamações por não entrega ou dano:** o [formulário de atendimento da Spee-Dee](https://speedeedelivery.com/customer-service/) traz opções dedicadas **Claims – Non-Delivery** e **Claims – Damage**, e pede o **endereço completo de entrega** ao enviar dúvidas de rastreamento. Veja o [guia de entregue mas não recebida](guide:delivered-not-received) se constar entregue sem ter chegado.

## Como acompanhar Spee-Dee no Peek

Cole qualquer código Spee-Dee de 18 caracteres (`SP` + 16 dígitos) ou 20 caracteres (`SP` + 18 dígitos), ou um link de `speedeedelivery.com`, e o Peek reconhece a Spee-Dee imediatamente. Se precisar alterar a transportadora, use **Alterar** ao adicionar ou **Alterar transportadora** na tela do pacote.

O Peek consulta a página da Spee-Dee diretamente pelo [adaptador Spee-Dee de código aberto do universal-parcel-scraper](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee), recorrendo a uma rede remota quando o host recusa conexão direta. Em **códigos reutilizados**, ele mantém apenas as linhas posteriores à entrega anterior ocorrida um dia ou mais antes, remove comentários de depuração do HTML e o número da rota após `OUT FOR DELIVERY` e registra cidade/terminal e horário de cada leitura. A Spee-Dee informa horários locais sem fuso: o Peek interpreta cada leitura pelo fuso do local (Chicago para St. Cloud, Fargo, Milwaukee ou Sioux Falls; Detroit para Detroit), usando o da leitura vizinha se faltar local — ou Nova York — e exibe tudo no relógio do seu aparelho. O site da Spee-Dee não fornece data prevista de entrega nem peso.

O Peek mapeia `ARRIVAL SCAN` para **Em trânsito**, `OUT FOR DELIVERY` para **Saiu para entrega** e `Delivered` para **Entregue**, aplicando regras padrão às demais atividades.

Enquanto o pacote estiver aberto no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta até a cada 10 minutos; no dia em que a Spee-Dee exibir `OUT FOR DELIVERY`, a cada 2 minutos a partir das 6h no relógio da leitura mais recente — ou a cada 5 minutos se a última consulta precisou abrir navegador — enquanto o site oficial responder. Nos demais casos, verifica de hora em hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, consulta a cada hora, e a cada 6 horas após sete dias em "Etiqueta criada". Quando a Spee-Dee não responde e o Peek acompanha por serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da saída para entrega.

Quando o registro mais recente completa quatro dias e o pacote segue em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca a data daquela leitura (por exemplo, "Sem atualização de rastreamento desde seg 12 out") com a nota "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando Spee-Dee." e link para a página de atendimento da Spee-Dee. Após três semanas sem novidade, sugere consultar o vendedor enquanto continua verificando.

Nas preferências de notificação do Peek, escolha **Cada atualização**, **Etapas importantes** ou **Somente entrega**.

## Como entrar em contato com a Spee-Dee Delivery

A Spee-Dee destaca em sua [página de atendimento ao cliente](https://speedeedelivery.com/customer-service/) que quem liga fala diretamente com um atendente humano, sem menu eletrônico automatizado:

- **Telefone gratuito (EUA):** `800-862-5578`
- **Telefone local:** `320-251-6697`
- **Fax:** `320-251-1846` (fax LTL: `320-240-7995`)
- **Formulário on-line e reclamações:** [Spee-Dee Customer Service](https://speedeedelivery.com/customer-service/) (inclua o código `SP` e o endereço completo de entrega em dúvidas de rastreio)
- **Endereço físico:** Spee-Dee Delivery Service Inc., 4101 Clearwater Road, St. Cloud, MN 56301
- **Endereço postal:** P.O. Box 1417, St. Cloud, MN 56302
- **Sobre a empresa e rastreio:** [About Spee-Dee](https://speedeedelivery.com/about-us/) e [Track a Shipment](https://speedeedelivery.com/track-a-shipment/)

## Perguntas frequentes

### Por que minha página da Spee-Dee mostra uma entrega feita meses atrás?

A Spee-Dee reutiliza códigos `SP` de 16 dígitos ao longo do tempo, então a página oficial pode listar um envio antigo já concluído abaixo das leituras do seu pacote atual. O Peek detecta automaticamente esse limite e oculta as linhas de entregas anteriores.

### Quais estados dos EUA a Spee-Dee Delivery atende?

A Spee-Dee cobre integralmente Illinois, Iowa, Minnesota, Dakota do Norte, Dakota do Sul e Wisconsin, além de cidades e códigos postais selecionados no Kansas, Michigan, Missouri e Nebraska.

### A Spee-Dee Delivery mostra data prevista de entrega na página de rastreio?

Não. A página exibe leituras de chegada em terminais, `OUT FOR DELIVERY` e dados da entrega final, sem previsão de entrega. Você pode consultar o prazo terrestre típico por código postal nos [Mapas de Área de Serviço da Spee-Dee](https://speedeedelivery.com/services/service-area-maps/).

### O que devo informar ao contatar a Spee-Dee sobre uma encomenda?

Tenha em mãos o código `SP` completo de 18 ou 20 caracteres e o endereço completo de entrega — o formulário de atendimento da Spee-Dee pede explicitamente o endereço completo em consultas de rastreamento.

:::sources
- [Spee-Dee Delivery](https://speedeedelivery.com/)
- [Spee-Dee — About Us](https://speedeedelivery.com/about-us/)
- [Spee-Dee — Track a Shipment](https://speedeedelivery.com/track-a-shipment/)
- [Spee-Dee — Service Area Maps](https://speedeedelivery.com/services/service-area-maps/)
- [Spee-Dee — Customer Service](https://speedeedelivery.com/customer-service/)
- [universal-parcel-scraper Spee-Dee adapter](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/spee-dee)
:::
